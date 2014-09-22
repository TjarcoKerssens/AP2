package assignment2;

/**
 * @author Cees Schouten
 * @elements Element of type data
 * @domain Any element of type data
 * @structure Linear
 * @constructor SortedList<E>();
 * @precondition -
 * @postcondition A default empty List is created
 */
public interface SortedListInterface<E extends Data> {

	/**
	 * Add an element at the right position to the list
	 * 
	 * @param e
	 * @return This list
	 * @precondition Element e is not null
	 * @postcondition Element e is added to this List
	 */
	List<E> insert(E e);

}