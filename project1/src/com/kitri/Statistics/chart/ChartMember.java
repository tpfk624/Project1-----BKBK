package com.kitri.Statistics.chart;

import java.awt.Font;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;


import com.kitri.Statistics.StatMemberDto;

import java.awt.Color;
import java.awt.Component;
import java.awt.Choice;
import java.awt.Rectangle;
import java.util.Vector;
import java.awt.ComponentOrientation;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ChartMember extends JPanel {

//	private static final Component BarChartBean = null;


	public ChartMember() {
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setLayout(null);
		setBounds(203, 49, 1240, 713);

		JLabel lblNewLabel = new JLabel("\uC5F0\uB839\uBCC4 \uD68C\uC6D0 \uBD84\uD3EC\uB3C4");
		lblNewLabel.setFont(new Font("����", Font.BOLD, 30));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(473, 58, 319, 37);
		add(lblNewLabel);

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(50, 214, 560, 450);// 380
		add(panel);
		panel.add(createChart());
		////////////////////////////////////////////////////////////////
		

		// �÷����� ������ش�.
		Vector<String> column1 = new Vector<String>();
		column1.addElement("ȸ��ID");
		column1.addElement("�̸�");
		column1.addElement("����");
		column1.addElement("���ɴ�");
		

		// �߰��� �÷������� �� �����ϰ� ���ڷ� �־���
		DefaultTableModel model = new DefaultTableModel(column1, 0);

		//******************dao �Լ� ��� ����****************************
		StatDao dao = new StatDao();
		
		 Vector<StatMemberDto> list = new Vector<StatMemberDto>();
		 
		 list = dao.selectMemberRanking();
		 
		 int size = list.size();
		 
		 for(int i = 0; i < size; i++) {
			 Vector<String> rows = new Vector<String>();
			 rows.addElement(list.get(i).getMemberId());
			 rows.addElement(list.get(i).getName());
			 rows.addElement(list.get(i).getBirth());
			 rows.addElement(list.get(i).getAge());
		
			 model.addRow(rows);
		 }
			//************************************************************

		
		JPanel panel2 = new JPanel();
		panel2.setBounds(0, 0, 0, 0);
		add(panel2);
		panel2.setBackground(Color.WHITE);
		panel2.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel2.setVisible(true);

		// �߰��� �����͵��� �𵨿� �߰��ϰ� ���� ���̺� ���ڷ� �־��ش�.
		JTable table = new JTable(model);
		table.setGridColor(Color.GRAY);
		table.setBackground(Color.WHITE);
		table.setForeground(Color.BLACK);
		JScrollPane jScollPane = new JScrollPane(table);
		add(jScollPane);
		jScollPane.setBorder(new LineBorder(new Color(130, 135, 144)));
		jScollPane.setViewportView(table);

		jScollPane.setBackground(Color.WHITE);
		jScollPane.setPreferredSize(new Dimension(560, 450));
		jScollPane.setBounds(622, 214, 560, 450);
		jScollPane.setForeground(Color.WHITE);
		
		table.setFillsViewportHeight(true);
	}

	private static ChartPanel createChart() {

		  DefaultPieDataset pieDataset = new DefaultPieDataset();
		  
		  StatDao dao = new StatDao();
		  
		  Vector<StatMemberDto> list = new Vector<StatMemberDto>();
		  
		  list = dao.rankingGraph();
		  
		  Vector<String> customerAge = new Vector<String>();        // 10��
		  Vector<Integer> customerAgeCount = new Vector<Integer>(); // 2
		   
		  // �� ���� 
		  int size = list.size(); 
		  for (int i = 0; i < size; i++) { 
		   
		  customerAge.addElement(list.get(i).getCustomerAge()); 
		  customerAgeCount.addElement(list.get(i).getCustomerAgeCount()); 
		   
		  // ��, ����, ī�װ� ���� 
		  pieDataset.setValue(customerAge.get(i), customerAgeCount.get(i)); 
		  } 
		  
		  JFreeChart chart = ChartFactory.createPieChart
		   ("���ɺ� ���", pieDataset, false,true,true);
		  
		  chart.getTitle().setFont(new Font("�������", Font.BOLD, 30)); // ��Ʈ ���� ��Ʈ
		  
		  PiePlot plot = (PiePlot) chart.getPlot();
	        plot.setLabelFont(new Font("�������", Font.BOLD, 15)); // ��Ʈ ���� ��Ʈ
	        plot.setCircular(true); //�� �۰�
	        plot.setLabelGap(0.02);
		 
		  ChartPanel frame1=new ChartPanel(chart); //
		  frame1.setVisible(true);
		  frame1.setSize(300,300);
		  
		return frame1;

	}
}
