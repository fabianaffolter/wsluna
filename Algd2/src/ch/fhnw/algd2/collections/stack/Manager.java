package ch.fhnw.algd2.collections.stack;

public class Manager {

	public static void main(String[] args) {
		IStack<Double> doubleStack = new Stack<>();
		doubleStack.push(2.3);
		doubleStack.push(3.4);
		doubleStack.push(1.2);
		doubleStack.push(9.8);
		doubleStack.push(6.7);
		System.out.println("New stack: " + doubleStack.toString());
		System.out.println(doubleStack.top());
		System.out.println("Stack: " + doubleStack.toString());
		System.out.println(doubleStack.pop());
		System.out.println("Stack: " + doubleStack.toString());
		System.out.println(doubleStack.isEmpty());
	}
}
