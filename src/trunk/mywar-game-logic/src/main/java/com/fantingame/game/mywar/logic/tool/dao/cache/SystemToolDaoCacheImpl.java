package com.fantingame.game.mywar.logic.tool.dao.cache;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;

import com.fantingame.game.common.dao.staticdata.StaticDataDaoBase;
import com.fantingame.game.mywar.logic.tool.dao.SystemToolDao;
import com.fantingame.game.mywar.logic.tool.dao.mysql.SystemToolDaoMysqlImpl;
import com.fantingame.game.mywar.logic.tool.model.SystemTool;
import com.fantingame.game.mywar.logic.tool.model.SystemToolOpen;


public class SystemToolDaoCacheImpl extends StaticDataDaoBase implements SystemToolDao{
    @Autowired
	private SystemToolDaoMysqlImpl systemToolDaoMysqlImpl;
	
	private Map<Integer,SystemTool> systemToolCache;
	
	private Map<Integer,Integer> systemToolOpenCache;
	
	@Override
	public SystemTool get(int toolId) {
		return systemToolCache.get(toolId);
	}

	@Override
	public List<SystemTool> getSystemToolList() {
		List<SystemTool> list = new ArrayList<SystemTool>();
		list.addAll(systemToolCache.values());
		return list;
	}

	@Override
	public void reload() {
		initCache();
	}

	private void initCache(){ 
		Map<Integer,SystemTool> systemToolCacheTemp = new ConcurrentHashMap<Integer,SystemTool>();
		List<SystemTool> list = systemToolDaoMysqlImpl.getSystemToolList();
		for(SystemTool systemTool:list){
			systemToolCacheTemp.put(systemTool.getToolId(), systemTool);
		}
		systemToolCache = systemToolCacheTemp;
		Map<Integer,Integer> systemToolOpenCacheTemp = new ConcurrentHashMap<Integer,Integer>();
		List<SystemToolOpen> keyList = systemToolDaoMysqlImpl.getAllGiftBoxOpenKey();
		for(SystemToolOpen systemToolOpen:keyList){
			systemToolOpenCacheTemp.put(systemToolOpen.getToolId(), systemToolOpen.getOpenToolId());
		}
		systemToolOpenCache = systemToolOpenCacheTemp;
	}
	@Override
	public int getGiftBoxKey(int giftBoxToolId) {
		Integer keyId = systemToolOpenCache.get(giftBoxToolId);
		return keyId==null?0:keyId;
	}

	@Override
	public void startup() throws Exception {
		initCache();
	}
}
