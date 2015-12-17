package com.fantingame.game.msgbody.notify.achievement;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;

import com.fantingame.game.msgbody.client.achievement.UserAchievementBO;
import java.util.List;
import java.util.ArrayList;

/**成就信息变更推送接口**/
public class Achievement_updateNotify implements ICodeAble {

		/**需要更新的用户成就信息**/
	private List<UserAchievementBO> updateUserAchievementList=null;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		
        if(updateUserAchievementList==null||updateUserAchievementList.size()==0){
			outputStream.writeInt(0);
		}else{
			outputStream.writeInt(updateUserAchievementList.size());
		}
		if(updateUserAchievementList!=null&&updateUserAchievementList.size()>0){
			for(int updateUserAchievementListi=0;updateUserAchievementListi<updateUserAchievementList.size();updateUserAchievementListi++){
				updateUserAchievementList.get(updateUserAchievementListi).encode(outputStream);
			}
		}
	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		
        int updateUserAchievementListSize = inputStream.readInt();
		if(updateUserAchievementListSize>0){
			updateUserAchievementList = new ArrayList<UserAchievementBO>();
			for(int updateUserAchievementListi=0;updateUserAchievementListi<updateUserAchievementListSize;updateUserAchievementListi++){
				 UserAchievementBO entry = new UserAchievementBO();entry.decode(inputStream);updateUserAchievementList.add(entry);
			}
		}
	}
	
		/**需要更新的用户成就信息**/
    public List<UserAchievementBO> getUpdateUserAchievementList() {
		return updateUserAchievementList;
	}
	/**需要更新的用户成就信息**/
    public void setUpdateUserAchievementList(List<UserAchievementBO> updateUserAchievementList) {
		this.updateUserAchievementList = updateUserAchievementList;
	}

	
	
}
