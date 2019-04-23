package com.kitri.Book.pay;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JOptionPane;

public class payMainService implements ActionListener, KeyListener {
   boolean clearOnNextDigit;
   int displayMode;
   int INPUT_MODE = 0;
   String lastOperator;
   double lastNumber;

   payMain p;

   public payMainService(payMain payMain) {
      p = payMain;

   }
   /**
    *  	���հ���
    */
   public void goComplex() {
	      p.cpc.sum.setText(p.payR.getText()); // �����ݾ��� card�� �Ѿ����

	      for (int i = 0; i < p.cpc.buttons.length; i++) {
	         p.cpc.buttons[i].addActionListener(this);
	      }
	      p.cpc.confirm.addActionListener(this);
	      p.cpc.cancle.addActionListener(this);
	}
   
   /**
    * card > Listener + ��Ÿ����
    */
   public void goCard() {
      p.card.sum.setText(p.payR.getText()); // �����ݾ��� card�� �Ѿ����

      for (int i = 0; i < p.card.buttons.length; i++) {
         p.card.buttons[i].addActionListener(this);
      }
      p.card.confirm.addActionListener(this);
      p.card.cancle.addActionListener(this);
   }

   /**
    * Cash > Listener + ��Ÿ����
    */
   public void goCash() {

      for (int i = 0; i < p.cash.buttons.length; i++) {
         p.cash.buttons[i].addActionListener(this);
         p.cash.buttons[i].addKeyListener(this);
      }
      p.cash.confirm.addActionListener(this);
   }

   /**
    * ActionEvent
    */
   @Override
   public void actionPerformed(ActionEvent e) {
      Object ob = e.getSource();

      // card�� cash ��ȣ�е� �Ѹ���
      int len = p.cash.buttons.length;
      for (int i = 0; i < len; i++) {
         if (ob == p.cash.buttons[i]) {

            if (i <= 8) {
               number(i);
               break;
            } else {
               switch (i) {
               case 9: // 100
                  number(100);
                  // p.cash.moneyL.setText("00");
                  break;
               case 10: // 1000
                  number(1000);
                  break;
               case 11: // 10000
                  number(10000);
                  break;
               case 12:
                  clearAll();
               }
            }
         } else if (ob == p.card.buttons[i]) {
            if (i <= 8) {
               number(i);
               break;
            } else {
               switch (i) {
               case 9: // 100
                  number(100);
                  break;
               case 10: // 1000
                  number(1000);
                  break;
               case 11: // ��ĭ�� �����
                  break;
               case 12: // �������
                  clearAll();
               case 13: // Ȯ��
                  break;
               }
            }

         } else if (ob == p.card.cancle) {
            p.card.setVisible(false);
         } else if (ob == p.card.confirm) { // ������ȸ Button
            p.card.setLocation(500, 400);
            p.card.cardNumber.setText("1234-5678-4123");
            p.card.setVisible(true);

            int s = Integer.parseInt(p.card.sum.getText());

            int total = Integer.parseInt(p.totalR.getText());
            int discount = Integer.parseInt(p.discountR.getText());
            int pay = total - discount;
            
            
            if (s < pay) {
               JOptionPane.showMessageDialog(null, "[���ΰź�] �ݾ��� �����մϴ�.");
               p.card.sum.setText(""); // [��ȿ�� CHECK] : �ٽ� ��ĭ�����
            } else {
               p.givenR.setText(p.card.sum.getText());
               GoPayMain();
               JOptionPane.showMessageDialog(null, "[���οϷ�] " + s + "�� �����Ǿ����ϴ�.");
               p.card.setVisible(false);
            }

            break;
         }
      } // end

      if (ob == p.cash.confirm) {
         // �ѱݾ�, ���αݾ�, �����ݾ�, �����ݾ�, �Ž�����
         int total = Integer.parseInt(p.totalR.getText());
         int discount = Integer.parseInt(p.discountR.getText());
         int pay = total - discount;
         int given = Integer.parseInt(p.cash.moneyL.getText()); // ī��, ���� �����ؾ�
         int change = given - pay;

         // total�̶� discount�� main��
         p.payR.setText(String.valueOf(pay));
         p.givenR.setText(p.cash.moneyL.getText());

         // [��ȿ�� check] �ܵ��� 0���� �������
         if (change <= 0) {
            JOptionPane.showMessageDialog(null, "[���ΰź�] �ݾ��� �����մϴ�.");
            p.givenR.setText("");
         } else
            p.changeR.setText(String.valueOf(change));

         p.cash.setVisible(false);
      }
   }

   private void GoPayMain() { // from Card
      int total = Integer.parseInt(p.totalR.getText());
      int discount = Integer.parseInt(p.discountR.getText());
      int pay = total - discount;
      int given = Integer.parseInt(p.card.sum.getText()); // ī��, ���� �����ؾ�
      int change = given - pay;

      p.payR.setText(String.valueOf(pay));
      p.givenR.setText(String.valueOf(given));
      if (change <= 0) {
         p.changeR.setText("������� �ʽ��ϴ�.");
         p.changeR.setForeground(Color.red);
      } else
         p.changeR.setText(String.valueOf(change));

   }

   private void clearAll() {
      p.cash.moneyL.setText("0");
      p.card.sum.setText("0");
      lastOperator = "0";
      lastNumber = 0;
      displayMode = INPUT_MODE;
      clearOnNextDigit = true;
   }

   private void number(int i) {
      String inputString = p.card.sum.getText();

      // null
      if (clearOnNextDigit)
         p.card.sum.setText("");

      if (inputString.indexOf("0") == 0)
         inputString = inputString.substring(1);

      if ((!inputString.equals("0") || i > 0)) {
         p.card.sum.setText(inputString + (i+1));
         p.cash.moneyL.setText(inputString + i);
      }

      displayMode = INPUT_MODE;
      clearOnNextDigit = false;
   }

   /**
    * KeyEvent : Cash�κп��� Ű�������
    */
   @Override
   public void keyPressed(KeyEvent e) {
      int keycode = e.getKeyChar();
      // System.out.println(e.getKeyCode()+ " "+ keycode ); ��ɷ� Ư����ȣ �༮�� �ڵ带 �˾Ƴ½��ϴ�...
      // ^^;;�β�
      switch (keycode) {
      case KeyEvent.VK_0:
         number(0);
         break;
      case KeyEvent.VK_1:
         number(1);
         break;
      case KeyEvent.VK_2:
         number(2);
         break;
      case KeyEvent.VK_3:
         number(3);
         break;
      case KeyEvent.VK_4:
         number(4);
         break;
      case KeyEvent.VK_5:
         number(5);
         break;
      case KeyEvent.VK_6:
         number(6);
         break;
      case KeyEvent.VK_7:
         number(7);
         break;
      case KeyEvent.VK_8:
         number(8);
         break;
      case KeyEvent.VK_9:
         number(9);
         break;
      case 27: // �� �����
         clearAll();
         break;
      }
   }

   /**
    * �غ��ʹ� �����ϼҼ�
    */
   @Override
   public void keyReleased(KeyEvent e) {
      // TODO Auto-generated method stub

   }

   @Override
   public void keyTyped(KeyEvent e) {
      // TODO Auto-generated method stub

   }


}