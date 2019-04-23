package com.kitri.Main.frame;

import java.awt.*;
import java.sql.SQLException;
import java.text.*;
import java.util.*;
import java.util.List;

import javax.swing.*;

import com.kitri.Main.dto.Basket;
import com.kitri.Main.memberDto.MemberDto;
import com.kitri.Main.swing.box.FPanel;
import com.kitri.Main.voucher.VoucherDto;

public class MainFrameService {

	MainController mc;

	public MainFrameService(MainController mc) {
		this.mc = mc;
	}

	public void paymentComplete() {
		FPanel f = findFp();
		f.bookDtoBasket.clear();
		f.foodDtoBasket.clear();
		f.voucherDtoBasket.clear();
		f.endTime = "";
		f.startTime = "";
		f.mid = "";
		f.b = false;
		f.labelName.setText("");
		f.labelTime.setText("");
		f.labelPrice.setText("");
		f.setBackground(new Color(245, 245, 245));
		mc.mf.vt.remove(MainFrame.ID);
		logout();
	}

	public ImageIcon imageConvert(String fileName) {
//		ImageIcon icon = new ImageIcon("C:\\Users\\Administrator\\Pictures\\memberIcon\\" + fileName);
		ImageIcon icon = new ImageIcon("C:\\Users\\�̼���\\Pictures\\memberIcon" + fileName);
		ImageIcon editIcon = new ImageIcon(icon.getImage().getScaledInstance(85, 79, Image.SCALE_SMOOTH));
		return editIcon;
	}

	public void currentTime() {
		Date d = new Date();
		Format f = new SimpleDateFormat("HH:mm:ss");
		mc.mf.time = f.format(d);
		mc.mf.getTimeL().setText(mc.mf.time);
	}

	public void currentDate() {
		Date d = new Date();
		Format f = new SimpleDateFormat("yyyy/MM/dd");
		mc.mf.date = f.format(d);
		mc.mf.getDateL().setText(mc.mf.date);
	}

	public void login(String str) throws SQLException {// �ڸ������� �α����Ҷ�

		MemberDto dto = mc.mf.dao.selectMemberId(str);
		if (dto != null) {

			MainFrame.ID = dto.getMember_ID();
			mc.mf.labelLogInName.setText(dto.getName());
			mc.mf.labelLogInBirth.setText(dto.getBirth());
			mc.mf.labelCouponNum1.setText(String.valueOf((dto.getCou_Birth())));
			mc.mf.labelCouponNum2.setText(String.valueOf((dto.getCou_Sale())));
			mc.mf.card.show(mc.mf.panelMember, "ȸ���α���");
			mc.mf.tfMemberID.setText("");
			mc.mf.tablePayment.setVisible(true);
		} else {
			JOptionPane.showMessageDialog(null, "ȸ���� �������� �ʽ��ϴ�. ȸ�����̵� Ȯ�����ּ���.");
		}

	}

	public void login2(String name, String phoneNum) throws SQLException {// �̸� ��ȭ��ȣ �ļ� �α����Ҷ�

		MemberDto dto = mc.mf.dao.select(name, phoneNum);
		if (dto != null) {
			MainFrame.ID = dto.getMember_ID();
			mc.mf.labelLogInName.setText(dto.getName());
			mc.mf.labelLogInBirth.setText(dto.getBirth());
			mc.mf.labelCouponNum1.setText(String.valueOf((dto.getCou_Birth())));
			mc.mf.labelCouponNum2.setText(String.valueOf((dto.getCou_Sale())));
			mc.mf.card.show(mc.mf.panelMember, "ȸ���α���");
			mc.mf.tfMemberID.setText("");
			mc.mf.tablePayment.setVisible(true);
		} else {
			JOptionPane.showMessageDialog(null, "ȸ���� �������� �ʽ��ϴ�. ȸ�����̵� Ȯ�����ּ���.");
		}

	}

	public void guestLogin(String str) throws SQLException {

		MemberDto dto = mc.mf.dao.guestSelect(str);
		if (dto != null) {
			MainFrame.ID = dto.getMember_ID();
			mc.mf.labelLogInName.setText(dto.getName());
			mc.mf.labelLogInBirth.setText(dto.getBirth());
			mc.mf.labelCouponNum1.setText(String.valueOf((dto.getCou_Birth())));
			mc.mf.labelCouponNum2.setText(String.valueOf((dto.getCou_Sale())));
			mc.mf.card.show(mc.mf.panelMember, "ȸ���α���");
			mc.mf.tfMemberID.setText("");
			mc.mf.tablePayment.setVisible(true);
		} else {
			JOptionPane.showMessageDialog(null, "ȸ���� �������� �ʽ��ϴ�. ȸ�����̵� Ȯ�����ּ���.");
		}

	}

