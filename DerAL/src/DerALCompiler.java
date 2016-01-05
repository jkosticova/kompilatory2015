import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupFile;

public class DerALCompiler extends DerALBaseVisitor<CodeFragment>{
	private DerALCompilerGlobalState globalCompilerState;
	private STGroup templates = new STGroupFile("templates.stg");

	@Override
	public CodeFragment visitSourceCode(DerALParser.SourceCodeContext ctx) {
		globalCompilerState = new DerALCompilerGlobalState();
		
		ST template = templates.getInstanceOf("program");
		CodeFragment codeOfLLVMMain = new CodeFragment();
		
		codeOfLLVMMain.addCode(visit(ctx.declarations()));	
		globalCompilerState.line++;
		codeOfLLVMMain.addCode(visit(ctx.mainProgram()));
		template.add("code", codeOfLLVMMain);
		template.add("global_var_defs", globalCompilerState.globalVariablesDeclarationsLLVMCode);
		
		checkIfForwardDeclaredFunctionsAreDefined();
		String funcDefsLLVM = "";
		for(FunctionInfo fi : globalCompilerState.definedFunctionsList){
			funcDefsLLVM += getLLVMCodeForDeclaredFunction(fi);
		}
		template.add("func_defs", funcDefsLLVM);
		
		String externFuncDeclarationsLLVM = "";
		for(FunctionInfo fi : globalCompilerState.externFunctionsList){
			externFuncDeclarationsLLVM += getLLVMCodeForExternFunction(fi);
		}
		template.add("extern_func_declarations", externFuncDeclarationsLLVM);

		if(!globalCompilerState.wasAnError){
			CodeFragment compilated = new CodeFragment();
			compilated.setCode(template.render());
			return compilated;
		}
		else{
			return new CodeFragment();
		}
	}
	
	private String getLLVMCodeForExternFunction(FunctionInfo fi) {
		String llvm = "declare " + Type.getLLVMTypeFromType(fi.retType) + " @" + fi.name + "(";
		
		for(int i=0;i<fi.args.size();i++){
			if(i!=0){
				llvm += ", ";
			}
			llvm += Type.getLLVMTypeFromType(fi.args.get(i).type);
		}
		
		llvm += ")\n";
		return llvm;
	}

	private void checkIfForwardDeclaredFunctionsAreDefined(){
		for(FunctionInfo declaredFunctionInfo : globalCompilerState.forwardDeclaredFunctionsList){
			if(!globalCompilerState.isDeclaredFunctionDefined(declaredFunctionInfo)){
				globalCompilerState.reportError("Funkcia " + declaredFunctionInfo.getFunctionSignature() + " bola deklaruvaná, ale nide neni definuvaná.", false);
			}
		}
	}
	
	private int countCharsInString(String str, char c){
		int count = 0;
		
		for(int i=0;i<str.length();i++){
			if(c == str.charAt(i)){
				count++;
			}
		}
		
		return count;
	}

	@Override
	public CodeFragment visitDeclarations(DerALParser.DeclarationsContext ctx) {
		CodeFragment ret = new CodeFragment();
		if(ctx.children != null){
			for(ParseTree decl : ctx.children){
				if(decl instanceof DerALParser.DeclarationContext){
					ret.addCode(visit(decl));
				}
				else if(decl instanceof TerminalNode){
					TerminalNode nlOrComment = (TerminalNode)decl;
					globalCompilerState.line += countCharsInString(nlOrComment.getText(), '\n');
				}
			}
		}
		return ret;
	}
	
	@Override 
	public CodeFragment visitDeclarationGlobalVar(DerALParser.DeclarationGlobalVarContext ctx) { 
		return getCodeFragForVariableDeclaration(ctx.variableDeclaration(), true);
	}


	@Override
	public CodeFragment visitMainProgram(DerALParser.MainProgramContext ctx) {
		Type mainRetType = new Type();
		mainRetType.type = Type.TypeEnum.INT;
		
		globalCompilerState.localVarStack.enterCodeBlock();
		CodeFragment ret = getCodeFragForStatements(ctx.statements(), mainRetType);
		globalCompilerState.localVarStack.exitCodeBlock();
		
		return ret;
	}
	
	/*
	 * statement 	: variableDeclaration Semicolon		# statementVarDeclaration
			| assignment Semicolon				# statementAssignment
			| loopStatement						# statementLoop
			| condStatement						# statementCond
			| Return expression? Semicolon 		# statementReturn
			| compoundStatement					# statementCompound
			| expression Semicolon				# statementExpression
			| Semicolon 						# statementSemicol 
			;
	 * */
	private CodeFragment getCodeFragForStatement(DerALParser.StatementContext stCtx, Type functionRetType){
		if(stCtx instanceof DerALParser.StatementVarDeclarationContext){
			return visitStatementVarDeclaration((DerALParser.StatementVarDeclarationContext)stCtx);
		}
		else if(stCtx instanceof DerALParser.StatementAssignmentContext){
			return visitStatementAssignment((DerALParser.StatementAssignmentContext)stCtx);
		}
		else if(stCtx instanceof DerALParser.StatementCompoundContext){
			return getCodeFragForCompoundStatement((DerALParser.StatementCompoundContext)stCtx, functionRetType);
		}
		else if(stCtx instanceof DerALParser.StatementExpressionContext){
			return visitStatementExpression((DerALParser.StatementExpressionContext)stCtx);
		}
		else if(stCtx instanceof DerALParser.StatementReturnContext){
			return getCodeFragForReturnStatement((DerALParser.StatementReturnContext)stCtx, functionRetType);
		}
		else if(stCtx instanceof DerALParser.StatementCondContext){
			return getCodeFragForCondStatement((DerALParser.StatementCondContext)stCtx, functionRetType);
		}
		else if(stCtx instanceof DerALParser.StatementLoopContext){
			return getCodeFragForLoopStatement(((DerALParser.StatementLoopContext)stCtx).loopStatement(), functionRetType);
		}
		
		System.err.println("este nedefinovane riesenie statementu " + stCtx.getText());
		return new CodeFragment();
	}
	
	private CodeFragment getCodeFragForStatements(DerALParser.StatementsContext ctx, Type functionRetType){
		CodeFragment statementsCode = new CodeFragment();
		
		if(ctx.children != null){
			for(ParseTree statementOrNewlineOrComment : ctx.children){
				if(statementOrNewlineOrComment instanceof DerALParser.StatementContext){
					DerALParser.StatementContext statement = (DerALParser.StatementContext)statementOrNewlineOrComment;
					statementsCode.addCode(getCodeFragForStatement(statement, functionRetType));
				}
				else if(statementOrNewlineOrComment instanceof TerminalNode){
					TerminalNode nlOrComment = (TerminalNode)statementOrNewlineOrComment;
					globalCompilerState.line += countCharsInString(nlOrComment.getText(), '\n');
				}	
			}
		}
		
		return statementsCode;
	}
	
	private CodeFragment getCodeFragForLoopStatement(DerALParser.LoopStatementContext ctx, Type functionRetType){
		if(ctx instanceof DerALParser.LoopWhileContext){
			return getCodeFragForWhileLoop((DerALParser.LoopWhileContext)ctx,functionRetType);
		}
		else if(ctx instanceof DerALParser.LoopForContext){
			return getCodeFragForForLoop((DerALParser.LoopForContext)ctx,functionRetType);
		}
		else{
			DerALCompilerGlobalState.reportSomethingStrange("Voláky čunný cyklus...");
			return new CodeFragment();
		}
	}
	
	/*
	 * 	For LeftParenthesis forInitStatement Semicolon loopCheckStatement Semicolon forLoopStatement RightParenthesis Newline* LeftBrace statements RightBrace
	 *  loopCheckStatement	: expression? ;
		forInitStatement 	: variableDeclaration	# forInitVarDecl
							| assignment 			# forInitAssignment
							| 						# forInitEmpty
							;

		forLoopStatement 	: assignment			# forLoopStatementAssignment
							| 						# forLoopStatementEmpty
	 * */	
	private CodeFragment getCodeFragForForLoop(DerALParser.LoopForContext ctx, Type functionRetType){
		ST forTpl = templates.getInstanceOf("for");
		
		globalCompilerState.localVarStack.enterCodeBlock();
		
		forTpl.add("namesIndex", globalCompilerState.getNextFreeRegister());
		forTpl.add("initLLVM", visit(ctx.forInitStatement()));
		
		if(ctx.loopCheckStatement().expression() != null){
			CodeFragment condExprCodeFrag = visit(ctx.loopCheckStatement().expression());
			
			if(!isTypeSuitableAsConditional(condExprCodeFrag.getResultType())){
				globalCompilerState.reportError("V podménkách móžeš používať iba boolovské výrazy a ty si použeu výraz typu " + condExprCodeFrag.getResultType().toString() 
						+ " (výraz: " + ctx.loopCheckStatement().expression().getText() + ")");
			}
			
			forTpl.add("condExpr", condExprCodeFrag);
			forTpl.add("condResReg", condExprCodeFrag.getResultRegister());
		}
		
		forTpl.add("loopStatementLLVM", visit(ctx.forLoopStatement()));
		
		globalCompilerState.line += ctx.Newline().size();
		
		forTpl.add("body", getCodeFragForStatements(ctx.statements(), functionRetType));
		globalCompilerState.localVarStack.exitCodeBlock();
		CodeFragment forCodeFrag = new CodeFragment();
		forCodeFrag.addCode(forTpl.render());
		return forCodeFrag;
	}
	
	//While LeftParenthesis loopCheckStatement RightParenthesis Newline* LeftBrace statements RightBrace
	private CodeFragment getCodeFragForWhileLoop(DerALParser.LoopWhileContext ctx, Type functionRetType){
		ST whileTpl = templates.getInstanceOf("while");
		whileTpl.add("namesIndex", globalCompilerState.getNextFreeRegister());
		
		if(ctx.loopCheckStatement().expression() != null){
			CodeFragment condExprCodeFrag = visit(ctx.loopCheckStatement().expression());
			
			if(!isTypeSuitableAsConditional(condExprCodeFrag.getResultType())){
				globalCompilerState.reportError("V podménkách móžeš používať iba boolovské výrazy a ty si použeu výraz typu " + condExprCodeFrag.getResultType().toString() 
						+ " (výraz: " + ctx.loopCheckStatement().expression().getText() + ")");
			}
			
			whileTpl.add("condExpr", condExprCodeFrag);
			whileTpl.add("condResReg", condExprCodeFrag.getResultRegister());
		}
		
		globalCompilerState.line += ctx.Newline().size();
		
		globalCompilerState.localVarStack.enterCodeBlock();	
		whileTpl.add("body", getCodeFragForStatements(ctx.statements(), functionRetType));;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
		globalCompilerState.localVarStack.exitCodeBlock();
		
		CodeFragment whileCodeFrag = new CodeFragment();
		whileCodeFrag.addCode(whileTpl.render());
		return whileCodeFrag;
	}

	@Override
	public CodeFragment visitDeclarationSemicol(DerALParser.DeclarationSemicolContext ctx) {
		return new CodeFragment();
	}

	@Override
	public CodeFragment visitStatementVarDeclaration(DerALParser.StatementVarDeclarationContext ctx) {
		return getCodeFragForVariableDeclaration(ctx.variableDeclaration(), false);
	}

	@Override
	public CodeFragment visitStatementAssignment(DerALParser.StatementAssignmentContext ctx) {
		return visit(ctx.assignment());
	}

	@Override
	public CodeFragment visitStatementExpression(DerALParser.StatementExpressionContext ctx) {
		return visit(ctx.expression());
	}

	@Override
	public CodeFragment visitStatementSemicol(DerALParser.StatementSemicolContext ctx) {
		return new CodeFragment();
	}

	@Override
	public CodeFragment visitForInitVarDecl(DerALParser.ForInitVarDeclContext ctx) {
		return getCodeFragForVariableDeclaration(ctx.variableDeclaration(),false);
	}

