package com.kitri.Manager.page;

import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class MemTM extends DefaultTableModel{
	public Vector list = new Vector(); 
	public Vector header = new Vector();
	public JTable memT;
	
	public MemTM() {
		header.add("No");
		header.add("�̸�");
		header.add("��ȭ��ȣ");
		header.add("�ּ�");
		header.add("�������");
		header.add("õ����������");
		header.add("������ �̿���");
		header.add("������Ȳ");
		header.add("���");
		
		setDataVector(list, header);
		memT = new JTable(this);
		memT.setFillsViewportHeight(true);
	
	}
	
	@Override
	public boolean isCellEditable(int row, int column) {
		return false;
	}
}
