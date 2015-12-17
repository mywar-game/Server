package com.fantingame.game.msgbody.client.legion;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;

import com.fantingame.game.msgbody.client.legion.LegionInfoBO;
import com.fantingame.game.msgbody.client.legion.UserLegionInfoBO;

/**创建军团**/
public class LegionAction_createLegionRes implements ICodeAble {

		/**军团信息**/
	private LegionInfoBO legionInfo=null;
	/**用户军团信息**/
	private UserLegionInfoBO userLegionInfo=null;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		legionInfo.encode(outputStream);

		userLegionInfo.encode(outputStream);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		legionInfo=new LegionInfoBO();    
		legionInfo.decode(inputStream);

		userLegionInfo=new UserLegionInfoBO();    
		userLegionInfo.decode(inputStream);


	}
	
		/**军团信息**/
    public LegionInfoBO getLegionInfo() {
		return legionInfo;
	}
	/**军团信息**/
    public void setLegionInfo(LegionInfoBO legionInfo) {
		this.legionInfo = legionInfo;
	}
	/**用户军团信息**/
    public UserLegionInfoBO getUserLegionInfo() {
		return userLegionInfo;
	}
	/**用户军团信息**/
    public void setUserLegionInfo(UserLegionInfoBO userLegionInfo) {
		this.userLegionInfo = userLegionInfo;
	}

	
	
}
