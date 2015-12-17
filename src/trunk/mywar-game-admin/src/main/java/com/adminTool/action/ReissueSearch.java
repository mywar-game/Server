package com.adminTool.action;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.adminTool.bo.PaymentOrder;
import com.adminTool.bo.User;
import com.adminTool.service.PaymentOrderService;
import com.adminTool.service.UserService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;
import com.log.bo.UserRegLog;
import com.log.service.UserRegLogService;
import com.system.bo.GameWeb;
import com.system.service.GameWebService;
import com.tool.ParseJason;

public class ReissueSearch extends ALDAdminActionSupport {

	private static final long serialVersionUID = 3489149156798170650L;
	
	private String isCommit = "F";
	private String orderId;
	private Integer gameWebId;

	private PaymentOrder paymentOrder = new PaymentOrder();
	private List<GameWeb> gameWebList = new ArrayList<GameWeb>();

	public String execute() {
		
		if ("F".equals(isCommit)) {
			GameWebService gameWebService = ServiceCacheFactory.getServiceCache().getService(GameWebService.class);
	  		gameWebList = gameWebService.findGameWebList();
	  		
			return INPUT;
		} else {
			UserService userService = ServiceCacheFactory.getServiceCache().getService(UserService.class);
			PaymentOrderService pos = ServiceCacheFactory.getServiceCache().getService(PaymentOrderService.class);
			UserRegLogService userRegLogService = ServiceCacheFactory.getServiceCache().getService(UserRegLogService.class);
			
			// 先查询后台数据库
			paymentOrder = pos.findByOrderId(orderId);
			
			// 若后台数据库不存在，则查询 game-web，并将新的 paymentOrder 存入后台数据库
			if (paymentOrder == null) {
			
				// 向 game-web 查询订单信息
				String response = pos.reissueSearch(orderId, gameWebId); 
				
				Map<String, String> map = ParseJason.jason2Map(response);
				
				if (!map.get("rc").equals("1000")) {
					return ERROR;
				}
				
				Map<String, String> order = ParseJason.jason2Map(map.get("order"));
				UserRegLog userRegLog = userRegLogService.findUserRegLogByUserName(order.get("partnerUserId"));
				paymentOrder = new PaymentOrder();
                if(userRegLog!=null){
                	User user = userService.findUserByUserId(userRegLog.getUserId());
					paymentOrder.setUserName(user.getName());
					paymentOrder.setUserId(user.getUserId());
                }else{
                	paymentOrder.setUserName("看不到");
                	paymentOrder.setUserId("xx");
                }
				paymentOrder.setAmount(Double.valueOf(order.get("amount")));
				long createdTime = Long.valueOf(order.get("createdTime"));
				paymentOrder.setCreatedTime(new Timestamp(createdTime));
				paymentOrder.setOrderId(order.get("orderId"));
				int status = Integer.valueOf(order.get("status"));
				paymentOrder.setStatus(status);
				if (order.get("partnerOrderId").equals("null")) {
					paymentOrder.setPartnerOrderId(null);
				}
				// add
				paymentOrder.setGameServerId(gameWebId);
				
				if (status == 0) { // status 为0表示还未充值
					pos.savePaymentOrder(paymentOrder);
				}
			}
			
			return SUCCESS;
		}
	}

	public String getIsCommit() {
		return isCommit;
	}

	public void setIsCommit(String isCommit) {
		this.isCommit = isCommit;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public PaymentOrder getPaymentOrder() {
		return paymentOrder;
	}

	public void setPaymentOrder(PaymentOrder paymentOrder) {
		this.paymentOrder = paymentOrder;
	}
	
	public List<GameWeb> getGameWebList() {
		return gameWebList;
	}

	public void setGameWebList(List<GameWeb> gameWebList) {
		this.gameWebList = gameWebList;
	}
	
	public Integer getGameWebId() {
		return gameWebId;
	}
	
	public void setGameWebId(Integer gameWebId) {
		this.gameWebId = gameWebId;
	}
}
