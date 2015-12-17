package com.fantingame.game.msgbody.client.hero;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;


/**用户英雄技能位置更新对象**/
public class UserHeroSkillPosChangeBO implements ICodeAble {

		/**用户英雄技能唯一id**/
	private String userHeroSkillId="";
	/**31、32、33、34、35为团长技能位置**/
	private Integer pos=0;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		outputStream.writeUTF(userHeroSkillId);

		outputStream.writeInt(pos);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		userHeroSkillId = inputStream.readUTF();

		pos = inputStream.readInt();


	}
	
		/**用户英雄技能唯一id**/
    public String getUserHeroSkillId() {
		return userHeroSkillId;
	}
	/**用户英雄技能唯一id**/
    public void setUserHeroSkillId(String userHeroSkillId) {
		this.userHeroSkillId = userHeroSkillId;
	}
	/**31、32、33、34、35为团长技能位置**/
    public Integer getPos() {
		return pos;
	}
	/**31、32、33、34、35为团长技能位置**/
    public void setPos(Integer pos) {
		this.pos = pos;
	}

	
	
}
