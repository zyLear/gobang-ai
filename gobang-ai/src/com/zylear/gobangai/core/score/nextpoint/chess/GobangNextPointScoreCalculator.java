package com.zylear.gobangai.core.score.nextpoint.chess;

import com.zylear.gobangai.bean.GobangConstants;
import com.zylear.gobangai.core.GobangOperation;
import com.zylear.gobangai.core.score.nextpoint.NextPointScoreCalculatorBase;

/**
 * @author xiezongyu
 * @date 2021/6/24
 */
public class GobangNextPointScoreCalculator extends NextPointScoreCalculatorBase {


//    @Override
//    protected boolean preCalculateScore(int[][] tryChess, int xIndex, int yIndex, int xDirection, int yDirection, int calculateColor) {
//        return true;
//    }

    @Override
    protected int getScore(int[][] tryChess, int xIndex, int yIndex, int xDirection, int yDirection, int calculateColor) {
        int continueCount = 0;
        int blockCount = 0;
        int opponentColor = -calculateColor;

        int x;
        int y;

        for (x = xIndex + xDirection, y = yIndex + yDirection;
             x >= 0 && x <= GobangConstants.COLS && y >= 0 && y <= GobangConstants.COLS;
             x = x + xDirection, y = y + yDirection) {
            if (tryChess[x][y] == calculateColor) {
                continueCount++;
            } else {
                if (tryChess[x][y] == opponentColor) {
                    blockCount++;
                }
                break;
            }
        }
        if (!GobangOperation.isInside(x, y)) {
            blockCount++;
        }

        for (x = xIndex - xDirection, y = yIndex - yDirection;
             x >= 0 && x <= GobangConstants.COLS && y >= 0 && y <= GobangConstants.COLS;
             x = x - xDirection, y = y - yDirection) {
            if (tryChess[x][y] == calculateColor) {
                continueCount++;
            } else {
                if (tryChess[x][y] == opponentColor) {
                    blockCount++;
                }
                break;
            }
        }
        if (!GobangOperation.isInside(x, y)) {
            blockCount++;
        }
        int score;
        //计算自己的颜色
        score = getMyTryScore(continueCount, blockCount);
        return score;
    }

    private int getMyTryScore(int score, int blockCount) {
        switch (score) {
            case 0:
                return 0;
            case 1:
                if (blockCount == 2) {
                    return 0;
                }
                if (blockCount == 1) {
                    return 2;
                }
                if (blockCount == 0) {
                    return 4;
                }
            case 2:
                if (blockCount == 2) {
                    return 0;
                }
                if (blockCount == 1) {
                    return 4;
                }
                if (blockCount == 0) {
                    return 6;
                }
            case 3:
                if (blockCount == 2) {
                    return 0;
                }
                if (blockCount == 1) {
                    return 6;
                }
                if (blockCount == 0) {
                    return 8;
                }
            case 4:
                return 10;

            default:
                return 10;
        }

    }

    @Override
    protected int postProcessScore(int score) {
        return score;
    }
}
