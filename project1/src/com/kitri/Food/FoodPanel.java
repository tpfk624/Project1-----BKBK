package com.kitri.Food;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.SystemColor;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import com.kitri.Main.frame.MainFrame;

public class FoodPanel extends JPanel {

//	   public JPanel mainPanel = new JPanel();//main panel //--------------------------------------------------------------------------[h] JPanel�� ��ӹް� main panel�� this�� ��ü��

	   
	   //////////////////////////////////////////////
	   //ī�װ� ��ư��
	   public JButton drinkBtn = new JButton("\uC74C\uB8CC");
	   public JButton snackBtn = new JButton("\uACFC\uC790");
	   public JButton noodleBtn = new JButton("\uB77C\uBA74");
	   public JButton instfoodBtn = new JButton("\uC74C\uC2DD");
	   
	   //�޴��� panel
	   JPanel menuPanel;
	   //�޴��� View
	   public DrinkView dv = new DrinkView();
	   public SnackView sv = new SnackView();
	   public NoodleView nv = new NoodleView();
	   public InstFoodView ifv = new InstFoodView();
	   
	   CardLayout card = new CardLayout();
	   //////////////////////////////////
	   //�ֹ����,�Ϸ��ư
	   JButton cancelBtn = new JButton("\uCDE8\uC18C");
	   JButton completeBtn = new JButton("\uC644\uB8CC");
	   ////////////////////////////////////
	   //�ֹ�list
	   public JPanel orderPanel = new JPanel();
	   public JScrollPane tableP;
	   public JTable listT;
	   public DefaultTableModel tM;
	   ///////////////////////////////////////////
	   //�ѱݾ� textfield
	   JTextField textField;
	   public JTextField sumprice;
	   //�ֹ�������ư
	   JButton plusBtn = new JButton("+");
	   JButton minusBtn = new JButton("-");
	   JButton oneCancel = new JButton("X");
	   JButton totalCancel = new JButton("\uC804\uCCB4 \uCDE8\uC18C");
	
