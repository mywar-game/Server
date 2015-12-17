package com.adminTool.action;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.adminTool.bo.MallDiscount;
import com.adminTool.bo.MallDiscountItem;
import com.adminTool.service.MallDiscountItemService;
import com.adminTool.service.MallDiscountService;
import com.framework.common.ALDAdminPageActionSupport;
import com.framework.common.IPage;
import com.framework.servicemanager.ServiceCacheFactory;
import com.opensymphony.xwork2.ModelDriven;
import com.system.bo.TGameServer;
import com.system.service.TGameServerService;


public class UpdateMallDiscount extends ALDAdminPageActionSupport implements ModelDriven<MallDiscount> {

	private static final long serialVersionUID = 1L;
	private String isCommit = "F";
	
	private List<TGameServer> tgameServerList ;
	private List<MallDiscountItem> mallDiscountItemList;
	
	private MallDiscount mallDiscount = new MallDiscount();
	private String startDate;
	private String endDate;
	private Integer startHour;
	private Integer endHour;
	private Integer startMin;
	private Integer endMin;
	
	private String selectedServers;
	private String selectedItems;
	
	public String execute() {
		MallDiscountService mds = ServiceCacheFactory.getServiceCache().getService(MallDiscountService.class);
		MallDiscountItemService mdis = ServiceCacheFactory.getServiceCache().getService(MallDiscountItemService.class);
		
		
		TGameServerService tGameServerService = ServiceCacheFactory.getServiceCache().getService(TGameServerService.class);
		IPage<TGameServer> iPage = tGameServerService.findTGameServerPageList(super.getToPage(), ALDAdminPageActionSupport.DEFAULT_PAGESIZE);
		
		if ("F".equals(this.isCommit)) {
			mallDiscount = mds.findOneMallDiscount(mallDiscount.getActivityId());

			DateFormat df=new SimpleDateFormat("yyyy-MM-dd HH mm"); 
			
			Timestamp startTimestamp = mallDiscount.getStartTime();
			String startTime = df.format(new Date(startTimestamp.getTime()));
			String[] strs = startTime.split(" ");
			startDate = strs[0];
			startHour = Integer.parseInt(strs[1]);
			startMin = Integer.parseInt(strs[2]);
			
			Timestamp endTimestamp = mallDiscount.getEndTime();
			String endTime = df.format(new Date(endTimestamp.getTime()));
			strs = endTime.split(" ");
			endDate = strs[0];
			endHour = Integer.parseInt(strs[1]);
			endMin = Integer.parseInt(strs[2]);
			
			if (iPage != null) {
				tgameServerList = (List<TGameServer>) iPage.getData();
				mallDiscountItemList = mdis.findMallDiscountItemList(mallDiscount.getActivityId());
//				super.setTotalPage(iPage.getTotalPage());
//				super.setTotalSize(iPage.getTotalSize());
			}
			
			return INPUT;
		} else {
			
			// 更新后台管理数据库中的商品打折活动
			for (MallDiscountItem discountItem : mallDiscountItemList) {
				if (selectedItems.contains(String.valueOf(discountItem.getMallId()))) {
					discountItem.setIsChecked(1);
				} else {
					discountItem.setIsChecked(0);
				}
				
				mdis.updateMallDiscountItem(discountItem);
			}
			
			updateMallDiscount(mds);
			
			return SUCCESS;
		}
	}

	private void updateMallDiscount(MallDiscountService mds) {
		Calendar calendar = Calendar.getInstance();
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try {
			calendar.setTime(df.parse(startDate));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if (startHour != null) {
			calendar.set(Calendar.HOUR_OF_DAY, startHour);
		}
		if (startMin != null) {
			calendar.set(Calendar.MINUTE, startMin);
		}
		Timestamp startTimestamp = new Timestamp(calendar.getTimeInMillis());
		mallDiscount.setStartTime(startTimestamp);
		
		
		try {
			calendar.setTime(df.parse(endDate));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if (endHour != null) {
			calendar.set(Calendar.HOUR_OF_DAY, endHour);
		}
		
		if (endMin != null){
			calendar.set(Calendar.MINUTE, endMin);
		}
		Timestamp endTimestamp = new Timestamp(calendar.getTimeInMillis());
		mallDiscount.setEndTime(endTimestamp);
	
		long time = new Date().getTime();
		mallDiscount.setSubmitTime(new Timestamp(time));
		mallDiscount.setStatus(0);
		
		mallDiscount.setServers(selectedServers);
		mds.updateMallDiscount(mallDiscount);
	}

	public String getStartDate() {
		return startDate;
	}



	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}



	public String getEndDate() {
		return endDate;
	}



	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}



	public Integer getStartHour() {
		return startHour;
	}



	public void setStartHour(Integer startHour) {
		this.startHour = startHour;
	}



	public Integer getEndHour() {
		return endHour;
	}



	public void setEndHour(Integer endHour) {
		this.endHour = endHour;
	}



	public Integer getStartMin() {
		return startMin;
	}



	public void setStartMin(Integer startMin) {
		this.startMin = startMin;
	}



	public Integer getEndMin() {
		return endMin;
	}



	public void setEndMin(Integer endMin) {
		this.endMin = endMin;
	}



	public List<MallDiscountItem> getMallDiscountItemList() {
		return mallDiscountItemList;
	}

	public void setMallDiscountItemList(
			List<MallDiscountItem> mallDiscountItemList) {
		this.mallDiscountItemList = mallDiscountItemList;
	}

	public List<TGameServer> getTgameServerList() {
		return tgameServerList;
	}

	public void setTgameServerList(List<TGameServer> tgameServerList) {
		this.tgameServerList = tgameServerList;
	}

	@Override
	public MallDiscount getModel() {
		return mallDiscount;
	}

	public MallDiscount getMallDiscount() {
		return mallDiscount;
	}

	public void setMallDiscount(MallDiscount mallDiscount) {
		this.mallDiscount = mallDiscount;
	}

	public String getSelectedServers() {
		return selectedServers;
	}

	public void setSelectedServers(String selectedServers) {
		this.selectedServers = selectedServers;
	}

	public String getSelectedItems() {
		return selectedItems;
	}

	public void setSelectedItems(String selectedItems) {
		this.selectedItems = selectedItems;
	}

	public String getIsCommit() {
		return isCommit;
	}

	public void setIsCommit(String isCommit) {
		this.isCommit = isCommit;
	}

}
