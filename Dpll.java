import java.util.ArrayList;

public class Dpll {

    public Boolean solver (ArrayList<Variable> listOfChar, ArrayList<Clause> clauseList, Boolean truthVal) {
        char c;

        if (clauseList.isEmpty()) {

        return true;
        }

        if (listOfChar.isEmpty()) {

        return false;
        }

        listOfChar.get(0).setTruthAsg(truthVal);

        //if character matches a character in the clause, then we work with it

        for (Clause clause : clauseList) {




        }

        return false;
    }

}
