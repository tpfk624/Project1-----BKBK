package com.kitri.Food.data;

import java.util.Date;

// �������� Dto

public class RentHstrDto {

	private int bookNum; // �����ڵ�
	private String memberId; // ȸ��ID
	private String payNum; // ������ȣ
	private Date rentDate; // ��������
	private Date returnDate; // �ݳ�������
	private char rentState; // �ݳ�����
	private int rentFee; // �뿩��
	private Date payDate; // ��������

	public int getBookNum() {
		return bookNum;
	}

	public void setBookNum(int bookNum) {
		this.bookNum = bookNum;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getPayNum() {
		return payNum;
	}

	public void setPayNum(String payNum) {
		this.payNum = payNum;
	}

	public Date getRentDate() {
		return rentDate;
	}

	public void setRentDate(Date rentDate) {
		this.rentDate = rentDate;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	public char getRentState() {
		return rentState;
	}

	public void setRentState(char rentState) {
		this.rentState = rentState;
	}

	public int getRentFee() {
		return rentFee;
	}

	public void setRentFee(int rentFee) {
		this.rentFee = rentFee;
	}

	public Date getPayDate() {
		return payDate;
	}

	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}

}
