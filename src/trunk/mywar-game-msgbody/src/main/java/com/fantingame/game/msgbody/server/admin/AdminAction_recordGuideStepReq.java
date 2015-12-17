package com.fantingame.game.msgbody.server.admin;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;


/**跳过新手引导**/
public class AdminAction_recordGuideStepReq implements ICodeAble {

		/**用户id**/
	private String userId="";
	/**引导步骤**/
	private Integer guideStep=0;
	/**ip**/
	private String ip="";

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		outputStream.writeUTF(userId);

		outputStream.writeInt(guideStep);

		outputStream.writeUTF(ip);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		userId = inputStream.readUTF();

		guideStep = inputStream.readInt();

		ip = inputStream.readUTF();


	}
	
		/**用户id**/
    public String getUserId() {
		return userId;
	}
	/**用户id**/
    public void setUserId(String userId) {
		this.userId = userId;
	}
	/**引导步骤**/
    public Integer getGuideStep() {
		return guideStep;
	}
	/**引导步骤**/
    public void setGuideStep(Integer guideStep) {
		this.guideStep = guideStep;
	}
	/**ip**/
    public String getIp() {
		return ip;
	}
	/**ip**/
    public void setIp(String ip) {
		this.ip = ip;
	}

	
	
}
