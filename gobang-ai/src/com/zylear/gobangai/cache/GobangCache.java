package com.zylear.gobangai.cache;

import com.zylear.gobangai.bean.Point;

import java.util.BitSet;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by xiezongyu on 2018/9/11.
 */
public class GobangCache {

    public static Map<String, Point> gobangOptimizeMap = new HashMap<>(10000000);

    public static Map<BitSet, Integer> scoreCache = new HashMap<>(10000000);

}
