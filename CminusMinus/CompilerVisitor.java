import com.sun.org.apache.bcel.internal.classfile.Code;
import org.stringtemplate.v4.ST;

import java.util.*;


public class CompilerVisitor extends CminusMinusBaseVisitor<CodeFragment> {
    private Map<String, Variable> mem = new HashMap<>();
    private Set<String> localDeclarations = new HashSet<>();
    private int labelIndex = 0;
    private int registerIndex = 0;
    private CodeFragment DefFunctions = new CodeFragment();
    private Map<String, Functions> functions= new HashMap<>();
    private CodeFragment DefExternFunctions = new CodeFragment();

    private String generateNewLabel() {
        return String.format("%d", this.labelIndex++);
    }

    private String generateNewRegister() {
        return String.format("%%R%d", this.registerIndex++);
    }

    @Override
    public CodeFragment visitBlock_statement(CminusMinusParser.Block_statementContext ctx) {
        return visit(ctx.block());
    }

    @Override
    public CodeFragment visitFun_block(CminusMinusParser.Fun_blockContext ctx) {
        return visit(ctx.function_block());
    }

    @Override
    public CodeFragment visitFun_call(CminusMinusParser.Fun_callContext ctx) {
        return visit(ctx.function_call());
    }

    @Override
    public CodeFragment visitFunctionCall(CminusMinusParser.FunctionCallContext ctx) {
        return visit(ctx.function_call());
    }

    @Override
    public CodeFragment visitVariable(CminusMinusParser.VariableContext ctx) {
        return visit(ctx.var());
    }

    @Override
    public CodeFragment visitPriority(CminusMinusParser.PriorityContext ctx) {
        return visit(ctx.expression());
    }


    @Override
    public CodeFragment visitDeclarVarInt(CminusMinusParser.DeclarVarIntContext ctx) {
        return visit(ctx.declar_var_Int());
    }

    @Override
    public CodeFragment visitDeclarVarStr(CminusMinusParser.DeclarVarStrContext ctx) {
        return visit(ctx.declar_var_Str());
    }

    @Override
    public CodeFragment visitDeclareArray(CminusMinusParser.DeclareArrayContext ctx) {
        return visit(ctx.declare_array());
    }

    @Override
    public CodeFragment visitArray(CminusMinusParser.ArrayContext ctx) {
        return visit(ctx.arr());
    }



    @Override
    public CodeFragment visitBlock(CminusMinusParser.BlockContext ctx) {
        Map<String, Variable> oldmem= this.mem;
        Map<String, Variable> localmem = new HashMap<>(this.mem);
        Set<String> oldDeclarations = this.localDeclarations;
        this.localDeclarations=new HashSet<>();
        this.mem=localmem;

        CodeFragment body= visit(ctx.statements());

        this.localDeclarations=oldDeclarations;
        this.mem=oldmem;

        return body;
    }

    @Override
    public CodeFragment visitFunction_block(CminusMinusParser.Function_blockContext ctx) {
        CodeFragment body= visit(ctx.statements());
        CodeFragment ret = visit(ctx.ret());
        body.addCode(ret);
        body.setRegister(ret.getRegister());
        body.setType(ret.getType());

        return body;
    }

    @Override
    public CodeFragment visitRet(CminusMinusParser.RetContext ctx) {
        CodeFragment code;
        code=visit(ctx.expression());
        String t;
        if(code.getType().equals(Type.INT)||code.getType().equals(Type.ARRAY_INT)) {
            t = "i32";
        }else{
            t="i8*";
        }
        code.addCode(String.format("ret %s %s\n", t, code.getRegister()));

        return code;
    }

    @Override
    public CodeFragment visitInit(CminusMinusParser.InitContext ctx) {
        CodeFragment body = visit(ctx.statements());
        ST template = new ST(
                "declare void @printInt(i32*)\n"
                + "declare void @printString(i8*)\n"
                + "declare void @scanInt(i32*)\n"
                + "declare void @scanString(i8*)\n"
                + "<extern_functions>\n"
                + "<functions>\n"
                + "define i32 @main() {\n"
                + "start:\n"
                + "<body_code>"
                + "ret i32 0\n"
                + "}\n"
        );

        template.add("extern_functions", DefExternFunctions);
        template.add("functions", DefFunctions);
        template.add("body_code", body);

        CodeFragment code = new CodeFragment();
        code.addCode(template.render());
        code.setRegister(body.getRegister());
        return code;
    }

    @Override
    public CodeFragment visitStatements(CminusMinusParser.StatementsContext ctx) {
        CodeFragment code = new CodeFragment();
        for (CminusMinusParser.StatementContext s : ctx.statement()) {
            CodeFragment statement = visit(s);
            code.addCode(statement);
        }
        return code;
    }

    @Override
    public CodeFragment visitInt(CminusMinusParser.IntContext ctx) {
        String value = ctx.INT().getText();
        CodeFragment code = new CodeFragment();
        String register = generateNewRegister();
        code.setRegister(register);
        code.addCode(String.format("%s = add i32 0, %s\n", register, value));
        return code;
    }

