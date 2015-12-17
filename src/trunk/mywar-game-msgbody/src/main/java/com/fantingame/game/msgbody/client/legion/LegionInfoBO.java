package com.fantingame.game.msgbody.client.legion;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;


/**军团信息对象**/
public class LegionInfoBO implements ICodeAble {

		/**军团id**/
	private String legionId="";
	/**军团经验**/
	private Integer exp=0;
	/**军团等级**/
	private Integer level=0;
	/**军团名称**/
	private String legionName="";
	/**军团战斗力**/
	private Integer power=0;
	/**军团公告**/
	private String notice="";
	/**军团宣言**/
	private String declaration="";
	/**军团长姓名**/
	private String legionLeaderName="";
	/**最大人数**/
	private Integer maxNum=0;
	/**当前军团人数**/
	private Integer currentNum=0;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		outputStream.writeUTF(legionId);

		outputStream.writeInt(exp);

		outputStream.writeInt(level);

		outputStream.writeUTF(legionName);

		outputStream.writeInt(power);

		outputStream.writeUTF(notice);

		outputStream.writeUTF(declaration);

		outputStream.writeUTF(legionLeaderName);

		outputStream.writeInt(maxNum);

		outputStream.writeInt(currentNum);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		legionId = inputStream.readUTF();

		exp = inputStream.readInt();

		level = inputStream.readInt();

		legionName = inputStream.readUTF();

		power = inputStream.readInt();

		notice = inputStream.readUTF();

		declaration = inputStream.readUTF();

		legionLeaderName = inputStream.readUTF();

		maxNum = inputStream.readInt();

		currentNum = inputStream.readInt();


	}
	
		/**军团id**/
    public String getLegionId() {
		return legionId;
	}
	/**军团id**/
    public void setLegionId(String legionId) {
		this.legionId = legionId;
	}
	/**军团经验**/
    public Integer getExp() {
		return exp;
	}
	/**军团经验**/
    public void setExp(Integer exp) {
		this.exp = exp;
	}
	/**军团等级**/
    public Integer getLevel() {
		return level;
	}
	/**军团等级**/
    public void setLevel(Integer level) {
		this.level = level;
	}
	/**军团名称**/
    public String getLegionName() {
		return legionName;
	}
	/**军团名称**/
    public void setLegionName(String legionName) {
		this.legionName = legionName;
	}
	/**军团战斗力**/
    public Integer getPower() {
		return power;
	}
	/**军团战斗力**/
    public void setPower(Integer power) {
		this.power = power;
	}
	/**军团公告**/
    public String getNotice() {
		return notice;
	}
	/**军团公告**/
    public void setNotice(String notice) {
		this.notice = notice;
	}
	/**军团宣言**/
    public String getDeclaration() {
		return declaration;
	}
	/**军团宣言**/
    public void setDeclaration(String declaration) {
		this.declaration = declaration;
	}
	/**军团长姓名**/
    public String getLegionLeaderName() {
		return legionLeaderName;
	}
	/**军团长姓名**/
    public void setLegionLeaderName(String legionLeaderName) {
		this.legionLeaderName = legionLeaderName;
	}
	/**最大人数**/
    public Integer getMaxNum() {
		return maxNum;
	}
	/**最大人数**/
    public void setMaxNum(Integer maxNum) {
		this.maxNum = maxNum;
	}
	/**当前军团人数**/
    public Integer getCurrentNum() {
		return currentNum;
	}
	/**当前军团人数**/
    public void setCurrentNum(Integer currentNum) {
		this.currentNum = currentNum;
	}

	
	
}
