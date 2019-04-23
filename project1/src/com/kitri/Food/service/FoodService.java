package com.kitri.Food.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import com.kitri.Food.FoodController;
import com.kitri.Food.FoodPanel;
import com.kitri.Food.data.BasketDto;
import com.kitri.Food.data.FoodDto;
import com.kitri.Food.data.StockDto;

public class FoodService {

	public FoodController foodController;
	public FoodPanel ff;

	public FoodDto foodDto;
	public BasketDto basketDto;

	public FoodService(FoodController foodController) {
		this.foodController = foodController;
		ff = foodController.foodFrame;
		foodDto = new FoodDto();

	}

	public void plus() {
		int row = ff.listT.getSelectedRow();// select �� row�� index (0���� ����/�ƹ��͵� select�ȵǸ� -1)
		if (row == -1) {
//			System.out.println("+�� ����� �������ּ���!");
			JOptionPane.showMessageDialog(ff, "(+) �� ����� �������ּ���!", "select����", JOptionPane.ERROR_MESSAGE);
		} else {
			int sumprice = Integer.parseInt(ff.sumprice.getText()) + (int) ff.tM.getValueAt(row, 4);
			ff.sumprice.setText(sumprice + "");

			Object column = ff.tM.getValueAt(row, 3);// �ش� index�� row���� ����column (0���� ����)/////////row�� -1�̶��(�ƹ��͵� ����X)��
														// ����â
			ff.tM.setValueAt((int) column + 1, row, 3);// ���� �ٲ�
		}

	}

	public void minus() {
		int row = ff.listT.getSelectedRow();// select �� row�� index (0���� ����/�ƹ��͵� select�ȵǸ� -1)
		if (row == -1) {
//			System.out.println("-�� ����� �������ּ���!");
			JOptionPane.showMessageDialog(ff, "(-) �� ����� �������ּ���!", "select����", JOptionPane.ERROR_MESSAGE);
		} else {
			int sumprice = Integer.parseInt(ff.sumprice.getText()) - (int) ff.tM.getValueAt(row, 4);
			ff.sumprice.setText(sumprice + "");

			Object column = ff.tM.getValueAt(row, 3);// �ش� index�� row���� ����column (0���� ����)/////////row�� -1�̶��(�ƹ��͵� ����X)��
														// ����â
			if ((int) column > 1) {
				ff.tM.setValueAt((int) column - 1, row, 3);// ���� �ٲ�
			} else {
				ff.tM.removeRow(row); // ������ 1���� �۾����� list���� ����
				// no �ٲٱ�
				int rownum = ff.tM.getRowCount();
				for (int i = row; i < rownum; i++) {
					ff.tM.setValueAt(row + 1, i, 0);
					row++;
				}
//				
			}

		}

	}

	public void oneCancel() {
		int row = ff.listT.getSelectedRow();// ������ row select
		if (row == -1) {
			JOptionPane.showMessageDialog(ff, "������ ����� �������ּ���!", "select����", JOptionPane.ERROR_MESSAGE);
		} else {
			// sumprice ����
			// ����� ����� �Ѱ��� ����Xprice
			int minusprice = (int) ff.tM.getValueAt(row, 4) * (int) ff.tM.getValueAt(row, 3);
			int sumprice = Integer.parseInt(ff.sumprice.getText()) - minusprice;
			ff.sumprice.setText(sumprice + "");

			ff.tM.removeRow(row); /// �����Ҷ��� listT�� �ƴ� tM����... listT.remove(row)�ȵ� �Ф�

			// no �ٲٱ�
			int rownum = ff.tM.getRowCount();
			for (int i = row; i < rownum; i++) {
				ff.tM.setValueAt(row + 1, i, 0);
				row++;
			}
		}
	}

	public void totalCancel() {
		int rownum = ff.tM.getRowCount();
		for (int i = 0; i < rownum; i++) {
			ff.tM.removeRow(0);// �ȿ� i�� �ƴ� 0�� �־����. ���پ� ���� ������ row�� index�� �޶����� ����!
		}
		ff.sumprice.setText("0");// sumprice �ʱ�ȭ

	}

	public void cancelOrder() {
		// �׳� ��ȭ������ ���ư���~~
		ff.setVisible(false);// ���α׷��� �������� ����!!
		// table �ʱ�ȭ
		totalCancel();
	}

//	public void completeOrder(String memberid) {//�ֹ��ϴ� member_id ���;���.
//		//food_name , member_id , pay_num, order_amt,pay_date
//		//tM list�� �ִ� food_num�̶� order_amt(����) �о��
//		int rownum = ff.tM.getRowCount();//1���� ����
////		Ư�� pay_num���� Order_hstr ���̺� insert �ϱ�!
//		for(int i=0;i<rownum;i++) {
//			String food_name = (String)ff.tM.getValueAt(i, 2);
//			String pay_num = "100001";
//			int order_amt = (int)ff.tM.getValueAt(i, 3);
//			FoodDao.getInstance().insertOrder(food_name, memberid, pay_num, order_amt);
//		}
//
//		cancelOrder();
//	}