    @Override
    public CodeFragment visitStr(CminusMinusParser.StrContext ctx) {
        CodeFragment code = new CodeFragment();
        String stringPtr = generateNewRegister();

        String stringName = ctx.STRING().getText();
        ST template1 = new ST("<pointer> = alloca i8, i32 <length>; <name>\n");
        template1.add("pointer", stringPtr);
        template1.add("length", stringName.length() + 1);
        template1.add("name", stringName);
        code.addCode(template1.render());
        code.setRegister(stringPtr);
        code.setType(Type.STRING);

        for (int i = 0; i < stringName.length() + 1; i++) {
            char character = i < stringName.length()  ? stringName.charAt(i) : '\0';
            int value = Integer.valueOf(character);
            ST template = new ST(
                    "<character_register> = getelementptr i8* <pointer>, i32 <index>\n" +
                    "store i8 <character>, i8* <character_register>\n"
            );
            template.add("index", i);
            template.add("pointer", stringPtr);
            template.add("character_register", this.generateNewRegister());
            template.add("character", value);

            code.addCode(template.render());
        }

        return code;
    }


    @Override
    public CodeFragment visitPrint_int(CminusMinusParser.Print_intContext ctx) {
        String mem_reg = generateNewRegister();
        CodeFragment code = new CodeFragment();
        CodeFragment variable = visit(ctx.expression());

        if(variable.getType().equals(Type.INT)){
            ST template = new ST(
                    "<variable>" +
                    "<mem_reg> = alloca i32\n"+
                    "store i32 <val_reg>, i32* <mem_reg>\n" +
                    "call void @printInt (<type>* <mem_reg>)\n"
            );
            template.add("variable", variable);
            template.add("type", variable.getLlvmCode());
            template.add("mem_reg", mem_reg);
            template.add("val_reg", variable.getRegister());
            code.addCode(template.render());
        }
        else if(variable.getType().equals(Type.ARRAY_INT)){
            ST template = new ST(
                    "<variable>" +
                            "<mem_reg> = alloca i32\n"+
                            "<value_reg>= load i32* <val_reg>\n"+
                            "store i32 <value_reg>, i32* <mem_reg>\n" +
                            "call void @printInt (<type>* <mem_reg>)\n"
            );
            template.add("variable", variable);
            template.add("type", variable.getLlvmCode());
            template.add("mem_reg", mem_reg);
            template.add("val_reg", variable.getRegister());
            template.add("value_reg", generateNewRegister());
            code.addCode(template.render());
        }
        else {
            System.err.println(String.format("Error: idenifier '%s' is not NUM", variable.getType() ));
        }

        return code;
    }

    @Override
    public CodeFragment visitPrint_str(CminusMinusParser.Print_strContext ctx) {
        CodeFragment code = new CodeFragment();
        CodeFragment variable = visit(ctx.expression());


        Type var_type = variable.getType();
        if(var_type == Type.STRING ){
            ST template = new ST(
                    "<variable>" +
                    "call void @printString (<type> <val_reg>)\n"
            );
            template.add("variable", variable);
            template.add("type", variable.getLlvmCode());
            template.add("val_reg", variable.getRegister());
            code.addCode(template.render());
        }
        else if(var_type == Type.ARRAY_STR){
            ST template = new ST(
                    "<variable>" +
                    "<value_reg>= load <type>* <val_reg>\n"+
                    "call void @printString (<type> <value_reg>)\n"
            );
            template.add("variable", variable);
            template.add("value_reg", generateNewRegister());
            template.add("type", variable.getLlvmCode());
            template.add("val_reg", variable.getRegister());
            code.addCode(template.render());
        }
        else {
            System.err.println(String.format("Error: idenifier '%s' is not TEXT", variable.getType()));
        }

        return code;
    }

    @Override
    public CodeFragment visitVar(CminusMinusParser.VarContext ctx) {
        String name= ctx.STRING().getText();
        CodeFragment code = new CodeFragment();
        String register = generateNewRegister();
        String ptr;
        if(mem.containsKey(name)){
            Variable var =mem.get(name);
            String type=var.getCode()+"*";
            int hv =0;
            if(var.getType().equals(Type.ARRAY_INT)){
                hv=var.getRozmer();
            }
            else if(var.getType().equals(Type.ARRAY_STR)){
                hv=var.getRozmer();
            }

            for(int i=0;i<hv;i++){
                type+="*";
            }
            ptr=var.getRegister();
            ST template = new ST(
                    "<register> = load <type> <pointer>\n"
                );
            template.add("register", register);
            template.add("pointer", ptr);
            template.add("type", type);
            code.addCode(template.render());
            code.setType(var.getType());
            code.setRegister(register);
        }
        else {
            System.err.println(String.format("Error: idenifier '%s' is not declared", name));
        }

        return code;
    }


    private CodeFragment generateCodeForBinaryOperator(String op , CodeFragment left, CodeFragment right){
        CodeFragment code = new CodeFragment();
        if( (left.getType() == Type.INT || left.getType() == Type.ARRAY_INT) && (right.getType() == Type.INT || right.getType() == Type.ARRAY_INT)){
            String left_val=left.getRegister();
            String right_val =right.getRegister();


            ST template1 = new ST(
                    "<left_code>" +
                    "<right_code>"
            );

            template1.add("left_code", left);
            template1.add("right_code", right);
            code.addCode(template1.render());

            if(left.getType().equals(Type.ARRAY_INT)){
                ST tmp1= new ST(
                        "<value_reg> = load i32* <pointer_reg>\n"
                );
                String val_reg=generateNewRegister();
                tmp1.add("value_reg", val_reg);
                tmp1.add("pointer_reg", left.getRegister());
                code.addCode(tmp1.render());
                left_val=val_reg;
            }

            if(right.getType().equals(Type.ARRAY_INT)){
                ST tmp2= new ST(
                        "<value_reg> = load i32* <pointer_reg>\n"
                );
                String val_reg=generateNewRegister();
                tmp2.add("value_reg",val_reg);
                tmp2.add("pointer_reg", right.getRegister());
                code.addCode(tmp2.render());
                right_val=val_reg;
            }

            ST template2= new ST(
                    "<ret> = <op> i32 <left_val>, <right_val>\n"
            );

            template2.add("left_val", left_val);
            template2.add("op", op);
            template2.add("right_val", right_val);
            String ret_register = this.generateNewRegister();
            template2.add("ret", ret_register);


            code.setRegister(ret_register);
            code.addCode(template2.render());
            code.setType(Type.INT);
            return code;
        }
        else {
            System.err.println("ERROR you can't use operator " + op + " on " + left.getType() + " and " + right.getType());
        }
        return new CodeFragment();
    }

