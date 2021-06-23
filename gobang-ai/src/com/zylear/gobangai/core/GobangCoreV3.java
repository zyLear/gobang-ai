package com.zylear.gobangai.core;


import com.zylear.gobangai.core.trypoint.GobangTryChessCore;
import com.zylear.gobangai.ui.GobangPanel.BestPoint;
import com.zylear.gobangai.Point;
import com.zylear.gobangai.bean.GobangConstants;
import com.zylear.gobangai.cache.GobangCache;
import com.zylear.gobangai.core.score.GobangChessScoreCoreV2;


/**
 * try to add visited array to reduce duplicate calculate.
 * try to serialize 2d array.
 * <p>
 * 20180911 update
 * try to use the result calculated at the deepest point.
 * <p>
 * without execute calculate
 * <p>
 * <p>
 * Created by xiezongyu on 2018/9/9.
 */
public class GobangCoreV3 {

    public static final Integer deadline = 60000;
    private static long bottomCount = 0;
    private static long repeatedCount = 0;
    private static long nodeCount = 0;
    private static BestPoint bestPoint;

    private static Integer maxGameDepth;
    private static Integer maxExecuteDepth;
//    private static BestPoint bestPoint;
//    private static Integer chessCount;

    //    private static Set<Integer> visited;
//    private static Map<String, Integer> visitedMap  = new HashMap<>(10000000);
//    private static Map<Integer, Map<String, Integer>> totalVisitedMap = new HashMap<>(15 * 15);


    public synchronized static BestPoint calculate(int[][] tryChess, int gameDepth, int executeDepth, int calculateColor) {
        maxExecuteDepth = executeDepth;
        maxGameDepth = gameDepth;
        int score;
        String uniqueKeyV3 = GobangOperation.getUniqueKeyV3(tryChess);

//        String tryUniqueKey = GobangOperation.getUniqueKeyV3(tryChess);
        BestPoint bestPoint = GobangCache.gobangOptimizeMap.get(uniqueKeyV3);
        if (bestPoint != null && bestPoint.score == GobangConstants.WIN_SCORE) {
            repeatedCount++;
            System.out.println("v3 beat my key first  score: " + bestPoint.score);
            return bestPoint;
        }

        score = minMax(tryChess, gameDepth, GobangConstants.ALPHA, GobangConstants.BETA, calculateColor);
        GobangCache.gobangOptimizeMap.put(uniqueKeyV3, GobangCoreV3.bestPoint);

        System.out.println("v3 分数：" + score +
                "   bottomCount:" + bottomCount +
                "   repeatedCount:" + repeatedCount +
                "   nodeCount:" + nodeCount);

        bottomCount = 0;
        repeatedCount = 0;
        nodeCount = 0;
        return GobangCoreV3.bestPoint;

    }


    public static int minMax(int[][] tryChess, int depth, int alpha, int beta, int calculateColor) {


        depth--;

        if (depth == 0) {
            String tryUniqueKey = GobangOperation.getUniqueKeyV3(tryChess);
            BestPoint bestPoint = GobangCache.gobangOptimizeMap.get(tryUniqueKey);
            if (bestPoint != null) {
                repeatedCount++;
                System.out.println("v3 beat my key  score: " + bestPoint.score);
                return bestPoint.score;
            }
            return GobangChessScoreCoreV2.getChessScore(tryChess, calculateColor);
        }


        if (depth % 2 == 0) {
            if (depth != maxGameDepth - 1) {
                String tryUniqueKey = GobangOperation.getUniqueKeyV3(tryChess);
                BestPoint bestPoint = GobangCache.gobangOptimizeMap.get(tryUniqueKey);
                if (bestPoint != null) {
                    if (bestPoint.score == GobangConstants.WIN_SCORE || bestPoint.score == GobangConstants.LOSE_SCORE) {
                        repeatedCount++;
                        System.out.println("v3 beat my key middle  score: " + bestPoint.score);
                        return bestPoint.score;
                    }

                }
            }


            Point[] tryPoints = new Point[225];
            GobangTryChessCore.getTryPoints(tryChess, tryPoints, calculateColor);
            if (depth == maxGameDepth - 1) {
                System.out.println("v3 nearest depth try count :" + tryPoints[0].count);
            }
            for (int i = 0; i < tryPoints[0].count; i++) {
                tryChess[tryPoints[i].x][tryPoints[i].y] = calculateColor;
                int t;
                if (tryPoints[0].sheng == 1) {
                    t = GobangConstants.WIN_SCORE;
                } else {
                    t = minMax(tryChess, depth, alpha, beta, calculateColor);
                }

                //there is α-β pruning core
                if (t >= beta) {
                    tryChess[tryPoints[i].x][tryPoints[i].y] = 0;
                    return t;
                }

                if (t > alpha) {
                    alpha = t;
                    if (depth == maxGameDepth - 1) {
                        bestPoint = new BestPoint();
                        bestPoint.x = tryPoints[i].x;
                        bestPoint.y = tryPoints[i].y;
                        bestPoint.score = alpha;
                    }
                }
//                if (depth == maxGameDepth - 1) {
//                    System.out.println("v3 nearest depth score:下层分数：" + alpha);
//                }
                tryChess[tryPoints[i].x][tryPoints[i].y] = 0;
            }
            return alpha;

        } else {

            Point[] tryPoints = new Point[225];
            GobangTryChessCore.getTryPoints(tryChess, tryPoints, -calculateColor);
            for (int i = 0; i < tryPoints[0].count; i++) {
                tryChess[tryPoints[i].x][tryPoints[i].y] = -calculateColor;
                int t;
                if (tryPoints[0].sheng == 1) {
                    t = GobangConstants.LOSE_SCORE;
                } else {
                    t = minMax(tryChess, depth, alpha, beta, calculateColor);
                }

                //there is α-β pruning core
                if (t <= alpha) {
                    tryChess[tryPoints[i].x][tryPoints[i].y] = 0;
                    return t;
                }

                if (t < beta) {
                    beta = t;
                }
                tryChess[tryPoints[i].x][tryPoints[i].y] = 0;

            }
            return beta;
        }
    }


}
