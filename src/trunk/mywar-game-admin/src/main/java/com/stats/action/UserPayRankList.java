package com.stats.action;

import java.util.ArrayList;
import java.util.List;
import com.adminTool.bo.Partner;
import com.adminTool.service.PartnerService;
import com.framework.common.ALDAdminStatsDatePageActionSupport;
import com.framework.common.IPage;
import com.framework.servicemanager.ServiceCacheFactory;
import com.framework.util.DateUtil;
import com.stats.bean.PayRank;
import com.stats.service.UserPayStatsService;

public class UserPayRankList extends ALDAdminStatsDatePageActionSupport {
	private static final long serialVersionUID = 1L;
	private String channel;
	private List<Partner> partnerList;
	private List<PayRank> rankList = new ArrayList<PayRank>();
	@Override
	public String execute() throws Exception {
		UserPayStatsService userPayStatsService = ServiceCacheFactory.getServiceCache().getService(UserPayStatsService.class);
		PartnerService partnerService = ServiceCacheFactory.getServiceCache().getService(PartnerService.class);
		partnerList = partnerService.findPartnerList();
		String[] dates = new String[2];
		dates[0] = DateUtil.dateToString(super.getStartDate(), DateUtil.LONG_DATE_FORMAT);
		dates[1] = DateUtil.dateToString(super.getEndDate(), DateUtil.LONG_DATE_FORMAT);
		IPage<Object> page = userPayStatsService.findChannelPayRank(dates, channel,super.getPageSize(),super.getToPage());
		if(page!=null){
			List<Object> list = (List<Object>)page.getData();
			if(list!=null && list.size()>0){
				for(int i=0;i<list.size();i++){
					Object[] arr = (Object[])list.get(i);
					PayRank rank = new PayRank();
					rank.setRank((i+1)*(super.getToPage()*super.getPageSize()+1));
					rank.setUserId(arr[0].toString());
					rank.setUserName(arr[1].toString());
					rank.setSysNum(Integer.valueOf(arr[2].toString()));
					rank.setUserLevel(Integer.valueOf(arr[3].toString()));
					rank.setAmount(Double.valueOf(arr[4].toString()));
					rank.setPayTimes(Integer.valueOf(arr[5].toString()));
					rank.setLastLoginTime(arr[6].toString());
					rankList.add(rank);
				}
				super.setTotalPage(page.getTotalPage());
				super.setTotalSize(page.getTotalSize());
			}
		}
		return SUCCESS;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public List<Partner> getPartnerList() {
		return partnerList;
	}
	public void setPartnerList(List<Partner> partnerList) {
		this.partnerList = partnerList;
	}
	public List<PayRank> getRankList() {
		return rankList;
	}
	public void setRankList(List<PayRank> rankList) {
		this.rankList = rankList;
	}

}
