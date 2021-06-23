package com.zylear.gobangai.core.calculator;

import com.zylear.gobangai.Point;
import com.zylear.gobangai.bean.GobangConstants;
import com.zylear.gobangai.bean.NextPointHuntBean;
import com.zylear.gobangai.core.GobangOperation;
import com.zylear.gobangai.core.score.GobangChessScoreCoreV2;
import com.zylear.gobangai.core.score.ScoreCalculator;
import com.zylear.gobangai.core.trypoint.GobangTryChessCore;
import com.zylear.gobangai.core.trypoint.NextPointHunter;
import com.zylear.gobangai.ui.GobangPanel.BestPoint;

import java.util.LinkedList;
import java.util.List;

/**
 * @author xiezongyu
 * @date 2021/6/23
 */
public class NormalMinMaxCalculator implements MinMaxCalculator {


    private ScoreCalculator scoreCalculator;
    private NextPointHunter nextPointHunter;
    private BestPoint bestPoint = new BestPoint();

    public NormalMinMaxCalculator(ScoreCalculator scoreCalculator, NextPointHunter nextPointHunter) {
        this.scoreCalculator = scoreCalculator;
        this.nextPointHunter = nextPointHunter;
    }


    @Override
    public int calculate(int[][] tryChess, int depth, int maxDepth, int alpha, int beta, int calculateColor) {

        depth--;
        if (depth == 0) {
            return scoreCalculator.getChessScore(tryChess, calculateColor);
        }


        if ((depth & 1) == 0) {

            NextPointHuntBean huntBean = nextPointHunter.getNextPointList(tryChess, calculateColor);
            List<Point> points = huntBean.points;

            for (Point point : points) {


                tryChess[point.x][point.y] = calculateColor;
                int score;
                if (huntBean.finished) {
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
                        bestPoint.score = alpha;
                        if (alpha == GobangConstants.WIN_SCORE) {
//                            System.out.println("博弈预算胜出！current i: " + i + " total: " + tryPoints[0].count);
                            break;
                        }
                    }
                }
                tryChess[point.x][point.y] = 0;
            }

            return alpha;

        } else {

            NextPointHuntBean huntBean = nextPointHunter.getNextPointList(tryChess, calculateColor);
            List<Point> points = huntBean.points;

            for (Point point : points) {
                tryChess[point.x][point.y] = -calculateColor;
                int t;
                if (huntBean.finished) {
                    t = GobangConstants.LOSE_SCORE;
                } else {
                    t = calculate(tryChess, depth, maxDepth, alpha, beta, calculateColor);
                }

                //there is α-β pruning core
                if (t <= alpha) {
                    tryChess[point.x][point.y] = 0;
                    return t;
                }

                if (t < beta) {
                    beta = t;
                }

                tryChess[point.x][point.y] = 0;

            }
            return beta;
        }
    }

}
