package com.adminTool.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.adminTool.bo.PaymentOrder;
import com.adminTool.dao.PaymentOrderDao;
import com.framework.client.http.HttpClientBridge;
import com.framework.common.DBSource;
import com.framework.common.IPage;
import com.framework.servicemanager.ServiceCacheFactory;
import com.system.bo.GameWeb;
import com.system.service.GameWebService;

public class PaymentOrderService {
	

	private static final String ROOT_PATH = "http://wapi.ios.hw.fantingame.com:80";
	private static final String GET_ORDER_URL = "/webApi/getOrder.do";
	private static final String FILL_ORDER_URL = "/webApi/fillOrder.do";
	private static final String crcKey = "098!@#&^%asdfg*&^%589l";
	
	private static final String GET_ORDERLIST_URL = "/webApi/getOrderByPartnerUserId.do";
	
	private PaymentOrderDao paymentOrderDao;
	
	public void updatePaymentOrder(PaymentOrder paymentOrder) {
		paymentOrderDao.update(paymentOrder, DBSource.ADMIN);
	}
	
	public void savePaymentOrder(PaymentOrder paymentOrder) {
		paymentOrderDao.save(paymentOrder, DBSource.ADMIN);
	}
	
	public IPage<PaymentOrder> findAllPaymentOrder(Integer currentPage, Integer pageSize) {
		paymentOrderDao.closeSession(DBSource.ADMIN);
		return paymentOrderDao.findPage("from PaymentOrder where status=0", new ArrayList<Object>(), pageSize, currentPage);
		
	}
	
	public List<PaymentOrder> findAllPaymentOrderByUserId(String userId, Integer webGameId) {
		paymentOrderDao.closeSession(DBSource.ADMIN);
		List<Object> list = new ArrayList<Object>();
		list.add(userId);
		list.add(webGameId);
		return paymentOrderDao.find("from PaymentOrder where userId = ? and gameServerId = ?", list);
		
	}
	
	public PaymentOrder findByOrderId(String orderId) {		
		paymentOrderDao.closeSession(DBSource.ADMIN);
		return paymentOrderDao.loadBy("orderId", orderId, DBSource.ADMIN);
	}

	public PaymentOrderDao getPaymentOrderDao() {
		return paymentOrderDao;
	}

	public void setPaymentOrderDao(PaymentOrderDao paymentOrderDao) {
		this.paymentOrderDao = paymentOrderDao;
	}
	
	/**
	 * 查询 game-web 中的订单信息
	 */
	public String reissueSearch(String orderId, int gameWebId) {
		String params = "&orderNo=" + orderId;
		GameWebService gameWebService = ServiceCacheFactory.getServiceCache().getService(GameWebService.class);
  		GameWeb gameWeb = gameWebService.getGameWebById(gameWebId);
  		
		// String url = ROOT_PATH + GET_ORDER_URL;
  		String url = "http://" + gameWeb.getServerIp() + ":" + gameWeb.getServerPort() + GET_ORDER_URL;
  		
		String response = HttpClientBridge.sendToPaymentController(url, params, orderId, crcKey);
		
		return response;
	}
	
	/**
	 * 补发审核通过，向 game-web 发送请求，进行补发
	 */
	public String fillOrder(String orderId, String partnerOrderId, int gameWebId) {
		StringBuilder sb = new StringBuilder();
		sb.append("&orderNo=").append(orderId);
		sb.append("&partnerOrderId=").append(partnerOrderId);
		GameWebService gameWebService = ServiceCacheFactory.getServiceCache().getService(GameWebService.class);
  		GameWeb gameWeb = gameWebService.getGameWebById(gameWebId);
  		
		//String url = ROOT_PATH + FILL_ORDER_URL;
  		String url = "http://" + gameWeb.getServerIp() + ":" + gameWeb.getServerPort() + FILL_ORDER_URL;
		
		String md5Str = orderId + partnerOrderId;
		String response = HttpClientBridge.sendToPaymentController(url, sb.toString(), md5Str, crcKey);
		return response;
	}
	
	@Test
	public void test() {
		String orderId = "430853d3f16c4903a4fd53329b95da6d";
		String partnerOrderId = "12345";
		//fillOrder(orderId, partnerOrderId);
	}
	
	/**
	 * 查询 game-web 中的订单列表, 根据partnerUserId
	 */
	public String reissueSearchByPartnerUserId(String partnerUserId, int gameWebId) {
		String params = "&partnerUserId=" + partnerUserId;
		GameWebService gameWebService = ServiceCacheFactory.getServiceCache().getService(GameWebService.class);
  		GameWeb gameWeb = gameWebService.getGameWebById(gameWebId);

  		String url = "http://" + gameWeb.getServerIp() + ":" + gameWeb.getServerPort() + GET_ORDERLIST_URL;
  		
		String response = HttpClientBridge.sendToPaymentController(url, params, partnerUserId, crcKey);
		
		return response;
	}
	
}
