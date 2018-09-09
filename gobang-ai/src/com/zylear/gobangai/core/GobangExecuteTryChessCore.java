package com.zylear.gobangai.core;

import com.zylear.gobangai.NullPoint;
import com.zylear.gobangai.bean.GobangConstants;

/**
 * Created by xiezongyu on 2018/9/7.
 */
public class GobangExecuteTryChessCore {

    public static boolean getTryPoints(int[][] tryChess, NullPoint[] tryPoints, NullPoint mark, int calculateColor) {

        for (int o = 0; o < 225; o++)
            tryPoints[o] = new NullPoint();


        int[][] tempNull = new int[15][15];

        mark.count = 0;


        int max = 0;

        for (int x = 0; x < GobangConstants.FIFTEEN; x++) {
            for (int y = 0; y < GobangConstants.FIFTEEN; y++) {
                if (tryChess[x][y] == 0) {

                    if (GobangOperation.isInside(x - 1, y)) {
                        if (tryChess[x - 1][y] != 0) {
                            tempNull[x][y] = getExecuteTryPointScore(tryChess, x, y, calculateColor);
                            if (max < tempNull[x][y]) {
                                max = tempNull[x][y];
                                tryPoints[0].x = x;
                                tryPoints[0].y = y;
                            }
                            tryPoints[0].count++;
                            continue;
                        }
                    }
                    if (GobangOperation.isInside(x - 2, y)) {
                        if (tryChess[x - 2][y] != 0) {
                            tempNull[x][y] = getExecuteTryPointScore(tryChess, x, y, calculateColor);
                            if (max < tempNull[x][y]) {
                                max = tempNull[x][y];
                                tryPoints[0].x = x;
                                tryPoints[0].y = y;
                            }
                            tryPoints[0].count++;
                            continue;
                        }
                    }
                    if (GobangOperation.isInside(x + 1, y)) {
                        if (tryChess[x + 1][y] != 0) {
                            tempNull[x][y] = getExecuteTryPointScore(tryChess, x, y, calculateColor);
                            if (max < tempNull[x][y]) {
                                max = tempNull[x][y];
                                tryPoints[0].x = x;
                                tryPoints[0].y = y;
                            }
                            tryPoints[0].count++;
                            continue;
                        }
                    }
                    if (GobangOperation.isInside(x + 2, y)) {
                        if (tryChess[x + 2][y] != 0) {
                            tempNull[x][y] = getExecuteTryPointScore(tryChess, x, y, calculateColor);
                            if (max < tempNull[x][y]) {
                                max = tempNull[x][y];
                                tryPoints[0].x = x;
                                tryPoints[0].y = y;
                            }
                            tryPoints[0].count++;
                            continue;
                        }
                    }

                    if (GobangOperation.isInside(x, y - 1)) {
                        if (tryChess[x][y - 1] != 0) {
                            tempNull[x][y] = getExecuteTryPointScore(tryChess, x, y, calculateColor);
                            if (max < tempNull[x][y]) {
                                max = tempNull[x][y];
                                tryPoints[0].x = x;
                                tryPoints[0].y = y;
                            }
                            tryPoints[0].count++;
                            continue;
                        }
                    }

                    if (GobangOperation.isInside(x, y - 2)) {
                        if (tryChess[x][y - 2] != 0) {
                            tempNull[x][y] = getExecuteTryPointScore(tryChess, x, y, calculateColor);
                            if (max < tempNull[x][y]) {
                                max = tempNull[x][y];
                                tryPoints[0].x = x;
                                tryPoints[0].y = y;
                            }
                            tryPoints[0].count++;
                            continue;
                        }
                    }

                    if (GobangOperation.isInside(x, y + 1)) {
                        if (tryChess[x][y + 1] != 0) {
                            tempNull[x][y] = getExecuteTryPointScore(tryChess, x, y, calculateColor);
                            if (max < tempNull[x][y]) {
                                max = tempNull[x][y];
                                tryPoints[0].x = x;
                                tryPoints[0].y = y;
                            }
                            tryPoints[0].count++;
                            continue;
                        }
                    }

                    if (GobangOperation.isInside(x, y + 2)) {
                        if (tryChess[x][y + 2] != 0) {
                            tempNull[x][y] = getExecuteTryPointScore(tryChess, x, y, calculateColor);
                            if (max < tempNull[x][y]) {
                                max = tempNull[x][y];
                                tryPoints[0].x = x;
                                tryPoints[0].y = y;
                            }
                            tryPoints[0].count++;
                            continue;
                        }
                    }


                    if (GobangOperation.isInside(x + 1, y + 1)) {
                        if (tryChess[x + 1][y + 1] != 0) {
                            tempNull[x][y] = getExecuteTryPointScore(tryChess, x, y, calculateColor);
                            if (max < tempNull[x][y]) {
                                max = tempNull[x][y];
                                tryPoints[0].x = x;
                                tryPoints[0].y = y;
                            }
                            tryPoints[0].count++;
                            continue;
                        }
                    }


                    if (GobangOperation.isInside(x + 2, y + 2)) {
                        if (tryChess[x + 2][y + 2] != 0) {
                            tempNull[x][y] = getExecuteTryPointScore(tryChess, x, y, calculateColor);
                            if (max < tempNull[x][y]) {
                                max = tempNull[x][y];
                                tryPoints[0].x = x;
                                tryPoints[0].y = y;
                            }
                            tryPoints[0].count++;
                            continue;
                        }
                    }


                    if (GobangOperation.isInside(x - 1, y + 1)) {
                        if (tryChess[x - 1][y + 1] != 0) {
                            tempNull[x][y] = getExecuteTryPointScore(tryChess, x, y, calculateColor);
                            if (max < tempNull[x][y]) {
                                max = tempNull[x][y];
                                tryPoints[0].x = x;
                                tryPoints[0].y = y;
                            }
                            tryPoints[0].count++;
                            continue;
                        }
                    }


                    if (GobangOperation.isInside(x - 2, y + 2)) {
                        if (tryChess[x - 2][y + 2] != 0) {
                            tempNull[x][y] = getExecuteTryPointScore(tryChess, x, y, calculateColor);
                            if (max < tempNull[x][y]) {
                                max = tempNull[x][y];
                                tryPoints[0].x = x;
                                tryPoints[0].y = y;
                            }
                            tryPoints[0].count++;
                            continue;
                        }
                    }

                    if (GobangOperation.isInside(x + 1, y - 1)) {
                        if (tryChess[x + 1][y - 1] != 0) {
                            tempNull[x][y] = getExecuteTryPointScore(tryChess, x, y, calculateColor);
                            if (max < tempNull[x][y]) {
                                max = tempNull[x][y];
                                tryPoints[0].x = x;
                                tryPoints[0].y = y;
                            }
                            tryPoints[0].count++;
                            continue;
                        }
                    }

                    if (GobangOperation.isInside(x + 2, y - 2)) {
                        if (tryChess[x + 2][y - 2] != 0) {
                            tempNull[x][y] = getExecuteTryPointScore(tryChess, x, y, calculateColor);
                            if (max < tempNull[x][y]) {
                                max = tempNull[x][y];
                                tryPoints[0].x = x;
                                tryPoints[0].y = y;
                            }
                            tryPoints[0].count++;
                            continue;
                        }
                    }


                    if (GobangOperation.isInside(x - 1, y - 1)) {
                        if (tryChess[x - 1][y - 1] != 0) {
                            tempNull[x][y] = getExecuteTryPointScore(tryChess, x, y, calculateColor);
                            if (max < tempNull[x][y]) {
                                max = tempNull[x][y];
                                tryPoints[0].x = x;
                                tryPoints[0].y = y;
                            }
                            tryPoints[0].count++;
                            continue;
                        }
                    }


                    if (GobangOperation.isInside(x - 2, y - 2)) {
                        if (tryChess[x - 2][y - 2] != 0) {
                            tempNull[x][y] = getExecuteTryPointScore(tryChess, x, y, calculateColor);
                            if (max < tempNull[x][y]) {
                                max = tempNull[x][y];
                                tryPoints[0].x = x;
                                tryPoints[0].y = y;
                            }
                            tryPoints[0].count++;
                            continue;
                        }
                    }

                    if (GobangOperation.isInside(x - 1, y + 2)) {
                        if (tryChess[x - 1][y + 2] != 0) {
                            tempNull[x][y] = getExecuteTryPointScore(tryChess, x, y, calculateColor);
                            if (max < tempNull[x][y]) {
                                max = tempNull[x][y];
                                tryPoints[0].x = x;
                                tryPoints[0].y = y;
                            }
                            tryPoints[0].count++;
                            continue;
                        }
                    }

                    if (GobangOperation.isInside(x - 2, y + 1)) {
                        if (tryChess[x - 2][y + 1] != 0) {
                            tempNull[x][y] = getExecuteTryPointScore(tryChess, x, y, calculateColor);
                            if (max < tempNull[x][y]) {
                                max = tempNull[x][y];
                                tryPoints[0].x = x;
                                tryPoints[0].y = y;
                            }
                            tryPoints[0].count++;
                            continue;
                        }
                    }

                    if (GobangOperation.isInside(x + 1, y + 2)) {
                        if (tryChess[x + 1][y + 2] != 0) {
                            tempNull[x][y] = getExecuteTryPointScore(tryChess, x, y, calculateColor);
                            if (max < tempNull[x][y]) {
                                max = tempNull[x][y];
                                tryPoints[0].x = x;
                                tryPoints[0].y = y;
                            }
                            tryPoints[0].count++;
                            continue;
                        }
                    }

                    if (GobangOperation.isInside(x + 2, y + 1)) {
                        if (tryChess[x + 2][y + 1] != 0) {
                            tempNull[x][y] = getExecuteTryPointScore(tryChess, x, y, calculateColor);
                            if (max < tempNull[x][y]) {
                                max = tempNull[x][y];
                                tryPoints[0].x = x;
                                tryPoints[0].y = y;
                            }
                            tryPoints[0].count++;
                            continue;
                        }
                    }
                    if (GobangOperation.isInside(x + 1, y - 2)) {
                        if (tryChess[x + 1][y - 2] != 0) {
                            tempNull[x][y] = getExecuteTryPointScore(tryChess, x, y, calculateColor);
                            if (max < tempNull[x][y]) {
                                max = tempNull[x][y];
                                tryPoints[0].x = x;
                                tryPoints[0].y = y;
                            }
                            tryPoints[0].count++;
                            continue;
                        }
                    }

                    if (GobangOperation.isInside(x + 2, y - 1)) {
                        if (tryChess[x + 2][y - 1] != 0) {
                            tempNull[x][y] = getExecuteTryPointScore(tryChess, x, y, calculateColor);
                            if (max < tempNull[x][y]) {
                                max = tempNull[x][y];
                                tryPoints[0].x = x;
                                tryPoints[0].y = y;
                            }
                            tryPoints[0].count++;
                            continue;
                        }
                    }

                    if (GobangOperation.isInside(x - 1, y - 2)) {
                        if (tryChess[x - 1][y - 2] != 0) {
                            tempNull[x][y] = getExecuteTryPointScore(tryChess, x, y, calculateColor);
                            if (max < tempNull[x][y]) {
                                max = tempNull[x][y];
                                tryPoints[0].x = x;
                                tryPoints[0].y = y;
                            }
                            tryPoints[0].count++;
                            continue;
                        }
                    }

                    if (GobangOperation.isInside(x - 2, y - 1)) {
                        if (tryChess[x - 2][y - 1] != 0) {
                            tempNull[x][y] = getExecuteTryPointScore(tryChess, x, y, calculateColor);
                            if (max < tempNull[x][y]) {
                                max = tempNull[x][y];
                                tryPoints[0].x = x;
                                tryPoints[0].y = y;
                            }
                            tryPoints[0].count++;
                            continue;
                        }
                    }


                }
            }
        }


        if (max == 10) {
            mark.count = 1;
            mark.sheng = 1;
            return true;
        } else if (max == 9) {
            mark.count = 1;
            return true;

        } else if (max == 8) {

            mark.count = 1;

            return true;
        } else if (max >= 4 && max <= 7) {
            int count = 0;
            int u = 7;
            while (u >= 4) {
                for (int x = 0; x < GobangConstants.FIFTEEN; x++) {
                    for (int y = 0; y < GobangConstants.FIFTEEN; y++) {
                        if (tempNull[x][y] == u) {
                            tryPoints[count].x = x;
                            tryPoints[count].y = y;
                            count++;
                        }
                    }
                }
                u--;

            }
            mark.count = count;
            return true;
        }

        mark.count = 0;
        return false;
    }

