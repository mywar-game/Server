package com.system.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.adminTool.bo.Partner;
import com.adminTool.service.PartnerService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;
import com.opensymphony.xwork2.ModelDriven;
import com.system.bo.PackageInfo;
import com.system.service.PackageInfoService;

/**
 * 修改版本包信息
 * 
 * @author yezp
 */
public class UpdatePackageInfo extends ALDAdminActionSupport implements
		ModelDriven<PackageInfo> {

	private static final long serialVersionUID = -7958966972140811420L;

	private PackageInfo packageInfo = new PackageInfo();
	private Map<String, PackageInfo> infoMap;
	private List<Partner> partnerList;

	private String isCommit = "F";
	private String gameWebId;
	private String[] pid;

	public String execute() {
		PackageInfoService infoService = ServiceCacheFactory.getServiceCache()
				.getService(PackageInfoService.class);
		PartnerService partnerService = ServiceCacheFactory.getServiceCache()
				.getService(PartnerService.class);

		Integer dbSourceId = 0;
		if (gameWebId != null && !gameWebId.equals("")) {
			dbSourceId = Integer.parseInt(gameWebId);
		}

		partnerList = partnerService.findPartnerList();
		infoMap = new HashMap<String, PackageInfo>();
		List<PackageInfo> infoList = infoService.getInfoListByVersion(
				packageInfo.getVersion(), dbSourceId);
		for (PackageInfo info : infoList) {
			infoMap.put(info.getPartnerId(), info);
		}

		if ("F".equals(isCommit)) {
			if (infoList != null && infoList.size() > 0) {
				packageInfo = infoList.get(0);
			}
			return INPUT;
		}

		for (String partnerId : pid) {
			// 已有的版本信息，先删除再添加
			if (infoMap.containsKey(partnerId)) {
				infoService.delPackageInfo(infoMap.get(partnerId).getId(), dbSourceId);
			} 

			packageInfo.setPartnerId(partnerId);
			infoService.addPackageInfo(packageInfo, dbSourceId);
			
		}

		return SUCCESS;
	}

	public String[] getPid() {
		return pid;
	}

	public void setPid(String[] pid) {
		this.pid = pid;
	}

	public PackageInfo getPackageInfo() {
		return packageInfo;
	}

	public void setPackageInfo(PackageInfo packageInfo) {
		this.packageInfo = packageInfo;
	}

	public String getIsCommit() {
		return isCommit;
	}

	public void setIsCommit(String isCommit) {
		this.isCommit = isCommit;
	}

	public String getGameWebId() {
		return gameWebId;
	}

	public void setGameWebId(String gameWebId) {
		this.gameWebId = gameWebId;
	}

	public Map<String, PackageInfo> getInfoMap() {
		return infoMap;
	}

	public void setInfoMap(Map<String, PackageInfo> infoMap) {
		this.infoMap = infoMap;
	}

	public List<Partner> getPartnerList() {
		return partnerList;
	}

	public void setPartnerList(List<Partner> partnerList) {
		this.partnerList = partnerList;
	}

	@Override
	public PackageInfo getModel() {
		// TODO Auto-generated method stub
		return packageInfo;
	}

}
