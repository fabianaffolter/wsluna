package ch.fhnw.algd2.hashing;

public class LinearHashMap<T> extends OpenHashMap<T> {
	public LinearHashMap(int size) {
		super(size);
	}

	@Override
	public int h(int key) {
		return 0;
	}

	@Override
	public int s(int j, int key) {
		return 0;
	}

	public static void main(String argv[]) {
		LinearHashMap<String> table = new LinearHashMap<String>(
				7);
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
		System.out.println(table.getLoad());
	}
}
