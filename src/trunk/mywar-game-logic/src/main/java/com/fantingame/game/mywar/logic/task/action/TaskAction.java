package com.fantingame.game.mywar.logic.task.action;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.fantingame.game.msgbody.client.task.TaskAction_addTaskReq;
import com.fantingame.game.msgbody.client.task.TaskAction_commitTaskReq;
import com.fantingame.game.msgbody.client.task.TaskAction_commitTaskRes;
import com.fantingame.game.msgbody.client.task.TaskAction_dropTaskReq;
import com.fantingame.game.msgbody.client.task.TaskAction_getUserDailyTaskInfoRes;
import com.fantingame.game.msgbody.client.task.TaskAction_oneClickRefreshRes;
import com.fantingame.game.msgbody.client.task.TaskAction_receiveTaskReq;
import com.fantingame.game.msgbody.client.task.TaskAction_receiveTaskRes;
import com.fantingame.game.msgbody.client.task.TaskAction_refreshFiveStarRes;
import com.fantingame.game.msgbody.client.task.TaskAction_toolOpenTaskReq;
import com.fantingame.game.msgbody.client.task.TaskAction_toolOpenTaskRes;
import com.fantingame.game.msgbody.client.task.UserDailyTaskInfoBO;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import com.fantingame.game.msgbody.common.model.Msg;
import com.fantingame.game.mywar.logic.task.check.ITaskHitChecker;
import com.fantingame.game.mywar.logic.task.constant.TaskLibraryType;
import com.fantingame.game.mywar.logic.task.model.UserTask;
import com.fantingame.game.mywar.logic.task.service.UserTaskService;
import com.fantingame.game.server.annotation.GameAction;
import com.fantingame.game.server.channel.Channel;
import com.fantingame.game.server.msg.MsgBuilder;
import com.google.common.collect.Maps;

@GameAction
public class TaskAction {
	
	@Autowired
	private UserTaskService userTaskService;
	
    /**
     * 领取任务
     * 
     * @param msg
     * @param channel
     * @return
     */
	public ICodeAble addTask(Msg msg,Channel channel) {
		String userId = msg.getMsgHead().getFromID();
		TaskAction_addTaskReq action_addTaskReq = msg.decodeBody(TaskAction_addTaskReq.class);
		userTaskService.addTask(userId, action_addTaskReq.getSystemTaskId(), action_addTaskReq.getStar());
		return MsgBuilder.EMPTY_BODY;
	}
	
	/**
	 * 领取任务奖励
	 * 
	 * @param msg
	 * @param channel
	 * @return
	 */
	public ICodeAble receiveTask(Msg msg,Channel channel) {
		String userId = msg.getMsgHead().getFromID();
		TaskAction_receiveTaskReq action_receiveTaskReq = msg.decodeBody(TaskAction_receiveTaskReq.class);
		
		TaskAction_receiveTaskRes res = userTaskService.receive(userId, action_receiveTaskReq.getSystemTaskId());
		return res;
	}
	
	/**
	 * 提交任务(有些需要客户端提交的任务 如: 寻找npc)
	 * @param msg
	 * @param channel
	 * @return
	 */
	public ICodeAble commitTask(Msg msg,Channel channel) {
		String userId = msg.getMsgHead().getFromID();
		TaskAction_commitTaskReq commitTaskReq = msg.decodeBody(TaskAction_commitTaskReq.class);
		final int clientSystemTaskId = commitTaskReq.getSystemTaskId();
		userTaskService.updateTaskFinish(userId, commitTaskReq.getTimes(), new ITaskHitChecker() {
			@Override
			public Map<String, Object> isHit(int systemTaskId, int taskLibrary,
					Map<String, String> params) {
				Map<String, Object> returnMap = Maps.newConcurrentMap();
				returnMap.put("rt", false);
				returnMap.put("tm", 1);
				
				//暂时只有寻人任务 才能通过这个接口进行提交
				if(systemTaskId == clientSystemTaskId && taskLibrary == TaskLibraryType.FIND_NPC){
					returnMap.put("rt", true);
					return returnMap;
				}
				
				return returnMap;
			}
		});
		
		UserTask userTask = this.userTaskService.getUserTask(userId, clientSystemTaskId);
		TaskAction_commitTaskRes res = new TaskAction_commitTaskRes();
		res.setStatus(userTask.getStatus());
		res.setSystemTaskId(clientSystemTaskId);
		return res;
	}
	
	/**
	 * 放弃任务
	 * @param msg
	 * @param channel
	 * @return
	 */
	public ICodeAble dropTask(Msg msg,Channel channel) {
		String userId = msg.getMsgHead().getFromID();
        TaskAction_dropTaskReq req = msg.decodeBody(TaskAction_dropTaskReq.class);
        userTaskService.dropTask(userId, req.getSystemTaskId());
        return MsgBuilder.EMPTY_BODY;
	}
	
	/**
	 * 获取用户日常任务信息
	 * 
	 * @return
	 */
	public ICodeAble getUserDailyTaskInfo(Msg msg, Channel channel) {
		String userId = msg.getMsgHead().getFromID();
		
		TaskAction_getUserDailyTaskInfoRes res = this.userTaskService.getUserDailyTaskInfo(userId);
		return res;
	}
	
	/**
	 * 一键刷新
	 * 
	 * @param msg
	 * @param channel
	 * @return
	 */
	public ICodeAble oneClickRefresh(Msg msg, Channel channel) {
		String userId = msg.getMsgHead().getFromID();
		
		List<UserDailyTaskInfoBO> infoBOList = this.userTaskService.oneClickRefresh(userId);
		TaskAction_oneClickRefreshRes res = new TaskAction_oneClickRefreshRes();
		res.setUserDailyTaskInfoList(infoBOList);
		return res;
	}
	
	/**
	 * 刷新五星日常任务
	 * 
	 * @param msg
	 * @param channel
	 * @return
	 */
	public ICodeAble refreshFiveStar(Msg msg, Channel channel) {
		String userId = msg.getMsgHead().getFromID();
		
		UserDailyTaskInfoBO infoBO = this.userTaskService.refreshFiveStar(userId);
		TaskAction_refreshFiveStarRes res = new TaskAction_refreshFiveStarRes();
		res.setUserDailyTaskInfo(infoBO);
		return res;
	}
	
	/**
	 * 道具开启任务
	 * 
	 * @param msg
	 * @param channel
	 * @return
	 */
	public ICodeAble toolOpenTask(Msg msg, Channel channel) {
		String userId = msg.getMsgHead().getFromID();
		
		TaskAction_toolOpenTaskReq req = msg.decodeBody(TaskAction_toolOpenTaskReq.class);
		int systemTaskId = this.userTaskService.toolOpenTask(userId, req.getToolId());
		TaskAction_toolOpenTaskRes res = new TaskAction_toolOpenTaskRes();
		res.setSystemTaskId(systemTaskId);
		
		return res;
	}
	
}
