using SmartReview.DataAnalyzer.Sentiment;
using SmartReview.DataAnalyzer.Sentiment.Models;
using System;

namespace SmartReview.DataAnalyzer.ConsoleClient
{
    class Program
    {
        const string MODEL_PATH = @"T:\FPT\STUDY\SUMMER2020\PRX\Project\SmartReview\Source\SmartReview.DataAnalyzer\SmartReview.DataAnalyzer.Sentiment\MLModel.zip";
        static void Main(string[] args)
        {
            //TrainYelpSentiment();
            TestSentimentAnalysis();
        }

        static void TestSentimentAnalysis()
        {
            var trainer = new YelpSentimentTrainer();
            var engine = trainer.GetPredictionEngine(MODEL_PATH);
            var output = engine.Predict(new ModelInput
            {
                Text = "This service give me a good mood. I wanna go there more"
            });
            Console.WriteLine(output.Prediction + "-" + output.Score);
        }

        static void TrainYelpSentiment()
        {
            //var csvPath = @"T:\FPT\STUDY\SUMMER2020\PRX\Project\SmartReview\yelp.csv";
            //var data = FileProcessor.ReadCsv(csvPath, false);
            var tsvPath = @"T:\FPT\STUDY\SUMMER2020\PRX\Project\SmartReview\yelp.tsv";
            var trainer = new YelpSentimentTrainer();
            //trainer.PreprocessYelpTsv(data, tsvPath);
            trainer.CreateModel(tsvPath, MODEL_PATH);
        }
    }
}
