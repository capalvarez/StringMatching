package automata;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import preprocessing.BookPreProcess;

import readers.BookReader;

public class AutomataMatcher {
	String text;
	
	public AutomataMatcher(String t){
		text = t; 
	}
	
	public Integer[] getMatchIndex(String pattern){
		Character[] chars = getCharacterAlpha();
		ArrayList<Character> alphabet = new ArrayList<Character>(Arrays.asList(chars));
		
		int[][] deltaFunction = (new AutomataConstructor(pattern,alphabet)).getDeltaFunction();
		
		System.out.println(Arrays.deepToString(deltaFunction));
		
		ArrayList<Integer> indexList = new ArrayList<Integer>();
		
		int m = pattern.length();
		char[] charText = text.toCharArray();
		
		int q = 0;
		
		for(int i=0;i<charText.length;i++){
			q = deltaFunction[q][alphabet.indexOf(charText[i])];
			
			if(q==m){
				indexList.add(i-m+1);
			}
		}
		
		Integer[] iArray = new Integer[indexList.size()];
		iArray = indexList.toArray(iArray);
		
		return iArray;
	}
	
	private Character[] getCharacterAlpha(){
		char[] alphabet = (" abcdefghijklmnñopqrstuvwxyz0123456789" + "\n").toCharArray();
		Character[] alpha = new Character[alphabet.length];
		
		for(int i=0;i<alphabet.length;i++){
			alpha[i] = (Character) alphabet[i];
		}
		
		return alpha;
	}
	
	public static void main(String[] args) throws IOException{
		BookReader bR = new BookReader(new File("./data/ejemplo.txt"));
		BookPreProcess bPP = new BookPreProcess(bR.getBook());
		AutomataMatcher aM = new AutomataMatcher(bPP.getProcessedBook());
				
		Integer[] app = aM.getMatchIndex("abyss");
		for(int i=0;i<app.length;i++){
			System.out.println(app[i]);
		}
	}

}
