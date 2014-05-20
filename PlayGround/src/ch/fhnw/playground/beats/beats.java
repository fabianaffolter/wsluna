package ch.fhnw.playground.beats;

import java.util.Date;

public class beats {

	public static void main(String[] args) {
		Date r = new Date();
		System.out.println(r.toString());
		int hours = r.getHours() - 1;
		int min = r.getMinutes();
		int sec = r.getSeconds();

		System.out.println(hours + "hours " + min + "min " + sec + "sec");

		int totsec = 3600 * hours + 60 * min + sec;

		System.out.println("seconds over all: " + totsec);

		System.out.println("Beats: " + totsec / 86.4);
	}
}
