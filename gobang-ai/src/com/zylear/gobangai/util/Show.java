package com.zylear.gobangai.util;

import com.zylear.gobangai.Point;
import com.zylear.gobangai.bean.GobangConstant;
import com.zylear.gobangai.core.GobangChessScoreCoreV2;
import com.zylear.gobangai.core.GobangTryChessCore;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiezongyu on 2018/10/14.
 */
public class Show {

    int computerColor = 1;
    int humanColor = -1;


    public int miniMax(int[][] tryChess, int depth) {
        if (depth == 0) {
            return getChessScore(tryChess);    //返回估值函数计算出的局面的分数
        }
        if (depth % 2 == 0) {     // 偶数层 电脑下棋
            List<Point> tryPoints = getTryPoints(tryChess); //获取下一步能下棋的位置
            int alpha = Integer.MIN_VALUE;
            for (Point point : tryPoints) {
                tryChess[point.x][point.y] = computerColor;   //尝试下这一步
                int score = miniMax(tryChess, depth - 1);  //递归计算出分数
                if (score > alpha) {
                    alpha = score;
                }
                tryChess[point.x][point.y] = 0;  //取消尝试的棋子
            }
            return alpha;  //偶数层 返回最大值
        } else {         // 奇数层 玩家下棋
            List<Point> tryPoints = getTryPoints(tryChess);
            int beta = Integer.MAX_VALUE;
            for (Point point : tryPoints) {
                tryChess[point.x][point.y] = humanColor;
                int score = miniMax(tryChess, depth - 1);
                if (score < beta) {
                    beta = score;
                }
                tryChess[point.x][point.y] = 0;
            }
            return beta;
        }
    }

    private int getChessScore(int[][] tryChess) {
        return 0;
    }

    private List<Point> getTryPoints(int[][] tryChess) {
        return new ArrayList<>();
    }

    private int getChessScore(int[][] tryChess, int calculateColor) {
        return 0;
    }


    public int miniMax(int[][] tryChess, int depth, int alpha, int beta) {
        if (depth == 0) {
            return getChessScore(tryChess);    //返回估值函数计算出的局面的分数
        }
        if (depth % 2 == 0) {     // 偶数层 电脑下棋
            List<Point> tryPoints = getTryPoints(tryChess); //启发式搜索
            for (Point point : tryPoints) {
                tryChess[point.x][point.y] = computerColor;   //尝试下这一步
                int score = miniMax(tryChess, depth - 1);  //递归计算出分数
                if (score >= beta) {
                    tryChess[point.x][point.y] = 0; //取消尝试的棋子
                    return score;
                }
                if (score > alpha) {
                    alpha = score;
                }
                tryChess[point.x][point.y] = 0;  //取消尝试的棋子
            }
            return alpha;  //偶数层 返回最大值
        } else {         // 奇数层 玩家下棋
            List<Point> tryPoints = getTryPoints(tryChess);
            for (Point point : tryPoints) {
                tryChess[point.x][point.y] = humanColor;
                int score = miniMax(tryChess, depth - 1);
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
