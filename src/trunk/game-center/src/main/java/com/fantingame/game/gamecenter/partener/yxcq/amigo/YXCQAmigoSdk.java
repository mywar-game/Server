package com.fantingame.game.gamecenter.partener.yxcq.amigo;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.InvalidKeyException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SignatureException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.security.spec.InvalidKeySpecException;
import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.CharEncoding;
import org.apache.log4j.Logger;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import com.fantingame.game.gamecenter.constant.ServiceReturnCode;
import com.fantingame.game.gamecenter.exception.ServiceException;
import com.fantingame.game.gamecenter.partener.BaseSdk;
import com.fantingame.game.gamecenter.partener.fantin.util.TradeInfo;
import com.fantingame.game.gamecenter.util.json.Json;

/**
 * 金立
 * 
 * @author yezp
 */
public class YXCQAmigoSdk extends BaseSdk {

	private static final Logger logger = Logger.getLogger(YXCQAmigoSdk.class);
	private static YXCQAmigoSdk ins;
	private static Properties prop;
	
	private final static String PROTOCOL_HEAD = "https://";
	private static String apiKey;
	private static String secretKey;
	private static String publicKey;
	private static String privateKey;
	private static String host;
	private static String port;
	private static String url;
	private static String method = "POST";
	private static String DELIVER_TYPE = "1";
	private static String gioneePayInit;
	// 成功响应状态码
	private static final String CREATE_SUCCESS_RESPONSE_CODE = "200010000";
	
	public static YXCQAmigoSdk instance() {
		synchronized (YXCQAmigoSdk.class) {
			if (ins == null) {
				ins = new YXCQAmigoSdk();
			}
		}
		
		return ins;
	}
	
	private YXCQAmigoSdk() {
		loadSdkProperties();
	}
	
	public void reload(){
		loadSdkProperties();
	}
	
