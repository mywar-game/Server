package com.adminTool.action;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

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
/**
 * 输入用户的lodoId 查询出在某一区下所有的订单
 * @author Administrator
 *
 */

public class ReissueSearchById extends ALDAdminActionSupport {

	private static final long serialVersionUID = 3489149156798170650L;
	
	private String isCommit = "F";
	
	private Integer gameWebId;
	private Integer lodoId;
	private PaymentOrder paymentOrder = new PaymentOrder();
	private List<GameWeb> gameWebList = new ArrayList<GameWeb>();
	private List<PaymentOrder> paymentOrderList = new ArrayList<PaymentOrder>();
	private List<PaymentOrder> resultList = new ArrayList<PaymentOrder>();

	public String execute() {
		
		if ("F".equals(isCommit)) {
			GameWebService gameWebService = ServiceCacheFactory.getServiceCache().getService(GameWebService.class);
	  		gameWebList = gameWebService.findGameWebList();
	  		
			return INPUT;
		} else {
			UserService userService = ServiceCacheFactory.getServiceCache().getService(UserService.class);
			PaymentOrderService pos = ServiceCacheFactory.getServiceCache().getService(PaymentOrderService.class);
			UserRegLogService userRegLogService = ServiceCacheFactory.getServiceCache().getService(UserRegLogService.class);
				
			// 查询用户
			User user = userService.findUserByLodoId(lodoId);
			if (user==null) {
				super.setErroDescrip("玩家不存在！");
				return SUCCESS;
			}
			List<PaymentOrder> tempList = pos.findAllPaymentOrderByUserId(user.getUserId(), gameWebId);
			
			// 查询渠道Id
			UserRegLog userRegLog = userRegLogService.findUserRegLogByUserId(user.getUserId());
			
			if (userRegLog == null) {
				super.setErroDescrip("玩家不存在!");
				return SUCCESS;
			}
			// 查询game-web
			String response = pos.reissueSearchByPartnerUserId(userRegLog.getUserName(), gameWebId);
			Map<String, String> map = ParseJason.jason2Map(response);
			if (!map.get("rc").equals("1000")) {
				super.setErroDescrip("查询异常!");
				return SUCCESS;
			} else {
				String paymentOrderListStr = map.get("orderList");
				if (paymentOrderListStr != null) {
					//paymentOrderListStr = paymentOrderListStr.substring(1, paymentOrderListStr.length()-1);
					/*String[] paymentOrderListArr = paymentOrderListStr.split(",");
					for (String str : paymentOrderListArr) {
						String s = str.substring(1, str.length()-1);
					}*/
					JSONArray jsonArr = JSONArray.fromObject(paymentOrderListStr);
					for (int i = 0; i < jsonArr.size(); i++) {
						JSONObject jsonObject = jsonArr.getJSONObject(i);
						//String userName = jsonObject.getString("userName");
						String userName = user.getUserName();
						String userId = user.getUserId();
						Double amount = Double.valueOf(jsonObject.getString("amount"));
						long createdTime = Long.valueOf(jsonObject.getString("createdTime"));
						String orderId = jsonObject.getString("orderId");
						Integer status = Integer.valueOf(jsonObject.getInt("status"));
						String partnerOrderId = jsonObject.getString("partnerOrderId");
						PaymentOrder order = new PaymentOrder();
						order.setOrderId(orderId);
						order.setUserId(userId);
						order.setPartnerOrderId(partnerOrderId);
						order.setUserName(userName);
						order.setAmount(amount);
						order.setStatus(status);
						order.setCreatedTime(new Timestamp(createdTime));
						order.setGameServerId(gameWebId);
						paymentOrderList.add(order);
						// pos.savePaymentOrder(order);
					}
				}
				/*JSONObject jsonObject = JSONObject.fromObject(paymentOrderListStr);
				List<PaymentOrder> paymentOrderList = JSONArray.toList((JSONArray)jsonObject.get(HttpClientBridge.SUCCESS), PaymentOrder.class);*/
				for (PaymentOrder p : paymentOrderList) {
					String orderId = p.getOrderId();
					String userId = p.getUserId();
					Boolean hased = false;
					for (PaymentOrder pp : tempList) {
						String tOrderId = pp.getOrderId();
						String tUserId = pp.getUserId();
						if (orderId.equalsIgnoreCase(tOrderId) && userId.equalsIgnoreCase(tUserId)) {
							// 已经存在数据
							hased = true;
							break;
						} else {
							hased = false;
							continue;
						}
					}
					if (!hased) {
						pos.savePaymentOrder(p);
					}
				}
				// 再次查询
				resultList = pos.findAllPaymentOrderByUserId(user.getUserId(), gameWebId);
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

	public Integer getLodoId() {
		return lodoId;
	}

	public void setLodoId(Integer lodoId) {
		this.lodoId = lodoId;
	}

	public List<PaymentOrder> getPaymentOrderList() {
		return paymentOrderList;
	}

	public void setPaymentOrderList(List<PaymentOrder> paymentOrderList) {
		this.paymentOrderList = paymentOrderList;
	}

	public List<PaymentOrder> getResultList() {
		return resultList;
	}

	public void setResultList(List<PaymentOrder> resultList) {
		this.resultList = resultList;
	}

}

