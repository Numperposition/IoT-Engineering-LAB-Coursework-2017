package cn.edu.bupt.iot.lab.coursework.ui;

import cn.edu.bupt.iot.lab.coursework.datastructure.Stock;
import cn.edu.bupt.iot.lab.coursework.datastructure.Tag;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * A class to show the main platform of the software, both for administrators and customers
 *
 * @author Group 78
 * @version 1.8
 */

public class CoreGUI {
    public static void main(String args[]) {
        Stock stock = new Stock("tags");
        new CoreGUI(stock);
    }

    public CoreGUI(final Stock stock) {
        final JFrame frame = new JFrame("welcome");
        JLabel head = new JLabel("Intelligent Gateway Control System", JLabel.CENTER);
        head.setOpaque(true);
        head.setBackground(new java.awt.Color(240, 255, 255));
        head.setFont(new Font("Apple Chancery", Font.PLAIN, 30));

        JPanel centerPanel = new JPanel(new GridLayout(1, 3, 20, 20));
        centerPanel.setBackground(new java.awt.Color(240, 255, 255));
        JPanel bottomPanel = new JPanel(new GridLayout(1, 2));
        bottomPanel.setBackground(new java.awt.Color(240, 255, 255));
        JButton b1 = new JButton("Register tag");
        b1.setFont(new Font("Britannic Bold", Font.PLAIN, 15));
        JButton b2 = new JButton("Delete tag");
        b2.setFont(new Font("Britannic Bold", Font.PLAIN, 15));
        JButton b3 = new JButton("Check tag");
        b3.setFont(new Font("Britannic Bold", Font.PLAIN, 15));
        b1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //execute the registration function here.
                //add the logic function here.

                //if added successfully

                Tag tag = new Tag("2");
                if (stock.has(tag.getId())) {
                    new NoticeGUI("Fail to add!");
                } else {
                    stock.add(tag);
                    stock.scan();
                    new NoticeGUI("Successfully added: EPC code:" + tag.getId());
                }
                //frame.dispose();
            }
        });
        b2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //execute the deletion function here.
                //the following statements below are used for testing
                //the list, used to store EPC number is a global static object

                new DeleteGUI(stock);
                frame.setVisible(false);
            }
        });
        b3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //execute the comparison logic function here.

                //if compared successfully
                Tag tag = new Tag("2");
                if (stock.has(tag.getId()))
                    new NoticeGUI("welcome EPC:" + tag.getId());
                else
                    new NoticeGUI("Couldn't find EPC:" + tag.getId());
                //frame.dispose();
            }
        });
        JButton back = new JButton("Back");

        JButton exit = new JButton("Exit");
        exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(1);
            }
        });
        back.setFont(new Font("Britannic Bold", Font.PLAIN, 15));


        exit.setFont(new Font("Britannic Bold", Font.PLAIN, 15));
        //back.setOpaque(true);
        b1.setFont(new Font("serif", Font.BOLD, 20));
        b2.setFont(new Font("serif", Font.BOLD, 20));
        b3.setFont(new Font("serif", Font.BOLD, 20));
        centerPanel.add(b1);
        centerPanel.add(b2);
        centerPanel.add(b3);
        bottomPanel.add(back);
        bottomPanel.add(exit);
        frame.getContentPane().add(BorderLayout.NORTH, head);
        frame.getContentPane().add(BorderLayout.CENTER, centerPanel);
        frame.getContentPane().add(BorderLayout.SOUTH, bottomPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setVisible(true);

    }
/*	public static void main(String [] args) {
		
		CoreGUI start = new CoreGUI();
		
	}*/
}

