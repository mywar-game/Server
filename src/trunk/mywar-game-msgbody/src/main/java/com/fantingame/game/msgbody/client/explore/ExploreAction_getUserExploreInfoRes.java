package com.fantingame.game.msgbody.client.explore;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;

import com.fantingame.game.msgbody.client.explore.UserExploreInfoBO;

/**获取用户探索信息**/
public class ExploreAction_getUserExploreInfoRes implements ICodeAble {

		/**用户探索信息**/
	private UserExploreInfoBO userExploreInfoBO=null;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		userExploreInfoBO.encode(outputStream);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		userExploreInfoBO=new UserExploreInfoBO();    
		userExploreInfoBO.decode(inputStream);


	}
	
		/**用户探索信息**/
    public UserExploreInfoBO getUserExploreInfoBO() {
		return userExploreInfoBO;
	}
	/**用户探索信息**/
    public void setUserExploreInfoBO(UserExploreInfoBO userExploreInfoBO) {
		this.userExploreInfoBO = userExploreInfoBO;
	}

	
	
}
