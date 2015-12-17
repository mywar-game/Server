package com.fantingame.game.msgbody.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;

/* 测试消息体  */
public class ResTest implements ICodeAble {

	/**  test **/
	private String userId= new String() ;
	/**  test **/
	private int userSequense;
	/**  test **/
	private boolean boolTest;
	/**  test **/
	private String stringtest= new String() ;
	/**  test **/
	private short shortTest;
	/**  test **/
	private List<UserDetailedInfo> userDetailedInfoList= new ArrayList<UserDetailedInfo>() ;
	/**  test **/
	private List<String> stringlist= new ArrayList<String>() ;
	/**  test **/
	private List<Integer> intlist= new ArrayList<Integer>() ;
	/**  test **/
	private Map<String,UserDetailedInfo> myMap2=new HashMap<String,UserDetailedInfo>() ;

	public ResTest(){}

	public ResTest(String userId , int userSequense , boolean boolTest , String stringtest , short shortTest){
		this.userId=userId;
		this.userSequense=userSequense;
		this.boolTest=boolTest;
		this.stringtest=stringtest;
		this.shortTest=shortTest;
	}

	public void decode(IXInputStream dataInputStream) throws IOException {
		userId = dataInputStream.readUTF();
		userSequense = dataInputStream.readInt();
		boolTest = dataInputStream.readBoolean();
		stringtest = dataInputStream.readUTF();
		shortTest = dataInputStream.readShort();
		int userDetailedInfoListSize = dataInputStream.readInt();
		for(int i=0;i<userDetailedInfoListSize;i++)
		{
			UserDetailedInfo t = new  UserDetailedInfo();
			t.decode(dataInputStream);
			userDetailedInfoList.add(t);
		}

		int stringlistSize = dataInputStream.readInt();
		for(int i=0;i<stringlistSize;i++)
		{
			String t = new  String();
			t = dataInputStream.readUTF();
			stringlist.add(t);
		}

		int intlistSize = dataInputStream.readInt();
		for(int i=0;i<intlistSize;i++)
		{
			intlist.add(dataInputStream.readInt());
		}

		int myMap2Size = dataInputStream.readInt();
		
		for(int i=0;i<myMap2Size;i++)
		{
			String key = new String();
			key = dataInputStream.readUTF();
			UserDetailedInfo t = new UserDetailedInfo();
			t.decode(dataInputStream);
			myMap2.put(key,t);
		}

	}

	public void encode(IXOutStream dataOutputStream) throws IOException {
		dataOutputStream.writeUTF(userId);
		dataOutputStream.writeInt(userSequense);
		dataOutputStream.writeBoolean(boolTest);
		dataOutputStream.writeUTF(stringtest);
		dataOutputStream.writeShort(shortTest);
		if(userDetailedInfoList!=null)
		{
			dataOutputStream.writeInt(userDetailedInfoList.size());
			for(int i=0,size=userDetailedInfoList.size();i<size;i++)
			{
				UserDetailedInfo t= (UserDetailedInfo)userDetailedInfoList.get(i);
				t.encode(dataOutputStream);
			}
		}
		else
		{
			dataOutputStream.writeInt(0);
		}

		if(stringlist!=null)
		{
			dataOutputStream.writeInt(stringlist.size());
			for(int i=0,size=stringlist.size();i<size;i++)
			{
				String t= (String)stringlist.get(i);
				dataOutputStream.writeUTF(t);
			}
		}
		else
		{
			dataOutputStream.writeInt(0);
		}

		if(intlist!=null)
		{
			dataOutputStream.writeInt(intlist.size());
			for(int i=0,size=intlist.size();i<size;i++)
			{
				dataOutputStream.writeInt(intlist.get(i));
			}
		}
		else
		{
			dataOutputStream.writeInt(0);
		}

		if(myMap2!=null)
		{
			dataOutputStream.writeInt(myMap2.size());
			Iterator<String> it =myMap2.keySet().iterator();;
			while(it.hasNext())
			{
				String key = it.next();
				dataOutputStream.writeUTF(key);
				UserDetailedInfo t= (UserDetailedInfo)myMap2.get(key);
				t.encode(dataOutputStream);
			}
		}
		else
		{
			dataOutputStream.writeInt(0);
		}

	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserSequense(int userSequense) {
		this.userSequense = userSequense;
	}

	public int getUserSequense() {
		return userSequense;
	}

	public void setBoolTest(boolean boolTest) {
		this.boolTest = boolTest;
	}

	public boolean getBoolTest() {
		return boolTest;
	}

	public void setStringtest(String stringtest) {
		this.stringtest = stringtest;
	}

	public String getStringtest() {
		return stringtest;
	}

	public void setShortTest(short shortTest) {
		this.shortTest = shortTest;
	}

	public short getShortTest() {
		return shortTest;
	}

	public void addUserDetailedInfoList(UserDetailedInfo value ) {
		userDetailedInfoList.add(value);
	}

	public void delUserDetailedInfoList(int index) {
		userDetailedInfoList.remove(index);
	}

	public UserDetailedInfo getUserDetailedInfoList(int index) {
		return userDetailedInfoList.get(index);
	}

	public int getUserDetailedInfoListSize() {
		return userDetailedInfoList.size();
	}

	public void addStringlist(String value ) {
		stringlist.add(value);
	}

	public void delStringlist(int index) {
		stringlist.remove(index);
	}

	public String getStringlist(int index) {
		return stringlist.get(index);
	}

	public int getStringlistSize() {
		return stringlist.size();
	}

	public void addIntlist(int value ) {
		intlist.add(value);
	}

	public void delIntlist(int index) {
		intlist.remove(index);
	}

	public int getIntlist(int index) {
		return (intlist.get(index));
	}

	public int getIntlistSize() {
		return intlist.size();
	}

	public void putMyMap2(String key , UserDetailedInfo value ) {
		myMap2.put(key , value);
	}

	public void delMyMap2(String key) {
		myMap2.remove(key);
	}

	public void delAllMyMap2() {
		myMap2.clear();
	}

	public UserDetailedInfo getMyMap2(String key) {
		return (UserDetailedInfo)myMap2.get(key);
	}

}