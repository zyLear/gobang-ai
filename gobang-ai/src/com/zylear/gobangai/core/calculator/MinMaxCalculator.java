package com.zylear.gobangai.core.calculator;

public interface MinMaxCalculator {

    int calculate(int[][] tryChess, int depth, int maxDepth, int alpha, int beta, int calculateColor);

}
