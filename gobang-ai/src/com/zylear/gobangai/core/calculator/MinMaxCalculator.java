package com.zylear.gobangai.core.calculator;

import com.zylear.gobangai.ui.GobangPanel.BestPoint;

public interface MinMaxCalculator {

    int calculate(int[][] tryChess, int depth, int maxDepth, int alpha, int beta, int calculateColor);

    BestPoint getBestPoint(int[][] tryChess, int maxDepth, int calculateColor);

}
