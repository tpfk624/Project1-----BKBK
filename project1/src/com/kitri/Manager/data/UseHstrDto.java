package com.kitri.Manager.data;

import java.util.Date;

// �̿볻�� Dto

public class UseHstrDto {

	private String memberId; // ȸ��ID
	private String payNum; // ������ȣ
	private Date stTime; // ���۽ð�
	private Date eTime; // ����ð�
	private Date payDate; // ��������

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

	public Date getStTime() {
		return stTime;
	}

	public void setStTime(Date stTime) {
		this.stTime = stTime;
	}

	public Date geteTime() {
		return eTime;
	}

	public void seteTime(Date eTime) {
		this.eTime = eTime;
	}

	public Date getPayDate() {
		return payDate;
	}

	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}

}
