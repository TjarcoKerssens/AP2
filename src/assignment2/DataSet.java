package assignment2;

public class DataSet<E extends Data> implements DataSetInterface<E> {
	
	SortedList<E> dataSet;
	
	public DataSet() {
		init();
	}

	@Override
	public void init() {
		this.dataSet = new SortedList<E>();
	}

	@Override
	public E getElement() {
		return dataSet.retrieve();
	}
	
	@Override
	public void addElement(E Element) {
		dataSet.insert(Element);	
	}

	@Override
	public boolean removeElement(E element) {
		
		if (dataSet.find(element)) {
			dataSet.remove();
			return true;
		}
		return false;
	}

	@Override
	public boolean contains(E element) {
		return dataSet.find(element);
	}

	@Override
	public boolean isEmpty() {
		return dataSet.isEmpty();
	}
	
	public  DataSet<E> clone() {
		DataSet<E> clone = new DataSet<E>();
		SortedList<E> temp = dataSet;
		
		while (!this.dataSet.isEmpty()) {
			E element = this.getElement();
			
			clone.addElement(element);
			this.removeElement(element);
		}
		
		return clone;
	}

	@Override
	public DataSetInterface<E> difference(DataSetInterface<E> set) {
		
		DataSetInterface<E> firstSet = clone();
		DataSetInterface<E> resultSet = new DataSet<E>();
		
		while (!firstSet.isEmpty()) {
			
			E element = firstSet.getElement();
			if (!set.contains(element)) {
				resultSet.addElement(element);
			}
			firstSet.removeElement(element);
		}
		return resultSet;
	}

	@Override
	public DataSetInterface<E> intersection(DataSetInterface<E> set) {
		
		DataSetInterface<E> firstSet = clone();
		DataSetInterface<E> resultSet = new DataSet<E>();
		
		while(!firstSet.isEmpty()) {
			E element = firstSet.getElement();
			if(set.contains(element)) {
				resultSet.addElement(element);
			}
			firstSet.removeElement(element);
		}
		return resultSet;
	}

	@Override
	public DataSetInterface<E> union(DataSetInterface<E> set) {
		
		DataSetInterface<E> secondSet = set.clone();
		DataSetInterface<E> resultSet = clone();
		
		while (!secondSet.isEmpty()) {
			E element = secondSet.getElement();
			if (!resultSet.contains(element)) {
				resultSet.addElement(element);
			}
			secondSet.removeElement(element);
		}
		return resultSet;
	}

	@Override
	public DataSetInterface<E> symmetricDifference(DataSetInterface<E> set) {
		
		DataSetInterface<E> resultSet = this.union(set);
		DataSetInterface<E> intersectionSet = this.intersection(set);
		
		while(!intersectionSet.isEmpty()) {
			E element = intersectionSet.getElement();
			if(resultSet.contains(element)) {
				resultSet.removeElement(element);
			}
			intersectionSet.removeElement(element);
		}
		return resultSet;
	}
}
