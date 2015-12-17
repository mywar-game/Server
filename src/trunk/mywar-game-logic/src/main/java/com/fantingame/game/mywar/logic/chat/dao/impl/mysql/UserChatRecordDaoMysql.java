package com.fantingame.game.mywar.logic.chat.dao.impl.mysql;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.fantingame.common.jdbc.Jdbc;
import com.fantingame.common.jdbc.SqlParameter;
import com.fantingame.game.mywar.logic.chat.model.UserChatRecord;

public class UserChatRecordDaoMysql {

	@Autowired
	private Jdbc jdbcUser;
	
	private static final String table = "user_chat_record";
	
	/**
	 * 获取聊天记录
	 * 
	 * @param type
	 * @param limit
	 * @return
	 */
	public List<UserChatRecord> getUserChatRecord(int type, int limit) {
		String sql = "select * from " + table + " where type = ? order by updated_time desc limit ?";
		SqlParameter param = new SqlParameter();
		param.setInt(type);
		param.setInt(limit);
		
		return this.jdbcUser.getList(sql, UserChatRecord.class, param);
	}
	
	/**
	 * 获取阵营聊天记录
	 * 
	 * @param camp
	 * @param type
	 * @param limit
	 * @return
	 */
	public List<UserChatRecord> getCampChatRecord(int camp, int type, int limit) {
		String sql = "select * from " + table + " where camp = ? and type = ? order by updated_time desc limit ?";
		SqlParameter param = new SqlParameter();
		param.setInt(camp);
		param.setInt(type);
		param.setInt(limit);
		
		return this.jdbcUser.getList(sql, UserChatRecord.class, param);
	}
	
	/**
	 * 添加聊天记录
	 * 
	 * @param userChatRecord
	 * @return
	 */
	public boolean add(UserChatRecord userChatRecord) {
		return this.jdbcUser.insert(userChatRecord) > 0;
	}
	
}
