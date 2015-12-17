package com.system.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.adminTool.bo.Partner;
import com.adminTool.service.PartnerService;
import com.framework.common.ALDAdminPageActionSupport;
import com.framework.common.IPage;
import com.framework.servicemanager.ServiceCacheFactory;
import com.system.bo.AmendNotice;
import com.system.bo.GameWeb;
import com.system.bo.SpecialNotice;
import com.system.service.AmendNoticeService;
import com.system.service.GameWebService;
import com.system.service.SpecialNoticeService;

/**
 **登录通知列表
 * 
 * @author lin
 */
public class SpecialNoticeList extends ALDAdminPageActionSupport {

	private static final long serialVersionUID = -3440493231972074422L;
	private Map<GameWeb, List<SpecialNotice>> specialNoticeMap;

	public String execute() {
		GameWebService service = ServiceCacheFactory.getServiceCache()
				.getService(GameWebService.class);
		SpecialNoticeService specialNoticeService = ServiceCacheFactory.getServiceCache()
				.getService(SpecialNoticeService.class);
		specialNoticeMap = new HashMap<GameWeb, List<SpecialNotice>>();
		List<GameWeb> list = service.findGameWebList();
		
		PartnerService partnerService = ServiceCacheFactory.getServiceCache()
				.getService(PartnerService.class);
		Map<String, Partner> partnerMap = partnerService.findAllPartnerMap();

		for (GameWeb gameWeb : list) {
			IPage<SpecialNotice> iPage = specialNoticeService.findSpecialNoticePageList(
					gameWeb.getServerId(), super.getToPage(),
					ALDAdminPageActionSupport.DEFAULT_PAGESIZE);

			List<SpecialNotice> specialNoticeList = (List<SpecialNotice>) iPage.getData();
			for (int i = 0; i < specialNoticeList.size(); i++) {
				SpecialNotice sn = specialNoticeList.get(i);
				String content = sn.getContent();
				if (content.length() > 120) {
					sn.setContent2(content.substring(0, 120) + "...");
				} else {
					sn.setContent2(sn.getContent());
				}

				//处理未知 partnerId
				if(!partnerMap.containsKey(sn.getPartnerId())){
					sn.setPartnerName("未知渠道");
				}else
					sn.setPartnerName(partnerMap.get(sn.getPartnerId()).getPName());
			}
			specialNoticeMap.put(gameWeb, specialNoticeList);
			
			super.setTotalPage(iPage.getTotalPage());
			super.setTotalSize(iPage.getTotalSize());
		}

		return SUCCESS;
	}

	public Map<GameWeb, List<SpecialNotice>> getSpecialNoticeMap() {
		return specialNoticeMap;
	}

	public void setSpecialNoticeMap(Map<GameWeb, List<SpecialNotice>> specialNoticeMap) {
		this.specialNoticeMap = specialNoticeMap;
	}

}
