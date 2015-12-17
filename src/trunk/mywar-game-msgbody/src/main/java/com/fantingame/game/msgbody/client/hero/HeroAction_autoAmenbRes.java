package com.fantingame.game.msgbody.client.hero;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;

import com.fantingame.game.msgbody.client.hero.UserHeroBO;
import java.util.List;
import java.util.ArrayList;

/**自动上阵**/
public class HeroAction_autoAmenbRes implements ICodeAble {

		/**需要更新的英雄列表，如果客户端缓存中存在该列表中的英雄对象，则替换**/
	private List<UserHeroBO> updateHeroList=null;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		
        if(updateHeroList==null||updateHeroList.size()==0){
			outputStream.writeInt(0);
		}else{
			outputStream.writeInt(updateHeroList.size());
		}
		if(updateHeroList!=null&&updateHeroList.size()>0){
			for(int updateHeroListi=0;updateHeroListi<updateHeroList.size();updateHeroListi++){
				updateHeroList.get(updateHeroListi).encode(outputStream);
			}
		}
	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		
        int updateHeroListSize = inputStream.readInt();
		if(updateHeroListSize>0){
			updateHeroList = new ArrayList<UserHeroBO>();
			for(int updateHeroListi=0;updateHeroListi<updateHeroListSize;updateHeroListi++){
				 UserHeroBO entry = new UserHeroBO();entry.decode(inputStream);updateHeroList.add(entry);
			}
		}
	}
	
		/**需要更新的英雄列表，如果客户端缓存中存在该列表中的英雄对象，则替换**/
    public List<UserHeroBO> getUpdateHeroList() {
		return updateHeroList;
	}
	/**需要更新的英雄列表，如果客户端缓存中存在该列表中的英雄对象，则替换**/
    public void setUpdateHeroList(List<UserHeroBO> updateHeroList) {
		this.updateHeroList = updateHeroList;
	}

	
	
}
