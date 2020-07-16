using Microsoft.ML;
using Microsoft.ML.Data;
using SmartReview.DataAnalyzer.Sentiment.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace SmartReview.DataAnalyzer.Sentiment
{
    public class ReviewSentimentAnalyzer
    {
        protected readonly MLContext mlContext;
        protected readonly PredictionEngine<ModelInput, ModelOutput> engine;
        public ReviewSentimentAnalyzer(string modelPath)
        {
            mlContext = new MLContext(seed: 1);
            ITransformer mlModel = mlContext.Model.Load(modelPath, out var modelInputSchema);
            engine = mlContext.Model.CreatePredictionEngine<ModelInput, ModelOutput>(mlModel);
        }

        public ModelOutput Predict(ModelInput input)
        {
            var output = engine.Predict(input);
            return output;
        }
    }
}
