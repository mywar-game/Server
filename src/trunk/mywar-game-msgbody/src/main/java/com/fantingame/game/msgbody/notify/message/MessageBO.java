package com.fantingame.game.msgbody.notify.message;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;

import com.fantingame.game.msgbody.notify.message.ColorBO;

/**跑马灯消息对象**/
public class MessageBO implements ICodeAble {

		/**内容**/
	private String txt="";
	/**颜色对象**/
	private ColorBO cor=null;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		outputStream.writeUTF(txt);

		cor.encode(outputStream);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		txt = inputStream.readUTF();

		cor=new ColorBO();    
		cor.decode(inputStream);


	}
	
		/**内容**/
    public String getTxt() {
		return txt;
	}
	/**内容**/
    public void setTxt(String txt) {
		this.txt = txt;
	}
	/**颜色对象**/
    public ColorBO getCor() {
		return cor;
	}
	/**颜色对象**/
    public void setCor(ColorBO cor) {
		this.cor = cor;
	}

	
	
}
