package com.zylear.gobangai.core.nextpoint;


import com.zylear.gobangai.Point;
import com.zylear.gobangai.bean.GobangConstants;

/**
 * Created by xiezongyu on 2018/9/7.
 */
public class GobangTryChessCore {


    //EmptyTryPoint[]
    public static void getTryPoints(int[][] tryChess, Point[] P, int c) {

        for (int o = 0; o < 225; o++)
            P[o] = new Point();


        int[][] tempNull = new int[15][15];

        P[0].count = 0;
        int max = 0;

        for (int x = 0; x < GobangConstants.FIFTEEN; x++) {
            for (int y = 0; y < GobangConstants.FIFTEEN; y++) {
                if (tryChess[x][y] == 0) {
                    boolean flag = false;


                    if (isInside(x - 1, y)) {
                        if (tryChess[x - 1][y] != 0) {
                            tempNull[x][y] = getTryPointScore(tryChess, x, y, c);
                            if (max < tempNull[x][y]) {
                                max = tempNull[x][y];
                                P[0].x = x;
                                P[0].y = y;
                            }
                            P[0].count++;
                            continue;
                        }
                    }
                    if (isInside(x - 2, y)) {
                        if (tryChess[x - 2][y] != 0) {
                            tempNull[x][y] = getTryPointScore(tryChess, x, y, c);
                            if (max < tempNull[x][y]) {
                                max = tempNull[x][y];
                                P[0].x = x;
                                P[0].y = y;
                            }
                            P[0].count++;
                            continue;
                        }
                    }
                    if (isInside(x + 1, y)) {
                        if (tryChess[x + 1][y] != 0) {
                            tempNull[x][y] = getTryPointScore(tryChess, x, y, c);
                            if (max < tempNull[x][y]) {
                                max = tempNull[x][y];
                                P[0].x = x;
                                P[0].y = y;
                            }
                            P[0].count++;
                            continue;
                        }
                    }
                    if (isInside(x + 2, y)) {
                        if (tryChess[x + 2][y] != 0) {
                            tempNull[x][y] = getTryPointScore(tryChess, x, y, c);
                            if (max < tempNull[x][y]) {
                                max = tempNull[x][y];
                                P[0].x = x;
                                P[0].y = y;
                            }
                            P[0].count++;
                            continue;
                        }
                    }

                    if (isInside(x, y - 1)) {
                        if (tryChess[x][y - 1] != 0) {
                            tempNull[x][y] = getTryPointScore(tryChess, x, y, c);
                            if (max < tempNull[x][y]) {
                                max = tempNull[x][y];
                                P[0].x = x;
                                P[0].y = y;
                            }
                            P[0].count++;
                            continue;
                        }
                    }

                    if (isInside(x, y - 2)) {
                        if (tryChess[x][y - 2] != 0) {
                            tempNull[x][y] = getTryPointScore(tryChess, x, y, c);
                            if (max < tempNull[x][y]) {
                                max = tempNull[x][y];
                                P[0].x = x;
                                P[0].y = y;
                            }
                            P[0].count++;
                            continue;
                        }
                    }

                    if (isInside(x, y + 1)) {
                        if (tryChess[x][y + 1] != 0) {
                            tempNull[x][y] = getTryPointScore(tryChess, x, y, c);
                            if (max < tempNull[x][y]) {
                                max = tempNull[x][y];
                                P[0].x = x;
                                P[0].y = y;
                            }
                            P[0].count++;
                            continue;
                        }
                    }

                    if (isInside(x, y + 2)) {
                        if (tryChess[x][y + 2] != 0) {
                            tempNull[x][y] = getTryPointScore(tryChess, x, y, c);
                            if (max < tempNull[x][y]) {
                                max = tempNull[x][y];
                                P[0].x = x;
                                P[0].y = y;
                            }
                            P[0].count++;
                            continue;
                        }
                    }


                    if (isInside(x + 1, y + 1)) {
                        if (tryChess[x + 1][y + 1] != 0) {
                            tempNull[x][y] = getTryPointScore(tryChess, x, y, c);
                            if (max < tempNull[x][y]) {
                                max = tempNull[x][y];
                                P[0].x = x;
                                P[0].y = y;
                            }
                            P[0].count++;
                            continue;
                        }
                    }


                    if (isInside(x + 2, y + 2)) {
                        if (tryChess[x + 2][y + 2] != 0) {
                            tempNull[x][y] = getTryPointScore(tryChess, x, y, c);
                            if (max < tempNull[x][y]) {
                                max = tempNull[x][y];
                                P[0].x = x;
                                P[0].y = y;
                            }
                            P[0].count++;
                            continue;
                        }
                    }


                    if (isInside(x - 1, y + 1)) {
                        if (tryChess[x - 1][y + 1] != 0) {
                            tempNull[x][y] = getTryPointScore(tryChess, x, y, c);
                            if (max < tempNull[x][y]) {
                                max = tempNull[x][y];
                                P[0].x = x;
                                P[0].y = y;
                            }
                            P[0].count++;
                            continue;
                        }
                    }


                    if (isInside(x - 2, y + 2)) {
                        if (tryChess[x - 2][y + 2] != 0) {
                            tempNull[x][y] = getTryPointScore(tryChess, x, y, c);
                            if (max < tempNull[x][y]) {
                                max = tempNull[x][y];
                                P[0].x = x;
                                P[0].y = y;
                            }
                            P[0].count++;
                            continue;
                        }
                    }

                    if (isInside(x + 1, y - 1)) {
                        if (tryChess[x + 1][y - 1] != 0) {
                            tempNull[x][y] = getTryPointScore(tryChess, x, y, c);
                            if (max < tempNull[x][y]) {
                                max = tempNull[x][y];
                                P[0].x = x;
                                P[0].y = y;
                            }
                            P[0].count++;
                            continue;
                        }
                    }

                    if (isInside(x + 2, y - 2)) {
                        if (tryChess[x + 2][y - 2] != 0) {
                            tempNull[x][y] = getTryPointScore(tryChess, x, y, c);
                            if (max < tempNull[x][y]) {
                                max = tempNull[x][y];
                                P[0].x = x;
                                P[0].y = y;
                            }
                            P[0].count++;
                            continue;
                        }
                    }


                    if (isInside(x - 1, y - 1)) {
                        if (tryChess[x - 1][y - 1] != 0) {
                            tempNull[x][y] = getTryPointScore(tryChess, x, y, c);
                            if (max < tempNull[x][y]) {
                                max = tempNull[x][y];
                                P[0].x = x;
                                P[0].y = y;
                            }
                            P[0].count++;
                            continue;
                        }
                    }


                    if (isInside(x - 2, y - 2)) {
                        if (tryChess[x - 2][y - 2] != 0) {
                            tempNull[x][y] = getTryPointScore(tryChess, x, y, c);
                            if (max < tempNull[x][y]) {
                                max = tempNull[x][y];
                                P[0].x = x;
                                P[0].y = y;
                            }
                            P[0].count++;
                            continue;
                        }
                    }

                    if (isInside(x - 1, y + 2)) {
                        if (tryChess[x - 1][y + 2] != 0) {
                            tempNull[x][y] = getTryPointScore(tryChess, x, y, c);
                            if (max < tempNull[x][y]) {
                                max = tempNull[x][y];
                                P[0].x = x;
                                P[0].y = y;
                            }
                            P[0].count++;
                            continue;
                        }
                    }

                    if (isInside(x - 2, y + 1)) {
                        if (tryChess[x - 2][y + 1] != 0) {
                            tempNull[x][y] = getTryPointScore(tryChess, x, y, c);
                            if (max < tempNull[x][y]) {
                                max = tempNull[x][y];
                                P[0].x = x;
                                P[0].y = y;
                            }
                            P[0].count++;
                            continue;
                        }
                    }

                    if (isInside(x + 1, y + 2)) {
                        if (tryChess[x + 1][y + 2] != 0) {
                            tempNull[x][y] = getTryPointScore(tryChess, x, y, c);
                            if (max < tempNull[x][y]) {
                                max = tempNull[x][y];
                                P[0].x = x;
                                P[0].y = y;
                            }
                            P[0].count++;
                            continue;
                        }
                    }

                    if (isInside(x + 2, y + 1)) {
                        if (tryChess[x + 2][y + 1] != 0) {
                            tempNull[x][y] = getTryPointScore(tryChess, x, y, c);
                            if (max < tempNull[x][y]) {
                                max = tempNull[x][y];
                                P[0].x = x;
                                P[0].y = y;
                            }
                            P[0].count++;
                            continue;
                        }
                    }
                    if (isInside(x + 1, y - 2)) {
                        if (tryChess[x + 1][y - 2] != 0) {
                            tempNull[x][y] = getTryPointScore(tryChess, x, y, c);
                            if (max < tempNull[x][y]) {
                                max = tempNull[x][y];
                                P[0].x = x;
                                P[0].y = y;
                            }
                            P[0].count++;
                            continue;
                        }
                    }

                    if (isInside(x + 2, y - 1)) {
                        if (tryChess[x + 2][y - 1] != 0) {
                            tempNull[x][y] = getTryPointScore(tryChess, x, y, c);
                            if (max < tempNull[x][y]) {
                                max = tempNull[x][y];
                                P[0].x = x;
                                P[0].y = y;
                            }
                            P[0].count++;
                            continue;
                        }
                    }

                    if (isInside(x - 1, y - 2)) {
                        if (tryChess[x - 1][y - 2] != 0) {
                            tempNull[x][y] = getTryPointScore(tryChess, x, y, c);
                            if (max < tempNull[x][y]) {
                                max = tempNull[x][y];
                                P[0].x = x;
                                P[0].y = y;
                            }
                            P[0].count++;
                            continue;
                        }
                    }

                    if (isInside(x - 2, y - 1)) {
                        if (tryChess[x - 2][y - 1] != 0) {
                            tempNull[x][y] = getTryPointScore(tryChess, x, y, c);
                            if (max < tempNull[x][y]) {
                                max = tempNull[x][y];
                                P[0].x = x;
                                P[0].y = y;
                            }
                            P[0].count++;
                            continue;
                        }
                    }


                }

            }
        }


        if (max == 10) {
            P[0].count = 1;
            P[0].sheng = 1;
            return;
        } else if (max == 9) {
            P[0].count = 1;
            return;

        } else if (max == 8) {

            P[0].count = 0;
            for (int x = 0; x < GobangConstants.FIFTEEN; x++) {
                for (int y = 0; y < GobangConstants.FIFTEEN; y++) {
                    if (tempNull[x][y] == 8) {
                        P[P[0].count].x = x;
                        P[P[0].count].y = y;
                        P[0].count++;
                    }
                }
            }

            return;
        }


        int count = 0;
        int u = 7;
        while (u > 0) {
            for (int x = 0; x < GobangConstants.FIFTEEN; x++) {
                for (int y = 0; y < GobangConstants.FIFTEEN; y++) {
                    if (tempNull[x][y] == u) {
                        P[count].x = x;
                        P[count].y = y;
                        count++;
                    }
                }
            }
            u--;

        }
        return;
    }

