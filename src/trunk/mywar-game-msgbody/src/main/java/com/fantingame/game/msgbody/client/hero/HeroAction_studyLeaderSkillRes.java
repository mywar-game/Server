package com.fantingame.game.msgbody.client.hero;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;

import com.fantingame.game.msgbody.client.hero.UserHeroSkillBO;

/**学习团长技能**/
public class HeroAction_studyLeaderSkillRes implements ICodeAble {

		/**用户金币数**/
	private Integer gold=0;
	/**用户钻石数**/
	private Integer money=0;
	/**用户英雄技能对象**/
	private UserHeroSkillBO userHeroSkillBO=null;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		outputStream.writeInt(gold);

		outputStream.writeInt(money);

		userHeroSkillBO.encode(outputStream);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		gold = inputStream.readInt();

		money = inputStream.readInt();

		userHeroSkillBO=new UserHeroSkillBO();    
		userHeroSkillBO.decode(inputStream);


	}
	
		/**用户金币数**/
    public Integer getGold() {
		return gold;
	}
	/**用户金币数**/
    public void setGold(Integer gold) {
		this.gold = gold;
	}
	/**用户钻石数**/
    public Integer getMoney() {
		return money;
	}
	/**用户钻石数**/
    public void setMoney(Integer money) {
		this.money = money;
	}
	/**用户英雄技能对象**/
    public UserHeroSkillBO getUserHeroSkillBO() {
		return userHeroSkillBO;
	}
	/**用户英雄技能对象**/
    public void setUserHeroSkillBO(UserHeroSkillBO userHeroSkillBO) {
		this.userHeroSkillBO = userHeroSkillBO;
	}

	
	
}
