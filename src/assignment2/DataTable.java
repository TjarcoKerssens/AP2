package assignment2;

public class DataTable<I extends Data, D extends Clonable> implements
		DataTableInterface<I, D> {
	private SortedList<IdentifierValue<I, D>> list;

	public DataTable() {
		list = new SortedList<>();
	}

	@Override
	public void store(I identifier, D data) {
		list.insert(new IdentifierValue<I, D>(identifier, data));
	}

	@Override
	public D lookUp(I identifier) throws APException {
		if (!list.find(new IdentifierValue<I, D>(identifier, null))) {
			throw new APException("No entry for given identifier");
		}
		return list.retrieve().getData();
	}

	private class IdentifierValue<I2 extends Data, D2 extends Clonable>
			implements IdentifierValueInterface<I2, D2> {
		private I2 identifier;
		private D2 data;

		public IdentifierValue(I2 identifier, D2 data) {
			init(identifier, data);
		}

		@Override
		public void init(I2 identifier, D2 data) {
			this.identifier = identifier;
			this.data = data;
		}

		@Override
		public I2 getIdentifier() {
			return identifier;
		}

		@Override
		public void setIdentifier(I2 identifier) {
			this.identifier = identifier;
		}

		@Override
		public D2 getData() {
			return data;
		}

		@Override
		public void setData(D2 dataSet) {
			this.data = dataSet;
		}

		@Override
		public int compareTo(Object o) {
			return identifier.compareTo(o);
		}

		@SuppressWarnings("unchecked")
		@Override
		public IdentifierValue<I2, D2> clone() {
			I2 identifierClone = (I2) identifier.clone();
			D2 dataClone = (D2) data.clone();
			return new IdentifierValue<I2, D2>(identifierClone, dataClone);
		}

	}

}
