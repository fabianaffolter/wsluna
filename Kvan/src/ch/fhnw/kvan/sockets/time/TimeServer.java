package ch.fhnw.kvan.sockets.time;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class TimeServer {

	public static void main(String args[]) throws IOException {
		int port = 1234;
		@SuppressWarnings("resource")
		ServerSocket server = new ServerSocket(port);
		while (true) {
			System.out.println("Waiting for a client...");
			Socket client = server.accept();
			System.out.println("Client from " + client.getInetAddress()
					+ " connected");
			// Prepare the output stream for the TCP/IP socket
			OutputStream out = client.getOutputStream();
			Date date = new Date();
			// Convert Date to array of bytes. Socket can only handle bytes
			byte b[] = date.toString().getBytes();
			out.write(b);
		}
	}
}
