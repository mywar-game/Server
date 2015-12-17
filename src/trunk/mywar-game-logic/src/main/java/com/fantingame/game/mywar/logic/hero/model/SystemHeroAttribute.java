package com.fantingame.game.mywar.logic.hero.model;

/**
 * `system_hero_id` int(11) NOT NULL COMMENT '英雄唯一id', `strength` int(11) NOT
 * NULL COMMENT '力量', `strength_up` int(11) NOT NULL COMMENT '力量成长值', `agile`
 * int(11) NOT NULL COMMENT '敏捷', `agile_up` int(11) NOT NULL COMMENT '敏捷成长值',
 * `stamina` int(11) NOT NULL COMMENT '耐力', `stamina_up` int(11) NOT NULL
 * COMMENT '耐力成长值', `intelligence` int(11) NOT NULL COMMENT '智慧',
 * `intelligence_up` int(11) NOT NULL COMMENT '智慧成长值', `spirit` int(11) NOT NULL
 * COMMENT '精神', `spirit_up` int(11) NOT NULL COMMENT '精神成长值', `hp` int(11) NOT
 * NULL COMMENT '生命值', `attack_power` int(11) NOT NULL COMMENT '攻击强度',
 * `magic_power` int(11) NOT NULL COMMENT '法术强度', `armor` int(11) NOT NULL
 * COMMENT '护甲', `magic_resist` int(11) NOT NULL COMMENT '法术抗性', `attack_speed`
 * int(11) NOT NULL COMMENT '攻击速度（每出手时间=1/value秒）', `speed_up` int(11) NOT NULL
 * COMMENT '急速,影响技能cd时间', `penetration` int(11) NOT NULL COMMENT '穿透',
 * `break_armor` int(11) NOT NULL COMMENT '破甲', `hit` int(11) NOT NULL COMMENT
 * '命中', `dodge` int(11) NOT NULL COMMENT '闪避', `phy_crit` int(11) NOT NULL
 * COMMENT '物理暴击', `mag_crit` int(11) NOT NULL COMMENT '法术暴击', `tenacity`
 * int(11) NOT NULL COMMENT '韧性', `parry` int(11) NOT NULL COMMENT '格挡',
 * `accurate` int(11) NOT NULL COMMENT '精准', `master` int(11) NOT NULL COMMENT
 * '精通', `hit_parry` int(11) NOT NULL COMMENT '招架', `energy` int(11) NOT NULL
 * COMMENT '能量值', `energy_recover` int(11) NOT NULL COMMENT '能量恢复速度',
 * `move_speed` int(11) NOT NULL COMMENT '移动速度（像素点/秒）',
 */
public class SystemHeroAttribute {
	private int systemHeroId;
	private int strength;
	private int strengthUp;
	private int agile;
	private int agileUp;
	private int stamina;
	private int staminaUp;
	private int intelligence;
	private int intelligenceUp;
	private int resistance;
	private int spirit;
	private int spiritUp;
	private int hp;
	private int attackPower;
	private int magicPower;
	private int armor;
	private int magicResist;
	private int attackSpeed;
	private int speedUp;
	private int penetration;
	private int breakArmor;
	private int hit;
	private int dodge;
	private int phyCrit;
	private int magCrit;
	private int tenacity;
	private int parry;
	private int accurate;
	private int master;
	private int hitParry;
	private int energy;
	private int energyRecover;
	private int moveSpeed;
	private int attackType;
	private int attackRange;
	private int miniDamage;
	private int maxDamage;

	public int getSystemHeroId() {
		return systemHeroId;
	}

	public void setSystemHeroId(int systemHeroId) {
		this.systemHeroId = systemHeroId;
	}

	public int getStrength() {
		return strength;
	}

	public void setStrength(int strength) {
		this.strength = strength;
	}

	public int getStrengthUp() {
		return strengthUp;
	}

	public void setStrengthUp(int strengthUp) {
		this.strengthUp = strengthUp;
	}

	public int getAgile() {
		return agile;
	}

	public void setAgile(int agile) {
		this.agile = agile;
	}

	public int getAgileUp() {
		return agileUp;
	}

	public void setAgileUp(int agileUp) {
		this.agileUp = agileUp;
	}

	public int getStamina() {
		return stamina;
	}

