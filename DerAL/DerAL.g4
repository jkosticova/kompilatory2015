grammar DerAL;

sourceCode  : declarations Begin Newline mainProgram EOF;

declarations : (declaration | Newline | BlockComment | LineComment)*;

mainProgram : statements;

statements : (statement | Newline | BlockComment | LineComment)*;

declaration : functionDefinition					# declarationFunctioDefinition
			| functionForwardDeclaration Semicolon	# declarationFunctionForwardDeclaration
			| functionExternDeclaration Semicolon	# declarationFunctionExtern
			| variableDeclaration Semicolon			# declarationGlobalVar
			| Semicolon								# declarationSemicol
			;
			
statement 	: variableDeclaration Semicolon		# statementVarDeclaration
			| assignment Semicolon				# statementAssignment
			| loopStatement						# statementLoop
			| condStatement						# statementCond
			| Return expression? Semicolon 		# statementReturn
			| compoundStatement					# statementCompound
			| expression Semicolon				# statementExpression
			| Semicolon 						# statementSemicol
			;
			
loopStatement	: While LeftParenthesis loopCheckStatement RightParenthesis Newline* LeftBrace statements RightBrace												# loopWhile
				| For LeftParenthesis forInitStatement Semicolon loopCheckStatement Semicolon forLoopStatement RightParenthesis Newline* LeftBrace statements RightBrace		# loopFor
				;

loopCheckStatement	: expression? ;


forInitStatement 	: variableDeclaration	# forInitVarDecl
					| assignment 			# forInitAssignment
					| 						# forInitEmpty
					;

forLoopStatement 	: assignment			# forLoopStatementAssignment
					| 						# forLoopStatementEmpty
					;
					
condStatement	: If LeftParenthesis expression RightParenthesis Newline* statement (Newline* Else Newline* statement)?;

assignment : Identifier arrayIndex* (assignOp=(Assign|PlusAssign|MinusAssign|StarAssign|DivAssign|ModAssign)) expression;
			
compoundStatement : LeftBrace statements  RightBrace;
			
typeSpecification	: typeName arrayIndex+					# arrayAllocTypeSpecification
					| typeName (LeftBracket RightBracket)+	# arrayNoAllocTypeSpecification
					| typeName								# basicTypeSpecification
					| Void 									# voidTypeSpecification
					;
					
variableDeclaration	: typeSpecification Identifier (Assign expression)?;

functionExternDeclaration : Extern typeSpecification Identifier LeftParenthesis definitionArgsList RightParenthesis;

functionForwardDeclaration : Dec typeSpecification Identifier LeftParenthesis definitionArgsList RightParenthesis;
			
functionDefinition : Def typeSpecification Identifier LeftParenthesis definitionArgsList RightParenthesis Newline* functionBody;

definitionArgsList 	: definitionArgSpecification (',' definitionArgSpecification)*	
					| 
					;
					
definitionArgSpecification : typeSpecification Identifier;

functionBody : LeftBrace statements RightBrace;

expression 	: op=(Minus|Not) expression												# unaryExpression //hotovo
			| expression op=(Star|Div|Mod) expression 								# mulExpression //hotovo
			| expression op=(Plus|Minus) expression									# addExpression //hotovo
			| expression op=(Equal|Less|Greater|LessEqual|GreaterEqual|NotEqual) expression 	# relExpression //hotovo
			| expression And expression												# AndExpression //hotovo
			| expression Or expression												# OrExpression //hotovo
			| LeftParenthesis expression RightParenthesis							# ParExpression //hotovo
			| BoolConstatnt															# boolConstExpression //hotovo?
			| Identifier arrayIndex*												# varExpression //hotovo
			| Identifier LeftParenthesis argListFuncCall RightParenthesis			# funcCallExpression //hotovo
			| IntegerConstant														# intConstExpression //hotovo
			| DoubleConstant														# doubleConstExpression //hotovo
			| CharConstant															# charConstExpression //hotovo
			| StringConstant														# stringConstExpression //hotovo
			| arrayIndex+															# arrayAllocExpression //hotovo
			;
			
argListFuncCall	: (expression (',' expression)*)? ;

arrayIndex	: LeftBracket expression RightBracket;

typeName 	: Int
			| Unsigned
			| Char
			| Double
			| Bool
			| String ;

Int : 'cele' ;
Unsigned : 'prirodzene' ;
Char : 'znak' ;
Double : 'realne' ;
Bool : 'bool' ;
Void : 'nist' ;
String : 'retazec' ;

Begin : 'ZACATEK' ;
For : 'dokolecka' ; 
While : 'dokedy' ; 
If : 'ak' ; 
Else : 'inac' ; 
Break : 'prerus' ; 
Continue : 'pokracuj' ; 
Return : 'vrac' ; 
Class : 'treda' ; 
Def : 'def' ; 
Dec : 'slubujem' ;
Extern : 'cudza' ; 
New : 'novy' ; 
Delete : 'zmaz' ;
Set : 'mnozina' ;

LeftParenthesis : '(';
RightParenthesis : ')';
LeftBracket : '[';
RightBracket : ']';
LeftBrace : '{';
RightBrace : '}';
Semicolon : ';' ;
Equal : '==' ;
NotEqual : '!=' ;
PlusAssign : '+=' ;
MinusAssign : '-=' ; 
StarAssign : '*=' ; 
DivAssign : '/=' ;
Assign : '=' ;
ModAssign : '%=' ;
LessEqual : '<=' ; 
GreaterEqual : '>=' ; 
And : '&&' ; 
Or : '||' ; 
Not : '!' ;
Less : '<' ; 
Greater : '>' ;
Plus : '+' ;
Minus : '-' ;
Star : '*' ;
Div : '/' ;
Mod : '%' ; 

Identifier : IdentifierChar (IdentifierChar | Digit)* ;

fragment IdentifierChar : [a-zA-Z_];

BoolConstatnt : 'pravda' | 'blud';

DoubleConstant : Digit+ '.' Digit+;

IntegerConstant : Digit+ ;

fragment Digit : [0-9] ;

CharConstant : '\'' CCharOrEscaped '\'';

fragment CCharOrEscaped : ~['\''\\\r\n\t] 
						| CharEscaped ;
						
fragment CharEscaped : '\\' ['\''nrt\\];

StringConstant : '"' StrCharOrEscaped* '"' ;

fragment StrCharOrEscaped 	: ~["\\\r\n\t] 
							| StrEscaped ;
							
fragment StrEscaped : '\\' ["nrt\\];

Newline : ('\n' | '\r\n');
		
Whitespace : [ \t\r]+ -> skip ;

LineComment : '//' (~[\r\n])*? Newline; 
BlockComment : '/*' .*? '*/';

