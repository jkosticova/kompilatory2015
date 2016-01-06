
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.FileInputStream;
import java.io.InputStream;

public class Compiler {
    public static void main(String[] args) throws Exception {
        String inputFile = null;
        if ( args.length>0 ) inputFile = args[0];
        InputStream is = System.in;
        if ( inputFile!=null ) is = new FileInputStream(inputFile);
        ANTLRInputStream input = new ANTLRInputStream(is);
        CminusMinusLexer lexer = new CminusMinusLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        CminusMinusParser parser = new CminusMinusParser(tokens);
        ParseTree tree = parser.init(); // parse

        CompilerVisitor eval = new CompilerVisitor();
        CodeFragment code = eval.visit(tree);
        System.out.print(code.toString());
    }
}
