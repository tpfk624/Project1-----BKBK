package com.kitri.Manager.page;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.table.DefaultTableModel;

import com.kitri.Manager.main.MgmtMain;

public class BookMgmtDesign extends JPanel {

//----------------------------------------------------------------------------------------------------------------�����
	private JPanel panelC = new JPanel();

	private JPanel panelR = new JPanel();
	private JPanel panelR1 = new JPanel();
	public JButton selAllMenu = new JButton("All");
	String[] str = {"��������", "�帣", "������", "�۰���"};
	public JComboBox serchC = new JComboBox(str);
	public JTextField findTF = new JTextField();
	public JButton findB;

	private JPanel panelR2 = new JPanel();
	public BookTM bookTM = new BookTM();
	private JPanel panelS = new JPanel();
	public JButton homeB = new JButton("������ �α׾ƿ�");
	public JButton statsB = new JButton("���");
	public JButton addB = new JButton("�߰�");
	public JButton mdfyB = new JButton("����");
	public JButton delB = new JButton("����");
	public JButton mgmtNumB = new JButton("������ȣ����");

	
	/**
	 * Create the panel.
	 */
	public BookMgmtDesign() {

		//-----------------------------------------------------------������ �г� ��� ����
		panelR1.setBackground(SystemColor.window);
		panelR1.setBounds(0, 0, 1374, 43);
		panelR1.setLayout(null);
		
		selAllMenu.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		selAllMenu.setFont(new Font("���� ���", Font.BOLD, 12));
		selAllMenu.setBackground(SystemColor.controlHighlight);
		selAllMenu.setBounds(0, 13, 82, 30);
		
		serchC.setBounds(1069, 4, 103, 30);
		
		findTF.setBounds(1184, 4, 150, 30);
		findTF.setColumns(10);
		findB = new JButton() {
			@Override
			protected void paintComponent(Graphics g) {
				Dimension d = getSize();
				ImageIcon imageIcon = new ImageIcon(".\\src\\com\\kitri\\Manager\\image\\magnifying-glass.png");
				Image image = imageIcon.getImage();
				g.drawImage(image, 0, 0, d.width, d.height, null);
				setOpaque(true);
			}
		};
		findB.setBackground(SystemColor.window);
		findB.setBounds(1337, 4, 36, 29);
		
		panelR1.add(selAllMenu);
		panelR1.add(serchC);
		panelR1.add(findTF);
		panelR1.add(findB);
		
		
		//-------------------------------------------------------------������ �г� �ϴ� ����
		panelR2.setBounds(0, 47, 1374, 715);
		panelR2.setLayout(new BorderLayout(0, 0));

		//���̺� ����
		JScrollPane tableP = new JScrollPane(bookTM.bookT); 
		panelR2.add(tableP);
		
		//---------------------------------------------------------------������ �г� ����
		panelR.setBounds(0, 0, 1374, 767);
		panelR.setBackground(SystemColor.window);
		panelR.setLayout(null);
		panelR.add(panelR1);
		panelR.add(panelR2);

		//---------------------------------------------------------------�ϴ� ��ư ����
		panelS.setBackground(SystemColor.window);
		panelS.setBounds(0, 767, 1470, 49);
		panelS.setLayout(null);
		btnD(homeB, SystemColor.controlHighlight, 12);
		btnD(statsB, SystemColor.controlHighlight, 12);
		btnD(addB, SystemColor.controlHighlight, 12);
		btnD(mdfyB, SystemColor.controlHighlight, 12);
		btnD(delB, SystemColor.controlHighlight, 12);
		btnD(mgmtNumB, SystemColor.controlHighlight, 12);
		homeB.setBounds(0, 10, 121, 30);
		statsB.setBounds(250, 10, 75, 30);
		addB.setBounds(1125, 10, 75, 30);
		mdfyB.setBounds(1212, 10, 75, 30);
		delB.setBounds(1299, 10, 75, 30);
		mgmtNumB.setMargin(new Insets(2, 8, 2, 8));
		mgmtNumB.setBounds(133, 10, 105, 30);
		panelS.add(homeB);
		panelS.add(statsB);
		panelS.add(addB);
		panelS.add(mdfyB);
		panelS.add(delB);
		panelS.add(mgmtNumB);
		
		//----------------------------------------------------------------�������� ���� �г� ����
		panelC.setBackground(SystemColor.controlHighlight);
		panelC.setBounds(0, 0, 1374, 767);
		panelC.setLayout(null);
		panelC.add(panelR);
		
		setBounds(12, 10, 1374, 815);
		setLayout(null);
		add(panelC);
		add(panelS);

	}
	
	
	private void btnD(JButton b, Color c, int size) {	// ��ư ������ method
		b.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		b.setFont(new Font("���� ���", Font.BOLD, size));
		if(c != null)
			b.setBackground(c);
	}
	
	private Font fontD(int m, int size) {
		Font f = null;
		if(m!=0)
			f = new Font("���� ���", Font.BOLD, size);
		else
			f = new Font("���� ���", Font.PLAIN, size);
		return f;
	}
}