	   ///////////////////////////////////////
	   //Event Controller
	   public FoodController foodController;
//--------------------------------------------------------------------------------------------------------------------------------------------[h] MainFrame�� �޾ƿͼ� control >> Service���� �Ѱ���	
	   public MainFrame mf;
	/**
	 * Create the panel.
	 */
	public FoodPanel(MainFrame mainFrame) {
		this.mf = mainFrame;
//--------------------------------------------------------------------------------------------------------------------------------------------[h]
		
	      /////////////////////////////////////////////////////////////////
	      //main panel
//	      mainPanel.setBackground(Color.WHITE);
//	      mainPanel.setBounds(0, 36, 1494, 835); // �� ũ���� �г� �����ؼ� cardLayout���� ����~~
//	      mainPanel.setLayout(null);
		
//--------------------------------------------------------------------------------------------------------------------------------------------[h] JPanel�� ��ӹް� main panel�� this�� ��ü��
	      setBackground(Color.WHITE);
	      setBounds(0, 36, 1494, 835); // �� ũ���� �г� �����ؼ� cardLayout���� ����~~
	      setLayout(null);
//--------------------------------------------------------------------------------------------------------------------------------------------[h]	      
	      
	      drinkBtn.setForeground(Color.WHITE);
	      drinkBtn.setBackground(Color.DARK_GRAY);
	      //////////////////////////////////////////////
	      //ī�װ� ��ư��
	      drinkBtn.setBounds(1374, 71, 97, 50);
//	      mainPanel.add(drinkBtn);
	      
//--------------------------------------------------------------------------------------------------------------------------------------------[h] JPanel�� ��ӹް� main panel�� this�� ��ü��	      
	      add(drinkBtn);
//--------------------------------------------------------------------------------------------------------------------------------------------[h]
	      
	      drinkBtn.setFont(new Font("���� ���", Font.BOLD, 20));
	      snackBtn.setForeground(Color.WHITE);
	      snackBtn.setBackground(Color.DARK_GRAY);
	      snackBtn.setBounds(1374, 131, 97, 50);
//	      mainPanel.add(snackBtn);
	      
//--------------------------------------------------------------------------------------------------------------------------------------------[h] JPanel�� ��ӹް� main panel�� this�� ��ü��
	      add(snackBtn);
//--------------------------------------------------------------------------------------------------------------------------------------------[h]
	      
	      snackBtn.setFont(new Font("���� ���", Font.BOLD, 20));
	      noodleBtn.setForeground(Color.WHITE);
	      noodleBtn.setBackground(Color.DARK_GRAY);
	      noodleBtn.setBounds(1374, 191, 97, 50);
//	      mainPanel.add(noodleBtn);
	      
//--------------------------------------------------------------------------------------------------------------------------------------------[h] JPanel�� ��ӹް� main panel�� this�� ��ü��	      
	      add(noodleBtn);
//--------------------------------------------------------------------------------------------------------------------------------------------[h]
	      
	      noodleBtn.setFont(new Font("���� ���", Font.BOLD, 20));
	      instfoodBtn.setForeground(Color.WHITE);
	      instfoodBtn.setBackground(Color.DARK_GRAY);
	      instfoodBtn.setBounds(1374, 251, 97, 50);
//	      mainPanel.add(instfoodBtn);
	      
//--------------------------------------------------------------------------------------------------------------------------------------------[h] JPanel�� ��ӹް� main panel�� this�� ��ü��	      
	      add(instfoodBtn);
//--------------------------------------------------------------------------------------------------------------------------------------------[h]	
	      
	      instfoodBtn.setFont(new Font("���� ���", Font.BOLD, 20));
	      ///////////////////////////////////////////////
	      //menu�� ��ư�� ���� panel : menuPanel
	      menuPanel = new JPanel();
	      menuPanel.setBounds(717, 67, 656, 583);
	            
	      menuPanel.setLayout(card);//menuPanel�� cardLayout �����
	      
	      dv.setLocation(715, 67);//�� ī�װ� panel
	      menuPanel.add("drinkview",dv);
	      sv.setLocation(715, 67);
	      menuPanel.add("snackview",sv);
	      nv.setLocation(715, 67);
	      menuPanel.add("noodleview",nv);
	      ifv.setLocation(715, 67);
	      menuPanel.add("instfoodview",ifv);
	      
	      card.show(menuPanel, "drinkview");//ù�������� ���̴� panel : drink
//	      mainPanel.add(menuPanel);
	      
//--------------------------------------------------------------------------------------------------------------------------------------------[h] JPanel�� ��ӹް� main panel�� this�� ��ü��	      
	      add(menuPanel);
//--------------------------------------------------------------------------------------------------------------------------------------------[h]
	      
	      //////////////////////////////////////////////
	      //�ֹ� ���,�Ϸ� ��ư
	      cancelBtn.setForeground(Color.WHITE);
	      cancelBtn.setBackground(new Color(52, 152, 219));
	      cancelBtn.setBounds(883, 708, 235, 47);
	      cancelBtn.setFont(new Font("���� ���", Font.BOLD, 30));
//	      mainPanel.add(cancelBtn);
	      
//--------------------------------------------------------------------------------------------------------------------------------------------[h] JPanel�� ��ӹް� main panel�� this�� ��ü��	      
	      add(cancelBtn);
//--------------------------------------------------------------------------------------------------------------------------------------------[h]

	      completeBtn.setForeground(Color.WHITE);
	      completeBtn.setBackground(new Color(52, 152, 219));
	      completeBtn.setBounds(1138, 708, 235, 47);
	      completeBtn.setFont(new Font("���� ���", Font.BOLD, 30));
//	      mainPanel.add(completeBtn);
	      
//--------------------------------------------------------------------------------------------------------------------------------------------[h] JPanel�� ��ӹް� main panel�� this�� ��ü��	      
	      add(completeBtn);
//--------------------------------------------------------------------------------------------------------------------------------------------[h]
	      
	      //////////////////////////////////////
	      //�ֹ�list
	      orderPanel.setBounds(48, 67, 630, 583);
	      orderPanel.setLayout(new BorderLayout(0, 0));
	      //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	      //Jtable
	      //�÷� ��
	      Vector<String> column1 = new Vector<String>();
	      column1.addElement("No");

	      column1.addElement("ī�װ�");
	      column1.addElement("�޴�");
	      column1.addElement("����");
	      column1.addElement("����");
	      
	      //�߰��� �÷������� �� �����ϰ� ���� ����
	      tM = new DefaultTableModel(column1, 0);
//	      boolean tt = tM.isCellEditable(0, column1);
	      
	      
	      //JTable�� ����.
	      listT = new JTable(tM);
	      listT.setFillsViewportHeight(true);
	      
	      //table ����
	      tableP = new JScrollPane(listT);
	      orderPanel.add(tableP);
	      
	      
//	      mainPanel.add(orderPanel);
	      
//--------------------------------------------------------------------------------------------------------------------------------------------[h] JPanel�� ��ӹް� main panel�� this�� ��ü��	      
	      add(orderPanel);
//--------------------------------------------------------------------------------------------------------------------------------------------[h]
	      ///!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	      
	      textField = new JTextField();
	      textField.setBackground(new Color(52, 152, 219));
	      textField.setForeground(Color.WHITE);
	      textField.setHorizontalAlignment(SwingConstants.CENTER);
	      textField.setText("\uCD1D\uAE08\uC561");
	      textField.setBounds(48, 681, 225, 37);
//	      mainPanel.add(textField);
	      
//--------------------------------------------------------------------------------------------------------------------------------------------[h] JPanel�� ��ӹް� main panel�� this�� ��ü��	      
	      add(textField);
//--------------------------------------------------------------------------------------------------------------------------------------------[h]

	      textField.setColumns(10);
	      textField.setFont(new Font("���� ���", Font.BOLD, 25));
	      //�ѱݾ�
	      sumprice = new JTextField();
	      sumprice.setText("0");
	      sumprice.setHorizontalAlignment(SwingConstants.CENTER);
	      sumprice.setBounds(48, 718, 225, 37);
//	      mainPanel.add(sumprice);
	      
//--------------------------------------------------------------------------------------------------------------------------------------------[h] JPanel�� ��ӹް� main panel�� this�� ��ü��	      
	      add(sumprice);
//--------------------------------------------------------------------------------------------------------------------------------------------[h]
	      
	      sumprice.setColumns(10);
	      sumprice.setFont(new Font("���� ���", Font.BOLD, 30));
	      plusBtn.setForeground(Color.WHITE);
	      plusBtn.setBackground(Color.DARK_GRAY);
	      
	      plusBtn.setBounds(285, 680, 75, 75);
//	      mainPanel.add(plusBtn);
	      
//--------------------------------------------------------------------------------------------------------------------------------------------[h] JPanel�� ��ӹް� main panel�� this�� ��ü��	      
	      add(plusBtn);
//--------------------------------------------------------------------------------------------------------------------------------------------[h]
	      
	      plusBtn.setFont(new Font("���� ���", Font.BOLD, 40));
	      minusBtn.setForeground(Color.WHITE);
	      minusBtn.setBackground(Color.DARK_GRAY);
	      minusBtn.setBounds(372, 680, 75, 75);
//	      mainPanel.add(minusBtn);
	      
//--------------------------------------------------------------------------------------------------------------------------------------------[h] JPanel�� ��ӹް� main panel�� this�� ��ü��	      
	      add(minusBtn);
//--------------------------------------------------------------------------------------------------------------------------------------------[h]
	      
	      minusBtn.setFont(new Font("���� ���", Font.BOLD, 40));
	      oneCancel.setForeground(Color.WHITE);
	      oneCancel.setBackground(Color.DARK_GRAY);
	      oneCancel.setBounds(459, 680, 75, 75);
//	      mainPanel.add(oneCancel);
	      
//--------------------------------------------------------------------------------------------------------------------------------------------[h] JPanel�� ��ӹް� main panel�� this�� ��ü��	      
	      add(oneCancel);
//--------------------------------------------------------------------------------------------------------------------------------------------[h]
	      
	      oneCancel.setFont(new Font("���� ���", Font.BOLD, 30));
	      totalCancel.setForeground(Color.WHITE);
	      totalCancel.setBackground(Color.DARK_GRAY);
	      totalCancel.setBounds(546, 680, 132, 75);
//	      mainPanel.add(totalCancel);
	      
//--------------------------------------------------------------------------------------------------------------------------------------------[h] JPanel�� ��ӹް� main panel�� this�� ��ü��	      
	      add(totalCancel);
//--------------------------------------------------------------------------------------------------------------------------------------------[h]
	      
	      totalCancel.setFont(new Font("���� ���", Font.BOLD, 20));
	      //////////////////////////////////////
	      
	     
	      /////////////////////////////
	      //Event Listener
	      foodController = new FoodController(this);
	      
	      drinkBtn.addActionListener(foodController);
	      snackBtn.addActionListener(foodController);
	      noodleBtn.addActionListener(foodController);
	      instfoodBtn.addActionListener(foodController);
	      
	      plusBtn.addActionListener(foodController);
	      minusBtn.addActionListener(foodController);
	      oneCancel.addActionListener(foodController);
	      totalCancel.addActionListener(foodController);
	      
	      cancelBtn.addActionListener(foodController);
	      completeBtn.addActionListener(foodController);
	      
	      //DrinkView 
	      int drinklen = dv.drinkbtn.length;
	      for(int i=0;i<drinklen;i++) {
	    	   dv.drinkbtn[i].addActionListener(foodController);
	      }
	      //SnackView
	      int snacklen = sv.snackbtn.length;
	      for(int i=0;i<snacklen;i++) {
	    	   sv.snackbtn[i].addActionListener(foodController);
	      }
	      //NoodleView
	      int noodlelen = nv.noodlebtn.length;
	      for(int i=0;i<noodlelen;i++) {
	    	   nv.noodlebtn[i].addActionListener(foodController);
	      }
	      //InstFoodView
	      int instlen = ifv.instfoodbtn.length;
	      for(int i=0;i<instlen;i++) {
	    	  ifv.instfoodbtn[i].addActionListener(foodController);
	      }
	      
	}

}
