package suffixTree;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Node{
    private Node suffixLink;
    private Map<Character, Edge> children;

    public Node(Node suffixLink){
        this.suffixLink = suffixLink;
        children = new HashMap<Character, Edge>();
    }

    public void addEdge(char charToAdd, Edge edge){
        children.put(charToAdd, edge);
    }

    public void removeEdge(char charToRemove){
        children.remove(charToRemove);
    }

    public Edge findEdge(char ch){
        return children.get(ch);
    }

    public boolean isLeaf(){
     	return children.size()==0;
    }
    
    public Node getSuffixLink(){
        return suffixLink;
    }

    public void setSuffixLink(Node suffixLink){
        this.suffixLink = suffixLink;
    }

    public Collection<Edge> getEdges(){
        return children.values();
    }
	
    public Map<Character, Edge> getChildren(){
    	return children;
    }
    
}
