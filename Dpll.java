import java.util.ArrayList;

public class Dpll {

	ArrayList<Clause> clauseStorage;

	public Boolean solver(ArrayList<Variable> listOfChar, ArrayList<Clause> clauseList, Boolean truthVal) {
		char c;

		if (clauseList.isEmpty()) {

			return true;
		}

		if (listOfChar.isEmpty()) {

			return false;
		}

		listOfChar.get(0).setTruthAsg(truthVal);

		// Need to check clause that contains a specific var, assign a truth value to
		// it, see if it solves
		// any clauses. If it does, remove those clauses.

		// if character matches a character in the clause, then we work with it

		Boolean check;
		Boolean isSolved;

		for (Clause clause : clauseList) {

			isSolved = false;

			if (clause.getVar_a() == listOfChar.get(0).getVarName()) {

				// check truth value

				check = clause.isBool_a();

				if (check == listOfChar.get(0).getTruthAsg()) {
					// means clause is good? I.E remove clause from clause list
					// add to clause Storage
					clauseStorage.add(clause);
					clauseList.remove(clause);
					isSolved = true;

				}

			}

			if (!isSolved && clause.getVar_b() == listOfChar.get(0).getVarName()) {

				check = clause.isBool_b();

				if (check == listOfChar.get(0).getTruthAsg()) {
					clauseStorage.add(clause);
					clauseList.remove(clause);
					isSolved = true;
				}
			}

			if (!isSolved && clause.getVar_c() == listOfChar.get(0).getVarName()) {

				check = clause.isBool_c();

				if (check == listOfChar.get(0).getTruthAsg()) {
					clauseStorage.add(clause);
					clauseList.remove(clause);
					isSolved = true;
				}
			}

			// don't want to process the clause after we low truth assignment solves it.

		}
		// append clauseStorage with clauseList, restoring removed clauses. Then flip
		// truth values.

		// save variable, and in worst case append pair variable to the start of the
		// listOfChar

		// This point: We would remove variable from listofChar
		Variable varStorage = listOfChar.get(0);
		listOfChar.remove(0);

		Boolean success = solver(listOfChar, clauseList, truthVal);

		if (success == true) {
			return success;
		}

		else {

			// add back char and add back clauses
			// toggle truth values; truthVal
			// then recurse

			// append clauseStorage to start of clauseList or add clauseStorage to end of
			// clauseList
			
			listOfChar.add(0, varStorage);
			clauseList.addAll(clauseStorage);
			
			return solver(listOfChar, clauseList, !truthVal);

		}

		
	}

}
