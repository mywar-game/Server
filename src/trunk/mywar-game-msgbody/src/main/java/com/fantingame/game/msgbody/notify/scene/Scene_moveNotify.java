package com.fantingame.game.msgbody.notify.scene;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;


/**场景中移动广播**/
public class Scene_moveNotify implements ICodeAble {

		/**用户唯一编号**/
	private String userId="";
	/**移动目的地的x坐标**/
	private Integer posX=0;
	/**移动目的地y坐标**/
	private Integer posY=0;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		outputStream.writeUTF(userId);

		outputStream.writeInt(posX);

		outputStream.writeInt(posY);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		userId = inputStream.readUTF();

		posX = inputStream.readInt();

		posY = inputStream.readInt();


	}
	
		/**用户唯一编号**/
    public String getUserId() {
		return userId;
	}
	/**用户唯一编号**/
    public void setUserId(String userId) {
		this.userId = userId;
	}
	/**移动目的地的x坐标**/
    public Integer getPosX() {
		return posX;
	}
	/**移动目的地的x坐标**/
    public void setPosX(Integer posX) {
		this.posX = posX;
	}
	/**移动目的地y坐标**/
    public Integer getPosY() {
		return posY;
	}
	/**移动目的地y坐标**/
    public void setPosY(Integer posY) {
		this.posY = posY;
	}

	
	
}
