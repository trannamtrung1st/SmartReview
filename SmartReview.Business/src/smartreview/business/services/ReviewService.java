/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smartreview.business.services;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
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
import smartreview.business.models.AnalyzeReviewModel;
import smartreview.business.models.ReviewAnalysisResult;
import smartreview.data.daos.BusinessReviewDAO;
import smartreview.data.daos.ReviewCategoryDAO;
import smartreview.data.models.BusinessReview;
import smartreview.data.models.CategoriesOfReviews;
import smartreview.data.models.ReviewCategory;
import smartreview.helper.HttpHelper;
import smartreview.helper.StreamHelper;
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
