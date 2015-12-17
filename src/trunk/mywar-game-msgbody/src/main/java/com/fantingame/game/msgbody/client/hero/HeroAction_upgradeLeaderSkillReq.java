package com.fantingame.game.msgbody.client.hero;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;

import com.fantingame.game.msgbody.client.hero.SkillToolBO;
import java.util.List;
import java.util.ArrayList;

/**升级团长技能**/
public class HeroAction_upgradeLeaderSkillReq implements ICodeAble {

		/**用户团长技能id**/
	private String userHeroSkillId="";
	/**技能道具列表**/
	private List<SkillToolBO> skillToolBOList=null;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		outputStream.writeUTF(userHeroSkillId);

		
        if(skillToolBOList==null||skillToolBOList.size()==0){
			outputStream.writeInt(0);
		}else{
			outputStream.writeInt(skillToolBOList.size());
		}
		if(skillToolBOList!=null&&skillToolBOList.size()>0){
			for(int skillToolBOListi=0;skillToolBOListi<skillToolBOList.size();skillToolBOListi++){
				skillToolBOList.get(skillToolBOListi).encode(outputStream);
			}
		}
	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		userHeroSkillId = inputStream.readUTF();

		
        int skillToolBOListSize = inputStream.readInt();
		if(skillToolBOListSize>0){
			skillToolBOList = new ArrayList<SkillToolBO>();
			for(int skillToolBOListi=0;skillToolBOListi<skillToolBOListSize;skillToolBOListi++){
				 SkillToolBO entry = new SkillToolBO();entry.decode(inputStream);skillToolBOList.add(entry);
			}
		}
	}
	
		/**用户团长技能id**/
    public String getUserHeroSkillId() {
		return userHeroSkillId;
	}
	/**用户团长技能id**/
    public void setUserHeroSkillId(String userHeroSkillId) {
		this.userHeroSkillId = userHeroSkillId;
	}
	/**技能道具列表**/
    public List<SkillToolBO> getSkillToolBOList() {
		return skillToolBOList;
	}
	/**技能道具列表**/
    public void setSkillToolBOList(List<SkillToolBO> skillToolBOList) {
		this.skillToolBOList = skillToolBOList;
	}

	
	
}
