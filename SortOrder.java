import java.util.Comparator;

/*
 * This class contains a method that will order the array list in alphabetical order. 
 * 
 */

public class SortOrder implements Comparator<Variable> {

	@Override
	public int compare(Variable o1, Variable o2) {
		// TODO Auto-generated method stub
		return o1.getVarName() - o2.getVarName();
	}

}
