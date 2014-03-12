package ch.fhnw.algd2.collections.tree;

public class Manager {

	public static void main(String[] args) {
		// ComparableList<Integer> list = new ComparableList<Integer>();
		// Random k = new Random();
		// for (int i = 0; i < Math.pow(2, 9) - 1; ++i) {
		// list.addHead(k.nextInt(1000));
		// }
		// list.mergeSort();
		// System.out.println(list.toString());
		// ListIterator<Integer> it = list.iterator();
		// int i = 0;
		// int[] a = new int[list.size()];
		// while (it.hasNext()) {
		// a[i] = it.next();
		// i++;
		// }
		int[] a = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16,
				17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 39, 30 };
		Tree tre = new Tree(a);
		tre.show();
		System.out.println(tre.exists(14567));
		System.out.println(tre.find(5));
		tre.insert(5);
		tre.show();
		tre.insert(4000);
		tre.show();
		tre.insert(7);
		tre.show();
		tre.remove(0);
		tre.remove(19);
		tre.show();
	}
}
