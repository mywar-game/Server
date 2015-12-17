package com.stats.collector;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.adminTool.bo.Partner;
import com.adminTool.service.PartnerService;
import com.dataconfig.bo.PaymentLog;
import com.dataconfig.service.UserPayService;
import com.framework.servicemanager.ServiceCacheFactory;
import com.stats.bo.PaymentLogStats;
import com.stats.service.UserPaymentLogStatsService;

public class PaymentLogStatsCollector implements Collector {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void execute(Date date) throws Exception {

		List<Object> userPayHistoryList;
		UserPayService userPayHistoryLogService = ServiceCacheFactory
				.getServiceCache().getService(UserPayService.class);
		userPayHistoryList = userPayHistoryLogService.getPaymentLogList();
		PartnerService partnerService = ServiceCacheFactory.getServiceCache()
				.getService(PartnerService.class);
		Map<String, Partner> partnerMap = partnerService.findAllPartnerMap();
		UserPaymentLogStatsService userPayStatsService = ServiceCacheFactory
				.getServiceCache().getService(UserPaymentLogStatsService.class);

		userPayStatsService.deleteAll();

		if (userPayHistoryList != null) {

			List<PaymentLogStats> paymentLogStatsList = new ArrayList<PaymentLogStats>();

			for (int i=0;i<userPayHistoryList.size();i++) {

				Object[] log = (Object[])userPayHistoryList.get(i);
				PaymentLogStats logOrder = new PaymentLogStats();

				logOrder.setServerId(log[4].toString());
				logOrder.setUserId(log[0].toString());
				logOrder.setUserName(log[1].toString());
				logOrder.setTotalAmount(Double.parseDouble(log[2].toString()));
				logOrder.setLastCreateTime((Timestamp)log[5]);
				

				String partnerId = log[3].toString();
				logOrder.setPartnerId(partnerId);
				Partner partner = partnerMap.get(partnerId);
				if (partner == null) {
					logOrder.setPartnerName("未命名渠道号");
				} else {
					logOrder.setPartnerName(partnerMap.get(partnerId)
							.getPName());
				}

				paymentLogStatsList.add(logOrder);
			}

			userPayStatsService.addPaymentLogStatsBatch(paymentLogStatsList);
			
		}

	}

}
