package ch.fhnw.kvan.concurrency.postofficefair;

public class PostClient extends Thread {
	private final PostService desk;

	public PostClient(String name, PostService desk) {
		super(name);
		this.desk = desk;
		start();
	}

	@Override
	public void run() {
		int i = 0;
		while (i < 1) {
			try {
				sleep((int) (Math.random() * 100));
			} catch (InterruptedException e) {
			}
			desk.enter();
			System.out.println(getName() + " entered office\t\t|");
			try {
				sleep((int) (Math.random() * 200));
			} catch (InterruptedException e) {
			}
			System.out.println("\t\t\t\t|\t\t " + getName() + " left office");
			desk.leave();
			++i;
		}
	}
}
