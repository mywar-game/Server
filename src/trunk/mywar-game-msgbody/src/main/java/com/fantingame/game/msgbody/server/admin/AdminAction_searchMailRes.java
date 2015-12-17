package com.fantingame.game.msgbody.server.admin;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;

import com.fantingame.game.msgbody.server.admin.ResUserMailBO;
import java.util.List;
import java.util.ArrayList;

/**查询用户邮件**/
public class AdminAction_searchMailRes implements ICodeAble {

		/**返回邮件对象列表**/
	private List<ResUserMailBO> resUserMailBOList=null;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		
        if(resUserMailBOList==null||resUserMailBOList.size()==0){
			outputStream.writeInt(0);
		}else{
			outputStream.writeInt(resUserMailBOList.size());
		}
		if(resUserMailBOList!=null&&resUserMailBOList.size()>0){
			for(int resUserMailBOListi=0;resUserMailBOListi<resUserMailBOList.size();resUserMailBOListi++){
				resUserMailBOList.get(resUserMailBOListi).encode(outputStream);
			}
		}
	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		
        int resUserMailBOListSize = inputStream.readInt();
		if(resUserMailBOListSize>0){
			resUserMailBOList = new ArrayList<ResUserMailBO>();
			for(int resUserMailBOListi=0;resUserMailBOListi<resUserMailBOListSize;resUserMailBOListi++){
				 ResUserMailBO entry = new ResUserMailBO();entry.decode(inputStream);resUserMailBOList.add(entry);
			}
		}
	}
	
		/**返回邮件对象列表**/
    public List<ResUserMailBO> getResUserMailBOList() {
		return resUserMailBOList;
	}
	/**返回邮件对象列表**/
    public void setResUserMailBOList(List<ResUserMailBO> resUserMailBOList) {
		this.resUserMailBOList = resUserMailBOList;
	}

	
	
}
