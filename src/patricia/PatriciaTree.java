package patricia;

public class PatriciaTree {
	Node treeRoot;
	
	public PatriciaTree(){
		treeRoot = new Node();
		treeRoot.setAsLeaf();
	}
	
	public void insert(String word, int indexInText){
		LookupRes res = lookup(word);
				
		if(res.getNode()!=null){
			if(res.getNode().isLeaf()){
				res.getNode().setAsNotLeaf();
			}
			System.out.println("en nodo");
			System.out.println("hijos de este nodo " + res.getNode().getChildrenNum());
			reinsert(res.getStringFound(), word, indexInText);	
			
		}else{
			System.out.println("en arista");
			reinsert(res.getStringFound()+res.getEdge().getValue(),word,indexInText);
		}
	}
	
	public void reinsert(String p, String value, int indexInText){
		String mcp = getMaxCommonPrefix(value,p);
		//System.out.println("p " + p + " value "+ value + " mcp " + mcp);
		LookupRes res = lookup(mcp);
				
		if(res.getNode()!=null){
			if(res.getStringFound().equals(value)){
				//System.out.println("palabra repetida");
				res.getNode().addOccurrence(indexInText);
			}else{
				Node newNode = new Node();
				newNode.setAsLeaf();
				newNode.addOccurrence(indexInText);
				//System.out.println("value " + value + " mcp " + mcp + " differ " + getDifference(value,mcp));
				
				res.getNode().addChild(newNode,getDifference(value,mcp));
				res.getNode().setAsNotLeaf();
			}
		}else{
			Node newNode = new Node();
			
			Edge edgeToSplit = res.getEdge();
			Node beginNode = edgeToSplit.getBeginNode();
			beginNode.removeChild(edgeToSplit);
			beginNode.addChild(newNode,edgeToSplit.getValue().substring(0,res.getIndex()));
			
			newNode.addChild(edgeToSplit.getEndNode(),
					edgeToSplit.getValue().substring(res.getIndex(),edgeToSplit.getValue().length()));			
			
			Node wordNode = new Node();
			newNode.addChild(wordNode,getDifference(value,res.getStringFound()));
			wordNode.addOccurrence(indexInText);
			wordNode.setAsLeaf();
		}
	}
	
	private String getDifference(String p, String mcp){
		int endIndex = mcp.length();
		//System.out.println(endIndex);
		//System.out.println(p + "   " + p.length());
		
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
		
		while(traverse!= null && !traverse.isLeaf()){
			
			String substringToFind = pattern.substring(lengthFound, pattern.length());
								
			if(substringToFind.length()==0){
				returnValue.setNode(traverse);
				return returnValue;
			}
			
			PrefixRes res = traverse.getChildFromPrefix(substringToFind);
						
			if(res.getEdge()==null){
				System.out.println("no encontre arista");
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
		
		//System.out.println("hoja?");
		returnValue.setNode(traverse);
		return returnValue;	
	}
	
	public Integer[] search(String pattern){
		Node traverse = treeRoot;
		int lengthFound = 0;
		
		while(traverse!=null && !traverse.isLeaf() && lengthFound<pattern.length()){
			
			Edge nextEdge = traverse.getEdge(pattern.substring(lengthFound,pattern.length()));
			
			if(nextEdge!=null){
				//System.out.println(nextEdge.getValue());
				traverse = nextEdge.getEndNode();
				lengthFound = lengthFound + nextEdge.getSubstringLength();
			}else{
				//System.out.println("aqui :C");
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
