package com.stats.bo;

import java.util.Date;

// 分渠道 留存统计
public class UserRemainByChannelStats implements java.io.Serializable {

	private static final long serialVersionUID = 1341246888434249991L;
	// Fields
	private Integer id;
	private Integer sysNum;
	private Integer userTotal;
	private Integer newReg;
	private Integer dayActive;
	private Float oneDayRemain;
	private Float twoDayRemain;
	private Float threeDayRemain;
	private Float fourDayRemain;
	private Float fiveDayRemain;
	private Float sixDayRemain;
	private Float sevenDayRemain;
	// add
		private Float eightDayRemain;
		private Float neightDayRemain;
		private Float tenDayRemain;
		private Float elevenDayRemain;
		private Float twelfDayRemain;
		private Float thirteenDayRemain;
		private Float fourteenDayRemain;
	// end add
	private Float fifteenDayRemain;
	// add
		private Float sixteenDayRemain;
		private Float seventeenDayRemain;
		private Float eightteenDayRemain;
		private Float neightteenDayRemain;
		private Float twentieDayRemain;
		private Float twentieoneDayRemain;
		private Float twentietwoDayRemain;
		private Float twentiethreeDayRemain;
		private Float twentiefourDayRemain;
		private Float twentiefiveDayRemain;
		private Float twentiesixDayRemain;
		private Float twentiesevenDayRemain;
		private Float twentieeightDayRemain;
		private Float twentienightDayRemain;
	// end add
	private Float thirtyDayRemain;
	private Date time;
	private Integer oneDayRemainNum;
	private Integer oneDayRegNum;
	
	private Integer twoDayRemainNum;
	private Integer twoDayRegNum;
	
	private Integer threeDayRemainNum;
	private Integer threeDayRegNum;
	
	private Integer fourDayRemainNum;
	private Integer fourDayRegNum;
	
	private Integer fiveDayRemainNum;
	private Integer fiveDayRegNum;
	
	private Integer sixDayRemainNum;
	private Integer sixDayRegNum;
	
	private Integer sevenDayRemainNum;
	private Integer sevenDayRegNum;
	
	// add
		private Integer eightDayRemainNum;
		private Integer eightDayRegNum;
		
		private Integer neightDayRemainNum;
		private Integer neightDayRegNum;
		
		private Integer tenDayRemainNum;
		private Integer tenDayRegNum;
		
		private Integer elevenDayRemainNum;
		private Integer elevenDayRegNum;
		
		private Integer twelfDayRemainNum;
		private Integer twelfDayRegNum;
		
		private Integer thirteenDayRemainNum;
		private Integer thirteenDayRegNum;
		
		private Integer fourteenDayRemainNum;
		private Integer fourteenDayRegNum;
		
	// end add
	
	private Integer fifteenDayRemainNum;
	private Integer fifteenDayRegNum;
	
	// add
	
		private Integer sixteenDayRemainNum;
		private Integer sixteenDayRegNum;
		
		private Integer seventeenDayRemainNum;
		private Integer seventeenDayRegNum;
		
		private Integer eightteenDayRemainNum;
		private Integer eightteenDayRegNum;
		
		private Integer neightteenDayRemainNum;
		private Integer neightteenDayRegNum;
		
		private Integer twentieDayRemainNum;
		private Integer twentieDayRegNum;
		
		private Integer twentieoneDayRemainNum;
		private Integer twentieoneDayRegNum;
		
		private Integer twentietwoDayRemainNum;
		private Integer twentietwoDayRegNum;
		
		private Integer twentiethreeDayRemainNum;
		private Integer twentiethreeDayRegNum;
		
		private Integer twentiefourDayRemainNum;
		private Integer twentiefourDayRegNum;
		
		private Integer twentiefiveDayRemainNum;
		private Integer twentiefiveDayRegNum;
		
		private Integer twentiesixDayRemainNum;
		private Integer twentiesixDayRegNum;
		
		private Integer twentiesevenDayRemainNum;
		private Integer twentiesevenDayRegNum;
		
		private Integer twentieeightDayRemainNum;
		private Integer twentieeightDayRegNum;
		
		private Integer twentieneightDayRemainNum;
		private Integer twentieneightDayRegNum;
		
	// end add
		
	
	

