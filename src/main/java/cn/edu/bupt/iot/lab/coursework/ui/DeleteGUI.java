package cn.edu.bupt.iot.lab.coursework.ui;

import cn.edu.bupt.iot.lab.coursework.datastructure.Stock;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Title      : DeleteScheduleGUI.java
 * Description: A class to delete the schedule when user choose
 * Copyright  : Copyright (c) 2905-2017 All rights reserved
 *
 * @author Group 78
 * @version 1.8
 */
public class DeleteGUI {
    private JFrame frame;


    public DeleteGUI(final Stock stock) {

        int num = stock.allTags().size();
        frame = new JFrame("Admin");
        JLabel head1 = new JLabel("Delete Tag", JLabel.CENTER);

        head1.setOpaque(true);
        head1.setBackground(new java.awt.Color(240, 255, 240));
        head1.setFont(new Font("American Typewriter", Font.PLAIN, 20));

        JPanel panel0 = new JPanel(new GridLayout(1, 1));
        JPanel panel1 = new JPanel(new GridLayout(num + 1, 1));
        JPanel panel2 = new JPanel(new GridLayout(num + 1, 1));
        JPanel panel3 = new JPanel(new GridLayout(1, 2));
        JButton back = new JButton("Back");
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new CoreGUI(stock);
                frame.dispose();
            }
        });
        JButton exit = new JButton("Exit");
        exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new CoreGUI(stock);
                frame.dispose();
            }
        });
        back.setFont(new Font("Britannic Bold", Font.PLAIN, 15));
        exit.setFont(new Font("Britannic Bold", Font.PLAIN, 15));
        panel0.add(head1);

        panel1.add(new JLabel("EPC Number", JLabel.CENTER));
        //panel1.add(new JLabel("start time", JLabel.CENTER));
        panel1.setBackground(new java.awt.Color(240, 255, 255));
        panel2.add(new JLabel("Operation", JLabel.CENTER));
        panel2.setBackground(new java.awt.Color(240, 255, 255));
        panel3.add(back);
        panel3.add(exit);
        panel3.setBackground(new java.awt.Color(240, 255, 255));
        JLabel[] labels = new JLabel[num];
        JButton[] buttons = new JButton[num];

        for (int i = 0; i < num; i++) {
            String currentTag = stock.allTags().iterator().next().getId();
            labels[i] = new JLabel(currentTag, JLabel.CENTER);

            panel1.add(labels[i]);
            buttons[i] = new JButton("Delete");
            buttons[i].addActionListener(new ButtonListener(currentTag, stock));
            panel2.add(buttons[i]);


        }
        frame.getContentPane().add(BorderLayout.NORTH, panel0);
        frame.getContentPane().add(BorderLayout.SOUTH, panel3);
        frame.getContentPane().add(BorderLayout.EAST, panel2);
        frame.getContentPane().add(BorderLayout.CENTER, panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setVisible(true);

    }

    public class ButtonListener implements ActionListener {
        private String curTag;
        private Stock stock;

        public ButtonListener(String str, Stock stock) {
            this.curTag = str;
            this.stock = stock;

        }

        public void actionPerformed(ActionEvent e) {
            //remove the tag id from the list
            stock.remove(curTag);

            new NoticeGUI("You have delete the EPC number");
            frame.dispose();
            new DeleteGUI(stock);

        }
    }
   /* public static void main(String [] args)
    {
    	new DeleteScheduleGUI(2);
    }*/
}
