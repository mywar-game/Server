package com.fantingame.game.mywar.logic.charge.dao.cache;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fantingame.game.common.dao.staticdata.StaticDataDaoBase;
import com.fantingame.game.mywar.logic.charge.dao.SystemGoldSetDao;
import com.fantingame.game.mywar.logic.charge.dao.mysql.SystemGoldSetDaoMysqlImpl;
import com.fantingame.game.mywar.logic.charge.model.SystemGoldSet;


public class SystemGoldSetDaoCacheImpl extends StaticDataDaoBase implements SystemGoldSetDao{

	private List<SystemGoldSet> getlistCache = new ArrayList<SystemGoldSet>();
	private Map<String, List<SystemGoldSet>> map = new HashMap<String, List<SystemGoldSet>>();

	private SystemGoldSetDaoMysqlImpl systemGoldSetDaoMysqlImpl;

	@Override
	public List<SystemGoldSet> getList() {
		if(getlistCache==null||getlistCache.size()==0){
			getlistCache = systemGoldSetDaoMysqlImpl.getList();
		}
		return getlistCache;
	}

	@Override
	public SystemGoldSet getByPayAmount(double amount, String partnerId) {
		SystemGoldSet result = null;
		List<SystemGoldSet> list = getListByPartnerId(partnerId);
		// 取默认的，暂定默认为1000
		if (list == null || list.size() == 0)
			list = getListByPartnerId("1000");
		
		for(SystemGoldSet systemGoldSet : list){
			if (result == null) {
				if (systemGoldSet.getMoney().doubleValue() <= amount) {
					result = systemGoldSet;
				}
			} else {
				if (systemGoldSet.getMoney().doubleValue() <= amount && systemGoldSet.getGold() > result.getGold()) {
					result = systemGoldSet;
				}
			}
		}		
		return result;
	}
	
	/**
	 * 根据渠道号获取充值档
	 * 
	 * @param partnerId
	 * @return
	 */
	private List<SystemGoldSet> getListByPartnerId(String partnerId) {
		List<SystemGoldSet> goldSetList = getList();
		for (SystemGoldSet systemGoldSet : goldSetList) {
			if (map.containsKey(systemGoldSet.getPartnerId())) {
				map.get(systemGoldSet.getPartnerId()).add(systemGoldSet);
			} else {
				List<SystemGoldSet> list = new ArrayList<SystemGoldSet>();
				list.add(systemGoldSet);
				map.put(systemGoldSet.getPartnerId(), list);
			}
		}
	
		return map.get(partnerId);
	}
	
	public void setSystemGoldSetDaoMysqlImpl(SystemGoldSetDaoMysqlImpl systemGoldSetDaoMysqlImpl) {
		this.systemGoldSetDaoMysqlImpl = systemGoldSetDaoMysqlImpl;
	}
	
	@Override
	public void reload() {
		getlistCache.clear();
		map.clear();
	}

	@Override
	public void startup() throws Exception {
		
	}
}
