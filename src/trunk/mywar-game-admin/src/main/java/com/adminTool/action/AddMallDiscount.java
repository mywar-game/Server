package com.adminTool.action;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.beanutils.BeanUtils;

import com.adminTool.bo.MallDiscount;
import com.adminTool.bo.MallDiscountItem;
import com.adminTool.bo.SystemMallDiscount;
import com.adminTool.service.MallDiscountItemService;
import com.adminTool.service.MallDiscountService;
import com.framework.common.ALDAdminPageActionSupport;
import com.framework.common.IPage;
import com.framework.servicemanager.ServiceCacheFactory;
import com.opensymphony.xwork2.ModelDriven;
import com.system.bo.TGameServer;
import com.system.service.TGameServerService;

public class AddMallDiscount extends ALDAdminPageActionSupport implements
		ModelDriven<MallDiscount> {

	private static final long serialVersionUID = -4568765774317930763L;
	// 要提交到的游戏服务器
	@SuppressWarnings("unused")
	private static final String REQ_URL = "addMallDiscount.do";

	private MallDiscount mallDiscount = new MallDiscount();
	private String startDate;
	private String endDate;
	private Integer startHour;
	private Integer endHour;
	private Integer startMin;
	private Integer endMin;

	private List<MallDiscountItem> mallDiscountItemList = new ArrayList<MallDiscountItem>();
	private List<TGameServer> tgameServerList;
	private List<SystemMallDiscount> systemMallDiscountList;

	private String selectedServers;
	private String selectedItems;

	private String isCommit = "F";

	public String execute() throws Exception {
		MallDiscountService mds = ServiceCacheFactory.getServiceCache()
				.getService(MallDiscountService.class);
		MallDiscountItemService mdis = ServiceCacheFactory.getServiceCache()
				.getService(MallDiscountItemService.class);

		TGameServerService tGameServerService = ServiceCacheFactory
				.getServiceCache().getService(TGameServerService.class);
		IPage<TGameServer> iPage = tGameServerService.findTGameServerPageList(
				super.getToPage(), 10000);
		IPage<SystemMallDiscount> systemMallDiscountPage = mdis
				.findSystemMallDiscountList(super.getToPage(),
						ALDAdminPageActionSupport.DEFAULT_PAGESIZE);

		if ("F".equals(this.isCommit)) {
			if (iPage != null && systemMallDiscountPage != null) {
				tgameServerList = (List<TGameServer>) iPage.getData();
				// systemMallDiscountList = (List<SystemMallDiscount>)
				// systemMallDiscountPage.getData();
				systemMallDiscountList = mdis.findAllSystemMallDiscount();
				// super.setTotalPage(systemMallDiscountPage.getTotalPage());
				// super.setTotalSize(systemMallDiscountPage.getTotalSize());
			}
			return INPUT;
		} else {
			
			// 检测所添加的服务器是否有活动还未结束，未结束刚返回无法添加异常
			List<MallDiscount> discountList = mds.getMallDiscountList();
			String[] servers = selectedServers.split(",");
			for (MallDiscount disc : discountList) {
				for (String server : servers) {
					if (disc.getServers().contains(server)) {
						if (iPage != null && systemMallDiscountPage != null) {
							tgameServerList = (List<TGameServer>) iPage
									.getData();
							systemMallDiscountList = mdis
									.findAllSystemMallDiscount();
						}
						for (TGameServer tgs : tgameServerList) {
							if (tgs.getServerId()==Integer.parseInt(server)) {
								setErroDescrip("服务器：【" + tgs.getServerAlias()
										+ "】有活动正在运行，暂时无法添加新活动！");
								return INPUT;
							}
						}
					}
				}
			}

			String activityId = generateÅctivityID();

			for (SystemMallDiscount smd : systemMallDiscountList) {
				MallDiscountItem mdi = new MallDiscountItem();
				// BeanUtils.copyProperties(mdi, smd);
				// mdi.setActivityId(activityId);
				//
				// if (smd.getDiscount() == null){
				// mdi.setDiscount(100);
				// mdi.setDiscountPrice(smd.getOriginalPrice().doubleValue());
				// }

				// 根据 selectedItems 中的内容设置是否勾选
				String[] items = selectedItems.split(", ");

				// mdi.setIsChecked(0);

				for (String item : items) {
					if (Integer.valueOf(item).equals(smd.getMallId())) {
						BeanUtils.copyProperties(mdi, smd);
						mdi.setActivityId(activityId);
						mdi.setIsChecked(1);
						mallDiscountItemList.add(mdi);
						break;
					}
				}

				// mallDiscountItemList.add(mdi);

			}
			saveMallDiscount(mds, activityId);

			// 将打折活动商品添加到后台管理数据库
			mdis.addMallDiscountItem(mallDiscountItemList);

			return SUCCESS;
		}
	}

	private void saveMallDiscount(MallDiscountService mds, String activityId)
			throws ParseException {
		Calendar calendar = Calendar.getInstance();

		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		calendar.setTime(df.parse(startDate));
		if (startHour != null) {
			calendar.set(Calendar.HOUR_OF_DAY, startHour);
		}
		if (startMin != null) {
			calendar.set(Calendar.MINUTE, startMin);
		}
		Timestamp startTimestamp = new Timestamp(calendar.getTimeInMillis());
		mallDiscount.setStartTime(startTimestamp);

		calendar.setTime(df.parse(endDate));
		if (endHour != null) {
			calendar.set(Calendar.HOUR_OF_DAY, endHour);
		}

		if (endMin != null) {
			calendar.set(Calendar.MINUTE, endMin);
		}
		Timestamp endTimestamp = new Timestamp(calendar.getTimeInMillis());
		mallDiscount.setEndTime(endTimestamp);

		mallDiscount.setActivityId(activityId);
		long time = new Date().getTime();
		mallDiscount.setSubmitTime(new Timestamp(time));
		mallDiscount.setStatus(0);

		mallDiscount.setServers(selectedServers);
		mds.addMallDiscount(mallDiscount);
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

	public String generateÅctivityID() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

	public List<SystemMallDiscount> getSystemMallDiscountList() {
		return systemMallDiscountList;
	}

	public void setSystemMallDiscountList(
			List<SystemMallDiscount> systemMallDiscountList) {
		this.systemMallDiscountList = systemMallDiscountList;
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

	public MallDiscount getMallDiscount() {
		return mallDiscount;
	}

	public void setMallDiscount(MallDiscount mallDiscount) {
		this.mallDiscount = mallDiscount;
	}

	public String getIsCommit() {
		return isCommit;
	}

	public void setIsCommit(String isCommit) {
		this.isCommit = isCommit;
	}

	@Override
	public MallDiscount getModel() {
		return mallDiscount;
	}

}
