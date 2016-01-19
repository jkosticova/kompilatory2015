import java.util.List;

public class Function {
    private String name;
    private Type returnType;
    private List<Type> arg_list;
    private Code code;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Type getReturnType() {
        return returnType;
    }

    public void setReturnType(Type returnType) {
        this.returnType = returnType;
    }

    public List<Type> getArg_list() {
        return arg_list;
    }

    public void setArg_list(List<Type> arg_list) {
        this.arg_list = arg_list;
    }

    public Code getCode() {
        return code;
    }

    public void setCode(Code code) {
        this.code = code;
    }

    public Function(String name, Type returnType, List<Type> arg_list) {
        this.name = name;
        this.returnType = returnType;
        this.arg_list = arg_list;
    }

    public Function(String name, Type returnType, List<Type> arg_list, Code code) {
        this.name = name;
        this.returnType = returnType;
        this.arg_list = arg_list;
        this.code = code;
    }
}
