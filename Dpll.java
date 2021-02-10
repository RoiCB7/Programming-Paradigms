import java.util.ArrayList;

/*Class Name: DPLL
 * This class will be used to solve the given formula.
 * It only contains one method, solver.
 * 
 */
public class Dpll {

	/*
	 * Method: solver
	 * 
	 * Details: This method will take in a list of variables and a list of Clauses,
	 * along with the assigned truth value. It will will work by eliminating clauses
	 * that have been solved and storing them in a back up list, called
	 * clauseStorage. If the program needs to backtrack at any point, it will
	 * restore the clauseList to it's original order from clausesStorage. It will
	 * perform much of the same function with the list of variables in listOfChar.
	 * Storing eliminated variables in a local variable called varStorage. If it 
	 * finds a satisfiable solution, it will return true.
	 * 
	 * Input: An array list of variables (listOfChar), an ArrayList of clauses (clauseList) and a
	 * truth value (truthVal).
	 * 
	 * 
	 */

	public Boolean solver(ArrayList<Variable> listOfChar, ArrayList<Clause> clauseList, Boolean truthVal) {

		ArrayList<Clause> clauseStorage = new ArrayList<Clause>();

		if (clauseList.isEmpty()) {

			return true;
		}

		if (listOfChar.isEmpty()) {

			return false;
		}

		listOfChar.get(0).setTruthAsg(truthVal);

		Boolean check;
		Boolean isSolved;

		for (Clause clause : clauseList) {

			isSolved = false;

			if (clause.getVar_a() == listOfChar.get(0).getVarName()) {

				check = clause.isBool_a();

				if (check == listOfChar.get(0).getTruthAsg()) {
					clauseStorage.add(clause);
					isSolved = true;

				}

			}

			if (!isSolved && clause.getVar_b() == listOfChar.get(0).getVarName()) {

				check = clause.isBool_b();

				if (check == listOfChar.get(0).getTruthAsg()) {
					clauseStorage.add(clause);
					isSolved = true;
				}
			}

			if (!isSolved && clause.getVar_c() == listOfChar.get(0).getVarName()) {

				check = clause.isBool_c();

				if (check == listOfChar.get(0).getTruthAsg()) {
					clauseStorage.add(clause);
					isSolved = true;
				}
			}

		}

		clauseList.removeAll(clauseStorage);

		Variable varStorage = listOfChar.get(0); // save variable.
		listOfChar.remove(0);

		Boolean success = solver(listOfChar, clauseList, truthVal);

		if (!success && varStorage.getRun() == true) {

			listOfChar.add(0, varStorage);
			clauseList.addAll(clauseStorage); // append clauseStorage with clauseList, restoring removed clauses. Then
											  // flip truth values.

			// If it's a var first run
			// flip that var run to false
			// set other var in list to first run

			listOfChar.get(0).setRun(false);

			for (int i = 1; i < listOfChar.size(); i++) {
				listOfChar.get(i).setRun(true);
			}

			success = solver(listOfChar, clauseList, !truthVal);

		}

		if (!listOfChar.contains(varStorage)) {
			listOfChar.add(0, varStorage);

		}

		return success;

	}

}
