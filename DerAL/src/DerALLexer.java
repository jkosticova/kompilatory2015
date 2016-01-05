// Generated from DerAL.g4 by ANTLR 4.5.1
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class DerALLexer extends Lexer {
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
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "Int", "Unsigned", "Char", "Double", "Bool", "Void", "String", 
		"Begin", "For", "While", "If", "Else", "Break", "Continue", "Return", 
		"Class", "Def", "Dec", "Extern", "New", "Delete", "Set", "LeftParenthesis", 
		"RightParenthesis", "LeftBracket", "RightBracket", "LeftBrace", "RightBrace", 
		"Semicolon", "Equal", "NotEqual", "PlusAssign", "MinusAssign", "StarAssign", 
		"DivAssign", "Assign", "ModAssign", "LessEqual", "GreaterEqual", "And", 
		"Or", "Not", "Less", "Greater", "Plus", "Minus", "Star", "Div", "Mod", 
		"Identifier", "IdentifierChar", "BoolConstatnt", "DoubleConstant", "IntegerConstant", 
		"Digit", "CharConstant", "CCharOrEscaped", "CharEscaped", "StringConstant", 
		"StrCharOrEscaped", "StrEscaped", "Newline", "Whitespace", "LineComment", 
		"BlockComment"
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


	public DerALLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "DerAL.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2>\u01bf\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t<\4=\t="+
		"\4>\t>\4?\t?\4@\t@\4A\tA\4B\tB\4C\tC\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\4\3"+
		"\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6"+
		"\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3"+
		"\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13"+
		"\3\13\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r"+
		"\3\16\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\20\3\20"+
		"\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3\21\3\21\3\22\3\22"+
		"\3\22\3\22\3\22\3\22\3\23\3\23\3\23\3\23\3\24\3\24\3\24\3\24\3\24\3\24"+
		"\3\24\3\24\3\24\3\25\3\25\3\25\3\25\3\25\3\25\3\26\3\26\3\26\3\26\3\26"+
		"\3\27\3\27\3\27\3\27\3\27\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\31"+
		"\3\31\3\32\3\32\3\33\3\33\3\34\3\34\3\35\3\35\3\36\3\36\3\37\3\37\3 \3"+
		" \3 \3!\3!\3!\3\"\3\"\3\"\3#\3#\3#\3$\3$\3$\3%\3%\3%\3&\3&\3\'\3\'\3\'"+
		"\3(\3(\3(\3)\3)\3)\3*\3*\3*\3+\3+\3+\3,\3,\3-\3-\3.\3.\3/\3/\3\60\3\60"+
		"\3\61\3\61\3\62\3\62\3\63\3\63\3\64\3\64\3\64\7\64\u015d\n\64\f\64\16"+
		"\64\u0160\13\64\3\65\3\65\3\66\3\66\3\66\3\66\3\66\3\66\3\66\3\66\3\66"+
		"\3\66\5\66\u016e\n\66\3\67\6\67\u0171\n\67\r\67\16\67\u0172\3\67\3\67"+
		"\6\67\u0177\n\67\r\67\16\67\u0178\38\68\u017c\n8\r8\168\u017d\39\39\3"+
		":\3:\3:\3:\3;\3;\5;\u0188\n;\3<\3<\3<\3=\3=\7=\u018f\n=\f=\16=\u0192\13"+
		"=\3=\3=\3>\3>\5>\u0198\n>\3?\3?\3?\3@\3@\3@\5@\u01a0\n@\3A\6A\u01a3\n"+
		"A\rA\16A\u01a4\3A\3A\3B\3B\3B\3B\7B\u01ad\nB\fB\16B\u01b0\13B\3B\3B\3"+
		"C\3C\3C\3C\7C\u01b8\nC\fC\16C\u01bb\13C\3C\3C\3C\4\u01ae\u01b9\2D\3\3"+
		"\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21"+
		"!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33\65\34\67\359\36;\37= ?!"+
		"A\"C#E$G%I&K\'M(O)Q*S+U,W-Y.[/]\60_\61a\62c\63e\64g\65i\2k\66m\67o8q\2"+
		"s9u\2w\2y:{\2}\2\177;\u0081<\u0083=\u0085>\3\2\n\5\2C\\aac|\3\2\62;\6"+
		"\2\13\f\17\17))^^\7\2))^^ppttvv\6\2\13\f\17\17$$^^\7\2$$^^ppttvv\5\2\13"+
		"\13\17\17\"\"\4\2\f\f\17\17\u01c5\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2"+
		"\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3"+
		"\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2"+
		"\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2"+
		"\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2"+
		"\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2"+
		"\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2"+
		"O\3\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\2U\3\2\2\2\2W\3\2\2\2\2Y\3\2\2\2\2[\3"+
		"\2\2\2\2]\3\2\2\2\2_\3\2\2\2\2a\3\2\2\2\2c\3\2\2\2\2e\3\2\2\2\2g\3\2\2"+
		"\2\2k\3\2\2\2\2m\3\2\2\2\2o\3\2\2\2\2s\3\2\2\2\2y\3\2\2\2\2\177\3\2\2"+
		"\2\2\u0081\3\2\2\2\2\u0083\3\2\2\2\2\u0085\3\2\2\2\3\u0087\3\2\2\2\5\u0089"+
		"\3\2\2\2\7\u008e\3\2\2\2\t\u0099\3\2\2\2\13\u009e\3\2\2\2\r\u00a5\3\2"+
		"\2\2\17\u00aa\3\2\2\2\21\u00af\3\2\2\2\23\u00b7\3\2\2\2\25\u00bf\3\2\2"+
		"\2\27\u00c9\3\2\2\2\31\u00d0\3\2\2\2\33\u00d3\3\2\2\2\35\u00d8\3\2\2\2"+
		"\37\u00df\3\2\2\2!\u00e8\3\2\2\2#\u00ed\3\2\2\2%\u00f3\3\2\2\2\'\u00f7"+
		"\3\2\2\2)\u0100\3\2\2\2+\u0106\3\2\2\2-\u010b\3\2\2\2/\u0110\3\2\2\2\61"+
		"\u0118\3\2\2\2\63\u011a\3\2\2\2\65\u011c\3\2\2\2\67\u011e\3\2\2\29\u0120"+
		"\3\2\2\2;\u0122\3\2\2\2=\u0124\3\2\2\2?\u0126\3\2\2\2A\u0129\3\2\2\2C"+
		"\u012c\3\2\2\2E\u012f\3\2\2\2G\u0132\3\2\2\2I\u0135\3\2\2\2K\u0138\3\2"+
		"\2\2M\u013a\3\2\2\2O\u013d\3\2\2\2Q\u0140\3\2\2\2S\u0143\3\2\2\2U\u0146"+
		"\3\2\2\2W\u0149\3\2\2\2Y\u014b\3\2\2\2[\u014d\3\2\2\2]\u014f\3\2\2\2_"+
		"\u0151\3\2\2\2a\u0153\3\2\2\2c\u0155\3\2\2\2e\u0157\3\2\2\2g\u0159\3\2"+
		"\2\2i\u0161\3\2\2\2k\u016d\3\2\2\2m\u0170\3\2\2\2o\u017b\3\2\2\2q\u017f"+
		"\3\2\2\2s\u0181\3\2\2\2u\u0187\3\2\2\2w\u0189\3\2\2\2y\u018c\3\2\2\2{"+
		"\u0197\3\2\2\2}\u0199\3\2\2\2\177\u019f\3\2\2\2\u0081\u01a2\3\2\2\2\u0083"+
		"\u01a8\3\2\2\2\u0085\u01b3\3\2\2\2\u0087\u0088\7.\2\2\u0088\4\3\2\2\2"+
		"\u0089\u008a\7e\2\2\u008a\u008b\7g\2\2\u008b\u008c\7n\2\2\u008c\u008d"+
		"\7g\2\2\u008d\6\3\2\2\2\u008e\u008f\7r\2\2\u008f\u0090\7t\2\2\u0090\u0091"+
		"\7k\2\2\u0091\u0092\7t\2\2\u0092\u0093\7q\2\2\u0093\u0094\7f\2\2\u0094"+
		"\u0095\7|\2\2\u0095\u0096\7g\2\2\u0096\u0097\7p\2\2\u0097\u0098\7g\2\2"+
		"\u0098\b\3\2\2\2\u0099\u009a\7|\2\2\u009a\u009b\7p\2\2\u009b\u009c\7c"+
		"\2\2\u009c\u009d\7m\2\2\u009d\n\3\2\2\2\u009e\u009f\7t\2\2\u009f\u00a0"+
		"\7g\2\2\u00a0\u00a1\7c\2\2\u00a1\u00a2\7n\2\2\u00a2\u00a3\7p\2\2\u00a3"+
		"\u00a4\7g\2\2\u00a4\f\3\2\2\2\u00a5\u00a6\7d\2\2\u00a6\u00a7\7q\2\2\u00a7"+
		"\u00a8\7q\2\2\u00a8\u00a9\7n\2\2\u00a9\16\3\2\2\2\u00aa\u00ab\7p\2\2\u00ab"+
		"\u00ac\7k\2\2\u00ac\u00ad\7u\2\2\u00ad\u00ae\7v\2\2\u00ae\20\3\2\2\2\u00af"+
		"\u00b0\7t\2\2\u00b0\u00b1\7g\2\2\u00b1\u00b2\7v\2\2\u00b2\u00b3\7c\2\2"+
		"\u00b3\u00b4\7|\2\2\u00b4\u00b5\7g\2\2\u00b5\u00b6\7e\2\2\u00b6\22\3\2"+
		"\2\2\u00b7\u00b8\7\\\2\2\u00b8\u00b9\7C\2\2\u00b9\u00ba\7E\2\2\u00ba\u00bb"+
		"\7C\2\2\u00bb\u00bc\7V\2\2\u00bc\u00bd\7G\2\2\u00bd\u00be\7M\2\2\u00be"+
		"\24\3\2\2\2\u00bf\u00c0\7f\2\2\u00c0\u00c1\7q\2\2\u00c1\u00c2\7m\2\2\u00c2"+
		"\u00c3\7q\2\2\u00c3\u00c4\7n\2\2\u00c4\u00c5\7g\2\2\u00c5\u00c6\7e\2\2"+
		"\u00c6\u00c7\7m\2\2\u00c7\u00c8\7c\2\2\u00c8\26\3\2\2\2\u00c9\u00ca\7"+
		"f\2\2\u00ca\u00cb\7q\2\2\u00cb\u00cc\7m\2\2\u00cc\u00cd\7g\2\2\u00cd\u00ce"+
		"\7f\2\2\u00ce\u00cf\7{\2\2\u00cf\30\3\2\2\2\u00d0\u00d1\7c\2\2\u00d1\u00d2"+
		"\7m\2\2\u00d2\32\3\2\2\2\u00d3\u00d4\7k\2\2\u00d4\u00d5\7p\2\2\u00d5\u00d6"+
		"\7c\2\2\u00d6\u00d7\7e\2\2\u00d7\34\3\2\2\2\u00d8\u00d9\7r\2\2\u00d9\u00da"+
		"\7t\2\2\u00da\u00db\7g\2\2\u00db\u00dc\7t\2\2\u00dc\u00dd\7w\2\2\u00dd"+
		"\u00de\7u\2\2\u00de\36\3\2\2\2\u00df\u00e0\7r\2\2\u00e0\u00e1\7q\2\2\u00e1"+
		"\u00e2\7m\2\2\u00e2\u00e3\7t\2\2\u00e3\u00e4\7c\2\2\u00e4\u00e5\7e\2\2"+
		"\u00e5\u00e6\7w\2\2\u00e6\u00e7\7l\2\2\u00e7 \3\2\2\2\u00e8\u00e9\7x\2"+
		"\2\u00e9\u00ea\7t\2\2\u00ea\u00eb\7c\2\2\u00eb\u00ec\7e\2\2\u00ec\"\3"+
		"\2\2\2\u00ed\u00ee\7v\2\2\u00ee\u00ef\7t\2\2\u00ef\u00f0\7g\2\2\u00f0"+
		"\u00f1\7f\2\2\u00f1\u00f2\7c\2\2\u00f2$\3\2\2\2\u00f3\u00f4\7f\2\2\u00f4"+
		"\u00f5\7g\2\2\u00f5\u00f6\7h\2\2\u00f6&\3\2\2\2\u00f7\u00f8\7u\2\2\u00f8"+
		"\u00f9\7n\2\2\u00f9\u00fa\7w\2\2\u00fa\u00fb\7d\2\2\u00fb\u00fc\7w\2\2"+
		"\u00fc\u00fd\7l\2\2\u00fd\u00fe\7g\2\2\u00fe\u00ff\7o\2\2\u00ff(\3\2\2"+
		"\2\u0100\u0101\7e\2\2\u0101\u0102\7w\2\2\u0102\u0103\7f\2\2\u0103\u0104"+
		"\7|\2\2\u0104\u0105\7c\2\2\u0105*\3\2\2\2\u0106\u0107\7p\2\2\u0107\u0108"+
		"\7q\2\2\u0108\u0109\7x\2\2\u0109\u010a\7{\2\2\u010a,\3\2\2\2\u010b\u010c"+
		"\7|\2\2\u010c\u010d\7o\2\2\u010d\u010e\7c\2\2\u010e\u010f\7|\2\2\u010f"+
		".\3\2\2\2\u0110\u0111\7o\2\2\u0111\u0112\7p\2\2\u0112\u0113\7q\2\2\u0113"+
		"\u0114\7|\2\2\u0114\u0115\7k\2\2\u0115\u0116\7p\2\2\u0116\u0117\7c\2\2"+
		"\u0117\60\3\2\2\2\u0118\u0119\7*\2\2\u0119\62\3\2\2\2\u011a\u011b\7+\2"+
		"\2\u011b\64\3\2\2\2\u011c\u011d\7]\2\2\u011d\66\3\2\2\2\u011e\u011f\7"+
		"_\2\2\u011f8\3\2\2\2\u0120\u0121\7}\2\2\u0121:\3\2\2\2\u0122\u0123\7\177"+
		"\2\2\u0123<\3\2\2\2\u0124\u0125\7=\2\2\u0125>\3\2\2\2\u0126\u0127\7?\2"+
		"\2\u0127\u0128\7?\2\2\u0128@\3\2\2\2\u0129\u012a\7#\2\2\u012a\u012b\7"+
		"?\2\2\u012bB\3\2\2\2\u012c\u012d\7-\2\2\u012d\u012e\7?\2\2\u012eD\3\2"+
		"\2\2\u012f\u0130\7/\2\2\u0130\u0131\7?\2\2\u0131F\3\2\2\2\u0132\u0133"+
		"\7,\2\2\u0133\u0134\7?\2\2\u0134H\3\2\2\2\u0135\u0136\7\61\2\2\u0136\u0137"+
		"\7?\2\2\u0137J\3\2\2\2\u0138\u0139\7?\2\2\u0139L\3\2\2\2\u013a\u013b\7"+
		"\'\2\2\u013b\u013c\7?\2\2\u013cN\3\2\2\2\u013d\u013e\7>\2\2\u013e\u013f"+
		"\7?\2\2\u013fP\3\2\2\2\u0140\u0141\7@\2\2\u0141\u0142\7?\2\2\u0142R\3"+
		"\2\2\2\u0143\u0144\7(\2\2\u0144\u0145\7(\2\2\u0145T\3\2\2\2\u0146\u0147"+
		"\7~\2\2\u0147\u0148\7~\2\2\u0148V\3\2\2\2\u0149\u014a\7#\2\2\u014aX\3"+
		"\2\2\2\u014b\u014c\7>\2\2\u014cZ\3\2\2\2\u014d\u014e\7@\2\2\u014e\\\3"+
		"\2\2\2\u014f\u0150\7-\2\2\u0150^\3\2\2\2\u0151\u0152\7/\2\2\u0152`\3\2"+
		"\2\2\u0153\u0154\7,\2\2\u0154b\3\2\2\2\u0155\u0156\7\61\2\2\u0156d\3\2"+
		"\2\2\u0157\u0158\7\'\2\2\u0158f\3\2\2\2\u0159\u015e\5i\65\2\u015a\u015d"+
		"\5i\65\2\u015b\u015d\5q9\2\u015c\u015a\3\2\2\2\u015c\u015b\3\2\2\2\u015d"+
		"\u0160\3\2\2\2\u015e\u015c\3\2\2\2\u015e\u015f\3\2\2\2\u015fh\3\2\2\2"+
		"\u0160\u015e\3\2\2\2\u0161\u0162\t\2\2\2\u0162j\3\2\2\2\u0163\u0164\7"+
		"r\2\2\u0164\u0165\7t\2\2\u0165\u0166\7c\2\2\u0166\u0167\7x\2\2\u0167\u0168"+
		"\7f\2\2\u0168\u016e\7c\2\2\u0169\u016a\7d\2\2\u016a\u016b\7n\2\2\u016b"+
		"\u016c\7w\2\2\u016c\u016e\7f\2\2\u016d\u0163\3\2\2\2\u016d\u0169\3\2\2"+
		"\2\u016el\3\2\2\2\u016f\u0171\5q9\2\u0170\u016f\3\2\2\2\u0171\u0172\3"+
		"\2\2\2\u0172\u0170\3\2\2\2\u0172\u0173\3\2\2\2\u0173\u0174\3\2\2\2\u0174"+
		"\u0176\7\60\2\2\u0175\u0177\5q9\2\u0176\u0175\3\2\2\2\u0177\u0178\3\2"+
		"\2\2\u0178\u0176\3\2\2\2\u0178\u0179\3\2\2\2\u0179n\3\2\2\2\u017a\u017c"+
		"\5q9\2\u017b\u017a\3\2\2\2\u017c\u017d\3\2\2\2\u017d\u017b\3\2\2\2\u017d"+
		"\u017e\3\2\2\2\u017ep\3\2\2\2\u017f\u0180\t\3\2\2\u0180r\3\2\2\2\u0181"+
		"\u0182\7)\2\2\u0182\u0183\5u;\2\u0183\u0184\7)\2\2\u0184t\3\2\2\2\u0185"+
		"\u0188\n\4\2\2\u0186\u0188\5w<\2\u0187\u0185\3\2\2\2\u0187\u0186\3\2\2"+
		"\2\u0188v\3\2\2\2\u0189\u018a\7^\2\2\u018a\u018b\t\5\2\2\u018bx\3\2\2"+
		"\2\u018c\u0190\7$\2\2\u018d\u018f\5{>\2\u018e\u018d\3\2\2\2\u018f\u0192"+
		"\3\2\2\2\u0190\u018e\3\2\2\2\u0190\u0191\3\2\2\2\u0191\u0193\3\2\2\2\u0192"+
		"\u0190\3\2\2\2\u0193\u0194\7$\2\2\u0194z\3\2\2\2\u0195\u0198\n\6\2\2\u0196"+
		"\u0198\5}?\2\u0197\u0195\3\2\2\2\u0197\u0196\3\2\2\2\u0198|\3\2\2\2\u0199"+
		"\u019a\7^\2\2\u019a\u019b\t\7\2\2\u019b~\3\2\2\2\u019c\u01a0\7\f\2\2\u019d"+
		"\u019e\7\17\2\2\u019e\u01a0\7\f\2\2\u019f\u019c\3\2\2\2\u019f\u019d\3"+
		"\2\2\2\u01a0\u0080\3\2\2\2\u01a1\u01a3\t\b\2\2\u01a2\u01a1\3\2\2\2\u01a3"+
		"\u01a4\3\2\2\2\u01a4\u01a2\3\2\2\2\u01a4\u01a5\3\2\2\2\u01a5\u01a6\3\2"+
		"\2\2\u01a6\u01a7\bA\2\2\u01a7\u0082\3\2\2\2\u01a8\u01a9\7\61\2\2\u01a9"+
		"\u01aa\7\61\2\2\u01aa\u01ae\3\2\2\2\u01ab\u01ad\n\t\2\2\u01ac\u01ab\3"+
		"\2\2\2\u01ad\u01b0\3\2\2\2\u01ae\u01af\3\2\2\2\u01ae\u01ac\3\2\2\2\u01af"+
		"\u01b1\3\2\2\2\u01b0\u01ae\3\2\2\2\u01b1\u01b2\5\177@\2\u01b2\u0084\3"+
		"\2\2\2\u01b3\u01b4\7\61\2\2\u01b4\u01b5\7,\2\2\u01b5\u01b9\3\2\2\2\u01b6"+
		"\u01b8\13\2\2\2\u01b7\u01b6\3\2\2\2\u01b8\u01bb\3\2\2\2\u01b9\u01ba\3"+
		"\2\2\2\u01b9\u01b7\3\2\2\2\u01ba\u01bc\3\2\2\2\u01bb\u01b9\3\2\2\2\u01bc"+
		"\u01bd\7,\2\2\u01bd\u01be\7\61\2\2\u01be\u0086\3\2\2\2\20\2\u015c\u015e"+
		"\u016d\u0172\u0178\u017d\u0187\u0190\u0197\u019f\u01a4\u01ae\u01b9\3\b"+
		"\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}