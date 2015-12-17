package com.fantingame.game.msgbody.client.hero;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;


/**上阵团长技能**/
public class HeroAction_changeSkillPosReq implements ICodeAble {

		/**用户技能唯一id**/
	private String userSkillId="";
	/**要上阵到的位置（0为下阵）**/
	private Integer pos=0;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		outputStream.writeUTF(userSkillId);

		outputStream.writeInt(pos);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		userSkillId = inputStream.readUTF();

		pos = inputStream.readInt();


	}
	
		/**用户技能唯一id**/
    public String getUserSkillId() {
		return userSkillId;
	}
	/**用户技能唯一id**/
    public void setUserSkillId(String userSkillId) {
		this.userSkillId = userSkillId;
	}
	/**要上阵到的位置（0为下阵）**/
    public Integer getPos() {
		return pos;
	}
	/**要上阵到的位置（0为下阵）**/
    public void setPos(Integer pos) {
		this.pos = pos;
	}

	
	
}
