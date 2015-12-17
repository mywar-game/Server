package com.adminTool.action;

import java.sql.Timestamp;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.adminTool.bo.Activity;
import com.adminTool.service.ActivityService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 更新活动信息
 * 
 * @author yezp
 */
public class UpdateActivity extends ALDAdminActionSupport implements
		ModelDriven<Activity> {

	private static final long serialVersionUID = -5804335633878775480L;

	private Activity activity = new Activity();
	private String beiShu1;
	private String beiShu2;
	private Timestamp startTime1 ;
	private Timestamp endTime1;
	private Timestamp startTime2;
	private Timestamp endTime2;

	private String isCommit = "F";

	public String execute() {
		ActivityService activityService = ServiceCacheFactory.getServiceCache()
				.getService(ActivityService.class);
		if ("F".equals(isCommit)) {
			activity = activityService
					.findOneActivity(activity.getActivityId());

			if (activity.getActivityType() == 100 || activity.getActivityType() == 18) {

				String params = activity.getParam();
//				if(!params.contains("-")){
//					beiShu1 = params;
//					return INPUT;
//				}
				if (params != null && !params.equals("")) {
					String[] temp = params.split(",");
					String[] temp1 = temp[0].split("#");
					String[] temp2;
					if (temp1.length >= 2) {
						temp2 = temp1[1].split("至");
						if (temp2.length >= 2) {
							startTime1 = stringToTimestamp(temp2[0]);
							endTime1 = stringToTimestamp(temp2[1]);
						}
					}
					if (temp1.length >= 1) {
						beiShu1 = temp1[0];
					}
					String[] temp3 = null;
					if (temp.length >= 2) {
						temp3 = temp[1].split("#");
						String[] temp4;
						if (temp3.length >= 2) {
							temp4 = temp3[1].split("至");
							if (temp4.length >= 2) {
								startTime2 = stringToTimestamp(temp4[0]);
								endTime2 = stringToTimestamp(temp4[1]);
							}
						}
					}
					if (temp3 != null && temp3.length >= 1) {
						beiShu2 = temp3[0];
					}
				}
//				if (params.contains(",")) {
//					if (params.contains(",")) {
//						String temp[] = params.split(",");
//
//						String temp1[] ;
//						String temp2[] ;
//						
//						if(!(temp[0]==null||"".equals(temp[0]))){
//							temp1 = temp[0].split("#");
//							temp2 = temp1[1].split("至");
//
//							beiShu1 = temp1[0];
//							startTime1 = stringToTimestamp(temp2[0]);
//							endTime1 =  stringToTimestamp(temp2[1]);
//						}
						

//						temp1 = temp[1].split("#");
//						temp2 = temp1[1].split("至");
//						
//						beiShu2 = temp1[0];
//						startTime2 = stringToTimestamp(temp2[0]);
//						endTime2 = stringToTimestamp(temp2[1]);
//					}
//				} else {
//					String temp1[] = params.split("#");
//					String temp2[] = temp1[1].split("至");
//					
//					beiShu1 = temp1[0];
//					startTime1 = stringToTimestamp(temp2[0]);
//					endTime1 =  stringToTimestamp(temp2[1]);
//				}
			}

			return INPUT;
		}

		if (activity.getActivityType() == 100 || activity.getActivityType() == 18) {
			String param = "";

			if (!"".equals(beiShu1)) {
				param += beiShu1;
				if (startTime1 != null
						&& endTime1 != null) {
					param += "#"
							+ timestampToString(startTime1) + "至"
							+ timestampToString(endTime1);
				}
			}

			if (!"".equals(beiShu2)) {
				param += ",";
				param += beiShu2;
				if (startTime2 != null
						&& endTime2 != null) {
					param += "#"
							+ timestampToString(startTime2) + "至"
							+ timestampToString(endTime2);
				}
			}

			activity.setParam(param);
			
		}
		
		activityService.updateActivity(activity);

		return SUCCESS;
	}

	
	public String getBeiShu1() {
		return beiShu1;
	}


	public void setBeiShu1(String beiShu1) {
		this.beiShu1 = beiShu1;
	}


	public String getBeiShu2() {
		return beiShu2;
	}


	public void setBeiShu2(String beiShu2) {
		this.beiShu2 = beiShu2;
	}


	public Timestamp getEndTime1() {
		return endTime1;
	}


	public void setEndTime1(Timestamp endTime1) {
		this.endTime1 = endTime1;
	}


	public Timestamp getStartTime2() {
		return startTime2;
	}


	public void setStartTime2(Timestamp startTime2) {
		this.startTime2 = startTime2;
	}


	public Timestamp getEndTime2() {
		return endTime2;
	}


	public void setEndTime2(Timestamp endTime2) {
		this.endTime2 = endTime2;
	}


	public Timestamp getStartTime1() {
		return startTime1;
	}


	public void setStartTime1(Timestamp startTime1) {
		this.startTime1 = startTime1;
	}


	public Activity getActivity() {
		return activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	public String getIsCommit() {
		return isCommit;
	}

	public void setIsCommit(String isCommit) {
		this.isCommit = isCommit;
	}

	@Override
	public Activity getModel() {
		// TODO Auto-generated method stub
		return activity;
	}

	public Timestamp stringToTimestamp(String time) {

		try {
			Format f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date d = (Date) f.parseObject(time);
			Timestamp ts = new Timestamp(d.getTime());
			return ts;
		} catch (Exception e) {
			System.out.println("时间转换异常");
		}
		return null;
	}
	
	public String timestampToString(Timestamp time){
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String str = df.format(time);
		return str;
	}

}
