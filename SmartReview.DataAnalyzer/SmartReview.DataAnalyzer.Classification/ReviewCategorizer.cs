using Microsoft.ML;
using Microsoft.ML.Data;
using SmartReview.DataAnalyzer.Classification.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace SmartReview.DataAnalyzer.Classification
{
    public class ReviewCategorizer
    {
        protected readonly MLContext mlContext;
        protected readonly PredictionEngine<ModelInput, ModelOutput> engine;
        public ReviewCategorizer(string modelPath)
        {
            mlContext = new MLContext(seed: 1);
            ITransformer mlModel = mlContext.Model.Load(modelPath, out var modelInputSchema);
            engine = mlContext.Model.CreatePredictionEngine<ModelInput, ModelOutput>(mlModel);
        }

        public ModelOutputViewModel Predict(ModelInput input, float minScore = 0.2f)
        {
            // Use model to make prediction on input data
            ModelOutput result = engine.Predict(input);
            var labelNames = new List<string>();
            var column = engine.OutputSchema.GetColumnOrNull("Label");
            if (column.HasValue)
            {
                VBuffer<ReadOnlyMemory<char>> vbuffer = new VBuffer<ReadOnlyMemory<char>>();
                column.Value.GetKeyValues(ref vbuffer);

                foreach (ReadOnlyMemory<char> denseValue in vbuffer.DenseValues())
                    labelNames.Add(denseValue.ToString());
            }
            var topOutputs = result.Score.Select((s, i) => new TopOutput
            {
                Label = labelNames[i],
                Score = s
            }).OrderByDescending(o => o.Score).Where(o => o.Score >= minScore).ToList();
            return new ModelOutputViewModel
            {
                Prediction = result.Category,
                TopOutputs = topOutputs
            };
        }
    }
}
