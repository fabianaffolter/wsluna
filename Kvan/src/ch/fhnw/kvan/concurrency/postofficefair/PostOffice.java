package ch.fhnw.kvan.concurrency.postofficefair;

public class PostOffice {

	public static void main(String[] args) {
		PostService service = new PostService();

		for (int i = 0; i < 40; i++) {
			new PostClient("Client " + i, service);
		}
	}
}
