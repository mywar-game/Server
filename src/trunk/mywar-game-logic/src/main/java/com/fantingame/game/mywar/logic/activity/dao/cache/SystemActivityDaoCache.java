package com.fantingame.game.mywar.logic.activity.dao.cache;

import java.util.List;

import com.fantingame.game.common.dao.staticdata.StaticDataDaoBaseT;
import com.fantingame.game.mywar.logic.activity.model.SystemActivity;
import com.google.common.collect.Lists;

public class SystemActivityDaoCache extends StaticDataDaoBaseT<Integer, SystemActivity> {

	@Override
	protected Integer getCacheKey(SystemActivity v) {
		return v.getActivityId();
	}
	
	public SystemActivity getSystemActivity(int activityId) {
		return super.getValue(activityId);
	}
	
	public List<SystemActivity> getAllList(int status) {
		List<SystemActivity> activityList = Lists.newArrayList();
		List<SystemActivity> list = super.getValueList();
		for (SystemActivity activity : list) {
			if (activity.getStatus() == status)
				activityList.add(activity);
		}
		
		return activityList;
	}

}
