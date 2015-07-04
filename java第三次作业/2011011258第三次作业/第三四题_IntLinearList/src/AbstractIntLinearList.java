
public abstract class AbstractIntLinearList extends AbstractIntCollection{
	abstract IntLinearIterator linearIterator();
	abstract int get(int index);
	abstract void addAt(int index, int n);
	
}