package patricia;

public class Edge {
	private String substring;
	private Node beginNode;
	private Node endNode;
	
	public Edge(String sub, Node init, Node end){
		substring = sub;
		beginNode = init;
		endNode = end;
	}
	
	public String getValue(){
		return substring;
	}
	
	public int getSubstringLength(){
		return substring.length();
	}
	
	public boolean equals(Edge e){
		return substring.equals(e.getValue());
	}
	
	public Node getEndNode(){
		return endNode;
	}
	
	public Node getBeginNode(){
		return beginNode;
	}
	
	
}
