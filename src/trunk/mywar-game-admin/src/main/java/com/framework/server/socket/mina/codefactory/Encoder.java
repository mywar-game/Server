package com.framework.server.socket.mina.codefactory; 

import org.apache.mina.core.buffer.IoBuffer; 
import org.apache.mina.core.session.IoSession; 
import org.apache.mina.filter.codec.ProtocolEncoderAdapter; 
import org.apache.mina.filter.codec.ProtocolEncoderOutput; 

public class Encoder extends ProtocolEncoderAdapter {

	public void encode(IoSession session, Object message, 
			ProtocolEncoderOutput out) throws Exception {
		// TODO Auto-generated method stub
	 		 byte[] bytes = (byte[]) message; 
	 		
	 		 IoBuffer buf = IoBuffer.allocate(bytes.length + 4); //实例化buffer
	 		 buf.putInt(bytes.length); 
	     	 buf.put(bytes); //将数据放入buffer
	     	 buf.flip(); //指针归零
	     	 out.write(buf); //写出
	     	 buf.free(); //释放
	}
}
