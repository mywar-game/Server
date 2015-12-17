package com.fantingame.game.msgbody.client.legion;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;

import com.fantingame.game.msgbody.client.legion.UserLegionInfoBO;
import com.fantingame.game.msgbody.client.legion.LegionInfoBO;

/**用户捐献**/
public class LegionAction_userInvestRes implements ICodeAble {

		/**用户公会信息**/
	private UserLegionInfoBO userLegionInfo=null;
	/**公会信息**/
	private LegionInfoBO legionInfo=null;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		userLegionInfo.encode(outputStream);

		legionInfo.encode(outputStream);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		userLegionInfo=new UserLegionInfoBO();    
		userLegionInfo.decode(inputStream);

		legionInfo=new LegionInfoBO();    
		legionInfo.decode(inputStream);


	}
	
		/**用户公会信息**/
    public UserLegionInfoBO getUserLegionInfo() {
		return userLegionInfo;
	}
	/**用户公会信息**/
    public void setUserLegionInfo(UserLegionInfoBO userLegionInfo) {
		this.userLegionInfo = userLegionInfo;
	}
	/**公会信息**/
    public LegionInfoBO getLegionInfo() {
		return legionInfo;
	}
	/**公会信息**/
    public void setLegionInfo(LegionInfoBO legionInfo) {
		this.legionInfo = legionInfo;
	}

	
	
}
