package patricia;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import preprocessing.BookPreProcess;

import readers.BookReader;

public class PatriciaTreeMatcher {
	String text;
	PatriciaTree patTree = new PatriciaTree();
	
	public PatriciaTreeMatcher(String t){
		text = t;
		buildPatricia();
	}
	
	private void buildPatricia(){		
		HashMap<Integer,String> words = getWordsByIndex();
		Set<Integer> indexes = words.keySet();
		
		Iterator<Integer> it = indexes.iterator();
		for(Integer i: indexes){
		//for(int i = 0;i<6;i++){	
			//Integer next = it.next();
			String word = words.get(i);
			System.out.println("Insertando " + word);
			
			patTree.insert(word,i);
		}		
	}
	
	private HashMap<Integer,String> getWordsByIndex(){
		HashMap<Integer,String> words = new HashMap<Integer,String>();
		int globalIndex = 0;
		
		while(globalIndex<text.length()){
			int beginIndex = globalIndex;
			int endIndex = globalIndex;
			
			
			while(endIndex<text.length() && text.charAt(endIndex)!=' '){
				endIndex++;
			}
			
			words.put(beginIndex, text.substring(beginIndex, endIndex));
			
			globalIndex = endIndex;
			while(globalIndex<text.length() && text.charAt(globalIndex)==' '){
				globalIndex++;
			}		
		}
		
		return words;
	}
	
	public Integer[] getMatchIndex(String pattern){
		return patTree.search(pattern);
	}

	
	public static void main(String[] args) throws IOException{
		BookReader bR = new BookReader(new File("./data/ejemplo.txt"));
		BookPreProcess bPP = new BookPreProcess(bR.getBook());
		PatriciaTreeMatcher ptm = new PatriciaTreeMatcher(bPP.getProcessedBook());
		
		/*HashMap<Integer,String> wordList = ptm.getWordsByIndex();
		for (Integer key: wordList.keySet()){

            String value = wordList.get(key);  
            System.out.println(key + " " + value);  
		} */
		
		Integer[] indexes = ptm.getMatchIndex("abyss");

		/*for(int i = 0;i<indexes.length;i++){
			System.out.println(indexes[i]);
		}*/

	} 
	
}
