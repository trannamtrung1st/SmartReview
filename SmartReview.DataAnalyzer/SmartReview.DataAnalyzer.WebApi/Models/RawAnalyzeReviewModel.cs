using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using System.Xml.Serialization;

namespace SmartReview.DataAnalyzer.WebApi.Models
{
    [XmlRoot("analyzeReviewModel")]
    public class RawAnalyzeReviewModel
    {
        [JsonProperty("reviews")]
        [XmlArray("reviews")]
        [XmlArrayItem("item")]
        public List<RawReviewModel> Reviews { get; set; }
    }

    [XmlRoot("rawReview")]
    public class RawReviewModel
    {
        [JsonProperty("review_id")]
        [XmlElement("reviewId")]
        public int ReviewId { get; set; }
        [JsonProperty("review_text")]
        [XmlElement("reviewText")]
        public string ReviewText { get; set; }
        [JsonProperty("rating")]
        [XmlElement("rating")]
        public float Rating { get; set; }
    }
}
