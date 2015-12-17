package com.fantingame.game.gamecenter.partener.lenovo;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.fantingame.game.gamecenter.partener.BaseSdk;
import com.fantingame.game.gamecenter.util.Base64;
import com.fantingame.game.gamecenter.util.EncryptUtil;
import com.fantingame.game.gamecenter.util.RSAUtil;
import com.fantingame.game.gamecenter.util.UrlRequestUtils;
import com.fantingame.game.gamecenter.util.UrlRequestUtils.Mode;

public class LenovoSdk extends BaseSdk {
	private static final Logger logger = Logger.getLogger(LenovoSdk.class);
	private static LenovoSdk ins;

	private static Properties prop;

	private final static String PROTOCOL_HEAD = "http://";
	private String host;
	private String realm;
	private String appKey;
	private String payCallback;
	private String partnerId;

	public static LenovoSdk instance() {
		synchronized (LenovoSdk.class) {
			if (ins == null) {
				ins = new LenovoSdk();
			}
		}

		return ins;
	}

	private LenovoSdk() {
		 loadSdkProperties();
	}

	public void reload(){
		loadSdkProperties();
	}
	
	private void loadSdkProperties() {
		try {
			prop = PropertiesLoaderUtils.loadProperties(new ClassPathResource("sdk.properties"));
			host = prop.getProperty("LenovoSdk.host");
			realm = prop.getProperty("LenovoSdk.realm");
			appKey = prop.getProperty("LenovoSdk.appKey");
			payCallback = prop.getProperty("LenovoSdk.payCallback");
			partnerId = prop.getProperty("LenovoSdk.partnerId"); 
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 获取用户信息
	 * 
	 * @param lpsust
	 * @return
	 */
	public Map<String, String> getAccount(String lpsust) {
		String url = PROTOCOL_HEAD + host + "/interserver/authen/1.2/getaccountid";
		Map<String, String> paraMap = new HashMap<String, String>();
		paraMap.put("lpsust", lpsust);
		paraMap.put("realm", realm);
		String result = UrlRequestUtils.execute(url, paraMap, Mode.GET);
		logger.info("getAccount result:" + result);
		try {
			return parseLogin(result);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	protected Map<String, String> parseLogin(String xml) throws ParserConfigurationException, SAXException, IOException {
		Map<String, String> map = new HashMap<String, String>();
		InputStream in = null;
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			in = new ByteArrayInputStream(xml.getBytes("utf8"));
			Document document = builder.parse(in);
			Element root = document.getDocumentElement();
			NodeList bookNodes1 = root.getElementsByTagName("AccountID");
			NodeList bookNodes2 = root.getElementsByTagName("Username");
			Element el1 = (Element) bookNodes1.item(0);
			Element el2 = (Element) bookNodes2.item(0);
			String accountId = getElementValue(el1);
			String username = getElementValue(el2);
			map.put("accountId", accountId);
			map.put("username", username);
		} finally {
			IOUtils.closeQuietly(in);
		}
		return map;
	}

	/**
	 * 
	 * @param transdata
	 *            同步过来的transdata数据
	 * @param sign
	 *            同步过来的sign数据
	 * @param key
	 *            应用的密钥(商户可从商户自服务系统获取)
	 * @return 验证签名结果 true:验证通过 false:验证失败
	 */
	public boolean validSign(String transdata, String sign) {
		try {
			String md5Str = EncryptUtil.getMD5(transdata);

			String decodeBaseStr = Base64.decode(appKey);

			String[] decodeBaseVec = decodeBaseStr.replace('+', '#').split("#");

			String privateKey = decodeBaseVec[0];
			String modkey = decodeBaseVec[1];

			String reqMd5 = RSAUtil.decrypt(sign, new BigInteger(privateKey),
					new BigInteger(modkey));

			if (md5Str.equals(reqMd5)) {
				return true;
			} else {
				return false;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;

	}
	
	private String getElementValue(Element el) {
		NodeList childs = el.getChildNodes();
		return childs.item(0).getNodeValue();
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getRealm() {
		return realm;
	}

	public void setRealm(String realm) {
		this.realm = realm;
	}

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + "<IdentityInfo><AccountID>USS-0540</AccountID>" + "<Username>你好么</Username>" + "</IdentityInfo>";
		LenovoSdk sdk = new LenovoSdk();
	}

	public String getPayCallback() {
		return payCallback;
	}

	public void setPayCallback(String payCallback) {
		this.payCallback = payCallback;
	}

	public String getPartnerId() {
		return partnerId;
	}
}
