package com.fantingame.game.msgbody.client.scene;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;


/**开启场景**/
public class SceneAction_openRes implements ICodeAble {

		/**场景id，客户端需要将该sceneId加入到已开启场景列表中**/
	private Integer sceneId=0;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		outputStream.writeInt(sceneId);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		sceneId = inputStream.readInt();


	}
	
		/**场景id，客户端需要将该sceneId加入到已开启场景列表中**/
    public Integer getSceneId() {
		return sceneId;
	}
	/**场景id，客户端需要将该sceneId加入到已开启场景列表中**/
    public void setSceneId(Integer sceneId) {
		this.sceneId = sceneId;
	}

	
	
}
