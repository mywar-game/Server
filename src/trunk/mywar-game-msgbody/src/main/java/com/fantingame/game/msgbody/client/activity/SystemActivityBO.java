package com.fantingame.game.msgbody.client.activity;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;


/**运营活动对象**/
public class SystemActivityBO implements ICodeAble {

		/**活动id**/
	private Integer activityId=0;
	/**显示分类**/
	private Integer activityShowType=0;
	/**活动类型**/
	private Integer activityType=0;
	/**活动名称**/
	private String activityName="";
	/**活动标题**/
	private String activityTitle="";
	/**活动描述**/
	private String activityDesc="";
	/**活动开始时间**/
	private Long startTime=0l;
	/**活动结束时间**/
	private Long endTime=0l;
	/**扩展参数**/
	private String param="";
	/**开放的星期**/
	private String openWeeks="";
	/**开放的时间段**/
	private String openTime="";
	/**是否显示0不显示1显示**/
	private Integer display=0;
	/**状态0开启1关闭**/
	private Integer status=0;
	/**排序字段**/
	private Integer sort=0;
	/**图片id**/
	private Integer imgId=0;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		outputStream.writeInt(activityId);

		outputStream.writeInt(activityShowType);

		outputStream.writeInt(activityType);

		outputStream.writeUTF(activityName);

		outputStream.writeUTF(activityTitle);

		outputStream.writeUTF(activityDesc);

		outputStream.writeLong(startTime);

		outputStream.writeLong(endTime);

		outputStream.writeUTF(param);

		outputStream.writeUTF(openWeeks);

		outputStream.writeUTF(openTime);

		outputStream.writeInt(display);

		outputStream.writeInt(status);

		outputStream.writeInt(sort);

		outputStream.writeInt(imgId);


	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		activityId = inputStream.readInt();

		activityShowType = inputStream.readInt();

		activityType = inputStream.readInt();

		activityName = inputStream.readUTF();

		activityTitle = inputStream.readUTF();

		activityDesc = inputStream.readUTF();

		startTime = inputStream.readLong();

		endTime = inputStream.readLong();

		param = inputStream.readUTF();

		openWeeks = inputStream.readUTF();

		openTime = inputStream.readUTF();

		display = inputStream.readInt();

		status = inputStream.readInt();

		sort = inputStream.readInt();

		imgId = inputStream.readInt();


	}
	
		/**活动id**/
    public Integer getActivityId() {
		return activityId;
	}
	/**活动id**/
    public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}
	/**显示分类**/
    public Integer getActivityShowType() {
		return activityShowType;
	}
	/**显示分类**/
    public void setActivityShowType(Integer activityShowType) {
		this.activityShowType = activityShowType;
	}
	/**活动类型**/
    public Integer getActivityType() {
		return activityType;
	}
	/**活动类型**/
    public void setActivityType(Integer activityType) {
		this.activityType = activityType;
	}
	/**活动名称**/
    public String getActivityName() {
		return activityName;
	}
	/**活动名称**/
    public void setActivityName(String activityName) {
		this.activityName = activityName;
	}
	/**活动标题**/
    public String getActivityTitle() {
		return activityTitle;
	}
	/**活动标题**/
    public void setActivityTitle(String activityTitle) {
		this.activityTitle = activityTitle;
	}
	/**活动描述**/
    public String getActivityDesc() {
		return activityDesc;
	}
	/**活动描述**/
    public void setActivityDesc(String activityDesc) {
		this.activityDesc = activityDesc;
	}
	/**活动开始时间**/
    public Long getStartTime() {
		return startTime;
	}
	/**活动开始时间**/
    public void setStartTime(Long startTime) {
		this.startTime = startTime;
	}
	/**活动结束时间**/
    public Long getEndTime() {
		return endTime;
	}
	/**活动结束时间**/
    public void setEndTime(Long endTime) {
		this.endTime = endTime;
	}
	/**扩展参数**/
    public String getParam() {
		return param;
	}
	/**扩展参数**/
    public void setParam(String param) {
		this.param = param;
	}
	/**开放的星期**/
    public String getOpenWeeks() {
		return openWeeks;
	}
	/**开放的星期**/
    public void setOpenWeeks(String openWeeks) {
		this.openWeeks = openWeeks;
	}
	/**开放的时间段**/
    public String getOpenTime() {
		return openTime;
	}
	/**开放的时间段**/
    public void setOpenTime(String openTime) {
		this.openTime = openTime;
	}
	/**是否显示0不显示1显示**/
    public Integer getDisplay() {
		return display;
	}
	/**是否显示0不显示1显示**/
    public void setDisplay(Integer display) {
		this.display = display;
	}
	/**状态0开启1关闭**/
    public Integer getStatus() {
		return status;
	}
	/**状态0开启1关闭**/
    public void setStatus(Integer status) {
		this.status = status;
	}
	/**排序字段**/
    public Integer getSort() {
		return sort;
	}
	/**排序字段**/
    public void setSort(Integer sort) {
		this.sort = sort;
	}
	/**图片id**/
    public Integer getImgId() {
		return imgId;
	}
	/**图片id**/
    public void setImgId(Integer imgId) {
		this.imgId = imgId;
	}

	
	
}
