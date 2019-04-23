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
      
      String str = e.getActionCommand(); // 왜 Object는 안먹힐까?
      Object ob = e.getSource();
      
      if (str.equals("신용/체크카드")) {
         pms.goCard();
         p.card.setLocation(500, 400);
         p.card.setVisible(true);
      } else if (str.equals("현금")) {
         pms.goCash();
         p.cash.setLocation(450, 300);
         p.cash.setVisible(true);
      } else if (str.equals("복합결제")) {
    	  pms.goComplex();
          p.cpc.setLocation(500, 400);
          p.cpc.setVisible(true);
          
      } 
      
      
   }
}