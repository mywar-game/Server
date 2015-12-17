package com.fantingame.game.msgbody.client.pk;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;

import com.fantingame.game.msgbody.client.pk.PkChallengerBO;
import java.util.List;
import java.util.ArrayList;

/**换一批**/
public class PkAction_refreshChallengerRes implements ICodeAble {

		/**用户可挑战列表**/
	private List<PkChallengerBO> userPkList=null;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		
        if(userPkList==null||userPkList.size()==0){
			outputStream.writeInt(0);
		}else{
			outputStream.writeInt(userPkList.size());
		}
		if(userPkList!=null&&userPkList.size()>0){
			for(int userPkListi=0;userPkListi<userPkList.size();userPkListi++){
				userPkList.get(userPkListi).encode(outputStream);
			}
		}
	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		
        int userPkListSize = inputStream.readInt();
		if(userPkListSize>0){
			userPkList = new ArrayList<PkChallengerBO>();
			for(int userPkListi=0;userPkListi<userPkListSize;userPkListi++){
				 PkChallengerBO entry = new PkChallengerBO();entry.decode(inputStream);userPkList.add(entry);
			}
		}
	}
	
		/**用户可挑战列表**/
    public List<PkChallengerBO> getUserPkList() {
		return userPkList;
	}
	/**用户可挑战列表**/
    public void setUserPkList(List<PkChallengerBO> userPkList) {
		this.userPkList = userPkList;
	}

	
	
}
