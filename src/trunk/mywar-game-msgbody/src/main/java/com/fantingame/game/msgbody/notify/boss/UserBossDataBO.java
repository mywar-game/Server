package com.fantingame.game.msgbody.notify.boss;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;

import com.fantingame.game.msgbody.client.hero.UserHeroBO;
import com.fantingame.game.msgbody.client.equip.UserEquipBO;
import java.util.List;
import java.util.ArrayList;

/**用户Boss战信息对象**/
public class UserBossDataBO implements ICodeAble {

		/**用户唯一id**/
	private String userId="";
	/**用户名**/
	private String userName="";
	/**用户英雄**/
	private UserHeroBO userHeroBO=null;
	/**用户装备列表**/
	private List<UserEquipBO> equipList=null;
	/**进入场景所在x坐标**/
	private Integer posX=0;
	/**进入场景所在y坐标**/
	private Integer posY=0;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		outputStream.writeUTF(userId);

		outputStream.writeUTF(userName);

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


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		userId = inputStream.readUTF();

		userName = inputStream.readUTF();

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


	}
	
		/**用户唯一id**/
    public String getUserId() {
		return userId;
	}
	/**用户唯一id**/
    public void setUserId(String userId) {
		this.userId = userId;
	}
	/**用户名**/
    public String getUserName() {
		return userName;
	}
	/**用户名**/
    public void setUserName(String userName) {
		this.userName = userName;
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

	
	
}
