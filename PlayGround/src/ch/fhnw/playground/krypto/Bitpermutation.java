package ch.fhnw.playground.krypto;

public class Bitpermutation {
	/**
	 * 
	 * 
	 * AB HIER EDITIEREN:
	 * 
	 * 
	 * */
	static int a = 0b1111; // 1. int Block
	static int b = 0b0111; // 2. int Block
	static int c = 0b0001; // 3. int Block
	static int d = 0b0100; // 4. int Block

	// EDIT PERMUTATION PATTERN HERE
	// DO NOT USE INDICES BIGGER THAN 15 AND COVER RANGE!
	public static boolean[] boolPermutation(boolean[] origin) {
		boolean[] twin = new boolean[origin.length];
		twin[0] = origin[9];
		twin[1] = origin[10];
		twin[2] = origin[11];
		twin[3] = origin[12];
		twin[4] = origin[13];
		twin[5] = origin[14];
		twin[6] = origin[15];
		twin[7] = origin[0];
		twin[8] = origin[1];
		twin[9] = origin[2];
		twin[10] = origin[3];
		twin[11] = origin[4];
		twin[12] = origin[5];
		twin[13] = origin[6];
		twin[14] = origin[7];
		twin[15] = origin[8];
		return twin;
	}

	/**
	 * 
	 * 
	 * AB HIER NUR IM NOTFALL EDITIEREN!
	 * 
	 * 
	 * */
	public static void main(String[] args) {
		init();
	}

	public static int[] encryption(int a, int b, int c, int d) {
		int[] in = new int[4];
		in[0] = a;
		in[1] = b;
		in[2] = c;
		in[3] = d;

		StringBuilder str = new StringBuilder();
		for (int block : in) {
			checkValidInput(block);
			String s = Integer.toBinaryString(block);
			while (s.length() < 4)
				s = "0" + s;
			str.append(s);
		}
		System.out.println("Before permutation:\t" + str);

		// to boolean array
		boolean[] boolarray;
		if (str.length() != 16)
			throw new IllegalStateException("Does not match 16 bit pattern.");
		else
			boolarray = new boolean[16];
		for (int i = 0; i < str.length(); ++i) {
			if (str.charAt(i) == '0')
				boolarray[i] = false;
			else
				boolarray[i] = true;
		}
		str.delete(0, str.length());
		boolean[] twin = boolPermutation(boolarray);
		for (int i = 0; i < twin.length; ++i) {
			if (twin[i] == false)
				str.append('0');
			else
				str.append('1');
		}
		System.out.println("After permutation:\t" + str);
		int w = 0;
		int x = 0;
		int y = 0;
		int z = 0;
		String sub1 = str.substring(0, 4);
		String sub2 = str.substring(4, 8);
		String sub3 = str.substring(8, 12);
		String sub4 = str.substring(12, 16);
		w = Integer.parseInt(sub1, 2);
		x = Integer.parseInt(sub2, 2);
		y = Integer.parseInt(sub3, 2);
		z = Integer.parseInt(sub4, 2);
		int[] result = new int[4];
		result[0] = w;
		result[1] = x;
		result[2] = y;
		result[3] = z;
		return result;
	}

	public static void checkValidInput(int a) {
		if (a > 15) {
			throw new IllegalStateException("Input " + a
					+ " does not match input pattern!");
		}
	}

	public static void init() {
		System.out.println("Input decimal:\t\t" + a + "/" + b + "/" + c + "/"
				+ d);
		System.out.println("Input binary:\t\t[" + Integer.toBinaryString(a)
				+ "/" + Integer.toBinaryString(b) + "/"
				+ Integer.toBinaryString(c) + "/" + Integer.toBinaryString(d)
				+ "]\n");
		int[] myencryptedarray = encryption(a, b, c, d);
		System.out.println("\nOutput decimal:\t\t" + myencryptedarray[0] + "/"
				+ myencryptedarray[1] + "/" + myencryptedarray[2] + "/"
				+ myencryptedarray[3]);
		System.out.println("Output binary:\t\t["
				+ Integer.toBinaryString(myencryptedarray[0]) + "/"
				+ Integer.toBinaryString(myencryptedarray[1]) + "/"
				+ Integer.toBinaryString(myencryptedarray[2]) + "/"
				+ Integer.toBinaryString(myencryptedarray[3]) + "]");
	}
}
