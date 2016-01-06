import com.sun.org.apache.bcel.internal.classfile.Code;

import java.util.ArrayList;
import java.util.List;

public class Functions{
    public int argSize;
    public List<Variable> arguments;
    public Type type;
    public CodeFragment code;

    public Functions(){
        argSize=0;
        arguments=new ArrayList<>();
        type=Type.INT;
        code = new CodeFragment();
    }

    public Functions(int s, List<Variable> args, Type t){
        argSize=s;
        arguments=args;
        type=t;
    }

    public void setCode(CodeFragment code) {
        this.code = code;
    }

    public CodeFragment getCode() {
        return code;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Type getType() {
        return type;
    }

    public int getSize() {
        return argSize;
    }

    public List<Variable> getArguments() {
        return arguments;
    }

    public void setSize(int size) {
        this.argSize = size;
    }

    public void addArguments(Variable x){
        this.arguments.add(x);
    }

    public String getLlvmType(){
        if(type.equals(Type.INT) || type.equals(Type.ARRAY_INT))
            return "i32";
        else
            return "i8*";
    }
}
