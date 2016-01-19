// Generated from /home/brando/School/git/Kompilatory/Exmi.g4 by ANTLR 4.5.1
import org.antlr.v4.runtime.tree.AbstractParseTreeVisitor;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.stringtemplate.v4.*;

import java.util.*;

/**
 * This class provides an empty implementation of {@link ExmiVisitor},
 * which can be extended to create a visitor which only needs to handle a subset
 * of the available methods.
 *
 * The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */

public class ExmiLLVMVisitor extends AbstractParseTreeVisitor<Code> implements ExmiVisitor<Code> {
    Program program = new Program();

	private Code initProgram() {
		Code libraryCode = new Code();
		program.addFunction(new Function(
			"printInt", new Type("int"), Collections.singletonList(new Type("int")),
			new Code("declare i32 @printInt(i32)\n")
		), null);

        program.addFunction(new Function(
                "printChar", new Type("int"), Collections.singletonList(new Type("char")),
                new Code("declare i32 @printChar(i8)\n")
        ), null);

        program.addFunction(new Function(
                "printString", new Type("int"), Collections.singletonList(new Type("char", 1)),
                new Code("declare i32 @printString(i8*)\n")
        ), null);

        program.addFunction(new Function(
                "printBool", new Type("int"), Collections.singletonList(new Type("bool")),
                new Code("declare i32 @printBool(i1)\n")
        ), null);

        program.addFunction(new Function(
                "printEndl", new Type("int"), Collections.emptyList(),
                new Code("declare i32 @printEndl()\n")
        ), null);

        program.addFunction(new Function(
                "scanInt", new Type("int"), Collections.emptyList(),
                new Code("declare i32 @scanInt()\n")
        ), null);

        program.addFunction(new Function(
                "scanChar", new Type("char"), Collections.emptyList(),
                new Code("declare i8 @scanChar()\n")
        ), null);

		program.addFunction(new Function(
				"iexp", new Type("int"), Arrays.asList(new Type("int"), new Type("int")),
				new Code("declare i32 @iexp(i32, i32)\n")
		), null);

		for(Function f : program.getFunctions()) {
			libraryCode.addCode(f.getCode());
		}
        libraryCode.addCode("\n");
		return libraryCode;
	}

	@Override public Code visitInit(ExmiParser.InitContext ctx) {
		Code finalCode = initProgram();
        Code code = visitChildren(ctx);
        finalCode.addCode(program.getConstants());
		finalCode.addCode(code);
		return finalCode;
	}

    @Override
    public Code visitObject(ExmiParser.ObjectContext ctx) {
        Variable var = program.getVariable(ctx.ID().getText(), new Position(ctx));
        List<ExmiParser.ExpressionContext> obj = ctx.expression();
        if(var.getType().getRec() != ctx.expression().size() && ctx.expression().size() != 0) {
            throw new RuntimeException("Incorrect number of brackets in array at " +  new Position(ctx));
        }

        if (ctx.expression().size() == 0) {
            Code code = new Code("", var.getType() + var.getType().getStars() + "* " + var.getRegister(), var.getType());
            return code;
        }
        Code code = new Code();
        String newId = var.getRegister();
        String stars = new String(var.getType().getStars());
        for(ExmiParser.ExpressionContext ectx : ctx.expression()) {
            Code exp_code = program.convertType(visit(ectx), new Type("int"), getNewIdentifier());
            code.addCode(exp_code);

            String lastId = newId;
            newId = getNewIdentifier();

            ST get = new ST(
                    "<new>.ptr = load <type><stars>* <old>\n" +
                    "<new> = getelementptr <type><stars> <new>.ptr, i32 <num>\n");
            get.add("new", newId);
            get.add("old", lastId);
            get.add("type", var.getType());
            get.add("stars", stars);
            get.add("num", exp_code.getMemRegister());
            code.addCode(get.render());

            stars = stars.substring(0, stars.length() - 1);
        }

        Type type = new Type(var.getType().getType());
        code.setMemRegister(var.getType() + "* " + newId, type);
        return code;
    }

    @Override
    public Code visitArg_one(ExmiParser.Arg_oneContext ctx) {
        return null;
    }

