/*
 * 
 * This class contains the details of a single Clause. Assuming the clause is in 3CNF format.
 * Three variables and three corresponding truth assignments. 
 * 
 * 
 */
public class Clause {
    private final char var_a;
    private final char var_b;
    private final char var_c;
    private final boolean bool_a;
    private final boolean bool_b;
    private final boolean bool_c;

    public Clause(char var_a, char var_b, char var_c, boolean bool_a, boolean bool_b, boolean bool_c) {
        this.var_a = var_a;
        this.var_b = var_b;
        this.var_c = var_c;
        this.bool_a = bool_a;
        this.bool_b = bool_b;
        this.bool_c = bool_c;

    }

    public char getVar_a() {
        return var_a;
    }

    public char getVar_b() {
        return var_b;
    }

    public char getVar_c() {
        return var_c;
    }

    public boolean isBool_a() {
        return bool_a;
    }

    public boolean isBool_b() {
        return bool_b;
    }

    public boolean isBool_c() {
        return bool_c;
    }

}
