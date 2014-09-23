package assignment2;

/**
 * 
 * A table which links the Identifiers to the Datasets
 * 
 * @author Tjarco Kerssens
 * @elements Identfiers and DataSets
 * @structure linear
 * @domain Identifiers and DataSet objects
 * 
 * @constructor DataTable(); 
 * 			<dd><b>precondition</b><dt>-
 * 			<dd><b>postcondition</b><dt>A new empty DataTable is created. 
 */
public interface DataTableInterface {

	/**
	 * Stores the Identifier and the DataSet in the table. The data can later be
	 * retrieved by using the identifier.
	 * 
	 * @param identifier
	 * @param data
	 * @precondition The identifier and the data are not null
	 * @postcondition The identifier and data are added to the table and linked
	 *                together.
	 * 
	 */
	void store(IdentifierInterface identifier, DataSetInterface<?> data);

	/**
	 * Lookup a DataSet by providing the identifier it is linked to. 
	 * 
	 * @param identifier
	 * @precondition The identifier is not null
	 * @postcondition return the DataSet which is linked to the given identifier. If
	 * 				no DataSet could be found, returns null. 
	 */
	DataSetInterface<?> lookUp(IdentifierInterface identifier);
}
