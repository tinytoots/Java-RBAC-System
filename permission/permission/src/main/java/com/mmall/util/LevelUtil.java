package com.mmall.util;


import org.apache.commons.lang3.StringUtils;

public class LevelUtil {

    // 各个层级直接的分隔符
    public final static String SEPARATOR = ".";

    public final static String ROOT = "0";

    // 0 首层
    // 0.1
    // 0.1.2
    // 0.1.3
    // 0.4
    public static String calculateLevel(String parentLevel, int parentId) {
        if (StringUtils.isBlank(parentLevel)) {
            return ROOT;
        } else {
            return StringUtils.join(parentLevel, SEPARATOR, parentId);
        }
    }

}
