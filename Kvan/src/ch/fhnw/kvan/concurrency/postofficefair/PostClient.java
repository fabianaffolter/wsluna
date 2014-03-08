package ch.fhnw.kvan.concurrency.postofficefair;

public class PostClient extends Thread {
	private final PostQueue desk;
	public int myticket;

	public PostClient(String name, PostQueue desk) {
		super(name);
		this.desk = desk;
		start();
	}

	@Override
	public void run() {
		int i = 0;
		while (i < 1) {
			myticket = desk.getTicket();
			try {
				sleep((int) (Math.random() * 1));
			} catch (InterruptedException e) {
			}
			// desk.enqueue(this);
			desk.enter(myticket);
			System.out.println(getName() + " entered office\t\t|");
			desk.anzeigeIter();
			// yield();
			try {
				sleep((int) (Math.random() * 2));
			} catch (InterruptedException e) {
			}
			System.out.println("\t\t\t\t\t|\t\t " + getName() + " left office");
			desk.leave();
			// desk.dequeue();
			++i;
		}
	}
}
