package ch.fhnw.algd2.collections.singlelinklist;

public class Manager {

	public static void main(String[] args) {
		SingleLinkList<Integer> myList = new SingleLinkList<Integer>();
		myList.insertFirst(1);
		myList.insertFirst(2);
		myList.insertFirst(3);
		myList.insertFirst(4);
		myList.insertFirst(5);
		System.out.println("List: " + myList.toString());
		System.out.println("At index 0: " + myList.get(0));
		System.out.println("First element: " + myList.getFirst());
		myList.remove(3);
		System.out.println("1 deleted: " + myList.toString());
		myList.removeFirst();
		System.out.println("First deleted: " + myList.toString());
		myList.insertAfter(22, 2);
		System.out.println("After adding one after: " + myList.toString());
		myList.removeAll();
		System.out.println("After removeAll: " + myList.toString());

		myList.insertFirst(1);
		myList.insertFirst(2);
		myList.insertFirst(3);
		myList.insertFirst(4);
		myList.insertFirst(5);
		SingleLinkList<Integer> twinList = new SingleLinkList<Integer>(myList);
		System.out.println("Origin: " + myList.toString());
		System.out.println("Twin: " + twinList.toString());
	}
}
