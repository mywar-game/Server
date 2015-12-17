package com.fantingame.game.mywar.logic.user.dao.mysql;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.fantingame.common.jdbc.Jdbc;
import com.fantingame.common.jdbc.SqlParameter;
import com.fantingame.game.mywar.logic.user.dao.UserDao;
import com.fantingame.game.mywar.logic.user.model.User;
import com.google.common.collect.Lists;

public class UserDaoMysqlImpl implements UserDao {
	@Autowired
	private Jdbc jdbcUser;

	@Override
	public User getByUserId(String userId) {
		String sql = "select * from user where user_id=? limit 1";
		SqlParameter parameter = new SqlParameter();
		parameter.setString(userId);
		return jdbcUser.get(sql, User.class, parameter);
	}

	@Override
	public User getByFtId(String ftId) {
		String sql = "select * from user where ft_id = ? limit 1";
		SqlParameter param = new SqlParameter();
		param.setString(ftId);
		
		return this.jdbcUser.get(sql, User.class, param);
	}
	
	@Override
	public boolean add(User user) {
		return jdbcUser.insert(user) > 0;
	}

	public boolean addMoney(String userId, int addAmount) {
		if (addAmount < 0) {
			throw new RuntimeException("增加的money必须大于0");
		}
		String sql = "update user set money = money + ? where user_id = ? limit 1";
		SqlParameter parameter = new SqlParameter();
		parameter.setInt(addAmount);
		parameter.setString(userId);
		return jdbcUser.update(sql, parameter) > 0;
	}

	@Override
	public boolean reduceMoney(String userId, int reduceAmount) {
		if (reduceAmount < 0) {
			throw new RuntimeException("减的的money必须大于0");
		}
		String sql = "update user set money=money-? where user_id = ? and money - ? >= 0 limit 1";
		SqlParameter parameter = new SqlParameter();
		parameter.setInt(reduceAmount);
		parameter.setString(userId);
		parameter.setInt(reduceAmount);
		return jdbcUser.update(sql, parameter) > 0;
	}

	@Override
	public boolean addGold(String userId, int addAmount) {
		if (addAmount < 0) {
			throw new RuntimeException("增加的gold必须大于0");
		}
		String sql = "update user set gold = gold + ? where user_id = ?";
		SqlParameter parameter = new SqlParameter();
		parameter.setInt(addAmount);
		parameter.setString(userId);
		return jdbcUser.update(sql, parameter) > 0;
	}

	@Override
	public boolean reduceGold(String userId, int reduceAmount) {
		if (reduceAmount < 0) {
			throw new RuntimeException("减的的money必须大于0");
		}
		String sql = "update user set gold = gold - ? where user_id = ? and gold - ? >= 0 limit 1";
		SqlParameter parameter = new SqlParameter();
		parameter.setInt(reduceAmount);
		parameter.setString(userId);
		parameter.setInt(reduceAmount);
		return jdbcUser.update(sql, parameter) > 0;
	}

	@Override
	public boolean addJobExp(String userId, int addAmount) {
		if (addAmount <= 0) 
			throw new RuntimeException("添加的职业经验必须大于0");
		
		String sql = "update user set job_exp = job_exp + ? where user_id = ?";
		SqlParameter param = new SqlParameter();
		param.setInt(addAmount);
		param.setString(userId);
		
		return this.jdbcUser.update(sql, param) > 0;
	}
	
	@Override
	public boolean reduceJobExp(String userId, int reduceAmount) {
		if (reduceAmount < 0) {
			throw new RuntimeException("减的的JobExp必须大于0");
		}
		
		String sql = "update user set job_exp = job_exp - ? where user_id = ? and job_exp - ? >= 0 limit 1";
		SqlParameter parameter = new SqlParameter();
		parameter.setInt(reduceAmount);
		parameter.setString(userId);
		parameter.setInt(reduceAmount);
		return jdbcUser.update(sql, parameter) > 0;
	}
	
	@Override
	public boolean updateLevelAndExp(String userId, int level, int exp) {
		String sql = "update user set exp = exp + ?, level = ? where user_id = ? limit 1";
		SqlParameter parameter = new SqlParameter();
		parameter.setInt(exp);
		parameter.setInt(level);
		parameter.setString(userId);
		return jdbcUser.update(sql, parameter) > 0;
	}

	@Override
	public boolean addPower(String userId, int addAmount, Date addTime) {
		String sql = "update user set power = power + ?";
		SqlParameter parameter = new SqlParameter();
		parameter.setInt(addAmount);
		if (addTime != null) {
			sql = sql + ",power_resum_time = ?";
			parameter.setObject(addTime);
		}
		sql = sql + " where user_id = ? limit 1";
		parameter.setString(userId);
		return jdbcUser.update(sql, parameter) > 0;
	}

	@Override
	public boolean reducePower(String userId, int reduceAmount) {
		String sql = "update user set power = power - ?";
		sql = sql + " where user_id = ? and power - ? >= 0 limit 1";
		SqlParameter parameter = new SqlParameter();
		parameter.setInt(reduceAmount);
		parameter.setString(userId);
		parameter.setInt(reduceAmount);
		return jdbcUser.update(sql, parameter) > 0;
	}

	@Override
	public boolean addActivity(String userId, int addAmount, Date addTime) {
		String sql = "update user set activity = activity + ?";
		SqlParameter parameter = new SqlParameter();
		parameter.setInt(addAmount);
		if (addTime != null) {
			sql = sql + ",activity_resum_time=?";
			parameter.setObject(addTime);
		}
		sql = sql + " where user_id=? limit 1";
		parameter.setString(userId);
		return jdbcUser.update(sql, parameter) > 0;
	}

