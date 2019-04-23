package com.kitri.Main.EditMember;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import com.kitri.Main.frame.*;
import com.kitri.Main.memberDto.*;
public class EditMemberFrameController implements ActionListener {
	EditMemberFrame emf;
	EditMemberFrameService emfs;
	
	public EditMemberFrameController(EditMemberFrame emf) {
		this.emf = emf;
		emfs = new EditMemberFrameService(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		String str = e.getActionCommand();
		if (str.equals("회원정보수정")) {
				int a = emf.mf.dao.editMember(emf);
				if (a > 0) {
					int len = emf.mf.fp.length;
					for (int i = 0; i < len; i++) {
					
						System.out.println(MainFrame.ID);
						if (emf.mf.fp[i].mid.equals(MainFrame.ID)) {
							emf.mf.fp[i].mid = emf.mf.dao.newID;
							emf.mf.vt.remove(emf.mf.fp[i].mid);
							emf.mf.vt.add(emf.mf.dao.newID);
							break;
						}
					}
					JOptionPane.showMessageDialog(emf, "회원정보수정 성공!!!");
					emf.mf.mc.mfs.logout();
					emf.emfc.emfs.clear();
					emf.setVisible(false);
				} else {
					JOptionPane.showMessageDialog(emf, "회원정보수정 실패...");
				}
		}else if (str.equals("취소")) {
			emf.mf.mc.mfs.logout();
			emf.emfc.emfs.clear();
			emf.setVisible(false);
				
		}
		
	}

}
