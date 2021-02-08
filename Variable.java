
public class Variable {

    private final char varName;
    private Boolean truthAsg;

    public Variable(char varName) {
        super();
        this.varName = varName;
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
}
