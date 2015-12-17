package com.admin.util;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.text.DecimalFormat;

public class Tools {
	
	/**
	 * 截取小数位
	 * @param num
	 * @param format
	 * @return
	 */
	public static String decimalFormat(double num1,double num2,String format){
		if(num1==0 || num2==0){
			return "0";
		}
		DecimalFormat df = new DecimalFormat(format);
		Double value = Double.valueOf(df.format(num1/num2));
		if(value==0){
			return "0";
		}
		return value.toString();
	}
	
	public static void main(String[] args) {
		DecimalFormat df = new DecimalFormat("#.00");
		Double value = Double.valueOf(df.format(100d/3d));
		System.out.print(value);
	}

	public static String UrlFormat(String url, String sessionID) {
		if (url.indexOf("?") > -1) {
			url = url + "&amp;sessionID=" + sessionID;
		} else {
			url = url + "?sessionID=" + sessionID;
		}
		return url;

	}

	public static boolean isEmpty(String str) {
		if (str == null  || str.equals("")) {
			return true;
		} 
		return false;
		

	}

	public static String filterHtmlFlag(String input) {
		if (!hasSpecialChars(input)) {
			return input;
		}
		StringBuffer filtered = new StringBuffer(input.length());
		char c;
		for (int i = 0; i <= input.length() - 1; i++) {
			c = input.charAt(i);
			switch (c) {
			case '<':
				filtered.append("&lt;");
				break;
			case '>':
				filtered.append("&gt;");
				break;
			case '"':
				filtered.append("&quot;");
				break;
			case '&':
				filtered.append("&amp;");
				break;
			case '\'':
				filtered.append("&apos;");
				break;	
			default:
				filtered.append(c);
			}
		}
		return (filtered.toString());
	}

	public static boolean hasSpecialChars(String input) {
		boolean flag = false;
		if ((input != null) && (input.length() > 0)) {
			char c;
			for (int i = 0; i <= input.length() - 1; i++) {
				c = input.charAt(i);
				switch (c) {
				case '>':
					flag = true;
					break;
				case '<':
					flag = true;
					break;
				case '"':
					flag = true;
					break;
				case '&':
					flag = true;
					break;
				case '\'':
					flag = true;
					break;		
				}
			}
		}
		return flag;
	}

