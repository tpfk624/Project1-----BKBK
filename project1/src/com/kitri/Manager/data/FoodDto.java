package com.kitri.Manager.data;

// ���� Dto

public class FoodDto {
	private String foodName; // �����̸�
	private String foodCtg; // ��������
	private int foodPrice; // ���İ���
	private String stock1Name; // ���1
	private int stock1Num; // ���1����
	private String stock2Name; // ���2
	private int stock2Num; // ���2����
	private String stock3Name; // ���3
	private int stock3Num; // ���3����
	private char state; // ����

	public String getFoodName() {
		return foodName;
	}

	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}

	public String getFoodCtg() {
		return foodCtg;
	}

	public void setFoodCtg(String foodCtg) {
		this.foodCtg = foodCtg;
	}

	public int getFoodPrice() {
		return foodPrice;
	}

	public void setFoodPrice(int foodPrice) {
		this.foodPrice = foodPrice;
	}

	public String getStock1Name() {
		return stock1Name;
	}

	public void setStock1Name(String stock1Name) {
		this.stock1Name = stock1Name;
	}

	public int getStock1Num() {
		return stock1Num;
	}

	public void setStock1Num(int stock1Num) {
		this.stock1Num = stock1Num;
	}

	public String getStock2Name() {
		return stock2Name;
	}

	public void setStock2Name(String stock2Name) {
		this.stock2Name = stock2Name;
	}

	public int getStock2Num() {
		return stock2Num;
	}

	public void setStock2Num(int stock2Num) {
		this.stock2Num = stock2Num;
	}

	public String getStock3Name() {
		return stock3Name;
	}

	public void setStock3Name(String stock3Name) {
		this.stock3Name = stock3Name;
	}

	public int getStock3Num() {
		return stock3Num;
	}

	public void setStock3Num(int stock3Num) {
		this.stock3Num = stock3Num;
	}

	public char getState() {
		return state;
	}

	public void setState(char state) {
		this.state = state;
	}

}