	@Override
	public CodeFragment visitForInitAssignment(DerALParser.ForInitAssignmentContext ctx) {
		return visitAssignment(ctx.assignment());
	}

	@Override
	public CodeFragment visitForInitEmpty(DerALParser.ForInitEmptyContext ctx) {
		return new CodeFragment();
	}

	@Override
	public CodeFragment visitForLoopStatementAssignment(DerALParser.ForLoopStatementAssignmentContext ctx) {
		return visitAssignment(ctx.assignment());
	}

	@Override
	public CodeFragment visitForLoopStatementEmpty(DerALParser.ForLoopStatementEmptyContext ctx) {
		return new CodeFragment();
	}
	
	private boolean isTypeSuitableAsConditional(Type type){
		return type.arrayLevel == 0 && type.type == Type.TypeEnum.BOOL;
	}

	//condStatement	: If LeftParenthesis expression RightParenthesis Newline* statement (Newline* Else Newline* statement)?;
	public CodeFragment getCodeFragForCondStatement(DerALParser.StatementCondContext ctx, Type functionRetType) {
		CodeFragment condExprCodeFrag = visit(ctx.condStatement().expression());
		
		if(!isTypeSuitableAsConditional(condExprCodeFrag.getResultType())){
			globalCompilerState.reportError("V podménkách móžeš používať iba boolovské výrazy a ty si použeu výraz typu " + condExprCodeFrag.getResultType().toString() 
					+ " (výraz: " + ctx.condStatement().expression().getText() + ")");
		}
		
		ST ifTpl = templates.getInstanceOf("if");
		ifTpl.add("condExpr", condExprCodeFrag);
		ifTpl.add("condResReg", condExprCodeFrag.getResultRegister());
		ifTpl.add("namesIndex", globalCompilerState.getNextFreeRegister());
		
		int statementChildInd = 0;
		while(true){
			if(ctx.condStatement().getChild(statementChildInd) instanceof DerALParser.StatementContext){
				statementChildInd++;
				break;
			}
			if(ctx.condStatement().getChild(statementChildInd) instanceof TerminalNode){
				TerminalNode tn = (TerminalNode)ctx.condStatement().getChild(statementChildInd);
				if(tn.getSymbol().getType() == DerALLexer.Newline){
					globalCompilerState.line++;
				}
			}
			statementChildInd++;
		}
		
		CodeFragment trueStatementCodeFrag = getCodeFragForStatement(ctx.condStatement().statement(0), functionRetType);
		ifTpl.add("trueLLVMCode", trueStatementCodeFrag);
		
		if(ctx.condStatement().Else() != null){
			while(true){
				if(ctx.condStatement().getChild(statementChildInd) instanceof DerALParser.StatementContext){
					statementChildInd++;
					break;
				}
				if(ctx.condStatement().getChild(statementChildInd) instanceof TerminalNode){
					TerminalNode tn = (TerminalNode)ctx.condStatement().getChild(statementChildInd);
					if(tn.getSymbol().getType() == DerALLexer.Newline){
						globalCompilerState.line++;
					}
				}
				statementChildInd++;
			}
			
			CodeFragment falseStatementCodeFrag = getCodeFragForStatement(ctx.condStatement().statement(1), functionRetType);
			ifTpl.add("falseLLVMCode", falseStatementCodeFrag);
			ifTpl.add("isElse", true);
		}
		
		CodeFragment ifCodeFrag = new CodeFragment();
		ifCodeFrag.addCode(ifTpl.render());
		return ifCodeFrag;
	}
	
	private CodeFragment getCodeFragForSimpleAssignNoIndexingBasicVarType(VariableInfo varInfo, boolean isGlobal, 
			DerALParser.ExpressionContext expression) {
		CodeFragment codeFragForSimpleAssign = new CodeFragment();
		
		CodeFragment expressionCode = visit(expression);
		
		if(!isTypeSuitableForAssignment(varInfo.type, expressionCode.getResultType())){
			globalCompilerState.reportError("Snažíš sa priradiť do premennej "+ varInfo.name + " typu " + varInfo.type.toString() 
				+ " výraz typu " + expressionCode.getResultType());
			return new CodeFragment();
		}
		
		codeFragForSimpleAssign.addCode(expressionCode);

		ST varAssignCodeTpl = templates.getInstanceOf("var_assign");
		varAssignCodeTpl.add("llvmVarName", varInfo.nameInLLVMCode);
		varAssignCodeTpl.add("valueRegister", expressionCode.getResultRegister());
		varAssignCodeTpl.add("llvmVarType", Type.getLLVMTypeFromType(varInfo.type));
		
		if(varInfo.type.arrayLevel == 0 && expressionCode.getResultType().arrayLevel == 0 
				&& varInfo.type.type == Type.TypeEnum.INT && expressionCode.getResultType().type == Type.TypeEnum.CHAR){
			varAssignCodeTpl.add("valI8toI32", "1");
			varAssignCodeTpl.add("regForCasted", globalCompilerState.getNextFreeRegister());
		}
		else if(varInfo.type.arrayLevel == 0 && expressionCode.getResultType().arrayLevel == 0 
				&& varInfo.type.type == Type.TypeEnum.CHAR && expressionCode.getResultType().type == Type.TypeEnum.INT){
			varAssignCodeTpl.add("valI32toI8", "1");
			varAssignCodeTpl.add("regForCasted", globalCompilerState.getNextFreeRegister());
		}
		else{
			varAssignCodeTpl.add("noCast", "1");
		}
		
		varAssignCodeTpl.add("isGlobal", isGlobal);
		
		codeFragForSimpleAssign.addCode(varAssignCodeTpl.render());
		
		return codeFragForSimpleAssign;
	}
	
	private CodeFragment getCodeFragForSimpleAssignNoIndexingArrayVarType(VariableInfo varInfo, boolean isGlobal, 
			DerALParser.ExpressionContext expression){
		CodeFragment assignCodeFrag = new CodeFragment();
		
		CodeFragment exprCodeFrag = visit(expression);
		
		if(!isTypeSuitableForAssignment(varInfo.type, exprCodeFrag.getResultType())){
			globalCompilerState.reportError("Snažíš sa priradiť do premennej " + varInfo.name + " typu " 
					+ varInfo.type.toString() + " výraz typu " + exprCodeFrag.getResultType().toString());
			return new CodeFragment();
		}
		
		if(exprCodeFrag.getResultType().type == Type.TypeEnum.ARRAY_ALLOC){
			CodeFragment arrayAllocCodeFrag = getCodeFragForArrayAllocationOfVar(exprCodeFrag, 
					exprCodeFrag.getResultType().arrayLevel, varInfo.nameInLLVMCode, varInfo.type, isGlobal);
			assignCodeFrag.addCode(arrayAllocCodeFrag);
		}
		else{
			assignCodeFrag.addCode(exprCodeFrag);
			ST assignTpl = templates.getInstanceOf("var_assign");
			assignTpl.add("llvmVarType", Type.getLLVMTypeFromType(varInfo.type));
			assignTpl.add("valueRegister", exprCodeFrag.getResultRegister());
			assignTpl.add("llvmVarName", varInfo.nameInLLVMCode);
			assignTpl.add("noCast", "1");
			if(isGlobal){
				assignTpl.add("isGlobal", "1");
			}
			assignCodeFrag.addCode(assignTpl.render());
		}
		
		return assignCodeFrag;
	}
	
	private CodeFragment getCodeFragForSimpleAssignIndexing(VariableInfo varInfo, boolean isGlobal,
			List<DerALParser.ArrayIndexContext> arrayIndexList, DerALParser.ExpressionContext expression){
		if(varInfo.type.arrayLevel < arrayIndexList.size()){
			globalCompilerState.reportError("Príliš vela indexov do funduša " + varInfo.name + ". Funduš je dimenzie " 
					+ Integer.toString(varInfo.type.arrayLevel));
			return new CodeFragment();
		}
		
		CodeFragment assignCodeFrag = new CodeFragment();
		
		CodeFragment ptrToIndexedCellCodeFrag = 
				getCodeFragForGetPtrToIndexedArrayVarCell(varInfo, isGlobal,arrayIndexList);
		CodeFragment exprCodeFrag = visit(expression);
		
		if(!isTypeSuitableForAssignment(ptrToIndexedCellCodeFrag.getResultType(), exprCodeFrag.getResultType())){
			globalCompilerState.reportError("Priradenie výrazu typu " + exprCodeFrag.getResultType().toString() + 
					" do premennej typu " + ptrToIndexedCellCodeFrag.getResultType().toString());
			return new CodeFragment();
		}
		
		assignCodeFrag.addCode(ptrToIndexedCellCodeFrag);
		
		VariableInfo pseudoVarIndexedInfo = new VariableInfo();
		pseudoVarIndexedInfo.name = varInfo.name;
		pseudoVarIndexedInfo.nameInLLVMCode = ptrToIndexedCellCodeFrag.getResultRegister();
		pseudoVarIndexedInfo.type = ptrToIndexedCellCodeFrag.getResultType();
		
		assignCodeFrag.addCode(getCodeFragForSimpleAssignNoIndexing(pseudoVarIndexedInfo, false, expression));
		
		return assignCodeFrag;
	}
	
	private CodeFragment getCodeFragForSimpleAssignNoIndexing(VariableInfo varInfo, boolean isGlobal, 
			DerALParser.ExpressionContext expression){
		if(varInfo.type.arrayLevel == 0){
			return getCodeFragForSimpleAssignNoIndexingBasicVarType(varInfo,isGlobal, expression);
		}
		else{
			return getCodeFragForSimpleAssignNoIndexingArrayVarType(varInfo,isGlobal, expression);
		}
	}
	
	private CodeFragment getCodeFragForSimpleAssign(VariableInfo varInfo, boolean isGlobal,
			List<DerALParser.ArrayIndexContext> arrayIndexList, DerALParser.ExpressionContext expression){
		if(arrayIndexList == null || arrayIndexList.isEmpty()){
			return getCodeFragForSimpleAssignNoIndexing(varInfo,isGlobal, expression);
		}
		else{
			return getCodeFragForSimpleAssignIndexing(varInfo, isGlobal,arrayIndexList, expression);
		}
	}
	
	private CodeFragment getGodeFragForOpAssignNoIndexing(int assignOpType, VariableInfo varInfo, boolean isGlobal, 
			DerALParser.ExpressionContext expression) {
		CodeFragment exprCodeFrag = visit(expression);
		
		if(!areTypesSuitableForArithmeticRelExpression(getOpTypeForAssignOp(assignOpType), 
				varInfo.type, exprCodeFrag.getResultType())){
			globalCompilerState.reportError("Špatný typ priraďuvaného výrazu: " + exprCodeFrag.getResultType().toString());
			return new CodeFragment();
		}
		
		CodeFragment opAssignCodeFrag = new CodeFragment();
		
		ST opAssignTpl = templates.getInstanceOf("op_assign");
		opAssignTpl.add("varNameLLVM", varInfo.nameInLLVMCode);
		opAssignTpl.add("varValReg", globalCompilerState.getNextFreeRegister());
		opAssignTpl.add("varTypeLLVM", Type.getLLVMTypeFromType(varInfo.type));
		opAssignTpl.add("isGlobal", isGlobal);
		opAssignTpl.add("expr", exprCodeFrag);
		opAssignTpl.add("exprResReg", exprCodeFrag.getResultRegister());
		opAssignTpl.add("regToStoreUpdatedVal", globalCompilerState.getNextFreeRegister());
		opAssignTpl.add("llvmOp", getLLVMOp(getOpTypeForAssignOp(assignOpType), varInfo.type.type == Type.TypeEnum.DOUBLE));
		opAssignTpl.add("isDouble", varInfo.type.type == Type.TypeEnum.DOUBLE);
		
		if(varInfo.type.type == Type.TypeEnum.CHAR && exprCodeFrag.getResultType().type == Type.TypeEnum.INT){
			opAssignTpl.add("castExprI32toI8", "1");
			opAssignTpl.add("regToStoreCasted", globalCompilerState.getNextFreeRegister());
		}
		else if(varInfo.type.type == Type.TypeEnum.INT && exprCodeFrag.getResultType().type == Type.TypeEnum.CHAR){
			opAssignTpl.add("castExprI8toI32", "1");
			opAssignTpl.add("regToStoreCasted", globalCompilerState.getNextFreeRegister());
		}
		
		opAssignCodeFrag.addCode(opAssignTpl.render());
		return opAssignCodeFrag;
	}
	
