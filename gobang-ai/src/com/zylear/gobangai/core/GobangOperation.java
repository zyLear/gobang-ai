package com.zylear.gobangai.core;

import com.zylear.gobangai.bean.GobangConstants;

import java.util.Arrays;

/**
 * Created by xiezongyu on 2018/9/7.
 */
public class GobangOperation {


    public static boolean isInside(int x, int y) {

        return x >= 0 && x < GobangConstants.FIFTEEN && y >= 0 && y < GobangConstants.FIFTEEN;
    }

    public static boolean isLessFive(int[][] tryChess, int xIndex, int yIndex, int xDirect, int yDirect, int c) {

        boolean lessFive;
        int init = 4;

        int color = -c;


        int x;
        int y;
        init = getInit(tryChess, xIndex, yIndex, xDirect, yDirect, init, color);

        for (x = xIndex - xDirect, y = yIndex - yDirect; x >= 0 && x <= GobangConstants.COLS && y >= 0 && y <= GobangConstants.ROWS; x = x - xDirect, y = y - yDirect) {
            if (tryChess[x][y] != color)
                init--;
            if (tryChess[x][y] == color)
                break;
            if (init <= 0)
                break;
        }


        if (init > 0)
            lessFive = true;
        else
            lessFive = false;


        return lessFive;
    }



    public static boolean isLessFiveV2(int[][] tryChess, int xIndex, int yIndex, int xDirection, int yDirection, int color) {

        int init = 4;

        int opponentColor = -color;

        init = getInit(tryChess, xIndex, yIndex, xDirection, yDirection, init, opponentColor);
        init = getInit(tryChess, xIndex, -yIndex, -xDirection, yDirection, init, opponentColor);

        return init > 0;
    }

    private static int getInit(int[][] tryChess, int xIndex, int yIndex, int xDirection, int yDirection, int init, int opponentColor) {
        int x;
        int y;
        for (x = xIndex + xDirection, y = yIndex + yDirection;
             x >= 0 && x <= GobangConstants.COLS && y >= 0 && y <= GobangConstants.ROWS;
             x = x + xDirection, y = y + yDirection) {
            if (tryChess[x][y] != opponentColor) {
                init--;
            }
            if (tryChess[x][y] == opponentColor) {
                break;
            }
            if (init <= 0) {
                break;
            }
        }
        return init;
    }


    public static Integer getUniqueKey(int x, int y, int calculateColor) {
        return (x * 15 + y + 1) * calculateColor;
    }

    public static String getUniqueKeyV3(int[][] tryChess) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < tryChess.length; i++) {
            for (int j = 0; j < tryChess[i].length; j++) {
                stringBuilder.append(tryChess[i][j]);
            }
        }
        return stringBuilder.toString();
    }

    public static String getUniqueKeyV2(int[][] tryChess) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < tryChess.length; i++) {
            stringBuilder.append(Arrays.toString(tryChess[i]));
        }
        return stringBuilder.toString();
    }


    public static void main(String[] args) {
        int[][] a = new int[15][15];
        a[3][5] = -1;
        a[3][8] = 1;
        long time = System.currentTimeMillis();
//        for (int i = 0; i < 300000; i++) {
//            getUniqueKeyV2(a);
//        }
        long newTime = System.currentTimeMillis();
        System.out.println(newTime - time);
        time = System.currentTimeMillis();
//        for (int i = 0; i < 300000; i++) {
//            getUniqueKeyV3(a);
//        }
        newTime = System.currentTimeMillis();
        System.out.println(newTime - time);

        System.out.println(getUniqueKeyV3(a));
//        System.out.println(getUniqueKey(0, 0, 1));
//        System.out.println(getUniqueKey(0, 4, 1));
//        System.out.println(getUniqueKey(1, 4, 1));
    }
}
