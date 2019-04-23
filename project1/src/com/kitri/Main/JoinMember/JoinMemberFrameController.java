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
		if (str.equals("회원등록")) {
//			JOptionPane.showConfirmDialog(jmf, "정보들이 맞습니까?", "확인", JOptionPane.OK_CANCEL_OPTION);
			if (jmfs.isNumber(jmf.tfBirth.getText()) == false || jmfs.isNumber(jmf.tfPhoneNum1.getText()) == false
					|| jmfs.isNumber(jmf.tfPhoneNum2.getText()) == false || jmfs.isNumber(jmf.tfPhoneNum3.getText()) == false) {
				JOptionPane.showMessageDialog(null, "생년월일과, 전화번호에는 숫자만 입력가능합니다.");
			} else {
				
				int a = jmf.mf.dao.insertMember(jmf);
				if (a > 0) {
					JOptionPane.showMessageDialog(jmf, "회원등록 성공!!!");
					jmfs.clear();
					jmf.setVisible(false);
					
				} else {
					JOptionPane.showMessageDialog(jmf, "회원등록 실패...");
				}
			}
		} else if (str.equals("취소")) {
			jmfs.clear();
			jmf.dispose();
		}

	}

}
