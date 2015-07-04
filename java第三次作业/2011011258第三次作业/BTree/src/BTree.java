import java.util.Collection;
import java.util.Collections;

/**
 * @author mayrock
 *
 */
public class BTree {

	public static final int PRE_ORDER = 1;
	public static final int IN_ORDER = 2;
	public static final int POST_ORDER = 3;
	public static final int DESCENDING = 4;
	
	public int size;
	public BTNode root;
	
	public BTree(){
		size = 0;
	}
	public BTree(int[] data){
		size = 0;
		buildTree(data);
	}
	
	public void buildTree(int[] data){
		for(int i = 0; i < data.length;i ++)
			add(data[i]);
	}
	
	public void add(int _data){
		if(root == null){
			root = new BTNode(_data, null);
			size ++;
		}
		else{
		    root.add(_data);
		    size ++;
		}
	}
	public void addLChild(BTNode node, int n){
		node.addLChild(n);
		size++;
	}
	public void addRChild(BTNode node, int n){
		node.addRChild(n);
		size++;
	}
	
	public int getNodeCount() {
		//TODO: your code here
		return size;
	}
	
	/***
	 * Return an array of all nodes in the tree.
	 * @param order: one of BTree.PRE_ORDER, BTree.IN_ORDER, 
	 * BTree.POST_ORDER and BTree.DESCENDING
	 * @return An array of Comparable objects in the particular order
	 */
	@SuppressWarnings("rawtypes")

	public Comparable[] getAllNodes(int order) {	
		Comparable[] s = new Comparable[getNodeCount()];
		//TODO: write your code here
		Caculator cacu = new Caculator();
		if(order == PRE_ORDER){
			System.out.println("PRE_ORDER");
			root.preOrder(s, cacu);
		}
		else if(order == IN_ORDER){
			System.out.println("IN_ORDER");
			root.inOrder(s, cacu);
		}
		else if(order == POST_ORDER){
			System.out.println("POST_ORDER");
			root.postOrder(s, cacu);
		}
		else if(order == DESCENDING){
			System.out.println("DES_ORDER");
			root.descend(s, cacu);
			int N = getNodeCount();
			for(int i = 0; i < N - 1; i ++){
				for(int j = 0; j < N - i - 1; j ++){
					if(s[j].compareTo(s[j + 1]) <= 0){
						int t = ((BTNode)s[j + 1]).data;
						((BTNode)s[j + 1]).data = ((BTNode)s[j]).data;
						((BTNode)s[j]).data = t;
					}
				}
			}
			for(int i = 0; i < N; i ++){
				System.out.println(((BTNode)s[i]).data);
			}
		}
		else{
			System.out.println("order should be in the range of one to four");
		}
		
		return s;
	}
	
	public static void main(String[] args) {
		//TODO: you test code here
		
		BTree tree1 = new BTree();
		tree1.add(1);
		tree1.addLChild(tree1.root, 2);
		tree1.addRChild(tree1.root, 5);
		tree1.addLChild(tree1.root.lChild, 3);
		tree1.addRChild(tree1.root.lChild,4);
		for(int i = 1; i <= 4; i++){
			Comparable[] s = tree1.getAllNodes(i);
		
		}
		System.out.println("Another tree");
		
		//用数组生成搜索树,做孩子小于父节点值,有孩子大于父节点值
		int[] data = {0,4,6,2,7,-1,3,6};
		BTree tree = new BTree(data);
		for(int i = 1; i <= 4; i++){
			Comparable[] s = tree.getAllNodes(i);
			/*for(int j= 0; j < s.length; j ++){
				System.out.println("compare with 3 = " + s[j].compareTo(node));
				
			}*/
		}
	
	}
}
