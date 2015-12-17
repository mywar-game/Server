package com.fantingame.game.msgbody.client.forces;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;


/**攻击关卡**/
public class ForcesAction_attackReq implements ICodeAble {

		/**地图id**/
	private Integer mapId=0;
	/**攻击或采集的关卡id**/
	private Integer forcesId=0;
	/**关卡类型如果是普通怪物或采集关卡传1即可，如果为boss关卡1代表简单、2精英、3困难**/
	private Integer forcesType=0;
	/**好友的用户id**/
	private String userFriendId="";

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		outputStream.writeInt(mapId);

		outputStream.writeInt(forcesId);

		outputStream.writeInt(forcesType);

		outputStream.writeUTF(userFriendId);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		mapId = inputStream.readInt();

		forcesId = inputStream.readInt();

		forcesType = inputStream.readInt();

		userFriendId = inputStream.readUTF();


	}
	
		/**地图id**/
    public Integer getMapId() {
		return mapId;
	}
	/**地图id**/
    public void setMapId(Integer mapId) {
		this.mapId = mapId;
	}
	/**攻击或采集的关卡id**/
    public Integer getForcesId() {
		return forcesId;
	}
	/**攻击或采集的关卡id**/
    public void setForcesId(Integer forcesId) {
		this.forcesId = forcesId;
	}
	/**关卡类型如果是普通怪物或采集关卡传1即可，如果为boss关卡1代表简单、2精英、3困难**/
    public Integer getForcesType() {
		return forcesType;
	}
	/**关卡类型如果是普通怪物或采集关卡传1即可，如果为boss关卡1代表简单、2精英、3困难**/
    public void setForcesType(Integer forcesType) {
		this.forcesType = forcesType;
	}
	/**好友的用户id**/
    public String getUserFriendId() {
		return userFriendId;
	}
	/**好友的用户id**/
    public void setUserFriendId(String userFriendId) {
		this.userFriendId = userFriendId;
	}

	
	
}
