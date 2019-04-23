// https://m.blog.naver.com/PostView.nhn?blogId=ken6ybn&logNo=100165772630&proxyReferer=https%3A%2F%2Fwww.google.com%2F ����

package com.kitri.Book.rent;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import com.kitri.Main.frame.MainFrame;


public class RentalMain extends JFrame {

   public JTable table;
   JScrollPane pane;
   public DefaultTableModel model;

   public JTextField searchTF;
   private JLabel noticeL = new JLabel(">> NOTICE : �ݳ��ؾ��ϴ� ��������� �����ٶ��ϴ�.");
   
   public JButton search = new JButton("��  ��");
   public JButton cancle  = new JButton("��  ��");;
   public JButton basket = new JButton("��  ��");;
   
   public RentalController rc = new RentalController(this);
   public JList bookL;
   public JButton change;

   public BookDAO dao = new BookDAO();
   public Vector<BookDTO> list = new Vector<BookDTO>();

   Vector<String> col = new Vector<String>(3);
   
   
   public JTable book;
   public JButton move = new JButton("");
   
//---------------------------------------------------------------------------------------------------------------------------[h] main frame ������ �ޱ�
   public MainFrame mf;
   
   public RentalMain(MainFrame mainFrame) {
	   
	   this.mf = mainFrame;
//----------------------------------------------------------------------------------------------------------------------------[h]	   
      getContentPane().setBackground(new Color(255, 255, 255));
      setBackground(SystemColor.text);
      setBounds(0, 0, 1195, 529);
      getContentPane().setLayout(null);
      

      //////////////////////////////////////////////////////////////////////////////// DB ����
      
      col.add("å �̸�");
      col.add("�۰�");
      col.add("���ǻ�");
      col.add("���� ���ɿ���");

      model = new DefaultTableModel(col, 0); // �߰��� Į�������� �� �����ϰ� ���ڷ� �־���

      list = dao.BookList();

      int size = list.size();

      for (int i = 0; i < size; i++) {
    	  Vector<String> rows = new Vector<String>();
         rows.addElement(list.get(i).getBookName());
         rows.addElement(list.get(i).getAuthor());
         rows.addElement(list.get(i).getPublisher());
//         rows.addElement(list.get(i).getRentState());
         
         model.addRow(rows);
      }

      book = new JTable(model);
      book.setGridColor(Color.GRAY);
      book.setBackground(Color.WHITE);
      book.setForeground(Color.BLACK);
      
      JScrollPane pane = new JScrollPane(book);
      getContentPane().add(pane);
      pane.setBounds(35, 102, 709, 345);
      pane.setBorder(new LineBorder(new Color(130, 135, 144)));
      pane.setViewportView(book);
      
      pane.setBackground(Color.WHITE);
      pane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
      pane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
      pane.setFont(new Font("����", Font.PLAIN, 20));
      getContentPane().add(pane);
      
      ////////////////////////////////////////////////////////////////////////////////

      setBounds(0, 36, 1211, 561);
      getContentPane().setLayout(null);

      searchTF = new JTextField();
      searchTF.setBounds(35, 45, 991, 46);
      getContentPane().add(searchTF);
      searchTF.setFont(new Font("����", Font.PLAIN, 17));
      searchTF.setColumns(10);

      search.setFont(new Font("Dialog", Font.BOLD, 15));
      search.setForeground(UIManager.getColor("Button.foreground"));
      search.setBackground(UIManager.getColor("Button.background"));
      search.setBounds(1038, 46, 123, 46);
      getContentPane().add(search);

      bookL = new JList();
      bookL.setBounds(827, 100, 338, 345);
      getContentPane().add(bookL);
      bookL.setFont(new Font("����", Font.PLAIN, 18));
      bookL.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));

      cancle.setFont(new Font("Dialog", Font.BOLD, 15));
      cancle.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
      cancle.setForeground(UIManager.getColor("Button.foreground"));
      cancle.setBackground(UIManager.getColor("Button.background"));
      cancle.setBounds(827, 455, 152, 46);
      getContentPane().add(cancle);

      basket.setFont(new Font("Dialog", Font.BOLD, 15));
      basket.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
      basket.setBackground(UIManager.getColor("Button.background"));
      basket.setForeground(UIManager.getColor("Button.foreground"));
      basket.setBounds(1020, 455, 141, 46);
      getContentPane().add(basket);
      noticeL.setForeground(new Color(255, 0, 0));

      noticeL.setFont(new Font("����", Font.BOLD | Font.ITALIC, 14));
      noticeL.setBounds(35, 470, 434, 15);
      getContentPane().add(noticeL);
      
      change = new JButton("\uAD50\uCCB4\uC694\uB9DD");
      change.setForeground(Color.BLACK);
      change.setFont(new Font("Dialog", Font.BOLD, 15));
      change.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
      change.setBackground(SystemColor.menu);
      change.setBounds(595, 457, 152, 46);
      getContentPane().add(change);
      move.setBackground(Color.BLACK);
      move.setBounds(766, 263, 49, 46);
      
      getContentPane().add(move);
      
      // eventListener
      search.addActionListener(rc);
      change.addActionListener(rc);
      move.addActionListener(rc);
      
   }






//-------------------------------------------------------------------------------------------------------------------------------------------------[h] main �ּ�ó��  
//   /**
//    * Launch the application.
//    */
//   public static void main(String[] args) {
//      EventQueue.invokeLater(new Runnable() {
//         public void run() {
//            try {
//               RentalMain R = new RentalMain();
//               R.setVisible(true);
//            } catch (Exception e) {
//               e.printStackTrace();
//            }
//         }
//      });
//   }
}