	public static String pageTagForDiary(long totalPage, long currentPage,
			String sessionID, String urls, String para1, String value1,
			String para2, String value2, String para3, String value3) {
		totalPage--;
		String url;
		if (Tools.isEmpty(urls)) {
			urls = "";
			url = "";
		} else {
			url = urls;
		}
		int paraNum = 0;
		if (!Tools.isEmpty(para1)) {
			url = url + "?" + para1 + " = " + value1;
			paraNum++;
		}

		if (!Tools.isEmpty(para2) && paraNum > 0) {
			url = url + "&amp;" + para2 + " = " + value2;
			paraNum++;
		} else if (!Tools.isEmpty(para2) && paraNum == 0) {
			url = url + "?" + para2 + " = " + value2;
			paraNum++;
		}

		if (!Tools.isEmpty(para3) && paraNum > 0) {
			url = url + "&amp;" + para3 + " = " + value3;
			paraNum++;
		} else if (!Tools.isEmpty(para3) && paraNum == 0) {
			url = url + "?" + para3 + " = " + value3;
			paraNum++;
		}

		String str = "";
		String nextPageText = "下一页";
		String upPageText = "上一页";
		if ((currentPage == 0) && (totalPage > 0)) {
			if (paraNum <= 0) {
				str = str
						+ "<a href = '"
						+ Tools.UrlFormat(url + "?tp = " + (currentPage + 1),
								sessionID) + "'>" + nextPageText + "</a>("
						+ (currentPage + 1) + "/" + (totalPage + 1) + ")<br/>";
			} else {
				str = str
						+ "<a href = '"
						+ Tools.UrlFormat(url + "&amp;tp = " + (currentPage + 1),
								sessionID) + "'>" + nextPageText + "</a>("
						+ (currentPage + 1) + "/" + (totalPage + 1) + ")<br/>";
			}
		} else if ((currentPage == 0) && (totalPage <= 0)) {
			str = "";
		} else if ((currentPage > 0) && (totalPage > currentPage)) {
			if (paraNum <= 0) {
				str = str
						+ "<a href = '"
						+ Tools.UrlFormat(url + "?tp = " + (currentPage + 1),
								sessionID)
						+ "'>"
						+ nextPageText
						+ "</a>|<a href = '"
						+ Tools.UrlFormat(url + "?tp = " + (currentPage - 1),
								sessionID) + "'>" + upPageText + "</a>("
						+ (currentPage + 1) + "/" + (totalPage + 1) + ")<br/>";
			} else {
				str = str
						+ "<a href = '"
						+ Tools.UrlFormat(url + "&amp;tp = " + (currentPage + 1),
								sessionID)
						+ "'>"
						+ nextPageText
						+ "</a>|<a href = '"
						+ Tools.UrlFormat(url + "&amp;tp = " + (currentPage - 1),
								sessionID) + "'>" + upPageText + "</a>("
						+ (currentPage + 1) + "/" + (totalPage + 1) + ")<br/>";
			}
		} else if ((currentPage > 0) && (totalPage <= currentPage)) {
			if (paraNum <= 0) {
				str = str
						+ "<a href = '"
						+ Tools.UrlFormat(url + "?tp = " + (currentPage - 1),
								sessionID) + "'>" + upPageText + "</a>("
						+ (currentPage + 1) + "/" + (totalPage + 1) + ")<br/>";
			} else {
				str = str
						+ "<a href = '"
						+ Tools.UrlFormat(url + "&amp;tp = " + (currentPage - 1),
								sessionID) + "'>" + upPageText + "</a>("
						+ (currentPage + 1) + "/" + (totalPage + 1) + ")<br/>";
			}
		}
		return str;
	}

	public static String escape(String src) {
		int i;
		char j;
		StringBuffer tmp = new StringBuffer();
		tmp.ensureCapacity(src.length() * 6);
		for (i = 0; i < src.length(); i++) {
			j = src.charAt(i);
			if (Character.isDigit(j)  || Character.isLowerCase(j)
					 || Character.isUpperCase(j))
				tmp.append(j);
			else if (j < 256) {
				tmp.append("%");
				if (j < 16)
					tmp.append("0");
				tmp.append(Integer.toString(j, 16));
			} else {
				tmp.append("%u");
				tmp.append(Integer.toString(j, 16));
			}
		}
		return tmp.toString();
	}

	public static String unescape(String src) {
		StringBuffer tmp = new StringBuffer();
		tmp.ensureCapacity(src.length());
		int lastPos = 0, pos = 0;
		char ch;
		while (lastPos < src.length()) {
			pos = src.indexOf("%", lastPos);
			if (pos == lastPos) {
				if (src.charAt(pos + 1) == 'u') {
					ch = (char) Integer.parseInt(src
							.substring(pos + 2, pos + 6), 16);
					tmp.append(ch);
					lastPos = pos + 6;
				} else {
					ch = (char) Integer.parseInt(src
							.substring(pos + 1, pos + 3), 16);
					tmp.append(ch);
					lastPos = pos + 3;
				}
			} else {
				if (pos == -1) {
					tmp.append(src.substring(lastPos));
					lastPos = src.length();
				} else {
					tmp.append(src.substring(lastPos, pos));
					lastPos = pos;
				}
			}
		}
		return tmp.toString();
	}
	
	   /**  
     * 检查email输入是否正确  
     * 正确的书写格式为 username@domain  
     * @param value  
     * @return  
     */  
    public static boolean checkEmail(String value, int length) {   
            return value.matches("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*") && value.length() <= length; 
    }   
  
