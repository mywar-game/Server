package com.fantingame.game.msgbody.client.forces;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;

import com.fantingame.game.msgbody.client.forces.UserForcesBO;
import java.util.List;
import java.util.ArrayList;

/**获取用户副本关卡信息列表**/
public class ForcesAction_getCopyForcesInfoRes implements ICodeAble {

		/**用户副本关卡信息列表**/
	private List<UserForcesBO> userForcesList=null;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		
        if(userForcesList==null||userForcesList.size()==0){
			outputStream.writeInt(0);
		}else{
			outputStream.writeInt(userForcesList.size());
		}
		if(userForcesList!=null&&userForcesList.size()>0){
			for(int userForcesListi=0;userForcesListi<userForcesList.size();userForcesListi++){
				userForcesList.get(userForcesListi).encode(outputStream);
			}
		}
	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		
        int userForcesListSize = inputStream.readInt();
		if(userForcesListSize>0){
			userForcesList = new ArrayList<UserForcesBO>();
			for(int userForcesListi=0;userForcesListi<userForcesListSize;userForcesListi++){
				 UserForcesBO entry = new UserForcesBO();entry.decode(inputStream);userForcesList.add(entry);
			}
		}
	}
	
		/**用户副本关卡信息列表**/
    public List<UserForcesBO> getUserForcesList() {
		return userForcesList;
	}
	/**用户副本关卡信息列表**/
    public void setUserForcesList(List<UserForcesBO> userForcesList) {
		this.userForcesList = userForcesList;
	}

	
	
}
