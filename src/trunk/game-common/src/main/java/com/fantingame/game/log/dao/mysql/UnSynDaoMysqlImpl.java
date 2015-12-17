package com.fantingame.game.log.dao.mysql;


import org.springframework.beans.factory.annotation.Autowired;

import com.fantingame.common.jdbc.Jdbc;
import com.fantingame.game.log.dao.UnSynDao;
public class UnSynDaoMysqlImpl implements UnSynDao {
	@Autowired	
	private Jdbc jdbcLog;
	@Override
	public void executeLogSql(String sql) {
		jdbcLog.update(sql, null);
	}
	/**
	 * 批量执行sql
	 * @param sql
	 */
	@Override
	public int[] executeBatchLogSql(String[] sql){
		return jdbcLog.batchSql(sql);
	}
}
