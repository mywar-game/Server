package com.fantingame.game.msgbody.client.achievement;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;

import com.fantingame.game.msgbody.client.achievement.UserAchievementBO;
import java.util.List;
import java.util.ArrayList;

/**获取用户成就系统信息**/
public class AchievementAction_getUserAchievementInfoRes implements ICodeAble {

		/**用户成就列表**/
	private List<UserAchievementBO> userAchievementList=null;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		
        if(userAchievementList==null||userAchievementList.size()==0){
			outputStream.writeInt(0);
		}else{
			outputStream.writeInt(userAchievementList.size());
		}
		if(userAchievementList!=null&&userAchievementList.size()>0){
			for(int userAchievementListi=0;userAchievementListi<userAchievementList.size();userAchievementListi++){
				userAchievementList.get(userAchievementListi).encode(outputStream);
			}
		}
	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		
        int userAchievementListSize = inputStream.readInt();
		if(userAchievementListSize>0){
			userAchievementList = new ArrayList<UserAchievementBO>();
			for(int userAchievementListi=0;userAchievementListi<userAchievementListSize;userAchievementListi++){
				 UserAchievementBO entry = new UserAchievementBO();entry.decode(inputStream);userAchievementList.add(entry);
			}
		}
	}
	
		/**用户成就列表**/
    public List<UserAchievementBO> getUserAchievementList() {
		return userAchievementList;
	}
	/**用户成就列表**/
    public void setUserAchievementList(List<UserAchievementBO> userAchievementList) {
		this.userAchievementList = userAchievementList;
	}

	
	
}
