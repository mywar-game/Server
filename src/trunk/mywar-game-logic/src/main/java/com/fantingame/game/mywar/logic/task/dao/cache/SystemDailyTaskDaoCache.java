package com.fantingame.game.mywar.logic.task.dao.cache;

import java.util.List;
import java.util.Map;

import com.fantingame.game.common.dao.staticdata.StaticDataDaoBaseT;
import com.fantingame.game.mywar.logic.task.model.SystemDailyTask;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class SystemDailyTaskDaoCache extends StaticDataDaoBaseT<String, SystemDailyTask>{

	@Override
	protected String getCacheKey(SystemDailyTask v) {
		return v.getSystemTaskId() + "_" + v.getStar();
	}	
	
	public Map<Integer, List<SystemDailyTask>> getSystemDailyTaskList(int level, int camp) {
		List<SystemDailyTask> list = super.getValueList();
		Map<Integer, List<SystemDailyTask>> taskMap = Maps.newConcurrentMap();
		
		for (SystemDailyTask dailyTask : list) {
			if (dailyTask.getCamp() == camp && (level >= dailyTask.getMinLevel() && level <= dailyTask.getMaxLevel())) {
				if (taskMap.containsKey(dailyTask.getSystemTaskId())) {
					taskMap.get(dailyTask.getSystemTaskId()).add(dailyTask);
				} else {
					List<SystemDailyTask> taskList = Lists.newArrayList();
					taskList.add(dailyTask);
					taskMap.put(dailyTask.getSystemTaskId(), taskList);
				}
			}
				
		}
		
		return taskMap;
	}
	
	public SystemDailyTask getSystemDailyTask(int systemTaskId, int star) {
		return super.getValue(systemTaskId + "_" + star);
	}
	
}
