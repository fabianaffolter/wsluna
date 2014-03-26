package ch.fhnw.algd2.collections.avltree;

//******************************************************************************
//  FHNW.ALGD2  -  Excercise 5 : AVL Trees                                     *
// --------------------------------------------------------------------------- *
//  vorgegebene Elemente                                                       *
//******************************************************************************

public class AVLTree {

	// ***** API
	// *******************************************************************

	public AVLTree() {
		m_root.R = null;
		m_root.U = m_root;
	}

	public AVLTree(int[] sorted) {
		this();
		m_root.R = buildTree(sorted, 0, sorted.length - 1, m_root);
		updateBalances(m_root.R);
	}

	public void show() {
		traverse(m_root.R, 0);
		rotateLeft(find(5).node);
		System.out.println("***");
		traverse(m_root.R, 0);
	}

	public boolean exists(int key) {
		Node r = m_root.R;
		while (r != null) {
			if (r.key == key)
				return true;
			r = key > r.key ? r.R : r.L;
		}
		return false;
	}

	public boolean insert(int key) {
		// TODO : assignment 5.3
		SearchResult sr = find(key);
		if (sr.node != null) {
			System.out.println("Key already exists, no action taken");
			return false;
		} else {
			sr.node = new Node(key);
			sr.node.key = key;
			if (sr.isLeftChild) {
				sr.parent.L = sr.node;
				sr.parent.L.bal = 0;
				--sr.parent.bal;
			} else {
				sr.parent.R = sr.node;
				sr.parent.R.bal = 0;
				++sr.parent.bal;
			}
			if (sr.parent.bal != 0)
				updateIn(sr.parent);
			return true;
		}
	}

	public boolean remove(int key) {
		return remove(key, null);
	}

	public boolean checkBalanceFactors() {
		return balanceInfoIsCorrect(m_root.R);
	}

	// *****************************************************************************
	// ***** auxiliaries
	// ***********************************************************
	// *****************************************************************************

	private Node buildTree(int[] a, int start, int end, Node parent) {
		Node ret = null;
		if (start <= end) {
			int M = (start + end) / 2;
			ret = new Node(a[M]);
			ret.U = parent;
			ret.bal = 0;
			ret.L = buildTree(a, start, M - 1, ret);
			ret.R = buildTree(a, M + 1, end, ret);
		}
		return ret;
	}

	// used to show a tree semigraphically
	private void traverse(Node root, int level) {
		if (root != null) {
			traverse(root.R, level + 1);
			for (int i = 0; i < level; ++i)
				System.out.print("        ");
			System.out.print("[");
			System.out.format("%1$03d ", root.key);
			if (root.bal != 0)
				System.out.format("%1$+2d", root.bal);
			else
				System.out.print("\u00B70");
			System.out.print("]");
			System.out.println();
			traverse(root.L, level + 1);
		}
	}

	private SearchResult find(int key) {
		SearchResult res = new SearchResult(m_root, m_root.R, false);
		while (res.node != null) {
			if (res.node.key == key)
				return res;
			res.parent = res.node;
			if (key > res.node.key) {
				res.node = res.node.R;
				res.isLeftChild = false;
			} else {
				res.node = res.node.L;
				res.isLeftChild = true;
			}
		}
		return res;
	}

	// this method is used when a tree is generated from a sorted Array
	private int updateBalances(Node n) {
		if (n != null) {
			int left = updateBalances(n.L);
			int rght = updateBalances(n.R);
			n.bal = rght - left;
			return 1 + (left > rght ? left : rght);
		} else {
			return 0;
		}
	}

	// removes node using optimized search, if starting node is known (pointed
	// by r)
	private boolean remove(int key, SearchResult r) {
		// TODO : assignment 5.4
		return false;
	}

	// search removal-substitute for node p
	private SearchResult searchNearestSmaller(Node p) {
		// int ref = p.key;
		// if (p.U == m_root) {
		// System.out.println("Fall element ist root");
		// p = getBiggestChildNode(p.L);
		// return new SearchResult(p.U, p, false);
		// } else if (p.U.R == p && p.L != null && p.R != null) {
		// System.out.println("Fall element ist rechts & hat Kinder");
		// p = getBiggestChildNode(p.L);
		// return new SearchResult(p.U, p, false);
		// } else if (p.U.R == p) {
		// System.out.println("Fall element ist rechts & hat KEINE Kinder");
		// return new SearchResult(p.U.U, p.U, false);
		// } else if (p.L != null && p.L.L == null && p.L.R == null) {
		// System.out
		// .println("Fall element ist links und hat linkes kind und das hat keine kinder mehr");
		// return new SearchResult(p, p.L, false);
		// } else if (p.L == null) {
		// System.out.println("Fall element ist links und hat KEIN kind");
		// return null;
		// } else {
		// System.out.println("kein fall");
		// return null;
		// }
		SearchResult s = new SearchResult(p, p.L, true);
		while (s.node.R != null) {
			s.parent = s.node;
			s.node = s.node.R;
			s.isLeftChild = false;
		}
		return s;
	}

	// searches biggest child of specific node
	private Node getBiggestChildNode(Node src) {
		while (src.R != null)
			src = src.R;
		return src;
	}

