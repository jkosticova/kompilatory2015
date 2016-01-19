import org.antlr.v4.runtime.ParserRuleContext;

public class Position {
    int line;
    int pos;

    @Override public String toString() {
        return line + ":" + pos;
    }

    public Position(ParserRuleContext ctx) {
        this(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine());
    }

    public Position(int line, int pos) {
        this.line = line;
        this.pos = pos;
    }
}
