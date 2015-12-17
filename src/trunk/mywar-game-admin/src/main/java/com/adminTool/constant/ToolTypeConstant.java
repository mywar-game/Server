package com.adminTool.constant;

import java.util.HashMap;
import java.util.Map;

import com.dataconfig.bo.SystemHero;
import com.dataconfig.service.SystemHeroService;
import com.dataconfig.service.TTreasureConstantService;
import com.framework.servicemanager.ServiceCacheFactory;

/**
 * 道具类型常量
 * 
 * @author yezp
 */
public class ToolTypeConstant {

	/**
	 * 钻石
	 */
	public final static int MONEY = 4;

	/**
	 * 金币
	 */
	public final static int GOLD = 3;

	/**
	 * 英雄
	 */
	public final static int HERO = 7;
	
	/**
	 * 宝箱
	 */
	public static final int GIFT_BOX = 100;
	/**
	 * 行囊
	 */
	public static final int BACK_PACK = 101;
	/**
	 * 技能书
	 */
	public static final int SKILL_BOOK = 102;

	private Map<Integer, String> toolTypeNameMap;
	private Map<Integer, String> toolIdNameMap;
	private Map<Integer, String> equipmentMap;
	private Map<Integer, String> artifactIdNameMap;
	private Map<Integer, SystemHero> heroMap;

	public ToolTypeConstant() {
		toolTypeNameMap = new HashMap<Integer, String>();
		toolTypeNameMap.put(MONEY, "钻石");
		toolTypeNameMap.put(GOLD, "金币");
		toolTypeNameMap.put(HERO, "英雄");
		toolTypeNameMap.put(GIFT_BOX, "宝箱");
		toolTypeNameMap.put(BACK_PACK, "行囊");
		toolTypeNameMap.put(SKILL_BOOK, "技能书");

		TTreasureConstantService treasureConstantService = ServiceCacheFactory
				.getServiceCache().getService(TTreasureConstantService.class);
//		SystemArtifactService artifactService = ServiceCacheFactory
//				.getServiceCache().getService(SystemArtifactService.class);
//		EEquipmentConstantService equipmentService = ServiceCacheFactory
//				.getServiceCache().getService(EEquipmentConstantService.class);
		SystemHeroService heroService = ServiceCacheFactory.getServiceCache()
				.getService(SystemHeroService.class);

		toolIdNameMap = treasureConstantService.findTreasureIdNameMap();
//		artifactIdNameMap = artifactService.findArtifactIDNameMap();
//		equipmentMap = equipmentService.findEquipmentIDNameMap();
		heroMap = heroService.getHeroMap();
	}

	/**
	 * 获取道具类型、名称
	 * 
	 * @param str
	 * @return
	 */
	public String getToolTypeAndName(String str) {
		String show = "";
		String[] rewardsArr = str.split("\\|");
		for (String rewards : rewardsArr) {
			String[] reward = rewards.split(",");
			if (reward.length == 3) {
				int toolType = Integer.parseInt(reward[0]);
				int toolId = Integer.parseInt(reward[1]);
				String typeName = toolTypeNameMap.get(toolType);
				String toolName = getToolName(toolType, toolId);

				if (show.length() > 0) {
					show = show + "|" + typeName + "," + toolName + ","
							+ reward[2];
				} else {
					show = typeName + "," + toolName + "," + reward[2];
				}
			}
		}

		return show;
	}

	/**
	 * 根据道具类型、ID获取道具名称
	 * 
	 * @param toolType
	 * @param toolId
	 * @return
	 */
	public String getToolName(int toolType, int toolId) {
		String toolName = "";

		switch (toolType) {
//		case EQUIP: {
//			toolName = equipmentMap.get(toolId);
//			break;
//		}
		case HERO: {
			SystemHero hero = heroMap.get(toolId);
			if (hero == null) {
				break;
			}
			
			toolName = hero.getHeroName();
			break;
		}
//		case ARTIFACT: {
//			toolName = artifactIdNameMap.get(toolId);
//			break;
//		}
		default: {
			toolName = toolIdNameMap.get(toolId);
			if (toolName == null) {
				toolName = toolTypeNameMap.get(toolType);
			}
			break;
		}
		}

		return toolName;
	}

	public Map<Integer, String> getToolIdNameMap() {
		return toolIdNameMap;
	}

	public void setToolIdNameMap(Map<Integer, String> toolIdNameMap) {
		this.toolIdNameMap = toolIdNameMap;
	}

	public Map<Integer, String> getEquipmentMap() {
		return equipmentMap;
	}

	public void setEquipmentMap(Map<Integer, String> equipmentMap) {
		this.equipmentMap = equipmentMap;
	}

	public Map<Integer, String> getArtifactIdNameMap() {
		return artifactIdNameMap;
	}

	public void setArtifactIdNameMap(Map<Integer, String> artifactIdNameMap) {
		this.artifactIdNameMap = artifactIdNameMap;
	}

	public Map<Integer, String> getToolTypeNameMap() {
		return toolTypeNameMap;
	}

	public void setToolTypeNameMap(Map<Integer, String> toolTypeNameMap) {
		this.toolTypeNameMap = toolTypeNameMap;
	}

}
