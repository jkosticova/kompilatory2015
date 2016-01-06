import org.antlr.v4.runtime.misc.Pair;
import org.stringtemplate.v4.ST;

import java.util.*;
import java.util.stream.Collectors;

public class CompilerVisitor extends fuBaseVisitor<CodeFragment> {
    private Map<String, List<String>> externalFunctions = new HashMap<>();
    private Map<String, List<Pair<String, String>>> functions = new HashMap<>();
    private Map<String, String> functionBodies = new HashMap<>();
    private Map<String, Variable> variables = new HashMap<>();
    private int labelIndex = 0;
    private int registerIndex = 0;
    private String currentFunctionName = null;

    private void initializeFunctions() {
        String printLnIntBody = "%1 = call i32 (i8*, ...) @printf(i8* getelementptr ([4 x i8], [4 x i8]* @.number.newline, i64 0, i64 0), i32 %c)\n" +
                "ret void\n";
        String printLnStrBody = "%1 = call i32 (i8*, ...) @printf(i8* getelementptr ([4 x i8], [4 x i8]* @.string.newline, i64 0, i64 0), i8* %c)\n" +
                "ret void\n";
        String printIntBody = "%1 = call i32 (i8*, ...) @printf(i8* getelementptr ([3 x i8], [3 x i8]* @.number, i64 0, i64 0), i32 %c)\n" +
                "ret void\n";
        String printStrBody = "%1 = call i32 (i8*, ...) @printf(i8* getelementptr ([3 x i8], [3 x i8]* @.string, i64 0, i64 0), i8* %c)\n" +
                "ret void\n";
        String printSpaceBody = "%1 = call i32 (i8*, ...) @printf(i8* getelementptr ([2 x i8], [2 x i8]* @.space, i64 0, i64 0))\n" +
                "ret void \n";
        String printNewLine = "%1 = call i32 (i8*, ...) @printf(i8* getelementptr ([2 x i8], [2 x i8]* @.newline, i64 0, i64 0))\n" +
                "ret void \n";
        Pair<String, String> printIntArg1 = new Pair<>("i32", "%c");
        Pair<String, String> printStrArg1 = new Pair<>("i8*", "%c");
        Pair<String, String> voidFuncArg0 = new Pair<>("void", "");
        ArrayList<Pair<String, String>> printIntArgs = new ArrayList<>();
        printIntArgs.add(voidFuncArg0);
        printIntArgs.add(printIntArg1);
        ArrayList<Pair<String, String>> printStrArgs = new ArrayList<>();
        printStrArgs.add(voidFuncArg0);
        printStrArgs.add(printStrArg1);
        ArrayList<Pair<String, String>> voidVoidArgs = new ArrayList<>();
        voidVoidArgs.add(voidFuncArg0);
        functionBodies.put("printLnInt", printLnIntBody);
        functions.put("printLnInt", printIntArgs);
        functionBodies.put("printLnStr", printLnStrBody);
        functions.put("printLnStr", printStrArgs);
        functionBodies.put("printInt", printIntBody);
        functions.put("printInt", printIntArgs);
        functionBodies.put("printStr", printStrBody);
        functions.put("printStr", printStrArgs);
        functionBodies.put("printSpace", printSpaceBody);
        functions.put("printSpace", voidVoidArgs);
        functionBodies.put("printNewLine", printNewLine);
        functions.put("printNewLine", voidVoidArgs);
    }

    private void initializeExternalFunctions() {
        externalFunctions.put("printf", new ArrayList<>());
        externalFunctions.put("scanf", new ArrayList<>());
        externalFunctions.put("strcpy", new ArrayList<>());
        externalFunctions.put("strlen", new ArrayList<>());
        externalFunctions.put("strcat", new ArrayList<>());
        externalFunctions.put("malloc", new ArrayList<>());

        externalFunctions.get("printf").add("i32");
        externalFunctions.get("printf").add("i8*");
        externalFunctions.get("printf").add("...");

        externalFunctions.get("scanf").add("i32");
        externalFunctions.get("scanf").add("i8*");
        externalFunctions.get("scanf").add("...");

        externalFunctions.get("strcpy").add("i8*");
        externalFunctions.get("strcpy").add("i8*");
        externalFunctions.get("strcpy").add("i8*");

        externalFunctions.get("strlen").add("i32");
        externalFunctions.get("strlen").add("i8*");

        externalFunctions.get("strcat").add("i8*");
        externalFunctions.get("strcat").add("i8*");
        externalFunctions.get("strcat").add("i8*");

        externalFunctions.get("malloc").add("i8*");
        externalFunctions.get("malloc").add("i64");
    }

    private String renderExternalFunction(String name) {
        List<String> arguments = externalFunctions.get(name);
        String argString = String.join(", ", arguments.subList(1, arguments.size()));

        ST template = new ST("declare <return_type> @<function_name>(<arguments>)\n");
        template.add("return_type", arguments.get(0));
        template.add("function_name", name);
        template.add("arguments", argString);
        return template.render();
    }

    private String renderFunction(String name) {
        List<Pair<String, String>> arguments = functions.get(name);
        String functionBody = functionBodies.get(name);
        String argString = arguments.subList(1, arguments.size()).stream().map((p) -> (p.a + " " + p.b)).collect(Collectors.joining(", "));

        ST template = new ST("define <return_type> @<function_name>(<arguments>) {\n<function_body>}\n");
        template.add("return_type", arguments.get(0).a);
        template.add("function_name", name);
        template.add("arguments", argString);
        template.add("function_body", functionBody);
        return template.render();
    }

    @Override
    public CodeFragment visitInit(fuParser.InitContext ctx) {
        initializeFunctions();
        initializeExternalFunctions();
        CodeFragment programBody = visit(ctx.statements());

        ST template = new ST(
                "@.number = constant [3 x i8] c\"%d\\00\", align 1\n" +
                        "@.string = constant [3 x i8] c\"%s\\00\", align 1\n" +
                        "@.string.limited = constant [7 x i8] c\"%1023s\\00\", align 1\n" +
                        "@.number.newline = constant [4 x i8] c\"%d\\0A\\00\", align 1\n" +
                        "@.string.newline = constant [4 x i8] c\"%s\\0A\\00\", align 1\n" +
                        "@.space = constant [2 x i8] c\" \\00\", align 1\n" +
                        "@.newline = constant [2 x i8] c\"\\0a\\00\", align 1\n" +
                        "<declarations>\n" +
                        "<definitions>\n" +
                        "define i32 @main() {\n" +
                        "<program_code>" +
                        "ret i32 0\n" +
                        "}"
        );
        List<String> declarations = externalFunctions.keySet().stream().map(this::renderExternalFunction).collect(Collectors.toList());
        List<String> definitions = functions.keySet().stream().map(this::renderFunction).collect(Collectors.toList());
        template.add("declarations", declarations);
        template.add("definitions", definitions);
        template.add("program_code", programBody);
        CodeFragment ret = new CodeFragment();
        ret.addCode(template.render());
        return ret;
    }

    @Override
    public CodeFragment visitAdd(fuParser.AddContext ctx) {
        CodeFragment left = visit(ctx.expression(0));
        CodeFragment right = visit(ctx.expression(1));

        VariableType leftType = left.getVariable().getVariableType();
        VariableType rightType = right.getVariable().getVariableType();
        if (leftType == VariableType.INT && rightType == VariableType.INT) {
            return this.visitAddInt(left, right);
        } else if (leftType == VariableType.STRING && rightType == VariableType.STRING) {
            return this.visitAddString(left, right);
        } else {
            System.err.println("Can't use binary operation '+' on " + leftType.toReadableString() + " and " + rightType.toReadableString());
            return new CodeFragment();
        }
    }

    @Override
    public CodeFragment visitSubtract(fuParser.SubtractContext ctx) {
        CodeFragment left = visit(ctx.expression(0));
        CodeFragment right = visit(ctx.expression(1));

        VariableType leftType = left.getVariable().getVariableType();
        VariableType rightType = right.getVariable().getVariableType();
        if (leftType == VariableType.INT && rightType == VariableType.INT) {
            return this.visitSubtractInt(left, right);
        } else {
            System.err.println("Can't use binary operation '-' on " + leftType.toReadableString() + " and " + rightType.toReadableString());
            return new CodeFragment();
        }
    }

    @Override
    public CodeFragment visitDivision(fuParser.DivisionContext ctx) {
        CodeFragment left = visit(ctx.expression(0));
        CodeFragment right = visit(ctx.expression(1));

        VariableType leftType = left.getVariable().getVariableType();
        VariableType rightType = right.getVariable().getVariableType();
        if (leftType == VariableType.INT && rightType == VariableType.INT) {
            return this.visitDivisionInt(left, right);
        } else {
            System.err.println("Can't use binary operation '/' on " + leftType.toReadableString() + " and " + rightType.toReadableString());
            return new CodeFragment();
        }
    }

