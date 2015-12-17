package com.fantingame.game.mywar.logic.pawnshop.dao.cache;

import java.util.List;
import java.util.Map;

import com.fantingame.game.common.dao.userdata.BaseCacheMapDao;
import com.fantingame.game.mywar.logic.pawnshop.dao.UserPawnshopInfoDao;
import com.fantingame.game.mywar.logic.pawnshop.dao.mysql.UserPawnshopInfoDaoMysqlImpl;
import com.fantingame.game.mywar.logic.pawnshop.model.UserPawnshopInfo;
import com.google.common.collect.Maps;

public class UserPawnshopInfoDaoCacheImpl extends BaseCacheMapDao<UserPawnshopInfo> implements UserPawnshopInfoDao {

	private UserPawnshopInfoDaoMysqlImpl userPawnshopInfoDaoMysqlImpl;
	
	public UserPawnshopInfoDaoMysqlImpl getUserPawnshopInfoDaoMysqlImpl() {
		return userPawnshopInfoDaoMysqlImpl;
	}

	public void setUserPawnshopInfoDaoMysqlImpl(
			UserPawnshopInfoDaoMysqlImpl userPawnshopInfoDaoMysqlImpl) {
		this.userPawnshopInfoDaoMysqlImpl = userPawnshopInfoDaoMysqlImpl;
	}

	@Override
	public List<UserPawnshopInfo> getUserPawnshopInfoList(int camp) {		
		return super.getMapList(camp + "");
	}

	@Override
	public UserPawnshopInfo getUserPawnshopInfo(int camp, int mallId) {		
		return super.getByKey(String.valueOf(camp), String.valueOf(mallId));
	}

	@Override
	public boolean updateUserPawnshopInfo(int camp, int mallId, int num) {
		if (userPawnshopInfoDaoMysqlImpl.updateUserPawnshopInfo(camp, mallId, num)) {
			if (super.isExitKey(String.valueOf(camp))) {
				UserPawnshopInfo userPawnshopInfo = super.getByKey(String.valueOf(camp), String.valueOf(mallId));
				userPawnshopInfo.setNum(num);
			}
			
			return true;
		}		
		
		return false;
	}

	@Override
	public boolean add(int camp, List<UserPawnshopInfo> list) {		
		if (userPawnshopInfoDaoMysqlImpl.add(camp, list)) {
			if (super.isExitKey(String.valueOf(camp))) {
				for (UserPawnshopInfo userPawnshopInfo : list) {
					super.addMapValues(String.valueOf(camp), String.valueOf(userPawnshopInfo.getMallId()), userPawnshopInfo);
				}
			}
			
			return true;
		}
		
		return false;
	}

	@Override
	protected Map<String, UserPawnshopInfo> loadFromDb(String camp) {
		Map<String, UserPawnshopInfo> map = Maps.newConcurrentMap();
		List<UserPawnshopInfo> list = this.userPawnshopInfoDaoMysqlImpl.getUserPawnshopInfoList(Integer.parseInt(camp));
		for (UserPawnshopInfo info : list) {
			map.put(info.getMallId() + "", info);
		}
		
		return map;
	}
}
