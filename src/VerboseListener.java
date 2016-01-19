import org.antlr.v4.runtime.*;

import java.util.*;
 
public class VerboseListener extends BaseErrorListener {
 
        @Override
        public void syntaxError(
                Recognizer<?, ?> recognizer, // lexer alebo parser, ktory detekoval chybu
                Object offendingSymbol,      // token, ktory sposobil chybu (v pripade lexera je null)
                int line,                    // cislo riadku, kde sa vyskytla chyba
                int charPositionInLine,      // pozicia v ramci riadku, kde sa vyskytla chyba
                String msg,                  // chybova sprava, ktora sa ma vypisat
                RecognitionException e       // vynimka generovana lexerom/parserom, ktora sposobila reportovanie chyby
        ) {
                // nas ErrorListener je urceny iba pre parser
                List<String> stack = ((Parser)recognizer).getRuleInvocationStack();
                Collections.reverse(stack);
                System.err.println("stack: " + stack);
                System.err.println("line " + line + ":" + charPositionInLine + " at " + offendingSymbol + ": " + msg);
        }
}