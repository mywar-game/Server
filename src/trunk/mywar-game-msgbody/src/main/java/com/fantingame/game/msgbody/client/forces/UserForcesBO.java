package com.fantingame.game.msgbody.client.forces;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;


/**用户关卡对象**/
public class UserForcesBO implements ICodeAble {

		/**用户编号**/
	private String userId="";
	/**关卡地图id**/
	private Integer mapId=0;
	/**大关卡id**/
	private Integer bigForcesId=0;
	/**关卡id**/
	private Integer forcesId=0;
	/**状态-状态0未通关1通过了**/
	private Integer status=0;
	/**1简单2普通3精英4困难如没有记录也没有通关**/
	private Integer forcesType=0;
	/**今日已攻打次数**/
	private Integer times=0;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		outputStream.writeUTF(userId);

		outputStream.writeInt(mapId);

		outputStream.writeInt(bigForcesId);

		outputStream.writeInt(forcesId);

		outputStream.writeInt(status);

		outputStream.writeInt(forcesType);

		outputStream.writeInt(times);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		userId = inputStream.readUTF();

		mapId = inputStream.readInt();

		bigForcesId = inputStream.readInt();

		forcesId = inputStream.readInt();

		status = inputStream.readInt();

		forcesType = inputStream.readInt();

		times = inputStream.readInt();


	}
	
		/**用户编号**/
    public String getUserId() {
		return userId;
	}
	/**用户编号**/
    public void setUserId(String userId) {
		this.userId = userId;
	}
	/**关卡地图id**/
    public Integer getMapId() {
		return mapId;
	}
	/**关卡地图id**/
    public void setMapId(Integer mapId) {
		this.mapId = mapId;
	}
	/**大关卡id**/
    public Integer getBigForcesId() {
		return bigForcesId;
	}
	/**大关卡id**/
    public void setBigForcesId(Integer bigForcesId) {
		this.bigForcesId = bigForcesId;
	}
	/**关卡id**/
    public Integer getForcesId() {
		return forcesId;
	}
	/**关卡id**/
    public void setForcesId(Integer forcesId) {
		this.forcesId = forcesId;
	}
	/**状态-状态0未通关1通过了**/
    public Integer getStatus() {
		return status;
	}
	/**状态-状态0未通关1通过了**/
    public void setStatus(Integer status) {
		this.status = status;
	}
	/**1简单2普通3精英4困难如没有记录也没有通关**/
    public Integer getForcesType() {
		return forcesType;
	}
	/**1简单2普通3精英4困难如没有记录也没有通关**/
    public void setForcesType(Integer forcesType) {
		this.forcesType = forcesType;
	}
	/**今日已攻打次数**/
    public Integer getTimes() {
		return times;
	}
	/**今日已攻打次数**/
    public void setTimes(Integer times) {
		this.times = times;
	}

	
	
}
