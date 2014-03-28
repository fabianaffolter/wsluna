package ch.fhnw.playground.java8;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class java8 {

	public static void main(String[] args) {
		List<String> myList = Arrays.asList("element1", "element2", "element3");
		myList.forEach(element -> System.out.println(element));

		Collection<String> myList2 = Arrays.asList("Hello", "Java", "Affolter",
				"Pepe", "Meyer");
		System.out.println(myList2.stream()
				.filter(element -> element.length() > 4).count());
		System.out.println(myList2
				.stream()
				.filter(element -> element.contains("a")
						|| element.contains("A")).count());
	}
}
