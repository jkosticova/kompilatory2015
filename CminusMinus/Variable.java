
public class Variable {
    public Type type;
    public String register;
    public int rozmer;

    public Variable(){
        type = Type.INT;
        register= "";
        rozmer =0;
    }

    public Variable(Type t, String reg){
        this.type=t;
        this.register=reg;
        rozmer =0;
    }



    public int getRozmer(){
        return this.rozmer;
    }

    public void setRozmer(int x){
        this.rozmer =x;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type t){
        this.type=t;
    }

    public String getRegister() {
        return this.register;
    }

    public void setRegister(String reg) {
        this.register=reg;
    }

    public boolean isArray(){
        return this.type==Type.ARRAY_INT || this.type==Type.ARRAY_STR;
    }



    public String getCode() {
        if(this.type == Type.INT || this.type == Type.ARRAY_INT)
            return "i32";
        else
            return "i8*";
    }
}
