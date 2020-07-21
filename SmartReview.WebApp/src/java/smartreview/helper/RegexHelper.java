/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smartreview.helper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author TNT
 */
public class RegexHelper {

    public static Matcher matcherDotAll(String str, String regex) {
        Matcher matcher = Pattern.compile(regex, Pattern.DOTALL).matcher(str);
        return matcher;
    }

    public static boolean matchDotAll(String str, String regex) {
        return Pattern.compile(regex, Pattern.DOTALL).matcher(str).matches();
    }

    public static String replaceDotAll(String srcStr, String regex, String replaceStr) {
        Matcher matcher = Pattern.compile(regex, Pattern.DOTALL).matcher(srcStr);
        return matcher.replaceAll(replaceStr);
    }

    public static String replaceGroupDotAll(String source, String regex, String groupToReplace, String replacement) {
        return RegexHelper.replaceGroupDotAll(source, regex, groupToReplace, 1, replacement);
    }

    public static String replaceGroupDotAll(String source, String regex, String groupToReplace, int groupOccurrence, String replacement) {
        Matcher m = Pattern.compile(regex, Pattern.DOTALL).matcher(source);
        for (int i = 0; i < groupOccurrence; i++) {
            if (!m.find()) {
                return source; // pattern not met, may also throw an exception here
            }
        }
        int removedLength = 0;
        StringBuilder sb = new StringBuilder(source);
        do {
            int start = m.start(groupToReplace);
            int end = m.end(groupToReplace);
            sb = sb.replace(start - removedLength, end - removedLength, replacement);
            removedLength += end - start - replacement.length();
        } while (m.find());
        return sb.toString();
    }

}
