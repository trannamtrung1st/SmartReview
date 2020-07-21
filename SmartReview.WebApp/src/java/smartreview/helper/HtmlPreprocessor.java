/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smartreview.helper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import smartreview.webapp.XmlParserConfig;

/**
 *
 * @author TNT
 */
public class HtmlPreprocessor {

    private final XmlParserConfig config;

    public HtmlPreprocessor(XmlParserConfig config) {
        this.config = config;
    }

    public String refineHtml(String src) {
        src = getNecessaryPart(src);
        src = removeMiscs(src);
        XMLSyntaxChecker checker = new XMLSyntaxChecker(config);
        src = checker.check(src);
        src = getNecessaryPart(src);
        return src;
    }

    public String getNecessaryPart(String src) {
        String result = src;
        String regex = config.getNeccessaryPart();
        Pattern pattern = Pattern.compile(regex, Pattern.DOTALL);
        Matcher matcher = pattern.matcher(result);
        if (matcher.find()) {
            result = matcher.group(0);
        }
        return result;
    }

    public String removeMiscs(String src) {
        String result = src;
        for (String misc : config.getMiscs().getPattern()) {
            Pattern pattern = Pattern.compile(misc, Pattern.DOTALL);
            Matcher matcher = pattern.matcher(result);
            result = matcher.replaceAll("");
        }
        return result;
    }
}
