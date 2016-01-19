import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

public class Exmi {
    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            System.out.println("Wrong number of arguments!");
            return;
        }

        CharStream input = new ANTLRFileStream(args[0]);
        ExmiLexer lexer = new ExmiLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        ExmiParser parser = new ExmiParser(tokens);

        parser.removeErrorListeners();                   // vypnutie aktualnych ErrorListener-ov
        parser.addErrorListener(new VerboseListener());  // zapnutie noveho ErrorListener-a

        ParseTree tree = parser.init();

        System.out.println(";" + tree.toStringTree(parser)); // print LISP-style tree

        ExmiVisitor<Code> eval = new ExmiLLVMVisitor();
        Code program = eval.visit(tree);

        System.out.println(program.toString());
    }
}