    private CodeFragment IncDecUnar(String varN,CodeFragment cod, String op){
        CodeFragment c= new CodeFragment();
        String varName= varN;
        if(mem.containsKey(varName)){
            Variable var = mem.get(varName);
            if(var.getType().equals(Type.INT)) {
                String tmp_reg = generateNewRegister();
                ST template = new ST(
                        "<var>" +
                        "<ret_reg> =  <op> i32 <var_reg>, 1\n" +
                        "store i32 <ret_reg>, i32* <mem_reg>\n"
                );

                template.add("var", cod);
                template.add("op", op);
                template.add("var_reg", cod.getRegister());
                template.add("ret_reg", tmp_reg);
                template.add("mem_reg", var.getRegister());
                c.addCode(template.render());
            }
            else
                System.err.println(String.format("Error: Variable %s is not declared as number.", varName));

        }
        else {
            System.err.println(String.format("Error: Variable %s is not declared", varName));
        }
        return c;
    }

    @Override
    public CodeFragment visitIncrement(CminusMinusParser.IncrementContext ctx) {
        return IncDecUnar(ctx.var().STRING().getText(), visit(ctx.var()), "add");
    }

    @Override
    public CodeFragment visitDecrement(CminusMinusParser.DecrementContext ctx) {
        return IncDecUnar(ctx.var().STRING().getText(), visit(ctx.var()), "sub");
    }

    @Override
    public CodeFragment visitUnarMinus(CminusMinusParser.UnarMinusContext ctx) {
        CodeFragment code= new CodeFragment();
        CodeFragment variable = visit(ctx.expression());
            if(variable.getType().equals(Type.INT)) {
                String tmp_reg = generateNewRegister();
                ST template = new ST(
                        "<var>" +
                        "<ret_reg> =  sub i32 0, <var_reg>\n"
                );

                template.add("var", variable);
                template.add("var_reg", variable.getRegister());
                template.add("ret_reg", tmp_reg);
                template.add("mem_reg", variable.getRegister());
                code.addCode(template.render());
                code.setRegister(tmp_reg);
                code.setType(Type.INT);
            }
            else
                System.err.println("Error: Expression must be number");

        return code;
    }

    @Override
    public CodeFragment visitAdd(CminusMinusParser.AddContext ctx) {
        CodeFragment left = visit(ctx.expression(0));
        CodeFragment right = visit(ctx.expression(1));

        if (ctx.op.getText().equals("+"))
            return generateCodeForBinaryOperator("add", left, right);
        else {
            return generateCodeForBinaryOperator("sub", left, right);
        }
    }

    @Override
    public CodeFragment visitMul(CminusMinusParser.MulContext ctx) {
        CodeFragment left = visit(ctx.expression(0));
        CodeFragment right = visit(ctx.expression(1));

        if (ctx.op.getText().equals("*"))
            return generateCodeForBinaryOperator("mul", left, right);
        else if (ctx.op.getText().equals("/"))
            return generateCodeForBinaryOperator("sdiv", left, right);
        else
            return generateCodeForBinaryOperator("srem", left, right);

    }

    @Override
    public CodeFragment visitAnd(CminusMinusParser.AndContext ctx) {
        CodeFragment left = visit(ctx.expression(0));
        CodeFragment right = visit(ctx.expression(1));

        return generateCodeForBinaryOperator("and", left, right);
    }

    @Override
    public CodeFragment visitAssignArr(CminusMinusParser.AssignArrContext ctx) {
        String name = ctx.arr().STRING().getText();
        CodeFragment code = new CodeFragment();

        Variable var = mem.get(name);
        CodeFragment value= visit(ctx.expression());
        CodeFragment pointer= visit(ctx.arr());
        if(var.getType().equals(Type.ARRAY_INT) && value.getType().equals(Type.INT)){
            ST template= new ST(
                    "<val_code>"+
                    "<get_pointer>"+
                    "store i32 <val_reg>, i32* <pointer_reg>\n"
            );
            template.add("val_code", value);
            template.add("val_reg", value.getRegister());
            template.add("get_pointer", pointer);
            template.add("pointer_reg", pointer.getRegister());
            code.addCode(template.render());
        }
        else if(var.getType().equals(Type.ARRAY_INT) && value.getType().equals(Type.ARRAY_INT)){
            ST template= new ST(
                    "<val_code>"+
                    "<get_pointer>"+
                    "<arr_value> = load i32* <val_reg>\n"+
                    "store i32 <arr_value>, i32* <pointer_reg>\n"
            );
            template.add("val_code", value);
            template.add("val_reg", value.getRegister());
            template.add("arr_value", generateNewRegister());
            template.add("get_pointer", pointer);
            template.add("pointer_reg", pointer.getRegister());
            code.addCode(template.render());
        }
        else if(var.getType().equals(Type.ARRAY_STR) && value.getType().equals(Type.STRING)){
            ST template= new ST(
                    "<val_code>"+
                    "<get_pointer>"+
                    "store i8* <val_reg>, i8** <pointer_reg>\n"
            );
            template.add("val_code", value);
            template.add("val_reg", value.getRegister());
            template.add("get_pointer", pointer);
            template.add("pointer_reg", pointer.getRegister());
            code.addCode(template.render());
        }
        else {
            System.err.println(String.format("Error: You can not assign %s to %s", var.getType(), value.getType()));
        }

        return code;
    }

