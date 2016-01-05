// Generated from DerAL.g4 by ANTLR 4.5.1
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link DerALParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface DerALVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link DerALParser#sourceCode}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSourceCode(DerALParser.SourceCodeContext ctx);
	/**
	 * Visit a parse tree produced by {@link DerALParser#declarations}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclarations(DerALParser.DeclarationsContext ctx);
	/**
	 * Visit a parse tree produced by {@link DerALParser#mainProgram}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMainProgram(DerALParser.MainProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link DerALParser#statements}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatements(DerALParser.StatementsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code declarationFunctioDefinition}
	 * labeled alternative in {@link DerALParser#declaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclarationFunctioDefinition(DerALParser.DeclarationFunctioDefinitionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code declarationFunctionForwardDeclaration}
	 * labeled alternative in {@link DerALParser#declaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclarationFunctionForwardDeclaration(DerALParser.DeclarationFunctionForwardDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code declarationFunctionExtern}
	 * labeled alternative in {@link DerALParser#declaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclarationFunctionExtern(DerALParser.DeclarationFunctionExternContext ctx);
	/**
	 * Visit a parse tree produced by the {@code declarationGlobalVar}
	 * labeled alternative in {@link DerALParser#declaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclarationGlobalVar(DerALParser.DeclarationGlobalVarContext ctx);
	/**
	 * Visit a parse tree produced by the {@code declarationSemicol}
	 * labeled alternative in {@link DerALParser#declaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclarationSemicol(DerALParser.DeclarationSemicolContext ctx);
	/**
	 * Visit a parse tree produced by the {@code statementVarDeclaration}
	 * labeled alternative in {@link DerALParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatementVarDeclaration(DerALParser.StatementVarDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code statementAssignment}
	 * labeled alternative in {@link DerALParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatementAssignment(DerALParser.StatementAssignmentContext ctx);
	/**
	 * Visit a parse tree produced by the {@code statementLoop}
	 * labeled alternative in {@link DerALParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatementLoop(DerALParser.StatementLoopContext ctx);
	/**
	 * Visit a parse tree produced by the {@code statementCond}
	 * labeled alternative in {@link DerALParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatementCond(DerALParser.StatementCondContext ctx);
	/**
	 * Visit a parse tree produced by the {@code statementReturn}
	 * labeled alternative in {@link DerALParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatementReturn(DerALParser.StatementReturnContext ctx);
	/**
	 * Visit a parse tree produced by the {@code statementCompound}
	 * labeled alternative in {@link DerALParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatementCompound(DerALParser.StatementCompoundContext ctx);
	/**
	 * Visit a parse tree produced by the {@code statementExpression}
	 * labeled alternative in {@link DerALParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatementExpression(DerALParser.StatementExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code statementSemicol}
	 * labeled alternative in {@link DerALParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatementSemicol(DerALParser.StatementSemicolContext ctx);
	/**
	 * Visit a parse tree produced by the {@code loopWhile}
	 * labeled alternative in {@link DerALParser#loopStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLoopWhile(DerALParser.LoopWhileContext ctx);
	/**
	 * Visit a parse tree produced by the {@code loopFor}
	 * labeled alternative in {@link DerALParser#loopStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLoopFor(DerALParser.LoopForContext ctx);
	/**
	 * Visit a parse tree produced by {@link DerALParser#loopCheckStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLoopCheckStatement(DerALParser.LoopCheckStatementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code forInitVarDecl}
	 * labeled alternative in {@link DerALParser#forInitStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForInitVarDecl(DerALParser.ForInitVarDeclContext ctx);
	/**
	 * Visit a parse tree produced by the {@code forInitAssignment}
	 * labeled alternative in {@link DerALParser#forInitStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForInitAssignment(DerALParser.ForInitAssignmentContext ctx);
	/**
	 * Visit a parse tree produced by the {@code forInitEmpty}
	 * labeled alternative in {@link DerALParser#forInitStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForInitEmpty(DerALParser.ForInitEmptyContext ctx);
	/**
	 * Visit a parse tree produced by the {@code forLoopStatementAssignment}
	 * labeled alternative in {@link DerALParser#forLoopStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForLoopStatementAssignment(DerALParser.ForLoopStatementAssignmentContext ctx);
	/**
	 * Visit a parse tree produced by the {@code forLoopStatementEmpty}
	 * labeled alternative in {@link DerALParser#forLoopStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForLoopStatementEmpty(DerALParser.ForLoopStatementEmptyContext ctx);
	/**
	 * Visit a parse tree produced by {@link DerALParser#condStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCondStatement(DerALParser.CondStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link DerALParser#assignment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignment(DerALParser.AssignmentContext ctx);
	/**
	 * Visit a parse tree produced by {@link DerALParser#compoundStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompoundStatement(DerALParser.CompoundStatementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code arrayAllocTypeSpecification}
	 * labeled alternative in {@link DerALParser#typeSpecification}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayAllocTypeSpecification(DerALParser.ArrayAllocTypeSpecificationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code arrayNoAllocTypeSpecification}
	 * labeled alternative in {@link DerALParser#typeSpecification}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayNoAllocTypeSpecification(DerALParser.ArrayNoAllocTypeSpecificationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code basicTypeSpecification}
	 * labeled alternative in {@link DerALParser#typeSpecification}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBasicTypeSpecification(DerALParser.BasicTypeSpecificationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code voidTypeSpecification}
	 * labeled alternative in {@link DerALParser#typeSpecification}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVoidTypeSpecification(DerALParser.VoidTypeSpecificationContext ctx);
	/**
	 * Visit a parse tree produced by {@link DerALParser#variableDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariableDeclaration(DerALParser.VariableDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link DerALParser#functionExternDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionExternDeclaration(DerALParser.FunctionExternDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link DerALParser#functionForwardDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionForwardDeclaration(DerALParser.FunctionForwardDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link DerALParser#functionDefinition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionDefinition(DerALParser.FunctionDefinitionContext ctx);
	/**
	 * Visit a parse tree produced by {@link DerALParser#definitionArgsList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDefinitionArgsList(DerALParser.DefinitionArgsListContext ctx);
	/**
	 * Visit a parse tree produced by {@link DerALParser#definitionArgSpecification}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDefinitionArgSpecification(DerALParser.DefinitionArgSpecificationContext ctx);
	/**
	 * Visit a parse tree produced by {@link DerALParser#functionBody}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionBody(DerALParser.FunctionBodyContext ctx);
	/**
	 * Visit a parse tree produced by the {@code stringConstExpression}
	 * labeled alternative in {@link DerALParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStringConstExpression(DerALParser.StringConstExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code varExpression}
	 * labeled alternative in {@link DerALParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarExpression(DerALParser.VarExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code relExpression}
	 * labeled alternative in {@link DerALParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRelExpression(DerALParser.RelExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code intConstExpression}
	 * labeled alternative in {@link DerALParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntConstExpression(DerALParser.IntConstExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code OrExpression}
	 * labeled alternative in {@link DerALParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOrExpression(DerALParser.OrExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code funcCallExpression}
	 * labeled alternative in {@link DerALParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuncCallExpression(DerALParser.FuncCallExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code doubleConstExpression}
	 * labeled alternative in {@link DerALParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDoubleConstExpression(DerALParser.DoubleConstExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code AndExpression}
	 * labeled alternative in {@link DerALParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAndExpression(DerALParser.AndExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code addExpression}
	 * labeled alternative in {@link DerALParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAddExpression(DerALParser.AddExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code boolConstExpression}
	 * labeled alternative in {@link DerALParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoolConstExpression(DerALParser.BoolConstExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code unaryExpression}
	 * labeled alternative in {@link DerALParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnaryExpression(DerALParser.UnaryExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code charConstExpression}
	 * labeled alternative in {@link DerALParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCharConstExpression(DerALParser.CharConstExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code arrayAllocExpression}
	 * labeled alternative in {@link DerALParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayAllocExpression(DerALParser.ArrayAllocExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ParExpression}
	 * labeled alternative in {@link DerALParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParExpression(DerALParser.ParExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code mulExpression}
	 * labeled alternative in {@link DerALParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMulExpression(DerALParser.MulExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link DerALParser#argListFuncCall}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArgListFuncCall(DerALParser.ArgListFuncCallContext ctx);
	/**
	 * Visit a parse tree produced by {@link DerALParser#arrayIndex}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayIndex(DerALParser.ArrayIndexContext ctx);
	/**
	 * Visit a parse tree produced by {@link DerALParser#typeName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeName(DerALParser.TypeNameContext ctx);
}