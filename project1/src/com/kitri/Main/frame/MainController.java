package com.kitri.Main.frame;

import java.awt.Color;
import java.awt.event.*;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;

import com.kitri.Main.dto.Basket;
import com.kitri.Main.swing.box.*;
import com.kitri.Main.voucher.VoucherDto;


public class MainController implements ActionListener, MouseListener {

	public MainFrame mf;
	public MainFrameService mfs;
	VoucherDto vou1 = new VoucherDto("1�ð� �̿��", 2500, 10);
	VoucherDto vou2 = new VoucherDto("2�ð� �̿��", 5000, 20);
	VoucherDto vou3 = new VoucherDto("3�ð� �̿��", 7500, 30);
	VoucherDto overTime = new VoucherDto("�ð��߰�", 500, 5);

	public MainController(MainFrame managerFrame) {

		this.mf = managerFrame;
		mfs = new MainFrameService(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object ob = e.getSource();
		String str = e.getActionCommand();
		String combo = mf.comboBoxVoucher.getSelectedItem().toString();
		if (ob == mf.tfPhoneNum || str.equals("ȸ���α���")) {
			memberLoginProcess();
		} else if (str.equals("��ȸ��")) {
			guestLoginProcess();
		} else if (str.equals("��������")) {//---------------------------------------------------------------------------------[h]��������btn >>> rentalPanel ���� �α��� �Ǿ����� ���� �Ѿ���� ���Ǽ���
			mf.rentalMain.setVisible(true);
		} else if (str.equals("ȸ�����")) {
			joinMemberProcess();
		} else if (str.equals("ȸ����������")) {
			editMemberProcess();
		} else if (str.equals("�����ֹ�")) {
			mf.serCard.show(mf.panelCard, "Food");//------------------------------------------------------------------------[h]�����ֹ�btn >>> FoodPanel ���� �α��� �Ǿ����� ���� �Ѿ���� ���Ǽ���
			
		} else if (str.equals("������")) {
			managerProcess();
		} else if (str.equals("����")) {
			paymentProcess();
		} else if (str.equals("Logout")) {
			mfs.logout();
		} else if (str.equals("-")) {
			buttonMinusProcess();
		} else if (ob == mf.buttonMcancel) {
			mf.tfManagerId.setText("");
			mf.panelManager.setVisible(false);
		} else if (str.equals("LogIn")) {
			managerLogInProcess();
		} else if (ob == mf.tfMemberID) {
			mf.tfPhoneNum.requestFocus();
		} else if (ob == mf.tfManagerId) {
			mf.buttonMlogin.requestFocus();
		}

		if (combo.equals("1�ð� �̿��")) {
			mfs.addVoucher(vou1);
		} else if (combo.equals("2�ð� �̿��")) {
			mfs.addVoucher(vou2);
		} else if (combo.equals("3�ð� �̿��")) {
			mfs.addVoucher(vou3);
		}
	}

	private void paymentProcess() {
		System.out.println("1");
	}
//		//���⿡ �����̲� ����Ʈ�� �ű�� �۾��� �ʿ��� 
//		FPanel f = mfs.findFp();
//		int size = f.voucherDtoBasket.size();
//		for (int i = 0; i < size; i++) {
//			�����̲�����Ʈ .add(f.voucherDtoBasket.get(i));
//		}
//		size = f.foodDtoBasket.size();
//		for (int i = 0; i < size; i++) {
//			�����̲�����Ʈ .add(f.foodDtoBasket.get(i));
//		}
//		size = f.bookDtoBasket.size();
//		for (int i = 0; i < size; i++) {
//			�����̲�����Ʈ .add(f.bookDtoBasket.get(i));
//		}
//		//�뿩����� ��� �ѱ� ���� ���� �̾߱��غ���

	private void managerLogInProcess() {
		char[] c = mf.tfManagerId.getPassword();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < c.length; i++) {
			sb.append(c[i]);
		}
		String str = String.valueOf(sb);
		int a;
		try {
			a = mf.dao.managerSelect(str);
			if (a > 0) {
				// ������ �������� ����
				mf.serCard.show(mf.panelCard, "Manager");
				mf.tfManagerId.setText("");
				mf.panelManager.setVisible(false);
			} else {
				JOptionPane.showMessageDialog(mf, "������ ��ȣ�� �ٸ��ϴ�. Ȯ�����ּ���.");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void managerProcess() {
		mfs.logout();
		mf.panelManager.setVisible(true);
	}

	private void buttonMinusProcess() {
		int a = mf.tablePayment.getSelectedRow();
		if (a == -1) {
			return;
		} else {
			Object ob = mf.tablePayment.getValueAt(a, 1);
			String str = String.valueOf(ob);
			FPanel f = mfs.findFp();
			switch (str) {
			case "�̿��":
				mfs.bMProcess(a, f, f.voucherDtoBasket);
				break;
			case "����":
				mfs.bMProcess(a, f, f.foodDtoBasket);
				break;
			case "å":
				mfs.bMProcess(a, f, f.bookDtoBasket);
				break;
			}
		}
	}

	private void memberLoginProcess() {
		try {
			String str = mf.tfMemberID.getText();
			String str1 = mf.tfPhoneNum.getText();
			mfs.login2(str, str1);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	private void guestLoginProcess() {
		NumberFormat nf = new DecimalFormat("00");
		int size = mf.vt.size();
		if (size == 0) {
			try {
				mfs.guestLogin("��ȸ��01");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			for (int i = 2; i < 31; i++) {
				String str = "��ȸ��" + (nf.format(i));
				if (mf.vt.indexOf(str) == -1) {
					try {
						mfs.guestLogin(str);
					} catch (SQLException e) {
						e.printStackTrace();
					}
					break;
				} else if (i == 31) {
					JOptionPane.showMessageDialog(mf, "���̻� ���̺��� �����ϴ�.");
				}
			}
		}
		mf.image.setIcon(mfs.imageConvert("Guest.png"));
	}

	private void joinMemberProcess() {
		mf.jmf.setVisible(true);
	}

	private void editMemberProcess() {
		if (!mf.labelLogInName.getText().isEmpty()) {
			mf.emf.labelName.setText(mf.labelLogInName.getText());
			mf.emf.labelBirth.setText(mf.labelLogInBirth.getText());
			mf.emf.setVisible(true);
		} else {
			JOptionPane.showMessageDialog(mf, "�α����� �������ּ���.");
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {

		LocalDateTime oldTime = LocalDateTime.now();
		if (e.getSource() instanceof FPanel) {
			FPanel fp = (FPanel) e.getSource();
			int i = Integer.parseInt(fp.getName());
			FPanel f = mf.fp[i];
			if (f.startTime.isEmpty()) {
				f.startTime = oldTime.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
			}
			if (!MainFrame.ID.isEmpty() && mf.fp[i].mid.isEmpty()) {
				if (mf.vt.indexOf(MainFrame.ID) == -1) {
					f.b = true;
					mf.vt.add(MainFrame.ID);
					f.mid = MainFrame.ID;
					f.setBackground(new Color(51, 153, 255));
					f.labelName.setText(mf.labelLogInName.getText());
					f.labelPrice.setText("0");

					f.th = new Thread(new Runnable() {

						@Override
						public void run() {
							while (f.b) {

								LocalDateTime ldt = LocalDateTime.now();
								f.a = Duration.between(oldTime, ldt).toMillis() / 1000;
								String str = mfs.secToHHMM(f.a);
								f.endTime = ldt.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));

								f.labelTime.setText(str);
								try {
									Thread.sleep(1000);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
							}
						}
					});
					f.th.start();
					mf.card.show(mf.panelMember, "LogIn");
					mfs.logout();

				} else {
					JOptionPane.showMessageDialog(mf, "�̹� �̿����� �մ��Դϴ�.");
					mfs.logout();
				}
			} else if (mf.labelLogInName.getText().isEmpty() && !f.labelName.getText().isEmpty()) {
				String str = f.mid;
				StringBuffer sb = new StringBuffer(str);
				mfs.logout();
				if (sb.indexOf("��ȸ��") == -1) {
					try {
						mfs.login(str);
						mfs.printTable(f);
					} catch (SQLException e1) {

						e1.printStackTrace();
					}

				} else {
					try {
						mfs.guestLogin(str);
						mfs.printTable(f);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}

			} else {
				return;
			}
		} else {
			return;
		}
		
	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
