using Microsoft.ML.Data;
using System;
using System.Collections.Generic;
using System.Text;

namespace SmartReview.DataAnalyzer.Classification.Models
{
    public class ModelInput
    {
        [ColumnName("policies_violated")]
        [LoadColumn(0)]
        public string Category { get; set; }
        [ColumnName("review")]
        [LoadColumn(1)]
        public string Review { get; set; }
    }
}
