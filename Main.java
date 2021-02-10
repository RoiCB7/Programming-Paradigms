import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
/* Names: Luke Cating, Josh Lewis, Chris Botuli
 * Emails: icati243@mtroyal.ca, jlewi359@mtroyal.ca, cbotu861@mtroyal.ca
 * 
 * The program will take in 3CNF formula and either provide a truth assignment that will attempt to solve
 * the formula. It will respond with a 'satisfiable' if it finds a valid truth assignment, along with a list of
 * the variable assignments that satisfy the formula. It will respond will unsatisfiable if it can't find 
 * valid variable truth assignments. 
 * 
 * It will respond if the formula is in an invalid format.
 * 
 * Limitations: 3cnf09 will give the wrong answer. 
 * 
 */

public class Main {
	static ArrayList<Variable> listOfChar = new ArrayList<Variable>();
	static ArrayList<Clause> clauseList = new ArrayList<Clause>();
	static Dpll solve = new Dpll();

	public static void main(String[] args) {

		try {
			File InputFile = new File(args[0]);
			Scanner readFile = new Scanner(InputFile);
			textProcess(readFile);
			Boolean answer = solve.solver(listOfChar, clauseList, true);

			Collections.sort(listOfChar, new SortOrder());	//sort Arraylist in alphabetical order

			if (answer == true) {

				System.out.print("satisfiable by [");

				for (int i = 0; i < listOfChar.size(); i++) {

					if (listOfChar.get(i).getTruthAsg() != null) {

						if (i > 0) {
							System.out.print(", ");
						}

						System.out.print(listOfChar.get(i).getVarName() + "=");
						System.out.print(listOfChar.get(i).getTruthAsg());

					}
				}
				System.out.println("]");

			} else
				System.out.println("unsatisfiable");

		}

		catch (FileNotFoundException fnfe) {
			System.err.println("Cannot open file");
		}
	}

	/*
	 * Method Name: checkChar Details: checkChar will take in a character from the
	 * input turned String. It will create a new variable based off that character.
	 * It will only add to the listOfChar that stores variables if it is either
	 * empty, or if it doesn't already exist in the list.
	 * 
	 * Input: char c
	 * 
	 * Returns: void
	 * 
	 */

	public static void checkChar(char c) {

		Variable newVar = new Variable(c);

		if (listOfChar.isEmpty()) {
			listOfChar.add(newVar);
		} else {
			for (Variable v : listOfChar) {
				if (v.getVarName() == c) {
					return;
				}
			}

			listOfChar.add(newVar);
		}

	}

	/*
	 * Method Name: textProcess Details: This method will handle the processing and
	 * parsing of the formula. It will begin with turning the file into a String,
	 * and eliminate any spaces. It will then run the length of the String and using
	 * a char variable, will pick up and store all valid characters. It will assign
	 * a false value to characters with a "'". Once the char has reached a point in
	 * the String that contains a closing bracket, provided it has passed all error
	 * handling, it will then consider it a clause and add it to the clause list.
	 * 
	 * Input: Scanner readFile
	 * 
	 * Returns: void
	 * 
	 */

	public static void textProcess(Scanner readFile) {

		int varCount;
		String line = "";
		char c;
		while (readFile.hasNext()) {
			line += readFile.nextLine();
			line = line.replaceAll("\\s+", "");
		}

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

				if (i == line.length()) {
					System.out.println("Invalid formula");
					System.exit(0);
				}

				c = line.charAt(i);

				while (varCount < 3) {

					if ((c <= 'z') && (c >= 'a')) {
						checkChar(c);
						varCount++;

						if (varCount == 1) {
							x = c;
						} else if (varCount == 2) {
							y = c;
						} else
							z = c;

						i++;
						c = line.charAt(i);

						if (c == '\'') { // Checking for not or "'".
							if (varCount == 1) {
								boolX = false;
							} else if (varCount == 2) {
								boolY = false;
							} else
								boolZ = false;
							i++;
							c = line.charAt(i);

						}

						if (varCount < 3) {

							if (c == '+') {
								i++;
								c = line.charAt(i);
							} else {
								System.out.println("Invalid formula");
								System.exit(0);

							}
						}
					} else {
						System.out.println("Invalid formula");
						System.exit(0);

					}

				}

				if (c == ')') {

					Clause clause = new Clause(x, y, z, boolX, boolY, boolZ);
					clauseList.add(clause);

				} else {
					System.out.println("Invalid formula");
					System.exit(0);
				}
			} else {
				System.out.println("Invalid formula");
				System.exit(0);
			}

		}

	}
}
