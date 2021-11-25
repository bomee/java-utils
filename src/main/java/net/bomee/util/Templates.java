package net.bomee.util;

import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 一个简单的模版工具类，主要处理${variable}的自动填充
 *
 * @author bomee shiaupo@qq.com
 */
public final class Templates {
    private Templates() {
    }

    private static final Pattern PATTERN_PARAM = Pattern.compile("\\$\\{(\\w+)}");

    /**
     * 提取变量集合
     *
     * @param template 字符模版
     * @return 变量集合
     */
    public static Set<String> extractVariables(String template) {
        Set<String> variables = new LinkedHashSet<>();
        Matcher matcher = PATTERN_PARAM.matcher(template);
        while (matcher.find()) {
            variables.add(matcher.group(1));
        }
        return variables;
    }

    /**
     * 解析模版
     *
     * @param template 字符模版
     * @param args     模版变量Map
     * @return 解析后的字符串
     */
    public static String resolve(String template, Map<String, ?> args) {
        Matcher matcher = PATTERN_PARAM.matcher(template);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            String argName = matcher.group(1);
            Object argValue = args.get(argName);
            if (argValue == null) {
                throw new IllegalArgumentException("Miss argument: " + argName);
            }
            matcher.appendReplacement(sb, argValue.toString());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }
}
