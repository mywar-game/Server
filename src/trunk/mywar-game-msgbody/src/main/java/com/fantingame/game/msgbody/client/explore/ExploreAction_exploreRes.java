package com.fantingame.game.msgbody.client.explore;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;

import com.fantingame.game.msgbody.client.goods.CommonGoodsBeanBO;

/**探索**/
public class ExploreAction_exploreRes implements ICodeAble {

		/**通用奖励对象**/
	private CommonGoodsBeanBO drop=null;
	/**剩余探索次数**/
	private Integer exploreTimes=0;
	/**新的地图id**/
	private Integer mapId=0;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		drop.encode(outputStream);

		outputStream.writeInt(exploreTimes);

		outputStream.writeInt(mapId);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		drop=new CommonGoodsBeanBO();    
		drop.decode(inputStream);

		exploreTimes = inputStream.readInt();

		mapId = inputStream.readInt();


	}
	
		/**通用奖励对象**/
    public CommonGoodsBeanBO getDrop() {
		return drop;
	}
	/**通用奖励对象**/
    public void setDrop(CommonGoodsBeanBO drop) {
		this.drop = drop;
	}
	/**剩余探索次数**/
    public Integer getExploreTimes() {
		return exploreTimes;
	}
	/**剩余探索次数**/
    public void setExploreTimes(Integer exploreTimes) {
		this.exploreTimes = exploreTimes;
	}
	/**新的地图id**/
    public Integer getMapId() {
		return mapId;
	}
	/**新的地图id**/
    public void setMapId(Integer mapId) {
		this.mapId = mapId;
	}

	
	
}
