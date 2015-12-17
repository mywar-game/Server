package com.fantingame.game.mywar.logic.activity.dao.cache;

import java.util.List;
import java.util.Map;

import com.fantingame.game.common.dao.staticdata.StaticDataDaoBaseT;
import com.fantingame.game.mywar.logic.activity.model.SystemActivityTask;
import com.google.common.collect.Maps;

public class SystemActivityTaskDaoCache extends StaticDataDaoBaseT<Integer, SystemActivityTask> {

	@Override
	protected Integer getCacheKey(SystemActivityTask v) {
		return v.getActivityTaskId();
	}
	
	private Map<Integer, SystemActivityTask> map = Maps.newConcurrentMap();	
	public SystemActivityTask getSystemActivityTask(int activityTaskId) {
		return super.getValue(activityTaskId);
	}

	public List<SystemActivityTask> getSystemActivityTaskList() {
		return super.getValueList();
	}
	
	public Map<Integer, SystemActivityTask> getActivityTaskByTarget() {
		if (map.size() > 0)
			return map;
		
		List<SystemActivityTask> taskList = super.getValueList();
		for (SystemActivityTask task : taskList) {
			map.put(task.getTargetType(), task);
		}
		
		return map;
	}
	
	@Override
	public void startup() throws Exception {
		super.startup();
		getActivityTaskByTarget();
	}
	
	@Override
	public void reload() {
		super.reload();
		map.clear();
		getActivityTaskByTarget();
	}
}
