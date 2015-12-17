package com.admin.bo;

/**
 * AdminMenu entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class AdminMenu implements java.io.Serializable {

	// Fields

	private Integer id;
	private String menuName;
	private String menuPath;
	private Integer parentId;
	private Integer powerId;
	private Integer orderId;
	private String ifLeaf;
	private String description;

	// Constructors

	/** default constructor */
	public AdminMenu() {
	}

	/** full constructor */
	public AdminMenu(String menuName, String menuPath, Integer parentId,
			Integer powerId, Integer orderId, String ifLeaf, String description) {
		this.menuName = menuName;
		this.menuPath = menuPath;
		this.parentId = parentId;
		this.powerId = powerId;
		this.orderId = orderId;
		this.ifLeaf = ifLeaf;
		this.description = description;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMenuName() {
		return this.menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getMenuPath() {
		return this.menuPath;
	}

	public void setMenuPath(String menuPath) {
		this.menuPath = menuPath;
	}

	public Integer getParentId() {
		return this.parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public Integer getPowerId() {
		return this.powerId;
	}

	public void setPowerId(Integer powerId) {
		this.powerId = powerId;
	}

	public Integer getOrderId() {
		return this.orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public String getIfLeaf() {
		return this.ifLeaf;
	}

	public void setIfLeaf(String ifLeaf) {
		this.ifLeaf = ifLeaf;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}