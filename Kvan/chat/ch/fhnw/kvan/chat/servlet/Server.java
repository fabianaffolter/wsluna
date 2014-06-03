package ch.fhnw.kvan.chat.servlet;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ch.fhnw.kvan.chat.ChatRoomDriver;

@WebServlet("/chat/server")

public class Server extends HttpServlet {
	private static final long serialVersionUID = 2659407803062419933L;
	
	static ChatRoomDriver crd = new ChatRoomDriver();
	
	static ServerHelper helper;
	static{
	helper = new ServerHelper();
}
	
	public static void main(String args[]) throws IOException {
		int port = 1235;
		crd.connect("localhost", port);		
		}
	

		public void doGet(HttpServletRequest request, HttpServletResponse response)
				throws IOException, ServletException {
			
			ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
			ObjectOutputStream out = new ObjectOutputStream(byteOut);

			Enumeration<?> e = request.getParameterNames();

			String name = (String) e.nextElement();
			String method = request.getParameter("action");
			String[] params = new String[3];
			int i = 0;
			while (e.hasMoreElements()) {
				name = (String) e.nextElement();
				params[i] = request.getParameter(name);
				i++;
			}

			out.writeObject(helper.getResponse(method, params));

			byte[] buf = byteOut.toByteArray();
			response.setContentType("application/octet-stream");
			response.setContentLength(buf.length);

			ServletOutputStream sOut = response.getOutputStream();
			sOut.write(buf);
			sOut.close();

		
	}
	
	static class ServerHelper{
			
		private Object getResponse(String method, String[] params) throws IOException {
			

			switch (method) {
			case "addParticipant":
				System.out.println("addParticipant, name: "+params[0]);
				return crd.getChatRoom().addParticipant(params[0]);
			
			case "removeParticipant":
				System.out.println("removeParticipant, name: "+params[0]);
				return crd.getChatRoom().removeParticipant(params[0]);
				
			case "addTopic":
				System.out.println("addTopic, topic: "+params[0]);
				return crd.getChatRoom().addTopic(params[0]);
				
			case "removeTopic":
				System.out.println("removeTopic, topic: "+params[0]);
				return crd.getChatRoom().removeTopic(params[0]);
				
			case "postMessage":
				System.out.println("postMessage, message: "+params[0]+" ,topic: "+params[1]);
				return crd.getChatRoom().addMessage(params[1], params[0]);
				
			case "getMessages":
				System.out.println("getMessages, topic: "+params[0]);
				return crd.getChatRoom().getMessages(params[0]);
				
			case "refresh":
				System.out.println("refresh, topic: "+params[0]);
				return crd.getChatRoom().refresh(params[0]);
				
			default:
				System.out.println("False GET request");
				throw new RuntimeException();

			}
		}
		
	}

}
