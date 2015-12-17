package com.fantingame.game.msgbody.client.boss;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;

import com.fantingame.game.msgbody.notify.boss.UserBossDataBO;
import java.util.List;
import java.util.ArrayList;

/**开始攻打世界boss**/
public class BossAction_startAttackBossRes implements ICodeAble {

		/**用户数据**/
	private List<UserBossDataBO> userDataList=null;
	/**是否为房主0否1是**/
	private Integer isRoomOwner=0;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		
        if(userDataList==null||userDataList.size()==0){
			outputStream.writeInt(0);
		}else{
			outputStream.writeInt(userDataList.size());
		}
		if(userDataList!=null&&userDataList.size()>0){
			for(int userDataListi=0;userDataListi<userDataList.size();userDataListi++){
				userDataList.get(userDataListi).encode(outputStream);
			}
		}		outputStream.writeInt(isRoomOwner);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		
        int userDataListSize = inputStream.readInt();
		if(userDataListSize>0){
			userDataList = new ArrayList<UserBossDataBO>();
			for(int userDataListi=0;userDataListi<userDataListSize;userDataListi++){
				 UserBossDataBO entry = new UserBossDataBO();entry.decode(inputStream);userDataList.add(entry);
			}
		}		isRoomOwner = inputStream.readInt();


	}
	
		/**用户数据**/
    public List<UserBossDataBO> getUserDataList() {
		return userDataList;
	}
	/**用户数据**/
    public void setUserDataList(List<UserBossDataBO> userDataList) {
		this.userDataList = userDataList;
	}
	/**是否为房主0否1是**/
    public Integer getIsRoomOwner() {
		return isRoomOwner;
	}
	/**是否为房主0否1是**/
    public void setIsRoomOwner(Integer isRoomOwner) {
		this.isRoomOwner = isRoomOwner;
	}

	
	
}
