package com.fantingame.game.msgbody.client.scene;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;


/**用户场景数据BO**/
public class UserSceneDataBO implements ICodeAble {

		/**用户编号**/
	private String userId="";
	/**用户名称**/
	private String userName="";
	/**角色模型id**/
	private Integer heroId=0;
	/**所在位置x坐标**/
	private Integer posX=0;
	/**所在位置y坐标**/
	private Integer posY=0;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		outputStream.writeUTF(userId);

		outputStream.writeUTF(userName);

		outputStream.writeInt(heroId);

		outputStream.writeInt(posX);

		outputStream.writeInt(posY);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		userId = inputStream.readUTF();

		userName = inputStream.readUTF();

		heroId = inputStream.readInt();

		posX = inputStream.readInt();

		posY = inputStream.readInt();


	}
	
		/**用户编号**/
    public String getUserId() {
		return userId;
	}
	/**用户编号**/
    public void setUserId(String userId) {
		this.userId = userId;
	}
	/**用户名称**/
    public String getUserName() {
		return userName;
	}
	/**用户名称**/
    public void setUserName(String userName) {
		this.userName = userName;
	}
	/**角色模型id**/
    public Integer getHeroId() {
		return heroId;
	}
	/**角色模型id**/
    public void setHeroId(Integer heroId) {
		this.heroId = heroId;
	}
	/**所在位置x坐标**/
    public Integer getPosX() {
		return posX;
	}
	/**所在位置x坐标**/
    public void setPosX(Integer posX) {
		this.posX = posX;
	}
	/**所在位置y坐标**/
    public Integer getPosY() {
		return posY;
	}
	/**所在位置y坐标**/
    public void setPosY(Integer posY) {
		this.posY = posY;
	}

	
	
}