    @Override
    public Code visitFunction(ExmiParser.FunctionContext ctx) {
        Code code = new Code();
        Function f = program.getFunction(ctx.ID().getText(), new Position(ctx));
        List<Type> arg_types = f.getArg_list();
        Code arguments = new Code();

        if(ctx.expression().size() != arg_types.size()) {
            throw new RuntimeException("Wrong number of arguments at " + new Position(ctx));
        }

        int index = 0;
        for(ExmiParser.ExpressionContext exCtx : ctx.expression()) {
            Code exCode = visit(exCtx);
            if (!arg_types.get(index).getType().equals(exCode.getMemRegisterType().getType()) ||
                    arg_types.get(index).getRec() != exCode.getMemRegisterType().getRec()) {
                throw new RuntimeException("Type mismatch at " + new Position(exCtx));
            }
            if (index != 0) arguments.addCode(", ");
            arguments.addCode(exCode.getMemRegisterType() + exCode.getMemRegisterType().getStars() + " " + exCode.getMemRegister());
            code.addCode(exCode);
            index++;
        }

        String newId = getNewIdentifier("%obj_function");

        ST function_call = new ST("<reg> = call <rType> @<func> (<arguments>)\n");
        function_call.add("reg", newId);
        function_call.add("rType", f.getReturnType().getLlvm_type());
        function_call.add("func",  f.getName());
        function_call.add("arguments", arguments);

        code.addCode(new Code(function_call.render(), newId, f.getReturnType()));
        return code;
    }

    @Override public Code visitArglist(ExmiParser.ArglistContext ctx) {
        Code argumentList = new Code();
        List<ExmiParser.Arg_oneContext> arg_one = ctx.arg_one();
        for(int i=0; i < arg_one.size(); i++) {
            String type = arg_one.get(i).ID(0).toString();
            String name = arg_one.get(i).ID(1).toString();

            Type new_type = new Type(type, arg_one.get(i).LARR().size());
            Variable var = new Variable(name, "%" + getNewIdentifier(name), new_type);
            program.addVariable(var, new Position(ctx));
            if(i != 0) argumentList.addCode(", ");
            argumentList.addCode(var.getType() + var.getType().getStars() +  " " + var.getRegister());
        }
        return argumentList;
    }

	@Override public Code visitFunc_decl(ExmiParser.Func_declContext ctx) {
        program.addNewFunctionContext();

        Type rType = new Type(ctx.ID(0).toString());
        String name = ctx.ID(1).toString();

        Code arglist = visitArglist(ctx.arglist());
        List<Type> arglist_vars = program.getFunctionVariablesTypes();

        ST function_code = new ST("declare <rtype> @<name>(<arglist>)");

        function_code.add("rtype", rType.getLlvm_type());
        function_code.add("name", name);
        function_code.add("arglist", arglist.toString());

        Code function = new Code(function_code.render());
        program.addFunction(new Function(name, rType, arglist_vars, function), new Position(ctx));

        program.removeLastFunctionContext();
        return function;
    }

	@Override public Code visitFunc_def(ExmiParser.Func_defContext ctx) {
        program.addNewFunctionContext();

        Type rType = new Type(ctx.ID(0).toString());
        String name = ctx.ID(1).toString();

        Code arglist = visitArglist(ctx.arglist());
        List<Type> arglist_vars = program.getFunctionVariablesTypes();

        Code code_alloc_local = new Code();
        for(Variable var : program.getFunctionVariables()) {
            String newId = getNewIdentifier("%" + var.getName());
            code_alloc_local.addCode(newId + " = alloca " + var.getType() + var.getType().getStars() + "\n");
            ST store = new ST(
                    "store <type><stars> <from>, <type><stars>* <where>\n"
            );
            store.add("type", var.getType());
            store.add("stars", var.getType().getStars());
            store.add("from", var.getRegister());
            store.add("where", newId);
            code_alloc_local.addCode(store.render());
            var.setRegister(newId);
        }

        program.addFunction(new Function(name, rType, arglist_vars), new Position(ctx));
        Code code = visitCode(ctx.code());
        Code return_code = visit(ctx.expression());

        ST function_code = new ST(
                "define <rtype> @<name>(<arglist>) {\n" +
                "<alloc_local>" +
                "<code>" +
                "<return_code>" +
                "ret <return_type> <register>\n" +
                "}\n\n"
        );

        function_code.add("rtype", rType.getLlvm_type());
        function_code.add("name", name);
        function_code.add("arglist", arglist.toString());
        function_code.add("alloc_local", code_alloc_local);
        function_code.add("code", code.toString());
        function_code.add("return_code", return_code);
        function_code.add("return_type", return_code.getMemRegisterType());
        function_code.add("register", return_code.getMemRegister());
        Code function = new Code(function_code.render());

        program.removeLastFunctionContext();
        return function;
    }

