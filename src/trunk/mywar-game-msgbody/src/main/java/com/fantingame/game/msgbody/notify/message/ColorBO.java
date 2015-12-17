package com.fantingame.game.msgbody.notify.message;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;


/**颜色对象**/
public class ColorBO implements ICodeAble {

		/**红**/
	private Integer r=0;
	/**绿**/
	private Integer g=0;
	/**蓝**/
	private Integer b=0;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		outputStream.writeInt(r);

		outputStream.writeInt(g);

		outputStream.writeInt(b);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		r = inputStream.readInt();

		g = inputStream.readInt();

		b = inputStream.readInt();


	}
	
		/**红**/
    public Integer getR() {
		return r;
	}
	/**红**/
    public void setR(Integer r) {
		this.r = r;
	}
	/**绿**/
    public Integer getG() {
		return g;
	}
	/**绿**/
    public void setG(Integer g) {
		this.g = g;
	}
	/**蓝**/
    public Integer getB() {
		return b;
	}
	/**蓝**/
    public void setB(Integer b) {
		this.b = b;
	}

	
	
}
