public class Test {

	static void test() {
		IntLinearList list = new IntLinearList();
		for (int i = 0; i < 10; ++i) {
			list.add(i);
		}
		
		IntIterator iter = list.iterator();
		//no guarantee of order
		
		while (iter.hasNext()) {
			System.out.print( iter.next() + " ");
		}
		System.out.println();
		
		iter = list.linearIterator();
		//should guarantee order
		while (iter.hasNext()) {
			System.out.print(iter.next() + " ");
		}
		System.out.println();
		
		IntLinearIterator lIter = (IntLinearIterator)iter;
		while (lIter.hasPrevious()) {
			System.out.print(lIter.currentIndex() + ":" + lIter.previous() + " ");
		}
		
	}
	
	public static void main(String[] args) {
		Test.test();
	}
	
}