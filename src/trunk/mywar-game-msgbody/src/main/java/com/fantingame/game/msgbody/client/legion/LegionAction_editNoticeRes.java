package com.fantingame.game.msgbody.client.legion;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;


/**修改公告**/
public class LegionAction_editNoticeRes implements ICodeAble {

		/**公告内容**/
	private String notice="";

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		outputStream.writeUTF(notice);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		notice = inputStream.readUTF();


	}
	
		/**公告内容**/
    public String getNotice() {
		return notice;
	}
	/**公告内容**/
    public void setNotice(String notice) {
		this.notice = notice;
	}

	
	
}
