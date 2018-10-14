package com.zylear.gobangai.core;


import com.zylear.gobangai.GobangPanel.BestPoint;
import com.zylear.gobangai.NullPoint;
import com.zylear.gobangai.Point;
import com.zylear.gobangai.bean.GobangConstants;
import com.zylear.gobangai.cache.GobangCache;


/**
 * with execute calculate . with network strategy
 * <p>
 * <p>
 * Created by xiezongyu on 2018/9/11.
 */
public class GobangCoreV4 {

    public static final Integer deadline = 60000;
    private static long bottomCount = 0;
    private static long repeatedCount = 0;
    private static long nodeCount = 0;

    private static Integer maxGameDepth;
    private static Integer maxExecuteDepth;
    private static BestPoint bestPoint;


    public synchronized static BestPoint calculate(int[][] tryChess, int gameDepth, int executeDepth, int calculateColor) {
        maxExecuteDepth = executeDepth;
        maxGameDepth = gameDepth;
        bestPoint = new BestPoint();
        int score;

        String uniqueKeyV3 = GobangOperation.getUniqueKeyV3(tryChess);
        BestPoint bestPoint = GobangCache.gobangOptimizeMap.get(uniqueKeyV3);
        if (bestPoint != null && bestPoint.score == GobangConstants.WIN_SCORE) {
            repeatedCount++;
            System.out.println("v4 beat my key first  score: " + bestPoint.score);
            return bestPoint;
        }

        score = execute(tryChess, executeDepth, GobangConstants.ALPHA, GobangConstants.BETA, calculateColor);
        if (score != GobangConstants.WIN_SCORE) {
            System.out.println("算杀失败，正常博弈...");
            score = minMax(tryChess, gameDepth, GobangConstants.ALPHA, GobangConstants.BETA, calculateColor);
        } else {
            System.out.println("电脑算杀成功");
        }
        GobangCache.gobangOptimizeMap.put(uniqueKeyV3, GobangCoreV4.bestPoint);

        System.out.println("分数：" + score +
                "   bottomCount:" + bottomCount +
                "   repeatedCount:" + repeatedCount +
                "   nodeCount:" + nodeCount);
        bottomCount = 0;
        repeatedCount = 0;
        nodeCount = 0;
        return GobangCoreV4.bestPoint;

    }


    public static int minMax(int[][] tryChess, int depth, int alpha, int beta, int calculateColor) {
//        nodeCount++;
        depth--;
        if (depth == 0) {
//            bottomCount++;
            String tryUniqueKey = GobangOperation.getUniqueKeyV3(tryChess);
            BestPoint bestPoint = GobangCache.gobangOptimizeMap.get(tryUniqueKey);
            if (bestPoint != null) {
                repeatedCount++;
                System.out.println("v4 beat my key  score: " + bestPoint.score);
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
                        System.out.println("v4 beat my key middle  score: " + bestPoint.score);
                        return bestPoint.score;
                    }

                }
            }


