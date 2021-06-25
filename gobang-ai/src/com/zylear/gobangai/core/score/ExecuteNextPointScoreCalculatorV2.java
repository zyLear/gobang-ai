package com.zylear.gobangai.core.score;

import com.zylear.gobangai.bean.GobangConstants;
import com.zylear.gobangai.core.GobangOperation;

/**
 * @author xiezongyu
 * @date 2021/6/24
 */
public class ExecuteNextPointScoreCalculatorV2 extends NextPointScoreCalculatorBase {


    @Override
    protected boolean preCalculateScore(int[][] tryChess, int xIndex, int yIndex, int xDirection, int yDirection, int calculateColor) {
//        return !GobangOperation.isLessFiveV2(tryChess, xIndex, yIndex, xDirection, yDirection, calculateColor);
        return true;
    }

    @Override
    protected int getScore(int[][] tryChess, int xIndex, int yIndex, int xDirection, int yDirection, int calculateColor, int color) {
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
        if (calculateColor == color) {
            score = getMyTryScore(continueCount, blockCount);
        } else {
            score = getOpponentTryScore(continueCount, blockCount);
        }

        if (score < 6) {
            return -1;
        }

        return score;
    }

    private int getMyTryScore(int score, int blockCount) {
        switch (score) {
            case 0:
                return 2;
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

    private int getOpponentTryScore(int score, int blockCount) {
        switch (score) {
            case 0:
                return 1;
            case 1:
                if (blockCount == 2) {
                    return 0;
                }
                if (blockCount == 1) {
                    return 1;
                }
                if (blockCount == 0) {
                    return 3;
                }
            case 2:
                if (blockCount == 2) {
                    return 0;
                }
                if (blockCount == 1) {
                    return 3;
                }
                if (blockCount == 0) {
                    return 5;
                }
            case 3:
                if (blockCount == 2) {
                    return 0;
                }
                if (blockCount == 1) {
                    return 5;
                }
                if (blockCount == 0) {
                    return 7;
                }
            case 4:
                return 9;

            default:
                return 9;
        }

    }
}
