package ch.fhnw.kvan.concurrency.postofficefair;

public class PostOffice {

	public static void main(String[] args) {
		PostService service = new PostService(2);
		System.out.println("Clients entering \t\t|\t\t Clients leaving");
		System.out.println("---------------- \t\t\t\t ---------------");
		System.out.println();
		for (int i = 0; i < 10; i++) {
			new PostClient("Client " + i, service);
		}
	}
}
