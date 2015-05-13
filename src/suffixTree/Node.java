package suffixTree;

public class Node {
	int begin;
	int end;
	int depth; 
	Node parent;
	Node[] children;
	Node suffixLink;
	boolean isLeaf;
	
	public Node(int begin, int end, int depth, Node parent, int childNum) {
		this.begin = begin;
		this.end = end;
		this.parent = parent;
		this.depth = depth;
		
		children = new Node[childNum];
	}
	
	public Node findNode(int begin, int end){
		return null;
	}
	
	public boolean hasChild(int i){
		return children[i]!=null;
	}
	
	public boolean isLeaf(){
		return isLeaf;
	}
	
	public void implicitAdd(){
		end = -1;
	}
}
