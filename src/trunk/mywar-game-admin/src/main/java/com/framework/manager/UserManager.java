package com.framework.manager;

import java.util.HashMap;
import java.util.Map;

public class UserManager {
  private Map<String, UserInfo> userMap = new HashMap<String, UserInfo>();
  private static UserManager userManager;
  private UserManager() {
  };
  
  public static UserManager getInstance() {
	  if (userManager == null) {
		  userManager = new UserManager();
	  }
	  return userManager;
  }
  /**
   * 取出用户
   * @param userId
   * @return
   */
  public UserInfo getUserInfoByUserId(String userId) {
	  return userMap.get(userId);
  }
  /**
   * 加入用户
   * @param userInfo
   */
  public void putUserInfo(UserInfo userInfo) {
	  if (userInfo != null) {
		  userMap.put(userInfo.getUserId(), userInfo);
	  }
  }
  /**
   * 判断用户是否存在
   * @param userId
   * @return
   */
  public boolean isExsitUser(String userId) {
	  return userMap.containsKey(userId);
  }
  /**
   * 清除用户信息
   * @param userId
   */
  public void removeUserInfoByUserId(String userId) {
	  userMap.remove(userId);
  }
}
