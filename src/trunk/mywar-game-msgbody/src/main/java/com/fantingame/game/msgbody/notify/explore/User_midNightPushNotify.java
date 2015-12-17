package com.fantingame.game.msgbody.notify.explore;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;

import com.fantingame.game.msgbody.client.explore.UserExploreInfoBO;

/**用户午夜推送信息**/
public class User_midNightPushNotify implements ICodeAble {

		/**用户探索信息**/
	private UserExploreInfoBO userExploreInfoBO=null;
	/**当前服务器时间**/
	private Long systemTime=0l;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		userExploreInfoBO.encode(outputStream);

		outputStream.writeLong(systemTime);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		userExploreInfoBO=new UserExploreInfoBO();    
		userExploreInfoBO.decode(inputStream);

		systemTime = inputStream.readLong();


	}
	
		/**用户探索信息**/
    public UserExploreInfoBO getUserExploreInfoBO() {
		return userExploreInfoBO;
	}
	/**用户探索信息**/
    public void setUserExploreInfoBO(UserExploreInfoBO userExploreInfoBO) {
		this.userExploreInfoBO = userExploreInfoBO;
	}
	/**当前服务器时间**/
    public Long getSystemTime() {
		return systemTime;
	}
	/**当前服务器时间**/
    public void setSystemTime(Long systemTime) {
		this.systemTime = systemTime;
	}

	
	
}
