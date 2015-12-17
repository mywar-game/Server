package com.dataconfig.action;

import java.sql.Timestamp;

import com.adminTool.bo.AdminChangeConstantLog;
import com.adminTool.service.AdminChangeConstantLogService;
import com.dataconfig.bo.TMallTreasureConstant;
import com.dataconfig.service.TMallTreasureConstantService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;
import com.opensymphony.xwork2.ModelDriven;

public class AddTMallTreasureConstant  extends ALDAdminActionSupport implements ModelDriven<TMallTreasureConstant> {
	
	/**  */
	private static final long serialVersionUID = 7321329463918523696L;

	/** **/
	private TMallTreasureConstant tMallTreasureConstant = new TMallTreasureConstant();
	
	/** **/
	private String isCommit = "F";
	
	@Override
	public TMallTreasureConstant getModel() {
		return tMallTreasureConstant;
	}
	
	public String execute() {
		if ("F".equals(isCommit)) {
			return INPUT;
		} else {
			TMallTreasureConstantService tMallTreasureConstantService = ServiceCacheFactory.getServiceCache().getService(TMallTreasureConstantService.class);
			tMallTreasureConstantService.addTMallTreasureConstant(tMallTreasureConstant);
			//记录日志
			AdminChangeConstantLog adminChangeConstantLog = new AdminChangeConstantLog();
			adminChangeConstantLog.setSysNum(Integer.valueOf(super.getAdminUser().getExp1()));
			adminChangeConstantLog.setAdminName(super.getAdminUser().getAdminName());
			adminChangeConstantLog.setChangeTime(new Timestamp(System.currentTimeMillis()));
			adminChangeConstantLog.setConstantName("TMallTreasureConstant");
			adminChangeConstantLog.setChangeType(1);
			adminChangeConstantLog.setChangeDetail("添加"+super.getText("mallTreasureConstant.id.category_"+tMallTreasureConstant.getId().getCategory())+"类型的"+tMallTreasureConstant.getId().getTreasureId()+"记录<br/>");
			AdminChangeConstantLogService adminChangeConstantLogService = ServiceCacheFactory.getServiceCache().getService(AdminChangeConstantLogService.class);
			adminChangeConstantLogService.saveOne(adminChangeConstantLog);
			return SUCCESS;
		}
	}

	public TMallTreasureConstant gettMallTreasureConstant() {
		return tMallTreasureConstant;
	}

	public void settMallTreasureConstant(TMallTreasureConstant tMallTreasureConstant) {
		this.tMallTreasureConstant = tMallTreasureConstant;
	}

	public String getIsCommit() {
		return isCommit;
	}

	public void setIsCommit(String isCommit) {
		this.isCommit = isCommit;
	}

}
