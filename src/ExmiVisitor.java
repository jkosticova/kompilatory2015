// Generated from /home/brando/School/git/Kompilatory/Exmi.g4 by ANTLR 4.5.1
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link ExmiParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface ExmiVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link ExmiParser#init}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInit(ExmiParser.InitContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExmiParser#object}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitObject(ExmiParser.ObjectContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExmiParser#arg_one}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArg_one(ExmiParser.Arg_oneContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExmiParser#arglist}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArglist(ExmiParser.ArglistContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExmiParser#func_decl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunc_decl(ExmiParser.Func_declContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExmiParser#func_def}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunc_def(ExmiParser.Func_defContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExmiParser#code}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCode(ExmiParser.CodeContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExmiParser#if_block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIf_block(ExmiParser.If_blockContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExmiParser#while_block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhile_block(ExmiParser.While_blockContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExmiParser#assigment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssigment(ExmiParser.AssigmentContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExmiParser#declaration_global}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclaration_global(ExmiParser.Declaration_globalContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExmiParser#declaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclaration(ExmiParser.DeclarationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Par}
	 * labeled alternative in {@link ExmiParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPar(ExmiParser.ParContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Add}
	 * labeled alternative in {@link ExmiParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAdd(ExmiParser.AddContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Mod}
	 * labeled alternative in {@link ExmiParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMod(ExmiParser.ModContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Or}
	 * labeled alternative in {@link ExmiParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOr(ExmiParser.OrContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Mul}
	 * labeled alternative in {@link ExmiParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMul(ExmiParser.MulContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Var}
	 * labeled alternative in {@link ExmiParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVar(ExmiParser.VarContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Cmp}
	 * labeled alternative in {@link ExmiParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCmp(ExmiParser.CmpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Una}
	 * labeled alternative in {@link ExmiParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUna(ExmiParser.UnaContext ctx);
	/**
	 * Visit a parse tree produced by the {@code String}
	 * labeled alternative in {@link ExmiParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitString(ExmiParser.StringContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Int}
	 * labeled alternative in {@link ExmiParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInt(ExmiParser.IntContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Bool}
	 * labeled alternative in {@link ExmiParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBool(ExmiParser.BoolContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Char}
	 * labeled alternative in {@link ExmiParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitChar(ExmiParser.CharContext ctx);
	/**
	 * Visit a parse tree produced by the {@code And}
	 * labeled alternative in {@link ExmiParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAnd(ExmiParser.AndContext ctx);
	/**
	 * Visit a parse tree produced by the {@code function}
	 * labeled alternative in {@link ExmiParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunction(ExmiParser.FunctionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Exp}
	 * labeled alternative in {@link ExmiParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExp(ExmiParser.ExpContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExmiParser#comment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComment(ExmiParser.CommentContext ctx);
}