	public void setStamina(int stamina) {
		this.stamina = stamina;
	}

	public int getStaminaUp() {
		return staminaUp;
	}

	public void setStaminaUp(int staminaUp) {
		this.staminaUp = staminaUp;
	}

	public int getResistance() {
		return resistance;
	}

	public void setResistance(int resistance) {
		this.resistance = resistance;
	}

	public int getIntelligence() {
		return intelligence;
	}

	public void setIntelligence(int intelligence) {
		this.intelligence = intelligence;
	}

	public int getIntelligenceUp() {
		return intelligenceUp;
	}

	public void setIntelligenceUp(int intelligenceUp) {
		this.intelligenceUp = intelligenceUp;
	}

	public int getSpirit() {
		return spirit;
	}

	public void setSpirit(int spirit) {
		this.spirit = spirit;
	}

	public int getSpiritUp() {
		return spiritUp;
	}

	public void setSpiritUp(int spiritUp) {
		this.spiritUp = spiritUp;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getAttackPower() {
		return attackPower;
	}

	public void setAttackPower(int attackPower) {
		this.attackPower = attackPower;
	}

	public int getMagicPower() {
		return magicPower;
	}

	public void setMagicPower(int magicPower) {
		this.magicPower = magicPower;
	}

	public int getArmor() {
		return armor;
	}

	public void setArmor(int armor) {
		this.armor = armor;
	}

	public int getMagicResist() {
		return magicResist;
	}

	public void setMagicResist(int magicResist) {
		this.magicResist = magicResist;
	}

	public int getAttackSpeed() {
		return attackSpeed;
	}

	public void setAttackSpeed(int attackSpeed) {
		this.attackSpeed = attackSpeed;
	}

	public int getSpeedUp() {
		return speedUp;
	}

	public void setSpeedUp(int speedUp) {
		this.speedUp = speedUp;
	}

	public int getPenetration() {
		return penetration;
	}

	public void setPenetration(int penetration) {
		this.penetration = penetration;
	}

	public int getBreakArmor() {
		return breakArmor;
	}

	public void setBreakArmor(int breakArmor) {
		this.breakArmor = breakArmor;
	}

	public int getHit() {
		return hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}

	public int getDodge() {
		return dodge;
	}

	public void setDodge(int dodge) {
		this.dodge = dodge;
	}

	public int getPhyCrit() {
		return phyCrit;
	}

	public void setPhyCrit(int phyCrit) {
		this.phyCrit = phyCrit;
	}

	public int getMagCrit() {
		return magCrit;
	}

	public void setMagCrit(int magCrit) {
		this.magCrit = magCrit;
	}

	public int getTenacity() {
		return tenacity;
	}

	public void setTenacity(int tenacity) {
		this.tenacity = tenacity;
	}

	public int getParry() {
		return parry;
	}

	public void setParry(int parry) {
		this.parry = parry;
	}

	public int getAccurate() {
		return accurate;
	}

	public void setAccurate(int accurate) {
		this.accurate = accurate;
	}

	public int getMaster() {
		return master;
	}

	public void setMaster(int master) {
		this.master = master;
	}

	public int getHitParry() {
		return hitParry;
	}

	public void setHitParry(int hitParry) {
		this.hitParry = hitParry;
	}

	public int getEnergy() {
		return energy;
	}

	public void setEnergy(int energy) {
		this.energy = energy;
	}

	public int getEnergyRecover() {
		return energyRecover;
	}

	public void setEnergyRecover(int energyRecover) {
		this.energyRecover = energyRecover;
	}

	public int getMoveSpeed() {
		return moveSpeed;
	}

	public void setMoveSpeed(int moveSpeed) {
		this.moveSpeed = moveSpeed;
	}

	public int getAttackType() {
		return attackType;
	}

	public void setAttackType(int attackType) {
		this.attackType = attackType;
	}

	public int getAttackRange() {
		return attackRange;
	}

	public void setAttackRange(int attackRange) {
		this.attackRange = attackRange;
	}

	public int getMiniDamage() {
		return miniDamage;
	}

	public void setMiniDamage(int miniDamage) {
		this.miniDamage = miniDamage;
	}

	public int getMaxDamage() {
		return maxDamage;
	}

	public void setMaxDamage(int maxDamage) {
		this.maxDamage = maxDamage;
	}

}
