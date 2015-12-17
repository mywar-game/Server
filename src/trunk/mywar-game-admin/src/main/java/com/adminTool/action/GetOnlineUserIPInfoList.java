package com.adminTool.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.adminTool.constant.AdminToolCMDCode;
import com.adminTool.msgbody.ResGetSomeUserInfo;
import com.adminTool.msgbody.UserSomeInfo;
import com.framework.client.http.HttpServersBridge;
import com.framework.common.ALDAdminActionSupport;
import com.framework.common.CommomMsgBody;
import com.framework.constant.SystemConstant;
import com.framework.server.msg.Msg;

public class GetOnlineUserIPInfoList extends ALDAdminActionSupport {

	/**  */
	private static final long serialVersionUID = -3866895136688269609L;
	
	/**  */
	private Map<String, String[]> map = new HashMap<String, String[]>();
	
	public String execute(){
		//从游戏后台获得在线玩家信息
		Msg msg = HttpServersBridge.connectServerToExecuteTask(AdminToolCMDCode.GET_ONLINE_USER_INFO, new CommomMsgBody(), ResGetSomeUserInfo.class);
		if (msg.getMsgHead().getErrorCode() != SystemConstant.SUCCESS_CODE) {
			CommomMsgBody commomMsgBody = (CommomMsgBody) msg.getMsgBody();
			super.setErroCodeNum(commomMsgBody.getErrorCode());
			super.setErroDescrip(commomMsgBody.getErrorDescription()+" 获得在线玩家失败！");
			return SUCCESS;
		}
		List<UserSomeInfo> tempList = ((ResGetSomeUserInfo)msg.getMsgBody()).getUserSomeInfoList();
		
		for (int i = 0; i < tempList.size(); i++) {
			UserSomeInfo userSomeInfo = tempList.get(i);
			String ip = null;
			String name = userSomeInfo.getRoleName();
			if (map.get(ip) == null) {
				map.put(ip, new String[]{name});
			} else {
				String[] arr = map.get(ip);
				String[] tempArr = new String[arr.length+1];
				for (int j = 0; j < arr.length; j++) {
					tempArr[j] = arr[j];
				}
				tempArr[tempArr.length-1] = name;
				map.put(ip, tempArr);
			}
		}
		
		return SUCCESS;
	}

	public void setMap(Map<String, String[]> map) {
		this.map = map;
	}

	public Map<String, String[]> getMap() {
		return map;
	}


}
