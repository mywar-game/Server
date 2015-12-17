package com.fantingame.game.msgbody.client.hero;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;

import com.fantingame.game.msgbody.client.hero.UserCareerInfoBO;
import java.util.List;
import java.util.ArrayList;

/**获取用户职业解锁信息**/
public class HeroAction_getUserCareerClearInfoRes implements ICodeAble {

		/**用户职业信息列表**/
	private List<UserCareerInfoBO> userCareerInfoList=null;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		
        if(userCareerInfoList==null||userCareerInfoList.size()==0){
			outputStream.writeInt(0);
		}else{
			outputStream.writeInt(userCareerInfoList.size());
		}
		if(userCareerInfoList!=null&&userCareerInfoList.size()>0){
			for(int userCareerInfoListi=0;userCareerInfoListi<userCareerInfoList.size();userCareerInfoListi++){
				userCareerInfoList.get(userCareerInfoListi).encode(outputStream);
			}
		}
	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		
        int userCareerInfoListSize = inputStream.readInt();
		if(userCareerInfoListSize>0){
			userCareerInfoList = new ArrayList<UserCareerInfoBO>();
			for(int userCareerInfoListi=0;userCareerInfoListi<userCareerInfoListSize;userCareerInfoListi++){
				 UserCareerInfoBO entry = new UserCareerInfoBO();entry.decode(inputStream);userCareerInfoList.add(entry);
			}
		}
	}
	
		/**用户职业信息列表**/
    public List<UserCareerInfoBO> getUserCareerInfoList() {
		return userCareerInfoList;
	}
	/**用户职业信息列表**/
    public void setUserCareerInfoList(List<UserCareerInfoBO> userCareerInfoList) {
		this.userCareerInfoList = userCareerInfoList;
	}

	
	
}
