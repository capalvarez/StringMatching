package patricia;

public class PrefixRes {
	private int length;
	private Edge edge;
	
	public PrefixRes(Edge e){
		edge = e;
	}
	
	public PrefixRes(){
		
	}
	
	public Edge getEdge(){
		return edge;
	}
	
	public void setLength(int l){
		length = l;	
	}
	
	public int getMatchedLength(){
		return length;
	}
}
