package com.adminTool.bean;

import java.io.IOException;
import java.util.Date;

import com.framework.server.io.iface.IXInputStream;
import com.framework.server.io.iface.IXOutStream;
import com.framework.server.msg.model.ICodeAble;
public class UserMail implements ICodeAble{
	private long userMailId;
	private long userId;
	/**邮件主题**/
	private String theme;
	/**发送者玩家编号**/
	private long senderUserId;
	/**发送者名称**/
	private String senderName;
	/**发送时间**/
	private Date sendTime;
	/**内容**/
	private String content;
	/**邮件类型(1、玩家发的普通邮件2、军团3、好友邀请邮件4、系统邮件)**/
	private int mailType;
	/**好友邀请或者军团邀请信息的主键编号**/
	private long inviteId;
	/**邮件状态1、已读0未读**/
	private int state;
	private String mailAttach;
	/**是否领取了附件信息**/
	private boolean isGetAttach;

	public long getInviteId() {
		return inviteId;
	}

	public String getMailAttach() {
		return mailAttach;
	}

	public boolean isGetAttach() {
		return isGetAttach;
	}

	public void setGetAttach(boolean isGetAttach) {
		this.isGetAttach = isGetAttach;
	}

	public void setMailAttach(String mailAttach) {
		this.mailAttach = mailAttach;
	}

	public void setInviteId(long inviteId) {
		this.inviteId = inviteId;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public long getUserId() {
		return userId;
	}
	public long getUserMailId() {
		return userMailId;
	}

	public void setUserMailId(long userMailId) {
		this.userMailId = userMailId;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public long getSenderUserId() {
		return senderUserId;
	}

	public void setSenderUserId(long senderUserId) {
		this.senderUserId = senderUserId;
	}

	public Date getSendTime() {
		return sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public int getMailType() {
		return mailType;
	}

	public void setMailType(int mailType) {
		this.mailType = mailType;
	}

	public String getSenderName() {
		return senderName;
	}

	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		userMailId = Long.valueOf(inputStream.readUTF());
		userId = Long.valueOf(inputStream.readUTF());
		theme = inputStream.readUTF();
		senderUserId = Long.valueOf(inputStream.readUTF());
		senderName = inputStream.readUTF();
		sendTime = new Date(Long.parseLong(inputStream.readUTF()));
		content = inputStream.readUTF();
		mailType = inputStream.readInt();
		inviteId = Long.valueOf(inputStream.readUTF());
		state = inputStream.readInt();
		mailAttach = inputStream.readUTF();
		isGetAttach = inputStream.readBoolean();
	}

	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		outputStream.writeUTF(userMailId+"");
		outputStream.writeUTF(userId+"");
		outputStream.writeUTF(theme);
		outputStream.writeUTF(senderUserId+"");
		outputStream.writeUTF(senderName);
		outputStream.writeUTF(sendTime.getTime()+"");
		outputStream.writeUTF(content);
		outputStream.writeInt(mailType);
		outputStream.writeUTF(inviteId+"");
		outputStream.writeInt(state);
		outputStream.writeUTF(mailAttach);
		outputStream.writeBoolean(isGetAttach);
	}


}
