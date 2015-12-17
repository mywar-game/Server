package com.fantingame.game.msgbody.client.activity;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;

import com.fantingame.game.msgbody.client.activity.UserLoginRewardBO;
import java.util.List;
import java.util.ArrayList;

/**查看每月签到信息**/
public class ActivityAction_getLoginReward30InfoRes implements ICodeAble {

		/**用户签到列表**/
	private List<UserLoginRewardBO> userLoginRewardList=null;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		
        if(userLoginRewardList==null||userLoginRewardList.size()==0){
			outputStream.writeInt(0);
		}else{
			outputStream.writeInt(userLoginRewardList.size());
		}
		if(userLoginRewardList!=null&&userLoginRewardList.size()>0){
			for(int userLoginRewardListi=0;userLoginRewardListi<userLoginRewardList.size();userLoginRewardListi++){
				userLoginRewardList.get(userLoginRewardListi).encode(outputStream);
			}
		}
	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		
        int userLoginRewardListSize = inputStream.readInt();
		if(userLoginRewardListSize>0){
			userLoginRewardList = new ArrayList<UserLoginRewardBO>();
			for(int userLoginRewardListi=0;userLoginRewardListi<userLoginRewardListSize;userLoginRewardListi++){
				 UserLoginRewardBO entry = new UserLoginRewardBO();entry.decode(inputStream);userLoginRewardList.add(entry);
			}
		}
	}
	
		/**用户签到列表**/
    public List<UserLoginRewardBO> getUserLoginRewardList() {
		return userLoginRewardList;
	}
	/**用户签到列表**/
    public void setUserLoginRewardList(List<UserLoginRewardBO> userLoginRewardList) {
		this.userLoginRewardList = userLoginRewardList;
	}

	
	
}
