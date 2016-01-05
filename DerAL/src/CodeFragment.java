import java.util.List;

public class CodeFragment {

	private String code;
	private String resultRegister;
	private List<Integer> arrayAllocSizes;
	private Type resultType;
	
	public CodeFragment(){
		this.code = "";
		this.resultRegister = "";
		this.resultType = new Type();
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getResultRegister() {
		return resultRegister;
	}

	public void setResultRegister(String resultRegister) {
		this.resultRegister = resultRegister;
	}

	public Type getResultType() {
		return resultType;
	}

	public void setResultType(Type resultType) {
		this.resultType = resultType;
	}
	
	public List<Integer> getArrayAllocSizes() {
		return arrayAllocSizes;
	}

	public void setArrayAllocSizes(List<Integer> arrayAllocSizes) {
		this.arrayAllocSizes = arrayAllocSizes;
	}
	
	public void addCode(String code){
		this.code += code;
	}
	
	public void addCode(CodeFragment code){
		if(code == null){
			return;
		}
		this.code += code.getCode();
	}
	
	public String toString(){
		return this.code;
	}

}
