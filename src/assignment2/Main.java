package assignment2;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
	DataTable<Identifier, DataSet<NaturalNumber>> table;

	void run() {
		Scanner in = new Scanner(System.in);
		in.useDelimiter("");
		while (in.hasNextLine()) {
			try {
				readStatement(in);
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

	boolean nextCharIsMultiplicativeOperator(Scanner in) {
		return in.hasNext(Pattern.quote("*"));
	}

	void trimSpace(Scanner in) {
		while (nextCharIs(in, ' ')) {
			in.next();
		}
	}

	char character(Scanner in, char c) throws APException {
		if (!nextCharIs(in, c)) {
			throw new APException("");
		}

		return in.next().charAt(0);
	}

	void readStatement(Scanner in) throws APException {
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

	void readAssignment(Scanner in) throws APException {
		Identifier identifier = readIdentifier(in);
		DataSet<NaturalNumber> set = readExpression(in);
		table.store(identifier, set);
	}

	void readPrintStatement(Scanner in) {

	}

	void procesComment(Scanner in) {

	}

	Identifier readIdentifier(Scanner in) {
		return null;
	}

	DataSet<NaturalNumber> readExpression(Scanner in) throws APException {
		readTerm(in);
		while (nextCharIsAdditiveOperator(in)) {
			readTerm(in);
		}
		return null;
	}

	DataSet<?> readTerm(Scanner in) throws APException {
		readFactor(in);
		while (nextCharIsMultiplicativeOperator(in)) {
			readFactor(in);
		}
		return null;
	}

	DataSet<?> readFactor(Scanner in) throws APException {
		if (nextCharIsLetter(in)) {
			readIdentifier(in);
		} else if (nextCharIs(in, '(')) {
			in.next();
			readExpression(in); // readComplexFactor()
			character(in, ')');
		} else if (nextCharIs(in, '{')) {
			readSet(in);
		}
		return null;
	}

	DataSet<?> readSet(Scanner in) throws APException {
		character(in, '{');
		while (!nextCharIs(in, '}')) {
			readNumber(in);
		}
		return null;
	}

	NaturalNumber readNumber(Scanner in) throws APException {
		trimSpace(in);
		NaturalNumber n = new NaturalNumber();
		while (!nextCharIs(in, ' ') && !nextCharIs(in, '}')) {// Zolang er nog
																// een cijfer
																// is.
			n.addDigit(readDigit(in));
		}
		return null;
	}

	char readDigit(Scanner in) throws APException {
		if (!nextCharIsDigit(in)) {
			throw new APException("not a digit");
		}
		return in.next().charAt(0);
	}

	public static void main(String[] args) {

		// new Main().run();
	}

}
