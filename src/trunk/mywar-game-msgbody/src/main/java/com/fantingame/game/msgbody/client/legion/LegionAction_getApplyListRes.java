package com.fantingame.game.msgbody.client.legion;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;

import com.fantingame.game.msgbody.client.legion.UserApplyLegionBO;
import java.util.List;
import java.util.ArrayList;

/**查看申请列表**/
public class LegionAction_getApplyListRes implements ICodeAble {

		/**用户申请列表**/
	private List<UserApplyLegionBO> userApplyList=null;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		
        if(userApplyList==null||userApplyList.size()==0){
			outputStream.writeInt(0);
		}else{
			outputStream.writeInt(userApplyList.size());
		}
		if(userApplyList!=null&&userApplyList.size()>0){
			for(int userApplyListi=0;userApplyListi<userApplyList.size();userApplyListi++){
				userApplyList.get(userApplyListi).encode(outputStream);
			}
		}
	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		
        int userApplyListSize = inputStream.readInt();
		if(userApplyListSize>0){
			userApplyList = new ArrayList<UserApplyLegionBO>();
			for(int userApplyListi=0;userApplyListi<userApplyListSize;userApplyListi++){
				 UserApplyLegionBO entry = new UserApplyLegionBO();entry.decode(inputStream);userApplyList.add(entry);
			}
		}
	}
	
		/**用户申请列表**/
    public List<UserApplyLegionBO> getUserApplyList() {
		return userApplyList;
	}
	/**用户申请列表**/
    public void setUserApplyList(List<UserApplyLegionBO> userApplyList) {
		this.userApplyList = userApplyList;
	}

	
	
}
