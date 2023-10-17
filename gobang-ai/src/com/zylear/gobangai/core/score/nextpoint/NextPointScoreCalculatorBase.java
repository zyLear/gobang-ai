package com.zylear.gobangai.core.score.nextpoint;

import com.zylear.gobangai.bean.GobangConstants;

/**
 * @author xiezongyu
 * @date 2021/6/24
 */
abstract public class NextPointScoreCalculatorBase implements NextPointScoreCalculator {

    @Override
    public int getNextPointScore(int[][] tryChess, int x, int y, int color) {

        int max = Integer.MIN_VALUE;

        int count = 0;

        for (int[] direction : GobangConstants.DIRECTIONS) {
            int xDirection = direction[0];
            int yDirection = direction[1];

            int score = getScore(tryChess, x, y, xDirection, yDirection, color);
            if (score > max) {
                max = score;
            }

            score = getScore(tryChess, x, y, xDirection, yDirection, -color);
            score -= 1;
            if (score > max) {
                max = score;
            }
            if (score == 5) {
                count++;
            }
        }

        if (max == 5 && count >= 2) {
            //对手双三 要防
            //在正常情况下对手的活三不用防，但是如果是双的话，分数升级，变成活四
            return 7;
        }

        return postProcessScore(max);
    }

    abstract protected int postProcessScore(int score);


    abstract protected int getScore(int[][] tryChess, int xIndex, int yIndex, int xDirection, int yDirection, int calculateColor);


}
