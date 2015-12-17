package com.fantingame.game.msgbody.client.hero;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;


/**用户英雄对象**/
public class UserHeroBO implements ICodeAble {

		/**用户编号**/
	private String userId="";
	/**用户英雄唯一id**/
	private String userHeroId="";
	/**系统英雄唯一编号**/
	private Integer systemHeroId=0;
	/**英雄等级**/
	private Integer level=0;
	/**英雄经验**/
	private Integer exp=0;
	/**英雄装等**/
	private Integer effective=0;
	/**是否在场景中0不是1是**/
	private Integer isScene=0;
	/**位置0不在阵上1在阵上**/
	private Integer pos=0;
	/**是否为队长0不是1是**/
	private Integer isTeamLeader=0;
	/**英雄状态0在酒馆1在空闲的英雄2英雄在阵上3正在挖矿4正在修花圃5正在钓鱼**/
	private Integer status=0;
	/**星数**/
	private Integer star=0;
	/**英雄名称**/
	private String heroName="";

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		outputStream.writeUTF(userId);

		outputStream.writeUTF(userHeroId);

		outputStream.writeInt(systemHeroId);

		outputStream.writeInt(level);

		outputStream.writeInt(exp);

		outputStream.writeInt(effective);

		outputStream.writeInt(isScene);

		outputStream.writeInt(pos);

		outputStream.writeInt(isTeamLeader);

		outputStream.writeInt(status);

		outputStream.writeInt(star);

		outputStream.writeUTF(heroName);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		userId = inputStream.readUTF();

		userHeroId = inputStream.readUTF();

		systemHeroId = inputStream.readInt();

		level = inputStream.readInt();

		exp = inputStream.readInt();

		effective = inputStream.readInt();

		isScene = inputStream.readInt();

		pos = inputStream.readInt();

		isTeamLeader = inputStream.readInt();

		status = inputStream.readInt();

		star = inputStream.readInt();

		heroName = inputStream.readUTF();


	}
	
		/**用户编号**/
    public String getUserId() {
		return userId;
	}
	/**用户编号**/
    public void setUserId(String userId) {
		this.userId = userId;
	}
	/**用户英雄唯一id**/
    public String getUserHeroId() {
		return userHeroId;
	}
	/**用户英雄唯一id**/
    public void setUserHeroId(String userHeroId) {
		this.userHeroId = userHeroId;
	}
	/**系统英雄唯一编号**/
    public Integer getSystemHeroId() {
		return systemHeroId;
	}
	/**系统英雄唯一编号**/
    public void setSystemHeroId(Integer systemHeroId) {
		this.systemHeroId = systemHeroId;
	}
	/**英雄等级**/
    public Integer getLevel() {
		return level;
	}
	/**英雄等级**/
    public void setLevel(Integer level) {
		this.level = level;
	}
	/**英雄经验**/
    public Integer getExp() {
		return exp;
	}
	/**英雄经验**/
    public void setExp(Integer exp) {
		this.exp = exp;
	}
	/**英雄装等**/
    public Integer getEffective() {
		return effective;
	}
	/**英雄装等**/
    public void setEffective(Integer effective) {
		this.effective = effective;
	}
	/**是否在场景中0不是1是**/
    public Integer getIsScene() {
		return isScene;
	}
	/**是否在场景中0不是1是**/
    public void setIsScene(Integer isScene) {
		this.isScene = isScene;
	}
	/**位置0不在阵上1在阵上**/
    public Integer getPos() {
		return pos;
	}
	/**位置0不在阵上1在阵上**/
    public void setPos(Integer pos) {
		this.pos = pos;
	}
	/**是否为队长0不是1是**/
    public Integer getIsTeamLeader() {
		return isTeamLeader;
	}
	/**是否为队长0不是1是**/
    public void setIsTeamLeader(Integer isTeamLeader) {
		this.isTeamLeader = isTeamLeader;
	}
	/**英雄状态0在酒馆1在空闲的英雄2英雄在阵上3正在挖矿4正在修花圃5正在钓鱼**/
    public Integer getStatus() {
		return status;
	}
	/**英雄状态0在酒馆1在空闲的英雄2英雄在阵上3正在挖矿4正在修花圃5正在钓鱼**/
    public void setStatus(Integer status) {
		this.status = status;
	}
	/**星数**/
    public Integer getStar() {
		return star;
	}
	/**星数**/
    public void setStar(Integer star) {
		this.star = star;
	}
	/**英雄名称**/
    public String getHeroName() {
		return heroName;
	}
	/**英雄名称**/
    public void setHeroName(String heroName) {
		this.heroName = heroName;
	}

	
	
}
