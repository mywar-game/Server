package com.fantingame.game.mywar.logic.legion.dao.mysql;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.fantingame.common.jdbc.Jdbc;
import com.fantingame.common.jdbc.SqlParameter;
import com.fantingame.game.mywar.logic.legion.dao.UserMessageInfoDao;
import com.fantingame.game.mywar.logic.legion.model.UserMessageInfo;

public class UserMessageInfoDaoMysqlImpl implements UserMessageInfoDao {

	@Autowired
	private Jdbc jdbcUser;
	
	private final static String tableName = "user_message_info";

	@Override
	public List<UserMessageInfo> getUserMessageInfoList(String legionId) {
		String sql = "select * from " + tableName + " where legion_id = ?";
		SqlParameter param = new SqlParameter();
		param.setString(legionId);
		
		return this.jdbcUser.getList(sql, UserMessageInfo.class, param);
	}

	@Override
	public boolean addUserMessageInfo(UserMessageInfo userMessageInfo) {
		return this.jdbcUser.insert(userMessageInfo) > 0;
	}

	@Override
	public boolean deleteUserMessageInfo(String legionId) {
		String sql = "delete from " + tableName + " where legion_id = ?";
		SqlParameter param = new SqlParameter();
		param.setString(legionId);
		
		return this.jdbcUser.update(sql, param) > 0;
	}

	@Override
	public List<UserMessageInfo> getUserMessageInfoList(String legionId, Date endTime) {
		throw new RuntimeException("没有实现，请调用cache中的方法");
	}
	
}