    @Override
    public CodeFragment visitAssignVar(CminusMinusParser.AssignVarContext ctx) {
        CodeFragment value = visit(ctx.expression());
        ST template;
        CodeFragment ret = new CodeFragment();

        String identifier = ctx.name().getText();

        if (!mem.containsKey(identifier)) {
            System.err.println(String.format("Error: idenifier '%s' does not exists", identifier));
        } else {
            Variable var = mem.get(identifier);
            if(var.getType().equals(Type.INT)) {
                template = new ST(
                        "<val_code>" +
                        "store i32 <val_reg>, i32* <mem_reg>\n"
                );
            }
            else {
                template = new ST(
                        "<val_code>" +
                        "store i8* <val_reg>, i8** <mem_reg>\n"
                );
            }
            template.add("val_code", value);
            template.add("val_reg", value.getRegister());
            template.add("mem_reg", var.getRegister());
            ret.addCode(template.render());
            ret.setRegister(value.getRegister());
        }

        return ret;
    }

    @Override
    public CodeFragment visitDeclar_var_Int(CminusMinusParser.Declar_var_IntContext ctx) {
        String mem_register = this.generateNewRegister();
        Variable variable;
        String varName=ctx.STRING().getText();
        ST template;
        CodeFragment value= new CodeFragment();
        variable= new Variable(Type.INT,mem_register);
        if(ctx.expression() != null){
            value= visit(ctx.expression());
            if(value.getType() == Type.INT) {
                template = new ST(
                        "<mem_reg> = alloca i32\n" +
                        "<val_code>" +
                        "store i32 <val_reg>, i32* <mem_reg>\n"
                );
            }
            else if(value.getType() == Type.ARRAY_INT){
                template = new ST(
                        "<mem_reg> = alloca i32\n" +
                        "<val_code>" +
                        "<new_reg> = load i32* <val_reg>\n"+
                        "store i32 <new_reg>, i32* <mem_reg>\n"
                );
                template.add("new_reg", generateNewRegister());
            }
            else {
                System.err.println("ERROR you can declare num variable only with number" + value.getType());
                template=new ST("");
            }
        }
        else {
            template = new ST("<mem_reg> = alloca i32\n");
        }

        CodeFragment ret = new CodeFragment();
        if(!this.localDeclarations.contains(varName)){
            template.add("mem_reg", mem_register);
            template.add("val_code", value);
            template.add("val_reg", value.getRegister());
            ret.addCode(template.render());
            mem.put(varName, variable);
            localDeclarations.add(varName);
        }
        else {
            System.err.println(String.format("Error: idenifier '%s' is already declared", varName));
        }
        return ret;
    }

    @Override
    public CodeFragment visitDeclar_var_Str(CminusMinusParser.Declar_var_StrContext ctx) {
        String mem_register = this.generateNewRegister();
        Variable variable;
        String varName=ctx.STRING().getText();
        ST template;
        CodeFragment value= new CodeFragment();

        variable= new Variable(Type.STRING,mem_register);
        if(ctx.expression() != null){
            value= visit(ctx.expression());

            if(value.getType() == Type.STRING) {
                template = new ST(
                        "<mem_reg> = alloca i8*\n"+
                        "<val_code>" +
                        "store i8* <val_reg>, i8** <mem_reg>\n"
                );
            }
            else if(value.getType() == Type.ARRAY_STR){
                template = new ST(
                        "<mem_reg> = alloca i8*\n"+
                        "<val_code>" +
                        "<new_reg> = load i8** <val_reg>\n"+
                        "store i8* <new_reg>, i8** <mem_reg>\n"
                );
                template.add("new_reg", generateNewRegister());
            }
            else {
                System.err.println("ERROR you can declare text variable only with text" + value.getType());
                template=new ST("");
            }
        }
        else {
            template = new ST("<mem_reg> = alloca i8*\n");
        }
        CodeFragment ret = new CodeFragment();
        if(!this.localDeclarations.contains(varName)){
            template.add("mem_reg", mem_register);
            template.add("val_code", value);
            template.add("val_reg", value.getRegister());
            ret.addCode(template.render());
            mem.put(ctx.STRING().getText(), variable);
            localDeclarations.add(varName);
        }
        else {
            System.err.println(String.format("Error: idenifier '%s' is already declared", varName));
        }
        return ret;
    }

