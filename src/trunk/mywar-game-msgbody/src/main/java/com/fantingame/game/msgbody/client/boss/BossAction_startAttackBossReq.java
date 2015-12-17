package com.fantingame.game.msgbody.client.boss;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;


/**开始攻打世界boss**/
public class BossAction_startAttackBossReq implements ICodeAble {

		/**地图id**/
	private Integer mapId=0;
	/**坐标x**/
	private Integer x=0;
	/**坐标y**/
	private Integer y=0;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		outputStream.writeInt(mapId);

		outputStream.writeInt(x);

		outputStream.writeInt(y);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		mapId = inputStream.readInt();

		x = inputStream.readInt();

		y = inputStream.readInt();


	}
	
		/**地图id**/
    public Integer getMapId() {
		return mapId;
	}
	/**地图id**/
    public void setMapId(Integer mapId) {
		this.mapId = mapId;
	}
	/**坐标x**/
    public Integer getX() {
		return x;
	}
	/**坐标x**/
    public void setX(Integer x) {
		this.x = x;
	}
	/**坐标y**/
    public Integer getY() {
		return y;
	}
	/**坐标y**/
    public void setY(Integer y) {
		this.y = y;
	}

	
	
}
