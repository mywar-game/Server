package com.fantingame.game.msgbody.client.mail;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;

import java.util.List;
import java.util.ArrayList;

/**一键删除邮件**/
public class MailAction_oneClickDeleteRes implements ICodeAble {

		/**用户邮件id列表**/
	private List<Integer> userMailIdList=null;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		
        if(userMailIdList==null||userMailIdList.size()==0){
			outputStream.writeInt(0);
		}else{
			outputStream.writeInt(userMailIdList.size());
		}
		if(userMailIdList!=null&&userMailIdList.size()>0){
			for(int userMailIdListi=0;userMailIdListi<userMailIdList.size();userMailIdListi++){
						outputStream.writeInt(userMailIdList.get(userMailIdListi));


			}
		}
	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		
        int userMailIdListSize = inputStream.readInt();
		if(userMailIdListSize>0){
			userMailIdList = new ArrayList<Integer>();
			for(int userMailIdListi=0;userMailIdListi<userMailIdListSize;userMailIdListi++){
				 userMailIdList.add(inputStream.readInt());
			}
		}
	}
	
		/**用户邮件id列表**/
    public List<Integer> getUserMailIdList() {
		return userMailIdList;
	}
	/**用户邮件id列表**/
    public void setUserMailIdList(List<Integer> userMailIdList) {
		this.userMailIdList = userMailIdList;
	}

	
	
}
