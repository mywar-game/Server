package com.fandingame.game.version.listener;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.fandingame.game.framework.context.SpringMvcListener;
import com.fandingame.game.version.dao.ActiveCodeDao;

/**
 * 监听数据库连接
 * 
 * @author yezp
 */
public class ConnectDataBaseListener extends SpringMvcListener {
	private static Logger LOG = Logger.getLogger(ConnectDataBaseListener.class);

	@Override
	public void contextInitialized(ServletContextEvent event) {
		super.contextInitialized(event);
		ServletContext context = event.getServletContext();
		// 获取web环境下的ApplicationContext
		ApplicationContext appContext = WebApplicationContextUtils
				.getRequiredWebApplicationContext(context);

		try {
			// 验证hw_game_center 连接
			if (!appContext.containsBean("activeCodeDao")) {
				LOG.error("GameCenter 修改了activeCodeDao.....");
				System.exit(0);
			}
			ActiveCodeDao activeCodeDao = (ActiveCodeDao) appContext
					.getBean("activeCodeDao");
			activeCodeDao.getLoginServerBean();

		} catch (Exception e) {
			LOG.error("GameVersion服务器，连接数据库失败，请检查数据库连接配置是否正确。");
			System.exit(-1);
			// await();
		}
	}

//	private static final String SHUTDOWN_COMMAND = "/SHUTDOWN";
//
//	private void await() {
//		ServerSocket serverSocket = null;
//		int port = 5001;
//		try {
//			serverSocket = new ServerSocket(port, 1,
//					InetAddress.getByName("127.0.0.1"));
//		} catch (IOException e) {
//			e.printStackTrace();
//			System.exit(1);
//		}
//
//		Socket socket = null;
//		InputStream input = null;
//		OutputStream output = null;
//		try {
//			socket = serverSocket.accept();
//			input = socket.getInputStream();
//			output = socket.getOutputStream();
//
//			// create Request object and parse
//			Request request = new Request(input);
//			request.parse();
//
//			// create Response object
//			Response response = new Response(output);
//			response.setRequest(request);
//			response.sendStaticResource();
//
//			// Close the socket
//			socket.close();
//
//			// check if the previous URI is a shutdown command
//			request.getUri().equals(SHUTDOWN_COMMAND);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//	}
//
//	public class Request {
//		InputStream input;
//
//		public Request(InputStream input) {
//			this.input = input;
//		}
//
//		public void parse() {
//			// Read a set of characters from the socket
//			StringBuffer request = new StringBuffer(2048);
//			int i;
//			byte[] buffer = new byte[2048];
//
//			try {
//				i = input.read(buffer);
//			} catch (IOException e) {
//				e.printStackTrace();
//				i = -1;
//			}
//
//			for (int j = 0; j < i; j++) {
//				request.append((char) buffer[j]);
//			}
//
//			LOG.error(request.toString());
//			String uri = parseUri(request.toString());
//			LOG.error(uri);
//		}
//
//		private String parseUri(String requestString) {
//			int index1, index2;
//			index1 = requestString.indexOf(' ');
//
//			if (index1 != -1) {
//				index2 = requestString.indexOf(' ', index1 + 1);
//				if (index2 > index1)
//					return requestString.substring(index1 + 1, index2);
//			}
//
//			return null;
//		}
//	}
//
//	public class Response {
//		OutputStream output;
//		Request request;
//
//		public Response(OutputStream output) {
//			this.output = output;
//		}
//
//		public void setRequest(Request request) {
//			this.request = request;
//		}
//
//		public void sendStaticResource() throws IOException {
//			byte[] bytes = new byte[2048];
//			FileInputStream fis = null;
//
//			try {
//				
//				File file = new File(HttpServer.WEB_ROOT, request.getUri());
//				if (file.exists()) {
//					fis = new FileInputStream(file);
//					int ch = fis.read(bytes, 0, 2048);
//
//					while (ch != -1) {
//						output.write(bytes, 0, ch);
//						ch = fis.read(bytes, 0, 2048);
//					}
//				} else {
//					// file not found
//					String errorMessage = "HTTP/1.1 404 File Not Found/r/n"
//							+ "Content-Type: text/html/r/n"
//							+ "Content-Length: 23/r/n" + "/r/n"
//							+ "<h1>File Not Found</h1>";
//					output.write(errorMessage.getBytes());
//				}
//			} catch (Exception e) {
//				// thrown if cannot instantiate a File object
//				System.out.println(e.toString());
//			} finally {
//				if (fis != null)
//					fis.close();
//			}
//		}
//
//	}

}
