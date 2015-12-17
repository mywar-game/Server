package com.fantingame.game.msgbody.client.legion;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;


/**修改军团宣言**/
public class LegionAction_editDeclarationRes implements ICodeAble {

		/**军团宣言内容**/
	private String declaration="";

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		outputStream.writeUTF(declaration);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		declaration = inputStream.readUTF();


	}
	
		/**军团宣言内容**/
    public String getDeclaration() {
		return declaration;
	}
	/**军团宣言内容**/
    public void setDeclaration(String declaration) {
		this.declaration = declaration;
	}

	
	
}
