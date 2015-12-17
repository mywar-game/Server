package com.fantingame.game.msgbody.client.life;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;

import com.fantingame.game.msgbody.client.life.UserLifeInfoBO;
import java.util.List;
import java.util.ArrayList;

/**获取用户生活技能信息**/
public class LifeAction_getUserLifeInfoRes implements ICodeAble {

		/**用户生活信息对象列表**/
	private List<UserLifeInfoBO> userLifeInfoBOList=null;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		
        if(userLifeInfoBOList==null||userLifeInfoBOList.size()==0){
			outputStream.writeInt(0);
		}else{
			outputStream.writeInt(userLifeInfoBOList.size());
		}
		if(userLifeInfoBOList!=null&&userLifeInfoBOList.size()>0){
			for(int userLifeInfoBOListi=0;userLifeInfoBOListi<userLifeInfoBOList.size();userLifeInfoBOListi++){
				userLifeInfoBOList.get(userLifeInfoBOListi).encode(outputStream);
			}
		}
	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		
        int userLifeInfoBOListSize = inputStream.readInt();
		if(userLifeInfoBOListSize>0){
			userLifeInfoBOList = new ArrayList<UserLifeInfoBO>();
			for(int userLifeInfoBOListi=0;userLifeInfoBOListi<userLifeInfoBOListSize;userLifeInfoBOListi++){
				 UserLifeInfoBO entry = new UserLifeInfoBO();entry.decode(inputStream);userLifeInfoBOList.add(entry);
			}
		}
	}
	
		/**用户生活信息对象列表**/
    public List<UserLifeInfoBO> getUserLifeInfoBOList() {
		return userLifeInfoBOList;
	}
	/**用户生活信息对象列表**/
    public void setUserLifeInfoBOList(List<UserLifeInfoBO> userLifeInfoBOList) {
		this.userLifeInfoBOList = userLifeInfoBOList;
	}

	
	
}
