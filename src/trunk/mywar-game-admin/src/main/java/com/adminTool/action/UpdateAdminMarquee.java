package com.adminTool.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.adminTool.bo.AdminMarquee;
import com.adminTool.bo.Partner;
import com.adminTool.service.AdminMarqueeService;
import com.adminTool.service.PartnerService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;
import com.opensymphony.xwork2.ModelDriven;
import com.system.bo.GameWeb;
import com.system.bo.TGameServer;
import com.system.service.GameWebService;
import com.system.service.TGameServerService;

/**
 * 修改跑马灯
 * 
 * @author yezp
 */
public class UpdateAdminMarquee extends ALDAdminActionSupport implements
		ModelDriven<AdminMarquee> {

	private static final long serialVersionUID = 4398549591256367673L;

	private AdminMarquee adminMarquee = new AdminMarquee();
	private Map<GameWeb, List<TGameServer>> map;
	private Map<GameWeb, Map<String, String>> sidMap;
	private List<Partner> partnerList;
	private String isCommit = "F";
	private String[] serverIdArr;

	public String execute() {
		AdminMarqueeService service = ServiceCacheFactory.getServiceCache()
				.getService(AdminMarqueeService.class);
		if (isCommit.equals("F")) {
			GameWebService gameWebService = ServiceCacheFactory
					.getServiceCache().getService(GameWebService.class);
			TGameServerService serverService = ServiceCacheFactory
					.getServiceCache().getService(TGameServerService.class);
			PartnerService partnerService = ServiceCacheFactory.getServiceCache()
					.getService(PartnerService.class);
			partnerList = partnerService.findPartnerList();

			map = new HashMap<GameWeb, List<TGameServer>>();
			sidMap = new HashMap<GameWeb, Map<String, String>>();
			List<GameWeb> gameWebList = gameWebService.findGameWebList();

			adminMarquee = service.getOneAdminMarquee(adminMarquee.getId());
			String serverIds = adminMarquee.getServerIds();
			String[] sidArr = serverIds.split(",");

			for (GameWeb gameWeb : gameWebList) {
				List<TGameServer> list = serverService
						.findTGameServerListByGameWebId(gameWeb.getServerId());
				map.put(gameWeb, list);

				Map<String, String> sidList = new HashMap<String, String>();
				for (String sid : sidArr) {
					String[] strArr = sid.split("_");
					if (strArr.length == 2) {
						if (strArr[0].equals(gameWeb.getServerId().toString())) {
							sidList.put(strArr[1], strArr[1]);
						}
					}
				}

				sidMap.put(gameWeb, sidList);
			}

			return INPUT;
		}

		String sid = "";
		for (String serverId : serverIdArr) {
			if (sid.length() > 0) {
				sid = sid + "," + serverId;
			} else {
				sid = serverId;
			}
		}

		adminMarquee.setServerIds(sid);
		service.updateAdminMarquee(adminMarquee);
		return SUCCESS;
	}

	
	public List<Partner> getPartnerList() {
		return partnerList;
	}


	public void setPartnerList(List<Partner> partnerList) {
		this.partnerList = partnerList;
	}


	public Map<GameWeb, Map<String, String>> getSidMap() {
		return sidMap;
	}

	public void setSidMap(Map<GameWeb, Map<String, String>> sidMap) {
		this.sidMap = sidMap;
	}

	public Map<GameWeb, List<TGameServer>> getMap() {
		return map;
	}

	public void setMap(Map<GameWeb, List<TGameServer>> map) {
		this.map = map;
	}

	public String[] getServerIdArr() {
		return serverIdArr;
	}

	public void setServerIdArr(String[] serverIdArr) {
		this.serverIdArr = serverIdArr;
	}

	public AdminMarquee getAdminMarquee() {
		return adminMarquee;
	}

	public void setAdminMarquee(AdminMarquee adminMarquee) {
		this.adminMarquee = adminMarquee;
	}

	public String getIsCommit() {
		return isCommit;
	}

	public void setIsCommit(String isCommit) {
		this.isCommit = isCommit;
	}

	@Override
	public AdminMarquee getModel() {
		// TODO Auto-generated method stub
		return adminMarquee;
	}

}
