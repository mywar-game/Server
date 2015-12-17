package com.log.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.framework.common.DBSource;
import com.framework.common.IPage;
import com.framework.log.LogSystem;
import com.log.bo.UserTechnologyPointLog;
import com.log.dao.UserTechnologyPointLogDao;

public class UserTechnologyPointLogService {
	
	private UserTechnologyPointLogDao userTechnologyPointLogDao;
	
	public IPage<UserTechnologyPointLog> findPageList(String userId, Integer searchCategory, Integer searchType, Date startDate, Date endDate, int pageSize, int pageIndex) {
		StringBuffer hql = new StringBuffer();
		List<Object> args = new ArrayList<Object>();
		hql.append("select new UserTechnologyPointLog(log.userId, u.name, log.category, log.type, log.note, log.changeNum, log.finalNum, log.createTime) from UserTechnologyPointLog log, User u WHERE log.userId = u.userId");
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
		userTechnologyPointLogDao.closeSession(DBSource.LOG);
		return userTechnologyPointLogDao.findPage(hql.toString(), args, pageSize, pageIndex);
	}
	
	/**
	 * 测试科技点数据是否正确
	 * @param dates
	 * @throws Exception
	 */
	public void testPoint(String[] dates) throws Exception {
		String hql = new String();
		if (dates != null) {
			hql = "from UserTechnologyPointLog where createTime between '"+dates[0]+"' and '"+dates[1]+"' order by userId,createTime,logId";
		} else {
			hql = "from UserTechnologyPointLog order by userId,createTime,logId";
		}
		List<UserTechnologyPointLog> userTechnologyPointLogList = userTechnologyPointLogDao.find(hql, DBSource.LOG);
		
		for (int i = 1; i < userTechnologyPointLogList.size(); i++) {
			UserTechnologyPointLog Log = userTechnologyPointLogList.get(i);
			UserTechnologyPointLog lastLog = userTechnologyPointLogList.get(i-1); 
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
//				ReqUserInfo reqUserInfo = new ReqUserInfo(); 
//				reqUserInfo.setUserId(lastLog.getUserId()+"");
//				Msg msg = HttpServersBridge.connectServerToExecuteTask(AdminToolCMDCode.GET_USER_INFO, reqUserInfo, UserSomeInfo.class);
//				if (msg.getMsgHead().getErrorCode() != SystemConstant.SUCCESS_CODE) {
//					CommomMsgBody commomMsgBody = (CommomMsgBody) msg.getMsgBody();
//					LogSystem.warn(commomMsgBody.getErrorDescription()+" 查看用户信息失败！");
//				}
//				UserSomeInfo userSomeInfo = (UserSomeInfo)msg.getMsgBody();
//				if ( userSomeInfo.getUserTechnologyPoint() != lastLog.getFinalNum().intValue()) {
//					LogSystem.warn("userId: "+lastLog.getUserId()+" 日志科技点： "+lastLog.getFinalNum().intValue()+"  游戏科技点: "+userSomeInfo.getGolden());
//				}
			}
			
		}
	}

	public void setUserTechnologyPointLogDao(UserTechnologyPointLogDao userTechnologyPointLogDao) {
		this.userTechnologyPointLogDao = userTechnologyPointLogDao;
	}

	public UserTechnologyPointLogDao getUserTechnologyPointLogDao() {
		return userTechnologyPointLogDao;
	}

}
