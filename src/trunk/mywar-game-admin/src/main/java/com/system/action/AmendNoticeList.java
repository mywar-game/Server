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
import com.system.service.AmendNoticeService;
import com.system.service.GameWebService;

/**
 * 渠道通知列表
 * 
 * @author lin
 */
public class AmendNoticeList extends ALDAdminPageActionSupport {

	private static final long serialVersionUID = -7179195105197455616L;
	
	private Map<GameWeb, List<AmendNotice>> amendNoticeMap;

	public String execute() {
		GameWebService service = ServiceCacheFactory.getServiceCache()
				.getService(GameWebService.class);
		AmendNoticeService amendNoticeService = ServiceCacheFactory.getServiceCache()
				.getService(AmendNoticeService.class);
		amendNoticeMap = new HashMap<GameWeb, List<AmendNotice>>();
		List<GameWeb> list = service.findGameWebList();
		
		PartnerService partnerService = ServiceCacheFactory.getServiceCache()
				.getService(PartnerService.class);
		Map<String, Partner> partnerMap = partnerService.findAllPartnerMap();

		for (GameWeb gameWeb : list) {
			IPage<AmendNotice> iPage = amendNoticeService.findAmendNoticePageList(
					gameWeb.getServerId(), super.getToPage(),
					ALDAdminPageActionSupport.DEFAULT_PAGESIZE);

			List<AmendNotice> amendNoticeList = (List<AmendNotice>) iPage.getData();
			for (int i = 0; i < amendNoticeList.size(); i++) {
				AmendNotice an = amendNoticeList.get(i);
				String content = an.getContent();
				if (content.length() > 120) {
					an.setContent2(content.substring(0, 120) + "...");
				} else {
					an.setContent2(an.getContent());
				}

				//处理未知 partnerId
				if(!partnerMap.containsKey(an.getPartnerId())){
					an.setPartnerName("未知渠道");
				}else
					an.setPartnerName(partnerMap.get(an.getPartnerId()).getPName());
			}
			amendNoticeMap.put(gameWeb, amendNoticeList);
			
			super.setTotalPage(iPage.getTotalPage());
			super.setTotalSize(iPage.getTotalSize());
		}

		return SUCCESS;
	}

	public Map<GameWeb, List<AmendNotice>> getAmendNoticeMap() {
		return amendNoticeMap;
	}

	public void setAmendNoticeMap(Map<GameWeb, List<AmendNotice>> amendNoticeMap) {
		this.amendNoticeMap = amendNoticeMap;
	}

}
