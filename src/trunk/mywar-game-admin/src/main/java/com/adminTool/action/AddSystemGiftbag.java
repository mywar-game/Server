package com.adminTool.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.adminTool.bo.SystemGiftbag;
import com.adminTool.constant.ActivityConstant;
import com.adminTool.constant.SystemGiftbagManager;
import com.adminTool.service.SystemGiftbagService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;
import com.opensymphony.xwork2.ModelDriven;
import com.system.constant.ServerConstant;

/**
 * 添加系统礼包
 * 
 * @author yezp
 */
public class AddSystemGiftbag extends ALDAdminActionSupport implements
		ModelDriven<SystemGiftbag> {

	private static final long serialVersionUID = 556129139558371870L;
	private SystemGiftbag giftbag = new SystemGiftbag();
	private Integer gameWebId;
	private String isCommit = "F";
	private int oldGiftBagType = -1;
	
	private List<SystemGiftbag> giftbagList = new ArrayList<SystemGiftbag>();

	public String execute() {
		Integer dbSourceId = gameWebId + ServerConstant.GIFT_DBKEY_BEGIN_NUMBER;
		SystemGiftbagService service = ServiceCacheFactory.getServiceCache()
				.getService(SystemGiftbagService.class);

		if (isCommit.equals("F")) {
			SystemGiftbag systemGiftbag = service
					.findLastSystemGiftbag(dbSourceId);
			List<SystemGiftbag> systemGiftbagList = service.getGiftbagList(dbSourceId);
			List<Integer> systemGiftBagTypeList = new ArrayList<Integer>();
			if (systemGiftbag == null) {
				giftbag.setType(ActivityConstant.SYSTEMGIFTBAG_TYPE);
			} else {
				giftbag.setType(systemGiftbag.getType() + 1);
			}
			// add
			if (systemGiftbagList != null) {
				for (SystemGiftbag bag : systemGiftbagList) {
					if (!systemGiftBagTypeList.contains(bag.getType())) {
						SystemGiftbag tempSystemGiftbag = new SystemGiftbag();
						tempSystemGiftbag.setType(bag.getType());
						tempSystemGiftbag.setName(bag.getName());
						giftbagList.add(tempSystemGiftbag);
					}
				}
			}

			return INPUT;
		}

		if (oldGiftBagType != -1) {
			giftbag.setType(oldGiftBagType);
		}
		// 重置数据
		oldGiftBagType = -1;
		
		SystemGiftbagManager manager = new SystemGiftbagManager();
		giftbag.setCreatedTime(new Date());
		giftbag.setUpdatedTime(new Date());
		
		// 获取分表后缀，礼包码前缀
		String tablePrefix = manager.getTablePrefixMap().get(giftbag.getType() % 26);
		giftbag.setTablePrefix(tablePrefix);		
		service.addSystemGiftbag(giftbag, dbSourceId);
		
		SystemGiftbag systemGiftbag = service
				.findLastSystemGiftbag(dbSourceId);
		List<SystemGiftbag> systemGiftbagList = service.getGiftbagList(dbSourceId);
		List<Integer> systemGiftBagTypeList = new ArrayList<Integer>();
		if (systemGiftbag == null) {
			giftbag.setType(ActivityConstant.SYSTEMGIFTBAG_TYPE);
		} else {
			giftbag.setType(systemGiftbag.getType() + 1);
		}
		// add
		if (systemGiftbagList != null) {
			for (SystemGiftbag bag : systemGiftbagList) {
				if (!systemGiftBagTypeList.contains(bag.getType())) {
					SystemGiftbag tempSystemGiftbag = new SystemGiftbag();
					tempSystemGiftbag.setType(bag.getType());
					tempSystemGiftbag.setName(bag.getName());
					giftbagList.add(tempSystemGiftbag);
				}
			}
		}

		return SUCCESS;
	}

	public SystemGiftbag getGiftbag() {
		return giftbag;
	}

	public void setGiftbag(SystemGiftbag giftbag) {
		this.giftbag = giftbag;
	}

	public Integer getGameWebId() {
		return gameWebId;
	}

	public void setGameWebId(Integer gameWebId) {
		this.gameWebId = gameWebId;
	}

	public String getIsCommit() {
		return isCommit;
	}

	public void setIsCommit(String isCommit) {
		this.isCommit = isCommit;
	}

	@Override
	public SystemGiftbag getModel() {
		// TODO Auto-generated method stub
		return giftbag;
	}
	
	public List<SystemGiftbag> getGiftbagList() {
		return giftbagList;
	}

	public void setGiftbagList(List<SystemGiftbag> giftbagList) {
		this.giftbagList = giftbagList;
	}

	public int getOldGiftBagType() {
		return oldGiftBagType;
	}

	public void setOldGiftBagType(int oldGiftBagType) {
		this.oldGiftBagType = oldGiftBagType;
	}

}