	@Override public Code visitCode(ExmiParser.CodeContext ctx) {
		return visitChildren(ctx);
	}

	@Override public Code visitIf_block(ExmiParser.If_blockContext ctx) {
        Code expression = program.convertType(visit(ctx.expression()), new Type("bool"), getNewIdentifier());
        program.addNewBlock();
        Code if_block = visit(ctx.code(0));
        program.removeLastBlock();
        Code else_block = new Code();
        if(ctx.code().size() == 2) {
            program.addNewBlock();
            else_block = visit(ctx.code(1));
            program.removeLastBlock();
        }

        ST if_st = new ST(
                "  <cond>\n" +
                "  <cmp_reg> = icmp ne i1 <cond_reg>, 0\n" +
                "  br i1 <cmp_reg>, label %if.body<label_id>, label %if.else<label_id>\n" +
                "if.body<label_id>:\n" +
                "  <if_block>\n" +
                "  br label %if.done<label_id>\n" +
                "if.else<label_id>:\n" +
                "  <else_block>\n" +
                "  br label %if.done<label_id>\n" +
                "if.done<label_id>:\n");

        String cmp_reg = getNewIdentifier("%cmp_reg");
        String newId = getNewIdentifier("");

        if_st.add("cond", expression);
        if_st.add("cmp_reg", cmp_reg);
        if_st.add("cond_reg", expression.getMemRegister());
        if_st.add("label_id", newId);
        if_st.add("if_block", if_block);
        if_st.add("else_block", else_block);
        return new Code(if_st.render());
    }

    @Override
    public Code visitWhile_block(ExmiParser.While_blockContext ctx) {
        Code expression = program.convertType(visit(ctx.expression()), new Type("bool"), getNewIdentifier());
        program.addNewBlock();
        Code body_block = visit(ctx.code());
        program.removeLastBlock();

        ST body_st = new ST(
                "br label %while.cmp<label_id>\n" +
                "while.cmp<label_id>:\n" +
                "<cond>\n" +
                "<cmp_reg> = icmp ne <cond_type> <cond_reg>, 0\n" +
                "br i1 <cmp_reg>, label %while.body<label_id>, label %while.done<label_id>\n" +
                "while.body<label_id>:\n" +
                "<body_block>\n" +
                "br label %while.cmp<label_id>\n" +
                "while.done<label_id>:\n");

        String cmp_reg = getNewIdentifier("%cmp_reg");
        String newId = getNewIdentifier("");

        body_st.add("cond", expression);
        body_st.add("cmp_reg", cmp_reg);
        body_st.add("cond_reg", expression.getMemRegister());
        body_st.add("cond_type", expression.getMemRegisterType());
        body_st.add("label_id", newId);
        body_st.add("body_block", body_block);
        return new Code(body_st.render());
    }

    @Override public Code visitAssigment(ExmiParser.AssigmentContext ctx) {
        Code store_code = new Code();
        Code left_side = visit(ctx.object());
        Code expression = program.convertType(visit(ctx.expression()), left_side.getMemRegisterType(), getNewIdentifier());

        ST store = new ST(
                "store <type><stars> <from>, <where>\n"
        );
        store.add("type", expression.getMemRegisterType());
        store.add("stars", expression.getMemRegisterType().getStars());
        store.add("from", expression.getMemRegister());
        store.add("where", left_side.getMemRegister());

        store_code.addCode(expression);
        store_code.addCode(left_side);
        store_code.addCode(store.render());
        return store_code;
    }

	@Override public Code visitDeclaration_global(ExmiParser.Declaration_globalContext ctx) {
		Variable newVar = new Variable(
				ctx.ID(1).toString(),
				"@G_" + ctx.ID(1).toString(),
				ctx.ID(0).toString());
		program.addVariable(newVar,	new Position(ctx));
		return new Code(String.format("%s = global %s 0\n", newVar.getRegister(), newVar.getType()));
	}

