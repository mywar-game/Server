package com.adminTool.action;

import java.net.URLEncoder;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.adminTool.bo.MallDiscount;
import com.adminTool.bo.MallDiscountItem;
import com.adminTool.service.MallDiscountItemService;
import com.adminTool.service.MallDiscountService;
import com.framework.client.http.HttpClientBridge;
import com.framework.common.ALDAdminPageActionSupport;
import com.framework.common.IPage;
import com.framework.constant.SystemConstant;
import com.framework.servicemanager.ServiceCacheFactory;
import com.opensymphony.xwork2.ModelDriven;
import com.system.bo.TGameServer;
import com.system.service.TGameServerService;

public class AuditActivity extends ALDAdminPageActionSupport implements ModelDriven<MallDiscount>{

	private static final long serialVersionUID = -6793778563504805741L;
	private static final String Add_Items_URL = "addMallDiscountItems.do";
	private static final String Add_DISCOUNT_URL = "addMallDiscount.do";
	
	private String isCommit = "F";
	private Integer isAudited = 0;
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
	
	public List<TGameServer> getTgameServerList() {
		return tgameServerList;
	}
	
	public String execute() {
		MallDiscountService mds = ServiceCacheFactory.getServiceCache().getService(MallDiscountService.class);
		MallDiscountItemService mdis = ServiceCacheFactory.getServiceCache().getService(MallDiscountItemService.class);
		TGameServerService tGameServerService = ServiceCacheFactory.getServiceCache().getService(TGameServerService.class);
		mallDiscount = mds.findOneMallDiscount(mallDiscount.getActivityId());
		IPage<TGameServer> iPage = tGameServerService.findTGameServerPageList(super.getToPage(), ALDAdminPageActionSupport.DEFAULT_PAGESIZE);
		mallDiscountItemList = mdis.findMallDiscountItemList(mallDiscount.getActivityId());

		if ("F".equals(this.isCommit)) {
			// 获取勾选的服务器别名
			StringBuilder serverStr = new StringBuilder();
			String[] servers = mallDiscount.getServers().split(", ");
			for (String server : servers) {
				 TGameServer gameServer = tGameServerService.findOneTGameServer(Integer.valueOf(server));
				 if (gameServer == null)
					 continue;
				
				String name = gameServer.getServerAlias();
				serverStr.append(name).append("  ");
			}
			selectedServers = serverStr.toString();
			
			
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
				

				super.setTotalPage(iPage.getTotalPage());
				super.setTotalSize(iPage.getTotalSize());

			}
			
			return INPUT;
		} else {
			
			// 如果审批通过，则将打折数据发送到游戏服务器
			if (isAudited == 1) {
				String[] servers = mallDiscount.getServers().split(", ");
				
				for (String server : servers) {
					TGameServer gameServer = tGameServerService.findOneTGameServer(Integer.valueOf(server));
					if (gameServer == null)
						continue;
					
					// 将这次打折活动的信息添加到游戏数据库的 system_mall_discount_activity 表中
					addMallDiscount(gameServer);
					// 将这次活动中的打折商品添加到游戏数据库的 system_mall_discount_items 表中
					addMallDiscountItems(gameServer);
				}
			}
			
			mallDiscount.setStatus(isAudited);
			mds.updateMallDiscount(mallDiscount);
			
			return SUCCESS;
		}
	}
	
	@SuppressWarnings("deprecation")
	private String addMallDiscount(TGameServer gameServer) {
		StringBuilder sb = new StringBuilder(); // params
		StringBuilder sb1 = new StringBuilder(); // md5 
		sb.append("&activityId=").append(mallDiscount.getActivityId());
		sb.append("&startTime=").append(URLEncoder.encode(mallDiscount.getStartTime().toString()));
		sb.append("&endTime=").append(URLEncoder.encode(mallDiscount.getEndTime().toString()));
		
		sb1.append(mallDiscount.getActivityId()).append(URLEncoder.encode(mallDiscount.getStartTime().toString())).
					append(URLEncoder.encode(mallDiscount.getEndTime().toString()));
		
		String response = HttpClientBridge.sendToGameServer(Add_DISCOUNT_URL, sb.toString(), sb1.toString() , gameServer);
		
		if(response==null){
			super.setErroCodeNum(SystemConstant.FAIL_CODE);
			super.setErroDescrip("发送出现异常,请查看日志！");
			return ERROR;
		} else {
			return SUCCESS;
		}
	}

	/**
	 * 将审核通过的打折活动信息发送到游戏服务器
	 * @param server
	 */
	private String addMallDiscountItems(TGameServer gameServer) {
		StringBuilder sb = new StringBuilder(); // params
		StringBuilder sb1 =  new StringBuilder(); //md5str
		StringBuilder mallIdsStr = new StringBuilder();
		StringBuilder discountsStr = new StringBuilder();
		
		for (MallDiscountItem item : mallDiscountItemList) {
			if (item.getIsChecked() == 1) {
				mallIdsStr.append(item.getMallId()).append(",");
				discountsStr.append(item.getDiscount()).append(",");
			}
		}
		
		String mallIds = mallIdsStr.toString();
		String discounts = discountsStr.toString();
		
		sb.append("&activityId=").append(mallDiscount.getActivityId());
		sb.append("&mallIds=").append(mallIds.substring(0, mallIds.length()-1));
		sb.append("&discounts=").append(discounts.substring(0, discounts.length()-1));
		sb1.append(mallDiscount.getActivityId()).append(mallIds.substring(0, mallIds.length()-1)).append(discounts.substring(0, discounts.length()-1));
		
		String response = HttpClientBridge.sendToGameServer(Add_Items_URL, sb.toString(), sb1.toString(), gameServer);
		if(response==null){
			super.setErroCodeNum(SystemConstant.FAIL_CODE);
			super.setErroDescrip("发送出现异常,请查看日志！");
			return ERROR;
		} else {
			return SUCCESS;
		}
	}

	public String getSelectedServers() {
		return selectedServers;
	}

	public void setSelectedServers(String selectedServers) {
		this.selectedServers = selectedServers;
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

	public Integer getIsAudited() {
		return isAudited;
	}

	public void setIsAudited(Integer isAudited) {
		this.isAudited = isAudited;
	}

	public String getIsCommit() {
		return isCommit;
	}

	public void setIsCommit(String isCommit) {
		this.isCommit = isCommit;
	}

	public void setTgameServerList(List<TGameServer> tgameServerList) {
		this.tgameServerList = tgameServerList;
	}
	public List<MallDiscountItem> getMallDiscountItemList() {
		return mallDiscountItemList;
	}
	public void setMallDiscountItemList(List<MallDiscountItem> mallDiscountItemList) {
		this.mallDiscountItemList = mallDiscountItemList;
	}
	public MallDiscount getMallDiscount() {
		return mallDiscount;
	}
	public void setMallDiscount(MallDiscount mallDiscount) {
		this.mallDiscount = mallDiscount;
	}

	@Override
	public MallDiscount getModel() {
		return mallDiscount;
	}
	
	
	
	
}
