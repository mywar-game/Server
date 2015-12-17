package com.adminTool.bo;

import java.io.Serializable;

public class SystemMallDiscount implements Serializable{

	private static final long serialVersionUID = 1226622891645293617L;

	private Integer mallId;
	private String name;
	private Integer originalPrice;
	private Integer discount;
	private Double discountPrice;
	
	private int amountType; // 1钻石 2金币
	

	public SystemMallDiscount() {
		
	}

	public SystemMallDiscount(int mallId, String name, int originalPrice,
			int discount, double discountPrice, int isChecked) {
		super();
		this.mallId = mallId;
		this.name = name;
		this.originalPrice = originalPrice;
		this.discount = discount;
		this.discountPrice = discountPrice;
	}

	public Integer getMallId() {
		return mallId;
	}

	public void setMallId(Integer mallId) {
		this.mallId = mallId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getOriginalPrice() {
		return originalPrice;
	}

	public void setOriginalPrice(Integer originalPrice) {
		this.originalPrice = originalPrice;
	}

	public Integer getDiscount() {
		return discount;
	}

	public void setDiscount(Integer discount) {
		this.discount = discount;
	}

	public Double getDiscountPrice() {
		return discountPrice;
	}

	public void setDiscountPrice(Double discountPrice) {
		this.discountPrice = discountPrice;
	}

	public int getAmountType() {
		return amountType;
	}

	public void setAmountType(int amountType) {
		this.amountType = amountType;
	}
}
