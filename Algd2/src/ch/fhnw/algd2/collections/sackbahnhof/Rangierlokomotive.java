package ch.fhnw.algd2.collections.sackbahnhof;

import java.util.ArrayList;

import ch.fhnw.algd2.collections.stack.IStack;
import ch.fhnw.algd2.collections.stack.Stack;

public class Rangierlokomotive {

	public static void main(String[] args) {
		IStack<Character> s1 = new Stack<Character>();
		IStack<Character> s2 = new Stack<Character>();
		IStack<Character> s3 = new Stack<Character>();
		s1.push('A');
		s1.push('B');
		s1.push('A');
		s1.push('B');
		s2.push('B');
		s2.push('A');
		s2.push('A');
		System.out.println("In Bahnhof S1 sind vor dem Rangieren: "
				+ s1.toString());
		System.out.println("In Bahnhof S2 sind vor dem Rangieren: "
				+ s2.toString());
		System.out.println("In Bahnhof S2 sind vor dem Rangieren: "
				+ s3.toString());
		ArrayList<IStack<Character>> tracks = new ArrayList<IStack<Character>>();
		tracks.add(s1);
		tracks.add(s2);
		tracks.add(s3);
	}
}
