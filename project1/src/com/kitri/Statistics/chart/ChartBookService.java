package com.kitri.Statistics.chart;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import java.util.Vector;

import javax.swing.JTable;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.CategoryItemLabelGenerator;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.DatasetRenderingOrder;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.ui.GradientPaintTransformType;
import org.jfree.ui.HorizontalAlignment;
import org.jfree.ui.StandardGradientPaintTransformer;
import org.jfree.ui.TextAnchor;

import com.kitri.Statistics.StatBookDto;
import com.kitri.Statistics.StatMemberDto;

public class ChartBookService {

	Vector<StatMemberDto> bests = null; // Best5 ��ǰ �׷����� ���� ����
	Vector<StatMemberDto> results = null; // �˻� ��� ��ǰ ���̺�� ���� ����

	StatDao statDao = new StatDao(); // Dao ��ü

	ChartBookController chartBookController;
	private ChartBook cb;

	public ChartBookService(ChartBookController chartBookController) {
		this.chartBookController = chartBookController;
		cb = chartBookController.chartBook;
	}

	
	public JFreeChart setGraphbestBook() {

		 // ������ ����
        DefaultCategoryDataset dataset1 = new DefaultCategoryDataset();                // bar chart 1
       
        StatDao dao = new StatDao();

		Vector<StatBookDto> list = new Vector<StatBookDto>();

		Vector<Integer> totalcount = new Vector<Integer>(); // 2
		Vector<String> bookName = new Vector<String>(); // 10��

		String startyear = (String) ChartBook.startyear.getSelectedItem();// ��ư �������� �޺��ڽ��� ���� �о��
		String startmonth = (String) ChartBook.startmonth.getSelectedItem();// ��ư �������� �޺��ڽ��� ���� �о��
		String lastyear = (String) ChartBook.lastyear.getSelectedItem();// ��ư �������� �޺��ڽ��� ���� �о��
		String lastmonth = (String) ChartBook.lastmonth.getSelectedItem();// ��ư �������� �޺��ڽ��� ���� �о��

		// ������
		System.out.println(startyear.concat(startmonth));
		// ������
		System.out.println(lastyear.concat(lastmonth));

		/////////////////////////////////////////////////////////////////////
		String startdate = startyear.concat(startmonth); // ������
		String lastdate = lastyear.concat(lastmonth); // ������

		list = dao.BookTotalGraph(startdate, lastdate);

		// �� ����
		int size = list.size();
		for (int i = 0; i < size; i++) {

			
			totalcount.addElement(list.get(i).getTotalcount()); // ��ǰ �� ����
			bookName.addElement(list.get(i).getBookName()); // ��ǰ�̸�


			// ��, ����, ī�װ� ����
			dataset1.addValue(totalcount.get(i), "�뿩��", bookName.get(i));
		}
        
       
        // ������ ���� �� ����
        // ������ ����
        final BarRenderer renderer = new BarRenderer();

        // ���� �ɼ� ����
        final CategoryItemLabelGenerator generator = new StandardCategoryItemLabelGenerator();
        final ItemLabelPosition p_center = new ItemLabelPosition(
                ItemLabelAnchor.CENTER, TextAnchor.CENTER 
            );
        final ItemLabelPosition p_below = new ItemLabelPosition(
                     ItemLabelAnchor.OUTSIDE6, TextAnchor.TOP_LEFT
                     );
        Font f = new Font("�������", Font.BOLD, 14);
        Font axisF = new Font("�������", Font.PLAIN, 14);
        
        // ������ ����
        // �׷��� 1
        renderer.setBaseItemLabelGenerator(generator);
        renderer.setBaseItemLabelsVisible(true);
        renderer.setBasePositiveItemLabelPosition(p_center);
        renderer.setBaseItemLabelFont(f);
        renderer.setGradientPaintTransformer(new StandardGradientPaintTransformer(
                GradientPaintTransformType.VERTICAL));
        renderer.setSeriesPaint(0, new GradientPaint(1.0f, 1.0f, Color.white, 0.0f, 0.0f, Color.blue));
        renderer.setSeriesPaint(0, new Color(0,162,255));
 
        // plot ����
        final CategoryPlot plot = new CategoryPlot();
        
        // plot �� ������ ����
        plot.setDataset(dataset1);
        plot.setRenderer(renderer);

 
        // plot �⺻ ����
        plot.setOrientation(PlotOrientation.VERTICAL);             // �׷��� ǥ�� ����
//        plot.setRangeGridlinesVisible(true);                       // X�� ���̵� ���� ǥ�ÿ���
//        plot.setDomainGridlinesVisible(true);                      // Y�� ���̵� ���� ǥ�ÿ���
 
        // ������ ���� ���� : dataset ��� ������� ������ ( ��, ���� ����Ѱ� �Ʒ��� �� )
        plot.setDatasetRenderingOrder(DatasetRenderingOrder.FORWARD);
        
        // X�� ����
        plot.setDomainAxis(new CategoryAxis());              // X�� ���� ����
        plot.getDomainAxis().setTickLabelFont(axisF); // X�� ���ݶ� ��Ʈ ����
        plot.getDomainAxis().setCategoryLabelPositions(CategoryLabelPositions.STANDARD);       // ī�װ� �� ��ġ ����
 
        // Y�� ����
        plot.setRangeAxis(new NumberAxis());                 // Y�� ���� ����
        plot.getRangeAxis().setTickLabelFont(axisF);  // Y�� ���ݶ� ��Ʈ ����
        
        
        // ���õ� plot�� �������� chart ����
        final JFreeChart chart = new JFreeChart(plot);
        chart.setTitle("�α� ���� ����"); // ��Ʈ Ÿ��Ʋ
        chart.getTitle().setFont(new Font("�������", Font.BOLD, 25));
        return chart;

        
	}
}
