package com.kitri.Main.memberDto;
/*ȸ��ID	Member_ID
�̸�	Name
��ȭ��ȣ1	Phone_num1
��ȭ��ȣ2	Phone_num2
��ȭ��ȣ3	Phone_num3
�ּ�	Address
�������	Birth
�����ݾ�_����	Pay_sum
��������	Cou_birth
õ����������	Cou_sale

 * ID Member_ID PK �̸�+��ȭ��ȣ ������ 4�ڸ� �̸� Name ��ȭ��ȣ Phone_num �ּ� Address ������� Birth �����ݾ�_���� Pay_sum �������� Cou_birth õ���������� Cou_sale 
 */
public class MemberDto {
	private String Member_ID = "";
	private String Name = "";
	private String Phone_Num1 = "";
	private String Phone_Num2="";
	private String Phone_Num3="";
	private String address="";
	String Birth="";
	int Pay_Sum=0;//�������
	int Cou_Birth=0;//��������
	int Cou_Sale=0;// õ����������
	

	
		
	public MemberDto(String name, String phone_Num1, String phone_Num2, String phone_Num3, String address,
			String birth, int pay_Sum, int cou_Birth, int cou_Sale) {
		super();
		Member_ID = "";
		Name = name;
		Phone_Num1 = phone_Num1;
		Phone_Num2 = phone_Num2;
		Phone_Num3 = phone_Num3;
		this.address = address;
		Birth = birth;
		Pay_Sum = pay_Sum;
		Cou_Birth = cou_Birth;
		Cou_Sale = cou_Sale;
	}




	public String getMember_ID() {
		return Member_ID;
	}

	public void setMember_ID(String member_ID) {
		Member_ID = member_ID;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getPhone_Num1() {
		return Phone_Num1;
	}

	public void setPhone_Num1(String phone_Num1) {
		Phone_Num1 = phone_Num1;
	}

	public String getPhone_Num2() {
		return Phone_Num2;
	}

	public void setPhone_Num2(String phone_Num2) {
		Phone_Num2 = phone_Num2;
	}

	public String getPhone_Num3() {
		return Phone_Num3;
	}

	public void setPhone_Num3(String phone_Num3) {
		Phone_Num3 = phone_Num3;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getBirth() {
		return Birth;
	}

	public void setBirth(String birth) {
		Birth = birth;
	}

	public int getPay_Sum() {
		return Pay_Sum;
	}

	public void setPay_Sum(int pay_Sum) {
		Pay_Sum = pay_Sum;
	}

	public int getCou_Birth() {
		return Cou_Birth;
	}

	public void setCou_Birth(int cou_Birth) {
		Cou_Birth = cou_Birth;
	}

	public int getCou_Sale() {
		return Cou_Sale;
	}

	public void setCou_Sale(int cou_Sale) {
		Cou_Sale = cou_Sale;
	}
	
	
}
