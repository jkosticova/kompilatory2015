grammar Exmi;

init: (comment | (func_decl END_ST) | (declaration_global END_ST) | func_def)*;

ID: [a-z][a-zA-Z0-9]*;

object:
    ID (LARR expression RARR)*;

arg_one:
    ID ID(LARR RARR)*;

arglist:
    (arg_one (',' arg_one)*)?;

func_decl:
    ID ID LPAR arglist RPAR;

func_def:
    ID ID LPAR arglist RPAR LBLOCK code 'return' expression END_ST RBLOCK;

code:
    (expression END_ST
    | assigment END_ST
    | declaration END_ST
    | if_block
    | while_block
    | comment)*;


if_block:
    'if' LPAR expression RPAR LBLOCK code RBLOCK ('else' LBLOCK code RBLOCK)?;

while_block:
    'while' LPAR expression RPAR LBLOCK code RBLOCK;

assigment:
     object '=' expression
     |;

declaration_global:
    ID ID;

declaration:
   ID ID (LARR expression RARR)*;

expression:
     op=('-'|'+') expression                            # Una
     | ID LPAR (expression ( ',' expression )* )? RPAR  # function
     | <assoc=right> expression op='^' expression       # Exp
     | expression op=('/'|'*') expression               # Mul
     | expression op=('+'|'-') expression               # Add
     | expression op='%' expression               # Mod
     | expression
        op=('=='|'!='|'<'|'>'|'<='|'>=')
       expression                                       # Cmp
     | expression op='&&' expression                    # And
     | expression op='||' expression                    # Or
     | '(' expression ')'                               # Par
     | object                                           # Var
     | STRING                                           # String
     | INT                                              # Int
     | BOOL                                             # Bool
     | CHAR                                             # Char
     ;

comment: COMMENT;
COMMENT: '/*'[a-zA-Z0-9 ]*'*/';
INT: [0-9]+;
BOOL: 'TRUE' | 'FALSE';
CHAR: '\''~[']'\'';
STRING: '"' ~["]* '"';
MUL: '*';
DIV: '/';
ADD: '+';
SUB: '-';
EXP: '^';
OR: '||' | 'or';
AND: '&&' | 'and';
EQ: '==';
WS: (' ' | '\t' | '\n')+ -> skip;

LPAR: '(';
RPAR: ')';
LARR: '[';
RARR: ']';
LBLOCK: '{';
RBLOCK: '}';
END_ST: ';';
