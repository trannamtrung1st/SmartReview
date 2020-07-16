using SmartReview.DataAnalyzer.ConsoleClient.Processing;
using SmartReview.DataAnalyzer.ConsoleClient.Trainer;
using SmartReview_DataAnalyzerML.Model;
using System;

namespace SmartReview.DataAnalyzer.ConsoleClient
{
    class Program
    {
        static void Main(string[] args)
        {
            //TrainYelpSentiment();
            TestSentimentAnalysis();
        }

        static void TestSentimentAnalysis()
        {
            var output = ConsumeModel.Predict(new ModelInput
            {
                Text = "If you just want to not be hungry anymore eat here otherwise keep looking. The food is very unimpressive."
            });
            Console.WriteLine(output.Prediction + "-" + output.Score);
        }

        static void TrainYelpSentiment()
        {
            var csvPath = @"T:\FPT\STUDY\SUMMER2020\PRX\Project\SmartReview\yelp.csv";
            var data = FileProcessor.ReadCsv(csvPath, false);
            var tsvPath = @"T:\FPT\STUDY\SUMMER2020\PRX\Project\SmartReview\yelp.tsv";
            var trainer = new YelpSentimentTrainer();
            trainer.PreprocessYelpTsv(data, tsvPath);
        }
    }
}
