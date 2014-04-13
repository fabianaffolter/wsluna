package ch.fhnw.algd2.heaps.priorityheap;

public class PriorityHeap {
	private int heapsize;
	private Node root;

	public PriorityHeap() {
		heapsize = 0;
		root.F = root;
		root.L = null;
		root.R = null;
	}

	public PriorityHeap(int[] in) {
		root = new Node();
		root.P = Integer.MAX_VALUE;
		root.L = null;
		heapsize = in.length;
		root.R = buildHeap(in);
	}

	public void insert(int priority) {
		Node free = getLastNodeInHeap();
		Node insertion = new Node();
		insertion.F = free;
		insertion.P = priority;
		if (free.R == null) {
			free.R = insertion;
		} else {
			free.L = insertion;
		}
	}

	private Node buildHeap(int[] a) {
		for (int e : a) {
			insert(e);
		}
		return root;
	}

	private Node getLastNodeInHeap() {
		Node u = root;
		int r = heapsize;
		int k = Integer.highestOneBit(heapsize);
		while (k != 0 && u != null) {
			if ((r & k) != 0 && u.R != null) {
				u = u.R;
			}
			if ((r & k) != 0 && u.L != null) {
				u = u.L;
			}
			k >>>= 1;
		}
		return u;
	}

	private void traverse(Node root, int level) {
		if (root != null) {
			traverse(root.R, level + 1);
			visit(root, level);
			traverse(root.L, level + 1);
		}
	}

	private void visit(Node root, int level) {
		for (int i = 0; i < level; ++i) {
			System.out.print('\t');
		}
		System.out.println(root.P);
	}

	public void show() {
		traverse(this.root.R, 0);
	}

	private class Node {
		int P;
		Node F, L, R;
	}
}
