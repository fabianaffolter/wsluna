package ch.fhnw.algd2.hashing;

public class MyHashArray<T> {
	private T[] hashTable;
	private int m;

	public MyHashArray(int size) {
		this.m = size;
		this.hashTable = (T[]) new Object[this.m];
	}

	public void insert(int key, T obj) {
		hashTable[h(key)] = obj;
	}

	private int h(int key) {
		return key % m;
	}

	public void remove(int key) {
		hashTable[h(key)] = null;
	}

	public T find(int key) {
		return hashTable[h(key)];
	}

	@Override
	public String toString() {
		StringBuilder output = new StringBuilder();
		for (int i = 0; i < m; ++i)
			output.append("[Index \"" + i + "\", Object \"" + hashTable[i]
					+ "\"]; \n");
		output.delete(output.length() - 3, output.length());
		return output.toString();
	}

	public static void main(String[] args) {
		MyHashArray<String> table = new MyHashArray<String>(7);
		table.insert(1, "eins");
		table.insert(55, "fuenfundfuenfzig");
		table.insert(87, "siebenundachtzig");
		table.insert(3, "drei");
		table.insert(10, "zehn");
		table.insert(Integer.MAX_VALUE, "MAXICOSI");
		table.insert(22, "jogi");
		System.out.println(table);
		System.out.println(table.find(1));
		table.remove(1);
		System.out.println(table.find(1));
	}
	// Negative Schlüssel geben keine Exception
	// Bestehende Werte werden überschrieben -> Kollision

	// --> Definitiv nicht einsetzbar

}