package com.zylear.gobangai.core.calculator;

import com.zylear.gobangai.bean.Point;
import com.zylear.gobangai.bean.GobangConstants;
import com.zylear.gobangai.bean.NextPointHuntBean;
import com.zylear.gobangai.core.nextpoint.NextPointHunter;
import com.zylear.gobangai.core.score.ScoreCalculator;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author xiezongyu
 * @date 2021/6/23
 */
public class GobangMinMaxCalculator implements MinMaxCalculator {


    private final ScoreCalculator scoreCalculator;
    private final NextPointHunter nextPointHunter;

    private Point defaultPoint = new Point();
    private final List<Point> resultPoints = new ArrayList<>(30);
    private LinkedList<Point> record = new LinkedList<>();

    public GobangMinMaxCalculator(ScoreCalculator scoreCalculator, NextPointHunter nextPointHunter) {
        this.scoreCalculator = scoreCalculator;
        this.nextPointHunter = nextPointHunter;
    }


    @Override
    public Point getBestPoint(int[][] tryChess, int maxDepth, int calculateColor) {

        defaultPoint.x = 0;
        defaultPoint.y = 0;
        defaultPoint.score = 0;
        resultPoints.clear();
        calculate(tryChess, maxDepth, maxDepth, GobangConstants.ALPHA, GobangConstants.BETA, calculateColor);
        System.out.println("resultPoints: " + resultPoints);
        if (resultPoints.isEmpty()) {
            return defaultPoint;
        } else {
//            int index = new Random(System.currentTimeMillis()).nextInt(resultPoints.size());
            int index = 0;
            System.out.println("get result index:" + index);
            return resultPoints.get(index);
        }
    }

    @Override
    public int calculate(int[][] tryChess, int depth, int maxDepth, int alpha, int beta, int calculateColor) {

        depth--;
        if (depth == 0) {
            return scoreCalculator.getChessScore(tryChess, calculateColor);
        }


        if ((depth % 2) == 0) {

            int max = Integer.MIN_VALUE;

            NextPointHuntBean huntBean = nextPointHunter.getNextPointList(tryChess, calculateColor);
            List<Point> points = huntBean.points;
            if (points.isEmpty()) {
                return scoreCalculator.getChessScore(tryChess, calculateColor);
            }
            for (Point point : points) {

//                record.addLast(point);
                tryChess[point.x][point.y] = calculateColor;
                int score;
                if (huntBean.canwin) {
                    score = GobangConstants.WIN_SCORE;
//                    System.out.println(record);
                } else {
                    score = calculate(tryChess, depth, maxDepth, alpha, beta, calculateColor);
                }

//                if (depth == maxDepth - 1) {
//                    System.out.println("下层分数：" + point + " 分数：" + score);
//                }


                //there is α-β pruning core
                if (score >= beta) {

                    tryChess[point.x][point.y] = 0;
//                    record.removeLast();

//                    if (score == beta) {
//                        return score;
//                    } else {
//                        return score + 1;
//                    }
                    return score;
                }


                //博弈算法无关 处理随机
                if (score == alpha && depth == maxDepth - 1) {
                    addPoint(alpha, point);
                }

                if (score > alpha) {
                    alpha = score;
                    //博弈算法无关 处理结果
                    if (depth == maxDepth - 1) {
                        resultPoints.clear();
                        addPoint(alpha, point);
                        if (alpha == GobangConstants.WIN_SCORE) {
                            System.out.println("博弈预算胜出！ total: " + alpha);
                            break;
                        }
                    }
                }
                if (score > max) {
                    max = score;
                }

                tryChess[point.x][point.y] = 0;
//                record.removeLast();
            }

            return max;

        } else {

            int min = Integer.MAX_VALUE;

            NextPointHuntBean huntBean = nextPointHunter.getNextPointList(tryChess, -calculateColor);
            List<Point> points = huntBean.points;
            if (points.isEmpty()) {
                return scoreCalculator.getChessScore(tryChess, -calculateColor);
            }

            for (Point point : points) {
                tryChess[point.x][point.y] = -calculateColor;
//                record.addLast(point);
                int score;
                if (huntBean.canwin) {
                    score = GobangConstants.LOSE_SCORE;
                } else {
                    score = calculate(tryChess, depth, maxDepth, alpha, beta, calculateColor);
                }

                //there is α-β pruning core
                if (score <= alpha) {
                    tryChess[point.x][point.y] = 0;
//                    record.removeLast();

//                    if (score == alpha) {
//                        return score;
//                    } else {
//                        return score - 1;
//                    }
                    return score;
                }

                if (score < beta) {
                    beta = score;
                }
                if (score < min) {
                    min = score;
                }


                tryChess[point.x][point.y] = 0;
//                record.removeLast();

            }
            return min;
        }
    }

    private void addPoint(int alpha, Point point) {
        Point bestPoint = new Point();
        bestPoint.x = point.x;
        bestPoint.y = point.y;
        bestPoint.score = alpha;
        defaultPoint = bestPoint;
        resultPoints.add(bestPoint);
    }


}