    /**  
     * 检查电话输入是否正确  
     * 正确格式 012-87654321、0123-87654321、0123－7654321  
     * @param value  
     * @return  
     */  
    public static boolean checkTel(String value) {   
        return value.matches("\\d{4}-\\d{8}|\\d{4}-\\d{7}|\\d(3)-\\d(8)");
    }   
  
    /**  
     * 检查手机输入是否正确  
     *   
     * @param value  
     * @return  
     */  
    public static boolean checkMobile(String value) {   
        return value.matches("^[1][3,5]+\\d{9}"); 
    }   
  
    /**  
     * 检查中文名输入是否正确  
     *   
     * @param value  
     * @return  
     */  
    public static boolean checkChineseName(String value, int length) {   
        return value.matches("^[\u4e00-\u9fa5]+$") && value.length() <= length; 
    }   
    /**  
     * 检查HTML中首尾空行或空格  
     * @param value  
     * @return  
     */  
    public static boolean checkBlank(String value) {   
        return value.matches("^\\s*|\\s*$"); 
    }   
    /**  
     * 检查字符串是否含有HTML标签  
     * @param value  
     * @return  
     */  
       
    public static boolean checkHtmlTag(String value) {   
        return value.matches("<(\\S*?)[^>]*>.*?</\\1>|<.*? />"); 
    }   
    /**  
     * 检查URL是否合法  
     * @param value  
     * @return  
     */  
    public static boolean checkURL(String value) {   
        return value.matches("[a-zA-z]+://[^\\s]*"); 
    }   
    /**  
     * 检查IP是否合法  
     * @param value  
     * @return  
     */  
    public static boolean checkIP(String value) {   
        return value.matches("\\d{1,3}+\\.\\d{1,3}+\\.\\d{1,3}+\\.\\d{1,3}"); 
    }   
    /**  
     * 检查ID是否合法，开头必须是大小写字母，其他位可以有大小写字符、数字、下划线  
     * @param value  
     * @return  
     */  
    public static boolean checkID(String value) {   
        return value.matches("[a-zA-Z][a-zA-Z0-9_]{4,15}$"); 
    }   
    /**  
     * 检查QQ是否合法，必须是数字，且首位不能为0，最长15位  
     * @param value  
     * @return  
     */  
       
    public static boolean checkQQ(String value) {   
        return value.matches("[1-9][0-9]{4,13}"); 
    }   
    /**  
     * 检查邮编是否合法  
     * @param value  
     * @return  
     */  
    public static boolean checkPostCode(String value) {   
        return value.matches("[1-9]\\d{5}(?!\\d)"); 
    }   
    /**  
     * 检查身份证是否合法,15位或18位  
     * @param value  
     * @return  
     */  
    public static boolean checkIDCard(String value) {   
        return value.matches("\\d{15}|\\d{18}"); 
    }   
    /**  
     * 检查输入是否超出规定长度  
     *   
     * @param length  
     * @param value  
     * @return  
     */  
    public static boolean checkLength(String value, int length) {   
        return ((value == null  || "".equals(value.trim())) ? 0 : value.length()) <= length; 
    }   
  
    /**  
     * 检查是否为空字符串,空：true,不空:false  
     *   
     * @param value  
     * @return  
     */  
    public static boolean checkNull(String value) {   
        return value == null  || "".equals(value.trim()); 
    }   

    public static boolean urlCanUse(String strURL){
        boolean flag = false;
        try{
         URL url = new URL(strURL);
         URLConnection connection = url.openConnection();  
         HttpURLConnection httpConn = (HttpURLConnection)connection;
         int code = httpConn.getResponseCode();
         if(code==200){
          flag = true;
         }else{
          flag = false;
         }
      }catch (Exception e){
        System.out.println ("Error 1" + e.getMessage());
      }
      return flag;
    }
}
