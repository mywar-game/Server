package com.adminTool.bo;

public class SystemMallAmountRule {

	public SystemMallAmountRule() {}
	
	private int id;
	private int mallId;
	private int times;
	private int amount;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getMallId() {
		return mallId;
	}

	public void setMallId(int mallId) {
		this.mallId = mallId;
	}

	public int getTimes() {
		return times;
	}

	public void setTimes(int times) {
		this.times = times;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public SystemMallAmountRule(int mallId, int times, int amount) {
		super();
		this.mallId = mallId;
		this.times = times;
		this.amount = amount;
	}
}
