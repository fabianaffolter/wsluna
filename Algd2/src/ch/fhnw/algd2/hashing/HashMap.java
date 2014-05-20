package ch.fhnw.algd2.hashing;

public interface HashMap<T> {
	int getSize();

	boolean contains(int key);

	T get(int key);

	void put(int key, T data);

	void remove(int key);
}
