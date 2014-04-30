package ch.fhnw.kvan.chat.socket.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import ch.fhnw.kvan.chat.ChatRoom;
import ch.fhnw.kvan.chat.ChatRoomDriver;

public class Server {
	public static void main(String args[]) throws IOException {
		int port = 1235;
		ChatRoomDriver crd = new ChatRoomDriver();
		crd.connect("localhost", port);
		ConnectionListener monitor = new ConnectionListener();
		Thread mon = new Thread(monitor);
		mon.start();
		try (ServerSocket server = new ServerSocket(port)) {
			System.out.println("Started Chat Server on port " + port);
			while (true) {
				Socket s = server.accept();
				ConnectionHandler q = new ConnectionHandler(s);
				monitor.addConnection(q);
				q.setChatRoom((ChatRoom) crd.getChatRoom());
				Thread t = new Thread(q);
				t.start();
			}
		}
	}
}
