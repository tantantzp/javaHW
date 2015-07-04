
public class BTNode implements Comparable<BTNode>{
    public int data;
	public BTNode parent;
	public BTNode lChild;
	public BTNode rChild;
	
	
	public BTNode(int _data, BTNode _parent, BTNode _lChild, BTNode _rChild) {
		data = _data;
		parent = _parent;
		lChild = _lChild;
		rChild = _rChild;
	}
	public BTNode(int _data, BTNode _parent){
		data = _data;
		parent = _parent;
		lChild = rChild = null;
	}
	public void add(int _data){
		if(_data <= data){
			if(lChild != null) lChild.add(_data);
			else lChild = new BTNode(_data, this);
		}
		else{
			if(rChild != null) rChild.add(_data);
			else rChild = new BTNode(_data, this);
		}
		
	}
	
	public void preOrder(Comparable[] s, Caculator cacu){
		System.out.println(data);
		s[cacu.index] = this;
		cacu.index ++;
		if(lChild != null){
			lChild.preOrder(s, cacu);
		}
		if(rChild != null){
			rChild.preOrder(s, cacu);
		}
	}
	
	public void inOrder(Comparable[] s, Caculator cacu) {	
		if(lChild != null){
			lChild.inOrder(s, cacu);
		}
		System.out.println(data);
		s[cacu.index] = this;
		cacu.index ++;
		if(rChild != null){
			rChild.inOrder(s, cacu);
		}
		
	}
	public void postOrder(Comparable[] s, Caculator cacu) {	
		if(lChild != null){
			lChild.postOrder(s, cacu);
		}
		if(rChild != null){
			rChild.postOrder(s, cacu);
		}
		System.out.println(data);
		s[cacu.index] = this;
		cacu.index ++;
	}
	public void descend(Comparable[] s, Caculator cacu) {	
		if(rChild != null){
			rChild.descend(s, cacu);
		}		
		//System.out.println(data);
		s[cacu.index] = this;
		cacu.index ++;
		if(lChild != null){
			lChild.descend(s, cacu);
		}
	}
	public void addLChild(int n) {
		lChild = new BTNode(n, this);
	}
	public void addRChild(int n) {
		rChild = new BTNode(n, this);
	}
	public void setParent(BTNode _parent) {
		parent = _parent;
	}
	
	public BTNode getLChild() {
		return lChild;
	}
	public BTNode getRChild() {
		return rChild;
	}
	public BTNode getParent() {
		return parent;
	}
	
	public int compareTo(BTNode node) {
		if(data > node.data)
			return 1;
		else if(data == node.data)
			return 0;
		else
			return -1;	
	}
}

class Caculator{
	int index;
	Caculator(){
		index = 0;
	}
}
