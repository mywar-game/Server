package com.fantingame.game.msgbody.client.pk;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;

import com.fantingame.game.msgbody.client.pk.PkMallBO;
import java.util.List;
import java.util.ArrayList;

/**获取用户兑换奖励信息**/
public class PkAction_getUserPkMallInfoRes implements ICodeAble {

		/**荣誉兑换商城列表**/
	private List<PkMallBO> pkMallList=null;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		
        if(pkMallList==null||pkMallList.size()==0){
			outputStream.writeInt(0);
		}else{
			outputStream.writeInt(pkMallList.size());
		}
		if(pkMallList!=null&&pkMallList.size()>0){
			for(int pkMallListi=0;pkMallListi<pkMallList.size();pkMallListi++){
				pkMallList.get(pkMallListi).encode(outputStream);
			}
		}
	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		
        int pkMallListSize = inputStream.readInt();
		if(pkMallListSize>0){
			pkMallList = new ArrayList<PkMallBO>();
			for(int pkMallListi=0;pkMallListi<pkMallListSize;pkMallListi++){
				 PkMallBO entry = new PkMallBO();entry.decode(inputStream);pkMallList.add(entry);
			}
		}
	}
	
		/**荣誉兑换商城列表**/
    public List<PkMallBO> getPkMallList() {
		return pkMallList;
	}
	/**荣誉兑换商城列表**/
    public void setPkMallList(List<PkMallBO> pkMallList) {
		this.pkMallList = pkMallList;
	}

	
	
}
