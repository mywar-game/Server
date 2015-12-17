package com.adminTool.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.adminTool.bo.ChouyangObj;
import com.adminTool.bo.Partner;
import com.adminTool.service.PartnerService;
import com.adminTool.service.UserService;
import com.dataconfig.service.UserPayService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.log.LogSystem;
import com.framework.servicemanager.CustomerContextHolder;
import com.framework.servicemanager.ServiceCacheFactory;
import com.framework.util.DateUtil;
import com.system.bo.TGameServer;
import com.system.manager.DataSourceManager;

public class ChouyangList extends ALDAdminActionSupport {

	private static final long serialVersionUID = 1L;
	private Integer zone = -1; // 区
	private Integer channel = 0;
	private String date;
	private String endDate1;
	private Integer day = -1;
	private String channelName;
	private Float totalConsume = 0.0f;
	private Integer totalCreateUser = 0;
	private float ltv = 0.0f;
	List<ChouyangObj> list1 = new ArrayList<ChouyangObj>();
	
	Map<Integer, Float> tempMap = new HashMap<Integer, Float>();
	
	/**渠道列表**/
	private List<Partner> partnerList ;
	

	public float getLtv() {
		return ltv;
	}

	public void setLtv(float ltv) {
		this.ltv = ltv;
	}

	public String getEndDate1() {
		return endDate1;
	}

	public void setEndDate1(String endDate1) {
		this.endDate1 = endDate1;
	}

	public Integer getTotalCreateUser() {
		return totalCreateUser;
	}

	public void setTotalCreateUser(Integer totalCreateUser) {
		this.totalCreateUser = totalCreateUser;
	}

	public List<Partner> getPartnerList() {
		return partnerList;
	}

	public void setPartnerList(List<Partner> partnerList) {
		this.partnerList = partnerList;
	}

	public Integer getChannel() {
		return channel;
	}