    private CodeFragment generateArrayDeclaration(String register, int hv, String size, int index, CminusMinusParser.Declare_arrayContext ctx , String type){
        if(hv<0){
            return new CodeFragment();
        }
        else{
            CodeFragment code= new CodeFragment();
            String hviezdy="";
            for(int i=0;i<hv;i++){
                hviezdy+="*";
            }
            ST temp=new ST(
                    "<iterator>= alloca i32\n"+
                    "<tmp1> = add i32 0, 0 \n"+
                    "store i32 <tmp1>, i32* <iterator> \n"+
                    "br label %<cond_label>\n"+
                    "<cond_label>:\n"+
                    "<tmp2> = load i32* <iterator> \n"+
                    "<tmp3> = add i32 0, <size>\n"+
                    "<tmp4> = icmp slt i32 <tmp2>, <tmp3> \n"+
                    "br i1 <tmp4>, label %<body_label>, label %<end_label>\n"+
                    "<body_label>:\n"+
                    "<prep>"+
                    "<iterator_value> = load i32* <iterator>\n"+
                    "<position_reg> = getelementptr <type>*<hviezdy_plus> <register>, i32 <iterator_value>\n" +
                    "<new_reg> = alloca <type><hviezdy>, i32 <prep_size>\n"+
                    "store <type><hviezdy_plus> <new_reg>, <type>*<hviezdy_plus> <position_reg>\n"+
                    "<rekurzia>"+
                    "<tmp5> = load i32* <iterator>\n"+
                    "<tmp6> = add i32 <tmp5>, 1\n"+
                    "store i32 <tmp6>, i32* <iterator>\n"+
                    "br label %<cond_label>\n"+
                    "<end_label>:\n"
            );

            CodeFragment prep = visit((ctx.expression(index)));
            String new_reg = generateNewRegister();

            temp.add("iterator", generateNewRegister());
            temp.add("tmp1", generateNewRegister());
            temp.add("tmp2", generateNewRegister());
            temp.add("tmp3", generateNewRegister());
            temp.add("tmp4", generateNewRegister());
            temp.add("tmp5", generateNewRegister());
            temp.add("tmp6", generateNewRegister());
            temp.add("type", type);
            temp.add("iterator_value", generateNewRegister());
            temp.add("cond_label", "condition"+generateNewLabel());
            temp.add("body_label", "body"+generateNewLabel());
            temp.add("end_label", "end"+generateNewLabel());
            temp.add("size", size);
            temp.add("prep", prep);
            temp.add("prep_size", prep.getRegister());
            temp.add("hviezdy", hviezdy);
            temp.add("hviezdy_plus", hviezdy+"*");
            temp.add("position_reg", generateNewRegister());
            temp.add("register", register);
            temp.add("new_reg", new_reg);
            temp.add("rekurzia", generateArrayDeclaration(new_reg, hv-1, prep.getRegister(), index+1, ctx, type));
            code.addCode(temp.render());
            return code;
        }
    }

    @Override
    public CodeFragment visitDeclare_array(CminusMinusParser.Declare_arrayContext ctx) {
        CodeFragment code= new CodeFragment();
        String name=ctx.STRING().getText();
        String type= ctx.type().getText();

        Type var_type=Type.ARRAY_INT;
        String var_llvm_type="";
        switch (type){
            case "num":
                var_type=Type.ARRAY_INT;
                var_llvm_type="i32";
                break;
            case "text":
                var_type=Type.ARRAY_STR;
                var_llvm_type="i8*";
                break;
        }
        if(!mem.containsKey(name)) {
            String mem_reg = generateNewRegister();
            String variable_mem=generateNewRegister();
            CodeFragment value = visit(ctx.expression(0));
            String hviezdy = "";
            for (int i = 0; i < ctx.expression().size() - 1; i++) {
                hviezdy += "*";
            }
            ST template = new ST(
                    "<variable_mem> = alloca <type><hviezdicky>*\n"+
                    "<prep>" +
                    "<mem_reg>= alloca <type><hviezdicky>, i32 <rozmer>\n"
            );
            template.add("variable_mem", variable_mem);
            template.add("prep", value);
            template.add("mem_reg", mem_reg);
            template.add("type", var_llvm_type);
            template.add("hviezdicky", hviezdy);
            template.add("rozmer", value.getRegister());
            code.addCode(template.render());
            code.addCode(generateArrayDeclaration(mem_reg, ctx.expression().size() - 2, value.getRegister(), 1, ctx, var_llvm_type));

            ST tmp = new ST("store <type><hviezdicky>* <mem_reg>, <type><hviezdicky>** <variable_mem>\n");
            tmp.add("variable_mem", variable_mem);
            tmp.add("mem_reg", mem_reg);
            tmp.add("type", var_llvm_type);
            tmp.add("hviezdicky", hviezdy);
            code.addCode(tmp.render());
            Variable pole = new Variable(var_type, variable_mem);
            pole.setRozmer(ctx.expression().size());
            mem.put(name, pole);
        }
        else {
            System.err.println(String.format("Error: Variable %s is already declared", name));
        }

        return code;
    }

    private CodeFragment generateArrayValue(CminusMinusParser.ArrContext ctx, int index, String register, int hv, String type){
        CodeFragment code = new CodeFragment();
        if(index > ctx.expression().size()-1){
            return code;
        }
        else {
            String hviezdy="";
            for(int i=0;i<hv;i++){
                hviezdy+="*";
            }

            CodeFragment value = visit(ctx.expression(index));

            ST template = new ST(
                    "<tmp1> = load <type><hviezdy_plus> <register>\n"+
                    "<prep>"+
                    "<new_reg> = getelementptr <type><hviezdy> <tmp1>, i32 <prep_reg>\n"
            );

            String new_reg = generateNewRegister();

            template.add("tmp1", generateNewRegister());
            template.add("prep", value);
            template.add("type", type);
            template.add("hviezdy_plus", hviezdy+"*");
            template.add("hviezdy", hviezdy);
            template.add("register", register);
            template.add("prep_reg", value.getRegister());
            template.add("new_reg", new_reg);
            code.addCode(template.render());
            CodeFragment tmp= generateArrayValue(ctx, index+1, new_reg, hv-1, type);
            code.addCode(tmp);
            if(tmp.getRegister().equals("")){
                code.setRegister(new_reg);
            }
            else
                code.setRegister(tmp.getRegister());

            return code;
        }
    }

