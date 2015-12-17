package com.fantingame.game.msgbody.client.forces;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;


/**结束采集**/
public class ForcesAction_endCollectReq implements ICodeAble {

		/**地图id**/
	private Integer mapId=0;
	/**采集点id**/
	private Integer forcesId=0;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		outputStream.writeInt(mapId);

		outputStream.writeInt(forcesId);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		mapId = inputStream.readInt();

		forcesId = inputStream.readInt();


	}
	
		/**地图id**/
    public Integer getMapId() {
		return mapId;
	}
	/**地图id**/
    public void setMapId(Integer mapId) {
		this.mapId = mapId;
	}
	/**采集点id**/
    public Integer getForcesId() {
		return forcesId;
	}
	/**采集点id**/
    public void setForcesId(Integer forcesId) {
		this.forcesId = forcesId;
	}

	
	
}
