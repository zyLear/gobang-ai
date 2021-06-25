package com.zylear.gobangai.core.score;

import com.zylear.gobangai.bean.ChessColor;
import com.zylear.gobangai.bean.GobangConstants;
import com.zylear.gobangai.cache.GobangCache;
import com.zylear.gobangai.core.GobangOperation;
import com.zylear.gobangai.core.GobangStatistic;
import com.zylear.gobangai.ui.GobangPanel.BestPoint;

import java.util.BitSet;

/**
 * @author xiezongyu
 * @date 2021/6/24
 */
public class GobangScoreCalculator implements ScoreCalculator {



    @Override
    public int getChessScore(int[][] tryChess, int calculateColor) {

        GobangStatistic.calculateCount++;

//        BitSet tryUniqueKey = GobangOperation.getUniqueKeyV4(tryChess);
//        Integer score = GobangCache.scoreCache.get(tryUniqueKey);
//        if (score != null) {
//            GobangStatistic.hitCacheCount++;
//            return score;
//        }
        Integer score = 0;

        int whiteScore = 0;
        int blackScore = 0;

        for (int i = 0; i < GobangConstants.FIFTEEN; i++) {
            for (int j = 0; j < GobangConstants.FIFTEEN; j++) {
                if (tryChess[i][j] == ChessColor.WHITE) {
                    whiteScore = whiteScore + getPointScore(tryChess, i, j, ChessColor.WHITE);
                }
                if (tryChess[i][j] == ChessColor.BLACK) {
                    blackScore = blackScore + getPointScore(tryChess, i, j, ChessColor.BLACK);
                }
            }
        }
        if (ChessColor.WHITE == calculateColor) {
            score= whiteScore - blackScore;
        } else {
            score= blackScore - whiteScore;
        }

//        GobangCache.scoreCache.put(tryUniqueKey, score);
        return score;
    }

    private int[][] tryList = {
            {1, 1}, {1, -1}, {1, 0}, {0, 1}
    };

    private int getPointScore(int[][] tryChess, int xIndex, int yIndex, int color) {


        int score = 0;

        for (int[] ints : tryList) {
            int xDirection = ints[0];
            int yDirection = ints[1];

            if (!GobangOperation.isLessFiveV2(tryChess, xIndex, yIndex, xDirection, yDirection, color)) {
                score += getScore(tryChess, xIndex, yIndex, xDirection, yDirection, color);
                score += getScore(tryChess, xIndex, yIndex, -xDirection, -yDirection, color);
            }
        }
        return score;


    }

    private int getScore(int[][] tryChess, int xIndex, int yIndex, int xDirection, int yDirection, int color) {
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


    private int getPointScore(int score, boolean blockOneSide) {
        switch (score) {
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
