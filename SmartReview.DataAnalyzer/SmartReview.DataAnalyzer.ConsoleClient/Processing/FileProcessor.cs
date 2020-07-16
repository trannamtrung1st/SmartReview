using System;
using System.Collections.Generic;
using System.IO;
using System.Text;
using Microsoft.VisualBasic.FileIO;

namespace SmartReview.DataAnalyzer.ConsoleClient.Processing
{
    public static class FileProcessor
    {
        public static List<string[]> ReadCsv(string path, bool ignoreHeader)
        {
            using (TextFieldParser csvParser = new TextFieldParser(path))
            {
                csvParser.CommentTokens = new string[] { "#" };
                csvParser.SetDelimiters(new string[] { "," });
                csvParser.HasFieldsEnclosedInQuotes = true;

                if (ignoreHeader)
                    // Skip the row with the column names
                    csvParser.ReadLine();
                var list = new List<string[]>();
                while (!csvParser.EndOfData)
                {
                    // Read current line fields, pointer moves to the next line.
                    string[] fields = csvParser.ReadFields();
                    list.Add(fields);
                }
                return list;
            }
        }

        public static void SaveTsv(List<string[]> csvData, string path)
        {
            var lines = new List<string>();
            foreach (var d in csvData)
            {
                for (var i = 0; i < d.Length; i++)
                    d[i] = '"' + d[i].Replace("\"", "\"\"").Replace('\n', ' ') + '"';
                var line = string.Join('\t', d);
                lines.Add(line);
            }
            File.WriteAllLines(path, lines);
        }
    }
}
