grammar CminusMinus;

init: statements;

statements: (statement ';')*;

statement:
      name  ASSIGN expression                                                             # AssignVar
     | arr ASSIGN expression                                                              # AssignArr
     | declar_var_Int                                                                     # DeclarVarInt
     | declar_var_Str                                                                     # DeclarVarStr
     | declare_array                                                                      # DeclareArray
     | var'++'                                                                            # Increment
     | var'--'                                                                            # Decrement
     | '<<n'expression                                                                    # Print_int
     | '<<t'expression                                                                    # Print_str
     | '>>n' var                                                                          # Read_int
     | '>>t' var                                                                          # Read_str
     | block                                                                              # Block_statement
     | function_block                                                                     # Fun_block
     | function_call                                                                      # Fun_call
     | 'def' type name PAREN_OPEN args PAREN_CLOSE function_block                         # FunctionDef
     |'extern' 'def' type name'('args')'      		                                      # Extern
     | FOR PAREN_OPEN declar_var_Int? ';' expression ';' statement? PAREN_CLOSE statement # For_cycle
     | IF PAREN_OPEN expression PAREN_CLOSE statement (ELSE statement)?                   # If_else
     ;


expression:
     INT                                                                   # Int
     | '"'STRING'"'                                                        # Str
     | '-'expression                                                       # UnarMinus
     | var                                                                 # Variable
     | arr                                                                 # Array
     | expression op=(ADD|SUB) expression                                  # Add
     | expression op=(DIV|MUL|MOD) expression                              # Mul
     | expression op=AND expression                                        # And
     | expression op=OR expression                                         # Or
     | expression op=(EQ| NE| LE| GE| GT| LT) expression                   # Comparison
     | PAREN_OPEN expression PAREN_CLOSE                                   # Priority
     | function_call                                                       # FunctionCall
     ;


var: STRING;
arr: STRING ('[' expression ']')+;

block:BLOCK_START statements BLOCK_END ;
function_block:BLOCK_START statements ret BLOCK_END ;
function_call: name PAREN_OPEN ((expression ',')* expression)? PAREN_CLOSE;

ret: 'return' expression ';' ;

declare_array: type STRING ('[' expression ']')+;
declar_var_Int : 'num' STRING (ASSIGN expression)?;
declar_var_Str : 'text' STRING (ASSIGN expression)?;

args: (type lvalue (',' type lvalue)*)?;
type:   ('num'|'text');

ASSIGN: '=';
lvalue:
     simple_var
     |array_var
     ;

simple_var: STRING ;
array_var: STRING (LBRACK RBRACK)+;

name: STRING;

PAREN_OPEN: '(';
PAREN_CLOSE: ')';
LBRACK: '[';
RBRACK: ']';

ADD: '+';
SUB: '-';
MUL: '*';
DIV: '/';
MOD: '%';
EXP: '^';

AND: 'and'| '&&';
OR: 'or'| '||';

BLOCK_START: '{';
BLOCK_END: '}';
FOR: 'ring';
IF: 'ak';
ELSE: 'inak';

INT : NUMBER;
STRING: [a-zA-Z][a-zA-Z0-9]*;
NUMBER : '0' | [1-9]DIGIT*;
DIGIT: [0-9];
WHITESPACE: [ |\n|\t] -> skip;
COMMENT: '#' ~[\n]* -> skip;

EQ: '==';
NE: '!=';
LE: '<=';
GE: '>=';
GT: '>';
LT: '<';


