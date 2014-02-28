package ch.fhnw.algd2.collections.stack;

import ch.fhnw.algd2.collections.singlelinklist.SingleLinkList;

public class Stack<E> extends SingleLinkList<E> implements IStack<E> {

	@Override
	public E top() {
		return getFirst();
	}

	@Override
	public void push(E data) {
		insertFirst(data);
	}

	@Override
	public E pop() {
		E e = getFirst();
		removeFirst();
		return e;
	}
}
