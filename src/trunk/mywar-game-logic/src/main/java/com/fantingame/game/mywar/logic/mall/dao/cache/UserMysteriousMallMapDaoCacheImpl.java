package com.fantingame.game.mywar.logic.mall.dao.cache;

import com.fantingame.game.common.dao.userdata.BaseCacheDao;
import com.fantingame.game.mywar.logic.mall.dao.UserMysteriousMallMapDao;
import com.fantingame.game.mywar.logic.mall.dao.mysql.UserMysteriousMallMapDaoMysqlImpl;
import com.fantingame.game.mywar.logic.mall.model.UserMysteriousMallMap;

public class UserMysteriousMallMapDaoCacheImpl extends BaseCacheDao<UserMysteriousMallMap> implements UserMysteriousMallMapDao {

	private UserMysteriousMallMapDaoMysqlImpl userMysteriousMallMapDaoMysqlImpl;
	
	public UserMysteriousMallMapDaoMysqlImpl getUserMysteriousMallMapDaoMysqlImpl() {
		return userMysteriousMallMapDaoMysqlImpl;
	}

	public void setUserMysteriousMallMapDaoMysqlImpl( UserMysteriousMallMapDaoMysqlImpl userMysteriousMallMapDaoMysqlImpl) {
		this.userMysteriousMallMapDaoMysqlImpl = userMysteriousMallMapDaoMysqlImpl;
	}

	@Override
	protected UserMysteriousMallMap loadFromDb(String mapId) {
		return userMysteriousMallMapDaoMysqlImpl.getUserMysteriousMallMap(Integer.parseInt(mapId));
	}

	@Override
	public UserMysteriousMallMap getUserMysteriousMallMap(int mapId) {
		return super.getEntry(Integer.toString(mapId));
	}

	@Override
	public UserMysteriousMallMap getUserMysteriousMallMap() {		
		return this.userMysteriousMallMapDaoMysqlImpl.getUserMysteriousMallMap();
	}

	@Override
	public boolean delete(int mapId) {
		if (this.userMysteriousMallMapDaoMysqlImpl.delete(mapId)) {
			super.delete(Integer.toString(mapId));
			
			return true;
		}
		
		return false;
	}

	@Override
	public boolean save(UserMysteriousMallMap userMysteriousMallMap) {
		if (this.userMysteriousMallMapDaoMysqlImpl.save(userMysteriousMallMap)) {
			super.addEntry(userMysteriousMallMap.getMapId() + "", userMysteriousMallMap);
			
			return true;
		}
		
		return false;
	}

}
