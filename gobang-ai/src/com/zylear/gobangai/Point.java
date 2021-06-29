package com.zylear.gobangai;


import java.awt.*;

public class Point {

    public int x;//棋盘中的x索引
    public int y;//棋盘中的y索引
    public Color color;//颜色
    public static final int DIAMETER = 30;//直径
    public int count = 0;
    public int sheng = 0;
    public int score = 0;
    public int chessColor;

    public Point(int x, int y, Color color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }

    public Point(int x, int y) {
        this.x = x;
        this.y = y;

    }


    public Point() {

    }

    public int getX() {//拿到棋盘中x的索引
        return x;
    }

    public int getY() {
        return y;
    }

    public Color getColor() {//获得棋子的颜色
        return color;
    }

    public void setColor(Color color) {//获得棋子的颜色
        this.color = color;
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                ", color=" + chessColor +
                ", score=" + score +
                '}';
    }
}

