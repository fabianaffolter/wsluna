package ch.fhnw.kvan.sockets.time;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.ByteBuffer;

public class TimeClientBuffer {
	// Server:
	// Port: 1234
	public static void main(String[] args) throws IOException {
		int port = 1234;
		final int BUFFERLENGTH = 24;

		InetAddress ia = InetAddress.getByName("localhost");
		Socket server = new Socket(ia, port);
		BufferedReader in = new BufferedReader(new InputStreamReader(
				server.getInputStream()));
		int i = 0, q = 0;
		ByteBuffer buf = ByteBuffer.allocate(BUFFERLENGTH);
		while (i < 1) {
			System.out.println("Connected to " + server.getInetAddress());
			while (q < BUFFERLENGTH) {
				buf.put((byte) in.read());
				q++;
			}
			String date = new String(buf.array());
			System.out.println("The Time server says: " + date);
			i++;
		}
		server.close();
	}
}
