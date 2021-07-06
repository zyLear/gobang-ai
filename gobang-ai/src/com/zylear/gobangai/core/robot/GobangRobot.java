package com.zylear.gobangai.core.robot;

import com.zylear.gobangai.bean.Point;

public interface GobangRobot {

    String key();

    Point think(int[][] tryChess, int gameDepth, int executeDepth, int calculateColor);

}
