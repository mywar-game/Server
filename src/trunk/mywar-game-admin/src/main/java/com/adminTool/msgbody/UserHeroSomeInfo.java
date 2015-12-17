package com.adminTool.msgbody;

import java.io.Serializable;

public class UserHeroSomeInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 用户武将ID
	 */
	private String userHeroId;

	/**
	 * 用户ID
	 */
	private String userId;

	/**
	 * 武将人物ID
	 */
	private int heroId;

	/**
	 * 系统武将ID
	 */
	private int systemHeroId;

	/**
	 * 生命
	 */
	private int life;

	/**
	 * 物攻
	 */
	private int physicalAttack;

	/**
	 * 物防
	 */
	private int physicalDefense;

	/**
	 * 主动技能
	 */
	private int plan;

	/**
	 * 普通攻击
	 */
	private int normalPlan;

	/**
	 * 天赋技能1
	 */
	private int skill1;

	/**
	 * 天赋技能2
	 */
	private int skill2;

	/**
	 * 天赋技能3
	 */
	private int skill3;

	/**
	 * 天赋技能4
	 */
	private int skill4;

	/**
	 * 格挡
	 */
	private int parry;

	/**
	 * 暴击
	 */
	private int crit;

	/**
	 * 闪避
	 */
	private int dodge;

	/**
	 * 命中
	 */
	private int hit;

	/**
	 * 头像ID
	 */
	private int imgId;

	/**
	 * 卡牌ID
	 */
	private int cardId;

	/**
	 * 武将名字
	 */
	private String name;

	/**
	 * 武将站位
	 */
	private int pos;

	/**
	 * 武将等级
	 */
	private int level;

	/**
	 * 武 将经验
	 */
	private int exp;

	/**
	 * 武将被吞噬的经验
	 */
	private int dexp;

	/**
	 * 武将职业
	 */
	private int career;

	/**
	 * 武将出售价格
	 */
	private int price;
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getHeroId() {
		return heroId;
	}

	public void setHeroId(int heroId) {
		this.heroId = heroId;
	}

	public String getUserHeroId() {
		return userHeroId;
	}

	public void setUserHeroId(String userHeroId) {
		this.userHeroId = userHeroId;
	}

	public int getSystemHeroId() {
		return systemHeroId;
	}

	public void setSystemHeroId(int systemHeroId) {
		this.systemHeroId = systemHeroId;
	}

	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
	}

	public int getPhysicalAttack() {
		return physicalAttack;
	}

	public void setPhysicalAttack(int physicalAttack) {
		this.physicalAttack = physicalAttack;
	}

	public int getPhysicalDefense() {
		return physicalDefense;
	}

	public void setPhysicalDefense(int physicalDefense) {
		this.physicalDefense = physicalDefense;
	}

	public int getPlan() {
		return plan;
	}

	public void setPlan(int plan) {
		this.plan = plan;
	}

	public int getSkill1() {
		return skill1;
	}

	public void setSkill1(int skill1) {
		this.skill1 = skill1;
	}

	public int getSkill2() {
		return skill2;
	}

	public void setSkill2(int skill2) {
		this.skill2 = skill2;
	}

	public int getSkill3() {
		return skill3;
	}

	public void setSkill3(int skill3) {
		this.skill3 = skill3;
	}

	public int getSkill4() {
		return skill4;
	}

	public void setSkill4(int skill4) {
		this.skill4 = skill4;
	}

	public int getParry() {
		return parry;
	}

	public void setParry(int parry) {
		this.parry = parry;
	}

	public int getCrit() {
		return crit;
	}

	public void setCrit(int crit) {
		this.crit = crit;
	}

	public int getDodge() {
		return dodge;
	}

	public void setDodge(int dodge) {
		this.dodge = dodge;
	}

	public int getHit() {
		return hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}

	public int getImgId() {
		return imgId;
	}

	public void setImgId(int imgId) {
		this.imgId = imgId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPos() {
		return pos;
	}

	public void setPos(int pos) {
		this.pos = pos;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getExp() {
		return exp;
	}

	public void setExp(int exp) {
		this.exp = exp;
	}

	public int getCardId() {
		return cardId;
	}

	public void setCardId(int cardId) {
		this.cardId = cardId;
	}

	public int getDexp() {
		return dexp;
	}

	public void setDexp(int dexp) {
		this.dexp = dexp;
	}

	public int getCareer() {
		return career;
	}

	public void setCareer(int career) {
		this.career = career;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getNormalPlan() {
		return normalPlan;
	}

	public void setNormalPlan(int normalPlan) {
		this.normalPlan = normalPlan;
	}

}
