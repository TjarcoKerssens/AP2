package assignment2;

public class SortedList<E extends Data> extends List<E> implements
		SortedListInterface<E> {

	@Override
	public List<E> insert(E d) {

		if (this.isEmpty() == true) {
			this.insert(d);
		} else {
			this.setFirst();

			while (d.compareTo(this.list.data) < 0) {
				this.list = this.list.next;
			}

			if (this.list.prior == null) {
				insertFirst(d);
			} else if (this.list.next == null) {
				insertLast(d);
			} else {
				insertInOrder(d);
			}
		}
		return this;
	}

	public void insertFirst(E d) {
		this.list = new Node<E>(d, null, this.list);
		this.list.next.prior = this.list;
	}

	public void insertLast(E d) {
		this.list = new Node<E>(d, this.list.prior, null);
		this.list.prior.next = this.list;
	}

	public void insertInOrder(E d) {
		this.list = new Node<E>(d, this.list.prior, this.list.next);
		this.list.prior.next = this.list;
		this.list.next.prior = this.list;
	}
}
