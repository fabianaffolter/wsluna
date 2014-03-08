package ch.fhnw.algd2.collections.comparableList;

import java.util.Random;

public class Manager {

	public static void main(String[] args) {
		// ComparableList<Integer> myList = new ComparableList<Integer>();
		// myList.addHead(22);
		// myList.addHead(11);
		// myList.addHead(33);
		// myList.addTail(13);
		// myList.addTail(35);
		// myList.addTail(67);
		// System.out.println(myList.toString());
		// System.out.println(myList.size());
		// Integer a = myList.removeHead();
		// Integer b = myList.removeTail();
		// System.out.println(myList.toString());
		// System.out.println(myList.size());
		// System.out.println(a);
		// System.out.println(b);
		// ListIterator<Integer> iter = myList.iterator();
		// System.out.print("HasNext: ");
		// while (iter.hasNext()) {
		// System.out.print(iter.next() + " ");
		// }
		// System.out.println("");
		// System.out.print("HasPrevious: ");
		// while (iter.hasPrevious()) {
		// System.out.print(iter.previous() + " ");
		// }
		// System.out.println("");
		// iter.next();
		// iter.remove();
		// System.out.println(myList.toString());
		// System.out.println(myList.size());
		// System.out.println(iter.nextIndex());
		// System.out.println(iter.previousIndex());
		// iter.next();
		// System.out.println(iter.nextIndex());
		// System.out.println(iter.previousIndex());
		// iter.set(40005);
		// System.out.println(myList.toString());
		// System.out.println(iter.previous());
		// ListIterator<Integer> iterindex = myList.iterator(2);
		// System.out.println(iterindex.next());

		ComparableList<Integer> mySortedList = new ComparableList<Integer>();
		Random k = new Random();
		for (int i = 0; i < 20; ++i) {
			mySortedList.addHead(k.nextInt(39));
		}
		System.out.println(mySortedList.toString());
		mySortedList.mergeSort();
		System.out.println(mySortedList.toString());
	}
}
