/**
 * 
 */
package com.fantingame.game.gamecenter.partener.kuaiyong;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import org.apache.commons.lang.StringUtils;
/**
 * RSA签名验签类
 */
public class RSASignature{
	

	public static final String  SIGN_ALGORITHMS = "SHA1WithRSA";
	/**
	* RSA签名
	* @param content 待签名数据
	* @param privateKey 商户私钥
	* @param encode 字符集编码
	* @return 签名值
	*/
	public static String sign(String content, String privateKey, String encode)
	{
		String charset = "utf-8";
		if(!StringUtils.isBlank(encode)){
		    charset=encode;
		}
        try 
        {
        	PKCS8EncodedKeySpec priPKCS8 	= new PKCS8EncodedKeySpec( Base64.decode(privateKey) ); 
        	
        	KeyFactory keyf 				= KeyFactory.getInstance("RSA");
        	PrivateKey priKey 				= keyf.generatePrivate(priPKCS8);

            java.security.Signature signature = java.security.Signature.getInstance(SIGN_ALGORITHMS);

            signature.initSign(priKey);
            signature.update( content.getBytes(charset));

            byte[] signed = signature.sign();
            
            return Base64.encode(signed);
        }
        catch (Exception e) 
        {
        	e.printStackTrace();
        }
        
        return null;
    }
	
	public static String sign(String content, String privateKey)
	{
        try 
        {
        	PKCS8EncodedKeySpec priPKCS8 	= new PKCS8EncodedKeySpec( Base64.decode(privateKey) ); 
        	
        	KeyFactory keyf 				= KeyFactory.getInstance("RSA");
        	PrivateKey priKey 				= keyf.generatePrivate(priPKCS8);

            java.security.Signature signature = java.security.Signature.getInstance(SIGN_ALGORITHMS);

            signature.initSign(priKey);
            signature.update( content.getBytes());

            byte[] signed = signature.sign();
            
            return Base64.encode(signed);
        }
        catch (Exception e) 
        {
        	e.printStackTrace();
        }
        
        return null;
    }
	
	/**
	* RSA验签名检查
	* @param content 待签名数据
	* @param sign 签名值
	* @param publicKey 支付宝公钥
	* @param encode 字符集编码
	* @return 布尔值
	*/
	public static boolean doCheck(String content, String sign, String publicKey,String encode)
	{
	    String charset = "utf-8";
        if(!StringUtils.isBlank(encode)){
            charset=encode;
        }
		try 
		{
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
	        byte[] encodedKey = Base64.decode(publicKey);
	        PublicKey pubKey = keyFactory.generatePublic(new X509EncodedKeySpec(encodedKey));

		
			java.security.Signature signature = java.security.Signature
			.getInstance(SIGN_ALGORITHMS);
		
			signature.initVerify(pubKey);
			signature.update( content.getBytes(charset) );
		
			boolean bverify = signature.verify( Base64.decode(sign) );
			return bverify;
			
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		return false;
	}
	
	public static boolean doCheck(String content, String sign, String publicKey)
	{
		try 
		{
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
	        byte[] encodedKey = Base64.decode(publicKey);
	        PublicKey pubKey = keyFactory.generatePublic(new X509EncodedKeySpec(encodedKey));

		
			java.security.Signature signature = java.security.Signature
			.getInstance(SIGN_ALGORITHMS);
		
			signature.initVerify(pubKey);
			signature.update( content.getBytes() );
		
			boolean bverify = signature.verify( Base64.decode(sign) );
			return bverify;
			
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		return false;
	}
  public static void main(String[] args){  
	  String pubkey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDQl074HcPTQ+cOyoUaL4cznY8yt5WBg76oIUiaqO+eOG0a31J08e9jqU1kEs9Ik2yjU+VVMQdZpYEoewg/XmKhtj4GvvClvkvmMxZUXTP0pWLG8EIX0seHEt+9oECwmVsfJyVvXl2yAc0FKLRCmGfjBko38YRa0NJ4iW871toPnQIDAQAB";
	  String prikey = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBANCXTvgdw9ND5w7KhRovhzOdjzK3lYGDvqghSJqo7544bRrfUnTx72OpTWQSz0iTbKNT5VUxB1mlgSh7CD9eYqG2Pga+8KW+S+YzFlRdM/SlYsbwQhfSx4cS372gQLCZWx8nJW9eXbIBzQUotEKYZ+MGSjfxhFrQ0niJbzvW2g+dAgMBAAECgYBbhxlCrCTiTfhUOC9Bdzt3PZq5IW873mhJdBlcq+ZA4feWBAo1gHePnplz4QENkorn/Ac0Y4gbIRFUhu/uhmb2TZ0OeoaLEYpgXQya6v6YsUrtc5SNNnl5YyWD1Il0J8x5qo69Tx1zJ2K3JJsBX6XqLvr/KwryCz4nt0gFclQMLQJBAOxdnAJSA1t0YAnLTcFbtBOTn+oBG+U42k4GfV77wP0gIdl1Hj60FcYSMpnwm1Z0c71EkiIRaz59fgy6HoFB2q8CQQDh6xApLDNNz5xi4a1+G1wRw3vkrwaZ9/iZ4PFT6G1gxqtgXKV/9aZ7L4jGBxZpz/O94R7oBuFhPll4MkgbMR1zAkBe3UcjC0V7rB5rb+Q6KZMBlGtHIMDQY9wCGsqwl3wiW4YaX84OvnpKr70NWasOrNhS6zV3ZORVBUrU0tIxcHvTAkEAmx+UkTDQaXVMpHjhb93JckkQIQ4tMjuab86M5liKAhFoS1pSKXK7RYp2F8N2GGG5s3+IgTl4OBVfybB84m7vEQJBAMLq5hGVV3LJHPA+gnxmZF0n4kkwNlDyCdWGBR7BZ9e2aSMdCa6/tXgnRlkaXpZTxxXnxGG5DGcCRe/WI9p2dEc=";
	  
	  //程序测试字符串   
	  String encryptStr = "dealseq=20130222105228127&fee=0.01&notify_data=V/5/99ubmARZ0uOT/KOBrOdns/91mm23mAGDvfvgJjhG36/R82QaaEQrD7+vIR7850Hx03wZr7QcdIy2CGiEB+p5lgKDjU8FAQAjwLxqIWNT0T8ugdwGiMI3pC/SKlY3I0mKtoN78YNIgFLWA5QM0xSWvjls5p7hLbs4cZz6Oe4=&orderid=130222-23-10523450458&payresult=0&subject=充值100金&uid=放着我来&v=1";

  try {
	  	String dataEncrypted = RSASignature.sign(encryptStr, prikey);
	  	System.out.println("签名结果:" + dataEncrypted);  
	  	if(RSASignature.doCheck(encryptStr, dataEncrypted, pubkey)){
	  		System.out.println("验证通过。");
	  		
  		}else{
  			System.out.println("验证失败。");
  		}
      
  } catch (Exception e) {  
      System.err.println(e.getMessage());  
  }  
}  
}