	public void logout() {
		mc.mf.labelLogInName.setText("");
		mc.mf.labelLogInBirth.setText("");
		mc.mf.labelCouponNum1.setText("");
		mc.mf.labelCouponNum2.setText("");
		MainFrame.ID = "";
		mc.mf.tfPhoneNum.setText("");
		mc.mf.fullPrice.setText("");
		mc.mf.pay.setNumRows(0);
		mc.mf.book.setNumRows(0);
		mc.mf.card.show(mc.mf.panelMember, "LogIn");
	}

	public boolean isNumber(String a) {
		boolean flag = true;
		for (int i = 1; i < a.length(); i++) {
			int num = a.charAt(i) - 48;
			if (num < 0 || num > 9) {
				flag = false;
				break;
			}
		}
		return flag;
	}

	public String secToHHMM(long secs) {
		int hour, min, sec;
		sec = ((int) secs % 60) % 60;
		min = (int) secs / 60 % 60;
		hour = (int) secs / 3600;
		String timerBuffer = String.format("%02d:%02d:%02d", hour, min, sec);
		return timerBuffer;
	}

	public FPanel findFp() {// �ڸ� ã�� �뵵
		FPanel f = null;
		int len = mc.mf.fp.length;
		for (int i = 0; i < len; i++) {
			if (mc.mf.fp[i].mid.equals(MainFrame.ID)) {
				f = mc.mf.fp[i];
				break;
			}
		}
		return f;
	}

	public void printTable(FPanel f) {
		mc.mf.pay.setNumRows(0);
		mc.mf.book.setNumRows(0);
		for (int k = 0; k < f.voucherDtoBasket.size(); k++) {
			Basket bvd = f.voucherDtoBasket.get(k);
			Object[] ob = { k + 1, "�̿��", bvd.getVoucherDto().getName(), bvd.getCount(),
					(bvd.getVoucherDto()).getPrice() * (bvd.getCount()) };
			mc.mf.pay.addRow(ob);

		}
//		for (int k = 0; k < f.foodDtoBasket.size(); k++) {
//			Basket fd = f.foodDtoBasket.get(k);
//			Object[] ob = { k + 1, "����", fd.getVoucherDto().getName(),
//					fd.getCount(),
//					(fd.getVoucherDto()).getPrice() * (fd.getCount()) };
//			mc.mf.pay.addRow(ob);
//
//		}
//		for (int k = 0; k < f.bookDtoBasket.size(); k++) {
//			Basket bd = f.bookDtoBasket.get(k);
//			Object[] ob = { k + 1, "å", bd.getVoucherDto().getName(),
//					bd.getCount(),
//					(bd.getVoucherDto()).getPrice() * (bd.getCount()) };
//			mc.mf.pay.addRow(ob);
//
//		}

		int total = 0;
		for (int j = 0; j < mc.mf.tablePayment.getRowCount(); j++) {
			int amont = Integer.parseInt(String.valueOf(mc.mf.tablePayment.getValueAt(j, 4)));
			total = total + amont;
		}
		f.labelPrice.setText(String.valueOf(total));
		mc.mf.fullPrice.setText(String.valueOf(total));
		mc.mf.dao.rentBook(MainFrame.ID);
		overTime();

	}

	public void overTime() {
		FPanel f = findFp();
		int totalpaymentTime = 0;
		int paymentTime = 0;
		int len = f.voucherDtoBasket.size();
		for (int i = 0; i < len; i++) {
			if (f.voucherDtoBasket.get(i).getVoucherDto().getName().equals("�ð��߰�")) {
				f.voucherDtoBasket.remove(i);
				break;
			}
			Basket b = f.voucherDtoBasket.get(i);
			paymentTime = (b.getVoucherDto().getSec() * b.count);
			totalpaymentTime = totalpaymentTime + paymentTime;
		}
		int i = (int) f.a - totalpaymentTime;
		if (i > 0) {
			Basket b = new Basket(mc.overTime, i / (mc.overTime.getSec()));
			f.voucherDtoBasket.add(b);
		} else {
			return;
		}

	}

