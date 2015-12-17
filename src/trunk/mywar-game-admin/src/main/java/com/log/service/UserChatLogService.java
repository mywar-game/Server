package com.log.service;

import java.util.ArrayList;
import java.util.List;

import com.framework.common.DBSource;
import com.framework.common.IPage;
import com.log.bo.UserChatLog;
import com.log.dao.UserChatLogDao;

public class UserChatLogService {
	
	private UserChatLogDao userChatLogDao;

	public UserChatLogDao getUserChatLogDao() {
		return userChatLogDao;
	}

	public void setUserChatLogDao(UserChatLogDao userChatLogDao) {
		this.userChatLogDao = userChatLogDao;
	}

	/**
	 * 分页查询
	 * @param currentPage 页面显示总数
	 * @param pageSize 页数
	 * @return 返回一页列表
	 */
	public IPage<UserChatLog> findUserChatLogByPage(Integer currentPage, Integer pageSize) {
		userChatLogDao.closeSession(DBSource.LOG);
		IPage<UserChatLog> userChatLogList =  userChatLogDao.findPage("select new UserChatLog(ucl.chatType,ucl.chatMessage,ucl.chatObject,ucl.chatTime,ucl.systemNum,user.userName,user.name) from UserChatLog ucl , User user where user.userId = ucl.chatObject and date_add(chat_Time,interval 1 day) > curdate()", new ArrayList<Object>(), pageSize, currentPage);
		return userChatLogList;
	}
	
	/**
	 * 查询所有
	 * @return 所有聊天记录
	 */
	public List<UserChatLog> findUserChatLogAll() {
		userChatLogDao.closeSession(DBSource.LOG);
		List<UserChatLog> userChatLogList = userChatLogDao.find("from UserChatLog where date_add(chat_Time,interval 14 day) > curdate()", new ArrayList<Object>());
		return userChatLogList;
	}
	
	/**
	 * 分页查询
	 * @return
	 */
	public  List<Object> findUserChatLogList() {
		userChatLogDao.closeSession(DBSource.LOG);
		List<Object> userChatLogList =  userChatLogDao.findSQL_("select ucl.chat_Type,ucl.chat_Message,ucl.chat_Object,ucl.chat_Time,ucl.system_Num,user.user_Name,user.name from user_chat_log ucl , user user where user.user_Id = ucl.chat_object and date_add(chat_Time,interval 1 day) > curdate()");
		return userChatLogList;
	}


	/**
	 * 根据玩家ID查询玩家聊天记录
	 * @param currentPage
	 * @param pageSize
	 * @param querySql
	 * @return
	 */
	public List<Object> findUserChatLogByUser(String querySql) {
		StringBuffer string = new StringBuffer();
		string.append("select ucl.chat_Type,ucl.chat_Message,ucl.chat_Object,ucl.chat_Time,ucl.system_Num,user.user_Name,user.name");
		string.append(" from user_chat_log ucl, `user` user ");
		string.append(" where 1 = 1 ");
		string.append(querySql);
		string.append(" and ucl.chat_Object = user.user_Id and date_add(chat_Time,interval 1 day) > curdate()");
		userChatLogDao.closeSession(DBSource.LOG);
		List<Object> userChatLogList = userChatLogDao.findSQL_(new String(string));
		return userChatLogList;
	}
	
	/**
	 * 处理玩家聊天操作信息
	 * @param userId 玩家ID
	 * @param type 操作类型(1.禁言2.封号3.踢线)
	 */
//	public  void  savePlayerTreatment(Long userId , Integer type, ErrorCode errorCode) {
//		ResTreatmentUserChat resTreatmentUserChat = new ResTreatmentUserChat();
//		resTreatmentUserChat.setUserId(userId+"");
//		resTreatmentUserChat.setType(type);
//		Calendar calendar = Calendar.getInstance();
//		if (type == 1) {
//			//禁言1小时
//			calendar.add(Calendar.HOUR_OF_DAY, 1);
//		} else if (type == 2) {
//			//封号3天
//			calendar.add(Calendar.DAY_OF_YEAR, 3);
//		}
//		resTreatmentUserChat.setStateFinishTime(calendar.getTime().getTime() + "");
//		//发送到平台服务器
//		Msg msg = HttpServersBridge.connectServerToExecuteTask(AdminToolCMDCode.TREATMENT_USER_CHAT, resTreatmentUserChat, CommomMsgBody.class);
//		if (msg.getMsgHead().getErrorCode() != SystemConstant.SUCCESS_CODE) {
//			CommomMsgBody commomMsgBody = (CommomMsgBody) msg.getMsgBody();
//			errorCode.setErrorCode(commomMsgBody.getErrorCode());
//			errorCode.setErrorDisc(commomMsgBody.getErrorDescription()+" 操作失败！");
//		} else {
//			errorCode.setErrorDisc("操作成功");
//		}
//		return;
//	}

}
