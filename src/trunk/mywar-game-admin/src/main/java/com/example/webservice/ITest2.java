package com.example.webservice;

import javax.jws.WebService;


@WebService
public interface ITest2 {
   public void test(String veryfy);
   
   
   public String testObjectParam(String veryfy, int i, long c, boolean b, String position);
}
