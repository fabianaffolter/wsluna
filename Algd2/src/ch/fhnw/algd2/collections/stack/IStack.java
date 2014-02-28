package ch.fhnw.algd2.collections.stack;

public interface IStack<E> {

	public E top();

	public void push(E data);

	public E pop();

	public boolean isEmpty();
}