    private static int getExecuteTryPointScore(int[][] tryChess, int xIndex, int yIndex, int c) {
        int d = 0 - c;
        int max = 0;
        int t = 0;
        ////////////////////////////
        if (!GobangOperation.isLessFive(tryChess, xIndex, yIndex, 1, 0, c)) {
            t = getExecuteTryPointScore(tryChess, xIndex, yIndex, 1, 0, c, 0);
            if (t > max) {
                max = t;
            }

            t = getExecuteTryPointScore(tryChess, xIndex, yIndex, -1, 0, c, 0);
            if (t > max) {
                max = t;
            }
        }

        /////////////////////////////
        if (!GobangOperation.isLessFive(tryChess, xIndex, yIndex, 0, 1, c)) {
            t = getExecuteTryPointScore(tryChess, xIndex, yIndex, 0, 1, c, 0);
            if (t > max) {
                max = t;
            }

            t = getExecuteTryPointScore(tryChess, xIndex, yIndex, 0, -1, c, 0);
            if (t > max) {
                max = t;
            }
        }

        /////////////////////
        if (!GobangOperation.isLessFive(tryChess, xIndex, yIndex, 1, 1, c)) {
            t = getExecuteTryPointScore(tryChess, xIndex, yIndex, 1, 1, c, 0);
            if (t > max) {
                max = t;
            }

            t = getExecuteTryPointScore(tryChess, xIndex, yIndex, -1, -1, c, 0);
            if (t > max) {
                max = t;
            }
        }


        //////////////////////////
        if (!GobangOperation.isLessFive(tryChess, xIndex, yIndex, 1, -1, c)) {
            t = getExecuteTryPointScore(tryChess, xIndex, yIndex, 1, -1, c, 0);
            if (t > max) {
                max = t;
            }

            t = getExecuteTryPointScore(tryChess, xIndex, yIndex, -1, 1, c, 0);
            if (t > max) {
                max = t;
            }
        }


        ////////////////////////////对手**********************/
        if (!GobangOperation.isLessFive(tryChess, xIndex, yIndex, 1, 0, d)) {
            t = getExecuteTryPointScore(tryChess, xIndex, yIndex, 1, 0, d, 1);
            if (t > max) {
                max = t;
            }

            t = getExecuteTryPointScore(tryChess, xIndex, yIndex, -1, 0, d, 1);
            if (t > max) {
                max = t;
            }
        }

        /////////////////////////////
        if (!GobangOperation.isLessFive(tryChess, xIndex, yIndex, 0, 1, d)) {
            t = getExecuteTryPointScore(tryChess, xIndex, yIndex, 0, 1, d, 1);
            if (t > max) {
                max = t;
            }

            t = getExecuteTryPointScore(tryChess, xIndex, yIndex, 0, -1, d, 1);
            if (t > max) {
                max = t;
            }
        }

        /////////////////////
        if (!GobangOperation.isLessFive(tryChess, xIndex, yIndex, 1, 1, d)) {
            t = getExecuteTryPointScore(tryChess, xIndex, yIndex, 1, 1, d, 1);
            if (t > max) {
                max = t;
            }

            t = getExecuteTryPointScore(tryChess, xIndex, yIndex, -1, -1, d, 1);
            if (t > max) {
                max = t;
            }
        }


        //////////////////////////
        if (!GobangOperation.isLessFive(tryChess, xIndex, yIndex, 1, -1, d)) {
            t = getExecuteTryPointScore(tryChess, xIndex, yIndex, 1, -1, d, 1);
            if (t > max) {
                max = t;
            }

            t = getExecuteTryPointScore(tryChess, xIndex, yIndex, -1, 1, d, 1);
            if (t > max) {
                max = t;
            }
        }


        return max;
    }

