package com.zylear.gobangai.core.score;

import com.zylear.gobangai.bean.GobangConstants;
import com.zylear.gobangai.core.GobangOperation;

/**
 * @author xiezongyu
 * @date 2021/6/24
 */
public class ExecuteNextPointScoreCalculator extends NextPointScoreCalculatorBase {

    @Override
    protected boolean preCalculateScore(int[][] tryChess, int xIndex, int yIndex, int xDirection, int yDirection, int calculateColor) {
        return !GobangOperation.isLessFiveV2(tryChess, xIndex, yIndex, xDirection, yDirection, calculateColor);
    }

    /**
     * 算杀遍历八个方向
     *
     * @param tryChess
     * @param xIndex
     * @param yIndex
     * @param xDirection
     * @param yDirection
     * @param calculateColor
     * @param color
     * @return
     */
    @Override
    protected int getScore(int[][] tryChess, int xIndex, int yIndex, int xDirection, int yDirection, int calculateColor, int color) {
        int max = Integer.MIN_VALUE;
        int score = getNextScore(tryChess, xIndex, yIndex, xDirection, yDirection, calculateColor, color);
        if (score > max) {
            max = score;
        }
        score = getNextScore(tryChess, xIndex, yIndex, -xDirection, -yDirection, calculateColor, color);
        if (score > max) {
            max = score;
        }
        return max;
    }


    protected int getNextScore(int[][] tryChess, int xIndex, int yIndex, int xDirection, int yDirection, int calculateColor, int color) {
        int x;
        int y;

        //连续棋子的个数
        int continueCount = 0;
        int count = 0;
        boolean block = false;
        int score;


        int blockCount = 0;
        boolean live = true;


        for (x = xIndex + xDirection, y = yIndex + yDirection;
             x >= 0 && x < GobangConstants.FIFTEEN && y >= 0 && y < GobangConstants.FIFTEEN;
             x = x + xDirection, y = y + yDirection) {
            if (tryChess[x][y] == calculateColor) {
                continueCount++;
            } else {
                if (tryChess[x][y] != 0) {
                    blockCount++;
                    block = true;
                }
                break;
            }
        }

        if (!GobangOperation.isInside(x, y)) {
            blockCount++;
        }


        for (x = xIndex - xDirection, y = yIndex - yDirection;
             x >= 0 && x < GobangConstants.FIFTEEN && y >= 0 && y < GobangConstants.FIFTEEN;
             x = x - xDirection, y = y - yDirection) {

            if (tryChess[x][y] == calculateColor)
                continueCount++;

            else {

                if (live && tryChess[x][y] == 0) {
                    live = false;
                    count = continueCount;
                    continue;
                }

                if (tryChess[x][y] != 0) {
                    blockCount++;
                }

                break;

            }
        }
        if (!GobangOperation.isInside(x, y)) {
            blockCount++;
        }
        if (live) {
            count = continueCount;
        }
        if (!block && !live && count == continueCount) {
            blockCount = 0;
        }
        int isOpponentColor = color != calculateColor ? 1 : 0;
        score = getExecuteTryPointScore(count, continueCount, live, block, blockCount, isOpponentColor);

        if (score < 6) {
            return -1;
        }
        return score;
    }

    private int getExecuteTryPointScore(int count, int continueCount, boolean live, boolean block, int chance, int isOpponentColor) {

        if (count >= 4) {
            return 10 - isOpponentColor;
        } else if (count == 3 && !block && !live) {
            return 8 - isOpponentColor;
        } else if (continueCount >= 3) {
            return 6 - isOpponentColor;
        } else if (continueCount == 2 && chance == 0) {
            return 4 - isOpponentColor;
        }
        return 0;

    }
}
