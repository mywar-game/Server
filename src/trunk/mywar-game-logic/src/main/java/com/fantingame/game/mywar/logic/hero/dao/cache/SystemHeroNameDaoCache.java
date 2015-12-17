package com.fantingame.game.mywar.logic.hero.dao.cache;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.fantingame.game.common.dao.staticdata.StaticDataDaoBaseT;
import com.fantingame.game.mywar.logic.hero.model.SystemHeroName;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class SystemHeroNameDaoCache extends StaticDataDaoBaseT<Integer, SystemHeroName> {

	@Override
	protected Integer getCacheKey(SystemHeroName v) {
		return v.getId();
	}

	private Map<Integer, List<String>> map = Maps.newConcurrentMap();
	
	@Override
	public void startup() throws Exception {
		super.startup();
		getHeroNameMap();
	}
	
	@Override
	public void reload() {
		super.reload();
		
		map.clear();
		getHeroNameMap();
	}
	
	public Map<Integer, List<String>> getHeroNameMap() {
		if (map.size() > 0)
			return map;
		
		List<SystemHeroName> list = super.getValueList();
		List<String> maleNameList = Lists.newArrayList();
		List<String> femaleNameList = Lists.newArrayList();
		
		for (int i = 0; i < list.size(); i++) {
			SystemHeroName preName = list.get(i);
			
			for (int j = 0; j < list.size(); j++) {
				String maleName = "";
				String femaleName = "";
				SystemHeroName sufName = list.get(j);
				
				if (StringUtils.isNotBlank(preName.getMalePrefix())) {			
					maleName = preName.getMalePrefix();
				}
				
				if (!maleName.equals("") && StringUtils.isNotBlank(sufName.getMaleSuffix())) {
					maleName = maleName + sufName.getMaleSuffix();
					maleNameList.add(maleName);
				}
				
				if (StringUtils.isNotBlank(preName.getFemalePrefix())) {
					femaleName = preName.getFemalePrefix();
				}
				
				if (!femaleName.equals("") && StringUtils.isNotBlank(sufName.getFemaleSuffix())) {
					femaleName = femaleName + sufName.getFemaleSuffix();
					femaleNameList.add(femaleName);
				}				
			}			
		}
		
		map.put(1, maleNameList);
		map.put(0, femaleNameList);
		return map;
	}
	
}
