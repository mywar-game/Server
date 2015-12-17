package com.fantingame.game.mywar.logic.gemstone.dao.cache;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.fantingame.game.common.dao.userdata.BaseCacheMapDao;
import com.fantingame.game.mywar.logic.gemstone.dao.UserGemstoneDao;
import com.fantingame.game.mywar.logic.gemstone.dao.mysql.UserGemstoneDaoMysqlImpl;
import com.fantingame.game.mywar.logic.gemstone.model.UserGemstone;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class UserGemstoneDaoCacheImpl extends BaseCacheMapDao<UserGemstone> implements
		UserGemstoneDao {
	
	private UserGemstoneDaoMysqlImpl userGemstoneDaoMysqlImpl;

	public UserGemstoneDaoMysqlImpl getUserGemstoneDaoMysqlImpl() {
		return userGemstoneDaoMysqlImpl;
	}

	public void setUserGemstoneDaoMysqlImpl(UserGemstoneDaoMysqlImpl userGemstoneDaoMysqlImpl) {
		this.userGemstoneDaoMysqlImpl = userGemstoneDaoMysqlImpl;
	}

	@Override
	protected Map<String, UserGemstone> loadFromDb(String userId) {
		List<UserGemstone> userStoneList = this.userGemstoneDaoMysqlImpl.getUserGemstoneList(userId);
		Map<String, UserGemstone> map = Maps.newConcurrentMap();
		for (UserGemstone userGemstone: userStoneList) {
			map.put(userGemstone.getUserGemstoneId(), userGemstone);
		}
		
		return map;
	}

	@Override
	public List<UserGemstone> getUserGemstoneList(String userId) {
		return super.getMapList(userId);
	}

	@Override
	public boolean addUserGemstone(UserGemstone userGemstone) {
		if (this.userGemstoneDaoMysqlImpl.addUserGemstone(userGemstone)) {
			super.addMapValues(userGemstone.getUserId(), userGemstone.getUserGemstoneId(), userGemstone);
			
			return true;
		}
		
		return false;
	}

	@Override
	public UserGemstone getUserGemstone(String userId, String userGemstoneId) {		
		return super.getByKey(userId, userGemstoneId);
	}

	@Override
	public boolean deleteUserGemstone(String userId, String userGemstoneId) {
		if (this.userGemstoneDaoMysqlImpl.deleteUserGemstone(userId, userGemstoneId)) {
			super.deleteKey(userId, userGemstoneId);
			
			return true;
		}
		
		return false;
	}

	@Override
	public List<UserGemstone> getUserGemstoneListInEquip(String userId, String userEquipId) {
		List<UserGemstone> userGemstoneList = super.getMapList(userId);
		List<UserGemstone> list = Lists.newArrayList();
		for (UserGemstone userGemstone : userGemstoneList) {
			if (userGemstone.getUserEquipId() != null && userGemstone.getUserEquipId().equals(userEquipId))
				list.add(userGemstone);
		}
		
		return list;
	}

	@Override
	public boolean fillInEquip(String userId, String userGemstoneId, String userEquipId, int pos) {
		if (this.userGemstoneDaoMysqlImpl.fillInEquip(userId, userGemstoneId, userEquipId, pos)) {
			if (super.isExitKey(userId)) {
				UserGemstone userGemstone = super.getByKey(userId, userGemstoneId);
				userGemstone.setUserEquipId(userEquipId);
				userGemstone.setPos(pos);
				userGemstone.setUpdatedTime(new Date());				
			}
			
			return true;
		}
		
		return false;
	}

	@Override
	public List<UserGemstone> getUserGemstoneList(String userId, int gemstoneId) {
		List<UserGemstone> list = super.getMapList(userId);
		List<UserGemstone> stoneList = Lists.newArrayList();
		for (UserGemstone stone : list) {
			if (stone.getGemstoneId() == gemstoneId && stone.getPos() <= 0)
				stoneList.add(stone);
		}		
		
		return stoneList;
	}

	@Override
	public boolean upgradeUserGemstone(String userId, String userGemstoneId, int gemstoneId, String attr) {
		if (this.userGemstoneDaoMysqlImpl.upgradeUserGemstone(userId, userGemstoneId, gemstoneId, attr)) {
			if (super.isExitKey(userId)) {
				UserGemstone userGemstone = super.getByKey(userId, userGemstoneId);
				userGemstone.setGemstoneId(gemstoneId);
				userGemstone.setGemstoneAttr(attr);
				userGemstone.setUpdatedTime(new Date());
			}				
			
			return true;
		}		
		
		return false;
	}

	@Override
	public int getUserGemstoneCount(String userId, int type) {
		List<UserGemstone> list = super.getMapList(userId);
		int count = 0;
		for (UserGemstone stone : list) {
			if (stone.getPos() == 0 && stone.getStorehouseNum() == type)
				count++;
		}
		
		return count;
	}

	@Override
	public List<UserGemstone> getUnFillGemstoneList(String userId, int gemstoneId) {
		List<UserGemstone> list = super.getMapList(userId);
		List<UserGemstone> gemstoneList = Lists.newArrayList();
		for (UserGemstone userGemstone : list) {
			if (userGemstone.getGemstoneId() == gemstoneId && userGemstone.getPos() == 0
					&& userGemstone.getStorehouseNum() == 0)
				gemstoneList.add(userGemstone);
		}		
		
		return gemstoneList;
	}

	@Override
	public boolean storehouseInOrOut(String userId, String userGemstoneId, int storehouseNum) {
		if (this.userGemstoneDaoMysqlImpl.storehouseInOrOut(userId, userGemstoneId, storehouseNum)) {
			if (super.isExitKey(userId)) {
				UserGemstone userGemstone = super.getByKey(userId, userGemstoneId);
				userGemstone.setStorehouseNum(storehouseNum);
				userGemstone.setUpdatedTime(new Date());
			}
			
			return true;
		}
		
		return false;
	}

}
