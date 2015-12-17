package com.fantingame.game.msgbody.client.explore;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;


/**刷新地图**/
public class ExploreAction_refreshMapRes implements ICodeAble {

		/**地图id**/
	private Integer mapId=0;
	/**用户探索积分**/
	private Integer integral=0;
	/**剩余钻石**/
	private Integer money=0;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		outputStream.writeInt(mapId);

		outputStream.writeInt(integral);

		outputStream.writeInt(money);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		mapId = inputStream.readInt();

		integral = inputStream.readInt();

		money = inputStream.readInt();


	}
	
		/**地图id**/
    public Integer getMapId() {
		return mapId;
	}
	/**地图id**/
    public void setMapId(Integer mapId) {
		this.mapId = mapId;
	}
	/**用户探索积分**/
    public Integer getIntegral() {
		return integral;
	}
	/**用户探索积分**/
    public void setIntegral(Integer integral) {
		this.integral = integral;
	}
	/**剩余钻石**/
    public Integer getMoney() {
		return money;
	}
	/**剩余钻石**/
    public void setMoney(Integer money) {
		this.money = money;
	}

	
	
}
