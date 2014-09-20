package assignment2;

public class Number implements Data {
	private int number;

	public Number(int number) {
		this.number = number;
	}

	@Override
	public int compareTo(Object number) {
		return this.number - ((Number) number).getNumber();
	}

	@Override
	public Number clone() {
		return new Number(this.getNumber());
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	@Override
	public String toString() {
		return Integer.toString(number);
	}
}
