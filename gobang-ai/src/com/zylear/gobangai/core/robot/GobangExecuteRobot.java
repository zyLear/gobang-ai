package com.zylear.gobangai.core.robot;

import com.zylear.gobangai.core.calculator.MinMaxCalculator;
import com.zylear.gobangai.ui.GobangPanel.BestPoint;

/**
 * 算杀机器人
 *
 * @author xiezongyu
 * @date 2021/6/23
 */
public class GobangExecuteRobot implements GobangRobot {

    public GobangExecuteRobot(MinMaxCalculator minMaxCalculator, MinMaxCalculator executeMinMaxCalculator) {
        this.minMaxCalculator = minMaxCalculator;
        this.executeMinMaxCalculator = executeMinMaxCalculator;
    }

    private MinMaxCalculator minMaxCalculator;
    private MinMaxCalculator executeMinMaxCalculator;

    @Override
    public BestPoint think(int[][] tryChess, int gameDepth, int executeDepth, int calculateColor) {



        return null;
    }
}
