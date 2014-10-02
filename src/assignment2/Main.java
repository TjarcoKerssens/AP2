package assignment2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
	DataTable<Identifier, DataSet<NaturalNumber>> table;

	void run(String path) {
		table = new DataTable<>();
		File f = new File(path);
		Scanner in;
		try {
			in = new Scanner(f);
			in.useDelimiter("");
			while (in.hasNextLine()) {
				try {
					readStatement(in);
				} catch (APException e) {
					e.printStackTrace();
				}
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

	void trimSpace(Scanner in) {
		while (in.hasNext("\\s")) {
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
		trimSpace(in);
		if (nextCharIsLetter(in)) {
			readAssignment(in);
		} else if (nextCharIs(in, '?')) {
			readPrintStatement(in);
		} else if (nextCharIs(in, '/')) {
			procesComment(in);
		} else {
			throw new APException(in.next());
		}

	}

	void readAssignment(Scanner in) throws APException {
		Identifier identifier = readIdentifier(in);
		trimSpace(in);
		character(in, '=');
		trimSpace(in);
		DataSet<NaturalNumber> set = readExpression(in);
		table.store(identifier, set);
	}

	void readPrintStatement(Scanner in) throws APException {
		character(in, '?');
		trimSpace(in);
		DataSet<NaturalNumber> data = readExpression(in);
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
		while (nextCharIsLetter(in)) {
			identifier.addCharacter(in.next().charAt(0));
		}

		return identifier;
	}

	DataSet<NaturalNumber> readExpression(Scanner in) throws APException {
		DataSet<NaturalNumber> set = readTerm(in);
		while (nextCharIsAdditiveOperator(in)) {
			readTerm(in);
		}
		return set;
	}

	DataSet<NaturalNumber> readTerm(Scanner in) throws APException {
		DataSet<NaturalNumber> set = readFactor(in);

		while (nextCharIsMultiplicativeOperator(in)) {
			readFactor(in);
		}
		return set;
	}

	DataSet<NaturalNumber> readFactor(Scanner in) throws APException {
		DataSet<NaturalNumber> set = null;
		if (nextCharIsLetter(in)) {
			Identifier id = readIdentifier(in);
			set = table.lookUp(id);
		} else if (nextCharIs(in, '(')) {
			in.next();
			set = readExpression(in); // readComplexFactor()
			character(in, ')');
		} else if (nextCharIs(in, '{')) {
			set = readSet(in);
		}
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
		trimSpace(in);
		NaturalNumber n = new NaturalNumber();
		// As long as there is a digit left
		while (nextCharIsDigit(in)) {
			n.addDigit(readDigit(in));
		}
		trimSpace(in);
		if (!nextCharIs(in, '}')) {
			character(in, ',');
		}
		return n;
	}

	char readDigit(Scanner in) throws APException {
		if (!nextCharIsDigit(in)) {
			throw new APException("not a digit: " + in.next().charAt(0));
		}
		return in.next().charAt(0);
	}

	public static void main(String[] args) {
		String path = args[0];
		new Main().run(path);

	}

}
