package com.fantingame.game.msgbody.client.pk;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;

import com.fantingame.game.msgbody.client.pk.PkFightLogBO;
import java.util.List;
import java.util.ArrayList;

/**查看战斗日志**/
public class PkAction_getPkFightLogRes implements ICodeAble {

		/**战斗日志列表**/
	private List<PkFightLogBO> logList=null;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		
        if(logList==null||logList.size()==0){
			outputStream.writeInt(0);
		}else{
			outputStream.writeInt(logList.size());
		}
		if(logList!=null&&logList.size()>0){
			for(int logListi=0;logListi<logList.size();logListi++){
				logList.get(logListi).encode(outputStream);
			}
		}
	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		
        int logListSize = inputStream.readInt();
		if(logListSize>0){
			logList = new ArrayList<PkFightLogBO>();
			for(int logListi=0;logListi<logListSize;logListi++){
				 PkFightLogBO entry = new PkFightLogBO();entry.decode(inputStream);logList.add(entry);
			}
		}
	}
	
		/**战斗日志列表**/
    public List<PkFightLogBO> getLogList() {
		return logList;
	}
	/**战斗日志列表**/
    public void setLogList(List<PkFightLogBO> logList) {
		this.logList = logList;
	}

	
	
}
