package assignment2;

class Node<E extends Data> implements Clonable {

	E data;
	Node<E> prior,
		next;

	public Node (E d) {
		this(d, null, null);
	}


	public Node (E data, Node<E> prior, Node<E> next) {
		this.data = data == null ? null : (E) data.clone();
		this.prior = prior;
		this.next = next;
	}

	
	public Node<E> clone () {
		Node<E> clone;
		try{
			clone = (Node<E>)super.clone();
		}catch(CloneNotSupportedException CNSE){
			throw new Error("Impossible! instance cannot be cloned");
		}

		clone.data = data == null ? null : (E) data.clone();

		return clone;
	}
}
