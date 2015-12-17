package com.fantingame.game.msgbody.test;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;
import java.io.IOException;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Map.Entry;

/**接口中文名**/
public class LogicExampleAction_testReq implements ICodeAble {

		/**测试int字段注释**/
	private Integer intValue=0;
	/**测试short字段注释**/
	private Short shortValue=0;
	/**测试string字段注释**/
	private String stringValue1="";
	/**测试bool字段注释**/
	private Boolean boolValue=false;
	/**测试long字段注释**/
	private Long longValue=0l;
	/**测试字节字段注释**/
	private Byte byteValue=0;
	/**测试int2字段注释**/
	private Integer int2Value=0;
	/**测试list注释**/
	private List<String> beanList=null;
	/**测试map字段注释**/
	private Map<String,String> beanMap=null;

	
	@Override
	public void encode(IXOutStream outputStream) throws IOException {
		   
				outputStream.writeInt(intValue);

		outputStream.writeShort(shortValue);

		outputStream.writeUTF(stringValue1);

		outputStream.writeBoolean(boolValue);

		outputStream.writeLong(longValue);

		outputStream.writeByte(byteValue);

		outputStream.writeInt(int2Value);

		
        if(beanList==null||beanList.size()==0){
			outputStream.writeInt(0);
		}else{
			outputStream.writeInt(beanList.size());
		}
		if(beanList.size()>0){
			for(int beanListi=0;beanListi<beanList.size();beanListi++){
						outputStream.writeUTF(beanList.get(beanListi));


			}
		}		
        if(beanMap==null||beanMap.size()==0){
			outputStream.writeInt(0);
		}else{
			outputStream.writeInt(beanMap.size());
		}
        
		if(beanMap.size()>0){
			for(Entry<String, String> entry:beanMap.entrySet()){
				outputStream.writeUTF(entry.getKey());
						outputStream.writeUTF(entry.getValue());

;
			}
		}
	}

	@Override
	public void decode(IXInputStream inputStream) throws IOException {
		   
				intValue = inputStream.readInt();

		shortValue = inputStream.readShort();

		stringValue1 = inputStream.readUTF();

		boolValue = inputStream.readBoolean();

		longValue = inputStream.readLong();

		byteValue = inputStream.readByte();

		int2Value = inputStream.readInt();

		
        int beanListSize = inputStream.readInt();
		if(beanListSize>0){
			beanList = new ArrayList<String>();
			for(int beanListi=0;beanListi<beanListSize;beanListi++){
				 beanList.add(inputStream.readUTF());
			}
		}        
        int beanMapSize = inputStream.readInt();
		if(beanMapSize>0){
			beanMap = new HashMap<String,String>();
			for(int beanMapCursor=0;beanMapCursor<beanMapSize;beanMapCursor++){
				String key = inputStream.readUTF();
				beanMap.put(key,inputStream.readUTF());
			}
		}
	}
	
		/**测试int字段注释**/
    public Integer getIntValue() {
		return intValue;
	}
	/**测试int字段注释**/
    public void setIntValue(Integer intValue) {
		this.intValue = intValue;
	}
	/**测试short字段注释**/
    public Short getShortValue() {
		return shortValue;
	}
	/**测试short字段注释**/
    public void setShortValue(Short shortValue) {
		this.shortValue = shortValue;
	}
	/**测试string字段注释**/
    public String getStringValue1() {
		return stringValue1;
	}
	/**测试string字段注释**/
    public void setStringValue1(String stringValue1) {
		this.stringValue1 = stringValue1;
	}
	/**测试bool字段注释**/
    public Boolean getBoolValue() {
		return boolValue;
	}
	/**测试bool字段注释**/
    public void setBoolValue(Boolean boolValue) {
		this.boolValue = boolValue;
	}
	/**测试long字段注释**/
    public Long getLongValue() {
		return longValue;
	}
	/**测试long字段注释**/
    public void setLongValue(Long longValue) {
		this.longValue = longValue;
	}
	/**测试字节字段注释**/
    public Byte getByteValue() {
		return byteValue;
	}
	/**测试字节字段注释**/
    public void setByteValue(Byte byteValue) {
		this.byteValue = byteValue;
	}
	/**测试int2字段注释**/
    public Integer getInt2Value() {
		return int2Value;
	}
	/**测试int2字段注释**/
    public void setInt2Value(Integer int2Value) {
		this.int2Value = int2Value;
	}
	/**测试list注释**/
    public List<String> getBeanList() {
		return beanList;
	}
	/**测试list注释**/
    public void setBeanList(List<String> beanList) {
		this.beanList = beanList;
	}
	/**测试map字段注释**/
    public Map<String,String> getBeanMap() {
		return beanMap;
	}
	/**测试map字段注释**/
    public void setBeanMap(Map<String,String> beanMap) {
		this.beanMap = beanMap;
	}

	
	
}
