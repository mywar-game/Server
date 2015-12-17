package com.fantingame.game.msgbody.notify.boss;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;

import com.fantingame.game.msgbody.client.hero.UserHeroBO;
import com.fantingame.game.msgbody.client.equip.UserEquipBO;
import java.util.List;
import java.util.ArrayList;

/**用户进入Boss战广播**/
public class Boss_enterNotify implements ICodeAble {

		/**进入场景的用户唯一编号**/
	private String userId="";
	/**用户英雄**/
	private UserHeroBO userHeroBO=null;
	/**用户装备列表**/
	private List<UserEquipBO> equipList=null;
	/**进入场景所在x坐标**/
	private Integer posX=0;
	/**进入场景所在y坐标**/
	private Integer posY=0;
	/**玩家名称**/
	private String userName="";

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		outputStream.writeUTF(userId);

		userHeroBO.encode(outputStream);

		
        if(equipList==null||equipList.size()==0){
			outputStream.writeInt(0);
		}else{
			outputStream.writeInt(equipList.size());
		}
		if(equipList!=null&&equipList.size()>0){
			for(int equipListi=0;equipListi<equipList.size();equipListi++){
				equipList.get(equipListi).encode(outputStream);
			}
		}		outputStream.writeInt(posX);

		outputStream.writeInt(posY);

		outputStream.writeUTF(userName);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		userId = inputStream.readUTF();

		userHeroBO=new UserHeroBO();    
		userHeroBO.decode(inputStream);

		
        int equipListSize = inputStream.readInt();
		if(equipListSize>0){
			equipList = new ArrayList<UserEquipBO>();
			for(int equipListi=0;equipListi<equipListSize;equipListi++){
				 UserEquipBO entry = new UserEquipBO();entry.decode(inputStream);equipList.add(entry);
			}
		}		posX = inputStream.readInt();

		posY = inputStream.readInt();

		userName = inputStream.readUTF();


	}
	
		/**进入场景的用户唯一编号**/
    public String getUserId() {
		return userId;
	}
	/**进入场景的用户唯一编号**/
    public void setUserId(String userId) {
		this.userId = userId;
	}
	/**用户英雄**/
    public UserHeroBO getUserHeroBO() {
		return userHeroBO;
	}
	/**用户英雄**/
    public void setUserHeroBO(UserHeroBO userHeroBO) {
		this.userHeroBO = userHeroBO;
	}
	/**用户装备列表**/
    public List<UserEquipBO> getEquipList() {
		return equipList;
	}
	/**用户装备列表**/
    public void setEquipList(List<UserEquipBO> equipList) {
		this.equipList = equipList;
	}
	/**进入场景所在x坐标**/
    public Integer getPosX() {
		return posX;
	}
	/**进入场景所在x坐标**/
    public void setPosX(Integer posX) {
		this.posX = posX;
	}
	/**进入场景所在y坐标**/
    public Integer getPosY() {
		return posY;
	}
	/**进入场景所在y坐标**/
    public void setPosY(Integer posY) {
		this.posY = posY;
	}
	/**玩家名称**/
    public String getUserName() {
		return userName;
	}
	/**玩家名称**/
    public void setUserName(String userName) {
		this.userName = userName;
	}

	
	
}
