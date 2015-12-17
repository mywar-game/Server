package com.adminTool.action; 


import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.adminTool.bo.User;
import com.adminTool.msgbody.UserArtifactSomeInfo;
import com.adminTool.service.UserService;
import com.dataconfig.service.SystemArtifactService;
import com.framework.client.http.HttpClientBridge;
import com.framework.common.ALDAdminActionSupport;
import com.framework.constant.SystemConstant;
import com.framework.servicemanager.ServiceCacheFactory;
import com.opensymphony.xwork2.ModelDriven;

public class GetUserArtifactSingle extends ALDAdminActionSupport implements
		ModelDriven<User> {

	private static final long serialVersionUID = 3144459422096382508L;
	private static final String REQ_URL = "getUserArtifactById.do";
	private List<UserArtifactSomeInfo> userArtifactSomeInfoList;	
	private User user = new User(); 
	private Map<Integer, String> artifacts;
	private String artifactId;

	@SuppressWarnings({ "deprecation", "unchecked" })
	public String execute() {
		
		UserService userService = ServiceCacheFactory.getServiceCache()
				.getService(UserService.class); 
		SystemArtifactService artifactService = ServiceCacheFactory
				.getServiceCache().getService(SystemArtifactService.class);
		
		artifacts = artifactService.findArtifactIDNameMap();
		if (user.getUserId() == null && (user.getLodoId()==null ||user.getLodoId().intValue()==0)) {
			return SUCCESS; 
		}

		if (user.getUserId() != null && !"".equals(user.getUserId())) {
			user = userService.findUserByUserId(user.getUserId()); 
		}else if (user.getLodoId().intValue()!=0) {
			user = userService.findUserByLodoId(user.getLodoId()); 
		}
		if (user == null) {
			super.setErroDescrip("玩家不存在！");
			return SUCCESS;
		}
		String userId = user.getUserId();

		StringBuilder sb = new StringBuilder();
		StringBuilder sb1 = new StringBuilder();
		sb.append("&userId=").append(userId);
		sb.append("&artifactId=").append(artifactId);
		sb1.append(userId).append(artifactId);
		String response = HttpClientBridge.sendToGameServer(REQ_URL,sb.toString(),sb1.toString());
		if(response==null){
			super.setErroCodeNum(SystemConstant.FAIL_CODE);
			super.setErroDescrip("查询出现异常,请查看日志！");
		}else{
			JSONObject jsonObject = JSONObject.fromObject(response);
			if(jsonObject.containsKey(HttpClientBridge.FAIL)){
				super.setErroCodeNum(Integer.parseInt(jsonObject.get(HttpClientBridge.FAIL).toString()));
				super.setErroDescrip("查询失败！");
			}else{
				userArtifactSomeInfoList = JSONArray.toList((JSONArray)jsonObject.get(HttpClientBridge.SUCCESS), UserArtifactSomeInfo.class);
				
				if(userArtifactSomeInfoList.size() == 0)
					super.setErroDescrip("玩家不存在此道具");
			}
		}
		
		return SUCCESS; 
	}

	@Override
	public User getModel() {
		return user; 
	}

	public User getUser() {
		return user; 
	}

	public void setUser(User user) {
		this.user = user; 
	}

	public List<UserArtifactSomeInfo> getUserArtifactSomeInfoList() {
		return userArtifactSomeInfoList;
	}

	public void setUserArtifactSomeInfoList(
			List<UserArtifactSomeInfo> userArtifactSomeInfoList) {
		this.userArtifactSomeInfoList = userArtifactSomeInfoList;
	}

	public Map<Integer, String> getArtifacts() {
		return artifacts;
	}

	public void setArtifacts(Map<Integer, String> artifacts) {
		this.artifacts = artifacts;
	}

	public String getArtifactId() {
		return artifactId;
	}

	public void setArtifactId(String artifactId) {
		this.artifactId = artifactId;
	}

	
}