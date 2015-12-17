package com.fantingame.game.mywar.logic.user.dao.cache;

import java.util.Date;
import java.util.List;

import com.fantingame.game.common.dao.userdata.BaseCacheDao;
import com.fantingame.game.mywar.logic.user.dao.UserDao;
import com.fantingame.game.mywar.logic.user.dao.mysql.UserDaoMysqlImpl;
import com.fantingame.game.mywar.logic.user.model.User;

public class UserDaoCacheImpl extends BaseCacheDao<User> implements UserDao {
	private UserDaoMysqlImpl userDaoMysqlImpl;

	@Override
	public User getByUserId(String userId) {
		return super.getEntry(userId);
	}

	@Override
	public User getByFtId(String ftId) {
		return this.userDaoMysqlImpl.getByFtId(ftId);
	}
	
	@Override
	public boolean add(User user) {
		if (userDaoMysqlImpl.add(user)) {
			super.addEntry(user.getUserId(), user);
			return true;
		}
		return false;
	}

	@Override
	public User loadFromDb(String key) {
		return userDaoMysqlImpl.getByUserId(key);
	}

	public UserDaoMysqlImpl getuserDaoMysqlImpl() {
		return userDaoMysqlImpl;
	}

	public void setuserDaoMysqlImpl(UserDaoMysqlImpl userDaoMysqlImpl) {
		this.userDaoMysqlImpl = userDaoMysqlImpl;
	}

	@Override
	public boolean reduceMoney(String userId, int reduceAmount) {
		if (userDaoMysqlImpl.reduceMoney(userId, reduceAmount)) {
			if (super.isExitKey(userId)) {
				User user = super.getEntry(userId);
				user.setMoney(user.getMoney() - reduceAmount);
			}
			return true;
		}
		return false;
	}

	@Override
	public boolean addMoney(String userId, int addAmount) {
		if (userDaoMysqlImpl.addMoney(userId, addAmount)) {
			if (super.isExitKey(userId)) {
				User user = super.getEntry(userId);
				user.setMoney(user.getMoney() + addAmount);
			}
			return true;
		}
		return false;
	}

	@Override
	public boolean addGold(String userId, int addAmount) {
		if (userDaoMysqlImpl.addGold(userId, addAmount)) {
			if (super.isExitKey(userId)) {
				User user = super.getEntry(userId);
				user.setGold(user.getGold() + addAmount);
			}
			return true;
		}
		return false;
	}

	@Override
	public boolean reduceGold(String userId, int reduceAmount) {
		if (userDaoMysqlImpl.reduceGold(userId, reduceAmount)) {
			if (super.isExitKey(userId)) {
				User user = super.getEntry(userId);
				user.setGold(user.getGold() - reduceAmount);
			}
			return true;
		}
		return false;
	}

	@Override
	public boolean updateLevelAndExp(String userId, int level, int exp) {
		if (userDaoMysqlImpl.updateLevelAndExp(userId, level, exp)) {
			if (super.isExitKey(userId)) {
				User user = super.getEntry(userId);
				user.setLevel(level);
				user.setExp(user.getExp() + exp);
			}
			return true;
		}
		return false;
	}

	@Override
	public boolean addPower(String userId, int addAmount, Date addTime) {
		if (userDaoMysqlImpl.addPower(userId, addAmount, addTime)) {
			if (super.isExitKey(userId)) {
				User user = super.getEntry(userId);
				user.setPower(user.getPower() + addAmount);
				if (addTime != null) {
					user.setPowerResumTime(addTime);
				}
			}
			return true;
		}
		return false;
	}

	@Override
	public boolean reducePower(String userId, int reduceAmount) {
		if (userDaoMysqlImpl.reducePower(userId, reduceAmount)) {
			if (super.isExitKey(userId)) {
				User user = super.getEntry(userId);
				user.setPower(user.getPower() - reduceAmount);
			}
			return true;
		}
		return false;
	}

	@Override
	public boolean addActivity(String userId, int addAmount, Date addTime) {
		if (userDaoMysqlImpl.addActivity(userId, addAmount, addTime)) {
			if (super.isExitKey(userId)) {
				User user = super.getEntry(userId);
				user.setActivity(user.getActivity() + addAmount);
				if (addTime != null) {
					user.setActivityResumTime(addTime);
				}
			}
			return true;
		}
		return false;
	}

