package com.fantingame.game.gamecenter.partener.mzw;

import java.io.IOException;
import java.net.URLDecoder;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import com.fantingame.game.gamecenter.partener.BaseSdk;
import com.fantingame.game.gamecenter.util.UrlRequestUtils;
import com.fantingame.game.gamecenter.util.UrlRequestUtils.Mode;
import com.fantingame.game.gamecenter.util.json.Json;

/**
 * 拇指玩SDK
 * 
 * @author yezp
 */
public class MzwSdk extends BaseSdk {

	private static final Logger logger = Logger.getLogger(MzwSdk.class);
	
	private final static String PROTOCOL_HEAD = "http://";
	private static MzwSdk ins;
	private static Properties prop;
	private static String appkey;
	private static String testSign;
	private static String host;
	
	public static MzwSdk instance() {
		synchronized (MzwSdk.class) {
			if (ins == null) {
				ins = new MzwSdk();
			}
		}
		
		return ins;
	}
	
	private MzwSdk() {
		loadSdkProperties();
	}

	public void reload() {
		loadSdkProperties();
	}
	
	private void loadSdkProperties() {
		try {
			prop = PropertiesLoaderUtils.loadProperties(new ClassPathResource("sdk.properties"));
			appkey = prop.getProperty("MzwSdk.appkey");
			host = prop.getProperty("MzwSdk.host");
			testSign = prop.getProperty("MzwSdk.testSign");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	public MzwUserInfo getUserInfo(String token) {
		String url = host;
		Map<String, String> params = new HashMap<String, String>();
		params.put("token", token);
		params.put("appkey", appkey);
		
		String jsonStr = UrlRequestUtils.execute(url, params, Mode.GET);
		logger.info("MzwUserInfo login response:" + jsonStr);
		return Json.toObject(jsonStr, MzwUserInfo.class);
	}

	public boolean checkPayCallbackSign(MzwPaymentObj obj) {		
		try {
			String md5Sign = getSign(obj);
			logger.info("Mzw sign : " + obj.getSign());
			logger.info("Mzw getMd5 : " + md5Sign);
			if (obj.getSign().equals(md5Sign))
				return true;
			
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public String getSign(MzwPaymentObj obj) throws Throwable{
		
		StringBuilder builder = new StringBuilder();
		builder.append(obj.getAppkey());
		builder.append(obj.getOrderID());
		builder.append(obj.getProductName());
		builder.append(obj.getProductDesc());
		builder.append(obj.getProductID());
//		builder.append(URLDecoder.decode(productName, "UTF-8"));
//		builder.append(URLDecoder.decode(productDesc, "UTF-8"));
//		builder.append(URLDecoder.decode(productID, "UTF-8"));
		builder.append(obj.getMoney());
		builder.append(obj.getUid());
		builder.append(obj.getExtern());
		builder.append(testSign);
		
		String content = builder.toString();
		logger.info("Mzw before md5 content : " + content);
		return getMd5(content, "UTF-8");
		
	}
	
	public static void main(String[] args) {
		//"sign":"0110332014082600000036"
		MzwPaymentObj o = new MzwPaymentObj();
		o.setAppkey("b0260967bff880bee7632e52233cbb33");
		o.setOrderID("3998aa670ce448269e8c6decba492eef");
		String s1 = "50钻石";
		
		o.setProductName(URLDecoder.decode(s1));
		o.setProductDesc(URLDecoder.decode(s1));
		o.setProductID("0110332014082600000036");
		o.setMoney("5");
		o.setUid("6189671");
		o.setExtern("0110332014082600000036");
		try {
			System.out.println(MzwSdk.instance().getSign(o));
			// c4f4e6536daf3eb66d154138ba28e404
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	
	public String getMd5( String value, String charset ) {
		try
		{
			MessageDigest md5 = MessageDigest.getInstance( "MD5" );
			md5.update( value.getBytes( charset ) );
			return toHexString( md5.digest() );
		}
		catch( Throwable e )
		{
			e.printStackTrace();
		}
		return "";
	}
	
	private final static char	HEX_DIGITS[]	= { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

	public String toHexString( byte[] b )
	{
		StringBuilder sb = new StringBuilder( b.length * 2 );
		for( int i = 0; i < b.length; i++ )
		{
			sb.append( HEX_DIGITS[ ( b[ i ] & 0xf0 ) >>> 4 ] );
			sb.append( HEX_DIGITS[ b[ i ] & 0x0f ] );
		}
		return sb.toString();
	}
}