	private void loadSdkProperties() {
		try {
			prop = PropertiesLoaderUtils.loadProperties(new ClassPathResource("sdk.properties"));
			apiKey = prop.getProperty("AmigoSdk.apiKey");
			secretKey = prop.getProperty("AmigoSdk.secretKey");
			port = prop.getProperty("AmigoSdk.port");
			url = prop.getProperty("AmigoSdk.url");
			host = prop.getProperty("AmigoSdk.host");
			publicKey = prop.getProperty("AmigoSdk.publicKey");
			privateKey = prop.getProperty("AmigoSdk.privateKey");
			gioneePayInit = prop.getProperty("AmigoSdk.gioneePayInit");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	public String verify(String amigoToken) {
		HttpsURLConnection httpURLConnection = null;
		OutputStream out;

		TrustManager[] tm = { new MyX509TrustManager() };
		try {
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			sslContext.init(null, tm, new java.security.SecureRandom());
			// 从上述SSLContext对象中得到SSLSocketFactory对象
			SSLSocketFactory ssf = sslContext.getSocketFactory();
			// URL sendUrl = new URL(PROTOCOL_HEAD + "id.gionee.com/account/verify.do");
			URL sendUrl = new URL(PROTOCOL_HEAD + host + ":" + port + url);
			logger.info(PROTOCOL_HEAD + host + ":" + port + url);
			
			// https://id.gionee.com/account/verify.do
			httpURLConnection = (HttpsURLConnection) sendUrl.openConnection();
			httpURLConnection.setSSLSocketFactory(ssf);
			httpURLConnection.setDoInput(true); // true表示允许获得输入流,读取服务器响应的数据,该属性默认值为true
			httpURLConnection.setDoOutput(true); // true表示允许获得输出流,向远程服务器发送数据,该属性默认值为false
			httpURLConnection.setUseCaches(false); // 禁止缓存
			int timeout = 30000;
			httpURLConnection.setReadTimeout(timeout); // 30秒读取超时
			httpURLConnection.setConnectTimeout(timeout); // 30秒连接超时
			String method = "POST";
			httpURLConnection.setRequestMethod(method);
			httpURLConnection.setRequestProperty("Content-Type",
					"application/json");
			httpURLConnection.setRequestProperty("Authorization",
					builderAuthorization());
			out = httpURLConnection.getOutputStream();
			out.write(amigoToken.getBytes());
			out.flush();
			out.close();
			InputStream in = httpURLConnection.getInputStream();
			ByteArrayOutputStream buffer = new ByteArrayOutputStream();
			byte[] buff = new byte[1024];
			int len = -1;
			while ((len = in.read(buff)) != -1) {
				buffer.write(buff, 0, len);
			}
			String strJson = buffer.toString();
			logger.info("Amigo login verify response: " + strJson);
			
			return strJson;
		} catch (KeyManagementException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (NoSuchProviderException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (MalformedURLException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static void main(String[] args) {
		StringBuilder contentBuffer = new StringBuilder();
		contentBuffer.append("api_key=" + "97A0E1517C184F7BAD3232F6C7C7648F" + "&");
		contentBuffer.append("close_time=" + null + "&");
		contentBuffer.append("create_time=" + "20140827121612" + "&");
		contentBuffer.append("deal_price=" + "5.00" + "&");
		contentBuffer.append("out_order_no=" + "0110342014082700000025" + "&");
		contentBuffer.append("pay_channel=" + "100" + "&");
		contentBuffer.append("submit_time=" + "20140827121611" + "&");
		contentBuffer.append("user_id=" + null);
		System.out.println(contentBuffer.toString());
		try {
			String p = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCuCXqNpDUkOX52WGEzexagYW06OEYS3BY9W9GiRDLfPkQJqKEdpZ7HBy3u685sQSX28LsXgFFiA1S5Rf8eSeP8Rivz4m1vBq1X62lNuHwOe/A1dSoKmDPIVh0zuQtSd2aRX/L9XMXkkSG9vHki/CUCHj5Ps5My7nznT2ne2MEfeQIDAQAB";
			boolean f = RSASignature.doCheck(contentBuffer.toString(), "oNCwkQpXK1QC8QeyU8mbrnOmj+LL9Xt5Co09E7HZvZ3qeiqfNPakvm7tVlbpXN3R1skfNfGbgAmBCDc+BLVLl6enTOto9adQq1EHj4btEsdg/Q9xXspRv8cw0IXG/DUHEIjSatLdDZxOxC6RrS4ITfsYOt6FjiL2V5Hrzn/6kYU=", p, CharEncoding.UTF_8);
			System.out.println(f);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean checkPayCallbackSign (AmigoPaymentObj payment) {
		
		
		StringBuilder contentBuffer = new StringBuilder();
		contentBuffer.append("api_key=" + payment.getApiKey() + "&");
		contentBuffer.append("close_time=" + payment.getCloseTime() + "&");
		contentBuffer.append("create_time=" + payment.getCreateTime() + "&");
		contentBuffer.append("deal_price=" + payment.getDealPrice() + "&");
		contentBuffer.append("out_order_no=" + payment.getOutOrderNo() + "&");
		contentBuffer.append("pay_channel=" + payment.getPayChannel() + "&");
		contentBuffer.append("submit_time=" + payment.getSubmitTime() + "&");
		contentBuffer.append("user_id=" + payment.getUserId());
		
		if (StringUtils.isBlank(payment.getSign())) 
			return false;
		
		try {
			logger.info("constructor sign:" + contentBuffer.toString() + " getSign:" + payment.getSign());
			return RSASignature.doCheck(contentBuffer.toString(), payment.getSign(), publicKey, CharEncoding.UTF_8);
		} catch (InvalidKeyException e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		} catch (SignatureException e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		return false;
	}
	
	static class MyX509TrustManager implements X509TrustManager {
		@Override
		public void checkClientTrusted(X509Certificate[] chain, String authType)
				throws CertificateException {
		}

		@Override
		public void checkServerTrusted(X509Certificate[] chain, String authType)
				throws CertificateException {
		}

		@Override
		public X509Certificate[] getAcceptedIssuers() {
			return null;
		}
	}
	
	private static String builderAuthorization() {		
		Long ts = System.currentTimeMillis() / 1000;
		String nonce = StringUtil.randomStr().substring(0, 8);
		String mac = CryptoUtility.macSig(host, port, secretKey, ts.toString(),
				nonce, method, url);
		mac = mac.replace("\n", "");
		
		StringBuilder authStr = new StringBuilder();
		authStr.append("MAC ");
		authStr.append(String.format("id=\"%s\"", apiKey));
		authStr.append(String.format(",ts=\"%s\"", ts));
		authStr.append(String.format(",nonce=\"%s\"", nonce));
		authStr.append(String.format(",mac=\"%s\"", mac));
		return authStr.toString();
	}

	static class CryptoUtility {
		private static final String MAC_NAME = "HmacSHA1";

		public static String macSig(String host, String port, String macKey,
				String timestamp, String nonce, String method, String uri) {
			// 1. build mac string
			// 2. hmac-sha1
			// 3. base64-encoded

			StringBuffer buffer = new StringBuffer();
			buffer.append(timestamp).append("\n");
			buffer.append(nonce).append("\n");
			buffer.append(method.toUpperCase()).append("\n");
			buffer.append(uri).append("\n");
			buffer.append(host.toLowerCase()).append("\n");
			buffer.append(port).append("\n");
			buffer.append("\n");
			String text = buffer.toString();

			byte[] ciphertext = null;
			try {
				ciphertext = hmacSHA1Encrypt(macKey, text);
			} catch (Throwable e) {
				e.printStackTrace();
				return null;
			}

			String sigString = Base64.encodeToString(ciphertext, Base64.DEFAULT);
			return sigString;
		}

		public static byte[] hmacSHA1Encrypt(String encryptKey,
				String encryptText) throws InvalidKeyException,
				NoSuchAlgorithmException {
			Mac mac = Mac.getInstance(MAC_NAME);
			mac.init(new SecretKeySpec(StringUtil.getBytes(encryptKey),
					MAC_NAME));
			return mac.doFinal(StringUtil.getBytes(encryptText));
		}

	}

	static class StringUtil {
		public static final String UTF8 = "UTF-8";
		private static final byte[] BYTEARRAY = new byte[0];

		public static boolean isNullOrEmpty(String s) {
			if (s == null || s.isEmpty() || s.trim().isEmpty())
				return true;
			return false;
		}

		public static String randomStr() {
			return CamelUtility.uuidToString(UUID.randomUUID());
		}

		public static byte[] getBytes(String value) {
			return getBytes(value, UTF8);
		}

		public static byte[] getBytes(String value, String charset) {
			if (isNullOrEmpty(value))
				return BYTEARRAY;
			if (isNullOrEmpty(charset))
				charset = UTF8;
			try {
				return value.getBytes(charset);
			} catch (UnsupportedEncodingException e) {
				return BYTEARRAY;
			}
		}
	}

	static class CamelUtility {
		public static final int SizeOfUUID = 16;
		private static final int SizeOfLong = 8;
		private static final int BitsOfByte = 8;
		private static final int MBLShift = (SizeOfLong - 1) * BitsOfByte;

		private static final char[] HEX_CHAR_TABLE = { '0', '1', '2', '3', '4',
				'5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

		public static String uuidToString(UUID uuid) {
			long[] ll = { uuid.getMostSignificantBits(),
					uuid.getLeastSignificantBits() };
			StringBuilder str = new StringBuilder(SizeOfUUID * 2);
			for (int m = 0; m < ll.length; ++m) {
				for (int i = MBLShift; i > 0; i -= BitsOfByte)
					formatAsHex((byte) (ll[m] >>> i), str);
				formatAsHex((byte) (ll[m]), str);
			}
			return str.toString();
		}

		public static void formatAsHex(byte b, StringBuilder s) {
			s.append(HEX_CHAR_TABLE[(b >>> 4) & 0x0F]);
			s.append(HEX_CHAR_TABLE[b & 0x0F]);
		}
	}
	
	public void createServerOrder(TradeInfo info) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");   
		String submitTime = sdf.format(System.currentTimeMillis());  
		
		Order order = new Order(info.getTradeId(), info.getPayerId(), info.getTradeName(), apiKey, new BigDecimal(info.getReqFee()), new BigDecimal(info.getReqFee()), submitTime, null, null);
		logger.info("AmigoSdk order :" + order);
		String requestBody = null;
		try {
			requestBody = PayUtil.wrapCreateOrder(order, privateKey, DELIVER_TYPE);
		} catch (Exception e) {
			e.printStackTrace();
		}

		String response = null;
		try {
			response = HttpUtils.post(gioneePayInit, requestBody);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ServiceException(ServiceReturnCode.FAILD, "订单创建失败");
		}

		Map<String, Object> jsonMap = Json.toObject(response, Map.class);
		logger.info("AmigoSdk jsonMap = " + jsonMap);

		if (CREATE_SUCCESS_RESPONSE_CODE.equals((String) jsonMap.get("status"))) {

			String orderNo = (String) jsonMap.get("order_no");
			if (StringUtils.isBlank(orderNo)) {
				logger.error("AmigoSdk orderNo 为空");
				return;
			}
//			String apiKey = (String) jsonMap.get("api_key");
			String outOrderNo = (String) jsonMap.get("out_order_no");
			String submitTime1 = (String) jsonMap.get("submit_time");
			info.setExectInfo(outOrderNo + ":" + submitTime1);
			logger.info("AmigoSdk outOrderNo = " + outOrderNo + " submitTime = " + submitTime1);
			return;
		} else {
			throw new ServiceException(ServiceReturnCode.FAILD, "订单创建失败");
		}
	}
}