	@Override
	public boolean reduceActivity(String userId, int reduceAmount) {
		if (userDaoMysqlImpl.reduceActivity(userId, reduceAmount)) {
			if (super.isExitKey(userId)) {
				User user = super.getEntry(userId);
				user.setActivity(user.getActivity() - reduceAmount);
			}
			return true;
		}
		return false;
	}

	@Override
	public boolean updateVIPLevel(String userId, int VIPLevel) {
		if (userDaoMysqlImpl.updateVIPLevel(userId, VIPLevel)) {
			if (super.isExitKey(userId)) {
				User user = super.getEntry(userId);
				user.setVipLevel(VIPLevel);
			}
			return true;
		}
		return false;
	}

	@Override
	public boolean addPrestigeAndPrestigeLevel(String userId, int prestige,
			int prestigeLevel) {
		if (userDaoMysqlImpl.addPrestigeAndPrestigeLevel(userId, prestige,
				prestigeLevel)) {
			if (super.isExitKey(userId)) {
				User user = super.getEntry(userId);
				user.setPrestige(user.getPrestige() + prestige);
				user.setPrestigeLevel(user.getPrestigeLevel() + prestigeLevel);
			}
			return true;
		}
		return false;
	}

	@Override
	public boolean reducePrestige(String userId, int reduceAmount) {
		if (userDaoMysqlImpl.reducePrestige(userId, reduceAmount)) {
			if (super.isExitKey(userId)) {
				User user = super.getEntry(userId);
				user.setPrestige(user.getPrestige() - reduceAmount);
			}
			return true;
		}
		return false;
	}

	@Override
	public boolean addHonour(String userId, int addAmount) {
		if (userDaoMysqlImpl.addHonour(userId, addAmount)) {
			if (super.isExitKey(userId)) {
				User user = super.getEntry(userId);
				user.setHonour(user.getHonour() + addAmount);
			}
			return true;
		}
		return false;
	}

	@Override
	public boolean reduceHonour(String userId, int reduceAmount) {
		if (userDaoMysqlImpl.reduceHonour(userId, reduceAmount)) {
			if (super.isExitKey(userId)) {
				User user = super.getEntry(userId);
				user.setHonour(user.getHonour() - reduceAmount);
			}
			return true;
		}
		return false;
	}

	@Override
	public boolean isExitName(String name) {
		return userDaoMysqlImpl.isExitName(name);
	}

	@Override
	public List<String> getAllUserIds() {
		return userDaoMysqlImpl.getAllUserIds();
	}

	@Override
	public User getByUserName(String name) {
		return userDaoMysqlImpl.getByUserName(name);
	}

	@Override
	public boolean updateUserName(String userId, String name) {
		if (userDaoMysqlImpl.updateUserName(userId, name)) {
			if (super.isExitKey(userId)) {
				User user = super.getEntry(userId);
				user.setName(name);
			}
			
			return true;
		}
		
		return false;
	}

	@Override
	public List<String> getBetweenRegTimeList(Date startTime, Date endTime) {
		return this.userDaoMysqlImpl.getBetweenRegTimeList(startTime, endTime);
	}

	@Override
	public List<User> getJoinBattleUserList(int level, int camp) {
		return this.userDaoMysqlImpl.getJoinBattleUserList(level, camp);
	}

	@Override
	public List<User> getUserListByFloatLevel(int minLevel, int maxLevel) {
		return this.userDaoMysqlImpl.getUserListByFloatLevel(minLevel, maxLevel);
	}

	@Override
	public boolean addJobExp(String userId, int addAmount) {
		if (this.userDaoMysqlImpl.addJobExp(userId, addAmount)) {
			if (super.isExitKey(userId)) {
				User user = super.getEntry(userId);
				user.setJobExp(user.getJobExp() + addAmount);
			}
			
			return true;
		}		
		
		return false;
	}
	
	@Override
	public boolean reduceJobExp(String userId, int addAmount) {
		if (this.userDaoMysqlImpl.reduceJobExp(userId, addAmount)) {
			if (super.isExitKey(userId)) {
				User user = super.getEntry(userId);
				user.setJobExp(user.getJobExp() - addAmount);
			}
			
			return true;
		}		
		
		return false;
	}

	@Override
	public List<User> getUserRank(String columnName, int limit) {
		return this.userDaoMysqlImpl.getUserRank(columnName, limit);
	}

	@Override
	public int getUserRank(int value, String columnName) {
		return this.userDaoMysqlImpl.getUserRank(value, columnName);
	}
}
