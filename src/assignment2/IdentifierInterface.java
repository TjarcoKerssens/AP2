package assignment2;

/**
 * An interface for the Identifier class which will model the identifiers
 * present in the set
 * 
 * @author Tjarco Kerssens
 * @elements Characters
 * @structure Linear
 * @domain All alphanumeric characters, non empty identifiers starting with a
 *         letter
 * @constructor Identifier(char character); <dt><b>PRE-condition</b>
 *              <dd>-
 *              <dt><b>POST-condition</b>
 *              <dd>The Identifier is created and initialized with the first
 *              character
 *              <dt><b>throws</b>
 *              <dd>APException if the character is not alphabetic
 */

public interface IdentifierInterface extends Data {

	/**
	 * Initialization of data input
	 * 
	 * @throws APException
	 *             if the character is not a letter
	 * @precondition -
	 * @postcondition The identifier is reset to it's default state
	 * 
	 */
	void init(char character) throws APException;

	/**
	 * Add a character to the identifier
	 * 
	 * @param character
	 * @throws IllegalFormatException
	 *             if the character is not alphanumeric
	 * @postcondition The identifier is put into the Identifier
	 */
	void addCharacter(char character) throws APException;

	/**
	 * Remove a character from the from this Identifier at the specified index
	 * 
	 * @param index
	 * 
	 * @precondition character array is not null, index is not null
	 * @postcondition true: The character at the provided index is removed<br>
	 *                false: there exists no character at the provided array
	 *                position
	 */
	boolean removeCharacter(int index);

	/**
	 * Return the character at the provided index position
	 * 
	 * @param index
	 * @return Return the character on that index position
	 * 
	 * @precondition Index is a valid array position, array is not null
	 * @postcondition Character at provided index position is determined and
	 *                returned
	 */
	char getCharacter(int index);

	/**
	 * Give the amount of characters in the identifier
	 * 
	 * @return the amount of characters
	 * 
	 * @precondition the identifier is not null
	 * @postcondition The amount of characters in the identifier is computed and
	 *                returned as an integer
	 */
	int getLenght();

	/**
	 * Return all the characters in the Identifier as an array of chars
	 * 
	 * @return an array of chars
	 * 
	 * @precondition Array is not null
	 * @postcondition All characters of the identifier are returned
	 */
	char[] getAllCharacters();
}