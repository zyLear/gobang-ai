package com.zylear.gobangai.core.score.nextpoint;

import com.zylear.gobangai.bean.GobangConstants;
import com.zylear.gobangai.core.score.nextpoint.NextPointScoreCalculator;

/**
 * @author xiezongyu
 * @date 2021/6/24
 */
abstract public class NextPointScoreCalculatorBase implements NextPointScoreCalculator {

    @Override
    public int getNextPointScore(int[][] tryChess, int x, int y, int color) {

        int max = Integer.MIN_VALUE;


        for (int[] direction : GobangConstants.DERECTIONS) {
            int xDirection = direction[0];
            int yDirection = direction[1];

            int score = getScore(tryChess, x, y, xDirection, yDirection, color, color);
            if (score > max) {
                max = score;
            }
            score = getScore(tryChess, x, y, xDirection, yDirection, -color, color);
            if (score > max) {
                max = score;
            }

        }

        return max;
    }


    abstract protected int getScore(int[][] tryChess, int xIndex, int yIndex, int xDirection, int yDirection, int calculateColor, int color);


}
