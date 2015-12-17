package com.adminTool.action;

import java.util.ArrayList;
import java.util.List;

import com.adminTool.bo.ComparePaymenty;
import com.adminTool.service.ComparePaymentService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;
import com.system.bo.GameWeb;
import com.system.service.GameWebService;

/**
 * 订单号比较
 * 
 * @author
 * 
 */
public class ComparePayment extends ALDAdminActionSupport {
	private static final long serialVersionUID = -3931277220435902604L;
	private String isCommit = "F";
	private String orderId;
	private String lodoId;
    private String result;
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public List<String> getResultList() {
		return resultList;
	}
	public void setResultList(List<String> resultList) {
		this.resultList = resultList;
	}
	public String getLodoId() {
		return lodoId;
	}
	public void setLodoId(String lodoId) {
		this.lodoId = lodoId;
	}
    private int a;
	public int getA() {
		return a;
	}
	public void setA(int a) {
		this.a = a;
	}
	private Integer gameWebId;
	private List<GameWeb> gameWebList = new ArrayList<GameWeb>();
	private List<String> resultList = new ArrayList<String>();
	private List<String> resultList2 = new ArrayList<String>();

	public List<String> getResultList2() {
		return resultList2;
	}
	public void setResultList2(List<String> resultList2) {
		this.resultList2 = resultList2;
	}
	public String execute() {
		if ("F".equals(isCommit)) {
			GameWebService gameWebService = ServiceCacheFactory
					.getServiceCache().getService(GameWebService.class);
			gameWebList = gameWebService.findGameWebList();
			return SUCCESS;
		} else {
			if ((lodoId == null || lodoId.equals(""))) {
				return SUCCESS;
			}
			String[] paymentArr = lodoId.split("\r\n");
			List<String> list3=new ArrayList<String>();
		
			ComparePaymentService service = ServiceCacheFactory
					.getServiceCache().getService(ComparePaymentService.class);
			List<Object> list2 = service.find(gameWebId);
			for(String payment : paymentArr){
				list3.add(payment);	
			}
			for(int i=0;i<list3.size();i++){
				if(!list2.contains(list3.get(i))){
					resultList.add(list3.get(i));
				}
			}
			
//			for (int i = 0; i < paymentArr.length; i++) {
//				for (int j = 0; j < list2.size(); j++) {
//					if (paymentArr[i].equals(list2.get(j))) {
//						resultList.add(paymentArr[i]);
//						break;
//					}
//				}
//			}
//			if(resultList.size()==0){
//				for(int m=0;m<paymentArr.length;m++){
//				resultList2.add(paymentArr[m]);
//				}
//			}else{
//				for(int v=0;v<paymentArr.length;v++){
//					resultList.contains(o)
//					for (int l = 0; l < list2.size(); l++) {
//					if(paymentArr[v].equals(resultList.get(l))){
//						break;
//					}
//				
//				}
//			}
			GameWebService gameWebService = ServiceCacheFactory
					.getServiceCache().getService(GameWebService.class);
			gameWebList = gameWebService.findGameWebList();

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

	public Integer getGameWebId() {
		return gameWebId;
	}

	public void setGameWebId(Integer gameWebId) {
		this.gameWebId = gameWebId;
	}

	public List<GameWeb> getGameWebList() {
		return gameWebList;
	}

	public void setGameWebList(List<GameWeb> gameWebList) {
		this.gameWebList = gameWebList;
	}

}
