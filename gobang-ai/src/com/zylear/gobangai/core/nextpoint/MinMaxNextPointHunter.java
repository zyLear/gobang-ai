package com.zylear.gobangai.core.nextpoint;

import com.zylear.gobangai.Point;
import com.zylear.gobangai.bean.GobangConstants;
import com.zylear.gobangai.bean.NextPointHuntBean;
import com.zylear.gobangai.core.GobangOperation;
import com.zylear.gobangai.core.score.NextPointScoreCalculator;

import java.util.LinkedList;

/**
 * @author xiezongyu
 * @date 2021/6/23
 */
public class MinMaxNextPointHunter implements NextPointHunter {


    private static final Integer SCAN_RANGE = 2;

    private NextPointScoreCalculator nextPointScoreCalculator;

    public MinMaxNextPointHunter(NextPointScoreCalculator nextPointScoreCalculator) {
        this.nextPointScoreCalculator = nextPointScoreCalculator;
    }

    @Override
    public NextPointHuntBean getNextPointList(int[][] tryChess, int color) {

        NextPointHuntBean huntBean = new NextPointHuntBean();
        LinkedList<Point> list = new LinkedList<>();
        huntBean.points = list;


        int max = 0;


        outer:
        for (int x = 0; x < GobangConstants.FIFTEEN; x++) {
            for (int y = 0; y < GobangConstants.FIFTEEN; y++) {
                if (tryChess[x][y] == 0) {

                    loop:
                    for (int i = -SCAN_RANGE; i <= SCAN_RANGE; i++) {
                        for (int j = -SCAN_RANGE; j <= SCAN_RANGE; j++) {

                            int xIndex = x + i;
                            int yIndex = y + j;
                            if (GobangOperation.isInside(xIndex, yIndex) && tryChess[xIndex][yIndex] != 0) {
                                int score = nextPointScoreCalculator.getNextPointScore(tryChess, x, y, color);

                                if (score < 0) {
                                    continue;
                                }

                                Point point = new Point(x, y);
                                point.score = score;
                                if (max < score) {
                                    max = score;
                                    list.addFirst(point);
                                } else {
                                    list.addLast(point);
                                }
                                if (score == 10) {
                                    break outer;
                                }
                                break loop;
                            }
                        }
                    }
                }
            }
        }


        if (max == 10) {
            huntBean.canwin = true;
            huntBean.points = huntBean.points.subList(0, 1);
        } else if (max == 9) {
            huntBean.points = huntBean.points.subList(0, 1);
        } else {
            list.sort((o1, o2) -> Integer.compare(o2.score, o1.score));
        }

        return huntBean;
    }


}
