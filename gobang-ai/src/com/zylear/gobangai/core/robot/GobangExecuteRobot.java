package com.zylear.gobangai.core.robot;

import com.zylear.gobangai.bean.Point;
import com.zylear.gobangai.bean.GobangConstants;
import com.zylear.gobangai.cache.GobangCache;
import com.zylear.gobangai.core.GobangStatistic;
import com.zylear.gobangai.core.calculator.MinMaxCalculator;

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
    public Point think(int[][] tryChess, int gameDepth, int executeDepth, int calculateColor) {


        Point bestPoint = executeMinMaxCalculator.getBestPoint(tryChess, executeDepth, calculateColor);
        if (bestPoint.score != GobangConstants.WIN_SCORE) {
            System.out.println("算杀失败，正常博弈...");
            GobangStatistic.init();
            GobangCache.scoreCache.clear();
            bestPoint = minMaxCalculator.getBestPoint(tryChess, gameDepth, calculateColor);
        } else {
            System.out.println("电脑算杀成功! ");
        }
//        BestPoint bestPoint = minMaxCalculator.getBestPoint(tryChess, gameDepth, calculateColor);
        System.out.println("分数：" + bestPoint.score +
                " 棋盘计算分数次数: " + GobangStatistic.calculateCount +
                " 命中缓存次数: " + GobangStatistic.hitCacheCount
        );
        System.out.println("下棋点：" + bestPoint);
        return bestPoint;
    }
}