    @Override
    public CodeFragment visitMultiply(fuParser.MultiplyContext ctx) {
        CodeFragment left = visit(ctx.expression(0));
        CodeFragment right = visit(ctx.expression(1));

        VariableType leftType = left.getVariable().getVariableType();
        VariableType rightType = right.getVariable().getVariableType();
        if (leftType == VariableType.INT && rightType == VariableType.INT) {
            return this.visitMultiplicationInt(left, right);
        } else {
            System.err.println("Can't use binary operation '*' on " + leftType.toReadableString() + " and " + rightType.toReadableString());
            return new CodeFragment();
        }
    }

    @Override
    public CodeFragment visitModulus(fuParser.ModulusContext ctx) {
        CodeFragment left = visit(ctx.expression(0));
        CodeFragment right = visit(ctx.expression(1));

        VariableType leftType = left.getVariable().getVariableType();
        VariableType rightType = right.getVariable().getVariableType();
        if (leftType == VariableType.INT && rightType == VariableType.INT) {
            return this.visitModulusInt(left, right);
        } else {
            System.err.println("Can't use binary operation '%' on " + leftType.toReadableString() + " and " + rightType.toReadableString());
            return new CodeFragment();
        }
    }

    public CodeFragment booleanizeString(CodeFragment code) {
        String register = generateNewRegister();
        ST template = new ST("<temp_reg> = load i8, i8* <string_reg>\n" +
                "<temp2_reg> = icmp ne i8 <temp_reg>, 0\n" +
                "<ret_reg> = zext i1 <temp2_reg> to i32\n")
                .add("temp_reg", generateNewRegister())
                .add("temp2_reg", generateNewRegister())
                .add("ret_reg", register)
                .add("value_reg", code.getVariable().getRegister());
        code.addCode(template.render());
        code.setVariable(new Variable(register, VariableType.INT));
        return code;
    }

    public CodeFragment booleanizeArray(CodeFragment code) {
        String register = generateNewRegister();
        ST template = new ST("<register> = add i32 0, 1\n").add("register", register);
        code.addCode(template.render());
        code.setVariable(new Variable(register, VariableType.INT));
        return code;

    }

    public CodeFragment booleanizeInt(CodeFragment code) {
        String register = generateNewRegister();
        ST template = new ST("<temp_reg> = icmp ne i32 <value_reg>, 0\n" +
                "<ret_reg> = zext i1 <temp_reg> to i32\n")
                .add("temp_reg", generateNewRegister())
                .add("ret_reg", register)
                .add("value_reg", code.getVariable().getRegister());
        code.addCode(template.render());
        code.setVariable(new Variable(register, VariableType.INT));
        return code;
    }

    @Override
    public CodeFragment visitOr(fuParser.OrContext ctx) {
        CodeFragment left = booleanize(visit(ctx.expression(0)));
        CodeFragment right = booleanize(visit(ctx.expression(1)));
        return generateBooleanBinaryOperation(left, right, "or");
    }

    @Override
    public CodeFragment visitAnd(fuParser.AndContext ctx) {
        CodeFragment left = booleanize(visit(ctx.expression(0)));
        CodeFragment right = booleanize(visit(ctx.expression(1)));
        return generateBooleanBinaryOperation(left, right, "and");
    }

    private CodeFragment generateBooleanBinaryOperation(CodeFragment left, CodeFragment right, String operation) {
        String register = generateNewRegister();
        ST template = new ST("<left_code><right_code><register> = <operation> i32 <left>, <right>\n")
                .add("operation", operation)
                .add("register", register)
                .add("left", left.getVariable().getRegister())
                .add("right", right.getVariable().getRegister())
                .add("left_code", left)
                .add("right_code", right);
        CodeFragment code = new CodeFragment();
        code.addCode(template.render());
        code.setVariable(new Variable(register, VariableType.INT));
        return booleanize(code);
    }

    @Override
    public CodeFragment visitNot(fuParser.NotContext ctx) {
        CodeFragment code = booleanize(visit(ctx.expression()));
        String register = generateNewRegister();
        ST template = new ST("<temp_reg> = icmp eq i32 <value_reg>, 0\n" +
                "<register> = zext i1 <temp_reg> to i32\n")
                .add("register", register)
                .add("temp_reg", generateNewRegister())
                .add("value_reg", code.getVariable().getRegister());
        code.addCode(template.render());
        code.setVariable(new Variable(register, VariableType.INT));
        return code;
    }

    public CodeFragment booleanize(CodeFragment code) {
        /**
         * Boolean logic: Non-empty strings are true, every number except 0 is true, everything else is false.
         * True has value 1, False has value 0. Boolean values are stored as i32.
         */
        switch (code.getVariable().getVariableType()) {
            case VOID:
                break;
            case STRING:
                return booleanizeString(code);
            case INT:
                return booleanizeInt(code);
            case ARRAY_OF_STRING:
                return booleanizeArray(code);
            case ARRAY_OF_INT:
                return booleanizeArray(code);
        }
        String register = generateNewRegister();
        ST template = new ST("<register> = add i32 0, 0\n").add("register", register);
        code.addCode(template.render());
        code.setVariable(new Variable(register, VariableType.INT));
        return code;
    }

    @Override
    public CodeFragment visitStringValue(fuParser.StringValueContext ctx) {
        String value = ctx.getText();
        value = value.substring(1, value.length() - 1);

        return allocateStringExpression(value);
    }

    private CodeFragment allocateStringExpression(String value) {
        CodeFragment code = new CodeFragment();
        ST allocation = new ST("<register> = alloca i8, i32 <length>\n");
        String register = generateNewRegister();
        allocation.add("register", register);
        allocation.add("length", value.length() + 1);
        code.addCode(allocation.render());
        code.setVariable(new Variable(register, VariableType.STRING));

        for (int i = 0; i <= value.length(); i++) {
            int v = i < value.length() ? value.codePointAt(i) : 0;
            ST line = new ST(
                    "<char_reg> = getelementptr i8, i8* <string_reg>, i32 <index>\n" +
                            "store i8 <value>, i8* <char_reg>\n"
            );
            line.add("char_reg", this.generateNewRegister());
            line.add("string_reg", register);
            line.add("index", i);
            line.add("value", v);
            code.addCode(line.render());
        }
        return code;
    }

    private CodeFragment copyStringExpression(Variable origin) {
        CodeFragment code = new CodeFragment();
        String tempRegister = generateNewRegister();
        String lengthRegister = generateNewRegister();
        String newRegister = generateNewRegister();
        ST template = new ST("<temp_reg> = call i32 @strlen(i8* <value_reg>)\n" +
                "<length_reg> = add i32 1, <temp_reg>\n" +
                "<new_reg> = alloca i8, i32 <length_reg>\n" +
                "call i8* @strcpy(i8* <new_reg>, i8* <value_reg>)\n")
                .add("temp_reg", tempRegister)
                .add("length_reg", lengthRegister)
                .add("value_reg", origin.getRegister())
                .add("new_reg", newRegister);
        code.addCode(template.render());
        code.setVariable(new Variable(newRegister, VariableType.STRING));
        return code;
    }

    private CodeFragment heapCopyStringExpression(Variable origin) {
        CodeFragment code = new CodeFragment();
        String tempRegister = generateNewRegister();
        String lengthRegister = generateNewRegister();
        String newRegister = generateNewRegister();
        // We need to copy string on heap before returning it..
        ST template = new ST("<temp_reg> = call i32 @strlen(i8* <value_reg>)\n" +
                "<length_reg> = add i32 1, <temp_reg>\n" +
                "<true_length_reg> = zext i32 <length_reg> to i64\n" +
                "<new_reg> = call i8* @malloc(i64 <true_length_reg>)\n" +
                "call i8* @strcpy(i8* <new_reg>, i8* <value_reg>)\n")
                .add("temp_reg", tempRegister)
                .add("length_reg", lengthRegister)
                .add("true_length_reg", generateNewRegister())
                .add("value_reg", origin.getRegister())
                .add("new_reg", newRegister);
        code.addCode(template.render());
        code.setVariable(new Variable(newRegister, VariableType.STRING));
        return code;
    }

