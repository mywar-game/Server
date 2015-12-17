package com.example.bo;

import java.util.Date;
/**
 * 用户信息实体类
 * @author zqgame
 *
 */

public class Player {
	//用户编号
	private int userID;
	//平台用户编号
	private String plamUserID;
	//用户名
	private String userName;
	//用户昵称
	private String nickName;
	//头像地址
	private String face_Url;
	//邮箱地址
	private String email;
	//性别（F女 M男）
	private char sex;
	//经验值
	private int exp;
	//经验等级
	private int expLevel;
	//黑心等级
	private int blackLevel;
	//黑心经验值
	private int blackExp;
	//红心等级
	private int redLevel;
	//红心经验值
	private int redExp;
	//金币数量
	private int goldNum;
	//木材数量
	private int woodNum;
	//石油数量
	private int oilNum;
	//能量数量
	private int powerNum;
	//元宝数量
	private int money;
	//主矿类型  1 铜矿 2 铁矿 3 金矿 4 铀矿 5铝矿
	private char mainOre;
	//铜的数量
	private int tongNum;
	//铁的数量
	private int tieNum;
	//金的数量
	private int jinNum;
	//铀的数量
	private int youNum;
	//铝的数量
	private int lvNum;
	//人口数量
	private int people;
	//最大人口数
	private int maxPeople;
	//最后登录时间
	private Date lastLoginTime;
	//最后获取能量点时间
	private Date lastUpdatePowerTime;
	//最后刷新树木的时间
	private Date lastUpdateTreeTime;
	//大渠道号
	private Integer BigChannel;
	//小渠道号
	private Integer SmallChannel;
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public String getPlamUserID() {
		return plamUserID;
	}
	public void setPlamUserID(String plamUserID) {
		this.plamUserID = plamUserID;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getFace_Url() {
		return face_Url;
	}
	public void setFace_Url(String face_Url) {
		this.face_Url = face_Url;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public char getSex() {
		return sex;
	}
	public void setSex(char sex) {
		this.sex = sex;
	}
	public int getExp() {
		return exp;
	}
	public void setExp(int exp) {
		this.exp = exp;
	}
	public int getExpLevel() {
		return expLevel;
	}
	public void setExpLevel(int expLevel) {
		this.expLevel = expLevel;
	}
	public int getBlackLevel() {
		return blackLevel;
	}
	public void setBlackLevel(int blackLevel) {
		this.blackLevel = blackLevel;
	}
	public int getBlackExp() {
		return blackExp;
	}
	public void setBlackExp(int blackExp) {
		this.blackExp = blackExp;
	}
	public int getRedLevel() {
		return redLevel;
	}
	public void setRedLevel(int redLevel) {
		this.redLevel = redLevel;
	}
	public int getRedExp() {
		return redExp;
	}
	public void setRedExp(int redExp) {
		this.redExp = redExp;
	}
	public int getGoldNum() {
		return goldNum;
	}
	public void setGoldNum(int goldNum) {
		this.goldNum = goldNum;
	}
	public int getWoodNum() {
		return woodNum;
	}
	public void setWoodNum(int woodNum) {
		this.woodNum = woodNum;
	}
	public int getOilNum() {
		return oilNum;
	}
	public void setOilNum(int oilNum) {
		this.oilNum = oilNum;
	}
	public int getPowerNum() {
		return powerNum;
	}
	public void setPowerNum(int powerNum) {
		this.powerNum = powerNum;
	}
	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	public char getMainOre() {
		return mainOre;
	}
	public void setMainOre(char mainOre) {
		this.mainOre = mainOre;
	}
	public int getTongNum() {
		return tongNum;
	}
	public void setTongNum(int tongNum) {
		this.tongNum = tongNum;
	}
	public int getTieNum() {
		return tieNum;
	}
	public void setTieNum(int tieNum) {
		this.tieNum = tieNum;
	}
	public int getJinNum() {
		return jinNum;
	}
	public void setJinNum(int jinNum) {
		this.jinNum = jinNum;
	}
	public int getYouNum() {
		return youNum;
	}
	public void setYouNum(int youNum) {
		this.youNum = youNum;
	}
	public int getLvNum() {
		return lvNum;
	}
	public void setLvNum(int lvNum) {
		this.lvNum = lvNum;
	}
	public int getPeople() {
		return people;
	}
	public void setPeople(int people) {
		this.people = people;
	}
	public int getMaxPeople() {
		return maxPeople;
	}
	public void setMaxPeople(int maxPeople) {
		this.maxPeople = maxPeople;
	}
	public Date getLastLoginTime() {
		return lastLoginTime;
	}
	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	public Date getLastUpdatePowerTime() {
		return lastUpdatePowerTime;
	}
	public void setLastUpdatePowerTime(Date lastUpdatePowerTime) {
		this.lastUpdatePowerTime = lastUpdatePowerTime;
	}
	public Date getLastUpdateTreeTime() {
		return lastUpdateTreeTime;
	}
	public void setLastUpdateTreeTime(Date lastUpdateTreeTime) {
		this.lastUpdateTreeTime = lastUpdateTreeTime;
	}
	public Integer getBigChannel() {
		return BigChannel;
	}
	public void setBigChannel(Integer bigChannel) {
		BigChannel = bigChannel;
	}
	public Integer getSmallChannel() {
		return SmallChannel;
	}
	public void setSmallChannel(Integer smallChannel) {
		SmallChannel = smallChannel;
	}

}
