package com.fantingame.game.gamecenter.partener.amigo;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.lang3.CharEncoding;

public class HttpUtils {

	private static final int CONNECTION_TIMEOUT = 5000;
	private static final int READ_TIMEOUT = 5000;

	public static String post(String reqUrl, String body) throws Exception {
		String invokeUrl = reqUrl;
		URL serverUrl = new URL(invokeUrl);
		HttpURLConnection conn = (HttpURLConnection) serverUrl.openConnection();
//		HttpsURLConnection conn = (HttpsURLConnection) serverUrl.openConnection();
		conn.setConnectTimeout(CONNECTION_TIMEOUT);
		conn.setReadTimeout(READ_TIMEOUT);
		conn.setRequestMethod("POST");
		conn.setDoOutput(true);
		conn.connect();

		conn.getOutputStream().write(body.getBytes(CharEncoding.UTF_8));
		BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		StringBuffer buffer = new StringBuffer();
		String line = "";
		while ((line = in.readLine()) != null) {
			buffer.append(line);
		}
		in.close();
		String response = buffer.toString();
		conn.disconnect();
		return response;
	}
}