package com.stats.bo;

import java.util.Date;

/**
 * RechargeStats entity. @author MyEclipse Persistence Tools
 */

public class RechargeStats implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer sysNum;
	private String partnerId;
	private Integer payUserNum;
	private Integer dayActive;
	private Integer newRegPayNum;
	private Float payTotalAmount;
	private Float newRegPayTotalAmount;
	private Integer newReg;
	private Date time;
	
	/**以下是手动添加的属性**/
	/**旧用户付费数**/
	private Integer oldRegPayNum;
	/**旧用户付费总额**/
	private Float oldRegPayTotalAmount;
	/**平均新用户付费值**/
	private Float agvNewRegPayValue;
	/**穿透率**/
	private Float penetrateRate;
	/**付费率**/
	private Float payRate;
	private Float arppu;
	private Float arpu;
	// Constructors
	
	// 额外添加, 采集数据时用不上, 只用在页面显示
	private String serverName;

	public String getServerName() {
		return serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	/** default constructor */
	public RechargeStats() {
	}

	/** full constructor */
	public RechargeStats(Integer sysNum, String partnerId, Integer payUserNum,
			Integer dayActive, Integer newRegPayNum, Float payTotalAmount,
			Float newRegPayTotalAmount, Integer newReg, Date time) {
		this.sysNum = sysNum;
		this.partnerId = partnerId;
		this.payUserNum = payUserNum;
		this.dayActive = dayActive;
		this.newRegPayNum = newRegPayNum;
		this.payTotalAmount = payTotalAmount;
		this.newRegPayTotalAmount = newRegPayTotalAmount;
		this.newReg = newReg;
		this.time = time;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSysNum() {
		return this.sysNum;
	}

	public void setSysNum(Integer sysNum) {
		this.sysNum = sysNum;
	}

	public String getPartnerId() {
		return this.partnerId;
	}

	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}

	public Integer getPayUserNum() {
		return this.payUserNum;
	}

	public void setPayUserNum(Integer payUserNum) {
		this.payUserNum = payUserNum;
	}

	public Integer getDayActive() {
		return this.dayActive;
	}

	public void setDayActive(Integer dayActive) {
		this.dayActive = dayActive;
	}

	public Integer getNewRegPayNum() {
		return this.newRegPayNum;
	}

	public void setNewRegPayNum(Integer newRegPayNum) {
		this.newRegPayNum = newRegPayNum;
	}

	public Float getPayTotalAmount() {
		return this.payTotalAmount;
	}

	public void setPayTotalAmount(Float payTotalAmount) {
		this.payTotalAmount = payTotalAmount;
	}

	public Float getNewRegPayTotalAmount() {
		return this.newRegPayTotalAmount;
	}

	public void setNewRegPayTotalAmount(Float newRegPayTotalAmount) {
		this.newRegPayTotalAmount = newRegPayTotalAmount;
	}

	public Integer getNewReg() {
		return this.newReg;
	}

	public void setNewReg(Integer newReg) {
		this.newReg = newReg;
	}

	public Date getTime() {
		return this.time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public Integer getOldRegPayNum() {
		return oldRegPayNum;
	}

	public void setOldRegPayNum(Integer oldRegPayNum) {
		this.oldRegPayNum = oldRegPayNum;
	}

	public Float getOldRegPayTotalAmount() {
		return oldRegPayTotalAmount;
	}

	public void setOldRegPayTotalAmount(Float oldRegPayTotalAmount) {
		this.oldRegPayTotalAmount = oldRegPayTotalAmount;
	}

	public Float getAgvNewRegPayValue() {
		return agvNewRegPayValue;
	}

	public void setAgvNewRegPayValue(Float agvNewRegPayValue) {
		this.agvNewRegPayValue = agvNewRegPayValue;
	}

	public Float getPenetrateRate() {
		return penetrateRate;
	}

	public void setPenetrateRate(Float penetrateRate) {
		this.penetrateRate = penetrateRate;
	}

	public Float getPayRate() {
		return payRate;
	}

	public void setPayRate(Float payRate) {
		this.payRate = payRate;
	}

	public Float getArppu() {
		return arppu;
	}

	public void setArppu(Float arppu) {
		this.arppu = arppu;
	}

	public Float getArpu() {
		return arpu;
	}

	public void setArpu(Float arpu) {
		this.arpu = arpu;
	}

}