package ch.fhnw.algd2.collections.bag;

import static org.junit.Assert.assertFalse;

import org.junit.Test;

import ch.fhnw.algd2.collections.abstracts.MyAbstractCollectionBag;

public class UnsortedBagTest<E extends Comparable<E>> extends
		AbstractBagTest {

	@Override
	protected MyAbstractCollectionBag<Integer> createIntegerCollection(int size) {
		return new UnsortedBag<Integer>(size);
	}

	@Override
	protected Integer[] getExpectedOrderFor(Integer[] values) {
		return values;
	}

	
	@Test
	public void containsOtherObject() {
		Integer[] numbers = new Integer[] { 1, 2, 3 };
		addNumbersToBag(numbers);
		assertFalse(bag.contains("Asdf"));
	}
	
}
