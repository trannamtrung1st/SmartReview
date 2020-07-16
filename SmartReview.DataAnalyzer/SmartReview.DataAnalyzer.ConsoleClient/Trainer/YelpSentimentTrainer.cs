using SmartReview.DataAnalyzer.ConsoleClient.Processing;
using System;
using System.Collections.Generic;
using System.Text;

namespace SmartReview.DataAnalyzer.ConsoleClient.Trainer
{
    public class YelpSentimentTrainer
    {
        public const int STARS_COL_IDX = 3;
        public const int REVIEW_COL_IDX = 4;

        public void PreprocessYelpTsv(List<string[]> data, string outputPath)
        {
            var processedData = new List<string[]>();
            var isHeader = true;
            foreach (var d in data)
            {
                var d3 = d[STARS_COL_IDX];
                var d4 = d[REVIEW_COL_IDX];
                if (isHeader)
                {
                    isHeader = false;
                    processedData.Add(new[] { d3, d4 });
                    continue;
                }
                var stars = float.Parse(d3);
                var sentiment = stars > 3 ? true : false;
                var review = d4;
                processedData.Add(new[] { sentiment.ToString(), review });
            }
            FileProcessor.SaveTsv(processedData, outputPath);
        }

    }
}
