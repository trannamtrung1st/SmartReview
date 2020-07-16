using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using System.Xml.Serialization;

namespace SmartReview.DataAnalyzer.WebApi.Models
{
    [XmlRoot("reviewAnalysisResult")]
    public class ReviewAnalysisResultModel
    {
        [XmlArray("results")]
        [XmlArrayItem("item")]
        [JsonProperty("results")]
        public List<ReviewAnalysisModel> Results { get; set; }
    }
}
