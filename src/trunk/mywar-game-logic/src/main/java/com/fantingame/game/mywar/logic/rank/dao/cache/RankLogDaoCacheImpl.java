package com.fantingame.game.mywar.logic.rank.dao.cache;

import java.util.List;

import com.fantingame.game.common.dao.userdata.BaseCacheDao;
import com.fantingame.game.mywar.logic.rank.dao.RankLogDao;
import com.fantingame.game.mywar.logic.rank.dao.mysql.RankLogDaoMysqlImpl;
import com.fantingame.game.mywar.logic.rank.model.RankLog;
import com.google.common.collect.Lists;

public class RankLogDaoCacheImpl extends BaseCacheDao<List<RankLog>> implements RankLogDao {

	private RankLogDaoMysqlImpl rankLogDaoMysqlImpl;
	
	public RankLogDaoMysqlImpl getRankLogDaoMysqlImpl() {
		return rankLogDaoMysqlImpl;
	}

	public void setRankLogDaoMysqlImpl(RankLogDaoMysqlImpl rankLogDaoMysqlImpl) {
		this.rankLogDaoMysqlImpl = rankLogDaoMysqlImpl;
	}

	@Override
	protected List<RankLog> loadFromDb(String rankKey) {
		return this.rankLogDaoMysqlImpl.getRankLogList(rankKey);
	}

	@Override
	public List<RankLog> getRankLogList(String rankKey) {
		return super.getEntry(rankKey);
	}

	@Override
	public boolean addList(List<RankLog> rankList) {
		if (rankLogDaoMysqlImpl.addList(rankList)) {
			for (RankLog rankLog : rankList) {
				if (super.isExitKey(rankLog.getRankKey())) {
					super.getEntry(rankLog.getRankKey()).add(rankLog);
				}
				
				List<RankLog> list = Lists.newArrayList();
				list.add(rankLog);
				super.addEntry(rankLog.getRankKey(), list);
			}
			
			return true;
		}
		
		return false;
	}

	@Override
	public boolean deleteRankLog(String key) {
		if (this.rankLogDaoMysqlImpl.deleteRankLog(key)) {
			super.delete(key);
			
			return true;
		}
		
		return false;
	}

	@Override
	public boolean addRankLog(RankLog rankLog) {
		if (this.rankLogDaoMysqlImpl.addRankLog(rankLog)) {
			if (super.isExitKey(rankLog.getRankKey())) {
				super.getEntry(rankLog.getRankKey()).add(rankLog);
			}
			
			List<RankLog> list = Lists.newArrayList();
			list.add(rankLog);
			super.addEntry(rankLog.getRankKey(), list);
			
			return true;
		}
		
		return false;
	}
	
}
