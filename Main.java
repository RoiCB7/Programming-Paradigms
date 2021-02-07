import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	 static ArrayList<Character> listOfChar = new ArrayList<Character>();
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	    //this is a change!

		try {
		File InputFile = new File (args[0]);
		Scanner readFile = new Scanner (InputFile);
		System.out.println("Success!");
		textProcess(readFile);
		
		System.out.print("Contents of Arraylist: ");
		for(int i =0; i < listOfChar.size(); i++) {
			System.out.print(listOfChar.get(i));
		}
		
		}

		catch (FileNotFoundException fnfe) {
			System.err.println("Cannot open file");
		}
	}
	
	
	public static void checkChar (char c){
		
		if (listOfChar.contains(c)) {
			System.out.println("Char " + c + " already exists in array");
		}
		else
			listOfChar.add(c);	
	}

	public static void textProcess (Scanner readFile) {

		
		int varCount = 0;
		String line="";
		char c;
		while (readFile.hasNext()) {
			line += readFile.nextLine();
			line = line.replaceAll("\\s+","");
		}
		
		System.out.println(line);
		
		for (int i = 0; i < line.length(); i++) {
			c = line.charAt(i);

			if ((c <= 'z') && (c >= 'a')) {
		    	checkChar(c);
		    	varCount++;
		    	System.out.println(varCount);
		    	
			}
				
				
		}
		
	}
	
}
