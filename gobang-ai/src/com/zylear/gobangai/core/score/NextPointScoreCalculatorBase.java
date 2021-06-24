package com.zylear.gobangai.core.score;

import com.zylear.gobangai.bean.GobangConstants;
import com.zylear.gobangai.core.GobangOperation;

/**
 * @author xiezongyu
 * @date 2021/6/24
 */
abstract public class NextPointScoreCalculatorBase implements NextPointScoreCalculator {

   private int[][] directions = {
            {1, 1}, {1, -1}, {1, 0}, {0, 1}
    };

    @Override
    public int getNextPointScore(int[][] tryChess, int x, int y, int color) {

        int max = Integer.MIN_VALUE;


        for (int[] direction : directions) {
            int xDirection = direction[0];
            int yDirection = direction[1];

            if (!preCalculateScore(tryChess, x, y, xDirection, yDirection, color)) {
                continue;
            }

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

    abstract protected boolean preCalculateScore(int[][] tryChess, int xIndex, int yIndex, int xDirection, int yDirection, int calculateColor);


    abstract protected int getScore(int[][] tryChess, int xIndex, int yIndex, int xDirection, int yDirection, int calculateColor, int color);


}
