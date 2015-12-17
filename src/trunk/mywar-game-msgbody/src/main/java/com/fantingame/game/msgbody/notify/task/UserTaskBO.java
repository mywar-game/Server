package com.fantingame.game.msgbody.notify.task;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;


/**用户任务信息**/
public class UserTaskBO implements ICodeAble {

		/**系统任务id，可根据该信息从静态数据中获取任务详情**/
	private Integer systemTaskId=0;
	/**已完成次数**/
	private Integer finishTimes=0;
	/**状态0可领取1已领取2已完成3已获得奖励**/
	private Integer status=0;
	/**任务的星数（只用于日常任务）**/
	private Integer star=0;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		outputStream.writeInt(systemTaskId);

		outputStream.writeInt(finishTimes);

		outputStream.writeInt(status);

		outputStream.writeInt(star);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		systemTaskId = inputStream.readInt();

		finishTimes = inputStream.readInt();

		status = inputStream.readInt();

		star = inputStream.readInt();


	}
	
		/**系统任务id，可根据该信息从静态数据中获取任务详情**/
    public Integer getSystemTaskId() {
		return systemTaskId;
	}
	/**系统任务id，可根据该信息从静态数据中获取任务详情**/
    public void setSystemTaskId(Integer systemTaskId) {
		this.systemTaskId = systemTaskId;
	}
	/**已完成次数**/
    public Integer getFinishTimes() {
		return finishTimes;
	}
	/**已完成次数**/
    public void setFinishTimes(Integer finishTimes) {
		this.finishTimes = finishTimes;
	}
	/**状态0可领取1已领取2已完成3已获得奖励**/
    public Integer getStatus() {
		return status;
	}
	/**状态0可领取1已领取2已完成3已获得奖励**/
    public void setStatus(Integer status) {
		this.status = status;
	}
	/**任务的星数（只用于日常任务）**/
    public Integer getStar() {
		return star;
	}
	/**任务的星数（只用于日常任务）**/
    public void setStar(Integer star) {
		this.star = star;
	}

	
	
}