    @Override
    public CodeFragment visitArr(CminusMinusParser.ArrContext ctx) {
        CodeFragment code = new CodeFragment();
        String name=ctx.STRING().getText();
        if(mem.containsKey(name)){
            Variable var = mem.get(name);
            String type= var.getCode();
            if(var.getRozmer() == ctx.expression().size()){
                String hviezdy="";

                for(int i=0;i < ctx.expression().size();i++){
                    hviezdy+="*";
                }

                CodeFragment value = visit(ctx.expression(0));
                ST template = new ST(
                        "<loaded> = load <type><hviezdy>* <var_reg>\n"+
                        "<prep>"+
                        "<mem_reg> = getelementptr <type><hviezdy> <loaded>, i32 <prep_reg>\n"
                );
                String mem_reg = generateNewRegister();
                template.add("prep", value);
                template.add("prep_reg", value.getRegister());
                template.add("mem_reg", mem_reg);
                template.add("loaded",generateNewRegister());
                template.add("var_reg", var.getRegister());
                template.add("type", type);
                template.add("hviezdy", hviezdy);
                code.addCode(template.render());
                CodeFragment tmp= generateArrayValue(ctx, 1, mem_reg, ctx.expression().size() - 1, type);
                code.addCode(tmp);
                if(tmp.getRegister().equals("")){
                    code.setRegister(mem_reg);
                }
                else
                    code.setRegister(tmp.getRegister());
                code.setType(var.getType());
            }
            else {
                System.err.println(String.format("Error: Variable %s is already with %d dimensions", name, var.getRozmer()));
            }
        }
        else {
            System.err.println(String.format("Error: Variable %s is not declared", name));
        }
        return code;
    }

    @Override
    public CodeFragment visitFor_cycle(CminusMinusParser.For_cycleContext ctx) {
        Map<String, Variable> oldmem= this.mem;
        Map<String, Variable> localmem = new HashMap<>(this.mem);
        Set<String> oldDeclarations = this.localDeclarations;
        Set<String> localDeclar= new HashSet<>();
        this.localDeclarations=localDeclar;
        this.mem=localmem;

        CodeFragment code = new CodeFragment();
        ST template;
        CodeFragment iterator = visit(ctx.declar_var_Int());
        CodeFragment condition= visit(ctx.expression());
        CodeFragment increment= new CodeFragment();
        if(ctx.statement(0) != null)
            increment= visit(ctx.statement(0));
        CodeFragment body=visit(ctx.statement(1));
        template = new ST(
                "<iterator>" +
                "br label %<compare>\n" +
                "<compare>:\n" +
                "<condition>" +
                "<compare_reg> = icmp ne i32 <condition_register>, 0\n" +
                "br i1 <compare_reg>, label %<body_start>, label %<end_for>\n" +
                "<body_start>:\n" +
                "<body>" +
                "<increment>"+
                "br label %<compare>\n" +
                "<end_for>:\n"
        );
        template.add("iterator", iterator);
        template.add("compare", "condition"+generateNewLabel());
        template.add("condition", condition);
        template.add("condition_register", condition.getRegister());
        template.add("compare_reg", generateNewRegister());
        template.add("body_start", "body"+generateNewLabel());
        template.add("end_for", "endFor"+generateNewLabel());
        template.add("body", body);
        template.add("increment", increment);
        String end_register = generateNewRegister();
        template.add("ret", end_register);

        code.addCode(template.render());
        this.localDeclarations=oldDeclarations;
        this.mem=oldmem;
        return code;
    }

    private CodeFragment generateCodeForCompare(String instruction, CodeFragment left, CodeFragment right){
        CodeFragment code = new CodeFragment();
        String left_val=left.getRegister();
        String right_val =right.getRegister();



        ST template1 =  new ST(
                "<left_code>" +
                "<right_code>"
        );

        template1.add("left_code", left);
        template1.add("right_code", right);
        code.addCode(template1.render());

        if(left.getType().equals(Type.ARRAY_INT)){
            ST tmp1= new ST(
                    "<value_reg> = load i32* <pointer_reg>\n"
            );
            String val_reg=generateNewRegister();
            tmp1.add("value_reg", val_reg);
            tmp1.add("pointer_reg", left.getRegister());
            code.addCode(tmp1.render());
            left_val=val_reg;
        }

        if(right.getType().equals(Type.ARRAY_INT)){
            ST tmp2= new ST(
                    "<value_reg> = load i32* <pointer_reg>\n"
            );
            String val_reg=generateNewRegister();
            tmp2.add("value_reg",val_reg);
            tmp2.add("pointer_reg", right.getRegister());
            code.addCode(tmp2.render());
            right_val=val_reg;
        }


        ST template2 = new ST(
                "<ret> = icmp <instruction> i32 <left_val>, <right_val>\n"+
                "<transformed>= zext i1 <ret> to i32\n"
        );
        template2.add("left_val", left_val);
        template2.add("instruction", instruction);
        template2.add("right_val", right_val);
        String transform = this.generateNewRegister();
        template2.add("ret", this.generateNewRegister());
        template2.add("ret_type", left.getType());
        template2.add("transformed", transform);

        code.setRegister(transform);
        code.addCode(template2.render());
        code.setType(Type.INT);
        return code;
    }

