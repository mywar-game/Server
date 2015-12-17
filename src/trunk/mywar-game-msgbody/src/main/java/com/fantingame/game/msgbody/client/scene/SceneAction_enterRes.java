package com.fantingame.game.msgbody.client.scene;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;


/**进入场景**/
public class SceneAction_enterRes implements ICodeAble {

		/**给用户分配的线数（从0开始计数即0为1线1为2线）**/
	private Integer userLineNum=0;
	/**场景的线条数**/
	private Integer sceneLineNum=0;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		outputStream.writeInt(userLineNum);

		outputStream.writeInt(sceneLineNum);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		userLineNum = inputStream.readInt();

		sceneLineNum = inputStream.readInt();


	}
	
		/**给用户分配的线数（从0开始计数即0为1线1为2线）**/
    public Integer getUserLineNum() {
		return userLineNum;
	}
	/**给用户分配的线数（从0开始计数即0为1线1为2线）**/
    public void setUserLineNum(Integer userLineNum) {
		this.userLineNum = userLineNum;
	}
	/**场景的线条数**/
    public Integer getSceneLineNum() {
		return sceneLineNum;
	}
	/**场景的线条数**/
    public void setSceneLineNum(Integer sceneLineNum) {
		this.sceneLineNum = sceneLineNum;
	}

	
	
}
