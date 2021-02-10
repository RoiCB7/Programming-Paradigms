import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {
	static ArrayList<Variable> listOfChar = new ArrayList<Variable>();
	static ArrayList<Clause> clauseList = new ArrayList<Clause>();
	static Dpll solve = new Dpll();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// this is a change!

		try {
			File InputFile = new File(args[0]);
			Scanner readFile = new Scanner(InputFile);
			textProcess(readFile);
			System.out.println("File open was a Success!");
			Boolean answer = solve.solver(listOfChar, clauseList, true);

			Collections.sort(listOfChar, new SortOrder());

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

				if (i == line.length()) {
					System.out.println("Invalid formula");
					System.exit(0);
				}

				c = line.charAt(i);

				while (varCount < 3) {

					if ((c <= 'z') && (c >= 'a')) {
						checkChar(c);
						varCount++;
						// System.out.println("Variable Count " + varCount);

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

						if (varCount < 3) {

							if (c == '+') {
								i++;
								c = line.charAt(i);
							} else {
								System.out.println("Invalid formula");
								System.exit(0);
								// error handling & exit due to invalid expression. If c is a lettter

							}
						}
					} else {
						System.out.println("Invalid formula");
						System.exit(0);
						// error handling for invalid variable & exit program
					}

				}

				if (c == ')') {
					// Initialize clause
					// add clause to clause list

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
			// error handle otherwise

		}

	}
}
