package com.admin.action;

import com.admin.service.AdminPowerPhysicsService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;

public class DelPhysics extends ALDAdminActionSupport {
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private Integer id;
  public String execute() {
	  AdminPowerPhysicsService aps = ServiceCacheFactory.getServiceCache().getService(AdminPowerPhysicsService.class);
	  aps.delPowerPhysicsService(id);
	  return SUCCESS;
  }
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
}
