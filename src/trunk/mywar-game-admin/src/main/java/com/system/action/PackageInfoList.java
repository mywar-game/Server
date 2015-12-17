package com.system.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.adminTool.bo.Partner;
import com.adminTool.service.PartnerService;
import com.framework.common.ALDAdminPageActionSupport;
import com.framework.common.IPage;
import com.framework.servicemanager.ServiceCacheFactory;
import com.system.bo.GameWeb;
import com.system.bo.PackageInfo;
import com.system.service.GameWebService;
import com.system.service.PackageInfoService;

/**
 * 包信息列表
 * 
 * @author yezp
 */
public class PackageInfoList extends ALDAdminPageActionSupport {

	private static final long serialVersionUID = 1533387613343775075L;

	private Map<GameWeb, List<PackageInfo>> infoMap;
	private Map<String, Partner> partnerMap;

	public String execute() {
		GameWebService service = ServiceCacheFactory.getServiceCache()
				.getService(GameWebService.class);
		PackageInfoService packageInfoService = ServiceCacheFactory
				.getServiceCache().getService(PackageInfoService.class);
		PartnerService partnerService = ServiceCacheFactory.getServiceCache()
				.getService(PartnerService.class);

		partnerMap = partnerService.findAllPartnerMap();
		infoMap = new HashMap<GameWeb, List<PackageInfo>>();
		List<GameWeb> list = service.findGameWebList();		
		
		for (GameWeb gameWeb : list) {
			IPage<PackageInfo> iPage = packageInfoService.findPackageInfoPageList(gameWeb.getServerId(), super.getToPage(),
					ALDAdminPageActionSupport.DEFAULT_PAGESIZE);
			
			List<PackageInfo> infoList = (List<PackageInfo>) iPage.getData();
			infoMap.put(gameWeb, infoList);
			
			super.setTotalPage(iPage.getTotalPage());
			super.setTotalSize(iPage.getTotalSize());
		}
		
		return SUCCESS;
	}

	public Map<GameWeb, List<PackageInfo>> getInfoMap() {
		return infoMap;
	}

	public void setInfoMap(Map<GameWeb, List<PackageInfo>> infoMap) {
		this.infoMap = infoMap;
	}

	public Map<String, Partner> getPartnerMap() {
		return partnerMap;
	}

	public void setPartnerMap(Map<String, Partner> partnerMap) {
		this.partnerMap = partnerMap;
	}

}
