package com.fantingame.game.mywar.logic.scene.bean;


import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.fandingame.game.framework.log.LogSystem;
import com.fantingame.game.msgbody.notify.scene.Scene_enterNotify;
import com.fantingame.game.msgbody.notify.scene.Scene_exitNotify;
import com.fantingame.game.server.msg.MsgDispatchCenter;
import com.fantingame.game.server.room.RestrictionsRule;
import com.fantingame.game.server.room.Room;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/******************屏蔽同屏显示用户*******************/
// 1、A用户进入场景时，通知房间内其他用户，A进来了。（对于其他用户，判断其同屏显示设置人数是否已满，已满则屏蔽A用户）
// 2、A进入场景后，加载该场景的用户信息，根据A用户的设置加载相应的用户
// 3、A用户离开场景，只需通知对A可见的用户
// 4、A用户移动，只需通知对A可见的用户（若对A不可见的用户中，人数未满，则加载A的信息，变成对A可见）
// 5、换线：其实就是先离开F场景进入G场景

public class Line extends Room{
	private final Scene scene;
	private final int lineNum;
	private Map<String,UserSceneData> userIds;
    public Line(int lineNum,Scene scene) {
		this.lineNum = lineNum;
		this.scene = scene;
		this.userIds = Maps.newConcurrentMap();
	}
    
    public void enter(UserSceneData userSceneData, int x, int y, Map<String, UserSceneData> userSceneDataMap){
    	userSceneData.setLine(this);
		Position p = new Position();
		p.x = x;
		p.y = y;
		userSceneData.setPosition(p);
    	//非单人场景 发送用户进入房间广播
    	if(scene.getSceneType()==Scene.SCENE_TYPE_MULTY){
    		String userId = userSceneData.getUserId();
	    	userIds.put(userId, userSceneData);
	    	Scene_enterNotify scene_enterNotify = new Scene_enterNotify();
	    	scene_enterNotify.setUserId(userSceneData.getUserId());
	    	scene_enterNotify.setHeroId(userSceneData.getHeroId());
	    	scene_enterNotify.setPosX(userSceneData.getPosition().x);
	    	scene_enterNotify.setPosY(userSceneData.getPosition().y);
	    	scene_enterNotify.setUserName(userSceneData.getUserName());
	    	RestrictionsRule rule = new RestrictionsRule(RestrictionsRule.RULE_TYPE_NOT_TO_THIS_PERSON);
	    	rule.addUser(userId);
	    	
	    	// TODO 进入场景，判断需要通知那些用户
	    	List<String> userIdList = userSceneData.getLine().getAllUser();
	    	for (String otherUserId : userIdList) {
	    		if (otherUserId.equals(userId))
	    			continue;
	    		
	    		UserSceneData otherUser = userSceneDataMap.get(otherUserId);
	    		if (otherUser == null)
	    			continue;
	    		
	    		// 判断对方的场景里是否加载了你
	    		if (otherUser.getScreenUserIdMap().containsKey(userId))
	    			continue;
	    		
	    		// 对方场景的同屏显示未满
	    		if (otherUser.getScreenUserIdMap().size() < otherUser.getMaxScreenCount()) {
	    			otherUser.getScreenUserIdMap().put(userId, userId);
	    		} else {
	    			// 不给该用户加载信息
	    			rule.addUser(otherUserId);
	    		}
	    	}
	    	
	    	LogSystem.debug("发送进入广播，userName="+userSceneData.getUserName()+",posX="+scene_enterNotify.getPosX()+",posY="+scene_enterNotify.getPosY());
	    	MsgDispatchCenter.disPatchUserRoomMsg("Scene_enter", getRoomId(), scene_enterNotify, rule);
    	}
    }
    
    public void leave(UserSceneData userSceneData, Map<String, UserSceneData> userSceneDataMap){    	
    	//非单人场景 则要发送退出消息 并从房间中移除记录
    	if(scene.getSceneType() == Scene.SCENE_TYPE_MULTY){
    		String userId = userSceneData.getUserId();
	    	userIds.remove(userId);
	    	
	    	//发送用户离开房间广播
	    	Scene_exitNotify scene_enterNotify = new Scene_exitNotify();
	    	scene_enterNotify.setUserId(userId);
	    	RestrictionsRule rule = new RestrictionsRule(RestrictionsRule.RULE_TYPE_NOT_TO_THIS_PERSON);
	    	rule.addUser(userId);
	    	
	    	// TODO 离开场景，其他用户的map移出该用户并广播
	    	List<String> userIdList = userSceneData.getLine().getAllUser();
	    	for (String otherUserId : userIdList) {
	    		if (otherUserId.equals(userId))
	    			continue;
	    		
	    		UserSceneData otherUser = userSceneDataMap.get(otherUserId);
	    		if (otherUser == null)
	    			continue;
	    		
	    		// 判断对方的场景里是否加载了你，加载了则remove
	    		if (otherUser.getScreenUserIdMap().containsKey(userId)) {
	    			otherUser.getScreenUserIdMap().remove(userId);
	    		} else {
	    			// 未加载的不需要通知
	    			rule.addUser(otherUserId);
	    		}
	    	}
	    	
	    	MsgDispatchCenter.disPatchUserRoomMsg("Scene_exit", getRoomId(), scene_enterNotify, rule);
    	}
    	
    	// 离开场景
    	userSceneData.setLine(null);
    	userSceneData.setPosition(new Position());
    	userSceneData.getScreenUserIdMap().clear();
    }
    
	//用户对象信息
	public int getLineNum() {
		return lineNum;
	}
	
	@Override
	public List<String> getAllUser() {
		List<String> result = Lists.newArrayList();
		for(UserSceneData userSceneData:userIds.values()){
			if(userSceneData.isEntered){
				result.add(userSceneData.getUserId());
			}
		}
		return result;
	}
	
	public Collection<UserSceneData> getAllUserData(){
		return userIds.values();
	}
	
	public String getRoomId(){
		return scene.getSceneId()+"_"+lineNum;
	}
}
