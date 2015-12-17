package com.tool.email;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.admin.util.Tools;
import com.adminTool.bo.Partner;
import com.adminTool.service.PartnerService;
import com.framework.common.SystemStatsDate;
import com.framework.servicemanager.ServiceCacheFactory;
import com.framework.util.DateUtil;
import com.stats.service.HomePageService;
import com.system.service.TGameServerService;

/**
 * 
 * 操作邮件的工具类，该工具类初始化需提供了发送者邮箱地址等信息
 * 发送支持多个收件人，附件 html 及文本
 * @author sunny.sun
 * @version 1.0
 *
 */
public class MailUtil {

	// 定义发件人别名
	private String displayName;

	//邮件发送者
	private String from;

	//邮件服务器
	private String smtpServer;

	//用户名
	private String username;

	//密码
	private String password;

	//字符集
	private String charset = "utf-8";

	/**
	 * 初始化SMTP服务器地址
	 * @param smtpServer  服务器地址
	 * @param from 发送者
	 * @param displayName 别名
	 * @param username 用户名
	 * @param password 密码
	 */
	public MailUtil(String smtpServer, String from, String displayName,
			String username, String password) {
		this.smtpServer = smtpServer;
		this.from = from;
		this.displayName = displayName;
		this.username = username;
		this.password = password;
	}
	
	

