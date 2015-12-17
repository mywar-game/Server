package com.log.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.adminTool.msgbody.ReqUserInfo;
import com.framework.common.DBSource;
import com.framework.common.IPage;
import com.framework.log.LogSystem;
import com.log.bo.UserBuildingPointLog;
import com.log.dao.UserBuildingPointLogDao;

public class UserBuildingPointLogService {
	
	private UserBuildingPointLogDao userBuildingPointLogDao;
	
	public IPage<UserBuildingPointLog> findPageList(String userId, Integer searchCategory, Integer searchType, Date startDate, Date endDate, int pageSize, int pageIndex) {
		StringBuffer hql = new StringBuffer();
		List<Object> args = new ArrayList<Object>();
		hql.append("select new UserBuildingPointLog(log.userId, u.name, log.category, log.type, log.note, log.changeNum, log.finalNum, log.createTime) from UserBuildingPointLog log, User u WHERE log.userId = u.userId");
		if (userId != null) {
			hql.append(" and log.userId = ?");
			args.add(userId);
		}
		if (searchCategory != null) {
			hql.append(" and log.category = ?");
			args.add(searchCategory);
		}
		if (searchType != null) {
			hql.append(" and log.type = ?");
			args.add(searchType);
		}
		if (startDate != null && endDate != null) {
			hql.append(" and log.createTime between ? and ?");
			args.add(startDate);
			args.add(endDate);
		}
		userBuildingPointLogDao.closeSession(DBSource.LOG);
		return userBuildingPointLogDao.findPage(hql.toString(), args, pageSize, pageIndex);
	}

	/**
	 * 测试建筑点数据是否正确
	 * @param dates
	 * @throws Exception
	 */
	public void testPoint(String[] dates) throws Exception {
		String hql = new String();
		if (dates != null) {
			hql = "from UserBuildingPointLog where createTime between '"+dates[0]+"' and '"+dates[1]+"' order by userId,createTime,logId";
		} else {
			hql = "from UserBuildingPointLog order by userId,createTime,logId";
		}
		List<UserBuildingPointLog> userBuildingPointLogList = userBuildingPointLogDao.find(hql, DBSource.LOG);
		
		for (int i = 1; i < userBuildingPointLogList.size(); i++) {
			UserBuildingPointLog Log = userBuildingPointLogList.get(i);
			UserBuildingPointLog lastLog = userBuildingPointLogList.get(i-1); 
			//同一个玩家
			if (Log.getUserId().longValue() == lastLog.getUserId().longValue()) {
				//比较数据
				if (Log.getCategory() == 1) {
					if( (lastLog.getFinalNum()+Log.getChangeNum()) != Log.getFinalNum() ) {
						LogSystem.warn("userId: "+ lastLog.getUserId() +" 的logId： "+ lastLog.getLogId() + " " + Log.getLogId() + " 变化后的数据不对 数据：  " + lastLog.getFinalNum() + " " + Log.getFinalNum());
					}
				} else if (Log.getCategory() == 2) {
					if( (lastLog.getFinalNum()-Log.getChangeNum()) != Log.getFinalNum() ) {
						LogSystem.warn("userId: "+ lastLog.getUserId() +" 的logId： "+ lastLog.getLogId() + " " + Log.getLogId() + " 变化后的数据不对 数据：  " + lastLog.getFinalNum() + " " + Log.getFinalNum());
					}
				} else {
					LogSystem.warn("userId: "+ lastLog.getUserId() +" 的logId： "+ Log.getLogId()+" 的category不对 "+Log.getCategory());
				}
				//查看金币数是否能和游戏服务器对上
			} else {
				ReqUserInfo reqUserInfo = new ReqUserInfo(); 
				reqUserInfo.setUserId(lastLog.getUserId()+"");
			}
			
		}
	}
	
	public void setUserBuildingPointLogDao(UserBuildingPointLogDao userBuildingPointLogDao) {
		this.userBuildingPointLogDao = userBuildingPointLogDao;
	}

	public UserBuildingPointLogDao getUserBuildingPointLogDao() {
		return userBuildingPointLogDao;
	}

}
