package com.fantingame.game.msgbody.server.admin;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;


/**邮件对象**/
public class ResUserMailBO implements ICodeAble {

		/**用户邮件id**/
	private Integer userMailId=0;
	/**用户id**/
	private String userId="";
	/**邮件逻辑类型**/
	private Integer emailType=0;
	/**显示类型**/
	private Integer emailShowType=0;
	/**系统邮件id**/
	private String systemMailId="";
	/**邮件状态**/
	private Integer status=0;
	/**领取状态**/
	private Integer receiveStatus=0;
	/**参数**/
	private String param="";
	/**创建时间**/
	private Long createdTime=0l;
	/**更新时间**/
	private Long updatedTime=0l;
	/**来源id**/
	private Integer sourceId=0;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		outputStream.writeInt(userMailId);

		outputStream.writeUTF(userId);

		outputStream.writeInt(emailType);

		outputStream.writeInt(emailShowType);

		outputStream.writeUTF(systemMailId);

		outputStream.writeInt(status);

		outputStream.writeInt(receiveStatus);

		outputStream.writeUTF(param);

		outputStream.writeLong(createdTime);

		outputStream.writeLong(updatedTime);

		outputStream.writeInt(sourceId);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		userMailId = inputStream.readInt();

		userId = inputStream.readUTF();

		emailType = inputStream.readInt();

		emailShowType = inputStream.readInt();

		systemMailId = inputStream.readUTF();

		status = inputStream.readInt();

		receiveStatus = inputStream.readInt();

		param = inputStream.readUTF();

		createdTime = inputStream.readLong();

		updatedTime = inputStream.readLong();

		sourceId = inputStream.readInt();


	}
	
		/**用户邮件id**/
    public Integer getUserMailId() {
		return userMailId;
	}
	/**用户邮件id**/
    public void setUserMailId(Integer userMailId) {
		this.userMailId = userMailId;
	}
	/**用户id**/
    public String getUserId() {
		return userId;
	}
	/**用户id**/
    public void setUserId(String userId) {
		this.userId = userId;
	}
	/**邮件逻辑类型**/
    public Integer getEmailType() {
		return emailType;
	}
	/**邮件逻辑类型**/
    public void setEmailType(Integer emailType) {
		this.emailType = emailType;
	}
	/**显示类型**/
    public Integer getEmailShowType() {
		return emailShowType;
	}
	/**显示类型**/
    public void setEmailShowType(Integer emailShowType) {
		this.emailShowType = emailShowType;
	}
	/**系统邮件id**/
    public String getSystemMailId() {
		return systemMailId;
	}
	/**系统邮件id**/
    public void setSystemMailId(String systemMailId) {
		this.systemMailId = systemMailId;
	}
	/**邮件状态**/
    public Integer getStatus() {
		return status;
	}
	/**邮件状态**/
    public void setStatus(Integer status) {
		this.status = status;
	}
	/**领取状态**/
    public Integer getReceiveStatus() {
		return receiveStatus;
	}
	/**领取状态**/
    public void setReceiveStatus(Integer receiveStatus) {
		this.receiveStatus = receiveStatus;
	}
	/**参数**/
    public String getParam() {
		return param;
	}
	/**参数**/
    public void setParam(String param) {
		this.param = param;
	}
	/**创建时间**/
    public Long getCreatedTime() {
		return createdTime;
	}
	/**创建时间**/
    public void setCreatedTime(Long createdTime) {
		this.createdTime = createdTime;
	}
	/**更新时间**/
    public Long getUpdatedTime() {
		return updatedTime;
	}
	/**更新时间**/
    public void setUpdatedTime(Long updatedTime) {
		this.updatedTime = updatedTime;
	}
	/**来源id**/
    public Integer getSourceId() {
		return sourceId;
	}
	/**来源id**/
    public void setSourceId(Integer sourceId) {
		this.sourceId = sourceId;
	}

	
	
}
