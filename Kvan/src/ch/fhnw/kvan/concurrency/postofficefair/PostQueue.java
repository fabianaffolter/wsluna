package ch.fhnw.kvan.concurrency.postofficefair;

public class PostQueue {

	private final int SIZE = 10;
	private Object[] buf = new Object[SIZE];
	private int first = 0;
	private int last = 0;
	private Object notEmpty = new Object();
	private Object notFull = new Object();
	private PostTicketRobot robot = new PostTicketRobot();

	private int places;

	public Object dequeue() {
		synchronized (notEmpty) {
			while (first == last) {
				try {
					notEmpty.wait();
				} catch (InterruptedException e) {
				}
			}
		}
		Object e = buf[last];
		last = (last + 1) % SIZE;
		synchronized (notFull) {
			notFull.notifyAll();
		}
		return e;
	}

	public void enqueue(PostClient c) {
		synchronized (notFull) {
			while ((first + 1) % SIZE == last
					|| c.myticket != robot.requestWhoIsOn()) {
				try {
					notFull.wait();
				} catch (Exception e) {
				}

			}
		}
		buf[first] = c;
		last = (first + 1) % SIZE;
		synchronized (notEmpty) {
			notEmpty.notifyAll();
		}

	}

	public int getTicket() {
		return robot.aquireTicket();
	}

	public synchronized void anzeigeIter() {
		robot.nextTicket();
		notifyAll();

	}

	public PostQueue(int places) {
		if (places < 0)
			places = 0;
		this.places = places;

	}

	public PostQueue() {
	}

	public synchronized void enter(int nr) {
		while (places == 0 || nr != robot.requestWhoIsOn()) {
			try {
				wait();
			} catch (InterruptedException e) {
			}
		}
		places--;
	}

	public synchronized void leave() {
		places++;
		notifyAll();
	}
}
