/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smartreview.helper;

import java.io.IOException;
import java.util.regex.Matcher;

/**
 *
 * @author TNT
 */
public class Entry {

    public static void main(String[] args) throws IOException, InterruptedException {
        String url = "https://www.tripadvisor.com/Restaurant_Review-g33450-d11962753-Reviews-Del_Taco-Grand_Junction_Colorado.html";
        Matcher matcher = RegexHelper.matcherDotAll(url, "^.+?-.+?-(.+?)-.*$");
        matcher.find();
        System.out.println(matcher.group(1));
    }
}
