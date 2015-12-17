package com.system.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;
import com.system.bo.Command;
import com.system.bo.GameWeb;
import com.system.bo.GameWebServer;
import com.system.bo.TGameServer;
import com.system.constant.ServerConstant;
import com.system.manager.ShellExecutor;
import com.system.manager.ShellThread;
import com.system.service.CommandService;
import com.system.service.GameWebServerService;
import com.system.service.GameWebService;
import com.system.service.TGameServerService;

/**
 * 添加通知信息
 * 
 * @author yezp
 */
public class AddCMD extends ALDAdminActionSupport{

	private static final long serialVersionUID = 8013615837381215582L;
	private List<GameWebServer> serverList = new ArrayList<GameWebServer>();
	private String isCommit = "F";
	private String sid[];
	private int cmdType;
	private String version;
	
	public String execute() {
		TGameServerService gameServerService = ServiceCacheFactory.getServiceCache().getService(TGameServerService.class);
		CommandService commandService = ServiceCacheFactory.getServiceCache()
				.getService(CommandService.class);
		GameWebServerService service = ServiceCacheFactory.getServiceCache()
				.getService(GameWebServerService.class);
		GameWebService webService = ServiceCacheFactory.getServiceCache().getService(GameWebService.class);
		List<GameWeb> webList = webService.findGameWebList();
		List<GameWebServer> webServerList = null;
		Map<String, Integer> map = new HashMap<String, Integer>();
		if(webList!=null && webList.size()>0){
			for(GameWeb web : webList){
				webServerList = service.findAllServer(web.getServerId());
				if(webServerList!=null && webServerList.size()>0){
					for(GameWebServer webServer : webServerList){
						serverList.add(webServer);
						map.put(webServer.getServerId(), web.getServerId());
					}
				}
			}
		}
		if (isCommit.equals("F")) {
			return INPUT;
		}
		Map<String, String> ipMap = new HashMap<String, String>();
		List<TGameServer> tList = gameServerService.findTGameServerList();
		if(tList!=null && tList.size()>0){
			for(TGameServer server : tList){
				String[] arr = server.getServerAlias().split("-");
				if(arr.length!=2){
					setErroDescrip("请从(系统管理->服务器开服信息)修改服务器别名(统一格式:s1-恺英测试1服)");
					return INPUT;
				}
				ipMap.put(arr[0], server.getServerIp());
			}
		}

		if (sid == null || sid.length == 0) {
			setErroDescrip("请选择服务器。");
			return INPUT;
		}
		
		if (cmdType == 0) {
			setErroDescrip("请选择命令");
			return INPUT;
		}

		if(cmdType==1 || cmdType==8){//更新服务器和更新数据库都要填版本号 其中更新数据库时版本号填数据包名称
			if(StringUtils.isBlank(version)){
				setErroDescrip("请选择版本号");
				return INPUT;
			}
		}
		Map<String, String> cMap = new HashMap<String, String>();
		try {
			Properties prop = PropertiesLoaderUtils.loadProperties(new ClassPathResource("command.properties"));
			for(Object obj : prop.keySet()){
				cMap.put(obj.toString(), prop.getProperty(obj.toString()));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			setErroDescrip("解析登陆远程服务器配置文件报错");
			return INPUT;
		}
		for (String serverId : sid) {
			Command cmd = new Command();
			cmd.setCreatedTime(new Date());
			cmd.setCurrentCmd(cmdType);
			cmd.setCurrentCmdStatus(ServerConstant.EXECUTING);
			cmd.setExecInfo("");
			cmd.setServerId(serverId);
			cmd.setGameWebId(map.get(serverId));
			commandService.addCommand(cmd);
			if(cMap.containsKey(serverId)){
				String[] info = cMap.get(serverId).split("_");
				if(info.length!=2){
					cmd.setCurrentCmdStatus(ServerConstant.EXECUTE_FAIL);
					cmd.setExecInfo("服务器的远程连接配置错误!");
					commandService.update(cmd);
				}else{
					new Thread(new ShellThread(cmd.getId(), getCmds(cmdType, serverId), new ShellExecutor(ipMap.get(serverId), info[0], info[1], cmdType))).start();;
				}
			}else{
				cmd.setCurrentCmdStatus(ServerConstant.EXECUTE_FAIL);
				cmd.setExecInfo("服务器的远程连接信息没有配置!");
				commandService.update(cmd);
			}
		}
		return SUCCESS;
	}
	
	public String getCmds(int cmdType,String serverId){
		String cmds = "cd /data/server/run/heroworld/HW_"+serverId+"/shell/;";
		if(cmdType==1){//更新游戏服
			cmds += "./update.sh "+serverId+" "+version;
		}else if(cmdType==2){//覆盖游戏服配置(不覆盖jdbc.properties)
			cmds += "./copyconfig.sh "+serverId;
		}else if(cmdType==3){//覆盖游戏服配置(全部覆盖)
			cmds += "./copyconfig.sh "+serverId+" 1";
		}else if(cmdType==4){//关闭游戏服
			cmds += "./game.sh "+serverId+" stop";
		}else if(cmdType==5){//重启游戏服
			cmds += "./game.sh "+serverId+" restart";
		}else if(cmdType==6){//更新静态数据
			cmds += "./static_sql_update.sh "+serverId;
		}else if(cmdType==7){//更新结构数据
			cmds += "./struct_sql_update.sh "+serverId;
		}else if(cmdType==8){//导sql
			cmds += "./update_sql.sh "+serverId+" "+version;
		}else{
			cmds += "ls";
		}
		return cmds;
	}

	public List<GameWebServer> getServerList() {
		return serverList;
	}

	public void setServerList(List<GameWebServer> serverList) {
		this.serverList = serverList;
	}

	public String getIsCommit() {
		return isCommit;
	}

	public void setIsCommit(String isCommit) {
		this.isCommit = isCommit;
	}

	public String[] getSid() {
		return sid;
	}

	public void setSid(String[] sid) {
		this.sid = sid;
	}

	public int getCmdType() {
		return cmdType;
	}

	public void setCmdType(int cmdType) {
		this.cmdType = cmdType;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

}
