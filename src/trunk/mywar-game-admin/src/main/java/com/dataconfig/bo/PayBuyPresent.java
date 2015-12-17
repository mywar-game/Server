package com.dataconfig.bo;

/**
 * PPayBuyPresent entity. @author MyEclipse Persistence Tools
 */

public class PayBuyPresent implements java.io.Serializable {

	// Fields

	private Integer payBuyPresentId;
	private Integer treasureId;
	private Integer num;
	private Integer price;
	private Integer type;

	// Constructors

	/** default constructor */
	public PayBuyPresent() {
	}

	/** full constructor */
	public PayBuyPresent(Integer treasureId, Integer num, Integer price,
			Integer type) {
		this.treasureId = treasureId;
		this.num = num;
		this.price = price;
		this.type = type;
	}

	// Property accessors

	public Integer getPayBuyPresentId() {
		return this.payBuyPresentId;
	}

	public void setPayBuyPresentId(Integer payBuyPresentId) {
		this.payBuyPresentId = payBuyPresentId;
	}

	public Integer getTreasureId() {
		return this.treasureId;
	}

	public void setTreasureId(Integer treasureId) {
		this.treasureId = treasureId;
	}

	public Integer getNum() {
		return this.num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public Integer getPrice() {
		return this.price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

}