package com.fantingame.game.msgbody.notify.user;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;

import com.fantingame.game.msgbody.notify.user.UserPropertiesBO;
import java.util.List;
import java.util.ArrayList;

/**刷新用户属性值**/
public class User_pushUserPropertiesNotify implements ICodeAble {

		/**需要刷新的属性列表**/
	private List<UserPropertiesBO> userPropertiesList=null;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		
        if(userPropertiesList==null||userPropertiesList.size()==0){
			outputStream.writeInt(0);
		}else{
			outputStream.writeInt(userPropertiesList.size());
		}
		if(userPropertiesList!=null&&userPropertiesList.size()>0){
			for(int userPropertiesListi=0;userPropertiesListi<userPropertiesList.size();userPropertiesListi++){
				userPropertiesList.get(userPropertiesListi).encode(outputStream);
			}
		}
	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		
        int userPropertiesListSize = inputStream.readInt();
		if(userPropertiesListSize>0){
			userPropertiesList = new ArrayList<UserPropertiesBO>();
			for(int userPropertiesListi=0;userPropertiesListi<userPropertiesListSize;userPropertiesListi++){
				 UserPropertiesBO entry = new UserPropertiesBO();entry.decode(inputStream);userPropertiesList.add(entry);
			}
		}
	}
	
		/**需要刷新的属性列表**/
    public List<UserPropertiesBO> getUserPropertiesList() {
		return userPropertiesList;
	}
	/**需要刷新的属性列表**/
    public void setUserPropertiesList(List<UserPropertiesBO> userPropertiesList) {
		this.userPropertiesList = userPropertiesList;
	}

	
	
}
