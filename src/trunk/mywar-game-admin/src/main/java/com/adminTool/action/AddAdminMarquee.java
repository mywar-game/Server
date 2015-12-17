package com.adminTool.action;

import java.sql.Timestamp;
import java.util.Date;
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
 * 添加跑马灯
 * 
 * @author yezp
 */
public class AddAdminMarquee extends ALDAdminActionSupport implements
		ModelDriven<AdminMarquee> {

	private static final long serialVersionUID = 2777960771533094581L;

	private AdminMarquee adminMarquee = new AdminMarquee();
	private Map<GameWeb, List<TGameServer>> map;
	private List<Partner> partnerList;
	private String isCommit = "F";
	private String[] serverIdArr;

	public String execute() {
		if (isCommit.equals("F")) {
			GameWebService gameWebService = ServiceCacheFactory
					.getServiceCache().getService(GameWebService.class);
			TGameServerService serverService = ServiceCacheFactory
					.getServiceCache().getService(TGameServerService.class);
			PartnerService partnerService = ServiceCacheFactory.getServiceCache()
					.getService(PartnerService.class);
			partnerList = partnerService.findPartnerList();

			map = new HashMap<GameWeb, List<TGameServer>>();
			List<GameWeb> gameWebList = gameWebService.findGameWebList();
			for (GameWeb gameWeb : gameWebList) {
				List<TGameServer> list = serverService
						.findTGameServerListByGameWebId(gameWeb.getServerId());
				map.put(gameWeb, list);
			}
			
			return INPUT;
		}

		AdminMarqueeService service = ServiceCacheFactory.getServiceCache()
				.getService(AdminMarqueeService.class);

		Date now = new Date();
		Timestamp createdTime = new Timestamp(now.getTime());
		adminMarquee.setCreatedTime(createdTime);

		String sid = "";
		for (String serverId : serverIdArr) {
			if (sid.length() > 0) {
				sid = sid + "," + serverId;
			} else {
				sid = serverId;
			}
		}

		adminMarquee.setServerIds(sid);
		service.addAdminMarquee(adminMarquee);
		return SUCCESS;
	}

	public List<Partner> getPartnerList() {
		return partnerList;
	}


	public void setPartnerList(List<Partner> partnerList) {
		this.partnerList = partnerList;
	}


	public String[] getServerIdArr() {
		return serverIdArr;
	}

	public void setServerIdArr(String[] serverIdArr) {
		this.serverIdArr = serverIdArr;
	}

	public Map<GameWeb, List<TGameServer>> getMap() {
		return map;
	}

	public void setMap(Map<GameWeb, List<TGameServer>> map) {
		this.map = map;
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

	public AdminMarquee getAdminMarquee() {
		return adminMarquee;
	}

	public void setAdminMarquee(AdminMarquee adminMarquee) {
		this.adminMarquee = adminMarquee;
	}

}
