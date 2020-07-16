/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smartreview.helper;

import java.io.IOException;
import java.util.NoSuchElementException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 *
 * @author TNT
 */
public class Entry {

    public static void main(String[] args) throws IOException, InterruptedException {
        System.setProperty("webdriver.chrome.driver", "T:\\ITs\\WebDriver\\chromedriver84.exe");
        WebDriver driver = HttpHelper.getWebDriver();
        driver.get("https://www.tripadvisor.com/Restaurant_Review-g293925-d15101144-Reviews-The_D_Saigon-Ho_Chi_Minh_City.html");
        String src = driver.getPageSource();
        try {
            WebElement e = driver.findElement(By.cssSelector(".pageNum.current + .pageNum"));
            while (e != null) {
                e.click();
                Thread.sleep(1000);
//            WebElement element = new WebDriverWait(driver, 20).until((t) -> {
//                return null; //To change body of generated lambdas, choose Tools | Templates.
//            });

                src = driver.getPageSource();
                FileHelper.writeToFile(src, "temp.html");
                e = driver.findElement(By.cssSelector(".pageNum.current + .pageNum"));
            }
        } catch (NoSuchElementException e) {
        }
    }
}
