package preprocessing;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import readers.BookReader;

public class RandomWordSelector {
	String text;
	ArrayList<String> wordList;
	
	public RandomWordSelector(String text){
		this.text = text;
		wordList = new ArrayList<String>();
		
		buildWordList();
	}
	
	private void buildWordList(){
		int globalIndex = 0;
		
		while(globalIndex<text.length()){
			int beginIndex = globalIndex;
			int endIndex = globalIndex;
			
			while(endIndex<text.length() && text.charAt(endIndex)!=' '){
				endIndex++;
			}
			
			wordList.add(text.substring(beginIndex, endIndex));
			
			globalIndex = endIndex;
			while(globalIndex<text.length() && text.charAt(globalIndex)==' '){
				globalIndex++;
			}		
		}
	}
	
	public int getNumberOfWords(){
		return wordList.size();
	}
	
	public String[] getNWords(int N){
		int value;
		Random rand = new Random(); 
				
		String[] words = new String[N];
		
		for(int i=0;i<N;i++){
			value = rand.nextInt(wordList.size());
			words[i] = wordList.get(value);
		}
		
		return words;
	}
	
	public static void main(String[] args) throws IOException{
		BookReader bR = new BookReader(new File("./data/ejemplo.txt"));
		BookPreProcess bPP = new BookPreProcess(bR.getBook());
		
		RandomWordSelector rws = new RandomWordSelector(bPP.getProcessedBook());
		
		String[] words = rws.getNWords(10);
		
		for(int i=0;i<10;i++){
			System.out.println(words[i]);
		}
		
	}
	
	
	
	
}
