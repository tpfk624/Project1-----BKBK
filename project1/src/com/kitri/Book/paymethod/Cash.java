package com.kitri.Book.paymethod;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;


public class Cash extends JFrame {
   
   public JLabel moneyL;
   public JButton buttons[] = new JButton[13];
   public JButton confirm = new JButton("확 인");
   
   
   
   public Cash() {
      super("현금결제");
      setBackground(Color.WHITE);
      setBounds(898, 85, 576, 240);
      getContentPane().setLayout(null);
      
      for (int i = 0; i <= 8; i++) { // 1~9
         buttons[i] = new JButton(String.valueOf(i));
         buttons[i].setFont(new Font("굴림", Font.BOLD, 14));
         buttons[i].setBackground(SystemColor.inactiveCaptionBorder);
      }
      
      buttons[9] = new JButton("백");
      buttons[10] = new JButton("천");
      buttons[11] = new JButton("만");
      buttons[12] = new JButton("C");
//      buttons[13] = new JButton("확  인");
      
      
      buttons[0].setBounds(24, 93, 47, 42);
      buttons[1].setBounds(83, 93, 47, 42);
      buttons[2].setBounds(142, 93, 47, 42);
      buttons[3].setBounds(201, 93, 47, 42);
      buttons[4].setBounds(260, 93, 47, 42);
      buttons[5].setBounds(321, 93, 47, 42);
      buttons[6].setBounds(380, 93, 47, 42);
      buttons[7].setBounds(439, 93, 47, 42);
      buttons[8].setBounds(498, 93, 47, 42); // nun : 9
      buttons[9].setBounds(321, 145, 47, 42);
      buttons[10].setBounds(260, 145, 47, 42);
      buttons[11].setBounds(201, 145, 47, 42);
      buttons[12].setBounds(380, 145, 47, 42);
      confirm.setBounds(439, 145, 106, 42);
      
      for (int i = 9; i <= 12; i++) {
         buttons[i].setBackground(SystemColor.inactiveCaptionBorder);
      }
      
      buttons[9].setFont(new Font("굴림", Font.BOLD, 12));
      buttons[10].setFont(new Font("굴림", Font.BOLD, 12));
      buttons[11].setFont(new Font("굴림", Font.BOLD, 12));
      buttons[12].setFont(new Font("굴림", Font.BOLD, 14));
      confirm.setFont(new Font("굴림", Font.BOLD, 14));
      
      for (int i = 0; i < buttons.length; i++) {
         getContentPane().add(buttons[i]);
         
      }
      getContentPane().add(confirm);
      
      JPanel panel = new JPanel();
      panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
      panel.setBounds(22, 10, 520, 69);
      getContentPane().add(panel);
      panel.setLayout(null);
      
      JLabel requestL = new JLabel("[  \uD604\uAE08 \uACB0\uC81C  ]    \uBC1B\uC740 \uAE08\uC561\uC744 \uC785\uB825\uD558\uC2DC\uC624.");
      requestL.setBounds(12, 10, 508, 15);
      panel.add(requestL);
      requestL.setFont(new Font("HY그래픽M", Font.BOLD, 15));
      
      JLabel wonL = new JLabel(" \uC6D0");
      wonL.setBounds(474, 35, 22, 23);
      panel.add(wonL);
      wonL.setHorizontalAlignment(SwingConstants.RIGHT);
      wonL.setFont(new Font("HY그래픽M", Font.BOLD, 15));
      
      moneyL = new JLabel("");
      moneyL.setHorizontalAlignment(SwingConstants.RIGHT);
      moneyL.setFont(new Font("HY그래픽M", Font.BOLD, 15));
      moneyL.setBounds(268, 39, 205, 15);
      panel.add(moneyL);
      
   }
}