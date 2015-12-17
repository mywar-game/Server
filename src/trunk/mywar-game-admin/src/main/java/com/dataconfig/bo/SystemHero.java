package com.dataconfig.bo;

/**
 * 英雄
 * 
 * @author yezp
 */
public class SystemHero implements java.io.Serializable {

	private static final long serialVersionUID = -4472833665429057999L;

	// 英雄唯一id
	private int systemHeroId;
	// 模型id
	private int heroId;
	// 名字
	private String heroName;
	// 描述
	private String heroDesc;
	// 品质
	private int heroColor;
	// 角色职业id (1输出战士 2防御战士 3熊德 4树德 5防御骑士 6奶骑士 7法师 8盗贼 9牧师 10术士 11猎人 12元素萨满 13奶萨满
	// 14猎人宝宝 15术士宝宝 16法师宝宝）
	private int careerId;
	// 势力id （1 联盟 2部落）
	private int nationId;
	// 种族id(1兽人 2牛头人 3亡灵 4巨魔 5矮人 6人类 7暗夜精灵 8侏儒)
	private int raceId;
	// 性别Id(0 女 1男)
	private int sexId;
	// 资源id
	private String resId;
	// 能升级到的最大等级
	private int maxLevel;
	// 帧数
	private int frame;
	// 输出类型（决定该单位的站位区域（1：MT，2：近战，3：远程输出，4：奶，5：宝宝）
	private int standId;
	// 角色初始主动技能1 该id为system_hero_skill表中的id
	private int skill01;
	// 角色初始主动技能2 该id为system_hero_skill表中的id
	private int skill02;
	// 角色初始主动技能3 该id为system_hero_skill表中的id
	private int skill03;
	// 角色初始主动技能4 该id为system_hero_skill表中的id
	private int skill04;
	// 特殊技能
	private int skill05;
	// 角色初始被动技能1 该id为system_hero_skill表中的id
	private int objectSkill01;
	// 角色初始被动技能2 该id为system_hero_skill表中的id
	private int objectSkill02;
	// 角色初始被动技能3 该id为system_hero_skill表中的id
	private int objectSkill03;
	// 角色初始被动技能4 该id为system_hero_skill表中的id
	private int objectSkill04;

	public SystemHero() {
	}

	public SystemHero(int systemHeroId, int heroId, String heroName,
			String heroDesc, int heroColor, int careerId, int nationId,
			int raceId, int sexId, String resId, int maxLevel, int frame,
			int standId, int skill01, int skill02, int skill03, int skill04,
			int skill05, int objectSkill01, int objectSkill02,
			int objectSkill03, int objectSkill04) {
		super();
		this.systemHeroId = systemHeroId;
		this.heroId = heroId;
		this.heroName = heroName;
		this.heroDesc = heroDesc;
		this.heroColor = heroColor;
		this.careerId = careerId;
		this.nationId = nationId;
		this.raceId = raceId;
		this.sexId = sexId;
		this.resId = resId;
		this.maxLevel = maxLevel;
		this.frame = frame;
		this.standId = standId;
		this.skill01 = skill01;
		this.skill02 = skill02;
		this.skill03 = skill03;
		this.skill04 = skill04;
		this.skill05 = skill05;
		this.objectSkill01 = objectSkill01;
		this.objectSkill02 = objectSkill02;
		this.objectSkill03 = objectSkill03;
		this.objectSkill04 = objectSkill04;
	}

	public int getSystemHeroId() {
		return systemHeroId;
	}

	public void setSystemHeroId(int systemHeroId) {
		this.systemHeroId = systemHeroId;
	}

	public int getHeroId() {
		return heroId;
	}

	public void setHeroId(int heroId) {
		this.heroId = heroId;
	}

	public String getHeroName() {
		return heroName;
	}

	public void setHeroName(String heroName) {
		this.heroName = heroName;
	}

	public String getHeroDesc() {
		return heroDesc;
	}

	public void setHeroDesc(String heroDesc) {
		this.heroDesc = heroDesc;
	}

	public int getHeroColor() {
		return heroColor;
	}

	public void setHeroColor(int heroColor) {
		this.heroColor = heroColor;
	}

	public int getCareerId() {
		return careerId;
	}

	public void setCareerId(int careerId) {
		this.careerId = careerId;
	}

	public int getNationId() {
		return nationId;
	}

	public void setNationId(int nationId) {
		this.nationId = nationId;
	}

	public int getRaceId() {
		return raceId;
	}

	public void setRaceId(int raceId) {
		this.raceId = raceId;
	}

	public int getSexId() {
		return sexId;
	}

	public void setSexId(int sexId) {
		this.sexId = sexId;
	}

	public String getResId() {
		return resId;
	}

	public void setResId(String resId) {
		this.resId = resId;
	}

	public int getMaxLevel() {
		return maxLevel;
	}

	public void setMaxLevel(int maxLevel) {
		this.maxLevel = maxLevel;
	}

	public int getFrame() {
		return frame;
	}

	public void setFrame(int frame) {
		this.frame = frame;
	}

	public int getStandId() {
		return standId;
	}

	public void setStandId(int standId) {
		this.standId = standId;
	}

	public int getSkill01() {
		return skill01;
	}

	public void setSkill01(int skill01) {
		this.skill01 = skill01;
	}

	public int getSkill02() {
		return skill02;
	}

	public void setSkill02(int skill02) {
		this.skill02 = skill02;
	}

	public int getSkill03() {
		return skill03;
	}

	public void setSkill03(int skill03) {
		this.skill03 = skill03;
	}

	public int getSkill04() {
		return skill04;
	}

	public void setSkill04(int skill04) {
		this.skill04 = skill04;
	}

	public int getSkill05() {
		return skill05;
	}

	public void setSkill05(int skill05) {
		this.skill05 = skill05;
	}

	public int getObjectSkill01() {
		return objectSkill01;
	}

	public void setObjectSkill01(int objectSkill01) {
		this.objectSkill01 = objectSkill01;
	}

	public int getObjectSkill02() {
		return objectSkill02;
	}

	public void setObjectSkill02(int objectSkill02) {
		this.objectSkill02 = objectSkill02;
	}

	public int getObjectSkill03() {
		return objectSkill03;
	}

	public void setObjectSkill03(int objectSkill03) {
		this.objectSkill03 = objectSkill03;
	}

	public int getObjectSkill04() {
		return objectSkill04;
	}

	public void setObjectSkill04(int objectSkill04) {
		this.objectSkill04 = objectSkill04;
	}

}
