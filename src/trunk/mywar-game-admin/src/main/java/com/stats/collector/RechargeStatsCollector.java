package com.stats.collector;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.adminTool.bo.Partner;
import com.adminTool.service.PartnerService;
import com.adminTool.service.UserService;
import com.dataconfig.service.UserPayService;
import com.framework.common.SystemStatsDate;
import com.framework.log.LogSystem;
import com.framework.servicemanager.CustomerContextHolder;
import com.framework.servicemanager.ServiceCacheFactory;
import com.framework.util.DateUtil;
import com.log.service.UserLoginLogService;
import com.stats.bo.RechargeStats;
import com.stats.service.RechargeStatsService;

public class RechargeStatsCollector implements Collector {

	@Override
	public void execute(Date time)
			throws Exception {
		LogSystem.info("充值统计开始-------------");
		String[] dates = DateUtil.getOneDayStrArr(SystemStatsDate.YESTERDAY);
		Date date = DateUtil.getSomeDaysDiffDate(SystemStatsDate.YESTERDAY);
		UserService userService = ServiceCacheFactory.getServiceCache().getService(UserService.class);
		UserLoginLogService userLoginLogService = ServiceCacheFactory.getServiceCache().getService(UserLoginLogService.class);
		RechargeStatsService rechargeStatsService = ServiceCacheFactory.getServiceCache().getService(RechargeStatsService.class);
		UserPayService userPayService = ServiceCacheFactory.getServiceCache().getService(UserPayService.class);
		PartnerService partnerService = ServiceCacheFactory.getServiceCache().getService(PartnerService.class);
		List<Object> payList = userPayService.findTotalPayUserNumAndTotalAmount(dates, true);//以渠道分组的付费总用户和付费总额
		List<Object> newPayList = userPayService.findNewUserPayNumAndPayAmount(dates, true);//以渠道分组的新用户付费数和新用户付费总额
		List<Object> loginList = userLoginLogService.findActiveUserAmount(dates,true);//活跃
		List<Object> newRegList = userService.findCreateUserNumInSomeTime(dates,true);//新注册数
		/**将list数据装成map开始**/
		Map<String, Object[]> payMap = new HashMap<String, Object[]>();
		Map<String, Object[]> newpayMap = new HashMap<String, Object[]>();
		Map<String, Object[]> loginMap = new HashMap<String, Object[]>();
		Map<String, Object[]> newRegMap = new HashMap<String, Object[]>();
		Map<String, Integer> existPartnerMap = new HashMap<String, Integer>();//此次统计关联到了的partner
		if(payList!=null && payList.size()>0){
			for(int i=0;i<payList.size();i++){
				Object[] arr = (Object[])payList.get(i);
				payMap.put(arr[2].toString(), arr);
				existPartnerMap.put(arr[2].toString(), 1);
			}
		}
		if(newPayList!=null && newPayList.size()>0){
			for(int i=0;i<newPayList.size();i++){
				Object[] arr = (Object[])newPayList.get(i);
				newpayMap.put(arr[2].toString(), arr);
				existPartnerMap.put(arr[2].toString(), 1);
			}
		}
		if(loginList!=null && loginList.size()>0){
			for(int i=0;i<loginList.size();i++){
				Object[] arr = (Object[])loginList.get(i);
				loginMap.put(arr[2].toString(), arr);
				existPartnerMap.put(arr[2].toString(), 1);
			}
		}
		if(newRegList!=null && newRegList.size()>0){
			for(int i=0;i<newRegList.size();i++){
				Object[] arr = (Object[])newRegList.get(i);
				newRegMap.put(arr[1].toString(), arr);
				existPartnerMap.put(arr[1].toString(), 1);
			}
		}
		/**将list数据装成map结束**/
		Map<String, Partner> partnerMap = partnerService.findAllPartnerMap();
		List<RechargeStats> result = new ArrayList<RechargeStats>();
		if(partnerMap.size()>0){
			for(Partner partner : partnerMap.values()){
				if(!existPartnerMap.containsKey(partner.getPNum())){//此partner在本次统计中没有数据,不用生成统计
					continue;
				}
				RechargeStats stats = new RechargeStats();
				if(loginMap.containsKey(partner.getPNum())){//日活跃
					Object[] arr = loginMap.get(partner.getPNum());
					stats.setDayActive(Integer.valueOf(arr[0].toString()));
				}else{
					stats.setDayActive(0);
				}
				if(newRegMap.containsKey(partner.getPNum())){//新注册
					Object[] arr = newRegMap.get(partner.getPNum());
					stats.setNewReg(Integer.valueOf(arr[0].toString()));
				}else{
					stats.setNewReg(0);
				}
				if(newpayMap.containsKey(partner.getPNum())){
					Object[] arr = newpayMap.get(partner.getPNum());
					stats.setNewRegPayNum(Integer.valueOf(arr[0].toString()));
					stats.setNewRegPayTotalAmount(Float.valueOf(arr[1].toString()));
				}else{
					stats.setNewRegPayNum(0);
					stats.setNewRegPayTotalAmount(0f);
				}
				if(payMap.containsKey(partner.getPNum())){
					Object[] arr = payMap.get(partner.getPNum());
					stats.setPayUserNum(Integer.valueOf(arr[0].toString()));
					stats.setPayTotalAmount(Float.valueOf(arr[1].toString()));
				}else{
					stats.setPayUserNum(0);
					stats.setPayTotalAmount(0f);
				}
				stats.setPartnerId(partner.getPNum());
				stats.setSysNum(CustomerContextHolder.getSystemNum());
				stats.setTime(date);
				result.add(stats);
			}
		}
		if(result.size()>0){
			rechargeStatsService.saveBatch(result);
		}
		LogSystem.info("充值统计结束-------------");
	}

}