    private CodeFragment visitAddString(CodeFragment left, CodeFragment right) {
        CodeFragment ret = new CodeFragment();
        ret.addCode(left);
        ret.addCode(right);
        String newRegister = generateNewRegister();
        ST template = new ST("<length_left> = call i32 @strlen(i8* <left_reg>)\n" +
                "<length_right> = call i32 @strlen(i8* <right_reg>)\n" +
                "<temp_reg> = add i32 <length_left>, <length_right>\n" +
                "<length_reg> = add i32 <temp_reg>, 1\n" +
                "<new_reg> = alloca i8, i32 <length_reg>\n" +
                "store i8 0, i8* <new_reg>\n" +
                "call i8* @strcat(i8* <new_reg>, i8* <left_reg>)\n" +
                "call i8* @strcat(i8* <new_reg>, i8* <right_reg>)\n")
                .add("temp_reg", generateNewRegister())
                .add("length_left", generateNewRegister())
                .add("length_right", generateNewRegister())
                .add("length_reg", generateNewRegister())
                .add("new_reg", newRegister)
                .add("left_reg", left.getVariable().getRegister())
                .add("right_reg", right.getVariable().getRegister());
        ret.addCode(template.render());
        ret.setVariable(new Variable(newRegister, VariableType.STRING));
        return ret;
    }

    public CodeFragment generateIntegerBinaryOperation(CodeFragment left, CodeFragment right, String instruction) {
        ST template = new ST("<left_code><right_code><reg> = <instruction> <type> <left>, <right>\n");
        String register = this.generateNewRegister();

        template.add("instruction", instruction);
        template.add("reg", register);
        template.add("left_code", left);
        template.add("right_code", right);
        template.add("type", "i32");
        template.add("left", left.getVariable().getRegister());
        template.add("right", right.getVariable().getRegister());

        CodeFragment ret = new CodeFragment();
        ret.addCode(template.render());
        ret.setVariable(new Variable(register, VariableType.INT));
        return ret;
    }

    public CodeFragment generateIntegerModifyOperation(String varname, CodeFragment right, String instruction) {
        if (variables.containsKey(varname)) {
            Variable var = variables.get(varname);
            if (var.getVariableType() == VariableType.INT) {
                String leftReg = generateNewRegister();
                ST leftCode = new ST("<left_reg> = load i32, i32* <var_reg>\n");
                leftCode.add("left_reg", leftReg)
                        .add("var_reg", var.getRegister());
                CodeFragment left = new CodeFragment();
                left.setVariable(new Variable(leftReg, VariableType.INT));
                left.addCode(leftCode.render());
                ST template = new ST("<calculate_code>" +
                        "store i32 <result_reg>, i32* <var_reg>\n");
                CodeFragment binaryOp = generateIntegerBinaryOperation(left, right, instruction);
                template.add("calculate_code", binaryOp)
                        .add("result_reg", binaryOp.getVariable().getRegister())
                        .add("var_reg", var.getRegister());
                CodeFragment ret = new CodeFragment();
                ret.addCode(template.render());
                return ret;
            } else {
                System.err.println("You can't use " + instruction + " on variable with type " + var.getVariableType().toReadableString());
            }
        } else {
            System.err.println("Variable with name " + varname + " does not exist!");
        }
        return new CodeFragment();
    }

    public CodeFragment generateIntegerUnaryOperation(String varname, String instruction) {
        if (variables.containsKey(varname)) {
            Variable var = variables.get(varname);
            if (var.getVariableType() == VariableType.INT) {
                ST template = new ST("<temp_reg> = load i32, i32* <var_reg>\n" +
                        "<reg> = <operation> i32 <temp_reg>, 1\n" +
                        "store i32 <reg>, i32* <var_reg>\n");
                template.add("temp_reg", generateNewRegister())
                        .add("reg", generateNewRegister())
                        .add("var_reg", var.getRegister())
                        .add("operation", instruction);
                CodeFragment ret = new CodeFragment();
                ret.addCode(template.render());
                return ret;
            } else {
                System.err.println("You can't use integer unary operation on non-integer");
            }
        } else {
            System.err.println("Variable with name " + varname + " does not exist!");
        }
        return new CodeFragment();
    }

    public CodeFragment visitAddInt(CodeFragment left, CodeFragment right) {
        return generateIntegerBinaryOperation(left, right, "add");
    }

    public CodeFragment visitSubtractInt(CodeFragment left, CodeFragment right) {
        return generateIntegerBinaryOperation(left, right, "sub nsw");
    }

    public CodeFragment visitDivisionInt(CodeFragment left, CodeFragment right) {
        return generateIntegerBinaryOperation(left, right, "sdiv");
    }

    public CodeFragment visitMultiplicationInt(CodeFragment left, CodeFragment right) {
        return generateIntegerBinaryOperation(left, right, "mul nsw");
    }

    public CodeFragment visitModulusInt(CodeFragment left, CodeFragment right) {
        return generateIntegerBinaryOperation(left, right, "srem");
    }

    @Override
    public CodeFragment visitIncrementBy(fuParser.IncrementByContext ctx) {
        return generateIntegerModifyOperation(ctx.varname().getText(), visit(ctx.expression()), "add");
    }

    @Override
    public CodeFragment visitDecrementBy(fuParser.DecrementByContext ctx) {
        return generateIntegerModifyOperation(ctx.varname().getText(), visit(ctx.expression()), "sub nsw");
    }

    @Override
    public CodeFragment visitMultiplyBy(fuParser.MultiplyByContext ctx) {
        return generateIntegerModifyOperation(ctx.varname().getText(), visit(ctx.expression()), "mul nsw");
    }

    @Override
    public CodeFragment visitDivideBy(fuParser.DivideByContext ctx) {
        return generateIntegerModifyOperation(ctx.varname().getText(), visit(ctx.expression()), "sdiv");
    }

    @Override
    public CodeFragment visitModulusBy(fuParser.ModulusByContext ctx) {
        return generateIntegerModifyOperation(ctx.varname().getText(), visit(ctx.expression()), "srem");
    }

    @Override
    public CodeFragment visitIncrement(fuParser.IncrementContext ctx) {
        return generateIntegerUnaryOperation(ctx.varname().getText(), "add");
    }

    @Override
    public CodeFragment visitDecrement(fuParser.DecrementContext ctx) {
        return generateIntegerUnaryOperation(ctx.varname().getText(), "sub nsw");
    }

    @Override
    public CodeFragment visitPrint(fuParser.PrintContext ctx) {
        CodeFragment code = visit(ctx.expression());
        switch (code.getVariable().getVariableType()) {
            case VOID:
                return visitUnprintable(code);
            case STRING:
                return visitPrintString(code, false);
            case INT:
                return visitPrintInt(code, false);
            case ARRAY_OF_STRING:
                return visitUnprintable(code);
            case ARRAY_OF_INT:
                return visitUnprintable(code);
        }
        return visitUnprintable(code);
    }

    @Override
    public CodeFragment visitPrintLine(fuParser.PrintLineContext ctx) {
        CodeFragment code = visit(ctx.expression());
        switch (code.getVariable().getVariableType()) {
            case VOID:
                return visitUnprintable(code);
            case STRING:
                return visitPrintString(code, true);
            case INT:
                return visitPrintInt(code, true);
            case ARRAY_OF_STRING:
                return visitUnprintable(code);
            case ARRAY_OF_INT:
                return visitUnprintable(code);
        }
        return visitUnprintable(code);
    }

    @Override
    public CodeFragment visitScan(fuParser.ScanContext ctx) {
        String varname = ctx.varname().getText();
        if (variables.containsKey(varname)) {
            Variable var = variables.get(varname);
            switch (var.getVariableType()) {
                case STRING:
                    return scanString(var);
                case INT:
                    return scanInt(var);
                case VOID:
                case ARRAY_OF_STRING:
                case ARRAY_OF_INT:
                    System.err.println("You can't scan into variable of this type with <<");
            }
        } else {
            System.err.println("Variable with name " + varname + " does not exist!");
        }
        return new CodeFragment();
    }

    private CodeFragment scanString(Variable var) {
        String valueReg = generateNewRegister();
        ST template = new ST("<value_reg> = alloca i8, i32 1024\n" +
                "call i32 (i8*, ...) @scanf(i8* getelementptr ([7 x i8], [7 x i8]* @.string.limited, i64 0, i64 0), i8* <value_reg>)\n");
        template.add("value_reg", valueReg);
        var.setRegister(valueReg);
        CodeFragment ret = new CodeFragment();
        ret.addCode(template.render());
        return ret;
    }

    private CodeFragment scanInt(Variable var) {
        String value_reg = var.getRegister();
        ST template = new ST("call i32 (i8*, ...) @scanf(i8* getelementptr ([3 x i8], [3 x i8]* @.number, i64 0, i64 0), i32* <value_reg>)\n").add("value_reg", value_reg);
        CodeFragment ret = new CodeFragment();
        ret.addCode(template.render());
        return ret;
    }

    @Override
    public CodeFragment visitUnaryPlus(fuParser.UnaryPlusContext ctx) {
        CodeFragment code = visit(ctx.expression());
        if (code.getVariable().getVariableType() != VariableType.INT) {
            System.err.println("You can't use unary plus operator on something that is not INT");
            return new CodeFragment();
        } else {
            return code;
        }
    }