    private Code visitDeclaration(int pos, List<Code> list, String where, String type) {
        if(pos == list.size()) return new Code();
        String stars = "";
        for(int i=pos+1; i < list.size(); i++) stars += "*";
        ST alloc = new ST(
                "<where>.ptr = alloca <type><stars>, i32 <size>\n" +
                "store <type><stars>* <where>.ptr, <type><stars>** <where>\n" +
                "%<id> = alloca i32\n" +
                "%nula<id> = add i32 0, 0\n" +
                "store i32 %nula<id>, i32* %<id>\n" +
                "br label %while.cmp<id>\n" +
                "while.cmp<id>:\n" +
                "%i<id> = load i32* %<id>\n" +
                "%cmp<id> = icmp slt i32 %i<id>, <size>\n" +
                "br i1 %cmp<id>, label %while.body<id>, label %while.done<id>\n" +
                "while.body<id>:\n" +
                "%ptr<id> = getelementptr <type><stars>* <where>.ptr, i32 %i<id>\n" +
                "<inner>" +
                "%i2<id> = load i32* %<id>\n" +
                "%i3<id> = add i32 1, %i2<id>\n" +
                "store i32 %i3<id>, i32* %<id>\n" +
                "br label %while.cmp<id>\n" +
                "while.done<id>:\n"
        );
        String id = getNewIdentifier("");
        alloc.add("id", id);
        alloc.add("where", where);
        alloc.add("size", list.get(pos).getMemRegister());
        alloc.add("stars", stars);
        alloc.add("type", type);
        alloc.add("inner", visitDeclaration(pos+1, list, "%ptr" + id, type));

        return new Code(alloc.render());
    }

	@Override public Code visitDeclaration(ExmiParser.DeclarationContext ctx) {
        if(ctx.expression().size() != 0) {
            Code code_expressions = new Code();
            String name = ctx.ID(1).toString();
            String type = ctx.ID(0).toString();
            Variable var = new Variable(name, getNewIdentifier("%array"), new Type(type, ctx.expression().size()));

            List<Code> list = new ArrayList<>();
            for(int i=0; i < ctx.expression().size(); i++) {
                Code code = visit(ctx.expression(i));
                code_expressions.addCode(code);
                list.add(code);
            }

            code_expressions.addCode(var.getRegister() + " = alloca " + var.getType() + var.getType().getStars() + "\n");
            Code code = visitDeclaration(0, list, var.getRegister(), new Type(type).getLlvm_type());
            code_expressions.addCode(code);
            code.setMemRegister(var.getRegister(), var.getType());
            program.addVariable(var, new Position(ctx));
            return code_expressions;
        }
        else {
            Variable newVar = new Variable(
                    ctx.ID(1).toString(),
                    "%" + getNewIdentifier(ctx.ID(1).toString()),
                    ctx.ID(0).toString());
            program.addVariable(newVar, new Position(ctx));

            return new Code(String.format("%s = alloca %s\n", newVar.getRegister(), newVar.getType()));
        }
	}

	@Override public Code visitPar(ExmiParser.ParContext ctx) { return visitChildren(ctx); }

    public Code visitExpression(Code left, Code right, String operator) {
        right = program.convertType(right, left.getMemRegisterType(), getNewIdentifier());

        String instruction = "";
        switch (operator) {
            case "+":
                instruction = "add";
                break;
            case "-":
                instruction = "sub";
                break;
            case "*":
                instruction = "mul";
                break;
            case "/":
                instruction = "sdiv";
                break;
            case "%":
                instruction = "urem";
                break;
            case "^":
                String newId = getNewIdentifier("%iexp");
                left = program.convertType(left, new Type("int"), getNewIdentifier());
                right = program.convertType(right, new Type("int"), getNewIdentifier());
                Code code = new Code(newId + " = call i32 @iexp(i32 " +
                        left.getMemRegister() + ", i32 "+ right.getMemRegister() + ")\n", newId, new Type("int"));
                left.addCode(right);
                left.addCode(code);
                return left;
            case "&&":
                instruction = "and";
                break;
            case "||":
                instruction = "or";
                break;
        }

        Code expression = new Code();
        expression.addCode(left);
        expression.addCode(right);
        ST call = new ST(
                "<ret> = <instr> <type> <left>, <right>\n"
        );
        String newId = getNewIdentifier("%op_" + instruction);
        call.add("ret", newId);
        call.add("instr", instruction);
        call.add("type", left.getMemRegisterType());
        call.add("left", left.getMemRegister());
        call.add("right", right.getMemRegister());

        expression.addCode(new Code(call.render(), newId, left.getMemRegisterType()));
        return expression;
    }

	@Override public Code visitAdd(ExmiParser.AddContext ctx) {
        return visitExpression(visit(ctx.expression(0)), visit(ctx.expression(1)), ctx.op.getText());
    }

	@Override public Code visitOr(ExmiParser.OrContext ctx) {
        return visitExpression(visit(ctx.expression(0)), visit(ctx.expression(1)), ctx.op.getText());
    }

	@Override public Code visitMul(ExmiParser.MulContext ctx) {
        return visitExpression(visit(ctx.expression(0)), visit(ctx.expression(1)), ctx.op.getText());
    }