            Point[] tryPoints = new Point[225];
            GobangTryChessCore.getTryPoints(tryChess, tryPoints, calculateColor);
            if (depth == maxGameDepth - 1) {
                System.out.println("nearest depth try count :" + tryPoints[0].count);
            }
            for (int i = 0; i < tryPoints[0].count; i++) {

                tryChess[tryPoints[i].x][tryPoints[i].y] = calculateColor;
                int t;
                if (tryPoints[0].sheng == 1) {
//                    t = GobangChessScoreCoreV2.getChessScore(tryChess, calculateColor);
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
                        bestPoint.x = tryPoints[i].x;
                        bestPoint.y = tryPoints[i].y;
                        bestPoint.score = alpha;
                        if (alpha == GobangConstants.WIN_SCORE) {
                            System.out.println("博弈预算胜出！current i: " + i + " total: " + tryPoints[0].count);
                            break;
                        }
                    }
                }
                tryChess[tryPoints[i].x][tryPoints[i].y] = 0;
            }

            return alpha;

        } else {

            Point[] tryPoints = new Point[225];
            GobangTryChessCore.getTryPoints(tryChess, tryPoints, -calculateColor);
            for (int i = 0; i < tryPoints[0].count; i++) {
                Integer tryUniqueKey = GobangOperation.getUniqueKey(tryPoints[i].x, tryPoints[i].y, -calculateColor);
                tryChess[tryPoints[i].x][tryPoints[i].y] = -calculateColor;
                int t;
                if (tryPoints[0].sheng == 1) {
//                    t = GobangChessScoreCoreV2.getChessScore(tryChess, calculateColor);
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


    //
    public static int execute(int[][] tryChess, int depth, int alpha, int beta, int calculateColor) {


        depth--;
        if (depth == 0) {

            String tryUniqueKey = GobangOperation.getUniqueKeyV3(tryChess);
            BestPoint bestPoint = GobangCache.gobangOptimizeMap.get(tryUniqueKey);
            if (bestPoint != null && bestPoint.score == GobangConstants.WIN_SCORE) {
                repeatedCount++;
                System.out.println("v4 execute beat my key  score: " + bestPoint.score);
                return bestPoint.score;
            }

            return 0;
        }


        if (depth % 2 == 0) {

            if (depth != maxExecuteDepth - 1) {
                String tryUniqueKey = GobangOperation.getUniqueKeyV3(tryChess);
                BestPoint bestPoint = GobangCache.gobangOptimizeMap.get(tryUniqueKey);
                if (bestPoint != null) {
                    if (bestPoint.score == GobangConstants.WIN_SCORE ) {
                        repeatedCount++;
                        System.out.println("v4 execute beat my key middle  score: " + bestPoint.score);
                        return bestPoint.score;
                    }

                }
            }


            NullPoint[] tryPoints = new NullPoint[225];

            NullPoint np;
            NullPoint mark = new NullPoint();

//            getSSNewDown(p, mark, calculateColor);
            GobangExecuteTryChessCore.getTryPoints(tryChess, tryPoints, mark, calculateColor);

            if (depth == maxExecuteDepth - 1) {
                System.out.println("v4 execute next depth count：" + mark.count);
            }

            if (mark.count == 0) {
                return 0;
            }
            for (int i = 0; i < mark.count; i++) {

                np = tryPoints[i];
                tryChess[np.x][np.y] = calculateColor;
                int t;
                if (mark.sheng == 1) {
                    t = GobangConstants.WIN_SCORE;
                } else {
                    t = execute(tryChess, depth, alpha, beta, calculateColor);
                }


                if (t >= beta) {
                    tryChess[np.x][np.y] = 0;
                    return t;
                }

                if (t > alpha) {
                    alpha = t;

                    if (depth == maxExecuteDepth - 1) {
//                        fx = np.x;
//                        fy = np.y;
                        bestPoint.x = tryPoints[i].x;
                        bestPoint.y = tryPoints[i].y;
                        bestPoint.score = alpha;
                        if (alpha == GobangConstants.WIN_SCORE) {
                            System.out.println("v4 execute success！current i: " + i + " total: " + mark.count);
                            break;
                        }
                    }

                }
                if (depth == maxExecuteDepth - 1) {
                    System.out.println("v4 execute nearest depth score:下层分数：" + alpha);
                }
                tryChess[np.x][np.y] = 0;
            }
            return alpha;
        } else {

            NullPoint[] tryPoints = new NullPoint[225];

            NullPoint np;
            NullPoint mark = new NullPoint();
            GobangExecuteTryChessCore.getTryPoints(tryChess, tryPoints, mark, -calculateColor);

            if (mark.count == 0) {
                return 0;
            }
            for (int i = 0; i < mark.count; i++) {

                np = tryPoints[i];
                tryChess[np.x][np.y] = -calculateColor;   //尝试下棋子

                int t;
                if (mark.sheng == 1) {
                    t = GobangConstants.LOSE_SCORE;
                } else {
                    t = execute(tryChess, depth, alpha, beta, calculateColor);
                }

                if (t <= alpha) {
                    tryChess[np.x][np.y] = 0;

                    return t;
                }

                if (t < beta) {
                    beta = t;
                }
                tryChess[np.x][np.y] = 0;       //取走尝试下的棋子
            }
            return beta;

        }

    }


}