	private Integer thirtyDayRemainNum;
	private Integer thirtyDayRegNum;
	
	// 创建角色数量
	private Integer newCreated;
	
	// 渠道
	private String channel;
	
	private String channelName;
	
	// Constructors

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public UserRemainByChannelStats(Integer id, Integer sysNum,
			Integer userTotal, Integer newReg, Integer dayActive,
			Float oneDayRemain, Float twoDayRemain, Float threeDayRemain,
			Float fourDayRemain, Float fiveDayRemain, Float sixDayRemain,
			Float sevenDayRemain, Float fifteenDayRemain,
			Float thirtyDayRemain, Date time, Integer oneDayRemainNum,
			Integer oneDayRegNum, Integer twoDayRemainNum,
			Integer twoDayRegNum, Integer threeDayRemainNum,
			Integer threeDayRegNum, Integer fourDayRemainNum,
			Integer fourDayRegNum, Integer fiveDayRemainNum,
			Integer fiveDayRegNum, Integer sixDayRemainNum,
			Integer sixDayRegNum, Integer sevenDayRemainNum,
			Integer sevenDayRegNum, Integer fifteenDayRemainNum,
			Integer fifteenDayRegNum, Integer thirtyDayRemainNum,
			Integer thirtyDayRegNum, Integer newCreated,
			String channel, String channelName) {
		super();
		this.id = id;
		this.sysNum = sysNum;
		this.userTotal = userTotal;
		this.newReg = newReg;
		this.dayActive = dayActive;
		this.oneDayRemain = oneDayRemain;
		this.twoDayRemain = twoDayRemain;
		this.threeDayRemain = threeDayRemain;
		this.fourDayRemain = fourDayRemain;
		this.fiveDayRemain = fiveDayRemain;
		this.sixDayRemain = sixDayRemain;
		this.sevenDayRemain = sevenDayRemain;
		this.fifteenDayRemain = fifteenDayRemain;
		this.thirtyDayRemain = thirtyDayRemain;
		this.time = time;
		this.oneDayRemainNum = oneDayRemainNum;
		this.oneDayRegNum = oneDayRegNum;
		this.twoDayRemainNum = twoDayRemainNum;
		this.twoDayRegNum = twoDayRegNum;
		this.threeDayRemainNum = threeDayRemainNum;
		this.threeDayRegNum = threeDayRegNum;
		this.fourDayRemainNum = fourDayRemainNum;
		this.fourDayRegNum = fourDayRegNum;
		this.fiveDayRemainNum = fiveDayRemainNum;
		this.fiveDayRegNum = fiveDayRegNum;
		this.sixDayRemainNum = sixDayRemainNum;
		this.sixDayRegNum = sixDayRegNum;
		this.sevenDayRemainNum = sevenDayRemainNum;
		this.sevenDayRegNum = sevenDayRegNum;
		this.fifteenDayRemainNum = fifteenDayRemainNum;
		this.fifteenDayRegNum = fifteenDayRegNum;
		this.thirtyDayRemainNum = thirtyDayRemainNum;
		this.thirtyDayRegNum = thirtyDayRegNum;
		this.newCreated = newCreated;
		this.channel = channel;
		this.channelName = channelName;
	}

	public UserRemainByChannelStats() {
		super();
	}

	public Integer getNewCreated() {
		return newCreated;
	}

	public void setNewCreated(Integer newCreated) {
		this.newCreated = newCreated;
	}

	public Integer getOneDayRemainNum() {
		return oneDayRemainNum;
	}

	public void setOneDayRemainNum(Integer oneDayRemainNum) {
		this.oneDayRemainNum = oneDayRemainNum;
	}

	public Integer getOneDayRegNum() {
		return oneDayRegNum;
	}

	public void setOneDayRegNum(Integer oneDayRegNum) {
		this.oneDayRegNum = oneDayRegNum;
	}

	public Integer getTwoDayRemainNum() {
		return twoDayRemainNum;
	}

	public void setTwoDayRemainNum(Integer twoDayRemainNum) {
		this.twoDayRemainNum = twoDayRemainNum;
	}

	public Integer getTwoDayRegNum() {
		return twoDayRegNum;
	}

	public void setTwoDayRegNum(Integer twoDayRegNum) {
		this.twoDayRegNum = twoDayRegNum;
	}

