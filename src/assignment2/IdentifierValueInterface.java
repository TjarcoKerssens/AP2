package assignment2;

/**
 * @author Cees Schouten
 * @elements Objects
 * @domain Two Objects which form a relation
 * @structure none
 * 
 * @constructor IdentifierValue(I identifier,
 *              D data);
 * @precondition: -
 * @postcondition: The Objects are linked via a new instance of IdentifierValue
 */

public interface IdentifierValueInterface<I extends Data, D extends Clonable> extends Data {

	/**
	 * Initialize identifier and dataset
	 * 
	 * @precondition -
	 * @postcondition Initial identifier and dataset are created
	 */

	void init(I identifier, D data);

	/**
	 * Return an identifier from the dataset
	 * 
	 * @return An identifier
	 * @precondition -
	 * @postcondition A copy of an identifier is returned
	 */
	I getIdentifier();

	/**
	 * Assign an identifier to the dataset
	 * 
	 * @param identifier
	 * @precondition -
	 * @postcondition Identifier is assigned to the dataset
	 */
	void setIdentifier(I identifier);

	/**
	 * @return A copy of this dataset is returned
	 * @precondition -
	 * @postcondition A copy of this dataset is returned
	 */
	D getData();

	/**
	 * @param dataSet
	 * @precondition -
	 * @postcondition The dataset is set to this dataset
	 */
	void setData(D dataSet);

}
