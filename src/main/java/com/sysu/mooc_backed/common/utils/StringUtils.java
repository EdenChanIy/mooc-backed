package com.sysu.mooc_backed.common.utils;

public class StringUtils {
    //是否空字符
    public static boolean isEmpty(final CharSequence cs){
        return null == cs || cs.length() == 0;
    }
}
