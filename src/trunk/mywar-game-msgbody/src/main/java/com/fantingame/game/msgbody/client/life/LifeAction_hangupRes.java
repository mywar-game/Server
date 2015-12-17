package com.fantingame.game.msgbody.client.life;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;

import com.fantingame.game.msgbody.client.life.UserLifeInfoBO;

/**开始挂机**/
public class LifeAction_hangupRes implements ICodeAble {

		/**用户生活信息对象**/
	private UserLifeInfoBO userLifeInfoBO=null;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		userLifeInfoBO.encode(outputStream);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		userLifeInfoBO=new UserLifeInfoBO();    
		userLifeInfoBO.decode(inputStream);


	}
	
		/**用户生活信息对象**/
    public UserLifeInfoBO getUserLifeInfoBO() {
		return userLifeInfoBO;
	}
	/**用户生活信息对象**/
    public void setUserLifeInfoBO(UserLifeInfoBO userLifeInfoBO) {
		this.userLifeInfoBO = userLifeInfoBO;
	}

	
	
}
