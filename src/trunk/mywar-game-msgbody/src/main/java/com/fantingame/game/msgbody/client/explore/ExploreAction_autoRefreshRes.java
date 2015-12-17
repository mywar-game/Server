package com.fantingame.game.msgbody.client.explore;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;


/**自动刷新**/
public class ExploreAction_autoRefreshRes implements ICodeAble {

		/**地图Id**/
	private Integer mapId=0;
	/**是否停止1是0否**/
	private Integer stop=0;
	/**用户探索积分**/
	private Integer integral=0;
	/**花费钻石**/
	private Integer cost=0;
	/**剩余钻石数**/
	private Integer mon=0;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		outputStream.writeInt(mapId);

		outputStream.writeInt(stop);

		outputStream.writeInt(integral);

		outputStream.writeInt(cost);

		outputStream.writeInt(mon);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		mapId = inputStream.readInt();

		stop = inputStream.readInt();

		integral = inputStream.readInt();

		cost = inputStream.readInt();

		mon = inputStream.readInt();


	}
	
		/**地图Id**/
    public Integer getMapId() {
		return mapId;
	}
	/**地图Id**/
    public void setMapId(Integer mapId) {
		this.mapId = mapId;
	}
	/**是否停止1是0否**/
    public Integer getStop() {
		return stop;
	}
	/**是否停止1是0否**/
    public void setStop(Integer stop) {
		this.stop = stop;
	}
	/**用户探索积分**/
    public Integer getIntegral() {
		return integral;
	}
	/**用户探索积分**/
    public void setIntegral(Integer integral) {
		this.integral = integral;
	}
	/**花费钻石**/
    public Integer getCost() {
		return cost;
	}
	/**花费钻石**/
    public void setCost(Integer cost) {
		this.cost = cost;
	}
	/**剩余钻石数**/
    public Integer getMon() {
		return mon;
	}
	/**剩余钻石数**/
    public void setMon(Integer mon) {
		this.mon = mon;
	}

	
	
}