	private CodeFragment getGodeFragForOpAssignIndexing(int assignOpType, VariableInfo varInfo, boolean isGlobal, 
			DerALParser.ExpressionContext expression, List<DerALParser.ArrayIndexContext> arrayIndexList){
		if(varInfo.type.arrayLevel < arrayIndexList.size()){
			globalCompilerState.reportError("Príliš vela indexov do funduša " + varInfo.name + ". Funduš je dimenzie " 
					+ Integer.toString(varInfo.type.arrayLevel));
			return new CodeFragment();
		}
		if(varInfo.type.arrayLevel > arrayIndexList.size()){
			globalCompilerState.reportError("Priraďuvaňé spojené s operáciú možno používať len na premenné nefundušového typu");
			return new CodeFragment();
		}
		
		CodeFragment opAssignCodeFrag = new CodeFragment();
		
		CodeFragment ptrToIndexedCellCodeFrag = 
				getCodeFragForGetPtrToIndexedArrayVarCell(varInfo, isGlobal,arrayIndexList);
		CodeFragment exprCodeFrag = visit(expression);
		
		if(!isTypeSuitableForAssignment(ptrToIndexedCellCodeFrag.getResultType(), exprCodeFrag.getResultType())){
			globalCompilerState.reportError("Priradenie výrazu typu " + exprCodeFrag.getResultType().toString() + 
					" do premennej typu " + ptrToIndexedCellCodeFrag.getResultType().toString());
			return new CodeFragment();
		}
		
		opAssignCodeFrag.addCode(ptrToIndexedCellCodeFrag);
		
		VariableInfo pseudoVarIndexedInfo = new VariableInfo();
		pseudoVarIndexedInfo.name = varInfo.name;
		pseudoVarIndexedInfo.nameInLLVMCode = ptrToIndexedCellCodeFrag.getResultRegister();
		pseudoVarIndexedInfo.type = ptrToIndexedCellCodeFrag.getResultType();
		
		opAssignCodeFrag.addCode(getGodeFragForOpAssignNoIndexing(assignOpType, pseudoVarIndexedInfo, false, expression));
		
		return opAssignCodeFrag;
	}
	
	private CodeFragment getCodeFragForCompoundStatement(DerALParser.StatementCompoundContext stCtx, Type functionRetType){
		globalCompilerState.localVarStack.enterCodeBlock();
		CodeFragment ret = getCodeFragForStatements(stCtx.compoundStatement().statements(), functionRetType);
		globalCompilerState.localVarStack.exitCodeBlock();
		return ret;
	}
	
//assignment : Identifier arrayIndex* (assignOp=(Assign|PlusAssign|MinusAssign|StarAssign|DivAssign|ModAssign)) expression;
	@Override
	public CodeFragment visitAssignment(DerALParser.AssignmentContext ctx) {
		String varName = ctx.Identifier().getText();
		VariableInfo localVarInfo = globalCompilerState.localVarStack.findLocalVar(varName);
		VariableInfo globalVarInfo = globalCompilerState.findGlobalVarInfo(varName);
		
		if(localVarInfo==null && globalVarInfo==null){
			globalCompilerState.reportError("Premenná " + varName + " nebola nide deklaruvaná.");
			return new CodeFragment();
		}
		
		VariableInfo varInfo = localVarInfo;
		boolean isGlobal = false;;
		if(localVarInfo == null){
			varInfo = globalVarInfo;
			isGlobal = true;
		}
		
		if(ctx.assignOp.getType() == DerALLexer.Assign){
			return getCodeFragForSimpleAssign(varInfo, isGlobal, ctx.arrayIndex() ,ctx.expression());
		}
		else{
			if(ctx.arrayIndex().isEmpty()){
				return getGodeFragForOpAssignNoIndexing(ctx.assignOp.getType(), varInfo, isGlobal, ctx.expression());
			}
			else{
				return getGodeFragForOpAssignIndexing(ctx.assignOp.getType(), varInfo, isGlobal, ctx.expression(), ctx.arrayIndex());
			}
		}
	}
	
	private int getOpTypeForAssignOp(int assignOpType){
		if(assignOpType == DerALLexer.DivAssign){
			return DerALLexer.Div;
		}
		else if(assignOpType == DerALLexer.MinusAssign){
			return DerALLexer.Minus;
		}
		else if(assignOpType == DerALLexer.StarAssign){
			return DerALLexer.Star;
		}
		else if(assignOpType == DerALLexer.PlusAssign){
			return DerALLexer.Plus;
		}
		else if(assignOpType == DerALLexer.ModAssign){
			return DerALLexer.Mod;
		}
		else{
			DerALCompilerGlobalState.reportSomethingStrange("Snažím sa získať opType z assignemntOpType pre nejakú čudnú assignmentOp");
			return 578;
		}
	}

	@Override
	public CodeFragment visitVoidTypeSpecification(DerALParser.VoidTypeSpecificationContext ctx) {
		return super.visitVoidTypeSpecification(ctx);
	}
	
	private boolean isTypeSuitableForAssignment(Type typeOfVar, Type typeOfAssignedExpr){
		return (typeOfVar.arrayLevel == 0 && typeOfAssignedExpr.arrayLevel == 0 
				&& isTypeIntegral(typeOfVar.type) && isTypeIntegral(typeOfAssignedExpr.type)) 
				||
				(typeOfVar.arrayLevel >0 && typeOfAssignedExpr.type == Type.TypeEnum.ARRAY_ALLOC 
				&& typeOfAssignedExpr.arrayLevel <= typeOfVar.arrayLevel)
				||
				(typeOfVar.equals(typeOfAssignedExpr));
	}
	
	
	/**
	 * typeSpecification	: typeName arrayIndex+					# arrayAllocTypeSpecification
					| typeName (LeftBracket RightBracket)+	# arrayNoAllocTypeSpecification
					| typeName								# basicTypeSpecification
					| Void 									# voidTypeSpecification
					;
					
variableDeclaration	: typeSpecification Identifier (Assign expression)?;
	 * */
	private CodeFragment getCodeFragForBasicTypeVarDeclaration(String varName, DerALParser.BasicTypeSpecificationContext btCtx,
			boolean assign, DerALParser.ExpressionContext assignExprCtx, boolean isGlobal){
		if(btCtx.typeName().String() != null){
			return getCodeFragForArrayNoAllocTypeVarDeclaration(varName, btCtx, assign, assignExprCtx, isGlobal);
		}
		
		CodeFragment varDeclarationCode = new CodeFragment();
		
		VariableInfo varInfo = new VariableInfo();
		varInfo.name = varName;
		
		if(isGlobal){
			globalCompilerState.declareNewGlobalVar(varInfo);
		}
		else{
			globalCompilerState.localVarStack.declareNewVariable(varInfo);
		}
		
		Type typeOfVar = Type.getTypeFromTypeSpecificationCtx(btCtx);
		varDeclarationCode.setResultType(typeOfVar);
		varInfo.type = typeOfVar;
		
		ST varDeclarationCodeTemplate = templates.getInstanceOf("var_declaration_basic_type");
		varDeclarationCodeTemplate.add("llvmType", Type.getLLVMTypeFromType(typeOfVar));
		varInfo.nameInLLVMCode = globalCompilerState.getLLVMVarNameForVar(varName);
		varDeclarationCodeTemplate.add("varName", varInfo.nameInLLVMCode);
		if(isGlobal){
			varDeclarationCodeTemplate.add("isGlobal", "1");
			varDeclarationCodeTemplate.add("globalInitVal", getInitValForGlobal(typeOfVar));
			globalCompilerState.addGlobalVariableDeclarationLLVMCode(varDeclarationCodeTemplate.render());
		}
		else{
			varDeclarationCode.addCode(varDeclarationCodeTemplate.render());
		}
		
		if(assign){
			varDeclarationCode.addCode(getCodeFragForSimpleAssignNoIndexingBasicVarType(varInfo, isGlobal, assignExprCtx));
		}
		
		return varDeclarationCode;
	}
	
	private CodeFragment getCodeFragForArrayAllocationOfVar(CodeFragment arrayAllocSizesVectorCodeFrag, int arrayDimension,
			String arrayVarNameInLLVMCode, Type typeOfVar, boolean isGlobal){
		CodeFragment arrayAllocCodeFrag = new CodeFragment();
		
		ST arrayAllocBeginTpl = templates.getInstanceOf("array_alloc_begin");
		arrayAllocBeginTpl.add("arrayVarName", arrayVarNameInLLVMCode);
		arrayAllocBeginTpl.add("sizesVectorCode", arrayAllocSizesVectorCodeFrag);
		arrayAllocBeginTpl.add("sizesVectorReg", arrayAllocSizesVectorCodeFrag.getResultRegister());
		arrayAllocBeginTpl.add("sizesVectorType", Type.getLLVMTypeFromType(arrayAllocSizesVectorCodeFrag.getResultType()));
		String allocaTypeOnLevel = Type.getLLVMTypeFromType(typeOfVar);
		allocaTypeOnLevel = allocaTypeOnLevel.substring(0, allocaTypeOnLevel.length()-1);
		arrayAllocBeginTpl.add("allocaZeroLevelType", allocaTypeOnLevel);
		String namesIndex = globalCompilerState.getNextFreeRegister();
		arrayAllocBeginTpl.add("namesIndex", namesIndex);
		arrayAllocBeginTpl.add("isGlobal", isGlobal);
		
		arrayAllocCodeFrag.addCode(arrayAllocBeginTpl.render());
		
		List<ST> listOfArrayAllocLoopTpls = new ArrayList<ST>();
		for(int i=0;i<arrayDimension-1;i++){
			ST loopTpl = templates.getInstanceOf("array_alloc_loop");
			loopTpl.add("namesIndex", namesIndex);
			loopTpl.add("level", Integer.toString(i));
			loopTpl.add("nextLevel", Integer.toString(i+1));
			loopTpl.add("sizesVectorReg", arrayAllocSizesVectorCodeFrag.getResultRegister());
			loopTpl.add("sizesVectorType", Type.getLLVMTypeFromType(arrayAllocSizesVectorCodeFrag.getResultType()));
			allocaTypeOnLevel = allocaTypeOnLevel.substring(0, allocaTypeOnLevel.length()-1);
			loopTpl.add("allocaLevelType", allocaTypeOnLevel);
			listOfArrayAllocLoopTpls.add(loopTpl);
		}
		
		for(int i=listOfArrayAllocLoopTpls.size()-1;i>=1;i--){
			listOfArrayAllocLoopTpls.get(i-1).add("nextLevelLoopIfNeed", listOfArrayAllocLoopTpls.get(i).render());
		}
		
		if(listOfArrayAllocLoopTpls.size() > 0){
			arrayAllocCodeFrag.addCode(listOfArrayAllocLoopTpls.get(0).render());
		}
		
		return arrayAllocCodeFrag;
	}
	
	private String getInitValForGlobal(Type typeOfVar) {
		if(typeOfVar.arrayLevel > 0){
			return "null";
		}
		else if(isTypeIntegral(typeOfVar.type)){
			return "0";
		}
		else if(typeOfVar.type == Type.TypeEnum.DOUBLE){
			return "0.0";
		}
		else if(typeOfVar.type == Type.TypeEnum.BOOL){
			return "false";
		}
		else{
			DerALCompilerGlobalState.reportSomethingStrange("Snažíme sa získať inicializačnú honnotu pre premennú typu " + typeOfVar.toString());
			return "ZLE ZLE ZLE";
		}
	}
	