    @Override
    public CodeFragment visitIfElse(fuParser.IfElseContext ctx) {
        CodeFragment expression = booleanize(visit(ctx.expression()));
        CodeFragment thenBlock = visit(ctx.statements(0));
        CodeFragment elseBlock = visit(ctx.statements(1));
        String label = generateNewLabel();
        ST template = new ST("<expression_code>\n" +
                "<cmp_reg> = icmp ne i32 <expression_reg>, 0\n" +
                "br i1 <cmp_reg>, label %if.then<label>, label %if.else<label>\n" +
                "if.then<label>:\n" +
                "<then_code>\n" +
                "br label %if.done<label>\n" +
                "if.else<label>:\n" +
                "<else_code>\n" +
                "br label %if.done<label>\n" +
                "if.done<label>:\n");
        template.add("expression_code", expression)
                .add("cmp_reg", generateNewRegister())
                .add("expression_reg", expression.getVariable().getRegister())
                .add("label", label)
                .add("then_code", thenBlock)
                .add("else_code", elseBlock);
        CodeFragment ret = new CodeFragment();
        ret.addCode(template.render());
        return ret;
    }

    @Override
    public CodeFragment visitWhile(fuParser.WhileContext ctx) {
        CodeFragment expression = booleanize(visit(ctx.expression()));
        CodeFragment bodyBlock = visit(ctx.statements());
        String label = generateNewLabel();
        ST template = new ST("br label %while.start<label>\n" +
                "while.start<label>:\n" +
                "<expression_code>\n" +
                "<cmp_reg> = icmp ne i32 <expression_reg>, 0\n" +
                "br i1 <cmp_reg>, label %while.body<label>, label %while.done<label>\n" +
                "while.body<label>:\n" +
                "<body_code>\n" +
                "br label %while.start<label>\n" +
                "while.done<label>:\n");
        template.add("label", label)
                .add("expression_code", expression)
                .add("cmp_reg", generateNewRegister())
                .add("expression_reg", expression.getVariable().getRegister())
                .add("body_code", bodyBlock);
        CodeFragment ret = new CodeFragment();
        ret.addCode(template.render());
        return ret;
    }

    @Override
    public CodeFragment visitFor(fuParser.ForContext ctx) {
        CodeFragment beforeStatement = visit(ctx.statement(0));
        CodeFragment expression = booleanize(visit(ctx.expression()));
        CodeFragment bodyBlock = visit(ctx.statements());
        CodeFragment afterStatement = visit(ctx.statement(1));
        String label = generateNewLabel();
        ST template = new ST("<before_code>\n" +
                "br label %for.start<label>\n" +
                "for.start<label>:\n" +
                "<expression_code>\n" +
                "<cmp_reg> = icmp ne i32 <expression_reg>, 0\n" +
                "br i1 <cmp_reg>, label %for.body<label>, label %for.done<label>\n" +
                "for.body<label>:\n" +
                "<body_code>\n" +
                "<after_code>\n" +
                "br label %for.start<label>\n" +
                "for.done<label>:\n");
        template.add("label", label)
                .add("before_code", beforeStatement)
                .add("expression_code", expression)
                .add("cmp_reg", generateNewRegister())
                .add("expression_reg", expression.getVariable().getRegister())
                .add("body_code", bodyBlock)
                .add("after_code", afterStatement);
        CodeFragment ret = new CodeFragment();
        ret.addCode(template.render());
        return ret;
    }

    public CodeFragment generateIntegerToBooleanBinaryOperation(CodeFragment left, CodeFragment right, String operation) {
        if (left.getVariable().getVariableType() != VariableType.INT || right.getVariable().getVariableType() != VariableType.INT) {
            System.err.println("You can only compare integers");
        } else {
            String register = generateNewRegister();
            ST template = new ST("<left_code>" +
                    "<right_code>" +
                    "<tmp_reg> = icmp <operation> i32 <left>, <right>\n" +
                    "<register> = zext i1 <tmp_reg> to i32\n")
                    .add("operation", operation)
                    .add("register", register)
                    .add("tmp_reg", generateNewRegister())
                    .add("left", left.getVariable().getRegister())
                    .add("right", right.getVariable().getRegister())
                    .add("left_code", left)
                    .add("right_code", right);
            CodeFragment code = new CodeFragment();
            code.addCode(template.render());
            code.setVariable(new Variable(register, VariableType.INT));
            return code;
        }
        return new CodeFragment();
    }

    @Override
    public CodeFragment visitEquals(fuParser.EqualsContext ctx) {
        return generateIntegerToBooleanBinaryOperation(visit(ctx.expression(0)), visit(ctx.expression(1)), "eq");
    }

    @Override
    public CodeFragment visitLesserOrEqual(fuParser.LesserOrEqualContext ctx) {
        return generateIntegerToBooleanBinaryOperation(visit(ctx.expression(0)), visit(ctx.expression(1)), "sle");
    }

    @Override
    public CodeFragment visitGreaterThan(fuParser.GreaterThanContext ctx) {
        return generateIntegerToBooleanBinaryOperation(visit(ctx.expression(0)), visit(ctx.expression(1)), "sgt");
    }

    @Override
    public CodeFragment visitLessThan(fuParser.LessThanContext ctx) {
        return generateIntegerToBooleanBinaryOperation(visit(ctx.expression(0)), visit(ctx.expression(1)), "slt");
    }

    @Override
    public CodeFragment visitGreaterOrEqual(fuParser.GreaterOrEqualContext ctx) {
        return generateIntegerToBooleanBinaryOperation(visit(ctx.expression(0)), visit(ctx.expression(1)), "sge");
    }

    @Override
    public CodeFragment visitNotEqual(fuParser.NotEqualContext ctx) {
        return generateIntegerToBooleanBinaryOperation(visit(ctx.expression(0)), visit(ctx.expression(1)), "ne");
    }

    @Override
    public CodeFragment visitUnaryMinus(fuParser.UnaryMinusContext ctx) {
        CodeFragment code = visit(ctx.expression());
        if (code.getVariable().getVariableType() != VariableType.INT) {
            System.err.println("You can't use unary minus operator on something that is not INT");
            return new CodeFragment();
        } else {
            String register = generateNewRegister();
            ST template = new ST("<register> = sub nsw i32 0, <value_reg>\n").add("register", register).add("value_reg", code.getVariable().getRegister());
            code.addCode(template.render());
            code.setVariable(new Variable(register, VariableType.INT));
            return code;
        }
    }

    @Override
    public CodeFragment visitVarName(fuParser.VarNameContext ctx) {
        CodeFragment code = new CodeFragment();
        if (!variables.containsKey(ctx.getText())) {
            System.err.println("The specified variable does not exist!!");
        } else {
            Variable v = variables.get(ctx.getText());
            switch (v.getVariableType()) {
                // TODO: implement rest of variable type getting..., we are working with pointers in Strings and arrays, but base values with ints
                case VOID:
                    break;
                case STRING:
                    code.setVariable(v);
                    break;
                case INT:
                    String register = generateNewRegister();
                    code.setVariable(new Variable(register, v.getVariableType()));
                    ST template = new ST("<register> = load i32, i32* <value_reg>\n").add("register", register).add("value_reg", v.getRegister());
                    code.addCode(template.render());
                    break;
                case ARRAY_OF_STRING:
                    code.setVariable(v);
                    break;
                case ARRAY_OF_INT:
                    code.setVariable(v);
                    break;
            }

        }
        return code;
    }

    @Override
    public CodeFragment visitDeclareAssignStringVar(fuParser.DeclareAssignStringVarContext ctx) {
        String varname = ctx.varname().getText();
        CodeFragment ret = declareStringVar(varname);
        CodeFragment value = visit(ctx.expression());
        ret.addCode(assignVariable(varname, value));
        return ret;
    }

    @Override
    public CodeFragment visitDeclareAssignIntVar(fuParser.DeclareAssignIntVarContext ctx) {
        CodeFragment value = visit(ctx.expression());
        String varname = ctx.varname().getText();
        return declareAssignIntVar(varname, value);
    }

    private CodeFragment declareAssignIntVar(String varname, CodeFragment value) {
        CodeFragment ret = declareIntVar(varname);
        ret.addCode(assignVariable(varname, value));
        return ret;
    }

