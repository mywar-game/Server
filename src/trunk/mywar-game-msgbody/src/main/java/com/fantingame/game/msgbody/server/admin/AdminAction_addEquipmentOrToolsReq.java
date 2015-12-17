package com.fantingame.game.msgbody.server.admin;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;

import com.fantingame.game.msgbody.server.admin.AdminSendAttachBO;
import java.util.List;
import java.util.ArrayList;

/**发送道具或装备**/
public class AdminAction_addEquipmentOrToolsReq implements ICodeAble {

		/**发放道具装备对象**/
	private List<AdminSendAttachBO> sendAttachList=null;
	/**用户ids**/
	private String userIdStr="";
	/**是否全服发放**/
	private Boolean isSendAll=false;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {

		
        if(sendAttachList==null||sendAttachList.size()==0){
			outputStream.writeInt(0);
		}else{
			outputStream.writeInt(sendAttachList.size());
		}
		if(sendAttachList!=null&&sendAttachList.size()>0){
			for(int sendAttachListi=0;sendAttachListi<sendAttachList.size();sendAttachListi++){
				sendAttachList.get(sendAttachListi).encode(outputStream);
			}
		}		outputStream.writeUTF(userIdStr);

		outputStream.writeBoolean(isSendAll);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {

		
        int sendAttachListSize = inputStream.readInt();
		if(sendAttachListSize>0){
			sendAttachList = new ArrayList<AdminSendAttachBO>();
			for(int sendAttachListi=0;sendAttachListi<sendAttachListSize;sendAttachListi++){
				 AdminSendAttachBO entry = new AdminSendAttachBO();entry.decode(inputStream);sendAttachList.add(entry);
			}
		}		userIdStr = inputStream.readUTF();

		isSendAll = inputStream.readBoolean();


	}
	
		/**发放道具装备对象**/
    public List<AdminSendAttachBO> getSendAttachList() {
		return sendAttachList;
	}
	/**发放道具装备对象**/
    public void setSendAttachList(List<AdminSendAttachBO> sendAttachList) {
		this.sendAttachList = sendAttachList;
	}
	/**用户ids**/
    public String getUserIdStr() {
		return userIdStr;
	}
	/**用户ids**/
    public void setUserIdStr(String userIdStr) {
		this.userIdStr = userIdStr;
	}
	/**是否全服发放**/
    public Boolean getIsSendAll() {
		return isSendAll;
	}
	/**是否全服发放**/
    public void setIsSendAll(Boolean isSendAll) {
		this.isSendAll = isSendAll;
	}

	
	
}
