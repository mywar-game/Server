package com.framework.server.io.java;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

import com.framework.server.io.iface.IXInputStream;

public class JavaInput implements IXInputStream {
private DataInputStream dataInputStream;
	public void close() throws IOException {
		// TODO Auto-generated method stub
		dataInputStream.close();
	}

	public boolean readBoolean() throws IOException {
		// TODO Auto-generated method stub
		return dataInputStream.readBoolean();
	}

	public byte readByte() throws IOException {
		// TODO Auto-generated method stub
		return dataInputStream.readByte();
	}

	public char readChar() throws IOException {
		// TODO Auto-generated method stub
		return dataInputStream.readChar();
	}

	public double readDouble() throws IOException {
		// TODO Auto-generated method stub
		return dataInputStream.readDouble();
	}

	public float readFloat() throws IOException {
		// TODO Auto-generated method stub
		return dataInputStream.readFloat();
	}

	public void readFully(byte[] b, int position, int size) throws IOException {
		// TODO Auto-generated method stub
		dataInputStream.readFully(b, position, size);
	}

	public int readInt() throws IOException {
		// TODO Auto-generated method stub
		return dataInputStream.readInt();
	}

	public long readLong() throws IOException {
		// TODO Auto-generated method stub
		return dataInputStream.readLong();
	}

	public short readShort() throws IOException {
		// TODO Auto-generated method stub
		return dataInputStream.readShort();
	}

	public String readUTF() throws IOException {
		// TODO Auto-generated method stub
		return dataInputStream.readUTF();
	}

	public void setInputStream(InputStream inputStream) {
		// TODO Auto-generated method stub
		dataInputStream = new DataInputStream(inputStream);
	}

	public int available() throws IOException {
		// TODO Auto-generated method stub
		return dataInputStream.available();
	} 
}
