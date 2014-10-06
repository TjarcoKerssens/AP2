package assignment2;

public class NaturalNumber implements NaturalNumberInterface {

	private char[] naturalNumber;
	private int nextIndex;

	public NaturalNumber() {
		try{
			init('0');
		}catch(APException ex){
			//Will never happen
		}
	}

	public NaturalNumber(char digit) throws APException {
		init(digit);
	}

	@Override
	public String toString() {
		String s = "";
		for (int i = 0; i < nextIndex; i++) {
			s += naturalNumber[i];
		}
		return s;
	}

	@Override
	public boolean init(char digit) throws APException {
		
		if (!isNumeric(digit)) {
			return false;
		}
		naturalNumber = new char[1];
		addDigit(digit);
		return true;
	}

	@Override
	public void addDigit(char digit) throws APException {

		if (isNumeric(digit)) {
			arrayLenghtCheck();
			zeroReplace(digit);
		} else {
			throw new APException("Number '" + digit + "'is not numeric.");
		}
	}

	@Override
	public char[] getAllDigits() {
		char[] temp = new char[nextIndex];
		for (int i = 0; i < temp.length; i++) {
			temp[i] = naturalNumber[i];
		}
		return temp;
	}

	@Override
	public char getDigitAt(int position) throws APException {

		if (position < nextIndex) {
			return naturalNumber[position];
		} else {
			throw new APException("Index '" + position + "' is non existend");
		}
	}

	@Override
	public int compareTo(Object number) {

		NaturalNumber compare = ((NaturalNumber) number);

		if (compare.nextIndex != this.nextIndex) {
			return compare.nextIndex - this.nextIndex;
		}
		for (int i = 0; i < this.nextIndex; i++) {
			try {
				if (!(compare.getDigitAt(i) == this.getDigitAt(i))) {
					return compare.getDigitAt(i) - this.getDigitAt(i);
				}
			} catch (APException e) {
			}
		}
		return 0;
	}

	public NaturalNumber clone() {

		NaturalNumber clone = new NaturalNumber();

		for (int i = 0; i < this.nextIndex; i++) {
			try {
				clone.addDigit(this.getDigitAt(i));
			} catch (APException e) {
			}
		}
		return clone;
	}

	public boolean isNumeric(char digit) {
		return Character.isDigit(digit);
	}

	private void zeroReplace(char digit) {
		
		if(naturalNumber[0] == '0' && nextIndex == 1) {
			naturalNumber[0] = digit;
		} else {
			naturalNumber[nextIndex] = digit;
			nextIndex++;
		}
	}
	
	private void arrayLenghtCheck() {
		if (nextIndex == naturalNumber.length) {
			char[] temp = naturalNumber;
			naturalNumber = new char[temp.length * 2];
			for (int i = 0; i < temp.length; i++) {
				naturalNumber[i] = temp[i];
			}
		}
	}
}