package suffixTree;

import java.util.HashMap;
import java.util.Map;

public class Node {
	private Node suffixLink;
	private Map<Character, Node> children;
	private int suffixIndex;
	private int startIndex;
	private int endIndex;	
	
	public Node(int start, int end, Node suffixLink) {
		children = new HashMap<Character, Node>();
		
		startIndex = start;
		endIndex = end;
		
		suffixIndex = -1;
		this.suffixLink = suffixLink;
	}

	public boolean isRoot(){
		return startIndex<0 && endIndex<0;
	}
	
	public boolean isLeaf(){
		return suffixIndex>0;
	}
	
	public int getEdgeLength(){
		return endIndex - startIndex + 1;
	}
	
	public int getStartIndex(){
		return startIndex;
	}
	
	public int getEndIndex(){
		return endIndex;
	}
	
	public void setStartIndex(int start){
		startIndex = start;
	}
	
	public void setEndIndex(int end){
		endIndex = end;
	}
	
	public void addChild(char charToAdd, Node child) {
		children.put(charToAdd, child);
	}

	public Node findChild(char ch) {
		return children.get(ch);
	}

	public Node getSuffixLink() {
		return suffixLink;
	}

	public void setSuffixLink(Node suffixLink) {
		this.suffixLink = suffixLink;
	}

	public Map<Character,Node> getChildren() {
		return children;
	}
	
	public void setSuffixIndex(int index){
		suffixIndex = index;
	}
	
	public int getSuffixIndex(){
		return suffixIndex;
	}
}
