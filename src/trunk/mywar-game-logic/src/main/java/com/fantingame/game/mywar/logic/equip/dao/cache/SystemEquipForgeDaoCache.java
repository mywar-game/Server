package com.fantingame.game.mywar.logic.equip.dao.cache;

import java.util.List;

import com.fantingame.game.common.dao.staticdata.StaticDataDaoBaseListT;
import com.fantingame.game.mywar.logic.equip.model.SystemToolForge;

public class SystemEquipForgeDaoCache extends StaticDataDaoBaseListT<Integer, SystemToolForge> {

	@Override
	protected Integer getCacheKey(SystemToolForge v) {
		return v.getType();
	}

	public SystemToolForge getSystemToolForge(int type, int toolType, int toolId) {
		List<SystemToolForge> toolForgeList = super.getValue(type);
		for (SystemToolForge toolForge : toolForgeList) {
			if (toolForge.getToolType() == toolType && toolForge.getToolId() == toolId)
				return toolForge;
		}		
		
		return null;
	}
	
	public SystemToolForge getSystemToolForge(int type, String material) {
		List<SystemToolForge> toolForgeList = super.getValue(type);
		for (SystemToolForge toolForge : toolForgeList) {
			if (toolForge.getMaterial().equals(material))
				return toolForge;
		}		
		
		return null;
	}
}
