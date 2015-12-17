package com.system.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.framework.common.ALDAdminPageActionSupport;
import com.framework.common.IPage;
import com.framework.servicemanager.ServiceCacheFactory;
import com.system.bo.GameWeb;
import com.system.bo.Notice;
import com.system.service.GameWebService;
import com.system.service.NoticeService;

/**
 * 通知列表
 * 
 * @author yezp
 */
public class NoticeList extends ALDAdminPageActionSupport {

	private static final long serialVersionUID = -6730327320364071512L;

	private Map<GameWeb, List<Notice>> noticeMap;

	public String execute() {
		GameWebService service = ServiceCacheFactory.getServiceCache()
				.getService(GameWebService.class);
		NoticeService noticeService = ServiceCacheFactory.getServiceCache()
				.getService(NoticeService.class);
		noticeMap = new HashMap<GameWeb, List<Notice>>();
		List<GameWeb> list = service.findGameWebList();
		long maxTotalPage = 0;
		long maxTotalSize = 0;
		
		for (GameWeb gameWeb : list) {
			IPage<Notice> iPage = noticeService.findNoticePageList(
					gameWeb.getServerId(), super.getToPage(),
					ALDAdminPageActionSupport.DEFAULT_PAGESIZE);

			List<Notice> noticeList = (List<Notice>) iPage.getData();
			for (int i = 0; i < noticeList.size(); i++) {
				Notice n = noticeList.get(i);
				String content = n.getContent();
				if (content.length() > 120) {
					n.setContent2(content.substring(0, 120) + "...");
				} else {
					n.setContent2(n.getContent());
				}
			}
			noticeMap.put(gameWeb, noticeList);
			
			if (maxTotalPage < iPage.getTotalPage()) {
				maxTotalPage = iPage.getTotalPage();
				super.setTotalPage(maxTotalPage);
			}
			if (maxTotalSize < iPage.getTotalSize()) {
				maxTotalSize = iPage.getTotalSize();
				super.setTotalSize(maxTotalSize);
			}
//			super.setTotalPage(iPage.getTotalPage());
//			super.setTotalSize(iPage.getTotalSize());
		}

		return SUCCESS;
	}

	public Map<GameWeb, List<Notice>> getNoticeMap() {
		return noticeMap;
	}

	public void setNoticeMap(Map<GameWeb, List<Notice>> noticeMap) {
		this.noticeMap = noticeMap;
	}

}
