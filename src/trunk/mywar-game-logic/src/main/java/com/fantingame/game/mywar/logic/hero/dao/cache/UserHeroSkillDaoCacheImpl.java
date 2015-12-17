package com.fantingame.game.mywar.logic.hero.dao.cache;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.fandingame.game.framework.log.LogSystem;
import com.fantingame.game.common.dao.userdata.BaseCacheMapDao;
import com.fantingame.game.mywar.logic.hero.dao.UserHeroSkillDao;
import com.fantingame.game.mywar.logic.hero.dao.mysql.UserHeroSkillDaoMysqlImpl;
import com.fantingame.game.mywar.logic.hero.model.UserHeroSkill;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class UserHeroSkillDaoCacheImpl extends BaseCacheMapDao<UserHeroSkill> implements UserHeroSkillDao {

	private UserHeroSkillDaoMysqlImpl userHeroSkillDaoMysqlImpl;
	@Override
	public List<UserHeroSkill> getUserHeroSkillList(String userId) {
		return super.getMapList(userId);
	}
	@Override
	public List<UserHeroSkill> getUserHeroSkillList(String userId,
			String userHeroId) {
//		Collection<UserHeroSkill> list = super.getMapValues(userId);
		List<UserHeroSkill> result = Lists.newArrayList();
//		for(UserHeroSkill userHeroSkill:list){
//			if(userHeroSkill.getUserHeroId().equals(userHeroId)){
//				result.add(userHeroSkill);
//			}
//		}
		return result;
	}
	
	@Override
	public boolean updateUserHeroSkillPos(String userId, String userHeroSkillId,
			int pos) {
		if(userHeroSkillDaoMysqlImpl.updateUserHeroSkillPos(userId, userHeroSkillId, pos)){
			if(super.isExitKey(userId)){
				UserHeroSkill userHeroSkill = super.getByKey(userId, userHeroSkillId);
				if(userHeroSkill!=null){
					userHeroSkill.setPos(pos);
					userHeroSkill.setUpdatedTime(new Date());
				}else{
					LogSystem.warn("不应该为空的，为什么会为空呢,userId="+userId+",userHeroSkillId="+userHeroSkillId+",targetPos="+pos);
				}
			}
			return true;
		}
		return false;
	}
	
	@Override
	public boolean updateUserHeroSkillLevel(String userId, String userHeroSkillId,
			int level) {
		if(userHeroSkillDaoMysqlImpl.updateUserHeroSkillLevel(userId, userHeroSkillId, level)){
			if(super.isExitKey(userId)){
				UserHeroSkill userHeroSkill = super.getByKey(userId, userHeroSkillId);
				if(userHeroSkill!=null){
					userHeroSkill.setSkillLevel(level);
					userHeroSkill.setUpdatedTime(new Date());
				}else{
					LogSystem.warn("不应该为空的，为什么会为空呢,userId="+userId+",userHeroSkillId="+userHeroSkillId+",level="+level);
				}
			}
			return true;
		}
		return false;
	}
	
	@Override
	public boolean updateUserHeroSkill(String userId, String userHeroSkillId, int level, int exp) {
		if (userHeroSkillDaoMysqlImpl.updateUserHeroSkill(userId, userHeroSkillId, level, exp)) {
			if (super.isExitKey(userId)) {
				UserHeroSkill userHeroSkill = super.getByKey(userId, userHeroSkillId);
				if (userHeroSkill != null) {
					userHeroSkill.setSkillLevel(level);
					userHeroSkill.setSkillExp(exp);
					userHeroSkill.setUpdatedTime(new Date());
				} else {
					LogSystem.warn("怎么会为空呢？ userId=" + userId + ", userHeroSkillId=" + userHeroSkillId);
				}
			}
			
			return true;
		}		
		return false;
	}
	
	@Override
	public boolean updateUserHeroId(String userId, String userHeroSkillId,
			String userHeroId) {
		if(userHeroSkillDaoMysqlImpl.updateUserHeroId(userId, userHeroSkillId, userHeroId)){
			if(super.isExitKey(userId)){
				UserHeroSkill userHeroSkill = super.getByKey(userId, userHeroSkillId);
				if(userHeroSkill!=null){
//					userHeroSkill.setUserHeroId(userHeroId);
					userHeroSkill.setUpdatedTime(new Date());
				}else{
					LogSystem.warn("不应该为空的，为什么会为空呢,userId="+userId+",userHeroSkillId="+userHeroSkillId+",userHeroId="+userHeroId);
				}
			}
			return true;
		}
		return false;
	}
	
	@Override
	public boolean addUserHeroSkill(UserHeroSkill userHeroSkill) {
		if(userHeroSkillDaoMysqlImpl.addUserHeroSkill(userHeroSkill)){
			if(super.isExitKey(userHeroSkill.getUserId())){
				super.addMapValues(userHeroSkill.getUserId(), userHeroSkill.getUserHeroSkillId(), userHeroSkill);
			}
			return true;
		}
		return false;
	}
	
	@Override
	protected Map<String, UserHeroSkill> loadFromDb(String key) {
		Map<String, UserHeroSkill> map = Maps.newConcurrentMap();
		List<UserHeroSkill> list = userHeroSkillDaoMysqlImpl.getUserHeroSkillList(key);
		for(UserHeroSkill userHeroSkill:list){
			map.put(userHeroSkill.getUserHeroSkillId(), userHeroSkill);
		}
		return map;
	}
	
	@Override
	public boolean addUserHeroSkillList(String userId,
			List<UserHeroSkill> userHeroSkillList) {
		if(userHeroSkillDaoMysqlImpl.addUserHeroSkillList(userId, userHeroSkillList)){
			if(super.isExitKey(userId)){
				for(UserHeroSkill heroSkill:userHeroSkillList){
					super.addMapValues(userId, heroSkill.getUserHeroSkillId(), heroSkill);
				}
			}
		}
		return false;
	}
	
	public UserHeroSkillDaoMysqlImpl getUserHeroSkillDaoMysqlImpl() {
		return userHeroSkillDaoMysqlImpl;
	}
	
	public void setUserHeroSkillDaoMysqlImpl(
			UserHeroSkillDaoMysqlImpl userHeroSkillDaoMysqlImpl) {
		this.userHeroSkillDaoMysqlImpl = userHeroSkillDaoMysqlImpl;
	}
	
	@Override
	public UserHeroSkill getUserHeroSkill(String userId, int systemHeroSkillId) {
		Collection<UserHeroSkill> list = super.getMapValues(userId);
		for (UserHeroSkill userHeroSkill : list) {
			if (userHeroSkill.getSystemHeroSkillId() == systemHeroSkillId)
				return userHeroSkill;
		}		
		
		return null;
	}
	@Override
	public UserHeroSkill getUserHeroSkill(String userId, String userHeroSkillId) {
		Collection<UserHeroSkill> list = super.getMapValues(userId);
		for (UserHeroSkill userHeroSkill : list) {
			if (userHeroSkill.getUserHeroSkillId().equals(userHeroSkillId))
				return userHeroSkill;
		}		
		
		return null;
	}

}