    private static int getExecuteTryPointScore(int[][] tryChess, int xIndex, int yIndex, int xDirect, int yDirect, int color, int isMyColor) {
        int x;
        int y;

        int continueCount = 0;//连续棋子的个数
        int Count = 0;
        boolean block = false;
        int t = 0;


        int chance = 0;
        boolean live = true;


        for (x = xIndex + xDirect, y = yIndex + yDirect; x >= 0 && x <= 14 && y >= 0 && y <= 14; x = x + xDirect, y = y + yDirect) {
            if (tryChess[x][y] == color)
                continueCount++;
            else {
                if (tryChess[x][y] != 0) {
                    chance++;
                    block = true;
                }
                break;
            }
        }

        if (x < 0 || x > 14 || y < 0 || y > 14) chance++;


        for (x = xIndex - xDirect, y = yIndex - yDirect; x >= 0 && x <= 14 && y >= 0 && y <= 14; x = x - xDirect, y = y - yDirect) {

            if (tryChess[x][y] == color)
                continueCount++;


            else {

                if (live && tryChess[x][y] == 0) {
                    live = false;
                    Count = continueCount;
                    continue;
                }


                if (tryChess[x][y] != 0)
                    chance++;


                break;

            }
        }
        if (x < 0 || x > 14 || y < 0 || y > 14) chance++;
        if (live) Count = continueCount;
        if (!block && !live && Count == continueCount) chance = 0;

        t = getExecuteTryPointScore(Count, continueCount, live, block, chance, isMyColor);

        return t;


    }

    private static int getExecuteTryPointScore(int count, int continueCount, boolean live, boolean block, int chance, int isMyColor) {

        if (count >= 4) {
            return 10 - isMyColor;
        } else if (count == 3 && !block && !live) {
            return 8 - isMyColor;
        } else if (continueCount >= 3) {
            return 6 - isMyColor;
        } else if (continueCount == 2 && chance == 0) {
            return 4 - isMyColor;
        }
        return 0;

    }

}
