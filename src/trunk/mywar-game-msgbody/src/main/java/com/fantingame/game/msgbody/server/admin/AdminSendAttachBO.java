package com.fantingame.game.msgbody.server.admin;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;


/**用户发送道具对象**/
public class AdminSendAttachBO implements ICodeAble {

		/**附件id(装备常量id或者道具常量id)**/
	private Integer attachId=0;
	/**附件类型(1代表道具2代表装备)**/
	private Integer attachType=0;
	/**附件数量**/
	private Integer attachNum=0;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		outputStream.writeInt(attachId);

		outputStream.writeInt(attachType);

		outputStream.writeInt(attachNum);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		attachId = inputStream.readInt();

		attachType = inputStream.readInt();

		attachNum = inputStream.readInt();


	}
	
		/**附件id(装备常量id或者道具常量id)**/
    public Integer getAttachId() {
		return attachId;
	}
	/**附件id(装备常量id或者道具常量id)**/
    public void setAttachId(Integer attachId) {
		this.attachId = attachId;
	}
	/**附件类型(1代表道具2代表装备)**/
    public Integer getAttachType() {
		return attachType;
	}
	/**附件类型(1代表道具2代表装备)**/
    public void setAttachType(Integer attachType) {
		this.attachType = attachType;
	}
	/**附件数量**/
    public Integer getAttachNum() {
		return attachNum;
	}
	/**附件数量**/
    public void setAttachNum(Integer attachNum) {
		this.attachNum = attachNum;
	}

	
	
}
