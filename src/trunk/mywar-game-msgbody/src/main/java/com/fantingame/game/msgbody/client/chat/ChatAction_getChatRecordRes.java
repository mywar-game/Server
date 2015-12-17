package com.fantingame.game.msgbody.client.chat;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;

import com.fantingame.game.msgbody.client.chat.UserChatRecordBO;
import java.util.List;
import java.util.ArrayList;

/**获取聊天记录**/
public class ChatAction_getChatRecordRes implements ICodeAble {

		/**用户聊天信息**/
	private List<UserChatRecordBO> userChatRecordList=null;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		
        if(userChatRecordList==null||userChatRecordList.size()==0){
			outputStream.writeInt(0);
		}else{
			outputStream.writeInt(userChatRecordList.size());
		}
		if(userChatRecordList!=null&&userChatRecordList.size()>0){
			for(int userChatRecordListi=0;userChatRecordListi<userChatRecordList.size();userChatRecordListi++){
				userChatRecordList.get(userChatRecordListi).encode(outputStream);
			}
		}
	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		
        int userChatRecordListSize = inputStream.readInt();
		if(userChatRecordListSize>0){
			userChatRecordList = new ArrayList<UserChatRecordBO>();
			for(int userChatRecordListi=0;userChatRecordListi<userChatRecordListSize;userChatRecordListi++){
				 UserChatRecordBO entry = new UserChatRecordBO();entry.decode(inputStream);userChatRecordList.add(entry);
			}
		}
	}
	
		/**用户聊天信息**/
    public List<UserChatRecordBO> getUserChatRecordList() {
		return userChatRecordList;
	}
	/**用户聊天信息**/
    public void setUserChatRecordList(List<UserChatRecordBO> userChatRecordList) {
		this.userChatRecordList = userChatRecordList;
	}

	
	
}
