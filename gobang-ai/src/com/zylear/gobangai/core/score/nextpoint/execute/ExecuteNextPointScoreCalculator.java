package com.zylear.gobangai.core.score.nextpoint.execute;

import com.zylear.gobangai.bean.GobangConstants;
import com.zylear.gobangai.core.GobangOperation;
import com.zylear.gobangai.core.score.nextpoint.NextPointScoreCalculatorBase;

/**
 * @author xiezongyu
 * @date 2021/6/24
 */
public class ExecuteNextPointScoreCalculator extends NextPointScoreCalculatorBase {


    @Override
    protected int postProcessScore(int score) {
        return score < 6 ? 0 : score;
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
     * @return
     */
    @Override
    protected int getScore(int[][] tryChess, int xIndex, int yIndex, int xDirection, int yDirection, int calculateColor) {
        int max = Integer.MIN_VALUE;
        int score = getNextScore(tryChess, xIndex, yIndex, xDirection, yDirection, calculateColor);
        if (score > max) {
            max = score;
        }
        score = getNextScore(tryChess, xIndex, yIndex, -xDirection, -yDirection, calculateColor);
        if (score > max) {
            max = score;
        }
        return max;
    }


    protected int getNextScore(int[][] tryChess, int xIndex, int yIndex, int xDirection, int yDirection, int calculateColor) {
        int x;
        int y;

        //连续棋子的个数
        int count = 0;
        //在跳空前的数量
        int score;


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

        score = getExecuteTryPointScore(count, continuous, blockCount, countBeforeJump, countAfterJump);

        return score;
    }

    private int getExecuteTryPointScore(int count, boolean continuous, int blockCount, int countBeforeJump, int countAfterJump) {


        switch (count) {
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
                if (continuous) {
                    if (blockCount == 2) {
                        return 0;
                    }
                    if (blockCount == 1) {
                        return 4;
                    }
                    if (blockCount == 0) {
                        return 6;
                    }
                } else {
                    if (blockCount == 2) {
                        return 0;
                    }
                    if (blockCount == 1) {
                        return 4;
                    }
                    if (blockCount == 0) {
                        return 6;
                    }
                }

            case 3:
                if (continuous) {
                    if (blockCount == 2) {
                        return 0;
                    }
                    if (blockCount == 1) {
                        return 6;
                    }
                    if (blockCount == 0) {
                        return 8;
                    }
                } else {
                    return 6;
                }
                //case 4
            default:
                if (continuous) {
                    return 10;
                } else {
                    if (countBeforeJump >= 4 || countAfterJump >= 5) {
                        return 10;
                    } else {
                        return 8;
                    }
                }
        }

    }
}
