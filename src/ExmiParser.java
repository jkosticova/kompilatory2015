// Generated from /home/brando/School/git/Kompilatory/Exmi.g4 by ANTLR 4.5.1
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class ExmiParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.5.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, ID=15, COMMENT=16, INT=17, 
		BOOL=18, CHAR=19, STRING=20, MUL=21, DIV=22, ADD=23, SUB=24, EXP=25, OR=26, 
		AND=27, EQ=28, WS=29, LPAR=30, RPAR=31, LARR=32, RARR=33, LBLOCK=34, RBLOCK=35, 
		END_ST=36;
	public static final int
		RULE_init = 0, RULE_object = 1, RULE_arg_one = 2, RULE_arglist = 3, RULE_func_decl = 4, 
		RULE_func_def = 5, RULE_code = 6, RULE_if_block = 7, RULE_while_block = 8, 
		RULE_assigment = 9, RULE_declaration_global = 10, RULE_declaration = 11, 
		RULE_expression = 12, RULE_comment = 13;
	public static final String[] ruleNames = {
		"init", "object", "arg_one", "arglist", "func_decl", "func_def", "code", 
		"if_block", "while_block", "assigment", "declaration_global", "declaration", 
		"expression", "comment"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "','", "'return'", "'if'", "'else'", "'while'", "'='", "'%'", "'!='", 
		"'<'", "'>'", "'<='", "'>='", "'&&'", "'||'", null, null, null, null, 
		null, null, "'*'", "'/'", "'+'", "'-'", "'^'", null, null, "'=='", null, 
		"'('", "')'", "'['", "']'", "'{'", "'}'", "';'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, "ID", "COMMENT", "INT", "BOOL", "CHAR", "STRING", "MUL", 
		"DIV", "ADD", "SUB", "EXP", "OR", "AND", "EQ", "WS", "LPAR", "RPAR", "LARR", 
		"RARR", "LBLOCK", "RBLOCK", "END_ST"
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
	public String getGrammarFileName() { return "Exmi.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public ExmiParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class InitContext extends ParserRuleContext {
		public List<CommentContext> comment() {
			return getRuleContexts(CommentContext.class);
		}
		public CommentContext comment(int i) {
			return getRuleContext(CommentContext.class,i);
		}
		public List<Func_defContext> func_def() {
			return getRuleContexts(Func_defContext.class);
		}
		public Func_defContext func_def(int i) {
			return getRuleContext(Func_defContext.class,i);
		}
		public List<Func_declContext> func_decl() {
			return getRuleContexts(Func_declContext.class);
		}
		public Func_declContext func_decl(int i) {
			return getRuleContext(Func_declContext.class,i);
		}
		public List<TerminalNode> END_ST() { return getTokens(ExmiParser.END_ST); }
		public TerminalNode END_ST(int i) {
			return getToken(ExmiParser.END_ST, i);
		}
		public List<Declaration_globalContext> declaration_global() {
			return getRuleContexts(Declaration_globalContext.class);
		}
		public Declaration_globalContext declaration_global(int i) {
			return getRuleContext(Declaration_globalContext.class,i);
		}
		public InitContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_init; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExmiVisitor ) return ((ExmiVisitor<? extends T>)visitor).visitInit(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InitContext init() throws RecognitionException {
		InitContext _localctx = new InitContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_init);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(38);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ID || _la==COMMENT) {
				{
				setState(36);
				switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
				case 1:
					{
					setState(28);
					comment();
					}
					break;
				case 2:
					{
					{
					setState(29);
					func_decl();
					setState(30);
					match(END_ST);
					}
					}
					break;
				case 3:
					{
					{
					setState(32);
					declaration_global();
					setState(33);
					match(END_ST);
					}
					}
					break;
				case 4:
					{
					setState(35);
					func_def();
					}
					break;
				}
				}
				setState(40);
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

	public static class ObjectContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(ExmiParser.ID, 0); }
		public List<TerminalNode> LARR() { return getTokens(ExmiParser.LARR); }
		public TerminalNode LARR(int i) {
			return getToken(ExmiParser.LARR, i);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<TerminalNode> RARR() { return getTokens(ExmiParser.RARR); }
		public TerminalNode RARR(int i) {
			return getToken(ExmiParser.RARR, i);
		}
		public ObjectContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_object; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExmiVisitor ) return ((ExmiVisitor<? extends T>)visitor).visitObject(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ObjectContext object() throws RecognitionException {
		ObjectContext _localctx = new ObjectContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_object);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(41);
			match(ID);
			setState(48);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(42);
					match(LARR);
					setState(43);
					expression(0);
					setState(44);
					match(RARR);
					}
					} 
				}
				setState(50);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
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

	public static class Arg_oneContext extends ParserRuleContext {
		public List<TerminalNode> ID() { return getTokens(ExmiParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(ExmiParser.ID, i);
		}
		public List<TerminalNode> LARR() { return getTokens(ExmiParser.LARR); }
		public TerminalNode LARR(int i) {
			return getToken(ExmiParser.LARR, i);
		}
		public List<TerminalNode> RARR() { return getTokens(ExmiParser.RARR); }
		public TerminalNode RARR(int i) {
			return getToken(ExmiParser.RARR, i);
		}
		public Arg_oneContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arg_one; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExmiVisitor ) return ((ExmiVisitor<? extends T>)visitor).visitArg_one(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Arg_oneContext arg_one() throws RecognitionException {
		Arg_oneContext _localctx = new Arg_oneContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_arg_one);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(51);
			match(ID);
			setState(52);
			match(ID);
			setState(57);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==LARR) {
				{
				{
				setState(53);
				match(LARR);
				setState(54);
				match(RARR);
				}
				}
				setState(59);
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

	public static class ArglistContext extends ParserRuleContext {
		public List<Arg_oneContext> arg_one() {
			return getRuleContexts(Arg_oneContext.class);
		}
		public Arg_oneContext arg_one(int i) {
			return getRuleContext(Arg_oneContext.class,i);
		}
		public ArglistContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arglist; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExmiVisitor ) return ((ExmiVisitor<? extends T>)visitor).visitArglist(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArglistContext arglist() throws RecognitionException {
		ArglistContext _localctx = new ArglistContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_arglist);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(68);
			_la = _input.LA(1);
			if (_la==ID) {
				{
				setState(60);
				arg_one();
				setState(65);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__0) {
					{
					{
					setState(61);
					match(T__0);
					setState(62);
					arg_one();
					}
					}
					setState(67);
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

	public static class Func_declContext extends ParserRuleContext {
		public List<TerminalNode> ID() { return getTokens(ExmiParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(ExmiParser.ID, i);
		}
		public TerminalNode LPAR() { return getToken(ExmiParser.LPAR, 0); }
		public ArglistContext arglist() {
			return getRuleContext(ArglistContext.class,0);
		}
		public TerminalNode RPAR() { return getToken(ExmiParser.RPAR, 0); }
		public Func_declContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_func_decl; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExmiVisitor ) return ((ExmiVisitor<? extends T>)visitor).visitFunc_decl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Func_declContext func_decl() throws RecognitionException {
		Func_declContext _localctx = new Func_declContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_func_decl);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(70);
			match(ID);
			setState(71);
			match(ID);
			setState(72);
			match(LPAR);
			setState(73);
			arglist();
			setState(74);
			match(RPAR);
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

	public static class Func_defContext extends ParserRuleContext {
		public List<TerminalNode> ID() { return getTokens(ExmiParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(ExmiParser.ID, i);
		}
		public TerminalNode LPAR() { return getToken(ExmiParser.LPAR, 0); }
		public ArglistContext arglist() {
			return getRuleContext(ArglistContext.class,0);
		}
		public TerminalNode RPAR() { return getToken(ExmiParser.RPAR, 0); }
		public TerminalNode LBLOCK() { return getToken(ExmiParser.LBLOCK, 0); }
		public CodeContext code() {
			return getRuleContext(CodeContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode END_ST() { return getToken(ExmiParser.END_ST, 0); }
		public TerminalNode RBLOCK() { return getToken(ExmiParser.RBLOCK, 0); }
		public Func_defContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_func_def; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExmiVisitor ) return ((ExmiVisitor<? extends T>)visitor).visitFunc_def(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Func_defContext func_def() throws RecognitionException {
		Func_defContext _localctx = new Func_defContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_func_def);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(76);
			match(ID);
			setState(77);
			match(ID);
			setState(78);
			match(LPAR);
			setState(79);
			arglist();
			setState(80);
			match(RPAR);
			setState(81);
			match(LBLOCK);
			setState(82);
			code();
			setState(83);
			match(T__1);
			setState(84);
			expression(0);
			setState(85);
			match(END_ST);
			setState(86);
			match(RBLOCK);
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

	public static class CodeContext extends ParserRuleContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<TerminalNode> END_ST() { return getTokens(ExmiParser.END_ST); }
		public TerminalNode END_ST(int i) {
			return getToken(ExmiParser.END_ST, i);
		}
		public List<AssigmentContext> assigment() {
			return getRuleContexts(AssigmentContext.class);
		}
		public AssigmentContext assigment(int i) {
			return getRuleContext(AssigmentContext.class,i);
		}
		public List<DeclarationContext> declaration() {
			return getRuleContexts(DeclarationContext.class);
		}
		public DeclarationContext declaration(int i) {
			return getRuleContext(DeclarationContext.class,i);
		}
		public List<If_blockContext> if_block() {
			return getRuleContexts(If_blockContext.class);
		}
		public If_blockContext if_block(int i) {
			return getRuleContext(If_blockContext.class,i);
		}
		public List<While_blockContext> while_block() {
			return getRuleContexts(While_blockContext.class);
		}
		public While_blockContext while_block(int i) {
			return getRuleContext(While_blockContext.class,i);
		}
		public List<CommentContext> comment() {
			return getRuleContexts(CommentContext.class);
		}
		public CommentContext comment(int i) {
			return getRuleContext(CommentContext.class,i);
		}
		public CodeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_code; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExmiVisitor ) return ((ExmiVisitor<? extends T>)visitor).visitCode(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CodeContext code() throws RecognitionException {
		CodeContext _localctx = new CodeContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_code);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(102);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__4) | (1L << ID) | (1L << COMMENT) | (1L << INT) | (1L << BOOL) | (1L << CHAR) | (1L << STRING) | (1L << ADD) | (1L << SUB) | (1L << LPAR) | (1L << END_ST))) != 0)) {
				{
				setState(100);
				switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
				case 1:
					{
					setState(88);
					expression(0);
					setState(89);
					match(END_ST);
					}
					break;
				case 2:
					{
					setState(91);
					assigment();
					setState(92);
					match(END_ST);
					}
					break;
				case 3:
					{
					setState(94);
					declaration();
					setState(95);
					match(END_ST);
					}
					break;
				case 4:
					{
					setState(97);
					if_block();
					}
					break;
				case 5:
					{
					setState(98);
					while_block();
					}
					break;
				case 6:
					{
					setState(99);
					comment();
					}
					break;
				}
				}
				setState(104);
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

	public static class If_blockContext extends ParserRuleContext {
		public TerminalNode LPAR() { return getToken(ExmiParser.LPAR, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RPAR() { return getToken(ExmiParser.RPAR, 0); }
		public List<TerminalNode> LBLOCK() { return getTokens(ExmiParser.LBLOCK); }
		public TerminalNode LBLOCK(int i) {
			return getToken(ExmiParser.LBLOCK, i);
		}
		public List<CodeContext> code() {
			return getRuleContexts(CodeContext.class);
		}
		public CodeContext code(int i) {
			return getRuleContext(CodeContext.class,i);
		}
		public List<TerminalNode> RBLOCK() { return getTokens(ExmiParser.RBLOCK); }
		public TerminalNode RBLOCK(int i) {
			return getToken(ExmiParser.RBLOCK, i);
		}
		public If_blockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_if_block; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExmiVisitor ) return ((ExmiVisitor<? extends T>)visitor).visitIf_block(this);
			else return visitor.visitChildren(this);
		}
	}

	public final If_blockContext if_block() throws RecognitionException {
		If_blockContext _localctx = new If_blockContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_if_block);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(105);
			match(T__2);
			setState(106);
			match(LPAR);
			setState(107);
			expression(0);
			setState(108);
			match(RPAR);
			setState(109);
			match(LBLOCK);
			setState(110);
			code();
			setState(111);
			match(RBLOCK);
			setState(117);
			_la = _input.LA(1);
			if (_la==T__3) {
				{
				setState(112);
				match(T__3);
				setState(113);
				match(LBLOCK);
				setState(114);
				code();
				setState(115);
				match(RBLOCK);
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

	public static class While_blockContext extends ParserRuleContext {
		public TerminalNode LPAR() { return getToken(ExmiParser.LPAR, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RPAR() { return getToken(ExmiParser.RPAR, 0); }
		public TerminalNode LBLOCK() { return getToken(ExmiParser.LBLOCK, 0); }
		public CodeContext code() {
			return getRuleContext(CodeContext.class,0);
		}
		public TerminalNode RBLOCK() { return getToken(ExmiParser.RBLOCK, 0); }
		public While_blockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_while_block; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExmiVisitor ) return ((ExmiVisitor<? extends T>)visitor).visitWhile_block(this);
			else return visitor.visitChildren(this);
		}
	}

	public final While_blockContext while_block() throws RecognitionException {
		While_blockContext _localctx = new While_blockContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_while_block);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(119);
			match(T__4);
			setState(120);
			match(LPAR);
			setState(121);
			expression(0);
			setState(122);
			match(RPAR);
			setState(123);
			match(LBLOCK);
			setState(124);
			code();
			setState(125);
			match(RBLOCK);
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

	public static class AssigmentContext extends ParserRuleContext {
		public ObjectContext object() {
			return getRuleContext(ObjectContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public AssigmentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assigment; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExmiVisitor ) return ((ExmiVisitor<? extends T>)visitor).visitAssigment(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AssigmentContext assigment() throws RecognitionException {
		AssigmentContext _localctx = new AssigmentContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_assigment);
		try {
			setState(132);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(127);
				object();
				setState(128);
				match(T__5);
				setState(129);
				expression(0);
				}
				break;
			case END_ST:
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

	public static class Declaration_globalContext extends ParserRuleContext {
		public List<TerminalNode> ID() { return getTokens(ExmiParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(ExmiParser.ID, i);
		}
		public Declaration_globalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declaration_global; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExmiVisitor ) return ((ExmiVisitor<? extends T>)visitor).visitDeclaration_global(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Declaration_globalContext declaration_global() throws RecognitionException {
		Declaration_globalContext _localctx = new Declaration_globalContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_declaration_global);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(134);
			match(ID);
			setState(135);
			match(ID);
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
		public List<TerminalNode> ID() { return getTokens(ExmiParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(ExmiParser.ID, i);
		}
		public List<TerminalNode> LARR() { return getTokens(ExmiParser.LARR); }
		public TerminalNode LARR(int i) {
			return getToken(ExmiParser.LARR, i);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<TerminalNode> RARR() { return getTokens(ExmiParser.RARR); }
		public TerminalNode RARR(int i) {
			return getToken(ExmiParser.RARR, i);
		}
		public DeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declaration; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExmiVisitor ) return ((ExmiVisitor<? extends T>)visitor).visitDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DeclarationContext declaration() throws RecognitionException {
		DeclarationContext _localctx = new DeclarationContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_declaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(137);
			match(ID);
			setState(138);
			match(ID);
			setState(145);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==LARR) {
				{
				{
				setState(139);
				match(LARR);
				setState(140);
				expression(0);
				setState(141);
				match(RARR);
				}
				}
				setState(147);
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
	public static class ParContext extends ExpressionContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ParContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExmiVisitor ) return ((ExmiVisitor<? extends T>)visitor).visitPar(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class AddContext extends ExpressionContext {
		public Token op;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public AddContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExmiVisitor ) return ((ExmiVisitor<? extends T>)visitor).visitAdd(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ModContext extends ExpressionContext {
		public Token op;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public ModContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExmiVisitor ) return ((ExmiVisitor<? extends T>)visitor).visitMod(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class OrContext extends ExpressionContext {
		public Token op;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public OrContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExmiVisitor ) return ((ExmiVisitor<? extends T>)visitor).visitOr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class MulContext extends ExpressionContext {
		public Token op;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public MulContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExmiVisitor ) return ((ExmiVisitor<? extends T>)visitor).visitMul(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class VarContext extends ExpressionContext {
		public ObjectContext object() {
			return getRuleContext(ObjectContext.class,0);
		}
		public VarContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExmiVisitor ) return ((ExmiVisitor<? extends T>)visitor).visitVar(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class CmpContext extends ExpressionContext {
		public Token op;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public CmpContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExmiVisitor ) return ((ExmiVisitor<? extends T>)visitor).visitCmp(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class UnaContext extends ExpressionContext {
		public Token op;
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public UnaContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExmiVisitor ) return ((ExmiVisitor<? extends T>)visitor).visitUna(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class StringContext extends ExpressionContext {
		public TerminalNode STRING() { return getToken(ExmiParser.STRING, 0); }
		public StringContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExmiVisitor ) return ((ExmiVisitor<? extends T>)visitor).visitString(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IntContext extends ExpressionContext {
		public TerminalNode INT() { return getToken(ExmiParser.INT, 0); }
		public IntContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExmiVisitor ) return ((ExmiVisitor<? extends T>)visitor).visitInt(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BoolContext extends ExpressionContext {
		public TerminalNode BOOL() { return getToken(ExmiParser.BOOL, 0); }
		public BoolContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExmiVisitor ) return ((ExmiVisitor<? extends T>)visitor).visitBool(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class CharContext extends ExpressionContext {
		public TerminalNode CHAR() { return getToken(ExmiParser.CHAR, 0); }
		public CharContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExmiVisitor ) return ((ExmiVisitor<? extends T>)visitor).visitChar(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class AndContext extends ExpressionContext {
		public Token op;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public AndContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExmiVisitor ) return ((ExmiVisitor<? extends T>)visitor).visitAnd(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class FunctionContext extends ExpressionContext {
		public TerminalNode ID() { return getToken(ExmiParser.ID, 0); }
		public TerminalNode LPAR() { return getToken(ExmiParser.LPAR, 0); }
		public TerminalNode RPAR() { return getToken(ExmiParser.RPAR, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public FunctionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExmiVisitor ) return ((ExmiVisitor<? extends T>)visitor).visitFunction(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ExpContext extends ExpressionContext {
		public Token op;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public ExpContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExmiVisitor ) return ((ExmiVisitor<? extends T>)visitor).visitExp(this);
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
		int _startState = 24;
		enterRecursionRule(_localctx, 24, RULE_expression, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(173);
			switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
			case 1:
				{
				_localctx = new UnaContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(149);
				((UnaContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==ADD || _la==SUB) ) {
					((UnaContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(150);
				expression(15);
				}
				break;
			case 2:
				{
				_localctx = new FunctionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(151);
				match(ID);
				setState(152);
				match(LPAR);
				setState(161);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ID) | (1L << INT) | (1L << BOOL) | (1L << CHAR) | (1L << STRING) | (1L << ADD) | (1L << SUB) | (1L << LPAR))) != 0)) {
					{
					setState(153);
					expression(0);
					setState(158);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==T__0) {
						{
						{
						setState(154);
						match(T__0);
						setState(155);
						expression(0);
						}
						}
						setState(160);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(163);
				match(RPAR);
				}
				break;
			case 3:
				{
				_localctx = new ParContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(164);
				match(LPAR);
				setState(165);
				expression(0);
				setState(166);
				match(RPAR);
				}
				break;
			case 4:
				{
				_localctx = new VarContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(168);
				object();
				}
				break;
			case 5:
				{
				_localctx = new StringContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(169);
				match(STRING);
				}
				break;
			case 6:
				{
				_localctx = new IntContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(170);
				match(INT);
				}
				break;
			case 7:
				{
				_localctx = new BoolContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(171);
				match(BOOL);
				}
				break;
			case 8:
				{
				_localctx = new CharContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(172);
				match(CHAR);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(198);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,15,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(196);
					switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
					case 1:
						{
						_localctx = new ExpContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(175);
						if (!(precpred(_ctx, 13))) throw new FailedPredicateException(this, "precpred(_ctx, 13)");
						setState(176);
						((ExpContext)_localctx).op = match(EXP);
						setState(177);
						expression(13);
						}
						break;
					case 2:
						{
						_localctx = new MulContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(178);
						if (!(precpred(_ctx, 12))) throw new FailedPredicateException(this, "precpred(_ctx, 12)");
						setState(179);
						((MulContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==MUL || _la==DIV) ) {
							((MulContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						} else {
							consume();
						}
						setState(180);
						expression(13);
						}
						break;
					case 3:
						{
						_localctx = new AddContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(181);
						if (!(precpred(_ctx, 11))) throw new FailedPredicateException(this, "precpred(_ctx, 11)");
						setState(182);
						((AddContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==ADD || _la==SUB) ) {
							((AddContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						} else {
							consume();
						}
						setState(183);
						expression(12);
						}
						break;
					case 4:
						{
						_localctx = new ModContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(184);
						if (!(precpred(_ctx, 10))) throw new FailedPredicateException(this, "precpred(_ctx, 10)");
						setState(185);
						((ModContext)_localctx).op = match(T__6);
						setState(186);
						expression(11);
						}
						break;
					case 5:
						{
						_localctx = new CmpContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(187);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(188);
						((CmpContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__7) | (1L << T__8) | (1L << T__9) | (1L << T__10) | (1L << T__11) | (1L << EQ))) != 0)) ) {
							((CmpContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						} else {
							consume();
						}
						setState(189);
						expression(10);
						}
						break;
					case 6:
						{
						_localctx = new AndContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(190);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(191);
						((AndContext)_localctx).op = match(T__12);
						setState(192);
						expression(9);
						}
						break;
					case 7:
						{
						_localctx = new OrContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(193);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(194);
						((OrContext)_localctx).op = match(T__13);
						setState(195);
						expression(8);
						}
						break;
					}
					} 
				}
				setState(200);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,15,_ctx);
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

	public static class CommentContext extends ParserRuleContext {
		public TerminalNode COMMENT() { return getToken(ExmiParser.COMMENT, 0); }
		public CommentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comment; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExmiVisitor ) return ((ExmiVisitor<? extends T>)visitor).visitComment(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CommentContext comment() throws RecognitionException {
		CommentContext _localctx = new CommentContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_comment);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(201);
			match(COMMENT);
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
		case 12:
			return expression_sempred((ExpressionContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expression_sempred(ExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 13);
		case 1:
			return precpred(_ctx, 12);
		case 2:
			return precpred(_ctx, 11);
		case 3:
			return precpred(_ctx, 10);
		case 4:
			return precpred(_ctx, 9);
		case 5:
			return precpred(_ctx, 8);
		case 6:
			return precpred(_ctx, 7);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3&\u00ce\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3"+
		"\2\7\2\'\n\2\f\2\16\2*\13\2\3\3\3\3\3\3\3\3\3\3\7\3\61\n\3\f\3\16\3\64"+
		"\13\3\3\4\3\4\3\4\3\4\7\4:\n\4\f\4\16\4=\13\4\3\5\3\5\3\5\7\5B\n\5\f\5"+
		"\16\5E\13\5\5\5G\n\5\3\6\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3"+
		"\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b"+
		"\7\bg\n\b\f\b\16\bj\13\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3"+
		"\t\5\tx\n\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\5"+
		"\13\u0087\n\13\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\7\r\u0092\n\r\f\r\16"+
		"\r\u0095\13\r\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\7\16\u009f\n\16"+
		"\f\16\16\16\u00a2\13\16\5\16\u00a4\n\16\3\16\3\16\3\16\3\16\3\16\3\16"+
		"\3\16\3\16\3\16\3\16\5\16\u00b0\n\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16"+
		"\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16"+
		"\7\16\u00c7\n\16\f\16\16\16\u00ca\13\16\3\17\3\17\3\17\2\3\32\20\2\4\6"+
		"\b\n\f\16\20\22\24\26\30\32\34\2\5\3\2\31\32\3\2\27\30\4\2\n\16\36\36"+
		"\u00e0\2(\3\2\2\2\4+\3\2\2\2\6\65\3\2\2\2\bF\3\2\2\2\nH\3\2\2\2\fN\3\2"+
		"\2\2\16h\3\2\2\2\20k\3\2\2\2\22y\3\2\2\2\24\u0086\3\2\2\2\26\u0088\3\2"+
		"\2\2\30\u008b\3\2\2\2\32\u00af\3\2\2\2\34\u00cb\3\2\2\2\36\'\5\34\17\2"+
		"\37 \5\n\6\2 !\7&\2\2!\'\3\2\2\2\"#\5\26\f\2#$\7&\2\2$\'\3\2\2\2%\'\5"+
		"\f\7\2&\36\3\2\2\2&\37\3\2\2\2&\"\3\2\2\2&%\3\2\2\2\'*\3\2\2\2(&\3\2\2"+
		"\2()\3\2\2\2)\3\3\2\2\2*(\3\2\2\2+\62\7\21\2\2,-\7\"\2\2-.\5\32\16\2."+
		"/\7#\2\2/\61\3\2\2\2\60,\3\2\2\2\61\64\3\2\2\2\62\60\3\2\2\2\62\63\3\2"+
		"\2\2\63\5\3\2\2\2\64\62\3\2\2\2\65\66\7\21\2\2\66;\7\21\2\2\678\7\"\2"+
		"\28:\7#\2\29\67\3\2\2\2:=\3\2\2\2;9\3\2\2\2;<\3\2\2\2<\7\3\2\2\2=;\3\2"+
		"\2\2>C\5\6\4\2?@\7\3\2\2@B\5\6\4\2A?\3\2\2\2BE\3\2\2\2CA\3\2\2\2CD\3\2"+
		"\2\2DG\3\2\2\2EC\3\2\2\2F>\3\2\2\2FG\3\2\2\2G\t\3\2\2\2HI\7\21\2\2IJ\7"+
		"\21\2\2JK\7 \2\2KL\5\b\5\2LM\7!\2\2M\13\3\2\2\2NO\7\21\2\2OP\7\21\2\2"+
		"PQ\7 \2\2QR\5\b\5\2RS\7!\2\2ST\7$\2\2TU\5\16\b\2UV\7\4\2\2VW\5\32\16\2"+
		"WX\7&\2\2XY\7%\2\2Y\r\3\2\2\2Z[\5\32\16\2[\\\7&\2\2\\g\3\2\2\2]^\5\24"+
		"\13\2^_\7&\2\2_g\3\2\2\2`a\5\30\r\2ab\7&\2\2bg\3\2\2\2cg\5\20\t\2dg\5"+
		"\22\n\2eg\5\34\17\2fZ\3\2\2\2f]\3\2\2\2f`\3\2\2\2fc\3\2\2\2fd\3\2\2\2"+
		"fe\3\2\2\2gj\3\2\2\2hf\3\2\2\2hi\3\2\2\2i\17\3\2\2\2jh\3\2\2\2kl\7\5\2"+
		"\2lm\7 \2\2mn\5\32\16\2no\7!\2\2op\7$\2\2pq\5\16\b\2qw\7%\2\2rs\7\6\2"+
		"\2st\7$\2\2tu\5\16\b\2uv\7%\2\2vx\3\2\2\2wr\3\2\2\2wx\3\2\2\2x\21\3\2"+
		"\2\2yz\7\7\2\2z{\7 \2\2{|\5\32\16\2|}\7!\2\2}~\7$\2\2~\177\5\16\b\2\177"+
		"\u0080\7%\2\2\u0080\23\3\2\2\2\u0081\u0082\5\4\3\2\u0082\u0083\7\b\2\2"+
		"\u0083\u0084\5\32\16\2\u0084\u0087\3\2\2\2\u0085\u0087\3\2\2\2\u0086\u0081"+
		"\3\2\2\2\u0086\u0085\3\2\2\2\u0087\25\3\2\2\2\u0088\u0089\7\21\2\2\u0089"+
		"\u008a\7\21\2\2\u008a\27\3\2\2\2\u008b\u008c\7\21\2\2\u008c\u0093\7\21"+
		"\2\2\u008d\u008e\7\"\2\2\u008e\u008f\5\32\16\2\u008f\u0090\7#\2\2\u0090"+
		"\u0092\3\2\2\2\u0091\u008d\3\2\2\2\u0092\u0095\3\2\2\2\u0093\u0091\3\2"+
		"\2\2\u0093\u0094\3\2\2\2\u0094\31\3\2\2\2\u0095\u0093\3\2\2\2\u0096\u0097"+
		"\b\16\1\2\u0097\u0098\t\2\2\2\u0098\u00b0\5\32\16\21\u0099\u009a\7\21"+
		"\2\2\u009a\u00a3\7 \2\2\u009b\u00a0\5\32\16\2\u009c\u009d\7\3\2\2\u009d"+
		"\u009f\5\32\16\2\u009e\u009c\3\2\2\2\u009f\u00a2\3\2\2\2\u00a0\u009e\3"+
		"\2\2\2\u00a0\u00a1\3\2\2\2\u00a1\u00a4\3\2\2\2\u00a2\u00a0\3\2\2\2\u00a3"+
		"\u009b\3\2\2\2\u00a3\u00a4\3\2\2\2\u00a4\u00a5\3\2\2\2\u00a5\u00b0\7!"+
		"\2\2\u00a6\u00a7\7 \2\2\u00a7\u00a8\5\32\16\2\u00a8\u00a9\7!\2\2\u00a9"+
		"\u00b0\3\2\2\2\u00aa\u00b0\5\4\3\2\u00ab\u00b0\7\26\2\2\u00ac\u00b0\7"+
		"\23\2\2\u00ad\u00b0\7\24\2\2\u00ae\u00b0\7\25\2\2\u00af\u0096\3\2\2\2"+
		"\u00af\u0099\3\2\2\2\u00af\u00a6\3\2\2\2\u00af\u00aa\3\2\2\2\u00af\u00ab"+
		"\3\2\2\2\u00af\u00ac\3\2\2\2\u00af\u00ad\3\2\2\2\u00af\u00ae\3\2\2\2\u00b0"+
		"\u00c8\3\2\2\2\u00b1\u00b2\f\17\2\2\u00b2\u00b3\7\33\2\2\u00b3\u00c7\5"+
		"\32\16\17\u00b4\u00b5\f\16\2\2\u00b5\u00b6\t\3\2\2\u00b6\u00c7\5\32\16"+
		"\17\u00b7\u00b8\f\r\2\2\u00b8\u00b9\t\2\2\2\u00b9\u00c7\5\32\16\16\u00ba"+
		"\u00bb\f\f\2\2\u00bb\u00bc\7\t\2\2\u00bc\u00c7\5\32\16\r\u00bd\u00be\f"+
		"\13\2\2\u00be\u00bf\t\4\2\2\u00bf\u00c7\5\32\16\f\u00c0\u00c1\f\n\2\2"+
		"\u00c1\u00c2\7\17\2\2\u00c2\u00c7\5\32\16\13\u00c3\u00c4\f\t\2\2\u00c4"+
		"\u00c5\7\20\2\2\u00c5\u00c7\5\32\16\n\u00c6\u00b1\3\2\2\2\u00c6\u00b4"+
		"\3\2\2\2\u00c6\u00b7\3\2\2\2\u00c6\u00ba\3\2\2\2\u00c6\u00bd\3\2\2\2\u00c6"+
		"\u00c0\3\2\2\2\u00c6\u00c3\3\2\2\2\u00c7\u00ca\3\2\2\2\u00c8\u00c6\3\2"+
		"\2\2\u00c8\u00c9\3\2\2\2\u00c9\33\3\2\2\2\u00ca\u00c8\3\2\2\2\u00cb\u00cc"+
		"\7\22\2\2\u00cc\35\3\2\2\2\22&(\62;CFfhw\u0086\u0093\u00a0\u00a3\u00af"+
		"\u00c6\u00c8";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}