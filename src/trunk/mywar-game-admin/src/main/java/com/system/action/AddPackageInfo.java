package com.system.action;

import java.sql.Timestamp;
import java.util.Date;
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
 * 添加包信息
 * 
 * @author yezp
 */
public class AddPackageInfo extends ALDAdminActionSupport implements
		ModelDriven<PackageInfo> {

	private static final long serialVersionUID = 5370216532419668799L;

	private PackageInfo packageInfo = new PackageInfo();
	private String gameWebId;
	private List<Partner> partnerList;
	private String[] pid;
	private String isCommit = "F";

	public String execute() {
		PartnerService partnerService = ServiceCacheFactory
				.getServiceCache().getService(PartnerService.class);
		partnerList = partnerService.findPartnerList();
		if (isCommit.equals("F")) {
			return INPUT;
		}

		if (pid == null) {
			setErroDescrip("合作方不能为空。");
			return INPUT;
		}
		
		PackageInfoService packageInfoService = ServiceCacheFactory
				.getServiceCache().getService(PackageInfoService.class);
		Integer dbSourceId = 0;
		if (gameWebId != null && !gameWebId.equals("")) {
			dbSourceId = Integer.parseInt(gameWebId);
		}
		
		// 检查是否重复
		List<PackageInfo> infoList = packageInfoService.getPackageInfoList(dbSourceId);
		for (String partnerId : pid) {
			for (PackageInfo info : infoList) {
				if (info.getVersion().equals(packageInfo.getVersion()) && 
						info.getPartnerId().equals(partnerId)) {
					Map<String, Partner> partnerMap = partnerService
							.findAllPartnerMap();
					setErroDescrip("已存在该版本包信息：" + packageInfo.getVersion()
							+ "，合作方:" + partnerMap.get(partnerId).getPName());

					return INPUT;
				}
			}
		}
		
		Date date = new Date();
		Timestamp createdTime = new Timestamp(date.getTime());
		packageInfo.setCreatedTime(createdTime);
		
		// 添加
		for (String partnerId : pid) {
			packageInfo.setPartnerId(partnerId);
			packageInfoService.addPackageInfo(packageInfo, dbSourceId);
		}
		
		return SUCCESS;
	}

	public PackageInfo getPackageInfo() {
		return packageInfo;
	}

	public void setPackageInfo(PackageInfo packageInfo) {
		this.packageInfo = packageInfo;
	}

	public String getGameWebId() {
		return gameWebId;
	}

	public void setGameWebId(String gameWebId) {
		this.gameWebId = gameWebId;
	}

	public String getIsCommit() {
		return isCommit;
	}

	public void setIsCommit(String isCommit) {
		this.isCommit = isCommit;
	}

	public List<Partner> getPartnerList() {
		return partnerList;
	}

	public void setPartnerList(List<Partner> partnerList) {
		this.partnerList = partnerList;
	}

	public String[] getPid() {
		return pid;
	}

	public void setPid(String[] pid) {
		this.pid = pid;
	}

	@Override
	public PackageInfo getModel() {
		// TODO Auto-generated method stub
		return packageInfo;
	}

}
