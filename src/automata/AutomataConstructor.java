package automata;

public class AutomataConstructor {
	private String pattern;
	private char[] alphabet;
	private int[][] deltaFunction;
	
	public AutomataConstructor(String p, char[] alphabet){
		pattern = p;
		this.alphabet = alphabet;
	
		cubicConstructor();
	}
	
	public void cubicConstructor(){
		int m = pattern.length();
		deltaFunction = new int[alphabet.length][m];
		
		for(int q=0;q<m;q++){
			for(char a: alphabet){
				int k = Math.min(m+1,q+2);
				
				String Pk;
				String Pq;
				
				do{
					k--;
					Pk = pattern.substring(0,k+1);
					Pq = pattern.substring(0,q+1);
				}while(Pk.endsWith(Pq + a));
			
				deltaFunction[q][a] = k;
			}
		}
	}

	public void linearConstructor(){
		
	}
	
	public int[][] getDeltaFunction(){
		return deltaFunction;
	}
}
