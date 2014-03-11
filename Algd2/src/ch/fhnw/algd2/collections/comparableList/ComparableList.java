package ch.fhnw.algd2.collections.comparableList;

import java.util.ListIterator;
import java.util.NoSuchElementException;

public class ComparableList<E extends Comparable<E>> {
	private int length;
	private Element<E> head;
	private Element<E> tail;

	public ComparableList() {
		head = new Element<E>();
		head.prev = head;
		tail = new Element<E>();
		tail.next = tail;
		tail.prev = head;
		head.next = tail;
		length = 0;
	}

	public void addHead(E e) {
		Element<E> r = new Element<E>();
		r.next = head.next;
		r.prev = head;
		head.next.prev = r;
		head.next = r;
		r.data = e;
		++length;
	}

	public void addTail(E e) {
		Element<E> r = new Element<E>();
		tail.prev.next = r;
		r.next = tail;
		r.prev = tail.prev;
		tail.prev = r;
		r.data = e;
		++length;
	}

	public E removeHead() {
		if (isEmpty())
			throw new NoSuchElementException("List empty.");
		E value = head.next.data;
		Element<E> r = head.next;
		r.prev.next = r.next;
		r.next.prev = r.prev;
		--length;
		return value;
	}

	public E removeTail() {
		if (isEmpty())
			throw new NoSuchElementException("List empty.");
		E value = tail.prev.data;
		Element<E> r = tail.prev;
		r.prev.next = r.next;
		r.next.prev = r.prev;
		--length;
		return value;
	}

	public int size() {
		return length;
	}

	public boolean isEmpty() {
		return length == 0;
	}

	@Override
	public String toString() {
		if (isEmpty()) {
			return "List is empty.";
		} else {
			Element<E> current = head.next;
			StringBuilder str = new StringBuilder();
			while (current != tail) {
				str.append(current.data.toString() + "->");
				current = current.next;
			}
			str.append(".");
			return str.toString();
		}
	}

	private static class Element<T> {
		private Element<T> next;
		private Element<T> prev;
		private T data;

	}

	private void remove(Element<E> returned) {
		Element<E> current = head;
		while (current.next != null && current != returned) {
			current = current.next;
		}
		current.prev.next = current.next;
		current.next.prev = current.prev;
		--length;
	}

	/*
	 * Must have external constructors thus it's private
	 */
	public CListIterator iterator() {
		return new CListIterator();
	}

	public CListIterator iterator(int index) {
		return new CListIterator(index);
	}

	private class CListIterator implements ListIterator<E> {
		private Element<E> returned;
		private Element<E> next;
		private int index;

		private CListIterator() {
			returned = null;
			index = 0;
			next = head.next;
		}

		private CListIterator(int index) {
			returned = null;
			this.index = index;
			int i = 0;
			next = head.next;
			while (i <= index && hasNext()) {
				returned = next;
				next = next.next;
				i++;
			}
		}

		@Override
		public boolean hasNext() {
			return (next != tail);
		}

		@Override
		public E next() {
			E data = next.data;
			returned = next;
			next = next.next;
			index++;
			return data;
		}

		@Override
		public boolean hasPrevious() {
			return (next.prev != head);
		}

		@Override
		public E previous() {
			E data = next.prev.data;
			returned = next.prev;
			next = next.prev;
			index--;
			return data;
		}

		@Override
		public int nextIndex() {
			return index + 1;
		}

		@Override
		public int previousIndex() {
			return index - 1;
		}

		@Override
		public void remove() {
			if (returned == null) {
				throw new IllegalStateException();
			} else {
				if (returned == next) {
					next = returned.next;
				} else
					index--;
				ComparableList.this.remove(returned);
				returned = null;
			}
		}

		@Override
		public void set(E e) {
			if (returned == null)
				throw new IllegalStateException();
			else {
				if (!(e instanceof Comparable<?>)) {
					throw new IllegalArgumentException();
				}
				returned.data = e;
			}
		}

		/*
		 * Currently copied from H. Veitschegger (non-Javadoc) Inserts param e
		 * at position of iterator, list itself does not provide an "insert"
		 * statement
		 * 
		 * @see java.util.ListIterator#add(java.lang.Object)
		 */
		@Override
		public void add(E e) {
			Element<E> r = new Element<E>();
			r.data = e;
			r.prev = next.prev;
			r.next = next;
			next.prev.next = r;
			next.prev = r;
			++length;
		}
	}

	/*
	 * Sorts the list with the mergesort algorithm Currently copied from H.
	 * Veitschegger
	 */
	public void mergeSort() {
		if (this.size() > 1) {
			ComparableList<E> leftPart = this.split(); // O(n)
			leftPart.mergeSort();
			this.mergeSort();
			merge(leftPart);
		}
	}

	/*
	 * Merges the list this and other, result is in this Currently copied from
	 * H. Veitschegger
	 */
	private void merge(ComparableList<E> other) {
		CListIterator group1 = this.iterator();
		CListIterator group2 = other.iterator();
		CListIterator insert = this.iterator();
		E element1, element2;
		while (group1.hasNext() || group2.hasNext()) {
			if (group1.hasNext() && group2.hasNext()) {
				element1 = group1.next();
				element2 = group2.next();
				if (element1.compareTo(element2) < 0) {
					insert.add(element1);
					group2.previous();
				} else {
					insert.add(element2);
					group1.previous();
				}
			} else {
				if (group1.hasNext()) {
					insert.add(group1.next());
				} else {
					insert.add(group2.next());
				}
			}
		}
		// insert is at the end of the while, but the original group still
		// exists
		// the loop below deletes the rest of the origin
		while (insert.hasNext()) {
			insert.next();
			insert.remove();
		}
	}

	/*
	 * Splits this into two parts, returns left side as new list, right side
	 * O(n) Currently copied from H. Veitschegger stays in this
	 */
	private ComparableList<E> split() {
		ComparableList<E> leftPart = new ComparableList<E>();
		int size = this.size() / 2;
		for (int i = 0; i < size; ++i) {
			leftPart.addTail(this.removeHead());
		}
		return leftPart;
	}
}
