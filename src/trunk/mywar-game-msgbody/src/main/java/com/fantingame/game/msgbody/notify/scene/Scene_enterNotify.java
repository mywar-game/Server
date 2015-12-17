package com.fantingame.game.msgbody.notify.scene;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;


/**用户进入场景广播**/
public class Scene_enterNotify implements ICodeAble {

		/**进入场景的用户唯一编号**/
	private String userId="";
	/**用户角色模型id**/
	private Integer heroId=0;
	/**进入场景所在x坐标**/
	private Integer posX=0;
	/**进入场景所在y坐标**/
	private Integer posY=0;
	/**玩家名称**/
	private String userName="";

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		outputStream.writeUTF(userId);

		outputStream.writeInt(heroId);

		outputStream.writeInt(posX);

		outputStream.writeInt(posY);

		outputStream.writeUTF(userName);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		userId = inputStream.readUTF();

		heroId = inputStream.readInt();

		posX = inputStream.readInt();

		posY = inputStream.readInt();

		userName = inputStream.readUTF();


	}
	
		/**进入场景的用户唯一编号**/
    public String getUserId() {
		return userId;
	}
	/**进入场景的用户唯一编号**/
    public void setUserId(String userId) {
		this.userId = userId;
	}
	/**用户角色模型id**/
    public Integer getHeroId() {
		return heroId;
	}
	/**用户角色模型id**/
    public void setHeroId(Integer heroId) {
		this.heroId = heroId;
	}
	/**进入场景所在x坐标**/
    public Integer getPosX() {
		return posX;
	}
	/**进入场景所在x坐标**/
    public void setPosX(Integer posX) {
		this.posX = posX;
	}
	/**进入场景所在y坐标**/
    public Integer getPosY() {
		return posY;
	}
	/**进入场景所在y坐标**/
    public void setPosY(Integer posY) {
		this.posY = posY;
	}
	/**玩家名称**/
    public String getUserName() {
		return userName;
	}
	/**玩家名称**/
    public void setUserName(String userName) {
		this.userName = userName;
	}

	
	
}
