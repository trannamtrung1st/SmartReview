using SmartReview.DataAnalyzer.Classification;
using SmartReview.DataAnalyzer.Sentiment;
using SmartReview.DataAnalyzer.Sentiment.Models;
using System;
using System.IO;

namespace SmartReview.DataAnalyzer.ConsoleClient
{
    class Program
    {
        const string SENTIMENT_MODEL_PATH = @"T:\FPT\STUDY\SUMMER2020\PRX\Project\SmartReview\Source\SmartReview.DataAnalyzer\SmartReview.DataAnalyzer.Sentiment\MLModel.zip";
        const string CLASSIFY_MODEL_PATH = @"T:\FPT\STUDY\SUMMER2020\PRX\Project\SmartReview\Source\SmartReview.DataAnalyzer\SmartReview.DataAnalyzer.Classification\MLModel.zip";
        static void Main(string[] args)
        {
            //TrainYelpSentiment();
            //TestSentimentAnalysis();
            //TrainMacDonaldReview();
            //TestClassification();
            TestIntegration();
        }

        static void TestClassification()
        {
            var engine = new ReviewCategorizer(CLASSIFY_MODEL_PATH);
            var output = engine.Predict(new Classification.Models.ModelInput
            {
                Review = "We had lunch at the Mix, Greec... a very big plate but no taste. Everything was very greasy. The young people that Work there are very sweet but too eager to clean the table. A Customer needs space. No need to remove your glass or napkin. As the standing en watching. It is clean and nice decorated!!"
            });
            foreach (var r in output.TopOutputs)
                Console.WriteLine(r.Label + "-" + r.Score);
        }

        static void TestSentimentAnalysis()
        {
            var engine = new ReviewSentimentAnalyzer(SENTIMENT_MODEL_PATH);
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
            var trainer = new ReviewSentimentTrainer();
            //trainer.PreprocessYelpTsv(data, tsvPath);
            trainer.CreateModel(tsvPath, SENTIMENT_MODEL_PATH);
        }

        static void TrainMacDonaldReview()
        {
            var csvPath = @"T:\FPT\STUDY\SUMMER2020\PRX\Project\SmartReview\macdonald.csv";
            var data = FileProcessor.ReadCsv(csvPath, false);
            var tsvPath = @"T:\FPT\STUDY\SUMMER2020\PRX\Project\SmartReview\macdonald.tsv";
            var trainer = new ReviewCategorizeTrainer();
            var cSet = trainer.PreprocessReviewTsvThenReturnCategories(data, tsvPath);
            File.WriteAllLines(@"T:\FPT\STUDY\SUMMER2020\PRX\Project\SmartReview\categories.txt", cSet);
            trainer.CreateModel(tsvPath, CLASSIFY_MODEL_PATH);
        }

        static void TestIntegration()
        {
            var rData = "We had lunch at the Mix, Greec... a very big plate but no taste. Everything was very greasy. The young people that Work there are very sweet but too eager to clean the table. A Customer needs space. No need to remove your glass or napkin. As the standing en watching. It is clean and nice decorated!!";
            var sEngine = new ReviewSentimentAnalyzer(SENTIMENT_MODEL_PATH);
            var sOutput = sEngine.Predict(new ModelInput
            {
                Text = rData
            });
            Console.WriteLine(sOutput.Prediction + "-" + sOutput.Score);
            var cEngine = new ReviewCategorizer(CLASSIFY_MODEL_PATH);
            var cOutput = cEngine.Predict(new Classification.Models.ModelInput
            {
                Review = rData
            });
            foreach (var r in cOutput.TopOutputs)
                Console.WriteLine(r.Label + "-" + r.Score);
        }
    }
}
