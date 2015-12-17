package com.adminTool.action;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.adminTool.bo.AdminChangeConstantLog;
import com.adminTool.bo.Partner;
import com.adminTool.bo.SystemGoldSet;
import com.adminTool.service.AdminChangeConstantLogService;
import com.adminTool.service.PartnerService;
import com.adminTool.service.SystemGoldSetService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;

/**
 * 充值配置
 * @author Administrator
 *
 */
public class SystemGoldSetList extends ALDAdminActionSupport {

	private static final long serialVersionUID = -8054429659411753851L;
	private List<Partner> partnerList;
	private Boolean isCommit = false;
	private List<SystemGoldSet> systemGoldSetList;
	private String partnerIdArr[];
	private Integer systemGoldSetIdArr[];
	private Integer moneyArr[];
	private Integer goldArr[];
	private Integer typeArr[];
	private String titleArr[];
	private String descriptionArr[];
	private Timestamp startTimeArr[];
	private Timestamp endTimeArr[];
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public String execute() {
		SystemGoldSetService systemGoldSetService = ServiceCacheFactory.getServiceCache().getService(SystemGoldSetService.class);
		PartnerService partnerService = ServiceCacheFactory.getServiceCache().getService(PartnerService.class);
		partnerList = partnerService.findPartnerList();
		
		if (isCommit == false) {						
		} else {
			if (moneyArr != null) {
				systemGoldSetService.deleteAll();
				for (int i = 0; i < moneyArr.length; i++) {
					boolean mark = false;
					SystemGoldSet goldSet = new SystemGoldSet();
					if (moneyArr[i] != null && !moneyArr[i].equals("")) {
						goldSet.setMoney(moneyArr[i]);
						mark = true;
					} else {
						mark = false;
					}
					
					if (mark && partnerIdArr[i] != null && !partnerIdArr[i].equals("")) {
						goldSet.setPartnerId(partnerIdArr[i]);
						mark = true;
					} else {
						mark = false;
					}
					
					if (mark && typeArr[i] != null && !typeArr[i].equals("")) {
						goldSet.setType(typeArr[i]);
						mark = true;
					} else {
						mark = false;
					}
					
					if (mark && goldArr[i] != null && !goldArr[i].equals("")) {
						goldSet.setGold(goldArr[i]);
						mark = true;
					} else {
						mark = false;
					}
					
					if (mark && titleArr[i] != null && !titleArr[i].equals("")) {
						goldSet.setTitle(titleArr[i]);
						mark = true;
					} else {
						mark = false;
					}
					
					if (mark && descriptionArr[i] != null && !descriptionArr[i].equals("")) {
						goldSet.setDescription(descriptionArr[i]);
						mark = true;
					} else {
						mark = false;
					}
					
					if (mark && startTimeArr[i] != null && !startTimeArr[i].equals("")) {
						goldSet.setStartTime(startTimeArr[i]);
						mark = true;
					} else {
						mark = false;
					}
					
					if (mark && endTimeArr[i] != null && !endTimeArr[i].equals("")) {
						goldSet.setEndTime(endTimeArr[i]);
						mark = true;
					} else {
						mark = false;
					}
					
					SystemGoldSet lastGoldSet = systemGoldSetService.findLast();
					if (lastGoldSet == null) {
						goldSet.setSystemGoldSetId(1);
					} else {
						goldSet.setSystemGoldSetId(lastGoldSet.getSystemGoldSetId() + 1);
					}
					
					if (mark) {
						systemGoldSetService.save(goldSet);
						//记录日志
						AdminChangeConstantLog adminChangeConstantLog = new AdminChangeConstantLog();
						adminChangeConstantLog.setSysNum(Integer.valueOf(super.getAdminUser().getExp1()));
						adminChangeConstantLog.setAdminName(super.getAdminUser().getAdminName());
						adminChangeConstantLog.setChangeTime(new Timestamp(System.currentTimeMillis()));
						adminChangeConstantLog.setConstantName("SystemGoldSet");
						adminChangeConstantLog.setChangeType(3); 
						adminChangeConstantLog.setChangeDetail("修改 " + super.getText("额外赠送元宝:" + goldSet.getMoney() + " 赠送钻石:" + goldSet.getGold() + " 类型(是否首冲 0:否, 1:是):" + goldSet.getType() + " 开始时间:" + goldSet.getStartTime() + " 结束时间:" + goldSet.getEndTime() + " 充值管理:" + goldSet.getTitle() + " 描述:" + goldSet.getDescription() + " 记录<br/>"));
						AdminChangeConstantLogService adminChangeConstantLogService = ServiceCacheFactory.getServiceCache().getService(AdminChangeConstantLogService.class);
						adminChangeConstantLogService.saveOne(adminChangeConstantLog);
					}
				}
			}
		}
		systemGoldSetList = systemGoldSetService.getAll();
		Collections.sort(systemGoldSetList, new Comparator() {
			@Override
			public int compare(Object o1, Object o2) {
				return ((SystemGoldSet)o1).getSystemGoldSetId() - ((SystemGoldSet)o2).getSystemGoldSetId();
			}
		});
		return SUCCESS;
	}

