package com.kitri.Manager.page;

import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class BookTM extends DefaultTableModel {
	public Vector list = new Vector(); 
	public Vector header = new Vector();
	public JTable bookT;
	
	public BookTM() {
		header.add("No");
		header.add("��������");
		header.add("�帣");
		header.add("������");
		header.add("�۰���");
		header.add("���ǻ�");
		header.add("����");
		header.add("������Ȳ");
		header.add("���");
		
		setDataVector(list, header);
		bookT = new JTable(this);
		bookT.setFillsViewportHeight(true);
	
	}
	
	@Override
	public boolean isCellEditable(int row, int column) {
		return false;
	}
}
