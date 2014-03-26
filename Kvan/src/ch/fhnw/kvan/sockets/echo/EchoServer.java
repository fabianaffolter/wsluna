package ch.fhnw.kvan.sockets.echo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {

	public static void main(String[] args) throws IOException {
		ServerSocket server = new ServerSocket(1235);
		while (true) {
			Socket s = server.accept();
			BufferedReader in = new BufferedReader(new InputStreamReader(
					s.getInputStream()));
			PrintWriter out = new PrintWriter(s.getOutputStream(), true);
			String input = in.readLine();
			while (input != null && !input.equals("")) {
				out.println(input);
				input = in.readLine();
			}
			s.close();
			server.close();
		}
	}
}
