package com.kitri.Manager.page;

import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.kitri.Manager.data.StockDto;

public class StockTM extends DefaultTableModel{

	public Vector list = new Vector(); 
	public Vector header = new Vector();
	public JTable stockT;
	
	
	public StockTM() {
	
		header.add("No");
		header.add("ī�װ�");
		header.add("ǰ��");
		header.add("���� ����");
		header.add("�ܰ�");
		header.add("���");
		
		setDataVector(list, header);
		stockT = new JTable(this);
		stockT.setFillsViewportHeight(true);
		
//		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
//		dtcr.setHorizontalAlignment(JLabel.CENTER);
//		stockT.getColumn("No").setCellRenderer(dtcr);
//		stockT.getColumn("ī�װ�").setCellRenderer(dtcr);
//		stockT.getColumn("ǰ��").setCellRenderer(dtcr);
//		stockT.getColumn("���� ����").setCellRenderer(dtcr);
//		stockT.getColumn("�ܰ�").setCellRenderer(dtcr);
//		stockT.getColumn("���").setCellRenderer(dtcr);
//		stockT.getColumn("No").setPreferredWidth(10);
//		stockT.getColumn("ǰ��").setPreferredWidth(100);
//		stockT.setRowHeight(30);
	}
	
	@Override
	public boolean isCellEditable(int row, int column) {
		return false;
	}
	
}