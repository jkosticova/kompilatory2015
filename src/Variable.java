/**
 * Created by brando on 5.1.2016.
 */
public class Variable {
    private String name;
    private String register;
    private Type type = new Type();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegister() {
        return register;
    }

    public void setRegister(String register) {
        this.register = register;
    }

    public Type getType() {
        return type;
    }

    public void setType(String type) {
        this.type.setType(type);
    }

    public Variable(String name, String register, String type) {
        this.name = name;
        this.register = register;
        this.setType(type);
    }

    public Variable(String name, String register, Type type) {
        this.name = name;
        this.register = register;
        this.type = type;
    }
}
