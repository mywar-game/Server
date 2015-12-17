package com.fantingame.game.mywar.logic.pack.dao.cache;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.fantingame.game.common.dao.userdata.BaseCacheMapDao;
import com.fantingame.game.mywar.logic.pack.dao.mysql.UserBackPackExtendDaoMysql;
import com.fantingame.game.mywar.logic.pack.model.UserBackPackExtend;
import com.google.common.collect.Maps;

public class UserBackPackExtendsDaoCache extends BaseCacheMapDao<UserBackPackExtend> {
	@Autowired
	private UserBackPackExtendDaoMysql userBackPackExtendDaoMysql;
	/**
	 * 根据位置获取用户背包扩展信息
	 * @param userId
	 * @param pos
	 * @return
	 */
	public UserBackPackExtend getUserBackPackExtendByPos(String userId,int pos){
		return super.getByKey(userId, pos+"");
	}
	/**
	 * 获取用户背包扩展信息列表
	 * @param userId
	 * @return
	 */
	public List<UserBackPackExtend> getUserBackPackExtendList(String userId){
		return super.getMapList(userId);
	}
	/**
	 * 添加记录
	 * @param userBackPackExtend
	 * @return
	 */
	public boolean addUserBackPackExtend(UserBackPackExtend userBackPackExtend){
		if(userBackPackExtendDaoMysql.addUserBackPackExtend(userBackPackExtend)){
			if(super.isExitKey(userBackPackExtend.getUserId())){
				super.addMapValues(userBackPackExtend.getUserId(), userBackPackExtend.getPos()+"", userBackPackExtend);
			}
			return true;
		}
		return false;
	}
	/**
	 * 更新用户背包扩展信息
	 * @param userId
	 * @param pos
	 * @param toolId
	 * @return
	 */
	public boolean updateUserBackPackExtend(String userId,int pos,int toolId){
		if(userBackPackExtendDaoMysql.updateUserBackPackExtend(userId, pos, toolId)){
			if(super.isExitKey(userId)){
				UserBackPackExtend userBackPackExtend = super.getByKey(userId, pos+"");
				userBackPackExtend.setToolId(toolId);
			}
			return true;
		}
		return false;
	}
	
	
	@Override
	protected Map<String, UserBackPackExtend> loadFromDb(String key) {
		Map<String, UserBackPackExtend> map = Maps.newConcurrentMap();
		List<UserBackPackExtend> list = userBackPackExtendDaoMysql.getUserBackPackExtendsList(key);
		for(UserBackPackExtend backPackExtend :list){
			map.put(backPackExtend.getPos()+"", backPackExtend);
		}
		return map;
	}

}
