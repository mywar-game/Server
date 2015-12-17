package com.fantingame.game.msgbody.client.mail;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;

import com.fantingame.game.msgbody.client.mail.UserMailBO;
import java.util.List;
import java.util.ArrayList;

/**获取邮件列表**/
public class MailAction_getMailListRes implements ICodeAble {

		/**用户邮件列表**/
	private List<UserMailBO> userMailList=null;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		
        if(userMailList==null||userMailList.size()==0){
			outputStream.writeInt(0);
		}else{
			outputStream.writeInt(userMailList.size());
		}
		if(userMailList!=null&&userMailList.size()>0){
			for(int userMailListi=0;userMailListi<userMailList.size();userMailListi++){
				userMailList.get(userMailListi).encode(outputStream);
			}
		}
	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		
        int userMailListSize = inputStream.readInt();
		if(userMailListSize>0){
			userMailList = new ArrayList<UserMailBO>();
			for(int userMailListi=0;userMailListi<userMailListSize;userMailListi++){
				 UserMailBO entry = new UserMailBO();entry.decode(inputStream);userMailList.add(entry);
			}
		}
	}
	
		/**用户邮件列表**/
    public List<UserMailBO> getUserMailList() {
		return userMailList;
	}
	/**用户邮件列表**/
    public void setUserMailList(List<UserMailBO> userMailList) {
		this.userMailList = userMailList;
	}

	
	
}
