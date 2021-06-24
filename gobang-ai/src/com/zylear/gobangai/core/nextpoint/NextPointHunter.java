package com.zylear.gobangai.core.nextpoint;

import com.zylear.gobangai.bean.NextPointHuntBean;

/**
 * @author xiezongyu
 * @date 2021/6/23
 */
public interface NextPointHunter {

//    Point[] getTryPoints(int[][] tryChess, int color);

    NextPointHuntBean getNextPointList(int[][] tryChess, int color);

}
