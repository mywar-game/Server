package com.admin.util;

public class DTools {

	public static String UrlFormat(String url, long sessionID) {
		if (url.indexOf("?") > -1) {
			url = url + "&amp;sessionID = " + sessionID;
		} else {
			url = url + "?sessionID = " + sessionID;
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
			long sessionID, String urls, String para1, String value1,
			String para2, String value2, String para3, String value3) {
		totalPage--;
		String url;
		if (DTools.isEmpty(urls)) {
			urls = "";
			url = "";
		} else {
			url = urls;
		}
		int paraNum = 0;
		if (!DTools.isEmpty(para1)) {
			url = url + "?" + para1 + " = " + value1;
			paraNum++;
		}

		if (!DTools.isEmpty(para2) && paraNum > 0) {
			url = url + "&amp;" + para2 + " = " + value2;
			paraNum++;
		} else if (!DTools.isEmpty(para2) && paraNum == 0) {
			url = url + "?" + para2 + " = " + value2;
			paraNum++;
		}

		if (!DTools.isEmpty(para3) && paraNum > 0) {
			url = url + "&amp;" + para3 + " = " + value3;
			paraNum++;
		} else if (!DTools.isEmpty(para3) && paraNum == 0) {
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
						+ DTools.UrlFormat(url + "?tp = " + (currentPage + 1),
								sessionID) + "'>" + nextPageText + "</a>("
						+ (currentPage + 1) + "/" + (totalPage + 1) + ")<br/>";
			} else {
				str = str
						+ "<a href = '"
						+ DTools.UrlFormat(url + "&amp;tp = " + (currentPage + 1),
								sessionID) + "'>" + nextPageText + "</a>("
						+ (currentPage + 1) + "/" + (totalPage + 1) + ")<br/>";
			}
		} else if ((currentPage == 0) && (totalPage <= 0)) {
			str = "";
		} else if ((currentPage > 0) && (totalPage > currentPage)) {
			if (paraNum <= 0) {
				str = str
						+ "<a href = '"
						+ DTools.UrlFormat(url + "?tp = " + (currentPage + 1),
								sessionID)
						+ "'>"
						+ nextPageText
						+ "</a>|<a href = '"
						+ DTools.UrlFormat(url + "?tp = " + (currentPage - 1),
								sessionID) + "'>" + upPageText + "</a>("
						+ (currentPage + 1) + "/" + (totalPage + 1) + ")<br/>";
			} else {
				str = str
						+ "<a href = '"
						+ DTools.UrlFormat(url + "&amp;tp = " + (currentPage + 1),
								sessionID)
						+ "'>"
						+ nextPageText
						+ "</a>|<a href = '"
						+ DTools.UrlFormat(url + "&amp;tp = " + (currentPage - 1),
								sessionID) + "'>" + upPageText + "</a>("
						+ (currentPage + 1) + "/" + (totalPage + 1) + ")<br/>";
			}
		} else if ((currentPage > 0) && (totalPage <= currentPage)) {
			if (paraNum <= 0) {
				str = str
						+ "<a href = '"
						+ DTools.UrlFormat(url + "?tp = " + (currentPage - 1),
								sessionID) + "'>" + upPageText + "</a>("
						+ (currentPage + 1) + "/" + (totalPage + 1) + ")<br/>";
			} else {
				str = str
						+ "<a href = '"
						+ DTools.UrlFormat(url + "&amp;tp = " + (currentPage - 1),
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
	
	
}
