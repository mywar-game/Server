package com.fantingame.game.gamecenter.factory;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import com.fandingame.game.framework.log.LogSystem;
import com.fantingame.game.gamecenter.service.PartnerService;

public class PartnerServiceFactory implements BeanPostProcessor{
	private static final Logger logger = Logger.getLogger(PartnerServiceFactory.class);
	
	private Map<Integer,PartnerService> partnerServiceMap = new HashMap<Integer,PartnerService>();
	
	
	public  PartnerService getBean(Integer partenerId) {
        return partnerServiceMap.get(partenerId);
    }
	public  PartnerService getBean(String partenerId) {
		int pid = 0;
		
		try {
			pid = Integer.valueOf(partenerId);	        
		} catch (Exception e) {
			logger.error("partnerId can't be Integer. partnerId: " + partenerId + " " + e.getMessage());
		}
		
		return partnerServiceMap.get(pid);
    }
	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName)
			throws BeansException {
		return bean;
	}

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName)
			throws BeansException {
		if(bean instanceof PartnerService){
			PartnerService pservice = (PartnerService)bean;
			if(pservice.getPatenerEnum()==null){
				throw new RuntimeException("partenerId没有设置，请在getPatenerId方法中填入返回值，className="+pservice.getClass().getCanonicalName());
			}
			if(partnerServiceMap.containsKey(pservice.getPatenerEnum().getPartenerId())){
				throw new RuntimeException("patenerId不能重复，patenerId="+pservice.getPatenerEnum().getPartenerId()+",className="+pservice.getClass().getCanonicalName()+",the same className="+partnerServiceMap.get(pservice.getPatenerEnum().getPartenerId()).getClass().getCanonicalName());
			}
			partnerServiceMap.put(pservice.getPatenerEnum().getPartenerId(), pservice);
			LogSystem.info("成功装载 合作商信息"+pservice.getPatenerEnum().toString()+",sreviceClass="+pservice.getClass().getCanonicalName());
		}
		return bean;
	}  
	
}
