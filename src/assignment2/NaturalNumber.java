package assignment2;

public class NaturalNumber implements NaturalNumberInterface {
	
	private char[] naturalNumber;
	private int nextIndex;
	
	public NaturalNumber() {
		naturalNumber = new char[1];
		naturalNumber[0] = '0';
	}
	
	public NaturalNumber(char digit) throws APException {
		init(digit);
	}

	@Override
	public boolean init(char digit) throws APException {
		
		naturalNumber = new char[1];
		addDigit(digit);
	
		return true;
	}

	@Override
	public void addDigit(char digit) throws APException {
		
		if(isNumeric(digit)) {
			if (nextIndex == naturalNumber.length) {
				char [] temp = naturalNumber;
				naturalNumber = new char[temp.length * 2];
				for (int i = 0; i < temp.length; i++) {
					naturalNumber[i] = temp [i];
				}
			}
			naturalNumber[nextIndex] = digit;
			nextIndex++;
		} else {
			throw new APException ("Number '" + digit + "'is not numeric.");
		}
	}

	@Override
	public char[] getAllDigits() {
		return naturalNumber;
	}

	@Override
	public char getDigitAt(int position) throws APException {
		
		if(position < nextIndex) {
			return naturalNumber[position];
		} else {
			throw new APException("Index '" + position + "' is non existend");
		}
	}
	
	@Override
	public int compareTo(Object o) {
		return naturalNumber.toString().compareTo((String) o);	
	}
	
	public NaturalNumber clone() {
		
		NaturalNumber clone = new NaturalNumber();
		
		for(int i = 0; i < this.getAllDigits().length - 1; i++) {
			try {
				clone.addDigit(this.getDigitAt(i));
			} catch (APException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return clone;
	}
	
	public boolean isNumeric(char digit) {
		return Character.isDigit(digit);
	}
	

}
