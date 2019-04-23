// http://www.comware.co.kr/solution/solution_020300_2.asp Âü°í

package com.kitri.Book.pay;

import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.Vector;

import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;

import com.kitri.Book.paymethod.Card;
import com.kitri.Book.paymethod.CardPlusCash;
import com.kitri.Book.paymethod.Cash;
import com.kitri.Book.paymethod.Coupon;

import java.awt.Color;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.JFrame;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JPopupMenu;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class payMain extends JFrame { // ³ªÁß¿¡ ÆÐ³Î·Î¸¸ ¹Ù²ãÁÖ¸é µÊ
   
   JTextField searchTF;
   JTable jTable;
   
   // payPanel
   JPanel payPanel = new JPanel();
   public JLabel totalR;
   public JLabel discountR;
   public JLabel payR;
   public JLabel givenR;
   public JLabel changeR;
   
   // listPanel
   private JPanel listPanel;
   private JScrollPane pane;
   private JLabel memberL;
   JButton plus;
   JButton minus;
   JButton cancle;
   JButton cancleAll;
   
   Vector v;
   Vector cols;
   DefaultTableModel model;
   
   JTextField textField;
   
   

   // Pay Method
   public JButton cardB = new JButton("½Å¿ë/Ã¼Å©Ä«µå");
   private JButton cashB = new JButton("Çö±Ý");
   private JButton cardCashB = new JButton("º¹ÇÕ°áÁ¦");
   public JButton couponB;
   
   JPanel cardP = new JPanel();
   
   JPanel couponP = new JPanel();
   Coupon couponPanel = new Coupon();
   
   
   public payMainController pmc = new payMainController(this);
   public CardPlusCash cpc = new CardPlusCash();
   public Card card = new Card();
   public Cash cash = new Cash();
   public Coupon coupon = new Coupon();
   
   

   /**
    * Create the panel.
    */
   public payMain() {

      getContentPane().setBackground(Color.WHITE);

      
      setBackground(Color.WHITE);
      
      cols = getColumn();
      model = new DefaultTableModel(v, cols);
      
      setBounds(0, 36, 1494, 900);
      getContentPane().setLayout(null);
      payPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
      
      payPanel.setBackground(Color.WHITE);
      payPanel.setBounds(32, 499, 622, 302);
      getContentPane().add(payPanel);
      payPanel.setLayout(null);
      
      JLabel totalL = new JLabel("\uCD1D \uAE08\uC561");
      totalL.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
      totalL.setHorizontalAlignment(SwingConstants.CENTER);
      totalL.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 12));
      totalL.setBounds(29, 23, 131, 42);
      payPanel.add(totalL);
      
      JLabel discountL = new JLabel("\uD560\uC778\uAE08\uC561");
      discountL.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
      discountL.setHorizontalAlignment(SwingConstants.CENTER);
      discountL.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 12));
      discountL.setBounds(29, 82, 131, 42);
      payPanel.add(discountL);
      
      JLabel payL = new JLabel("\uBC1B\uC744\uAE08\uC561");
      payL.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
      payL.setHorizontalAlignment(SwingConstants.CENTER);
      payL.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 12));
      payL.setBounds(29, 134, 131, 42);
      payPanel.add(payL);
      
      JLabel givenL = new JLabel("\uACB0\uC81C\uAE08\uC561");
      givenL.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
      givenL.setHorizontalAlignment(SwingConstants.CENTER);
      givenL.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 12));
      givenL.setBounds(29, 186, 131, 42);
      payPanel.add(givenL);
      
      totalR = new JLabel("15000");
      totalR.setHorizontalAlignment(SwingConstants.CENTER);
      totalR.setFont(new Font("ÈÞ¸Õ¿¢½ºÆ÷", Font.BOLD, 18));
      totalR.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
      totalR.setBounds(182, 23, 401, 42);
      payPanel.add(totalR);
      
      discountR = new JLabel("0");
      discountR.setHorizontalAlignment(SwingConstants.CENTER);
      discountR.setFont(new Font("ÈÞ¸Õ¿¢½ºÆ÷", Font.BOLD, 18));
      discountR.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
      discountR.setBounds(182, 82, 401, 42);
      payPanel.add(discountR);
      
      payR = new JLabel("");
      payR.setBackground(new Color(255, 255, 153));
      payR.setHorizontalAlignment(SwingConstants.CENTER);
      payR.setFont(new Font("ÈÞ¸Õ¿¢½ºÆ÷", Font.BOLD, 18));
      payR.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
      payR.setBounds(182, 134, 401, 42);
      payPanel.add(payR);
      
      givenR = new JLabel("");
      givenR.setHorizontalAlignment(SwingConstants.CENTER);
      givenR.setFont(new Font("ÈÞ¸Õ¿¢½ºÆ÷", Font.BOLD, 18));
      givenR.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
      givenR.setBounds(182, 186, 401, 42);
      payPanel.add(givenR);
      
      JLabel changeL = new JLabel("\uAC70\uC2A4\uB984\uB3C8");
      changeL.setHorizontalAlignment(SwingConstants.CENTER);
      changeL.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 12));
      changeL.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
      changeL.setBounds(29, 238, 131, 42);
      payPanel.add(changeL);
      
      changeR = new JLabel("");
      changeR.setHorizontalAlignment(SwingConstants.CENTER);
      changeR.setFont(new Font("ÈÞ¸Õ¿¢½ºÆ÷", Font.BOLD, 18));
      changeR.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
      changeR.setBounds(182, 238, 401, 42);
      payPanel.add(changeR);
      
      listPanel = new JPanel();
      listPanel.setBorder(null);
      listPanel.setBackground(SystemColor.text);
      listPanel.setBounds(12, 57, 1470, 391);
      getContentPane().add(listPanel);
      listPanel.setLayout(null);
      
      memberL = new JLabel("\uD68C\uC6D0 ID : ");
      memberL.setBounds(22, 36, 57, 15);
      listPanel.add(memberL);
      jTable = new JTable(model);
      pane = new JScrollPane(jTable);
      pane.setBounds(22, 61, 1412, 319);
      listPanel.add(pane);
      pane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
      pane.setBorder(new EmptyBorder(0, 0, 0, 0));
      pane.setFont(new Font("±¼¸²", Font.PLAIN, 20));
      
      JPanel panel_1 = new JPanel();
      pane.setColumnHeaderView(panel_1);
      
      plus = new JButton("+");
      plus.setBounds(1054, 10, 47, 41);
      listPanel.add(plus);
      plus.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
      plus.setForeground(new Color(255, 255, 255));
      plus.setBackground(new Color(0, 0, 0));
      plus.setFont(new Font("±¼¸²", Font.BOLD, 15));
      
      minus = new JButton("-");
      minus.setBounds(1113, 10, 47, 41);
      listPanel.add(minus);
      minus.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
      minus.setForeground(new Color(255, 255, 255));
      minus.setBackground(new Color(0, 0, 0));
      minus.setFont(new Font("±¼¸²", Font.BOLD, 15));
      
      cancle = new JButton("\uD574 \uC81C");
      cancle.setBounds(1186, 10, 118, 41);
      listPanel.add(cancle);
      cancle.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
      cancle.setBackground(new Color(0, 0, 0));
      cancle.setForeground(new Color(255, 255, 255));
      cancle.setFont(new Font("±¼¸²", Font.PLAIN, 15));
      
      cancleAll = new JButton("\uC804\uCCB4\uD574\uC81C");
      cancleAll.setBounds(1316, 10, 118, 41);
      listPanel.add(cancleAll);
      cancleAll.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
         }
      });
      cancleAll.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
      cancleAll.setBackground(new Color(0, 0, 0));
      cancleAll.setForeground(new Color(255, 255, 255));
      cancleAll.setFont(new Font("±¼¸²", Font.PLAIN, 15));
      
   
      
      JPanel infoP = new JPanel();
      infoP.setLayout(null);
      infoP.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
      infoP.setBounds(691, 499, 568, 173);
      getContentPane().add(infoP);
      
      JLabel info1 = new JLabel("\u261E \uC0C1\uC138\uACB0\uC81C (\uD604\uAE08+\uCE74\uB4DC) \uB610\uB294 (\uCE74\uB4DC+\uCE74\uB4DC) \uCC98\uB9AC\uC21C\uC11C\n");
      info1.setForeground(new Color(204, 0, 0));
      info1.setFont(new Font("±¼¸²", Font.BOLD, 13));
      info1.setBounds(24, 10, 382, 43);
      infoP.add(info1);
      
      JLabel info2 = new JLabel("1. CL \uBC84\uD2BC\uC744 \uB20C\uB7EC \uBC1B\uC744 \uAE08\uC561\uB780\uC744 \uC9C0\uC6B4\uD6C4 \uC22B\uC790\uD328\uB4DC\uB97C \uC774\uC6A9\uD558\uC5EC \uBC1B\uC744 \uAE08\uC561\uC744 \uC785\uB825\uD569\uB2C8\uB2E4.");
      info2.setBounds(34, 36, 514, 43);
      infoP.add(info2);
      
      JLabel info3 = new JLabel("2. \uC2E0\uC6A9\uCE74\uB4DC \uB610\uB294 \uD604\uAE08 \uBC84\uD2BC\uC744 \uB20C\uB7EC \uC77C\uBD80 \uACB0\uC81C\uB97C \uC644\uB8CC\uD569\uB2C8\uB2E4.");
      info3.setBounds(34, 57, 467, 43);
      infoP.add(info3);
      
      JLabel info4 = new JLabel("3. \uB0A8\uC740 \uC794\uC561\uC774 [\uBC1B\uC744 \uAE08\uC561]\uC5D0 \uB2E4\uC2DC \uB098\uD0C0\uB0A9\uB2C8\uB2E4.");
      info4.setBounds(34, 78, 291, 43);
      infoP.add(info4);
      
      JLabel info5 = new JLabel("4. \uC2E0\uC6A9\uCE74\uB4DC \uB610\uB294 \uD604\uAE08 \uBC84\uD2BC\uC744 \uB20C\uB7EC\uC11C \uB0A8\uC740 \uC794\uC561\uC744 \uCC98\uB9AC\uD569\uB2C8\uB2E4.");
      info5.setBounds(34, 99, 467, 43);
      infoP.add(info5);
      
      JLabel info6 = new JLabel("5. \uACB0\uC81C \uAE08\uC561\uC744 \uBAA8\uB450 \uBC1B\uC744 \uB54C\uAE4C\uC9C0 1\uBC88\uBD80\uD130 4\uBC88\uAE4C\uC9C0 \uBC18\uBCF5\uD569\uB2C8\uB2E4.");
      info6.setBounds(34, 120, 410, 43);
      infoP.add(info6);
      