	private CodeFragment getCodeFragForArrayAllocTypeVarDeclaration(String varName, 
			DerALParser.ArrayAllocTypeSpecificationContext aaCtx, boolean assign, DerALParser.ExpressionContext expression,
			boolean isGlobal) {
		CodeFragment varDeclarationCodeFrag = new CodeFragment();
		
		VariableInfo varInfo = new VariableInfo();
		varInfo.name = varName;
		
		if(isGlobal){
			globalCompilerState.declareNewGlobalVar(varInfo);
		}
		else{
			globalCompilerState.localVarStack.declareNewVariable(varInfo);
		}
		
		Type typeOfVar = Type.getTypeFromTypeSpecificationCtx(aaCtx);
		varDeclarationCodeFrag.setResultType(typeOfVar);
		varInfo.type = typeOfVar;
		
		ST varDeclarationCodeTemplate = templates.getInstanceOf("var_declaration_basic_type");
		varDeclarationCodeTemplate.add("llvmType", Type.getLLVMTypeFromType(typeOfVar));
		varInfo.nameInLLVMCode = globalCompilerState.getLLVMVarNameForVar(varName);
		varDeclarationCodeTemplate.add("varName", varInfo.nameInLLVMCode);
		if(isGlobal){
			varDeclarationCodeTemplate.add("isGlobal", "1");
			varDeclarationCodeTemplate.add("globalInitVal", getInitValForGlobal(typeOfVar));
			globalCompilerState.addGlobalVariableDeclarationLLVMCode(varDeclarationCodeTemplate.render());
		}
		else{
			varDeclarationCodeFrag.addCode(varDeclarationCodeTemplate.render());
		}
		
		varDeclarationCodeFrag.addCode(getCodeFragForArrayAllocationOfVar(getCodeFragOfLLVMSizesVectorOfArrayAllocExpression(aaCtx.arrayIndex()), 
				aaCtx.arrayIndex().size(), varInfo.nameInLLVMCode, typeOfVar, isGlobal));
		
		if(assign){
			varDeclarationCodeFrag.addCode(getCodeFragForSimpleAssignNoIndexingArrayVarType(varInfo, isGlobal, expression));
		}
		
		return varDeclarationCodeFrag;
	}

	private CodeFragment getCodeFragForArrayNoAllocTypeVarDeclaration(String varName, 
			DerALParser.TypeSpecificationContext anaCtx, boolean assign, DerALParser.ExpressionContext expression,
			boolean isGlobal){
		CodeFragment varDeclarationCodeFrag = new CodeFragment();
		
		VariableInfo varInfo = new VariableInfo();
		varInfo.name = varName;
		
		if(isGlobal){
			globalCompilerState.declareNewGlobalVar(varInfo);
		}
		else{
			globalCompilerState.localVarStack.declareNewVariable(varInfo);
		}
		
		Type typeOfVar = Type.getTypeFromTypeSpecificationCtx(anaCtx);
		varDeclarationCodeFrag.setResultType(typeOfVar);
		varInfo.type = typeOfVar;
		
		ST varDeclarationCodeTemplate = templates.getInstanceOf("var_declaration_basic_type");
		varDeclarationCodeTemplate.add("llvmType", Type.getLLVMTypeFromType(typeOfVar));
		varInfo.nameInLLVMCode = globalCompilerState.getLLVMVarNameForVar(varName);
		varDeclarationCodeTemplate.add("varName", varInfo.nameInLLVMCode);
		if(isGlobal){
			varDeclarationCodeTemplate.add("isGlobal", "1");
			varDeclarationCodeTemplate.add("globalInitVal", getInitValForGlobal(typeOfVar));
			globalCompilerState.addGlobalVariableDeclarationLLVMCode(varDeclarationCodeTemplate.render());
		}
		else{
			varDeclarationCodeFrag.addCode(varDeclarationCodeTemplate.render());
		}
		
		if(assign){
			varDeclarationCodeFrag.addCode(getCodeFragForSimpleAssignNoIndexingArrayVarType(varInfo, isGlobal, expression));
		}
		
		return varDeclarationCodeFrag;
	}

	/*variableDeclaration	: typeSpecification Identifier (Assign expression)?;
	 typeSpecification	: typeName arrayIndex+					# arrayAllocTypeSpecification
					| typeName (LeftBracket RightBracket)+	# arrayNoAllocTypeSpecification
					| typeName								# basicTypeSpecification
					| Void 									# voidTypeSpecification
					;
	 */
	private CodeFragment getCodeFragForVariableDeclaration(DerALParser.VariableDeclarationContext ctx, boolean isGlobal){
		String varName = ctx.Identifier().getText();
		
		if(!isGlobal && globalCompilerState.localVarStack.isAlreadyDeclared(varName)){
			globalCompilerState.reportError("Re-deklarácia premennej " + varName);
			return new CodeFragment();
		}
		if(isGlobal && globalCompilerState.isGlobalAlreadyDeclared(varName)){
			globalCompilerState.reportError("Re-deklarácia globálnej premennej " + varName);
			return new CodeFragment();
		}

		if(ctx.typeSpecification().getRuleContext() instanceof DerALParser.ArrayAllocTypeSpecificationContext){
			DerALParser.ArrayAllocTypeSpecificationContext aaCtx = 
					(DerALParser.ArrayAllocTypeSpecificationContext) ctx.typeSpecification().getRuleContext();
			return getCodeFragForArrayAllocTypeVarDeclaration(varName, aaCtx, ctx.Assign() != null, ctx.expression(), isGlobal);
		}
		else if(ctx.typeSpecification().getRuleContext() instanceof DerALParser.ArrayNoAllocTypeSpecificationContext){
			DerALParser.ArrayNoAllocTypeSpecificationContext anaCtx = 
					(DerALParser.ArrayNoAllocTypeSpecificationContext)ctx.typeSpecification().getRuleContext();
			return getCodeFragForArrayNoAllocTypeVarDeclaration(varName, anaCtx, ctx.Assign() != null, ctx.expression(), isGlobal);
		}
		else if(ctx.typeSpecification().getRuleContext() instanceof DerALParser.BasicTypeSpecificationContext){
			DerALParser.BasicTypeSpecificationContext basicTypeCtx = 
					(DerALParser.BasicTypeSpecificationContext)ctx.typeSpecification().getRuleContext();
			return getCodeFragForBasicTypeVarDeclaration(varName, basicTypeCtx, ctx.Assign() != null, ctx.expression(), isGlobal);
		}
		else{
			globalCompilerState.reportError("Premenné typu nist sa neni povolené");
			return new CodeFragment();
		}
	}

	private boolean isTypeSuitableForFunctionArg(Type argType) {
		return argType.type != Type.TypeEnum.VOID && argType.type != Type.TypeEnum.ARRAY_ALLOC;
	}
	
	private boolean isTypeSuitableToReturn(Type exprType, Type functionRetType) {
		return exprType.equals(functionRetType);
	}
	
	private CodeFragment getCodeFragForReturnStatement(DerALParser.StatementReturnContext retStCtx, Type functionRetType){
		CodeFragment returnStatementCodeFrag = new CodeFragment();
		
		if(retStCtx.expression() == null){
			if(functionRetType.type != Type.TypeEnum.VOID){
				globalCompilerState.reportError("Zabudou si uvésť, čo vracáš. Mosíš vracať výraz typu " + functionRetType.toString());
				return new CodeFragment();
			}
			returnStatementCodeFrag.addCode("br label %$retLabel");
		}
		else{
			CodeFragment exprCodeFrag = visit(retStCtx.expression());
			if(!isTypeSuitableToReturn(exprCodeFrag.getResultType(),functionRetType)){
				globalCompilerState.reportError("Nemóžeš vracať " + exprCodeFrag.getResultType().toString() + " ve funkcii, kerá vracá " + functionRetType.toString());
				return new CodeFragment();
			}
			
			ST returnTpl = templates.getInstanceOf("return_statement");
			returnTpl.add("expression", exprCodeFrag.toString());
			returnTpl.add("exprResReg", exprCodeFrag.getResultRegister());
			returnTpl.add("retType", Type.getLLVMTypeFromType(functionRetType));
			returnStatementCodeFrag.addCode(returnTpl.render());
		}
		
		return returnStatementCodeFrag;
	}

	private CodeFragment getCodeFragForFunctionBody(DerALParser.FunctionBodyContext fbCtx, Type functionRetType){
		CodeFragment functionCodeFrag = new CodeFragment();
		
		functionCodeFrag.addCode(getCodeFragForStatements(fbCtx.statements(), functionRetType));
		
		return functionCodeFrag;
	}
	
	private boolean isTypeSuitableAsReturnType(Type retType) {
		return retType.arrayLevel == 0 && retType.type != Type.TypeEnum.ARRAY_ALLOC;
	}
	
	/*
	 * functionDeclaration : Def typeSpecification Identifier LeftParenthesis declarationArgsList RightParenthesis functionBody;

		declarationArgsList 	: declarationArgSpecification (',' declarationArgSpecification)*
								| 
								;
							
		declarationArgSpecification : typeSpecification Identifier;

	 * */
	@Override
	public CodeFragment visitFunctionDefinition(DerALParser.FunctionDefinitionContext ctx) {
		FunctionInfo functionInfo = new FunctionInfo();
		String functionName = ctx.Identifier().getText();
		functionInfo.name = functionName;
		
		if(globalCompilerState.isFunctionNameReserved(functionName)){
			globalCompilerState.reportError("Název funkcie '" + functionName + "' nemóže byť použitý. Toto meno je rezervuvané");
			globalCompilerState.line += ctx.Newline().size();
			return new CodeFragment();
		}
		if(globalCompilerState.isFunctionAlreadyDefined(functionName)){
			globalCompilerState.reportError("Re-definícia funkcie s názvem " + functionName);
			globalCompilerState.line += ctx.Newline().size();
			return new CodeFragment();
		}
		
		
		Type retType = Type.getTypeFromTypeSpecificationCtx(ctx.typeSpecification());
		if(!isTypeSuitableAsReturnType(retType)){
			globalCompilerState.reportError("Typ " + retType.toString() + " neni povolený jako návratový typ funkcie. Tip: funduše celkovo sa neni povolené.");
			globalCompilerState.line += ctx.Newline().size();
			return new CodeFragment();
		}
		functionInfo.retType = retType;
		if(retType.type != Type.TypeEnum.VOID){
			functionInfo.code += "%$ret = alloca " + Type.getLLVMTypeFromType(retType) + "\n";
		}
		
		globalCompilerState.localVarStack.enterCodeBlock();
		for(DerALParser.DefinitionArgSpecificationContext argCtx : ctx.definitionArgsList().definitionArgSpecification()){
			Type argType = Type.getTypeFromTypeSpecificationCtx(argCtx.typeSpecification());
			if(!isTypeSuitableForFunctionArg(argType)){
				globalCompilerState.reportError("Argument funkcie nemóže byť typu " + argType.toString());
			}
			
			String argName = argCtx.Identifier().getText();
			if(functionInfo.argNameAlreadyUsed(argName)){
				globalCompilerState.reportError("Znova použité meno argumentu funkcie: " + argName);
			}
			
			VariableInfo varInfoForVarStack = new VariableInfo();
			varInfoForVarStack.type = argType;
			varInfoForVarStack.name = argName;
			varInfoForVarStack.nameInLLVMCode = globalCompilerState.getLLVMVarNameForVar(argName);
			globalCompilerState.localVarStack.declareNewVariable(varInfoForVarStack);
			
			FunctionArgInfo argInfo = new FunctionArgInfo();
			argInfo.type = argType;
			argInfo.name = argName;
			functionInfo.args.add(argInfo);
			
			ST argInitTemplate = templates.getInstanceOf("function_arg_initialization");
			argInitTemplate.add("argVarLLVMName", varInfoForVarStack.nameInLLVMCode);
			argInitTemplate.add("argLLVMType", Type.getLLVMTypeFromType(argType));
			argInitTemplate.add("argName", argName);
			functionInfo.code += argInitTemplate.render();
		}
		globalCompilerState.definedFunctionsList.add(functionInfo);
		
		globalCompilerState.line += ctx.Newline().size();
		
		functionInfo.code += getCodeFragForFunctionBody(ctx.functionBody(), retType).getCode();
		
		ST functionEndTpl = templates.getInstanceOf("function_end");
		functionEndTpl.add("isVoid", retType.type == Type.TypeEnum.VOID);
		functionEndTpl.add("retType", Type.getLLVMTypeFromType(retType));
		functionInfo.code += functionEndTpl.render();
		
		globalCompilerState.localVarStack.exitCodeBlock();
		
		return new CodeFragment();
	}
	
