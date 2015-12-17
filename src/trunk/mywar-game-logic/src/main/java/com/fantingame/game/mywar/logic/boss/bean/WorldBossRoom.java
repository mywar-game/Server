package com.fantingame.game.mywar.logic.boss.bean;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.fandingame.game.framework.log.LogSystem;
import com.fantingame.game.msgbody.client.equip.UserEquipBO;
import com.fantingame.game.msgbody.client.hero.UserHeroBO;
import com.fantingame.game.msgbody.notify.boss.Boss_enterNotify;
import com.fantingame.game.msgbody.notify.boss.Boss_pushBossRoomOwnerNotify;
import com.fantingame.game.msgbody.notify.boss.Boss_pushUserLeaveNotify;
import com.fantingame.game.mywar.logic.scene.bean.Position;
import com.fantingame.game.server.msg.MsgDispatchCenter;
import com.fantingame.game.server.room.RestrictionsRule;
import com.fantingame.game.server.room.Room;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * 世界boss房间
 * 
 * @author yezp
 */
public class WorldBossRoom extends Room {
	
	private String roomId;
	private Map<String, UserBossBean> userIds;
	private String houseOwner = "";
	
	public WorldBossRoom() {
		userIds = Maps.newConcurrentMap();
	}	

	@Override
	public List<String> getAllUser() {
		List<String> list = Lists.newArrayList();
		for (UserBossBean userBossBean : userIds.values()) {
			if (userBossBean.isEntered) {
				list.add(userBossBean.getUserId());
			}
		}
		
		return list;
	}

	public void addUser(UserBossBean userBossBean, int x, int y, UserHeroBO userHero, List<UserEquipBO> equipList) {
		String userId = userBossBean.getUserId();
		userBossBean.setRoom(this);
		userBossBean.setRoomOwner(false);
		
		if (userIds.size() == 0) {
			userBossBean.setRoomOwner(true);
			this.houseOwner = userBossBean.getUserId();
		}
			
		Position p = new Position();
		p.x = x;
		p.y = y;
		userBossBean.setPosition(p);
		userIds.put(userId, userBossBean);
		
		// 发送广播
		Boss_enterNotify enterNotify = new Boss_enterNotify();
		enterNotify.setUserId(userId);
		enterNotify.setUserName(userBossBean.getUserName());
		enterNotify.setPosX(userBossBean.getPosition().x);
		enterNotify.setPosY(userBossBean.getPosition().y);
		enterNotify.setUserHeroBO(userHero);
		enterNotify.setEquipList(equipList);
		
		RestrictionsRule rule = new RestrictionsRule(RestrictionsRule.RULE_TYPE_NOT_TO_THIS_PERSON);
    	rule.addUser(userId);
    	
    	LogSystem.debug("发送进入世界BOSS广播，userName="+userBossBean.getUserName()+",posX="+enterNotify.getPosX()+",posY="+enterNotify.getPosY());
    	MsgDispatchCenter.disPatchUserRoomMsg("Boss_enter", getRoomId(), enterNotify, rule);		
	}
	
	/**
	 * 离开房间
	 * 
	 * @param userId
	 */
	public void leave(String userId) {
		this.userIds.remove(userId);
		
		// 开始推送用户退出boss战
		Boss_pushUserLeaveNotify notify = new Boss_pushUserLeaveNotify();
		notify.setUserId(userId);
				    		
		RestrictionsRule restrictionsRule = new RestrictionsRule(RestrictionsRule.RULE_TYPE_NOT_TO_THIS_PERSON);		
		restrictionsRule.addUser(userId);
		MsgDispatchCenter.disPatchUserLowerRoomMsg("Boss_pushUserLeave", getRoomId(), notify, restrictionsRule);
	}
	
	/**
	 * 更换房主
	 * 
	 */
	public void changeRoomOwner() {
		if (userIds.size() == 0)
			return;
		
		for (UserBossBean userBossBean : userIds.values()) {
			if (userBossBean.getStatus() == 1) {
				userBossBean.setRoomOwner(true);
				this.houseOwner = userBossBean.getUserId();
				
				// 发送广播
				Boss_pushBossRoomOwnerNotify enterNotify = new Boss_pushBossRoomOwnerNotify();
				enterNotify.setUserId(userBossBean.getUserId());
				
				RestrictionsRule rule = new RestrictionsRule(RestrictionsRule.RULE_TYPE_NOT_TO_THIS_PERSON);		    	
		    	LogSystem.debug("发送更改房主的信息");
		    	MsgDispatchCenter.disPatchUserRoomMsg("Boss_pushBossRoomOwner", getRoomId(), enterNotify, rule);
				break;
			}
		}
	}
	
	public Collection<UserBossBean> getAllUserData() {
		return this.userIds.values();
	}
	
	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}
	
	public String getRoomId() {
		return roomId;
	}

	public String getHouseOwner() {
		return houseOwner;
	}

	public void setHouseOwner(String houseOwner) {
		this.houseOwner = houseOwner;
	}

	public Map<String, UserBossBean> getUserIds() {
		return userIds;
	}

	public void setUserIds(Map<String, UserBossBean> userIds) {
		this.userIds = userIds;
	}
	
}
