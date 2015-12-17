package com.adminTool.action;

import net.sf.json.JSONObject;
import com.framework.client.http.HttpClientBridge;
import com.framework.common.ALDAdminActionSupport;
import com.framework.constant.SystemConstant;

public class DelUserArtifactSingle extends ALDAdminActionSupport{

	private static final long serialVersionUID = 6714681883336559490L;
	private static final String REQ_URL = "delUserArtifact.do";
	private String userId;
	private String userArtifactId;
	private String flag = "del";
	
	@Override
	public String execute() {
		
		StringBuilder sb = new StringBuilder();
		StringBuilder sb1 = new StringBuilder();
		sb.append("&userId=").append(userId);
		sb.append("&userArtifactId=").append(userArtifactId);

		sb1.append(userId).append(userArtifactId);
		String response = HttpClientBridge.sendToGameServer(REQ_URL,sb.toString(),sb1.toString());
		if(response==null){
			super.setErroCodeNum(SystemConstant.FAIL_CODE);
			super.setErroDescrip("删除出现异常,请查看日志！");
		}else{
			JSONObject jsonObject = JSONObject.fromObject(response);
			if (jsonObject.containsKey(HttpClientBridge.RETURN_RC)
					&& jsonObject.getInt(HttpClientBridge.RETURN_RC) != HttpClientBridge.RETURN_CODE){
				super.setErroCodeNum(Integer.parseInt(jsonObject.get(HttpClientBridge.FAIL).toString()));
				super.setErroDescrip("删除失败！");
			}
		}
		return SUCCESS; 
	 }

	public String getUserArtifactId() {
		return userArtifactId;
	}


	public void setUserArtifactId(String userArtifactId) {
		this.userArtifactId = userArtifactId;
	}


	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}
	
	
	
}
