package com.framework.server.http;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.framework.constant.SystemConstant;
import com.framework.server.bridge.BridgeEntry;
import com.framework.server.bridge.ChannelBridgeEntry;
import com.framework.server.channle.HttpChannel;
import com.framework.servicemanager.ServiceCacheFactory;



public class HttpServer extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BridgeEntry bridgeEntry;

	/**
	 * Constructor of the object.
	 */
	public HttpServer() {
		super();
	}

	public void init() throws ServletException {
		bridgeEntry = ServiceCacheFactory.getServiceCache().getService(ChannelBridgeEntry.class);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		InputStream inputStream = request.getInputStream();
		ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
		byte[] buff = new byte[256];
		int size = 0;
		while ((size = inputStream.read(buff, 0, 256)) > 0) {
			byteStream.write(buff, 0, size);
		}
		byte[] datas = byteStream.toByteArray();
		HttpChannel httpChannel = new HttpChannel(response.getOutputStream());
		httpChannel.setClientType(SystemConstant.JAVA_CLIENT);
		bridgeEntry.receivedData(httpChannel, datas);
		httpChannel.close();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	public void destroy() {
		super.destroy();
	}
}
