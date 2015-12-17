package com.dataconfig.action;

import java.sql.Timestamp;

import com.adminTool.bo.AdminChangeConstantLog;
import com.adminTool.service.AdminChangeConstantLogService;
import com.dataconfig.bo.TMallTreasureConstantId;
import com.dataconfig.service.TMallTreasureConstantService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;
import com.opensymphony.xwork2.ModelDriven;

public class DelTMallTreasureConstant  extends ALDAdminActionSupport implements ModelDriven<TMallTreasureConstantId> {

	/**  */
	private static final long serialVersionUID = 8661744331309899245L;
	
	/**  */
	private TMallTreasureConstantId id = new TMallTreasureConstantId();
	
	@Override
	public TMallTreasureConstantId getModel() {
		return getId();
	}
	
	public void executeDel() {
		TMallTreasureConstantService tMallTreasureConstantService = ServiceCacheFactory.getServiceCache().getService(TMallTreasureConstantService.class);
		tMallTreasureConstantService.delTMallTreasureConstant(id);
		//记录日志
		AdminChangeConstantLog adminChangeConstantLog = new AdminChangeConstantLog();
		adminChangeConstantLog.setSysNum(Integer.valueOf(super.getAdminUser().getExp1()));
		adminChangeConstantLog.setAdminName(super.getAdminUser().getAdminName());
		adminChangeConstantLog.setChangeTime(new Timestamp(System.currentTimeMillis()));
		adminChangeConstantLog.setConstantName("TMallTreasureConstant");
		adminChangeConstantLog.setChangeType(2);
		adminChangeConstantLog.setChangeDetail("删除"+super.getText("mallTreasureConstant.id.category_"+id.getCategory())+"类型的"+id.getTreasureId()+"记录<br/>");
		AdminChangeConstantLogService adminChangeConstantLogService = ServiceCacheFactory.getServiceCache().getService(AdminChangeConstantLogService.class);
		adminChangeConstantLogService.saveOne(adminChangeConstantLog);
	}
	
	public void setId(TMallTreasureConstantId id) {
		this.id = id;
	}
	
	public TMallTreasureConstantId getId() {
		return id;
	}
	
}
