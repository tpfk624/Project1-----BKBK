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
		lblNewLabel.setFont(new Font("굴림", Font.BOLD, 30));
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
		

		// 컬럼명을 만들어준다.
		Vector<String> column1 = new Vector<String>();
		column1.addElement("회원ID");
		column1.addElement("이름");
		column1.addElement("생일");
		column1.addElement("연령대");
		

		// 추가한 컬럼명으로 모델 생성하고 인자로 넣어줌
		DefaultTableModel model = new DefaultTableModel(column1, 0);

		//******************dao 함수 사용 예시****************************
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

		// 추가된 데이터들을 모델에 추가하고 모델을 테이블 인자로 넣어준다.
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
		  
		  Vector<String> customerAge = new Vector<String>();        // 10대
		  Vector<Integer> customerAgeCount = new Vector<Integer>(); // 2
		   
		  // 행 세팅 
		  int size = list.size(); 
		  for (int i = 0; i < size; i++) { 
		   
		  customerAge.addElement(list.get(i).getCustomerAge()); 
		  customerAgeCount.addElement(list.get(i).getCustomerAgeCount()); 
		   
		  // 값, 범례, 카테고리 지정 
		  pieDataset.setValue(customerAge.get(i), customerAgeCount.get(i)); 
		  } 
		  
		  JFreeChart chart = ChartFactory.createPieChart
		   ("연령별 통계", pieDataset, false,true,true);
		  
		  chart.getTitle().setFont(new Font("맑은고딕", Font.BOLD, 30)); // 차트 제목 폰트
		  
		  PiePlot plot = (PiePlot) chart.getPlot();
	        plot.setLabelFont(new Font("맑은고딕", Font.BOLD, 15)); // 차트 범례 폰트
	        plot.setCircular(true); //원 작게
	        plot.setLabelGap(0.02);
		 
		  ChartPanel frame1=new ChartPanel(chart); //
		  frame1.setVisible(true);
		  frame1.setSize(300,300);
		  
		return frame1;

	}
}
