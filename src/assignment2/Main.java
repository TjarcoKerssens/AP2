package assignment2;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {

	void run() {
		Scanner in = new Scanner(System.in);
		in.useDelimiter("");
		while (in.hasNextLine()) {
			try {
				readRow(in);
			} catch (APException e) {
				e.printStackTrace();
			}
		}
	}

	boolean nextCharIs(Scanner in, char c) {
		return in.hasNext(Pattern.quote(c + ""));
	}

	boolean nextCharIsLetter(Scanner in) {
		return in.hasNext(Pattern.compile("[A-Za-z]"));
	}

	boolean nextCharIsDigit(Scanner in) {
		return in.hasNext(Pattern.compile("[0-9]"));

	}

	boolean nextCharIsAdditiveOperator(Scanner in) {
		return in.hasNext(Pattern.compile("[+-//|]"));
	}
	
	boolean nextCharIsMultiplicativeOperator(Scanner in){
		return in.hasNext(Pattern.quote("*"));
	}
	
	void trimSpace(Scanner in){
		while(nextCharIs(in, ' ')){
			in.next();
		}
	}

	void character(Scanner in, char c) throws APException {
		if (!nextCharIs(in, c)) {
			throw new APException("");
		}

		in.next();
	}

	void readRow(Scanner in) throws APException {
		if (nextCharIsLetter(in)) {
			readAssignment(in);
		} else if (nextCharIs(in, '?')) {
			readPrintStatement(in);
		} else if (nextCharIs(in, '/')) {
			procesComment(in);
		} else {
			throw new APException("Invallid start of row");
		}

	}

	void readStatement(Scanner in) {

	}

	void readAssignment(Scanner in) throws APException {
		readIdentifier(in);
		readExpression(in);
	}

	void readPrintStatement(Scanner in) {

	}

	void procesComment(Scanner in) {

	}

	void readIdentifier(Scanner in) {

	}

	void readExpression(Scanner in) throws APException {
		readTerm(in);
		while (nextCharIsAdditiveOperator(in)) {
			readTerm(in);
		}
	}

	void readTerm(Scanner in) throws APException {
		readFactor(in);
		while (nextCharIsMultiplicativeOperator(in)) {
			readFactor(in);
		}
	}

	void readFactor(Scanner in) throws APException {
		if (nextCharIsLetter(in)) {
			readIdentifier(in);
		} else if (nextCharIs(in, '(')) {
			in.next();
			readExpression(in); //readComplexFactor()
			character(in, ')');
		} else if (nextCharIs(in, '{')) {
			readSet(in);
		}
	}

	void readSet(Scanner in) throws APException {
		character(in, '{');
		while (!nextCharIs(in, '}')) {
			readNumber(in);
		}
	}

	void readNumber(Scanner in) throws APException {
		while (!nextCharIs(in, ' ')) {//Zolang er nog een cijfer is.
			readDigit(in);
		}
	}

	char readDigit(Scanner in) throws APException {
		if (!nextCharIsDigit(in)) {
			throw new APException("not a digit");
		}
		return in.next().charAt(0);
	}

	public static void main(String[] args) {
		System.out.println("*".matches("[+-//|]") ? "succes" : "fail");
		//new Main().run();
	}

}
