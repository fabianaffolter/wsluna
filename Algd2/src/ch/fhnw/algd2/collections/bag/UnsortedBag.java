package ch.fhnw.algd2.collections.bag;

import java.util.Arrays;

import ch.fhnw.algd2.collections.abstracts.MyAbstractCollectionBag;

public class UnsortedBag<E extends Comparable<E>> extends
		MyAbstractCollectionBag<E> {

	public static final int DEFAULT_CAPACITY = 100;

	private Object[] data;
	private int objectcount;

	public UnsortedBag() {
		this(DEFAULT_CAPACITY);
	}

	public UnsortedBag(int capacity) {

		data = new Object[capacity];
		this.objectcount = 0;
	}

	@Override
	public boolean add(E e) {
		checkNull(e);
		if (objectcount == data.length) {
			throw new IllegalStateException("Capacity overflow.");
		} else {
			data[objectcount++] = e;
			return true;
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
		UnsortedBag<Integer> bag = new UnsortedBag<Integer>();
		bag.add(2);
		bag.add(1);
		bag.add(3);
		bag.add(5);
		bag.add(4);
		System.out.println(bag.contains(5));
		System.out.println(bag.contains(6));
		System.out.println(bag.remove(5));
		System.out.println(Arrays.toString(bag.toArray()));
	}

}
