package preprocessing;

public class BookPreProcess {
	String finishedBook;
	
	public BookPreProcess(String book){
		preprocess(book);
	}
	
	private void preprocess(String book){
		book = book.toLowerCase();
		
		String[] forbiddenSym = {"á","é","í","ó","ú","ñ"};
		String[] replacement = {"a","e","i","o","u","n"};
		for(int i=0; i<forbiddenSym.length;i++){
			book = book.replace(""+forbiddenSym[i],replacement[i]);		  
		}
		
		String[] punctuation = {".",",",";"};
		for(int i=0; i<punctuation.length;i++){
			book = book.replace(""+punctuation[i],"");		  
		}
		
	}
	
	public String getProcessedBook(){
		return finishedBook;
	}
	
}
