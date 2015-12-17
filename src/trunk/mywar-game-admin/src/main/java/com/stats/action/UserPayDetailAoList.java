package com.stats.action;

import java.util.List;
import com.framework.common.ALDAdminStatsDatePageActionSupport;
import com.framework.common.IPage;
import com.framework.servicemanager.ServiceCacheFactory;
import com.stats.bo.UserPayDetail;
import com.stats.service.UserPayStatsService;

public class UserPayDetailAoList extends ALDAdminStatsDatePageActionSupport {
	private static final long serialVersionUID = 1L;
	private String channel;
	private String sysNum;
	private List<UserPayDetail> detailList;
	@Override
	public String execute() throws Exception {
		UserPayStatsService userPayStatsService = ServiceCacheFactory.getServiceCache().getService(UserPayStatsService.class);
		IPage<UserPayDetail> page = null;
		if (getStartDate() != null && getEndDate() != null) {
			page = userPayStatsService.aoFindDetailPageListInDate(super.getPageSize(), super.getToPage(),channel,Integer.valueOf(sysNum), getStartDate(), getEndDate());
		} else {
			page = userPayStatsService.aoFindDetailPageList(channel,Integer.valueOf(sysNum),super.getPageSize(), super.getToPage());
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
	public String getSysNum() {
		return sysNum;
	}
	public void setSysNum(String sysNum) {
		this.sysNum = sysNum;
	}
}	
