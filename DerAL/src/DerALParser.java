// Generated from DerAL.g4 by ANTLR 4.5.1
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class DerALParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.5.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, Int=2, Unsigned=3, Char=4, Double=5, Bool=6, Void=7, String=8, 
		Begin=9, For=10, While=11, If=12, Else=13, Break=14, Continue=15, Return=16, 
		Class=17, Def=18, Dec=19, Extern=20, New=21, Delete=22, Set=23, LeftParenthesis=24, 
		RightParenthesis=25, LeftBracket=26, RightBracket=27, LeftBrace=28, RightBrace=29, 
		Semicolon=30, Equal=31, NotEqual=32, PlusAssign=33, MinusAssign=34, StarAssign=35, 
		DivAssign=36, Assign=37, ModAssign=38, LessEqual=39, GreaterEqual=40, 
		And=41, Or=42, Not=43, Less=44, Greater=45, Plus=46, Minus=47, Star=48, 
		Div=49, Mod=50, Identifier=51, BoolConstatnt=52, DoubleConstant=53, IntegerConstant=54, 
		CharConstant=55, StringConstant=56, Newline=57, Whitespace=58, LineComment=59, 
		BlockComment=60;
	public static final int
		RULE_sourceCode = 0, RULE_declarations = 1, RULE_mainProgram = 2, RULE_statements = 3, 
		RULE_declaration = 4, RULE_statement = 5, RULE_loopStatement = 6, RULE_loopCheckStatement = 7, 
		RULE_forInitStatement = 8, RULE_forLoopStatement = 9, RULE_condStatement = 10, 
		RULE_assignment = 11, RULE_compoundStatement = 12, RULE_typeSpecification = 13, 
		RULE_variableDeclaration = 14, RULE_functionExternDeclaration = 15, RULE_functionForwardDeclaration = 16, 
		RULE_functionDefinition = 17, RULE_definitionArgsList = 18, RULE_definitionArgSpecification = 19, 
		RULE_functionBody = 20, RULE_expression = 21, RULE_argListFuncCall = 22, 
		RULE_arrayIndex = 23, RULE_typeName = 24;
	public static final String[] ruleNames = {
		"sourceCode", "declarations", "mainProgram", "statements", "declaration", 
		"statement", "loopStatement", "loopCheckStatement", "forInitStatement", 
		"forLoopStatement", "condStatement", "assignment", "compoundStatement", 
		"typeSpecification", "variableDeclaration", "functionExternDeclaration", 
		"functionForwardDeclaration", "functionDefinition", "definitionArgsList", 
		"definitionArgSpecification", "functionBody", "expression", "argListFuncCall", 
		"arrayIndex", "typeName"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "','", "'cele'", "'prirodzene'", "'znak'", "'realne'", "'bool'", 
		"'nist'", "'retazec'", "'ZACATEK'", "'dokolecka'", "'dokedy'", "'ak'", 
		"'inac'", "'prerus'", "'pokracuj'", "'vrac'", "'treda'", "'def'", "'slubujem'", 
		"'cudza'", "'novy'", "'zmaz'", "'mnozina'", "'('", "')'", "'['", "']'", 
		"'{'", "'}'", "';'", "'=='", "'!='", "'+='", "'-='", "'*='", "'/='", "'='", 
		"'%='", "'<='", "'>='", "'&&'", "'||'", "'!'", "'<'", "'>'", "'+'", "'-'", 
		"'*'", "'/'", "'%'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, "Int", "Unsigned", "Char", "Double", "Bool", "Void", "String", 
		"Begin", "For", "While", "If", "Else", "Break", "Continue", "Return", 
		"Class", "Def", "Dec", "Extern", "New", "Delete", "Set", "LeftParenthesis", 
		"RightParenthesis", "LeftBracket", "RightBracket", "LeftBrace", "RightBrace", 
		"Semicolon", "Equal", "NotEqual", "PlusAssign", "MinusAssign", "StarAssign", 
		"DivAssign", "Assign", "ModAssign", "LessEqual", "GreaterEqual", "And", 
		"Or", "Not", "Less", "Greater", "Plus", "Minus", "Star", "Div", "Mod", 
		"Identifier", "BoolConstatnt", "DoubleConstant", "IntegerConstant", "CharConstant", 
		"StringConstant", "Newline", "Whitespace", "LineComment", "BlockComment"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "DerAL.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public DerALParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class SourceCodeContext extends ParserRuleContext {
		public DeclarationsContext declarations() {
			return getRuleContext(DeclarationsContext.class,0);
		}
		public TerminalNode Begin() { return getToken(DerALParser.Begin, 0); }
		public TerminalNode Newline() { return getToken(DerALParser.Newline, 0); }
		public MainProgramContext mainProgram() {
			return getRuleContext(MainProgramContext.class,0);
		}
		public TerminalNode EOF() { return getToken(DerALParser.EOF, 0); }
		public SourceCodeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sourceCode; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DerALVisitor ) return ((DerALVisitor<? extends T>)visitor).visitSourceCode(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SourceCodeContext sourceCode() throws RecognitionException {
		SourceCodeContext _localctx = new SourceCodeContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_sourceCode);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(50);
			declarations();
			setState(51);
			match(Begin);
			setState(52);
			match(Newline);
			setState(53);
			mainProgram();
			setState(54);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DeclarationsContext extends ParserRuleContext {
		public List<DeclarationContext> declaration() {
			return getRuleContexts(DeclarationContext.class);
		}
		public DeclarationContext declaration(int i) {
			return getRuleContext(DeclarationContext.class,i);
		}
		public List<TerminalNode> Newline() { return getTokens(DerALParser.Newline); }
		public TerminalNode Newline(int i) {
			return getToken(DerALParser.Newline, i);
		}
		public List<TerminalNode> BlockComment() { return getTokens(DerALParser.BlockComment); }
		public TerminalNode BlockComment(int i) {
			return getToken(DerALParser.BlockComment, i);
		}
		public List<TerminalNode> LineComment() { return getTokens(DerALParser.LineComment); }
		public TerminalNode LineComment(int i) {
			return getToken(DerALParser.LineComment, i);
		}
		public DeclarationsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declarations; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DerALVisitor ) return ((DerALVisitor<? extends T>)visitor).visitDeclarations(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DeclarationsContext declarations() throws RecognitionException {
		DeclarationsContext _localctx = new DeclarationsContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_declarations);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(62);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Int) | (1L << Unsigned) | (1L << Char) | (1L << Double) | (1L << Bool) | (1L << Void) | (1L << String) | (1L << Def) | (1L << Dec) | (1L << Extern) | (1L << Semicolon) | (1L << Newline) | (1L << LineComment) | (1L << BlockComment))) != 0)) {
				{
				setState(60);
				switch (_input.LA(1)) {
				case Int:
				case Unsigned:
				case Char:
				case Double:
				case Bool:
				case Void:
				case String:
				case Def:
				case Dec:
				case Extern:
				case Semicolon:
					{
					setState(56);
					declaration();
					}
					break;
				case Newline:
					{
					setState(57);
					match(Newline);
					}
					break;
				case BlockComment:
					{
					setState(58);
					match(BlockComment);
					}
					break;
				case LineComment:
					{
					setState(59);
					match(LineComment);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(64);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MainProgramContext extends ParserRuleContext {
		public StatementsContext statements() {
			return getRuleContext(StatementsContext.class,0);
		}
		public MainProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_mainProgram; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DerALVisitor ) return ((DerALVisitor<? extends T>)visitor).visitMainProgram(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MainProgramContext mainProgram() throws RecognitionException {
		MainProgramContext _localctx = new MainProgramContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_mainProgram);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(65);
			statements();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StatementsContext extends ParserRuleContext {
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public List<TerminalNode> Newline() { return getTokens(DerALParser.Newline); }
		public TerminalNode Newline(int i) {
			return getToken(DerALParser.Newline, i);
		}
		public List<TerminalNode> BlockComment() { return getTokens(DerALParser.BlockComment); }
		public TerminalNode BlockComment(int i) {
			return getToken(DerALParser.BlockComment, i);
		}
		public List<TerminalNode> LineComment() { return getTokens(DerALParser.LineComment); }
		public TerminalNode LineComment(int i) {
			return getToken(DerALParser.LineComment, i);
		}
		public StatementsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statements; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DerALVisitor ) return ((DerALVisitor<? extends T>)visitor).visitStatements(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatementsContext statements() throws RecognitionException {
		StatementsContext _localctx = new StatementsContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_statements);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(73);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Int) | (1L << Unsigned) | (1L << Char) | (1L << Double) | (1L << Bool) | (1L << Void) | (1L << String) | (1L << For) | (1L << While) | (1L << If) | (1L << Return) | (1L << LeftParenthesis) | (1L << LeftBracket) | (1L << LeftBrace) | (1L << Semicolon) | (1L << Not) | (1L << Minus) | (1L << Identifier) | (1L << BoolConstatnt) | (1L << DoubleConstant) | (1L << IntegerConstant) | (1L << CharConstant) | (1L << StringConstant) | (1L << Newline) | (1L << LineComment) | (1L << BlockComment))) != 0)) {
				{
				setState(71);
				switch (_input.LA(1)) {
				case Int:
				case Unsigned:
				case Char:
				case Double:
				case Bool:
				case Void:
				case String:
				case For:
				case While:
				case If:
				case Return:
				case LeftParenthesis:
				case LeftBracket:
				case LeftBrace:
				case Semicolon:
				case Not:
				case Minus:
				case Identifier:
				case BoolConstatnt:
				case DoubleConstant:
				case IntegerConstant:
				case CharConstant:
				case StringConstant:
					{
					setState(67);
					statement();
					}
					break;
				case Newline:
					{
					setState(68);
					match(Newline);
					}
					break;
				case BlockComment:
					{
					setState(69);
					match(BlockComment);
					}
					break;
				case LineComment:
					{
					setState(70);
					match(LineComment);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(75);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DeclarationContext extends ParserRuleContext {
		public DeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declaration; }
	 
		public DeclarationContext() { }
		public void copyFrom(DeclarationContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class DeclarationSemicolContext extends DeclarationContext {
		public TerminalNode Semicolon() { return getToken(DerALParser.Semicolon, 0); }
		public DeclarationSemicolContext(DeclarationContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DerALVisitor ) return ((DerALVisitor<? extends T>)visitor).visitDeclarationSemicol(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class DeclarationGlobalVarContext extends DeclarationContext {
		public VariableDeclarationContext variableDeclaration() {
			return getRuleContext(VariableDeclarationContext.class,0);
		}
		public TerminalNode Semicolon() { return getToken(DerALParser.Semicolon, 0); }
		public DeclarationGlobalVarContext(DeclarationContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DerALVisitor ) return ((DerALVisitor<? extends T>)visitor).visitDeclarationGlobalVar(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class DeclarationFunctioDefinitionContext extends DeclarationContext {
		public FunctionDefinitionContext functionDefinition() {
			return getRuleContext(FunctionDefinitionContext.class,0);
		}
		public DeclarationFunctioDefinitionContext(DeclarationContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DerALVisitor ) return ((DerALVisitor<? extends T>)visitor).visitDeclarationFunctioDefinition(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class DeclarationFunctionForwardDeclarationContext extends DeclarationContext {
		public FunctionForwardDeclarationContext functionForwardDeclaration() {
			return getRuleContext(FunctionForwardDeclarationContext.class,0);
		}
		public TerminalNode Semicolon() { return getToken(DerALParser.Semicolon, 0); }
		public DeclarationFunctionForwardDeclarationContext(DeclarationContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DerALVisitor ) return ((DerALVisitor<? extends T>)visitor).visitDeclarationFunctionForwardDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class DeclarationFunctionExternContext extends DeclarationContext {
		public FunctionExternDeclarationContext functionExternDeclaration() {
			return getRuleContext(FunctionExternDeclarationContext.class,0);
		}
		public TerminalNode Semicolon() { return getToken(DerALParser.Semicolon, 0); }
		public DeclarationFunctionExternContext(DeclarationContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DerALVisitor ) return ((DerALVisitor<? extends T>)visitor).visitDeclarationFunctionExtern(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DeclarationContext declaration() throws RecognitionException {
		DeclarationContext _localctx = new DeclarationContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_declaration);
		try {
			setState(87);
			switch (_input.LA(1)) {
			case Def:
				_localctx = new DeclarationFunctioDefinitionContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(76);
				functionDefinition();
				}
				break;
			case Dec:
				_localctx = new DeclarationFunctionForwardDeclarationContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(77);
				functionForwardDeclaration();
				setState(78);
				match(Semicolon);
				}
				break;
			case Extern:
				_localctx = new DeclarationFunctionExternContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(80);
				functionExternDeclaration();
				setState(81);
				match(Semicolon);
				}
				break;
			case Int:
			case Unsigned:
			case Char:
			case Double:
			case Bool:
			case Void:
			case String:
				_localctx = new DeclarationGlobalVarContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(83);
				variableDeclaration();
				setState(84);
				match(Semicolon);
				}
				break;
			case Semicolon:
				_localctx = new DeclarationSemicolContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(86);
				match(Semicolon);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StatementContext extends ParserRuleContext {
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
	 
		public StatementContext() { }
		public void copyFrom(StatementContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class StatementVarDeclarationContext extends StatementContext {
		public VariableDeclarationContext variableDeclaration() {
			return getRuleContext(VariableDeclarationContext.class,0);
		}
		public TerminalNode Semicolon() { return getToken(DerALParser.Semicolon, 0); }
		public StatementVarDeclarationContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DerALVisitor ) return ((DerALVisitor<? extends T>)visitor).visitStatementVarDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class StatementCompoundContext extends StatementContext {
		public CompoundStatementContext compoundStatement() {
			return getRuleContext(CompoundStatementContext.class,0);
		}
		public StatementCompoundContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DerALVisitor ) return ((DerALVisitor<? extends T>)visitor).visitStatementCompound(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class StatementAssignmentContext extends StatementContext {
		public AssignmentContext assignment() {
			return getRuleContext(AssignmentContext.class,0);
		}
		public TerminalNode Semicolon() { return getToken(DerALParser.Semicolon, 0); }
		public StatementAssignmentContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DerALVisitor ) return ((DerALVisitor<? extends T>)visitor).visitStatementAssignment(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class StatementReturnContext extends StatementContext {
		public TerminalNode Return() { return getToken(DerALParser.Return, 0); }
		public TerminalNode Semicolon() { return getToken(DerALParser.Semicolon, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public StatementReturnContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DerALVisitor ) return ((DerALVisitor<? extends T>)visitor).visitStatementReturn(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class StatementCondContext extends StatementContext {
		public CondStatementContext condStatement() {
			return getRuleContext(CondStatementContext.class,0);
		}
		public StatementCondContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DerALVisitor ) return ((DerALVisitor<? extends T>)visitor).visitStatementCond(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class StatementLoopContext extends StatementContext {
		public LoopStatementContext loopStatement() {
			return getRuleContext(LoopStatementContext.class,0);
		}
		public StatementLoopContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DerALVisitor ) return ((DerALVisitor<? extends T>)visitor).visitStatementLoop(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class StatementSemicolContext extends StatementContext {
		public TerminalNode Semicolon() { return getToken(DerALParser.Semicolon, 0); }
		public StatementSemicolContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DerALVisitor ) return ((DerALVisitor<? extends T>)visitor).visitStatementSemicol(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class StatementExpressionContext extends StatementContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode Semicolon() { return getToken(DerALParser.Semicolon, 0); }
		public StatementExpressionContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DerALVisitor ) return ((DerALVisitor<? extends T>)visitor).visitStatementExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_statement);
		int _la;
		try {
			setState(107);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				_localctx = new StatementVarDeclarationContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(89);
				variableDeclaration();
				setState(90);
				match(Semicolon);
				}
				break;
			case 2:
				_localctx = new StatementAssignmentContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(92);
				assignment();
				setState(93);
				match(Semicolon);
				}
				break;
			case 3:
				_localctx = new StatementLoopContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(95);
				loopStatement();
				}
				break;
			case 4:
				_localctx = new StatementCondContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(96);
				condStatement();
				}
				break;
			case 5:
				_localctx = new StatementReturnContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(97);
				match(Return);
				setState(99);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LeftParenthesis) | (1L << LeftBracket) | (1L << Not) | (1L << Minus) | (1L << Identifier) | (1L << BoolConstatnt) | (1L << DoubleConstant) | (1L << IntegerConstant) | (1L << CharConstant) | (1L << StringConstant))) != 0)) {
					{
					setState(98);
					expression(0);
					}
				}

				setState(101);
				match(Semicolon);
				}
				break;
			case 6:
				_localctx = new StatementCompoundContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(102);
				compoundStatement();
				}
				break;
			case 7:
				_localctx = new StatementExpressionContext(_localctx);
				enterOuterAlt(_localctx, 7);
				{
				setState(103);
				expression(0);
				setState(104);
				match(Semicolon);
				}
				break;
			case 8:
				_localctx = new StatementSemicolContext(_localctx);
				enterOuterAlt(_localctx, 8);
				{
				setState(106);
				match(Semicolon);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LoopStatementContext extends ParserRuleContext {
		public LoopStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_loopStatement; }
	 
		public LoopStatementContext() { }
		public void copyFrom(LoopStatementContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class LoopWhileContext extends LoopStatementContext {
		public TerminalNode While() { return getToken(DerALParser.While, 0); }
		public TerminalNode LeftParenthesis() { return getToken(DerALParser.LeftParenthesis, 0); }
		public LoopCheckStatementContext loopCheckStatement() {
			return getRuleContext(LoopCheckStatementContext.class,0);
		}
		public TerminalNode RightParenthesis() { return getToken(DerALParser.RightParenthesis, 0); }
		public TerminalNode LeftBrace() { return getToken(DerALParser.LeftBrace, 0); }
		public StatementsContext statements() {
			return getRuleContext(StatementsContext.class,0);
		}
		public TerminalNode RightBrace() { return getToken(DerALParser.RightBrace, 0); }
		public List<TerminalNode> Newline() { return getTokens(DerALParser.Newline); }
		public TerminalNode Newline(int i) {
			return getToken(DerALParser.Newline, i);
		}
		public LoopWhileContext(LoopStatementContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DerALVisitor ) return ((DerALVisitor<? extends T>)visitor).visitLoopWhile(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class LoopForContext extends LoopStatementContext {
		public TerminalNode For() { return getToken(DerALParser.For, 0); }
		public TerminalNode LeftParenthesis() { return getToken(DerALParser.LeftParenthesis, 0); }
		public ForInitStatementContext forInitStatement() {
			return getRuleContext(ForInitStatementContext.class,0);
		}
		public List<TerminalNode> Semicolon() { return getTokens(DerALParser.Semicolon); }
		public TerminalNode Semicolon(int i) {
			return getToken(DerALParser.Semicolon, i);
		}
		public LoopCheckStatementContext loopCheckStatement() {
			return getRuleContext(LoopCheckStatementContext.class,0);
		}
		public ForLoopStatementContext forLoopStatement() {
			return getRuleContext(ForLoopStatementContext.class,0);
		}
		public TerminalNode RightParenthesis() { return getToken(DerALParser.RightParenthesis, 0); }
		public TerminalNode LeftBrace() { return getToken(DerALParser.LeftBrace, 0); }
		public StatementsContext statements() {
			return getRuleContext(StatementsContext.class,0);
		}
		public TerminalNode RightBrace() { return getToken(DerALParser.RightBrace, 0); }
		public List<TerminalNode> Newline() { return getTokens(DerALParser.Newline); }
		public TerminalNode Newline(int i) {
			return getToken(DerALParser.Newline, i);
		}
		public LoopForContext(LoopStatementContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DerALVisitor ) return ((DerALVisitor<? extends T>)visitor).visitLoopFor(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LoopStatementContext loopStatement() throws RecognitionException {
		LoopStatementContext _localctx = new LoopStatementContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_loopStatement);
		int _la;
		try {
			setState(141);
			switch (_input.LA(1)) {
			case While:
				_localctx = new LoopWhileContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(109);
				match(While);
				setState(110);
				match(LeftParenthesis);
				setState(111);
				loopCheckStatement();
				setState(112);
				match(RightParenthesis);
				setState(116);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==Newline) {
					{
					{
					setState(113);
					match(Newline);
					}
					}
					setState(118);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(119);
				match(LeftBrace);
				setState(120);
				statements();
				setState(121);
				match(RightBrace);
				}
				break;
			case For:
				_localctx = new LoopForContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(123);
				match(For);
				setState(124);
				match(LeftParenthesis);
				setState(125);
				forInitStatement();
				setState(126);
				match(Semicolon);
				setState(127);
				loopCheckStatement();
				setState(128);
				match(Semicolon);
				setState(129);
				forLoopStatement();
				setState(130);
				match(RightParenthesis);
				setState(134);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==Newline) {
					{
					{
					setState(131);
					match(Newline);
					}
					}
					setState(136);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(137);
				match(LeftBrace);
				setState(138);
				statements();
				setState(139);
				match(RightBrace);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LoopCheckStatementContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public LoopCheckStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_loopCheckStatement; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DerALVisitor ) return ((DerALVisitor<? extends T>)visitor).visitLoopCheckStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LoopCheckStatementContext loopCheckStatement() throws RecognitionException {
		LoopCheckStatementContext _localctx = new LoopCheckStatementContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_loopCheckStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(144);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LeftParenthesis) | (1L << LeftBracket) | (1L << Not) | (1L << Minus) | (1L << Identifier) | (1L << BoolConstatnt) | (1L << DoubleConstant) | (1L << IntegerConstant) | (1L << CharConstant) | (1L << StringConstant))) != 0)) {
				{
				setState(143);
				expression(0);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ForInitStatementContext extends ParserRuleContext {
		public ForInitStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_forInitStatement; }
	 
		public ForInitStatementContext() { }
		public void copyFrom(ForInitStatementContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ForInitVarDeclContext extends ForInitStatementContext {
		public VariableDeclarationContext variableDeclaration() {
			return getRuleContext(VariableDeclarationContext.class,0);
		}
		public ForInitVarDeclContext(ForInitStatementContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DerALVisitor ) return ((DerALVisitor<? extends T>)visitor).visitForInitVarDecl(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ForInitEmptyContext extends ForInitStatementContext {
		public ForInitEmptyContext(ForInitStatementContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DerALVisitor ) return ((DerALVisitor<? extends T>)visitor).visitForInitEmpty(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ForInitAssignmentContext extends ForInitStatementContext {
		public AssignmentContext assignment() {
			return getRuleContext(AssignmentContext.class,0);
		}
		public ForInitAssignmentContext(ForInitStatementContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DerALVisitor ) return ((DerALVisitor<? extends T>)visitor).visitForInitAssignment(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ForInitStatementContext forInitStatement() throws RecognitionException {
		ForInitStatementContext _localctx = new ForInitStatementContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_forInitStatement);
		try {
			setState(149);
			switch (_input.LA(1)) {
			case Int:
			case Unsigned:
			case Char:
			case Double:
			case Bool:
			case Void:
			case String:
				_localctx = new ForInitVarDeclContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(146);
				variableDeclaration();
				}
				break;
			case Identifier:
				_localctx = new ForInitAssignmentContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(147);
				assignment();
				}
				break;
			case Semicolon:
				_localctx = new ForInitEmptyContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ForLoopStatementContext extends ParserRuleContext {
		public ForLoopStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_forLoopStatement; }
	 
		public ForLoopStatementContext() { }
		public void copyFrom(ForLoopStatementContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ForLoopStatementEmptyContext extends ForLoopStatementContext {
		public ForLoopStatementEmptyContext(ForLoopStatementContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DerALVisitor ) return ((DerALVisitor<? extends T>)visitor).visitForLoopStatementEmpty(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ForLoopStatementAssignmentContext extends ForLoopStatementContext {
		public AssignmentContext assignment() {
			return getRuleContext(AssignmentContext.class,0);
		}
		public ForLoopStatementAssignmentContext(ForLoopStatementContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DerALVisitor ) return ((DerALVisitor<? extends T>)visitor).visitForLoopStatementAssignment(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ForLoopStatementContext forLoopStatement() throws RecognitionException {
		ForLoopStatementContext _localctx = new ForLoopStatementContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_forLoopStatement);
		try {
			setState(153);
			switch (_input.LA(1)) {
			case Identifier:
				_localctx = new ForLoopStatementAssignmentContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(151);
				assignment();
				}
				break;
			case RightParenthesis:
				_localctx = new ForLoopStatementEmptyContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CondStatementContext extends ParserRuleContext {
		public TerminalNode If() { return getToken(DerALParser.If, 0); }
		public TerminalNode LeftParenthesis() { return getToken(DerALParser.LeftParenthesis, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RightParenthesis() { return getToken(DerALParser.RightParenthesis, 0); }
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public List<TerminalNode> Newline() { return getTokens(DerALParser.Newline); }
		public TerminalNode Newline(int i) {
			return getToken(DerALParser.Newline, i);
		}
		public TerminalNode Else() { return getToken(DerALParser.Else, 0); }
		public CondStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_condStatement; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DerALVisitor ) return ((DerALVisitor<? extends T>)visitor).visitCondStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CondStatementContext condStatement() throws RecognitionException {
		CondStatementContext _localctx = new CondStatementContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_condStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(155);
			match(If);
			setState(156);
			match(LeftParenthesis);
			setState(157);
			expression(0);
			setState(158);
			match(RightParenthesis);
			setState(162);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Newline) {
				{
				{
				setState(159);
				match(Newline);
				}
				}
				setState(164);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(165);
			statement();
			setState(180);
			switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
			case 1:
				{
				setState(169);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==Newline) {
					{
					{
					setState(166);
					match(Newline);
					}
					}
					setState(171);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(172);
				match(Else);
				setState(176);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==Newline) {
					{
					{
					setState(173);
					match(Newline);
					}
					}
					setState(178);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(179);
				statement();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AssignmentContext extends ParserRuleContext {
		public Token assignOp;
		public TerminalNode Identifier() { return getToken(DerALParser.Identifier, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public List<ArrayIndexContext> arrayIndex() {
			return getRuleContexts(ArrayIndexContext.class);
		}
		public ArrayIndexContext arrayIndex(int i) {
			return getRuleContext(ArrayIndexContext.class,i);
		}
		public TerminalNode Assign() { return getToken(DerALParser.Assign, 0); }
		public TerminalNode PlusAssign() { return getToken(DerALParser.PlusAssign, 0); }
		public TerminalNode MinusAssign() { return getToken(DerALParser.MinusAssign, 0); }
		public TerminalNode StarAssign() { return getToken(DerALParser.StarAssign, 0); }
		public TerminalNode DivAssign() { return getToken(DerALParser.DivAssign, 0); }
		public TerminalNode ModAssign() { return getToken(DerALParser.ModAssign, 0); }
		public AssignmentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignment; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DerALVisitor ) return ((DerALVisitor<? extends T>)visitor).visitAssignment(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AssignmentContext assignment() throws RecognitionException {
		AssignmentContext _localctx = new AssignmentContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_assignment);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(182);
			match(Identifier);
			setState(186);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==LeftBracket) {
				{
				{
				setState(183);
				arrayIndex();
				}
				}
				setState(188);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			{
			setState(189);
			((AssignmentContext)_localctx).assignOp = _input.LT(1);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << PlusAssign) | (1L << MinusAssign) | (1L << StarAssign) | (1L << DivAssign) | (1L << Assign) | (1L << ModAssign))) != 0)) ) {
				((AssignmentContext)_localctx).assignOp = (Token)_errHandler.recoverInline(this);
			} else {
				consume();
			}
			}
			setState(190);
			expression(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CompoundStatementContext extends ParserRuleContext {
		public TerminalNode LeftBrace() { return getToken(DerALParser.LeftBrace, 0); }
		public StatementsContext statements() {
			return getRuleContext(StatementsContext.class,0);
		}
		public TerminalNode RightBrace() { return getToken(DerALParser.RightBrace, 0); }
		public CompoundStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_compoundStatement; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DerALVisitor ) return ((DerALVisitor<? extends T>)visitor).visitCompoundStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CompoundStatementContext compoundStatement() throws RecognitionException {
		CompoundStatementContext _localctx = new CompoundStatementContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_compoundStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(192);
			match(LeftBrace);
			setState(193);
			statements();
			setState(194);
			match(RightBrace);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeSpecificationContext extends ParserRuleContext {
		public TypeSpecificationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeSpecification; }
	 
		public TypeSpecificationContext() { }
		public void copyFrom(TypeSpecificationContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ArrayAllocTypeSpecificationContext extends TypeSpecificationContext {
		public TypeNameContext typeName() {
			return getRuleContext(TypeNameContext.class,0);
		}
		public List<ArrayIndexContext> arrayIndex() {
			return getRuleContexts(ArrayIndexContext.class);
		}
		public ArrayIndexContext arrayIndex(int i) {
			return getRuleContext(ArrayIndexContext.class,i);
		}
		public ArrayAllocTypeSpecificationContext(TypeSpecificationContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DerALVisitor ) return ((DerALVisitor<? extends T>)visitor).visitArrayAllocTypeSpecification(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BasicTypeSpecificationContext extends TypeSpecificationContext {
		public TypeNameContext typeName() {
			return getRuleContext(TypeNameContext.class,0);
		}
		public BasicTypeSpecificationContext(TypeSpecificationContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DerALVisitor ) return ((DerALVisitor<? extends T>)visitor).visitBasicTypeSpecification(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class VoidTypeSpecificationContext extends TypeSpecificationContext {
		public TerminalNode Void() { return getToken(DerALParser.Void, 0); }
		public VoidTypeSpecificationContext(TypeSpecificationContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DerALVisitor ) return ((DerALVisitor<? extends T>)visitor).visitVoidTypeSpecification(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ArrayNoAllocTypeSpecificationContext extends TypeSpecificationContext {
		public TypeNameContext typeName() {
			return getRuleContext(TypeNameContext.class,0);
		}
		public List<TerminalNode> LeftBracket() { return getTokens(DerALParser.LeftBracket); }
		public TerminalNode LeftBracket(int i) {
			return getToken(DerALParser.LeftBracket, i);
		}
		public List<TerminalNode> RightBracket() { return getTokens(DerALParser.RightBracket); }
		public TerminalNode RightBracket(int i) {
			return getToken(DerALParser.RightBracket, i);
		}
		public ArrayNoAllocTypeSpecificationContext(TypeSpecificationContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DerALVisitor ) return ((DerALVisitor<? extends T>)visitor).visitArrayNoAllocTypeSpecification(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeSpecificationContext typeSpecification() throws RecognitionException {
		TypeSpecificationContext _localctx = new TypeSpecificationContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_typeSpecification);
		int _la;
		try {
			setState(211);
			switch ( getInterpreter().adaptivePredict(_input,20,_ctx) ) {
			case 1:
				_localctx = new ArrayAllocTypeSpecificationContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(196);
				typeName();
				setState(198); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(197);
					arrayIndex();
					}
					}
					setState(200); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==LeftBracket );
				}
				break;
			case 2:
				_localctx = new ArrayNoAllocTypeSpecificationContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(202);
				typeName();
				setState(205); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(203);
					match(LeftBracket);
					setState(204);
					match(RightBracket);
					}
					}
					setState(207); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==LeftBracket );
				}
				break;
			case 3:
				_localctx = new BasicTypeSpecificationContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(209);
				typeName();
				}
				break;
			case 4:
				_localctx = new VoidTypeSpecificationContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(210);
				match(Void);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VariableDeclarationContext extends ParserRuleContext {
		public TypeSpecificationContext typeSpecification() {
			return getRuleContext(TypeSpecificationContext.class,0);
		}
		public TerminalNode Identifier() { return getToken(DerALParser.Identifier, 0); }
		public TerminalNode Assign() { return getToken(DerALParser.Assign, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public VariableDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variableDeclaration; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DerALVisitor ) return ((DerALVisitor<? extends T>)visitor).visitVariableDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VariableDeclarationContext variableDeclaration() throws RecognitionException {
		VariableDeclarationContext _localctx = new VariableDeclarationContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_variableDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(213);
			typeSpecification();
			setState(214);
			match(Identifier);
			setState(217);
			_la = _input.LA(1);
			if (_la==Assign) {
				{
				setState(215);
				match(Assign);
				setState(216);
				expression(0);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FunctionExternDeclarationContext extends ParserRuleContext {
		public TerminalNode Extern() { return getToken(DerALParser.Extern, 0); }
		public TypeSpecificationContext typeSpecification() {
			return getRuleContext(TypeSpecificationContext.class,0);
		}
		public TerminalNode Identifier() { return getToken(DerALParser.Identifier, 0); }
		public TerminalNode LeftParenthesis() { return getToken(DerALParser.LeftParenthesis, 0); }
		public DefinitionArgsListContext definitionArgsList() {
			return getRuleContext(DefinitionArgsListContext.class,0);
		}
		public TerminalNode RightParenthesis() { return getToken(DerALParser.RightParenthesis, 0); }
		public FunctionExternDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionExternDeclaration; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DerALVisitor ) return ((DerALVisitor<? extends T>)visitor).visitFunctionExternDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunctionExternDeclarationContext functionExternDeclaration() throws RecognitionException {
		FunctionExternDeclarationContext _localctx = new FunctionExternDeclarationContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_functionExternDeclaration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(219);
			match(Extern);
			setState(220);
			typeSpecification();
			setState(221);
			match(Identifier);
			setState(222);
			match(LeftParenthesis);
			setState(223);
			definitionArgsList();
			setState(224);
			match(RightParenthesis);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FunctionForwardDeclarationContext extends ParserRuleContext {
		public TerminalNode Dec() { return getToken(DerALParser.Dec, 0); }
		public TypeSpecificationContext typeSpecification() {
			return getRuleContext(TypeSpecificationContext.class,0);
		}
		public TerminalNode Identifier() { return getToken(DerALParser.Identifier, 0); }
		public TerminalNode LeftParenthesis() { return getToken(DerALParser.LeftParenthesis, 0); }
		public DefinitionArgsListContext definitionArgsList() {
			return getRuleContext(DefinitionArgsListContext.class,0);
		}
		public TerminalNode RightParenthesis() { return getToken(DerALParser.RightParenthesis, 0); }
		public FunctionForwardDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionForwardDeclaration; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DerALVisitor ) return ((DerALVisitor<? extends T>)visitor).visitFunctionForwardDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunctionForwardDeclarationContext functionForwardDeclaration() throws RecognitionException {
		FunctionForwardDeclarationContext _localctx = new FunctionForwardDeclarationContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_functionForwardDeclaration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(226);
			match(Dec);
			setState(227);
			typeSpecification();
			setState(228);
			match(Identifier);
			setState(229);
			match(LeftParenthesis);
			setState(230);
			definitionArgsList();
			setState(231);
			match(RightParenthesis);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FunctionDefinitionContext extends ParserRuleContext {
		public TerminalNode Def() { return getToken(DerALParser.Def, 0); }
		public TypeSpecificationContext typeSpecification() {
			return getRuleContext(TypeSpecificationContext.class,0);
		}
		public TerminalNode Identifier() { return getToken(DerALParser.Identifier, 0); }
		public TerminalNode LeftParenthesis() { return getToken(DerALParser.LeftParenthesis, 0); }
		public DefinitionArgsListContext definitionArgsList() {
			return getRuleContext(DefinitionArgsListContext.class,0);
		}
		public TerminalNode RightParenthesis() { return getToken(DerALParser.RightParenthesis, 0); }
		public FunctionBodyContext functionBody() {
			return getRuleContext(FunctionBodyContext.class,0);
		}
		public List<TerminalNode> Newline() { return getTokens(DerALParser.Newline); }
		public TerminalNode Newline(int i) {
			return getToken(DerALParser.Newline, i);
		}
		public FunctionDefinitionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionDefinition; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DerALVisitor ) return ((DerALVisitor<? extends T>)visitor).visitFunctionDefinition(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunctionDefinitionContext functionDefinition() throws RecognitionException {
		FunctionDefinitionContext _localctx = new FunctionDefinitionContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_functionDefinition);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(233);
			match(Def);
			setState(234);
			typeSpecification();
			setState(235);
			match(Identifier);
			setState(236);
			match(LeftParenthesis);
			setState(237);
			definitionArgsList();
			setState(238);
			match(RightParenthesis);
			setState(242);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Newline) {
				{
				{
				setState(239);
				match(Newline);
				}
				}
				setState(244);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(245);
			functionBody();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DefinitionArgsListContext extends ParserRuleContext {
		public List<DefinitionArgSpecificationContext> definitionArgSpecification() {
			return getRuleContexts(DefinitionArgSpecificationContext.class);
		}
		public DefinitionArgSpecificationContext definitionArgSpecification(int i) {
			return getRuleContext(DefinitionArgSpecificationContext.class,i);
		}
		public DefinitionArgsListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_definitionArgsList; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DerALVisitor ) return ((DerALVisitor<? extends T>)visitor).visitDefinitionArgsList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DefinitionArgsListContext definitionArgsList() throws RecognitionException {
		DefinitionArgsListContext _localctx = new DefinitionArgsListContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_definitionArgsList);
		int _la;
		try {
			setState(256);
			switch (_input.LA(1)) {
			case Int:
			case Unsigned:
			case Char:
			case Double:
			case Bool:
			case Void:
			case String:
				enterOuterAlt(_localctx, 1);
				{
				setState(247);
				definitionArgSpecification();
				setState(252);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__0) {
					{
					{
					setState(248);
					match(T__0);
					setState(249);
					definitionArgSpecification();
					}
					}
					setState(254);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			case RightParenthesis:
				enterOuterAlt(_localctx, 2);
				{
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DefinitionArgSpecificationContext extends ParserRuleContext {
		public TypeSpecificationContext typeSpecification() {
			return getRuleContext(TypeSpecificationContext.class,0);
		}
		public TerminalNode Identifier() { return getToken(DerALParser.Identifier, 0); }
		public DefinitionArgSpecificationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_definitionArgSpecification; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DerALVisitor ) return ((DerALVisitor<? extends T>)visitor).visitDefinitionArgSpecification(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DefinitionArgSpecificationContext definitionArgSpecification() throws RecognitionException {
		DefinitionArgSpecificationContext _localctx = new DefinitionArgSpecificationContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_definitionArgSpecification);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(258);
			typeSpecification();
			setState(259);
			match(Identifier);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FunctionBodyContext extends ParserRuleContext {
		public TerminalNode LeftBrace() { return getToken(DerALParser.LeftBrace, 0); }
		public StatementsContext statements() {
			return getRuleContext(StatementsContext.class,0);
		}
		public TerminalNode RightBrace() { return getToken(DerALParser.RightBrace, 0); }
		public FunctionBodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionBody; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DerALVisitor ) return ((DerALVisitor<? extends T>)visitor).visitFunctionBody(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunctionBodyContext functionBody() throws RecognitionException {
		FunctionBodyContext _localctx = new FunctionBodyContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_functionBody);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(261);
			match(LeftBrace);
			setState(262);
			statements();
			setState(263);
			match(RightBrace);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionContext extends ParserRuleContext {
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
	 
		public ExpressionContext() { }
		public void copyFrom(ExpressionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class StringConstExpressionContext extends ExpressionContext {
		public TerminalNode StringConstant() { return getToken(DerALParser.StringConstant, 0); }
		public StringConstExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DerALVisitor ) return ((DerALVisitor<? extends T>)visitor).visitStringConstExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class VarExpressionContext extends ExpressionContext {
		public TerminalNode Identifier() { return getToken(DerALParser.Identifier, 0); }
		public List<ArrayIndexContext> arrayIndex() {
			return getRuleContexts(ArrayIndexContext.class);
		}
		public ArrayIndexContext arrayIndex(int i) {
			return getRuleContext(ArrayIndexContext.class,i);
		}
		public VarExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DerALVisitor ) return ((DerALVisitor<? extends T>)visitor).visitVarExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class RelExpressionContext extends ExpressionContext {
		public Token op;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode Equal() { return getToken(DerALParser.Equal, 0); }
		public TerminalNode Less() { return getToken(DerALParser.Less, 0); }
		public TerminalNode Greater() { return getToken(DerALParser.Greater, 0); }
		public TerminalNode LessEqual() { return getToken(DerALParser.LessEqual, 0); }
		public TerminalNode GreaterEqual() { return getToken(DerALParser.GreaterEqual, 0); }
		public TerminalNode NotEqual() { return getToken(DerALParser.NotEqual, 0); }
		public RelExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DerALVisitor ) return ((DerALVisitor<? extends T>)visitor).visitRelExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IntConstExpressionContext extends ExpressionContext {
		public TerminalNode IntegerConstant() { return getToken(DerALParser.IntegerConstant, 0); }
		public IntConstExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DerALVisitor ) return ((DerALVisitor<? extends T>)visitor).visitIntConstExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class OrExpressionContext extends ExpressionContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode Or() { return getToken(DerALParser.Or, 0); }
		public OrExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DerALVisitor ) return ((DerALVisitor<? extends T>)visitor).visitOrExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class FuncCallExpressionContext extends ExpressionContext {
		public TerminalNode Identifier() { return getToken(DerALParser.Identifier, 0); }
		public TerminalNode LeftParenthesis() { return getToken(DerALParser.LeftParenthesis, 0); }
		public ArgListFuncCallContext argListFuncCall() {
			return getRuleContext(ArgListFuncCallContext.class,0);
		}
		public TerminalNode RightParenthesis() { return getToken(DerALParser.RightParenthesis, 0); }
		public FuncCallExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DerALVisitor ) return ((DerALVisitor<? extends T>)visitor).visitFuncCallExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class DoubleConstExpressionContext extends ExpressionContext {
		public TerminalNode DoubleConstant() { return getToken(DerALParser.DoubleConstant, 0); }
		public DoubleConstExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DerALVisitor ) return ((DerALVisitor<? extends T>)visitor).visitDoubleConstExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class AndExpressionContext extends ExpressionContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode And() { return getToken(DerALParser.And, 0); }
		public AndExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DerALVisitor ) return ((DerALVisitor<? extends T>)visitor).visitAndExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class AddExpressionContext extends ExpressionContext {
		public Token op;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode Plus() { return getToken(DerALParser.Plus, 0); }
		public TerminalNode Minus() { return getToken(DerALParser.Minus, 0); }
		public AddExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DerALVisitor ) return ((DerALVisitor<? extends T>)visitor).visitAddExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BoolConstExpressionContext extends ExpressionContext {
		public TerminalNode BoolConstatnt() { return getToken(DerALParser.BoolConstatnt, 0); }
		public BoolConstExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DerALVisitor ) return ((DerALVisitor<? extends T>)visitor).visitBoolConstExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class UnaryExpressionContext extends ExpressionContext {
		public Token op;
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode Minus() { return getToken(DerALParser.Minus, 0); }
		public TerminalNode Not() { return getToken(DerALParser.Not, 0); }
		public UnaryExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DerALVisitor ) return ((DerALVisitor<? extends T>)visitor).visitUnaryExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class CharConstExpressionContext extends ExpressionContext {
		public TerminalNode CharConstant() { return getToken(DerALParser.CharConstant, 0); }
		public CharConstExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DerALVisitor ) return ((DerALVisitor<? extends T>)visitor).visitCharConstExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ArrayAllocExpressionContext extends ExpressionContext {
		public List<ArrayIndexContext> arrayIndex() {
			return getRuleContexts(ArrayIndexContext.class);
		}
		public ArrayIndexContext arrayIndex(int i) {
			return getRuleContext(ArrayIndexContext.class,i);
		}
		public ArrayAllocExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DerALVisitor ) return ((DerALVisitor<? extends T>)visitor).visitArrayAllocExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ParExpressionContext extends ExpressionContext {
		public TerminalNode LeftParenthesis() { return getToken(DerALParser.LeftParenthesis, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RightParenthesis() { return getToken(DerALParser.RightParenthesis, 0); }
		public ParExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DerALVisitor ) return ((DerALVisitor<? extends T>)visitor).visitParExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class MulExpressionContext extends ExpressionContext {
		public Token op;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode Star() { return getToken(DerALParser.Star, 0); }
		public TerminalNode Div() { return getToken(DerALParser.Div, 0); }
		public TerminalNode Mod() { return getToken(DerALParser.Mod, 0); }
		public MulExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DerALVisitor ) return ((DerALVisitor<? extends T>)visitor).visitMulExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		return expression(0);
	}

	private ExpressionContext expression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExpressionContext _localctx = new ExpressionContext(_ctx, _parentState);
		ExpressionContext _prevctx = _localctx;
		int _startState = 42;
		enterRecursionRule(_localctx, 42, RULE_expression, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(294);
			switch ( getInterpreter().adaptivePredict(_input,27,_ctx) ) {
			case 1:
				{
				_localctx = new UnaryExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(266);
				((UnaryExpressionContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==Not || _la==Minus) ) {
					((UnaryExpressionContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(267);
				expression(15);
				}
				break;
			case 2:
				{
				_localctx = new ParExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(268);
				match(LeftParenthesis);
				setState(269);
				expression(0);
				setState(270);
				match(RightParenthesis);
				}
				break;
			case 3:
				{
				_localctx = new BoolConstExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(272);
				match(BoolConstatnt);
				}
				break;
			case 4:
				{
				_localctx = new VarExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(273);
				match(Identifier);
				setState(277);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,25,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(274);
						arrayIndex();
						}
						} 
					}
					setState(279);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,25,_ctx);
				}
				}
				break;
			case 5:
				{
				_localctx = new FuncCallExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(280);
				match(Identifier);
				setState(281);
				match(LeftParenthesis);
				setState(282);
				argListFuncCall();
				setState(283);
				match(RightParenthesis);
				}
				break;
			case 6:
				{
				_localctx = new IntConstExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(285);
				match(IntegerConstant);
				}
				break;
			case 7:
				{
				_localctx = new DoubleConstExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(286);
				match(DoubleConstant);
				}
				break;
			case 8:
				{
				_localctx = new CharConstExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(287);
				match(CharConstant);
				}
				break;
			case 9:
				{
				_localctx = new StringConstExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(288);
				match(StringConstant);
				}
				break;
			case 10:
				{
				_localctx = new ArrayAllocExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(290); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(289);
						arrayIndex();
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(292); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,26,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(313);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,29,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(311);
					switch ( getInterpreter().adaptivePredict(_input,28,_ctx) ) {
					case 1:
						{
						_localctx = new MulExpressionContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(296);
						if (!(precpred(_ctx, 14))) throw new FailedPredicateException(this, "precpred(_ctx, 14)");
						setState(297);
						((MulExpressionContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Star) | (1L << Div) | (1L << Mod))) != 0)) ) {
							((MulExpressionContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						} else {
							consume();
						}
						setState(298);
						expression(15);
						}
						break;
					case 2:
						{
						_localctx = new AddExpressionContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(299);
						if (!(precpred(_ctx, 13))) throw new FailedPredicateException(this, "precpred(_ctx, 13)");
						setState(300);
						((AddExpressionContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==Plus || _la==Minus) ) {
							((AddExpressionContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						} else {
							consume();
						}
						setState(301);
						expression(14);
						}
						break;
					case 3:
						{
						_localctx = new RelExpressionContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(302);
						if (!(precpred(_ctx, 12))) throw new FailedPredicateException(this, "precpred(_ctx, 12)");
						setState(303);
						((RelExpressionContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Equal) | (1L << NotEqual) | (1L << LessEqual) | (1L << GreaterEqual) | (1L << Less) | (1L << Greater))) != 0)) ) {
							((RelExpressionContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						} else {
							consume();
						}
						setState(304);
						expression(13);
						}
						break;
					case 4:
						{
						_localctx = new AndExpressionContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(305);
						if (!(precpred(_ctx, 11))) throw new FailedPredicateException(this, "precpred(_ctx, 11)");
						setState(306);
						match(And);
						setState(307);
						expression(12);
						}
						break;
					case 5:
						{
						_localctx = new OrExpressionContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(308);
						if (!(precpred(_ctx, 10))) throw new FailedPredicateException(this, "precpred(_ctx, 10)");
						setState(309);
						match(Or);
						setState(310);
						expression(11);
						}
						break;
					}
					} 
				}
				setState(315);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,29,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class ArgListFuncCallContext extends ParserRuleContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public ArgListFuncCallContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_argListFuncCall; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DerALVisitor ) return ((DerALVisitor<? extends T>)visitor).visitArgListFuncCall(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArgListFuncCallContext argListFuncCall() throws RecognitionException {
		ArgListFuncCallContext _localctx = new ArgListFuncCallContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_argListFuncCall);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(324);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LeftParenthesis) | (1L << LeftBracket) | (1L << Not) | (1L << Minus) | (1L << Identifier) | (1L << BoolConstatnt) | (1L << DoubleConstant) | (1L << IntegerConstant) | (1L << CharConstant) | (1L << StringConstant))) != 0)) {
				{
				setState(316);
				expression(0);
				setState(321);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__0) {
					{
					{
					setState(317);
					match(T__0);
					setState(318);
					expression(0);
					}
					}
					setState(323);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArrayIndexContext extends ParserRuleContext {
		public TerminalNode LeftBracket() { return getToken(DerALParser.LeftBracket, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RightBracket() { return getToken(DerALParser.RightBracket, 0); }
		public ArrayIndexContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arrayIndex; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DerALVisitor ) return ((DerALVisitor<? extends T>)visitor).visitArrayIndex(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArrayIndexContext arrayIndex() throws RecognitionException {
		ArrayIndexContext _localctx = new ArrayIndexContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_arrayIndex);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(326);
			match(LeftBracket);
			setState(327);
			expression(0);
			setState(328);
			match(RightBracket);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeNameContext extends ParserRuleContext {
		public TerminalNode Int() { return getToken(DerALParser.Int, 0); }
		public TerminalNode Unsigned() { return getToken(DerALParser.Unsigned, 0); }
		public TerminalNode Char() { return getToken(DerALParser.Char, 0); }
		public TerminalNode Double() { return getToken(DerALParser.Double, 0); }
		public TerminalNode Bool() { return getToken(DerALParser.Bool, 0); }
		public TerminalNode String() { return getToken(DerALParser.String, 0); }
		public TypeNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeName; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof DerALVisitor ) return ((DerALVisitor<? extends T>)visitor).visitTypeName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeNameContext typeName() throws RecognitionException {
		TypeNameContext _localctx = new TypeNameContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_typeName);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(330);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Int) | (1L << Unsigned) | (1L << Char) | (1L << Double) | (1L << Bool) | (1L << String))) != 0)) ) {
			_errHandler.recoverInline(this);
			} else {
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 21:
			return expression_sempred((ExpressionContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expression_sempred(ExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 14);
		case 1:
			return precpred(_ctx, 13);
		case 2:
			return precpred(_ctx, 12);
		case 3:
			return precpred(_ctx, 11);
		case 4:
			return precpred(_ctx, 10);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3>\u014f\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\7\3?\n\3\f\3\16\3B"+
		"\13\3\3\4\3\4\3\5\3\5\3\5\3\5\7\5J\n\5\f\5\16\5M\13\5\3\6\3\6\3\6\3\6"+
		"\3\6\3\6\3\6\3\6\3\6\3\6\3\6\5\6Z\n\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7"+
		"\3\7\3\7\5\7f\n\7\3\7\3\7\3\7\3\7\3\7\3\7\5\7n\n\7\3\b\3\b\3\b\3\b\3\b"+
		"\7\bu\n\b\f\b\16\bx\13\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3"+
		"\b\3\b\7\b\u0087\n\b\f\b\16\b\u008a\13\b\3\b\3\b\3\b\3\b\5\b\u0090\n\b"+
		"\3\t\5\t\u0093\n\t\3\n\3\n\3\n\5\n\u0098\n\n\3\13\3\13\5\13\u009c\n\13"+
		"\3\f\3\f\3\f\3\f\3\f\7\f\u00a3\n\f\f\f\16\f\u00a6\13\f\3\f\3\f\7\f\u00aa"+
		"\n\f\f\f\16\f\u00ad\13\f\3\f\3\f\7\f\u00b1\n\f\f\f\16\f\u00b4\13\f\3\f"+
		"\5\f\u00b7\n\f\3\r\3\r\7\r\u00bb\n\r\f\r\16\r\u00be\13\r\3\r\3\r\3\r\3"+
		"\16\3\16\3\16\3\16\3\17\3\17\6\17\u00c9\n\17\r\17\16\17\u00ca\3\17\3\17"+
		"\3\17\6\17\u00d0\n\17\r\17\16\17\u00d1\3\17\3\17\5\17\u00d6\n\17\3\20"+
		"\3\20\3\20\3\20\5\20\u00dc\n\20\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\22"+
		"\3\22\3\22\3\22\3\22\3\22\3\22\3\23\3\23\3\23\3\23\3\23\3\23\3\23\7\23"+
		"\u00f3\n\23\f\23\16\23\u00f6\13\23\3\23\3\23\3\24\3\24\3\24\7\24\u00fd"+
		"\n\24\f\24\16\24\u0100\13\24\3\24\5\24\u0103\n\24\3\25\3\25\3\25\3\26"+
		"\3\26\3\26\3\26\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\7\27"+
		"\u0116\n\27\f\27\16\27\u0119\13\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27"+
		"\3\27\3\27\3\27\6\27\u0125\n\27\r\27\16\27\u0126\5\27\u0129\n\27\3\27"+
		"\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27"+
		"\7\27\u013a\n\27\f\27\16\27\u013d\13\27\3\30\3\30\3\30\7\30\u0142\n\30"+
		"\f\30\16\30\u0145\13\30\5\30\u0147\n\30\3\31\3\31\3\31\3\31\3\32\3\32"+
		"\3\32\2\3,\33\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62\2"+
		"\b\3\2#(\4\2--\61\61\3\2\62\64\3\2\60\61\5\2!\")*./\4\2\4\b\n\n\u0170"+
		"\2\64\3\2\2\2\4@\3\2\2\2\6C\3\2\2\2\bK\3\2\2\2\nY\3\2\2\2\fm\3\2\2\2\16"+
		"\u008f\3\2\2\2\20\u0092\3\2\2\2\22\u0097\3\2\2\2\24\u009b\3\2\2\2\26\u009d"+
		"\3\2\2\2\30\u00b8\3\2\2\2\32\u00c2\3\2\2\2\34\u00d5\3\2\2\2\36\u00d7\3"+
		"\2\2\2 \u00dd\3\2\2\2\"\u00e4\3\2\2\2$\u00eb\3\2\2\2&\u0102\3\2\2\2(\u0104"+
		"\3\2\2\2*\u0107\3\2\2\2,\u0128\3\2\2\2.\u0146\3\2\2\2\60\u0148\3\2\2\2"+
		"\62\u014c\3\2\2\2\64\65\5\4\3\2\65\66\7\13\2\2\66\67\7;\2\2\678\5\6\4"+
		"\289\7\2\2\39\3\3\2\2\2:?\5\n\6\2;?\7;\2\2<?\7>\2\2=?\7=\2\2>:\3\2\2\2"+
		">;\3\2\2\2><\3\2\2\2>=\3\2\2\2?B\3\2\2\2@>\3\2\2\2@A\3\2\2\2A\5\3\2\2"+
		"\2B@\3\2\2\2CD\5\b\5\2D\7\3\2\2\2EJ\5\f\7\2FJ\7;\2\2GJ\7>\2\2HJ\7=\2\2"+
		"IE\3\2\2\2IF\3\2\2\2IG\3\2\2\2IH\3\2\2\2JM\3\2\2\2KI\3\2\2\2KL\3\2\2\2"+
		"L\t\3\2\2\2MK\3\2\2\2NZ\5$\23\2OP\5\"\22\2PQ\7 \2\2QZ\3\2\2\2RS\5 \21"+
		"\2ST\7 \2\2TZ\3\2\2\2UV\5\36\20\2VW\7 \2\2WZ\3\2\2\2XZ\7 \2\2YN\3\2\2"+
		"\2YO\3\2\2\2YR\3\2\2\2YU\3\2\2\2YX\3\2\2\2Z\13\3\2\2\2[\\\5\36\20\2\\"+
		"]\7 \2\2]n\3\2\2\2^_\5\30\r\2_`\7 \2\2`n\3\2\2\2an\5\16\b\2bn\5\26\f\2"+
		"ce\7\22\2\2df\5,\27\2ed\3\2\2\2ef\3\2\2\2fg\3\2\2\2gn\7 \2\2hn\5\32\16"+
		"\2ij\5,\27\2jk\7 \2\2kn\3\2\2\2ln\7 \2\2m[\3\2\2\2m^\3\2\2\2ma\3\2\2\2"+
		"mb\3\2\2\2mc\3\2\2\2mh\3\2\2\2mi\3\2\2\2ml\3\2\2\2n\r\3\2\2\2op\7\r\2"+
		"\2pq\7\32\2\2qr\5\20\t\2rv\7\33\2\2su\7;\2\2ts\3\2\2\2ux\3\2\2\2vt\3\2"+
		"\2\2vw\3\2\2\2wy\3\2\2\2xv\3\2\2\2yz\7\36\2\2z{\5\b\5\2{|\7\37\2\2|\u0090"+
		"\3\2\2\2}~\7\f\2\2~\177\7\32\2\2\177\u0080\5\22\n\2\u0080\u0081\7 \2\2"+
		"\u0081\u0082\5\20\t\2\u0082\u0083\7 \2\2\u0083\u0084\5\24\13\2\u0084\u0088"+
		"\7\33\2\2\u0085\u0087\7;\2\2\u0086\u0085\3\2\2\2\u0087\u008a\3\2\2\2\u0088"+
		"\u0086\3\2\2\2\u0088\u0089\3\2\2\2\u0089\u008b\3\2\2\2\u008a\u0088\3\2"+
		"\2\2\u008b\u008c\7\36\2\2\u008c\u008d\5\b\5\2\u008d\u008e\7\37\2\2\u008e"+
		"\u0090\3\2\2\2\u008fo\3\2\2\2\u008f}\3\2\2\2\u0090\17\3\2\2\2\u0091\u0093"+
		"\5,\27\2\u0092\u0091\3\2\2\2\u0092\u0093\3\2\2\2\u0093\21\3\2\2\2\u0094"+
		"\u0098\5\36\20\2\u0095\u0098\5\30\r\2\u0096\u0098\3\2\2\2\u0097\u0094"+
		"\3\2\2\2\u0097\u0095\3\2\2\2\u0097\u0096\3\2\2\2\u0098\23\3\2\2\2\u0099"+
		"\u009c\5\30\r\2\u009a\u009c\3\2\2\2\u009b\u0099\3\2\2\2\u009b\u009a\3"+
		"\2\2\2\u009c\25\3\2\2\2\u009d\u009e\7\16\2\2\u009e\u009f\7\32\2\2\u009f"+
		"\u00a0\5,\27\2\u00a0\u00a4\7\33\2\2\u00a1\u00a3\7;\2\2\u00a2\u00a1\3\2"+
		"\2\2\u00a3\u00a6\3\2\2\2\u00a4\u00a2\3\2\2\2\u00a4\u00a5\3\2\2\2\u00a5"+
		"\u00a7\3\2\2\2\u00a6\u00a4\3\2\2\2\u00a7\u00b6\5\f\7\2\u00a8\u00aa\7;"+
		"\2\2\u00a9\u00a8\3\2\2\2\u00aa\u00ad\3\2\2\2\u00ab\u00a9\3\2\2\2\u00ab"+
		"\u00ac\3\2\2\2\u00ac\u00ae\3\2\2\2\u00ad\u00ab\3\2\2\2\u00ae\u00b2\7\17"+
		"\2\2\u00af\u00b1\7;\2\2\u00b0\u00af\3\2\2\2\u00b1\u00b4\3\2\2\2\u00b2"+
		"\u00b0\3\2\2\2\u00b2\u00b3\3\2\2\2\u00b3\u00b5\3\2\2\2\u00b4\u00b2\3\2"+
		"\2\2\u00b5\u00b7\5\f\7\2\u00b6\u00ab\3\2\2\2\u00b6\u00b7\3\2\2\2\u00b7"+
		"\27\3\2\2\2\u00b8\u00bc\7\65\2\2\u00b9\u00bb\5\60\31\2\u00ba\u00b9\3\2"+
		"\2\2\u00bb\u00be\3\2\2\2\u00bc\u00ba\3\2\2\2\u00bc\u00bd\3\2\2\2\u00bd"+
		"\u00bf\3\2\2\2\u00be\u00bc\3\2\2\2\u00bf\u00c0\t\2\2\2\u00c0\u00c1\5,"+
		"\27\2\u00c1\31\3\2\2\2\u00c2\u00c3\7\36\2\2\u00c3\u00c4\5\b\5\2\u00c4"+
		"\u00c5\7\37\2\2\u00c5\33\3\2\2\2\u00c6\u00c8\5\62\32\2\u00c7\u00c9\5\60"+
		"\31\2\u00c8\u00c7\3\2\2\2\u00c9\u00ca\3\2\2\2\u00ca\u00c8\3\2\2\2\u00ca"+
		"\u00cb\3\2\2\2\u00cb\u00d6\3\2\2\2\u00cc\u00cf\5\62\32\2\u00cd\u00ce\7"+
		"\34\2\2\u00ce\u00d0\7\35\2\2\u00cf\u00cd\3\2\2\2\u00d0\u00d1\3\2\2\2\u00d1"+
		"\u00cf\3\2\2\2\u00d1\u00d2\3\2\2\2\u00d2\u00d6\3\2\2\2\u00d3\u00d6\5\62"+
		"\32\2\u00d4\u00d6\7\t\2\2\u00d5\u00c6\3\2\2\2\u00d5\u00cc\3\2\2\2\u00d5"+
		"\u00d3\3\2\2\2\u00d5\u00d4\3\2\2\2\u00d6\35\3\2\2\2\u00d7\u00d8\5\34\17"+
		"\2\u00d8\u00db\7\65\2\2\u00d9\u00da\7\'\2\2\u00da\u00dc\5,\27\2\u00db"+
		"\u00d9\3\2\2\2\u00db\u00dc\3\2\2\2\u00dc\37\3\2\2\2\u00dd\u00de\7\26\2"+
		"\2\u00de\u00df\5\34\17\2\u00df\u00e0\7\65\2\2\u00e0\u00e1\7\32\2\2\u00e1"+
		"\u00e2\5&\24\2\u00e2\u00e3\7\33\2\2\u00e3!\3\2\2\2\u00e4\u00e5\7\25\2"+
		"\2\u00e5\u00e6\5\34\17\2\u00e6\u00e7\7\65\2\2\u00e7\u00e8\7\32\2\2\u00e8"+
		"\u00e9\5&\24\2\u00e9\u00ea\7\33\2\2\u00ea#\3\2\2\2\u00eb\u00ec\7\24\2"+
		"\2\u00ec\u00ed\5\34\17\2\u00ed\u00ee\7\65\2\2\u00ee\u00ef\7\32\2\2\u00ef"+
		"\u00f0\5&\24\2\u00f0\u00f4\7\33\2\2\u00f1\u00f3\7;\2\2\u00f2\u00f1\3\2"+
		"\2\2\u00f3\u00f6\3\2\2\2\u00f4\u00f2\3\2\2\2\u00f4\u00f5\3\2\2\2\u00f5"+
		"\u00f7\3\2\2\2\u00f6\u00f4\3\2\2\2\u00f7\u00f8\5*\26\2\u00f8%\3\2\2\2"+
		"\u00f9\u00fe\5(\25\2\u00fa\u00fb\7\3\2\2\u00fb\u00fd\5(\25\2\u00fc\u00fa"+
		"\3\2\2\2\u00fd\u0100\3\2\2\2\u00fe\u00fc\3\2\2\2\u00fe\u00ff\3\2\2\2\u00ff"+
		"\u0103\3\2\2\2\u0100\u00fe\3\2\2\2\u0101\u0103\3\2\2\2\u0102\u00f9\3\2"+
		"\2\2\u0102\u0101\3\2\2\2\u0103\'\3\2\2\2\u0104\u0105\5\34\17\2\u0105\u0106"+
		"\7\65\2\2\u0106)\3\2\2\2\u0107\u0108\7\36\2\2\u0108\u0109\5\b\5\2\u0109"+
		"\u010a\7\37\2\2\u010a+\3\2\2\2\u010b\u010c\b\27\1\2\u010c\u010d\t\3\2"+
		"\2\u010d\u0129\5,\27\21\u010e\u010f\7\32\2\2\u010f\u0110\5,\27\2\u0110"+
		"\u0111\7\33\2\2\u0111\u0129\3\2\2\2\u0112\u0129\7\66\2\2\u0113\u0117\7"+
		"\65\2\2\u0114\u0116\5\60\31\2\u0115\u0114\3\2\2\2\u0116\u0119\3\2\2\2"+
		"\u0117\u0115\3\2\2\2\u0117\u0118\3\2\2\2\u0118\u0129\3\2\2\2\u0119\u0117"+
		"\3\2\2\2\u011a\u011b\7\65\2\2\u011b\u011c\7\32\2\2\u011c\u011d\5.\30\2"+
		"\u011d\u011e\7\33\2\2\u011e\u0129\3\2\2\2\u011f\u0129\78\2\2\u0120\u0129"+
		"\7\67\2\2\u0121\u0129\79\2\2\u0122\u0129\7:\2\2\u0123\u0125\5\60\31\2"+
		"\u0124\u0123\3\2\2\2\u0125\u0126\3\2\2\2\u0126\u0124\3\2\2\2\u0126\u0127"+
		"\3\2\2\2\u0127\u0129\3\2\2\2\u0128\u010b\3\2\2\2\u0128\u010e\3\2\2\2\u0128"+
		"\u0112\3\2\2\2\u0128\u0113\3\2\2\2\u0128\u011a\3\2\2\2\u0128\u011f\3\2"+
		"\2\2\u0128\u0120\3\2\2\2\u0128\u0121\3\2\2\2\u0128\u0122\3\2\2\2\u0128"+
		"\u0124\3\2\2\2\u0129\u013b\3\2\2\2\u012a\u012b\f\20\2\2\u012b\u012c\t"+
		"\4\2\2\u012c\u013a\5,\27\21\u012d\u012e\f\17\2\2\u012e\u012f\t\5\2\2\u012f"+
		"\u013a\5,\27\20\u0130\u0131\f\16\2\2\u0131\u0132\t\6\2\2\u0132\u013a\5"+
		",\27\17\u0133\u0134\f\r\2\2\u0134\u0135\7+\2\2\u0135\u013a\5,\27\16\u0136"+
		"\u0137\f\f\2\2\u0137\u0138\7,\2\2\u0138\u013a\5,\27\r\u0139\u012a\3\2"+
		"\2\2\u0139\u012d\3\2\2\2\u0139\u0130\3\2\2\2\u0139\u0133\3\2\2\2\u0139"+
		"\u0136\3\2\2\2\u013a\u013d\3\2\2\2\u013b\u0139\3\2\2\2\u013b\u013c\3\2"+
		"\2\2\u013c-\3\2\2\2\u013d\u013b\3\2\2\2\u013e\u0143\5,\27\2\u013f\u0140"+
		"\7\3\2\2\u0140\u0142\5,\27\2\u0141\u013f\3\2\2\2\u0142\u0145\3\2\2\2\u0143"+
		"\u0141\3\2\2\2\u0143\u0144\3\2\2\2\u0144\u0147\3\2\2\2\u0145\u0143\3\2"+
		"\2\2\u0146\u013e\3\2\2\2\u0146\u0147\3\2\2\2\u0147/\3\2\2\2\u0148\u0149"+
		"\7\34\2\2\u0149\u014a\5,\27\2\u014a\u014b\7\35\2\2\u014b\61\3\2\2\2\u014c"+
		"\u014d\t\7\2\2\u014d\63\3\2\2\2\">@IKYemv\u0088\u008f\u0092\u0097\u009b"+
		"\u00a4\u00ab\u00b2\u00b6\u00bc\u00ca\u00d1\u00d5\u00db\u00f4\u00fe\u0102"+
		"\u0117\u0126\u0128\u0139\u013b\u0143\u0146";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}