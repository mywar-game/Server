package com.example.bo;

import java.util.Date;

/**
 * TArena entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class TArena implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer arenaId;
	private String name;
	private Integer playerId;
	private Integer level;
	private Integer mode;
	private Integer state;
	private Date createTime;

	// Constructors

	/** default constructor */
	public TArena() {
	}

	/** full constructor */
	public TArena(String name, Integer playerId, Integer level, Integer mode,
			Integer state, Date createTime) {
		this.name = name;
		this.playerId = playerId;
		this.level = level;
		this.mode = mode;
		this.state = state;
		this.createTime = createTime;
	}

	// Property accessors

	public Integer getArenaId() {
		return this.arenaId;
	}

	public void setArenaId(Integer arenaId) {
		this.arenaId = arenaId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPlayerId() {
		return this.playerId;
	}

	public void setPlayerId(Integer playerId) {
		this.playerId = playerId;
	}

	public Integer getLevel() {
		return this.level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Integer getMode() {
		return this.mode;
	}

	public void setMode(Integer mode) {
		this.mode = mode;
	}

	public Integer getState() {
		return this.state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}