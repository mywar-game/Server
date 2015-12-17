package com.adminTool.action;

import java.util.List;

import com.adminTool.bo.GiftbagDropTool;
import com.adminTool.constant.ToolTypeConstant;
import com.adminTool.service.GiftbagDropToolService;
import com.framework.common.ALDAdminPageActionSupport;
import com.framework.common.IPage;
import com.framework.servicemanager.ServiceCacheFactory;
import com.system.constant.ServerConstant;

/**
 * 礼包物品掉落列表
 * 
 * @author yezp
 */
public class GiftbagDropToolList extends ALDAdminPageActionSupport {

	private static final long serialVersionUID = -626952957948750220L;

	private List<GiftbagDropTool> dropToolList;

	private Integer gameWebId;
	private Integer giftbagId;
	private String isCommit = "F";

	public String execute() {
		GiftbagDropToolService service = ServiceCacheFactory.getServiceCache()
				.getService(GiftbagDropToolService.class);

		Integer dbSourceId = gameWebId + ServerConstant.GIFT_DBKEY_BEGIN_NUMBER;
		IPage<GiftbagDropTool> iPage = service.findGiftbagDropToolPageList(
				giftbagId, dbSourceId, super.getToPage(),
				ALDAdminPageActionSupport.DEFAULT_PAGESIZE);
		if (iPage == null) {
			return SUCCESS;
		}

		ToolTypeConstant typeConstant = new ToolTypeConstant();
		dropToolList = (List<GiftbagDropTool>) iPage.getData();
		for (GiftbagDropTool tool : dropToolList) {
			tool.setToolTypeName(typeConstant.getToolTypeNameMap().get(
					tool.getToolType()));
		}

		super.setTotalPage(iPage.getTotalPage());
		super.setTotalSize(iPage.getTotalSize());
		return SUCCESS;
	}

	public Integer getGameWebId() {
		return gameWebId;
	}

	public void setGameWebId(Integer gameWebId) {
		this.gameWebId = gameWebId;
	}

	public Integer getGiftbagId() {
		return giftbagId;
	}

	public void setGiftbagId(Integer giftbagId) {
		this.giftbagId = giftbagId;
	}

	public String getIsCommit() {
		return isCommit;
	}

	public void setIsCommit(String isCommit) {
		this.isCommit = isCommit;
	}

	public List<GiftbagDropTool> getDropToolList() {
		return dropToolList;
	}

	public void setDropToolList(List<GiftbagDropTool> dropToolList) {
		this.dropToolList = dropToolList;
	}

}
