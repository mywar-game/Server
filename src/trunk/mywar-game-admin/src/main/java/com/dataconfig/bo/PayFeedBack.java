package com.dataconfig.bo;

/**
 * PPayFeedBack entity. @author MyEclipse Persistence Tools
 */

public class PayFeedBack implements java.io.Serializable {

	// Fields

	private Integer payFeedBackId;
	private Integer payGolden;
	private Integer payFeedBackGolden;
	private String payFeedBackPresent;

	// Constructors

	/** default constructor */
	public PayFeedBack() {
	}

	/** minimal constructor */
	public PayFeedBack(Integer payGolden, Integer payFeedBackGolden) {
		this.payGolden = payGolden;
		this.payFeedBackGolden = payFeedBackGolden;
	}

	/** full constructor */
	public PayFeedBack(Integer payGolden, Integer payFeedBackGolden,
			String payFeedBackPresent) {
		this.payGolden = payGolden;
		this.payFeedBackGolden = payFeedBackGolden;
		this.payFeedBackPresent = payFeedBackPresent;
	}

	// Property accessors

	public Integer getPayFeedBackId() {
		return this.payFeedBackId;
	}

	public void setPayFeedBackId(Integer payFeedBackId) {
		this.payFeedBackId = payFeedBackId;
	}

	public Integer getPayGolden() {
		return this.payGolden;
	}

	public void setPayGolden(Integer payGolden) {
		this.payGolden = payGolden;
	}

	public Integer getPayFeedBackGolden() {
		return this.payFeedBackGolden;
	}

	public void setPayFeedBackGolden(Integer payFeedBackGolden) {
		this.payFeedBackGolden = payFeedBackGolden;
	}

	public String getPayFeedBackPresent() {
		return this.payFeedBackPresent;
	}

	public void setPayFeedBackPresent(String payFeedBackPresent) {
		this.payFeedBackPresent = payFeedBackPresent;
	}

}