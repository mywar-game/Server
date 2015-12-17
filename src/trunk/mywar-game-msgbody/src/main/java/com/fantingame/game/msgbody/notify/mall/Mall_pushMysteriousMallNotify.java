package com.fantingame.game.msgbody.notify.mall;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;


/**推送神秘商店信息**/
public class Mall_pushMysteriousMallNotify implements ICodeAble {

		/**神秘商店出现的地图**/
	private Integer mapId=0;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		outputStream.writeInt(mapId);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		mapId = inputStream.readInt();


	}
	
		/**神秘商店出现的地图**/
    public Integer getMapId() {
		return mapId;
	}
	/**神秘商店出现的地图**/
    public void setMapId(Integer mapId) {
		this.mapId = mapId;
	}

	
	
}
