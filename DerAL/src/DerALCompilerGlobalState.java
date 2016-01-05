import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DerALCompilerGlobalState {
	private String registerPrefix;
	public long line;
	public long lastUsedRegisterNo;
	public LocalVariablesStack localVarStack;
	public List<VariableInfo> globalVarList;
	public List<FunctionInfo> definedFunctionsList;
	public List<FunctionInfo> forwardDeclaredFunctionsList;
	public List<FunctionInfo> externFunctionsList;
	public boolean wasAnError;
	public Set<String> usedVarNamesInLLVMCode;
	public String globalVariablesDeclarationsLLVMCode;
	public List<String> reservedFunctionNames;
	public List<FunctionInfo> builtInFunctionsList;
	
	public DerALCompilerGlobalState(){
		line = 1;
		lastUsedRegisterNo = 1;
		localVarStack = new LocalVariablesStack();
		wasAnError = false;
		usedVarNamesInLLVMCode = new HashSet<String>();
		registerPrefix = ".";
		globalVarList = new ArrayList<VariableInfo>();
		globalVariablesDeclarationsLLVMCode = "";
		forwardDeclaredFunctionsList = new ArrayList<FunctionInfo>();
		externFunctionsList = new ArrayList<FunctionInfo>();
		
		reservedFunctionNames = new ArrayList<String>();
		initReservedFunctionNames();
		
		definedFunctionsList = new ArrayList<FunctionInfo>();
		
		builtInFunctionsList = new ArrayList<FunctionInfo>();
		initBuiltInFunctionsList();
	}
	
	private void initReservedFunctionNames(){
		reservedFunctionNames.add("tiskaj");
		reservedFunctionNames.add("vezni");
		reservedFunctionNames.add("tiskajRetazec");
		reservedFunctionNames.add("vezniRetazec");
		reservedFunctionNames.add("tiskajCele");
		reservedFunctionNames.add("vezniCele");
		reservedFunctionNames.add("tiskajRealne");
		reservedFunctionNames.add("vezniRealne");
		reservedFunctionNames.add("getchar");
		reservedFunctionNames.add("putchar");
		reservedFunctionNames.add("puts");
		reservedFunctionNames.add("gets");
		reservedFunctionNames.add("printf");
		reservedFunctionNames.add("scanf");
		reservedFunctionNames.add("putchar");
		reservedFunctionNames.add("main");
	}
	
	private void initBuiltInFunctionsList(){
		FunctionInfo tiskaj = new FunctionInfo();
		Type voidType = new Type(); voidType.type = Type.TypeEnum.VOID;
		tiskaj.retType = voidType;
		tiskaj.name = "tiskaj";
		Type i8Type = new Type(); i8Type.type = Type.TypeEnum.CHAR;
		FunctionArgInfo i8Arg = new FunctionArgInfo(); i8Arg.name = "c"; i8Arg.type = i8Type;
		tiskaj.args.add(i8Arg);
		builtInFunctionsList.add(tiskaj);
		
		FunctionInfo vezni = new FunctionInfo();
		vezni.name = "vezni";
		vezni.retType = i8Type;
		builtInFunctionsList.add(vezni);
		
		FunctionInfo tiskajRetazec = new FunctionInfo();
		tiskajRetazec.name = "tiskajRetazec";
		tiskajRetazec.retType = voidType;
		FunctionArgInfo stringArg = new FunctionArgInfo();
		Type stringType = new Type(); stringType.type = Type.TypeEnum.CHAR; stringType.arrayLevel = 1;
		stringArg.type = stringType;
		stringArg.name = "s";
		tiskajRetazec.args.add(stringArg);
		builtInFunctionsList.add(tiskajRetazec);	
		
		FunctionInfo vezniRetazec = new FunctionInfo();
		vezniRetazec.name = "vezniRetazec";
		vezniRetazec.retType = voidType;
		vezniRetazec.args.add(stringArg);
		builtInFunctionsList.add(vezniRetazec);
		
		FunctionInfo vezniCele = new FunctionInfo();
		vezniCele.name = "vezniCele";
		Type i32Type = new Type(); i32Type.type = Type.TypeEnum.INT;
		vezniCele.retType = i32Type;
		builtInFunctionsList.add(vezniCele);
		
		FunctionInfo tiskajCele = new FunctionInfo();
		tiskajCele.name = "tiskajCele";
		tiskajCele.retType = voidType;
		FunctionArgInfo i32arg = new FunctionArgInfo(); i32arg.type = i32Type; i32arg.name = "i";
		tiskajCele.args.add(i32arg);
		builtInFunctionsList.add(tiskajCele);
		
		FunctionInfo vezniRealne = new FunctionInfo();
		vezniRealne.name = "vezniRealne";
		Type doubleType = new Type(); doubleType.type = Type.TypeEnum.DOUBLE;
		vezniRealne.retType = doubleType;
		builtInFunctionsList.add(vezniRealne);
		
		FunctionInfo tiskajRealne = new FunctionInfo();
		tiskajRealne.name = "tiskajRealne";
		tiskajRealne.retType = voidType;
		FunctionArgInfo doubleArg = new FunctionArgInfo(); doubleArg.type = doubleType; doubleArg.name = "d";
		tiskajRealne.args.add(doubleArg);
		builtInFunctionsList.add(tiskajRealne);
	}
	
	public String getNextFreeRegister(){
		return this.registerPrefix + Long.toString(lastUsedRegisterNo++);
	}
	
	public String getLLVMVarNameForVar(String varName){
		for(long i = 0;i != -1;i++){
			String possibleName = varName + Long.toString(i);
			if(!usedVarNamesInLLVMCode.contains(possibleName)){
				usedVarNamesInLLVMCode.add(possibleName);
				return possibleName;
			}
		}
		
		reportError("Velice čunné, ale očivinne nestačilo 2^64 verzíí premennej s menom " + varName);
		return "haluz lebo java chce mat tu return";
	}
	
	public void reportError(String errMsg){
		System.err.println("Chyba na rádku číslo " + Long.toString(line) + ": " +errMsg);
		wasAnError = true;
	}
	
	public void reportError(String errMsg, boolean lineNo){
		String print = "";
		if(lineNo){
			print += "Chyba na rádku číslo " + Long.toString(line) + ": ";
		}
		else{
			print += "Globálna chyba: ";
		}
		print += errMsg;
		System.err.println(print);
		wasAnError = true;
	}
	
	public static void reportSomethingStrange(String strangeMsg){
		System.err.println("Oooops, toto by sa nemalo stať - ");
	}
	
	public boolean isGlobalNameDeclared(String name){
		for(VariableInfo globalVar : globalVarList){
			if(globalVar.name.equals(name)){
				return true;
			}
		}
		
		for(FunctionInfo function : definedFunctionsList){
			if(function.name.equals(name)){
				return true;
			}
		}
		
		return false;
	}
	
	public void addGlobalVariableDeclarationLLVMCode(String code){
		this.globalVariablesDeclarationsLLVMCode += code;
	}
	
	public void declareNewGlobalVar(VariableInfo varInfo){
		globalVarList.add(varInfo);
	}
	
	public VariableInfo findGlobalVarInfo(String varName){
		VariableInfo varInfoToRet = null;
		
		for(VariableInfo vi : globalVarList){
			if(vi.name.equals(varName)){
				varInfoToRet = vi;
			}
		}
		
		return varInfoToRet;
	}
	
	public boolean isGlobalAlreadyDeclared(String varName){
		for(VariableInfo vi : globalVarList){
			if(vi.name.equals(varName)){
				return true;
			}
		}
		return false;
	}
	
	//nepodporujem pretazovanie funkcii, moze byt iba jedna funkcia s tym istym menom
	public boolean isFunctionAlreadyDefined(String name){
		for(FunctionInfo fi : definedFunctionsList){
			if(fi.name.equals(name)){
				return true;
			}
		}
		for(FunctionInfo fi : externFunctionsList){
			if(fi.name.equals(name)){
				return true;
			}
		}
		
		return false;
	}
	
	public boolean isFunctionAlreadyForwardDeclared(String name){
		for(FunctionInfo fi : forwardDeclaredFunctionsList){
			if(fi.name.equals(name)){
				return true;
			}
		}
		return false;
	}
	
	public boolean isDeclaredFunctionDefined(FunctionInfo declaredFunctionInfo){
		for(FunctionInfo definedFunctionInfo : definedFunctionsList){
			if(FunctionInfo.functionDeclarationMatchDefinition(declaredFunctionInfo, definedFunctionInfo)){
				return true;
			}
		}
		return false;
	}
	
	public FunctionInfo findFunction(String name){
		for(FunctionInfo definedFunction : definedFunctionsList){
			if(definedFunction.name.equals(name)){
				return definedFunction;
			}
		}
		
		for(FunctionInfo declaredFunction : forwardDeclaredFunctionsList){
			if(declaredFunction.name.equals(name)){
				return declaredFunction;
			}
		}
		
		for(FunctionInfo externFunction : externFunctionsList){
			if(externFunction.name.equals(name)){
				return externFunction;
			}
		}
		
		for(FunctionInfo builtInFunction : builtInFunctionsList){
			if(builtInFunction.name.equals(name)){
				return builtInFunction;
			}
		}
		
		return null;
	}
	
	public boolean isFunctionNameReserved(String name){
		return reservedFunctionNames.contains(name);
	}
		
}



























