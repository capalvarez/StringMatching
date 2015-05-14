package suffixTree;

public class Suffix {
	private int beginIndex;
	private int endIndex;
	private Node initNode;

	public Suffix(Node initNode, int beginIndex, int endIndex){
		this.initNode = initNode;

		this.beginIndex = beginIndex;
		this.endIndex = endIndex;
	}

	public Node getNode(){
		return initNode;
	}
	
	public void setInitNode() {
		initNode = initNode.getSuffixLink();
	}
	
	public int getBeginIndex(){
		return beginIndex;
	}
	
	public int getEndIndex(){
		return endIndex;
	}
	
	public void increaseBegin(){
		beginIndex++;
	}

	public void increaseEnd(){
		endIndex++;
	}	
	
	public boolean isExplicit(){
		return beginIndex > endIndex;
	}

	public boolean isImplicit() {
		return endIndex >= beginIndex;
	}

	/*public void canonize() {
		if (!isExplicit()) {
			Edge edge = originNode.findEdge(originNode.charAt(beginIndex));

			int edgeSpan = edge.getSpan();
			while (edgeSpan <= getSpan()) {
				beginIndex += edgeSpan + 1;
				originNode = edge.getEndNode();
				if (beginIndex <= endIndex) {
					edge = edge.getEndNode().findEdge(
							originNode.charAt(beginIndex));
					edgeSpan = edge.getSpan();
				}
			}
		}
	}*/

	public int getSubstringLength() {
		return endIndex - beginIndex;
	}

}
