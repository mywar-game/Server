package com.stats.bean;

public class PayData {
	/**渠道号**/
	private String channel;
	/**总充值**/
	private double payAmount;
	/**总付费人数**/
	private int payUserNum;
	/**总充值次数**/
	private int payTimes;
	/**购买道具消耗**/
	private int buyToolConsume;
	/**arpu值--总充值/付费人数**/
	private String arpu;
	/**服务器名**/
	private String serverName;
	
	public String getServerName() {
		return serverName;
	}
	public void setServerName(String serverName) {
		this.serverName = serverName;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public double getPayAmount() {
		return payAmount;
	}
	public void setPayAmount(double payAmount) {
		this.payAmount = payAmount;
	}
	public int getPayUserNum() {
		return payUserNum;
	}
	public void setPayUserNum(int payUserNum) {
		this.payUserNum = payUserNum;
	}
	public int getPayTimes() {
		return payTimes;
	}
	public void setPayTimes(int payTimes) {
		this.payTimes = payTimes;
	}
	public int getBuyToolConsume() {
		return buyToolConsume;
	}
	public void setBuyToolConsume(int buyToolConsume) {
		this.buyToolConsume = buyToolConsume;
	}
	public String getArpu() {
		return arpu;
	}
	public void setArpu(String arpu) {
		this.arpu = arpu;
	}
	
}