	// trying to raise an empty sub-tree will cause a nullpointer exception
	private void rotateRight(Node p) {
		checkNodeNoChilds(p.L);
		int prebal = p.L.bal;
		if (p.L.R == null) {
			// case left child has right child == null
			p.L.U = p.U;
			p.L.R = p;
			// p.U.R = p.L;
			if (p.U.R == p)
				p.U.R = p.L; // attach to father at left
			else
				p.U.L = p.L; // attach to father at right
			p.U = p.L;
			p.L = null;
		} else {
			// case left child has right child != null
			Node q = p.L.R;
			p.L.U = p.U;
			p.L.R = p;
			// p.U.R = p.L;
			if (p.U.R == p)
				p.U.R = p.L; // attach to father at left
			else
				p.U.L = p.L; // attach to father at right
			p.U = p.L;
			p.L = q;
			q.U = p;
		}
		p.bal = p.U.bal = 0;
		if (prebal == 0) {
			p.bal = 1;
			p.U.bal = -1;
		}
	}

	// trying to raise an empty sub-tree will cause a nullpointer exception
	private void rotateLeft(Node p) {
		checkNodeNoChilds(p.R);
		int prebal = p.R.bal;
		if (p.R.L == null) {
			// case right child has left child == null
			Node u = p.U; // save current nodes father
			p.R.L = p; // assign p as new child of right child
			if (p.U.L == p)
				p.U.L = p.R; // attach to father at left
			else
				p.U.R = p.R; // attach to father at right
			p.U = p.R; // make right child to father
			p.R = null; // delete right reference
			p.U.U = u; // assign new root node the old father
		} else {
			// case right child has left child != null
			Node q = p.R.L; // save current nodes right child left reference
			q.U = p;
			p.R.L = p; // assign p as new child of right child
			if (p.U.L == p)
				p.U.L = p.R; // attach to father at left
			else
				p.U.R = p.R; // attach to father at right
			p.R.U = p.U; // change fathers
			p.U = p.R; // change father of p
			p.R = q; // assign old left node
			p.U.R.L = null; // override old place of q
		}
		p.bal = p.U.bal = 0;
		if (prebal == 0) {
			p.bal = 1;
			p.U.bal = -1;
		}
	}

	private void updateIn(Node p) {
		// E00: (bal(f)=0): f wurde durch das Einfügen ausgeglichen. hf ändert
		// sich nicht: fertig!
		// E01: |bal(f)|=1: hf hat sich geändert. Neue Iteration durch
		// updateIn() mit p := f und f := father(f)
		// E02: |bal(f)|=2: Führe eine Rotation über f aus. Nach einer
		// einfachen Rotation ist p die Wurzel des Teil- baumes: updateIn wird
		// mit f := father(p)) erneut iteriert, um bal(f) anzupassen. Nach einer
		// Doppelrotation liegt p um eine Ebene unter der Wurzel, und es wird
		// mit father(p) sowie mit father(father(p)) erneut iteriert.
		Node f;
		while (p.U.U != p.U) { // while p is not root
			f = p.U;
			f.bal += (f.L == p ? -1 : 1); // update parent's balance
			if (f.bal == 0) {
				return;
			} else if (f.bal * f.bal == 1) { // -1 or +1
				p = f; // cycle up with p's parent
			} else { // severe inbalance : rotate
				if (f.bal < 0) {
					if (p.bal > 0) {
						Node r = p.R;
						int bal = p.R.bal;
						rotateLeft(p);
						rotateRight(f);
						f.bal = p.bal = r.bal = 0;
						if (bal == 1)
							p.bal = -1;
						else if (bal == -1)
							f.bal = 1;
					} else {
						rotateRight(f);
					}
				} else {
					if (p.bal < 0) {
						Node l = p.L;
						int bal = p.L.bal;
						rotateRight(p);
						rotateLeft(f);
						f.bal = p.bal = l.bal = 0;
						if (bal == 1)
							f.bal = -1;
						else if (bal == -1)
							p.bal = 1;
					} else {
						rotateLeft(f);
					}
				}
				return;
			}
		}
	}

	private void updateOut(Node p) {
		// TODO : assignment 5.4
	}

	// compute depth of a tree : for testing issues only
	private int getDepth(Node root) {
		if (root == null) {
			return 0;
		} else {
			return 1 + Math.max(getDepth(root.L), getDepth(root.R));
		}
	}

	// check balance of a certain node : for testing issues only
	private boolean balanceInfoIsCorrect(Node node) {
		if (node == null) {
			return true;
		} else {
			return (getDepth(node.R) - getDepth(node.L) == node.bal)
					&& balanceInfoIsCorrect(node.R)
					&& balanceInfoIsCorrect(node.L);
		}
	}

	private void checkNodeNoChilds(Node p) {
		if (p == null)
			throw new NullPointerException("Node is empty.");
	}

	// *****************************************************************************
	// ***** attributes, constants & nested classes
	// ********************************
	// *****************************************************************************

	private Node m_root = new Node(Integer.MIN_VALUE);

	private static class Node {
		int key;
		int bal;
		Node L;
		Node R;
		Node U;

		Node(int key) {
			this.key = key;
		}
	}

	private static class SearchResult {
		Node node;
		Node parent;
		boolean isLeftChild;

		SearchResult(Node parent, Node result, boolean left) {
			this.node = result;
			this.parent = parent;
			this.isLeftChild = left;
		}
	}

}
