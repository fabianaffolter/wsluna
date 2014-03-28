package ch.fhnw.playground.java8;

public interface interfacewithdefault {
	void run();

	public default void forEach(int[] q) {
		q[0] = q[0] * q[1];
	}
}
