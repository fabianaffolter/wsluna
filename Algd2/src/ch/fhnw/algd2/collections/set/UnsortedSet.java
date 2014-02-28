package ch.fhnw.algd2.collections.set;

import java.util.Arrays;
import java.util.Set;

import ch.fhnw.algd2.collections.abstracts.MyAbstractCollectionSet;

public class UnsortedSet<E extends Comparable<E>> extends
		MyAbstractCollectionSet<E> implements Set<E> {

	public static final int DEFAULT_CAPACITY = 100;
	private int objectcount;

	private Object[] data;

	public UnsortedSet() {
		this(DEFAULT_CAPACITY);
	}

	public UnsortedSet(int capacity) {
		data = new Object[capacity];
	}

	public boolean add(E e) {
		checkNull(e);
		if (objectcount == data.length) {
			throw new IllegalStateException("Capacity overflow.");
		} else {
			if (!contains(e)) {
				data[objectcount++] = e;
				return true;
			} else {
				return false;
			}
		}
	}

	@Override
	public boolean remove(Object o) {
		int loc = indexOf(o);
		if (loc > -1) {
			--objectcount;
			for (int i = loc; i < objectcount; i++) {
				data[i] = data[i + 1];
			}
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean contains(Object o) {
		checkNull(o);
		if (indexOf(o) == -1)
			return false;
		else
			return true;
	}

	private int indexOf(Object o) {
		for (int i = 0; i < objectcount; i++) {
			if (data[i].equals(o))
				return i;
		}
		return -1;
	}

	@Override
	public Object[] toArray() {
		return Arrays.copyOf(data, size());
	}

	@Override
	public int size() {
		return objectcount;
	}

	public static void main(String[] args) {
		UnsortedSet<Integer> bag = new UnsortedSet<Integer>();
		bag.add(2);
		bag.add(2);
		bag.add(1);
		bag.add(3);
		bag.add(3);
		bag.add(1);
		bag.remove(3);
		System.out.println(Arrays.toString(bag.toArray()));
	}

}
