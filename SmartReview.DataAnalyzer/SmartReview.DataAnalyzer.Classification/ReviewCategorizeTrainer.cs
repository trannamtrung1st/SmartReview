using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace SmartReview.DataAnalyzer.Classification
{
    public class ReviewCategorizeTrainer
    {
        public const int CATEGORY_COL_IDX = 5;
        public const int REVIEW_COL_IDX = 9;

        public IEnumerable<string> PreprocessReviewTsvThenReturnCategories(List<string[]> data, string outputPath)
        {
            var processedData = new List<string[]>();
            var cSet = new HashSet<string>();
            var isHeader = true;
            foreach (var d in data)
            {
                var categories = d[CATEGORY_COL_IDX];
                var review = d[REVIEW_COL_IDX];
                if (isHeader)
                {
                    isHeader = false;
                    processedData.Add(new[] { categories, review });
                    continue;
                }
                var cSplit = categories.Split('\r').Where(o => !string.IsNullOrWhiteSpace(o));
                foreach (var c in cSplit)
                {
                    processedData.Add(new[] { c, review });
                    cSet.Add(c);
                }
            }
            FileProcessor.SaveTsv(processedData, outputPath);
            return cSet;
        }

    }
}
