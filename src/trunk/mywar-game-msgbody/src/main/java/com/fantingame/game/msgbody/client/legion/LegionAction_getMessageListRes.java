package com.fantingame.game.msgbody.client.legion;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;

import com.fantingame.game.msgbody.client.legion.UserMessageInfoBO;
import java.util.List;
import java.util.ArrayList;

/**获取留言信息列表**/
public class LegionAction_getMessageListRes implements ICodeAble {

		/**用户留言信息列表**/
	private List<UserMessageInfoBO> userMessageInfoList=null;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		
        if(userMessageInfoList==null||userMessageInfoList.size()==0){
			outputStream.writeInt(0);
		}else{
			outputStream.writeInt(userMessageInfoList.size());
		}
		if(userMessageInfoList!=null&&userMessageInfoList.size()>0){
			for(int userMessageInfoListi=0;userMessageInfoListi<userMessageInfoList.size();userMessageInfoListi++){
				userMessageInfoList.get(userMessageInfoListi).encode(outputStream);
			}
		}
	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		
        int userMessageInfoListSize = inputStream.readInt();
		if(userMessageInfoListSize>0){
			userMessageInfoList = new ArrayList<UserMessageInfoBO>();
			for(int userMessageInfoListi=0;userMessageInfoListi<userMessageInfoListSize;userMessageInfoListi++){
				 UserMessageInfoBO entry = new UserMessageInfoBO();entry.decode(inputStream);userMessageInfoList.add(entry);
			}
		}
	}
	
		/**用户留言信息列表**/
    public List<UserMessageInfoBO> getUserMessageInfoList() {
		return userMessageInfoList;
	}
	/**用户留言信息列表**/
    public void setUserMessageInfoList(List<UserMessageInfoBO> userMessageInfoList) {
		this.userMessageInfoList = userMessageInfoList;
	}

	
	
}
