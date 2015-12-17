package com.fantingame.game.msgbody.client.equip;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;


/**装备附魔**/
public class EquipAction_equipMagicReq implements ICodeAble {

		/**用户装备id**/
	private String userEquipId="";
	/**卷轴id**/
	private Integer reelId=0;
	/**状态：1开始2取消3完成**/
	private Integer status=0;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		outputStream.writeUTF(userEquipId);

		outputStream.writeInt(reelId);

		outputStream.writeInt(status);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		userEquipId = inputStream.readUTF();

		reelId = inputStream.readInt();

		status = inputStream.readInt();


	}
	
		/**用户装备id**/
    public String getUserEquipId() {
		return userEquipId;
	}
	/**用户装备id**/
    public void setUserEquipId(String userEquipId) {
		this.userEquipId = userEquipId;
	}
	/**卷轴id**/
    public Integer getReelId() {
		return reelId;
	}
	/**卷轴id**/
    public void setReelId(Integer reelId) {
		this.reelId = reelId;
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
