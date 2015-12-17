/**
 * 该类使用了Http client4.1的客户端，客户端可以使用默认的DefaultHttpClient创建连接客户端
 * 目的是为了android客户端可以少用依赖包
 */
package com.fantingame.game.gamecenter.partener.fantin.httpclient;
//
//import java.security.cert.CertificateException;
//import java.security.cert.X509Certificate;
//
//import javax.net.ssl.SSLContext;
//import javax.net.ssl.TrustManager;
//import javax.net.ssl.X509TrustManager;
//
//import org.apache.http.client.HttpClient;
//import org.apache.http.conn.scheme.PlainSocketFactory;
//import org.apache.http.conn.scheme.Scheme;
//import org.apache.http.conn.scheme.SchemeRegistry;
//import org.apache.http.conn.ssl.SSLSocketFactory;
//import org.apache.http.impl.client.DefaultHttpClient;
//import org.apache.http.impl.conn.PoolingClientConnectionManager;
//import org.apache.http.params.BasicHttpParams;
//import org.apache.http.params.HttpConnectionParams;
//import org.apache.http.params.HttpParams;
//
//import com.easou.sso.sdk.service.ClientConfig;
//
///**
// * HTTP客户端连接池
// * 
// * @author damon
// * @since 2012.10.16
// * @version 1.0
// * 
// */
public class HttpConnectionPoolManager {
//
//	//private static Log LOG = LogFactory.getLog(HttpConnectionPoolManager.class);
//
//	/** 配置参数 */
//	private static HttpParams httpParams;
//	/** 连接池 */
//	private static PoolingClientConnectionManager connectionManager;
//	/**
//	 * 最大连接数
//	 */
//	public final static int MAX_TOTAL_CONNECTIONS = 800;
//	/**
//	 * 获取连接的最大等待时间
//	 */
//	// public final static int WAIT_TIMEOUT = 60000;
//	/**
//	 * 每个路由最大连接数
//	 */
//	public final static int MAX_ROUTE_CONNECTIONS = 400;
//	/**
//	 * 连接超时时间
//	 */
//	public final static int CONNECT_TIMEOUT = 10000;
//	/**
//	 * 读取超时时间
//	 */
//	public final static int READ_TIMEOUT = 10000;
//	/** 请求管理类 */
//	public final static HttpClient httpClient;
//
//	private static SSLSocketFactory getTrustFactory() {
//		try {
//			SSLContext ctx = SSLContext.getInstance("TLS");
//			X509TrustManager tm = new X509TrustManager() {
//				public X509Certificate[] getAcceptedIssuers() {
//					return null;
//				}
//
//				public void checkClientTrusted(X509Certificate[] arg0,
//						String arg1) throws CertificateException {
//				}
//
//				public void checkServerTrusted(X509Certificate[] arg0,
//						String arg1) throws CertificateException {
//				}
//			};
//			ctx.init(null, new TrustManager[] { tm }, null);
//			return new SSLSocketFactory(ctx,
//					SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
//		} catch (Exception e) {
//			return null;
//		}
//	}
//
//	static {
//		// 最大连接数
//		int maxTotal = MAX_TOTAL_CONNECTIONS;
//		if (ClientConfig.getProperty("http.pool.maxTotal") != null) {// 从配置文件中获取
//			maxTotal = Integer.valueOf(ClientConfig
//					.getProperty("http.pool.maxTotal"));
//		}
//		// 最大路由数
//		int maxPerRoute = MAX_ROUTE_CONNECTIONS;
//		if (ClientConfig.getProperty("http.pool.maxPerRoute") != null) {// 从配置文件中获取
//			maxPerRoute = Integer.valueOf(ClientConfig
//					.getProperty("http.pool.maxPerRoute"));
//		}
//		// 套接字超时时间
//		int socketTimeout = READ_TIMEOUT;
//		if (ClientConfig.getProperty("http.socket.timeout") != null) {// 从配置文件中获取
//			socketTimeout = Integer.valueOf(ClientConfig
//					.getProperty("http.socket.timeout"));
//		}
//		// 设置连接超时时间
//		int connectTimeout = CONNECT_TIMEOUT;
//		if (ClientConfig.getProperty("http.connection.timeout") != null) {// 从配置文件中获取
//			connectTimeout = Integer.valueOf(ClientConfig
//					.getProperty("http.connection.timeout"));
//		}
//		/*if (LOG.isDebugEnabled()) {
//			LOG.debug("http pool maxTotal:" + maxTotal);
//			LOG.debug("http pool maxPerRoute:" + maxPerRoute);
//			LOG.debug("http socket timeout:" + socketTimeout);
//			LOG.debug("http.connection.timeout:" + connectTimeout);
//		}*/
//		httpParams = new BasicHttpParams();
//		SchemeRegistry schemeRegistry = new SchemeRegistry();
//		schemeRegistry.register(new Scheme("http", 80, PlainSocketFactory
//				.getSocketFactory()));
//		schemeRegistry.register(new Scheme("https", 443, getTrustFactory()));
//		connectionManager = new PoolingClientConnectionManager(schemeRegistry);
//		// 设置最大连接数
//		connectionManager.setMaxTotal(maxTotal);
//		// Increase default max connection per route to 20
//		connectionManager.setDefaultMaxPerRoute(maxPerRoute);
//		// Increase max connections for localhost:80 to 50
//		// HttpHost localhost = new HttpHost("locahost", 80);
//		// cm.setMaxPerRoute(new HttpRoute(localhost), MAX_ROUTE_CONNECTIONS);
//
//		// 设置连接超时时间
//		HttpConnectionParams.setConnectionTimeout(httpParams, connectTimeout);
//		// 设置读取超时时间
//		HttpConnectionParams.setSoTimeout(httpParams, socketTimeout);
//		// 初始化对象
//		httpClient = new DefaultHttpClient(connectionManager, httpParams);
//
//	}
//
//	public static HttpClient getHttpClient() {
//		return httpClient;
//	}
//
}