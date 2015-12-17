package com.fantingame.game.mywar.logic.equip.dao.cache;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.fantingame.game.common.dao.userdata.BaseCacheMapDao;
import com.fantingame.game.mywar.logic.equip.dao.UserEquipDao;
import com.fantingame.game.mywar.logic.equip.dao.mysql.UserEquipDaoMysqlImpl;
import com.fantingame.game.mywar.logic.equip.model.UserEquip;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class UserEquipDaoCacheImpl extends BaseCacheMapDao<UserEquip> implements
		UserEquipDao {

	private UserEquipDaoMysqlImpl userEquipDaoMysqlImpl;
	
	public UserEquipDaoMysqlImpl getUserEquipDaoMysqlImpl() {
		return userEquipDaoMysqlImpl;
	}
	
	public void setUserEquipDaoMysqlImpl(UserEquipDaoMysqlImpl userEquipDaoMysqlImpl) {
		this.userEquipDaoMysqlImpl = userEquipDaoMysqlImpl;
	}

	@Override
	protected Map<String, UserEquip> loadFromDb(String userId) {
		Map<String, UserEquip> map = Maps.newConcurrentMap();
		List<UserEquip> list = userEquipDaoMysqlImpl.getUserEquipList(userId);
		for (UserEquip userEquip : list) {
			map.put(userEquip.getUserEquipId(), userEquip);
		}
		
		return map;
	}

	@Override
	public List<UserEquip> getUserEquipList(String userId) {
		return super.getMapList(userId);
	}

	@Override
	public UserEquip getUserEquip(String userId, String userEquipId) {
		return super.getByKey(userId, userEquipId);
		
//		Collection<UserEquip> list = super.getMapValues(userId);
//		if (list != null && list.size() > 0) {
//			for (UserEquip userEquip : list) {
//				if (userEquip.getUserEquipId().equals(userEquipId))
//					return userEquip;
//			}
//		}
//		
//		return null;
	}

	@Override
	public UserEquip getUserEquip(String userId, String userHeroId, int pos) {
		Collection<UserEquip> list = super.getMapValues(userId);
		if (list != null && list.size() > 0) {
			for (UserEquip userEquip : list) {
				if (StringUtils.isNotBlank(userEquip.getUserHeroId()) 
						&& userEquip.getUserHeroId().equals(userHeroId) && pos == userEquip.getPos())
					return userEquip;
			}
		}
		
		return null;
	}
	
	@Override
	public boolean changeEquipUserHeroId(String userId, String userEquipId, String userHeroId, int pos) {
		if (userEquipDaoMysqlImpl.changeEquipUserHeroId(userId, userEquipId, userHeroId, pos)) {
			if (super.isExitKey(userId)) {
				UserEquip userEquip = super.getByKey(userId, userEquipId);
				userEquip.setUserHeroId(userHeroId);
				userEquip.setUpdatedTime(new Date());
				userEquip.setPos(pos);
			}
			
			return true;
		}
		return false;
	}

	@Override
	public boolean addEquip(UserEquip userEquip) {
		if (userEquipDaoMysqlImpl.addEquip(userEquip)) {
			if (super.isExitKey(userEquip.getUserId())) {
				super.addMapValues(userEquip.getUserId(), userEquip.getUserEquipId(), userEquip);
			}			
			
			return true;
		}
		
		return false;
	}

	@Override
	public List<UserEquip> getUserEquipList(String userId, String userHeroId) {
		Collection<UserEquip> list = super.getMapValues(userId);
		List<UserEquip> userEquipList = Lists.newArrayList();
		if (list != null && list.size() > 0) {
			for (UserEquip userEquip : list) {
				if (StringUtils.isNotBlank(userEquip.getUserHeroId()) && userEquip.getUserHeroId().equals(userHeroId))
					userEquipList.add(userEquip);
			}
		}
		
		return userEquipList;
	}

	@Override
	public boolean deleteUserEquip(String userId, String userEquipId) {
		if (userEquipDaoMysqlImpl.deleteUserEquip(userId, userEquipId)) {
			if (super.isExitKey(userId)) {
				super.deleteKey(userId, userEquipId);
			}
			
			return true;
		}
		
		return false;
	}

	@Override
	public int getUserEquipCount(String userId, int type) {
		Collection<UserEquip> list = super.getMapValues(userId);
		int count = 0;
		if (list != null && list.size() > 0) {
			for (UserEquip userEquip : list) {
				if (StringUtils.isBlank(userEquip.getUserHeroId()) && userEquip.getStorehouseNum() == type)
					count++;
			}
		}
		
		return count;
	}

	@Override
	public List<UserEquip> getUserEquipList(String userId, int equipId) {
		Collection<UserEquip> list = super.getMapValues(userId);
		List<UserEquip> equipList = Lists.newArrayList();
		for (UserEquip userEquip : list) {
			if (userEquip.getEquipId() == equipId && StringUtils.isBlank(userEquip.getUserHeroId()) 
					&& userEquip.getStorehouseNum() == 0)
				equipList.add(userEquip);
		}
		
		return equipList;
	}

	@Override
	public boolean equipMagic(String userId, String userEquipId, String equipMagicAttr) {
		if (this.userEquipDaoMysqlImpl.equipMagic(userId, userEquipId, equipMagicAttr)) {
			if (super.isExitKey(userId)) {
				UserEquip userEquip = super.getByKey(userId, userEquipId);
				userEquip.setMagicEquipAttr(equipMagicAttr);
				userEquip.setUpdatedTime(new Date());
			}
			
			return true;
		}		
		
		return false;
	}

	@Override
	public boolean storehouseInOrOut(String userId, String userEquipId, int storehouseNum) {
		if (this.userEquipDaoMysqlImpl.storehouseInOrOut(userId, userEquipId, storehouseNum)) {
			if (super.isExitKey(userId)) {
				UserEquip userEquip = super.getByKey(userId, userEquipId);
				userEquip.setStorehouseNum(storehouseNum);
				userEquip.setUpdatedTime(new Date());
			}
			
			return true;
		}
		
		return false;
	}

}
