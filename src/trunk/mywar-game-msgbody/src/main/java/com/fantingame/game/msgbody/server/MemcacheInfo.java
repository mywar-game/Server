package com.fantingame.game.msgbody.server;

import com.fantingame.game.msgbody.common.io.iface.IXInputStream;
import com.fantingame.game.msgbody.common.io.iface.IXOutStream;
import com.fantingame.game.msgbody.common.model.ICodeAble;

import java.io.IOException;

/* 缓存信息  */
public class MemcacheInfo implements ICodeAble {

	/**  缓存名 **/
	private String cacheName= new String() ;
	/**  get方法的调用次数 **/
	private String queryNum= new String() ;
	/**  根据索引的查询次数 **/
	private String indexNum= new String() ;
	/**  获取列表查询次数 **/
	private String listNum= new String() ;
	/**  删除次数 **/
	private String delNum= new String() ;
	/**  更新或保存的次数 **/
	private String saveorupdateNum= new String() ;
	/**  添加的次数 **/
	private String addNum= new String() ;
	/**  从数据库查询的次数 **/
	private String fromDataBaseNum= new String() ;
	/**  遇到同步查询的次数 **/
	private String synNum= new String() ;
	/**  查询不到数据的次数 **/
	private String missFromMemcachedNum= new String() ;
	/**  当前key数量 **/
	private String keyNum= new String() ;

	public MemcacheInfo(){}

	public MemcacheInfo(String cacheName , String queryNum , String indexNum , String listNum , String delNum , String saveorupdateNum , String addNum , String fromDataBaseNum , String synNum , String missFromMemcachedNum , String keyNum){
		this.cacheName=cacheName;
		this.queryNum=queryNum;
		this.indexNum=indexNum;
		this.listNum=listNum;
		this.delNum=delNum;
		this.saveorupdateNum=saveorupdateNum;
		this.addNum=addNum;
		this.fromDataBaseNum=fromDataBaseNum;
		this.synNum=synNum;
		this.missFromMemcachedNum=missFromMemcachedNum;
		this.keyNum=keyNum;
	}

	public void decode(IXInputStream dataInputStream) throws IOException {
		cacheName = dataInputStream.readUTF();
		queryNum = dataInputStream.readUTF();
		indexNum = dataInputStream.readUTF();
		listNum = dataInputStream.readUTF();
		delNum = dataInputStream.readUTF();
		saveorupdateNum = dataInputStream.readUTF();
		addNum = dataInputStream.readUTF();
		fromDataBaseNum = dataInputStream.readUTF();
		synNum = dataInputStream.readUTF();
		missFromMemcachedNum = dataInputStream.readUTF();
		keyNum = dataInputStream.readUTF();
	}

	public void encode(IXOutStream dataOutputStream) throws IOException {
		dataOutputStream.writeUTF(cacheName);
		dataOutputStream.writeUTF(queryNum);
		dataOutputStream.writeUTF(indexNum);
		dataOutputStream.writeUTF(listNum);
		dataOutputStream.writeUTF(delNum);
		dataOutputStream.writeUTF(saveorupdateNum);
		dataOutputStream.writeUTF(addNum);
		dataOutputStream.writeUTF(fromDataBaseNum);
		dataOutputStream.writeUTF(synNum);
		dataOutputStream.writeUTF(missFromMemcachedNum);
		dataOutputStream.writeUTF(keyNum);
	}

	public void setCacheName(String cacheName) {
		this.cacheName = cacheName;
	}

	public String getCacheName() {
		return cacheName;
	}

	public void setQueryNum(String queryNum) {
		this.queryNum = queryNum;
	}

	public String getQueryNum() {
		return queryNum;
	}

	public void setIndexNum(String indexNum) {
		this.indexNum = indexNum;
	}

	public String getIndexNum() {
		return indexNum;
	}

	public void setListNum(String listNum) {
		this.listNum = listNum;
	}

	public String getListNum() {
		return listNum;
	}

	public void setDelNum(String delNum) {
		this.delNum = delNum;
	}

	public String getDelNum() {
		return delNum;
	}

	public void setSaveorupdateNum(String saveorupdateNum) {
		this.saveorupdateNum = saveorupdateNum;
	}

	public String getSaveorupdateNum() {
		return saveorupdateNum;
	}

	public void setAddNum(String addNum) {
		this.addNum = addNum;
	}

	public String getAddNum() {
		return addNum;
	}

	public void setFromDataBaseNum(String fromDataBaseNum) {
		this.fromDataBaseNum = fromDataBaseNum;
	}

	public String getFromDataBaseNum() {
		return fromDataBaseNum;
	}

	public void setSynNum(String synNum) {
		this.synNum = synNum;
	}

	public String getSynNum() {
		return synNum;
	}

	public void setMissFromMemcachedNum(String missFromMemcachedNum) {
		this.missFromMemcachedNum = missFromMemcachedNum;
	}

	public String getMissFromMemcachedNum() {
		return missFromMemcachedNum;
	}

	public void setKeyNum(String keyNum) {
		this.keyNum = keyNum;
	}

	public String getKeyNum() {
		return keyNum;
	}

}