/////////////////////////////////////////////////////// coupon ±âº»*/
      couponP.setBounds(830, 699, 622, 107);
      couponP.setLayout(null);
      couponP.add(new Coupon());
      couponPanel.setSize(568, 107);
      couponPanel.setLocation(691, 694);
      couponPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
      getContentPane().add(couponPanel);
      
/////////////////////////////////////////////////////// °áÁ¦¹æ¹ý      
      cardB.setFont(new Font("±¼¸²", Font.BOLD, 15));
      cardB.setBounds(1293, 649, 156, 69);
      cardB.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
      cardB.setBackground(new Color(106, 90, 205));
      cardB.setForeground(new Color(255, 255, 255));
      getContentPane().add(cardB);
      
      cashB.setFont(new Font("±¼¸²", Font.BOLD, 15));
      cashB.setBounds(1293, 574, 156, 69);
      cashB.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
      cashB.setBackground(new Color(106, 90, 205));
      cashB.setForeground(new Color(255, 255, 255));
      getContentPane().add(cashB);
      
      cardCashB.setFont(new Font("±¼¸²", Font.BOLD, 15));
      cardCashB.setForeground(new Color(255, 255, 255));
      cardCashB.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
      cardCashB.setBackground(new Color(106, 90, 205));
      cardCashB.setBounds(1293, 499, 156, 69);
      getContentPane().add(cardCashB);
      
      
      couponB = new JButton("\uCFE0  \uD3F0");
      couponB.setFont(new Font("±¼¸²", Font.BOLD, 15));
      couponB.setBounds(1293, 726, 156, 69);
      getContentPane().add(couponB);
      couponB.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
      couponB.setBackground(new Color(255, 127, 80));
      couponB.setForeground(new Color(255, 255, 255));
      
      

      // eventListener
      this.cardB.addActionListener(pmc);
      this.cashB.addActionListener(pmc);
      this.cardCashB.addActionListener(pmc);
      
      
   }
   


   public Vector getColumn(){ // ³ªÁß¿¡ PaymentService·Î ³Ñ±æ²¨
        Vector col = new Vector();
        col.add("No.");
        col.add("»óÇ°¸í");
        col.add("´Ü°¡");
        col.add("¼ö·®");
        col.add("±Ý¾×");
        col.add("ºñ°í");
       
        return col;
    }
   
   public static void main(String[] args) {
      EventQueue.invokeLater(new Runnable() {
         public void run() {
            try {
               payMain frame = new payMain();
               frame.setVisible(true);
            } catch (Exception e) {
               e.printStackTrace();
            }
         }
      });
   }
   
//   private static void addPopup(Component component, final JPopupMenu popup) {
//      component.addMouseListener(new MouseAdapter() {
//         public void mousePressed(MouseEvent e) {
//            if (e.isPopupTrigger()) {
//               showMenu(e);
//            }
//         }
//         public void mouseReleased(MouseEvent e) {
//            if (e.isPopupTrigger()) {
//               showMenu(e);
//            }
//         }
//         private void showMenu(MouseEvent e) {
//            popup.show(e.getComponent(), e.getX(), e.getY());
//         }
//      });
//   }
}