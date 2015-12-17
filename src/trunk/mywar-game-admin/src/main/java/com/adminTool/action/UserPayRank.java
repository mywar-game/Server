package com.adminTool.action;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.adminTool.constant.AdminToolCMDCode;
import com.adminTool.msgbody.ReqGetSomeUserInfo;
import com.adminTool.msgbody.ResGetSomeUserInfo;
import com.adminTool.msgbody.UserSomeInfo;
import com.dataconfig.service.BaPveConstantService;
import com.framework.client.http.HttpServersBridge;
import com.framework.common.ALDAdminPageActionSupport;
import com.framework.common.CommomMsgBody;
import com.framework.constant.SystemConstant;
import com.framework.server.msg.Msg;
import com.framework.servicemanager.ServiceCacheFactory;
import com.log.service.UserPayHistoryLogService;

public class UserPayRank extends ALDAdminPageActionSupport {

	/** * */
	private static final long serialVersionUID = -3552893068639603988L;
	
	private List<UserSomeInfo> list;
	
	/**  **/
	private Map<String, String> baPveIdNamesMap; 

	public String execute(){
		UserPayHistoryLogService payLogService = ServiceCacheFactory.getServiceCache().getService(UserPayHistoryLogService.class);
		//每个付费玩家的充值数
		Map<Long, String> payMap = payLogService.getEveryUserPayAmount("DESC");
		//翻页参数
		super.setTotalSize(payMap.size());
		if (payMap.size() % super.getPageSize() == 0) {
			super.setTotalPage(payMap.size()/super.getPageSize());
		} else {
			super.setTotalPage(payMap.size()/super.getPageSize() + 1 );
		}
		//分页显示，只要一截
		Map<Long, Integer> pageMap = new LinkedHashMap<Long, Integer>();
		Iterator<Entry<Long, String>> ite = payMap.entrySet().iterator();
		int i = 0;
		while(ite.hasNext()) {
			Entry<Long, String> entry = ite.next();
			if (i == super.getToPage()*super.getPageSize()) {
				if (pageMap.size() < super.getPageSize()) {
					pageMap.put(entry.getKey(), Integer.valueOf(entry.getValue().split("_")[1]));
				} else {
					break;
				}
			} else {
				i++;
			}
			
		}
		
		ReqGetSomeUserInfo req = new ReqGetSomeUserInfo();
		Long[] userIdArr = pageMap.keySet().toArray(new Long[0]);
		StringBuffer userIds = new StringBuffer();
		for (int j = 0; j < userIdArr.length-1; j++) {
			userIds.append(userIdArr[j]).append(",");
		}
		userIds.append(userIdArr[userIdArr.length-1]);
		req.setUserIds(userIds.toString());
		Msg msg = HttpServersBridge.connectServerToExecuteTask(AdminToolCMDCode.GET_SOME_USER_INFO, req, ResGetSomeUserInfo.class);
		if (msg.getMsgHead().getErrorCode() != SystemConstant.SUCCESS_CODE) {
			CommomMsgBody commomMsgBody = (CommomMsgBody) msg.getMsgBody();
			super.setErroCodeNum(commomMsgBody.getErrorCode());
			super.setErroDescrip(commomMsgBody.getErrorDescription()+" 获取玩家信息失败！");
			return SUCCESS; 
		}
		list = ((ResGetSomeUserInfo)msg.getMsgBody()).getUserSomeInfoList();
		BaPveConstantService baPveConstantService = ServiceCacheFactory.getServiceCache().getService(BaPveConstantService.class); 
		baPveIdNamesMap = baPveConstantService.findBaPveIdNamesMap(); 
		return SUCCESS;
	}

	public void setList(List<UserSomeInfo> list) {
		this.list = list;
	}

	public List<UserSomeInfo> getList() {
		return list;
	}

	public void setBaPveIdNamesMap(Map<String, String> baPveIdNamesMap) {
		this.baPveIdNamesMap = baPveIdNamesMap;
	}

	public Map<String, String> getBaPveIdNamesMap() {
		return baPveIdNamesMap;
	}
	
}
