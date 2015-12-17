package com.dataconfig.bo;

/**
 * PPetUnlock entity. @author MyEclipse Persistence Tools
 */

public class PetUnlock implements java.io.Serializable {

	// Fields

	private Integer petUnlockId;
	private Integer petStoreUnlockLevel;
	private Integer petTechnologyUnlockLevel;
	private String petIds;
	private Integer petNum;
	private String petUnlockGolden;
	private Integer maxLookPetUnlockId;
	private Integer addAttribute;

	// Constructors

	/** default constructor */
	public PetUnlock() {
	}

	/** full constructor */
	public PetUnlock(Integer petStoreUnlockLevel,
			Integer petTechnologyUnlockLevel, String petIds, Integer petNum,
			String petUnlockGolden, Integer maxLookPetUnlockId,
			Integer addAttribute) {
		this.petStoreUnlockLevel = petStoreUnlockLevel;
		this.petTechnologyUnlockLevel = petTechnologyUnlockLevel;
		this.petIds = petIds;
		this.petNum = petNum;
		this.petUnlockGolden = petUnlockGolden;
		this.maxLookPetUnlockId = maxLookPetUnlockId;
		this.addAttribute = addAttribute;
	}

	// Property accessors

	public Integer getPetUnlockId() {
		return this.petUnlockId;
	}

	public void setPetUnlockId(Integer petUnlockId) {
		this.petUnlockId = petUnlockId;
	}

	public Integer getPetStoreUnlockLevel() {
		return this.petStoreUnlockLevel;
	}

	public void setPetStoreUnlockLevel(Integer petStoreUnlockLevel) {
		this.petStoreUnlockLevel = petStoreUnlockLevel;
	}

	public Integer getPetTechnologyUnlockLevel() {
		return this.petTechnologyUnlockLevel;
	}

	public void setPetTechnologyUnlockLevel(Integer petTechnologyUnlockLevel) {
		this.petTechnologyUnlockLevel = petTechnologyUnlockLevel;
	}

	public String getPetIds() {
		return this.petIds;
	}

	public void setPetIds(String petIds) {
		this.petIds = petIds;
	}

	public Integer getPetNum() {
		return this.petNum;
	}

	public void setPetNum(Integer petNum) {
		this.petNum = petNum;
	}

	public String getPetUnlockGolden() {
		return this.petUnlockGolden;
	}

	public void setPetUnlockGolden(String petUnlockGolden) {
		this.petUnlockGolden = petUnlockGolden;
	}

	public Integer getMaxLookPetUnlockId() {
		return this.maxLookPetUnlockId;
	}

	public void setMaxLookPetUnlockId(Integer maxLookPetUnlockId) {
		this.maxLookPetUnlockId = maxLookPetUnlockId;
	}

	public Integer getAddAttribute() {
		return this.addAttribute;
	}

	public void setAddAttribute(Integer addAttribute) {
		this.addAttribute = addAttribute;
	}

}