	public void completeOrder() {
		int rownum = ff.tM.getRowCount();
		for (int i = 0; i < rownum; i++) {
			String food_name = (String) ff.tM.getValueAt(i, 2);
			int order_amt = (int) ff.tM.getValueAt(i, 3);
			FoodDto food1 = FoodDao.getInstance().selectFood(food_name);
			BasketDto bk = new BasketDto(food1, order_amt);
//			foodController.foodFrame.mf.mc.mfs.addFood(bk);//-----------------------------------------------------------------------------------------------[h] ���������� ���� �ѱ�� �κ�
			//��� ����
			//��ŭ �����ϴ°�?
			food1.getStock1Name();
			//�����ϴ� ��
			int delstock1 = food1.getStock1Num()*order_amt;
			int delstock2 = food1.getStock2Num()*order_amt;
			int delstock3 = food1.getStock3Num()*order_amt;
			//���� ���
			StockDto stockDto1 = FoodDao.getInstance().getStock(food1.getStock1Name());
			FoodDao.getInstance().updateStock(stockDto1.getRestAmt()-delstock1,stockDto1.getStockName());
			if(food1.getStock2Name()!=null) {
				StockDto stockDto2 = FoodDao.getInstance().getStock(food1.getStock2Name());
				FoodDao.getInstance().updateStock(stockDto2.getRestAmt()-delstock2,stockDto2.getStockName());
			}
			if(food1.getStock3Name() !=null) {
			StockDto stockDto3 = FoodDao.getInstance().getStock(food1.getStock3Name());
			FoodDao.getInstance().updateStock(stockDto3.getRestAmt()-delstock3,stockDto3.getStockName());
			}
			
		}
		//��� ����!!!
		//����Ʈ�� foodname�� ���� food�� stocknum1,2,3��ŭ ���� ����� ����
		
		
		cancelOrder();
	}

//	public void icedrink() {
//		
//		int row = ff.listT.getSelectedRow();
//		if (row ==-1) {
//			JOptionPane.showMessageDialog(ff, "ice�� �� ���Ḧ �������ּ���!", "select����", JOptionPane.ERROR_MESSAGE);
//			ff.ice.setSelected(false);
//		} else {
//			String icedrink = (String)ff.tM.getValueAt(row, 2);
//			boolean flag = icedrink.endsWith("(I)");
////			System.out.println(flag);
//			if(flag ==false) {
//				ff.tM.setValueAt(icedrink+"(I)", row, 2);
//				
//			} else {
//				int leng = icedrink.length();
//				String orgin = icedrink.substring(0, leng-3);
//				ff.tM.setValueAt(orgin, row, 2);
//			}
//			ff.ice.setSelected(false);
//		}
//	}

	public void foodProccess(String btname) {// food�޴� ��ư ������ ��
		// db���� bt�� �̸��� ���� row�� ���� ������
		FoodDto food1 = FoodDao.getInstance().selectFood(btname);

		if (stockProccess(food1) == true) {// ���� return�� true�� : ����ִ�
			int count = ff.tM.getRowCount();

			// �޾ƿ� ������ Vector�� �����
			Vector row = new Vector();
			row.addElement(count + 1);
			row.addElement(food1.getFoodCtg());
			row.addElement(food1.getFoodName());
			row.addElement(1);// �ֹ�����
			row.addElement(food1.getFoodPrice());
			// �ѱݾ� sumprice ���ϱ�
			int price = food1.getFoodPrice();
			int sumprice = price + Integer.parseInt(ff.sumprice.getText());
			ff.sumprice.setText(sumprice + "");

			boolean flag = false;
			if (count == 0) {
				ff.tM.addRow(row);
			} else {
				for (int i = 0; i < count; i++) {

					String foodname = (String) ff.tM.getValueAt(i, 2);// ���� list�� �ִ� FoodName
					if (foodname.equals(btname)) {// ���� list(i��°)�� ������ư�� �ִٸ�?!
						// �����ø���
						// �̸��� ��ư���̸��� ���� row indexã�� : i
						// �� row�� ����column�� ã�� getValueAt(row , 3)
						Object foodnum = ff.tM.getValueAt(i, 3);// ����
						// ����column�� +1�ϱ� setValueAt((int)column+1, row, 3)
						ff.tM.setValueAt((int) foodnum + 1, i, 3);
						flag = false;
						break;
					} else {// ���ο� �޴����
						flag = true;
					}
				}
			}

			if (flag == true) {
				ff.tM.addRow(row);// Jtable�� �߰�
			}
		} else {
			JOptionPane.showMessageDialog(ff, "��� �����ϴ�!", "���X", JOptionPane.NO_OPTION);
		}

	}

	private boolean stockProccess(FoodDto food1) {
		boolean flag = false;
		StockDto stock1 = FoodDao.getInstance().getStock(food1.getStock1Name());
		
		if (food1.getStock2Name() != null) {//���2�� ���� ��
			StockDto stock2 = FoodDao.getInstance().getStock(food1.getStock2Name());
			if(food1.getStock3Name() !=null) {//���1,2,3�� ������
				StockDto stock3 = FoodDao.getInstance().getStock(food1.getStock3Name());
				if(stock1.getRestAmt() >= food1.getStock1Num() && stock2.getRestAmt() >= food1.getStock2Num()
				&& stock3.getRestAmt() >= food1.getStock3Num()) {
					flag = true;
				} else {
					flag = false;
				}
			} else {//���1,2�� ���� ��
				if (stock1.getRestAmt() >= food1.getStock1Num() && stock2.getRestAmt() >= food1.getStock2Num()) {
					flag = true;
				} else {
					flag = false;
				}
			}
		} else {//���1�� ���� ��
			if (stock1.getRestAmt() >= food1.getStock1Num()) {//���1�� �������Ҷ�
				flag = true;
			} else {
				flag = false;
			}
		}
	
		return flag;
	}

}
