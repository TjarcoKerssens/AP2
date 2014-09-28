package assignment2;

public class SortedList<E extends Data> extends List<E> implements
		SortedListInterface<E> {

	@Override
	public List<E> insert(E d) {

		if (this.isEmpty()) {
			super.insert(d);
		} else {
			this.setFirst();

			// New item is the smallest item so far
			if (list.data.compareTo(d) > 0) {
				insertFirst(d);
				return this;
			}

			// Find the item where the new item should be placed after
			while (list.next != null && list.next.data.compareTo(d) < 0) {
				list = list.next;
			}

			if (this.list.next == null) {
				// New item is the largest so far
				insertLast(d);
			} else {
				insertInOrder(d);
			}
		}
		return this;
	}

	public void insertFirst(E d) {
		list = list.prior = new Node<E>(d, null, list);
	}

	public void insertLast(E d) {
		list = list.next = new Node<E>(d, list, null);
	}

	public void insertInOrder(E d) {
		list = list.next = list.next.prior = new Node<E>(d, list,
				list.next);
	}

	public boolean find(E d) {
		if (d.compareTo(list.data) == 0) {
			return true;
		} else if (d.compareTo(this.list.data) < 0) {
			while (list.next != null) {
				this.list = this.list.next;

				if (d.compareTo(this.list.data) == 0) {
					return true;
				}
			}
		} else {
			while (list.prior != null) {
				this.list = this.list.prior;

				if (d.compareTo(this.list.data) == 0) {
					return true;
				}
			}
		}
		return false;
	}
}
