package com.adminTool.service;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

import com.adminTool.bo.AdminIssueDiamondLog;
import com.adminTool.bo.User;
import com.adminTool.bo.UserType;
import com.adminTool.constant.AdminToolCMDCode;
import com.adminTool.dao.UserTypeDao;
import com.adminTool.msgbody.ReqAddGolden;
import com.framework.client.http.HttpServersBridge;
import com.framework.common.CommomMsgBody;
import com.framework.common.DBSource;
import com.framework.constant.SystemConstant;
import com.framework.log.LogSystem;
import com.framework.server.msg.Msg;
import com.framework.servicemanager.CustomerContextHolder;
import com.framework.servicemanager.ServiceCacheFactory;
import com.log.bo.UserLevelupLog;
import com.log.service.UserLevelupLogService;

public class UserTypeService {
	
	private UserTypeDao userTypeDao;
	
	/**
	 * 保存或者更新玩家类型
	 * @param userType
	 */
	public void saveOrUpdateUserType(UserType userType){
		if (this.isExistUserTypeByUserIdAndSysnum(userType)) {
			StringBuffer sb = new StringBuffer();
			sb.append("update user_type set type = ").append(userType.getType()).append(" where SYS_NUM = ").append(userType.getSysNum()).append(" and USER_ID = ").append(userType.getUserId());
			userTypeDao.closeSession(DBSource.ADMIN);
			userTypeDao.executeSQL_(sb.toString());
		} else {
			userTypeDao.save(userType, DBSource.ADMIN);
		}
	}
	
	/**
	 * 指定服务器的指定user是否是测试账号
	 * @param userType 
	 * @return 
	 */
	public boolean isExistUserTypeByUserIdAndSysnum(UserType userType){
		StringBuffer sb = new StringBuffer();
		sb.append("select t.USER_ID from user_type t where t.SYS_NUM = ").append(userType.getSysNum()).append(" and t.USER_ID = ").append(userType.getUserId());
//		System.out.println(" == "+ sb.toString());
		userTypeDao.closeSession(DBSource.ADMIN);
		List<Object> list = userTypeDao.findSQL_(sb.toString());
		if (list == null || list.size() == 0) {
			return false;
		}
		return true;
	}

	/**
	 * 此方法定时运行，如果在间隔期间升到整10级，奖等级*10
	 */
	public void issueDiamondForTestUser(){
		StringBuffer sb = new StringBuffer();
		sb.append("select t.USER_ID from user_type t where t.type = 2 and t.sys_num = " + CustomerContextHolder.getSystemNum());
//		System.out.println(" == "+ sb.toString());
		userTypeDao.closeSession(DBSource.ADMIN);
		List<Object> list = userTypeDao.findSQL_(sb.toString());
		if (list == null || list.size() == 0) {
			return;
		}
		for (int i = 0; i < list.size(); i++) {
			Object obj= (Object)list.get(i);
			String userId = obj.toString();
			UserLevelupLogService userLevelupLogService = ServiceCacheFactory.getServiceCache().getService(UserLevelupLogService.class);
			//上次执行时间
			Calendar c = Calendar.getInstance();
			c.setTimeInMillis(System.currentTimeMillis()-24*60*60*1000);
			c.set(Calendar.SECOND, 0);
	        c.set(Calendar.MILLISECOND, 0);
	        
			List<UserLevelupLog> logList = userLevelupLogService.findTenLevelLogByUserIdAndTime(userId, c.getTime());
			
			if (logList != null && logList.size() > 0) {
				for (UserLevelupLog userLevelupLog : logList) {
					ReqAddGolden reqAddGolden = new ReqAddGolden();
					reqAddGolden.setGolden(userLevelupLog.getLevel() * 100);
					reqAddGolden.setUserId(userId+"");
					Msg msg = HttpServersBridge.connectServerToExecuteTask(AdminToolCMDCode.ADD_USER_GOLDEN, reqAddGolden, CommomMsgBody.class);
					if (msg.getMsgHead().getErrorCode() != SystemConstant.SUCCESS_CODE) {
						CommomMsgBody commomMsgBody = (CommomMsgBody) msg.getMsgBody();
						LogSystem.warn(commomMsgBody.getErrorDescription()+" 给userId:"+userId+"发放"+reqAddGolden.getGolden()+"金币失败！");
					}
					
					UserService userService = ServiceCacheFactory.getServiceCache().getService(UserService.class);
					User user = userService.findUserByUserId(userId);
					AdminIssueDiamondLogService adminIssueDiamondLogService = ServiceCacheFactory.getServiceCache().getService(AdminIssueDiamondLogService.class);
					AdminIssueDiamondLog adminIssueDiamondLog = new AdminIssueDiamondLog();
					adminIssueDiamondLog.setSysNum(CustomerContextHolder.getSystemNum());
					adminIssueDiamondLog.setAdminName("系统");
					adminIssueDiamondLog.setIssueTime(new Timestamp(System.currentTimeMillis()));
					adminIssueDiamondLog.setReceiveUser(userId + "_" + user.getName());
					adminIssueDiamondLog.setNum(userLevelupLog.getLevel() * 10);
					adminIssueDiamondLog.setIssueReason("测试号");
					adminIssueDiamondLogService.saveAdminIssueDiamondLog(adminIssueDiamondLog);
				}
			}
		}
	}
	
	/**
	 * 查找指定服务器的测试号的玩家编号集合（格式userId1,userId2）
	 * @param sysNum
	 * @return
	 */
	public String findUserIdsBySysNum(Integer sysNum){
		//SELECT GROUP_CONCAT(USER_ID) FROM user_type WHERE type = 2 AND SYS_NUM = 1001
		StringBuffer userIds = new StringBuffer();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT USER_ID FROM user_type WHERE type = 2 AND SYS_NUM = ").append(sysNum);
		userTypeDao.closeSession(DBSource.ADMIN);
		List<Object> list = userTypeDao.findSQL_(sql.toString());
		if (list != null && list.size() > 0) {
			if (userIds== null || userIds.length() == 0) {
				userIds.append(list.get(0).toString());
			} else {
				userIds.append(",").append(list.get(0).toString());
			}
		}
		return userIds.toString();
	}
	
	/**
	 * @return the userTypeDao
	 */
	public UserTypeDao getUserTypeDao() {
		return userTypeDao;
	}

	/**
	 * @param userTypeDao the userTypeDao to set
	 */
	public void setUserTypeDao(UserTypeDao userTypeDao) {
		this.userTypeDao = userTypeDao;
	}
}
