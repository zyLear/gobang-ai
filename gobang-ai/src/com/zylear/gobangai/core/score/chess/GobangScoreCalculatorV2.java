package com.zylear.gobangai.core.score.chess;

import com.zylear.gobangai.bean.GobangConstants;
import com.zylear.gobangai.core.GobangOperation;

/**
 * @author xiezongyu
 * @date 2021/6/29
 */
public class GobangScoreCalculatorV2 extends GobangScoreCalculatorBase {
    @Override
    protected boolean preCalculateScore(int[][] tryChess, int xIndex, int yIndex, int xDirection, int yDirection, int calculateColor) {
        return !GobangOperation.isLessFiveV2(tryChess, xIndex, yIndex, xDirection, yDirection, calculateColor);
    }

    @Override
    protected int getScore(int[][] tryChess, int xIndex, int yIndex, int xDirection, int yDirection, int calculateColor) {

        int x;
        int y;

        //连续棋子的个数
        int count = 0;


        int blockCount = 0;
        boolean continuous = true;
        int countBeforeJump = 0;
        int countAfterJump = 0;


        for (x = xIndex + xDirection, y = yIndex + yDirection;
             x >= 0 && x < GobangConstants.FIFTEEN && y >= 0 && y < GobangConstants.FIFTEEN;
             x = x + xDirection, y = y + yDirection) {
            if (tryChess[x][y] == calculateColor) {
                count++;
                countBeforeJump++;
            } else {
                if (tryChess[x][y] != 0) {
                    blockCount++;
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

            if (tryChess[x][y] == calculateColor) {
                count++;
                if (continuous) {
                    countBeforeJump++;
                }else {
                    countAfterJump++;
                }

            } else {
                if (continuous && tryChess[x][y] == 0
                        && GobangOperation.isInside(x - xDirection, y - yDirection)
                        && tryChess[x - xDirection][y - yDirection] == calculateColor) {

                    continuous = false;
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

        return getPointScore(count, continuous, blockCount, countBeforeJump, countAfterJump);

    }

    private int getPointScore(int count, boolean continuous, int blockCount, int countBeforeJump, int countAfterJump) {

        switch (count) {
            case 0:
                return 0;
            case 1:
                if (blockCount == 2) {
                    return 0;
                }
                if (blockCount == 1) {
                    return 10;
                }
                if (blockCount == 0) {
                    return 100;
                }
            case 2:
                if (continuous) {
                    if (blockCount == 2) {
                        return 0;
                    }
                    if (blockCount == 1) {
                        return 100;
                    }
                    if (blockCount == 0) {
                        return 1000;
                    }
                } else {
                    if (blockCount == 2) {
                        return 0;
                    }
                    if (blockCount == 1) {
                        return 100;
                    }
                    if (blockCount == 0) {
                        return 1000;
                    }
                }

            case 3:
                if (continuous) {
                    if (blockCount == 2) {
                        return 0;
                    }
                    if (blockCount == 1) {
                        return 1000;
                    }
                    if (blockCount == 0) {
                        return 10000;
                    }
                } else {
                    return 1000;
                }
                //case 4
            default:
                if (continuous) {
                    return GobangConstants.WIN_SCORE;
                } else {
                    if (countBeforeJump >= 4 || countAfterJump >= 5) {
                        return GobangConstants.WIN_SCORE;
                    } else {
                        return 10000;
                    }
                }
        }
    }


}