	@Override 
	public CodeFragment visitDeclarationFunctionForwardDeclaration(DerALParser.DeclarationFunctionForwardDeclarationContext ctx) { 
		return visitFunctionForwardDeclaration(ctx.functionForwardDeclaration()); 
	}
	
	@Override
	public CodeFragment visitFunctionForwardDeclaration(DerALParser.FunctionForwardDeclarationContext ctx) {
		FunctionInfo functionInfo = new FunctionInfo();
		String functionName = ctx.Identifier().getText();
		functionInfo.name = functionName;
		
		if(globalCompilerState.isFunctionNameReserved(functionName)){
			globalCompilerState.reportError("Název funkcie '" + functionName + "' nemóže byť použitý. Toto meno je rezervuvané");
			return new CodeFragment();
		}
		if(globalCompilerState.isFunctionAlreadyDefined(functionName)){
			globalCompilerState.reportError("Funkcie s názvem " + functionName + " je už definuvaná");
			return new CodeFragment();
		}
		if(globalCompilerState.isFunctionAlreadyForwardDeclared(functionName)){
			globalCompilerState.reportError("Funkcia s názvem " + functionName + " už bola deklaruvaná");
			return new CodeFragment();
		}
		
		
		Type retType = Type.getTypeFromTypeSpecificationCtx(ctx.typeSpecification());
		if(!isTypeSuitableAsReturnType(retType)){
			globalCompilerState.reportError("Typ " + retType.toString() + " neni povolený jako návratový typ funkcie. Tip: funduše celkovo sa neni povolené.");
			return new CodeFragment();
		}
		functionInfo.retType = retType;
		
		for(DerALParser.DefinitionArgSpecificationContext argCtx : ctx.definitionArgsList().definitionArgSpecification()){
			Type argType = Type.getTypeFromTypeSpecificationCtx(argCtx.typeSpecification());
			if(!isTypeSuitableForFunctionArg(argType)){
				globalCompilerState.reportError("Argument funkcie nemóže byť typu " + argType.toString());
			}
			
			String argName = argCtx.Identifier().getText();
			if(functionInfo.argNameAlreadyUsed(argName)){
				globalCompilerState.reportError("Znova použité meno argumentu funkcie: " + argName);
			}
			
			FunctionArgInfo argInfo = new FunctionArgInfo();
			argInfo.type = argType;
			argInfo.name = argName;
			functionInfo.args.add(argInfo);
			
		}

		globalCompilerState.forwardDeclaredFunctionsList.add(functionInfo);
		
		return new CodeFragment();
	}
	
	@Override 
	public CodeFragment visitDeclarationFunctionExtern(DerALParser.DeclarationFunctionExternContext ctx) { 
		return visitFunctionExternDeclaration(ctx.functionExternDeclaration()); 
	}
	
	@Override 
	public CodeFragment visitFunctionExternDeclaration(DerALParser.FunctionExternDeclarationContext ctx) { 
		FunctionInfo functionInfo = new FunctionInfo();
		String functionName = ctx.Identifier().getText();
		functionInfo.name = functionName;
		
		if(globalCompilerState.isFunctionNameReserved(functionName)){
			globalCompilerState.reportError("Název funkcie '" + functionName + "' nemóže byť použitý. Toto meno je rezervuvané");
			return new CodeFragment();
		}
		if(globalCompilerState.isFunctionAlreadyDefined(functionName)){
			globalCompilerState.reportError("Funkcie s názvem " + functionName + " je už definuvaná");
			return new CodeFragment();
		}
		if(globalCompilerState.isFunctionAlreadyForwardDeclared(functionName)){
			globalCompilerState.reportError("Funkcia s názvem " + functionName + " už bola deklaruvaná");
			return new CodeFragment();
		}
		
		
		Type retType = Type.getTypeFromTypeSpecificationCtx(ctx.typeSpecification());
		if(!isTypeSuitableAsReturnType(retType)){
			globalCompilerState.reportError("Typ " + retType.toString() + " neni povolený jako návratový typ funkcie. Tip: funduše celkovo sa neni povolené.");
			return new CodeFragment();
		}
		functionInfo.retType = retType;
		
		for(DerALParser.DefinitionArgSpecificationContext argCtx : ctx.definitionArgsList().definitionArgSpecification()){
			Type argType = Type.getTypeFromTypeSpecificationCtx(argCtx.typeSpecification());
			if(!isTypeSuitableForFunctionArg(argType)){
				globalCompilerState.reportError("Argument funkcie nemóže byť typu " + argType.toString());
			}
			
			String argName = argCtx.Identifier().getText();
			if(functionInfo.argNameAlreadyUsed(argName)){
				globalCompilerState.reportError("Znova použité meno argumentu funkcie: " + argName);
			}
			
			FunctionArgInfo argInfo = new FunctionArgInfo();
			argInfo.type = argType;
			argInfo.name = argName;
			functionInfo.args.add(argInfo);
			
		}

		globalCompilerState.externFunctionsList.add(functionInfo);
		
		return new CodeFragment();
	}

	
	private String getLLVMCodeForDeclaredFunction(FunctionInfo fi){
		String functionDefinitionLLVM = "";
		functionDefinitionLLVM += "define " + Type.getLLVMTypeFromType(fi.retType) + " @" + fi.name + "(";
		
		for(int i=0; i<fi.args.size(); i++){
			if(i==0){
				functionDefinitionLLVM += Type.getLLVMTypeFromType(fi.args.get(i).type) + " %" + fi.args.get(i).name;  
			}
			else{
				functionDefinitionLLVM += ", " + Type.getLLVMTypeFromType(fi.args.get(i).type) + " %" + fi.args.get(i).name;
			}
		}
		
		functionDefinitionLLVM += "){\n";
		functionDefinitionLLVM += fi.code;
		functionDefinitionLLVM += "\n}\n";
		return functionDefinitionLLVM;
	}
	
	//"\\\r\n\t
	private String getLLVMStringRepresentationOfEscapedStringChar(char escaped){
		if(escaped == 'n'){
			return "\\0A";
		}
		else if(escaped == 't'){
			return "\\09";
		}
		else if(escaped == 'r'){
			return "\\0D";
		}
		else if(escaped == '\\'){
			return "\\5C";
		}
		else if(escaped == '"'){
			return "\\22";
		}
		else{
			DerALCompilerGlobalState.reportSomethingStrange("Snazim sa zistit LLVM reprezentaciu escaped znaku " + escaped);
			return "TOTO BY SA NEMALO STAT";
		}
	}

	private String getLLVMStringConst(DerALParser.StringConstExpressionContext ctx){
		String llvmStr = "\"";
		
		for(int i=1;i<ctx.StringConstant().getText().length()-1;i++){
			if(ctx.StringConstant().getText().charAt(i) != '\\'){
				llvmStr += ctx.StringConstant().getText().charAt(i);
			}
			else{
				llvmStr += getLLVMStringRepresentationOfEscapedStringChar(ctx.StringConstant().getText().charAt(++i));
			}
		}
		
		return llvmStr + "\\00\"";
	}
	
	/*
	 * Predpokladame, ze llvmStringConst je v spravnom formate, tj. vo formate "<nieco>"
	 * inak nezarucujem spravne spravanie
	 * */
	private int getLLVMStringConstLength(String llvmStringConst) {
		int length = 0;
		
		for(int i = 1;i<llvmStringConst.length()-1;i++){
			if(llvmStringConst.charAt(i) != '\\'){
				length++;
			}
			else{
				length++;
				i++;
				i++;
			}
		}
		
		return length;
	}
	
	@Override
	public CodeFragment visitStringConstExpression(DerALParser.StringConstExpressionContext ctx) {
		CodeFragment stringConstCodeFrag = new CodeFragment();
		Type stringConstResultType = new Type();
		stringConstResultType.type = Type.TypeEnum.CHAR;
		stringConstResultType.arrayLevel = 1;
		stringConstCodeFrag.setResultType(stringConstResultType);
		
		String llvmStringConst = getLLVMStringConst(ctx);
		int stringLength = getLLVMStringConstLength(llvmStringConst);
		
		//@.str = constant [4 x i8] c"abc\00"
		String globalStrConstReg = globalCompilerState.getNextFreeRegister();
		String globalConstCode = "@" + globalStrConstReg + " = constant [" 
				+ Integer.toString(stringLength) + " x i8] c" + llvmStringConst + "\n";
		globalCompilerState.addGlobalVariableDeclarationLLVMCode(globalConstCode);
		
		//%str = getelementptr [4 x i8]* @.str, i32 0, i32 0
		String registerToStoreString = globalCompilerState.getNextFreeRegister();
		stringConstCodeFrag.setResultRegister(registerToStoreString);
		
		ST strConstTemplate = templates.getInstanceOf("string_const");
		strConstTemplate.add("strLen", Integer.toString(stringLength));
		strConstTemplate.add("regToStoreStr", registerToStoreString);
		strConstTemplate.add("globalConstReg", globalStrConstReg);
		stringConstCodeFrag.addCode(strConstTemplate.render());
		
		return stringConstCodeFrag;
	}
	
	private CodeFragment getCodeFragForBasicVarExpression(VariableInfo varInfo, boolean isGlobal){
		CodeFragment varExprCodeFrag = new CodeFragment();

		ST varExprBasicTpl = templates.getInstanceOf("var_expression_basic");
		varExprBasicTpl.add("varLLVMType", Type.getLLVMTypeFromType(varInfo.type));
		varExprBasicTpl.add("varNameLLVM", varInfo.nameInLLVMCode);
		varExprBasicTpl.add("isGlobal", isGlobal);
		
		String regToStoreResult = globalCompilerState.getNextFreeRegister();
		varExprBasicTpl.add("regToStoreResult", regToStoreResult);
		
		varExprCodeFrag.setResultRegister(regToStoreResult);
		varExprCodeFrag.setResultType(varInfo.type);
		varExprCodeFrag.addCode(varExprBasicTpl.render());
		
		return varExprCodeFrag;
	}
	
	private CodeFragment getCodeFragForGetPtrToIndexedArrayVarCell(VariableInfo arrayVarInfo, boolean isGlobal,
			List<DerALParser.ArrayIndexContext> arrayIndexList){
		CodeFragment codeFragForGetPtrToIndexedArrayVarCell = new CodeFragment();
		
		CodeFragment llvmIndexesVectorCodeFrag = getCodeFragOfLLVMSizesVectorOfArrayAllocExpression(arrayIndexList);
		codeFragForGetPtrToIndexedArrayVarCell.addCode(llvmIndexesVectorCodeFrag);
		
		Type typeOfPrevIndexed = new Type(arrayVarInfo.type);
		String regToStorePrevIndexedPtr = arrayVarInfo.nameInLLVMCode;
		for(int i=0;i<arrayIndexList.size();i++){
			ST arrayIndexTpl = templates.getInstanceOf("var_expression_array_index");
			
			String regToStoreIndexedPtr = globalCompilerState.getNextFreeRegister();
			arrayIndexTpl.add("regToStoreIndexedPtr", regToStoreIndexedPtr);
			arrayIndexTpl.add("arrayNameLLVM", regToStorePrevIndexedPtr);
			arrayIndexTpl.add("arrayLLVMType", Type.getLLVMTypeFromType(typeOfPrevIndexed));
			arrayIndexTpl.add("indexReg", globalCompilerState.getNextFreeRegister());
			arrayIndexTpl.add("indexesVectorLLVMType", Type.getLLVMTypeFromType(llvmIndexesVectorCodeFrag.getResultType()));
			arrayIndexTpl.add("indexesVectorReg", llvmIndexesVectorCodeFrag.getResultRegister());
			arrayIndexTpl.add("indexNo", Integer.toString(i));
			arrayIndexTpl.add("helpReg", globalCompilerState.getNextFreeRegister());
			if(i==0){
				arrayIndexTpl.add("isGlobal", isGlobal);
			}
			
			codeFragForGetPtrToIndexedArrayVarCell.addCode(arrayIndexTpl.render());
			
			typeOfPrevIndexed.arrayLevel--;
			regToStorePrevIndexedPtr = regToStoreIndexedPtr;
		}
		
		codeFragForGetPtrToIndexedArrayVarCell.setResultRegister(regToStorePrevIndexedPtr);
		codeFragForGetPtrToIndexedArrayVarCell.setResultType(typeOfPrevIndexed);
		return codeFragForGetPtrToIndexedArrayVarCell;
	}
	
