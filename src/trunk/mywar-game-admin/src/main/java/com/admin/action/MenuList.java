package com.admin.action;

import java.util.ArrayList;
import java.util.List;

import com.admin.bo.AdminMenu;
import com.admin.service.AdminMenuService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;

public class MenuList extends ALDAdminActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<AdminMenu> menuList;
	private String treeStr = "";

	public String getTreeStr() {
		return treeStr;
	}

	public void setTreeStr(String treeStr) {
		this.treeStr = treeStr;
	}

	public String execute() {
		AdminMenuService adminMenuService = ServiceCacheFactory.getServiceCache().getService(AdminMenuService.class);
		menuList = adminMenuService.findAllList();
		makeTree(0);
		return SUCCESS;
	}

	public void makeTree(int parentId) {
		List<AdminMenu> childList = findChild(parentId);
		treeStr = treeStr + "<table>";
		for (AdminMenu tempg : childList) {
			treeStr = treeStr + "<tr>";
			treeStr = treeStr + "<td>--" + tempg.getMenuName();
			if (tempg.getIfLeaf().equals("T")) {
				// treeStr = treeStr + 
				// "<a href = javascript:if (confirm('将删除该节点，是否继续？'))location.replace('delchild?groupid = "+ tempg.getId()+ "');><font color = '#ff6600'>删除</font></a>|<a href = 'editchild?groupid = "+ tempg.getId()+ "'><font color = '#ff6600'>修改</font></a>|<a href = 'addproduct?parentid = "+ tempg.getId()+ "'><font color = 'red'>添加产品</font></a></td>";
				treeStr = treeStr
						+ "<a href=javascript:if(confirm('" + this.getText("menulistJsp.note", new String[]{tempg.getMenuName()}) + "'))location.replace('delmenuchild?id="
						+ tempg.getId()
						+ "');><font color='#ff6600'>" + this.getText("delete") + "</font></a>|<a href='editmenuchild?id="
						+ tempg.getId()
						+ "'><font color='#ff6600'>" + this.getText("update") + "</font></a></td>";
			} else {
				treeStr = treeStr
						+ "<a href='editmenuchild?id="
						+ tempg.getId()
						+ "'><font color='#ff6600'>" + this.getText("update") + "</font></a>|<a href='addmenuchild?parentId="
						+ tempg.getId()
						+ "'><font color='#ff6600'>" + this.getText("menulistJsp.addChileNode") + "</font></a></td>";
			}

			if (!tempg.getIfLeaf().equals("T")) {
				treeStr = treeStr + ("<td>");
				makeTree(tempg.getId());
				treeStr = treeStr + ("</td>");
			}
			treeStr = treeStr + ("</tr>");
		}
		treeStr = treeStr + ("</table>");
	}

	public List<AdminMenu> findChild(int parentId) {
		List<AdminMenu> tempList = new ArrayList<AdminMenu>();
		for (AdminMenu tempg : menuList) {
			if (tempg.getParentId() == parentId) {
				tempList.add(tempg);
			}
		}
		return tempList;
	}

	public List<AdminMenu> getMenuList() {
		return menuList;
	}

	public void setMenuList(List<AdminMenu> menuList) {
		this.menuList = menuList;
	}
}
