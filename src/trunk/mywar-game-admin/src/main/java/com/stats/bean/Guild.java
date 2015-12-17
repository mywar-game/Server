package com.stats.bean;

import java.io.IOException;

import com.framework.server.io.iface.IXInputStream;
import com.framework.server.io.iface.IXOutStream;
import com.framework.server.msg.model.ICodeAble;

public class Guild implements ICodeAble {
	
	/** 军团编号 */
	private long guildId;
	
	/** 军团名称 */
	private String guildName;
	
	/** 军团等级 */
	private int guildLevel;
	
	/** 军团荣誉 */
	private int honor;
	
	/** 军团公告  */
	private String guildNotice;
	
	/** 军团粮食 */
	private int guildGrain;
	
	/** 军团矿产 */
	private int guildMineral;
	
	/** 军团金钱 */
	private int guildMoney;
	
	/** 军团代币 */
	private int guildGolden;
	
	/** 军团排行 */
	private int guildRank;
	
	/** 军团长 */
	private long guildLeaderId;
	
	/** 军团长名称 */
	private String guildLeaderName;
	
	/** 军团人口 */
	private int guildPopulation;
	
	/** 军团最大人口数 */
	private int guildMaxPopulation;
	
	/** 军团官员数量 */
	private int guildOfficeNum;
	
	/** 军团扩建次数  */
	private int guildExpansion;
	
	/** 军团资源最大上限 */
	private int guildMaxResource;
	
	/** 军团科技--众志成城 */
	private int technologyExp;
	
	/** 军团科技--军团仓库 */
	private int technologyMaxResource;
	
	/** 军团科技--建筑加速 */
	private int technolgoyBuildingCd;
	
	/** 军团科技--钢铁防护 */
	private int technolgoyHeroDefense;
	
	/** 军团科技--加强生存 */
	private int technologyHeroLife;
	
	/** 军团科技--集中火力 */
	private int technologyHeroAttack;
	
	public void decode(IXInputStream dataInputStream) throws IOException {
		guildId = Long.valueOf(dataInputStream.readUTF());
		guildName = dataInputStream.readUTF();
		guildLevel = dataInputStream.readInt();
		honor = dataInputStream.readInt();
		guildNotice = dataInputStream.readUTF();
		guildGrain = dataInputStream.readInt();
		guildMineral = dataInputStream.readInt();
		guildMoney = dataInputStream.readInt();
		guildGolden = dataInputStream.readInt();
		guildRank = dataInputStream.readInt();
		guildLeaderId = Long.valueOf(dataInputStream.readUTF());
		guildLeaderName = dataInputStream.readUTF();
		guildPopulation = dataInputStream.readInt();
		guildMaxPopulation = dataInputStream.readInt();
		guildOfficeNum = dataInputStream.readInt();
		guildExpansion = dataInputStream.readInt();
		guildMaxResource = dataInputStream.readInt();
		technologyExp = dataInputStream.readInt();
		technologyMaxResource = dataInputStream.readInt();
		technolgoyBuildingCd = dataInputStream.readInt();
		technolgoyHeroDefense = dataInputStream.readInt();
		technologyHeroLife = dataInputStream.readInt();
		technologyHeroAttack = dataInputStream.readInt();
	}

	public void encode(IXOutStream dataOutputStream) throws IOException {
		dataOutputStream.writeUTF(guildId + "");
		dataOutputStream.writeUTF(guildName);
		dataOutputStream.writeInt(guildLevel);
		dataOutputStream.writeInt(honor);
		dataOutputStream.writeUTF(guildNotice);
		dataOutputStream.writeInt(guildGrain);
		dataOutputStream.writeInt(guildMineral);
		dataOutputStream.writeInt(guildMoney);
		dataOutputStream.writeInt(guildGolden);
		dataOutputStream.writeInt(guildRank);
		dataOutputStream.writeUTF(guildLeaderId + "");
		dataOutputStream.writeUTF(guildLeaderName);
		dataOutputStream.writeInt(guildPopulation);
		dataOutputStream.writeInt(guildMaxPopulation);
		dataOutputStream.writeInt(guildOfficeNum);
		dataOutputStream.writeInt(guildExpansion);
		dataOutputStream.writeInt(guildMaxResource);
		dataOutputStream.writeInt(technologyExp);
		dataOutputStream.writeInt(technologyMaxResource);
		dataOutputStream.writeInt(technolgoyBuildingCd);
		dataOutputStream.writeInt(technolgoyHeroDefense);
		dataOutputStream.writeInt(technologyHeroLife);
		dataOutputStream.writeInt(technologyHeroAttack);
	}
	
