package suffixTree;

import java.util.ArrayList;

public class SuffixTree {
	private String text;
    private Node root;
    private int nodesCount;

    public SuffixTree(String text) {
        nodesCount = 0;
        this.text = text;
        root = new Node(null);

        Suffix active = new Suffix(root, 0, -1);
        for (int i = 0; i < text.length(); i++) {
            addPrefix(active, i);
        }
    }
    
    private void addPrefix(Suffix active, int endIndex) {
        Node lastParentNode = null;
        Node parentNode;

        while (true){
            Edge edge;
            parentNode = active.getNode();

            if (active.isExplicit()){
                edge = active.getNode().findEdge(text.charAt(endIndex));
                
                if (edge != null){
                	break;
                }    
            }else{
                edge = active.getNode().findEdge(text.charAt(active.getBeginIndex()));
                int length = active.getSubstringLength();
                
                if (text.charAt(edge.getBeginIndex() + length + 1) == text.charAt(endIndex)){
                    break;
                }    
                
                parentNode = edge.splitEdge(active,text);
            }

            Edge newEdge = new Edge(parentNode,endIndex, text.length() - 1);
            newEdge.getBeginNode().addEdge(text.charAt(endIndex), newEdge);
    
            updateSuffixNode(lastParentNode, parentNode);
            lastParentNode = parentNode;

            if(active.getNode() == root){
                active.increaseBegin();
            }else{
            	 active.setInitNode();
            }
               
            active.canonize(text);
        }
        updateSuffixNode(lastParentNode, parentNode);
        active.increaseEnd();   
        active.canonize(text);
    }

    private void updateSuffixNode(Node node, Node suffixLink){
        if ((node != null) && (node != root)){
            node.setSuffixLink(suffixLink);
        }
    }  

    public String getText(){
        return text;
    }

    public Node getRootNode(){
        return root;
    }
    
    public int getNewNodeNumber(){
        return nodesCount++;
    }
    
    public ArrayList<Integer> search(String pattern){
		int index = -1;
		Node node = root;

		int i = 0;
		while (i < pattern.length()){
			if ((node == null) || (i == text.length()))
				return new ArrayList<Integer>();

			Edge edge = node.findEdge(pattern.charAt(i));
			if (edge == null)
				return new ArrayList<Integer>();

			index = edge.getBeginIndex() - i;
			i++;

			for (int j = edge.getBeginIndex() + 1; j <= edge.getEndIndex(); j++) {
				if (i == pattern.length())
					break;
				if (text.charAt(j) != pattern.charAt(i))
					return new ArrayList<Integer>();
				i++;
			}
			node = edge.getEndNode();
		}
		
		ArrayList<Integer> indexes = new ArrayList<Integer>();
		
		if(node.isLeaf()){
			indexes.add(index);
		}else{
						
			
			while(!node.isLeaf()){
				
			}
		}
		
		return indexes;
	}
}
