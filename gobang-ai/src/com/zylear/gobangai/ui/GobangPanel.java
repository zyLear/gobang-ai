package com.zylear.gobangai.ui;


import com.zylear.gobangai.Point;
import com.zylear.gobangai.bean.GobangConstants;
import com.zylear.gobangai.bean.RecordPoint;
import com.zylear.gobangai.bean.network.GobangOptimize;
import com.zylear.gobangai.bean.network.GobangResponse;
import com.zylear.gobangai.cache.GobangCache;
import com.zylear.gobangai.core.*;
import com.zylear.gobangai.core.calculator.*;
import com.zylear.gobangai.util.JsonUtil;
import com.zylear.gobangai.util.OkHttpUtil;
import okhttp3.Response;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Ellipse2D;
import java.io.IOException;
import java.util.*;
import java.util.List;

@SuppressWarnings("serial")
public class GobangPanel extends JPanel implements MouseListener {


//    private List<BestPoint> bestPoints;

    public void changeComputerFirst() {
        isComputerFirst = !isComputerFirst;
        System.out.println("current computerFirst: " + isComputerFirst);
    }

    public void changeGameDepth(int value) {
        gameDepth = value;
        System.out.println("current gameDepth: " + gameDepth);
    }

    public static class BestPoint {
        public BestPoint() {

        }

        public int x;
        public int y;
        public int score;

        public BestPoint(int x, int y, int score) {
            this.x = x;
            this.y = y;
            this.score = score;
        }
    }

    double time;
    public int fx = 0, fy = 0;
    public static final int MARGIN = 30;//边距
    public static final int GRID_SPAN = 35;//网格间距
    public static final int ROWS = 14;//棋盘行数
    public static final int COLS = 14;//棋盘列数
    public static final int FIFTEEN = 15;

    public int gameDepth = 7;
    public int executeDepth = 13;
    public boolean gamestart = false;
    boolean isVisual = false;
    private int Deadline = 60000;
    private double currentime;

    public boolean isComputerFirst = true;

    private List<RecordPoint> recordPoints = new ArrayList<>(128);
    private int highScore;

    private int strategy = 5;

    int begin = 1;
    com.zylear.gobangai.Point[] chessList = new com.zylear.gobangai.Point[(ROWS + 1) * (COLS + 1)];//初始每个数组元素为null


    int[][] tryChess = new int[15][15];
    JLabel noti = new JLabel("通知");

    private boolean pressMark = false;


    boolean isBlack = true;//默认开始是黑棋先
    boolean gameOver = false;//游戏是否结束
    int chessCount = 0;//当前棋盘棋子的个数
    int xIndex, yIndex;//当前刚下棋子的索引
    int x1, x2, y1, y2;
    boolean judgeWin;  //判断输赢
    boolean paint = false;


    public static final int WHITE = 1;
    public static final int BLACK = -1;
    int computerColor = BLACK;

    Image img;
    Image shadows;
    Color colortemp;


    public void changeStrategy(int value) {
        System.out.println("change strategy: " + value);
        this.strategy = value;
    }

    private int[][] allChess = new int[15][15];

    public GobangPanel() {
        noti.setFont(new Font("宋体", Font.PLAIN, 20));
        com.zylear.gobangai.Point ch = new com.zylear.gobangai.Point(7, 7, Color.white);
        //  chessList[0]=ch;
        // setBackground(Color.blue);//设置背景色为橘黄色
        //		   img=Toolkit.getDefaultToolkit().getImage("board.jpg");
        //		   shadows=Toolkit.getDefaultToolkit().getImage("shadows.jpg");
        this.setLayout(null);
        //     this.add(noti,new BorderLayout().SOUTH);
        this.add(noti);
        noti.setBounds(210, 530, 200, 50);
        // noti.setVisible(false);
        addMouseListener(this);
        noti.setVisible(true);
        addMouseMotionListener(new MouseMotionListener() {
            public void mouseDragged(MouseEvent e) {

            }

            public void mouseMoved(MouseEvent e) {
                int x1 = (e.getX() - MARGIN + GRID_SPAN / 2) / GRID_SPAN;
                //将鼠标点击的坐标位置转成网格索引
                int y1 = (e.getY() - MARGIN + GRID_SPAN / 2) / GRID_SPAN;
                //游戏已经结束不能下
                //落在棋盘外不能下
                //x，y位置已经有棋子存在，不能下
                if (x1 < 0 || x1 > ROWS || y1 < 0 || y1 > COLS || gameOver || findChess(x1, y1))
                    setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                    //设置成默认状态
                else setCursor(new Cursor(Cursor.HAND_CURSOR));

            }
        });

        restart();  //默认 电脑先走
    }


