using Newtonsoft.Json;
using SmartReview.DataAnalyzer.Classification.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using System.Xml.Serialization;

namespace SmartReview.DataAnalyzer.WebApi.Models
{
    [XmlRoot("reviewAnalysis")]
    public class ReviewAnalysisModel
    {
        [XmlElement("reviewCode")]
        [JsonProperty("review_code")]
        public string ReviewCode { get; set; }
        [XmlElement("prediction")]
        [JsonProperty("prediction")]
        public bool IsPositive { get; set; }

        [XmlElement("badReviewDetail")]
        [JsonProperty("bad_review_detail")]
        public ModelOutputs Output { get; set; }
    }
}