	private CodeFragment getCodeFragForArrayVarExpression(VariableInfo varInfo, boolean isGlobal, 
			List<DerALParser.ArrayIndexContext> arrayIndexList){
		CodeFragment valOfIndexedArrayCodeFrag = new CodeFragment();
				
		CodeFragment codeFragForGetPtrToIndexedArrayVarCell = 
							getCodeFragForGetPtrToIndexedArrayVarCell(varInfo, isGlobal,arrayIndexList);
		valOfIndexedArrayCodeFrag.addCode(codeFragForGetPtrToIndexedArrayVarCell);
		
		ST getFinalValTpl = templates.getInstanceOf("var_expression_basic");
		
		String regToStoreResult = globalCompilerState.getNextFreeRegister();
		getFinalValTpl.add("regToStoreResult", regToStoreResult);
		valOfIndexedArrayCodeFrag.setResultRegister(regToStoreResult);
		
		getFinalValTpl.add("varNameLLVM", codeFragForGetPtrToIndexedArrayVarCell.getResultRegister());
		getFinalValTpl.add("varLLVMType", Type.getLLVMTypeFromType(codeFragForGetPtrToIndexedArrayVarCell.getResultType()));
		valOfIndexedArrayCodeFrag.setResultType(codeFragForGetPtrToIndexedArrayVarCell.getResultType());
		valOfIndexedArrayCodeFrag.addCode(getFinalValTpl.render());
		
		return valOfIndexedArrayCodeFrag;
	}

	@Override
	public CodeFragment visitVarExpression(DerALParser.VarExpressionContext ctx) {
		//bohuzial mi to nevedelo sparsovat prioritne keywords pravda a blud ako BoolExpression takze natvrdo...
		if(ctx.getText().equals("pravda") || ctx.getText().equals("blud")){
			return visitBoolConstExpressionFromVarExpression(ctx.getText());
		}
		
		String varName = ctx.Identifier().getText();
		VariableInfo globalVarInfo = globalCompilerState.findGlobalVarInfo(varName);
		VariableInfo localVarInfo = globalCompilerState.localVarStack.findLocalVar(varName);
		
		if(globalVarInfo == null && localVarInfo == null){
			globalCompilerState.reportError("Steu si použiť nedeklaruvanú premennú " + varName);
			return new CodeFragment();
		}
		
		boolean isGlobal = false;
		VariableInfo varInfo = localVarInfo;
		if(localVarInfo == null){
			varInfo = globalVarInfo;
			isGlobal = true;
		}
		
		if(ctx.arrayIndex().size() == 0){
			return getCodeFragForBasicVarExpression(varInfo, isGlobal);
		}
		else if(ctx.arrayIndex().size() > varInfo.type.arrayLevel){
			globalCompilerState.reportError("Príliš vela indexov do funduša " + varName 
					+ ". Tento funduš je dimenzie " + Integer.toString(varInfo.type.arrayLevel));
			return new CodeFragment();
		}
		else{
			return getCodeFragForArrayVarExpression(varInfo, isGlobal, ctx.arrayIndex());
		}
	}

	@Override
	public CodeFragment visitIntConstExpression(DerALParser.IntConstExpressionContext ctx) {
		CodeFragment intConstCodeFrag = new CodeFragment();
		Type intType = new Type();
		intType.type = Type.TypeEnum.INT;
		intType.arrayLevel = 0;
		intConstCodeFrag.setResultType(intType);
		
		ST intConstTpl = this.templates.getInstanceOf("int_const");
		String regToStoreInt = globalCompilerState.getNextFreeRegister();
		intConstCodeFrag.setResultRegister(regToStoreInt);
		intConstTpl.add("regToStoreInt", regToStoreInt);
		intConstTpl.add("val", ctx.IntegerConstant().getText());
		intConstCodeFrag.addCode(intConstTpl.render());
		
		return intConstCodeFrag;
	}
	
	private String convertDoubleFromHumanReadableToLLVMHexadecimal(String humanReadableDouble){
		Double d = Double.valueOf(humanReadableDouble);
		return "0x" + Long.toHexString(Double.doubleToRawLongBits(d)).toUpperCase();
	}
	
	@Override
	public CodeFragment visitDoubleConstExpression(DerALParser.DoubleConstExpressionContext ctx) {
		CodeFragment doubleConstCodeFrag = new CodeFragment();
		Type doubleType = new Type();
		doubleType.type = Type.TypeEnum.DOUBLE;
		doubleType.arrayLevel = 0;
		doubleConstCodeFrag.setResultType(doubleType);
		
		ST doubleConstTpl = this.templates.getInstanceOf("double_const");
		String regToStoreDouble = globalCompilerState.getNextFreeRegister();
		doubleConstCodeFrag.setResultRegister(regToStoreDouble);
		doubleConstTpl.add("regToStoreDouble", regToStoreDouble);
		doubleConstTpl.add("val", convertDoubleFromHumanReadableToLLVMHexadecimal(ctx.DoubleConstant().getText()));
		doubleConstCodeFrag.addCode(doubleConstTpl.render());
		
		return doubleConstCodeFrag;
	}

	/*
	 * Identifier LeftParenthesis argListFuncCall RightParenthesis			# funcCallExpression
	 * argListFuncCall	: (expression (',' expression)*)? ;
	 * */
	@Override
	public CodeFragment visitFuncCallExpression(DerALParser.FuncCallExpressionContext ctx) {
		String functionName = ctx.Identifier().getText();
		
		FunctionInfo functionInfo = globalCompilerState.findFunction(functionName);
		if(functionInfo == null){
			globalCompilerState.reportError("Volaňé nedefinuvanej funkcie " + functionName);
			return new CodeFragment();
		}
		if(functionInfo.args.size() != ctx.argListFuncCall().expression().size()){
			globalCompilerState.reportError("Špatný počet argumentov pre funkciu " + functionInfo.getFunctionSignature());
			return new CodeFragment();
		}
		
		CodeFragment funcCallCodeFrag = new CodeFragment();
		
		String llvmArgsList = "";
		for(int i=0;i<ctx.argListFuncCall().expression().size();i++){
			CodeFragment argCodeFrag = visit(ctx.argListFuncCall().expression().get(i));
			
			if(!argCodeFrag.getResultType().equals(functionInfo.args.get(i).type)){
				globalCompilerState.reportError("Špatný typ argumentu na pozícii č. " + Integer.toString(i) + " pri volaní funkcie " + functionInfo.getFunctionSignature() + ": "
						+ argCodeFrag.getResultType().toString() + "(výraz: " + ctx.argListFuncCall().expression().get(i).getText()  + ")");
			}
			funcCallCodeFrag.addCode(argCodeFrag);
			
			if(i!=0){
				llvmArgsList += ", ";
			}
			llvmArgsList += Type.getLLVMTypeFromType(argCodeFrag.getResultType()) + " %" + argCodeFrag.getResultRegister();
		}
		
		ST funcCallTpl = templates.getInstanceOf("function_call");
		funcCallTpl.add("retType", Type.getLLVMTypeFromType(functionInfo.retType));
		funcCallTpl.add("notVoid", functionInfo.retType.type != Type.TypeEnum.VOID);
		String resultReg = globalCompilerState.getNextFreeRegister();
		funcCallCodeFrag.setResultRegister(resultReg);
		funcCallTpl.add("resultReg", resultReg);
		funcCallTpl.add("functionName", functionName);
		funcCallTpl.add("llvmArgsList", llvmArgsList);
		
		funcCallCodeFrag.addCode(funcCallTpl.render());
		funcCallCodeFrag.setResultType(functionInfo.retType);
		
		return funcCallCodeFrag;
	}

	private boolean areTypesSuitableForBooleanOperation(Type leftOpType, Type rightOpType){
		return leftOpType.arrayLevel==0 && rightOpType.arrayLevel==0 
				&& leftOpType.type == Type.TypeEnum.BOOL && rightOpType.type == Type.TypeEnum.BOOL;
	}
	
	private CodeFragment getCodeFragForBinaryBoolExpression(DerALParser.ExpressionContext leftOperandCtx, 
			DerALParser.ExpressionContext rightOperandCtx, boolean trueIfAndFalseIfOr){
		CodeFragment leftOperandExprCodeFrag = visit(leftOperandCtx);
		CodeFragment rightOperandExprCodeFrag = visit(rightOperandCtx);
		
		if(!areTypesSuitableForBooleanOperation(leftOperandExprCodeFrag.getResultType(), rightOperandExprCodeFrag.getResultType())){
			globalCompilerState.reportError("Zlé typy pre binárnu boolovskú operáciu :"
					+ leftOperandExprCodeFrag.getResultType().toString() + " a " + rightOperandExprCodeFrag.getResultType().toString()
					+ ". Obidvá mosá byť typu bool.");
			return new CodeFragment();
		}
		
		CodeFragment boolExprCodeFrag = new CodeFragment();
		Type type = new Type(); type.type = Type.TypeEnum.BOOL;
		boolExprCodeFrag.setResultType(type);
		String resultRegister =  globalCompilerState.getNextFreeRegister();
		boolExprCodeFrag.setResultRegister(resultRegister);
		
		ST template;
		if(trueIfAndFalseIfOr){
			template = templates.getInstanceOf("and_expression");
		}
		else{
			template = templates.getInstanceOf("or_expression");
		}
		
		template.add("leftOpExpr", leftOperandExprCodeFrag.getCode());
		template.add("leftResultReg", leftOperandExprCodeFrag.getResultRegister());
		template.add("rightOpExpr", rightOperandExprCodeFrag.getCode());
		template.add("rightResultReg", rightOperandExprCodeFrag.getResultRegister());
		template.add("labelsNo", globalCompilerState.getNextFreeRegister());
		template.add("resultReg", resultRegister);
		
		boolExprCodeFrag.addCode(template.render());
		return boolExprCodeFrag;
	}
	
	@Override
	public CodeFragment visitOrExpression(DerALParser.OrExpressionContext ctx) {
		return getCodeFragForBinaryBoolExpression(ctx.expression(0), ctx.expression(1), false);
	}
	
	@Override
	public CodeFragment visitAndExpression(DerALParser.AndExpressionContext ctx) {
		return getCodeFragForBinaryBoolExpression(ctx.expression(0), ctx.expression(1), true);
	}
	
	private String getBoolValForLLVM(String boolConstStr){
		if(boolConstStr.equals("pravda")){
			return "1";
		}
		else if(boolConstStr.equals("blud")){
			return "0";
		}
		else{
			DerALCompilerGlobalState.reportSomethingStrange("Snažím sa zistiť bool hodnotu pre " + boolConstStr);
			return "ZLE ZLE ZLE!";
		}
	}
	
