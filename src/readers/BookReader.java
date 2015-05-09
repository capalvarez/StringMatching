package readers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class BookReader {
	String oneLinebook;
	
	
	public BookReader(File bookFile) throws IOException{
		BufferedReader br = new BufferedReader(new FileReader(bookFile));    
		
		readFile(br);
	}
	
	private void readFile(BufferedReader br) throws IOException{
		 try{
			 StringBuilder builder = new StringBuilder();
		     String line = br.readLine();

		     while (line != null){
		    	 builder.append(line);
		    	 builder.append(" ");
		         line = br.readLine();
		     }
		     
		     oneLinebook = builder.toString();  
		 }finally{
			 br.close();
		 }	
	}
	
}
