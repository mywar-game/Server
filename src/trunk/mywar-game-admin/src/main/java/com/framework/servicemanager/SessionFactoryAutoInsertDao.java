package com.framework.servicemanager;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * 
 * 自动注入sessionFactory到Dao（继承HibernateDaoSupport�?
 * 
 * @author mengchao
 * 
 */
public class SessionFactoryAutoInsertDao implements BeanPostProcessor {

	private SessionFactory sessionFactory;

	private List<HibernateDaoSupport> list = new ArrayList<HibernateDaoSupport>();
	
	public org.hibernate.SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Object postProcessBeforeInitialization(Object bean, String beanName)
			throws BeansException {
		if (bean instanceof HibernateDaoSupport
				 && ((HibernateDaoSupport) bean).getSessionFactory() == null) // 当Bean实现HibernateDaoSupport时自动注入sessionFactory
		{
			((HibernateDaoSupport) bean).setSessionFactory(sessionFactory);
		    list.add((HibernateDaoSupport) bean);
		}
		return bean;
	}

	public Object postProcessAfterInitialization(Object bean, String beanName)
			throws BeansException {
		return bean;
	}

	public List<HibernateDaoSupport> getList() {
		return list;
	}

}
