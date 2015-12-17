package com.adminTool.msgbody;

import java.io.IOException;

import com.framework.server.io.iface.IXInputStream;
import com.framework.server.io.iface.IXOutStream;
import com.framework.server.msg.model.ICodeAble;

/* memCache常量信息  */
public class MemCacheInfo implements ICodeAble {

	/**  服务器地址 **/
	private String adress = new String();
	/**  服务器向网络发送的总字节数 **/
	private String bytes_written = new String();
	/**  服务器分配的连接结构的数量 **/
	private String connection_structures = new String();
	/**  存储item字节数 **/
	private String bytes = new String();
	/**  item总数 **/
	private String total_items = new String();
	/**  服务器运行以来接受的连接总数 **/
	private String total_connections = new String();
	/**  服务器运行时间，单位秒 **/
	private String uptime = new String();
	/**  服务器进程ID **/
	private String pid = new String();
	/**  请求成功的总次数 **/
	private String get_hits = new String();
	/**  item个数 **/
	private String curr_items = new String();
	/**  为获取空间删除item的总数 **/
	private String evictions = new String();
	/**  服务器的版本号 **/
	private String version = new String();
	/**  取回请求总数 **/
	private String cmd_get = new String();
	/**  服务器当前unix时间戳 **/
	private String time = new String();
	/**  操作系统字大小(这台服务器是64位的) **/
	private String pointer_size = new String();
	/**  存储请求总数 **/
	private String cmd_set = new String();
	/**  memCache线程数 **/
	private String threads = new String();
	/**  服务器为memcache分配的内存数（字节） **/
	private String limit_maxbytes = new String();
	/**  服务器从网络读取到的总字节数 **/
	private String bytes_read = new String();
	/**  当前打开连接数 **/
	private String curr_connections = new String();
	/**  get未命中次数 **/
	private String get_misses = new String();

	public MemCacheInfo() {
	}

	public MemCacheInfo(String adress , String bytes_written , String connection_structures , String bytes , String total_items , String total_connections , String uptime , String pid , String get_hits , String curr_items , String evictions , String version , String cmd_get , String time , String pointer_size , String cmd_set , String threads , String limit_maxbytes , String bytes_read , String curr_connections , String get_misses) {
		this.adress = adress;
		this.bytes_written = bytes_written;
		this.connection_structures = connection_structures;
		this.bytes = bytes;
		this.total_items = total_items;
		this.total_connections = total_connections;
		this.uptime = uptime;
		this.pid = pid;
		this.get_hits = get_hits;
		this.curr_items = curr_items;
		this.evictions = evictions;
		this.version = version;
		this.cmd_get = cmd_get;
		this.time = time;
		this.pointer_size = pointer_size;
		this.cmd_set = cmd_set;
		this.threads = threads;
		this.limit_maxbytes = limit_maxbytes;
		this.bytes_read = bytes_read;
		this.curr_connections = curr_connections;
		this.get_misses = get_misses;
	}

	public void decode(IXInputStream dataInputStream) throws IOException {
		adress = dataInputStream.readUTF();
		bytes_written = dataInputStream.readUTF();
		connection_structures = dataInputStream.readUTF();
		bytes = dataInputStream.readUTF();
		total_items = dataInputStream.readUTF();
		total_connections = dataInputStream.readUTF();
		uptime = dataInputStream.readUTF();
		pid = dataInputStream.readUTF();
		get_hits = dataInputStream.readUTF();
		curr_items = dataInputStream.readUTF();
		evictions = dataInputStream.readUTF();
		version = dataInputStream.readUTF();
		cmd_get = dataInputStream.readUTF();
		time = dataInputStream.readUTF();
		pointer_size = dataInputStream.readUTF();
		cmd_set = dataInputStream.readUTF();
		threads = dataInputStream.readUTF();
		limit_maxbytes = dataInputStream.readUTF();
		bytes_read = dataInputStream.readUTF();
		curr_connections = dataInputStream.readUTF();
		get_misses = dataInputStream.readUTF();
	}

