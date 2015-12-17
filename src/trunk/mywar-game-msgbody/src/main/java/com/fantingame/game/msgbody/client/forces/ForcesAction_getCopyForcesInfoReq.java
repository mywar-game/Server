package com.fantingame.game.msgbody.client.forces;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;


/**获取用户副本关卡信息列表**/
public class ForcesAction_getCopyForcesInfoReq implements ICodeAble {

		/**该副本的所在的地图id**/
	private Integer mapId=0;
	/**大关卡id**/
	private Integer bigForcesId=0;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		outputStream.writeInt(mapId);

		outputStream.writeInt(bigForcesId);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		mapId = inputStream.readInt();

		bigForcesId = inputStream.readInt();


	}
	
		/**该副本的所在的地图id**/
    public Integer getMapId() {
		return mapId;
	}
	/**该副本的所在的地图id**/
    public void setMapId(Integer mapId) {
		this.mapId = mapId;
	}
	/**大关卡id**/
    public Integer getBigForcesId() {
		return bigForcesId;
	}
	/**大关卡id**/
    public void setBigForcesId(Integer bigForcesId) {
		this.bigForcesId = bigForcesId;
	}

	
	
}
