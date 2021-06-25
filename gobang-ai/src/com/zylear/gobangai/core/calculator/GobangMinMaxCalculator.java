package com.zylear.gobangai.core.calculator;

import com.zylear.gobangai.Point;
import com.zylear.gobangai.bean.GobangConstants;
import com.zylear.gobangai.bean.NextPointHuntBean;
import com.zylear.gobangai.core.score.ScoreCalculator;
import com.zylear.gobangai.core.nextpoint.NextPointHunter;
import com.zylear.gobangai.ui.GobangPanel.BestPoint;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author xiezongyu
 * @date 2021/6/23
 */
public class GobangMinMaxCalculator implements MinMaxCalculator {


    private ScoreCalculator scoreCalculator;
    private NextPointHunter nextPointHunter;

    private BestPoint defaultPoint = new BestPoint();
//    private List<BestPoint> resultPoints = new ArrayList<>(30);


    public GobangMinMaxCalculator(ScoreCalculator scoreCalculator, NextPointHunter nextPointHunter) {
        this.scoreCalculator = scoreCalculator;
        this.nextPointHunter = nextPointHunter;
    }


    @Override
    public BestPoint getBestPoint(int[][] tryChess, int maxDepth, int calculateColor) {

        defaultPoint.x = 0;
        defaultPoint.y = 0;
        defaultPoint.score = 0;
        calculate(tryChess, maxDepth, maxDepth, GobangConstants.ALPHA, GobangConstants.BETA, calculateColor);
        return defaultPoint;
    }

    @Override
    public int calculate(int[][] tryChess, int depth, int maxDepth, int alpha, int beta, int calculateColor) {

        depth--;
        if (depth == 0) {
            return scoreCalculator.getChessScore(tryChess, calculateColor);
        }


        if ((depth % 2) == 0) {

            NextPointHuntBean huntBean = nextPointHunter.getNextPointList(tryChess, calculateColor);
            List<Point> points = huntBean.points;

            for (Point point : points) {


                tryChess[point.x][point.y] = calculateColor;
                int score;
                if (huntBean.canwin) {
                    score = GobangConstants.WIN_SCORE;
                } else {
                    score = calculate(tryChess, depth, maxDepth, alpha, beta, calculateColor);
                }

                //there is α-β pruning core
                if (score >= beta) {

                    tryChess[point.x][point.y] = 0;
                    return score;
                }

                //博弈算法无关 处理随机
//                if (score == alpha && depth == maxDepth - 1) {
//                    addPoint(alpha, point);
//                }

                if (score > alpha) {
                    alpha = score;
                    //博弈算法无关 处理结果
                    if (depth == maxDepth - 1) {
                        addPoint(alpha, point);
                        if (alpha == GobangConstants.WIN_SCORE) {
                            System.out.println("博弈预算胜出！ total: " + alpha);
                            break;
                        }
                    }
                }
                tryChess[point.x][point.y] = 0;
            }

            return alpha;

        } else {

            NextPointHuntBean huntBean = nextPointHunter.getNextPointList(tryChess, -calculateColor);
            List<Point> points = huntBean.points;

            for (Point point : points) {
                tryChess[point.x][point.y] = -calculateColor;
                int score;
                if (huntBean.canwin) {
                    score = GobangConstants.LOSE_SCORE;
                } else {
                    score = calculate(tryChess, depth, maxDepth, alpha, beta, calculateColor);
                }

                //there is α-β pruning core
                if (score <= alpha) {
                    tryChess[point.x][point.y] = 0;
                    return score;
                }

                if (score < beta) {
                    beta = score;
                }

                tryChess[point.x][point.y] = 0;

            }
            return beta;
        }
    }

    private void addPoint(int alpha, Point point) {
        BestPoint bestPoint = new BestPoint();
        bestPoint.x = point.x;
        bestPoint.y = point.y;
        bestPoint.score = alpha;
        defaultPoint = bestPoint;
    }


}
