package com.fantingame.game.gamecenter.partener.ppassistant;

import java.io.BufferedReader;  
import java.io.IOException;  
import java.io.InputStream;  
import java.io.InputStreamReader;
import java.security.InvalidKeyException;  
import java.security.KeyFactory;  
import java.security.KeyPair;  
import java.security.KeyPairGenerator;  
import java.security.NoSuchAlgorithmException;  
import java.security.SecureRandom;  
import java.security.interfaces.RSAPrivateKey;  
import java.security.interfaces.RSAPublicKey;  
import java.security.spec.InvalidKeySpecException;  
import java.security.spec.X509EncodedKeySpec;  

import javax.crypto.BadPaddingException;  
import javax.crypto.Cipher;  
import javax.crypto.IllegalBlockSizeException;  
import javax.crypto.NoSuchPaddingException;  

import org.apache.commons.codec.binary.Base64;
 

public class PPAssistantRSAEncrypt {  
 
	//默认公钥(openssl)

    public static final String DEFAULT_PUBLIC_KEY=   
		"MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA29+5kpEhWMuoxqCQe6J0opizgosPp9n2VoQ0KT5QmAwlJuH112iUSF2f6wB20pm1HFPBZgv0AObznSVU++34ewZ02mrgX5lzNokk1ISSXEJIun9e4lQJe0N0lx4uySNtEWyJad2ZTTf5J9CHms6BtoWCrVccKzhNlhNKXBB6BG/dlaGl7DKIZjpRYecL7Y3hU83HGjaf6z0KoGHKmlnpXUbeceLQoKZ4zjwwfI20AWU7h2LEiWaDpjlN+5LxOyO2tULfTU6dlq13qD1DsPUYYmSdFpFVszvyJyOk1Lxc0DN6BGKjgk0B42yiawXMmZdKAkcqWWhZyfy++V4TAtBuowIDAQAB";
  
  
    /** 
     * 私钥 
     */  
    private RSAPrivateKey privateKey;  
  
    /** 
     * 公钥 
     */  
    private RSAPublicKey publicKey;  
      
    /** 
     * 字节数据转字符串专用集合 
     */  
    private static final char[] HEX_CHAR= {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};  
    
    /** 
     * 获取私钥 
     * @return 当前的私钥对象 
     */  
    public RSAPrivateKey getPrivateKey() {  
        return privateKey;  
    }  
    
    /** 
     * 获取公钥 
     * @return 当前的公钥对象 
     */  
    public RSAPublicKey getPublicKey() {  
        return publicKey;  
    }  
  
    /** 
     * 随机生成密钥对 
     */  
    public void genKeyPair(){  
        KeyPairGenerator keyPairGen= null;  
        try {  
            keyPairGen= KeyPairGenerator.getInstance("RSA");  
        } catch (NoSuchAlgorithmException e) {  
            e.printStackTrace();  
        }  
        keyPairGen.initialize(1024, new SecureRandom());  
        KeyPair keyPair= keyPairGen.generateKeyPair();  
        this.privateKey= (RSAPrivateKey) keyPair.getPrivate();  
        this.publicKey= (RSAPublicKey) keyPair.getPublic();  
    }  
  
    /** 
     * 从文件中输入流中加载公钥 
     * @param in 公钥输入流 
     * @throws Exception 加载公钥时产生的异常 
     */  
    public void loadPublicKey(InputStream in) throws Exception{
        try {  
            BufferedReader br= new BufferedReader(new InputStreamReader(in));  
            String readLine= null;  
            StringBuilder sb= new StringBuilder();  
            while((readLine= br.readLine())!=null){  
                if(readLine.charAt(0)=='-'){  
                    continue;  
                }else{  
                    sb.append(readLine);  
                    sb.append('\r');  
                }  
            }  
            loadPublicKey(sb.toString());  
        } catch (IOException e) {  
            throw new Exception("公钥数据流读取错误");  
        } catch (NullPointerException e) {  
            throw new Exception("公钥输入流为空");  
        }  
    }  
  
  
    /** 
     * 从字符串中加载公钥 
     * @param publicKeyStr 公钥数据字符串 
     * @throws Exception 加载公钥时产生的异常 
     */  
    public void loadPublicKey(String publicKeyStr) throws Exception{  
    	//System.out.println("publicKeyStr:"+ publicKeyStr);
        try {  
            byte[] buffer= Base64.decodeBase64(publicKeyStr);  
            KeyFactory keyFactory= KeyFactory.getInstance("RSA");  
            X509EncodedKeySpec keySpec= new X509EncodedKeySpec(buffer);  
            this.publicKey= (RSAPublicKey) keyFactory.generatePublic(keySpec);  
        } catch (NoSuchAlgorithmException e) {  
            throw new Exception("无此算法");  
        } catch (InvalidKeySpecException e) {  
            throw new Exception("公钥非法");  
        } catch (NullPointerException e) {  
            throw new Exception("公钥数据为空");  
        }  
    }  
  
