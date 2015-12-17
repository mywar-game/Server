package com.fantingame.game.msgbody.server.admin;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;


/**发送邮件**/
public class AdminAction_sendMailReq implements ICodeAble {

		/**邮件标题**/
	private String title="";
	/**邮件内容**/
	private String content="";
	/**道具ids**/
	private String toolIds="";
	/**玩家游戏id**/
	private String lodoIds="";
	/**目标**/
	private String target="";
	/**发送时间**/
	private Long date=0l;
	/**邮件源id**/
	private String sourceId="";
	/**渠道号**/
	private String partner="";

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		outputStream.writeUTF(title);

		outputStream.writeUTF(content);

		outputStream.writeUTF(toolIds);

		outputStream.writeUTF(lodoIds);

		outputStream.writeUTF(target);

		outputStream.writeLong(date);

		outputStream.writeUTF(sourceId);

		outputStream.writeUTF(partner);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		title = inputStream.readUTF();

		content = inputStream.readUTF();

		toolIds = inputStream.readUTF();

		lodoIds = inputStream.readUTF();

		target = inputStream.readUTF();

		date = inputStream.readLong();

		sourceId = inputStream.readUTF();

		partner = inputStream.readUTF();


	}
	
		/**邮件标题**/
    public String getTitle() {
		return title;
	}
	/**邮件标题**/
    public void setTitle(String title) {
		this.title = title;
	}
	/**邮件内容**/
    public String getContent() {
		return content;
	}
	/**邮件内容**/
    public void setContent(String content) {
		this.content = content;
	}
	/**道具ids**/
    public String getToolIds() {
		return toolIds;
	}
	/**道具ids**/
    public void setToolIds(String toolIds) {
		this.toolIds = toolIds;
	}
	/**玩家游戏id**/
    public String getLodoIds() {
		return lodoIds;
	}
	/**玩家游戏id**/
    public void setLodoIds(String lodoIds) {
		this.lodoIds = lodoIds;
	}
	/**目标**/
    public String getTarget() {
		return target;
	}
	/**目标**/
    public void setTarget(String target) {
		this.target = target;
	}
	/**发送时间**/
    public Long getDate() {
		return date;
	}
	/**发送时间**/
    public void setDate(Long date) {
		this.date = date;
	}
	/**邮件源id**/
    public String getSourceId() {
		return sourceId;
	}
	/**邮件源id**/
    public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}
	/**渠道号**/
    public String getPartner() {
		return partner;
	}
	/**渠道号**/
    public void setPartner(String partner) {
		this.partner = partner;
	}

	
	
}
