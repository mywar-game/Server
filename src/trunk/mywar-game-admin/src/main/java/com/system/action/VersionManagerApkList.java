package com.system.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.adminTool.bo.Partner;
import com.adminTool.bo.PartnerQn;
import com.adminTool.service.PartnerQnService;
import com.adminTool.service.PartnerService;
import com.framework.common.ALDAdminPageActionSupport;
import com.framework.common.IPage;
import com.framework.servicemanager.ServiceCacheFactory;
import com.system.bo.GameWeb;
import com.system.bo.VersionManagerApk;
import com.system.service.GameWebService;
import com.system.service.VersionManagerApkService;

/**
 * APK版本管理列表
 * 
 * @author yezp
 */
public class VersionManagerApkList extends ALDAdminPageActionSupport {

	private static final long serialVersionUID = 1087502710018795057L;
	private Map<String, Partner> partnerMap;
	private Map<GameWeb, List<VersionManagerApk>> apkMap;
	private Map<String, PartnerQn> partnerQnMap;

	public String execute() {
		GameWebService gameWebService = ServiceCacheFactory.getServiceCache()
				.getService(GameWebService.class);
		VersionManagerApkService service = ServiceCacheFactory
				.getServiceCache().getService(VersionManagerApkService.class);
		PartnerService partnerService = ServiceCacheFactory.getServiceCache()
				.getService(PartnerService.class);
		PartnerQnService qnService = ServiceCacheFactory.getServiceCache()
				.getService(PartnerQnService.class);

		List<GameWeb> list = gameWebService.findGameWebList();
		partnerMap = partnerService.findAllPartnerMap();
		partnerQnMap = qnService.findAllQnMap();

		apkMap = new HashMap<GameWeb, List<VersionManagerApk>>();
		for (GameWeb gameWeb : list) {
			IPage<VersionManagerApk> iPage = service.findVersionApkPageList(
					gameWeb.getServerId(), super.getToPage(),
					ALDAdminPageActionSupport.DEFAULT_PAGESIZE);

			List<VersionManagerApk> apkList = (List<VersionManagerApk>) iPage
					.getData();
			
			Collections.sort(apkList, new Comparator<VersionManagerApk>() {
	            public int compare(VersionManagerApk arg0, VersionManagerApk arg1) {
	            	// 比较大版本
	            	if (arg1.getApkBigVersion() > arg0.getApkBigVersion()) {
	            		return 1;
	            	} else if (arg1.getApkBigVersion() > arg0.getApkBigVersion()) {
	            		return -1;
	            	} else {
	            		// 比较小版本
	            		if (arg1.getApkSmallVersion() > arg0.getApkSmallVersion()) {
	            			return 1;
	            		} else {
	            			return -1;
	            		}
	            	}
	            }
	        });
			
			if (apkList != null && apkList.size() > 20) {
				List<VersionManagerApk> apkListTemp = new ArrayList<VersionManagerApk>();
				for (int i = 0; i < 20; i++) {
					apkListTemp.add(apkList.get(i));
				}
				apkMap.put(gameWeb, apkListTemp);
			} else {
				apkMap.put(gameWeb, apkList);
			}

			super.setTotalPage(iPage.getTotalPage());
			super.setTotalSize(iPage.getTotalSize());
		}

		return SUCCESS;
	}

	public Map<String, Partner> getPartnerMap() {
		return partnerMap;
	}

	public void setPartnerMap(Map<String, Partner> partnerMap) {
		this.partnerMap = partnerMap;
	}

	public Map<GameWeb, List<VersionManagerApk>> getApkMap() {
		return apkMap;
	}

	public void setApkMap(Map<GameWeb, List<VersionManagerApk>> apkMap) {
		this.apkMap = apkMap;
	}

	public Map<String, PartnerQn> getPartnerQnMap() {
		return partnerQnMap;
	}

	public void setPartnerQnMap(Map<String, PartnerQn> partnerQnMap) {
		this.partnerQnMap = partnerQnMap;
	}

}
