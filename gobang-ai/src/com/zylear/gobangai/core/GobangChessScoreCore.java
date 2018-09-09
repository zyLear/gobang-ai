package com.zylear.gobangai.core;


import com.zylear.gobangai.bean.ChessColor;
import com.zylear.gobangai.bean.GobangConstants;

/**
 * Created by xiezongyu on 2018/9/7.
 */
public class GobangChessScoreCore {


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

    private static int getPointScore(int[][] tryChess, int xIndex, int yIndex, int c) {
        int continueCount = 1;//连续棋子的个数
        int d = 1;
        if (c == 1)
            d = -1;
        int chance = 0;
        int x;
        int y;
        int score = 0;
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
        score = score + getScore(continueCount, chance);


///////2

        continueCount = 1;
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
        score = score + getScore(continueCount, chance);


/////////3


        continueCount = 1;
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
        score = score + getScore(continueCount, chance);


        //////////4

        continueCount = 1;
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
        score = score + getScore(continueCount, chance);

        return score;
    }

    private static int getScore(int t, int c) {
        switch (t) {
            case 1:
                if (c == 2) {
                    return 0;
                }
                if (c == 1) {
                    return 0;
                }
                if (c == 0) {
                    return 10;
                }
            case 2:
                if (c == 2) {
                    return 0;
                }
                if (c == 1) {
                    return 10;
                }
                if (c == 0) {
                    return 100;
                }
            case 3:
                if (c == 2) {
                    return 0;
                }
                if (c == 1) {
                    return 100;
                }
                if (c == 0) {
                    return 1000;
                }
            case 4:
                if (c == 2) {
                    return 0;
                }
                if (c == 1) {
                    return 1000;
                }
                if (c == 0) {
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
