package com.framework.server.io.java;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import com.framework.server.io.iface.IXOutStream;

public class JavaOutput implements IXOutStream {
    private DataOutputStream dataOutputStream;
	public void close() throws IOException {
		// TODO Auto-generated method stub
		dataOutputStream.close();
	}

	public void setOutputStream(OutputStream outputstream) {
		// TODO Auto-generated method stub
		dataOutputStream = new DataOutputStream(outputstream);
	}

	public void writeBoolean(boolean b) throws IOException {
		// TODO Auto-generated method stub
		dataOutputStream.writeBoolean(b);
	}

	public void writeByte(byte b) throws IOException {
		// TODO Auto-generated method stub
		dataOutputStream.write(b);
	}

	public void writeBytes(byte[] b) throws IOException {
		// TODO Auto-generated method stub
		dataOutputStream.write(b);
	}

	public void writeChar(char c) throws IOException {
		// TODO Auto-generated method stub
		dataOutputStream.writeChar(c);
	}

	public void writeDouble(double d) throws IOException {
		// TODO Auto-generated method stub
		dataOutputStream.writeDouble(d);
	}

	public void writeFloat(float f) throws IOException {
		// TODO Auto-generated method stub
		dataOutputStream.writeFloat(f);
	}

	public void writeInt(int i) throws IOException {
		// TODO Auto-generated method stub
		dataOutputStream.writeInt(i);
	}

	public void writeLong(long l) throws IOException {
		// TODO Auto-generated method stub
		dataOutputStream.writeLong(l);
	}

	public void writeShort(short s) throws IOException {
		// TODO Auto-generated method stub
		dataOutputStream.writeShort(s);
	}

	public void writeUTF(String s) throws IOException {
		// TODO Auto-generated method stub
		dataOutputStream.writeUTF(s);
	}
	
}
