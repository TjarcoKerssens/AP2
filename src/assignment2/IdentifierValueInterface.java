package assignment2;

/**
 * @author Cees Schouten
 * @elements Identifier and DataSet objects
 * @domain Any Identifier and DataSet
 * @structure none
 * @constructor IdentifierValue(IdentifierInterface identifier,
 *              DataSetInterface<?> data);
 * @precondition: -
 * @postcondition: Default empty identifier and dataset are created
 */

public interface IdentifierValueInterface extends Data {

	/**
	 * Initialize identifier and dataset
	 * 
	 * @precondition -
	 * @postcondition Initial identifier and dataset are created
	 */

	void init(IdentifierInterface identifier, DataSetInterface<?> data);

	/**
	 * Return an identifier from the dataset
	 * 
	 * @return An identifier
	 * @precondition -
	 * @postcondition A copy of an identifier is returned
	 */
	IdentifierInterface getIdentifier();

	/**
	 * Assign an identifier to the dataset
	 * 
	 * @param identifier
	 * @precondition -
	 * @postcondition Identifier is assigned to the dataset
	 */
	void setIdentifier(IdentifierInterface identifier);

	/**
	 * @return A copy of this dataset is returned
	 * @precondition -
	 * @postcondition A copy of this dataset is returned
	 */
	DataSetInterface<?> getDataset();

	/**
	 * @param dataSet
	 * @precondition -
	 * @postcondition The dataset is set to this dataset
	 */
	void setDataset(DataSetInterface<?> dataSet);

}
