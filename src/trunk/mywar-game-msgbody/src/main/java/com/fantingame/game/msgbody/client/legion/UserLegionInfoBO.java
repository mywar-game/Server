package com.fantingame.game.msgbody.client.legion;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;


/**用户军团信息对象**/
public class UserLegionInfoBO implements ICodeAble {

		/**军团id**/
	private String legionId="";
	/**用户贡献度**/
	private Integer contribution=0;
	/**军团身份1成员2副团长3军团长**/
	private Integer identity=0;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		outputStream.writeUTF(legionId);

		outputStream.writeInt(contribution);

		outputStream.writeInt(identity);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		legionId = inputStream.readUTF();

		contribution = inputStream.readInt();

		identity = inputStream.readInt();


	}
	
		/**军团id**/
    public String getLegionId() {
		return legionId;
	}
	/**军团id**/
    public void setLegionId(String legionId) {
		this.legionId = legionId;
	}
	/**用户贡献度**/
    public Integer getContribution() {
		return contribution;
	}
	/**用户贡献度**/
    public void setContribution(Integer contribution) {
		this.contribution = contribution;
	}
	/**军团身份1成员2副团长3军团长**/
    public Integer getIdentity() {
		return identity;
	}
	/**军团身份1成员2副团长3军团长**/
    public void setIdentity(Integer identity) {
		this.identity = identity;
	}

	
	
}
