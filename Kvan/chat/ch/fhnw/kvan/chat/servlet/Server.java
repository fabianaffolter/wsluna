package ch.fhnw.kvan.chat.servlet;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ch.fhnw.kvan.chat.ChatRoomDriver;

@WebServlet("/Kvan/chat/Server")
public class Server extends HttpServlet {
	private static final long serialVersionUID = 2659407803062419933L;

	static ChatRoomDriver crd = new ChatRoomDriver();

	static ServerHelper helper;
	static {
		int port = 1235;
		crd.connect("localhost", port);
		helper = new ServerHelper();
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		System.out.println("DoGetCalled");
		ByteArrayOutputStream byteOut = new ByteArrayOutputStream();

		Enumeration<?> e = request.getParameterNames();
		System.out.println("\n\nNew Request");
		String name = (String) e.nextElement();
		String method = request.getParameter("action");
		System.out.println("method=" + method);
		String[] params = new String[3];
		int i = 0;
		while (e.hasMoreElements()) {
			name = (String) e.nextElement();
			params[i] = request.getParameter(name);
			System.out.println(params[i]);
			i++;
		}
		String rep = helper.getResponse(method, params);
		System.out.println(rep);
		byteOut.write(rep.getBytes());

		byte[] buf = byteOut.toByteArray();
		response.setContentType("application/octet-stream");
		response.setContentLength(buf.length);
		System.out.println("now writing");
		ServletOutputStream sOut = response.getOutputStream();
		sOut.write(buf);
		sOut.close();
	}

	static class ServerHelper {

		private String getResponse(String method, String[] params)
				throws IOException {

			switch (method) {
			case "addParticipant":
				System.out.println("addParticipant, name: " + params[0]);
				if (crd.getChatRoom().addParticipant(params[0]))
					return "add_participant=" + params[0] + "\r\n";
				else
					return "null";

			case "removeParticipant":
				System.out.println("removeParticipant, name: " + params[0]);
				if (crd.getChatRoom().removeParticipant(params[0]))
					return "remove_participant=" + params[0] + "\r\n";
				else
					return "null";

			case "addTopic":
				System.out.println("addTopic, name: " + params[0]);
				if (crd.getChatRoom().addTopic(params[0]))
					return "add_topic=" + params[0] + "\r\n";
				else
					return "null";
			case "removeTopic":
				System.out.println("removeTopic, name: " + params[0]);
				if (crd.getChatRoom().removeTopic(params[0]))
					return "remove_topic=" + params[0] + "\r\n";
				else
					return "null";

			case "postMessage":
				System.out.println("postMessage, message: " + params[0]
						+ " ,topic: " + params[1]);
				if (crd.getChatRoom().addMessage(params[1], params[0]))
					return "message=" + params[0] + "\r\n";
				else
					return null;
			case "getMessages":
				System.out.println("getMessage, message: " + params[0]);
				if (crd.getChatRoom().getMessages(params[0]) != null)
					return "messages=" + params[0] + "\r\n";
				else
					return null;

			case "refresh":
				System.out.println("refresh, topic: " + params[0]);
				return crd.getChatRoom().refresh(params[0]);

			default:
				System.out.println("False GET request");
				throw new RuntimeException();

			}
		}

	}

}
