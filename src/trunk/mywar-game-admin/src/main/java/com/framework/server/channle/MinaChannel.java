package com.framework.server.channle;

import org.apache.mina.core.session.IoSession;



public class MinaChannel extends AbstractChnnel {

	private final IoSession ioSession;

	private boolean isClosed = false;

	public MinaChannel(IoSession ioSession) {
		this.ioSession = ioSession;
	}

	public synchronized void close() {
		if (isClosed) {
			return;
		}
		isClosed = true;
		ioSession.close(true);
	}

	public boolean isClosed() {
		return isClosed;
	}

	public synchronized void write(byte[] datas) {
		if (datas == null  || datas.length == 0) {
			return;
		}
		if (!isClosed) {
			ioSession.write(datas);
		}
	}
}
