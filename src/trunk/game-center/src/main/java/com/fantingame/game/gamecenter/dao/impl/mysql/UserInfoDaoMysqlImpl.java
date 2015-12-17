package com.fantingame.game.gamecenter.dao.impl.mysql;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.fantingame.common.jdbc.Jdbc;
import com.fantingame.common.jdbc.SqlParameter;
import com.fantingame.game.gamecenter.dao.UserInfoDao;
import com.fantingame.game.gamecenter.model.UserInfo;

public class UserInfoDaoMysqlImpl implements UserInfoDao {

	@Autowired
	private Jdbc jdbc;
	
	private final static String table = "user_info";
	
	@Override
	public UserInfo getUserInfo(String partnerUserId, String serverId) {
		String sql = "select * from " + table + " where partner_user_id = ? and server_id = ?";
		SqlParameter param = new SqlParameter();
		param.setString(partnerUserId);
		param.setString(serverId);
		
		return this.jdbc.get(sql, UserInfo.class, param);
	}

	@Override
	public boolean save(UserInfo userInfo) {		
		return this.jdbc.insert(userInfo) > 0;
	}

	@Override
	public boolean updateUserInfo(UserInfo userInfo) {
		String sql = "update " + table + " set updated_time = now() where partner_user_id = ? and server_id = ?";
		SqlParameter param = new SqlParameter();
		param.setString(userInfo.getPartnerUserId());
		param.setString(userInfo.getServerId());
		
		return this.jdbc.update(sql, param) > 0;
	}

	@Override
	public List<UserInfo> getUserInfoList(String partnerUserId) {
		String sql = "select * from " + table + " where partner_user_id = ? order by updated_time desc limit 2";
		SqlParameter param = new SqlParameter();
		param.setString(partnerUserId);
		
		return this.jdbc.getList(sql, UserInfo.class, param);
	}
	
}
