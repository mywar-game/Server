package com.fantingame.game.msgbody.client.life;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;

import java.util.List;
import java.util.ArrayList;

/**开始挂机**/
public class LifeAction_hangupReq implements ICodeAble {

		/**类别（1矿场2花圃3渔场）**/
	private Integer category=0;
	/**用户英雄id列表**/
	private List<String> userHeroIdList=null;
	/**好友用户id**/
	private String userFriendId="";

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		outputStream.writeInt(category);

		
        if(userHeroIdList==null||userHeroIdList.size()==0){
			outputStream.writeInt(0);
		}else{
			outputStream.writeInt(userHeroIdList.size());
		}
		if(userHeroIdList!=null&&userHeroIdList.size()>0){
			for(int userHeroIdListi=0;userHeroIdListi<userHeroIdList.size();userHeroIdListi++){
						outputStream.writeUTF(userHeroIdList.get(userHeroIdListi));


			}
		}		outputStream.writeUTF(userFriendId);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		category = inputStream.readInt();

		
        int userHeroIdListSize = inputStream.readInt();
		if(userHeroIdListSize>0){
			userHeroIdList = new ArrayList<String>();
			for(int userHeroIdListi=0;userHeroIdListi<userHeroIdListSize;userHeroIdListi++){
				 userHeroIdList.add(inputStream.readUTF());
			}
		}		userFriendId = inputStream.readUTF();


	}
	
		/**类别（1矿场2花圃3渔场）**/
    public Integer getCategory() {
		return category;
	}
	/**类别（1矿场2花圃3渔场）**/
    public void setCategory(Integer category) {
		this.category = category;
	}
	/**用户英雄id列表**/
    public List<String> getUserHeroIdList() {
		return userHeroIdList;
	}
	/**用户英雄id列表**/
    public void setUserHeroIdList(List<String> userHeroIdList) {
		this.userHeroIdList = userHeroIdList;
	}
	/**好友用户id**/
    public String getUserFriendId() {
		return userFriendId;
	}
	/**好友用户id**/
    public void setUserFriendId(String userFriendId) {
		this.userFriendId = userFriendId;
	}

	
	
}
