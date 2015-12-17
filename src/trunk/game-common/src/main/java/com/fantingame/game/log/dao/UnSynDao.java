package com.fantingame.game.log.dao;


/**
 * 异步执行sql 存储数据库
 *
 */
public interface UnSynDao {
	/**
	 * 批量执行sql
	 * @param sql
	 */
	public int[] executeBatchLogSql(String[] sql);
	/**
	 * 日志库内执行sql
	 * @param sql
	 */
	public void executeLogSql(String sql);
}
