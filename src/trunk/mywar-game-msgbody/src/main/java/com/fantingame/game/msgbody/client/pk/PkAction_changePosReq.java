package com.fantingame.game.msgbody.client.pk;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;

import java.util.List;
import java.util.ArrayList;

/**上阵、下阵防守阵营**/
public class PkAction_changePosReq implements ICodeAble {

		/**用户英雄id列表**/
	private List<String> userHeroIdList=null;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		
        if(userHeroIdList==null||userHeroIdList.size()==0){
			outputStream.writeInt(0);
		}else{
			outputStream.writeInt(userHeroIdList.size());
		}
		if(userHeroIdList!=null&&userHeroIdList.size()>0){
			for(int userHeroIdListi=0;userHeroIdListi<userHeroIdList.size();userHeroIdListi++){
						outputStream.writeUTF(userHeroIdList.get(userHeroIdListi));


			}
		}
	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		
        int userHeroIdListSize = inputStream.readInt();
		if(userHeroIdListSize>0){
			userHeroIdList = new ArrayList<String>();
			for(int userHeroIdListi=0;userHeroIdListi<userHeroIdListSize;userHeroIdListi++){
				 userHeroIdList.add(inputStream.readUTF());
			}
		}
	}
	
		/**用户英雄id列表**/
    public List<String> getUserHeroIdList() {
		return userHeroIdList;
	}
	/**用户英雄id列表**/
    public void setUserHeroIdList(List<String> userHeroIdList) {
		this.userHeroIdList = userHeroIdList;
	}

	
	
}
