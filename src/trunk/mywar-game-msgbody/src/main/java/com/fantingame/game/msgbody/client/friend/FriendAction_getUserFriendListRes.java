package com.fantingame.game.msgbody.client.friend;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;

import com.fantingame.game.msgbody.client.friend.UserFriendInfoBO;
import java.util.List;
import java.util.ArrayList;

/**获取好友列表**/
public class FriendAction_getUserFriendListRes implements ICodeAble {

		/**用户好友信息对象列表**/
	private List<UserFriendInfoBO> userFriendInfoBOList=null;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		
        if(userFriendInfoBOList==null||userFriendInfoBOList.size()==0){
			outputStream.writeInt(0);
		}else{
			outputStream.writeInt(userFriendInfoBOList.size());
		}
		if(userFriendInfoBOList!=null&&userFriendInfoBOList.size()>0){
			for(int userFriendInfoBOListi=0;userFriendInfoBOListi<userFriendInfoBOList.size();userFriendInfoBOListi++){
				userFriendInfoBOList.get(userFriendInfoBOListi).encode(outputStream);
			}
		}
	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		
        int userFriendInfoBOListSize = inputStream.readInt();
		if(userFriendInfoBOListSize>0){
			userFriendInfoBOList = new ArrayList<UserFriendInfoBO>();
			for(int userFriendInfoBOListi=0;userFriendInfoBOListi<userFriendInfoBOListSize;userFriendInfoBOListi++){
				 UserFriendInfoBO entry = new UserFriendInfoBO();entry.decode(inputStream);userFriendInfoBOList.add(entry);
			}
		}
	}
	
		/**用户好友信息对象列表**/
    public List<UserFriendInfoBO> getUserFriendInfoBOList() {
		return userFriendInfoBOList;
	}
	/**用户好友信息对象列表**/
    public void setUserFriendInfoBOList(List<UserFriendInfoBO> userFriendInfoBOList) {
		this.userFriendInfoBOList = userFriendInfoBOList;
	}

	
	
}
