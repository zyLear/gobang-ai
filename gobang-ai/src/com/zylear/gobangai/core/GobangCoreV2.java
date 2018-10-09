package com.zylear.gobangai.core;


import com.zylear.gobangai.GobangPanel.BestPoint;
import com.zylear.gobangai.NullPoint;
import com.zylear.gobangai.Point;
import com.zylear.gobangai.bean.GobangConstants;

import java.util.ArrayList;
import java.util.List;


/**
 * with execute. without network strategy
 * <p>
 * <p>
 * Created by xiezongyu on 2018/9/9.
 */
public class GobangCoreV2 {

    public static final Integer deadline = 60000;

    private static Integer maxGameDepth;
    private static Integer maxExecuteDepth;
    private static BestPoint result;


    public synchronized static BestPoint calculate(int[][] tryChess, int gameDepth, int executeDepth, int calculateColor) {
        maxExecuteDepth = executeDepth;
        maxGameDepth = gameDepth;
        result = new BestPoint();
        int score;

        score = execute(tryChess, executeDepth, GobangConstants.ALPHA, GobangConstants.BETA, calculateColor);
        if (score != GobangConstants.WIN_SCORE) {
            System.out.println("电脑算杀失败，正常博弈...");
            score = minMax(tryChess, gameDepth, GobangConstants.ALPHA, GobangConstants.BETA, calculateColor);
        } else {
            System.out.println("电脑算杀成功");
        }

        System.out.println("分数：" + score);
        return result;

    }


    public static int minMax(int[][] tryChess, int depth, int alpha, int beta, int calculateColor) {
//        depth--;
        if (depth == 0) {
            return GobangChessScoreCoreV2.getChessScore(tryChess, calculateColor);
        }

        if (depth % 2 == 0) {
            List<Point> tryPoints = new ArrayList<>(128);
            boolean canOver = GobangTryChessCore.getTryPoints(tryChess, tryPoints, calculateColor);

            for (Point currentPoint : tryPoints) {
                tryChess[currentPoint.x][currentPoint.y] = calculateColor;
                int score;
                if (canOver) {
                    score = GobangConstants.WIN_SCORE;
                } else {
                    score = minMax(tryChess, depth - 1, alpha, beta, calculateColor);
                }
                if (score >= beta) {
                    tryChess[currentPoint.x][currentPoint.y] = 0;
                    return score;
                }
                if (score > alpha) {
                    alpha = score;
                    if (depth == maxGameDepth) {
                        result.x = currentPoint.x;
                        result.y = currentPoint.y;
                        if (alpha == GobangConstants.WIN_SCORE) {
                            break;
                        }
                    }
                }
                tryChess[currentPoint.x][currentPoint.y] = 0;
            }
            return alpha;
        } else {
            List<Point> tryPoints = new ArrayList<>(128);
            boolean canOver = GobangTryChessCore.getTryPoints(tryChess, tryPoints, -calculateColor);
            for (Point currentPoint : tryPoints) {
                tryChess[currentPoint.x][currentPoint.y] = -calculateColor;
                int score;
                if (canOver) {
                    score = GobangConstants.LOSE_SCORE;
                } else {
                    score = minMax(tryChess, depth - 1, alpha, beta, calculateColor);
                }
                if (score <= alpha) {
                    tryChess[currentPoint.x][currentPoint.y] = 0;
                    return score;
                }
                if (score < beta) {
                    beta = score;
                }
                tryChess[currentPoint.x][currentPoint.y] = 0;
            }
            return beta;
        }
    }

    //
    public static int execute(int[][] tryChess, int depth, int alpha, int beta, int calculateColor) {


//        depth--;
        if (depth == 0) {
            return 0;
        }


        if (depth % 2 == 0) {

            NullPoint[] tryPoints = new NullPoint[225];

            NullPoint np;
            NullPoint mark = new NullPoint();

//            getSSNewDown(p, mark, calculateColor);
            GobangExecuteTryChessCore.getTryPoints(tryChess, tryPoints, mark, calculateColor);

            if (depth == maxExecuteDepth) {
                System.out.println("execute next depth count：" + mark.count);
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
                    t = execute(tryChess, depth - 1, alpha, beta, calculateColor);
                }


                if (t >= beta) {
                    tryChess[np.x][np.y] = 0;
                    return t;
                }

                if (t > alpha) {
                    alpha = t;

                    if (depth == maxExecuteDepth) {
//                        fx = np.x;
//                        fy = np.y;
                        result.x = tryPoints[i].x;
                        result.y = tryPoints[i].y;

                        if (alpha == GobangConstants.WIN_SCORE) {
                            System.out.println("算杀成功！current i: " + i + " total: " + mark.count);
                            break;
                        }
                    }


                }

                if (depth == maxExecuteDepth) {
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
                } else {
                    t = execute(tryChess, depth - 1, alpha, beta, calculateColor);
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
