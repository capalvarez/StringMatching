package patricia;

public class PatriciaTree {
	public Node treeRoot;
	
	public PatriciaTree(){
		treeRoot = new Node();
		treeRoot.setAsLeaf();
	}
	
	public void insert(String word, int indexInText){
		LookupRes res = lookup(word);
				
		if(res.getNode()!=null){
			reinsert(res.getStringFound(), word, indexInText);	
		}else{
			reinsert(res.getStringFound()+res.getEdge().getValue(),word,indexInText);
		}	
	}
	
	public void reinsert(String p, String value, int indexInText){
		String mcp = getMaxCommonPrefix(value,p);
		LookupRes res = lookup(mcp);
				
		if(res.getNode()!=null){
			if(res.getStringFound().equals(value)){
				res.getNode().addOccurrence(indexInText);
			}else{
				Node newNode = new Node();
				newNode.setAsLeaf();
				newNode.addOccurrence(indexInText);
						
				res.getNode().addChild(newNode,getDifference(value,mcp));
				res.getNode().setAsNotLeaf();
			}
		}else{
			if(res.getIndex()==value.length()){
				res.getEdge().getEndNode().addOccurrence(indexInText);
				return;	
			}
			
			Node newNode = new Node();
			
			Edge edgeToSplit = res.getEdge();
			Node beginNode = edgeToSplit.getBeginNode();
			beginNode.removeChild(edgeToSplit);
			beginNode.addChild(newNode,edgeToSplit.getValue().substring(0,res.getIndex()));
			
			newNode.addChild(edgeToSplit.getEndNode(),
					edgeToSplit.getValue().substring(res.getIndex(),edgeToSplit.getValue().length()));			
			
			Node wordNode = new Node();
			newNode.addChild(wordNode,value.substring(res.getIndex(), value.length()));
			wordNode.addOccurrence(indexInText);
			wordNode.setAsLeaf();

		}
	}
	
	private String getDifference(String p, String mcp){
		int endIndex = mcp.length();

		return p.substring(endIndex, p.length());
	}
	
	private String getMaxCommonPrefix(String p1, String p2){
		int i = 0;
		int minLength = Math.min(p1.length(), p2.length());
		
		while(i<minLength && p1.charAt(i)==p2.charAt(i)){
			i++;
		}
		
		return p1.substring(0,i);
	}
	
	private LookupRes lookup(String pattern){
		Node traverse = treeRoot;
		int lengthFound = 0;
		LookupRes returnValue = new LookupRes();
		
		while(traverse!= null){
			
			String substringToFind = pattern.substring(lengthFound, pattern.length());
			
			if(substringToFind.length()==0){
				returnValue.setNode(traverse);
				return returnValue;
			}
			
			PrefixRes res = traverse.getChildFromPrefix(substringToFind);
						
			if(res.getEdge()==null){
				returnValue.setNode(traverse);
				return returnValue;
			}else{
				if(res.getMatchedLength()==res.getEdge().getSubstringLength()){
					traverse = res.getEdge().getEndNode();
					returnValue.addSubstring(res.getEdge().getValue());
					lengthFound = lengthFound + res.getMatchedLength();
				}else{
					returnValue.setEdge(res.getEdge(),res.getMatchedLength());
					return returnValue;
				}
			}
		}

		returnValue.setNode(traverse);
		return returnValue;	
	}
	
	public Integer[] search(String pattern){
		Node traverse = treeRoot;
		int lengthFound = 0;
		
		while(traverse!=null && !traverse.isLeaf() && lengthFound<pattern.length()){
			
			Edge nextEdge = traverse.getEdge(pattern.substring(lengthFound,pattern.length()));
			
			if(nextEdge!=null){
				traverse = nextEdge.getEndNode();
				lengthFound = lengthFound + nextEdge.getSubstringLength();
			}else{
				traverse = null;
			}		
		}
		
		if(traverse==null){
			return null;
		}else{
			Integer[] iArray = new Integer[traverse.getOccurrences().size()];
			iArray = traverse.getOccurrences().toArray(iArray);
			
			return iArray;
		}	
	}

	
	
	
}
