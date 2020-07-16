/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smartreview.helper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import org.apache.hc.client5.http.entity.mime.MultipartEntityBuilder;
import org.apache.hc.core5.http.ContentType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

/**
 *
 * @author TNT
 */
public class HttpHelper {

    private static final WebDriver DRIVER;

    static {
        ChromeOptions chromeOptions = new ChromeOptions();
//        chromeOptions.addArguments("--headless");
        DRIVER = new ChromeDriver(chromeOptions);
    }

    public static WebDriver getWebDriver() {
        return DRIVER;
    }

    public static MultipartEntityBuilder addFileUpload(MultipartEntityBuilder builder, String partName, InputStream is, String fileName) {
        return builder.addBinaryBody(partName, is, ContentType.MULTIPART_FORM_DATA, fileName);
    }

    public static String encodeUrl(String url) throws UnsupportedEncodingException {
        return URLEncoder.encode(url, "UTF-8");
    }

    public static InputStream getInputStream(String url) throws MalformedURLException, IOException {
        URLConnection conn = new URL(url).openConnection();
        conn.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36 Edg/83.0.478.56");
        return conn.getInputStream();
    }

    public static void download(String url, String saveFile) throws MalformedURLException, IOException {
        try (InputStream is = getInputStream(url)) {
            FileHelper.saveStreamToFile(is, saveFile);
        }
    }

    public static String getPageContent(String url) throws IOException {
        StringBuilder sb = new StringBuilder("");
        try (InputStream is = getInputStream(url);
                InputStreamReader isReader = new InputStreamReader(is, "UTF-8");
                BufferedReader bReader = new BufferedReader(isReader)) {
            String line;
            while ((line = bReader.readLine()) != null) {
                sb.append(line).append("\n");
            }
            return sb.toString();
        }
    }
}