	@Override
	public boolean reduceActivity(String userId, int reduceAmount) {
		String sql = "update user set activity = activity - ?";
		sql = sql + " where user_id = ? and activity - ? >= 0 limit 1";
		SqlParameter parameter = new SqlParameter();
		parameter.setInt(reduceAmount);
		parameter.setString(userId);
		parameter.setInt(reduceAmount);
		return jdbcUser.update(sql, parameter) > 0;
	}

	@Override
	public boolean updateVIPLevel(String userId, int VIPLevel) {
		String sql = "UPDATE user set vip_level = ? WHERE user_id = ?";
		SqlParameter parameter = new SqlParameter();
		parameter.setInt(VIPLevel);
		parameter.setString(userId);
		return this.jdbcUser.update(sql, parameter) > 0;
	}

	@Override
	public boolean addPrestigeAndPrestigeLevel(String userId, int prestige,
			int prestigeLevel) {
		if (prestige < 0 || prestigeLevel < 0) {
			throw new RuntimeException("增加的prestige必须大于0");
		}
		String sql = "update user set prestige=prestige+?,prestige_level=prestige_level+? where user_id=?";
		SqlParameter parameter = new SqlParameter();
		parameter.setInt(prestige);
		parameter.setInt(prestigeLevel);
		parameter.setString(userId);
		return jdbcUser.update(sql, parameter) > 0;
	}

	@Override
	public boolean reducePrestige(String userId, int reduceAmount) {
		if (reduceAmount < 0) {
			throw new RuntimeException("减的的prestige必须大于0");
		}
		String sql = "update user set prestige=prestige-? where user_id=? and prestige-?>=0 limit 1";
		SqlParameter parameter = new SqlParameter();
		parameter.setInt(reduceAmount);
		parameter.setString(userId);
		parameter.setInt(reduceAmount);
		return jdbcUser.update(sql, parameter) > 0;
	}

	@Override
	public boolean addHonour(String userId, int addAmount) {
		if (addAmount < 0) {
			throw new RuntimeException("增加的honour必须大于0");
		}
		String sql = "update user set honour=honour+? where user_id=?";
		SqlParameter parameter = new SqlParameter();
		parameter.setInt(addAmount);
		parameter.setString(userId);
		return jdbcUser.update(sql, parameter) > 0;
	}

	@Override
	public boolean reduceHonour(String userId, int reduceAmount) {
		if (reduceAmount < 0) {
			throw new RuntimeException("减的的honour必须大于0");
		}
		String sql = "update user set honour=honour-? where user_id=? and honour-?>=0 limit 1";
		SqlParameter parameter = new SqlParameter();
		parameter.setInt(reduceAmount);
		parameter.setString(userId);
		parameter.setInt(reduceAmount);
		return jdbcUser.update(sql, parameter) > 0;
	}

	@Override
	public boolean isExitName(String name) {
		String sql = "select user_id from user where name=? limit 1";
		SqlParameter parameter = new SqlParameter();
		parameter.setString(name);
		return jdbcUser.get(sql, User.class, parameter) != null;
	}

	@Override
	public List<String> getAllUserIds() {
		String sql = "select user_id from user";
		return jdbcUser.getList(sql, String.class);
	}

	@Override
	public User getByUserName(String name) {
		String sql = "select * from user where name = ? limit 1";
		SqlParameter param = new SqlParameter();
		param.setString(name);
		
		return this.jdbcUser.get(sql, User.class, param);
	}

	@Override
	public boolean updateUserName(String userId, String name) {
		String sql = "update user set name = ? where user_id = ?";
		SqlParameter param = new SqlParameter();
		param.setString(name);
		param.setString(userId);
		
		return this.jdbcUser.update(sql, param) > 0;
	}

	@Override
	public List<String> getBetweenRegTimeList(Date startTime, Date endTime) {
		String sql ="select * from user where reg_time >= ? and reg_time <= ?";
		SqlParameter param = new SqlParameter();
		param.setObject(startTime);
		param.setObject(endTime);
		
		List<String> userIdList = Lists.newArrayList();
		List<User> userList = this.jdbcUser.getList(sql, User.class, param);
		if (userList != null && userList.size() > 0) {
			for (User user : userList) {
				userIdList.add(user.getUserId());
			}
		}			
		
		return userIdList;
	}

	@Override
	public List<User> getJoinBattleUserList(int level, int camp) {
		String sql = "select * from user where camp = ? and level between ? and ? limit 100";
		SqlParameter param = new SqlParameter();
		param.setInt(camp);
		if (level - 5 <= 0) {
			param.setInt(1);
			param.setInt(10);
		} else {
			param.setInt(level - 5);
			param.setInt(level + 5);
		}			
		
		return this.jdbcUser.getList(sql, User.class, param);
	}

	@Override
	public List<User> getUserListByFloatLevel(int minLevel, int maxLevel) {
		String sql = "select * from user where level between ? and ? limit 50";
		SqlParameter param = new SqlParameter();
		param.setInt(minLevel);
		param.setInt(maxLevel);
		
		return this.jdbcUser.getList(sql, User.class, param);
	}

	@Override
	public List<User> getUserRank(String columnName, int limit) {
		String sql = "select * from user order by " + columnName + " desc limit ?";
		SqlParameter param = new SqlParameter();
		param.setInt(limit);
		
		return this.jdbcUser.getList(sql, User.class, param);
	}

	@Override
	public int getUserRank(int value, String columnName) {
		String sql = "select count(*) from user where " + columnName + " >= ? order by reg_time asc";
		SqlParameter param = new SqlParameter();
		param.setInt(value);
		
		return this.jdbcUser.getInt(sql, param);
	}
}
