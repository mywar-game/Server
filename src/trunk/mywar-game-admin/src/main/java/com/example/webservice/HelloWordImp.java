package com.example.webservice;

import com.framework.log.LogSystem;
import com.framework.webservice.WebServiceBaseEntry;

public class HelloWordImp extends WebServiceBaseEntry implements
		IHelloWorld {
	public String sayHi(String veryFileKey, String text) {
		// TODO Auto-generated method stub
		text = text + "ggg2";
		LogSystem.info(text);
		return text;
	}
}