	public String getDisplayName() {
		return displayName;
	}



	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}



	public String getFrom() {
		return from;
	}



	public void setFrom(String from) {
		this.from = from;
	}



	public String getSmtpServer() {
		return smtpServer;
	}



	public void setSmtpServer(String smtpServer) {
		this.smtpServer = smtpServer;
	}



	public String getUsername() {
		return username;
	}



	public void setUsername(String username) {
		this.username = username;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public String getCharset() {
		return charset;
	}



	public void setCharset(String charset) {
		this.charset = charset;
	}



	/**
	 * @param to
	 *            发送地址
	 * @param isAuth
	 *            是否需要认证
	 * @param subject
	 *            主题
	 * @param content
	 *            内容
	 * @param isHtml
	 *            是否是html
	 * @param files
	 *            附件
	 * @return
	 * @throws MessagingException
	 * @throws UnsupportedEncodingException
	 */
	public void send(String[] tos, boolean isAuth, String subject,
			String content, boolean isHtml, File[] files)
			throws MessagingException, UnsupportedEncodingException {
		Session session = null;
		Properties props = System.getProperties();
		props.put("mail.smtp.host", smtpServer);
		props.put("mail.smtp.localhost",smtpServer);
		props.put("smtpd_helo_restrictions","permit_mynetworks,permit_sasl_authenticated,reject_non_fqdn_hostname,reject_invalid_hostname,permit");
		if (isAuth) { // 服务器需要身份认证
			props.put("mail.smtp.auth", "true");
			//生成认证的Authenticator
			Authenticator authenticator = new Authenticator() {
				protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
					return new javax.mail.PasswordAuthentication(username,
							password);
				}
			};
			session = Session.getDefaultInstance(props, authenticator);
		} else {
			props.put("mail.smtp.auth", "false");
			session = Session.getDefaultInstance(props, null);
		}
		//是否debug
		session.setDebug(true);
		Transport trans = session.getTransport("smtp");
		
		//多个接收人
		InternetAddress[] address = new InternetAddress[tos.length];
		for (int i = 0; i < address.length; i++) {
			address[i] = new InternetAddress(tos[i]);
		}
		
		//连接服务器
		trans.connect(smtpServer, username, password);
		
		//生成发送的消息
		Message msg = new MimeMessage(session);
		
		//邮件的地址及别名
		Address from_address = new InternetAddress(from, displayName);
		
		//设置
		msg.setFrom(from_address);
		
		//设置接收人地址
		msg.setRecipients(Message.RecipientType.TO, address);
		
		//设置发送主题
		msg.setSubject(subject);
		
		//部件
		Multipart mp = new MimeMultipart();
		
		//body部件
		MimeBodyPart mbp = new MimeBodyPart();
		
		//判断发送的是否是html格式
		if (isHtml) {// 如果是html格式
			mbp.setContent(content, "text/html;charset=" + charset);
		} else{
			mbp.setText(content);
		}
		//将该正文部件加入到整体部件
		mp.addBodyPart(mbp);
		
		if (files != null && files.length > 0) {// 判断是佛有附件
			//存在附件就将附件全部加入到BodyPart
			for (File file : files) {
				mbp = new MimeBodyPart();
				FileDataSource fds = new FileDataSource(file); // 得到数据源
				mbp.setDataHandler(new DataHandler(fds)); // 得到附件本身并至入BodyPart
				mbp.setFileName(fds.getName()); // 得到文件名同样至入BodyPart
				mp.addBodyPart(mbp);
			}
		}
		// Multipart加入到信件
		msg.setContent(mp); 
		
		// 设置信件头的发送日期
		msg.setSentDate(new Date());
		
		// 发送信件
		msg.saveChanges();
	
		//发送
		trans.sendMessage(msg, msg.getAllRecipients());
		
		//结束
		trans.close();

	}
	
	public void homePageinit(){
		try {
			TGameServerService gameServerService = ServiceCacheFactory.getServiceCache().getService(TGameServerService.class);
			PartnerService partnerService = ServiceCacheFactory.getServiceCache().getService(PartnerService.class);
			HomePageService homePageService = ServiceCacheFactory.getServiceCache().getService(HomePageService.class);
			Map<Integer,String> serverMap = gameServerService.findServerIdNameMapForOfficialServer();
			List<Partner> partnerList = partnerService.findPartnerList();
			Map<String, Partner> partnerMap = new HashMap<String, Partner>();
			if(partnerList!=null && partnerList.size()>0){
				for(Partner p : partnerList){
					partnerMap.put(p.getPNum(), p);
				}
			}
			Calendar calendar = Calendar.getInstance();
			String beginDate = calendar.get(Calendar.YEAR)+"-"+(calendar.get(Calendar.MONTH)+1)+"-"+(calendar.get(Calendar.DAY_OF_MONTH)-1);
			String[] dates = new String[]{beginDate,beginDate};//查询日期
			List<Object> pageList = null;
			pageList = homePageService.findData(dates,0,0);
			List<Object> totalList = homePageService.findTotalData(dates, 0, 0,"0");
			if(pageList!=null && pageList.size()>0){
				Map<String,String> haveMap = new HashMap<String, String>();
				Document document = Jsoup.parse(MailUtil.class.getResourceAsStream("/template/email_template.html"),"utf-8","easou.com");
				Element element = document.getElementById("data");
				if(totalList!=null && totalList.size()>0){
					Object[] totalArr = (Object[])totalList.get(0);
					int totalNewReg = Integer.valueOf(totalArr[0].toString());
					int totalPayUserNum = Integer.valueOf(totalArr[1].toString());
					double totalPayAmount = Double.valueOf(totalArr[2].toString());
					Element allElement = document.getElementById("allNum");
					allElement.text("总创角数 ："+totalNewReg+Jsoup.parse("&nbsp;&nbsp;").text()+"总付费人数 ："+totalPayUserNum+Jsoup.parse("&nbsp;&nbsp;").text()+"总收入 ："+totalPayAmount);
				}
				for(int i=0;i<pageList.size();i++){
					Map<String, String> map = new HashMap<String, String>();
					Object[] arr = (Object[])pageList.get(i);
					map.put("date", arr[0].toString());
					map.put("sysNum", arr[1].toString());
					map.put("server", serverMap.get(Integer.valueOf(arr[1].toString())));
					map.put("channel", "全渠道");
					String key = map.get("date")+map.get("sysNum")+map.get("channel");
					if(haveMap.containsKey(key)){
						continue;
					}else{
						haveMap.put(key,null);
					}
					element.append("<tr id='"+i+"'></tr>");
					Element tdElement = element.getElementById(""+i);
					tdElement.append("<td>"+map.get("date")+"</td>");
					tdElement.append("<td>"+map.get("server")+"</td>");
					tdElement.append("<td>"+map.get("channel")+"</td>");
					int totalNum = Integer.valueOf(arr[2].toString());//累积用户
					tdElement.append("<td>"+totalNum+"</td>");
					int dayActive = Integer.valueOf(arr[3].toString());//日活跃
					tdElement.append("<td>"+dayActive+"</td>");
					int newUser = Integer.valueOf(arr[5].toString());//创角数
					tdElement.append("<td>"+newUser+"</td>");
					int payUserNum = Integer.valueOf(arr[6].toString());//当天充值人数
					double payAmount = Double.valueOf(arr[7].toString());//收入
					int newRegPayUserNum = Integer.valueOf(arr[8].toString());//当天新增注册的付费人
					tdElement.append("<td>"+payUserNum+"</td>");
					tdElement.append("<td>"+Tools.decimalFormat((double)newRegPayUserNum*100,(double)newUser, "#.00")+"%</td>");
					tdElement.append("<td>"+Tools.decimalFormat((double)payUserNum*100,(double)dayActive, "#.00")+"%</td>");
					if(DateUtil.isOpenThirtyDays(DateUtil.stringtoDate(arr[0].toString(), DateUtil.LONG_DATE_FORMAT), Integer.valueOf(arr[1].toString()))){
						int monthActive = Integer.valueOf(arr[4].toString());//月活跃
						tdElement.append("<td>"+Tools.decimalFormat((double)dayActive,(double)monthActive, "#.00")+"</td>");
					}else{
						tdElement.append("<td>0</td>");
					}
					tdElement.append("<td>"+payAmount+"</td>");
					tdElement.append("<td>"+Tools.decimalFormat((double)payAmount,(double)dayActive, "#.0")+"</td>");
					tdElement.append("<td>"+Tools.decimalFormat((double)payAmount,(double)payUserNum, "#.0")+"</td>");
					int hourIndex = Integer.valueOf(arr[9].toString());//数据的时间节点
					Date date = DateUtil.stringtoDate(arr[0].toString(), DateUtil.LONG_DATE_FORMAT);
					String str = DateUtil.dateToString(new Date(date.getTime()+(hourIndex+1)*SystemStatsDate.HALF_HOUR_INDEX-1000), DateUtil.FORMAT_ONE);
					tdElement.append("<td>"+str+"</td>");
				}
				String info = document.html()+"此为系统自动发送的运营数据，设置为每天早上9点发送昨日数据。";
				send(new String[] {"allen_liu@staff.easou.com","glenn_zhang@staff.easou.com","leoyu_yu@staff.easou.com","pany_pan@staff.easou.com","ricky_li@staff.easou.com"},false, "后台首页数据",info,true,null);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws UnsupportedEncodingException,
			MessagingException {
		MailUtil mailUtil = new MailUtil("smtp.163.com", "***@163.com",
				"Sunny sun", "*****@163.com", "*****");
		mailUtil.send(new String[] { "****@163.com",
				"****@163.com" }, false, "test",
				"<a href=\"www.baidu.com\">百度一下</a>", false,
				new File[] { new File("F:/test.txt") });
	}
}