    /** 
     * 公钥加密过程 
     * @param publicKey 公钥 
     * @param plainTextData 明文数据 
     * @return 
     * @throws Exception 加密过程中的异常信息 
     */  
    public byte[] encrypt(RSAPublicKey publicKey, byte[] plainTextData) throws Exception{  
        if(publicKey== null){  
            throw new Exception("加密公钥为空, 请设置");  
        }  
        Cipher cipher= null;  
        try {
        	//使用默认RSA
            cipher= Cipher.getInstance("RSA");
            //cipher= Cipher.getInstance("RSA", new BouncyCastleProvider());    
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);  
            byte[] output= cipher.doFinal(plainTextData);  
            return output;  
        } catch (NoSuchAlgorithmException e) {  
            throw new Exception("无此加密算法");  
        } catch (NoSuchPaddingException e) {  
            e.printStackTrace();  
            return null;  
        }catch (InvalidKeyException e) {  
            throw new Exception("加密公钥非法,请检查");  
        } catch (IllegalBlockSizeException e) {  
            throw new Exception("明文长度非法");  
        } catch (BadPaddingException e) {  
            throw new Exception("明文数据已损坏");  
        }  
    }  

    /** 
     * 公钥解密过程 
     * @param publicKey 公钥 
     * @param cipherData 密文数据 
     * @return 明文 
     * @throws Exception 解密过程中的异常信息 
     */  
    public byte[] decrypt(RSAPublicKey publicKey, byte[] cipherData) throws Exception{  
        if (publicKey== null){  
            throw new Exception("解密公钥为空, 请设置");  
        }  
        Cipher cipher= null;  
        try {  
        	//使用默认RSA
            cipher= Cipher.getInstance("RSA");
            //cipher= Cipher.getInstance("RSA", new BouncyCastleProvider());  
            cipher.init(Cipher.DECRYPT_MODE, publicKey);  
            byte[] output= cipher.doFinal(cipherData);  
            return output;  
        } catch (NoSuchAlgorithmException e) {  
            throw new Exception("无此解密算法");  
        } catch (NoSuchPaddingException e) {  
            e.printStackTrace();  
            return null;  
        }catch (InvalidKeyException e) {  
            throw new Exception("解密公钥非法,请检查");  
        } catch (IllegalBlockSizeException e) {  
            throw new Exception("密文长度非法");  
        } catch (BadPaddingException e) {  
            throw new Exception("密文数据已损坏");  
        }         
    }  
  
      
    /** 
     * 字节数据转十六进制字符串 
     * @param data 输入数据 
     * @return 十六进制内容 
     */  
    public static String byteArrayToString(byte[] data){  
        StringBuilder stringBuilder= new StringBuilder();  
        for (int i=0; i<data.length; i++){  
            //取出字节的高四位 作为索引得到相应的十六进制标识符 注意无符号右移   
            stringBuilder.append(HEX_CHAR[(data[i] & 0xf0)>>> 4]);  
            //取出字节的低四位 作为索引得到相应的十六进制标识符   
            stringBuilder.append(HEX_CHAR[(data[i] & 0x0f)]);  
            if (i<data.length-1){  
                stringBuilder.append(' ');  
            }  
        }  
        return stringBuilder.toString();  
    }  
  
  
    public static void main(String[] args){  
    	PPAssistantRSAEncrypt rsaEncrypt= new PPAssistantRSAEncrypt();  
        //rsaEncrypt.genKeyPair();   
  
        //加载公钥   
        try {  
            rsaEncrypt.loadPublicKey(PPAssistantRSAEncrypt.DEFAULT_PUBLIC_KEY);  
            System.out.println("加载公钥成功");  
        } catch (Exception e) {  
            System.err.println(e.getMessage());  
            System.err.println("加载公钥失败");  
        }  
        
        //文档测试数据 
        String testDataStr = "1MoPm3CUVVFq uHTQZ3u6S2DhZ0fJ5wxuBk8WKM5dLf4ei8fZ7IZIUtNvhnRz4Khl7bMClzwmzqzsMaUwdb6HtJVSU iqPogMs7sghYtRo gRTmnGVy7FBvMSuTyKokpVPWqy8r1fgKfO/UwQX/83JNMhVOoHAWlIc7TSihMSkhvY8bDd03ZnyZofHli7KH5eXdRcuZUFA6rz4KtOXqWeKrFBpvmXcmZV9jKcK3z0GJw6vkEbuHz55gbTRhDHwIv6C DyFeiM4AyZvPxRIT7B  c7vFRcTLwEVPFemfyYQpSmZBoqivZ7/6QuoWEXwLLO/bWSrUDmrQhru5imNakPg==";    
        //String testDataStr = "1MoPm3CUVVFq uHTQZ3u6S2DhZ0fJ5wxuBk8WKM5dLf4ei8fZ7IZIUtNvhnRz4Khl7bMClzwmzqzsMaUwdb6HtJVSU iqPogMs7sghYtRo gRTmnGVy7FBvMSuTyKokpVPWqy8r1fgKfO/UwQX/83JNMhVOoHAWlIc7TSihMSkhvY8bDd03ZnyZofHli7KH5eXdRcuZUFA6rz4KtOXqWeKrFBpvmXcmZV9jKcK3z0GJw6vkEbuHz55gbTRhDHwIv6C DyFeiM4AyZvPxRIT7B  c7vFRcTLwEVPFemfyYQpSmZBoqivZ7/6QuoWEXwLLO/bWSrUDmrQhru5imNakPg==";
        try {
            byte[] dcDataStr = Base64.decodeBase64(testDataStr);
            byte[] plainData = rsaEncrypt.decrypt(rsaEncrypt.getPublicKey(), dcDataStr);  
            System.out.println("文档测试数据明文长度:" + plainData.length);  
            System.out.println(PPAssistantRSAEncrypt.byteArrayToString(plainData));  
            System.out.println(new String(plainData));
            
        } catch (Exception e) {  
            System.err.println(e.getMessage());  
        }  
    }  
}  