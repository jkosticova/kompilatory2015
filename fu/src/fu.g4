grammar fu;

init: statements;
statements: (statement ';')*;

varname: STRING;
funcname: STRING;

statement:
    vartype funcname PAREN_OPEN ( | (arrvartype varname) (',' arrvartype varname)*) PAREN_CLOSE BLOCK_OPEN statements 'return' expression ';' BLOCK_CLOSE # FunctionDefinition
    | VARTYPE_VOID funcname PAREN_OPEN ( | (arrvartype varname) (',' arrvartype varname)*) PAREN_CLOSE BLOCK_OPEN statements BLOCK_CLOSE # VoidFunctionDefinition
    | vartype funcname PAREN_OPEN ( | (arrvartype) (',' arrvartype)*) PAREN_CLOSE                    # ExternalFunctionDeclaration
    | VARTYPE_INTEGER varname                                                   # DeclareIntVar
    | VARTYPE_STRING varname                                                    # DeclareStringVar
    | VARTYPE_INTEGER varname '=' expression                                    # DeclareAssignIntVar
    | VARTYPE_STRING varname '=' expression                                     # DeclareAssignStringVar
    | VARTYPE_INTEGER (BRACK_OPEN expression BRACK_CLOSE)+ varname              # DeclareIntArray
    | VARTYPE_STRING (BRACK_OPEN expression BRACK_CLOSE)+ varname               # DeclareStringArray
    | IF PAREN_OPEN expression PAREN_CLOSE THEN BLOCK_OPEN statements BLOCK_CLOSE ELSE BLOCK_OPEN statements BLOCK_CLOSE # IfElse
    | WHILE PAREN_OPEN expression PAREN_CLOSE BLOCK_OPEN statements BLOCK_CLOSE # While
    | FOR PAREN_OPEN statement SEP expression SEP statement PAREN_CLOSE BLOCK_OPEN statements BLOCK_CLOSE # For
    | varname (BRACK_OPEN expression BRACK_CLOSE)+ '=' expression               # ArrayPut
    | varname '=' expression                                                    # AssignVariable
    | varname INC                                                               # Increment
    | varname DEC                                                               # Decrement
    | varname INCBY expression                                                  # IncrementBy
    | varname DECBY expression                                                  # DecrementBy
    | varname MULBY expression                                                  # MultiplyBy
    | varname DIVBY expression                                                  # DivideBy
    | varname MODBY expression                                                  # ModulusBy
    | expression OUTPUT                                                         # Print
    | expression OUTPUTLINE                                                     # PrintLine
    | varname OUTPUT VARTYPE_INTEGER BRACK_OPEN expression BRACK_CLOSE          # PrintSpaceSeparatedIntegers
    | varname OUTPUT VARTYPE_STRING BRACK_OPEN expression BRACK_CLOSE           # PrintSpaceSeparatedStrings
    | varname OUTPUTLINE VARTYPE_INTEGER BRACK_OPEN expression BRACK_CLOSE      # PrintLineSeparatedIntegers
    | varname OUTPUTLINE VARTYPE_STRING BRACK_OPEN expression BRACK_CLOSE       # PrintLineSeparatedStrings
    | varname INPUT                                                             # Scan
    | varname INPUT VARTYPE_INTEGER BRACK_OPEN expression BRACK_CLOSE           # ScanArrayOfIntegers
    | varname INPUT VARTYPE_STRING BRACK_OPEN expression BRACK_CLOSE            # ScanArrayOfStrings
    | expression                                                                # ContainedExpression
    ;

expression:
    funcname PAREN_OPEN ( | (expression) (',' expression)*) PAREN_CLOSE       # FunctionCall
    | ADD expression                                      # UnaryPlus
    | SUB expression                                    # UnaryMinus
    | expression DIV expression                         # Division
    | expression MOD expression                         # Modulus
    | expression ADD expression                         # Add
    | expression MUL expression                         # Multiply
    | expression SUB expression                         # Subtract
    | NOT expression                                    # Not
    | expression AND expression                         # And
    | expression OR expression                          # Or
    | expression GT expression                          # GreaterThan
    | expression EQ expression                          # Equals
    | expression LT expression                          # LessThan
    | expression GTE expression                         # GreaterOrEqual
    | expression LTE expression                         # LesserOrEqual
    | expression NE expression                          # NotEqual
    | PAREN_OPEN expression PAREN_CLOSE                 # Paren
    | ('"' STRING '"')                                  # StringValue
    | varname (BRACK_OPEN expression BRACK_CLOSE)+      # ArrayGet
    | STRING                                            # VarName
    | INT                                               # Integer
    ;

INT: DIGIT+;
VARTYPE_INTEGER: '\\/';
VARTYPE_STRING: '\\\\';
VARTYPE_VOID: '\\-';
arrvartype: (vartype (BRACK_OPEN BRACK_CLOSE)*);
vartype: (VARTYPE_INTEGER | VARTYPE_STRING);


INPUT: '<<';
OUTPUT: '>>';
OUTPUTLINE: '>>>';
GT: '>';
EQ: '==';
LT: '<';
GTE: '>=';
LTE: '<=';
NE: '<>';
INC: '++';
DEC: '--';
INCBY: '+=';
DECBY: '-=';
MULBY: '*=';
DIVBY: '/=';
MODBY: '%=';
MUL: '*';
DIV: '/';
MOD: '%';
ADD: '+';
SUB: '-';
IF: '?';
THEN: ':';
ELSE: '::';
WHILE: '$';
FOR: '@';
SEP: ';';
BLOCK_OPEN: '{';
BLOCK_CLOSE: '}';
BRACK_OPEN: '[';
BRACK_CLOSE: ']';
PAREN_OPEN: '(';
PAREN_CLOSE: ')';
AND: '&' '&';
OR: '|' '|';
NOT: '!';
COMMENT: '~'.*?'~' -> skip;

STRING: [a-zA-Z][a-zA-Z0-9]*;
WHITESPACE: [ \t\n] -> skip;
fragment DIGIT: [0-9];