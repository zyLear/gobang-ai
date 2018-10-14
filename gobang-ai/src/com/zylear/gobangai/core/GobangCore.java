package com.zylear.gobangai.core;


import com.zylear.gobangai.GobangPanel.BestPoint;
import com.zylear.gobangai.NullPoint;
import com.zylear.gobangai.Point;
import com.zylear.gobangai.bean.GobangConstants;


/**
 * Created by xiezongyu on 2018/9/6.
 */
public class GobangCore {


    public static final Integer deadline = 60000;
    public static int count = 0;


    public static BestPoint calculate(int[][] tryChess, int gameDepth, int executeDepth, int calculateColor) {
        BestPoint bestPoint = new BestPoint();
        int score = GobangCore.minMax(tryChess, gameDepth, gameDepth, GobangConstants.ALPHA, GobangConstants.BETA, calculateColor, bestPoint);
        System.out.println("分数：" + score + " count:" + count);
        count = 0;
        return bestPoint;
    }


    public static int minMax(int[][] tryChess, int depth, int maxDepth, int alpha, int beta, int calculateColor, BestPoint bestPoint) {

//        if (System.currentTimeMillis() - currentime > deadline) return 1;
        depth--;
        if (depth == 0) {
            return GobangChessScoreCoreV2.getChessScore(tryChess, calculateColor);
        }


        if (depth % 2 == 0) {

            Point[] tryPoints = new Point[225];
            GobangTryChessCore.getTryPoints(tryChess, tryPoints, calculateColor);
            if (depth == maxDepth - 1) {
                System.out.println("shu:" + tryPoints[0].count);
            }
            for (int i = 0; i < tryPoints[0].count; i++) {


                tryChess[tryPoints[i].x][tryPoints[i].y] = calculateColor;
                int t;
                if (tryPoints[0].sheng == 1) {
//                    t = GobangChessScoreCoreV2.getChessScore(tryChess, calculateColor);
                    t = GobangConstants.WIN_SCORE;
                } else {
                    t = minMax(tryChess, depth, maxDepth, alpha, beta, calculateColor, bestPoint);
                }


                if (t >= beta) {

                    tryChess[tryPoints[i].x][tryPoints[i].y] = 0;
                    return t;
                }


                if (t > alpha) {
                    alpha = t;
                    if (depth == maxDepth - 1) {
                        bestPoint.x = tryPoints[i].x;
                        bestPoint.y = tryPoints[i].y;
                        bestPoint.score = alpha;
                    }
                }
                tryChess[tryPoints[i].x][tryPoints[i].y] = 0;
            }


            return alpha;

        } else {

            Point[] p = new Point[225];
            GobangTryChessCore.getTryPoints(tryChess, p, -calculateColor);
            for (int i = 0; i < p[0].count; i++) {
                tryChess[p[i].x][p[i].y] = -calculateColor;
                int t;
                if (p[0].sheng == 1) {
                    t = GobangConstants.LOSE_SCORE;
                } else {
                    t = minMax(tryChess, depth, maxDepth, alpha, beta, calculateColor, bestPoint);
                }

                if (t <= alpha) {
                    tryChess[p[i].x][p[i].y] = 0;
                    return t;
                }

                if (t < beta) {
                    beta = t;
                }

                tryChess[p[i].x][p[i].y] = 0;

            }

            return beta;
        }
    }



}
