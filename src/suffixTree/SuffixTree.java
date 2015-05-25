package suffixTree;

import java.util.ArrayList;
import java.util.Map;

public class SuffixTree {
	String text;
	Node treeRoot;
	
	Node lastNewNode;
	Node activeNode;
	int activeEdge = -1;
	int activeLength = 0;
	
	int remainingSuffixCount = 0;
	int leafEnd = 0;
	int splitIndex = 0;
	
	public SuffixTree(String text){
		this.text = text;
		buildSuffixTree();
	}
		
	private boolean walkDown(Node currentNode){
		int length = currentNode.getEdgeLength();
		
		if(length<=activeLength){
			activeEdge = activeEdge + length;
			activeLength = activeLength - length;
			activeNode = currentNode;		
			
			return true;
		}
		
		return false;
	}
	
	private void extendPhase(int i){
		leafEnd++;
		remainingSuffixCount++;
		lastNewNode = null;
		
		while(remainingSuffixCount>0){
			if(activeLength==0){
				activeEdge = i;
			}
		
			if(activeNode.findChild(text.charAt(i))==null){
				Node newNode = new Node(i,-1,treeRoot);
				activeNode.addChild(text.charAt(i), newNode);
			
				if(lastNewNode!=null){
					lastNewNode.setSuffixLink(activeNode);
					lastNewNode = null;
				}
			}else{
				Node nextNode = activeNode.findChild(text.charAt(i));
				
				if(walkDown(nextNode)){
					continue;
				}
				
				if(text.charAt(i)==text.charAt(nextNode.getStartIndex() + activeLength)){
					if(lastNewNode!=null && !activeNode.isRoot()){
						lastNewNode.setSuffixLink(activeNode);
						lastNewNode = null;
					}
					
					activeLength++;
					break;
				}
				
				splitIndex = nextNode.getStartIndex() + activeLength - 1;
				
				Node newNode = new Node(nextNode.getStartIndex(),splitIndex,treeRoot);
				activeNode.addChild(text.charAt(activeEdge), newNode);
				
				Node splitChild = new Node(i,-1,treeRoot);
				newNode.addChild(text.charAt(i),splitChild);
								
				nextNode.setStartIndex(nextNode.getStartIndex() + activeLength);
				newNode.addChild(text.charAt(nextNode.getStartIndex()),nextNode);
				
				if(lastNewNode!=null){
					lastNewNode.setSuffixLink(splitChild);
				}
				
				lastNewNode = splitChild;
				
			}
			
			remainingSuffixCount--;
			if(activeNode.isRoot() && activeLength>0){
				activeLength--;
				activeEdge = i - remainingSuffixCount + 1;
			}else if(!activeNode.isRoot()){
				activeNode = activeNode.getSuffixLink();
			}
		}
	}
	
	private void buildSuffixTree(){
		treeRoot = new Node(-1,-1,null);
		activeNode = treeRoot;
		
		for(int i=0;i<text.length();i++){
			extendPhase(i);
		}
		
		finishSuffixTree(treeRoot,0);
	}
	
	private void finishSuffixTree(Node n, int labelHeight){
		if(n==null){
			return;
		}
		
		boolean currentIsLeaf = true;
		Map<Character,Node> children = n.getChildren(); 
		
		for(Character c: children.keySet()){
			Node child = children.get(c);
			finishSuffixTree(child,labelHeight + child.getEdgeLength());
			
			currentIsLeaf = false;
		}
	
		if(currentIsLeaf){
			n.setEndIndex(leafEnd);
			n.setSuffixIndex(text.length() - labelHeight);
		}
	}
	
	private int traverseEdge(String pattern,int patternIndex, int start, int end){
		for(int k = start; k<=end && text.charAt(patternIndex)!='\0';k++, patternIndex++){
			if(pattern.charAt(patternIndex)!=text.charAt(k)){
				return -1;
			}
		}
		
		if(pattern.charAt(patternIndex)=='\0'){
			return 1;
		}
		
		return 0;
	}
	
	private ArrayList<Integer> searchInLeaves(Node n){
		ArrayList<Integer> returnValue = new ArrayList<Integer>();
		
		if(n==null){
			return returnValue;
		}
		
		if(n.isLeaf()){
			returnValue.add(n.getSuffixIndex());
			
			return returnValue;
		}
		
		Map<Character,Node> children = n.getChildren();
		for(Character c: children.keySet()){
			returnValue.addAll(searchInLeaves(children.get(c)));
		}
		
		return returnValue;
	}
	
	private ArrayList<Integer> searchInNode(Node n, String pattern, int patternIndex){
		ArrayList<Integer> returnValue = new ArrayList<Integer>();
		if(n==null){
			return null;
		}
		
		if(!n.isRoot()){
			int res = traverseEdge(pattern,patternIndex,n.getStartIndex(),n.getEndIndex());
			if(res<0){
				return null;
			}else if(res>0){
				if(n.isLeaf()){
					returnValue.add(n.getSuffixIndex());
					return returnValue;
				}else{
					return searchInLeaves(n);
				}
			}
		}
		
		patternIndex = patternIndex + n.getEdgeLength(); 
		
		Node child = n.findChild(pattern.charAt(patternIndex));
		if(child!=null){
			return searchInNode(child,pattern,patternIndex);
		}else{
			return null;
		}
	}
	
	public Integer[] search(String pattern){
		ArrayList<Integer> iList = searchInNode(treeRoot,pattern,0);
	
		if(iList!=null){
			Integer[] iArray = new Integer[iList.size()];
			iArray = iList.toArray(iArray);
			
			return iArray;
		}else{
			return null;
		}
		
	}
	
}
