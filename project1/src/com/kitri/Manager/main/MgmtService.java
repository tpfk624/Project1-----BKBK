package com.kitri.Manager.main;

import java.awt.Color;
import java.awt.Dimension;
import java.text.*;
import java.util.*;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;

import com.kitri.Manager.dao.*;
import com.kitri.Manager.data.*;
import com.kitri.Manager.page.BookMgmtDesign;
import com.kitri.Manager.page.FoodMgmtDesign;
import com.kitri.Manager.page.MemberMgmtDesign;
import com.kitri.Manager.popup.*;

public class MgmtService {

	// field
	MgmtController mmc;
	FoodMgmtDesign fP;
	BookMgmtDesign bP;
	MemberMgmtDesign mP;
	
	FctgDialog fcA;
	FsDialog fsA;
	BookDialog bAdd;
	MemDialog mAdd;
	
	FoodDto foodDto;
	StockDto stockDto;
	BookDto bookDto;
	MemberDto memDto;
	List<String> list;
	
	
	String ctg;
	int amtS;

	
	// constructor
	public MgmtService(MgmtController mmc) {
		this.mmc = mmc;
		fP = mmc.mm.fm;
		bP = mmc.mm.bm;
		mP = mmc.mm.mm;
		fcA = mmc.mm.fcAdd;
		fsA = mmc.mm.fsAdd;
		bAdd = mmc.mm.bAdd;
		mAdd = mmc.mm.mAdd;
		
	}
	

//-------------------------------------------------------------------------------------------------------------------------------------------------[h] ������ȣ����
	
	public boolean isNum(String str) { //���ڸ� true ��ȯ
		boolean flag = true;
		int len = str.length();
		for (int i = 0; i < len; i++) {
			int num = str.charAt(i) - 48;
			if(num < 0 || num > 9) {
				flag = false;
				continue;
			}
		}
		return flag;
	}
	
