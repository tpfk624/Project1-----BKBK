package com.kitri.Book.pay;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class payMainController implements ActionListener{
   
   payMain p;
   payMainService pms;
   
   public payMainController(payMain payMain) {
      this.p = payMain;
      pms = new payMainService(p);
   }

   @Override
   public void actionPerformed(ActionEvent e) {
      
      String str = e.getActionCommand(); // �� Object�� �ȸ�����?
      Object ob = e.getSource();
      
      if (str.equals("�ſ�/üũī��")) {
         pms.goCard();
         p.card.setLocation(500, 400);
         p.card.setVisible(true);
      } else if (str.equals("����")) {
         pms.goCash();
         p.cash.setLocation(450, 300);
         p.cash.setVisible(true);
      } else if (str.equals("���հ���")) {
    	  pms.goComplex();
          p.cpc.setLocation(500, 400);
          p.cpc.setVisible(true);
          
      } 
      
      
   }
}