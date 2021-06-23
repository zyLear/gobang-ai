package com.zylear.gobangai.core.trypoint;

import com.zylear.gobangai.Point;
import com.zylear.gobangai.bean.NextPointHuntBean;

import java.util.LinkedList;

/**
 * @author xiezongyu
 * @date 2021/6/23
 */
public interface NextPointHunter {

//    Point[] getTryPoints(int[][] tryChess, int color);

    NextPointHuntBean getNextPointList(int[][] tryChess, int color);

}
