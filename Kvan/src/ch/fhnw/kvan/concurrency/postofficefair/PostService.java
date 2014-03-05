package ch.fhnw.kvan.concurrency.postofficefair;

public class PostService {

	private final int SIZE = 10;
	private Object[] buf = new Object[SIZE];
	private int first = 0;
	private int last = 0;
	private Object notEmpty = new Object();
	private Object notFull = new Object();

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

	public void enqueue(Object c) {
		synchronized (notFull) {
			while ((first + 1) % SIZE == last) {
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
}
