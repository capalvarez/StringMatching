package preprocessing;

public class BookPreProcess {
	String finishedBook;
	
	public BookPreProcess(String book){
		preprocess(book);
	}
	
	private void preprocess(String book){
		book = book.toLowerCase();
		
		String[] forbiddenSym = {"�","�","�","�","�","�"};
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
