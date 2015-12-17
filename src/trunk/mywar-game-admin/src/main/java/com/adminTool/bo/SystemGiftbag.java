package com.adminTool.bo;

import java.util.Date;

/**
 * 系统礼包
 * 
 * @author yezp
 */
public class SystemGiftbag implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int giftbagId;
	private int type;
	private String name;
	private int codeNum;
	private String tablePrefix;
	private String description;
	private int imgId;
	private Date createdTime;
	private Date updatedTime;

	public SystemGiftbag() {
	}

	public SystemGiftbag(int giftbagId, int type, String name, int codeNum,
			String tablePrefix, String description, int imgId,
			Date createdTime, Date updatedTime) {
		super();
		this.giftbagId = giftbagId;
		this.type = type;
		this.name = name;
		this.codeNum = codeNum;
		this.tablePrefix = tablePrefix;
		this.description = description;
		this.imgId = imgId;
		this.createdTime = createdTime;
		this.updatedTime = updatedTime;
	}

	public int getGiftbagId() {
		return this.giftbagId;
	}

	public void setGiftbagId(int giftbagId) {
		this.giftbagId = giftbagId;
	}

	public int getType() {
		return this.type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getImgId() {
		return this.imgId;
	}

	public void setImgId(int imgId) {
		this.imgId = imgId;
	}

	public int getCodeNum() {
		return codeNum;
	}

	public void setCodeNum(int codeNum) {
		this.codeNum = codeNum;
	}

	public String getTablePrefix() {
		return tablePrefix;
	}

	public void setTablePrefix(String tablePrefix) {
		this.tablePrefix = tablePrefix;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public Date getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}

}
