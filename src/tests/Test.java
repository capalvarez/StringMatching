package tests;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import automata.AutomataMatcher;
import patricia.PatriciaTreeMatcher;
import preprocessing.BookPreProcess;
import preprocessing.RandomWordSelector;
import readers.BookReader;
import suffixTree.SuffixTreeMatcher;

public class Test {
	String text;
	String[] words;
			
	public Test(String fileName) throws IOException{
		BookReader bR = new BookReader(new File(fileName));
		BookPreProcess bPP = new BookPreProcess(bR.getBook());
				
		this.text = bPP.getProcessedBook();
		RandomWordSelector rws = new RandomWordSelector(text);
		words = rws.getNWords(rws.getNumberOfWords()/10);	
	}
	
	public long[] automataTest(){
		long constructionTime = 0;
		long searchTime = 0;	
		AutomataMatcher am = new AutomataMatcher(text);
				
		for(int i=0;i<words.length;i++){
			Integer[] results = am.getMatchIndex(words[i]);
			
			constructionTime = constructionTime + am.getAutomataTime(); 
			searchTime = constructionTime + am.getSearchTime(); 
		}
		
		long[] results = {constructionTime,searchTime};
		return results;
	}
	
	public long[] patriciaTest(){
		long startTimePatricia = System.currentTimeMillis();
		PatriciaTreeMatcher ptm = new PatriciaTreeMatcher(text);	
		long endTimePatricia = System.currentTimeMillis();
		
		for(int i=0;i<words.length;i++){
			Integer[] results = ptm.getMatchIndex(words[i]);
		}
		
		long endTimeSearch = System.currentTimeMillis(); 
		
		long[] results = {(endTimePatricia - startTimePatricia),(endTimeSearch - endTimePatricia)};
		
		return results;
	}
	
	public long[] suffixTreeTest(){
		long startTimeSuffix = System.currentTimeMillis();
		SuffixTreeMatcher stm = new SuffixTreeMatcher(text);	
		long endTimeSuffix = System.currentTimeMillis();
		
		for(int i=0;i<words.length;i++){
			Integer[] results = stm.getMatchIndex(words[i]);
		}
		
		long endTimeSearch = System.currentTimeMillis(); 
		
		long[] results = {(endTimeSuffix - startTimeSuffix),(endTimeSearch - endTimeSuffix)};
		
		return results;	
	}
	
	public static void main(String[] args) throws IOException{
		String[] paths = {"./data/dark_materials.txt","./data/dark_tower.txt","./data/douglas_adams.txt","./data/gabriel_garcia_marquez.txt","./data/haruki_murakami.txt"};
		PrintWriter writer = new PrintWriter("results.txt", "UTF-8");
		
		for(int i=0;i<paths.length;i++){
			writer.println("Testeando el archivo " + paths[i]);
			Test test = new Test(paths[i]);		
			
			long[] automataResults = test.automataTest();
			long[] patriciaResults = test.patriciaTest();
			long[] suffixResults = test.suffixTreeTest();
						
			writer.println("Resultados de automata");
			writer.println("Tiempo de total de construccion " + automataResults[0] +" milis");
			writer.println("Tiempo de total de busqueda " + automataResults[1] +" milis");
			writer.println("");
			writer.println("Resultados de árboles patricia");
			writer.println("Tiempo de total de construccion " + patriciaResults[0] +" milis");
			writer.println("Tiempo de total de busqueda " + patriciaResults[1] +" milis");
			writer.println("");
			writer.println("Resultados de suffix trees");
			writer.println("Tiempo de total de construccion " + suffixResults[0] +" milis");
			writer.println("Tiempo de total de busqueda " + suffixResults[1] +" milis");		
		}
		
		writer.close();
		
	}
	
	
	
}
