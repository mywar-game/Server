package com.adminTool.action;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import net.sf.json.JSONObject;

import com.adminTool.bo.ActivityRefreshConstant;
import com.adminTool.service.ActivityRefreshConstantService;
import com.framework.client.http.HttpClientBridge;
import com.framework.common.ALDAdminActionSupport;
import com.framework.constant.SystemConstant;
import com.framework.log.LogSystem;
import com.framework.servicemanager.CustomerContextHolder;
import com.framework.servicemanager.ServiceCacheFactory;

/**
 * 刷新活动列表
 * 
 * @author yezp
 */
public class ReflashActivityList extends ALDAdminActionSupport {

	private static final long serialVersionUID = 1L;

	private static final String REQ_URL = "reflashActivity.do";
	
	private String freshClassName="SystemActivityDaoCacheImpl";
	
	private int activityId = -1;
	
	private String serverIds;

	public String execute() {
		StringBuilder sb = new StringBuilder();
		sb.append("&className=");
		if (activityId == -1) {
		} else {
			// 根据活动ID查询静态常量类
			ActivityRefreshConstantService service = ServiceCacheFactory.getServiceCache().getService(ActivityRefreshConstantService.class);
			List<ActivityRefreshConstant> list = service.getAll();
			for (ActivityRefreshConstant activity : list) {
				if (activity.getActivityId() == activityId) {
					freshClassName = activity.getRefreshClassName();
				}
			}
		}
		sb.append(freshClassName);
		String response = null;
		if(StringUtils.isBlank(serverIds)){
			response = HttpClientBridge.sendToGameServer(REQ_URL,
					sb.toString(), freshClassName);
			if (response == null) {
				LogSystem.info("response is null");
				super.setErroCodeNum(SystemConstant.FAIL_CODE);
				super.setErroDescrip("刷新出现异常,请查看日志！");
			} else {
				JSONObject jsonObject = JSONObject.fromObject(response);
				if (jsonObject.containsKey(HttpClientBridge.RETURN_RC)
						&& jsonObject.getInt(HttpClientBridge.RETURN_RC) != HttpClientBridge.RETURN_CODE) {
					LogSystem.info("response errorcode : "+jsonObject.getInt(HttpClientBridge.RETURN_RC));
					super.setErroCodeNum(SystemConstant.FAIL_CODE);
					super.setErroDescrip("刷新出现异常,请查看日志！");
				}
			}
		}else{
			String serverIdsArr[] = serverIds.split(",");
			int sysNum = CustomerContextHolder.getSystemNum();
			StringBuffer desc = new StringBuffer();
			for (String serverId : serverIdsArr) {
				CustomerContextHolder.setSystemNum(Integer.parseInt(serverId));
				response = HttpClientBridge.sendToGameServer(REQ_URL,
						sb.toString(), freshClassName);
				if (response == null) {
					LogSystem.info(serverId+" response is null");
					super.setErroCodeNum(SystemConstant.FAIL_CODE);
					super.setErroDescrip("刷新出现异常,请查看日志！");
					desc.append(serverId+",");
				} else {
					JSONObject jsonObject = JSONObject.fromObject(response);
					if (jsonObject.containsKey(HttpClientBridge.RETURN_RC)
							&& jsonObject.getInt(HttpClientBridge.RETURN_RC) != HttpClientBridge.RETURN_CODE) {
						LogSystem.info(serverId+" response errorcode : "+jsonObject.getInt(HttpClientBridge.RETURN_RC));
						super.setErroCodeNum(SystemConstant.FAIL_CODE);
						super.setErroDescrip("刷新出现异常,请查看日志！");
						desc.append(serverId+",");
					}
				}
			}
			if(desc.length()>0){
				super.setErroDescrip(desc.substring(0, desc.length()-1)+"服务器刷新失败");
			}
			//恢复服务器
			CustomerContextHolder.setSystemNum(sysNum);
		}
		return SUCCESS;
	}

	public String getFreshClassName() {
		return freshClassName;
	}

	public void setFreshClassName(String freshClassName) {
		this.freshClassName = freshClassName;
	}

	public int getActivityId() {
		return activityId;
	}

	public void setActivityId(int activityId) {
		this.activityId = activityId;
	}

	public String getServerIds() {
		return serverIds;
	}

	public void setServerIds(String serverIds) {
		this.serverIds = serverIds;
	}

}
