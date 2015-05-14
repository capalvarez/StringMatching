package suffixTree;

public class Edge {
	 private int beginIndex;     
	 private int endIndex;
	 private Node beginNode;
	 private Node endNode;      

	public Edge(Node startNode, int beginIndex, int endIndex){
		this.beginIndex = beginIndex;
		this.endIndex = endIndex;
		
		this.beginNode = startNode;
		this.endNode = new Node(null);
	}
	
	public int getBeginIndex(){
		return beginIndex;
	}
	
	public int getEndIndex(){
		return endIndex;
	}

	public void setEndIndex(int endIndex){
		this.endIndex = endIndex;
	}
	
	public Node splitEdge(Suffix suffix, String text){
		/*Sacamos la arista del nodo inicial*/
		beginNode.removeEdge(text.charAt(beginIndex));
		
		/*Crea una nueva arista que termina en el sufijo a incluir*/
		Edge newEdge = new Edge(suffix.getNode(), beginIndex, beginIndex + suffix.getSubstringLength());
		newEdge.getBeginNode().addEdge(text.charAt(beginIndex), newEdge);
		newEdge.endNode.setSuffixLink(suffix.getNode());
		
		/*Actualizamos esta arista para representar la mitad del substring*/
		beginIndex = beginIndex + suffix.getSubstringLength() + 1;
		beginNode = newEdge.getEndNode();
		beginNode.addEdge(text.charAt(beginIndex), this);
		
		return newEdge.getEndNode();
	}

	public int getSubstringLength(){
		return endIndex - beginIndex;
	}

	public Node getBeginNode(){
		return beginNode;
	}

	public void setBeginNode(Node beginNode){
		this.beginNode = beginNode;
	}

	public Node getEndNode(){
		return endNode;
	}

	public String toString(){
		return endNode.toString();
	}
}

