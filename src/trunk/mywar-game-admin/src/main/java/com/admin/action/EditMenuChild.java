package com.admin.action;

import com.admin.bo.AdminMenu;
import com.admin.service.AdminMenuService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.common.ErrorCode;
import com.framework.common.SystemCode;
import com.framework.servicemanager.ServiceCacheFactory;

public class EditMenuChild extends ALDAdminActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String menuName;
	private String menuPath;
	private Integer orderId;
	private String ifLeaf;
	private String isCommit = "F"; 
	
	public String getIsCommit() {
		return isCommit;
	}

	public void setIsCommit(String isCommit) {
		this.isCommit = isCommit;
	}

	public String execute() {
		AdminMenuService ams = ServiceCacheFactory.getServiceCache().getService(AdminMenuService.class);
		
		AdminMenu am = ams.findOneAdminMenu(id);
		if (am == null) return ERROR;
		/**
		 * 查询
		 */
		if (isCommit.equals("F")) {
			menuName = am.getMenuName();
			menuPath = am.getMenuPath();
			ifLeaf = am.getIfLeaf();
			int dateOrderId = am.getOrderId();
			int max = ams.findMaxOrderId(am.getParentId());
            int min = ams.findMinOrderId(am.getParentId());
			if (dateOrderId == AdminMenuService.ORDER_INIT) {
				orderId = 0;
			//已经顶置
			} else if (dateOrderId == max) {
				orderId = 1;
			//如果已经底置
			} else if (dateOrderId == min) {
				orderId = -1;
			} else {
				orderId = 0;
			}

		}
		/**
		 * 提交编辑
		 */
		if (isCommit.equals("T")) {
            am.setIfLeaf(ifLeaf);
            am.setMenuName(menuName);
            am.setMenuPath(menuPath);
            
        	/**
    		 * 如果选择了置顶
    		 */
    		if (orderId == 1) {
    			orderId = ams.findMaxOrderId(am.getParentId()) + 1;
                am.setOrderId(orderId);
    		}
    		/**
    		 * 如果选择了底置
    		 */
    		if (orderId == -1) {
    			orderId = ams.findMinOrderId(am.getParentId()) - 1;
                am.setOrderId(orderId);
    		}
            
			ErrorCode error = new ErrorCode();
			ams.updateAdminMenuService(am, error);
			if (error.getErrorCode() != SystemCode.SYS_SUCESS) {
				super.setErroCodeNum(error.getErrorCode());
				super.setErroDescrip(this.getText(error.getErrorDisc()));
			} else {
				return "result";
			}
		}
		return SUCCESS;
	}
	
	public boolean check() {
         return true;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public String getMenuPath() {
		return menuPath;
	}
	public void setMenuPath(String menuPath) {
		this.menuPath = menuPath;
	}

	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public String getIfLeaf() {
		return ifLeaf;
	}
	public void setIfLeaf(String ifLeaf) {
		this.ifLeaf = ifLeaf;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}



}
