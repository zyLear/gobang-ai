package com.zylear.gobangai.core.robot;

import com.zylear.gobangai.ui.GobangPanel.BestPoint;

public interface GobangRobot {


    BestPoint think(int[][] tryChess, int gameDepth, int executeDepth, int calculateColor);

}
