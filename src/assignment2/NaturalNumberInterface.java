package assignment2;

/**
 * 
 * @author Tjarco Kerssens
 * @elements characters
 * @structure Linear
 * @domain All numeric characters
 * 
 * @constructor NaturalNumber(); <dt><b>precondition</b>
 *              <dd>-
 *              <dt><b>postcondition</b>
 *              <dd>A new NaturalNumber is created representing the number 0
 * 
 * @constructor NaturalNumber(char ... digit) throws APException; <dt>
 *              <b>params</b><dd> one or more characters representing the digits
 *              <dt><b>precondition</b><dd> - <dt><b>postcondition</b><dd>A new
 *              NaturalNumber is created representing the number consisting of
 *              the digits as parameter <dt><b>throws</b><dd>APException if one
 *              of the characters is not a digit.
 */
public interface NaturalNumberInterface extends Data {

	/**
	 * Initialize this NaturalNumber. May be used to reset this Object
	 * 
	 * @param digit
	 *            zero or more digits to represent this number
	 * @precondition all characters are digits
	 * @postcondition The NaturalNumber is initialized with the digits
	 *                given.(with 0 if none are given)<br>
	 *                return true if successful<br>
	 *                return false if one or more of the characters are not
	 *                digits
	 * 
	 */
	boolean init(char... digit);

	/**
	 * Adds a new digit to the NaturalNumber
	 * 
	 * @param digit
	 *            the digit to be added
	 * @throws APException
	 *             if the character is not a digit
	 * @precondition -
	 * @postcondition the digit is added to the NaturalNumber
	 */
	void addDigit(char digit) throws APException;

	/**
	 * @return All digits of the NaturalNumber
	 * @precondition -
	 * @postcondition A copy of the characters is returned
	 */
	char[] getAllDigits();

	/**
	 * @param o
	 * @return the difference between this NaturalNumber and the one as
	 *         parameter
	 * @precondition o is a NaturalNumber
	 * @postcondition The result of the comparison is returned
	 */
	@Override
	public int compareTo(Object o);

	/**
	 * 
	 * @return a copy of this NaturalNumber
	 * @precondition -
	 * @postcondition An exact copy of all digits is made an put into a new
	 *                NaturalNumber object, which is returned
	 */
	@Override
	public NaturalNumberInterface clone();

}
