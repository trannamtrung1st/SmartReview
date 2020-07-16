using Microsoft.ML.Data;
using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Text;
using System.Xml.Serialization;

namespace SmartReview.DataAnalyzer.Classification.Models
{
    public class ModelOutput
    {
        [ColumnName("PredictedLabel")]
        public string Category;
        public float[] Score;
    }

    [XmlRoot("modelOutput")]
    public class ModelOutputs
    {
        [XmlElement("prediction")]
        [JsonProperty("prediction")]
        public string Prediction { get; set; }
        [XmlArray("topOutputs")]
        [XmlArrayItem("topOutput")]
        [JsonProperty("top_outputs")]
        public List<TopOutput> TopOutputs { get; set; }
    }

    [XmlRoot("topOutput")]
    public class TopOutput
    {
        [XmlElement("label")]
        public string Label { get; set; }
        [XmlElement("score")]
        public float Score { get; set; }
    }
}
