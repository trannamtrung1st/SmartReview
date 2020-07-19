using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using TNT.Core.Http.DI;
using TNT.Core.Helpers.DI;
using System.Data.SqlClient;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Http.Extensions;
using Microsoft.AspNetCore.Http;
using System.IO;
using System.Net.Mime;
using Microsoft.Net.Http.Headers;
using Microsoft.AspNetCore.Mvc.Formatters;
using SmartReview.DataAnalyzer.WebApi.Controllers;
using SmartReview.DataAnalyzer.Sentiment;
using SmartReview.DataAnalyzer.Classification;
using SmartReview.DataAnalyzer.WebApi.Models;

namespace PetID.ClassifyWebApi.Controllers
{
    [Route("api/reviews")]
    [ApiController]
    [InjectionFilter]
    public class ReviewsController : BaseController
    {
        //[Inject]
        //private ReviewSentimentAnalyzer _sEngine;
        [Inject]
        private ReviewCategorizer _cEngine;

        [Produces(MediaTypeNames.Application.Xml, MediaTypeNames.Application.Json,
            Type = typeof(ReviewAnalysisResultModel))]
        [Consumes(MediaTypeNames.Application.Xml, MediaTypeNames.Application.Json)]
        [HttpPost("")]
        public IActionResult Analyze([FromBody]RawAnalyzeReviewModel model, float min_score = 0.2f)
        {
            var results = new List<ReviewAnalysisModel>();
            foreach (var r in model.Reviews)
            {
                var aModel = new ReviewAnalysisModel();
                aModel.ReviewCode = r.ReviewCode;
                if (!string.IsNullOrWhiteSpace(r.ReviewText))
                {
                    //var sOutput = _sEngine.Predict(new SmartReview.DataAnalyzer.Sentiment.Models.ModelInput
                    //{
                    //    Text = r.ReviewText
                    //});
                    aModel.IsPositive = /*sOutput.Prediction ||*/ r.Rating > 3.5;
                    if (!aModel.IsPositive)
                        aModel.Output = _cEngine.Predict(new SmartReview.DataAnalyzer.Classification.Models.ModelInput
                        {
                            Review = r.ReviewText
                        }, minScore: min_score);
                }
                else
                {
                    aModel.IsPositive = r.Rating > 3.5;
                }
                results.Add(aModel);
            }
            return Ok(new ReviewAnalysisResultModel()
            {
                Results = results
            });
        }

    }
}