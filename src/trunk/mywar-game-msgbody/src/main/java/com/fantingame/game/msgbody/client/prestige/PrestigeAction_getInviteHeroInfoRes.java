package com.fantingame.game.msgbody.client.prestige;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;

import com.fantingame.game.msgbody.client.prestige.InviteHeroBO;
import java.util.List;
import java.util.ArrayList;

/**获取用户酒馆招募英雄的信息**/
public class PrestigeAction_getInviteHeroInfoRes implements ICodeAble {

		/**英雄列表**/
	private List<InviteHeroBO> inviteHeroList=null;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		
        if(inviteHeroList==null||inviteHeroList.size()==0){
			outputStream.writeInt(0);
		}else{
			outputStream.writeInt(inviteHeroList.size());
		}
		if(inviteHeroList!=null&&inviteHeroList.size()>0){
			for(int inviteHeroListi=0;inviteHeroListi<inviteHeroList.size();inviteHeroListi++){
				inviteHeroList.get(inviteHeroListi).encode(outputStream);
			}
		}
	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		
        int inviteHeroListSize = inputStream.readInt();
		if(inviteHeroListSize>0){
			inviteHeroList = new ArrayList<InviteHeroBO>();
			for(int inviteHeroListi=0;inviteHeroListi<inviteHeroListSize;inviteHeroListi++){
				 InviteHeroBO entry = new InviteHeroBO();entry.decode(inputStream);inviteHeroList.add(entry);
			}
		}
	}
	
		/**英雄列表**/
    public List<InviteHeroBO> getInviteHeroList() {
		return inviteHeroList;
	}
	/**英雄列表**/
    public void setInviteHeroList(List<InviteHeroBO> inviteHeroList) {
		this.inviteHeroList = inviteHeroList;
	}

	
	
}
