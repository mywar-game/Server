package com.system.action;

import java.util.ArrayList;
import java.util.List;

import com.adminTool.bo.Partner;
import com.adminTool.service.PartnerService;
import com.framework.common.ALDAdminPageActionSupport;
import com.framework.common.IPage;
import com.framework.servicemanager.ServiceCacheFactory;

/**
 * 合作方，渠道列表
 * 
 * @author yezp
 */
public class PartnerList extends ALDAdminPageActionSupport {

	private static final long serialVersionUID = -927907427472614227L;

	private List<Partner> partnerList;

	public String execute() {
		PartnerService partnerService = ServiceCacheFactory.getServiceCache()
				.getService(PartnerService.class);
		IPage<Partner> iPage = partnerService.findPartnerPageList(
				super.getToPage(), ALDAdminPageActionSupport.DEFAULT_PAGESIZE);

		if (iPage == null) {
			partnerList = new ArrayList<Partner>();
			return SUCCESS;
		}

		partnerList = (List<Partner>) iPage.getData();
		super.setTotalPage(iPage.getTotalPage());
		super.setTotalSize(iPage.getTotalSize());

		return SUCCESS;
	}

	public List<Partner> getPartnerList() {
		return partnerList;
	}

	public void setPartnerList(List<Partner> partnerList) {
		this.partnerList = partnerList;
	}

}
