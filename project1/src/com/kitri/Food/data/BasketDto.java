package com.kitri.Food.data;

public class BasketDto {
	public Object dto;
	public int count;
	private String name;
	
	
	public BasketDto(Object dto, int count) {
		super();
		this.dto = dto;//���� ����
		this.count = count;//����
		this.name = dto.toString();
	}
	
	
	public Object getDto() {
		return dto;
	}
	public void setDto(Object dto) {
		this.dto = dto;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	@Override
	public String toString() {
		return name;
	}
}
