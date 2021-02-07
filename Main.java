import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	static ArrayList<Character> listOfChar = new ArrayList<Character>();
	static ArrayList<Clause> clauseList = new ArrayList<Clause>();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// this is a change!

		try {
			File InputFile = new File(args[0]);
			Scanner readFile = new Scanner(InputFile);
			System.out.println("File open was a Success!");
			textProcess(readFile);

			System.out.print("Contents of Arraylist: ");
			for (int i = 0; i < listOfChar.size(); i++) {
				System.out.print(listOfChar.get(i));
			}

		}

		catch (FileNotFoundException fnfe) {
			System.err.println("Cannot open file");
		}
	}

	public static void checkChar(char c) {

		if (listOfChar.contains(c)) {
			System.out.println("Char " + c + " already exists in array");
		} else
			listOfChar.add(c);
	}

	public static void textProcess(Scanner readFile) {

		int varCount;
		String line = "";
		char c;
		while (readFile.hasNext()) {
			line += readFile.nextLine();
			line = line.replaceAll("\\s+", "");
		}

		System.out.println("Current Clause: " + line);

		Boolean boolX;
		Boolean boolY;
		Boolean boolZ;
		char x;
		char y;
		char z;

		for (int i = 0; i < line.length(); i++) {
			c = line.charAt(i);

			varCount = 0;

			boolX = true;
			boolY = true;
			boolZ = true;

			x = ' ';
			y = ' ';
			z = ' ';

			if (c == '(') {

				i++;
				c = line.charAt(i);
				while (varCount < 3) {

					if ((c <= 'z') && (c >= 'a')) {
						checkChar(c);
						varCount++;
						System.out.println("Variable Count " + varCount);

						if (varCount == 1) {
							x = c;
						} else if (varCount == 2) {
							y = c;
						} else
							z = c;

						i++;
						c = line.charAt(i);

						if (c == '\'') {
							if (varCount == 1) {
								boolX = false;
							} else if (varCount == 2) {
								boolY = false;
							} else
								boolZ = false;
							i++;
							c = line.charAt(i);

						}
						if (c == '+') {
							i++;
							c = line.charAt(i);
						} else {
							// error handling & exit due to invalid expression. If c is a lettter

						}
					} else {
						// error handling for invalid variable & exit program
					}

				}

				if (c == ')') {
					// Initialize clause
					// add clause to clause list
					Clause clause = new Clause (x, y, z, boolX, boolY, boolZ);
					clauseList.add(clause);
				}
			}
			// error handle otherwise

		}

	}
}
