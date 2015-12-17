package com.dataconfig.bo;

import java.io.Serializable;

/**
 * 神器类
 * 
 * @author yezp
 */
public class SystemArtifact implements Serializable {

	private Integer artifactId;
	/**
	 * 神器模型ID
	 */
	private Integer artifactModelId;
	/**
	 * 神器类型(1 该国家下的所有武将都能穿，2 专属某个武将才能穿戴)
	 */
	private Integer artifactWearType;
	/**
	 * 专属类型穿戴武将id
	 */
	private Integer wearSystemHeroId;
	/**
	 * 技能
	 */
	private Integer artifactSkillId1;
	private Integer artifactSkillId2;
	private Integer artifactSkillId3;
	private Integer artifactSkillId4;
	/**
	 * 图像id
	 */
	private Integer imgId;
	/**
	 * 国家
	 */
	private Integer countryId;
	/**
	 * 品质（1白、2绿、3蓝、4紫、5橙、6红）
	 */
	private Integer quality;
	/**
	 * 出售价格
	 */
	private Integer price;
	/**
	 * 名称
	 */
	private String name;

	public Integer getArtifactId() {
		return artifactId;
	}

	public void setArtifactId(Integer artifactId) {
		this.artifactId = artifactId;
	}

	public Integer getArtifactModelId() {
		return artifactModelId;
	}

	public void setArtifactModelId(Integer artifactModelId) {
		this.artifactModelId = artifactModelId;
	}

	public Integer getArtifactWearType() {
		return artifactWearType;
	}

	public void setArtifactWearType(Integer artifactWearType) {
		this.artifactWearType = artifactWearType;
	}

	public Integer getWearSystemHeroId() {
		return wearSystemHeroId;
	}

	public void setWearSystemHeroId(Integer wearSystemHeroId) {
		this.wearSystemHeroId = wearSystemHeroId;
	}

	public Integer getArtifactSkillId1() {
		return artifactSkillId1;
	}

	public void setArtifactSkillId1(Integer artifactSkillId1) {
		this.artifactSkillId1 = artifactSkillId1;
	}

	public Integer getArtifactSkillId2() {
		return artifactSkillId2;
	}

	public void setArtifactSkillId2(Integer artifactSkillId2) {
		this.artifactSkillId2 = artifactSkillId2;
	}

	public Integer getArtifactSkillId3() {
		return artifactSkillId3;
	}

	public void setArtifactSkillId3(Integer artifactSkillId3) {
		this.artifactSkillId3 = artifactSkillId3;
	}

	public Integer getArtifactSkillId4() {
		return artifactSkillId4;
	}

	public void setArtifactSkillId4(Integer artifactSkillId4) {
		this.artifactSkillId4 = artifactSkillId4;
	}

	public Integer getImgId() {
		return imgId;
	}

	public void setImgId(Integer imgId) {
		this.imgId = imgId;
	}

	public Integer getCountryId() {
		return countryId;
	}

	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}

	public Integer getQuality() {
		return quality;
	}

	public void setQuality(Integer quality) {
		this.quality = quality;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
