package com.framework.server.channle;

import java.io.IOException;
import java.io.OutputStream;

import com.framework.log.LogSystem;


public class HttpChannel extends AbstractChnnel {

	private final OutputStream outputStream;

	public HttpChannel(OutputStream outputStream) {
		this.outputStream = outputStream;
	}
	
	public void close() {
	}

	public boolean isClosed() {
		return false;
	}

	public void write(byte[] datas) {
		if (datas == null  || datas.length == 0) {
			return;
		}
		try {
			outputStream.write(datas);
			outputStream.close();
		} catch (IOException e) {
			LogSystem.error(e, "");
		}
	}

}
