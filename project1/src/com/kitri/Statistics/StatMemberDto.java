package com.kitri.Statistics;

import java.util.Date;

//**************************************************************

public class StatMemberDto {
	private String age;        //���̴�
	private String memberId;	//ȸ��ID
	private String name;		//�̸�
	private String birth;			//�������
	
	//�׷�����
	// �մ� ���ɺ� �׷���
	private String customerAge;
	private int customerAgeCount;

	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public Integer getStatTotalPrice() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public String getCustomerAge() {
		return customerAge;
	}
	public void setCustomerAge(String customerAge) {
		this.customerAge = customerAge;
	}
	public int getCustomerAgeCount() {
		return customerAgeCount;
	}
	public void setCustomerAgeCount(int customerAgeCount) {
		this.customerAgeCount = customerAgeCount;
	}

}
