package com.adminTool.action;

import com.adminTool.bo.GiftbagDropTool;
import com.adminTool.service.GiftbagDropToolService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;
import com.opensymphony.xwork2.ModelDriven;
import com.system.constant.ServerConstant;

/**
 * 添加礼包掉落道具
 * 
 * @author yezp
 */
public class AddGiftbagDropTool extends ALDAdminActionSupport implements
		ModelDriven<GiftbagDropTool> {

	private static final long serialVersionUID = -1040908530863389205L;
	private GiftbagDropTool giftbagDropTool = new GiftbagDropTool();
	private Integer gameWebId;
	private Integer giftbagId;
	private String isCommit = "F";

	private String[] toolTypeArr;
	private String[] toolIdArr;
	private String[] nameArr;
	private String[] toolNumArr;
	private String[] lowerNumArr;
	private String[] upperNumArr;

	public String execute() {
		if (toolTypeArr == null) {
			isCommit = "F";
			return SUCCESS;
		}

		GiftbagDropToolService service = ServiceCacheFactory.getServiceCache()
				.getService(GiftbagDropToolService.class);
		int dbSourceId = gameWebId + ServerConstant.GIFT_DBKEY_BEGIN_NUMBER;
		service.delGiftbagDropTool(dbSourceId, giftbagDropTool.getGiftbagId());

		for (int i = 0; i < toolTypeArr.length; i++) {
			boolean mark = false;
			if (toolTypeArr[i] != null && !toolTypeArr[i].equals("")) {
				try {
					int typeId = Integer.parseInt(toolTypeArr[i]);
					giftbagDropTool.setToolType(typeId);
					mark = true;
				} catch (NumberFormatException e) {
					mark = false;
				}
			}

			if (mark && toolIdArr[i] != null && !toolIdArr[i].equals("")) {
				try {
					int toolId = Integer.parseInt(toolIdArr[i]);
					giftbagDropTool.setToolId(toolId);
				} catch (NumberFormatException e) {
					mark = false;
				}
			} else {
				mark = false;
			}

			if (mark && nameArr[i] != null && !nameArr[i].equals("")) {
				giftbagDropTool.setName(nameArr[i]);
			} else {
				mark = false;
			}

			if (mark && toolNumArr[i] != null && !toolNumArr[i].equals("")) {
				try {
					int num = Integer.parseInt(toolNumArr[i]);
					giftbagDropTool.setToolNum(num);
					;
				} catch (NumberFormatException e) {
					mark = false;
				}
			} else {
				mark = false;
			}

			if (mark && lowerNumArr[i] != null && !lowerNumArr[i].equals("")) {
				try {
					int lowerNum = Integer.parseInt(lowerNumArr[i]);
					giftbagDropTool.setLowerNum(lowerNum);
				} catch (NumberFormatException e) {
					mark = false;
				}
			} else {
				mark = false;
			}

			if (mark && upperNumArr[i] != null && !upperNumArr[i].equals("")) {
				try {
					int upperNum = Integer.parseInt(upperNumArr[i]);
					giftbagDropTool.setUpperNum(upperNum);
				} catch (NumberFormatException e) {
					mark = false;
				}
			} else {
				mark = false;
			}

			if (mark) {
				service.addGiftbagDropTool(giftbagDropTool, dbSourceId);
			}
		}

		return SUCCESS;
	}

	public String[] getToolTypeArr() {
		return toolTypeArr;
	}

	public void setToolTypeArr(String[] toolTypeArr) {
		this.toolTypeArr = toolTypeArr;
	}

	public String[] getToolIdArr() {
		return toolIdArr;
	}

	public void setToolIdArr(String[] toolIdArr) {
		this.toolIdArr = toolIdArr;
	}

	public String[] getNameArr() {
		return nameArr;
	}

	public void setNameArr(String[] nameArr) {
		this.nameArr = nameArr;
	}

	public String[] getToolNumArr() {
		return toolNumArr;
	}

	public void setToolNumArr(String[] toolNumArr) {
		this.toolNumArr = toolNumArr;
	}

	public String[] getLowerNumArr() {
		return lowerNumArr;
	}

	public void setLowerNumArr(String[] lowerNumArr) {
		this.lowerNumArr = lowerNumArr;
	}

	public String[] getUpperNumArr() {
		return upperNumArr;
	}

	public void setUpperNumArr(String[] upperNumArr) {
		this.upperNumArr = upperNumArr;
	}

	@Override
	public GiftbagDropTool getModel() {
		return giftbagDropTool;
	}

	public GiftbagDropTool getGiftbagDropTool() {
		return giftbagDropTool;
	}

	public void setGiftbagDropTool(GiftbagDropTool giftbagDropTool) {
		this.giftbagDropTool = giftbagDropTool;
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

	public Integer getGiftbagId() {
		return giftbagId;
	}

	public void setGiftbagId(Integer giftbagId) {
		this.giftbagId = giftbagId;
	}

	public void setIsCommit(String isCommit) {
		this.isCommit = isCommit;
	}

}
