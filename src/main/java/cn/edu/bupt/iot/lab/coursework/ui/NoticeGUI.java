package cn.edu.bupt.iot.lab.coursework.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Title      : ErrorGUI.java
 * Description: A class to notice user after execute some operations.
 * Copyright  : Copyright (c) 2905-2017 All rights reserved
 *
 * @author Group 78
 * @version 1.8
 */
@SuppressWarnings("serial")
public class NoticeGUI extends JPanel {
    JFrame frame = new JFrame();
    JPanel panel1 = new JPanel();
    JButton buton = new JButton("OK");

    public NoticeGUI(String str) {
        JLabel b = new JLabel(str, JLabel.CENTER);
        b.setFont(new Font("Arial", Font.PLAIN, 15));
        panel1.add(b);
        frame.getContentPane().add(BorderLayout.CENTER, panel1);
        frame.getContentPane().add(BorderLayout.SOUTH, buton);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Notification");
        frame.setSize(300, 150);
        frame.setVisible(true);
        frame.setAlwaysOnTop(true);

        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int width = screenSize.width, height = screenSize.height, x = (width - WIDTH) / 2, y = (height - HEIGHT) / 2;
        frame.setLocation(x, y);
        buton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //new Purchase();
                frame.dispose();


            }
        });
    }
}
