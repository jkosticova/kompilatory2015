import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.tree.ParseTree;
import java.io.InputStream;

public class Compiler {
    public static void main(String[] args) throws Exception {
        InputStream is = System.in;
        ANTLRInputStream input = new ANTLRInputStream(is);
        fuLexer lexer = new fuLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        fuParser parser = new fuParser(tokens);
        ParseTree tree = parser.init();

        CompilerVisitor eval = new CompilerVisitor();
        CodeFragment code = eval.visit(tree);
        System.out.println(code);
    }
}
