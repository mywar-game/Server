package com.dataconfig.bo;

/**
 * SystemTool entity. @author MyEclipse Persistence Tools
 */

public class SystemTool implements java.io.Serializable {

	// Fields

	private Integer toolId;
	private Integer type;
	private Integer color;
	private String name;
	private String description;
	private Integer price;
	private String source;
	private String imgId;
	private String animation;

	// Constructors

	/** default constructor */
	public SystemTool() {
	}

	/** minimal constructor */
	public SystemTool(Integer type, String name, String description,
			Integer price, String imgId, String animation) {
		this.type = type;
		this.name = name;
		this.description = description;
		this.price = price;
		this.imgId = imgId;
		this.animation = animation;
	}

	/** full constructor */
	public SystemTool(Integer type, Integer color, String name,
			String description, Integer price, String source, String imgId,
			String animation) {
		this.type = type;
		this.color = color;
		this.name = name;
		this.description = description;
		this.price = price;
		this.source = source;
		this.imgId = imgId;
		this.animation = animation;
	}

	// Property accessors

	public Integer getToolId() {
		return this.toolId;
	}

	public void setToolId(Integer toolId) {
		this.toolId = toolId;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getColor() {
		return this.color;
	}

	public void setColor(Integer color) {
		this.color = color;
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

	public Integer getPrice() {
		return this.price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public String getSource() {
		return this.source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getImgId() {
		return this.imgId;
	}

	public void setImgId(String imgId) {
		this.imgId = imgId;
	}

	public String getAnimation() {
		return this.animation;
	}

	public void setAnimation(String animation) {
		this.animation = animation;
	}

}