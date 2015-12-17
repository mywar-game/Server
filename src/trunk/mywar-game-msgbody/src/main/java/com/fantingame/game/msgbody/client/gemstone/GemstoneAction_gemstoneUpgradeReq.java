package com.fantingame.game.msgbody.client.gemstone;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;


/**宝石精炼升级**/
public class GemstoneAction_gemstoneUpgradeReq implements ICodeAble {

		/**用户宝石id**/
	private String userGemstoneId="";
	/**状态：1开始2取消3完成**/
	private Integer status=0;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		outputStream.writeUTF(userGemstoneId);

		outputStream.writeInt(status);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		userGemstoneId = inputStream.readUTF();

		status = inputStream.readInt();


	}
	
		/**用户宝石id**/
    public String getUserGemstoneId() {
		return userGemstoneId;
	}
	/**用户宝石id**/
    public void setUserGemstoneId(String userGemstoneId) {
		this.userGemstoneId = userGemstoneId;
	}
	/**状态：1开始2取消3完成**/
    public Integer getStatus() {
		return status;
	}
	/**状态：1开始2取消3完成**/
    public void setStatus(Integer status) {
		this.status = status;
	}

	
	
}
