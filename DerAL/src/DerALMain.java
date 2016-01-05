import java.io.FileInputStream;
import java.io.InputStream;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;


public class DerALMain {
    public static void main(String[] args) throws Exception {
        String inputFile = null;
        if ( args.length>0 ) inputFile = args[0];
        InputStream is = System.in;
        if ( inputFile!=null ) is = new FileInputStream(inputFile);
        ANTLRInputStream input = new ANTLRInputStream(is);
        DerALLexer lexer = new DerALLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        DerALParser parser = new DerALParser(tokens);
        ParseTree tree = parser.sourceCode(); // parse
 
        DerALCompiler compiler = new DerALCompiler();
        System.out.println(compiler.visit(tree));
    }
}
