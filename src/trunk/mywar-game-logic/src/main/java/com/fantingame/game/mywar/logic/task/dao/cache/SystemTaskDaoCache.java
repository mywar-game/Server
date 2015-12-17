package com.fantingame.game.mywar.logic.task.dao.cache;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.fantingame.game.common.dao.staticdata.StaticDataDaoBase;
import com.fantingame.game.mywar.logic.task.dao.mysql.SystemTaskDaoMysql;
import com.fantingame.game.mywar.logic.task.model.SystemTask;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class SystemTaskDaoCache extends StaticDataDaoBase{
	private Map<Integer,SystemTask> taskMap;
	
	@Autowired
	private SystemTaskDaoMysql systemTaskDaoMysql;
	 /**
	  * 任务id获取任务对象
	  * @param systemTask
	  * @return
	  */
    public SystemTask getBySystemTaskId(int systemTaskId){
    	return taskMap.get(systemTaskId);
    }
    /**
     * 获取某一类型的任务对象
     * @param type
     * @return
     */
    public List<SystemTask> getTaskListByType(int type){
    	List<SystemTask> list = Lists.newArrayList();
    	for(SystemTask task:taskMap.values()){
    		if(task.getTaskType()==type){
    			list.add(task);
    		}
    	}
    	return list;
    }
    /**
     * 获取前置任务id列表
     * @param taskId 任务id
     * @param camp   阵营
     * @return
     */
    public List<SystemTask> getPreSystemTask(int type, int preTaskId, int camp){
    	List<SystemTask> list = Lists.newArrayList();
    	for(SystemTask task: taskMap.values()){
    		// 通用或指定阵营的
    		if(task.getTaskType() == type && (task.getCamp() == 0 || task.getCamp() == camp)){
        		if(task.getPremiseTask() == preTaskId){
        			list.add(task);
        		}
    		}
    	}
    	return list;
    }
	/**
	 * 根据 taskLibrary 获取系统任务列表
	 */
	public List<SystemTask> getByTaskTargetType(int taskLibrary){
		List<SystemTask> list = Lists.newArrayList();
    	for(SystemTask task:taskMap.values()){
    		if(task.getTaskLibrary()==taskLibrary){
    			list.add(task);
    		}
    	}
    	return list;
	}
	
	@Override
	public void reload() {
		List<SystemTask> list = systemTaskDaoMysql.getAll();
		Map<Integer,SystemTask> taskTempMap = Maps.newConcurrentMap();
		for(SystemTask systemTask:list){
			taskTempMap.put(systemTask.getSystemTaskId(), systemTask);
		} 
		taskMap = taskTempMap;
	}
	@Override
	public void startup() throws Exception {
		reload();
	}
}
