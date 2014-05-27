package ch.fhnw.algd2.hashing;

public abstract class OpenHashMap<T> implements HashMap<T> {

	public abstract int h(int key);

	public abstract int s(int j, int key);

	private static enum State {
		FREE, OCCUPIED, DELETED
	};

	private int m;
	private Element[] hashTable;
	private int n;

	@SuppressWarnings("unchecked")
	public OpenHashMap(int size) {
		n = 0;
		hashTable = new OpenHashMap.Element[size];
		for (int i = 0; i < size; i++) {
			hashTable[i] = new Element(-1, null);
		}
	}

	@Override
	public int getSize() {
		return m;
	}

	protected int getTableSize() {
		return hashTable.length;
	}

	@Override
	public boolean contains(int key) {
		return find(key) != null ? true : false;
	}

	@Override
	public T get(int key) {
		return getElement(key).data;
	}

	private Element getElement(int key) {
		return find(key);
	}

	@Override
	public void put(int key, T data) {
	}

	@Override
	public void remove(int key) {

	}

	private class Element {
		private T data;
		private Integer key;
		private State state;

		public Element(int key, T data) {
			this.data = data;
			this.key = key;
			state = state.FREE;
		}

		@SuppressWarnings("unchecked")
		@Override
		public boolean equals(Object o) {
			return this.key.equals(((Element) o).key);
		}
	}

	@Override
	public String toString() {
		throw new UnsupportedOperationException("Not yet implemented.");
	}

	public double getLoad() {
		double r = n;
		return r / m;
	}

	private Element find(int key) {
		int j = 0;
		int i;
		int hashValue = h(key);
		do {
			i = ((hashValue - s(j, key)) % getTableSize() + getTableSize())
					% getTableSize();
			++j;
		} while (hashTable[i].state != State.FREE && hashTable[i].key != key);
		if (hashTable[i].state == State.OCCUPIED) {
			assert hashTable[i].key == key;
			return hashTable[i];
		}
		return null;
	}

}
