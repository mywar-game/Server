package com.framework.client.socket.codec;



import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

import com.framework.common.ZlibUtil;




public class DecoderForClient extends CumulativeProtocolDecoder {
/**
 * 返回false = 通知父类继续去获取数据
 * 返回true =  通知父类重新执行一次doDecode()方法
 */
	@Override
	protected boolean doDecode(IoSession session, IoBuffer in,
			ProtocolDecoderOutput out) throws Exception {
//		in.order(ByteOrder.LITTLE_ENDIAN); //字节序, 

		//消息buf
//		IoBuffer buf = IoBuffer.allocate(128); //ServerConfig.MessageMaxByte 最大消息字节数
//		buf.order(ByteOrder.LITTLE_ENDIAN);
		if (in.remaining() > 5) {
			in.mark();
			int length = in.getInt();
			byte isCompress = in.get();
//			if (length > 128) {
//			}
			if (length > in.remaining()){
				in.reset();
				return false;
			}
			byte[] bytes = new byte[length];
			in.get(bytes, 0, length);
			if(isCompress==1){
//				LogSystem.info("MinaClient收到数据大小"+length+",是否压缩？="+isCompress);
				bytes = ZlibUtil.decompress(bytes);
			}
			out.write(bytes);
			//此种情况是粘包的情况 
			if(in.hasRemaining()){
				return true;
			}else{
			    return false;
			}
		} else {
			return false;
		}
	}
	
	public byte[] copybyte1Tobyte2(byte[] byte1,byte[] byte2){
		for(int i=0;i<byte1.length;i++){
			byte2[8+i] = byte1[i];
		}
		return byte2;
	}
	public byte[] long2bytes(long num,int arrayLength) {
		   byte[] b = new byte[8+arrayLength];
		   for (int i = 0; i < 8; i++) {
		    b[i] = (byte) (num >>> (56 - i * 8));
		   }
		   return b;
		}
}
