/**
 * Created by brando on 6.1.2016.
 */
public class Type {
    private String type;
    private String llvm_type;
    private int rec;
    private String stars;
    private String brackets;

    public String getLlvm_type() {
        return llvm_type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        setType(type, 0);
    }
    public void setType(String type, int rec) {
        String stars = "";
        String brackets = "";
        for(int i=0; i < rec; i++) {
            stars += "*";
            brackets += "[]";
        }

        this.type = type;
        if(type.equals("int")) {
            this.llvm_type = "i32";
        } else if(type.equals("char")) {
            this.llvm_type = "i8";
        } else if(type.equals("bool")) {
            this.llvm_type = "i1";
        } else {
            throw new RuntimeException("Unknown Variable type: " + type);
        }

        this.brackets += brackets;
        this.stars = stars;
        this.rec = rec;
    }

    @Override public String toString() {
        return getLlvm_type();
    }

    public int getRec() {
        return rec;
    }

    public String getStars() {
        return stars;
    }

    public String getBrackets() {
        return brackets;
    }

    public Type() {}

    public Type(String type) {
        setType(type);
    }

    public Type(String type, int rec) {
        setType(type, rec);
    }
}