    @Override
    public CodeFragment visitComparison(CminusMinusParser.ComparisonContext ctx) {
        CodeFragment left = visit(ctx.expression(0));
        CodeFragment right = visit(ctx.expression(1));
        if(ctx.op.getText().equals("=="))
            return generateCodeForCompare("eq", left, right);
        else if(ctx.op.getText().equals("!="))
            return generateCodeForCompare("ne", left, right);
        else if(ctx.op.getText().equals("<="))
            return generateCodeForCompare("sle", left, right);
        else if(ctx.op.getText().equals(">="))
            return generateCodeForCompare("sge", left, right);
        else if(ctx.op.getText().equals(">"))
            return generateCodeForCompare("sgt", left, right);
        else
            return generateCodeForCompare("slt", left, right);
    }

    @Override
    public CodeFragment visitIf_else(CminusMinusParser.If_elseContext ctx) {
        CodeFragment ret = new CodeFragment();
        CodeFragment condition = visit(ctx.expression());
        String condition_reg= condition.getRegister();


        CodeFragment statement_true = visit(ctx.statement(0));
        CodeFragment statement_false = new CodeFragment();

        if(ctx.statement().size() >1){
            statement_false=visit(ctx.statement(1));
        }

        ST template1 = new ST(
                "<condition_code>"
        );
        template1.add("condition_code", condition);
        ret.addCode(template1.render());

        if(condition.getType().equals(Type.STRING) || condition.getType().equals(Type.ARRAY_STR)){
            condition_reg="1";
        }
        else if(condition.getType().equals(Type.ARRAY_INT)){
            ST tmp= new ST(
                    "<value_reg> = load i32* <pointer_reg>\n"
            );
            String val_reg=generateNewRegister();
            tmp.add("value_reg", val_reg);
            tmp.add("pointer_reg", condition.getRegister());
            ret.addCode(tmp.render());
            condition_reg=val_reg;
        }



        ST template2 = new ST(
                "<compare_reg> = icmp ne i32 <condition_reg>, 0\n" +
                "br i1 <compare_reg>, label %<true_label>, label %<false_label>\n" +
                "<true_label>:\n" +
                "<true_code>" +
                "br label %<end>\n" +
                "<false_label>:\n" +
                "<false_code>" +
                "br label %<end>\n" +
                "<end>:\n"
        );

        template2.add("true_code", statement_true);
        template2.add("false_code", statement_false);
        template2.add("compare_reg", this.generateNewRegister());
        template2.add("condition_reg", condition_reg);
        template2.add("true_label", "True"+this.generateNewLabel());
        template2.add("false_label", "False" + this.generateNewLabel());
        template2.add("end", "End"+this.generateNewLabel());

        ret.addCode(template2.render());

        return ret;
    }


    @Override
    public CodeFragment visitFunctionDef(CminusMinusParser.FunctionDefContext ctx) {
        Map<String, Variable> oldmem= this.mem;
        Map<String, Variable> localmem = new HashMap<>();
        Set<String> oldDeclarations = this.localDeclarations;
        this.localDeclarations=new HashSet<>();
        this.mem=localmem;


        String type = ctx.type().getText();
        String name = ctx.name().getText();

        String ftype="i32";
        Type function_type=Type.INT;
        switch (type){
            case "num":
                function_type=Type.INT;
                ftype="i32";
                break;
            case "text":
                function_type=Type.STRING;
                ftype="i8*";
                break;
        }

        CodeFragment args=new CodeFragment();
        List<String> arguments= new ArrayList<>();
        List<Variable> premenne= new ArrayList<>();
        Functions newFun= new Functions(ctx.args().lvalue().size(), premenne, function_type);
        this.functions.put(name + ctx.args().lvalue().size(),newFun);

        for(int i=0;i<ctx.args().lvalue().size();i++){
            String varName=ctx.args().lvalue(i).getText();
            if(varName.contains("[")){
                int end= varName.indexOf('[');
                varName=varName.substring(0, end);
            }
            if(!this.mem.containsKey(varName)){
                ST tmpTemplate= new ST(
                        "<mem_reg> = alloca <type>\n" +
                        "store <type> %<val_reg>, <type>* <mem_reg>\n"
                );
                String t="i32";
                Type tp=Type.INT;
                int size=0;
                if(ctx.args().lvalue(i).simple_var() != null) {
                    switch (ctx.args().type(i).getText()) {
                        case "num":
                            t = "i32";
                            tp = Type.INT;
                            break;
                        case "text":
                            t = "i8*";
                            tp = Type.STRING;
                            break;
                    }
                }
                else {
                    switch (ctx.args().type(i).getText()) {
                        case "num":
                            t = "i32";
                            tp = Type.ARRAY_INT;
                            break;
                        case "text":
                            t = "i8*";
                            tp = Type.ARRAY_STR;
                            break;
                    }
                    String hviezdicky="";
                    size=ctx.args().lvalue(i).array_var().LBRACK().size();
                    for(int j=0;j<size;j++){
                        hviezdicky+="*";
                    }
                    t+=hviezdicky;
                }
                String mem_reg=generateNewRegister();
                tmpTemplate.add("mem_reg", mem_reg);
                tmpTemplate.add("type", t);
                tmpTemplate.add("val_reg", varName);
                args.addCode(tmpTemplate.render());
                arguments.add(t + " %" + varName);
                Variable tmpVar=new Variable(tp, mem_reg);
                tmpVar.setRozmer(size);
                mem.put(varName, tmpVar);
                premenne.add(tmpVar);
                localDeclarations.add(varName);
            }
            else {
                System.err.println(String.format("Error: idenifier '%s' is already declared in this function", varName));
            }

        }

        String attr= String.join(",", arguments);
        CodeFragment body = visit(ctx.function_block());
        ST template= new ST(
                "define <type> @<name>(<arguments>){\n" +
                "<args>"+
                "<body>" +
                "}"
        );
        template.add("type", ftype);
        template.add("name", name);
        template.add("arguments", attr);
        template.add("args", args);
        template.add("body", body);
        DefFunctions.addCode(template.render());


        this.localDeclarations=oldDeclarations;
        this.mem=oldmem;

        return new CodeFragment();
    }

