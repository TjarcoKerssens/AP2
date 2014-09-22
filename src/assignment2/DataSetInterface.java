package assignment2;

/**
 * A set which can hold elements of type Data
 * 
 * @author Tjarco Kerssens
 * @elements A collection of elements of type Data
 * @domain Any Object which implements the Data interface
 * @structure none
 * 
 * @param <E> The type to be hold in this set
 * 
 * @constructor DataSet<E>(); <dd><b>precondition</b> - <dd>
 *              <b>postcondition</b> A default empty set is created for type E
 */
public interface DataSetInterface<E extends Data> extends Clonable {

	/**
	 * Initialize this DataSet as an empty set. May also be used to reset this
	 * set
	 * 
	 * @precondition -
	 * @postcondition The set is created
	 */
	void init();

	/**
	 * @return An arbitrary element of the set
	 * @precondition The set is not empty
	 * @postcondition An element is returned
	 */
	E getElement();

	/**
	 * Remove an element from the set
	 * 
	 * @param element
	 *            to be removed
	 * @precondition -
	 * @postcondition returned:<br>
	 *                <i>true:</i>If the element could be found and is removed<br>
	 *                <i>false:</i>If this set does not contain the element
	 */
	boolean removeElement(E element);

	/**
	 * 
	 * @param element
	 * @precondition -
	 * @postcondition returned: <br>
	 *                <i>true: </i>If the set contains the element<br>
	 *                <i>false: </i>If the set does not contain the element
	 */
	boolean contains(E element);

	/**
	 * @precondition -
	 * @postcondition returned: <br>
	 *                <i>true: </i> if the set is empty <br>
	 *                <i>false: </i> if the set contains one or more elements
	 */
	boolean isEmpty();

	/**
	 * @precondition -
	 * @postcondition An exact clone of this set is returned, containing all the
	 *                elements which are also present in this set
	 */
	@Override
	public DataSetInterface<E> clone();
}
