package suffixTree;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import preprocessing.BookPreProcess;
import readers.BookReader;
import automata.AutomataMatcher;

public class SuffixTreeMatcher {
	String text;
	SuffixTree tree;
	
	public SuffixTreeMatcher(String text){
		tree = new SuffixTree(text);
	}
	
	public Integer[] getMatchIndex(String pattern){
		ArrayList<Integer> indexList = tree.search(pattern);
		
		Integer[] iArray = new Integer[indexList.size()];
		iArray = indexList.toArray(iArray);
		
		return iArray;
	}
	
	public static void main(String[] args) throws IOException{
		//BookReader bR = new BookReader(new File("./data/ejemplo.txt"));
		//BookPreProcess bPP = new BookPreProcess(bR.getBook());
		SuffixTreeMatcher sTM = new SuffixTreeMatcher("nonsense");
		Node root = sTM.tree.root;
		//Node firstChild = root.findEdge('e').getEndNode();
		
		//System.out.println(firstChild.printSubstring("nonsense"));
		//Collection<Edge> children = firstChild.getEdges();
		//System.out.println(children.size());
		
		/*for (Iterator<Edge> it = children.iterator(); it.hasNext(); ){
		    System.out.println(it.next().printSubstring("nonsense"));
		}*/
		
		
		
		Integer[] app = sTM.getMatchIndex("se");
		/*for(int i=0;i<app.length;i++){
			System.out.println(app[i]);
		}*/
			
	}
	
	
}
