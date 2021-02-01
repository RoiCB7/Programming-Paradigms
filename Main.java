import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
		try {
		File textFile = new File (args[0]); 
		Scanner readText = new Scanner (textFile);
		System.out.println("Success!");
		}
		
		catch (FileNotFoundException fnfe) {
			System.err.println("Cannot open file");
		}
	}
	
	public static void textProcess (Scanner readText) {
		
		
	}

}
