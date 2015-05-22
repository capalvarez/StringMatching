package patricia;

import java.util.ArrayList;
import java.util.HashMap;

public class Node{
	private HashMap<Edge,Node> children;
	private ArrayList<Integer> occurrences = new ArrayList<Integer>();
	private boolean isLeaf = false;
	
	public boolean isLeaf(){
		return isLeaf;
	}
	
	public void setAsLeaf(){
		isLeaf = true;
	}
	
	public void setAsNotLeaf(){
		isLeaf = false;
	}
	
	public Edge getEdge(String sub){
		return null;
	}

	public void addChild(Node n, String s){
		Edge e = new Edge(s,this,n);
		children.put(e,n);
	}
	
	public void removeChild(Edge edgeToRemove){
		children.remove(edgeToRemove);
	}
	
	public PrefixRes getChildFromPrefix(String sToFind){
		Edge usefulEdge = null;
		for(Edge e: children.keySet()){
			if(e.getValue().charAt(0)==sToFind.charAt(0)){
				usefulEdge = e;
				break;
			}
		}
		
		if(usefulEdge==null){
			return new PrefixRes();
		}
				
		if(sToFind.length()<=usefulEdge.getSubstringLength()){
			PrefixRes r = new PrefixRes(usefulEdge);
			
			if(usefulEdge.getValue().startsWith(sToFind)){
				r.setLength(sToFind.length());
			}else{
				String mcp = getMaxCommonPrefix(usefulEdge.getValue(),sToFind);
				r.setLength(mcp.length());
			}
			
			return r;
		}else{
			if(sToFind.equals(usefulEdge.getValue())){
				return usefulEdge.getEndNode().getChildFromPrefix(
						sToFind.substring(usefulEdge.getSubstringLength(), sToFind.length()));
			}else{
				return new PrefixRes();
			}
		}	
	}
	
	private String getMaxCommonPrefix(String p1, String p2){
		int i = 0;
		int minLength = Math.min(p1.length(), p2.length());
		
		while(p1.charAt(i)==p2.charAt(i) && i<minLength){
			i++;
		}
		
		return p1.substring(0,i+1);
	}
	
	public ArrayList<Integer> getOccurrences(){
		return occurrences;
	}
}
