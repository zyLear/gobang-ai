package com.zylear.gobangai.core;

import com.zylear.gobangai.core.calculator.GobangMinMaxCalculator;
import com.zylear.gobangai.core.robot.GobangExecuteRobot;
import com.zylear.gobangai.core.robot.GobangRobot;
import com.zylear.gobangai.core.nextpoint.MinMaxNextPointHunter;
import com.zylear.gobangai.core.score.chess.GobangExecuteScoreCalculator;
import com.zylear.gobangai.core.score.chess.GobangScoreCalculatorV1;
import com.zylear.gobangai.core.score.chess.GobangScoreCalculatorV2;
import com.zylear.gobangai.core.score.nextpoint.chess.GobangNextPointScoreCalculator;
import com.zylear.gobangai.core.score.nextpoint.execute.ExecuteNextPointScoreCalculator;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xiezongyu
 * @date 2021/6/24
 */
public class GobangStrategy {

    public static Map<String, GobangRobot> robots = new HashMap<>();

    static {
        GobangMinMaxCalculator gobangMinMaxCalculator =
                new GobangMinMaxCalculator(new GobangScoreCalculatorV1(),
                        new MinMaxNextPointHunter(new GobangNextPointScoreCalculator()));

        GobangMinMaxCalculator executeCalculator =
                new GobangMinMaxCalculator(new GobangExecuteScoreCalculator(),
                        new MinMaxNextPointHunter(new ExecuteNextPointScoreCalculator()));

        GobangExecuteRobot gobangExecuteRobot = new GobangExecuteRobot(gobangMinMaxCalculator, executeCalculator);
        robots.put(gobangExecuteRobot.key(), gobangExecuteRobot);

    }


    public static GobangRobot getGobangRobot(String key) {
        return robots.get(key);
    }
}