	public void setChannel(Integer channel) {
		this.channel = channel;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Integer getDay() {
		return day;
	}

	public void setDay(Integer day) {
		this.day = day;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public Float getTotalConsume() {
		return totalConsume;
	}

	public void setTotalConsume(Float totalConsume) {
		this.totalConsume = totalConsume;
	}

	public List<ChouyangObj> getList1() {
		return list1;
	}

	public void setList1(List<ChouyangObj> list1) {
		this.list1 = list1;
	}

	public Integer getZone() {
		return zone;
	}

	public void setZone(Integer zone) {
		this.zone = zone;
	}

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	@Override
	public String execute() {
		Map<Integer, TGameServer> map = this.getGameServerMap();
		UserService userService = ServiceCacheFactory.getServiceCache().getService(UserService.class);
		UserPayService userPayHistoryLogService = ServiceCacheFactory.getServiceCache().getService(UserPayService.class);
		PartnerService partnerService = ServiceCacheFactory.getServiceCache().getService(PartnerService.class);
		
		partnerList = partnerService.findPartnerList(); // 渠道信息
		StringBuffer chennels = new StringBuffer();
		
		if (zone == -1 && channel == 0) {
			return INPUT; // 第一次打开面板
		}
		if (zone != -1 && channel != 0 || (zone == -1 && channel != 0)) {
			// 选区，选渠道
			chennels.append(channel + ",");
		} else if (zone != -1 && channel == 0) {
			// 选区，但是不选渠道
			if (zone == 1) {
				// ios越狱
				for (Partner p : partnerList) {
					if (Integer.valueOf(p.getPNum()) > 2000 && Integer.valueOf(p.getPNum()) < 3000) {
						chennels.append(Integer.valueOf(p.getPNum()) + ",");
					}
				}
			}
			if (zone == 3) {
				// android
				for (Partner p : partnerList) {
					if (Integer.valueOf(p.getPNum()) < 2000) {
						chennels.append(Integer.valueOf(p.getPNum()) + ",");
					}
				}
			}
			if (zone == 4) {
				// 英雄传奇IOS正版
				chennels.append(3002 + ",");
			}
			if (zone == 5) {
				// 热血英雄正版苹果
				chennels.append(3001 + ",");
			}
		}
		String tempChannelStr = chennels.substring(0, chennels.lastIndexOf(","));
		
		for (TGameServer server : map.values()) {
			// 遍历所有服务器
			CustomerContextHolder.setSystemNum(server.getServerId());
			Integer sysNum = CustomerContextHolder.getSystemNum(); // 保存当前
			LogSystem.info("当前服务器ID：here = " + sysNum);
			List<Object> newUserList = userService.findCreateUserNum(date, tempChannelStr);
			if (newUserList != null) {
				for (int i = 0; i < newUserList.size(); i++) {
					Object[] arr = (Object[]) newUserList.get(i);
					String temp = arr[0].toString();
					if (temp.equals("0") || temp == "0") {
						continue;
					}
					totalCreateUser += Integer.valueOf(temp); // 创建角色总数
				}
			}
			String regDateStr = date + " 00:00:00";
			String regEndDateStr = date + " 23:59:59";
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date regDate = null;
			try {
				regDate = sf.parse(regDateStr);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			for (int j = 0; j < day; j ++) {
				Date endChouyangDate = DateUtil.getDiffDate(regDate, j);
				String endChouyangDateStr = sf.format(endChouyangDate);
				String[] tempStrArr = endChouyangDateStr.split(" ");
				String endResultStr = tempStrArr[0] + " 23:59:59";
				String[] dates = {regDateStr, endResultStr};
				List<Object> payList = userPayHistoryLogService.findPayAmount(dates, tempChannelStr, regEndDateStr);
				if (payList != null) {
					for (int i = 0; i < payList.size(); i++) {
						Object[] arr = (Object[]) payList.get(i);
						if (arr == null) {
							continue;
						}
						if (arr[0] == null) {
							continue;
						}
						String temp = arr[0].toString();
						if (temp.equals("0") || temp == "0") {
							continue;
						}
						totalConsume += Float.valueOf(arr[0].toString()); // 付费总数
						if (tempMap.get(j) == null) {
							tempMap.put(j, Float.valueOf(arr[0].toString()));
						} else {
							tempMap.put(j, Float.valueOf(arr[0].toString()) + tempMap.get(j));
						}
					}
				}
			}
		}
		
		LogSystem.info("totalConsume = " + totalConsume);
		for (int i = 0; i < day; i++) {
			ChouyangObj obj = new ChouyangObj();
			String regDateStr = date;
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
			Date regDate = null;
			try {
				regDate = sf.parse(regDateStr);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			Date endChouyangDate = DateUtil.getDiffDate(regDate, i);
			String endChouyangDateStr = sf.format(endChouyangDate);
			obj.setEndRegDateStr(endChouyangDateStr);
			obj.setTotalConsume(tempMap.get(i) == null ? 0 : tempMap.get(i));
			if (totalCreateUser == 0) {
				obj.setLtv(0);
			} else {
				obj.setLtv(obj.getTotalConsume() / totalCreateUser);
			}
			obj.setDay(i);
			obj.setTotalCreateUser(totalCreateUser);
			list1.add(obj);
		}
		for (int i = 0; i < partnerList.size(); i++) {
			Partner p = partnerList.get(i);
			if (p.getPNum().equals(Integer.toString(channel))) {
				channelName = p.getPName();
				break;
			}
		}
		if (zone != -1 && channel == 0 && zone == 1) {
			channelName = "ios越狱版所有渠道";
		} else if (zone != -1 && channel == 0 && zone == 3) {
			channelName = "android所有渠道";
		}
//		if (totalCreateUser == 0) {
//			ltv = 0.0f;
//		} else {
//			ltv = totalConsume / totalCreateUser;
//		}
		return SUCCESS;
	}
	
	public Map<Integer, TGameServer> getGameServerMap() {
		Map<Integer, TGameServer> map = DataSourceManager.getInstatnce().getGameServerMap();
		return map;
	}
}
