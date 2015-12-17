package com.adminTool.bo;

import java.io.Serializable;

public class MallDiscountItem implements Serializable{

	private Integer id;
	private String activityId;
	private Integer mallId;
	private String name;
	private Integer originalPrice;
	private Integer discount;
	private Double discountPrice;
	private Integer isChecked;
	
	public MallDiscountItem() {
		
	}

	public MallDiscountItem(String activityId, Integer mallId, String name, Integer originalPrice,
			Integer discount, Double discountPrice, Integer isChecked) {
		super();
		this.activityId = activityId;
		this.mallId = mallId;
		this.name = name;
		this.originalPrice = originalPrice;
		this.discount = discount;
		this.discountPrice = discountPrice;
		this.isChecked = isChecked;
	}

	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getActivityId() {
		return activityId;
	}

	public void setActivityId(String activityId) {
		this.activityId = activityId;
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

	public Integer getIsChecked() {
		return isChecked;
	}

	public void setIsChecked(Integer isChecked) {
		this.isChecked = isChecked;
	}
	
}
