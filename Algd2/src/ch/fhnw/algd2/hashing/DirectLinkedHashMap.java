package ch.fhnw.algd2.hashing;

import java.util.LinkedList;

public class DirectLinkedHashMap<T> implements HashMap<T> {

	private int m;
	private LinkedList<Element>[] hashTable;
	private int n;

	@SuppressWarnings("unchecked")
	public DirectLinkedHashMap(int size) {
		this.m = size;
		this.hashTable = new LinkedList[this.m];
	}

	@Override
	public int getSize() {
		return m;
	}

	@Override
	public boolean contains(int key) {
		return get(key) != null ? true : false;
	}

	@Override
	public T get(int key) {
		LinkedList<Element> pos = hashTable[h(key)];
		if (pos == null)
			return null;
		T n = null;
		for (Element r : pos) {
			if (r.key == key)
				n = r.data;
		}
		if (n != null)
			return n;
		else
			return null;
	}

	@Override
	public void put(int key, T data) {
		LinkedList<Element> pos = hashTable[h(key)];
		if (pos == null) {
			pos = new LinkedList<Element>();
			hashTable[h(key)] = pos;
		}
		pos.add(new Element(key, data));
		n++;
	}

	@Override
	public void remove(int key) {
		LinkedList<Element> pos = hashTable[h(key)];
		if (pos != null)
			pos.remove(get(key));
		n--;
	}

	private class Element {
		private T data;
		private Integer key;

		public Element(int key, T data) {
			this.data = data;
			this.key = key;
		}

		@SuppressWarnings("unchecked")
		@Override
		public boolean equals(Object o) {
			return this.key.equals(((Element) o).key);
		}
	}

	private int h(int key) {
		return key % m;
	}

	@Override
	public String toString() {
		StringBuilder output = new StringBuilder();
		for (int i = 0; i < m; ++i) {
			output.append("[Index " + i + ", Elemente];");

			if (hashTable[i] != null) {
				for (Element element : hashTable[i])
					output.append("\t" + element.key);
			}
			output.append("\n");
		}

		return output.toString();
	}

	public static void main(String argv[]) {
		DirectLinkedHashMap<String> table = new DirectLinkedHashMap<>(7);
		table.put(1, "eins");
		table.put(55, "fuenfundfuenfzig");
		table.put(87, "siebenundachtzig");
		table.put(3, "drei");
		table.put(10, "zehn");
		table.put(2332, "MAXICOSI");
		table.put(22, "jogi");
		System.out.println(table);
		table.remove(1);
		System.out.println(table);
	}

}
