package com.example.webservice;

import com.framework.webservice.WebServiceBaseEntry;

public class Test2 extends WebServiceBaseEntry implements ITest2 {

	public void test(String veryFY) {
		// TODO Auto-generated method stub
      System.out.println("Come test webService" + veryFY);
	}

	public String testObjectParam(String veryfy, String position) {
		// TODO Auto-generated method stub
		return "good";
	}

	public String testObjectParam(String veryfy, int i, long c, boolean b,
			String position) {
		// TODO Auto-generated method stub
		return "good";
	}

}
