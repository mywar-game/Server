package com.fantingame.game.msgbody.client.friend;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;

import com.fantingame.game.msgbody.client.friend.BattleUserInfoBO;
import java.util.List;
import java.util.ArrayList;

/**获取可参战用户**/
public class FriendAction_getJoinBattleUserListRes implements ICodeAble {

		/**用户聊天信息**/
	private List<BattleUserInfoBO> battleUserList=null;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		
        if(battleUserList==null||battleUserList.size()==0){
			outputStream.writeInt(0);
		}else{
			outputStream.writeInt(battleUserList.size());
		}
		if(battleUserList!=null&&battleUserList.size()>0){
			for(int battleUserListi=0;battleUserListi<battleUserList.size();battleUserListi++){
				battleUserList.get(battleUserListi).encode(outputStream);
			}
		}
	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		
        int battleUserListSize = inputStream.readInt();
		if(battleUserListSize>0){
			battleUserList = new ArrayList<BattleUserInfoBO>();
			for(int battleUserListi=0;battleUserListi<battleUserListSize;battleUserListi++){
				 BattleUserInfoBO entry = new BattleUserInfoBO();entry.decode(inputStream);battleUserList.add(entry);
			}
		}
	}
	
		/**用户聊天信息**/
    public List<BattleUserInfoBO> getBattleUserList() {
		return battleUserList;
	}
	/**用户聊天信息**/
    public void setBattleUserList(List<BattleUserInfoBO> battleUserList) {
		this.battleUserList = battleUserList;
	}

	
	
}
