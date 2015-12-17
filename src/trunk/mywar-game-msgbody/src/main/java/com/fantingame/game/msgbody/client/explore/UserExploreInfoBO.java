package com.fantingame.game.msgbody.client.explore;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;


/**用户探索信息对象**/
public class UserExploreInfoBO implements ICodeAble {

		/**地图Id**/
	private Integer mapId=0;
	/**积分**/
	private Integer integral=0;
	/**剩余探索次数**/
	private Integer exploreTimes=0;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		outputStream.writeInt(mapId);

		outputStream.writeInt(integral);

		outputStream.writeInt(exploreTimes);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		mapId = inputStream.readInt();

		integral = inputStream.readInt();

		exploreTimes = inputStream.readInt();


	}
	
		/**地图Id**/
    public Integer getMapId() {
		return mapId;
	}
	/**地图Id**/
    public void setMapId(Integer mapId) {
		this.mapId = mapId;
	}
	/**积分**/
    public Integer getIntegral() {
		return integral;
	}
	/**积分**/
    public void setIntegral(Integer integral) {
		this.integral = integral;
	}
	/**剩余探索次数**/
    public Integer getExploreTimes() {
		return exploreTimes;
	}
	/**剩余探索次数**/
    public void setExploreTimes(Integer exploreTimes) {
		this.exploreTimes = exploreTimes;
	}

	
	
}
