package com.kitri.Main.JoinMember;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class JoinMemberFrameController implements ActionListener {

	JoinMemberFrame jmf;
	JoinMemberFrameService jmfs;

	public JoinMemberFrameController(JoinMemberFrame jmf) {
		this.jmf = jmf;
		jmfs = new JoinMemberFrameService(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String str = e.getActionCommand();
		if (str.equals("ȸ�����")) {
//			JOptionPane.showConfirmDialog(jmf, "�������� �½��ϱ�?", "Ȯ��", JOptionPane.OK_CANCEL_OPTION);
			if (jmfs.isNumber(jmf.tfBirth.getText()) == false || jmfs.isNumber(jmf.tfPhoneNum1.getText()) == false
					|| jmfs.isNumber(jmf.tfPhoneNum2.getText()) == false || jmfs.isNumber(jmf.tfPhoneNum3.getText()) == false) {
				JOptionPane.showMessageDialog(null, "������ϰ�, ��ȭ��ȣ���� ���ڸ� �Է°����մϴ�.");
			} else {
				
				int a = jmf.mf.dao.insertMember(jmf);
				if (a > 0) {
					JOptionPane.showMessageDialog(jmf, "ȸ����� ����!!!");
					jmfs.clear();
					jmf.setVisible(false);
					
				} else {
					JOptionPane.showMessageDialog(jmf, "ȸ����� ����...");
				}
			}
		} else if (str.equals("���")) {
			jmfs.clear();
			jmf.dispose();
		}

	}

}
