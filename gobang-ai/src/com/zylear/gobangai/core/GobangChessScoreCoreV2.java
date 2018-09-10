package com.zylear.gobangai.core;


import com.zylear.gobangai.bean.ChessColor;
import com.zylear.gobangai.bean.GobangConstants;


/**
 * Created by xiezongyu on 2018/9/7.
 */
public class GobangChessScoreCoreV2 {


    public static int getChessScore(int[][] tryChess, int calculateColor) {
        int whiteScore = 0;
        int blackScore = 0;

        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                if (tryChess[i][j] == ChessColor.WHITE) {
                    whiteScore = whiteScore + getPointScore(tryChess, i, j, ChessColor.WHITE);
                }
                if (tryChess[i][j] == ChessColor.BLACK) {
                    blackScore = blackScore + getPointScore(tryChess, i, j, ChessColor.BLACK);
                }
            }
        }
        if (ChessColor.WHITE == calculateColor) {
            return whiteScore - blackScore;
        } else {
            return blackScore - whiteScore;
        }
    }

    private static int getPointScore(int[][] tryChess, int a, int b, int c) {

        int x;
        int y;
//        int init = 4;

        int continueCount = 1;//连续棋子的个数
        int Count = 0;
        int sign = 0;
        int score = 0;


        boolean lessFive = false;
        int xIndex = a;
        int yIndex = b;
        boolean bb = false;

//1
        int chance = 1;


        if (!GobangOperation.isLessFive(tryChess, xIndex, yIndex, 1, 0, c)) {

            bb = false;

            for (x = xIndex - 1; x >= 0; x--) {
                if (tryChess[x][yIndex] == c)
                    continueCount++;
                else {
                    if (tryChess[x][yIndex] != 0)
                        sign = 1;

                    break;
                }
            }

            if (x < 0) sign = 1;


            for (x = xIndex + 1; x <= GobangConstants.COLS; x++) {

                if (tryChess[x][yIndex] == c)
                    continueCount++;


                else {

                    if (chance == 1 && tryChess[x][yIndex] == 0) {
                        chance--;
                        Count = continueCount;
                        continue;
                    }


                    if (sign == 0 && tryChess[x][yIndex] == 0 && chance == 0 && Count != continueCount)
                        bb = true;


                    break;

                }

            }
            if (sign == 0 && chance == 0 && Count == continueCount)
                bb = true;

            if (sign == 1 && x > GobangConstants.COLS) continueCount = 0;


            score += getPointScore(continueCount, bb);


//2


            //  if(false)

            sign = 0;
            chance = 1;
            continueCount = 1;
            bb = false;

            for (x = xIndex + 1; x <= GobangConstants.COLS; x++) {

                if (tryChess[x][yIndex] == c)
                    continueCount++;


                else {
                    if (tryChess[x][yIndex] != 0)

                        sign = 1;

                    break;
                }
            }
            if (x > GobangConstants.COLS) sign = 1;

            for (x = xIndex - 1; x >= 0; x--) {
                if (tryChess[x][yIndex] == c)
                    continueCount++;


                else {
                    if (chance == 1 && tryChess[x][yIndex] == 0) {
                        chance--;
                        Count = continueCount;
                        continue;
                    }


                    if (sign == 0 && tryChess[x][yIndex] == 0 && chance == 0 && Count != continueCount)
                        bb = true;


                    break;
                }
            }
            if (sign == 0 && chance == 0 && Count == continueCount)
                bb = true;

            if (sign == 1 && x < 0) continueCount = 0;

            score += getPointScore(continueCount, bb);

        }


        //3
        if (!GobangOperation.isLessFive(tryChess, xIndex, yIndex, 0, 1, c)) {
            bb = false;

            sign = 0;
            chance = 1;
            continueCount = 1;
            for (y = yIndex - 1; y >= 0; y--) {

                if (tryChess[xIndex][y] == c)
                    continueCount++;
                else {
                    if (tryChess[xIndex][y] != 0)
                        sign = 1;
                    break;
                }
            }
            if (y < 0) sign = 1;


            for (y = yIndex + 1; y <= GobangConstants.ROWS; y++) {

                if (tryChess[xIndex][y] == c)
                    continueCount++;


                else {
                    if (chance == 1 && tryChess[xIndex][y] == 0) {
                        chance--;
                        Count = continueCount;
                        continue;
                    }

                    if (sign == 0 && tryChess[xIndex][y] == 0 && chance == 0 && Count != continueCount)
                        bb = true;


                    break;
                }

            }
            if (sign == 0 && chance == 0 && Count == continueCount)
                bb = true;

            if (sign == 1 && y > GobangConstants.ROWS) continueCount = 0;

            score += getPointScore(continueCount, bb);


//4

            bb = false;
            sign = 0;
            chance = 1;
            continueCount = 1;
            for (y = yIndex + 1; y <= GobangConstants.ROWS; y++) {

                if (tryChess[xIndex][y] == c)
                    continueCount++;


                else {
                    if (tryChess[xIndex][y] != 0)

                        sign = 1;
                    break;
                }

            }
            if (y > GobangConstants.ROWS) sign = 1;


            for (y = yIndex - 1; y >= 0; y--) {

                if (tryChess[xIndex][y] == c)
                    continueCount++;


                else {
                    if (chance == 1 && tryChess[xIndex][y] == 0) {
                        chance--;
                        Count = continueCount;
                        continue;
                    }


                    if (sign == 0 && tryChess[xIndex][y] == 0 && chance == 0 && Count != continueCount)
                        bb = true;

                    break;
                }
            }

            if (sign == 1 && y < 0) continueCount = 0;
            if (sign == 0 && chance == 0 && Count == continueCount)
                bb = true;

            score += getPointScore(continueCount, bb);
        }

        ////xxxxxxxxxx3


        //5
        if (!GobangOperation.isLessFive(tryChess, xIndex, yIndex, 1, -1, c)) {

            bb = false;
            sign = 0;
            chance = 1;
            continueCount = 1;

            for (x = xIndex + 1, y = yIndex - 1; y >= 0 && x <= GobangConstants.COLS; x++, y--) {

                if (tryChess[x][y] == c)
                    continueCount++;


                else {
                    if (tryChess[xIndex][y] != 0)

                        sign = 1;
                    break;
                }
            }
            if (x > GobangConstants.ROWS || y < 0) sign = 1;

            //西南寻找
            for (x = xIndex - 1, y = yIndex + 1; x >= 0 && y <= GobangConstants.ROWS; x--, y++) {

                if (tryChess[x][y] == c) {
                    continueCount++;
                } else {
                    if (chance == 1 && tryChess[x][y] == 0) {
                        chance--;
                        Count = continueCount;
                        continue;
                    }

                    if (sign == 0 && tryChess[x][y] == 0 && chance == 0 && Count != continueCount)
                        bb = true;

                    break;
                }
            }

            if (sign == 1 && (x < 0 || y > GobangConstants.ROWS)) continueCount = 0;
            if (sign == 0 && chance == 0 && Count == continueCount)
                bb = true;

            score += getPointScore(continueCount, bb);


            //6


            bb = false;
            sign = 0;
            chance = 1;
            continueCount = 1;


            //西南寻找
            for (x = xIndex - 1, y = yIndex + 1; x >= 0 && y <= GobangConstants.ROWS; x--, y++) {

                if (tryChess[x][y] == c)
                    continueCount++;


                else {
                    if (tryChess[x][y] != 0)

                        sign = 1;
                    break;
                }
            }
            if (x < 0 || y > GobangConstants.ROWS) sign = 1;


            for (x = xIndex + 1, y = yIndex - 1; y >= 0 && x <= GobangConstants.COLS; x++, y--) {

                if (tryChess[x][y] == c) {
                    continueCount++;

                } else {
                    if (chance == 1 && tryChess[x][y] == 0) {
                        chance--;
                        Count = continueCount;
                        continue;
                    }

                    if (sign == 0 && tryChess[x][y] == 0 && chance == 0 && Count != continueCount)
                        bb = true;

                    break;
                }


            }

            if (sign == 1 && (x > GobangConstants.ROWS || y < 0)) continueCount = 0;
            if (sign == 0 && chance == 0 && Count == continueCount)
                bb = true;

            score += getPointScore(continueCount, bb);
        }


        ////////xxxxxxxxx4
//.7

        if (!GobangOperation.isLessFive(tryChess, xIndex, yIndex, 1, 1, c)) {
            bb = false;
            sign = 0;
            chance = 1;
            continueCount = 1;

            for (x = xIndex - 1, y = yIndex - 1; x >= 0 && y >= 0; x--, y--) {

                if (tryChess[x][y] == c)
                    continueCount++;

                else {
                    if (tryChess[x][y] != 0)

                        sign = 1;
                    break;
                }
            }
            if (x < 0 || y < 0) sign = 1;


            for (x = xIndex + 1, y = yIndex + 1; x <= GobangConstants.COLS && y <= GobangConstants.ROWS; x++, y++) {

                if (tryChess[x][y] == c) {
                    continueCount++;

                } else {
                    if (chance == 1 && tryChess[x][y] == 0) {
                        chance--;
                        Count = continueCount;
                        continue;
                    }

                    if (sign == 0 && tryChess[x][y] == 0 && chance == 0 && Count != continueCount)
                        bb = true;

                    break;
                }
            }

            if (sign == 1 && (x > GobangConstants.ROWS || y > GobangConstants.ROWS)) continueCount = 0;
            if (sign == 0 && chance == 0 && Count == continueCount)
                bb = true;

            score += getPointScore(continueCount, bb);


            //8


            bb = false;
            chance = 1;
            continueCount = 1;
            sign = 0;

            for (x = xIndex + 1, y = yIndex + 1; x <= GobangConstants.COLS && y <= GobangConstants.ROWS; x++, y++) {

                if (tryChess[x][y] == c)
                    continueCount++;


                else {
                    if (tryChess[x][y] != 0)

                        sign = 1;
                    break;
                }
            }
            if (x > 14 || y > 14) sign = 1;


            for (x = xIndex - 1, y = yIndex - 1; x >= 0 && y >= 0; x--, y--) {

                if (tryChess[x][y] == c) {
                    continueCount++;
                } else {
                    if (chance == 1 && tryChess[x][y] == 0) {
                        chance--;
                        Count = continueCount;
                        continue;
                    }

                    if (sign == 0 && tryChess[x][y] == 0 && chance == 0 && Count != continueCount)
                        bb = true;

                    break;
                }
            }

            if (sign == 1 && (x < 0 || y < 0)) continueCount = 0;


            if (sign == 0 && chance == 0 && Count == continueCount)
                bb = true;

            score += getPointScore(continueCount, bb);

        }


        //1


        return score;


    }


    private static int getPointScore(int t, boolean bb) {
        switch (t) {
            case 0:
                return 0;
            case 1:
                if (!bb) {
                    return 0;
                } else {
                    return 10;
                }

            case 2:
                if (!bb) {
                    return 10;
                } else {
                    return 100;
                }
            case 3:
                if (!bb) {
                    return 100;
                } else {
                    return 1000;
                }
            case 4:
                if (!bb) {
                    return 1000;
                } else {
                    return 10000;
                }
            case 5:
                return 1000000;
            //if(c==2){return 1000000;}
            // if(c==1){return 100000;}
            //if(c==0){return 1000000;}
            default:
                return 1000000;
            //	if(c==2){return 1000000;}
            // if(c==1){return 100000;}
            // if(c==0){return 1000000;}
        }
    }


}
