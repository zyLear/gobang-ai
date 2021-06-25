package com.zylear.gobangai.core;

/**
 * @author xiezongyu
 * @date 2021/6/25
 */
public class GobangStatistic {

    public static Long calculateCount = 0L;

    public static Long hitCacheCount = 0L;

    public static void init() {
        calculateCount = 0L;
        hitCacheCount = 0L;
    }


}
