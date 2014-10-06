package assignment2;

/**
 * @author Cees Schouten
 * @elements Element of type data
 * @domain Any element of type data
 * @structure Linear
 * 
 * @param <E> Type to be hold in this list
 * @constructor SortedList<E>();
 * @precondition -
 * @postcondition A default empty List is created
 */
public interface SortedListInterface<E extends Data> {

	/**
	 * Add an element at the right position to the list such
	 * the resulting list is monotonically increasing
	 * 
	 * @param e
	 * @return This list
	 * @precondition Element e is not null
	 * @postcondition Element e is added to this List at the correct position
	 */
	List<E> insert(E e);
	
	/**
	 * Search for an element in the list
	 * 
	 * @param e
	 * @return a boolean wetter the element is found
	 * @precondition Element e is not null
	 * @postcondition if the element is found:
	 * 			<br>true is returned, current element points to found element
	 * 			<br>if the element is not present in the list
	 * 			<br>false is returned, current element points to the end of the list
	 */
	boolean find(E e);

}