package com.fantingame.game.msgbody.client.goods;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;

import com.fantingame.game.msgbody.client.goods.GoodsBeanBO;
import java.util.List;
import java.util.ArrayList;
import com.fantingame.game.msgbody.client.hero.UserHeroBO;
import com.fantingame.game.msgbody.client.hero.UserHeroSkillBO;
import com.fantingame.game.msgbody.client.equip.UserEquipBO;
import com.fantingame.game.msgbody.client.gemstone.UserGemstoneBO;

/**通用奖励对象列表**/
public class CommonGoodsBeanBO implements ICodeAble {

		/**奖励对象列表,如money，金币，经验，道具等等**/
	private List<GoodsBeanBO> goodsList=null;
	/**获得的英雄列表**/
	private List<UserHeroBO> heroList=null;
	/**获得的英雄技能列表(包括团长技能)**/
	private List<UserHeroSkillBO> heroSkillList=null;
	/**获得装备列表**/
	private List<UserEquipBO> equipList=null;
	/**获得用户宝石列表**/
	private List<UserGemstoneBO> gemstoneList=null;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		
        if(goodsList==null||goodsList.size()==0){
			outputStream.writeInt(0);
		}else{
			outputStream.writeInt(goodsList.size());
		}
		if(goodsList!=null&&goodsList.size()>0){
			for(int goodsListi=0;goodsListi<goodsList.size();goodsListi++){
				goodsList.get(goodsListi).encode(outputStream);
			}
		}		
        if(heroList==null||heroList.size()==0){
			outputStream.writeInt(0);
		}else{
			outputStream.writeInt(heroList.size());
		}
		if(heroList!=null&&heroList.size()>0){
			for(int heroListi=0;heroListi<heroList.size();heroListi++){
				heroList.get(heroListi).encode(outputStream);
			}
		}		
        if(heroSkillList==null||heroSkillList.size()==0){
			outputStream.writeInt(0);
		}else{
			outputStream.writeInt(heroSkillList.size());
		}
		if(heroSkillList!=null&&heroSkillList.size()>0){
			for(int heroSkillListi=0;heroSkillListi<heroSkillList.size();heroSkillListi++){
				heroSkillList.get(heroSkillListi).encode(outputStream);
			}
		}		
        if(equipList==null||equipList.size()==0){
			outputStream.writeInt(0);
		}else{
			outputStream.writeInt(equipList.size());
		}
		if(equipList!=null&&equipList.size()>0){
			for(int equipListi=0;equipListi<equipList.size();equipListi++){
				equipList.get(equipListi).encode(outputStream);
			}
		}		
        if(gemstoneList==null||gemstoneList.size()==0){
			outputStream.writeInt(0);
		}else{
			outputStream.writeInt(gemstoneList.size());
		}
		if(gemstoneList!=null&&gemstoneList.size()>0){
			for(int gemstoneListi=0;gemstoneListi<gemstoneList.size();gemstoneListi++){
				gemstoneList.get(gemstoneListi).encode(outputStream);
			}
		}
	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		
        int goodsListSize = inputStream.readInt();
		if(goodsListSize>0){
			goodsList = new ArrayList<GoodsBeanBO>();
			for(int goodsListi=0;goodsListi<goodsListSize;goodsListi++){
				 GoodsBeanBO entry = new GoodsBeanBO();entry.decode(inputStream);goodsList.add(entry);
			}
		}		
        int heroListSize = inputStream.readInt();
		if(heroListSize>0){
			heroList = new ArrayList<UserHeroBO>();
			for(int heroListi=0;heroListi<heroListSize;heroListi++){
				 UserHeroBO entry = new UserHeroBO();entry.decode(inputStream);heroList.add(entry);
			}
		}		
        int heroSkillListSize = inputStream.readInt();
		if(heroSkillListSize>0){
			heroSkillList = new ArrayList<UserHeroSkillBO>();
			for(int heroSkillListi=0;heroSkillListi<heroSkillListSize;heroSkillListi++){
				 UserHeroSkillBO entry = new UserHeroSkillBO();entry.decode(inputStream);heroSkillList.add(entry);
			}
		}		
        int equipListSize = inputStream.readInt();
		if(equipListSize>0){
			equipList = new ArrayList<UserEquipBO>();
			for(int equipListi=0;equipListi<equipListSize;equipListi++){
				 UserEquipBO entry = new UserEquipBO();entry.decode(inputStream);equipList.add(entry);
			}
		}		
        int gemstoneListSize = inputStream.readInt();
		if(gemstoneListSize>0){
			gemstoneList = new ArrayList<UserGemstoneBO>();
			for(int gemstoneListi=0;gemstoneListi<gemstoneListSize;gemstoneListi++){
				 UserGemstoneBO entry = new UserGemstoneBO();entry.decode(inputStream);gemstoneList.add(entry);
			}
		}
	}
	
		/**奖励对象列表,如money，金币，经验，道具等等**/
    public List<GoodsBeanBO> getGoodsList() {
		return goodsList;
	}
	/**奖励对象列表,如money，金币，经验，道具等等**/
    public void setGoodsList(List<GoodsBeanBO> goodsList) {
		this.goodsList = goodsList;
	}
	/**获得的英雄列表**/
    public List<UserHeroBO> getHeroList() {
		return heroList;
	}
	/**获得的英雄列表**/
    public void setHeroList(List<UserHeroBO> heroList) {
		this.heroList = heroList;
	}
	/**获得的英雄技能列表(包括团长技能)**/
    public List<UserHeroSkillBO> getHeroSkillList() {
		return heroSkillList;
	}
	/**获得的英雄技能列表(包括团长技能)**/
    public void setHeroSkillList(List<UserHeroSkillBO> heroSkillList) {
		this.heroSkillList = heroSkillList;
	}
	/**获得装备列表**/
    public List<UserEquipBO> getEquipList() {
		return equipList;
	}
	/**获得装备列表**/
    public void setEquipList(List<UserEquipBO> equipList) {
		this.equipList = equipList;
	}
	/**获得用户宝石列表**/
    public List<UserGemstoneBO> getGemstoneList() {
		return gemstoneList;
	}
	/**获得用户宝石列表**/
    public void setGemstoneList(List<UserGemstoneBO> gemstoneList) {
		this.gemstoneList = gemstoneList;
	}

	
	
}
