package com.fantingame.game.msgbody.notify.life;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;


/**天气信息对象**/
public class WeatherInfoBO implements ICodeAble {

		/**地图id**/
	private Integer mapId=0;
	/**组合id**/
	private Integer groupId=0;
	/**类型**/
	private Integer type=0;
	/**结束时间（时间戳）**/
	private Long endTime=0l;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		outputStream.writeInt(mapId);

		outputStream.writeInt(groupId);

		outputStream.writeInt(type);

		outputStream.writeLong(endTime);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		mapId = inputStream.readInt();

		groupId = inputStream.readInt();

		type = inputStream.readInt();

		endTime = inputStream.readLong();


	}
	
		/**地图id**/
    public Integer getMapId() {
		return mapId;
	}
	/**地图id**/
    public void setMapId(Integer mapId) {
		this.mapId = mapId;
	}
	/**组合id**/
    public Integer getGroupId() {
		return groupId;
	}
	/**组合id**/
    public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}
	/**类型**/
    public Integer getType() {
		return type;
	}
	/**类型**/
    public void setType(Integer type) {
		this.type = type;
	}
	/**结束时间（时间戳）**/
    public Long getEndTime() {
		return endTime;
	}
	/**结束时间（时间戳）**/
    public void setEndTime(Long endTime) {
		this.endTime = endTime;
	}

	
	
}
