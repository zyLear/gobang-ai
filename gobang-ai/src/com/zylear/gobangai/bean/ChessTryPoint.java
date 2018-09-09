package com.zylear.gobangai.bean;

public class ChessTryPoint {

    public Integer x;
    public Integer y;

    public Integer count = 0;
    public Boolean canWin = false;
    public Integer score = 0;


    public ChessTryPoint(int x, int y, int score) {
        this.x = x;
        this.y = y;
        score = score;
    }


}
