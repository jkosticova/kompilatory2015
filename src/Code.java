/**
 * Created by brando on 5.1.2016.
 */
public class Code {
    private String code = "";
    private String memRegister;
    private Type memRegisterType;

    void addCode(String newCode) {
        code += newCode;
    }

    void addCode(Code code) {
        if (code == null || code.getCode().equals("")) {
            //System.out.println("NULL CODE");
            return;
        }
        this.addCode(code.getCode());
        this.memRegister = code.getMemRegister();
        this.memRegisterType = code.getMemRegisterType();
    }

    String getCode() {
        return code;
    }

    public String getMemRegister() {
        return memRegister;
    }

    public Type getMemRegisterType() {
        return memRegisterType;
    }

    public void setMemRegister(String memRegister, Type type) {
        this.memRegister = memRegister;
        this.memRegisterType = type;
    }

    @Override public String toString() {
        return code;
    }
    public Code() {
        this("");
    }
    public Code(String code) {
        this.code = code;
    }

    public Code(String code, String memRegister, Type type) {
        this.code = code;
        this.memRegister = memRegister;
        this.memRegisterType = type;
    }
}
