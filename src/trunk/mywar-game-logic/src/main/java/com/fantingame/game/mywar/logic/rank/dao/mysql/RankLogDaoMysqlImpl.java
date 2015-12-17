package com.fantingame.game.mywar.logic.rank.dao.mysql;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.fantingame.common.jdbc.Jdbc;
import com.fantingame.common.jdbc.SqlParameter;
import com.fantingame.game.mywar.logic.rank.dao.RankLogDao;
import com.fantingame.game.mywar.logic.rank.model.RankLog;

public class RankLogDaoMysqlImpl implements RankLogDao {

	@Autowired
	private Jdbc jdbcUser;
	
	private final String table = "rank_log"; 
	
	@Override
	public List<RankLog> getRankLogList(String rankKey) {
		String sql = "select * from " + table + " where rank_key = ?"; 
		SqlParameter param = new SqlParameter();
		param.setString(rankKey);
		
		return this.jdbcUser.getList(sql, RankLog.class, param);
	}

	@Override
	public boolean addList(List<RankLog> rankList) {
		return this.jdbcUser.insert(rankList).length > 0;
	}

	@Override
	public boolean deleteRankLog(String key) {
		String sql = "delete from " + table + " where rank_key = ?";
		SqlParameter param = new SqlParameter();
		param.setString(key);
		
		return this.jdbcUser.update(sql, param) > 0;
	}

	@Override
	public boolean addRankLog(RankLog rankLog) {
		return this.jdbcUser.insert(rankLog) > 0;
	}

}
