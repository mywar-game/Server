package com.fantingame.game.msgbody.client.friend;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;

import com.fantingame.game.msgbody.client.friend.UserFriendInfoBO;
import java.util.List;
import java.util.ArrayList;

/**获取黑名单列表**/
public class FriendAction_getUserBlackListRes implements ICodeAble {

		/**黑名单列表**/
	private List<UserFriendInfoBO> userBlackInfoBOList=null;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		
        if(userBlackInfoBOList==null||userBlackInfoBOList.size()==0){
			outputStream.writeInt(0);
		}else{
			outputStream.writeInt(userBlackInfoBOList.size());
		}
		if(userBlackInfoBOList!=null&&userBlackInfoBOList.size()>0){
			for(int userBlackInfoBOListi=0;userBlackInfoBOListi<userBlackInfoBOList.size();userBlackInfoBOListi++){
				userBlackInfoBOList.get(userBlackInfoBOListi).encode(outputStream);
			}
		}
	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		
        int userBlackInfoBOListSize = inputStream.readInt();
		if(userBlackInfoBOListSize>0){
			userBlackInfoBOList = new ArrayList<UserFriendInfoBO>();
			for(int userBlackInfoBOListi=0;userBlackInfoBOListi<userBlackInfoBOListSize;userBlackInfoBOListi++){
				 UserFriendInfoBO entry = new UserFriendInfoBO();entry.decode(inputStream);userBlackInfoBOList.add(entry);
			}
		}
	}
	
		/**黑名单列表**/
    public List<UserFriendInfoBO> getUserBlackInfoBOList() {
		return userBlackInfoBOList;
	}
	/**黑名单列表**/
    public void setUserBlackInfoBOList(List<UserFriendInfoBO> userBlackInfoBOList) {
		this.userBlackInfoBOList = userBlackInfoBOList;
	}

	
	
}