	public List<Partner> getPartnerList() {
		return partnerList;
	}

	public void setPartnerList(List<Partner> partnerList) {
		this.partnerList = partnerList;
	}

	public Integer[] getSystemGoldSetListArr() {
		return systemGoldSetIdArr;
	}

	public void setSystemGoldSetListArr(Integer[] systemGoldSetListArr) {
		this.systemGoldSetIdArr = systemGoldSetListArr;
	}

	public Integer[] getMoneyArr() {
		return moneyArr;
	}

	public void setMoneyArr(Integer[] moneyArr) {
		this.moneyArr = moneyArr;
	}

	public Integer[] getGoldArr() {
		return goldArr;
	}

	public void setGoldArr(Integer[] goldArr) {
		this.goldArr = goldArr;
	}

	public Integer[] getTypeArr() {
		return typeArr;
	}

	public void setTypeArr(Integer[] typeArr) {
		this.typeArr = typeArr;
	}

	public String[] getPartnerIdArr() {
		return partnerIdArr;
	}

	public void setPartnerIdArr(String[] partnerIdArr) {
		this.partnerIdArr = partnerIdArr;
	}

	public String[] getTitleArr() {
		return titleArr;
	}

	public void setTitleArr(String[] titleArr) {
		this.titleArr = titleArr;
	}

	public String[] getDescriptionArr() {
		return descriptionArr;
	}

	public void setDescriptionArr(String[] descriptionArr) {
		this.descriptionArr = descriptionArr;
	}

	public Boolean getIsCommit() {
		return isCommit;
	}

	public void setIsCommit(Boolean isCommit) {
		this.isCommit = isCommit;
	}

	public List<SystemGoldSet> getSystemGoldSetList() {
		return systemGoldSetList;
	}

	public void setSystemGoldSetList(List<SystemGoldSet> systemGoldSetList) {
		this.systemGoldSetList = systemGoldSetList;
	}

	public Integer[] getSystemGoldSetIdArr() {
		return systemGoldSetIdArr;
	}

	public void setSystemGoldSetIdArr(Integer[] systemGoldSetIdArr) {
		this.systemGoldSetIdArr = systemGoldSetIdArr;
	}

	public Timestamp[] getStartTimeArr() {
		return startTimeArr;
	}

	public void setStartTimeArr(Timestamp[] startTimeArr) {
		this.startTimeArr = startTimeArr;
	}

	public Timestamp[] getEndTimeArr() {
		return endTimeArr;
	}

	public void setEndTimeArr(Timestamp[] endTimeArr) {
		this.endTimeArr = endTimeArr;
	}

}
