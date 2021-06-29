package com.zylear.gobangai.bean;

/**
 * Created by xiezongyu on 2018/9/7.
 */
public class GobangConstants {

    public static final Integer WIN_SCORE = 10000000;
    public static final Integer LOSE_SCORE = -10000000;
    public static final Integer LOSE_SCORE_SIGN = -8000000;

    public static final Integer FIFTEEN = 15;
    public static final int ROWS = 14;//棋盘行数
    public static final int COLS = 14;//棋盘列数

    public static final Integer ALPHA = Integer.MIN_VALUE;
    public static final Integer BETA = Integer.MAX_VALUE;

    public static final int WHITE = 1;
    public static final int BLACK = -1;

    public static final int[][] DERECTIONS = {
            {1, 1}, {1, -1}, {1, 0}, {0, 1}
    };




}
