package com.zylear.gobangai.core.score.chess;

import com.zylear.gobangai.bean.ChessColor;
import com.zylear.gobangai.bean.GobangConstants;
import com.zylear.gobangai.core.GobangOperation;
import com.zylear.gobangai.core.GobangStatistic;
import com.zylear.gobangai.core.score.ScoreCalculator;

/**
 * @author xiezongyu
 * @date 2021/6/24
 */
@Deprecated
public class GobangScoreCalculatorV1 extends GobangScoreCalculatorBase {

    @Override
    protected boolean preCalculateScore(int[][] tryChess, int xIndex, int yIndex, int xDirection, int yDirection, int calculateColor) {
        return !GobangOperation.isLessFiveV2(tryChess, xIndex, yIndex, xDirection, yDirection, calculateColor);
    }

    @Override
    protected int getScore(int[][] tryChess, int xIndex, int yIndex, int xDirection, int yDirection, int color) {
        int x;
        int y;
        //连续棋子的个数
        int continueCount = 1;
        int count = 0;
        int sign = 0;

        boolean bb = false;

        int chance = 1;

        for (x = xIndex + xDirection, y = yIndex + yDirection;
             x >= 0 && x <= GobangConstants.COLS && y >= 0 && y <= GobangConstants.ROWS;
             x = x + xDirection, y = y + yDirection) {

            if (tryChess[x][y] == color) {
                continueCount++;
            } else {
                if (tryChess[x][y] != 0) {
                    sign = 1;
                }
                break;
            }
        }
        if (!GobangOperation.isInside(x, y)) {
            sign = 1;
        }


        for (x = xIndex - xDirection, y = yIndex - yDirection;
             x >= 0 && x <= GobangConstants.COLS && y >= 0 && y <= GobangConstants.ROWS;
             x = x - xDirection, y = y - yDirection) {

            if (tryChess[x][y] == color) {
                continueCount++;
            } else {
                if (chance == 1 && tryChess[x][y] == 0) {
                    chance--;
                    count = continueCount;
                    continue;
                }

                if (sign == 0 && tryChess[x][y] == 0 && chance == 0 && count != continueCount) {
                    bb = true;
                }

                break;
            }
        }

        if (sign == 1 && !GobangOperation.isInside(x, y)) {
            continueCount = 0;
        }


        if (sign == 0 && chance == 0 && count == continueCount) {
            bb = true;
        }

        return getPointScore(continueCount, bb);
    }


    private int getPointScore(int continueCount, boolean blockOneSide) {
        switch (continueCount) {
            case 0:
                return 0;
            case 1:
                if (!blockOneSide) {
                    return 0;
                } else {
                    return 10;
                }

            case 2:
                if (!blockOneSide) {
                    return 10;
                } else {
                    return 100;
                }
            case 3:
                if (!blockOneSide) {
                    return 100;
                } else {
                    return 1000;
                }
            case 4:
                if (!blockOneSide) {
                    return 1000;
                } else {
                    return 10000;
                }
            case 5:
                return 1000000;
            default:
                return 1000000;
        }
    }

}
