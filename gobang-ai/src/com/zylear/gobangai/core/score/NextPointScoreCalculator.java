package com.zylear.gobangai.core.score;

public interface NextPointScoreCalculator {

    int getNextPointScore(int[][] tryChess, int x, int y, int color);

}
