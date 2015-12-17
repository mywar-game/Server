package com.fantingame.game.msgbody.client.scene;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;


/**加载场景完成**/
public class SceneAction_loadedReq implements ICodeAble {

		/**场景id**/
	private Integer sceneId=0;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		outputStream.writeInt(sceneId);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		sceneId = inputStream.readInt();


	}
	
		/**场景id**/
    public Integer getSceneId() {
		return sceneId;
	}
	/**场景id**/
    public void setSceneId(Integer sceneId) {
		this.sceneId = sceneId;
	}

	
	
}
