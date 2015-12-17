package com.system.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import com.adminTool.msgbody.UserSomeInfo;
import com.framework.client.http.HttpClientBridge;
import com.framework.common.ALDAdminStatsDatePageActionSupport;
import com.framework.servicemanager.CustomerContextHolder;
import com.framework.servicemanager.ServiceCacheFactory;
import com.system.bo.GameWeb;
import com.system.bo.GameWebServer;
import com.system.bo.TGameServer;
import com.system.service.GameWebServerService;
import com.system.service.GameWebService;
import com.system.service.TGameServerService;

/**
 * 所有服务器信息列表
 * @author 
 * 2012-7-25
 */
public class KaiYing extends ALDAdminStatsDatePageActionSupport {

	/**  */
	private static final long serialVersionUID = 1L;
	private int kingnetid;
	private int sid;
	private Map<String, Object> serverMap = new HashMap<String, Object>();
	private Map<String, Object> userMap = new HashMap<String, Object>();
	private static final String REQ_URL = "getUser.do";

	@Override
	public String execute() {
		GameWebService gameWebService = ServiceCacheFactory.getServiceCache().getService(GameWebService.class);
		GameWebServerService gameWebServerService = ServiceCacheFactory.getServiceCache().getService(GameWebServerService.class);
		List<GameWeb> webList = gameWebService.findGameWebList();
		List<Map<String, String>> slist = new ArrayList<Map<String,String>>(); 
		if(webList!=null && webList.size()>0){
			for(GameWeb gameWeb : webList){
				List<GameWebServer> webServerList = gameWebServerService.findAllServer(gameWeb.getServerId());
				if(webServerList!=null && webServerList.size()>0){
					for(GameWebServer gameWebServer : webServerList){
						Map<String, String> map = new HashMap<String, String>();
						map.put("server_name", gameWebServer.getServerId()+" "+gameWebServer.getServerName());
						map.put("server_status", gameWebServer.getStatus()+"");
						map.put("server_id", getServerId(gameWebServer.getServerId())+"");
						slist.add(map);
					}
				}
			}
			
		}
		Map<String, List<Map<String, String>>> sMap = new HashMap<String, List<Map<String, String>>>();
		sMap.put("slist", slist);
		serverMap.put("ret", 0);
		serverMap.put("msg", sMap);
		return SUCCESS;
	}
	
	public int getServerId(String serverId){
		if(serverId.startsWith("a")){
			return Integer.parseInt(serverId.replaceFirst("a", "100"));
		}else if(serverId.startsWith("s")){
			return Integer.parseInt(serverId.replaceFirst("s", "200"));
		}else if(serverId.startsWith("h")){
			return Integer.parseInt(serverId.replaceFirst("h", "300"));
		}else if(serverId.startsWith("k")){
			return Integer.parseInt(serverId.replaceFirst("k", "400"));
		}
		return 0;
	}
	
	public String getServerId(int serverId){
		if(String.valueOf(serverId).startsWith("1")){
			return "a"+(serverId-1000);
		}else if(String.valueOf(serverId).startsWith("2")){
			return "s"+(serverId-2000);
		}else if(String.valueOf(serverId).startsWith("3")){
			return "h"+(serverId-3000);
		}else if(String.valueOf(serverId).startsWith("4")){
			return "k"+(serverId-4000);
		}
		return "";
	}
	
	public int getPartnerId(int serverId){
		if(String.valueOf(serverId).startsWith("1")){
			return 4001;
		}else if(String.valueOf(serverId).startsWith("2")){
			return 4001;
		}else if(String.valueOf(serverId).startsWith("3")){
			return 4002;
		}else if(String.valueOf(serverId).startsWith("4")){
			return 4002;
		}
		return 0;
	}
	
	public String getUserInfo(){
		TGameServerService tGameServerService = ServiceCacheFactory.getServiceCache().getService(TGameServerService.class);
		List<TGameServer> tgamelist = tGameServerService.findTGameServerList();
		if(kingnetid==0 || sid==0){
			userMap.put("ret", 3);
			userMap.put("msg", "传入参数错误");
			return SUCCESS;
		}
		int systemNum = 0;
		if(tgamelist!=null && tgamelist.size()>0){
			for(TGameServer gameServer : tgamelist){
				String[] arr = gameServer.getServerAlias().split("-");
				if(arr[0].equals(getServerId(sid))){
					systemNum = gameServer.getServerId();
					break;
				}
			}
		}
		if(systemNum==0){
			userMap.put("ret", 3);
			userMap.put("msg", "后台没查到服务器");
			return SUCCESS;
		}
		CustomerContextHolder.setSystemNum(systemNum);
		StringBuilder sb = new StringBuilder();
		StringBuilder sb1 = new StringBuilder();
		sb.append("&partnerUserId=").append(kingnetid);
		sb.append("&serverId=").append(getServerId(sid));
		sb.append("&partnerId=").append(getPartnerId(sid));
		sb1.append(kingnetid).append(getServerId(sid)).append(getPartnerId(sid));
		String response = HttpClientBridge.sendToGameServer(REQ_URL,sb.toString(),sb1.toString());
		if(response==null){
			userMap.put("ret", 3);
			userMap.put("msg", "查询不到数据");
		}else{
			JSONObject jsonObject = JSONObject.fromObject(response);
			if(jsonObject.containsKey(HttpClientBridge.FAIL)){
				userMap.put("ret", 3);
				userMap.put("msg", "查询失败");
			}else{
				UserSomeInfo userSomeInfo = (UserSomeInfo)JSONObject.toBean((JSONObject)jsonObject.get(HttpClientBridge.SUCCESS), UserSomeInfo.class);
				Map<String, Object> userInfo = new HashMap<String, Object>();
				userInfo.put("rolename", userSomeInfo.getRoleName());
				userInfo.put("level", userSomeInfo.getLevel());
				userInfo.put("roleid", userSomeInfo.getUserId());
				userInfo.put("gold", userSomeInfo.getGold());
				userMap.put("ret", 0);
				userMap.put("msg", userInfo);
			}
		}
		return SUCCESS;
	}

	public int getKingnetid() {
		return kingnetid;
	}

	public void setKingnetid(int kingnetid) {
		this.kingnetid = kingnetid;
	}

	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public Map<String, Object> getServerMap() {
		return serverMap;
	}

	public void setServerMap(Map<String, Object> serverMap) {
		this.serverMap = serverMap;
	}

	public Map<String, Object> getUserMap() {
		return userMap;
	}

	public void setUserMap(Map<String, Object> userMap) {
		this.userMap = userMap;
	}


}
