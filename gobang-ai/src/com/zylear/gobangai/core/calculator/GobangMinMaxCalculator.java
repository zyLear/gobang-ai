package com.zylear.gobangai.core.calculator;

import com.zylear.gobangai.Point;
import com.zylear.gobangai.bean.GobangConstants;
import com.zylear.gobangai.bean.NextPointHuntBean;
import com.zylear.gobangai.core.score.ScoreCalculator;
import com.zylear.gobangai.core.nextpoint.NextPointHunter;
import com.zylear.gobangai.ui.GobangPanel.BestPoint;

import java.util.List;

/**
 * @author xiezongyu
 * @date 2021/6/23
 */
public class GobangMinMaxCalculator implements MinMaxCalculator {


    private ScoreCalculator scoreCalculator;
    private NextPointHunter nextPointHunter;

    private BestPoint bestPoint = new BestPoint();


    public GobangMinMaxCalculator(ScoreCalculator scoreCalculator, NextPointHunter nextPointHunter) {
        this.scoreCalculator = scoreCalculator;
        this.nextPointHunter = nextPointHunter;
    }


    @Override
    public BestPoint getBestPoint(int[][] tryChess, int maxDepth, int calculateColor) {

        bestPoint.x = 0;
        bestPoint.y = 0;
        bestPoint.score = calculate(tryChess, maxDepth, maxDepth, GobangConstants.ALPHA, GobangConstants.BETA, calculateColor);

        return bestPoint;
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

                if (score > alpha) {
                    alpha = score;
                    if (depth == maxDepth - 1) {
                        bestPoint.x = point.x;
                        bestPoint.y = point.y;
//                        bestPoint.score = alpha;
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


}
