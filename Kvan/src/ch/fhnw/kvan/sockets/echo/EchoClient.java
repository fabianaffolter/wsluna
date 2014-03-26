package ch.fhnw.kvan.sockets.echo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class EchoClient {

	public static void main(String[] args) throws UnknownHostException,
			IOException {
		Socket s = new Socket("localhost", 1235);
		PrintWriter out = new PrintWriter(s.getOutputStream());
		BufferedReader in = new BufferedReader(new InputStreamReader(
				s.getInputStream()));
		BufferedReader sysin = new BufferedReader(new InputStreamReader(
				System.in));

		String q = sysin.readLine();
		while (q != null && q != "") {
			out.println(q);
			out.flush();
			System.out.println(in.readLine());
			q = sysin.readLine();
		}
		System.out.println("Done!");
		s.close();
	}
}
