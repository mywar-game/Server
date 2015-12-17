package com.fantingame.game.msgbody.client.hero;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;


/**技能道具对象**/
public class SkillToolBO implements ICodeAble {

		/**技能书道具id**/
	private Integer toolId=0;
	/**技能书数量**/
	private Integer toolNum=0;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		outputStream.writeInt(toolId);

		outputStream.writeInt(toolNum);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		toolId = inputStream.readInt();

		toolNum = inputStream.readInt();


	}
	
		/**技能书道具id**/
    public Integer getToolId() {
		return toolId;
	}
	/**技能书道具id**/
    public void setToolId(Integer toolId) {
		this.toolId = toolId;
	}
	/**技能书数量**/
    public Integer getToolNum() {
		return toolNum;
	}
	/**技能书数量**/
    public void setToolNum(Integer toolNum) {
		this.toolNum = toolNum;
	}

	
	
}
