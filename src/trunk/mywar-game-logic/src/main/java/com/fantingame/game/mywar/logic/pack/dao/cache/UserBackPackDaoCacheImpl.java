package com.fantingame.game.mywar.logic.pack.dao.cache;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.fantingame.game.common.dao.userdata.BaseCacheMapDao;
import com.fantingame.game.mywar.logic.pack.dao.UserBackPackDao;
import com.fantingame.game.mywar.logic.pack.dao.mysql.UserBackPackDaoMysqlImpl;
import com.fantingame.game.mywar.logic.pack.model.UserBackPack;
import com.google.common.collect.Maps;

public class UserBackPackDaoCacheImpl extends BaseCacheMapDao<UserBackPack> implements UserBackPackDao {
	@Autowired
	private UserBackPackDaoMysqlImpl userBackPackDaoMysqlImpl;
	@Override
	public List<UserBackPack> getPackGoodsList(String userId) {
		return super.getMapList(userId);
	}
	@Override
	public boolean addUserBackPackGoods(UserBackPack userBackPack) {
		if(userBackPackDaoMysqlImpl.addUserBackPackGoods(userBackPack)){
			if(super.isExitKey(userBackPack.getUserId())){
				super.addMapValues(userBackPack.getUserId(), userBackPack.getUserBackPackId()+"", userBackPack);
			}
			return true;
		}
		return false;
	}
	@Override
	public boolean deleteBackPackGoods(String userId, int userBackPackId) {
		if(userBackPackDaoMysqlImpl.deleteBackPackGoods(userId, userBackPackId)){
			if(super.isExitKey(userId)){
				super.deleteKey(userId, userBackPackId+"");
			}
			return true;
		}
		return false;
	}
	@Override
	public boolean addUserBackPackGoodsList(String userId,
			List<UserBackPack> userBackPack) {
		if(userBackPackDaoMysqlImpl.addUserBackPackGoodsList(userId, userBackPack)){
			if(super.isExitKey(userId)){
				for(UserBackPack pack:userBackPack){
					super.addMapValues(userId, pack.getUserBackPackId()+"", pack);
				}
			}
			return true;
		}
		return false;
	}
	@Override
	public boolean updateUserGoodsPos(String userId, int userBackPackId, int pos) {
		if(userBackPackDaoMysqlImpl.updateUserGoodsPos(userId, userBackPackId, pos)){
			if(super.isExitKey(userId)){
				UserBackPack pack = super.getByKey(userId, userBackPackId+"");
				pack.setPos(pos);
				pack.setUpdatedTime(new Date());
			}
			return true;
		}
		return false;
	}
	@Override
	protected Map<String, UserBackPack> loadFromDb(String key) {
		Map<String, UserBackPack> map = Maps.newConcurrentMap();
		List<UserBackPack> list = userBackPackDaoMysqlImpl.getPackGoodsList(key);
		for(UserBackPack userBackPack:list){
			map.put(userBackPack.getUserBackPackId()+"",userBackPack);
		}
		return map;
	}

}
