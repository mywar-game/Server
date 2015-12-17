package com.log.action;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.framework.common.ALDAdminLogPageActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;
import com.log.service.PaymentTotalService;

@SuppressWarnings("serial")
public class PaymentTotalList extends ALDAdminLogPageActionSupport {

	private boolean isCommit = false;
	private String bTime;
	private String eTime;
	
	@Override
	public String execute() {
		if (isCommit == false) {
			return SUCCESS;
		}
		PaymentTotalService service = ServiceCacheFactory.getServiceCache().getService(PaymentTotalService.class);
		eTime = bTime;
		bTime += " 00:00:00";
		eTime += " 23:59:59";
		String[] regTimeArr = new String[] {bTime, eTime};
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for (int i = 0; i <= 14; i++) {
			try {
				Date date1 = sdf.parse(bTime);
				Date date2 = sdf.parse(eTime);
				Calendar cl1 = Calendar.getInstance();
				cl1.setTime(date1);
				cl1.add(Calendar.DATE, i);
				Calendar cl2 = Calendar.getInstance();
				cl2.setTime(date2);
				cl2.add(Calendar.DATE, i);
				String temp1 = sdf.format(cl1.getTime());
				String temp2 = sdf.format(cl2.getTime());
				
				// List<Object> list = service.find(regTimeArr, temp2);
				
			} catch (Exception e) {
				
			}
		}
		return SUCCESS;
	}
	
	public boolean isCommit() {
		return isCommit;
	}

	public void setCommit(boolean isCommit) {
		this.isCommit = isCommit;
	}

	public String getbTime() {
		return bTime;
	}

	public void setbTime(String bTime) {
		this.bTime = bTime;
	}

	public String geteTime() {
		return eTime;
	}

	public void seteTime(String eTime) {
		this.eTime = eTime;
	}
}
