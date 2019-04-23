package com.kitri.Statistics.chart;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import java.util.Vector;

import javax.swing.JTable;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.CategoryItemLabelGenerator;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PieLabelLinkStyle;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.ui.TextAnchor;


import com.kitri.Statistics.StatFoodDto;
import com.kitri.Statistics.StatMemberDto;

public class ChartFoodService {

	
	public static Component chart;


	StatDao statDao = new StatDao(); // Dao ��ü

	ChartFoodController chartFoodController;
	private ChartFood cf;

	public ChartFoodService(ChartFoodController chartFoodController) {
		this.chartFoodController = chartFoodController;
		cf = chartFoodController.chartFood;
	}

	// [��� �޼ҵ�]

	// <��ȸ ��ư Ŭ��> �̺�Ʈ
	public void search() {


		System.out.println("�Ϻ� ��ȸ ��ư�� ���Ƚ��ϴ�.");

		String startyear = (String) ChartFood.startyear.getSelectedItem();// ��ư �������� �޺��ڽ��� ���� �о��
		String startmonth = (String) ChartFood.startmonth.getSelectedItem();// ��ư �������� �޺��ڽ��� ���� �о��
		String lastyear = (String) ChartFood.lastyear.getSelectedItem();// ��ư �������� �޺��ڽ��� ���� �о��
		String lastmonth = (String) ChartFood.lastmonth.getSelectedItem();// ��ư �������� �޺��ڽ��� ���� �о��

		// ������
		System.out.println(startyear.concat(startmonth));
		// ������
		System.out.println(lastyear.concat(lastmonth));

		/////////////////////////////////////////////////////////////////////
		String startdate = startyear.concat(startmonth); // ������
		String lastdate = lastyear.concat(lastmonth); // ������
		System.out.println(startdate + " ������ " + lastdate);

		StatDao statDao = new StatDao(); // Dao ��ü


		chartFoodController.chartFood.rows = statDao.selectFoodRanking(startdate, lastdate);
		
		
		System.out.println("������" + chartFoodController.chartFood.rows.size());


		chartFoodController.chartFood.model.setDataVector(chartFoodController.chartFood.rows,
				chartFoodController.chartFood.column1);
		
		
		setGraphbestProducts();

	}

	static JFreeChart setGraphbestProducts() {

	
			DefaultCategoryDataset dataset = new DefaultCategoryDataset();
			
			 StatDao dao = new StatDao();
			 
			 Vector<StatFoodDto> list = new Vector<StatFoodDto>();
			  
			  Vector<String> productName = new Vector<String>();        // 10��
			  Vector<Integer> totalSellPrice = new Vector<Integer>(); // 2
			  
			  
			  
			  String startyear = (String) ChartFood.startyear.getSelectedItem();// ��ư �������� �޺��ڽ��� ���� �о��
				String startmonth = (String) ChartFood.startmonth.getSelectedItem();// ��ư �������� �޺��ڽ��� ���� �о��
				String lastyear = (String) ChartFood.lastyear.getSelectedItem();// ��ư �������� �޺��ڽ��� ���� �о��
				String lastmonth = (String) ChartFood.lastmonth.getSelectedItem();// ��ư �������� �޺��ڽ��� ���� �о��

				// ������
				System.out.println(startyear.concat(startmonth));
				// ������
				System.out.println(lastyear.concat(lastmonth));

				/////////////////////////////////////////////////////////////////////
				String startdate = startyear.concat(startmonth); // ������
				String lastdate = lastyear.concat(lastmonth); // ������
				
			 list = dao.productTotalGraph(startdate, lastdate);
			  
			  // �� ���� 
			  int size = list.size(); 
			  for (int i = 0; i < size; i++) { 
			   
				  productName.addElement(list.get(i).getProductName()); //��ǰ�̸�
				  totalSellPrice.addElement(list.get(i).getTotalSellPrice()); //��ǰ �� ����
			   
			  // ��, ����, ī�װ� ���� 
				  dataset.setValue(totalSellPrice.get(i),  "�����հ�", productName.get(i)); 
			  } 
			  
			 
			JFreeChart chart = ChartFactory.createBarChart	(
				"��ǰ ����","FoodName", "sum", dataset,
				PlotOrientation.VERTICAL, false,true, false);
			chart.setBackgroundPaint(Color.white);
			chart.getTitle().setPaint(Color.BLACK); 
			chart.getTitle().setFont(new Font("�������", Font.BOLD, 25));
		
			
			CategoryPlot p = chart.getCategoryPlot(); 
			p.setRangeGridlinePaint(Color.yellow); 
			
			
			return chart;
	}
}
	
	
	
	
	
	