package ch.fhnw.algd2.collections.tree;

public class Tree {
	private Node root;

	/*
	 * Constructor
	 */
	public Tree() {
		root = new Node();
		root.key = Integer.MIN_VALUE;
		root.L = null;
		root.R = null;
	}

	/*
	 * Constructor
	 * 
	 * @param sorted integer array
	 */
	public Tree(int[] origin) {
		root = new Node();
		root.key = Integer.MIN_VALUE;
		root.L = null;
		root.R = buildTree(origin, 0, origin.length - 1);
	}

	/*
	 * API: Show tree 90 degree to left
	 */
	public void show() {
		traverse(this.root.R, 0);
	}

	/*
	 * API: Check if given value exists
	 * 
	 * @param key to search for
	 */
	public boolean exists(int key) {
		return search(this.root.R, key);
	}

	/*
	 * API: Returns position of key in tree
	 * 
	 * @param key to search for
	 */
	public SearchResult find(int key) {
		SearchResult sr = new SearchResult();
		return explore(sr, this.root.R, null, key);
	}

	/*
	 * API: Retrieve a search result
	 */
	public SearchResult searchResult() {
		return new SearchResult();
	}

	/*
	 * API: Insert a non-existing node
	 */
	public boolean insert(int key) {
		SearchResult sr = find(key);
		if (sr.child != null) {
			System.out.println("Key already exists, no action taken");
			return false;
		} else {
			sr.child = new Node();
			sr.child.key = key;
			if (sr.left == true) {
				sr.father.L = sr.child;
			} else {
				sr.father.R = sr.child;
			}
			return true;
		}
	}

	/*
	 * API: Remove a key from the tree
	 */
	public boolean remove(int key) {
		SearchResult sr = find(key);
		// case 1 node does not exist
		if (sr.child == null) {
			System.out.println("Key does not exist");
			return false;
		} else {
			// case node has no sons
			if (sr.child.L == null && sr.child.R == null) {
				// if (sr.father.L == sr.child) {
				// sr.father.L = null;
				// }
				// if (sr.father.R == sr.child) {
				// sr.father.R = null;
				// }
				if (sr.left) {
					sr.father.L = null;
				} else {
					sr.father.R = null;
				}
			}
			// case node has one son

			// if (sr.child.L != null && sr.child.R == null) {
			// if (sr.father.L == sr.child) {
			// sr.father.L = sr.child.L;
			// }
			// if (sr.father.R == sr.child) {
			// sr.father.R = sr.child.L;
			// }
			// return true;
			// }
			// if (sr.child.R != null && sr.child.L == null) {
			// if (sr.father.L == sr.child) {
			// sr.father.L = sr.child.R;
			// }
			// if (sr.father.R == sr.child) {
			// sr.father.R = sr.child.L;
			// }
			// return true;
			// }
			else if (sr.child.L == null ^ sr.child.R == null) { // if either of
																// the
																// expressions
																// is true, but
																// not both
				if (sr.left) {
					// if child.L == null, father.L = child.R, else child.L
					sr.father.L = sr.child.L == null ? sr.child.R : sr.child.L;
				} else {
					// if child.L == null, father.R = child.R, else child.L
					sr.father.R = sr.child.L == null ? sr.child.R : sr.child.L;
				}
			} else if (sr.child.L != null && sr.child.R != null) {
				// case node has two sons
				System.out.println(getBiggestChildNode(sr.child.L).key);
				int biggestLeftChildKey = getBiggestChildNode(sr.child.L).key;
				remove(biggestLeftChildKey);
				sr.child.key = biggestLeftChildKey;
			}
		}
		return true;
	}

	/*
	 * HELPER METHODS
	 */
	private Node getBiggestChildNode(Node src) {
		while (src.R != null)
			src = src.R;
		return src;
	}

	private Node buildTree(int[] a, int start, int end) {
		if (start > end)
			return null;
		int mid = start + (end - start) / 2;
		Node q = new Node();
		q.key = a[mid];
		q.L = buildTree(a, start, mid - 1);
		q.R = buildTree(a, mid + 1, end);
		return q;
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
		System.out.println(root.key);
	}

	private Boolean search(Node node, int key) {
		if (node == null)
			return false;
		if (node.key > key)
			return search(node.L, key);
		else if (node.key < key)
			return search(node.R, key);
		else
			return true;
	}

	private SearchResult explore(SearchResult sr, Node n, Node father, int key) {
		sr.father = father;
		if (n == null) {
			sr.child = null;
			return sr;
		}
		if (n.key > key) {
			sr.left = true;
			sr.child = n;
			return explore(sr, n.L, n, key);
		} else if (n.key < key) {
			sr.left = false;
			sr.child = n;
			return explore(sr, n.R, n, key);
		} else {
			sr.child = n;
			return sr;
		}
	}

	/*
	 * PRIVATE CLASS Node
	 */
	private static class Node {
		private int key;
		private Node L, R;
	}

	/*
	 * PRIVATE CLASS SearchResult
	 */
	private static class SearchResult {
		private boolean left;
		private Node father;
		private Node child;

		@Override
		public String toString() {
			if (child == null) {
				return ("Father is: " + father.key + " Append would be: "
						+ left + " Node does not exist yet!	");
			} else {
				return ("Father is: " + father.key + " Append would be: "
						+ left + " Element allready exists as: " + child.key);
			}
		}
	}

}
