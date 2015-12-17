package com.fantingame.game.msgbody.client.hero;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;


/**学习团长技能**/
public class HeroAction_studyLeaderSkillReq implements ICodeAble {

		/**团长技能id**/
	private Integer systemHeroSkillId=0;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		outputStream.writeInt(systemHeroSkillId);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		systemHeroSkillId = inputStream.readInt();


	}
	
		/**团长技能id**/
    public Integer getSystemHeroSkillId() {
		return systemHeroSkillId;
	}
	/**团长技能id**/
    public void setSystemHeroSkillId(Integer systemHeroSkillId) {
		this.systemHeroSkillId = systemHeroSkillId;
	}

	
	
}
