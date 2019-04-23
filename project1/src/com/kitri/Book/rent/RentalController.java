package com.kitri.Book.rent;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JOptionPane;

public class RentalController implements ActionListener {
   
   RentalMain rm;
   RentalService rs;

   public RentalController(RentalMain r) {
      this.rm = r;
      rs = new RentalService(rm);
   }

   @Override
   public void actionPerformed(ActionEvent e) {
      Object ob = e.getSource();
      
      if(ob == rm.search) { 
         if (rm.searchTF.getText().trim().equals("")) 
            JOptionPane.showMessageDialog(null, "[�˸�] �˻�� �Է��ϼ���.");
         else if(ob == rm.search) {// �˻�� �Է��������
            String searchStr = rm.searchTF.getText().trim();// ã�� ����
             rs.searchData(searchStr);
             rm.searchTF.setText("");
              } 
         
         } else if(ob == rm.change) { // db�� ��ü��� 0���� 1�� change
            JOptionPane.showMessageDialog(null, "[�˸�] �ش� å�� ��ü��� ó���Ǿ����ϴ�.");
            
         } else if(ob == rm.move) {
        	 
        	 int selected = rm.book.getSelectedRow();
        	 System.out.println(selected);
        	 String thing;
        	Vector<BookDTO> list = new Vector<BookDTO>();
//        	list.get(0);
 //           String i = rows.addElement(list.get(0).getBookName());
  //          rm.bookL.add(comp);
  //      	 rm.bookL.add(rm, rm.book.getSelectedRow());
        	 
//--------------------------------------------------------------------------------------------------------------------------[h] �����̰� basket �̺�Ʈ ����� rentalMain        	 
         }
   	}
}