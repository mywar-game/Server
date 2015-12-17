package com.fantingame.game.gateway.server.manager;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.fantingame.game.msgbody.server.user.UserAction_logoutReq;
import com.fantingame.game.msgbody.user.UserToken;
import com.fantingame.game.server.channel.AbstractChannel;
import com.fantingame.game.server.channel.Channel;
import com.fantingame.game.server.msg.MsgDispatchCenter;
import com.fantingame.game.server.msg.ServerType;
import com.google.common.base.Strings;

public class UserManager {
  private Map<String,UserInfo> userMap = new ConcurrentHashMap<String,UserInfo>();
  private static UserManager userManager;
  private UserManager(){};
  
  public static UserManager getInstance() {
	  
	  if(userManager==null) {
		  
		  userManager = new UserManager();
	  }
	  return userManager;
  }
  
  /**
   * 获得玩家属性值
   * @param userId
   * @param attribute
   * @return
   */
  public Object getUserAttribute(String userId,String attribute){
	  UserInfo userInfo = getUserInfoByUserId(userId);
	  if(userInfo==null){
		  return null;
	  }
	  return userInfo.getAttribute(attribute);
  }
  /**
   * 取出用户
   * @param userId
   * @return
   */
  public UserInfo getUserInfoByUserId(String userId){
	  return userMap.get(userId);
  }
  /**
   * 加入用户
   * @param userInfo
   */
  public void putUserInfo(UserInfo userInfo){
	  if(userInfo!=null){
		  userMap.put(userInfo.getUserId(), userInfo);
	  }
  }
  /**
   * 判断用户是否存在
   * @param userId
   * @return
   */
  public boolean isExsitUser(String userId){
	  if(userId==null||userId.equals("")){
		  return false;
	  }
	  return userMap.containsKey(userId);
  }
  /**
   * 清除用户信息
   * @param userId
   */
  public void removeUserInfoByUserId(String userId){
	  if(userId!=null&&!userId.equals("")){
		  userMap.remove(userId);
	  }
  }
  /**
   * 获取在线用户人数
   * @param userId
   * @return
   */
  public int getUserMapSize(){
	  return userMap.size();
  }
  
  /**
   * 获得在线用户的用户id列表
   * @return
   */
  public Iterator<String> getUserKeyList(){
	 return userMap.keySet().iterator();
  }
  /**
   * 用户登录   返回用户序列号
   * @param userId
   * @return
   */
  public String userLoginIn(String userId,String token,UserType userType,Channel channel) {
		// 进行登录
	    String sessionId = channel.getChannelId();
	    UserInfo newUserInfo = new UserInfo(sessionId, userId + "", userType);
		putUserInfo(newUserInfo);
		// 设置用户一些用户信息到管道中
		channel.addAttribute(AbstractChannel.USER_ID, userId);
		channel.addAttribute(AbstractChannel.TOKEN, token);
		return sessionId;
  }
  /**
   * 登出
   * @param session
   */
  public void userLogout(Session session){
	    String usreId = session.getUserId();
	    if(!Strings.isNullOrEmpty(usreId)){
	    	UserAction_logoutReq logoutReq = new UserAction_logoutReq();
			logoutReq.setUserId(usreId);
			logoutReq.setUserIp(session.getIp());
			logoutReq.setOnLineSeconds(session.getOnlineSeconds());
			MsgDispatchCenter.disPatchServerProxUserMsg(ServerType.LOGIC_SERVER,"UserAction_logout",logoutReq,null,usreId);
	    }
	    String token = session.getToken();
	    if(!Strings.isNullOrEmpty(token)){
	    	UserToken ut = TokenManager.getInstance().getToken(token);
			// 把离线用户token保存起来
			TokenManager.getInstance().removeToken(token);
			OfflineTokenManager.getInstance().setToken(token, ut);
	    }
  }
}
