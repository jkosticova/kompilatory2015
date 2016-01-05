
public class VariableInfo {
	public String name;
	public String nameInLLVMCode;
	public Type type;
	
	public VariableInfo(){
		name = "";
		nameInLLVMCode = "";
		type = new Type();
	}
}