    public CodeFragment declareArray(VariableType type, int depth, int realDepth, List<CodeFragment> indices, String llvmType) {
        String indexRegister = indices.get(indices.size() - depth).getVariable().getRegister();
        String register = generateNewRegister();
        CodeFragment ret = new CodeFragment();
        if (realDepth == 0) {
            ret.setVariable(new Variable(register, VariableType.STRING));
        } else {
            ret.setVariable(new Variable(register, type));
            ret.getVariable().setArrayDepth(realDepth);
        }
        if (depth == 1) {
            ST template = new ST("<register> = alloca <llvmType>, i32 <index_reg>\n" +
                    "store <llvmType> 0, <llvmType>* <register>\n");
            template.add("register", register);
            template.add("index_reg", indexRegister);
            template.add("llvmType", llvmType);
            ret.addCode(template.render());
        } else {
            String label = generateNewLabel();
            StringBuilder sb = new StringBuilder();
            for (int i = 1; i < depth; i++) sb.append('*');
            String stars = sb.toString();
            ST templatePreRec = new ST("<register> = alloca <llvmType><stars>, i32 <index_reg>\n" +
                    "<temp_reg> = alloca i32\n" +
                    "store i32 <index_reg>, i32* <temp_reg>\n" +
                    "br label %alloc.start<label>\n" +
                    "alloc.start<label>:\n" +
                    "<counter_reg> = load i32, i32* <temp_reg>\n" +
                    "<cmp_reg> = icmp ne i32 <counter_reg>, 0\n" +
                    "br i1 <cmp_reg>, label %alloc.body<label>, label %alloc.done<label>\n" +
                    "alloc.body<label>:\n" +
                    "<lowered_reg> = sub i32 <counter_reg>, 1\n" +
                    "store i32 <lowered_reg>, i32* <temp_reg>\n"
            );

            String counter_reg = generateNewRegister();
            templatePreRec.add("register", register)
                    .add("temp_reg", generateNewRegister())
                    .add("index_reg", indexRegister)
                    .add("label", label)
                    .add("counter_reg", generateNewRegister())
                    .add("cmp_reg", generateNewRegister())
                    .add("lowered_reg", counter_reg)
                    .add("stars", stars)
                    .add("llvmType", llvmType);

            ST templateAfterRec = new ST("<temp_reg> = getelementptr <llvmType><stars>, <llvmType><stars>* <register>, i32 <counter_reg>\n" +
                    "store <llvmType><stars> <recursive_reg>, <llvmType><stars>* <temp_reg>\n" +
                    "br label %alloc.start<label>\n" +
                    "alloc.done<label>:\n");

            CodeFragment recursive = declareArray(type, depth - 1, realDepth - 1, indices, llvmType);

            templateAfterRec.add("temp_reg", generateNewRegister())
                    .add("stars", stars)
                    .add("register", register)
                    .add("label", label)
                    .add("recursive_reg", recursive.getVariable().getRegister())
                    .add("counter_reg", counter_reg)
                    .add("llvmType", llvmType);

            ret.addCode(templatePreRec.render());
            ret.addCode(recursive);
            ret.addCode(templateAfterRec.render());
        }
        return ret;
    }

    public CodeFragment declareIntArray(int depth, List<CodeFragment> indices) {
        String llvmType = "i32";
        VariableType variableType = VariableType.ARRAY_OF_INT;
        return declareArray(variableType, depth, depth, indices, llvmType);
    }

    public CodeFragment declareStringArray(int depth, List<CodeFragment> indices) {
        String llvmType = "i8";
        List<CodeFragment> strindices = new ArrayList<>(indices);
        CodeFragment oneIndex = new CodeFragment();
        oneIndex.setVariable(new Variable("1", VariableType.INT));
        strindices.add(oneIndex);
        VariableType variableType = VariableType.ARRAY_OF_STRING;
        return declareArray(variableType, depth + 1, depth, strindices, llvmType);
    }

    @Override
    public CodeFragment visitDeclareIntArray(fuParser.DeclareIntArrayContext ctx) {
        String varname = ctx.varname().getText();
        CodeFragment ret = new CodeFragment();
        List<CodeFragment> indices = ctx.expression().stream().map(this::visit).peek(ret::addCode).collect(Collectors.toList());
        int indicesCount = indices.size();

        CodeFragment declaration = declareIntArray(indicesCount, indices);
        if (variables.containsKey(varname)) {
            System.err.println("Error: Variable " + varname + " has been redefined!");
        }
        variables.put(varname, declaration.getVariable());
        ret.addCode(declaration);
        return ret;
    }

    @Override
    public CodeFragment visitDeclareStringArray(fuParser.DeclareStringArrayContext ctx) {
        String varname = ctx.varname().getText();
        CodeFragment ret = new CodeFragment();
        List<CodeFragment> indices = ctx.expression().stream().map(this::visit).peek(ret::addCode).collect(Collectors.toList());
        int indicesCount = indices.size();

        CodeFragment declaration = declareStringArray(indicesCount, indices);
        if (variables.containsKey(varname)) {
            System.err.println("Error: Variable " + varname + " has been redefined!");
        }
        variables.put(varname, declaration.getVariable());
        ret.addCode(declaration);
        return ret;
    }

    @Override
    public CodeFragment visitDeclareIntVar(fuParser.DeclareIntVarContext ctx) {
        String varname = ctx.varname().getText();
        return declareIntVar(varname);
    }

    @Override
    public CodeFragment visitParen(fuParser.ParenContext ctx) {
        return visit(ctx.expression());
    }

    private CodeFragment declareIntVar(String varname) {
        String register = generateNewRegister();
        // Block scoping won't be implemented
        if (variables.containsKey(varname)) {
            System.err.println("Error: Variable " + varname + " has been redefined!");
        }
        variables.put(varname, new Variable(register, VariableType.INT));
        ST template = new ST("<register> = alloca i32\nstore i32 0, i32* <register>\n").add("register", register);
        CodeFragment ret = new CodeFragment();
        ret.addCode(template.render());
        return ret;
    }

    @Override
    public CodeFragment visitDeclareStringVar(fuParser.DeclareStringVarContext ctx) {
        // Block scoping won't be implemented!
        String varname = ctx.varname().getText();
        return declareStringVar(varname);
    }

    private CodeFragment declareStringVar(String varname) {
        CodeFragment code = allocateStringExpression("");
        if (variables.containsKey(varname)) {
            System.err.println("Error: Variable " + varname + " has been redefined!");
        }
        variables.put(varname, new Variable(code.getVariable().getRegister(), VariableType.STRING));
        return code;
    }

    @Override
    public CodeFragment visitAssignVariable(fuParser.AssignVariableContext ctx) {
        String varname = ctx.varname().getText();
        CodeFragment code = visit(ctx.expression());
        return assignVariable(varname, code);
    }

    private CodeFragment assignVariable(String varname, CodeFragment code) {
        if (!this.variables.containsKey(varname)) {
            System.err.println("The specified variable does not exist!");
            return new CodeFragment();
        }
        Variable source = code.getVariable();
        Variable dest = variables.get(varname);
        if (source.getVariableType() == dest.getVariableType()) {
            switch (source.getVariableType()) {
                case INT:
                    ST template = new ST("store i32 <value>, i32* <varname>\n");
                    template.add("value", code.getVariable().getRegister());
                    template.add("varname", variables.get(varname).getRegister());
                    code.addCode(template.render());
                    return code;
                case VOID:
                    break;
                case STRING:
                    CodeFragment copy = copyStringExpression(source);
                    variables.put(varname, copy.getVariable());
                    code.addCode(copy);
                    return code;
                case ARRAY_OF_STRING:
                    break;
                case ARRAY_OF_INT:
                    break;
            }
            // Assign for strings is copy-by-value. Assign for arrays will not be implemented!
        } else {
            System.err.println("Assignment types do not match!");
        }
        System.err.println("Unsupported assign!");
        return new CodeFragment();
    }

    @Override
    public CodeFragment visitArrayGet(fuParser.ArrayGetContext ctx) {
        String varname = ctx.varname().getText();
        List<CodeFragment> indices = ctx.expression().stream().map(this::visit).collect(Collectors.toList());
        if (variables.containsKey(varname)) {
            Variable var = variables.get(varname);
            switch (var.getVariableType()) {
                case ARRAY_OF_STRING:
                    return arrayGetString(var, indices);
                case ARRAY_OF_INT:
                    return arrayGetInt(var, indices);
                case VOID:
                case STRING:
                case INT:
                    System.err.println("This variable is not an array!");
            }
        } else {
            System.err.println("You've tried to access undeclared array!");
        }
        return new CodeFragment();
    }

    @Override
    public CodeFragment visitArrayPut(fuParser.ArrayPutContext ctx) {
        String varname = ctx.varname().getText();
        List<CodeFragment> indices = ctx.expression().stream().map(this::visit).collect(Collectors.toList());
        CodeFragment value = indices.get(indices.size() - 1);
        indices = indices.subList(0, indices.size() - 1);
        if (variables.containsKey(varname)) {
            Variable var = variables.get(varname);
            switch (var.getVariableType()) {
                case ARRAY_OF_STRING:
                    return arrayPutString(var, indices, value);
                case ARRAY_OF_INT:
                    return arrayPutInt(var, indices, value);
                case VOID:
                case STRING:
                case INT:
                    System.err.println("This variable is not an array!");
            }
        } else {
            System.err.println("You've tried to access undeclared array!");
        }
        return new CodeFragment();
    }

