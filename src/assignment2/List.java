package assignment2;

public class List<E extends Data> implements ListInterface<E> {
	protected Node<E> list;

	@Override
	public boolean isEmpty() {
		return list == null;
	}

	@Override
	public List<E> init() {
		list = null;
		return this;
	}

	@Override
	public List<E> clone() {
		setFirst();
		List<E> clone = new List<E>();
		while (list.next != null) {
			clone.insert(list.next.clone().data);
			list = list.next;
		}
		return clone;
	}

	@Override
	public int size() {
		if(list == null){
			return 0;
		}
		Node<E> temp = list;
		setFirst();
		int size = 1;
		while (list.next != null) {
			size++;
			list = list.next;
		}
		list = temp;
		return size;
	}

	@Override
	public List<E> insert(E d) {
		if (list == null) {
			list = new Node<E>(d);
		} else if (list.next == null) {
			list = list.next = new Node<E>(d, list, null);
		} else {
			list = list.next = list.next.prior = new Node<E>(d, list, list.next);
		}
		return this;
	}

	@Override
	public E retrieve() {
		return list.data;
	}

	@Override
	public List<E> remove() {
		if (list.prior == null) {
			list = list.next;
			list.prior = null;
		} else if (list.next == null) {
			list = list.prior;
			list.next = null;
		} else {
			list.next.prior = list.prior;
			list = list.prior.next = list.next;
		}
		return this;
	}

	@Override
	public boolean find(E d) {
		if (!setFirst())
			return false;

		if (list.data.compareTo(d) == 0) {
			return true;
		}
		while (list.next != null) {
			list = list.next;

			if (list.data.compareTo(d) == 0) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean setFirst() {
		if (list == null) {
			return false;
		}
		while (list.prior != null) {
			list = list.prior;
		}
		return true;
	}

	@Override
	public boolean setLast() {
		if (list == null) {
			return false;
		}

		while (list.next != null) {
			list = list.next;
		}
		return true;
	}

	@Override
	public boolean getNext() {
		if (list == null || list.next == null) {
			return false;
		}

		list = list.next;
		return true;
	}

	@Override
	public boolean getPrior() {
		if (list == null || list.prior == null) {
			return false;
		}
		list = list.prior;
		return true;
	}

}
