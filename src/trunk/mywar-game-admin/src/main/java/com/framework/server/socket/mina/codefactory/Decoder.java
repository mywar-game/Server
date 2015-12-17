package com.framework.server.socket.mina.codefactory;


import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

import com.framework.log.LogSystem;

public class Decoder extends CumulativeProtocolDecoder {

	@Override
	protected boolean doDecode(IoSession session, IoBuffer in,
			ProtocolDecoderOutput out) throws Exception {
//		in.order(ByteOrder.LITTLE_ENDIAN); //字节序, 

		//消息buf
//		IoBuffer buf = IoBuffer.allocate(128); //ServerConfig.MessageMaxByte 最大消息字节数
//		buf.order(ByteOrder.LITTLE_ENDIAN);

		//考虑以下几种情况：
		//    1. 一个ip包中只包含一个完整消息
		//    2. 一个ip包中包含一个完整消息和另一个消息的一部分
		//    3. 一个ip包中包含一个消息的一部分
		//    4. 一个ip包中包含两个完整的数据消息或更多（循环处理在父类的decode中）
		if (in.remaining() > 4) {
			in.mark();
			int length = in.getInt();
//			if (length > 128) {
//			}
			if (length > in.remaining()) {
				in.reset();
				return false;
			}
			LogSystem.warn("有数据大小为:" + length);

			//复制一个完整消息
			byte[] bytes = new byte[length];
			in.get(bytes, 0, length);
			out.write(bytes);
			in.free();
			return true;
		} else {
			return false;
		}
	}
}
