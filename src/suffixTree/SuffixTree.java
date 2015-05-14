package suffixTree;

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

            // We didn't find a matching edge, so we create a new one, add it to the tree at the parent node position,
            // and insert it into the hash table.  When we create a new node, it also means we need to create
            // a suffix link to the new node from the last node we visited.
            Edge newEdge = new Edge(endIndex, text.length() - 1, parentNode);
            newEdge.insert();
            updateSuffixNode(lastParentNode, parentNode);
            lastParentNode = parentNode;

            if (active.getNode() == root)
                active.increaseBegin();
            else
                active.changeOriginNode();
            active.canonize();
        }
        updateSuffixNode(lastParentNode, parentNode);
        active.incEndIndex();   //Now the endpoint is the next active point
        active.canonize();
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
    
    
}
