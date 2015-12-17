package com.fantingame.game.msgbody.client.mail;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;

import com.fantingame.game.msgbody.client.goods.GoodsBeanBO;
import java.util.List;
import java.util.ArrayList;

/**用户邮件对象**/
public class UserMailBO implements ICodeAble {

		/**用户邮件id**/
	private Integer userMailId=0;
	/**邮件类型（-1用户间邮件）**/
	private Integer mailType=0;
	/**显示类型**/
	private Integer showType=0;
	/**邮件状态（0新邮件1已读2已删除）**/
	private Integer status=0;
	/**领取状态（0未领取1已领取）**/
	private Integer receiveStatus=0;
	/**内容**/
	private String content="";
	/**标题**/
	private String title="";
	/**署名**/
	private String sign="";
	/**来自谁的邮件，只有用户邮件才有**/
	private String fromUserId="";
	/**创建时间**/
	private Long createdTime=0l;
	/**过期时间**/
	private Integer expiredTime=0;
	/**附件物品**/
	private List<GoodsBeanBO> goodsBeanBOList=null;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		outputStream.writeInt(userMailId);

		outputStream.writeInt(mailType);

		outputStream.writeInt(showType);

		outputStream.writeInt(status);

		outputStream.writeInt(receiveStatus);

		outputStream.writeUTF(content);

		outputStream.writeUTF(title);

		outputStream.writeUTF(sign);

		outputStream.writeUTF(fromUserId);

		outputStream.writeLong(createdTime);

		outputStream.writeInt(expiredTime);

		
        if(goodsBeanBOList==null||goodsBeanBOList.size()==0){
			outputStream.writeInt(0);
		}else{
			outputStream.writeInt(goodsBeanBOList.size());
		}
		if(goodsBeanBOList!=null&&goodsBeanBOList.size()>0){
			for(int goodsBeanBOListi=0;goodsBeanBOListi<goodsBeanBOList.size();goodsBeanBOListi++){
				goodsBeanBOList.get(goodsBeanBOListi).encode(outputStream);
			}
		}
	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		userMailId = inputStream.readInt();

		mailType = inputStream.readInt();

		showType = inputStream.readInt();

		status = inputStream.readInt();

		receiveStatus = inputStream.readInt();

		content = inputStream.readUTF();

		title = inputStream.readUTF();

		sign = inputStream.readUTF();

		fromUserId = inputStream.readUTF();

		createdTime = inputStream.readLong();

		expiredTime = inputStream.readInt();

		
        int goodsBeanBOListSize = inputStream.readInt();
		if(goodsBeanBOListSize>0){
			goodsBeanBOList = new ArrayList<GoodsBeanBO>();
			for(int goodsBeanBOListi=0;goodsBeanBOListi<goodsBeanBOListSize;goodsBeanBOListi++){
				 GoodsBeanBO entry = new GoodsBeanBO();entry.decode(inputStream);goodsBeanBOList.add(entry);
			}
		}
	}
	
		/**用户邮件id**/
    public Integer getUserMailId() {
		return userMailId;
	}
	/**用户邮件id**/
    public void setUserMailId(Integer userMailId) {
		this.userMailId = userMailId;
	}
	/**邮件类型（-1用户间邮件）**/
    public Integer getMailType() {
		return mailType;
	}
	/**邮件类型（-1用户间邮件）**/
    public void setMailType(Integer mailType) {
		this.mailType = mailType;
	}
	/**显示类型**/
    public Integer getShowType() {
		return showType;
	}
	/**显示类型**/
    public void setShowType(Integer showType) {
		this.showType = showType;
	}
	/**邮件状态（0新邮件1已读2已删除）**/
    public Integer getStatus() {
		return status;
	}
	/**邮件状态（0新邮件1已读2已删除）**/
    public void setStatus(Integer status) {
		this.status = status;
	}
	/**领取状态（0未领取1已领取）**/
    public Integer getReceiveStatus() {
		return receiveStatus;
	}
	/**领取状态（0未领取1已领取）**/
    public void setReceiveStatus(Integer receiveStatus) {
		this.receiveStatus = receiveStatus;
	}
	/**内容**/
    public String getContent() {
		return content;
	}
	/**内容**/
    public void setContent(String content) {
		this.content = content;
	}
	/**标题**/
    public String getTitle() {
		return title;
	}
	/**标题**/
    public void setTitle(String title) {
		this.title = title;
	}
	/**署名**/
    public String getSign() {
		return sign;
	}
	/**署名**/
    public void setSign(String sign) {
		this.sign = sign;
	}
	/**来自谁的邮件，只有用户邮件才有**/
    public String getFromUserId() {
		return fromUserId;
	}
	/**来自谁的邮件，只有用户邮件才有**/
    public void setFromUserId(String fromUserId) {
		this.fromUserId = fromUserId;
	}
	/**创建时间**/
    public Long getCreatedTime() {
		return createdTime;
	}
	/**创建时间**/
    public void setCreatedTime(Long createdTime) {
		this.createdTime = createdTime;
	}
	/**过期时间**/
    public Integer getExpiredTime() {
		return expiredTime;
	}
	/**过期时间**/
    public void setExpiredTime(Integer expiredTime) {
		this.expiredTime = expiredTime;
	}
	/**附件物品**/
    public List<GoodsBeanBO> getGoodsBeanBOList() {
		return goodsBeanBOList;
	}
	/**附件物品**/
    public void setGoodsBeanBOList(List<GoodsBeanBO> goodsBeanBOList) {
		this.goodsBeanBOList = goodsBeanBOList;
	}

	
	
}
