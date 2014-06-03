package ch.fhnw.kvan.chat.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ch.fhnw.kvan.chat.ChatRoomDriver;

public class Server extends HttpServlet {
	private static final long serialVersionUID = 2659407803062419933L;
	
	public static void main(String args[]) throws IOException {
		int port = 1235;
		ChatRoomDriver crd = new ChatRoomDriver();
		crd.connect("localhost", port);
		
		}
	
	public void service(HttpServletRequest req, HttpServletResponse res){
		String method= req.getMethod();
		if (method.equals("METHOD_GET")){
				doGet(req, resp);
		
		}
	}
		
		public void doGet(HttpServletRequest request, HttpServletResponse response)
				throws IOException, ServletException {
			
		
		
	}
	
	static class ServerHelper{
			
	}

}
