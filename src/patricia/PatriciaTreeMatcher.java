package patricia;

import java.util.HashMap;
import java.util.Set;

public class PatriciaTreeMatcher {
	String text;
	PatriciaTree patTree;
	
	public PatriciaTreeMatcher(String t){
		text = t;
		buildPatricia();
	}
	
	private void buildPatricia(){		
		HashMap<Integer,String> words = getWordsByIndex();
		Set<Integer> indexes = words.keySet();
		
		for(Integer i: indexes){
			String word = words.get(i);
			patTree.insert(word,i);
		}		
	}
	
	private HashMap<Integer,String> getWordsByIndex(){
		HashMap<Integer,String> words = new HashMap<Integer,String>();
		int globalIndex = 0;
		
		while(globalIndex<text.length()){
			int beginIndex = globalIndex;
			int endIndex = globalIndex;
			
			while(text.charAt(endIndex)!=' '){
				endIndex++;
			}
			
			words.put(beginIndex, text.substring(beginIndex, endIndex));
			
			globalIndex = endIndex;
			while(text.charAt(globalIndex)==' '){
				globalIndex++;
			}		
		}
		
		return words;
	}
	
	public Integer[] getMatchIndex(String pattern){
		return patTree.search(pattern);
	}

	
	
	
	
}
