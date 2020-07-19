/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smartreview.business.services;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.xml.bind.JAXBException;
import org.apache.hc.client5.http.fluent.Request;
import org.apache.hc.client5.http.fluent.Response;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.HttpStatus;
import org.apache.hc.core5.http.io.entity.StringEntity;
import smartreview.business.Settings;
import smartreview.business.dtos.ReviewCategoryDTO;
import smartreview.business.dtos.ReviewDTO;
import smartreview.business.models.AnalyzeReviewModel;
import smartreview.business.models.CountModel;
import smartreview.business.models.ListReviewModel;
import smartreview.business.models.ReviewAnalysisResult;
import smartreview.data.daos.BusinessReviewDAO;
import smartreview.data.daos.ReviewCategoryDAO;
import smartreview.data.models.Business;
import smartreview.data.models.BusinessReview;
import smartreview.data.models.CategoriesOfReviews;
import smartreview.data.models.ReviewCategory;
import smartreview.helper.HttpHelper;
import smartreview.helper.StreamHelper;
import smartreview.helper.StringHelper;
import smartreview.helper.XMLHelper;

/**
 *
 * @author TNT
 */
public class ReviewService {

    protected BusinessReviewDAO reviewDAO;
    protected ReviewCategoryDAO reviewCategoryDAO;
    protected EntityManager entityManager;

    public ReviewService(EntityManager entityManager, BusinessReviewDAO reviewDAO, ReviewCategoryDAO reviewCategoryDAO) {
        this.reviewCategoryDAO = reviewCategoryDAO;
        this.entityManager = entityManager;
        this.reviewDAO = reviewDAO;
    }

    public List<BusinessReview> getReviewsOfBusiness(Integer bId, int page, int limit, CountModel countModel) {
        page = page - 1;
        String whereClause = "WHERE [businessId]=?bId\n";
        //count query
        String countQuery = "SELECT COUNT(code) FROM BusinessReview " + whereClause;
        Query query = reviewDAO.nativeQuery(countQuery);
        query = query.setParameter("bId", bId);
        Integer totalItems = (Integer) query.getSingleResult();
        Integer totalPages = totalItems / limit + 1;
        totalPages = (totalItems % limit) == 0
                ? totalPages - 1 : totalPages;
        countModel.setItemsPerPage(limit);
        countModel.setTotalItems(totalItems);
        countModel.setTotalPages(totalPages);

        //get list query
        String sql = "SELECT * FROM BusinessReview\n" + whereClause
                + "ORDER BY [reviewDate] DESC\n"
                + "OFFSET ?offset ROWS\n"
                + "FETCH NEXT ?limit ROWS ONLY";
        query = reviewDAO.nativeQuery(sql, BusinessReview.class);
        query = query.setParameter("bId", bId);
        query = query.setParameter("offset", page * limit);
        query = query.setParameter("limit", limit);
        List<BusinessReview> list = query.getResultList();
        return list;
    }

    public ListReviewModel toListReviewModel(List<BusinessReview> entities, CountModel countModel, Map<String, ReviewCategory> cateMap) {
        ListReviewModel o = new ListReviewModel();
        List<ReviewDTO> dtos = entities.stream().map((t) -> {
            ReviewDTO dto = new ReviewDTO();
            dto.copyFrom(t);
            List<ReviewCategoryDTO> list = toListReviewCategoryDTO(t.getCategoriesOfReviewsCollection(), cateMap);
            dto.setCategories(list);
            return dto;
        }).collect(Collectors.toList());
        o.setList(dtos);
        o.setCount(countModel);
        return o;
    }

    public List<ReviewCategoryDTO> toListReviewCategoryDTO(Collection<CategoriesOfReviews> entities, Map<String, ReviewCategory> cateMap) {
        return Arrays.asList(entities.toArray(new CategoriesOfReviews[entities.size()])).stream().map((t) -> {
            ReviewCategoryDTO dto = new ReviewCategoryDTO();
            dto.copyFrom(cateMap.get(t.getCategoryCode().getCode()));
            return dto;
        }).collect(Collectors.toList());
    }

    public boolean analyzeReviews(Map<String, BusinessReview> rawReviews, float minScore) throws Exception {
        String url = Settings.BASE_API_URL + "/api/reviews?min_score=" + minScore;
        AnalyzeReviewModel model = new AnalyzeReviewModel();
        AnalyzeReviewModel.Reviews reviews = new AnalyzeReviewModel.Reviews();
        List<AnalyzeReviewModel.Reviews.Item> items = reviews.getItem();
        model.setReviews(reviews);
        for (Map.Entry<String, BusinessReview> entry : rawReviews.entrySet()) {
            String key = entry.getKey();
            BusinessReview value = entry.getValue();
            AnalyzeReviewModel.Reviews.Item e = new AnalyzeReviewModel.Reviews.Item();
            e.setRating(value.getRating());
            e.setReviewCode(key);
            e.setReviewText(value.getReviewContent());
            items.add(e);
        }
        String modelXml = XMLHelper.marshall(model, AnalyzeReviewModel.class);
        HttpEntity entity = new StringEntity(modelXml, ContentType.APPLICATION_XML);
        Response resp = Request.post(url).addHeader("Accept", "application/xml")
                .body(entity).execute();
        CloseableHttpResponse httpResp = (CloseableHttpResponse) resp.returnResponse();
        String reason = httpResp.getReasonPhrase();
        String contentStr = StreamHelper.readStringFromStream(httpResp.getEntity().getContent());
        if (httpResp.getCode() == HttpStatus.SC_OK) {
            Map<String, ReviewCategory> cateMap = new HashMap<>();
            ReviewAnalysisResult result = XMLHelper.unmarshallDocXml(contentStr, ReviewAnalysisResult.class);
            result.getResults().getItem().forEach((t) -> {
                BusinessReview br = rawReviews.get(t.getReviewCode());
                br.setIsPositive(t.isPrediction());
                if (!br.getIsPositive()) {
                    List<CategoriesOfReviews> list = new ArrayList<>();
                    t.getBadReviewDetail().getTopOutputs().getTopOutput().forEach((o) -> {
                        CategoriesOfReviews e = new CategoriesOfReviews();
                        ReviewCategory cate;
                        if (!cateMap.containsKey(o.getLabel())) {
                            cate = reviewCategoryDAO.findById(ReviewCategory.class, o.getLabel());
                            cateMap.put(cate.getCode(), cate);
                        }
                        cate = cateMap.get(o.getLabel());
                        e.setCategoryCode(cate);
                        e.setReviewId(br);
                        list.add(e);
                    });
                    br.setCategoriesOfReviewsCollection(list);
                }
            });
            return true;
        }
        return false;
    }

    public BusinessReview findBusinessReviewByCode(String code) {
        String sql = "SELECT * FROM BusinessReview WHERE code=?code";
        Query query = reviewDAO.nativeQuery(sql, BusinessReview.class).setParameter("code", code);
        List<BusinessReview> list = query.getResultList();
        return list.size() > 0 ? list.get(0) : null;
    }

    public boolean reviewCodeExists(String code) {
        String sql = "SELECT COUNT(code) FROM BusinessReview WHERE code=?code";
        Query query = reviewDAO.nativeQuery(sql).setParameter("code", code);
        Integer count = (Integer) query.getSingleResult();
        return count > 0;
    }

    public BusinessReview createBusinessReview(BusinessReview entity) {
        return reviewDAO.create(entity);
    }

}
