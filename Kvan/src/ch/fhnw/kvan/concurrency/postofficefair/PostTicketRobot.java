package ch.fhnw.kvan.concurrency.postofficefair;

public class PostTicketRobot {
	private int newticket;
	private int firstinline;

	public PostTicketRobot() {
		newticket = Integer.MIN_VALUE;
		firstinline = Integer.MIN_VALUE;
	}

	public int aquireTicket() {
		return newticket++;
	}

	public int requestWhoIsOn() {
		return firstinline;
	}

	public void nextTicket() {
		firstinline++;
	}
}