	public void addVoucher(VoucherDto vd) {
		mc.mf.pay.setNumRows(0);
		FPanel f = findFp();
		if (MainFrame.ID==null) {
			JOptionPane.showMessageDialog(mc.mf, "�α��� ���� ���ּ���");
		} else {
			int len2 = f.voucherDtoBasket.size();
			if (len2 == 0) {
				Basket basket = new Basket(vd, 1);
				f.voucherDtoBasket.add(basket);
				printTable(f);
			} else {
				for (int j = 0; j < len2; j++) {
					Basket b = f.voucherDtoBasket.get(j);
					if (b.getVoucherDto().getName().equals(vd.getName())) {
						b.setCount(b.getCount() + 1);
						mc.mf.pay.setNumRows(0);
						printTable(f);
						break;
					} else if (j == len2 - 1) {
						Basket basket = new Basket(vd, 1);
						f.voucherDtoBasket.add(basket);
						printTable(f);
					}
				}
			}
		}
		mc.mf.comboBoxVoucher.setSelectedIndex(0);

	}

//	public void addFood(basket fd) {
//	FPanel f = findFp();
//	int len2 = f.foodDtoBasket.size();
//	if (len2 == 0) {
//		f.foodDtoBasket.add(fd);
//		printTable(f);
//	} else {
//		for (int j = 0; j < len2; j++) {
//			Basket b = f.foodDtoBasket.get(j);
//			if (b.getFoodDto().getName().equals(fd.getName())) {
//				b.setCount(b.getCount() + 1);
//				mc.mf.pay.setNumRows(0);
//				printTable(f);
//				break;
//			} else if (j == len2 - 1) {
//				f.foodDtoBasket.add(fd);
//				printTable(f);
//			}
//		}
//	}
////}
//	public void addBook(basket bd) {
//		FPanel f = findFp();
//		int len2 = f.bookDtoBasket.size();
//		if (len2 == 0) {
//			f.bookDtoBasket.add(bd);
//			printTable(f);
//		} else {
//			for (int j = 0; j < len2; j++) {
//				Basket b = f.bookDtoBasket.get(j);
//				if (b.getbookDto().getName().equals(bd.getName())) {
//					b.setCount(b.getCount() + 1);
//					mc.mf.pay.setNumRows(0);
//					printTable(f);
//					break;
//				} else if (j == len2 - 1) {
//					f.booktoBasket.add(bd);
//					printTable(f);
//				}
//			}
//		}
//	}

//	public void addFood(FoodDto fd) {
//		FPanel f = findFp();
//		int len2 = f.foodDtoBasket.size();
//		if (len2 == 0) {
//			Basket basket = new Basket(fd, 1);
//			f.foodDtoBasket.add(basket);
//			printTable(f);
//		} else {
//			for (int j = 0; j < len2; j++) {
//				Basket b = f.foodDtoBasket.get(j);
//				if (b.getVoucherDto().getName().equals(fd.getName())) {
//					b.setCount(b.getCount() + 1);
//					mc.mf.pay.setNumRows(0);
//					printTable(f);
//					break;
//				} else if (j == len2 - 1) {
//					Basket basket = new Basket(fd, 1);
//					f.foodDtoBasket.add(basket);
//					printTable(f);
//				}
//			}
//		}
//	}
//	
//	public void addBook(BookDto bd) {
//		FPanel f = findFp();
//		int len2 = f.bookDtoBasket.size();
//		if (len2 == 0) {
//			Basket basket = new Basket(bd, 1);
//			f.bookDtoBasket.add(basket);
//			printTable(f);
//		} else {
//			for (int j = 0; j < len2; j++) {
//				Basket b = f.foodDtoBasket.get(j);
//				if (b.getbookDto().getName().equals(bd.getName())) {
//					b.setCount(b.getCount() + 1);
//					mc.mf.pay.setNumRows(0);
//					printTable(f);
//					break;
//				} else if (j == len2 - 1) {
//					Basket basket = new Basket(bd, 1);
//					f.bookDtoBasket.add(basket);
//					printTable(f);
//				}
//			}
//		}
//	}
	public void bMProcess(int a, FPanel f, List<Basket> b) {
		Object ob1 = mc.mf.tablePayment.getValueAt(a, 2);
		String str1 = String.valueOf(ob1);
		Object ob2 = mc.mf.tablePayment.getValueAt(a, 3);
		int t = Integer.parseInt(String.valueOf(ob2));
		int len = b.size();
		if (t == 1) {
			for (int i = 0; i < len; i++) {
				if (b.get(i).getVoucherDto().toString().equals(str1)) {
					b.remove(i);
					printTable(f);
					break;
				}
			}

		} else {
			for (int i = 0; i < len; i++) {
				if (b.get(i).getVoucherDto().toString().equals(str1)) {
					Basket basket = b.get(i);
					int c = basket.getCount();
					basket.setCount(c - 1);
					printTable(f);
					break;

				}
			}
		}
	}

}
