package com.fantingame.game.msgbody.client.setting;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;


/**提交建议**/
public class SettingAction_commitAdviceReq implements ICodeAble {

		/**标题**/
	private String title="";
	/**内容**/
	private String content="";

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		outputStream.writeUTF(title);

		outputStream.writeUTF(content);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		title = inputStream.readUTF();

		content = inputStream.readUTF();


	}
	
		/**标题**/
    public String getTitle() {
		return title;
	}
	/**标题**/
    public void setTitle(String title) {
		this.title = title;
	}
	/**内容**/
    public String getContent() {
		return content;
	}
	/**内容**/
    public void setContent(String content) {
		this.content = content;
	}

	
	
}
