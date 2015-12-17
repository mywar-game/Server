package com.fantingame.game.mywar.logic.tool.dao.cache;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.fantingame.game.common.dao.userdata.BaseCacheMapDao;
import com.fantingame.game.mywar.logic.tool.dao.UserToolDao;
import com.fantingame.game.mywar.logic.tool.dao.mysql.UserToolDaoMysqlImpl;
import com.fantingame.game.mywar.logic.tool.model.UserTool;
import com.google.common.collect.Maps;

public class UserToolDaoCacheImpl extends BaseCacheMapDao<UserTool> implements UserToolDao {
    @Autowired
	private UserToolDaoMysqlImpl userToolDaoMysqlImpl;
	@Override
	public UserTool get(String userId, int toolId) {
		return super.getByKey(userId, toolId + "");
	}
	@Override
	public int getUserToolNum(String userId, int toolId) {
		UserTool tool = this.get(userId, toolId);
		return tool==null?0:tool.getToolNum();
	}
	@Override
	public boolean addUserTool(String userId, int toolId, int num, int storehouseNum) {
		if(userToolDaoMysqlImpl.addUserTool(userId, toolId, num, storehouseNum)){
			if(super.isExitKey(userId)){
				UserTool tool = this.get(userId, toolId);
				if(tool!=null){
					tool.setToolNum(tool.getToolNum()+num);
				}else{
					tool = new UserTool();
					tool.setUserId(userId);
					tool.setToolNum(num);
					tool.setToolId(toolId);
					tool.setCreatedTime(new Date());
					tool.setUpdatedTime(new Date());
					super.addMapValues(userId, toolId+"", tool);
				}
			}
			
			return true;
		}
		return false;
	}
	@Override
	public boolean reduceUserTool(String userId, int toolId, int num) {
		if(userToolDaoMysqlImpl.reduceUserTool(userId, toolId, num)){
			if(super.isExitKey(userId)) {
				UserTool tool = this.get(userId, toolId);
				tool.setToolNum(tool.getToolNum() - num);				
			}
			
			return true;
		}
		
		return false;
	}
	@Override
	public List<UserTool> getList(String userId) {
		return super.getMapList(userId);
	}
	@Override
	public int getUserToolCount(String userId) {
		Collection<UserTool> collection = super.getMapValues(userId);
		return collection==null?0:collection.size();
	}
	@Override
	protected Map<String, UserTool> loadFromDb(String key) {
		Map<String,UserTool> map = Maps.newConcurrentMap();
		List<UserTool> list = userToolDaoMysqlImpl.getList(key);
		for(UserTool userTool:list){
			map.put(userTool.getToolId()+"", userTool);
		}
		return map;
	}
	
	public Map<String,UserTool> getUserToolMap(String userId){
		return super.getEntry(userId);
	}
	@Override
	public boolean storehouseInOrOut(String userId, int toolId, int toolNum, int storehouseNum) {
		if (this.userToolDaoMysqlImpl.storehouseInOrOut(userId, toolId, toolNum, storehouseNum)) {
			if (super.isExitKey(userId)) {
				UserTool userTool = this.get(userId, toolId);
				userTool.setToolNum(toolNum);
				userTool.setStorehouseNum(storehouseNum);
				userTool.setUpdatedTime(new Date());
			}
			
			return true;
		}
		
		return false;
	}
}
