package com.fantingame.game.msgbody.client.forces;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;


/**获取采集以及打怪的奖励**/
public class ForcesAction_getCollectFightRewardReq implements ICodeAble {

		/**地图id**/
	private Integer mapId=0;
	/**采集点id**/
	private Integer forcesId=0;
	/**-1输,0平局,1赢**/
	private Integer flag=0;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		outputStream.writeInt(mapId);

		outputStream.writeInt(forcesId);

		outputStream.writeInt(flag);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		mapId = inputStream.readInt();

		forcesId = inputStream.readInt();

		flag = inputStream.readInt();


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
	/**-1输,0平局,1赢**/
    public Integer getFlag() {
		return flag;
	}
	/**-1输,0平局,1赢**/
    public void setFlag(Integer flag) {
		this.flag = flag;
	}

	
	
}