	public Integer getThreeDayRemainNum() {
		return threeDayRemainNum;
	}

	public void setThreeDayRemainNum(Integer threeDayRemainNum) {
		this.threeDayRemainNum = threeDayRemainNum;
	}

	public Integer getThreeDayRegNum() {
		return threeDayRegNum;
	}

	public void setThreeDayRegNum(Integer threeDayRegNum) {
		this.threeDayRegNum = threeDayRegNum;
	}

	public Integer getFourDayRemainNum() {
		return fourDayRemainNum;
	}

	public void setFourDayRemainNum(Integer fourDayRemainNum) {
		this.fourDayRemainNum = fourDayRemainNum;
	}

	public Integer getFourDayRegNum() {
		return fourDayRegNum;
	}

	public void setFourDayRegNum(Integer fourDayRegNum) {
		this.fourDayRegNum = fourDayRegNum;
	}

	public Integer getFiveDayRemainNum() {
		return fiveDayRemainNum;
	}

	public void setFiveDayRemainNum(Integer fiveDayRemainNum) {
		this.fiveDayRemainNum = fiveDayRemainNum;
	}

	public Integer getFiveDayRegNum() {
		return fiveDayRegNum;
	}

	public void setFiveDayRegNum(Integer fiveDayRegNum) {
		this.fiveDayRegNum = fiveDayRegNum;
	}

	public Integer getSixDayRemainNum() {
		return sixDayRemainNum;
	}

	public void setSixDayRemainNum(Integer sixDayRemainNum) {
		this.sixDayRemainNum = sixDayRemainNum;
	}

	public Integer getSixDayRegNum() {
		return sixDayRegNum;
	}

	public void setSixDayRegNum(Integer sixDayRegNum) {
		this.sixDayRegNum = sixDayRegNum;
	}

	public Integer getSevenDayRemainNum() {
		return sevenDayRemainNum;
	}

	public void setSevenDayRemainNum(Integer sevenDayRemainNum) {
		this.sevenDayRemainNum = sevenDayRemainNum;
	}

	public Integer getSevenDayRegNum() {
		return sevenDayRegNum;
	}

	public void setSevenDayRegNum(Integer sevenDayRegNum) {
		this.sevenDayRegNum = sevenDayRegNum;
	}

	public Integer getFifteenDayRemainNum() {
		return fifteenDayRemainNum;
	}

	public void setFifteenDayRemainNum(Integer fifteenDayRemainNum) {
		this.fifteenDayRemainNum = fifteenDayRemainNum;
	}

	public Integer getFifteenDayRegNum() {
		return fifteenDayRegNum;
	}

	public void setFifteenDayRegNum(Integer fifteenDayRegNum) {
		this.fifteenDayRegNum = fifteenDayRegNum;
	}

	public Integer getThirtyDayRemainNum() {
		return thirtyDayRemainNum;
	}

	public void setThirtyDayRemainNum(Integer thirtyDayRemainNum) {
		this.thirtyDayRemainNum = thirtyDayRemainNum;
	}

	public Integer getThirtyDayRegNum() {
		return thirtyDayRegNum;
	}

