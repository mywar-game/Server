package com.admin.action;

import com.admin.service.AdminMenuService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.common.ErrorCode;
import com.framework.common.SystemCode;
import com.framework.servicemanager.ServiceCacheFactory;

public class DeletMenuChild extends ALDAdminActionSupport {
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private Integer Id;
public Integer getId() {
	return Id;
}
public void setId(Integer id) {
	Id = id;
}
public String execute() {
	AdminMenuService ams = ServiceCacheFactory.getServiceCache().getService(AdminMenuService.class);
    ErrorCode error = new ErrorCode();
	ams.delAdminMenuService(Id, error);
	if (error.getErrorCode() != SystemCode.SYS_SUCESS) {
		super.setErroCodeNum(error.getErrorCode());
		super.setErroDescrip(this.getText(error.getErrorDisc()));
	} else {
		return "result";
	}
	return SUCCESS;
}
}
