import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
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
            //System.out.println("File open was a Success!");
            textProcess(readFile);
            System.out.println("File open was a Success!");
           Boolean answer =  solve.solver(listOfChar, clauseList, true);
           System.out.println("This is the answer: " + answer);
            /*
            System.out.print("Contents of Arraylist: ");
            for (int i = 0; i < listOfChar.size(); i++) {
                System.out.println(listOfChar.get(i));
            }

            System.out.println("Contents of Clauselist: ");
            for (int i = 0; i < clauseList.size(); i++) {
                System.out.print(clauseList.get(i).getVar_a());
                System.out.print(clauseList.get(i).getVar_b());
                System.out.println(clauseList.get(i).getVar_c());
                System.out.print(clauseList.get(i).isBool_a());
                System.out.print(clauseList.get(i).isBool_b());
                System.out.print(clauseList.get(i).isBool_c());
            }*/

        }

        catch (FileNotFoundException fnfe) {
            System.err.println("Cannot open file");
        }
    }

    public static void checkChar(char c) {

        Variable newVar = new Variable(c);

        if (listOfChar.contains(newVar)) {
           // System.out.println("Char " + newVar.getVarName() + " already exists in array");
        } else

            listOfChar.add(newVar);
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
                     //   System.out.println("Variable Count " + varCount);

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
                            //    System.out.println("Expression is invalid 1");
                             //   System.exit(0);
                                // error handling & exit due to invalid expression. If c is a lettter

                            }
                        }
                    } else {
                      //  System.out.println("Expression is invalid 2");
                      //  System.exit(0);
                        // error handling for invalid variable & exit program
                    }

                }

                if (c == ')') {
                    // Initialize clause
                    // add clause to clause list
                    Clause clause = new Clause(x, y, z, boolX, boolY, boolZ);
                    clauseList.add(clause);

                }
            }
            // error handle otherwise

        }

    }
}
