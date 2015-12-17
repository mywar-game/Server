package com.fantingame.game.msgbody.client.scene;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;


/**换线**/
public class SceneAction_changeLineReq implements ICodeAble {

		/**场景id**/
	private Integer sceneId=0;
	/**目标线编号（从0开始计数即0为1线1为2线）**/
	private Integer targetLineNum=0;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		outputStream.writeInt(sceneId);

		outputStream.writeInt(targetLineNum);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		sceneId = inputStream.readInt();

		targetLineNum = inputStream.readInt();


	}
	
		/**场景id**/
    public Integer getSceneId() {
		return sceneId;
	}
	/**场景id**/
    public void setSceneId(Integer sceneId) {
		this.sceneId = sceneId;
	}
	/**目标线编号（从0开始计数即0为1线1为2线）**/
    public Integer getTargetLineNum() {
		return targetLineNum;
	}
	/**目标线编号（从0开始计数即0为1线1为2线）**/
    public void setTargetLineNum(Integer targetLineNum) {
		this.targetLineNum = targetLineNum;
	}

	
	
}
