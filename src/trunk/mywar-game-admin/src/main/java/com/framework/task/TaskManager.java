package com.framework.task;


import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import com.framework.server.msg.MsgHead;
/**
 * Task管理器
 * @author mengc
 *
 */
public class TaskManager implements BeanPostProcessor {
  private Map<MsgHead, TaskEntry> taskMap = new HashMap<MsgHead, TaskEntry>();
  private static TaskManager taskManager;
  private TaskManager() {
  }
  
  public static TaskManager getInstance() {
	  if (taskManager == null) {
		  taskManager = new TaskManager();
	  }
	  return taskManager;
  }
  
  public void addTask(MsgHead msgHead, TaskEntry task) {
	  taskMap.put(msgHead, task);
  }
  
  public void removeTask(MsgHead msgHead) {
	  taskMap.remove(msgHead);
  }
  
  public TaskEntry findTask(MsgHead msgHead) {
	  if (taskMap.containsKey(msgHead)) {
		  return taskMap.get(msgHead);
	  }
	  return null;
  }

	public Object postProcessAfterInitialization(Object arg0, String arg1)
			throws BeansException {
		// TODO Auto-generated method stub
		return arg0;
	}

	public Object postProcessBeforeInitialization(Object arg0, String arg1)
			throws BeansException {
		// TODO Auto-generated method stub
		if (arg0 instanceof TaskEntry) {
			MsgHead msgHead = new MsgHead();
			msgHead.setCmdCode(((TaskEntry) arg0).getCmdCode());
	        if (msgHead.getCmdCode() == 0) {
	        	throw new NullPointerException("Task " + arg0.getClass().getSimpleName() + "没有配置cmdCode!");
	        }
	        if (((TaskEntry) arg0).getRequestMsgBody() == null) {
	        	throw new NullPointerException("Task " + arg0.getClass().getSimpleName() + "没有配置请求消息体!");
	        }
			getInstance().addTask(msgHead, (TaskEntry) arg0);
		}
		return arg0;
	}
}
