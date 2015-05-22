package preprocessing;

import java.io.File;
import java.io.IOException;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Arrays;

import readers.BookReader;

public class BookPreProcess {
	private String finishedBook;
	private ArrayList<Character> forbidden;
	
	public BookPreProcess(String book){
		char[] forb = "@.,;:-_|°¬!#$%&/()=\t\b\n\'\"\\?¡¿+*~[]{}^-_—".toCharArray();
		forbidden = new ArrayList<Character>(Arrays.asList(toCharacter(forb)));	
		
		preprocess(book);	
	}
	
	private void preprocess(String book){
		book = book.toLowerCase();
		book = Normalizer.normalize(book, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
				
		ArrayList<Character> bookChar = new ArrayList<Character>(Arrays.asList(toCharacter(book.toCharArray())));
			
		for(int i=0; i<bookChar.size();i++){

			if(forbidden.contains(bookChar.get(i))){
				bookChar.set(i,null);
			}
		}
						
		finishedBook = getString(bookChar);
	}
	
	public String getProcessedBook(){
		return finishedBook;
	}
	
	public Character[] toCharacter(char[] array){
		Character[] newArray = new Character[array.length];
		
		for(int i=0;i<array.length;i++){
			newArray[i] = (Character) array[i];
		}
		
		return newArray;
	}
	
	public String getString(ArrayList<Character> array){
		StringBuilder sb = new StringBuilder(array.size());
	
		for (Character c : array)
			if(c!=null){
				sb.append(c.charValue());
			}
	
		return sb.toString();
		
	}
	
	public static void main(String[] args) throws IOException{
		BookReader bR = new BookReader(new File("./data/ejemplo.txt"));
		BookPreProcess bPP = new BookPreProcess(bR.getBook());
		
		System.out.println(bPP.getProcessedBook());
		
	}
}
