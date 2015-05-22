package patricia;

public class PatriciaTree {
	Node treeRoot;
	
	public PatriciaTree(String text){
		treeRoot = new Node();
		treeRoot.setAsLeaf();
	}
	
	public void insert(String word, int indexInText){
		LookupRes res = lookup(word);
		
		if(res.getNode()!=null){
			if(res.getNode().isLeaf()){
				res.getNode().setAsNotLeaf();
			}
			reinsert(res.getStringFound(),word);	
			
		}else{
			reinsert(res.getStringFound()+res.getEdge().getValue(),word);
		}
	}
	
	public void reinsert(String p, String value){
		String mcp = getMaxCommonPrefix(value,p);
		LookupRes res = lookup(mcp);
		
		if(res.getNode()!=null){
			res.getNode().addChild(new Node(),getDifference(p,mcp));
		}else{
			Node newNode = new Node();
			
			Edge edgeToSplit = res.getEdge();
			Node beginNode = edgeToSplit.getBeginNode();
			beginNode.removeChild(edgeToSplit);
			beginNode.addChild(newNode,edgeToSplit.getValue().substring(0,res.getIndex()));
			beginNode.addChild(edgeToSplit.getEndNode(),
					edgeToSplit.getValue().substring(res.getIndex(),edgeToSplit.getValue().length()));			
			
			Node wordNode = new Node();
			newNode.addChild(wordNode,getDifference(p,mcp));
			
		}
	}
	
	private String getDifference(String p, String mcp){
		int endIndex = mcp.length();
		
		return p.substring(endIndex, p.length());
	}
	
	private String getMaxCommonPrefix(String p1, String p2){
		int i = 0;
		int minLength = Math.min(p1.length(), p2.length());
		
		while(p1.charAt(i)==p2.charAt(i) && i<minLength){
			i++;
		}
		
		return p1.substring(0,i+1);
	}
	
	private LookupRes lookup(String pattern){
		Node traverse = treeRoot;
		int lengthFound = 0;
		LookupRes returnValue = new LookupRes();
		
		while(traverse!= null && !traverse.isLeaf()){
			PrefixRes res = traverse.getChildFromPrefix(pattern.substring(lengthFound, pattern.length()));
			
			if(res.getEdge()==null){
				returnValue.setNode(traverse);
				return returnValue;
			}else{
				if(res.getMatchedLength()==res.getEdge().getSubstringLength()){
					traverse = res.getEdge().getEndNode();
					returnValue.addSubstring(res.getEdge().getValue());
					
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
