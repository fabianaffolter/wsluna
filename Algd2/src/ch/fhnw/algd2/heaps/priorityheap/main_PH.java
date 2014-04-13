package ch.fhnw.algd2.heaps.priorityheap;

public class main_PH {

	public static void main(String[] args) {
		int[] a = { 62, 38, 86, 81, 1, 58, 17, 87, 61, 10, 43, 85, 1, 95, 78,
				88, 13, 52, 68, 31 };
		PriorityHeap tre = new PriorityHeap(a);
		tre.show();
	}
}
