import java.util.Collections;

public class Variable {
    private String register;
    private VariableType variableType;
    private int arrayDepth = 0;

    public Variable(String register, VariableType vType) {
        this.register = register;
        this.variableType = vType;
    }

    public Variable() {
        this.register = "";
        this.variableType = VariableType.STRING;
    }

    public static Variable fromReadableString(String wut) {
        Variable var = new Variable();
        switch (wut) {
            case "\\\\":
                var.setVariableType(VariableType.STRING);
                break;
            case "\\/":
                var.setVariableType(VariableType.INT);
                break;
            case "\\-":
                var.setVariableType(VariableType.VOID);
                break;
        }
        boolean isCorrect = wut.startsWith("\\\\[]");
        for (int i = 4; i < wut.length(); i++) {
            int j = i % 2;
            char t = j == 0 ? '[' : ']';
            isCorrect = isCorrect && (wut.charAt(i) == t);
        }

        if (isCorrect) {
            var.setVariableType(VariableType.ARRAY_OF_STRING);
            var.setArrayDepth((wut.length() - 2) / 2);
        }

        isCorrect = wut.startsWith("\\/[]");
        for (int i = 4; i < wut.length(); i++) {
            int j = i % 2;
            char t = j == 0 ? '[' : ']';
            isCorrect = isCorrect && (wut.charAt(i) == t);
        }

        if (isCorrect) {
            var.setVariableType(VariableType.ARRAY_OF_INT);
            var.setArrayDepth((wut.length() - 2) / 2);
        }
        return var;
    }

    public VariableType getVariableType() {
        return variableType;
    }

    public void setVariableType(VariableType variableType) {
        this.variableType = variableType;
    }

    public String getRegister() {
        return register;
    }

    public void setRegister(String register) {
        this.register = register;
    }

    public int getArrayDepth() {
        return arrayDepth;
    }

    public void setArrayDepth(int arrayDepth) {
        this.arrayDepth = arrayDepth;
    }

    public String toReadableString() {
        return getVariableType().toReadableString() + String.join("", Collections.nCopies(getArrayDepth() - 1, "[]"));
    }

    public String toLLVMTypeString() {
        return getVariableType().toString() + (getArrayDepth() > 0 ? String.join("", Collections.nCopies(getArrayDepth() - 1, "*")) : "");
    }

    public String toLLVMString() {
        return toLLVMTypeString() + " " + this.getRegister();
    }
}
