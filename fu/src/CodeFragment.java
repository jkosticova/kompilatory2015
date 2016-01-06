public class CodeFragment {
    private String code;
    private String functions;
    private Variable variable;

    public CodeFragment() {
        this.code = "";
        this.functions = "";
        this.variable = new Variable();
    }

    public void addCode(String code) {
        this.code += code;
    }

    public void addCode(CodeFragment fragment) {
        this.code += fragment.toString();
    }

    public String toString() {
        return this.code;
    }

    public String getFunctions() {
        return this.functions;
    }

    public void addFunctionsCode(CodeFragment fragment) {
        this.functions += fragment.toString();
    }

    public void addFunctionsCode(String string) {
        this.functions += string;
    }

    public Variable getVariable() {
        return variable;
    }

    public void setVariable(Variable variable) {
        this.variable = variable;
    }

}