    @Override public Code visitAnd(ExmiParser.AndContext ctx) {
        return visitExpression(visit(ctx.expression(0)), visit(ctx.expression(1)), ctx.op.getText());
    }

    @Override public Code visitExp(ExmiParser.ExpContext ctx) {
        return visitExpression(visit(ctx.expression(0)), visit(ctx.expression(1)), ctx.op.getText());
    }

    @Override
    public Code visitComment(ExmiParser.CommentContext ctx) {
        return null;
    }

    @Override public Code visitMod(ExmiParser.ModContext ctx) {
        return visitExpression(visit(ctx.expression(0)), visit(ctx.expression(1)), ctx.op.getText());
    }

    @Override public Code visitVar(ExmiParser.VarContext ctx) {
        Code object = visit(ctx.object());
        String newId = getNewIdentifier("%visitVar");
        Code code = new Code(newId + " = load " +  object.getMemRegister() + "\n", newId, object.getMemRegisterType());
        object.addCode(code);
        return object;
    }

	@Override public Code visitUna(ExmiParser.UnaContext ctx) {
        switch (ctx.op.getText()) {
            case "-":
                Code code = visit(ctx.expression());
                String newId = getNewIdentifier("%una");
                code.addCode(new Code(
                        newId + " = sub " + code.getMemRegisterType() + " 0, " + code.getMemRegister() + "\n",
                        newId,
                        code.getMemRegisterType()
                ));
                return code;
            case "+":
                return visit(ctx.expression());
        }
        return null;
    }

	@Override public Code visitString(ExmiParser.StringContext ctx) {
        String newId = getNewIdentifier("%string");
        int len = ctx.STRING().getText().length() - 1;
        Code code = new Code(newId + "= alloca i8, i32 " + len + "\n", newId, new Type("char", 1));

        String str = ctx.STRING().getText();
        for(int i=1; i < str.length(); i++) {
            char ch = i != len ? str.charAt(i) : 0;
            code.addCode(newId + "." + (i-1) + " = getelementptr i8* " + newId + ", i32 " + (i-1) + "\n");
            code.addCode("store i8 " + (int) ch + ", i8* " + newId + "." + (i-1) + "\n");
        }

        return code;
    }

    @Override
    public Code visitCmp(ExmiParser.CmpContext ctx) {
        Code left = visit(ctx.expression(0));
        Code right = visit(ctx.expression(1));

        String cmp = "";
        switch (ctx.op.getText()) {
            case "==": cmp = "eq"; break;
            case "!=": cmp = "ne"; break;
            case "<": cmp = "slt"; break;
            case ">": cmp = "sgt"; break;
            case "<=": cmp = "sle"; break;
            case ">=": cmp = "sge"; break;
        }
        right = program.convertType(right, left.getMemRegisterType(), getNewIdentifier());

        Code code = new Code();
        code.addCode(left);
        code.addCode(right);
        String newId = getNewIdentifier("%eq");
        code.addCode(newId + " = icmp "+ cmp + " " + left.getMemRegisterType() + " " + left.getMemRegister() + ", " +
                right.getMemRegister() + "\n");
        code.setMemRegister(newId, new Type("bool"));
        return code;
    }

    @Override public Code visitInt(ExmiParser.IntContext ctx) {
        String newId = getNewIdentifier("%int");
        return new Code(newId + " = add i32 0, " + ctx.getText() + "\n", newId, new Type("int"));
    }

    @Override public Code visitBool(ExmiParser.BoolContext ctx) {
        String newId = getNewIdentifier("%bool");
        int val = ctx.getText().equals("TRUE") ? 1 : 0;
        return new Code(newId + " = add i1 0, " + val + "\n", newId, new Type("bool"));
    }

    @Override public Code visitChar(ExmiParser.CharContext ctx) {
        String newId = getNewIdentifier("%char");
        Code code = new Code(newId + " = add i8 0, " +
                (int) ctx.getText().charAt(1) + "\n", newId, new Type("char"));
        return code;
    }

    private int counter = 0;
    private String getNewIdentifier() {
        return this.getNewIdentifier("%def");
    }

    private String getNewIdentifier(String prefix) {
        return prefix + "_" +Integer.toString(counter++);
    }

    protected Code defaultResult() {
        return new Code();
    }

    protected Code aggregateResult(Code aggregate, Code nextResult) {
		if (aggregate == null) {
			aggregate = new Code();
		}
		aggregate.addCode(nextResult);
		return aggregate;
	}

}