package com.framework.common;

import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.Tag;

import com.admin.util.Tools;
import com.opensymphony.xwork2.ActionSupport;



public class PageTag implements Tag { 

	private PageContext pageContext;
	private Tag parent;
	private String urls;
	private String para1;
	private String value1;
	private String para2;
	private String value2;
	private String para3;
	private String value3;
	private String para4;
	private String value4;
	private String datePara1;
	private Date dateValue1;
	private String datePara2;
	private Date dateValue2;
	
	private String paraStr;
	
    public PageTag() {
    	super();
    }
	    
   
	public void setPageContext(final PageContext pageContext) {
    	this.pageContext = pageContext;
    }
    
    public void setParent(Tag parent) {
    	this.parent = parent;
    }
    public int doStartTag() throws JspTagException {
    	return SKIP_BODY;
    }
    
    public int doEndTag() throws JspTagException {
    	try {
    		long totalPage = ((Long) pageContext.getRequest().getAttribute("totalPage")).longValue() - 1;
    		int currentPage = ((Integer) pageContext.getRequest().getAttribute("toPage")).intValue();
        	String sessionID;
        	if (pageContext.getRequest().getAttribute("sessionID") == null) {
        		sessionID = "";
        	} else {
        		sessionID = (String) pageContext.getRequest().getAttribute("sessionID");
        	}
    		
    		String url = "";
	    	String requestAldUrl = "";
	    	if (pageContext.getRequest().getAttribute("requestAldUrl") != null) {
	    		requestAldUrl = (String) pageContext.getRequest().getAttribute("requestAldUrl");
	    	}
    		if (Tools.isEmpty(urls)) {
    	    		url = requestAldUrl;
    		} else {   	    	
    	    	if (urls.indexOf("?") == 0) {
    	    		url = requestAldUrl + urls;
    	    	} else {
    	    		url = urls;
    	    	}
    		}
    		//url已有参数个数，当添加参数时，如果已有参数个数为0，则在参数前加“？”，否则加&
    		int paraNum = 0;
    		
    		if (url.indexOf("?") != -1) {
    			paraNum++;
    		}
    		//因为参数太多，统一写到这里
    		if (!Tools.isEmpty(paraStr)) {
				String[] paraArr = paraStr.split(",");
				if (paraArr != null && paraArr.length > 0) {
					for (int i = 0; i < paraArr.length; i=i+2) {
						String para = paraArr[i];
						String value = "";
						//最后一个参数没有相应值
						if ((i+1) < paraArr.length) {
							value = URLEncoder.encode(URLEncoder.encode(paraArr[i+1],"UTF-8"),"UTF-8");
						}
						if (!Tools.isEmpty(para) && paraNum == 0 && !Tools.isEmpty(value)) {
							url = url + "?" + para + "=" + value;
							paraNum++;
						} else if (!Tools.isEmpty(para) && paraNum > 0 && !Tools.isEmpty(value)) {
							url = url + "&amp;" + para + "=" + value;
							paraNum++;
						}
					}	
				}
			}
    		
    		if (!Tools.isEmpty(para1) && paraNum == 0 && !Tools.isEmpty(value1)) {
    			url = url + "?" + para1 + "=" + value1;
    			paraNum++;
    		} else if (!Tools.isEmpty(para1) && paraNum > 0 && !Tools.isEmpty(value1)) {
    			url = url + "&amp;" + para1 + "=" + value1;
    			paraNum++;
    		}
    		
    		if (!Tools.isEmpty(para2) && paraNum > 0 && !Tools.isEmpty(value2)) {
    			url = url + "&amp;" + para2 + "=" + value2;
    			paraNum++;
    		} else if (!Tools.isEmpty(para2) && paraNum == 0 && !Tools.isEmpty(value2)) {
    			url = url + "?" + para2 + "=" + value2;
    			paraNum++;
    		}
    		
    		if (!Tools.isEmpty(para3) && paraNum > 0 && !Tools.isEmpty(value3)) {
    			url = url + "&amp;" + para3 + "=" + value3;
    			paraNum++;
    		} else if (!Tools.isEmpty(para3) && paraNum == 0 && !Tools.isEmpty(value3)) {
    			url = url + "?" + para3 + "=" + value3;
    			paraNum++;
    		}
    		
    		if (!Tools.isEmpty(para4) && paraNum > 0 && !Tools.isEmpty(value4)) {
    			url = url + "&amp;" + para4 + "=" + value4;
    			paraNum++;
    		} else if (!Tools.isEmpty(para4) && paraNum == 0 && !Tools.isEmpty(value4)) {
    			url = url + "?" + para4 + "=" + value4;
    			paraNum++;
    		}
    		
    		String dateValue1Str = "";
    		if (dateValue1 != null) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				dateValue1Str = sdf.format(dateValue1);
			}
    		if (!Tools.isEmpty(datePara1) && paraNum > 0 && !Tools.isEmpty(dateValue1Str)) {
    			url = url + "&amp;" + datePara1 + "=" + dateValue1Str;
    			paraNum++;
    		} else if (!Tools.isEmpty(datePara1) && paraNum == 0 && !Tools.isEmpty(dateValue1Str)) {
    			url = url + "?" + datePara1 + "=" + dateValue1Str;
    			paraNum++;
    		}
    		String dateValue2Str = "";
    		if (dateValue2 != null) {
    			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    			dateValue2Str = sdf.format(dateValue2);
    		}
    		if (!Tools.isEmpty(datePara2) && paraNum > 0 && !Tools.isEmpty(dateValue2Str)) {
    			url = url + "&amp;" + datePara2 + "=" + dateValue2Str;
    			paraNum++;
    		} else if (!Tools.isEmpty(datePara2) && paraNum == 0 && !Tools.isEmpty(dateValue2Str)) {
    			url = url + "?" + datePara2 + "=" + dateValue2Str;
    			paraNum++;
    		}
        	String str = "";
        	//struts国际化
    		ActionSupport actionSupport = new ActionSupport();
        	String nextPageText = actionSupport.getText("nextPageText");
        	String upPageText = actionSupport.getText("upPageText");
        	//只有下一页
        	if (currentPage == 0 && totalPage > 0) {
        		if (paraNum <= 0) {
        			str = str + "<a href='" + Tools.UrlFormat(url + "?toPage=" + (currentPage + 1), sessionID) + "'>" + nextPageText + "</a>(" + (currentPage + 1) + "/" + (totalPage + 1) + ")";
        		} else {
        			str = str + "<a href='" + Tools.UrlFormat(url + "&amp;toPage=" + (currentPage + 1), sessionID) + "'>" + nextPageText + "</a>(" + (currentPage + 1) + "/" + (totalPage + 1) + ")";	
        		}
        	} else if ((currentPage == 0) && (totalPage <= 0)) {
        		str = "";
        	} else if ((currentPage > 0) && (totalPage > currentPage)) {
        		if (paraNum <= 0) {
        			str = str + "<a href='" + Tools.UrlFormat(url + "?toPage=" + (currentPage + 1), sessionID) + "'>" + nextPageText + "</a>|<a href='" + Tools.UrlFormat(url + "?toPage=" + (currentPage - 1), sessionID) + "'>" + upPageText + "</a>(" + (currentPage + 1) + "/" + (totalPage + 1) + ")";
        		} else {
        			str = str + "<a href='" + Tools.UrlFormat(url + "&amp;toPage=" + (currentPage + 1), sessionID) + "'>" + nextPageText + "</a>|<a href='" + Tools.UrlFormat(url + "&amp;toPage=" + (currentPage - 1), sessionID) + "'>" + upPageText + "</a>(" + (currentPage + 1) + "/" + (totalPage + 1) + ")";
        		}
        	} else if ((currentPage > 0) && (totalPage <= currentPage)) {
        		if (paraNum <= 0) {        		 
        			str = str + "<a href='" + Tools.UrlFormat(url + "?toPage=" + (currentPage - 1), sessionID) + "'>" + upPageText + "</a>(" + (currentPage + 1) + "/" + (totalPage + 1) + ")";
        		} else {
        			str = str + "<a href='" + Tools.UrlFormat(url + "&amp;toPage=" + (currentPage - 1), sessionID) + "'>" + upPageText + "</a>(" + (currentPage + 1) + "/" + (totalPage + 1) + ")";
        		}
        	}
        	String firstPageText = actionSupport.getText("FirstPageText");
        	String endPageText = actionSupport.getText("EndPageText");
        	if(!str.equals("")){
        		String text = "<input id='toPageInputText' type='text' name='toPage' value='"+(currentPage+1)+"' onkeydown='if(event.keyCode==13)document.getElementById(&quot;pageGo&quot;).click()' class='textbox' size='1' maxlength='3' /><input type='button' id='pageGo' value='GO' class='button' onClick=gotoPage(document.getElementById('toPageInputText').value); />";
        		if(paraNum <= 0){
            		str = "【<a href='" + Tools.UrlFormat(url + "?toPage=" + 0, sessionID) + "'>"+firstPageText+"</a>】"+"【"+str+"】"+"【<a href='" + Tools.UrlFormat(url + "?toPage=" + (totalPage), sessionID) + "'>"+endPageText+"</a>】"+text+"<br />";
        		}else{
            		str = "【<a href='" + Tools.UrlFormat(url + "&amp;toPage=" + 0, sessionID) + "'>"+firstPageText+"</a>】"+"【"+str+"】"+"【<a href='" + Tools.UrlFormat(url + "&amp;toPage=" + (totalPage), sessionID) + "'>"+endPageText+"</a>】"+text+"<br />";
        		}
        	}
        	String endUrlStr="";
        	if(paraNum<=0){
        		endUrlStr = url+"?";
        	}else{
        		endUrlStr = url+"&";
        	}
        	str=str+"<script>function gotoPage(pageNum){location.href='"+endUrlStr.replaceAll("&amp;", "&")+"toPage='+(pageNum-1)+'';}</script>";
        	pageContext.getOut().write(str);
	} catch (IOException e) {
    		throw new JspTagException("IO Error" + e.getMessage());
    	}
    	return EVAL_PAGE;
    }
    
    public void release() {
    	
    }
	public Tag getParent() {
		return parent;
	}

	

	public void setPara1(String para1) {
		this.para1 = para1;
	}


	public void setValue1(String value1) {
		this.value1 = value1;
	}


	public void setPara2(String para2) {
		this.para2 = para2;
	}


	public void setValue2(String value2) {
		this.value2 = value2;
	}


	public void setPara3(String para3) {
		this.para3 = para3;
	}


	public void setValue3(String value3) {
		this.value3 = value3;
	}


	public void setUrls(String urls) {
		this.urls = urls;
	}


	public void setDatePara1(String datePara1) {
		this.datePara1 = datePara1;
	}


	public String getDatePara1() {
		return datePara1;
	}


	public void setDateValue1(Date dateValue1) {
		this.dateValue1 = dateValue1;
	}


	public Date getDateValue1() {
		return dateValue1;
	}


	public void setDatePara2(String datePara2) {
		this.datePara2 = datePara2;
	}


	public void setDateValue2(Date dateValue2) {
		this.dateValue2 = dateValue2;
	}


	public void setPara4(String para4) {
		this.para4 = para4;
	}


	public String getPara4() {
		return para4;
	}


	public void setValue4(String value4) {
		this.value4 = value4;
	}


	public String getValue4() {
		return value4;
	}

	/**
	 * @param paraStr the paraStr to set
	 */
	public void setParaStr(String paraStr) {
		this.paraStr = paraStr;
	}
}
