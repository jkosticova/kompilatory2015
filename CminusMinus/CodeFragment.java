public class CodeFragment {
    private String code;
    private Variable var;

    public CodeFragment() {
        this.code = "";
        this.var = new Variable();
    }

    public Type getType() {
        return var.getType();
    }

    public void setType(Type type){
        var.setType(type);
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

    public void setRegister(String register) {
        this.var.setRegister(register);
    }

    public String getRegister() {
        return this.var.getRegister();
    }

    public String getLlvmCode() {
        return this.var.getCode();
    }
}
