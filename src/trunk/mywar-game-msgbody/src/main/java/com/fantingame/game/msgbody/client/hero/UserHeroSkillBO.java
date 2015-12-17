package com.fantingame.game.msgbody.client.hero;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;


/**用户英雄技能对象**/
public class UserHeroSkillBO implements ICodeAble {

		/**用户编号**/
	private String userId="";
	/**用户英雄技能唯一id**/
	private String userHeroSkillId="";
	/**系统英雄技能唯一编号**/
	private Integer systemHeroSkillId=0;
	/**技能等级**/
	private Integer skillLevel=0;
	/**技能经验**/
	private Integer skillExp=0;
	/**技能所在英雄的位置,11、12、13、14、15为主动技能位置,21、22、23、24、25为被动技能的位置,31、32、33、34、35为团长技能位置**/
	private Integer pos=0;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		outputStream.writeUTF(userId);

		outputStream.writeUTF(userHeroSkillId);

		outputStream.writeInt(systemHeroSkillId);

		outputStream.writeInt(skillLevel);

		outputStream.writeInt(skillExp);

		outputStream.writeInt(pos);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		userId = inputStream.readUTF();

		userHeroSkillId = inputStream.readUTF();

		systemHeroSkillId = inputStream.readInt();

		skillLevel = inputStream.readInt();

		skillExp = inputStream.readInt();

		pos = inputStream.readInt();


	}
	
		/**用户编号**/
    public String getUserId() {
		return userId;
	}
	/**用户编号**/
    public void setUserId(String userId) {
		this.userId = userId;
	}
	/**用户英雄技能唯一id**/
    public String getUserHeroSkillId() {
		return userHeroSkillId;
	}
	/**用户英雄技能唯一id**/
    public void setUserHeroSkillId(String userHeroSkillId) {
		this.userHeroSkillId = userHeroSkillId;
	}
	/**系统英雄技能唯一编号**/
    public Integer getSystemHeroSkillId() {
		return systemHeroSkillId;
	}
	/**系统英雄技能唯一编号**/
    public void setSystemHeroSkillId(Integer systemHeroSkillId) {
		this.systemHeroSkillId = systemHeroSkillId;
	}
	/**技能等级**/
    public Integer getSkillLevel() {
		return skillLevel;
	}
	/**技能等级**/
    public void setSkillLevel(Integer skillLevel) {
		this.skillLevel = skillLevel;
	}
	/**技能经验**/
    public Integer getSkillExp() {
		return skillExp;
	}
	/**技能经验**/
    public void setSkillExp(Integer skillExp) {
		this.skillExp = skillExp;
	}
	/**技能所在英雄的位置,11、12、13、14、15为主动技能位置,21、22、23、24、25为被动技能的位置,31、32、33、34、35为团长技能位置**/
    public Integer getPos() {
		return pos;
	}
	/**技能所在英雄的位置,11、12、13、14、15为主动技能位置,21、22、23、24、25为被动技能的位置,31、32、33、34、35为团长技能位置**/
    public void setPos(Integer pos) {
		this.pos = pos;
	}

	
	
}
