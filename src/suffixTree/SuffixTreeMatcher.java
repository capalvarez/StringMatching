package suffixTree;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import preprocessing.BookPreProcess;
import readers.BookReader;

public class SuffixTreeMatcher {
	String text;
	SuffixTree suffixTree;
	
	public SuffixTreeMatcher(String text){
		this.text = text;
		suffixTree = new SuffixTree(text);
	}
	
	public Integer[] getMatchIndex(String pattern){
		return suffixTree.search(pattern);
	}
	
	public static void main(String[] args) throws IOException{	
		BookReader bR = new BookReader(new File("./data/ejemplo.txt"));
		BookPreProcess bPP = new BookPreProcess("The womb of nature and perhaps her grave, Of neither");
		SuffixTreeMatcher stm = new SuffixTreeMatcher(bPP.getProcessedBook());
		System.out.println(bPP.getProcessedBook());
		//SuffixTreeMatcher stm = new SuffixTreeMatcher();
		
		Map<Character,Node> children = stm.suffixTree.treeRoot.getChildren();
		int i = 1;
		for(Character c: children.keySet()){
			System.out.println("hijo numero "+ i + " es " + c);
			System.out.println("numero de hijos del hijo " + children.get(c).getChildren().size());
			i++;
		}
		System.out.println();
		System.out.println("hijos de espacio");
		Map<Character,Node> aChildren = children.get(' ').getChildren();
		for(Character c: aChildren.keySet()){
			System.out.println("start " + aChildren.get(c).getStartIndex() + " end " + aChildren.get(c).getEndIndex());
			//System.out.println("suffix Index " + aChildren.get(c).getSuffixIndex());
		}
		
		/*System.out.println();
		System.out.println("n");
		Map<Character,Node> nChildren =  children.get('n').getChildren();
		for(Character c: nChildren.keySet()){
			System.out.println("start " + nChildren.get(c).getStartIndex() + " end " + nChildren.get(c).getEndIndex());
			System.out.println("suffix Index " + nChildren.get(c).getSuffixIndex());
		}*/
		
		
		/*Integer[] indexes = stm.getMatchIndex("neither");

		for(int i = 0;i<indexes.length;i++){
			System.out.println(indexes[i]);
		}*/
	
	}
	
	
	
	
}
