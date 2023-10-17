package com.zylear.gobangai.core;

import com.zylear.gobangai.bean.GobangConstants;

/**
 * Created by xiezongyu on 2018/9/6.
 */
public class GobangJudge {

    private static boolean isWin(int[][] chessBoard, int originX, int originY, int xDirection, int yDirection, int color) {
        int x;
        int y;
        int continueCount = 1;
        for (x = originX + xDirection, y = originY + yDirection;
             x >= 0 && x <= 14 && y >= 0 && y <= 14;
             x = x + xDirection, y = y + yDirection) {
            if (chessBoard[x][y] == color) {
                continueCount++;
            } else {
                break;
            }

            if (continueCount >= 5) {
                return true;
            }
        }

        for (x = originX - xDirection, y = originY - yDirection;
             x >= 0 && x <= 14 && y >= 0 && y <= 14;
             x = x - xDirection, y = y - yDirection) {
            if (chessBoard[x][y] == color) {
                continueCount++;
            } else {
                break;
            }

            if (continueCount >= 5) {
                return true;
            }
        }
        return false;

    }

    public static boolean isWin(int[][] chessBoard, int x, int y, int color) {

        for (int[] ints : GobangConstants.DIRECTIONS) {
            int xDirection = ints[0];
            int yDirection = ints[1];
            if (isWin(chessBoard, x, y, xDirection, yDirection, color)) {
                return true;
            }
        }

        return false;
    }


    public static void main(String[] args) {
        int[][] ints = new int[15][15];
        ints[4][14] = 1;
        if (isWin(ints, 4, 14, 1, -1, 1)) {

        }
    }
}
