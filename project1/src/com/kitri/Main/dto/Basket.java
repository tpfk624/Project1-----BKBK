package com.kitri.Main.dto;

import com.kitri.Main.voucher.VoucherDto;

public class Basket {
	
	private VoucherDto vdto;
//	public FoodDto fdto;
//	public BookDto bdto;
	public int count;
	private String name;
	
	public Basket(VoucherDto vdto, int count) {
		super();
		this.vdto = vdto;
		this.count = count;
		this.name = vdto.toString();
	}
//	public Basket(FoodDto fdto, int count) {
//		super();
//		this.dto = dto;
//		this.count = count;
//		this.name = dto.toString();
//	}
//	public Basket(BookDto bdto, int count) {
//		super();
//		this.dto = dto;
//		this.count = count;
//		this.name = dto.toString();
//	}
	
	
	
	public VoucherDto getVoucherDto() {
		return vdto;
	}



	public void setVoucherDto(VoucherDto vdto) {
		this.vdto = vdto;
	}

//	public FoodDto getFoodDto() {
//		return fdto;
//	}
//
//
//
//	public void setFoodDto(FoodDto fdto) {
//		this.fdto = fdto;
//	}
//
//
//
//	public BookDto getBookDto() {
//		return bdto;
//	}
//
//
//
//	public void setBookDto(BookDto bdto) {
//		this.bdto = bdto;
//	}
	

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
