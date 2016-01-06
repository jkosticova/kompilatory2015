public enum VariableType {
    VOID, STRING, INT, ARRAY_OF_STRING, ARRAY_OF_INT;

    public String toReadableString() {
        switch (this) {
            case VOID:
                return "VOID";
            case STRING:
                return "\\\\";
            case INT:
                return "\\/";
            case ARRAY_OF_STRING:
                return "\\\\[]";
            case ARRAY_OF_INT:
                return "\\/[]";
        }
        return "";
    }

    @Override
    public String toString() {
        switch (this) {
            case VOID:
                return "void";
            case STRING:
                return "i8*";
            case INT:
                return "i32";
            case ARRAY_OF_STRING:
                return "i8**";
            case ARRAY_OF_INT:
                return "i32*";
        }
        return "";
    }
}
