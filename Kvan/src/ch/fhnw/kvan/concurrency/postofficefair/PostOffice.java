package ch.fhnw.kvan.concurrency.postofficefair;

public class PostOffice {

	public static void main(String[] args) {
		PostQueue service = new PostQueue(3);
		// PostQueue service = new PostQueue();
		System.out.println("Clients entering \t\t\t|\t\t Clients leaving");
		System.out.println("---------------- \t\t\t\t\t ---------------");
		System.out.println();
		for (int i = 0; i < 100; i++) {
			new PostClient("Client " + i, service);
		}
	}
}