	public void setThirtyDayRegNum(Integer thirtyDayRegNum) {
		this.thirtyDayRegNum = thirtyDayRegNum;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSysNum() {
		return this.sysNum;
	}

	public void setSysNum(Integer sysNum) {
		this.sysNum = sysNum;
	}

	public Integer getUserTotal() {
		return this.userTotal;
	}

	public void setUserTotal(Integer userTotal) {
		this.userTotal = userTotal;
	}

	public Integer getNewReg() {
		return this.newReg;
	}

	public void setNewReg(Integer newReg) {
		this.newReg = newReg;
	}

	public Integer getDayActive() {
		return this.dayActive;
	}

	public void setDayActive(Integer dayActive) {
		this.dayActive = dayActive;
	}

	public Float getOneDayRemain() {
		return this.oneDayRemain;
	}

	public void setOneDayRemain(Float oneDayRemain) {
		this.oneDayRemain = oneDayRemain;
	}

	public Float getTwoDayRemain() {
		return this.twoDayRemain;
	}

	public void setTwoDayRemain(Float twoDayRemain) {
		this.twoDayRemain = twoDayRemain;
	}

	public Float getThreeDayRemain() {
		return this.threeDayRemain;
	}

	public void setThreeDayRemain(Float threeDayRemain) {
		this.threeDayRemain = threeDayRemain;
	}

	public Float getFourDayRemain() {
		return this.fourDayRemain;
	}

	public void setFourDayRemain(Float fourDayRemain) {
		this.fourDayRemain = fourDayRemain;
	}

	public Float getFiveDayRemain() {
		return this.fiveDayRemain;
	}

	public void setFiveDayRemain(Float fiveDayRemain) {
		this.fiveDayRemain = fiveDayRemain;
	}

	public Float getSixDayRemain() {
		return this.sixDayRemain;
	}

	public void setSixDayRemain(Float sixDayRemain) {
		this.sixDayRemain = sixDayRemain;
	}

	public Float getSevenDayRemain() {
		return this.sevenDayRemain;
	}

	public void setSevenDayRemain(Float sevenDayRemain) {
		this.sevenDayRemain = sevenDayRemain;
	}

	public Float getFifteenDayRemain() {
		return this.fifteenDayRemain;
	}

	public void setFifteenDayRemain(Float fifteenDayRemain) {
		this.fifteenDayRemain = fifteenDayRemain;
	}

	public Float getThirtyDayRemain() {
		return this.thirtyDayRemain;
	}

	public void setThirtyDayRemain(Float thirtyDayRemain) {
		this.thirtyDayRemain = thirtyDayRemain;
	}

	public Date getTime() {
		return this.time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}
	
	
	public Float getEightDayRemain() {
		return eightDayRemain;
	}

	public void setEightDayRemain(Float eightDayRemain) {
		this.eightDayRemain = eightDayRemain;
	}

	public Float getNeightDayRemain() {
		return neightDayRemain;
	}

	public void setNeightDayRemain(Float neightDayRemain) {
		this.neightDayRemain = neightDayRemain;
	}

	public Float getTenDayRemain() {
		return tenDayRemain;
	}

	public void setTenDayRemain(Float tenDayRemain) {
		this.tenDayRemain = tenDayRemain;
	}

	public Float getElevenDayRemain() {
		return elevenDayRemain;
	}

	public void setElevenDayRemain(Float elevenDayRemain) {
		this.elevenDayRemain = elevenDayRemain;
	}

	public Float getTwelfDayRemain() {
		return twelfDayRemain;
	}

	public void setTwelfDayRemain(Float twelfDayRemain) {
		this.twelfDayRemain = twelfDayRemain;
	}

	public Float getThirteenDayRemain() {
		return thirteenDayRemain;
	}

	public void setThirteenDayRemain(Float thirteenDayRemain) {
		this.thirteenDayRemain = thirteenDayRemain;
	}

	public Float getFourteenDayRemain() {
		return fourteenDayRemain;
	}

	public void setFourteenDayRemain(Float fourteenDayRemain) {
		this.fourteenDayRemain = fourteenDayRemain;
	}

	public Float getSixteenDayRemain() {
		return sixteenDayRemain;
	}

	public void setSixteenDayRemain(Float sixteenDayRemain) {
		this.sixteenDayRemain = sixteenDayRemain;
	}

	public Float getSeventeenDayRemain() {
		return seventeenDayRemain;
	}

	public void setSeventeenDayRemain(Float seventeenDayRemain) {
		this.seventeenDayRemain = seventeenDayRemain;
	}

	public Float getEightteenDayRemain() {
		return eightteenDayRemain;
	}

	public void setEightteenDayRemain(Float eightteenDayRemain) {
		this.eightteenDayRemain = eightteenDayRemain;
	}

	public Float getNeightteenDayRemain() {
		return neightteenDayRemain;
	}

	public void setNeightteenDayRemain(Float neightteenDayRemain) {
		this.neightteenDayRemain = neightteenDayRemain;
	}

	public Float getTwentieDayRemain() {
		return twentieDayRemain;
	}

	public void setTwentieDayRemain(Float twentieDayRemain) {
		this.twentieDayRemain = twentieDayRemain;
	}

	public Float getTwentieoneDayRemain() {
		return twentieoneDayRemain;
	}

	public void setTwentieoneDayRemain(Float twentieoneDayRemain) {
		this.twentieoneDayRemain = twentieoneDayRemain;
	}

	public Float getTwentietwoDayRemain() {
		return twentietwoDayRemain;
	}

	public void setTwentietwoDayRemain(Float twentietwoDayRemain) {
		this.twentietwoDayRemain = twentietwoDayRemain;
	}

	public Float getTwentiethreeDayRemain() {
		return twentiethreeDayRemain;
	}

	public void setTwentiethreeDayRemain(Float twentiethreeDayRemain) {
		this.twentiethreeDayRemain = twentiethreeDayRemain;
	}

	public Float getTwentiefourDayRemain() {
		return twentiefourDayRemain;
	}

	public void setTwentiefourDayRemain(Float twentiefourDayRemain) {
		this.twentiefourDayRemain = twentiefourDayRemain;
	}

	public Float getTwentiefiveDayRemain() {
		return twentiefiveDayRemain;
	}

	public void setTwentiefiveDayRemain(Float twentiefiveDayRemain) {
		this.twentiefiveDayRemain = twentiefiveDayRemain;
	}

	public Float getTwentiesixDayRemain() {
		return twentiesixDayRemain;
	}

	public void setTwentiesixDayRemain(Float twentiesixDayRemain) {
		this.twentiesixDayRemain = twentiesixDayRemain;
	}

	public Float getTwentiesevenDayRemain() {
		return twentiesevenDayRemain;
	}

	public void setTwentiesevenDayRemain(Float twentiesevenDayRemain) {
		this.twentiesevenDayRemain = twentiesevenDayRemain;
	}

	public Float getTwentieeightDayRemain() {
		return twentieeightDayRemain;
	}

	public void setTwentieeightDayRemain(Float twentieeightDayRemain) {
		this.twentieeightDayRemain = twentieeightDayRemain;
	}

	public Float getTwentienightDayRemain() {
		return twentienightDayRemain;
	}

	public void setTwentienightDayRemain(Float twentienightDayRemain) {
		this.twentienightDayRemain = twentienightDayRemain;
	}

	public Integer getEightDayRemainNum() {
		return eightDayRemainNum;
	}

	public void setEightDayRemainNum(Integer eightDayRemainNum) {
		this.eightDayRemainNum = eightDayRemainNum;
	}

	public Integer getEightDayRegNum() {
		return eightDayRegNum;
	}

	public void setEightDayRegNum(Integer eightDayRegNum) {
		this.eightDayRegNum = eightDayRegNum;
	}

	public Integer getNeightDayRemainNum() {
		return neightDayRemainNum;
	}

	public void setNeightDayRemainNum(Integer neightDayRemainNum) {
		this.neightDayRemainNum = neightDayRemainNum;
	}

	public Integer getNeightDayRegNum() {
		return neightDayRegNum;
	}

	public void setNeightDayRegNum(Integer neightDayRegNum) {
		this.neightDayRegNum = neightDayRegNum;
	}

	public Integer getTenDayRemainNum() {
		return tenDayRemainNum;
	}

	public void setTenDayRemainNum(Integer tenDayRemainNum) {
		this.tenDayRemainNum = tenDayRemainNum;
	}

	public Integer getTenDayRegNum() {
		return tenDayRegNum;
	}

	public void setTenDayRegNum(Integer tenDayRegNum) {
		this.tenDayRegNum = tenDayRegNum;
	}

	public Integer getElevenDayRemainNum() {
		return elevenDayRemainNum;
	}

	public void setElevenDayRemainNum(Integer elevenDayRemainNum) {
		this.elevenDayRemainNum = elevenDayRemainNum;
	}

	public Integer getElevenDayRegNum() {
		return elevenDayRegNum;
	}

	public void setElevenDayRegNum(Integer elevenDayRegNum) {
		this.elevenDayRegNum = elevenDayRegNum;
	}

	public Integer getTwelfDayRemainNum() {
		return twelfDayRemainNum;
	}

	public void setTwelfDayRemainNum(Integer twelfDayRemainNum) {
		this.twelfDayRemainNum = twelfDayRemainNum;
	}

	public Integer getTwelfDayRegNum() {
		return twelfDayRegNum;
	}

	public void setTwelfDayRegNum(Integer twelfDayRegNum) {
		this.twelfDayRegNum = twelfDayRegNum;
	}

	public Integer getThirteenDayRemainNum() {
		return thirteenDayRemainNum;
	}

	public void setThirteenDayRemainNum(Integer thirteenDayRemainNum) {
		this.thirteenDayRemainNum = thirteenDayRemainNum;
	}

	public Integer getThirteenDayRegNum() {
		return thirteenDayRegNum;
	}

	public void setThirteenDayRegNum(Integer thirteenDayRegNum) {
		this.thirteenDayRegNum = thirteenDayRegNum;
	}

	public Integer getFourteenDayRemainNum() {
		return fourteenDayRemainNum;
	}

	public void setFourteenDayRemainNum(Integer fourteenDayRemainNum) {
		this.fourteenDayRemainNum = fourteenDayRemainNum;
	}

	public Integer getFourteenDayRegNum() {
		return fourteenDayRegNum;
	}

	public void setFourteenDayRegNum(Integer fourteenDayRegNum) {
		this.fourteenDayRegNum = fourteenDayRegNum;
	}

	public Integer getSixteenDayRemainNum() {
		return sixteenDayRemainNum;
	}

	public void setSixteenDayRemainNum(Integer sixteenDayRemainNum) {
		this.sixteenDayRemainNum = sixteenDayRemainNum;
	}

	public Integer getSixteenDayRegNum() {
		return sixteenDayRegNum;
	}

	public void setSixteenDayRegNum(Integer sixteenDayRegNum) {
		this.sixteenDayRegNum = sixteenDayRegNum;
	}

	public Integer getSeventeenDayRemainNum() {
		return seventeenDayRemainNum;
	}

	public void setSeventeenDayRemainNum(Integer seventeenDayRemainNum) {
		this.seventeenDayRemainNum = seventeenDayRemainNum;
	}

	public Integer getSeventeenDayRegNum() {
		return seventeenDayRegNum;
	}

	public void setSeventeenDayRegNum(Integer seventeenDayRegNum) {
		this.seventeenDayRegNum = seventeenDayRegNum;
	}

	public Integer getEightteenDayRemainNum() {
		return eightteenDayRemainNum;
	}

	public void setEightteenDayRemainNum(Integer eightteenDayRemainNum) {
		this.eightteenDayRemainNum = eightteenDayRemainNum;
	}

	public Integer getEightteenDayRegNum() {
		return eightteenDayRegNum;
	}

	public void setEightteenDayRegNum(Integer eightteenDayRegNum) {
		this.eightteenDayRegNum = eightteenDayRegNum;
	}

	public Integer getNeightteenDayRemainNum() {
		return neightteenDayRemainNum;
	}

	public void setNeightteenDayRemainNum(Integer neightteenDayRemainNum) {
		this.neightteenDayRemainNum = neightteenDayRemainNum;
	}

	public Integer getNeightteenDayRegNum() {
		return neightteenDayRegNum;
	}

	public void setNeightteenDayRegNum(Integer neightteenDayRegNum) {
		this.neightteenDayRegNum = neightteenDayRegNum;
	}

	public Integer getTwentieDayRemainNum() {
		return twentieDayRemainNum;
	}

	public void setTwentieDayRemainNum(Integer twentieDayRemainNum) {
		this.twentieDayRemainNum = twentieDayRemainNum;
	}

	public Integer getTwentieDayRegNum() {
		return twentieDayRegNum;
	}

	public void setTwentieDayRegNum(Integer twentieDayRegNum) {
		this.twentieDayRegNum = twentieDayRegNum;
	}

	public Integer getTwentieoneDayRemainNum() {
		return twentieoneDayRemainNum;
	}

	public void setTwentieoneDayRemainNum(Integer twentieoneDayRemainNum) {
		this.twentieoneDayRemainNum = twentieoneDayRemainNum;
	}

	public Integer getTwentieoneDayRegNum() {
		return twentieoneDayRegNum;
	}

	public void setTwentieoneDayRegNum(Integer twentieoneDayRegNum) {
		this.twentieoneDayRegNum = twentieoneDayRegNum;
	}

	public Integer getTwentietwoDayRemainNum() {
		return twentietwoDayRemainNum;
	}

	public void setTwentietwoDayRemainNum(Integer twentietwoDayRemainNum) {
		this.twentietwoDayRemainNum = twentietwoDayRemainNum;
	}

	public Integer getTwentietwoDayRegNum() {
		return twentietwoDayRegNum;
	}

	public void setTwentietwoDayRegNum(Integer twentietwoDayRegNum) {
		this.twentietwoDayRegNum = twentietwoDayRegNum;
	}

	public Integer getTwentiethreeDayRemainNum() {
		return twentiethreeDayRemainNum;
	}

	public void setTwentiethreeDayRemainNum(Integer twentiethreeDayRemainNum) {
		this.twentiethreeDayRemainNum = twentiethreeDayRemainNum;
	}

	public Integer getTwentiethreeDayRegNum() {
		return twentiethreeDayRegNum;
	}

	public void setTwentiethreeDayRegNum(Integer twentiethreeDayRegNum) {
		this.twentiethreeDayRegNum = twentiethreeDayRegNum;
	}

	public Integer getTwentiefourDayRemainNum() {
		return twentiefourDayRemainNum;
	}

	public void setTwentiefourDayRemainNum(Integer twentiefourDayRemainNum) {
		this.twentiefourDayRemainNum = twentiefourDayRemainNum;
	}

	public Integer getTwentiefourDayRegNum() {
		return twentiefourDayRegNum;
	}

	public void setTwentiefourDayRegNum(Integer twentiefourDayRegNum) {
		this.twentiefourDayRegNum = twentiefourDayRegNum;
	}

	public Integer getTwentiefiveDayRemainNum() {
		return twentiefiveDayRemainNum;
	}

	public void setTwentiefiveDayRemainNum(Integer twentiefiveDayRemainNum) {
		this.twentiefiveDayRemainNum = twentiefiveDayRemainNum;
	}

	public Integer getTwentiefiveDayRegNum() {
		return twentiefiveDayRegNum;
	}

	public void setTwentiefiveDayRegNum(Integer twentiefiveDayRegNum) {
		this.twentiefiveDayRegNum = twentiefiveDayRegNum;
	}

	public Integer getTwentiesixDayRemainNum() {
		return twentiesixDayRemainNum;
	}

	public void setTwentiesixDayRemainNum(Integer twentiesixDayRemainNum) {
		this.twentiesixDayRemainNum = twentiesixDayRemainNum;
	}

	public Integer getTwentiesixDayRegNum() {
		return twentiesixDayRegNum;
	}

	public void setTwentiesixDayRegNum(Integer twentiesixDayRegNum) {
		this.twentiesixDayRegNum = twentiesixDayRegNum;
	}

	public Integer getTwentiesevenDayRemainNum() {
		return twentiesevenDayRemainNum;
	}

	public void setTwentiesevenDayRemainNum(Integer twentiesevenDayRemainNum) {
		this.twentiesevenDayRemainNum = twentiesevenDayRemainNum;
	}

	public Integer getTwentiesevenDayRegNum() {
		return twentiesevenDayRegNum;
	}

	public void setTwentiesevenDayRegNum(Integer twentiesevenDayRegNum) {
		this.twentiesevenDayRegNum = twentiesevenDayRegNum;
	}

	public Integer getTwentieeightDayRemainNum() {
		return twentieeightDayRemainNum;
	}

	public void setTwentieeightDayRemainNum(Integer twentieeightDayRemainNum) {
		this.twentieeightDayRemainNum = twentieeightDayRemainNum;
	}

	public Integer getTwentieeightDayRegNum() {
		return twentieeightDayRegNum;
	}

	public void setTwentieeightDayRegNum(Integer twentieeightDayRegNum) {
		this.twentieeightDayRegNum = twentieeightDayRegNum;
	}

	public Integer getTwentieneightDayRemainNum() {
		return twentieneightDayRemainNum;
	}

	public void setTwentieneightDayRemainNum(Integer twentieneightDayRemainNum) {
		this.twentieneightDayRemainNum = twentieneightDayRemainNum;
	}

	public Integer getTwentieneightDayRegNum() {
		return twentieneightDayRegNum;
	}

	public void setTwentieneightDayRegNum(Integer twentieneightDayRegNum) {
		this.twentieneightDayRegNum = twentieneightDayRegNum;
	}
}
