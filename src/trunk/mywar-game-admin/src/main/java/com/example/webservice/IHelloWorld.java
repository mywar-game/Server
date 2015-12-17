package com.example.webservice;

import javax.jws.WebService;
@WebService
public interface IHelloWorld {
   String sayHi(String veriyFile, String text);
}
