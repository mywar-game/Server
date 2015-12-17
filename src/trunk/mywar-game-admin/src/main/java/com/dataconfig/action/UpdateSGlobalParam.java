package com.dataconfig.action;

import com.dataconfig.bo.SGlobalParam;
import com.dataconfig.service.SGlobalParamService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;
import com.opensymphony.xwork2.ModelDriven;

public class UpdateSGlobalParam  extends ALDAdminActionSupport implements ModelDriven<SGlobalParam> {

	/** **/
	private static final long serialVersionUID = -2265580788201208909L;

	/** **/
	private SGlobalParam globalParam = new SGlobalParam();
	
	/** **/
	private String isCommit = "F";
	
	public String execute() {
		SGlobalParamService sGlobalParamService = ServiceCacheFactory.getServiceCache().getService(SGlobalParamService.class);
		if ("F".equals(isCommit)) {
			globalParam = sGlobalParamService.findOneSGlobalParam(globalParam.getGlobalKey());
			return INPUT;
		} else {
			sGlobalParamService.updateOnesGlobalParam(globalParam);
			return SUCCESS;
		}
	}
	
	public String getIsCommit() {
		return isCommit;
	}
	public void setIsCommit(String isCommit) {
		this.isCommit = isCommit;
	}

	@Override
	public SGlobalParam getModel() {
		return globalParam;
	}

	/**
	 * @return the globalParam
	 */
	public SGlobalParam getGlobalParam() {
		return globalParam;
	}

	/**
	 * @param globalParam the globalParam to set
	 */
	public void setGlobalParam(SGlobalParam globalParam) {
		this.globalParam = globalParam;
	}

}
