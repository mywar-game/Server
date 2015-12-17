package com.stats.action;

import java.util.ArrayList;
import java.util.List;

import com.adminTool.constant.AdminToolCMDCode;
import com.framework.client.http.HttpServersBridge;
import com.framework.common.ALDAdminActionSupport;
import com.framework.common.CommomMsgBody;
import com.framework.server.msg.Msg;
import com.stats.msgbody.MemcacheInfo;
import com.stats.msgbody.ResMemcacheInfoList;

public class MemcacheUseRecord extends ALDAdminActionSupport {
	private static final long serialVersionUID = 1L;
	private MemcacheInfo memcacheInfo = new MemcacheInfo();
	private List<MemcacheInfo> memcacheInfoList = new ArrayList<MemcacheInfo>();
	public String execute() {
		Msg msg = HttpServersBridge.connectServerToExecuteTask(AdminToolCMDCode.MEMCACHE_USE_RECORD, new CommomMsgBody(), ResMemcacheInfoList.class);
		ResMemcacheInfoList resMemcacheInfoList = (ResMemcacheInfoList)msg.getMsgBody();
		memcacheInfo = resMemcacheInfoList.getMemcacheInfo();
		memcacheInfoList = resMemcacheInfoList.getMemcacheInfoList();
		return SUCCESS; 
	}
	public void setMemcacheInfo(MemcacheInfo memcacheInfo) {
		this.memcacheInfo = memcacheInfo;
	}
	public MemcacheInfo getMemcacheInfo() {
		return memcacheInfo;
	}
	public void setMemcacheInfoList(List<MemcacheInfo> memcacheInfoList) {
		this.memcacheInfoList = memcacheInfoList;
	}
	public List<MemcacheInfo> getMemcacheInfoList() {
		return memcacheInfoList;
	}
}
