package ch.fhnw.kvan.sockets.basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class EchoClient {
	public static void main(String[] args) throws UnknownHostException,
			IOException {
		Socket s = new Socket("localhost", 1234);
		PrintWriter out = new PrintWriter(s.getOutputStream());
		BufferedReader in = new BufferedReader(new InputStreamReader(
				s.getInputStream()));
		BufferedReader stdin = new BufferedReader(new InputStreamReader(
				System.in));
		String input = stdin.readLine();

		while (input != null && !input.equals("")) {
			System.out.println("Client: " + input);
			out.println(input);
			out.flush();
			System.out.println("Echo: " + in.readLine());
			input = stdin.readLine();

		}
		s.close();
	}

}
