package com.system.action;

import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;
import com.system.service.PackageInfoService;

/**
 * 删除版本包信息
 * 
 * @author yezp
 */
public class DelPackageInfo extends ALDAdminActionSupport {

	private static final long serialVersionUID = -7851601068669383988L;

	private Integer id;
	private String gameWebId;

	public void executeDel() {
		PackageInfoService packageInfoService = ServiceCacheFactory
				.getServiceCache().getService(PackageInfoService.class);
		Integer dbSourceId = 0;
		if (gameWebId != null && !gameWebId.equals("")) {
			dbSourceId = Integer.parseInt(gameWebId);
		}
		
		packageInfoService.delPackageInfo(id, dbSourceId);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getGameWebId() {
		return gameWebId;
	}

	public void setGameWebId(String gameWebId) {
		this.gameWebId = gameWebId;
	}

}
