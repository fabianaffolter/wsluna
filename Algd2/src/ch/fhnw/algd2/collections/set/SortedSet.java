package ch.fhnw.algd2.collections.set;

import java.util.Arrays;
import java.util.Set;

import ch.fhnw.algd2.collections.abstracts.MyAbstractCollectionSet;

public class SortedSet<E extends Comparable<E>> extends MyAbstractCollectionSet<E>
		implements Set<E> {

	public static final int DEFAULT_CAPACITY = 100;
	private int objectcount;
	private Object[] data;

	public SortedSet() {
		this(DEFAULT_CAPACITY);
	}

	public SortedSet(int capacity) {
		data = new Object[capacity];
	}

	@Override
	public boolean add(E e) {
		checkNull(e);
		if (objectcount == data.length) {
			throw new IllegalStateException("Capacity overflow.");
		} else if (this.contains(e)) {
			return false;
		} else {
			insertObject(e);
			return true;
		}
	}

	private void insertObject(E e) {
		int index = indexOf(e);
		if (index < 0) {
			index = ~index;
		}
		for (int i = objectcount - 1; i >= index; i--) {
			data[i + 1] = data[i];
		}
		data[index] = e;
		++objectcount;
	}

	@Override
	public boolean remove(Object o) {
		int loc = indexOf(o);
		if (loc > -1) {
			for (int i = loc; i < objectcount; i++) {
				if (i + 1 < objectcount) {
					data[i] = data[i + 1];
				}
			}
			--objectcount;
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean contains(Object o) {
		checkNull(o);
		return (indexOf(o) >= 0);
	}

	private int indexOf(Object o) {
		return Arrays.binarySearch(data, 0, objectcount, o);
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
		SortedSet<Integer> bag = new SortedSet<Integer>();
		bag.add(1);
		bag.add(2);
		bag.add(3);
		bag.add(4);
		bag.add(5);
		bag.add(4);
		System.out.println(Arrays.toString(bag.toArray()));
		System.out.println(bag.contains(5));
		System.out.println(bag.contains(6));
		System.out.println(bag.remove(5));
		System.out.println(bag.contains(5));
		System.out.println(Arrays.toString(bag.toArray()));
	}

}
