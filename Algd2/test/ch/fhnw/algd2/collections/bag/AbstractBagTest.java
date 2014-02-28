package ch.fhnw.algd2.collections.bag;

import static org.junit.Assert.assertTrue;
import ch.fhnw.algd2.collections.abstracts.AbstractCollectionBagTest;

public abstract class AbstractBagTest extends AbstractCollectionBagTest {

	@Override
	protected void addNumbersToBag(Integer[] numbers) {
		for (Integer number : numbers) {
			assertTrue(bag.add(number));
		}
	}

}
