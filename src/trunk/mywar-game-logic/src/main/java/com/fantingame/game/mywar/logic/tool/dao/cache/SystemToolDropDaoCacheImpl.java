package com.fantingame.game.mywar.logic.tool.dao.cache;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;

import com.fantingame.game.common.dao.staticdata.StaticDataDaoBase;
import com.fantingame.game.mywar.logic.tool.dao.SystemToolDropDao;
import com.fantingame.game.mywar.logic.tool.dao.mysql.SystemToolDropDaoMysqlImpl;
import com.fantingame.game.mywar.logic.tool.model.SystemToolDrop;
import com.google.common.collect.Lists;

public class SystemToolDropDaoCacheImpl extends StaticDataDaoBase implements SystemToolDropDao{

	private Map<Integer, List<SystemToolDrop>> systemToolDropMap;
    @Autowired
	private SystemToolDropDaoMysqlImpl systemToolDropDaoMysqlImpl;
	@Override
	public List<SystemToolDrop> getSystemToolDropList(int toolId) {
			return this.systemToolDropMap.get(toolId);
	}
	
	public void initCache(){
		Map<Integer, List<SystemToolDrop>> systemToolDropMapTemp = new ConcurrentHashMap<Integer, List<SystemToolDrop>>();
		List<SystemToolDrop> list = systemToolDropDaoMysqlImpl.getAll();
		for(SystemToolDrop drop:list){
			if(systemToolDropMapTemp.containsKey(drop.getToolId())){
				systemToolDropMapTemp.get(drop.getToolId()).add(drop);
			}else{
				List<SystemToolDrop> listEntry = Lists.newArrayList();
				listEntry.add(drop);
				systemToolDropMapTemp.put(drop.getToolId(), listEntry);
			}
		}
		systemToolDropMap = systemToolDropMapTemp;
	}
	
	public SystemToolDropDaoMysqlImpl getSystemToolDropDaoMysqlImpl() {
		return systemToolDropDaoMysqlImpl;
	}
	
	public void setSystemToolDropDaoMysqlImpl(
			SystemToolDropDaoMysqlImpl systemToolDropDaoMysqlImpl) {
		this.systemToolDropDaoMysqlImpl = systemToolDropDaoMysqlImpl;
	}
	
	@Override
	public void reload() {
		initCache();
	}
	
	@Override
	public void startup() throws Exception {
		initCache();
	}
}
