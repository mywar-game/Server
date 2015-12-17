package com.admin.action;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;

import com.admin.bo.AdminRole;
import com.admin.bo.AdminUser;
import com.admin.service.AdminRoleService;
import com.admin.service.AdminUserService;
import com.admin.util.DTools;
import com.framework.common.ALDAdminActionSupport;
import com.framework.common.ErrorCode;
import com.framework.common.MD5;
import com.framework.common.SystemCode;
import com.framework.servicemanager.ServiceCacheFactory;
import com.framework.util.DateUtil;

public class AddAdminUser extends ALDAdminActionSupport {

	/**  */
	private static final long serialVersionUID = 1L;

	/**  */
	private  String newName;

	/**  */
	private  String newPwd;

	/**  */
	private  String newDesc;

	/**  */
	private  Integer newRoleId = 0;
	
	private String dueTime; 

	public String getDueTime() {
		return dueTime;
	}

	public void setDueTime(String dueTime) {
		this.dueTime = dueTime;
	}

	/**  */
	private  String isCommit = "F";
	
	private Map<Integer, String> roleIdAndNameMap;
	
	@Override
    public String execute() {
		AdminRoleService adminRoleService = ServiceCacheFactory.getServiceCache().getService(AdminRoleService.class);
		roleIdAndNameMap = adminRoleService.findIdAndNameMap();
    	if (isCommit.equals("T")) {
    		if (!check()) {
    			return INPUT;
    		}
    		String menuPowerString;
    		String powerString;
    		if (newRoleId == null || newRoleId == 0) {
    			menuPowerString = AdminUserService.INITAL_MENU_POWER;
    			powerString= AdminUserService.INITAL_POWER;
			} else {
				AdminRole adminRole = adminRoleService.findOneAdminRole(newRoleId);
				menuPowerString = adminRole.getMenuPowerString();
				powerString = adminRole.getPowerString();
			}

    		newPwd = MD5.md5Of32(newPwd);
    		AdminUser au = new AdminUser(newName, newPwd, powerString, menuPowerString, newRoleId, 0, newDesc, null, null);    		
    		au.setDueTime(new Timestamp(DateUtil.getDiffDate(new Date(), Integer.valueOf(dueTime)).getTime()));
    		au.setLoginFailTimes(0);
    		ErrorCode errorCode = new ErrorCode();
	    	AdminUserService aus = ServiceCacheFactory.getServiceCache().getService(AdminUserService.class);
	    	aus.addAdminUserService(au, errorCode);
	    	if (errorCode.getErrorCode() != SystemCode.SYS_SUCESS) {
	    		super.setErroDescrip(errorCode.getErrorDisc());
	    		return INPUT;
	    	} else {
	    		return SUCCESS;
	    	}
    	} else {
        	return INPUT;
    	}
    }
  
    public boolean check() {
    	if (DTools.isEmpty(newName)) {
    		super.setErroDescrip("用户名不能为空");
    		return false;
    	}
    	if (DTools.isEmpty(newPwd)) {
    		super.setErroDescrip("密码不能为空");
    		return false;
    	}
    	if (DTools.isEmpty(dueTime) || Integer.parseInt(dueTime)==0) {
    		super.setErroDescrip("密码有效期不能为空");
    		return false;
    	}
    	
    	return true;
    }
    
    public String getNewName() {
    	return newName;
    }
    
    public void setNewName(String newName) {
    	this.newName = newName;
    }
    
    public String getNewPwd() {
    	return newPwd;
    }
    
    public void setNewPwd(String newPwd) {
    	this.newPwd = newPwd;
    }
    
    public String getNewDesc() {
    	return newDesc;
    }
    
    public void setNewDesc(String newDesc) {
    	this.newDesc = newDesc;
    }

	public String getIsCommit() {
		return isCommit;
	}

	public void setIsCommit(String isCommit) {
		this.isCommit = isCommit;
	}

	public void setRoleIdAndNameMap(Map<Integer, String> roleIdAndNameMap) {
		this.roleIdAndNameMap = roleIdAndNameMap;
	}

	public Map<Integer, String> getRoleIdAndNameMap() {
		return roleIdAndNameMap;
	}

	public void setNewRoleId(Integer newRoleId) {
		this.newRoleId = newRoleId;
	}

	public Integer getNewRoleId() {
		return newRoleId;
	}
}
