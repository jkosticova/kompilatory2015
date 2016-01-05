import java.util.ArrayList;
import java.util.List;


public class FunctionInfo {
	public String name;
	public List<FunctionArgInfo> args;
	public Type retType;
	public String code;
	
	public FunctionInfo(){
		name = "";
		args = new ArrayList<FunctionArgInfo>();
		retType = new Type();
		code = "";
	}
	
	public String getFunctionSignature(){
		String functionSignature = retType.toString() + " " + name + "(";
		
		for(int i=0;i<args.size();i++){
			if(i!=0){
				functionSignature += ", ";
			}
			functionSignature += args.get(i).type.toString();
		}
		
		functionSignature += ")";
		
		return functionSignature;
	}
	
	public boolean argNameAlreadyUsed(String argName){
		for(FunctionArgInfo fai : args){
			if(fai.name.equals(argName)){
				return true;
			}
		}
		return false;
	}
	
	public static boolean functionDeclarationMatchDefinition(FunctionInfo declaration, FunctionInfo definition){
		if(!declaration.retType.equals(definition.retType)){
			return false;
		}
		if(!declaration.name.equals(definition.name)){
			return false;
		}
		if(declaration.args.size() != definition.args.size()){
			return false;
		}
		for(int i=0;i<declaration.args.size();i++){
			if(!declaration.args.get(i).type.equals(definition.args.get(i).type)){
				return false;
			}
		}
		
		return true;
	}
}
