package com.fantingame.game.msgbody.server.admin;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;


/**重载静态数据**/
public class AdminAction_reloadStaticDataReq implements ICodeAble {

		/**重载的静态数据对象的类名称，ALL为全部**/
	private String className="";

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		outputStream.writeUTF(className);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		className = inputStream.readUTF();


	}
	
		/**重载的静态数据对象的类名称，ALL为全部**/
    public String getClassName() {
		return className;
	}
	/**重载的静态数据对象的类名称，ALL为全部**/
    public void setClassName(String className) {
		this.className = className;
	}

	
	
}
