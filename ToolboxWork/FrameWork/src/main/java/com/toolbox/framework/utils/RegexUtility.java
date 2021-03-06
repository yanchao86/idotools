package com.toolbox.framework.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class RegexUtility {

    public static interface ReplaceProcessor {
        public String replace(String str);
    }

    public static String replace(String text, String regex, ReplaceProcessor process) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        int beginIndex = 0;
        StringBuilder buf = new StringBuilder();
        while (matcher.find()) {
            int start = matcher.start();
            int end = matcher.end();
            buf.append(text.substring(beginIndex, start));
            buf.append(process.replace(text.substring(start, end)));
            beginIndex = end;
        }
        buf.append(text.substring(beginIndex));
        return buf.toString();
    }


    public static String findGroup(String text, String regex, int index) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        if (matcher.find()) {
            return matcher.group(index);
        }
        return null;
    }
    
    public static List<String> findGroup(String text, String regex) {
        List<String> result = new ArrayList<String>();
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            result.add(matcher.group(1));
        }
        return result;
    }
}
