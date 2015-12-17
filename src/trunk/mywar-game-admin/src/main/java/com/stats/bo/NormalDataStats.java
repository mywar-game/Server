package com.stats.bo;

import java.sql.Timestamp;
import java.util.Date;

import com.system.manager.DataSourceManager;

/**
 * NormalDataStats entity. @author MyEclipse Persistence Tools
 */

public class NormalDataStats implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer sysNum;
	private Integer userTotal;
	private Integer newReg;
	private Integer dayActive;
	private Integer maxOnline;
	private Timestamp maxOnlineTime;
	private Integer totalOnline;
	private Integer loginTotalNum;
	private String channelIncomeInfo;
	private Integer payNum;
	private Date time;
	private String agvTimeStr;
	
	/**手动添加属性**/
	/**活跃率**/
	private String activeRate;
	/**平均在线**/
	private int agvOnline;
	/**平均时长**/
	private String agvTime;
	/**平均登陆次数**/
	private int agvLoginNum;
	/**当日的收入**/
	private double dayIncome;
	/**活跃付费率**/
	private String activePayRate;
	private String arpu;
	private String arppu;
	/**注册arpu**/
	private String regArpu;
	/**服务器名字**/
	private String serverName;

	// Constructors
	
	/** default constructor */
	public NormalDataStats() {
	}

	/** full constructor */
	public NormalDataStats(Integer sysNum, Integer userTotal, Integer newReg,
			Integer dayActive, Integer maxOnline, Timestamp maxOnlineTime, Integer totalOnline, Integer loginTotalNum,
			String channelIncomeInfo, Integer payNum, Date time) {
		this.sysNum = sysNum;
		this.userTotal = userTotal;
		this.newReg = newReg;
		this.dayActive = dayActive;
		this.maxOnline = maxOnline;
		this.maxOnlineTime = maxOnlineTime;
		this.totalOnline = totalOnline;
		this.loginTotalNum = loginTotalNum;
		this.channelIncomeInfo = channelIncomeInfo;
		this.payNum = payNum;
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

	public Integer getUserTotal() {
		return this.userTotal;
	}

	public void setUserTotal(Integer userTotal) {
		this.userTotal = userTotal;
	}

	public Integer getNewReg() {
		return this.newReg;
	}

	public void setNewReg(Integer newReg) {
		this.newReg = newReg;
	}

	public Integer getDayActive() {
		return this.dayActive;
	}

	public void setDayActive(Integer dayActive) {
		this.dayActive = dayActive;
	}

	public Integer getMaxOnline() {
		return this.maxOnline;
	}

	public void setMaxOnline(Integer maxOnline) {
		this.maxOnline = maxOnline;
	}

	public Timestamp getMaxOnlineTime() {
		return this.maxOnlineTime;
	}

	public void setMaxOnlineTime(Timestamp maxOnlineTime) {
		this.maxOnlineTime = maxOnlineTime;
	}

	public Integer getTotalOnline() {
		return this.totalOnline;
	}

	public void setTotalOnline(Integer totalOnline) {
		this.totalOnline = totalOnline;
	}

	public Integer getLoginTotalNum() {
		return this.loginTotalNum;
	}

	public void setLoginTotalNum(Integer loginTotalNum) {
		this.loginTotalNum = loginTotalNum;
	}

	public String getChannelIncomeInfo() {
		return this.channelIncomeInfo;
	}

	public void setChannelIncomeInfo(String channelIncomeInfo) {
		this.channelIncomeInfo = channelIncomeInfo;
	}

	public Integer getPayNum() {
		return this.payNum;
	}

	public void setPayNum(Integer payNum) {
		this.payNum = payNum;
	}

	public Date getTime() {
		return this.time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getActiveRate() {
		return activeRate;
	}

	public void setActiveRate(String activeRate) {
		this.activeRate = activeRate;
	}

	public int getAgvOnline() {
		return agvOnline;
	}

	public void setAgvOnline(int agvOnline) {
		this.agvOnline = agvOnline;
	}

	public String getAgvTime() {
		return agvTime;
	}

	public void setAgvTime(String agvTime) {
		this.agvTime = agvTime;
	}

	public int getAgvLoginNum() {
		return agvLoginNum;
	}

	public void setAgvLoginNum(int agvLoginNum) {
		this.agvLoginNum = agvLoginNum;
	}

	public double getDayIncome() {
		return dayIncome;
	}

	public void setDayIncome(double dayIncome) {
		this.dayIncome = dayIncome;
	}

	public String getActivePayRate() {
		return activePayRate;
	}

	public void setActivePayRate(String activePayRate) {
		this.activePayRate = activePayRate;
	}

	public String getArpu() {
		return arpu;
	}

	public void setArpu(String arpu) {
		this.arpu = arpu;
	}

	public String getArppu() {
		return arppu;
	}

	public void setArppu(String arppu) {
		this.arppu = arppu;
	}

	public String getRegArpu() {
		return regArpu;
	}

	public void setRegArpu(String regArpu) {
		this.regArpu = regArpu;
	}
	public String getServerName() {
		return serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	public String getAgvTimeStr() {
		return agvTimeStr;
	}

	public void setAgvTimeStr(String agvTimeStr) {
		this.agvTimeStr = agvTimeStr;
	}


}