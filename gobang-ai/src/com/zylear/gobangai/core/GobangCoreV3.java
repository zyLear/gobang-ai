//package com.zylear.gobangai.core;
//
//
//import com.zylear.gobangai.GobangPanel.BestPoint;
//import com.zylear.gobangai.NullPoint;
//import com.zylear.gobangai.Point;
//import com.zylear.gobangai.bean.GobangConstant;
//import com.zylear.gobangai.cache.GobangCache;
//
//
///**
// * try to add visited array to reduce duplicate calculate.
// * try to serialize 2d array.
// *
// * 20180911 update
// * try to use the result calculated at the deepest point.
// *
// * without checkmate calculate
// *
// * <p>
// * Created by xiezongyu on 2018/9/9.
// */
//public class GobangCoreV3 {
//
//    public static final Integer deadline = 60000;
//    private static long bottomCount = 0;
//    private static long repeatedCount = 0;
//    private static long nodeCount = 0;
//    private static BestPoint bestPoint;
//
//    private static Integer maxGameDepth;
//    private static Integer maxExecuteDepth;
////    private static BestPoint bestPoint;
////    private static Integer chessCount;
//
//    //    private static Set<Integer> visited;
////    private static Map<String, Integer> visitedMap  = new HashMap<>(10000000);
////    private static Map<Integer, Map<String, Integer>> totalVisitedMap = new HashMap<>(15 * 15);
//
//
//    public synchronized static BestPoint calculate(int[][] tryChess, int gameDepth, int executeDepth, int calculateColor) {
//        maxExecuteDepth = executeDepth;
//        maxGameDepth = gameDepth;
////        GobangCoreV3.chessCount = chessCount;
////        bestPoint = new BestPoint();
//        int score;
////        visited = new HashSet<>(1000000);
////        visitedMap = new HashMap<>(10000000);
////        visitedMap = totalVisitedMap.computeIfAbsent(chessCount, new Function<Integer, Map<String, Integer>>() {
////            @Override
////            public Map<String, Integer> apply(Integer integer) {
////                visitedMap = new HashMap<>(10000000);
////                totalVisitedMap.put(chessCount, visitedMap);
////                return visitedMap;
////            }
////        });
////        if (visitedMap == null) {
////            visitedMap = new HashMap<>(10000000);
////            totalVisitedMap.put(chessCount, visitedMap);
////        }
//        String uniqueKeyV2 = GobangOperation.getUniqueKeyV2(tryChess);
////        BestPoint bestPoint = gobangOptimizeMap.get(uniqueKeyV2);
////        if (bestPoint != null) {
////            if (bestPoint.score > GobangConstant.LOSE_SCORE_SIGN) {
////                return bestPoint;
////            }
////            System.out.println("lose .. key : " + uniqueKeyV2);
////        }
//
//        score = miniMax(tryChess, gameDepth, GobangConstant.ALPHA, GobangConstant.BETA, calculateColor);
//        GobangCache.gobangOptimizeMap.put(uniqueKeyV2, GobangCoreV3.bestPoint);
//
//        System.out.println("v3 分数：" + score +
//                "   bottomCount:" + bottomCount +
//                "   repeatedCount:" + repeatedCount +
//                "   nodeCount:" + nodeCount);
//
//        bottomCount = 0;
//        repeatedCount = 0;
//        nodeCount = 0;
//        return GobangCoreV3.bestPoint;
//
//    }
//
//
//    public static int miniMax(int[][] tryChess, int depth, int alpha, int beta, int calculateColor) {
//
//
//        depth--;
//
//
//        if (depth == 0) {
//            String tryUniqueKey = GobangOperation.getUniqueKeyV2(tryChess);
//            BestPoint bestPoint = GobangCache.gobangOptimizeMap.get(tryUniqueKey);
//            if (bestPoint != null) {
//                repeatedCount++;
//                System.out.println("beat my key  score: " + bestPoint.score);
//                return bestPoint.score;
//            }
//            return GobangChessScoreCoreV2.getChessScore(tryChess, calculateColor);
//        }
//
//
//        if (depth % 2 == 0) {
//
//            Point[] tryPoints = new Point[225];
//            GobangTryChessCore.getTryPoints(tryChess, tryPoints, calculateColor);
//            if (depth == maxGameDepth - 1) {
//                System.out.println("nearest depth try count :" + tryPoints[0].count);
//            }
//            for (int i = 0; i < tryPoints[0].count; i++) {
//                tryChess[tryPoints[i].x][tryPoints[i].y] = calculateColor;
//                int t;
//                if (tryPoints[0].sheng == 1) {
//                    t = GobangChessScoreCoreV2.getChessScore(tryChess, calculateColor);
//                } else {
//                    t = miniMax(tryChess, depth, alpha, beta, calculateColor);
//                }
//                if (t >= beta) {
////                    visitedMap.put(tryUniqueKey, t);
//                    tryChess[tryPoints[i].x][tryPoints[i].y] = 0;
//                    return t;
//                }
//
//                if (t > alpha) {
//                    alpha = t;
//                    if (depth == maxGameDepth - 1) {
//                        bestPoint = new BestPoint();
//                        bestPoint.x = tryPoints[i].x;
//                        bestPoint.y = tryPoints[i].y;
//                        bestPoint.score = alpha;
//                    }
//                }
////                visited.remove(tryUniqueKey);
//                tryChess[tryPoints[i].x][tryPoints[i].y] = 0;
//            }
//            return alpha;
//
//        } else {
//
//            Point[] tryPoints = new Point[225];
//            GobangTryChessCore.getTryPoints(tryChess, tryPoints, -calculateColor);
//            for (int i = 0; i < tryPoints[0].count; i++) {
////                Integer tryUniqueKey = GobangOperation.getUniqueKey(tryPoints[i].x, tryPoints[i].y, -calculateColor);
////                visited.add(tryUniqueKey);
////                String tryUniqueKey = GobangOperation.getUniqueKeyV2(tryChess);
////                Integer integer = visitedMap.get(tryUniqueKey);
////                if (integer != null) {
////                    repeatedCount++;
//////                    visited.remove(tryUniqueKey);
////                    return integer;
////                }
//                tryChess[tryPoints[i].x][tryPoints[i].y] = -calculateColor;
//                int t;
//                if (tryPoints[0].sheng == 1) {
//                    t = GobangChessScoreCoreV2.getChessScore(tryChess, calculateColor);
//                } else {
//                    t = miniMax(tryChess, depth, alpha, beta, calculateColor);
//                }
////                visitedMap.put(GobangOperation.getUniqueKeyV2(tryChess), t);
//
//                //there is α-β pruning core
//                if (t <= alpha) {
//                    tryChess[tryPoints[i].x][tryPoints[i].y] = 0;
////                    visitedMap.put(tryUniqueKey, t);
//                    return t;
//                }
//
//                if (t < beta) {
//                    beta = t;
//                }
////                visited.remove(tryUniqueKey);
//                tryChess[tryPoints[i].x][tryPoints[i].y] = 0;
//
//            }
////            visitedMap.put(tryUniqueKey, beta);
//            return beta;
//        }
//    }
//
//
//    //
//    public static int checkmate(int[][] tryChess, int depth, int alpha, int beta, int calculateColor) {
//
//
//        depth--;
//        if (depth == 0) {
//            return 0;
//        }
//
//
//        if (depth % 2 == 0) {
//
//            NullPoint[] tryPoints = new NullPoint[225];
//
//            NullPoint np;
//            NullPoint mark = new NullPoint();
//
//            GobangCheckmateTryChessCore.getTryPoints(tryChess, tryPoints, mark, calculateColor);
//
//            if (depth == maxExecuteDepth - 1) {
//                System.out.println("shu：" + mark.count);
//            }
//
//            if (mark.count == 0) {
//                return 0;
//            }
//            for (int i = 0; i < mark.count; i++) {
//
//
//                np = tryPoints[i];
//                tryChess[np.x][np.y] = calculateColor;
//                int t;
//                if (mark.sheng == 1) {
//
//                    t = GobangConstant.WIN_SCORE;
//
//
//                } else
//                    t = checkmate(tryChess, depth, alpha, beta, calculateColor);
//
//
//                if (t >= beta) {
//
//                    tryChess[np.x][np.y] = 0;
//                    return t;
//                }
//
//                if (t > alpha) {
//                    alpha = t;
//
//                    if (depth == maxExecuteDepth - 1) {
////                        bestPoint.x = tryPoints[i].x;
////                        bestPoint.y = tryPoints[i].y;
//
//
//                        if (alpha == 10000000) {
//                            System.out.println("准备结束！");
//                            break;
//                        }
//                    }
//
//
//                }
//
//                if (depth == maxExecuteDepth - 1) {
//                    System.out.println("aa下层分数：" + alpha);
//                }
//
//                tryChess[np.x][np.y] = 0;
//
//            }
//
//            return alpha;
//
//        } else {
//
//            NullPoint[] tryPoints = new NullPoint[225];
//
//
//            NullPoint np;
//            NullPoint mark = new NullPoint();
//
//            GobangCheckmateTryChessCore.getTryPoints(tryChess, tryPoints, mark, -calculateColor);
//
//
//            if (mark.count == 0) {
//                return 0;
//            }
//            for (int i = 0; i < mark.count; i++) {
//
//                np = tryPoints[i];
//                tryChess[np.x][np.y] = -calculateColor;   //尝试下棋子
//
//                int t;
//                if (mark.sheng == 1) {
//                    t = GobangConstant.LOSE_SCORE;
//
//
//                } else
//                    t = checkmate(tryChess, depth, alpha, beta, calculateColor);
//
//
//                if (t <= alpha) {
//                    tryChess[np.x][np.y] = 0;
//
//                    return t;
//                }
//
//
//                if (t < beta) {
//                    beta = t;
//                }
//
//                tryChess[np.x][np.y] = 0;       //取走尝试下的棋子
//
//            }
//            return beta;
//
//        }
//
//    }
//
//
//}
