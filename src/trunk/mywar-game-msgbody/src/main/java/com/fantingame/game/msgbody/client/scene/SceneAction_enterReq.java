package com.fantingame.game.msgbody.client.scene;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;


/**进入场景**/
public class SceneAction_enterReq implements ICodeAble {

		/**要进入的场景id**/
	private Integer sceneId=0;
	/**所在位置x坐标**/
	private Integer posX=0;
	/**所在位置y坐标**/
	private Integer posY=0;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		outputStream.writeInt(sceneId);

		outputStream.writeInt(posX);

		outputStream.writeInt(posY);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		sceneId = inputStream.readInt();

		posX = inputStream.readInt();

		posY = inputStream.readInt();


	}
	
		/**要进入的场景id**/
    public Integer getSceneId() {
		return sceneId;
	}
	/**要进入的场景id**/
    public void setSceneId(Integer sceneId) {
		this.sceneId = sceneId;
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
