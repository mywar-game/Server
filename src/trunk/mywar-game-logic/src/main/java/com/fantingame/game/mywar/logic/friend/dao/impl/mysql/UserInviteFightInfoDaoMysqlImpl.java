package com.fantingame.game.mywar.logic.friend.dao.impl.mysql;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.fantingame.common.jdbc.Jdbc;
import com.fantingame.common.jdbc.SqlParameter;
import com.fantingame.game.mywar.logic.friend.dao.UserInviteFightInfoDao;
import com.fantingame.game.mywar.logic.friend.model.UserInviteFightInfo;

public class UserInviteFightInfoDaoMysqlImpl implements UserInviteFightInfoDao {

	@Autowired
	private Jdbc jdbcUser;
	
	private final static String table = "user_invite_fight_info";

	@Override
	public List<UserInviteFightInfo> getUserInviteFightInfoList(String userId) {
		String sql = "select * from " + table + " where user_id = ?";
		SqlParameter param = new SqlParameter();
		param.setString(userId);
		
		return this.jdbcUser.getList(sql, UserInviteFightInfo.class, param);
	}

	@Override
	public boolean deleteInviteFightList(String userId) {
		String sql = "delete from " + table + " where user_id = ?";
		SqlParameter param = new SqlParameter();
		param.setString(userId);
		
		return this.jdbcUser.update(sql, param) > 0;
	}

	@Override
	public UserInviteFightInfo getUserInviteFightInfo(String userId, String userFriendId) {
		String sql = "select * from " + table + " where user_id = ? and invite_user_id = ?";
		SqlParameter param = new SqlParameter();
		param.setString(userId);
		param.setString(userFriendId);
		
		return this.jdbcUser.get(sql, UserInviteFightInfo.class, param);
	}

	@Override
	public boolean addUserInviteFightInfo(UserInviteFightInfo inviteFight) {
		return this.jdbcUser.insert(inviteFight) > 0;
	}
	
}
