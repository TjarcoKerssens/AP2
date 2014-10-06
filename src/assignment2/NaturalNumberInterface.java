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
 * @constructor NaturalNumber(char digit) throws APException; <dt>
 *              <b>params</b><dd> A character representing the digit
 *              <dt><b>precondition</b><dd> - 
 *              <dt><b>postcondition</b><dd>A new  NaturalNumber is created representing the number
 *              consisting of the digit as parameter 
 *              <dt><b>throws</b><dd>APException if the character is not a digit
 */
public interface NaturalNumberInterface extends Data {

	/**
	 * Initialize this NaturalNumber. May be used to reset this Object
	 * 
	 * @param digit
	 *            The character wich represents the digit
	 * @throws APException 
	 * @precondition all characters are digits (?)
	 * @postcondition The NaturalNumber is initialized with the digit
	 *                given.<br>
	 *                return true if successful<br>
	 *                return false if the character is not a digit
	 * 
	 */
	boolean init(char digit) throws APException;

	/**
	 * Adds a new digit to the NaturalNumber, doubles length of the 
	 * natural number array if upper limit has been reached, checks
	 * if the first digit is zero, if so replaces it with the parameter
	 * digit
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
	 * Return this NaturalNumber as an character array
	 * 
	 * @return All digits of the NaturalNumber
	 * @precondition -
	 * @postcondition A copy of the characters is returned
	 */
	char[] getAllDigits();
	
	/**
	 * Return the digit at the specified index as an character
	 * 
	 * @param position The index of the digit to be returned
	 * @precondition -
	 * @postcondition return the digit at the given position. 
	 * @throws APException if the position is not a valid index
	 */
	char getDigitAt(int position) throws APException;
}
