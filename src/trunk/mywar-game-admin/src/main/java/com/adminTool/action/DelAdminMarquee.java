package com.adminTool.action;

import com.adminTool.service.AdminMarqueeService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;

/**
 * 删除跑马灯
 * 
 * @author yezp
 */
public class DelAdminMarquee extends ALDAdminActionSupport {

	private static final long serialVersionUID = 9040581215822872230L;

	private Integer id;

	public void executeDel() {
		AdminMarqueeService service = ServiceCacheFactory.getServiceCache()
				.getService(AdminMarqueeService.class);
		
		service.delAdminMarquee(id);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
