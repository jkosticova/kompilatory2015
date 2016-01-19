// Generated from /home/brando/School/git/Kompilatory/Exmi.g4 by ANTLR 4.5.1
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class ExmiLexer extends Lexer {
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
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
		"T__9", "T__10", "T__11", "T__12", "T__13", "ID", "COMMENT", "INT", "BOOL", 
		"CHAR", "STRING", "MUL", "DIV", "ADD", "SUB", "EXP", "OR", "AND", "EQ", 
		"WS", "LPAR", "RPAR", "LARR", "RARR", "LBLOCK", "RBLOCK", "END_ST"
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


	public ExmiLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Exmi.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2&\u00d8\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\4"+
		"\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\b\3\b\3"+
		"\t\3\t\3\t\3\n\3\n\3\13\3\13\3\f\3\f\3\f\3\r\3\r\3\r\3\16\3\16\3\16\3"+
		"\17\3\17\3\17\3\20\3\20\7\20|\n\20\f\20\16\20\177\13\20\3\21\3\21\3\21"+
		"\3\21\7\21\u0085\n\21\f\21\16\21\u0088\13\21\3\21\3\21\3\21\3\22\6\22"+
		"\u008e\n\22\r\22\16\22\u008f\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3"+
		"\23\5\23\u009b\n\23\3\24\3\24\3\24\3\24\3\25\3\25\7\25\u00a3\n\25\f\25"+
		"\16\25\u00a6\13\25\3\25\3\25\3\26\3\26\3\27\3\27\3\30\3\30\3\31\3\31\3"+
		"\32\3\32\3\33\3\33\3\33\3\33\5\33\u00b8\n\33\3\34\3\34\3\34\3\34\3\34"+
		"\5\34\u00bf\n\34\3\35\3\35\3\35\3\36\6\36\u00c5\n\36\r\36\16\36\u00c6"+
		"\3\36\3\36\3\37\3\37\3 \3 \3!\3!\3\"\3\"\3#\3#\3$\3$\3%\3%\2\2&\3\3\5"+
		"\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21"+
		"!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33\65\34\67\359\36;\37= ?!"+
		"A\"C#E$G%I&\3\2\t\3\2c|\5\2\62;C\\c|\6\2\"\"\62;C\\c|\3\2\62;\3\2))\3"+
		"\2$$\4\2\13\f\"\"\u00df\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2"+
		"\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25"+
		"\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2"+
		"\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2"+
		"\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3"+
		"\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2"+
		"\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\3K\3\2\2\2\5M\3\2\2\2\7T\3\2\2\2\t"+
		"W\3\2\2\2\13\\\3\2\2\2\rb\3\2\2\2\17d\3\2\2\2\21f\3\2\2\2\23i\3\2\2\2"+
		"\25k\3\2\2\2\27m\3\2\2\2\31p\3\2\2\2\33s\3\2\2\2\35v\3\2\2\2\37y\3\2\2"+
		"\2!\u0080\3\2\2\2#\u008d\3\2\2\2%\u009a\3\2\2\2\'\u009c\3\2\2\2)\u00a0"+
		"\3\2\2\2+\u00a9\3\2\2\2-\u00ab\3\2\2\2/\u00ad\3\2\2\2\61\u00af\3\2\2\2"+
		"\63\u00b1\3\2\2\2\65\u00b7\3\2\2\2\67\u00be\3\2\2\29\u00c0\3\2\2\2;\u00c4"+
		"\3\2\2\2=\u00ca\3\2\2\2?\u00cc\3\2\2\2A\u00ce\3\2\2\2C\u00d0\3\2\2\2E"+
		"\u00d2\3\2\2\2G\u00d4\3\2\2\2I\u00d6\3\2\2\2KL\7.\2\2L\4\3\2\2\2MN\7t"+
		"\2\2NO\7g\2\2OP\7v\2\2PQ\7w\2\2QR\7t\2\2RS\7p\2\2S\6\3\2\2\2TU\7k\2\2"+
		"UV\7h\2\2V\b\3\2\2\2WX\7g\2\2XY\7n\2\2YZ\7u\2\2Z[\7g\2\2[\n\3\2\2\2\\"+
		"]\7y\2\2]^\7j\2\2^_\7k\2\2_`\7n\2\2`a\7g\2\2a\f\3\2\2\2bc\7?\2\2c\16\3"+
		"\2\2\2de\7\'\2\2e\20\3\2\2\2fg\7#\2\2gh\7?\2\2h\22\3\2\2\2ij\7>\2\2j\24"+
		"\3\2\2\2kl\7@\2\2l\26\3\2\2\2mn\7>\2\2no\7?\2\2o\30\3\2\2\2pq\7@\2\2q"+
		"r\7?\2\2r\32\3\2\2\2st\7(\2\2tu\7(\2\2u\34\3\2\2\2vw\7~\2\2wx\7~\2\2x"+
		"\36\3\2\2\2y}\t\2\2\2z|\t\3\2\2{z\3\2\2\2|\177\3\2\2\2}{\3\2\2\2}~\3\2"+
		"\2\2~ \3\2\2\2\177}\3\2\2\2\u0080\u0081\7\61\2\2\u0081\u0082\7,\2\2\u0082"+
		"\u0086\3\2\2\2\u0083\u0085\t\4\2\2\u0084\u0083\3\2\2\2\u0085\u0088\3\2"+
		"\2\2\u0086\u0084\3\2\2\2\u0086\u0087\3\2\2\2\u0087\u0089\3\2\2\2\u0088"+
		"\u0086\3\2\2\2\u0089\u008a\7,\2\2\u008a\u008b\7\61\2\2\u008b\"\3\2\2\2"+
		"\u008c\u008e\t\5\2\2\u008d\u008c\3\2\2\2\u008e\u008f\3\2\2\2\u008f\u008d"+
		"\3\2\2\2\u008f\u0090\3\2\2\2\u0090$\3\2\2\2\u0091\u0092\7V\2\2\u0092\u0093"+
		"\7T\2\2\u0093\u0094\7W\2\2\u0094\u009b\7G\2\2\u0095\u0096\7H\2\2\u0096"+
		"\u0097\7C\2\2\u0097\u0098\7N\2\2\u0098\u0099\7U\2\2\u0099\u009b\7G\2\2"+
		"\u009a\u0091\3\2\2\2\u009a\u0095\3\2\2\2\u009b&\3\2\2\2\u009c\u009d\7"+
		")\2\2\u009d\u009e\n\6\2\2\u009e\u009f\7)\2\2\u009f(\3\2\2\2\u00a0\u00a4"+
		"\7$\2\2\u00a1\u00a3\n\7\2\2\u00a2\u00a1\3\2\2\2\u00a3\u00a6\3\2\2\2\u00a4"+
		"\u00a2\3\2\2\2\u00a4\u00a5\3\2\2\2\u00a5\u00a7\3\2\2\2\u00a6\u00a4\3\2"+
		"\2\2\u00a7\u00a8\7$\2\2\u00a8*\3\2\2\2\u00a9\u00aa\7,\2\2\u00aa,\3\2\2"+
		"\2\u00ab\u00ac\7\61\2\2\u00ac.\3\2\2\2\u00ad\u00ae\7-\2\2\u00ae\60\3\2"+
		"\2\2\u00af\u00b0\7/\2\2\u00b0\62\3\2\2\2\u00b1\u00b2\7`\2\2\u00b2\64\3"+
		"\2\2\2\u00b3\u00b4\7~\2\2\u00b4\u00b8\7~\2\2\u00b5\u00b6\7q\2\2\u00b6"+
		"\u00b8\7t\2\2\u00b7\u00b3\3\2\2\2\u00b7\u00b5\3\2\2\2\u00b8\66\3\2\2\2"+
		"\u00b9\u00ba\7(\2\2\u00ba\u00bf\7(\2\2\u00bb\u00bc\7c\2\2\u00bc\u00bd"+
		"\7p\2\2\u00bd\u00bf\7f\2\2\u00be\u00b9\3\2\2\2\u00be\u00bb\3\2\2\2\u00bf"+
		"8\3\2\2\2\u00c0\u00c1\7?\2\2\u00c1\u00c2\7?\2\2\u00c2:\3\2\2\2\u00c3\u00c5"+
		"\t\b\2\2\u00c4\u00c3\3\2\2\2\u00c5\u00c6\3\2\2\2\u00c6\u00c4\3\2\2\2\u00c6"+
		"\u00c7\3\2\2\2\u00c7\u00c8\3\2\2\2\u00c8\u00c9\b\36\2\2\u00c9<\3\2\2\2"+
		"\u00ca\u00cb\7*\2\2\u00cb>\3\2\2\2\u00cc\u00cd\7+\2\2\u00cd@\3\2\2\2\u00ce"+
		"\u00cf\7]\2\2\u00cfB\3\2\2\2\u00d0\u00d1\7_\2\2\u00d1D\3\2\2\2\u00d2\u00d3"+
		"\7}\2\2\u00d3F\3\2\2\2\u00d4\u00d5\7\177\2\2\u00d5H\3\2\2\2\u00d6\u00d7"+
		"\7=\2\2\u00d7J\3\2\2\2\13\2}\u0086\u008f\u009a\u00a4\u00b7\u00be\u00c6"+
		"\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}