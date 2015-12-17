package com.adminTool.action;

import sun.misc.BASE64Encoder;

import com.admin.util.Tools;
import com.adminTool.bo.User;
import com.adminTool.service.UserService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.common.MD5;
import com.framework.servicemanager.ServiceCacheFactory;
import com.system.bo.TGameServer;
import com.system.service.TGameServerService;

/**
 * 登录玩家账号
 * @author hzy
 * 2012-10-24
 */
public class LoginUserAccount extends ALDAdminActionSupport {

	/** * */
	private static final long serialVersionUID = 1L;
	
	private String userName;
	
	private String isCommit = "F";
	
	private String newWindow = new String();
	
	public String execute() {
		if ("F".equals(isCommit)) {
			return SUCCESS;
		}
		UserService userService = ServiceCacheFactory.getServiceCache().getService(UserService.class);
		//玩家账号为空
		if (Tools.isEmpty(userName)) {
			super.setErroDescrip("玩家账号为空，请填写！");
			return SUCCESS;
		}
		userName = userName.trim();
//		User user = userService.findUserByUserName(userName);
		User user = null;
		//玩家不存在
		if (user == null) {
			super.setErroDescrip(this.getText("log.userNameNotExist", new String[]{userName}));
			return SUCCESS;
		}
		//根据服务器编号获得游戏地址
		String sysNum = this.getAdminUser().getExp1();
		TGameServerService gameServerService = ServiceCacheFactory.getServiceCache().getService(TGameServerService.class);
		TGameServer gameServer = gameServerService.findOneTGameServer(Integer.valueOf(sysNum));
		if (gameServer == null) {
			super.setErroDescrip("服务器为空：sysNum" + sysNum);
			return SUCCESS;
		}
		
		String gameUrl = gameServer.getGameServerUrl();
		if (Tools.isEmpty(gameUrl)) {
			super.setErroDescrip("游戏地址为空！");
			return SUCCESS;
		}
		//检查游戏地址格式和是否可用
		if (!Tools.checkURL(gameUrl)) {
			super.setErroDescrip("<a href='"+gameUrl+"' target='_blank'>游戏地址</a>格式不正确！");
			return SUCCESS;
		}
		if (!Tools.urlCanUse(gameUrl)) {
			super.setErroDescrip("<a href='"+gameUrl+"' target='_blank'>游戏地址</a>不可用！");
			return SUCCESS;
		}
		String str = userName+"&"+System.currentTimeMillis()+"&"+gameServer.getLoginEncryption();
		String md5Str = MD5.md5Of32(str);
		BASE64Encoder encoder = new BASE64Encoder();
		String base64 = encoder.encodeBuffer((userName+"&"+System.currentTimeMillis()+"&"+md5Str+"&0& & ").getBytes());
		//base64后有换行符
		newWindow = (gameUrl+"?;"+base64).replaceAll("\n|\r", "");
		return SUCCESS;
	}

	public void setIsCommit(String isCommit) {
		this.isCommit = isCommit;
	}

	public String getIsCommit() {
		return isCommit;
	}

	public void setNewWindow(String newWindow) {
		this.newWindow = newWindow;
	}

	public String getNewWindow() {
		return newWindow;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserName() {
		return userName;
	}
}