    protected boolean findChess(int x1, int y1) {

        for (com.zylear.gobangai.Point c : chessList) {
            if (c != null && x1 == c.getX() && y1 == c.getY())
                return true;
        }
        return false;
    }


    protected boolean getChess(int x, int y, Color c) {

        for (com.zylear.gobangai.Point p : chessList) {
            if (p != null && p.getX() == x && p.getY() == y && p.getColor() == c)
                return true;
        }

        return false;
    }


    protected boolean isNull(int x, int y) {


        if (getChess(x, y, Color.black) || (getChess(x, y, Color.white))) {
            return false;
        }

        return true;
    }


    public void restart() {

//        syncChessInfo();

        for (int a = 0; a < chessList.length; a++) {
            chessList[a] = null;
        }
        recordPoints.clear();

        if (isComputerFirst) {
            allChess = new int[15][15];
            chessCount = 1;

            com.zylear.gobangai.Point ch = new com.zylear.gobangai.Point(7, 7, Color.black);
            chessList[0] = ch;
            allChess[7][7] = BLACK;

            isBlack = false;
            computerColor = BLACK;
        } else {
            allChess = new int[15][15];
            chessCount = 0;
            isBlack = true;
            computerColor = WHITE;
        }


        gameOver = false;
        judgeWin = false;
        begin = 1;
    }

