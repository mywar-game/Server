package com.example.msgbody;

import java.io.IOException;

import com.framework.server.io.iface.IXInputStream;
import com.framework.server.io.iface.IXOutStream;
import com.framework.server.msg.model.ICodeAble;

public class bodyExample implements ICodeAble {
	private int age;
	private String name;
	public void decode(IXInputStream inputStream) throws IOException {
		// TODO Auto-generated method stub
		age = inputStream.readInt();
		name = inputStream.readUTF();
	}

	public void encode(IXOutStream outputStream) throws IOException {
		// TODO Auto-generated method stub
		outputStream.writeInt(age);
		outputStream.writeUTF(name);
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
