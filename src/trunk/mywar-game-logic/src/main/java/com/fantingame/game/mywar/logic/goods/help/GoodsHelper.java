package com.fantingame.game.mywar.logic.goods.help;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.NumberUtils;

import com.fantingame.game.msgbody.client.equip.UserEquipBO;
import com.fantingame.game.msgbody.client.gemstone.UserGemstoneBO;
import com.fantingame.game.msgbody.client.goods.CommonGoodsBeanBO;
import com.fantingame.game.msgbody.client.goods.GoodsBeanBO;
import com.fantingame.game.msgbody.client.hero.UserHeroBO;
import com.fantingame.game.msgbody.client.hero.UserHeroSkillBO;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;


public class GoodsHelper {
	/**
	 * 解析旧落字符串
	 * 
	 * @param goodsIds
	 * @return
	 */
	public static List<GoodsBeanBO> parseDropGoods(String goodsIds) {
		List<GoodsBeanBO> dropToolBOList = new ArrayList<GoodsBeanBO>();
		if (Strings.isNullOrEmpty(goodsIds)) {
			return dropToolBOList;
		}
		String[] strs = goodsIds.split("\\|");
		for (String str : strs) {
			String[] infos = str.split(",");
			if (infos.length != 3) {
				continue;
			}
			int goodsType = NumberUtils.parseNumber(infos[0], Integer.class);
			int goodsId = NumberUtils.parseNumber(infos[1], Integer.class);
			int goodsNum = NumberUtils.parseNumber(infos[2], Integer.class);
			GoodsBeanBO dropToolBO = new GoodsBeanBO();
			dropToolBO.setGoodsType(goodsType);
			dropToolBO.setGoodsId(goodsId);
			dropToolBO.setGoodsNum(goodsNum);
			dropToolBOList.add(dropToolBO);
		}
		return dropToolBOList;
	}
	
	public static GoodsBeanBO parseDropGood(int toolType, int toolId, int toolNum) {		
		GoodsBeanBO dropToolBO = new GoodsBeanBO();
		dropToolBO.setGoodsType(toolType);
		dropToolBO.setGoodsId(toolId);
		dropToolBO.setGoodsNum(toolNum);
			
		return dropToolBO;
	}
	
	/**
	 * 添加一个bo
	 * @param drop
	 * @param goodsType
	 * @param goodsId
	 * @param goodsNum
	 */
	public static void addCommonDropGoodsBean(CommonGoodsBeanBO drop,int goodsType,int goodsId,int goodsNum){
		boolean isInitList = false;
		List<GoodsBeanBO> list = drop.getGoodsList();
		if(list==null){
			list = Lists.newArrayList();
			isInitList = true;
		}
		GoodsBeanBO beanBO = new GoodsBeanBO();
		beanBO.setGoodsId(goodsId);
		beanBO.setGoodsNum(goodsNum);
		beanBO.setGoodsType(goodsType);
		list.add(beanBO);
		if(isInitList){
			drop.setGoodsList(list);
		}
	}
	/**
	 * 处理添加一个bo
	 * @param drop
	 * @param bo
	 */
	public static void addCommonDropGoodsBean(CommonGoodsBeanBO drop,GoodsBeanBO bo){
		boolean isInitList = false;
		List<GoodsBeanBO> list = drop.getGoodsList();
		if(list==null){
			list = Lists.newArrayList();
			isInitList = true;
		}
		list.add(bo);
		if(isInitList){
			drop.setGoodsList(list);
		}
	}
	/**
	 * 处理添加英雄
	 * @param drop
	 * @param userHeroBO
	 */
	public static void addCommonDropUserHeroBO(CommonGoodsBeanBO drop,UserHeroBO userHeroBO){
		boolean isInitList = false;
		List<UserHeroBO> list = drop.getHeroList();
		if(list==null){
			list = Lists.newArrayList();
			isInitList = true;
		}
		list.add(userHeroBO);
		if(isInitList){
			drop.setHeroList(list);
		}
	}
	
	/**
	 * 处理添加装备
	 * 
	 * @param drop
	 * @param userHeroBO
	 */
	public static void addCommonDropUserEquipBO(CommonGoodsBeanBO drop, UserEquipBO userEquipBO){
		boolean isInitList = false;
		List<UserEquipBO> list = drop.getEquipList();
		if (list==null) {
			list = Lists.newArrayList();
			isInitList = true;
		}
		
		list.add(userEquipBO);
		if (isInitList) {
			drop.setEquipList(list);
		}
	}
	
	/**
	 * 处理添加装备
	 * 
	 * @param drop
	 * @param userHeroBO
	 */
	public static void addCommonDropUserGemstoneBO(CommonGoodsBeanBO drop, UserGemstoneBO userGemstoneBO){
		boolean isInitList = false;
		List<UserGemstoneBO> list = drop.getGemstoneList();
		if (list==null) {
			list = Lists.newArrayList();
			isInitList = true;
		}
		
		list.add(userGemstoneBO);
		if (isInitList) {
			drop.setGemstoneList(list);
		}
	}
	
	/**
	 * 处理添加英雄列表
	 * @param drop
	 * @param userHeroBOs
	 */
	public static void addCommonDropUserHeroBO(CommonGoodsBeanBO drop,List<UserHeroBO> userHeroBOs){
		boolean isInitList = false;
		List<UserHeroBO> list = drop.getHeroList();
		if(list==null){
			list = Lists.newArrayList();
			isInitList = true;
		}
		list.addAll(userHeroBOs);
		if(isInitList){
			drop.setHeroList(list);
		}
	}
	/**
	 * 处理添加英雄技能
	 * @param drop
	 * @param userHeroSkillBO
	 */
	public static void addCommonDropUserHeroSkillBO(CommonGoodsBeanBO drop,UserHeroSkillBO userHeroSkillBO){
		boolean isInitList = false;
		List<UserHeroSkillBO> list = drop.getHeroSkillList();
		if(list==null){
			list = Lists.newArrayList();
			isInitList = true;
		}
		list.add(userHeroSkillBO);
		if(isInitList){
			drop.setHeroSkillList(list);
		}
	}
	/**
	 * 处理添加英雄技能
	 * @param drop
	 * @param userHeroSkillBO
	 */
	public static void addCommonDropUserHeroSkillBO(CommonGoodsBeanBO drop,List<UserHeroSkillBO> userHeroSkillBOs){
		boolean isInitList = false;
		List<UserHeroSkillBO> list = drop.getHeroSkillList();
		if(list==null){
			list = Lists.newArrayList();
			isInitList = true;
		}
		list.addAll(userHeroSkillBOs);
		if(isInitList){
			drop.setHeroSkillList(list);
		}
	}
}