	public void encode(IXOutStream dataOutputStream) throws IOException {
		dataOutputStream.writeUTF(adress);
		dataOutputStream.writeUTF(bytes_written);
		dataOutputStream.writeUTF(connection_structures);
		dataOutputStream.writeUTF(bytes);
		dataOutputStream.writeUTF(total_items);
		dataOutputStream.writeUTF(total_connections);
		dataOutputStream.writeUTF(uptime);
		dataOutputStream.writeUTF(pid);
		dataOutputStream.writeUTF(get_hits);
		dataOutputStream.writeUTF(curr_items);
		dataOutputStream.writeUTF(evictions);
		dataOutputStream.writeUTF(version);
		dataOutputStream.writeUTF(cmd_get);
		dataOutputStream.writeUTF(time);
		dataOutputStream.writeUTF(pointer_size);
		dataOutputStream.writeUTF(cmd_set);
		dataOutputStream.writeUTF(threads);
		dataOutputStream.writeUTF(limit_maxbytes);
		dataOutputStream.writeUTF(bytes_read);
		dataOutputStream.writeUTF(curr_connections);
		dataOutputStream.writeUTF(get_misses);
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public String getAdress() {
		return adress;
	}

	public void setBytes_written(String bytes_written) {
		this.bytes_written = bytes_written;
	}

	public String getBytes_written() {
		return bytes_written;
	}

	public void setConnection_structures(String connection_structures) {
		this.connection_structures = connection_structures;
	}

	public String getConnection_structures() {
		return connection_structures;
	}

	public void setBytes(String bytes) {
		this.bytes = bytes;
	}

	public String getBytes() {
		return bytes;
	}

	public void setTotal_items(String total_items) {
		this.total_items = total_items;
	}

	public String getTotal_items() {
		return total_items;
	}

	public void setTotal_connections(String total_connections) {
		this.total_connections = total_connections;
	}

	public String getTotal_connections() {
		return total_connections;
	}

	public void setUptime(String uptime) {
		this.uptime = uptime;
	}

	public String getUptime() {
		return uptime;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getPid() {
		return pid;
	}

	public void setGet_hits(String get_hits) {
		this.get_hits = get_hits;
	}

	public String getGet_hits() {
		return get_hits;
	}

	public void setCurr_items(String curr_items) {
		this.curr_items = curr_items;
	}

	public String getCurr_items() {
		return curr_items;
	}

	public void setEvictions(String evictions) {
		this.evictions = evictions;
	}

	public String getEvictions() {
		return evictions;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getVersion() {
		return version;
	}

	public void setCmd_get(String cmd_get) {
		this.cmd_get = cmd_get;
	}

	public String getCmd_get() {
		return cmd_get;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getTime() {
		return time;
	}

	public void setPointer_size(String pointer_size) {
		this.pointer_size = pointer_size;
	}

	public String getPointer_size() {
		return pointer_size;
	}

	public void setCmd_set(String cmd_set) {
		this.cmd_set = cmd_set;
	}

	public String getCmd_set() {
		return cmd_set;
	}

	public void setThreads(String threads) {
		this.threads = threads;
	}

	public String getThreads() {
		return threads;
	}

	public void setLimit_maxbytes(String limit_maxbytes) {
		this.limit_maxbytes = limit_maxbytes;
	}

	public String getLimit_maxbytes() {
		return limit_maxbytes;
	}

	public void setBytes_read(String bytes_read) {
		this.bytes_read = bytes_read;
	}

	public String getBytes_read() {
		return bytes_read;
	}

	public void setCurr_connections(String curr_connections) {
		this.curr_connections = curr_connections;
	}

	public String getCurr_connections() {
		return curr_connections;
	}

	public void setGet_misses(String get_misses) {
		this.get_misses = get_misses;
	}

	public String getGet_misses() {
		return get_misses;
	}

}