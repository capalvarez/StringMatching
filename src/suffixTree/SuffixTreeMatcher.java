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
		BookPreProcess bPP = new BookPreProcess(bR.getBook());
		SuffixTreeMatcher stm = new SuffixTreeMatcher(bPP.getProcessedBook());
	
		Integer[] indexes = stm.getMatchIndex("abyss");

		for(int j = 0;j<indexes.length;j++){
			System.out.println(indexes[j]);
		}
	
	}
	
	
	
	
}
