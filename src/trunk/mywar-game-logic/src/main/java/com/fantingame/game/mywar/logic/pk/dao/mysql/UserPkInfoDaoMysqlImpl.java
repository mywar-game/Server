package com.fantingame.game.mywar.logic.pk.dao.mysql;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.fantingame.common.jdbc.Jdbc;
import com.fantingame.common.jdbc.SqlParameter;
import com.fantingame.game.mywar.logic.pk.dao.UserPkInfoDao;
import com.fantingame.game.mywar.logic.pk.model.UserPkInfo;

public class UserPkInfoDaoMysqlImpl implements UserPkInfoDao {
	
	@Autowired
	private Jdbc jdbcUser;
	
	private final static String table = "user_pk_info";

	@Override
	public UserPkInfo getUserPkInfo(String userId) {
		String sql = "select * from " + table + " where user_id = ?";
		SqlParameter param = new SqlParameter();
		param.setString(userId);
		
		return this.jdbcUser.get(sql, UserPkInfo.class, param);
	}

	@Override
	public UserPkInfo getLastUserPkInfo() {
		String sql = "select * from " + table + " order by rank desc limit 1";
		return this.jdbcUser.get(sql, UserPkInfo.class, new SqlParameter());
	}

	@Override
	public boolean addUserPkInfo(UserPkInfo userPkInfo) {
		return this.jdbcUser.insert(userPkInfo) > 0;
	}

	@Override
	public List<UserPkInfo> findChallengerList(int minRank, int maxRank, String userId) {
		String sql = "select * from " + table + " where user_id <> ? and rank between ? and ? order by rank desc";
		SqlParameter param = new SqlParameter();
		param.setString(userId);
		param.setInt(maxRank);
		param.setInt(minRank);
		
		return this.jdbcUser.getList(sql, UserPkInfo.class, param);
	}

	@Override
	public boolean updateUserPkInfo(String userId, int hasChallengeTimes, int buyChallengeTimes, int isReset) {
		String sql = "update " + table + " set has_challenge_times = ?, buy_challenge_times = ?, is_reset = ?, updated_time = now() where user_id = ?";
		SqlParameter param = new SqlParameter();
		param.setInt(hasChallengeTimes);
		param.setInt(buyChallengeTimes);
		param.setInt(isReset);
		param.setString(userId);
		
		return this.jdbcUser.update(sql, param) > 0;
	}

	@Override
	public boolean resetUserWaitingTime(String userId, int isReset) {
		String sql = "update " + table + " set is_reset = ? where user_id = ?";
		SqlParameter param = new SqlParameter();
		param.setInt(isReset);
		param.setString(userId);
		
		return this.jdbcUser.update(sql, param) > 0;
	}

	@Override
	public List<UserPkInfo> getRankList() {
		String sql = "select * from " + table + " order by rank asc limit 100";		
		return this.jdbcUser.getList(sql, UserPkInfo.class);
	}

	@Override
	public boolean updateUserPkInfo(String userId, int challengeTimes, int isReset, Date attackTime) {
		String sql = "update " + table + " set has_challenge_times = ?, is_reset = ?, updated_time = ? where user_id = ?";
		SqlParameter param = new SqlParameter();
		param.setInt(challengeTimes);
		param.setInt(isReset);
		param.setObject(attackTime);
		param.setString(userId);
		
		return this.jdbcUser.update(sql, param) > 0;
	}

	@Override
	public boolean changeRank(String userId, String targetUserId, int hasChallengeTimes, int isReset, int highestRank, Date attackTime) {
		String sql = "update user_pk_info a, user_pk_info b set a.rank = b.rank, b.rank = a.rank, "
				+ "a.has_challenge_times = ?, a.is_reset = ?, a.highest_rank = ?, a.updated_time = ? where a.user_id = ? and b.user_id = ?";
		SqlParameter param = new SqlParameter();
		param.setInt(hasChallengeTimes);
		param.setInt(isReset);
		param.setInt(highestRank);
		param.setObject(attackTime);
		param.setString(userId);
		param.setString(targetUserId);
		
		return this.jdbcUser.update(sql, param) > 0;
	}

	@Override
	public boolean updateUserPkInfoBuyChallengeTimes(String userId, int buyChallengeTimes) {
		String sql = "update " + table + " set buy_challenge_times = ? where user_id = ?";
		SqlParameter param = new SqlParameter();
		param.setInt(buyChallengeTimes);
		param.setString(userId);
		
		return this.jdbcUser.update(sql, param) > 0;
	}

	@Override
	public int getUserPkInfoCount() {
		String sql = "select count(*) from " + table;		
		
		return this.jdbcUser.getInt(sql, new SqlParameter());
	}

	@Override
	public boolean addAll(List<UserPkInfo> infoList) {
		return this.jdbcUser.insert(infoList).length > 0;
	}
	
}
