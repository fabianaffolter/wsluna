package ch.fhnw.algd2.collections.singlelinklist;

import java.util.NoSuchElementException;

public class SingleLinkList<E> {
	private int length;
	private Element<E> head;

	public SingleLinkList() {
		head = new Element<E>();
		length = 0;
	}

	public SingleLinkList(SingleLinkList<E> orig) {
		Element<E> tail = null;
		Element<E> current = orig.head;
		while (current != null) {
			if (head == null) {
				head = new Element<E>();
				head.data = current.data;
				head.next = null;
				tail = head;
			} else {
				tail.next = new Element<E>();
				tail = tail.next;
				tail.data = current.data;
				tail.next = null;
			}
			current = current.next;
		}
		length = orig.length;
	}

	public void insertFirst(E e) {
		Element<E> r = new Element<E>();
		r.data = e;
		r.next = head;
		head = r;
		++length;
	}

	public void insertAfter(E e, int index) {
		isValidIndex(index);
		Element<E> insert = new Element<E>();
		Element<E> current = head;
		Element<E> rr = null;
		// index = length - index - 1;
		++index;
		insert.data = e;
		int i = 0;
		while (current.next != null && i != index) {
			rr = current;
			current = current.next;
			++i;
		}
		if (!(rr == null)) {
			rr.next = insert;
			insert.next = current;
			++length;
		} else {
			insertFirst(e);
		}
	}

	public void removeFirst() {
		if (isEmpty())
			throw new NoSuchElementException("List empty.");
		remove(0);
	}

	private boolean isValidIndex(int index) {
		if (index < length && index >= 0)
			return true;
		else
			throw new IndexOutOfBoundsException("Illegal index.");
	}

	public void remove(int index) {
		isValidIndex(index);
		// index = length - index - 1;
		Element<E> rr = null;
		Element<E> current = head;
		int i = 0;
		while (current.next != null && i != index) {
			rr = current;
			current = current.next;
			++i;
		}
		if (rr == null)
			head = head.next;
		else
			rr.next = current.next;
		--length;
	}

	public void removeAll() {
		head.next = null;
		length = 0;
		// while (length > 0) {
		// removeFirst();
		// }
	}

	public E getFirst() {
		if (isEmpty())
			throw new NoSuchElementException("List empty.");
		return get(0);
	}

	public E get(int index) {
		isValidIndex(index);
		// index = length - index - 1;
		Element<E> current = head;
		int i = 0;
		while (current.next != null && i != index) {
			current = current.next;
			++i;
		}
		return current.data;

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
			Element<E> current = head;
			StringBuilder str = new StringBuilder();
			while (current.next != null) {
				str.append(current.data.toString() + "->");
				current = current.next;
			}
			str.append(".");
			return str.toString();
		}
	}

	private static class Element<E> {
		private Element<E> next;
		private E data;

	}
}
