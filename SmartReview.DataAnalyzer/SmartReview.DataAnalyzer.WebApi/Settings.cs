using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace SmartReview.DataAnalyzer.WebApi
{
    public class Settings
    {
        public string SentimentAnalyzerModelPath { get; set; }
        public string CategorizerModelPath { get; set; }
        private static Settings _instance;
        public static Settings Instance
        {
            get
            {
                if (_instance == null) _instance = new Settings();
                return _instance;
            }
        }
    }
}