    private CodeFragment arrayPutInt(Variable var, List<CodeFragment> indices, CodeFragment value) {
        String llvmType = "i32";
        int depthMod = 1;
        return arrayPut(var, indices, value, llvmType, depthMod);
    }

    private CodeFragment arrayPut(Variable var, List<CodeFragment> indices, CodeFragment value, String llvmType, int depthMod) {
        int depth = indices.size();
        if (depth != var.getArrayDepth()) {
            System.err.println("Depth is: " + depth);
            System.err.println("Array depth is: " + var.getArrayDepth());
            System.err.println("Array depth and indices count do not match!");
        } else {
            CodeFragment ret = new CodeFragment();
            ret.addCode(value);
            indices.stream().peek(ret::addCode).collect(Collectors.toList());
            String source = var.getRegister();
            for (int i = 0; i < depth; i++) {
                String index = indices.get(i).getVariable().getRegister();
                ST template;
                if (i == depth - 1) {
                    template = new ST("<temp_reg> = getelementptr <llvm_type><stars>, <llvm_type><stars>* <source>, i32 <index>\n" +
                            "store <llvm_type><stars> <value>, <llvm_type><stars>* <temp_reg>\n");
                } else {
                    template = new ST("<temp_reg> = getelementptr <llvm_type><stars>, <llvm_type><stars>* <source>, i32 <index>\n" +
                            "<register> = load <llvm_type><stars>, <llvm_type><stars>* <temp_reg>\n");
                }

                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < depth - i - depthMod; j++) {
                    sb.append('*');
                }
                String stars = sb.toString();
                String register = generateNewRegister();
                template.add("temp_reg", generateNewRegister())
                        .add("register", register)
                        .add("stars", stars)
                        .add("source", source)
                        .add("index", index)
                        .add("llvm_type", llvmType)
                        .add("value", value.getVariable().getRegister());
                ret.addCode(template.render());
                source = register;
            }
            return ret;
        }
        return new CodeFragment();
    }

    private CodeFragment arrayGetInt(Variable var, List<CodeFragment> indices) {
        int depth = indices.size();
        int depthMod = 1;
        String llvmType = "i32";
        return arrayGet(var, indices, depth, depthMod, llvmType, VariableType.INT);
    }

    private CodeFragment arrayGet(Variable var, List<CodeFragment> indices, int depth, int depthMod, String llvmType, VariableType varType) {
        if (depth != var.getArrayDepth()) {
            System.err.println("Array depth and indices count do not match!");
        } else {
            CodeFragment ret = new CodeFragment();
            indices.stream().peek(ret::addCode).collect(Collectors.toList());
            String source = var.getRegister();
            for (int i = 0; i < depth; i++) {
                String index = indices.get(i).getVariable().getRegister();
                ST template = new ST("<temp_reg> = getelementptr <llvm_type><stars>, <llvm_type><stars>* <source>, i32 <index>\n" +
                        "<register> = load <llvm_type><stars>, <llvm_type><stars>* <temp_reg>\n");
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < depth - i - depthMod; j++) {
                    sb.append('*');
                }
                String stars = sb.toString();
                String register = generateNewRegister();
                template.add("temp_reg", generateNewRegister())
                        .add("register", register)
                        .add("stars", stars)
                        .add("source", source)
                        .add("index", index)
                        .add("llvm_type", llvmType);
                ret.addCode(template.render());
                source = register;
            }
            ret.setVariable(new Variable(source, varType));
            return ret;
        }
        return new CodeFragment();
    }

    private CodeFragment arrayPutString(Variable var, List<CodeFragment> indices, CodeFragment value) {
        String llvmType = "i8";
        int depthMod = 0;
        CodeFragment ret = new CodeFragment();
        ret.addCode(value);
        CodeFragment valueCopy;
        if (functions.containsKey(currentFunctionName) && functions.get(currentFunctionName).stream().map(p -> p.b).collect(Collectors.toSet()).contains(var.getRegister())) {
            valueCopy = heapCopyStringExpression(value.getVariable());
        } else {
            valueCopy = copyStringExpression(value.getVariable());
        }
        ret.addCode(arrayPut(var, indices, valueCopy, llvmType, depthMod));
        return ret;
    }

    private CodeFragment arrayGetString(Variable var, List<CodeFragment> indices) {
        int depth = indices.size();
        int depthMod = 0;
        String llvmType = "i8";
        return arrayGet(var, indices, depth, depthMod, llvmType, VariableType.STRING);
    }

    private CodeFragment visitPrintInt(CodeFragment code, boolean printNewLine) {
        String funcname = printNewLine ? "printLnInt" : "printInt";
        ST template = new ST(
                "<value_code>" +
                        "call void @<funcname>(i32 <value>)\n"
        );
        template.add("value_code", code);
        template.add("value", code.getVariable().getRegister());
        template.add("funcname", funcname);
        CodeFragment ret = new CodeFragment();
        ret.addCode(template.render());
        return ret;
    }

    @Override
    public CodeFragment visitPrintSpaceSeparatedIntegers(fuParser.PrintSpaceSeparatedIntegersContext ctx) {
        String varname = ctx.varname().getText();
        CodeFragment expression = visit(ctx.expression());
        return printIntArray(varname, expression, false);
    }

    private CodeFragment scanArray(String varname, CodeFragment expression, VariableType type) {
        if (variables.containsKey(varname)) {
            if (expression.getVariable().getVariableType() == VariableType.INT) {
                Variable var = variables.get(varname);
                if (var.getVariableType() == type) {
                    if (var.getArrayDepth() == 1) {
                        String valueReg = generateNewRegister();
                        String pointer = generateNewRegister();
                        String positionReg = generateNewRegister();
                        ST reallocInt = new ST("<value_reg> = getelementptr i32, i32* <array_reg>, i32 <position_reg>\n");
                        ST reallocString = new ST("<pointer> = getelementptr i8*, i8** <array_reg>, i32 <position_reg>\n" +
                                "<value_reg> = alloca i8, i32 1024\n" +
                                "store i8* <value_reg>, i8** <pointer>\n");
                        ST realloc = type == VariableType.ARRAY_OF_INT ? reallocInt : reallocString;
                        realloc.add("position_reg", positionReg);
                        realloc.add("value_reg", valueReg);
                        realloc.add("pointer", pointer);
                        realloc.add("array_reg", var.getRegister());
                        String formattingStringInt = "i8* getelementptr ([3 x i8], [3 x i8]* @.number, i64 0, i64 0)";
                        String formattingStringString = "i8* getelementptr ([7 x i8], [7 x i8]* @.string.limited, i64 0, i64 0)";
                        String formattingString = type == VariableType.ARRAY_OF_INT ? formattingStringInt : formattingStringString;

                        ST template = new ST("<temp_reg> = alloca i32\n" +
                                "store i32 <size_reg>, i32* <temp_reg>\n" +
                                "br label %input.start<label>\n" +
                                "input.start<label>:\n" +
                                "<counter_reg> = load i32, i32* <temp_reg>\n" +
                                "<cmp_reg> = icmp ne i32 <counter_reg>, 0\n" +
                                "br i1 <cmp_reg>, label %input.body<label>, label %input.done<label>\n" +
                                "input.body<label>:\n" +
                                "<lowered_reg> = sub i32 <counter_reg>, 1\n" +
                                "store i32 <lowered_reg>, i32* <temp_reg>\n" +
                                "<position_reg> = sub i32 <size_reg>, <counter_reg>\n" +
                                "<realloc>" +
                                "call i32 (i8*, ...) @scanf(<formatting_string>, <vartype> <value_reg>)\n" +
                                "br label %input.start<label>\n" +
                                "input.done<label>:\n");
                        template.add("temp_reg", generateNewRegister())
                                .add("size_reg", expression.getVariable().getRegister())
                                .add("label", generateNewLabel())
                                .add("counter_reg", generateNewRegister())
                                .add("cmp_reg", generateNewRegister())
                                .add("lowered_reg", generateNewRegister())
                                .add("position_reg", positionReg)
                                .add("pointer", pointer)
                                .add("vartype", type == VariableType.ARRAY_OF_INT ? "i32*" : "i8*")
                                .add("array_reg", var.getRegister())
                                .add("realloc", realloc.render())
                                .add("formatting_string", formattingString)
                                .add("value_reg", valueReg);
                        CodeFragment ret = new CodeFragment();
                        ret.addCode(expression);
                        ret.addCode(template.render());
                        return ret;
                    } else {
                        System.err.println("Array to be scanned into is too deep");
                    }
                } else {
                    System.err.println("Wrong array type when scanning array");
                }
            } else {
                System.err.println("Number of scanned elements must be number!");
            }
        } else {
            System.err.println("Variable with name " + varname + " does not exist!");
        }
        return new CodeFragment();
    }

    private CodeFragment printIntArray(String varname, CodeFragment expression, boolean lineSep) {
        if (variables.containsKey(varname)) {
            if (expression.getVariable().getVariableType() == VariableType.INT) {
                Variable var = variables.get(varname);
                if (var.getVariableType() == VariableType.ARRAY_OF_INT) {
                    if (var.getArrayDepth() == 1) {
                        String valueReg = generateNewRegister();
                        ST printTemplateSpace = new ST("call void @printInt(i32 <value_reg>)\n" +
                                "call void @printSpace()\n");
                        ST printTemplateLine = new ST("call void @printLnInt(i32 <value_reg>)\n");
                        ST printTemplate = lineSep ? printTemplateLine : printTemplateSpace;
                        String additional = lineSep ? "" : "call void @printNewLine()\n";
                        printTemplate.add("value_reg", valueReg);
                        ST template = new ST("<temp_reg> = alloca i32\n" +
                                "store i32 <size_reg>, i32* <temp_reg>\n" +
                                "br label %output.start<label>\n" +
                                "output.start<label>:\n" +
                                "<counter_reg> = load i32, i32* <temp_reg>\n" +
                                "<cmp_reg> = icmp ne i32 <counter_reg>, 0\n" +
                                "br i1 <cmp_reg>, label %output.body<label>, label %output.done<label>\n" +
                                "output.body<label>:\n" +
                                "<lowered_reg> = sub i32 <counter_reg>, 1\n" +
                                "store i32 <lowered_reg>, i32* <temp_reg>\n" +
                                "<position_reg> = sub i32 <size_reg>, <counter_reg>\n" +
                                "<pointer> = getelementptr i32, i32* <array_reg>, i32 <position_reg>\n" +
                                "<value_reg> = load i32, i32* <pointer>\n" +
                                "<print_template>" +
                                "br label %output.start<label>\n" +
                                "output.done<label>:\n" +
                                "<additional>");
                        template.add("temp_reg", generateNewRegister())
                                .add("size_reg", expression.getVariable().getRegister())
                                .add("label", generateNewLabel())
                                .add("counter_reg", generateNewRegister())
                                .add("cmp_reg", generateNewRegister())
                                .add("lowered_reg", generateNewRegister())
                                .add("position_reg", generateNewRegister())
                                .add("pointer", generateNewRegister())
                                .add("array_reg", var.getRegister())
                                .add("value_reg", valueReg)
                                .add("print_template", printTemplate.render())
                                .add("additional", additional);
                        CodeFragment ret = new CodeFragment();
                        ret.addCode(expression);
                        ret.addCode(template.render());
                        return ret;
                    } else {
                        System.err.println("Array with name " + varname + " is too deep!");
                    }
                } else {
                    System.err.println("Variable with name " + varname + " is not an array of integers!");
                }
            } else {
                System.err.println("Number of printed elements must be number!");
            }
        } else {
            System.err.println("Variable with name " + varname + " does not exist!");
        }
        return new CodeFragment();
    }

    private CodeFragment printStringArray(String varname, CodeFragment expression, boolean lineSep) {
        if (variables.containsKey(varname)) {
            if (expression.getVariable().getVariableType() == VariableType.INT) {
                Variable var = variables.get(varname);
                if (var.getVariableType() == VariableType.ARRAY_OF_STRING) {
                    if (var.getArrayDepth() == 1) {
                        String valueReg = generateNewRegister();
                        ST printTemplateSpace = new ST("call void @printStr(i8* <value_reg>)\n" +
                                "call void @printSpace()\n");
                        ST printTemplateLine = new ST("call void @printLnStr(i8* <value_reg>)\n");
                        ST printTemplate = lineSep ? printTemplateLine : printTemplateSpace;
                        String additional = lineSep ? "" : "call void @printNewLine()\n";
                        printTemplate.add("value_reg", valueReg);
                        ST template = new ST("<temp_reg> = alloca i32\n" +
                                "store i32 <size_reg>, i32* <temp_reg>\n" +
                                "br label %output.start<label>\n" +
                                "output.start<label>:\n" +
                                "<counter_reg> = load i32, i32* <temp_reg>\n" +
                                "<cmp_reg> = icmp ne i32 <counter_reg>, 0\n" +
                                "br i1 <cmp_reg>, label %output.body<label>, label %output.done<label>\n" +
                                "output.body<label>:\n" +
                                "<lowered_reg> = sub i32 <counter_reg>, 1\n" +
                                "store i32 <lowered_reg>, i32* <temp_reg>\n" +
                                "<position_reg> = sub i32 <size_reg>, <counter_reg>\n" +
                                "<pointer> = getelementptr i8*, i8** <array_reg>, i32 <position_reg>" +
                                "<value_reg> = load i8*, i8** <pointer>\n" +
                                "<print_template>" +
                                "br label %output.start<label>\n" +
                                "output.done<label>:\n" +
                                "<additional>");
                        template.add("temp_reg", generateNewRegister())
                                .add("size_reg", expression.getVariable().getRegister())
                                .add("label", generateNewLabel())
                                .add("counter_reg", generateNewRegister())
                                .add("cmp_reg", generateNewRegister())
                                .add("lowered_reg", generateNewRegister())
                                .add("position_reg", generateNewRegister())
                                .add("pointer", generateNewRegister())
                                .add("array_reg", var.getRegister())
                                .add("value_reg", valueReg)
                                .add("print_template", printTemplate.render())
                                .add("additional", additional);
                        CodeFragment ret = new CodeFragment();
                        ret.addCode(expression);
                        ret.addCode(template.render());
                        return ret;
                    } else {
                        System.err.println("Array with name " + varname + " is too deep!");
                    }
                } else {
                    System.err.println("Variable with name " + varname + " is not an array of strings!");
                }
            } else {
                System.err.println("Number of printed elements must be number!");
            }
        } else {
            System.err.println("Variable with name " + varname + " does not exist!");
        }
        return new CodeFragment();
    }

    @Override
    public CodeFragment visitPrintSpaceSeparatedStrings(fuParser.PrintSpaceSeparatedStringsContext ctx) {
        String varname = ctx.varname().getText();
        CodeFragment expression = visit(ctx.expression());
        return printStringArray(varname, expression, false);
    }

    @Override
    public CodeFragment visitPrintLineSeparatedIntegers(fuParser.PrintLineSeparatedIntegersContext ctx) {
        String varname = ctx.varname().getText();
        CodeFragment expression = visit(ctx.expression());
        return printIntArray(varname, expression, true);
    }

    @Override
    public CodeFragment visitPrintLineSeparatedStrings(fuParser.PrintLineSeparatedStringsContext ctx) {
        String varname = ctx.varname().getText();
        CodeFragment expression = visit(ctx.expression());
        return printStringArray(varname, expression, true);
    }

    @Override
    public CodeFragment visitScanArrayOfStrings(fuParser.ScanArrayOfStringsContext ctx) {
        String varname = ctx.varname().getText();
        CodeFragment expression = visit(ctx.expression());
        return scanArray(varname, expression, VariableType.ARRAY_OF_STRING);
    }

    @Override
    public CodeFragment visitScanArrayOfIntegers(fuParser.ScanArrayOfIntegersContext ctx) {
        String varname = ctx.varname().getText();
        CodeFragment expression = visit(ctx.expression());
        return scanArray(varname, expression, VariableType.ARRAY_OF_INT);
    }

    private CodeFragment visitPrintString(CodeFragment code, boolean printNewLine) {
        String funcname = printNewLine ? "printLnStr" : "printStr";
        ST template = new ST("call void @<funcname>(i8* <register>)\n").add("register", code.getVariable().getRegister())
                .add("funcname", funcname);
        code.addCode(template.render());
        return code;
    }

    private CodeFragment visitUnprintable(CodeFragment code) {
        System.err.println("You can't print void!");
        return new CodeFragment();
    }

    @Override
    public CodeFragment visitInteger(fuParser.IntegerContext ctx) {
        String value = ctx.INT().getText();
        CodeFragment code = new CodeFragment();
        String register = generateNewRegister();
        code.setVariable(new Variable(register, VariableType.INT));
        code.addCode(String.format("%s = add i32 0, %s\n", register, value));
        return code;
    }

    private String generateNewLabel() {
        return String.format("%d", this.labelIndex++);
    }

    private String generateNewRegister() {
        return String.format("%%R%d", this.registerIndex++);
    }

    @Override
    public CodeFragment visitStatements(fuParser.StatementsContext ctx) {
        return ctx.statement().stream().map(this::visit).collect(CodeFragment::new, CodeFragment::addCode, CodeFragment::addCode);
    }

    @Override
    public CodeFragment visitVoidFunctionDefinition(fuParser.VoidFunctionDefinitionContext ctx) {
        String functionName = ctx.funcname().getText();
        String returnType = "void";
        fuParser.StatementsContext statementsContext = ctx.statements();
        List<fuParser.ArrvartypeContext> arrvartypeContexts = ctx.arrvartype();
        List<fuParser.VarnameContext> varnameContexts = ctx.varname();
        return generateFunctionDefinition(functionName, returnType, null, statementsContext, arrvartypeContexts, varnameContexts);
    }

    @Override
    public CodeFragment visitFunctionDefinition(fuParser.FunctionDefinitionContext ctx) {
        String functionName = ctx.funcname().getText();
        String returnType = Variable.fromReadableString(ctx.vartype().getText()).toLLVMTypeString();
        fuParser.ExpressionContext returnExpressionContext = ctx.expression();
        fuParser.StatementsContext statementsContext = ctx.statements();
        List<fuParser.ArrvartypeContext> arrvartypeContexts = ctx.arrvartype();
        List<fuParser.VarnameContext> varnameContexts = ctx.varname();
        return generateFunctionDefinition(functionName, returnType, returnExpressionContext, statementsContext, arrvartypeContexts, varnameContexts);
    }

    private CodeFragment generateFunctionDefinition(String functionName, String returnType, fuParser.ExpressionContext returnExpressionContext, fuParser.StatementsContext statementsContext, List<fuParser.ArrvartypeContext> arrvartypeContexts, List<fuParser.VarnameContext> varnameContexts) {
        List<String> varTypes = arrvartypeContexts.stream()
                .map(fuParser.ArrvartypeContext::getText)
                .map(Variable::fromReadableString)
                .map(Variable::toLLVMTypeString)
                .collect(Collectors.toList());
        List<String> varNames = varnameContexts.stream()
                .map(fuParser.VarnameContext::getText)
                .map(p -> "%" + p)
                .collect(Collectors.toList());

        // Find duplicates using set.
        Set<String> nameSet = new HashSet<>(varNames);
        if (nameSet.size() != varNames.size()) {
            System.err.println("You can't have two (or more) arguments of the function sharing the same name.");
        } else {
            List<Pair<String, String>> arguments = new ArrayList<>();
            arguments.add(new Pair<>(returnType, ""));
            for (int i = 0; i < varTypes.size(); i++) {
                arguments.add(new Pair<>(varTypes.get(i), varNames.get(i)));
            }

            if (externalFunctions.containsKey(functionName) || functionBodies.containsKey(functionName)) {
                System.err.println("Function with name " + functionName + " is already defined or declared as external!");
            } else {
                functions.put(functionName, arguments);

                Map<String, Variable> oldVariables = this.variables;
                String oldFunctionName = this.currentFunctionName;
                currentFunctionName = functionName;
                this.variables = new HashMap<>();
                CodeFragment intAllocations = new CodeFragment();
                // Feed the arguments.
                List<Variable> variableList = arrvartypeContexts.stream()
                        .map(fuParser.ArrvartypeContext::getText)
                        .map(Variable::fromReadableString)
                        .collect(Collectors.toList());
                for (int i = 0; i < varNames.size(); i++) {
                    variableList.get(i).setRegister(varNames.get(i));
                    if (variableList.get(i).getVariableType() == VariableType.INT) {
                        CodeFragment empty = new CodeFragment();
                        empty.setVariable(variableList.get(i));
                        intAllocations.addCode(declareAssignIntVar(varnameContexts.get(i).getText(), empty));
                    } else {
                        variables.put(varnameContexts.get(i).getText(), variableList.get(i));
                    }
                }

                if (returnExpressionContext != null) {
                    CodeFragment functionBody = visit(statementsContext);
                    CodeFragment returnExpression = visit(returnExpressionContext);
                    ST template = new ST("<int_allocations>" +
                            "<statements>" +
                            "<expression_value>" +
                            "<string_conversion>" +
                            "ret <return_type> <expression_reg>\n");
                    template.add("int_allocations", intAllocations);
                    template.add("statements", functionBody);
                    template.add("expression_value", returnExpression);
                    if (returnExpression.getVariable().getVariableType() == VariableType.STRING) {
                        CodeFragment copiedString = heapCopyStringExpression(returnExpression.getVariable());
                        template.add("string_conversion", copiedString);
                        template.add("expression_reg", copiedString.getVariable().getRegister());
                    } else {
                        template.add("string_conversion", "");
                        template.add("expression_reg", returnExpression.getVariable().getRegister());
                    }
                    template.add("return_type", returnType);
                    if (Objects.equals(returnType, returnExpression.getVariable().toLLVMTypeString())) {
                        this.functionBodies.put(functionName, template.render());
                    } else {
                        System.err.println("Return Type is: " + returnType);
                        System.err.println("Return expression type is: " + returnExpression.getVariable().toLLVMTypeString());
                        System.err.println("The return value type must match function type!");
                    }
                } else {
                    this.functionBodies.put(functionName, intAllocations.toString() + visit(statementsContext).toString() + "ret void\n");
                }

                this.variables = oldVariables;
                currentFunctionName = oldFunctionName;
            }
        }
        return new CodeFragment();
    }

    @Override
    public CodeFragment visitExternalFunctionDeclaration(fuParser.ExternalFunctionDeclarationContext ctx) {
        String functionName = ctx.funcname().getText();
        String returnType = Variable.fromReadableString(ctx.vartype().getText()).toLLVMTypeString();
        List<String> varTypes = ctx.arrvartype().stream()
                .map(fuParser.ArrvartypeContext::getText)
                .map(Variable::fromReadableString)
                .map(Variable::toLLVMTypeString)
                .collect(Collectors.toList());
        List<String> arguments = new ArrayList<>();
        arguments.add(returnType);
        arguments.addAll(varTypes);

        if (externalFunctions.containsKey(functionName) || functionBodies.containsKey(functionName)) {
            System.err.println("Function with name " + functionName + " is already defined or declared as external!");
        } else {
            externalFunctions.put(functionName, arguments);
        }
        return new CodeFragment();
    }

    @Override
    public CodeFragment visitFunctionCall(fuParser.FunctionCallContext ctx) {
        String functionName = ctx.funcname().getText();
        CodeFragment ret = new CodeFragment();
        if (functions.containsKey(functionName) || externalFunctions.containsKey(functionName)) {
            List<String> argTypes = new ArrayList<>();
            String argList = String.join(", ", ctx.expression().stream()
                    .map(this::visit)
                    .peek(ret::addCode)
                    .map(CodeFragment::getVariable)
                    .peek(p -> argTypes.add(p.toLLVMTypeString()))
                    .map(Variable::toLLVMString)
                    .collect(Collectors.toList()));

            List<String> funcArgTypes;
            if (functions.containsKey(functionName))
                funcArgTypes = functions.get(functionName).subList(1, functions.get(functionName).size()).stream().map(p -> p.a).collect(Collectors.toList());
            else
                funcArgTypes = externalFunctions.get(functionName).subList(1, externalFunctions.get(functionName).size());
            if (funcArgTypes.size() != argTypes.size()) {
                System.err.println("Number of arguments must match the function declaration!");
            } else {
                boolean argumentsMatch = true;
                for (int i = 0; i < funcArgTypes.size(); i++) {
                    argumentsMatch = argumentsMatch && funcArgTypes.get(i).equals(argTypes.get(i));
                }
                if (!argumentsMatch) {
                    System.err.println("Argument types must match the function declaration!");
                } else {
                    String returnRegister = generateNewRegister();
                    String returnType = functions.get(functionName).get(0).a;
                    VariableType returnVarType;
                    ST templateNonVoid = new ST("<return_reg> = call <return_type> @<function_name>(<arguments>)\n");
                    ST templateVoid = new ST("call <return_type> @<function_name>(<arguments>)\n" +
                            "<return_reg> = alloca i32\n" +
                            "store i32 0, i32* <return_reg>\n");
                    ST template = templateNonVoid;
                    switch (returnType) {
                        case "i8*":
                            returnVarType = VariableType.STRING;
                            break;
                        case "i32":
                            returnVarType = VariableType.INT;
                            break;
                        case "void":
                            template = templateVoid;
                            returnVarType = VariableType.INT;
                            break;
                        default:
                            returnVarType = null;
                            break;
                    }
                    template.add("return_type", returnType)
                            .add("function_name", functionName)
                            .add("arguments", argList)
                            .add("return_reg", returnRegister);
                    ret.addCode(template.render());
                    if (returnVarType == null) {
                        System.err.println("Functions returning arrays are not supported!");
                    } else {
                        ret.setVariable(new Variable(returnRegister, returnVarType));
                    }
                }
            }
        } else {
            System.err.println("Function " + functionName + "wasn't defined nor declared as external!");
        }

        return ret;
    }

    @Override
    public CodeFragment visitContainedExpression(fuParser.ContainedExpressionContext ctx) {
        return visit(ctx.expression());
    }
}
