package com.fantingame.game.msgbody.notify.message;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;

import com.fantingame.game.msgbody.notify.message.MessageBO;
import java.util.List;
import java.util.ArrayList;

/**推送跑马灯**/
public class Message_pushMessageNotify implements ICodeAble {

		/**跑马灯消息对象**/
	private List<MessageBO> messageList=null;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		
        if(messageList==null||messageList.size()==0){
			outputStream.writeInt(0);
		}else{
			outputStream.writeInt(messageList.size());
		}
		if(messageList!=null&&messageList.size()>0){
			for(int messageListi=0;messageListi<messageList.size();messageListi++){
				messageList.get(messageListi).encode(outputStream);
			}
		}
	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		
        int messageListSize = inputStream.readInt();
		if(messageListSize>0){
			messageList = new ArrayList<MessageBO>();
			for(int messageListi=0;messageListi<messageListSize;messageListi++){
				 MessageBO entry = new MessageBO();entry.decode(inputStream);messageList.add(entry);
			}
		}
	}
	
		/**跑马灯消息对象**/
    public List<MessageBO> getMessageList() {
		return messageList;
	}
	/**跑马灯消息对象**/
    public void setMessageList(List<MessageBO> messageList) {
		this.messageList = messageList;
	}

	
	
}
