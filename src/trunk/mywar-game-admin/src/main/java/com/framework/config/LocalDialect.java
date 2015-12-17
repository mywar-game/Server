package com.framework.config;

import java.sql.Types;

import org.hibernate.Hibernate;
import org.hibernate.dialect.MySQLDialect;

public class LocalDialect extends MySQLDialect {

	public LocalDialect() {
		super();
		registerHibernateType(Types.LONGVARCHAR, Hibernate.STRING.getName());//注册text类型
	}
	
}
