package patricia;

public class LookupRes {
	private Node resNode;
	private Edge resEdge;
	private int indexToSplit;
	private StringBuilder builder = new StringBuilder();
	
	public LookupRes(Node n){
		resNode = n;
	}
	
	public LookupRes(){
		
	}
	
	public void setNode(Node n){
		resNode = n;
	}
	
	public void setEdge(Edge e, int index){
		resEdge = e;
		indexToSplit = index;
	}
	
	public LookupRes(Edge e, int index){
		resEdge = e;
		indexToSplit = index;
	}
	
	public Node getNode(){
		return resNode;
	}
	
	public Edge getEdge(){
		return resEdge;
	}
	
	public int getIndex(){
		return indexToSplit;
	}
	
	public String getStringFound(){
		return builder.toString();
	}
	
	public void addSubstring(String s){
		builder.append(s);
	}
	
}
