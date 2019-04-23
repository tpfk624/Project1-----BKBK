package com.kitri.Book.rent;

import java.util.Vector;

import javax.swing.table.DefaultTableModel;

public class RentalService {
	RentalMain r;
	RentalController rc;

	public RentalService(RentalMain rentalMain) {
		r = rentalMain;
	}

	public void searchData(String searchStr) {
		Vector<BookDTO> list = r.dao.select(searchStr);

		Vector<Vector<String>> newList = new Vector<Vector<String>>();
		int len = list.size();
		for (int i = 0; i < len; i++) {
			Vector<String> vec = new Vector<String>();
			vec.add(list.get(i).getBookName());
			vec.add(list.get(i).getAuthor());
			vec.add(list.get(i).getPublisher());
			newList.add(vec);
		}

		Vector<String> colVec = new Vector<String>();

		for (int i = 0; i < r.col.size(); i++) {
			colVec.add(r.col.elementAt(i));
		}
		
		// 갱신하려면 model을 추가해야한다.
		DefaultTableModel model = new DefaultTableModel(newList, colVec);
		r.book.setModel(model);
	}
}