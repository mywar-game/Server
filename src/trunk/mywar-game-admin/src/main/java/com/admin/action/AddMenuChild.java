package com.admin.action;

import com.admin.bo.AdminMenu;
import com.admin.service.AdminMenuService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.common.ErrorCode;
import com.framework.common.SystemCode;
import com.framework.servicemanager.ServiceCacheFactory;

public class AddMenuChild extends ALDAdminActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String menuName;
	private String menuPath;
	private Integer parentId;
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
		
		if (isCommit.equals("F")) return SUCCESS;
		
		AdminMenuService ams = ServiceCacheFactory.getServiceCache().getService(AdminMenuService.class);
		/**
		 * 查询当前最大权限位
		 */
		Integer powerId = ams.findMaxPowerId() + 1;
		Integer order = AdminMenuService.ORDER_INIT;
		/**
		 * 如果选择了置顶
		 */
		if (orderId == 1) {
			order = ams.findMaxOrderId(parentId) + 1;
		}
		/**
		 * 如果选择了底置
		 */
		if (orderId == -1) {
			order = ams.findMinOrderId(parentId) - 1;
		}
		/**
		 * 如果选择不操作
		 */
		if (orderId == 0) {
			order = AdminMenuService.ORDER_INIT;
		}
		AdminMenu am = new AdminMenu(menuName, menuPath, parentId, 
				powerId, order, ifLeaf, "");
		ErrorCode error = new ErrorCode();
		ams.addAdminMenuService(am, error);
		if (error.getErrorCode() != SystemCode.SYS_SUCESS) {
			super.setErroCodeNum(error.getErrorCode());
			super.setErroDescrip(this.getText(error.getErrorDisc()));
		} else {
			return "result";
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
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
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

}
