package assignment2;

/**	@elements : objects of type E
 *	@structure : linear
 *	@domain :	All rows of elements of type E are valid values for a list.
 *       		For every non-empty list the reference "current" is pointing to an 
 *			element in the list.
 *	@constructor - List ();
 *	<dl>
 *		<dt><b>PRE-conditie</b><dd>		-
 *		<dt><b>POST-conditie</b><dd> 	The new List-object is the empty list.
 * </dl>
 **/

public interface ListInterface<E extends Data> extends Clonable {

	/**
	 * Boolean to check whether the list is empty or not	
	 * 
	 * @precondition -
	 *  @postcondition - FALSE: list is not empty.
	 *  				TRUE:  list is empty.
	 **/
	boolean isEmpty ();

	/** 
	 * Initialize this List object as an empty list
	 * 
	 * @precondition  -
	 *	@postcondition - list-POST is empty and has been returned.
	 **/
	List<E> init ();

	/**	
	 * Return the list size (number of elements in the list) as an integer
	 * 
	 * @precondition  -
	 *	@postcondition - The number of elements has been returned.
	 **/
	int size ();

	/** 
	 * Add an element to the list
	 * 
	 * @param data to be added
	 * @precondition  -
	 *	@postcondition - A copy of d has been added to List-PRE.
	 *    				current points to the newly added element.
	 *   				list-POST has been returned.
	 **/
	List<E> insert (E d);


	/** 
	 * Retrieve data contained by the element list/current is pointing to
	 * 
	 * @precondition  - The list is not empty.
	 *	@postcondition - A copy of the value of the current element has been returned.
	 */
	E retrieve ();


	/** 
	 * Remove an element for the list and set the correct pointers for the adjacent
	 * elements in the list
	 * 
	 * @precondition  - The list is not empty.
	 * 	@postcondition - The current element of list-PRE is not present in list-POST.
	 * 	    			current-POST points to
	 *    					- if list-POST is empty
	 *   						null
	 *   					- if list-POST is not empty
	 *     						if current-PRE was the last element of list-PRE
	 *     							the last element of list-POST
	 *     						else 
	 *     							the element after current-PRE 
	 *  				list-POST has been returned.
	 **/
	List<E> remove ();


	/** 
	 * Search the list for an element containing the parameters data, set list to
	 * corresponding element if found
	 * 
	 * @param data contained by the element
	 * 
	 * @precondition  - 
	 *	@postcondition - TRUE:  list contains a copy of d.
	 *	     			current-POST points to the first element in list that
	 *	     			contains a copy of d.
	 *     				FALSE: list does not contain a copy of d.
	 *	 	    			current-POST points to
	 *	      				- if list-POST is empty
	 *                    				null
	 *	      				- else	
	 *	    					the last element in list
	 **/
	boolean find (E d);


	/** 
	 * Set list to the first element in the list
	 * 
	 * @precondition  - 
	 *	@postcondition - FALSE: list is empty
	 *    				TRUE:  current points to the first element
	 **/
	boolean setFirst ();


	/**	
	 * Set list to the last element in the list
	 * 
	 * @precondition  - 
	 *	@postcondition - FALSE: list is empty
	 *     				TRUE:  current points to the last element
	 */
	boolean setLast ();


	/** 
	 * Set list to the next element in the list
	 * 
	 * @precondition  - 
	 *	@postcondition - FALSE: list is empty or current points to the last element
	 *     				TRUE:  current-POST points to the next element of current-PRE
	 */
	boolean getNext ();


	/** 
	 * Set list to the previous element in the list
	 * 
	 * @precondition  - 
	 *	@postcondition - FALSE: list is empty or current points to the first element
	 *     				TRUE:  current-POST points to the prior element of current-PRE
	 */
	boolean getPrior ();

	/** 
	 * Clone/deep-copy this list
	 * 
	 * @precondition  -
	 *	@postcondition - A deep-copy of list has been returned.
	 **/
	public List<E> clone ();


}
