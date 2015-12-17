package com.fantingame.game.msgbody.client.pk;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;

import java.util.List;
import java.util.ArrayList;

/**竞技场排行榜对象**/
public class PkRankBO implements ICodeAble {

		/**排名**/
	private Integer rank=0;
	/**用户名称**/
	private String userName="";
	/**公会名称**/
	private String legionName="";
	/**用户等级**/
	private Integer level=0;
	/**防守装等**/
	private Integer defencePower=0;
	/**队长头像英雄id**/
	private Integer systemHeroId=0;
	/**防守阵营英雄id**/
	private List<Integer> defenceHeroList=null;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		outputStream.writeInt(rank);

		outputStream.writeUTF(userName);

		outputStream.writeUTF(legionName);

		outputStream.writeInt(level);

		outputStream.writeInt(defencePower);

		outputStream.writeInt(systemHeroId);

		
        if(defenceHeroList==null||defenceHeroList.size()==0){
			outputStream.writeInt(0);
		}else{
			outputStream.writeInt(defenceHeroList.size());
		}
		if(defenceHeroList!=null&&defenceHeroList.size()>0){
			for(int defenceHeroListi=0;defenceHeroListi<defenceHeroList.size();defenceHeroListi++){
						outputStream.writeInt(defenceHeroList.get(defenceHeroListi));


			}
		}
	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		rank = inputStream.readInt();

		userName = inputStream.readUTF();

		legionName = inputStream.readUTF();

		level = inputStream.readInt();

		defencePower = inputStream.readInt();

		systemHeroId = inputStream.readInt();

		
        int defenceHeroListSize = inputStream.readInt();
		if(defenceHeroListSize>0){
			defenceHeroList = new ArrayList<Integer>();
			for(int defenceHeroListi=0;defenceHeroListi<defenceHeroListSize;defenceHeroListi++){
				 defenceHeroList.add(inputStream.readInt());
			}
		}
	}
	
		/**排名**/
    public Integer getRank() {
		return rank;
	}
	/**排名**/
    public void setRank(Integer rank) {
		this.rank = rank;
	}
	/**用户名称**/
    public String getUserName() {
		return userName;
	}
	/**用户名称**/
    public void setUserName(String userName) {
		this.userName = userName;
	}
	/**公会名称**/
    public String getLegionName() {
		return legionName;
	}
	/**公会名称**/
    public void setLegionName(String legionName) {
		this.legionName = legionName;
	}
	/**用户等级**/
    public Integer getLevel() {
		return level;
	}
	/**用户等级**/
    public void setLevel(Integer level) {
		this.level = level;
	}
	/**防守装等**/
    public Integer getDefencePower() {
		return defencePower;
	}
	/**防守装等**/
    public void setDefencePower(Integer defencePower) {
		this.defencePower = defencePower;
	}
	/**队长头像英雄id**/
    public Integer getSystemHeroId() {
		return systemHeroId;
	}
	/**队长头像英雄id**/
    public void setSystemHeroId(Integer systemHeroId) {
		this.systemHeroId = systemHeroId;
	}
	/**防守阵营英雄id**/
    public List<Integer> getDefenceHeroList() {
		return defenceHeroList;
	}
	/**防守阵营英雄id**/
    public void setDefenceHeroList(List<Integer> defenceHeroList) {
		this.defenceHeroList = defenceHeroList;
	}

	
	
}
