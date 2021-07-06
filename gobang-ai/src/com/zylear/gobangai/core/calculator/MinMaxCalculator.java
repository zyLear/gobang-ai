package com.zylear.gobangai.core.calculator;

import com.zylear.gobangai.bean.Point;

public interface MinMaxCalculator {

    int calculate(int[][] tryChess, int depth, int maxDepth, int alpha, int beta, int calculateColor);

    Point getBestPoint(int[][] tryChess, int maxDepth, int calculateColor);

}
