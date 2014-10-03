package assignment2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
	private DataTable<Identifier, DataSet<NaturalNumber>> table;
	private int lineCount;

	void run(String path) {
		table = new DataTable<>();
		File f = new File(path);
		Scanner in;
		lineCount = 1;
		try {
			in = new Scanner(f);
			while (in.hasNextLine()) {
				String row = in.nextLine();
				Scanner rowScanner = new Scanner(row);
				rowScanner.useDelimiter("");
				try {
					readStatement(rowScanner);
				} catch (APException e) {
					System.err.println(e.getMessage());
				}
				lineCount++;
			}
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
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

	void trimWhiteSpace(Scanner in) {
		while (in.hasNext("\\s")) {
			in.next();
		}
	}

char character(Scanner in, char c) throws APException {
		if (!nextCharIs(in, c)) {
			if (in.hasNext()) {
				throw new APException("Unexpected character: '" + in.next()
						+ "' ,expected '" + c + "' on line " + lineCount);
			} else {
				throw new APException("Unexpected end of line, expected '"
						+ c + "' on line " + lineCount);
			}
		}

		return in.next().charAt(0);
	}

	void readStatement(Scanner in) throws APException {
		trimWhiteSpace(in);
		if (nextCharIsLetter(in)) {
			readAssignment(in);
		} else if (nextCharIs(in, '?')) {
			readPrintStatement(in);
		} else if (nextCharIs(in, '/')) {
			procesComment(in);
		} else if (!in.hasNext()) {
			throw new APException("Empty line detected on line " + lineCount);
		}else{
			throw new APException("Invallid start of a statement: '" + in.next() + "' on line " + lineCount);
		}
	}

	void readAssignment(Scanner in) throws APException {
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

	void readPrintStatement(Scanner in) throws APException {
		character(in, '?');
		trimWhiteSpace(in);
		DataSet<NaturalNumber> data = readExpression(in);
		data = data.clone();
		System.out.print("{");
		while (!data.isEmpty()) {
			NaturalNumber number = data.getElement();
			char[] digits = number.getAllDigits();
			for (char c : digits) {
				System.out.print(c);
			}
			data.removeElement(number);
			if (!data.isEmpty())
				System.out.print(", ");
		}
		System.out.println("}");
	}

	void procesComment(Scanner in) {
		in.nextLine();
	}

	Identifier readIdentifier(Scanner in) throws APException {
		Identifier identifier = new Identifier(in.next().charAt(0));
		while (nextCharIsLetter(in) || nextCharIsDigit(in)) {
			identifier.addCharacter(in.next().charAt(0));
		}

		return identifier;
	}

	DataSet<NaturalNumber> readExpression(Scanner in) throws APException {
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

	DataSet<NaturalNumber> readTerm(Scanner in) throws APException {
		DataSet<NaturalNumber> set = readFactor(in);
		trimWhiteSpace(in);
		while (nextCharIsMultiplicativeOperator(in)) {
			in.next();
			set = (DataSet<NaturalNumber>) set.intersection(readFactor(in));
			trimWhiteSpace(in);
		}
		return set;
	}

	DataSet<NaturalNumber> readFactor(Scanner in) throws APException {
		trimWhiteSpace(in);
		DataSet<NaturalNumber> set = null;
		if (nextCharIsLetter(in)) {
			Identifier id = readIdentifier(in);
			set = table.lookUp(id);
		} else if (nextCharIs(in, '(')) {
			set = readComplexFactor(in);
		} else if (nextCharIs(in, '{')) {
			set = readSet(in);
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

	DataSet<NaturalNumber> readSet(Scanner in) throws APException {
		character(in, '{');
		DataSet<NaturalNumber> set = new DataSet<>();
		while (!nextCharIs(in, '}')) {
			set.addElement(readNumber(in));
		}
		character(in, '}');
		return set;
	}

	NaturalNumber readNumber(Scanner in) throws APException {
		trimWhiteSpace(in);

		NaturalNumber n = new NaturalNumber();
		// As long as there is a digit left
		while (!nextCharIs(in, '}') && !nextCharIs(in, ',')) {
			n.addDigit(readDigit(in));
		}
		trimWhiteSpace(in);
		if (!nextCharIs(in, '}')) {
			character(in, ',');
		}
		return n;
	}

	char readDigit(Scanner in) throws APException {
		if (!nextCharIsDigit(in)) {
			throw new APException("not a digit: '" + in.next().charAt(0)
					+ "', on line " + lineCount);
		}
		return in.next().charAt(0);
	}

	public static void main(String[] args) {
		String path = args[0];
		new Main().run(path);
	}

}
