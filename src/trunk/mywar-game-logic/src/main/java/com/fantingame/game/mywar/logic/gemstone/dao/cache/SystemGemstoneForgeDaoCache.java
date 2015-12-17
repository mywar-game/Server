package com.fantingame.game.mywar.logic.gemstone.dao.cache;

import java.util.List;

import com.fantingame.game.common.dao.staticdata.StaticDataDaoBaseListT;
import com.fantingame.game.mywar.logic.gemstone.model.SystemGemstoneForge;

public class SystemGemstoneForgeDaoCache extends StaticDataDaoBaseListT<Integer, SystemGemstoneForge> {

	@Override
	protected Integer getCacheKey(SystemGemstoneForge v) {
		return v.getType();
	}
	
	public SystemGemstoneForge getSystemGemstoneForge(int type, int toolType, int toolId) {
		List<SystemGemstoneForge> toolForgeList = super.getValue(type);
		for (SystemGemstoneForge toolForge : toolForgeList) {
			if (toolForge.getToolType() == toolType && toolForge.getToolId() == toolId)
				return toolForge;
		}		
		
		return null;
	}

	public SystemGemstoneForge getSystemGemstoneForge(int type, String material) {
		List<SystemGemstoneForge> toolForgeList = super.getValue(type);
		for (SystemGemstoneForge toolForge : toolForgeList) {
			if (toolForge.getMaterial().equals(material))
				return toolForge;
		}		
		
		return null;
	}
	
}
