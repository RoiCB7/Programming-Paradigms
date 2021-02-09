import java.util.ArrayList;

public class Dpll {


	public Boolean solver(ArrayList<Variable> listOfChar, ArrayList<Clause> clauseList, Boolean truthVal) {
		
		ArrayList<Clause> clauseStorage = new ArrayList<Clause>();
		
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
					//clauseList.remove(clause);
					isSolved = true;

				}

			}

			if (!isSolved && clause.getVar_b() == listOfChar.get(0).getVarName()) {

				check = clause.isBool_b();

				if (check == listOfChar.get(0).getTruthAsg()) {
					clauseStorage.add(clause);
					//clauseList.remove(clause);
					isSolved = true;
				}
			}

			if (!isSolved && clause.getVar_c() == listOfChar.get(0).getVarName()) {

				check = clause.isBool_c();

				if (check == listOfChar.get(0).getTruthAsg()) {
					clauseStorage.add(clause);
					//clauseList.remove(clause);
					isSolved = true;
				}
			}						

			// don't want to process the clause after we low truth assignment solves it.

		}
		
		clauseList.removeAll(clauseStorage);
		// append clauseStorage with clauseList, restoring removed clauses. Then flip
		// truth values.

		// save variable, and in worst case append pair variable to the start of the
		// listOfChar

		// This point: We would remove variable from listofChar
		Variable varStorage = listOfChar.get(0);
		listOfChar.remove(0);
		
		Boolean success = solver(listOfChar, clauseList, truthVal);
	

	
		 if (!success && varStorage.getRun() == true) {
	
		
			listOfChar.add(0, varStorage);
			clauseList.addAll(clauseStorage);

			listOfChar.get(0).setRun(false);

			//If it's a char first run
			//flip that char run to false
			//set other chars in list to first run
			
			for (int i = 1; i < listOfChar.size(); i++) {
				listOfChar.get(i).setRun(true);
			}

			success =  solver(listOfChar, clauseList, !truthVal);

		}
		 
		 if (!listOfChar.contains(varStorage)) {
			 listOfChar.add(0, varStorage);
			 
		 }
		 
		 return success;
		
	}

}
