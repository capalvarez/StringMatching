package automata;

import java.util.ArrayList;

public class AutomataConstructor {
	private String pattern;
	private ArrayList<Character> alphabet;
	private int[][] deltaFunction;

	
	public AutomataConstructor(String p, ArrayList<Character> alphabet){
		pattern = p;
		this.alphabet = alphabet;
	
		cubicConstructor();		
	}
	
	public void cubicConstructor(){
		int m = pattern.length();
		deltaFunction = new int[m+1][alphabet.size()];
		
		for(int q=0;q<=m;q++){
			for(char a: alphabet){
				int k = Math.min(m+1,q+2);
								
				String Pk;
				String Pq;
				
				do{
					k = k - 1;
					
					Pk = pattern.substring(0,k);
					Pq = pattern.substring(0,q);
				}while(!(Pq + a).endsWith(Pk));
			
				deltaFunction[q][alphabet.indexOf(a)] = k;
			}
		}
	}

	public void linearConstructor(){
		
	}
	
	public int[][] getDeltaFunction(){
		return deltaFunction;
	}
}