    private static boolean isInside(int x, int y) {

        if (x < 0 || x >= GobangConstants.FIFTEEN || y < 0 || y >= GobangConstants.FIFTEEN) {
            return false;
        }

        return true;
    }

    private static int getTryPointScore(int[][] tryChess, int xIndex, int yIndex, int c) {

        int continueCount = 0;//连续棋子的个数

        int d = -c;
        int max = 0;
        int chance = 0;

        int score = 0;
        int x;
        int y;
//////1
        for (x = xIndex - 1; x >= 0; x--) {
            if (tryChess[x][yIndex] == c)
                continueCount++;
            else {
                if (tryChess[x][yIndex] == d)
                    chance++;
                break;
            }
        }
        if (x < 0) chance++;
        for (x = xIndex + 1; x <= GobangConstants.COLS; x++) {
            if (tryChess[x][yIndex] == c)
                continueCount++;
            else {
                if (tryChess[x][yIndex] == d)
                    chance++;
                break;
            }
        }
        if (x > GobangConstants.FIFTEEN - 1) chance++;
        score = getMyTryScore(continueCount, chance);
        if (score > max) max = score;


///////2

        continueCount = 0;
        chance = 0;
        for (y = yIndex - 1; y >= 0; y--) {
            if (tryChess[xIndex][y] == c)
                continueCount++;
            else {
                if (tryChess[xIndex][y] == d)
                    chance++;
                break;
            }
        }
        if (y < 0) chance++;

        for (y = yIndex + 1; y <= GobangConstants.ROWS; y++) {
            if (tryChess[xIndex][y] == c)
                continueCount++;
            else {
                if (tryChess[xIndex][y] == d)
                    chance++;
                break;
            }
        }
        if (y > GobangConstants.FIFTEEN - 1) chance++;
        score = getMyTryScore(continueCount, chance);
        if (score > max) max = score;


/////////3


        continueCount = 0;
        chance = 0;
        for (x = xIndex + 1, y = yIndex - 1; y >= 0 && x <= GobangConstants.COLS; x++, y--) {
            if (tryChess[x][y] == c)
                continueCount++;
            else {
                if (tryChess[x][y] == d)
                    chance++;
                break;
            }
        }
        if (y < 0 || x > GobangConstants.FIFTEEN - 1) chance++;

        for (x = xIndex - 1, y = yIndex + 1; x >= 0 && y <= GobangConstants.ROWS; x--, y++) {
            if (tryChess[x][y] == c)
                continueCount++;
            else {
                if (tryChess[x][y] == d)
                    chance++;
                break;
            }
        }
        if (x < 0 || y > GobangConstants.FIFTEEN - 1) chance++;
        score = getMyTryScore(continueCount, chance);
        if (score > max) max = score;


        //////////4

        continueCount = 0;
        chance = 0;
        for (x = xIndex - 1, y = yIndex - 1; x >= 0 && y >= 0; x--, y--) {
            if (tryChess[x][y] == c)
                continueCount++;
            else {
                if (tryChess[x][y] == d)
                    chance++;
                break;
            }
        }
        if (x < 0 || y < 0) chance++;

        for (x = xIndex + 1, y = yIndex + 1; x <= GobangConstants.COLS && y <= GobangConstants.ROWS; x++, y++) {
            if (tryChess[x][y] == c)
                continueCount++;
            else {
                if (tryChess[x][y] == d)
                    chance++;
                break;
            }
        }
        if (x > GobangConstants.FIFTEEN - 1 || y > GobangConstants.FIFTEEN - 1) chance++;
        score = getMyTryScore(continueCount, chance);
        if (score > max) max = score;


        //xxxxxxxxxxxxxxxxxxxxx2222222

        continueCount = 0;
        chance = 0;
        for (x = xIndex - 1; x >= 0; x--) {
            if (tryChess[x][yIndex] == d)
                continueCount++;
            else {
                if (tryChess[x][yIndex] == c)
                    chance++;
                break;
            }
        }
        if (x < 0) chance++;
        for (x = xIndex + 1; x <= GobangConstants.COLS; x++) {
            if (tryChess[x][yIndex] == d)
                continueCount++;
            else {
                if (tryChess[x][yIndex] == c)
                    chance++;
                break;
            }
        }
        if (x > GobangConstants.FIFTEEN - 1) chance++;
        score = getOpponentTryScore(continueCount, chance);
        if (score > max) max = score;


///////2

        continueCount = 0;
        chance = 0;
        for (y = yIndex - 1; y >= 0; y--) {
            if (tryChess[xIndex][y] == d)
                continueCount++;
            else {
                if (tryChess[xIndex][y] == c)
                    chance++;
                break;
            }
        }
        if (y < 0) chance++;

        for (y = yIndex + 1; y <= GobangConstants.ROWS; y++) {
            if (tryChess[xIndex][y] == d)
                continueCount++;
            else {
                if (tryChess[xIndex][y] == c)
                    chance++;
                break;
            }
        }
        if (y > GobangConstants.FIFTEEN - 1) chance++;
        score = getOpponentTryScore(continueCount, chance);
        if (score > max) max = score;


/////////3


        continueCount = 0;
        chance = 0;
        for (x = xIndex + 1, y = yIndex - 1; y >= 0 && x <= GobangConstants.COLS; x++, y--) {
            if (tryChess[x][y] == d)
                continueCount++;
            else {
                if (tryChess[x][y] == c)
                    chance++;
                break;
            }
        }
        if (y < 0 || x > GobangConstants.FIFTEEN - 1) chance++;

        for (x = xIndex - 1, y = yIndex + 1; x >= 0 && y <= GobangConstants.ROWS; x--, y++) {
            if (tryChess[x][y] == d)
                continueCount++;
            else {
                if (tryChess[x][y] == c)
                    chance++;
                break;
            }
        }
        if (x < 0 || y > GobangConstants.FIFTEEN - 1) chance++;
        score = getOpponentTryScore(continueCount, chance);
        if (score > max) max = score;


        //////////4

        continueCount = 0;
        chance = 0;
        for (x = xIndex - 1, y = yIndex - 1; x >= 0 && y >= 0; x--, y--) {
            if (tryChess[x][y] == d)
                continueCount++;
            else {
                if (tryChess[x][y] == c)
                    chance++;
                break;
            }
        }
        if (x < 0 || y < 0) chance++;

        for (x = xIndex + 1, y = yIndex + 1; x <= GobangConstants.COLS && y <= GobangConstants.ROWS; x++, y++) {
            if (tryChess[x][y] == d)
                continueCount++;
            else {
                if (tryChess[x][y] == c)
                    chance++;
                break;
            }
        }
        if (x > GobangConstants.FIFTEEN - 1 || y > GobangConstants.FIFTEEN - 1) chance++;
        score = getOpponentTryScore(continueCount, chance);
        if (score > max) max = score;
        return max;
    }

