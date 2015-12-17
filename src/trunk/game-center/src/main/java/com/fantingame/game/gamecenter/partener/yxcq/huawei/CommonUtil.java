
package com.fantingame.game.gamecenter.partener.yxcq.huawei;

import java.util.Map;

import org.apache.log4j.Logger;

public abstract class CommonUtil
{
	
	private static final Logger logger = Logger.getLogger(CommonUtil.class);
	
    public static boolean rsaDoCheck(Map<String, Object> params, String sign, String publicKey)
    {
    	logger.info("HuaWei sign :" + sign);
    	logger.info("HuaWei publicKey :" + publicKey);
        //获取待签名字符串
        String content = RSA.getSignData(params);
        logger.info("HuaWei RSA.getSignData :" + content);
        //验签
        return RSA.doCheck(content, sign, publicKey);
    }
    
    
    public static void main(String[] args) {
    	String content = "amount=5.00&notifyTime=1406880409479&orderId=A2014080116044018292659E&payType=4&productName=50钻石&requestId=0110252014080100000020&result=0&userName=10086000000000293";
    	String sign = "UcyBuAELmgYg2T3zAswTGYiMgAKe4/xWXoKF/O2VKnO7YdLegJO0Zh81odxIjRWq/YKbZKM6sBEcLlmuENYYGA==";
    	String publicKey = "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBANVxYHeDKaJ7S3VwUyRHZZS0N6C7KEcsTQHQr8LT+IlPKuKS13/wL4lKpnsiOtCXRoObBD0iOqq6m47LXUKmKksCAwEAAQ==";
    	RSA.doCheck(content, sign, publicKey);
    }
}