	public CodeFragment visitBoolConstExpressionFromVarExpression(String boolConstStr){
		CodeFragment codeFrag = new CodeFragment();
		Type boolType = new Type();
		boolType.type = Type.TypeEnum.BOOL;
		codeFrag.setResultType(boolType);
		
		ST tpl = templates.getInstanceOf("bool_const");
		String regToStoreBool = globalCompilerState.getNextFreeRegister();
		codeFrag.setResultRegister(regToStoreBool);
		tpl.add("regToStoreBool", regToStoreBool);
		tpl.add("val", getBoolValForLLVM(boolConstStr));
		codeFrag.addCode(tpl.render());
		
		return codeFrag;
	}

	@Override
	public CodeFragment visitBoolConstExpression(DerALParser.BoolConstExpressionContext ctx) {
		System.out.println("Huraaaa, ono sa to uvedomilo a parsuje bool konstanty!!!!");
		
		CodeFragment codeFrag = new CodeFragment();
		Type boolType = new Type();
		boolType.type = Type.TypeEnum.BOOL;
		codeFrag.setResultType(boolType);
		
		ST tpl = templates.getInstanceOf("bool_const");
		String regToStoreBool = globalCompilerState.getNextFreeRegister();
		codeFrag.setResultRegister(regToStoreBool);
		tpl.add("regToStoreBool", regToStoreBool);
		tpl.add("val", getBoolValForLLVM(ctx.BoolConstatnt().getText()));
		codeFrag.addCode(tpl.render());
		
		return codeFrag;
	}
	
	private boolean isTypeSuitableForUnaryMinusExpression(Type type){
		return type.arrayLevel == 0 && (type.type == Type.TypeEnum.CHAR || type.type == Type.TypeEnum.DOUBLE || type.type == Type.TypeEnum.INT);
	}
	
	private CodeFragment visitUnaryMinusExpression(DerALParser.ExpressionContext ctx){
		CodeFragment unaryMinusCodeFrag = new CodeFragment();
		
		CodeFragment expressionCodeFrag = visit(ctx);
		
		if(!isTypeSuitableForUnaryMinusExpression(expressionCodeFrag.getResultType())){
			globalCompilerState.reportError("Špatný typ pre unárne mínus: " + expressionCodeFrag.getResultType().toString());
			return new CodeFragment();
		}
		unaryMinusCodeFrag.setResultType(expressionCodeFrag.getResultType());
		
		String regToStoreResult = globalCompilerState.getNextFreeRegister();
		unaryMinusCodeFrag.setResultRegister(regToStoreResult);
		
		ST tpl = templates.getInstanceOf("unary_minus");
		tpl.add("exprCode", expressionCodeFrag);
		tpl.add("exprValReg", expressionCodeFrag.getResultRegister());
		tpl.add("regToStoreResult", regToStoreResult);
		tpl.add("llvmType", Type.getLLVMTypeFromType(expressionCodeFrag.getResultType()));
		
		if(expressionCodeFrag.getResultType().type == Type.TypeEnum.DOUBLE){
			tpl.add("isDouble", "1");
		}
		
		unaryMinusCodeFrag.addCode(tpl.render());
		return unaryMinusCodeFrag;
	}
	
	private boolean isTypeSuitableForUnaryNotExpression(Type type){
		return type.arrayLevel==0 && type.type == Type.TypeEnum.BOOL;
	}
	
	private CodeFragment visitUnaryNotExpression(DerALParser.ExpressionContext ctx){
		CodeFragment unaryNotCodeFrag = new CodeFragment();
		
		CodeFragment expressionCodeFrag = visit(ctx);
		
		if(!isTypeSuitableForUnaryNotExpression(expressionCodeFrag.getResultType())){
			globalCompilerState.reportError("Špatný typ pre negáciu: " + expressionCodeFrag.getResultType().toString());
			return new CodeFragment();
		}
		unaryNotCodeFrag.setResultType(expressionCodeFrag.getResultType());
		
		String regToStoreResult = globalCompilerState.getNextFreeRegister();
		unaryNotCodeFrag.setResultRegister(regToStoreResult);
		
		ST tpl = templates.getInstanceOf("unary_not");
		tpl.add("exprCode", expressionCodeFrag);
		tpl.add("exprValReg", expressionCodeFrag.getResultRegister());
		tpl.add("regToStoreResult", regToStoreResult);
		
		unaryNotCodeFrag.addCode(tpl.render());
		return unaryNotCodeFrag;
	}

	@Override
	public CodeFragment visitUnaryExpression(DerALParser.UnaryExpressionContext ctx) {
		if(ctx.op.getType() == DerALLexer.Minus){
			return visitUnaryMinusExpression(ctx.expression());
		}
		else if(ctx.op.getType() == DerALLexer.Not){
			return visitUnaryNotExpression(ctx.expression());
		}
		
		DerALCompilerGlobalState.reportSomethingStrange("Snažíme sa riešiť unárny výraz s nejakým čudným operátorom");
		return new CodeFragment();
	}
	
	//~['\''\\\r\n\t] 
	private String getCharValForLLVMForEscapedChar(char escaped){
		if(escaped == '\''){
			return Integer.toString('\'');
		}
		else if(escaped == '\\'){
			return Integer.toString('\\');
		}
		else if(escaped == 'r'){
			return Integer.toString('\r');
		}
		else if(escaped == 'n'){
			return Integer.toString('\n');
		}
		else if(escaped == 't'){
			return Integer.toString('\t');
		}
		else{
			DerALCompilerGlobalState.reportSomethingStrange("Snažíme sa získať hodnotu znaku pre eskejpovaný znak" + escaped);
			return "ZLE ZLE ZLE";
		}
	}

	private String getCharValForLLVM(String charConstStr){
		if(charConstStr.charAt(1) == '\\'){
			return getCharValForLLVMForEscapedChar(charConstStr.charAt(2));
		}
		else{
			return Integer.toString(charConstStr.charAt(1) + 0);
		}
	}
	
	@Override
	public CodeFragment visitCharConstExpression(DerALParser.CharConstExpressionContext ctx) {
		CodeFragment codeFrag = new CodeFragment();
		Type charType = new Type();
		charType.type = Type.TypeEnum.CHAR;
		codeFrag.setResultType(charType);
		
		ST tpl = templates.getInstanceOf("char_const");
		String regToStoreChar = globalCompilerState.getNextFreeRegister();
		codeFrag.setResultRegister(regToStoreChar);
		tpl.add("regToStoreChar", regToStoreChar);
		tpl.add("val", getCharValForLLVM(ctx.CharConstant().getText()));
		codeFrag.addCode(tpl.render());
		
		return codeFrag;
	}

	@Override
	public CodeFragment visitParExpression(DerALParser.ParExpressionContext ctx) {
		return visit(ctx.expression());
	}
	
	private boolean isTypeIntegral(Type.TypeEnum type){
		return type == Type.TypeEnum.CHAR || type == Type.TypeEnum.INT;
	} 

	private boolean areTypesSuitableForArithmeticRelExpression(int opType,Type typeOp1, Type typeOp2){
		if(opType == DerALLexer.Mod){
			return areTypesSuitableForModExpression(typeOp1, typeOp2);
		}
		else if(opType == DerALLexer.Star || opType == DerALLexer.Div || opType == DerALLexer.Plus 
				|| opType == DerALLexer.Minus || isOperationRelational(opType)){
			return areTypesSuitableForStarDivPlusMinusRelExpression(typeOp1, typeOp2);
		}
		
		DerALCompilerGlobalState.reportSomethingStrange("Steme sa pýtať či sa typy vhonné pre voláku divnú operáciu v multiplikatívnom výraze");
		return false;
	}
	
	//(Equal|Less|Greater|LessEqual|GreaterEqual|NotEqual)
	private boolean isOperationRelational(int opType) {
		return opType == DerALLexer.Equal || opType == DerALLexer.Less || opType == DerALLexer.Greater
				|| opType == DerALLexer.LessEqual || opType == DerALLexer.GreaterEqual || opType == DerALLexer.NotEqual;
	}

	private boolean areTypesSuitableForStarDivPlusMinusRelExpression(Type typeOp1, Type typeOp2){
		return typeOp1.arrayLevel == 0 && typeOp2.arrayLevel == 0 && 
			((typeOp1.type == Type.TypeEnum.DOUBLE && typeOp2.type == Type.TypeEnum.DOUBLE)
			|| (isTypeIntegral(typeOp1.type) && isTypeIntegral(typeOp2.type))); 
	}
	
	private boolean areTypesSuitableForModExpression(Type typeOp1, Type typeOp2){
		return typeOp1.arrayLevel == 0 && typeOp2.arrayLevel == 0 && isTypeIntegral(typeOp1.type) && isTypeIntegral(typeOp2.type);
	}
	
	/* opType - ten kod pod ktorym pozna DerALLexer danu operaciu
	 * */
	private String getLLVMOp(int opType, boolean trueIfDoubleFalseIfInt){
		if(opType == DerALLexer.Star){
			if(trueIfDoubleFalseIfInt){
				return "fmul";
			}
			else{
				return "mul";
			}
		}
		else if(opType == DerALLexer.Div){
			if(trueIfDoubleFalseIfInt){
				return "fdiv";
			}
			else{
				return "sdiv";
			}
		}
		else if(opType == DerALLexer.Mod){
			if(trueIfDoubleFalseIfInt){
				DerALCompilerGlobalState.reportSomethingStrange("Snažíme sa získať LLVMOp pre '%' nad operandami realneho typu");
				return "ZLE ZLE ZLE";
			}
			else{
				return "srem";
			}
		}
		else if(opType == DerALLexer.Plus){
			if(trueIfDoubleFalseIfInt){
				return "fadd";
			}
			else{
				return "add";
			}
		}
		else if(opType == DerALLexer.Minus){
			if(trueIfDoubleFalseIfInt){
				return "fsub";
			}
			else{
				return "sub";
			}
		}
		else if(opType == DerALLexer.Equal){
			if(trueIfDoubleFalseIfInt){
				return "fcmp oeq";
			}
			else{
				return "icmp eq";
			}
		}
		else if(opType == DerALLexer.NotEqual){
			if(trueIfDoubleFalseIfInt){
				return "fcmp one";
			}
			else{
				return "icmp ne";
			}
		}
		else if(opType == DerALLexer.Greater){
			if(trueIfDoubleFalseIfInt){
				return "fcmp ogt";
			}
			else{
				return "icmp sgt";
			}
		}
		else if(opType == DerALLexer.GreaterEqual){
			if(trueIfDoubleFalseIfInt){
				return "fcmp oge";
			}
			else{
				return "icmp sge";
			}
		}
		else if(opType == DerALLexer.Less){
			if(trueIfDoubleFalseIfInt){
				return "fcmp olt";
			}
			else{
				return "icmp slt";
			}
		}
		else if(opType == DerALLexer.LessEqual){
			if(trueIfDoubleFalseIfInt){
				return "fcmp ole";
			}
			else{
				return "icmp sle";
			}
		}
	
		DerALCompilerGlobalState.reportSomethingStrange("Snažíme sa získať LLVMOp pre nejakú čudnú operáciu");
		return "ZLE ZLE ZLE";
	}
	