    @Override
    public CodeFragment visitFunction_call(CminusMinusParser.Function_callContext ctx) {
        if(this.functions.containsKey(ctx.name().getText()+ctx.expression().size()) ){
            List<Variable> defined= functions.get(ctx.name().getText()+ctx.expression().size()).getArguments();
            Functions function = functions.get(ctx.name().getText()+ctx.expression().size());
            CodeFragment prep= new CodeFragment();
            List<String> argList = new ArrayList<>();

            for(int i=0;i<ctx.expression().size();i++){
                CodeFragment arg = visit(ctx.expression(i));
                if(!arg.getType().equals(defined.get(i).getType())){
                    System.err.println(String.format("Error: Function argument type '%s' is different than declared one %s", arg.getType(),defined.get(i).getType() ));
                    prep=new CodeFragment();
                    break;
                }
                else {
                    prep.addCode(arg);
                    String hviezdy="";
                    if(defined.get(i).getType().equals(Type.ARRAY_INT) || defined.get(i).getType().equals(Type.ARRAY_STR)){
                        for(int j=0;j<defined.get(i).getRozmer();j++){
                            hviezdy+="*";
                        }
                    }
                    argList.add(arg.getLlvmCode() + hviezdy + " "+arg.getRegister());
                }
            }

            ST template = new ST(
                    "<prep>"+
                    "<mem_reg> = call <type> @<name>(<args>)\n"
            );
            template.add("prep", prep);
            template.add("name", ctx.name().getText());
            String args= String.join(",", argList);
            template.add("args", args);
            String mem_reg=generateNewRegister();
            template.add("mem_reg", mem_reg);
            template.add("type", function.getLlvmType());
            CodeFragment ret= new CodeFragment();
            ret.addCode(template.render());
            ret.setRegister(mem_reg);
            ret.setType(function.getType());
            return ret;
        }
        else {
            System.err.println(String.format("Error: Function '%s' is not declared", ctx.name().getText()+ctx.expression().size()));

            return new CodeFragment();
        }
    }

    @Override
    public CodeFragment visitExtern(CminusMinusParser.ExternContext ctx) {
        String type = ctx.type().getText();
        String name = ctx.name().getText();

        String ftype="i32";
        Type function_type=Type.INT;
        switch (type){
            case "num":
                function_type=Type.INT;
                ftype="i32";
                break;
            case "text":
                function_type=Type.STRING;
                ftype="i8";
                break;
        }

        List<String> arguments= new ArrayList<>();
        List<Variable> premenne= new ArrayList<>();
        Functions newFun= new Functions(ctx.args().lvalue().size(), premenne, function_type);
        this.functions.put(name + ctx.args().lvalue().size(),newFun);

        for(int i=0;i<ctx.args().lvalue().size();i++){
            String t="i32";
            Type tp=Type.INT;
            switch (ctx.args().type(i).getText()){
                case "num":
                    t="i32";
                    tp=Type.INT;
                    break;
                case "text":
                    t="i8";
                    tp=Type.STRING;
                    break;
            }

            arguments.add(t + '*');
            Variable tmpVar=new Variable(tp, "");
            premenne.add(tmpVar);
        }

        String attr= String.join(",", arguments);
        ST template= new ST(
                "declare <type> @<name>(<arguments>)\n"
        );
        template.add("type", ftype);
        template.add("name", name);
        template.add("arguments", attr);
        DefExternFunctions.addCode(template.render());

        return new CodeFragment();
    }

    @Override
    public CodeFragment visitRead_int(CminusMinusParser.Read_intContext ctx) {
        CodeFragment code = new CodeFragment();

        if(mem.containsKey(ctx.var().STRING().getText())) {
            Variable var = mem.get(ctx.var().STRING().getText());
            Type type = var.getType();

            if (!type.equals(Type.INT)) {
                System.err.println("Error: you cant read num to NaN variable");
            } else {
                ST template = new ST("call void @scanInt (i32* <value>)\n");
                template.add("value", var.getRegister());
                code.addCode(template.render());
            }
        }
        else {
            System.err.println("Error: You need to declare variable first");
        }

        return code;
    }

    @Override
    public CodeFragment visitRead_str(CminusMinusParser.Read_strContext ctx) {
        CodeFragment code = new CodeFragment();

        if(mem.containsKey(ctx.var().STRING().getText())) {
            Variable var = mem.get(ctx.var().STRING().getText());
            Type type = var.getType();
            String stringPtr = generateNewRegister();

            ST template1 = new ST("<pointer> = alloca i8, i32 2048;\n");
            template1.add("pointer", stringPtr);
            code.addCode(template1.render());
            code.setRegister(stringPtr);
            code.setType(Type.STRING);

            if (!type.equals(Type.STRING)) {
                System.err.println("Error: you cant read String to none string variable");
            } else {
                ST template = new ST(
                        "call void @scanString (i8* <value>)\n"+
                        "store i8* <value>, i8** <var_reg>\n"
                );
                template.add("value", stringPtr);
                template.add("var_reg", var.getRegister());
                code.addCode(template.render());
            }
        }
        else {
            System.err.println("Error: You need to declare variable first");
        }

        return code;
    }
}
