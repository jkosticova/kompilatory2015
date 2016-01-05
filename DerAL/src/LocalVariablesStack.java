import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class LocalVariablesStack {
	public Stack<List<VariableInfo>> varStack;
	
	public LocalVariablesStack(){
		varStack = new Stack<List<VariableInfo>>();
	}
	
	public boolean isAlreadyDeclared(String varName){
		for(VariableInfo varInfo: varStack.peek()){
			if(varInfo.name.equals(varName)){
				return true;
			}
		}
		return false;
	}
	
	public void declareNewVariable(VariableInfo newVar){
		this.varStack.peek().add(newVar);
	}
	
	public void enterCodeBlock(){
		varStack.push(new ArrayList<VariableInfo>());
	}
	
	public void exitCodeBlock(){
		varStack.pop();
	}
	
	public VariableInfo findLocalVar(String varName){
		VariableInfo localVarInfoToRet = null;
		
		for(List<VariableInfo> localVarsList : this.varStack){
			for(VariableInfo varInfo: localVarsList){
				if(varInfo.name.equals(varName)){
					localVarInfoToRet = varInfo;
				}
			}
		}
		
		return localVarInfoToRet;
	}
}
