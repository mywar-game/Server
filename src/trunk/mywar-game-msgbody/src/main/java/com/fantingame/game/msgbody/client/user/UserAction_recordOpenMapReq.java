package com.fantingame.game.msgbody.client.user;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;


/**记录开启的地图数据**/
public class UserAction_recordOpenMapReq implements ICodeAble {

		/**开启的地图id**/
	private Integer mapId=0;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		outputStream.writeInt(mapId);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		mapId = inputStream.readInt();


	}
	
		/**开启的地图id**/
    public Integer getMapId() {
		return mapId;
	}
	/**开启的地图id**/
    public void setMapId(Integer mapId) {
		this.mapId = mapId;
	}

	
	
}
