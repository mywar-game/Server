package com.log.action;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import com.adminTool.bo.User;
import com.adminTool.constant.AdminToolCMDCode;
import com.dataconfig.bo.SSensitive;
import com.dataconfig.service.SensitiveService;
import com.framework.client.http.HttpServersBridge;
import com.framework.common.ALDAdminPageActionSupport;
import com.framework.common.CommomMsgBody;
import com.framework.common.IPage;
import com.framework.constant.SystemConstant;
import com.framework.server.msg.Msg;
import com.framework.servicemanager.ServiceCacheFactory;
import com.log.bo.UserChatLog;
import com.log.msgbody.ResTreatmentUserChat;
import com.log.service.UserChatLogService;
import com.opensymphony.xwork2.ModelDriven;

public class UserChatLogList extends ALDAdminPageActionSupport implements ModelDriven<User> {

	private static final long serialVersionUID = -5483078032373899688L;

	private List<UserChatLog> userChatLogList;
	
	private List<SSensitive> sensitiveList;
	
	private Long userID;
	
	private int operateType;
	
	private String words;
	
	private int type;
	
	private int pageNum;
	
	private int pageSize;
	
	private int sysNum;
	
	private User user = new User();
	
	@Override
	public User getModel() {
		return user;
	}
	
	public String execute() {
		//处理敏感词
		SensitiveService sensitiveService = ServiceCacheFactory.getServiceCache().getService(SensitiveService.class);
		
		if (words != null && !words.trim().equals("") && type != 0) {
			try {
				words = URLDecoder.decode(words, "UTF-8").trim();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			SSensitive sensitive = new SSensitive();
			sensitive.setWord(words);
			if (type == 1) {
				//添加
				sensitiveService.saveSensitiveWords(sensitive);
			} else if (type == 2) {
				//删除 
				sensitiveService.deleteSensitiveWords(sensitive);
			}
			return SUCCESS;
		}
	
		UserChatLogService userChatLogService = ServiceCacheFactory.getServiceCache().getService(UserChatLogService.class);

		//查询信息设置 设置页面显示长度
		int size = 20;
		if ((user.getName() == null  || user.getName().equals("")) && user.getUserId() == null
				 && (user.getUserName() == null  || user.getUserName().equals(""))) {
			//查询所有聊天信息
			List<Object> chatLogs = userChatLogService.findUserChatLogList();
			List<UserChatLog> allChatLogs = new ArrayList<UserChatLog>();
			for (Object object : chatLogs) {
				Object[] value = (Object[]) object;
				UserChatLog userChatLog = new UserChatLog();
				userChatLog.setChatType(Integer.parseInt(value[0].toString()));
				userChatLog.setChatMessage(value[1].toString());
				userChatLog.setChatObject(Long.valueOf(value[2].toString()));
				userChatLog.setChatTime((Timestamp) value[3]);
				userChatLog.setSystemNum(Integer.parseInt(value[4].toString()));
				userChatLog.setUserName(value[5].toString());
				userChatLog.setName(value[6].toString());
				
				allChatLogs.add(userChatLog);
			}
			Collections.reverse(allChatLogs);
			userChatLogList = allChatLogs;
			
			IPage<SSensitive> sensitives = sensitiveService.findSensitiveWords(super.getToPage(), 20);
			sensitiveList = sensitiveService.findSensitiveWords();
			if (sensitiveList != null && allChatLogs != null && allChatLogs.size() > 0) {
				dealWithSensitiveWord(sensitiveList, userChatLogList);
				//处理页面显示
				pageNum = Math.max(0, pageNum);
				pageSize = (int) Math.ceil((double) allChatLogs.size() / (double) size);
				pageNum = Math.min(pageNum, pageSize - 1);
				
				int length = Math.min((pageNum + 1) * size, allChatLogs.size());
				List<UserChatLog> list = new ArrayList<UserChatLog>();
				for (int i = pageNum * size; i < length; i++) {
					list.add(allChatLogs.get(i));
				}
				userChatLogList = list;
				//设置当前敏感词显示页面
				if (sensitives != null && sensitives.getData() != null) {
					sensitiveList = (List<SSensitive>) sensitives.getData();
					super.setTotalPage(sensitives.getTotalPage());
					super.setTotalSize(sensitives.getPageSize());
				}
			}
			return SUCCESS;
		}

		StringBuffer querySql = new StringBuffer();
		
		if (user.getName() != null && !user.getName().trim().equals("")) {
			querySql.append(" and user.name = '");
			String name = null;
			try {
				name = URLDecoder.decode(user.getName(), "UTF-8").trim();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			querySql.append(name);
			querySql.append("'");
		}
		
		if (user.getUserName() != null && !user.getUserName().equals("")) {
			querySql.append(" and user.user_Name = '");
			String userName = null;
			try {
				userName = URLDecoder.decode(user.getUserName(), "UTF-8").trim();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			querySql.append(userName);
			querySql.append("'");
		}
		
		if (user.getUserId() != null) {
			querySql.append(" and user.user_Id = ");
			querySql.append(user.getUserId());
		}
		
		
		List<Object> chatLogs = userChatLogService.findUserChatLogByUser(new String(querySql));
		List<UserChatLog> allChatLogs = new ArrayList<UserChatLog>();
		for (Object object : chatLogs) {
			Object[] value = (Object[]) object;
			UserChatLog userChatLog = new UserChatLog();
			userChatLog.setChatType(Integer.parseInt(value[0].toString()));
			userChatLog.setChatMessage(value[1].toString());
			userChatLog.setChatObject(Long.valueOf(value[2].toString()));
			userChatLog.setChatTime((Timestamp) value[3]);
			userChatLog.setSystemNum(Integer.parseInt(value[4].toString()));
			userChatLog.setUserName(value[5].toString());
			userChatLog.setName(value[6].toString());
			
			allChatLogs.add(userChatLog);
		}
		Collections.reverse(allChatLogs);
		userChatLogList = allChatLogs;
		
		IPage<SSensitive> sensitives = sensitiveService.findSensitiveWords(super.getToPage(), 20);
		sensitiveList = sensitiveService.findSensitiveWords();
		if (sensitiveList != null && allChatLogs != null && allChatLogs.size() > 0) {
			dealWithSensitiveWord(sensitiveList, userChatLogList);
			//处理页面显示
			pageNum = Math.max(0, pageNum);
			pageSize = (int) Math.ceil((double) allChatLogs.size() / (double) size);
			pageNum = Math.min(pageNum, pageSize - 1);
			
			int length = Math.min((pageNum + 1) * size, allChatLogs.size());
			List<UserChatLog> list = new ArrayList<UserChatLog>();
			for (int i = pageNum * size; i < length; i++) {
				list.add(allChatLogs.get(i));
			}
			userChatLogList = list;
			//设置当前敏感词显示页面
			if (sensitives != null && sensitives.getData() != null) {
				sensitiveList = (List<SSensitive>) sensitives.getData();
				super.setTotalPage(sensitives.getTotalPage());
				super.setTotalSize(sensitives.getPageSize());
			}
		}
		return SUCCESS;
	}

	/**
	 * 处理显示聊天的敏感词加红
	 * @param sensitiveList
	 * @param userChatLogList
	 */
	private void dealWithSensitiveWord(List<SSensitive> sensitiveList, 
			List<UserChatLog> userChatLogList) {
		if (sensitiveList != null && sensitiveList.size() > 0) {
			String[]  keyWordStr = new String[sensitiveList.size()];
			int index = 0;
			for (SSensitive keyWord : sensitiveList) {
				keyWordStr[index] = keyWord.getWord();
				index++;
			}
			
			//处理敏感词匹配
			List<UserChatLog> userChatLogListSensitive = new ArrayList<UserChatLog>();
			List<UserChatLog> userChatLogListNotSensitive = new ArrayList<UserChatLog>();
			for (UserChatLog userChatLog : userChatLogList) {
				String chatMessage = userChatLog.getChatMessage();
				boolean bool = false;
				for (String key : keyWordStr) {
					int beforeLength = chatMessage.length();
					chatMessage = chatMessage.replaceAll(key, "<font color = '#FF0000'>" + key + "</font>");
					userChatLog.setChatMessage(chatMessage);
					int afterLength = chatMessage.length();
					
					if (beforeLength != afterLength) {
						bool = true;
					}
				}
				if (bool) {
					//处理过的敏感词
					userChatLogListSensitive.add(userChatLog);
				} else {
					//没有处理过的敏感词
					userChatLogListNotSensitive.add(userChatLog);
				}
				
			}
			userChatLogList.clear();
			//封装敏感字
			for (UserChatLog userChatLog : userChatLogListSensitive) {
				userChatLogList.add(userChatLog);
			}
			//封装信息
			for (UserChatLog userChatLog : userChatLogListNotSensitive) {
				userChatLogList.add(userChatLog);
			}
		}
	}
	
	/**
	 * 处理玩家聊天操作信息
	 * @param userId 玩家ID
	 * @param type 操作类型(1.禁言2.封号3.踢线)
	 */
	public String dealPlayerState() {
		ResTreatmentUserChat resTreatmentUserChat = new ResTreatmentUserChat();
		resTreatmentUserChat.setUserId(userID+"");
		resTreatmentUserChat.setType(operateType);
		Calendar calendar = Calendar.getInstance();
		if (operateType == 1) {
			//禁言1小时
			calendar.add(Calendar.HOUR_OF_DAY, 1);
		} else if (operateType == 2) {
			//封号3天
			calendar.add(Calendar.DAY_OF_YEAR, 3);
		}
		resTreatmentUserChat.setStateFinishTime(calendar.getTime().getTime() + "");
		
		//发送到平台服务器
		Msg msg = HttpServersBridge.connectServerToExecuteTask(AdminToolCMDCode.TREATMENT_USER_CHAT, resTreatmentUserChat, CommomMsgBody.class);
		if (msg.getMsgHead().getErrorCode() != SystemConstant.SUCCESS_CODE) {
			CommomMsgBody commomMsgBody = (CommomMsgBody) msg.getMsgBody();
			super.setErroCodeNum(commomMsgBody.getErrorCode());
			super.setErroDescrip(commomMsgBody.getErrorDescription()+" 操作失败！");
		} else {
			super.setErroDescrip("操作成功");
		}
		return "deal";
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public int getPageSize() {
		return pageSize;
	}
	
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	public int getPageNum() {
		return pageNum;
	}
	
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	
	public String getWords() {
		return words;
	}
	
	public void setWords(String words) {
		this.words = words;
	}
	
	public int getType() {
		return type;
	}
	
	public void setType(int type) {
		this.type = type;
	}
	
	public List<SSensitive> getSensitiveList() {
		return sensitiveList;
	}
	
	public void setSensitiveList(List<SSensitive> sensitiveList) {
		this.sensitiveList = sensitiveList;
	}
	
	public Long getUserID() {
		return userID;
	}
	
	public void setUserID(Long userID) {
		this.userID = userID;
	}
	
	public int getOperateType() {
		return operateType;
	}
	
	public void setOperateType(int operateType) {
		this.operateType = operateType;
	}
	
	public List<UserChatLog> getUserChatLogList() {
		return userChatLogList;
	}
	
	public void setUserChatLogList(List<UserChatLog> userChatLogList) {
		this.userChatLogList = userChatLogList;
	}

	public int getSysNum() {
		return sysNum;
	}

	public void setSysNum(int sysNum) {
		this.sysNum = sysNum;
	}
	
}