	public long getGuildId() {
		return guildId;
	}

	public void setGuildId(long guildId) {
		this.guildId = guildId;
	}

	public String getGuildName() {
		return guildName;
	}

	public void setGuildName(String guildName) {
		this.guildName = guildName;
	}

	public String getGuildNotice() {
		return guildNotice;
	}

	public void setGuildNotice(String guildNotice) {
		this.guildNotice = guildNotice;
	}

	public int getGuildGrain() {
		return guildGrain;
	}

	public void setGuildGrain(int guildGrain) {
		this.guildGrain = guildGrain;
	}

	public int getGuildMineral() {
		return guildMineral;
	}

	public void setGuildMineral(int guildMineral) {
		this.guildMineral = guildMineral;
	}

	public int getGuildMoney() {
		return guildMoney;
	}

	public void setGuildMoney(int guildMoney) {
		this.guildMoney = guildMoney;
	}

	public int getGuildGolden() {
		return guildGolden;
	}

	public void setGuildGolden(int guildGolden) {
		this.guildGolden = guildGolden;
	}

	public int getGuildRank() {
		return guildRank;
	}

	public void setGuildRank(int guildRank) {
		this.guildRank = guildRank;
	}

	public int getTechnologyExp() {
		return technologyExp;
	}

	public void setTechnologyExp(int technologyExp) {
		this.technologyExp = technologyExp;
	}

	public int getTechnologyMaxResource() {
		return technologyMaxResource;
	}

	public void setTechnologyMaxResource(int technologyMaxResource) {
		this.technologyMaxResource = technologyMaxResource;
	}

	public int getTechnolgoyBuildingCd() {
		return technolgoyBuildingCd;
	}

	public void setTechnolgoyBuildingCd(int technolgoyBuildingCd) {
		this.technolgoyBuildingCd = technolgoyBuildingCd;
	}

	public int getTechnolgoyHeroDefense() {
		return technolgoyHeroDefense;
	}

	public void setTechnolgoyHeroDefense(int technolgoyHeroDefense) {
		this.technolgoyHeroDefense = technolgoyHeroDefense;
	}

	public int getTechnologyHeroLife() {
		return technologyHeroLife;
	}

	public void setTechnologyHeroLife(int technologyHeroLife) {
		this.technologyHeroLife = technologyHeroLife;
	}

	public int getTechnologyHeroAttack() {
		return technologyHeroAttack;
	}

	public void setTechnologyHeroAttack(int technologyHeroAttack) {
		this.technologyHeroAttack = technologyHeroAttack;
	}

	public int getGuildLevel() {
		return guildLevel;
	}

	public void setGuildLevel(int guildLevel) {
		this.guildLevel = guildLevel;
	}

	public int getHonor() {
		return honor;
	}

	public void setHonor(int honor) {
		this.honor = honor;
	}
	
	public int getGuildPopulation() {
		return guildPopulation;
	}

	public void setGuildPopulation(int guildPopulation) {
		this.guildPopulation = guildPopulation;
	}

	public int getGuildExpansion() {
		return guildExpansion;
	}

	public void setGuildExpansion(int guildExpansion) {
		this.guildExpansion = guildExpansion;
	}

	public long getGuildLeaderId() {
		return guildLeaderId;
	}

	public void setGuildLeaderId(long guildLeaderId) {
		this.guildLeaderId = guildLeaderId;
	}

	public int getGuildMaxPopulation() {
		return guildMaxPopulation;
	}

	public void setGuildMaxPopulation(int guildMaxPopulation) {
		this.guildMaxPopulation = guildMaxPopulation;
	}

	public int getGuildOfficeNum() {
		return guildOfficeNum;
	}

	public void setGuildOfficeNum(int guildOfficeNum) {
		this.guildOfficeNum = guildOfficeNum;
	}

	public String getGuildLeaderName() {
		return guildLeaderName;
	}

	public void setGuildLeaderName(String guildLeaderName) {
		this.guildLeaderName = guildLeaderName;
	}

	public int getGuildMaxResource() {
		return guildMaxResource;
	}

	public void setGuildMaxResource(int guildMaxResource) {
		this.guildMaxResource = guildMaxResource;
	}

	
}
