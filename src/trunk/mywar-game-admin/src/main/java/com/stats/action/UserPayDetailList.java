package com.stats.action;

import java.util.List;

import com.adminTool.bo.Partner;
import com.adminTool.service.PartnerService;
import com.framework.common.ALDAdminStatsDatePageActionSupport;
import com.framework.common.IPage;
import com.framework.servicemanager.ServiceCacheFactory;
import com.stats.bo.UserPayDetail;
import com.stats.service.UserPayStatsService;

public class UserPayDetailList extends ALDAdminStatsDatePageActionSupport {
	private static final long serialVersionUID = 1L;
	private String channel;
	private List<Partner> partnerList;
	private List<UserPayDetail> detailList;
	@Override
	public String execute() throws Exception {
		UserPayStatsService userPayStatsService = ServiceCacheFactory.getServiceCache().getService(UserPayStatsService.class);
		PartnerService partnerService = ServiceCacheFactory.getServiceCache().getService(PartnerService.class);
		partnerList = partnerService.findPartnerList();
		IPage<UserPayDetail> page = null;
		if (getStartDate() != null && getEndDate() != null) {
			page = userPayStatsService.findDetailPageListInDate(super.getPageSize(), super.getToPage(),channel, getStartDate(), getEndDate());
		} else {
			page = userPayStatsService.findDetailPageList(channel,super.getPageSize(), super.getToPage());
		}
		if(page!=null){
			detailList = (List<UserPayDetail>)page.getData();
			super.setTotalPage(page.getTotalPage());
			super.setTotalSize(page.getTotalSize());
		}
		return SUCCESS;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public List<UserPayDetail> getDetailList() {
		return detailList;
	}
	public void setDetailList(List<UserPayDetail> detailList) {
		this.detailList = detailList;
	}
	public List<Partner> getPartnerList() {
		return partnerList;
	}
	public void setPartnerList(List<Partner> partnerList) {
		this.partnerList = partnerList;
	}
}	
