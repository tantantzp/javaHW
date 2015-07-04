public class IntLinearList extends AbstractIntLinearList {
	
	private final int CAPACITY = 1024;
	private intLinearIteratorImple linearIter;
	private IntIteratorImple intIter;
	private int[] data;
	private int size;
	
	public IntLinearList() {
		data = new int[CAPACITY];
		linearIter = new intLinearIteratorImple();
		intIter = new IntIteratorImple();
		size = 0;
	}
	private class IntIteratorImple implements IntIterator {
		int tempIndex = 0;

		public boolean hasNext() {
			return (tempIndex < size && tempIndex >= 0);
		}

		public int next() {
			int temp = data[tempIndex];
			tempIndex++;
			return temp;
		}
		
	};
	private class intLinearIteratorImple extends IntIteratorImple implements IntLinearIterator{
	
		public boolean hasPrevious() {
			return (tempIndex <= size && tempIndex > 0);
		}

		public int previous() {		
			int temp = data[--tempIndex];
			return temp;	
		}
		public int currentIndex() {
			return tempIndex;
		}
		
	};
	
	public IntIterator iterator() {
		intIter.tempIndex = 0;
		return intIter;
	}
	public IntLinearIterator linearIterator() {
		linearIter.tempIndex = 0;
		return linearIter;
	}

	public int get(int index) {
		if (index >= 0 && index < size)
			return data[index];
		else
			return Integer.MAX_VALUE;
	}

	public int size() {
		return size;
	}

	public void add(int n) {
		if (size < CAPACITY) {
			data[size] = n;
			size++;
		}
		else
			System.out.println("Overflow!");
	}

	void addAt(int index, int n) {
		if(index == size){
			add(n);
			return;
		}
		if(index > size || index < 0){
			System.out.println("out of range!");
			return;
		}
		int temp = size;
		for( ;temp > index; temp --){
			data[temp] = data[temp - 1];
		}
		data[index] = n;
	}
	
}
