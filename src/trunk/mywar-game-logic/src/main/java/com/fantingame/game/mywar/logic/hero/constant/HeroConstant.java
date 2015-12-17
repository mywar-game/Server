package com.fantingame.game.mywar.logic.hero.constant;

import java.util.Map;

import com.google.common.collect.Maps;

public class HeroConstant {

	private static HeroConstant ins;

	public static HeroConstant instance() {
		synchronized (HeroConstant.class) {
			if (ins == null)
				ins = new HeroConstant();
		}

		return ins;
	}

	/**
	 * 基础职业
	 */
	private Map<Integer, Integer> baseCareerMap = Maps.newConcurrentMap();

	/**
	 * 战士
	 */
	private final static int warrior = 1;
	
	/**
	 * 法师
	 */
	private final static int magician = 2;
	
	/**
	 * 牧师
	 */
	private final static int pastor = 3;
	
	/**
	 * 盗贼
	 */
	private final static int cracksman = 4;
	
	/**
	 * 猎人
	 */
	private final static int hunter = 5;
	
	/**
	 * 德鲁伊
	 */
	private final static int druids = 6;
	
	/**
	 * 圣骑士
	 */
	private final static int paladin = 7;
	
	/**
	 * 萨满祭司
	 */
	private final static int shaman = 8;
	
	public HeroConstant() {
		baseCareerMap.put(warrior, warrior);
		baseCareerMap.put(magician, magician);
		baseCareerMap.put(pastor, pastor);
		baseCareerMap.put(cracksman, cracksman);
		baseCareerMap.put(hunter, hunter);
		baseCareerMap.put(druids, druids);
		baseCareerMap.put(paladin, paladin);
		baseCareerMap.put(shaman, shaman);
		
				
	}

	public Map<Integer, Integer> getBaseCareerMap() {
		return baseCareerMap;
	}

	public void setBaseCareerMap(Map<Integer, Integer> careerMap) {
		this.baseCareerMap = careerMap;
	}

}
