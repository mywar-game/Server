package com.log.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.framework.common.DBSource;
import com.framework.common.IPage;
import com.framework.util.DateUtil;
import com.log.bo.UserArtifactLog;
import com.log.dao.UserArtifactLogDao;
import com.system.manager.DataSourceManager;

/**
 * 用户神器操作日志服务层
 * 
 * @author yezp
 */
public class UserArtifactLogService {

	private UserArtifactLogDao userArtifactLogDao;

	public UserArtifactLogDao getUserArtifactLogDao() {
		return userArtifactLogDao;
	}

	public void setUserArtifactLogDao(UserArtifactLogDao userArtifactLogDao) {
		this.userArtifactLogDao = userArtifactLogDao;
	}

	/**
	 * 根据条件分页查找神器操作日志
	 * 
	 * @param userId
	 * @param startDate
	 * @param endDate
	 * @param searchType
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public IPage<Object> findPageLogListByCondition(String userId,
			Date startDate, Date endDate, Integer searchType,
			Integer currentPage, Integer pageSize) {
		DataSourceManager manager = DataSourceManager.getInstatnce();
		Date[] arr = DateUtil.dayStrDiff(startDate, endDate);
		StringBuffer sql = new StringBuffer();
		StringBuffer queryStr = new StringBuffer();
		if (userId != null) {
			queryStr.append(" and USER_ID = '").append(userId).append("'");
		}
		if (searchType != null) {
			queryStr.append(" and OP_TYPE = ").append(searchType);
		}
		if (startDate != null && endDate != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			queryStr.append(" and CREATED_TIME BETWEEN '")
					.append(sdf.format(startDate)).append("' AND '")
					.append(sdf.format(endDate)).append("'");
		}
		queryStr.append(" order by CREATED_TIME DESC");
		String items = "USER_ARTIFACT_LOG_ID, USER_ID, USER_ARTIFACT_ID, ARTIFACT_ID, ARTIFACT_ATT_STR, NUM, OP_TYPE, OP_SMALL_TYPE, CREATED_TIME";
		sql.append(manager.getUnionSql("user_artifact_log", arr, queryStr,
				items));
		userArtifactLogDao.closeSession(DBSource.LOG);
		IPage<Object> userArtifactLogList = userArtifactLogDao
				.findSQL_PageUnion(new String(sql), new ArrayList<Object>(),
						pageSize, currentPage);
		return userArtifactLogList;
	}
	
	/**
	 * 查询获得神器
	 * @param type
	 * @return
	 */
	public List<UserArtifactLog> getListByOpType(int type) {
		userArtifactLogDao.closeSession(DBSource.LOG);
		List<Object> list = new ArrayList<Object>();
		list.add(type);
		return userArtifactLogDao.find("from UserArtifactLog", list);
	}
}
