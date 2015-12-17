package com.fantingame.game.msgbody.client.hero;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;

import com.fantingame.game.msgbody.client.hero.UserHeroSkillPosChangeBO;
import java.util.List;
import java.util.ArrayList;

/**上阵团长技能**/
public class HeroAction_changeSkillPosRes implements ICodeAble {

		/**需要更新的技能列表，修改列表中对应技能的位置属性**/
	private List<UserHeroSkillPosChangeBO> updateHeroSkillPosList=null;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		
        if(updateHeroSkillPosList==null||updateHeroSkillPosList.size()==0){
			outputStream.writeInt(0);
		}else{
			outputStream.writeInt(updateHeroSkillPosList.size());
		}
		if(updateHeroSkillPosList!=null&&updateHeroSkillPosList.size()>0){
			for(int updateHeroSkillPosListi=0;updateHeroSkillPosListi<updateHeroSkillPosList.size();updateHeroSkillPosListi++){
				updateHeroSkillPosList.get(updateHeroSkillPosListi).encode(outputStream);
			}
		}
	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		
        int updateHeroSkillPosListSize = inputStream.readInt();
		if(updateHeroSkillPosListSize>0){
			updateHeroSkillPosList = new ArrayList<UserHeroSkillPosChangeBO>();
			for(int updateHeroSkillPosListi=0;updateHeroSkillPosListi<updateHeroSkillPosListSize;updateHeroSkillPosListi++){
				 UserHeroSkillPosChangeBO entry = new UserHeroSkillPosChangeBO();entry.decode(inputStream);updateHeroSkillPosList.add(entry);
			}
		}
	}
	
		/**需要更新的技能列表，修改列表中对应技能的位置属性**/
    public List<UserHeroSkillPosChangeBO> getUpdateHeroSkillPosList() {
		return updateHeroSkillPosList;
	}
	/**需要更新的技能列表，修改列表中对应技能的位置属性**/
    public void setUpdateHeroSkillPosList(List<UserHeroSkillPosChangeBO> updateHeroSkillPosList) {
		this.updateHeroSkillPosList = updateHeroSkillPosList;
	}

	
	
}
