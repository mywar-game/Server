package com.adminTool.action;

import java.util.List;

import com.adminTool.bo.User;
import com.adminTool.constant.AdminToolCMDCode;
import com.adminTool.msgbody.ResGetSomeUserInfo;
import com.adminTool.msgbody.UserSomeInfo;
import com.adminTool.service.UserService;
import com.framework.client.http.HttpServersBridge;
import com.framework.common.ALDAdminPageActionSupport;
import com.framework.common.CommomMsgBody;
import com.framework.common.SystemCode;
import com.framework.constant.SystemConstant;
import com.framework.server.msg.Msg;
import com.framework.servicemanager.ServiceCacheFactory;

public class GetOnlineUserInfoList extends ALDAdminPageActionSupport {

	/**  */
	private static final long serialVersionUID = 7915845407328599006L;
	
	/**  */
	private String isSearch = "F";
	
	/**  */
	private User user;
	
	private List<UserSomeInfo> list;
	
	@Override
	public String execute() throws Exception {
		UserService userService =ServiceCacheFactory.getServiceCache().getService(UserService.class);
		
		//从游戏后台获得在线玩家信息
		Msg msg = HttpServersBridge.connectServerToExecuteTask(AdminToolCMDCode.GET_ONLINE_USER_INFO, new CommomMsgBody(), ResGetSomeUserInfo.class);
		if (msg.getMsgHead().getErrorCode() != SystemConstant.SUCCESS_CODE) {
			CommomMsgBody commomMsgBody = (CommomMsgBody) msg.getMsgBody();
			super.setErroCodeNum(commomMsgBody.getErrorCode());
			super.setErroDescrip(commomMsgBody.getErrorDescription()+" 获得在线玩家失败！");
			return SUCCESS;
		}
		list = ((ResGetSomeUserInfo)msg.getMsgBody()).getUserSomeInfoList();
		
		

		
		for (@SuppressWarnings("unused") UserSomeInfo userSomeInfo : list) {
//			userSomeInfo.setLoginTime(DateUtil.formatTime(Long.valueOf(userSomeInfo.getLoginTime())));
		}
		
		//如果搜索用户
		if ("T".equals(isSearch) && user != null) {
			User searchUser = userService.findUserByCondition(user.getUserId(), user.getUserName(), user.getName());
			if (searchUser == null) {
				super.setErroCodeNum(SystemCode.SYS_FAIL);
				super.setErroDescrip("玩家不存在！");
				return SUCCESS;
			}
			if (searchUser.getUserId() == null) {
				super.setErroCodeNum(SystemCode.SYS_FAIL);
				super.setErroDescrip("未知错误！");
				return SUCCESS;
			}
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).getUserId().equals(searchUser.getUserId())) {
					list = list.subList(i, i+1);
					break;
				}
			}
			//如果查找的用户不在在线用户列表中
			if (list != null && list.size() != 1) {
				super.setErroCodeNum(SystemCode.SYS_FAIL);
				super.setErroDescrip("玩家不在线！");
				return SUCCESS;
			}
		}
		return SUCCESS;
	}

	/**
	 * @return the isSearch
	 */
	public String getIsSearch() {
		return isSearch;
	}

	/**
	 * @param isSearch the isSearch to set
	 */
	public void setIsSearch(String isSearch) {
		this.isSearch = isSearch;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	public void setList(List<UserSomeInfo> list) {
		this.list = list;
	}

	public List<UserSomeInfo> getList() {
		return list;
	}

}
