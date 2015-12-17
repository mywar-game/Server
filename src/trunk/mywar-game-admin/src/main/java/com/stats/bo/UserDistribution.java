package com.stats.bo;

/**
 *  主角名称           		数量          占百分比
 *	| 10033 罗罗亚|      1503 |   1503/四者之合
 *	| 10036 阿隆    |      948  |   948/四者之合
 *	| 10041 马克    |      1843 |   1843/四者之合
 *	| 10046 草丛伦|      332  |   332/四者之合
 */

public class UserDistribution {

	// 主角ID
	private int mainHeroId;
	// 主角名称
	private String mainHeroName;
	// 主角注册数量
	private int num;
	// 占百分比
	private String percent;
	
	public UserDistribution(int mainHeroId, String mainHeroName, int num, String percent) {
		this.mainHeroId = mainHeroId;
		this.mainHeroName = mainHeroName;
		this.num = num;
		this.percent = percent;
	}
	
	public int getMainHeroId() {
		return mainHeroId;
	}
	public void setMainHeroId(int mainHeroId) {
		this.mainHeroId = mainHeroId;
	}
	public String getMainHeroName() {
		return mainHeroName;
	}
	public void setMainHeroName(String mainHeroName) {
		this.mainHeroName = mainHeroName;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getPercent() {
		return percent;
	}
	public void setPercent(String percent) {
		this.percent = percent;
	}
	
}
