package ch.fhnw.algd2.collections.stackcalc;

import terminal.VT;
import terminal.VTerm;
import ch.fhnw.algd2.collections.stack.IStack;
import ch.fhnw.algd2.collections.stack.Stack;

public class stackcalculator {

	public static void main(String[] args) {
		VTerm console = VTerm.getInstance(25, 80, "StackCalculator",
				VT.CS_SMALL);
		console.setColor(VT.GREEN);
		console.println("StackCalculator");
		console.println("Accepts digits from 0 to 9 and the operators + - * /");
		console.setColor(VT.GREY);
		console.println("Enter number, press enter. Enter operator to start calculation.");
		console.println("Press [ESC] to exit.");
		console.setColor(VT.SILVER);
		boolean leave = false;
		IStack<Integer> stack = new Stack<Integer>();
		char c = 0;
		int stacksize = 0;
		int res;
		StringBuilder str = new StringBuilder();

		while (!leave) {
			c = console.readChar();
			if (c == VT.K_ESCAPE) {
				leave = true;
			} else if (isValidInput(c)) {
				if (isOperator(c)) {
					if (stacksize > 1) {
						res = calculate(stack.pop(), stack.top(), c);
						stacksize--;
						console.print(c + " " + Integer.toString(res) + " ");
						stack.push(res);
					} else {
						console.setColor(VT.RED);
						console.print("Stack size too small.");
						console.setColor(VT.SILVER);
					}
				} else if (c == VT.K_ENTER) {
					stack.push(Integer.parseInt(str.toString()));
					++stacksize;
					str.delete(0, str.length());
					console.print(" ");
				} else {
					console.print(c);
					str.append(c);
				}
			} else {
				console.setColor(VT.RED);
				console.print("Invalid sign.");
				console.setColor(VT.SILVER);
			}
		}
		console.readyToExit(false);
	}

	public static boolean isValidInput(char c) {
		switch (c) {
		case '0':
			return true;
		case '1':
			return true;
		case '2':
			return true;
		case '3':
			return true;
		case '4':
			return true;
		case '5':
			return true;
		case '6':
			return true;
		case '7':
			return true;
		case '8':
			return true;
		case '9':
			return true;
		case '+':
			return true;
		case '-':
			return true;
		case '*':
			return true;
		case '/':
			return true;
		case VT.K_ENTER:
			return true;
		default:
			return false;
		}
	}

	public static boolean isOperator(char c) {
		switch (c) {
		case '+':
			return true;
		case '-':
			return true;
		case '*':
			return true;
		case '/':
			return true;
		default:
			return false;
		}
	}

	public static int calculate(int a, int b, int c) {
		switch (c) {
		case '+':
			return a + b;
		case '-':
			return a - b;
		case '*':
			return a * b;
		case '/':
			return b / a;
		default:
			return 0;
		}
	}

}
