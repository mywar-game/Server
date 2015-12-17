package com.fantingame.game.mywar.logic.equip.contant;

import java.util.Map;

import com.google.common.collect.Maps;

public class EquipConstant {

	private static EquipConstant ins;

	public static EquipConstant instance() {
		synchronized (EquipConstant.class) {
			if (ins == null)
				ins = new EquipConstant();
		}

		return ins;
	}

	// 主属性
	private Map<Integer, String> mainAttrMap = Maps.newConcurrentMap();
	// 次级属性
	private Map<Integer, String> secondaryMap = Maps.newConcurrentMap();
	// 附魔属性
	private Map<Integer, String> magicAttrMap = Maps.newConcurrentMap();

	public EquipConstant() {
		mainAttrMap.put(1, strength);// 力量
		mainAttrMap.put(2, agile);// 敏捷
		mainAttrMap.put(3, intelligence);// 智力

		secondaryMap.put(1, phyCrit);// 暴击
		secondaryMap.put(2, dodge);// 闪躲
		secondaryMap.put(3, speedUp);// 急速
		secondaryMap.put(4, damageIncrease);// 伤害增幅
		secondaryMap.put(5, toughIncrease);// 免伤增幅
		secondaryMap.put(6, treatmentIncrease);// 治疗增幅

		magicAttrMap.put(1, attackPower);
		magicAttrMap.put(2, magicPower);
		magicAttrMap.put(3, phyCrit);
		magicAttrMap.put(4, dodge);
		magicAttrMap.put(5, speedUp);
		magicAttrMap.put(6, hp);
	}

	public final static String strength = "strength";
	public final static String agile = "agile";
	public final static String intelligence = "intelligence";
	public final static String phyCrit = "phyCrit";
	public final static String dodge = "dodge";
	public final static String speedUp = "speedUp";
	public final static String damageIncrease = "damageIncrease";
	public final static String toughIncrease = "toughIncrease";
	public final static String treatmentIncrease = "treatmentIncrease";
	public final static String attackPower = "attackPower";
	public final static String magicPower = "magicPower";
	public final static String parry = "parry";
	public final static String hp = "hp";

	public Map<Integer, String> getMainAttrMap() {
		return mainAttrMap;
	}

	public void setMainAttrMap(Map<Integer, String> mainAttrMap) {
		this.mainAttrMap = mainAttrMap;
	}

	public Map<Integer, String> getSecondaryMap() {
		return secondaryMap;
	}

	public void setSecondaryMap(Map<Integer, String> secondaryMap) {
		this.secondaryMap = secondaryMap;
	}

	public Map<Integer, String> getMagicAttrMap() {
		return magicAttrMap;
	}

	public void setMagicAttrMap(Map<Integer, String> magicAttrMap) {
		this.magicAttrMap = magicAttrMap;
	}

	/**
	 * 主手位
	 */
	public static final int POS_1 = 1;

	/**
	 * 手套
	 */
	public static final int POS_2 = 2;

	/**
	 * 胸甲
	 */
	public static final int POS_3 = 3;

	/**
	 * 头盔
	 */
	public static final int POS_4 = 4;

	/**
	 * 肩甲
	 */
	public static final int POS_5 = 5;

	/**
	 * 裤子
	 */
	public static final int POS_6 = 6;

	/**
	 * 鞋子
	 */
	public static final int POS_7 = 7;

	/**
	 * 副手位
	 */
	public static final int POS_8 = 8;

	// 品质：1 优秀（绿）、2 精良（蓝）、3 史诗（紫）、4 传说（橙）
	public static final int QUALITY_WHITE = 1;
	public static final int QUALITY_GREEN = 2;
	public static final int QUALITY_BLUE = 3;
	public static final int QUALITY_PURPLE = 4;
	public static final int QUALITY_ORANGE = 5;

}
