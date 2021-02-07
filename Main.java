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

		String line = readFile.nextLine();
		char c;
		for (int i = 0; i < line.length(); i++) {
			c = line.charAt(i);

			if (c == '(') {
				System.out.println("Found One");
				i++;
				while (i < line.length()) {
				    if ((c <= 'z') && (c >= 'a')) {
				    	checkChar(c);
				    }
				}
			}
		}
		
				
	}
	
	public String toString() {
		return listOfChar.toString();		
	}
	
	
}
