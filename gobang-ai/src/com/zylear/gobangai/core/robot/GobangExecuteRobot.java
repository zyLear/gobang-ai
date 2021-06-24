package com.zylear.gobangai.core.robot;

import com.zylear.gobangai.bean.GobangConstants;
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
    public String key() {
        return "ai-v1";
    }

    @Override
    public BestPoint think(int[][] tryChess, int gameDepth, int executeDepth, int calculateColor) {


//        BestPoint bestPoint = executeMinMaxCalculator.getBestPoint(tryChess, gameDepth, calculateColor);
//        if (bestPoint.score != GobangConstants.WIN_SCORE) {
//            System.out.println("算杀失败，正常博弈...");
//            bestPoint = minMaxCalculator.getBestPoint(tryChess, gameDepth, calculateColor);
//        }else {
//            System.out.println("电脑算杀成功! ");
//        }
        BestPoint bestPoint = minMaxCalculator.getBestPoint(tryChess, gameDepth, calculateColor);
        return bestPoint;
    }
}
