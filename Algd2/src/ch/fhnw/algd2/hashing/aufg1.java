package ch.fhnw.algd2.hashing;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class aufg1 {

	public static void main(String[] args) throws NoSuchAlgorithmException {
		hash("Das Fach Algorithmen und Datenstrukturen 2 macht Spass!");
		hash("Das Fach Algorithmen und Datenstrukturen 1 macht Spass!");
	}

	public static void hash(String q) throws NoSuchAlgorithmException {
		byte[] dataBytes = q.getBytes();

		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(dataBytes);
		byte[] mdbytes = md.digest();
		StringBuilder sb = new StringBuilder();
		for (byte b : mdbytes) {
			sb.append(String.format("%02x", b));
		}
		System.out.println(sb.toString());
	}
}