	private CodeFragment getCodeFragForArithmeticExpression(Token operator, DerALParser.ExpressionContext leftOperandCtx, DerALParser.ExpressionContext rightOperandCtx){
		CodeFragment exprLeftOperandCodeFrag = visit(leftOperandCtx);
		CodeFragment exprRightOperandCodeFrag = visit(rightOperandCtx);
		
		if(!areTypesSuitableForArithmeticRelExpression(operator.getType(), exprLeftOperandCodeFrag.getResultType(), exprRightOperandCodeFrag.getResultType())){
			globalCompilerState.reportError("Špatné typy operandov pre binárnu operáciu " + operator.getText() + ":" + exprLeftOperandCodeFrag.getResultType().toString() + " a " + exprRightOperandCodeFrag.getResultType().toString());
			return new CodeFragment();
		}
		
		CodeFragment arithmeticExpCodeFrag = new CodeFragment();
		
		ST tpl = templates.getInstanceOf("artihmetic_rel_expression");
		tpl.add("leftOpExpr", exprLeftOperandCodeFrag);
		tpl.add("leftResultReg", exprLeftOperandCodeFrag.getResultRegister());
		tpl.add("rightOpExpr", exprRightOperandCodeFrag);
		tpl.add("rightResultReg", exprRightOperandCodeFrag.getResultRegister());
		
		String regToStoreResult = globalCompilerState.getNextFreeRegister();
		arithmeticExpCodeFrag.setResultRegister(regToStoreResult);
		tpl.add("regToStoreResult", regToStoreResult);
		
		//staci jenna kontrola, potom vem ze oba sa typu double, kvoli checkovaniu vhodnosti typov. obdobne pri intTypoch
		if(exprLeftOperandCodeFrag.getResultType().type == Type.TypeEnum.DOUBLE){
			tpl.add("isDouble", "1");
			Type doubleType = new Type(); doubleType.type = Type.TypeEnum.DOUBLE;
			arithmeticExpCodeFrag.setResultType(doubleType);
			tpl.add("llvmOp", getLLVMOp(operator.getType(),true));
		}
		else if(exprLeftOperandCodeFrag.getResultType().type == Type.TypeEnum.INT 
				&& exprRightOperandCodeFrag.getResultType().type == Type.TypeEnum.INT){
			tpl.add("intLLVMTypeOfOperands", Type.getLLVMTypeFromType(Type.TypeEnum.INT));
			Type intType = new Type(); intType.type = Type.TypeEnum.INT;
			arithmeticExpCodeFrag.setResultType(intType);
			tpl.add("llvmOp", getLLVMOp(operator.getType(),false));
		}
		else if(exprLeftOperandCodeFrag.getResultType().type == Type.TypeEnum.CHAR 
				&& exprRightOperandCodeFrag.getResultType().type == Type.TypeEnum.CHAR){
			tpl.add("intLLVMTypeOfOperands", Type.getLLVMTypeFromType(Type.TypeEnum.CHAR));
			Type charType = new Type(); charType.type = Type.TypeEnum.CHAR;
			arithmeticExpCodeFrag.setResultType(charType);
			tpl.add("llvmOp", getLLVMOp(operator.getType(),false));
		}
		else if(exprLeftOperandCodeFrag.getResultType().type == Type.TypeEnum.INT 
				&& exprRightOperandCodeFrag.getResultType().type == Type.TypeEnum.CHAR){
			tpl.add("castRight", "1");
			tpl.add("regToStoreCasted", globalCompilerState.getNextFreeRegister());
			Type intType = new Type(); intType.type = Type.TypeEnum.INT;
			arithmeticExpCodeFrag.setResultType(intType);
			tpl.add("llvmOp", getLLVMOp(operator.getType(),false));
		}
		else if(exprLeftOperandCodeFrag.getResultType().type == Type.TypeEnum.CHAR 
				&& exprRightOperandCodeFrag.getResultType().type == Type.TypeEnum.INT){
			tpl.add("castLeft", "1");
			tpl.add("regToStoreCasted", globalCompilerState.getNextFreeRegister());
			Type intType = new Type(); intType.type = Type.TypeEnum.INT;
			arithmeticExpCodeFrag.setResultType(intType);
			tpl.add("llvmOp", getLLVMOp(operator.getType(),false));
		}
		else{
			DerALCompilerGlobalState.reportSomethingStrange("Snažíme sa vyhodnotiť aritmetický výraz s volákyma čunnýma operandáma: " 
					+ exprLeftOperandCodeFrag.getResultType().toString() + " a " + exprRightOperandCodeFrag.getResultType().toString());
		}
		
		arithmeticExpCodeFrag.addCode(tpl.render());
		return arithmeticExpCodeFrag;
	}
	
	@Override
	//expression op=(Star|Div|Mod) expression
	public CodeFragment visitMulExpression(DerALParser.MulExpressionContext ctx) {
		return getCodeFragForArithmeticExpression(ctx.op, ctx.expression(0), ctx.expression(1));
	}

	//expression op=(Plus|Minus) expression
	@Override
	public CodeFragment visitAddExpression(DerALParser.AddExpressionContext ctx) {
		return getCodeFragForArithmeticExpression(ctx.op, ctx.expression(0), ctx.expression(1));
	}
	
	@Override
	public CodeFragment visitRelExpression(DerALParser.RelExpressionContext ctx) {
		CodeFragment exprLeftOperandCodeFrag = visit(ctx.expression(0));
		CodeFragment exprRightOperandCodeFrag = visit(ctx.expression(1));
		
		if(!areTypesSuitableForArithmeticRelExpression(ctx.op.getType(), exprLeftOperandCodeFrag.getResultType(), exprRightOperandCodeFrag.getResultType())){
			globalCompilerState.reportError("Špatné typy operandov pre relačnú operáciu " + ctx.op.getText() + ": " + exprLeftOperandCodeFrag.getResultType().toString() + " a " + exprRightOperandCodeFrag.getResultType().toString());
			return new CodeFragment();
		}
		
		CodeFragment relationalExpCodeFrag = new CodeFragment();
		Type boolType = new Type(); boolType.type = Type.TypeEnum.BOOL;
		relationalExpCodeFrag.setResultType(boolType);
		
		ST tpl = templates.getInstanceOf("artihmetic_rel_expression");
		tpl.add("leftOpExpr", exprLeftOperandCodeFrag);
		tpl.add("leftResultReg", exprLeftOperandCodeFrag.getResultRegister());
		tpl.add("rightOpExpr", exprRightOperandCodeFrag);
		tpl.add("rightResultReg", exprRightOperandCodeFrag.getResultRegister());
		
		String regToStoreResult = globalCompilerState.getNextFreeRegister();
		relationalExpCodeFrag.setResultRegister(regToStoreResult);
		tpl.add("regToStoreResult", regToStoreResult);
		
		//staci jenna kontrola, potom vem ze oba sa typu double, kvoli checkovaniu vhodnosti typov. obdobne pri intTypoch
		if(exprLeftOperandCodeFrag.getResultType().type == Type.TypeEnum.DOUBLE){
			tpl.add("isDouble", "1");
			tpl.add("llvmOp", getLLVMOp(ctx.op.getType(),true));
		}
		else if(exprLeftOperandCodeFrag.getResultType().type == Type.TypeEnum.INT 
				&& exprRightOperandCodeFrag.getResultType().type == Type.TypeEnum.INT){
			tpl.add("intLLVMTypeOfOperands", Type.getLLVMTypeFromType(Type.TypeEnum.INT));
			tpl.add("llvmOp", getLLVMOp(ctx.op.getType(),false));
		}
		else if(exprLeftOperandCodeFrag.getResultType().type == Type.TypeEnum.CHAR 
				&& exprRightOperandCodeFrag.getResultType().type == Type.TypeEnum.CHAR){
			tpl.add("intLLVMTypeOfOperands", Type.getLLVMTypeFromType(Type.TypeEnum.CHAR));
			tpl.add("llvmOp", getLLVMOp(ctx.op.getType(),false));
		}
		else if(exprLeftOperandCodeFrag.getResultType().type == Type.TypeEnum.INT 
				&& exprRightOperandCodeFrag.getResultType().type == Type.TypeEnum.CHAR){
			tpl.add("castRight", "1");
			tpl.add("regToStoreCasted", globalCompilerState.getNextFreeRegister());
			tpl.add("llvmOp", getLLVMOp(ctx.op.getType(),false));
		}
		else if(exprLeftOperandCodeFrag.getResultType().type == Type.TypeEnum.CHAR 
				&& exprRightOperandCodeFrag.getResultType().type == Type.TypeEnum.INT){
			tpl.add("castLeft", "1");
			tpl.add("regToStoreCasted", globalCompilerState.getNextFreeRegister());
			tpl.add("llvmOp", getLLVMOp(ctx.op.getType(),false));
		}
		else{
			DerALCompilerGlobalState.reportSomethingStrange("Snažíme sa vyhodnotiť relačný výraz s volákyma čunnýma operandáma: " 
					+ exprLeftOperandCodeFrag.getResultType().toString() + " a " + exprRightOperandCodeFrag.getResultType().toString());
		}
		
		relationalExpCodeFrag.addCode(tpl.render());
		return relationalExpCodeFrag;
	}

	private boolean isTypeSuitableForArrayIndex(Type type){
		return type.arrayLevel == 0 && type.type == Type.TypeEnum.INT;
	}
	
	/*
	 * Toto moze byt trocha zmatocne, takze vysvetlenie:
	 * ciel je uchovat vo vektore (tom z llvm v llvm kode) uchovat hodnoty, ktore reprezentuju velkosti,
	 * ktore v tejto allocExpression programator zadal a neskor ich pouzivat.
	 * vygeneruje sa kod na tento styl:
	  	%.3 = alloca < 3 x i32 >
		%.4 = load < 3 x i32 >* %.3
		%.5 = add i32 0, 4
		%.6 = insertelement < 3 x i32 > %.4, i32 %.5, i32 0
		%.7 = add i32 0, 5
		%.8 = insertelement < 3 x i32 > %.6, i32 %.7, i32 1
		%.9 = add i32 0, 7
		%.10 = insertelement < 3 x i32 > %.8, i32 %.9, i32 2 
	 * */
	private CodeFragment getCodeFragOfLLVMSizesVectorOfArrayAllocExpression(List<DerALParser.ArrayIndexContext> arrayIndexList){
		CodeFragment arrayAllocCodeFrag = new CodeFragment();
		Type arrayAllocType = new Type();
		arrayAllocType.type = Type.TypeEnum.ARRAY_ALLOC;
		arrayAllocType.arrayLevel = arrayIndexList.size();
		arrayAllocCodeFrag.setResultType(arrayAllocType);
		
		String llvmTypeOfVectorOfAllocSizes = "< " + arrayIndexList.size() + " x i32 >";
		String regToAllocSizesVector = globalCompilerState.getNextFreeRegister();
		arrayAllocCodeFrag.addCode("%" + regToAllocSizesVector + " = alloca " + llvmTypeOfVectorOfAllocSizes + "\n");
		
		String lastRegisterWithActualizedSizesVector = globalCompilerState.getNextFreeRegister();
		arrayAllocCodeFrag.addCode("%" + lastRegisterWithActualizedSizesVector + " = load " 
				+ llvmTypeOfVectorOfAllocSizes + "* %" + regToAllocSizesVector + "\n");
		for(int i=0;i<arrayIndexList.size();i++){
			CodeFragment arrayIndexExpressionCodeFrag = visit(arrayIndexList.get(i).expression());
			
			if(!isTypeSuitableForArrayIndex(arrayIndexExpressionCodeFrag.getResultType())){
				globalCompilerState.reportError("Špatný typ ako index do funduša: " 
						+ arrayIndexExpressionCodeFrag.getResultType().toString() + ". Indexuvať sa dá iba typom cele.");
				return new CodeFragment();
			}
			
			arrayAllocCodeFrag.addCode(arrayIndexExpressionCodeFrag);

			String nextRegisterWithActualizedSizesVector = globalCompilerState.getNextFreeRegister();
			arrayAllocCodeFrag.addCode("%" + nextRegisterWithActualizedSizesVector + " = insertelement " + llvmTypeOfVectorOfAllocSizes
					+ " %" + lastRegisterWithActualizedSizesVector + ", i32 %" + arrayIndexExpressionCodeFrag.getResultRegister()
					+ ", i32 " + Integer.toString(i) + "\n");
			lastRegisterWithActualizedSizesVector = nextRegisterWithActualizedSizesVector;
		}
		
		arrayAllocCodeFrag.setResultRegister(lastRegisterWithActualizedSizesVector);
		
		return arrayAllocCodeFrag; 
	}
	
	@Override 
	public CodeFragment visitArrayAllocExpression(DerALParser.ArrayAllocExpressionContext ctx) {
		return getCodeFragOfLLVMSizesVectorOfArrayAllocExpression(ctx.arrayIndex());
	}

	
}