	public void mgmtNum() {
		while(true) {
			String r = JOptionPane.showInputDialog(mmc.mm, "���ο� ��ȣ�� �Է��ϼ���", "������ȣ����", JOptionPane.OK_CANCEL_OPTION).trim();
			
			if(r.isEmpty()) {
				JOptionPane.showMessageDialog(mmc.mm, "��ȣ�� �Է��ϼ���.", "������ȣ��������", JOptionPane.ERROR_MESSAGE);
				continue;
			}
			if(r.length() != 5) {
				JOptionPane.showMessageDialog(mmc.mm, "5�ڸ� ���ڸ� �Է��ϼ���.", "������ȣ��������", JOptionPane.ERROR_MESSAGE);
				continue;
			}
			
			
			boolean rf = isNum(r);
			if(rf) {
				int result = FoodDao.getInstance().upMgmtNum(Integer.parseInt(r));
				if(result != 0) {
					JOptionPane.showMessageDialog(mmc.mm, "������ȣ�� �����Ǿ����ϴ�.", "�����Ϸ�", JOptionPane.INFORMATION_MESSAGE);
					break;
				}
				else {
					JOptionPane.showMessageDialog(mmc.mm, "�� �� ���� ������ �߻��߽��ϴ�.", "������ȣ��������", JOptionPane.ERROR_MESSAGE);
					break;
				}
			} else {
				JOptionPane.showMessageDialog(mmc.mm, "5�ڸ� ���ڸ� �Է��ϼ���.", "������ȣ��������", JOptionPane.ERROR_MESSAGE);
				continue;
			}
		}
	}
	
//-------------------------------------------------------------------------------------------------------------------------------------------------[h]

//--------------------------------------------------------------------------------------------------------------------------------------FOOD
	
	
	public void foodPage() {//---------------------------------------��ǰ���� �������� �̵�, �޴���ư&���̺� �ʱ�ȭ
		mmc.mm.cards.show(mmc.mm.cardP, "��ǰ����P");
		setMenuB("����");
	}
	
	
//-----------------------------------------------------------------------------------------------------------menu ���
	
	
	// �޴���ư ����
	public void setMenuB(String ctg) {
		this.ctg = ctg;
		list = FoodDao.getInstance().selCtgMenu(ctg);//ī�װ��� �޴��̸� select
		int size = list.size();
		for(int i=0;i<size;i++) {//�޴� ������ŭ for
			String btnL = list.get(i);
			if(btnL.length() > 5) {//�޴��̸��� �� ��� ����
				StringBuffer str = new StringBuffer(list.get(i));
				str.insert(3, "<br>");
				str.insert(0, "<html>");
				str.append("</html>");
				if(btnL.length() > 9) {
					str.insert(16, "<br>");
				}
				btnL = str.toString();
			}
			fP.menuB[i].setText(btnL);
			fP.menuB[i].setHorizontalAlignment(SwingConstants.CENTER);
			fP.abBD(fP.menuB[i],null, 15);
			fP.menuB[i].setVisible(true);
		}
		if(size < 25) {
			fP.menuB[size].setText("+");//������ �޴� ���� ��ư�� '+' ���� ���� ũ�� ���� & ���̰�
			fP.abBD(fP.menuB[size], new Color(52, 152, 219), 35);
			fP.menuB[size].setVisible(true);
			for(int i = 24; i > size ; i--) {
				fP.menuB[i].setVisible(false);
			}
		}
	}
	
	
	// menu��ư dialog
	public void setM(String btnL) {
		
		// ��ư�� �󺧿� ���� �ִ� ��� �ʱ�ȭ
		if(btnL.indexOf("<br>") != -1) {
			btnL = btnL.replace("<br>", "");
			btnL = btnL.replace("<html>", "");
			btnL = btnL.replace("</html>", "");
		}
		
		// dialog ��ư �ʱ�ȭ
		fcA.ctgC.setSelectedItem(ctg);
		fcA.ctgC.setEnabled(true);
		fcA.menuTF.setEnabled(true);
		fcA.menuTF.setText(null);
		fcA.priceTF.setText(null);

		if(!btnL.equals("+")) {// ���� �޴� ����&���� dialog
			foodDto = FoodDao.getInstance().findMenu(btnL, ctg);
			fcA.menuTF.setText(foodDto.getFoodName());
			fcA.menuTF.setEnabled(false);
			fcA.priceTF.setText(""+foodDto.getFoodPrice());
			fcA.ctgC.setSelectedItem(foodDto.getFoodCtg());
			fcA.ctgC.setEnabled(false);
			fcA.st1C.setSelectedItem(foodDto.getStock1Name());
			fcA.st1A.setText(""+foodDto.getStock1Num());
			if(foodDto.getStock2Name() != null) {
				fcA.st2C.setSelectedItem(foodDto.getStock2Name());
				fcA.st2A.setText(""+foodDto.getStock2Num());
			}
			if(foodDto.getStock3Name() !=null) {
				fcA.st3C.setSelectedItem(foodDto.getStock3Name());
				fcA.st3A.setText(""+foodDto.getStock3Num());
			}
			fcA.setTitle("�޴� ����");
			fcA.card.show(fcA.cardP, "mdf");
			fcA.setVisible(true);
		} else {// �޴� �߰� dialog
			fcA.setTitle("�޴� �߰�");
			fcA.card.show(fcA.cardP, "add");
			fcA.setVisible(true);
		}
	}
	
	
	// ctg�� ��� ǰ�� combobox�� �߰��ϰ� ���� �ʱ�ȭ
	public void comboItem(String category) {
		ctg = category;
		List<String> list = FoodDao.getInstance().selStockName(ctg);
		fcA.st1C.removeAllItems();
		fcA.st2C.removeAllItems();
		fcA.st3C.removeAllItems();
		
		for(String l : list) {
			fcA.st1C.addItem(l);
			fcA.st2C.addItem(l);
			fcA.st3C.addItem(l);
		}
		
		fcA.st1C.setSelectedItem(null);
		fcA.st1A.setText(null);
		fcA.st2C.setSelectedItem(null);
		fcA.st2A.setText(null);
		fcA.st3C.setSelectedItem(null);
		fcA.st3A.setText(null);
		setMenuB(ctg);
	}

	
	// �޴��߰�&����
	public void amMenu(String btn) {
		
		// ��ȿ�� �˻�
		String foodName = fcA.menuTF.getText().trim();
		if(foodName.isEmpty()) {
			JOptionPane.showMessageDialog(fcA, "�޴� �̸��� �Է��ϼ���.", "�ʼ��Է��׸񿡷�", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		if(btn.equals("�߰�")) {
			for(String l : list) {
				if(foodName.equals(l)) {
					JOptionPane.showMessageDialog(fcA, "�̹� �����ϴ� �޴��Դϴ�.", "�ʼ��Է��׸񿡷�", JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
		}
		
		if(fcA.priceTF.getText().isEmpty()) {
			JOptionPane.showMessageDialog(fcA, "������ �Է��ϼ���.", "�ʼ��Է��׸񿡷�", JOptionPane.ERROR_MESSAGE);
			return;
		} 
		if(fcA.st1C.getSelectedItem()==null || fcA.st1A.getText().isEmpty()) {
			JOptionPane.showMessageDialog(fcA, "���� 1�� �̻� �����ؾ��մϴ�.", "�ʼ��Է��׸񿡷�", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		// Dto�� ��Ƽ� Dao�� ���� DB�� insert|update
		foodDto = new FoodDto(); 
		foodDto.setFoodCtg((String)fcA.ctgC.getSelectedItem());
		foodDto.setFoodName(fcA.menuTF.getText());
		foodDto.setFoodPrice(Integer.parseInt(fcA.priceTF.getText()));
		foodDto.setStock1Name(fcA.st1C.getSelectedItem().toString());
		foodDto.setStock1Num(Integer.parseInt(fcA.st1A.getText()));
		
		if(fcA.st2C.getSelectedItem()!=null) {
			foodDto.setStock2Name(fcA.st2C.getSelectedItem().toString());
			foodDto.setStock2Num(Integer.parseInt(fcA.st2A.getText()));
		}
		if(fcA.st3C.getSelectedItem()!=null) {
			foodDto.setStock3Name(fcA.st3C.getSelectedItem().toString());
			foodDto.setStock3Num(Integer.parseInt(fcA.st3A.getText()));
		} 
		int i = FoodDao.getInstance().mergeMenu(foodDto);

		// ����޼��� ���
		if(i != 0) {
			if(btn.equals("����")) {
				JOptionPane.showMessageDialog(fcA, "�޴��� �����Ǿ����ϴ�.", "�޴������Ϸ�", JOptionPane.INFORMATION_MESSAGE);
			}
			else if(btn.equals("�߰�")) {
				JOptionPane.showMessageDialog(fcA, "�޴��� �߰��Ǿ����ϴ�.", "�޴��߰��Ϸ�", JOptionPane.INFORMATION_MESSAGE);
			}
			fcA.setVisible(false);
			setMenuB(ctg);
		} else
			JOptionPane.showMessageDialog(fcA, "�� �� ���� ������ ���Ͽ� �߰�/������ �����Ͽ����ϴ�.", "����", JOptionPane.ERROR_MESSAGE);
	}

	
	// �޴�����
	public void delMenu() {
		
		// ��ȿ�� �˻�
		String foodName = fcA.menuTF.getText().trim();
		String ctg = fcA.ctgC.getSelectedItem().toString();
		if(foodName.isEmpty()) {
			JOptionPane.showMessageDialog(fcA, "�޴� �̸��� �Է��ϼ���.", "�ʼ��Է��׸񿡷�", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		// �������� Ȯ���ϰ� ����|���
		int result = JOptionPane.showConfirmDialog(fcA, "�����Ͻðڽ��ϱ�?", "����Ȯ��", JOptionPane.YES_NO_OPTION);
		if(result == 0) {
			result = FoodDao.getInstance().delMenu(foodName, ctg);
			if(result != 0) {
				JOptionPane.showMessageDialog(fcA, foodName + ": ���� �Ϸ�Ǿ����ϴ�.", "�޴������Ϸ�", JOptionPane.INFORMATION_MESSAGE);
				fcA.setVisible(false);
			} else if(result == 0) {
				JOptionPane.showMessageDialog(fcA, foodName + ": ��ϵ��� ���� �޴��Դϴ�.", "�����׸񿡷�", JOptionPane.ERROR_MESSAGE);
				return;
			}
		}else if(result == 1) {
			return;
		}
		
		// �޴���ư �ʱ�ȭ
		setMenuB(ctg);
	}

	public void cancleMenu() {//-------------------------------------�޴� �߰�/���� ���
		JOptionPane.showMessageDialog(fcA, "�۾��� ����Ͻðڽ��ϱ�?", "���Ȯ��", JOptionPane.INFORMATION_MESSAGE);
		fcA.setVisible(false);
		
	}

	
//-----------------------------------------------------------------------------------------------------��� ���
	
	
	// table ����
	public void tableD() {
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(JLabel.CENTER);
		fP.stockTM.stockT.getColumn("No").setCellRenderer(dtcr);
		fP.stockTM.stockT.getColumn("ī�װ�").setCellRenderer(dtcr);
		fP.stockTM.stockT.getColumn("ǰ��").setCellRenderer(dtcr);
		fP.stockTM.stockT.getColumn("���� ����").setCellRenderer(dtcr);
		fP.stockTM.stockT.getColumn("�ܰ�").setCellRenderer(dtcr);
		fP.stockTM.stockT.getColumn("���").setCellRenderer(dtcr);
		fP.stockTM.stockT.getColumn("No").setPreferredWidth(10);
		fP.stockTM.stockT.getColumn("ǰ��").setPreferredWidth(100);
		fP.stockTM.stockT.setRowHeight(30);
	}
	
	//table �ʱ�ȭ
	public void setStockT(String ctg) {
		fP.stockTM.list = FoodDao.getInstance().selStock(ctg);
		fP.stockTM.setDataVector(fP.stockTM.list, fP.stockTM.header);
		tableD();
	}
	
	
	//ǰ�� �˻�
	public void selStock() {
		String sName = fP.stockName.getText().trim();
		if(sName.isEmpty()) {
			JOptionPane.showMessageDialog(fP, "�˻��� ǰ���� �Է��ϼ���", "ǰ���Է¿���", JOptionPane.ERROR_MESSAGE);
			return;
		}
		fP.stockTM.list = FoodDao.getInstance().findStockV(sName);
		if(fP.stockTM.list!=null) {
			fP.stockTM.setDataVector(fP.stockTM.list, fP.stockTM.header);
			tableD();
		}
		else
			JOptionPane.showMessageDialog(fcA, sName + ": ��ϵ��� ���� ǰ���Դϴ�.", "ǰ��˻�����", JOptionPane.ERROR_MESSAGE);
	}

	
	//dialog
	public void setS() {
		fsA.ctgC.setSelectedItem(null);
		fsA.menuTF.setText("");
		fsA.menuTF.setEnabled(true);
		fsA.amtTF.setText("");
		fsA.priceTF.setText("");
		amtS = 0;
		int x = fP.stockTM.stockT.getSelectedRow();
		String sName;
		if(x > -1)
			sName = fP.stockTM.stockT.getValueAt(x, 2).toString();
		else
			sName = null;
		if(sName != null) {
			stockDto = FoodDao.getInstance().findStockD(sName);
			fsA.ctgC.setSelectedItem(stockDto.getFoodCtg());
			fsA.menuTF.setText(stockDto.getStockName());
			fsA.menuTF.setEnabled(false);
			fsA.priceTF.setText(""+stockDto.getUnitCost());
			fP.stockTM.stockT.clearSelection();
			amtS = stockDto.getRestAmt();
			fsA.setVisible(true);
		} else {
			fsA.setVisible(true);
		}
		
	}

	
	//�԰�
	public void addStock() {
		if(fsA.ctgC.getSelectedItem()==null) {
			JOptionPane.showMessageDialog(fcA, "ī�װ��� �����ϼ���.", "�ʼ��Է��׸񿡷�", JOptionPane.ERROR_MESSAGE);
			return;
		}
		ctg = fsA.ctgC.getSelectedItem().toString();
		if(fsA.menuTF.getText().isEmpty()) {
			JOptionPane.showMessageDialog(fcA, "ǰ���� �Է��ϼ���.", "�ʼ��Է��׸񿡷�", JOptionPane.ERROR_MESSAGE);
			return;
		} 
		if(fsA.amtTF.getText().isEmpty()) {
			JOptionPane.showMessageDialog(fcA, "������ �Է��ϼ���.", "�ʼ��Է��׸񿡷�", JOptionPane.ERROR_MESSAGE);
			return;
		} 
		if(fsA.priceTF.getText().isEmpty()) {
			JOptionPane.showMessageDialog(fcA, "�ܰ��� �Է��ϼ���.", "�ʼ��Է��׸񿡷�", JOptionPane.ERROR_MESSAGE);
			return;
		} 
		stockDto = new StockDto(); 
		stockDto.setFoodCtg(ctg);
		stockDto.setStockName(fsA.menuTF.getText());
		stockDto.setRestAmt(Integer.parseInt(fsA.amtTF.getText()) + amtS);
		stockDto.setUnitCost(Integer.parseInt(fsA.priceTF.getText()));

		FoodDao.getInstance().mergeStock(stockDto);
		JOptionPane.showMessageDialog(fcA, "�԰� ó���� �Ϸ�Ǿ����ϴ�.", "�԰�Ϸ�", JOptionPane.INFORMATION_MESSAGE);
		fsA.setVisible(false);
		setStockT(ctg);
		setMenuB(ctg);
	}

	
	//�԰� ���
	public void cancleStock() {
		JOptionPane.showMessageDialog(fcA, "�۾��� ����Ͻðڽ��ϱ�?", "���Ȯ��", JOptionPane.INFORMATION_MESSAGE);
		fsA.setVisible(false);
	}
	
	
	//��� ����
	public void delStock() {
		int x = fP.stockTM.stockT.getSelectedRow();
		String sName = null;
		String ctg = null;
		if(x > -1) {
			sName = fP.stockTM.stockT.getValueAt(x, 2).toString();
			ctg = fP.stockTM.stockT.getValueAt(x, 1).toString();
		}
		else {
			JOptionPane.showMessageDialog(fP, "������ ǰ���� �����ϼ���.", "ǰ���ÿ���", JOptionPane.ERROR_MESSAGE);
			return;
		}
		int result = JOptionPane.showConfirmDialog(fP, "�����Ͻðڽ��ϱ�?", "����Ȯ��", JOptionPane.YES_NO_OPTION);
		if(result == 0) {
			FoodDao.getInstance().delStock(sName);
			JOptionPane.showMessageDialog(fP, sName + ": ���� �Ϸ�Ǿ����ϴ�.", "�޴������Ϸ�", JOptionPane.INFORMATION_MESSAGE);
		}else if(result == 1) {
			return;
		}
		setStockT(ctg);
		setMenuB(ctg);
		
	}
	
	
	
//--------------------------------------------------------------------------------------------------------------------------------------------BOOK
	public void bookPage() {
		bP.serchC.setSelectedItem(null);
		mmc.mm.cards.show(mmc.mm.cardP, "��������P");
		setBT();
	}


	public void setBT() {//��ü sel
		//db���� ��ü sel (vector)
		//���̺� �𵨿� ����
		bP.bookTM.list = BookDao.getInstance().serchB(null, null);
		bP.bookTM.setDataVector(bP.bookTM.list, bP.bookTM.header);
		bP.serchC.setSelectedItem(null);
		bP.findTF.setText(null);
	}

	public void serchB() {//å �˻�
		int x = bP.serchC.getSelectedIndex();
		String str = bP.findTF.getText().trim();
		if(x < 0) {
			JOptionPane.showMessageDialog(bP, "�˻��׸��� �����ϼ���.", "�ʼ��Է��׸񿡷�", JOptionPane.ERROR_MESSAGE);
			return;
		}
		if(str.isEmpty()) {
			JOptionPane.showMessageDialog(bP, "�˻�� �Է��ϼ���.", "�ʼ��Է��׸񿡷�", JOptionPane.ERROR_MESSAGE);
			return;
		}
		bP.serchC.setSelectedItem(null);
		bP.findTF.setText(null);
		switch (x) {
		case 0 : bP.bookTM.list = BookDao.getInstance().serchB("book_ctg_name", str); break;
		case 1 : bP.bookTM.list = BookDao.getInstance().serchB("genre_name", str); break;
		case 2 : bP.bookTM.list = BookDao.getInstance().serchB("book_name", str); break;
		case 3 : bP.bookTM.list = BookDao.getInstance().serchB("author", str); break;
		default : return;
		}
		if(bP.bookTM.list.size() == 0) {
			JOptionPane.showMessageDialog(bP, "�ش��ϴ� ������ �����ϴ�.", "�˻�����", JOptionPane.ERROR_MESSAGE);
			return;
		}
			
		bP.bookTM.setDataVector(bP.bookTM.list, bP.bookTM.header);
		
	}

	public void addB() {
		initB();
		bAdd.card.show(bAdd.cardP, "add");
		bAdd.ctgC.setSelectedItem(null);
		bAdd.genreC.setSelectedItem(null);
		bAdd.setVisible(true);
	}
	
	public void mdfB() {
		initB();
		bAdd.card.show(bAdd.cardP, "mdf");
		int x = bP.bookTM.bookT.getSelectedRow();
		if(x < 0) {
			JOptionPane.showMessageDialog(bP, "������ ������ �����ϼ���.", "�����̼��ÿ���", JOptionPane.ERROR_MESSAGE);
			return;
		}
		String bName = bP.bookTM.bookT.getValueAt(x, 3).toString();
		bookDto = BookDao.getInstance().findB(bName);
		bAdd.ctgC.setSelectedItem(bookDto.ctgDto.getBookCtgName());
		bAdd.genreC.setSelectedItem(bookDto.genDto.getGenreName());
		bAdd.nameTF.setText(bookDto.getBookName());
		bAdd.nameTF.setEnabled(false);
		bAdd.authorTF.setText(bookDto.getAuthor());
		bAdd.publisherTF.setText(bookDto.getPublisher());
		bAdd.priceTF.setText(""+bookDto.getBookPrice());
		bAdd.setVisible(true);
	}


	public void delBook() {
		int x = bP.bookTM.bookT.getSelectedRow();
		if(x < 0) {
			JOptionPane.showMessageDialog(bP, "������ ������ �����ϼ���.", "�����̼��ÿ���", JOptionPane.ERROR_MESSAGE);
			return;
		}
		String bName = bP.bookTM.bookT.getValueAt(x, 3).toString();
		int result = JOptionPane.showConfirmDialog(fcA, "�����Ͻðڽ��ϱ�?", "����Ȯ��", JOptionPane.YES_NO_OPTION);
		if(result == 0) {
			BookDao.getInstance().delBook(bName);
			JOptionPane.showMessageDialog(bP, bName + ": ���� �Ϸ�Ǿ����ϴ�.", "�޴������Ϸ�", JOptionPane.INFORMATION_MESSAGE);
			fcA.setVisible(false);
		}else if(result == 1) {
			bP.bookTM.bookT.clearSelection();
			return;
		}
		setBT();
	}


	public void amBook(String btn) {
		if(bAdd.ctgC.getSelectedItem() == null) {
			JOptionPane.showMessageDialog(bAdd, "���������� �����ϼ���.", "�ʼ��Է��׸񿡷�", JOptionPane.ERROR_MESSAGE);
			return;
		} 
		if(bAdd.genreC.getSelectedItem() == null) {
			JOptionPane.showMessageDialog(bAdd, "�帣�� �����ϼ���.", "�ʼ��Է��׸񿡷�", JOptionPane.ERROR_MESSAGE);
			return;
		} 
		if(bAdd.nameTF.getText().trim() == null) {
			JOptionPane.showMessageDialog(bAdd, "�������� �Է��ϼ���.", "�ʼ��Է��׸񿡷�", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		if(bAdd.authorTF.getText().trim() == null) {
			JOptionPane.showMessageDialog(bAdd, "�۰����� �Է��ϼ���.", "�ʼ��Է��׸񿡷�", JOptionPane.ERROR_MESSAGE);
			return;
		} 
		if(bAdd.publisherTF.getText().trim() == null) {
			JOptionPane.showMessageDialog(bAdd, "���ǻ縦 �Է��ϼ���.", "�ʼ��Է��׸񿡷�", JOptionPane.ERROR_MESSAGE);
			return;
		} 
		if(bAdd.priceTF.getText().trim() == null) {
			JOptionPane.showMessageDialog(bAdd, "������ �Է��ϼ���.", "�ʼ��Է��׸񿡷�", JOptionPane.ERROR_MESSAGE);
			return;
		} 
		bookDto = new BookDto(); 
		bookDto.setBookCtg(bAdd.ctgC.getSelectedIndex()+1);
		bookDto.setGenreNum(bAdd.genreC.getSelectedIndex()+1);
		bookDto.setBookName(bAdd.nameTF.getText().trim());
		bookDto.setAuthor(bAdd.authorTF.getText().trim());
		bookDto.setPublisher(bAdd.publisherTF.getText().trim());
		bookDto.setBookPrice(Integer.parseInt(bAdd.priceTF.getText().trim()));
		BookDao.getInstance().mergeB(bookDto);		
		if(btn.equals("����")) {
			JOptionPane.showMessageDialog(bAdd, "������ �����Ǿ����ϴ�.", "���������Ϸ�", JOptionPane.INFORMATION_MESSAGE);
		}
		else if(btn.equals("�߰�")) {
			JOptionPane.showMessageDialog(bAdd, "������ �߰��Ǿ����ϴ�.", "�����߰��Ϸ�", JOptionPane.INFORMATION_MESSAGE);
		}
		bAdd.setVisible(false);
		setBT();
	}

	private void initB() {
		bAdd.nameTF.setEnabled(true);
		bAdd.ctgC.removeAllItems();
		bAdd.genreC.removeAllItems();
		List<String> list = BookDao.getInstance().selCol("book_ctg_name", "book_ctg", "category");
		for(String l : list) {
			bAdd.ctgC.addItem(l);
		}
		list = BookDao.getInstance().selCol("genre_name", "genre_num", "genre");
		for(String l : list) {
			bAdd.genreC.addItem(l);
		}
		bAdd.nameTF.setText(null);
		bAdd.authorTF.setText(null);
		bAdd.publisherTF.setText(null);
		bAdd.priceTF.setText(null);
	}


	public void cancleBook() {
		JOptionPane.showMessageDialog(bAdd, "�۾��� ����Ͻðڽ��ϱ�?", "���Ȯ��", JOptionPane.INFORMATION_MESSAGE);
		bP.bookTM.bookT.clearSelection();
		bAdd.setVisible(false);
	}

//----------------------------------------------------------------------------------------------------------------------------------MEMBER
	
	public void memPage() {
		mmc.mm.cards.show(mmc.mm.cardP, "ȸ������P");
		mP.findC.setSelectedItem(null);
		selMem();
		
	}
	
	public void selMem() {
		mP.memTM.list = MemDao.getInstance().serchM(null, null);
		mP.findC.setSelectedItem(null);
		mP.memTM.setDataVector(mP.memTM.list, mP.memTM.header);
	}

	public void serchM() {
		int colN = mP.findC.getSelectedIndex();
		String str = mP.findTF.getText().trim();
		if(colN < 0) {
			JOptionPane.showMessageDialog(bAdd, "�˻������� �����ϼ���.", "�ʼ��Է��׸񿡷�", JOptionPane.ERROR_MESSAGE);
			return;
		}
		if(str.isEmpty()) {
			JOptionPane.showMessageDialog(bAdd, "�˻�� �Է��ϼ���.", "�ʼ��Է��׸񿡷�", JOptionPane.ERROR_MESSAGE);
			return;
		}
		switch (colN) {
		case 0 : mP.memTM.list = MemDao.getInstance().serchM("name", str); break;
		case 1 : mP.memTM.list = MemDao.getInstance().serchM("phone_num1 ||phone_num2 ||phone_num3", str); break;
		case 2 : mP.memTM.list = MemDao.getInstance().serchM("to_char(birth, 'yyyymmdd')", str); break;
		}
		mP.memTM.setDataVector(mP.memTM.list, mP.memTM.header);
		
	}


	public void addM() {
		mAdd.nameTF.setText(null);
		mAdd.nameTF.setEnabled(true);
		mAdd.ph1C.setSelectedItem(null);
		mAdd.ph2TF.setText(null);
		mAdd.ph3TF.setText(null);
		mAdd.adrTF.setText(null);
		mAdd.birth1.setText(null);
		mAdd.birth2.setText(null);
		mAdd.birth3.setText(null);
		mAdd.card.show(mAdd.cardP, "add");
		mAdd.setVisible(true);
	}


	public void mdfM() {
		int x = mP.memTM.memT.getSelectedRow();
		String mName = null;
		if(x > -1) {
			mName = mP.memTM.memT.getValueAt(x, 1).toString();
		}
		else {
			JOptionPane.showMessageDialog(mP, "������ ����� �����ϼ���.", "���ÿ���", JOptionPane.ERROR_MESSAGE);
			return;
		}
		memDto = MemDao.getInstance().findM(mName);
		mAdd.nameTF.setText(memDto.getName());
		mAdd.nameTF.setEnabled(false);
		mAdd.ph1C.setSelectedItem(memDto.getPhoneNum1());
		mAdd.ph2TF.setText(memDto.getPhoneNum2());
		mAdd.ph3TF.setText(memDto.getPhoneNum3());
		mAdd.adrTF.setText(memDto.getAddress());
		Format f = new SimpleDateFormat("yyyy-mm-dd");
		StringTokenizer birth = new StringTokenizer(f.format(memDto.getBirth()), "-");
		mAdd.birth1.setText(birth.nextToken());
		mAdd.birth2.setText(birth.nextToken());
		mAdd.birth3.setText(birth.nextToken());
		mAdd.card.show(mAdd.cardP, "mdf");
		mAdd.setVisible(true);
	}


	public void delM() {
		int x = mP.memTM.memT.getSelectedRow();
		String mID = null;
		String mName = null;
		if(x > -1) {
			StringTokenizer st = new StringTokenizer(mP.memTM.memT.getValueAt(x, 2).toString(), " - ");
			mName = mP.memTM.memT.getValueAt(x, 1).toString();
			st.nextToken();
			mID = mName.concat(st.nextToken()).concat(st.nextToken());
		}
		else {
			JOptionPane.showMessageDialog(mP, "������ ����� �����ϼ���.", "���ÿ���", JOptionPane.ERROR_MESSAGE);
			return;
		}
		int result = JOptionPane.showConfirmDialog(fcA, "�����Ͻðڽ��ϱ�?", "����Ȯ��", JOptionPane.YES_NO_OPTION);
		if(result == 0) {
			MemDao.getInstance().delM(mID);
			JOptionPane.showMessageDialog(mP, mName + ": ���� �Ϸ�Ǿ����ϴ�.", "�޴������Ϸ�", JOptionPane.INFORMATION_MESSAGE);
		}else if(result == 1) {
			mP.memTM.memT.clearSelection();
			return;
		}
		selMem();
	}

	public void amMem(String btn) {
		String name = mAdd.nameTF.getText().trim();
		if(name.equals("")) {
			JOptionPane.showMessageDialog(mAdd, "�̸��� �Է��ϼ���.", "�ʼ��Է��׸񿡷�", JOptionPane.ERROR_MESSAGE);
			return;
		} 
		
		String ph2 = mAdd.ph2TF.getText().trim();
		String ph3 = mAdd.ph3TF.getText().trim();
		if(mAdd.ph1C.getSelectedItem() == null || ph2.equals("") || ph3.equals("")) {
			JOptionPane.showMessageDialog(mAdd, "��ȭ��ȣ�� �Է��ϼ���.", "�ʼ��Է��׸񿡷�", JOptionPane.ERROR_MESSAGE);
			return;
		}
		if(ph2.length() > 4 || ph3.length() > 4) {
			JOptionPane.showMessageDialog(mAdd, "��ȭ��ȣ�� 4�ڸ� ���Ϸ� �Է��ϼ���.", "�ʼ��Է��׸񿡷�", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		String adr = mAdd.adrTF.getText().trim();
		if(adr.equals("")) {
			JOptionPane.showMessageDialog(mAdd, "�ּҸ� �Է��ϼ���.", "�ʼ��Է��׸񿡷�", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		String birth1 = mAdd.birth1.getText().trim();
		String birth2 = mAdd.birth1.getText().trim();
		String birth3 = mAdd.birth1.getText().trim();
		if(birth1.equals("") || birth2.equals("") || birth3.equals("")) {
			JOptionPane.showMessageDialog(mAdd, "��������� �Է��ϼ���.", "�ʼ��Է��׸񿡷�", JOptionPane.ERROR_MESSAGE);
			return;
		} 
		if(birth1.length() != 4 || birth2.length() != 2 || birth3.length() != 2) {
			JOptionPane.showMessageDialog(mAdd, "��������� ���Ŀ� �°� �Է��ϼ���.", "�ʼ��Է��׸񿡷�", JOptionPane.ERROR_MESSAGE);
			return;
		} 
		memDto = new MemberDto();
		memDto.setName(name);
		memDto.setPhoneNum1(mAdd.ph1C.getSelectedItem().toString());
		memDto.setPhoneNum2(ph2);
		memDto.setPhoneNum3(ph3);
		memDto.setAddress(adr);
		DateFormat df = new SimpleDateFormat("yyyy-mm-dd");
		memDto.setMemberId(memDto.getName() + memDto.getPhoneNum2() + memDto.getPhoneNum3()); 
		try {
			String birth = birth1 + "-" + birth2 + "-" + birth3;
			memDto.setBirth(df.parse(birth));//String�� util.data�� ��ȯ�ؼ� set
		} catch (ParseException e) {
			e.printStackTrace();
		}
		MemDao.getInstance().amM(memDto);		
		if(btn.equals("����")) {
			JOptionPane.showMessageDialog(bAdd, "ȸ�������� �����Ǿ����ϴ�.", "ȸ�������Ϸ�", JOptionPane.INFORMATION_MESSAGE);
		}
		else if(btn.equals("�߰�")) {
			JOptionPane.showMessageDialog(bAdd, "ȸ���� �߰��Ǿ����ϴ�.", "ȸ���߰��Ϸ�", JOptionPane.INFORMATION_MESSAGE);
		}
		mAdd.setVisible(false);
		selMem();
	}

	public void cancleMem() {
		JOptionPane.showMessageDialog(mAdd, "�۾��� ����Ͻðڽ��ϱ�?", "���Ȯ��", JOptionPane.INFORMATION_MESSAGE);
		mP.memTM.memT.clearSelection();
		mAdd.setVisible(false);
	}


	


}