    private void syncChessInfo() {
        try {
            Response response = OkHttpUtil.syncExecJsonPost("http://127.0.0.1/publish/admin/gobang/all-record", "");
//            System.out.println("response : " + response.body().string());

            GobangResponse records = JsonUtil.getObjectFromJson(response.body().string(), GobangResponse.class);
            HashMap<String, BestPoint> map = new HashMap<>(records.getList().size());
            for (GobangOptimize gobangOptimize : records.getList()) {
                BestPoint bestPoint = new BestPoint();
                bestPoint.x = gobangOptimize.getX();
                bestPoint.y = gobangOptimize.getY();
                bestPoint.score = gobangOptimize.getScore();
                map.put(gobangOptimize.getAllChess(), bestPoint);
            }
            GobangCache.gobangOptimizeMap = map;
            response.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    public void goBack() {
        if (chessCount == 0) return;
        chessList[chessCount - 1] = null;
        chessCount--;
        isBlack = !isBlack;
        repaint();
    }


    @Override
    public void mouseClicked(MouseEvent e) {


    }


    @Override
    public void mouseEntered(MouseEvent e) {
        //if(isVisual)
        //noti.setVisible(true);

    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mousePressed(MouseEvent e) {

//		   xIndex=(e.getX()-MARGIN+GRID_SPAN/2)/GRID_SPAN;
//		   yIndex=(e.getY()-MARGIN+GRID_SPAN/2)/GRID_SPAN;
//
//		   //落在棋盘外不能下
//		   if(xIndex<0||xIndex>ROWS||yIndex<0||yIndex>COLS)
//			   return;
//		// noti.setVisible(true);

        // noti.setText("电脑正在思考.....");

        if (pressMark) {
            return;
        }
        pressMark = true;


        //  isVisual=true;
        //将鼠标点击的坐标位置转换成网格索引
        xIndex = (e.getX() - MARGIN + GRID_SPAN / 2) / GRID_SPAN;
        yIndex = (e.getY() - MARGIN + GRID_SPAN / 2) / GRID_SPAN;

        //落在棋盘外不能下
        if (xIndex < 0 || xIndex > ROWS || yIndex < 0 || yIndex > COLS) {
            pressMark = false;
            return;
        }

        //如果x，y位置已经有棋子存在，不能下
        if (findChess(xIndex, yIndex)) {
            pressMark = false;
            return;
        }


        //可以进行时的处理
        com.zylear.gobangai.Point ch = new com.zylear.gobangai.Point(xIndex, yIndex, isBlack ? Color.black : Color.white);
        chessList[chessCount++] = ch;
        int color = isBlack ? BLACK : WHITE;
        allChess[xIndex][yIndex] = color;

        boolean judgeWin = GobangJudge.isWin(allChess, xIndex, yIndex, color);


        //如果胜出则给出提示信息，不能继续下棋
        handleWin(judgeWin, isBlack);
        if (judgeWin) {
            System.out.println("return  win");
            return;
        }

        // string1+="1";
        noti.setText("电脑正在思考.....");
        repaint();


        new Thread(new Runnable() {

            @Override
            public void run() {

                GobangPanel.this.myPress();

            }

        }).start();

    }

    @Override
    public void mouseReleased(MouseEvent arg0) {


    }


    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);//画棋盘

        ImageIcon ii = null;
        ii = new ImageIcon(/*"d:\\java\\图像\\first.jpg"*/);

        Image io = ii.getImage();

        if (paint)
            g.drawImage(io, 0, 0, 585, 585, this);


        this.setBackground(Color.ORANGE);//LIGHT_GRAY

        for (int i = 0; i <= ROWS; i++) {//画横线
            g.drawLine(MARGIN, MARGIN + i * GRID_SPAN, MARGIN + COLS * GRID_SPAN, MARGIN + i * GRID_SPAN);
        }
        for (int i = 0; i <= COLS; i++) {//画竖线
            g.drawLine(MARGIN + i * GRID_SPAN, MARGIN, MARGIN + i * GRID_SPAN, MARGIN + ROWS * GRID_SPAN);

        }

        //画棋子
        for (int i = 0; i < chessCount; i++) {
            //网格交叉点x，y坐标
            int xPos = chessList[i].getX() * GRID_SPAN + MARGIN;
            int yPos = chessList[i].getY() * GRID_SPAN + MARGIN;
            g.setColor(chessList[i].getColor());//设置颜色
            // g.fillOval(xPos-Point.DIAMETER/2, yPos-Point.DIAMETER/2,
            //Point.DIAMETER, Point.DIAMETER);
            //g.drawImage(shadows, xPos-Point.DIAMETER/2, yPos-Point.DIAMETER/2, Point.DIAMETER, Point.DIAMETER, null);
            colortemp = chessList[i].getColor();
            if (colortemp == Color.black) {
                RadialGradientPaint paint = new RadialGradientPaint(xPos - com.zylear.gobangai.Point.DIAMETER / 2 + 25, yPos - com.zylear.gobangai.Point.DIAMETER / 2 + 10, 20, new float[]{0f, 1f}
                        , new Color[]{Color.WHITE, Color.BLACK});
                ((Graphics2D) g).setPaint(paint);
                ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_DEFAULT);

            } else if (colortemp == Color.white) {
                RadialGradientPaint paint = new RadialGradientPaint(xPos - com.zylear.gobangai.Point.DIAMETER / 2 + 25, yPos - com.zylear.gobangai.Point.DIAMETER / 2 + 10, 70, new float[]{0f, 1f}
                        , new Color[]{Color.WHITE, Color.BLACK});
                ((Graphics2D) g).setPaint(paint);
                ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_DEFAULT);

            }

            Ellipse2D e = new Ellipse2D.Float(xPos - com.zylear.gobangai.Point.DIAMETER / 2, yPos - com.zylear.gobangai.Point.DIAMETER / 2, 34, 35);
            ((Graphics2D) g).fill(e);
            //标记最后一个棋子的红矩形框


            if (i == chessCount - 1) {//如果是最后一个棋子
                g.setColor(Color.red);
                g.drawRect(xPos - com.zylear.gobangai.Point.DIAMETER / 2, yPos - com.zylear.gobangai.Point.DIAMETER / 2,
                        34, 35);
            }

//            if (judgeWin) {
//                g.setColor(Color.red);
//                g.drawLine(x1 * 35 + 30, y1 * 35 + 30, x2 * 35 + 30, y2 * 35 + 30);
//                g.drawLine(x1 * 35 + 30 + 1, y1 * 35 + 30, x2 * 35 + 30 + 1, y2 * 35 + 30);
//                g.drawLine(x1 * 35 + 30 - 1, y1 * 35 + 30, x2 * 35 + 30 - 1, y2 * 35 + 30);
//                g.drawLine(x1 * 35 + 30, y1 * 35 + 30 + 1, x2 * 35 + 30, y2 * 35 + 30 + 1);
//                g.drawLine(x1 * 35 + 30, y1 * 35 + 30 - 1, x2 * 35 + 30, y2 * 35 + 30 - 1);
//            }

        }
    }


    public void myPress() {

        pressMark = true;
        String info;
        com.zylear.gobangai.Point ch;
        String colorName = null;
        for (int x = 0; x < FIFTEEN; x++) {
            for (int y = 0; y < FIFTEEN; y++) {
                tryChess[x][y] = 0;
            }
        }


        isBlack = !isBlack;
        int color;

        if (begin == 1) {
            color = isBlack ? BLACK : WHITE;
            if (isNull(chessList[0].x - 1, chessList[0].y - 1)) {
                allChess[chessList[0].x - 1][chessList[0].y - 1] = color;
                ch = new com.zylear.gobangai.Point(chessList[0].x - 1, chessList[0].y - 1, isBlack ? Color.black : Color.white);
            } else {
                ch = new com.zylear.gobangai.Point(chessList[0].x - 1, chessList[0].y + 1, isBlack ? Color.black : Color.white);
                allChess[chessList[0].x - 1][chessList[0].y + 1] = color;
            }
            chessList[chessCount++] = ch;


            begin--;
            noti.setText("用时：0.001秒");
        } else {

            for (int i = 0; i < chessCount; i++) {
                if (chessList[i].getColor() == Color.white)
                    tryChess[chessList[i].getX()][chessList[i].getY()] = 1;
                else tryChess[chessList[i].getX()][chessList[i].getY()] = -1;
            }

            BestPoint bestPoint;
            long currentTime = System.currentTimeMillis();
            System.out.println();
            System.out.println("电脑正在思考.....");
            bestPoint = calculate();
            RecordPoint recordPoint = formRecordPoint(bestPoint, GobangOperation.getUniqueKeyV3(allChess));
            recordPoints.add(recordPoint);
//            submit(recordPoint);

//            bestPoint = GobangCore.calculate(tryChess, gameDepth, executeDepth, computerColor);
            time = (System.currentTimeMillis() - currentTime);
            System.out.println("用时：" + time / 1000.0 + "秒");
            noti.setText("用时：" + time / 1000.0 + "秒");

            fx = bestPoint.x;
            fy = bestPoint.y;
            xIndex = fx;
            yIndex = fy;
            ch = new Point(xIndex, yIndex, isBlack ? Color.black : Color.white);
            chessList[chessCount++] = ch;

            color = isBlack ? BLACK : WHITE;
            allChess[xIndex][yIndex] = color;


        }

        boolean judgeWin = GobangJudge.isWin(allChess, xIndex, yIndex, color);

        repaint();


        //如果胜出则给出提示信息，不能继续下棋
        handleWin(judgeWin, isBlack);

        isBlack = !isBlack;
        pressMark = false;
        //noti.setText("");

    }

    private RecordPoint formRecordPoint(BestPoint bestPoint, String uniqueKeyV2) {
        RecordPoint recordPoint = new RecordPoint();
        recordPoint.x = bestPoint.x;
        recordPoint.y = bestPoint.y;
        recordPoint.score = bestPoint.score;
        recordPoint.allChess = uniqueKeyV2;
        return recordPoint;
    }

    private void submit(RecordPoint recordPoint) {

//        bestPoints.add(bestPoint);

        new Thread(new Runnable() {
            @Override
            public void run() {
                GobangOptimize gobangOptimize = new GobangOptimize();
                gobangOptimize.setAllChess(recordPoint.allChess);
                gobangOptimize.setX(recordPoint.x);
                gobangOptimize.setY(recordPoint.y);
                gobangOptimize.setScore(recordPoint.score);
                try {
                    Response response = OkHttpUtil.syncExecJsonPost("http://127.0.0.1/publish/admin/gobang/submit", JsonUtil.toJSONString(gobangOptimize));
                    System.out.println("response : " + response);
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    private BestPoint calculate() {
        switch (strategy) {
            default:
//            case 1:
//                return GobangCore.calculate(tryChess, gameDepth, executeDepth, computerColor);
//            case 2:
//                return GobangCoreV2.calculate(tryChess, gameDepth, executeDepth, computerColor);
//            case 3:
//                return GobangCoreV3.calculate(tryChess, gameDepth, executeDepth, computerColor);
//            case 4:
//                return GobangCoreV4.calculate(tryChess, gameDepth, executeDepth, computerColor);
//            case 5:
//                return GobangCoreV5.calculate(tryChess, 7, 13, computerColor);

                return GobangStrategy.getGobangRobot("ai-v1")
                        .think(tryChess, gameDepth, executeDepth, computerColor);

        }
    }

    private void handleWin(boolean isWin, boolean isBlack) {
        if (isWin) {
            String colorName = isBlack ? "黑棋" : "白棋";
            if ((isBlack && computerColor == WHITE) || (!isBlack && computerColor == BLACK)) {
                int sign = 1;
                int count = 1;
                for (int i = recordPoints.size() - 1; i > 0 && count > 0; i--) {
                    if (recordPoints.get(i).score > GobangConstants.LOSE_SCORE_SIGN) {

                        RecordPoint recordPoint = recordPoints.get(i);

                        Integer oldScore = recordPoint.score;
                        int loseScore = 0;
//                        int loseScore=((sign-count)/count*1.0)*(  recordPoint.score-GobangConstants.LOSE_SCORE);
                        if (count != sign) {
                            loseScore = (int) ((recordPoint.score - GobangConstants.LOSE_SCORE) * (0.01));
                            recordPoint.score = recordPoint.score - loseScore;
                        } else {
                            recordPoint.score = GobangConstants.LOSE_SCORE;
                        }
//                       - loseScore;
                        System.out.println("update lose score. key : " + recordPoint.allChess +
                                "  old score: " + oldScore + "  new score: " + recordPoint.score);

                        count--;
//                        submit(recordPoint);


                    }
                }
            }

            String msg = String.format("恭喜，%s赢了！", colorName);
            JOptionPane.showMessageDialog(this, msg);

            gameOver = true;
            restart();
            pressMark = false;
        }
    }


}