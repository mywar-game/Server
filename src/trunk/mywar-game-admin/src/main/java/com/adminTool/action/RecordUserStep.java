package com.adminTool.action;

import java.util.List;

import net.sf.json.JSONObject;

import com.adminTool.service.UserService;
import com.framework.client.http.HttpClientBridge;
import com.framework.common.ALDAdminActionSupport;
import com.framework.constant.SystemConstant;
import com.framework.servicemanager.ServiceCacheFactory;

/**
 * 跳过新手引导
 * @author Administrator
 *
 */
@SuppressWarnings("serial")
public class RecordUserStep extends ALDAdminActionSupport {

	private Integer lodoId = 0;
	private String userName = "";
	
	private static final String IP = "127.0.0.1";
	private static final Integer STEP = 99999;
	private static final String REQ_URL = "recordGuideStep.do";
	private String isCommit = "F";
	private String flag = "";

	@Override
	public String execute() {
		
		UserService userService = ServiceCacheFactory.getServiceCache().getService(UserService.class);
		
		if (isCommit == "T" || isCommit.equalsIgnoreCase("T")) {
			if ((lodoId == null || lodoId == 0) && (userName == null || userName.equals(""))) {
				return SUCCESS;
			}
			if (lodoId == null) {
				lodoId = 0;
			}
			List<com.adminTool.bo.User> list = userService.findUserByLodoIdAndUserName(lodoId, userName);
			
			if(list==null || list.size()==0){
				super.setErroDescrip("玩家不存在！");
				return SUCCESS;
			}
			
			for(com.adminTool.bo.User user : list){
				
				StringBuilder sb = new StringBuilder();
				StringBuilder sb1 = new StringBuilder();
				sb.append("&userId=").append(user.getUserId());
				sb.append("&guideStep=").append(STEP);
				sb.append("&ip=").append(IP);
				
				// 签名验证
				sb1.append(user.getUserId());
				sb1.append(STEP);
				sb1.append(IP);
				
				String response = HttpClientBridge.sendToGameServer(REQ_URL, sb.toString(), sb1.toString());
				if(response==null){
					super.setErroCodeNum(SystemConstant.FAIL_CODE);
					super.setErroDescrip("查询出现异常,请查看日志！");
				}else{
					JSONObject jsonObject = JSONObject.fromObject(response);
					int code = (Integer) jsonObject.get(HttpClientBridge.RETURN_RC);
					if(code != HttpClientBridge.RETURN_SUCCESS_CODE){
						super.setErroCodeNum(Integer.parseInt(jsonObject.get(HttpClientBridge.FAIL).toString()));
						super.setErroDescrip("查询失败！");
					}else{
						flag = (String) jsonObject.get("result");
					}
				}
			}
		}
		return SUCCESS;
	}
	
	public Integer getLodoId() {
		return lodoId;
	}

	public void setLodoId(Integer lodoId) {
		this.lodoId = lodoId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getIsCommit() {
		return isCommit;
	}

	public void setIsCommit(String isCommit) {
		this.isCommit = isCommit;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

}
