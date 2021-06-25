package com.zylear.gobangai.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class GobangJFrame extends JFrame {


    public GobangPanel start = new GobangPanel();


    public GobangJFrame(String str) {
        super(str);
        Container con = new Container();

        this.setContentPane(con);

        con.setLayout(new BorderLayout());

        JPanel cpanel = new JPanel();
        cpanel.setLayout(new FlowLayout());
        con.add(cpanel, BorderLayout.SOUTH);

        con.add(start);
        setBounds(700, 150, 555, 680);
        this.setResizable(false);

        final JButton btn1 = new JButton("重新开始");
        final JButton btn2 = new JButton("悔        棋");
        final JButton btn3 = new JButton("退出游戏");

        cpanel.add(btn1);
        cpanel.add(btn2);
        cpanel.add(btn3);
        //pack();

        JMenu menu = new JMenu("游戏");
        JMenu menu2 = new JMenu("设置");
        final JMenuItem item1 = new JMenuItem("重新开始");
        final JMenuItem item2 = new JMenuItem("悔        棋");
        final JMenuItem item3 = new JMenuItem("退出游戏");
        final JMenuItem item4 = new JMenuItem("设置背景");
        final JMenuItem item5 = new JMenuItem("关闭背景");
        final JMenuItem item10 = new JMenuItem("电脑先走");
        final JMenuItem item7 = new JMenuItem("v1");
        final JMenuItem item8 = new JMenuItem("v2");
        final JMenuItem item9 = new JMenuItem("v3");
        final JMenuItem item14 = new JMenuItem("v4");
        final JMenuItem item11 = new JMenuItem("3层");
        final JMenuItem item12 = new JMenuItem("5层");
        final JMenuItem item13 = new JMenuItem("7层");
        final JMenuItem item15 = new JMenuItem("9层");
        JMenuItem item6 = new JMenuItem("设        置");


        menu.add(item1);
        menu.add(item2);
        menu.add(item3);
        //menu.add(item6);


        //
//        menu2.add(item4);
//        menu2.add(item5);
//        menu2.add(item7);
//        menu2.add(item8);
//        menu2.add(item9);
//        menu2.add(item10);
        menu2.add(item11);
        menu2.add(item12);
        menu2.add(item13);
        menu2.add(item15);
//        menu2.add(item14);
        //

        JMenuBar menubar = new JMenuBar();

        menubar.add(menu);
        menubar.add(menu2);

        setJMenuBar(menubar);
        menubar.setVisible(true);


        class MyEvent implements ActionListener {
            public void actionPerformed(ActionEvent e) {
                Object obj = e.getSource();//获得事件源
                if (obj == btn1 || obj == item1) {
                    //重新开始
                    //JFiveFrame.this内部类引用外部类
                    //System.out.println("重新开始");
                    start.restart();
                    start.repaint();
                } else if (obj == btn2 || obj == item2) {
                    // System.out.println("悔棋...");
//                    JOptionPane.showMessageDialog(start, "不想给你悔棋");
                    start.goBack();


                } else if (obj == btn3 || obj == item3)
                    System.exit(0);
                else if (obj == item4) {
                    start.paint = true;
                    start.repaint();
                } else if (obj == item5) {
                    start.paint = false;
                    start.repaint();
                } else if (obj == item7) {
                    start.changeStrategy(1);
                } else if (obj == item8) {
                    start.changeStrategy(2);
                } else if (obj == item9) {
                    start.changeStrategy(3);
                } else if (obj == item14) {
                    start.changeStrategy(4);
                } else if (obj == item10) {
                    start.changeComputerFirst();
                } else if (obj == item11) {
                    start.changeGameDepth(3);
                } else if (obj == item12) {
                    start.changeGameDepth(5);
                } else if (obj == item13) {
                    start.changeGameDepth(7);
                } else if (obj == item15) {
                    start.changeGameDepth(9);
                }

            }

        }
        MyEvent my = new MyEvent();

        item1.addActionListener(my);
        item2.addActionListener(my);
        item3.addActionListener(my);
        item4.addActionListener(my);
        item5.addActionListener(my);
        item7.addActionListener(my);
        item8.addActionListener(my);
        item9.addActionListener(my);
        item10.addActionListener(my);
        item11.addActionListener(my);
        item12.addActionListener(my);
        item13.addActionListener(my);
        item14.addActionListener(my);


        btn1.addActionListener(my);
        btn2.addActionListener(my);
        btn3.addActionListener(my);


    }
    // java.net.URL file1 = getClass().getResource("d:java\\woke\\音乐\\Ring03.wav");


    public static void main(String[] args) {
        // TODO Auto-generated method stub

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    GobangJFrame frm = new GobangJFrame("五子棋游戏");
                    frm.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


    }


}

