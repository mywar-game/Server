package com.adminTool.service;

import java.util.ArrayList;
import java.util.List;

import com.adminTool.bo.UserRankSnapshot;
import com.adminTool.dao.UserRankSnapshotDao;
import com.framework.common.DBSource;
import com.framework.servicemanager.CustomerContextHolder;

public class UserRankSnapshotService {

	private UserRankSnapshotDao userRankSnapshotDao;
	
	/**
	 * 保存数据
	 * @param userRankSnapshotList
	 */
	public void saveUserRankSnapshotList(List<UserRankSnapshot> userRankSnapshotList) {
		if (userRankSnapshotList != null && userRankSnapshotList.size() > 0) {
			for (UserRankSnapshot userRankSnapshot : userRankSnapshotList) {
				userRankSnapshotDao.save(userRankSnapshot, DBSource.ADMIN);
			}
		}
	}
	
	/**
	 * 列表
	 * @param pageSize
	 * @param currentPage
	 * @return
	 */
	public List<UserRankSnapshot> findList() {
		userRankSnapshotDao.closeSession(DBSource.ADMIN);
		return userRankSnapshotDao.find("from UserRankSnapshot stats where stats.sysNum = " + CustomerContextHolder.getSystemNum(), DBSource.ADMIN);
	}
	
	/**
	 * 列表
	 * @param pageSize
	 * @param currentPage
	 * @return
	 */
	public List<UserRankSnapshot> findListInIds(String ids){
		userRankSnapshotDao.closeSession(DBSource.ADMIN);
		return userRankSnapshotDao.find("from UserRankSnapshot a where a.sysNum = " + CustomerContextHolder.getSystemNum()+" and a.id in ("+ids+")", DBSource.ADMIN);
	}

	/**
	 * 删除玩家快照排行
	 * @param ids
	 */
	public void delUserRankSnapshot(String ids){
		userRankSnapshotDao.closeSession(DBSource.ADMIN);
		userRankSnapshotDao.execute("delete from UserRankSnapshot where id in ("+ids+")", new ArrayList<Object>());
	}
	
	public void setUserRankSnapshotDao(UserRankSnapshotDao userRankSnapshotDao) {
		this.userRankSnapshotDao = userRankSnapshotDao;
	}

	public UserRankSnapshotDao getUserRankSnapshotDao() {
		return userRankSnapshotDao;
	}
}
