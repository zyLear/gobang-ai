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
//        long currentTime = System.currentTimeMillis();
        int score;
        BestPoint bestPoint = new BestPoint();
//        score = GobangCore.execute(tryChess, executeDepth, executeDepth, GobangConstants.ALPHA, GobangConstants.BETA, calculateColor, bestPoint);
//        if (score != GobangConstants.WIN_SCORE) {
//            System.out.println("算杀失败，开启敌人算杀");
//            calculateColor = -calculateColor;
//            score = GobangCore.execute(tryChess, executeDepth, executeDepth, GobangConstants.ALPHA, GobangConstants.BETA, calculateColor, bestPoint);
//            calculateColor = -calculateColor;
//            if (score != GobangConstants.WIN_SCORE) {
//                System.out.println("对手算杀失败，正常博弈...");
//

                score = GobangCore.minMax(tryChess, gameDepth, gameDepth, GobangConstants.ALPHA, GobangConstants.BETA, calculateColor, bestPoint);
//            } else {
//                System.out.println("对手算杀成功");
//            }
//        } else {
//            System.out.println("电脑算杀成功");
//        }

        System.out.println("分数：" + score + " count:" + count);
        count = 0;
        return bestPoint;
//        long time = (System.currentTimeMillis() - currentTime);

//        if (time > deadline) {
//
//            gameDepth -= 2;
//            for (int x = 0; x < GobangConstants.FIFTEEN; x++) {
//                for (int y = 0; y < GobangConstants.FIFTEEN; y++) {
//                    tryChess[x][y] = 0;
//                }
//            }
//
////            for (int i = 0; i < chessCount; i++) {
////                if (chessList[i].getColor() == Color.white)
////                    tryChess[chessList[i].getX()][chessList[i].getY()] = 1;
////                else tryChess[chessList[i].getX()][chessList[i].getY()] = -1;
////            }
//
//
//            System.out.println("时间超时，采用快速搜索战略");
////            noti.setText("时间超时，采用快速搜索战略");
////            currentTime = System.currentTimeMillis();
//            highScore = GobangCore.minMax(tryChess, gameDepth, gameDepth, ALPHA, BETA, computerColor, bestPoint);
//        }


    }


    public static int minMax(int[][] tryChess, int depth, int maxDepth, int alpha, int beta, int calculateColor, BestPoint bestPoint) {

//        if (System.currentTimeMillis() - currentime > deadline) return 1;
        depth--;
        if (depth == 0) {
//            count++;
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
                    t = GobangChessScoreCoreV2.getChessScore(tryChess, calculateColor);
                } else {
                    t = minMax(tryChess, depth, maxDepth, alpha, beta, calculateColor, bestPoint);
//                    if (System.currentTimeMillis() - currentime > deadline) return 1;
                }


                if (t >= beta) {

                    tryChess[tryPoints[i].x][tryPoints[i].y] = 0;
                    return t;
                }


                if (t > alpha) {
                    alpha = t;
                    if (depth == maxDepth - 1) {
//                        BestPoint pp = new BestPoint(p[i].x, p[i].y, ALPHA);
                        // bestPoints.add(pp);
                        bestPoint.x = tryPoints[i].x;
                        bestPoint.y = tryPoints[i].y;
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
                    t = GobangChessScoreCoreV2.getChessScore(tryChess, calculateColor);
                } else {
                    t = minMax(tryChess, depth, maxDepth, alpha, beta, calculateColor, bestPoint);
//                    if (System.currentTimeMillis() - currentime > deadline) return 1;
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


    //
    public static int execute(int[][] tryChess, int depth, int maxDepth, int alpha, int beta, int calculateColor, BestPoint bestPoint) {


        depth--;
        if (depth == 0) {
            return 0;
        }


        if (depth % 2 == 0) {

            NullPoint[] tryPoints = new NullPoint[225];

            NullPoint np;
            NullPoint mark = new NullPoint();

//            getSSNewDown(p, mark, calculateColor);
            GobangExecuteTryChessCore.getTryPoints(tryChess, tryPoints, mark, calculateColor);

            if (depth == maxDepth - 1) {
                System.out.println("shu：" + mark.count);
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


                } else
                    t = execute(tryChess, depth, maxDepth, alpha, beta, calculateColor, bestPoint);


                if (t >= beta) {

                    tryChess[np.x][np.y] = 0;
                    return t;
                }

                if (t > alpha) {
                    alpha = t;

                    if (depth == maxDepth - 1) {
//                        fx = np.x;
//                        fy = np.y;
                        bestPoint.x = tryPoints[i].x;
                        bestPoint.y = tryPoints[i].y;


                        if (alpha == 10000000) {
                            System.out.println("准备结束！");
                            break;
                        }
                    }


                }

                if (depth == maxDepth - 1) {
                    System.out.println("aa下层分数：" + alpha);
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


                } else
                    t = execute(tryChess, depth, maxDepth, alpha, beta, calculateColor, bestPoint);

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