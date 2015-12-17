package com.fantingame.game.msgbody.test;

import java.io.IOException;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;

public class TestEntryAllStruct implements ICodeAble {

	private int intValue;
	private short shortValue;
	private String stringValue1;
	private boolean boolValue;
	private long longValue;
	private byte byteValue;
	private int int2Value;
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		outputStream.writeInt(intValue);
		outputStream.writeShort(shortValue);
		outputStream.writeBoolean(boolValue);
		outputStream.writeLong(longValue);
		outputStream.writeByte(byteValue);
		outputStream.writeUTF(stringValue1);
		outputStream.writeInt(int2Value);
	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		intValue = inputStream.readInt();
		shortValue = inputStream.readShort();
		boolValue = inputStream.readBoolean();
		longValue = inputStream.readLong();
		byteValue = inputStream.readByte();
		stringValue1 = inputStream.readUTF();
		int2Value = inputStream.readInt();
	}

	public int getIntValue() {
		return intValue;
	}

	public void setIntValue(int intValue) {
		this.intValue = intValue;
	}

	public short getShortValue() {
		return shortValue;
	}

	public void setShortValue(short shortValue) {
		this.shortValue = shortValue;
	}

	public String getStringValue1() {
		return stringValue1;
	}

	public void setStringValue1(String stringValue1) {
		this.stringValue1 = stringValue1;
	}

	public boolean isBoolValue() {
		return boolValue;
	}

	public void setBoolValue(boolean boolValue) {
		this.boolValue = boolValue;
	}

	public long getLongValue() {
		return longValue;
	}

	public void setLongValue(long longValue) {
		this.longValue = longValue;
	}

	public byte getByteValue() {
		return byteValue;
	}

	public void setByteValue(byte byteValue) {
		this.byteValue = byteValue;
	}
	
	public String toString(){
		return "intValue="+intValue+",shortValue="+shortValue+",stringValue1="+stringValue1+",boolValue="+boolValue+",longValue="+longValue+",byteValue="+byteValue;
	}
}
