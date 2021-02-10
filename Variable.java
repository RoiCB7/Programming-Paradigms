/*
 * This is the class that holds the variable structure
 * which is then stored in the array list created in main.
 * It holds the getters and setters for the variable structure
 * as well as the constructor.
 *
 */


public class Variable {

    private final char varName;
    private Boolean truthAsg;
    private Boolean firstRun;

    public Variable(char varName) {
        super();
        this.varName = varName;
        this.firstRun = true;
    }

    public Boolean getTruthAsg() {
        return truthAsg;
    }

    public void setTruthAsg(Boolean truthAsg) {
        this.truthAsg = truthAsg;
    }

    public char getVarName() {
        return varName;
    }

    public Boolean getRun() {
        return firstRun;
    }

    public void setRun(Boolean run) {
    	this.firstRun = run;
    }
}
