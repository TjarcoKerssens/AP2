package assignment2;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
	private DataTable<Identifier, DataSet<NaturalNumber>> table;
	private int lineCount;

	void run() {
		table = new DataTable<>();
		lineCount = 1;
		Scanner in = new Scanner(System.in);
		while (in.hasNextLine()) {
			String row = in.nextLine();
			Scanner rowScanner = new Scanner(row);
			rowScanner.useDelimiter("");
			try {
				readStatement(rowScanner);
			} catch (APException e) {
				System.out.println(e.getMessage());
			}
			lineCount++;
		}
		in.close();

	}

	private boolean nextCharIs(Scanner in, char c) {
		return in.hasNext(Pattern.quote(c + ""));
	}

	private boolean nextCharIsLetter(Scanner in) {
		return in.hasNext(Pattern.compile("[A-Za-z]"));
	}

	private boolean nextCharIsDigit(Scanner in) {
		return in.hasNext(Pattern.compile("[0-9]"));

	}

	private boolean nextCharIsAdditiveOperator(Scanner in) {
		return in.hasNext(Pattern.compile("[+-//|]"));
	}

	private boolean nextCharIsMultiplicativeOperator(Scanner in) {
		return in.hasNext(Pattern.quote("*"));
	}

	private boolean nextCharIsReservedKeyWord(Scanner in) {
		return in.hasNext("[\\s+-//*=//|)(]");
	}

	private void trimWhiteSpace(Scanner in) {
		while (in.hasNext("\\s")) {
			in.next();
		}
	}

	private char character(Scanner in, char c) throws APException {
		if (!nextCharIs(in, c)) {
			if (in.hasNext()) {
				throw new APException("Unexpected character: '" + in.next()
						+ "' ,expected '" + c + "' on line " + lineCount);
			} else {
				throw new APException("Unexpected end of line, expected '" + c
						+ "' on line " + lineCount);
			}
		}

		return in.next().charAt(0);
	}

	private void readStatement(Scanner in) throws APException {
		trimWhiteSpace(in);
		if (nextCharIsLetter(in)) {
			readAssignment(in);
		} else if (nextCharIs(in, '?')) {
			readPrintStatement(in);
		} else if (nextCharIs(in, '/')) {
			procesComment(in);
		} else if (!in.hasNext()) {
			throw new APException("Empty line detected on line " + lineCount);
		} else {
			throw new APException("Invallid start of a statement: '"
					+ in.next() + "' on line " + lineCount);
		}
	}

	private void readAssignment(Scanner in) throws APException {
		Identifier identifier = readIdentifier(in);
		trimWhiteSpace(in);
		character(in, '=');
		trimWhiteSpace(in);
		DataSet<NaturalNumber> set = readExpression(in);
		if (in.hasNext()) {
			in.useDelimiter("\\z");
			throw new APException("Unexpected input: '" + in.next() + "'"
					+ " on line " + lineCount);
		}
		table.store(identifier, set);
	}

	private void readPrintStatement(Scanner in) throws APException {
		character(in, '?');
		trimWhiteSpace(in);
		DataSet<NaturalNumber> data = readExpression(in);
		data = data.clone();
		while (!data.isEmpty()) {
			NaturalNumber number = data.getElement();
			char[] digits = number.getAllDigits();
			for (char c : digits) {
				System.out.print(c);
			}
			data.removeElement(number);
			System.out.print(" ");
		}
		System.out.println("");
	}

	private void procesComment(Scanner in) {
		in.nextLine();
	}

	private Identifier readIdentifier(Scanner in) throws APException {
		Identifier identifier = new Identifier(in.next().charAt(0));
		while (!nextCharIsReservedKeyWord(in) && in.hasNext()) {
			identifier.addCharacter(in.next().charAt(0));
		}

		return identifier;
	}

	private DataSet<NaturalNumber> readExpression(Scanner in)
			throws APException {
		DataSet<NaturalNumber> set = readTerm(in);
		trimWhiteSpace(in);
		while (nextCharIsAdditiveOperator(in)) {
			char operator = in.next().charAt(0);
			switch (operator) {
			case '+':
				trimWhiteSpace(in);
				set = (DataSet<NaturalNumber>) set.union(readTerm(in));
				break;
			case '|':
				trimWhiteSpace(in);
				set = (DataSet<NaturalNumber>) set
						.symmetricDifference(readTerm(in));
				break;
			case '-':
				trimWhiteSpace(in);
				set = (DataSet<NaturalNumber>) set.difference(readTerm(in));
				break;
			}
			trimWhiteSpace(in);
		}
		return set;
	}

	private DataSet<NaturalNumber> readTerm(Scanner in) throws APException {
		DataSet<NaturalNumber> set = readFactor(in);
		trimWhiteSpace(in);
		while (nextCharIsMultiplicativeOperator(in)) {
			in.next();
			set = (DataSet<NaturalNumber>) set.intersection(readFactor(in));
			trimWhiteSpace(in);
		}
		return set;
	}

	private DataSet<NaturalNumber> readFactor(Scanner in) throws APException {
		trimWhiteSpace(in);
		DataSet<NaturalNumber> set = null;
		if (nextCharIsLetter(in)) {
			Identifier id = readIdentifier(in);
			set = table.lookUp(id);
		} else if (nextCharIs(in, '(')) {
			set = readComplexFactor(in);
		} else if (nextCharIs(in, '{')) {
			set = readSet(in);
		} else if (!in.hasNext()) {
			throw new APException("No expression specified, at line "
					+ lineCount);
		} else {
			throw new APException("Invalid character for start of factor: '"
					+ in.next() + "'" + " on line " + lineCount);
		}
		return set;
	}

	private DataSet<NaturalNumber> readComplexFactor(Scanner in)
			throws APException {
		DataSet<NaturalNumber> set;
		in.next();
		set = readExpression(in);
		character(in, ')');
		return set;
	}

	private DataSet<NaturalNumber> readSet(Scanner in) throws APException {
		character(in, '{');
		trimWhiteSpace(in);

		DataSet<NaturalNumber> set = new DataSet<>();
		while (!nextCharIs(in, '}')) {
			set.addElement(readNumber(in));
		}
		character(in, '}');
		return set;
	}

	private NaturalNumber readNumber(Scanner in) throws APException {
		trimWhiteSpace(in);

		NaturalNumber n = new NaturalNumber();
		boolean readAnyDigits = false;
		// As long as there is a digit left
		while (!nextCharIs(in, '}') && !nextCharIs(in, ',')) {
			n.addDigit(readDigit(in));
			if (in.hasNext("\\s")) {
				trimWhiteSpace(in);
				if (nextCharIsDigit(in)) {
					throw new APException(
							"No spaces in elements allowed, at line "
									+ lineCount);
				}
			}
			readAnyDigits = true;
		}
		testForEmptyElement(in, readAnyDigits);
		return n;
	}

	private void testForEmptyElement(Scanner in, boolean readAnyDigits)
			throws APException {
		if (!readAnyDigits) {
			throw new APException("Empty elements are not allowed, at line "
					+ lineCount);
		}
		trimWhiteSpace(in);
		if (!nextCharIs(in, '}')) {
			character(in, ',');
			if (nextCharIs(in, '}')) {
				throw new APException(
						"Empty elements are not allowed, at line " + lineCount);
			}
		}
	}

	private char readDigit(Scanner in) throws APException {
		if (!nextCharIsDigit(in)) {
			throw new APException("not a digit: '" + in.next().charAt(0)
					+ "', on line " + lineCount);
		}
		return in.next().charAt(0);
	}

	public static void main(String[] args) {
		new Main().run();
	}

}
