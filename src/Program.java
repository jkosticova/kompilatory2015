import java.util.*;

public class Program {
    private Code constants = new Code();
    private Map<String, Function> functions = new LinkedHashMap<>();

    private Map<String, Variable> globalContext = new LinkedHashMap<>();
    private List<List<Map<String, Variable>>> functionContexts = new ArrayList<>();


    public void addConstant(Code code) {
        constants.addCode(code);
    }

    public Code getConstants() {
        return constants;
    }

    public Function getFunction(String name, Position p) {
        if(!functions.containsKey(name)) {
            throw new RuntimeException("Uknown function " + name + " at " + p);
        }
        return functions.get(name);
    }

    public List<Function> getFunctions() {
        return new ArrayList(functions.values());
    }

    public void addFunction(Function f, Position p) {
        if(functions.containsKey(f.getName())) {
            throw new RuntimeException("Function declared more than once at " + p.toString());
        } else {
            functions.put(f.getName(), f);
        }
    }

    public Variable getVariable(String name, Position p) {
        if (functionContexts.size() != 0) {
            List<Map<String, Variable>> lastFunctionContext = functionContexts.get(functionContexts.size() - 1);
            for(int i=lastFunctionContext.size() - 1; i >= 0; i--) {
                Map<String, Variable> actualContext = lastFunctionContext.get(i);
                if (actualContext.containsKey(name)) {
                    return actualContext.get(name);
                }
            }
        }
        if (globalContext.containsKey(name)) {
            return globalContext.get(name);
        }
        throw new RuntimeException("Unknown variable " + name + " at " + p.toString());
    }

    /**
     * Must be called right after adding argument list.
     * @return
     */
    public List<Type> getFunctionVariablesTypes() {
        List<Type> vars = new ArrayList<>();
        List<Map<String, Variable>> actualfunctionContexts = functionContexts.get(functionContexts.size() - 1);
        Map<String, Variable> actualContext = actualfunctionContexts.get(actualfunctionContexts.size() - 1);
        for(Variable var : actualContext.values()) {
            vars.add(var.getType());
        }
        return vars;
    }

    /**
     * Must be called right after adding argument list.
     * @return
     */
    public List<Variable> getFunctionVariables() {
        List<Variable> vars = new ArrayList<>();
        List<Map<String, Variable>> actualfunctionContexts = functionContexts.get(functionContexts.size() - 1);
        Map<String, Variable> actualContext = actualfunctionContexts.get(actualfunctionContexts.size() - 1);
        for(Variable var : actualContext.values()) {
            vars.add(var);
        }
        return vars;
    }

    public void addVariable(Variable v, Position p) {
        if (functionContexts.size() != 0) {
            List<Map<String, Variable>> actualfunctionContexts = functionContexts.get(functionContexts.size() - 1);
            Map<String, Variable> actualContext = actualfunctionContexts.get(actualfunctionContexts.size() - 1);
            if (actualContext.containsKey(v.getName())) {
                throw new RuntimeException("Redeclaration of variable " + v.getName() + " at " + p.toString());
            }
            actualContext.put(v.getName(), v);
        } else {
            if (globalContext.containsKey(v.getName())) {
                throw new RuntimeException("Redeclaration of variable " + v.getName() + " at " + p.toString());
            }
            globalContext.put(v.getName(), v);
        }
    }

    public void addNewBlock() {
        if(functionContexts.size() == 0) {
            throw new RuntimeException("No function context created yet");
        }
        functionContexts.get(functionContexts.size() - 1).add(new LinkedHashMap());
    }

    public Map<String, Variable> removeLastBlock() {
        return functionContexts.get(functionContexts.size() - 1)
                .remove(functionContexts.get(functionContexts.size() - 1).size() - 1);
    }

    public void removeLastFunctionContext() {
        functionContexts.remove(functionContexts.size() - 1);
    }

    public void addNewFunctionContext() {
        List newList = new ArrayList();
        newList.add(new LinkedHashMap());
        functionContexts.add(newList);
    }

    public Code convertType(Code code, Type type, String newId) {
        if(code.getMemRegisterType().getRec() != type.getRec()) {
            throw new RuntimeException("Cannot convert arays " + code.getMemRegisterType() + "to" + type.getType());
        }

        if(code.getMemRegisterType().getType().equals(type.getType())) {
            return code;
        }

        if(type.getRec() != 0) {
            throw new RuntimeException("Cannot convert arays " + code.getMemRegisterType() + "to" + type.getType());
        }


        if(type.getType().equals("bool")) {
            if(code.getMemRegisterType().getType().equals("int")) {
                code.addCode(newId + "= icmp ne i32 0, " + code.getMemRegister() + "\n");
                code.setMemRegister(newId, type);
                return code;
            }
            if(code.getMemRegisterType().getType().equals("char")) {
                code.addCode(newId + "= icmp ne i8 0, " + code.getMemRegister() + "\n");
                code.setMemRegister(newId, type);
                return code;
            }
        }
        if(type.getType().equals("int")) {
            if(code.getMemRegisterType().getType().equals("bool")) {
                code.addCode(newId + "= zext i1 " + code.getMemRegister() + " to i32\n");
                code.setMemRegister(newId, type);
                return code;
            }
            if(code.getMemRegisterType().getType().equals("char")) {
                code.addCode(newId + "= zext i8 " + code.getMemRegister() + " to i32\n");
                code.setMemRegister(newId, type);
                return code;
            }
        }
        if(type.getType().equals("char")) {
            if(code.getMemRegisterType().getType().equals("bool")) {
                code.addCode(newId + "= zext i1 " + code.getMemRegister() + " to i8\n");
                code.setMemRegister(newId, type);
                return code;
            }
            if(code.getMemRegisterType().getType().equals("int")) {
                code.addCode(newId + "= trunc i32 " + code.getMemRegister() + " to i8\n");
                code.setMemRegister(newId, type);
                return code;
            }
        }
        throw new RuntimeException("Unknown conversion from " + code.getMemRegisterType() + "to" + type.getType());
    }
    public Program() {
    }
}
