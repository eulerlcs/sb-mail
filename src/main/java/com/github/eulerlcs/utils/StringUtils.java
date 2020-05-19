package com.github.eulerlcs.utils;

import org.apache.commons.text.StringSubstitutor;

import java.util.Map;

public interface StringUtils {
    static String format(String template, Map<String, String> paras){
        return new StringSubstitutor(paras).replace(template);
    }
}
