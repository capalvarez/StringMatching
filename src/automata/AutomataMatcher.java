package automata;

import java.util.ArrayList;

public class AutomataMatcher {
	String text;
	char[] alphabet = "abcdefghijklmnñopqrstuvwxyz0123456789".toCharArray();
	
	public AutomataMatcher(String t){
		text = t; 
	}
	
	public Integer[] getMatchIndex(String pattern){
		int[][] deltaFunction = (new AutomataConstructor(pattern,alphabet)).getDeltaFunction();
		
		ArrayList<Integer> indexList = new ArrayList<Integer>();
		
		int m = pattern.length();
		char[] charText = text.toCharArray();
		
		int q = 0;
		
		for(int i=0;i<charText.length;i++){
			int c = (int) charText[i];
			q = deltaFunction[q][c];
			
			if(q==m){
				indexList.add(i-m+1);
			}
		}
		
		Integer[] iArray = new Integer[indexList.size()];
		iArray = indexList.toArray(iArray);
		
		return iArray;
	}

}
