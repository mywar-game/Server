package com.fantingame.game.msgbody.client.scene;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;


/**用户移动**/
public class SceneAction_moveReq implements ICodeAble {

		/**场景id**/
	private Integer sceneId=0;
	/**目的点x坐标**/
	private Integer posX=0;
	/**目的点y坐标**/
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
	
		/**场景id**/
    public Integer getSceneId() {
		return sceneId;
	}
	/**场景id**/
    public void setSceneId(Integer sceneId) {
		this.sceneId = sceneId;
	}
	/**目的点x坐标**/
    public Integer getPosX() {
		return posX;
	}
	/**目的点x坐标**/
    public void setPosX(Integer posX) {
		this.posX = posX;
	}
	/**目的点y坐标**/
    public Integer getPosY() {
		return posY;
	}
	/**目的点y坐标**/
    public void setPosY(Integer posY) {
		this.posY = posY;
	}

	
	
}
