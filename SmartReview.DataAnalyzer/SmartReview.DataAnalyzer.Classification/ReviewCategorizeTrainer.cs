using Microsoft.ML;
using SmartReview.DataAnalyzer.Classification.Models;
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

        public PredictionEngine<ModelInput, ModelOutput> GetPredictionEngine(string modelPath)
        {
            ITransformer mlModel = _mlContext.Model.Load(modelPath, out var modelInputSchema);
            var predEngine = _mlContext.Model.CreatePredictionEngine<ModelInput, ModelOutput>(mlModel);
            return predEngine;
        }

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

        private static MLContext _mlContext = new MLContext(seed: 1);
        private static ITransformer _trainedModel;
        static IDataView _trainingDataView;
        // </SnippetDeclareGlobalVariables>
        public void CreateModel(string trainDataPath, string outputPath)
        {
            // Create MLContext to be shared across the model creation workflow objects
            // Set a random seed for repeatable/deterministic results across multiple trainings.
            // <SnippetCreateMLContext>
            // </SnippetCreateMLContext>

            // STEP 1: Common data loading configuration
            // CreateTextReader<GitHubIssue>(hasHeader: true) - Creates a TextLoader by inferencing the dataset schema from the GitHubIssue data model type.
            // .Read(_trainDataPath) - Loads the training text file into an IDataView (_trainingDataView) and maps from input columns to IDataView columns.
            Console.WriteLine($"=============== Loading Dataset  ===============");

            // <SnippetLoadTrainData>
            _trainingDataView = _mlContext.Data.LoadFromTextFile<ModelInput>(trainDataPath, hasHeader: true);
            // </SnippetLoadTrainData>

            Console.WriteLine($"=============== Finished Loading Dataset  ===============");

            // <SnippetSplitData>
            //   var (trainData, testData) = _mlContext.MulticlassClassification.TrainTestSplit(_trainingDataView, testFraction: 0.1);
            // </SnippetSplitData>

            // <SnippetCallProcessData>
            var pipeline = ProcessData();
            // </SnippetCallProcessData>

            // <SnippetCallBuildAndTrainModel>
            var trainingPipeline = BuildAndTrainModel(_trainingDataView, pipeline);
            // </SnippetCallBuildAndTrainModel>
            SaveModelAsFile(_mlContext, outputPath, _trainingDataView.Schema, _trainedModel);
        }

        public static IEstimator<ITransformer> ProcessData()
        {
            Console.WriteLine($"=============== Processing Data ===============");
            // STEP 2: Common data process configuration with pipeline data transformations
            // <SnippetMapValueToKey>
            var pipeline = _mlContext.Transforms.Conversion.MapValueToKey(inputColumnName: "policies_violated", outputColumnName: "Label")
                            // </SnippetMapValueToKey>
                            // <SnippetFeaturizeText>
                            .Append(_mlContext.Transforms.Text.FeaturizeText(inputColumnName: "review", outputColumnName: "Features"))
                            // </SnippetFeaturizeText>
                            //Sample Caching the DataView so estimators iterating over the data multiple times, instead of always reading from file, using the cache might get better performance.
                            // <SnippetAppendCache>
                            .AppendCacheCheckpoint(_mlContext);
            // </SnippetAppendCache>

            Console.WriteLine($"=============== Finished Processing Data ===============");

            // <SnippetReturnPipeline>
            return pipeline;
            // </SnippetReturnPipeline>
        }

        public static IEstimator<ITransformer> BuildAndTrainModel(IDataView trainingDataView, IEstimator<ITransformer> pipeline)
        {
            // STEP 3: Create the training algorithm/trainer
            // Use the multi-class SDCA algorithm to predict the label using features.
            //Set the trainer/algorithm and map label to value (original readable state)
            // <SnippetAddTrainer>
            var trainingPipeline = pipeline.Append(_mlContext.MulticlassClassification.Trainers.SdcaMaximumEntropy("Label", "Features"))
                    .Append(_mlContext.Transforms.Conversion.MapKeyToValue("PredictedLabel"));
            // </SnippetAddTrainer>

            // STEP 4: Train the model fitting to the DataSet
            Console.WriteLine($"=============== Training the model  ===============");

            // <SnippetTrainModel>
            _trainedModel = trainingPipeline.Fit(trainingDataView);
            // </SnippetTrainModel>
            Console.WriteLine($"=============== Finished Training the model Ending time: {DateTime.Now.ToString()} ===============");

            // (OPTIONAL) Try/test a single prediction with the "just-trained model" (Before saving the model)
            Console.WriteLine($"=============== Single Prediction just-trained-model ===============");

            // <SnippetReturnModel>
            return trainingPipeline;
            // </SnippetReturnModel>
        }

        private static void SaveModelAsFile(MLContext mlContext,
            string outputPath,
            DataViewSchema trainingDataViewSchema, ITransformer model)
        {
            // <SnippetSaveModel>
            mlContext.Model.Save(model, trainingDataViewSchema, outputPath);
            // </SnippetSaveModel>

            Console.WriteLine("The model is saved to {0}", outputPath);
        }

    }
}
