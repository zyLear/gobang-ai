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
abstract public class GobangScoreCalculatorBase implements ScoreCalculator {

    private int[][] tryList = {
            {1, 1}, {1, -1}, {1, 0}, {0, 1}
    };

    @Override
    public int getChessScore(int[][] tryChess, int calculateColor) {

        GobangStatistic.calculateCount++;


        Integer score;

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
            score = whiteScore - blackScore;
        } else {
            score = blackScore - whiteScore;
        }

        return score;
    }


    private int getPointScore(int[][] tryChess, int xIndex, int yIndex, int color) {


        int score = 0;

        for (int[] ints : tryList) {
            int xDirection = ints[0];
            int yDirection = ints[1];


            if (!preCalculateScore(tryChess, xIndex, yIndex, xDirection, yDirection, color)) {
                continue;
            }
            score += getScore(tryChess, xIndex, yIndex, xDirection, yDirection, color);
            score += getScore(tryChess, xIndex, yIndex, -xDirection, -yDirection, color);
        }
        return score;


    }

    abstract protected boolean preCalculateScore(int[][] tryChess, int xIndex, int yIndex, int xDirection, int yDirection, int calculateColor);

    abstract protected int getScore(int[][] tryChess, int xIndex, int yIndex, int xDirection, int yDirection, int color);

}
