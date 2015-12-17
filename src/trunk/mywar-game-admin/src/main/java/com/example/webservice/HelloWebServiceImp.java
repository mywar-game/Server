package com.example.webservice;

import javax.jws.WebService;
import javax.xml.namespace.QName;
import javax.xml.ws.Endpoint;

import com.framework.log.LogSystem;

@WebService(endpointInterface = "com.example.webservice.HelloWebService")
public class HelloWebServiceImp {

	public String sayHi(String text) {
		// TODO Auto-generated method stub
		text = text + "ggg";
		LogSystem.info(text);
		return text;
	}

	public void shutdown() {
		// TODO Auto-generated method stub
		
	}

	public void startup() {
		// TODO Auto-generated method stub
		HelloWebServiceImp implementor = new HelloWebServiceImp();
		String address = "http://localhost:9090/helloWorld";
		Endpoint.publish(address, implementor);
		System.out.println("Starting WebServer::" + "http://localhost:9090/helloWorld");
	}

  public static void main(String[] args) {
//	  JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
//	  factory.getInInterceptors().add(new LoggingInInterceptor());
//	  factory.getOutInterceptors().add(new LoggingOutInterceptor());
//	  factory.setServiceClass(ITest2.class);
//	  factory.setAddress("http://192.168.110.188:9090/test");
//	  ITest2 client = (ITest2)factory.create();
//	  client.test("111");
//	  String reply = client.sayHi("veryFyle", "HI");
//	  System.out.println("Server said: " + reply);
//	  System.exit(0); 
	  QName name = new QName("", "");
}
}
