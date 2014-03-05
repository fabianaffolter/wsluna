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
		while (true) {
			try {
				sleep((int) (Math.random() * 100));
			} catch (InterruptedException e) {
			}
			desk.enqueue(this);
			System.out.println(getName() + ": eingetreten.");
			try {
				sleep((int) (Math.random() * 100));
			} catch (InterruptedException e) {
			}
			System.out.println(getName() + ": ausgetreten.");
			desk.dequeue();
		}
	}
}
