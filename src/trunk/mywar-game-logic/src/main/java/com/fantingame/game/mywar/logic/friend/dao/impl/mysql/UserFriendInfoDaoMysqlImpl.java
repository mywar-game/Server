package com.fantingame.game.mywar.logic.friend.dao.impl.mysql;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.fantingame.common.jdbc.Jdbc;
import com.fantingame.common.jdbc.SqlParameter;
import com.fantingame.game.mywar.logic.friend.dao.UserFriendInfoDao;
import com.fantingame.game.mywar.logic.friend.model.UserFriendInfo;

public class UserFriendInfoDaoMysqlImpl implements UserFriendInfoDao {
	
	@Autowired
	private Jdbc jdbcUser;	
	private static final String table = "user_friend_info";

	@Override
	public List<UserFriendInfo> getUserFriendList(String userId, int status) {
		String sql = "select * from " + table + " where user_id = ? and status = ?";
		SqlParameter param = new SqlParameter();
		param.setString(userId);
		param.setInt(status);
		
		return this.jdbcUser.getList(sql, UserFriendInfo.class, param);
	}

	/**
	 * 获取用户所有的好友列表
	 * 
	 * @param userId
	 * @return
	 */
	public List<UserFriendInfo> getUserFriendInfoList(String userId) {
		String sql = "select * from " + table + " where user_id = ? order by updated_time asc";
		SqlParameter param = new SqlParameter();
		param.setString(userId);
		
		return this.jdbcUser.getList(sql, UserFriendInfo.class, param);
	}

	@Override
	public int getUserFriendCount(String userId) {
		String sql = "select count(*) from " + table + " where user_id = ?";
		SqlParameter param = new SqlParameter();
		param.setString(userId);
		
		return this.jdbcUser.getInt(sql, param);
	}

	@Override
	public UserFriendInfo getUserFriendInfo(String userId, String targetUserId) {
		String sql = "select * from " + table + " where user_id = ? and target_user_id = ?";
		SqlParameter param = new SqlParameter();
		param.setString(userId);
		param.setString(targetUserId);
		
		return this.jdbcUser.get(sql, UserFriendInfo.class, param);
	}

	@Override
	public boolean add(UserFriendInfo userFriendInfo) {
		return this.jdbcUser.insert(userFriendInfo) > 0;
	}

	@Override
	public boolean deleteFriend(String userId, String targetUserId) {
		String sql = "delete from " + table + " where (user_id = ? and user_friend_id = ?) or (user_friend_id = ? and user_id = ?)";
		SqlParameter param = new SqlParameter();
		param.setString(userId);
		param.setString(targetUserId);
		param.setString(userId);
		param.setString(targetUserId);
		
		return this.jdbcUser.update(sql, param) > 0;
	}

	@Override
	public boolean updateUserFriendStatus(UserFriendInfo info, int status) {
		String sql = "update " + table + " set status = ?, updated_time = now() where user_id = ? and user_friend_id = ?";
		SqlParameter param = new SqlParameter();
		param.setInt(status);
		param.setString(info.getUserId());
		param.setString(info.getUserFriendId());
		
		return this.jdbcUser.update(sql, param) > 0;
	}
	
}