    private static int getMyTryScore(int t, int c) {
        switch (t) {
            case 0:
                return 2;
            case 1:
                if (c == 2) {
                    return 0;
                }
                if (c == 1) {
                    return 2;
                }
                if (c == 0) {
                    return 4;
                }
            case 2:
                if (c == 2) {
                    return 0;
                }
                if (c == 1) {
                    return 4;
                }
                if (c == 0) {
                    return 6;
                }
            case 3:
                if (c == 2) {
                    return 0;
                }
                if (c == 1) {
                    return 6;
                }
                if (c == 0) {
                    return 8;
                }
            case 4:
                return 10;

            default:
                return 10;
        }

    }

    private static int getOpponentTryScore(int t, int c) {
        switch (t) {
            case 0:
                return 1;
            case 1:
                if (c == 2) {
                    return 0;
                }
                if (c == 1) {
                    return 1;
                }
                if (c == 0) {
                    return 3;
                }
            case 2:
                if (c == 2) {
                    return 0;
                }
                if (c == 1) {
                    return 3;
                }
                if (c == 0) {
                    return 5;
                }
            case 3:
                if (c == 2) {
                    return 0;
                }
                if (c == 1) {
                    return 5;
                }
                if (c == 0) {
                    return 7;
                }
            case 4:
                return 9;

            default:
                return 9;
        }

    }

}
