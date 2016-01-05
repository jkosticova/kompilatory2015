
public class Type {
	public enum TypeEnum { INT, CHAR, DOUBLE, BOOL, VOID, ARRAY_ALLOC };
	
	public TypeEnum type;
	public int arrayLevel = 0;
	
	public Type(){
		type = Type.TypeEnum.VOID;
	}
	
	public Type(Type type0){
		arrayLevel = type0.arrayLevel;
		type = type0.type;
	}
	
	
	public boolean equals(Object o){
		if(o instanceof Type){
			Type oT = (Type)o;
			return oT.type == this.type && oT.arrayLevel == this.arrayLevel;
		}
		return false;
	}
	
	public String toString(){
		String ret = "";
		
		if(type == TypeEnum.INT){
			ret += "cele";
		}
		else if(type == TypeEnum.CHAR){
			ret += "znak";
		}
		else if(type == TypeEnum.DOUBLE){
			ret += "realne";
		}
		else if(type == TypeEnum.BOOL){
			ret += "bool";
		}
		else if(type == TypeEnum.VOID){
			ret += "nist";
		}
		else if(type == TypeEnum.ARRAY_ALLOC){
			ret += "alokator pola";
		}
		
		for(int i=0;i<arrayLevel;i++){
			ret += "[]";
		}
		
		return ret;
	}
	
	/*
	typeName 	: Int
				| Unsigned
				| Char
				| Float
				| Bool
				| String ;
	*/
	public static Type getTypeFromTypeSpecificationCtx(DerALParser.TypeSpecificationContext ctx){
		Type typeToRet = new Type();
		typeToRet.arrayLevel = 0;
		
		if(ctx instanceof DerALParser.VoidTypeSpecificationContext){
			typeToRet.type = TypeEnum.VOID;
			return typeToRet;
		}
		
		DerALParser.TypeNameContext typeNameCtx;
		if(ctx instanceof DerALParser.ArrayAllocTypeSpecificationContext){
			DerALParser.ArrayAllocTypeSpecificationContext aaCtx = (DerALParser.ArrayAllocTypeSpecificationContext)ctx;
			typeNameCtx = aaCtx.typeName();
			typeToRet.arrayLevel = aaCtx.arrayIndex().size();
		}
		else if(ctx instanceof DerALParser.BasicTypeSpecificationContext){
			DerALParser.BasicTypeSpecificationContext btCtx = (DerALParser.BasicTypeSpecificationContext)ctx;
			typeNameCtx = btCtx.typeName();
		}
		else if(ctx instanceof DerALParser.ArrayNoAllocTypeSpecificationContext){
			DerALParser.ArrayNoAllocTypeSpecificationContext anaCtx = (DerALParser.ArrayNoAllocTypeSpecificationContext)ctx;
			typeNameCtx = anaCtx.typeName();
			typeToRet.arrayLevel = anaCtx.LeftBracket().size();
		}
		else{
			DerALCompilerGlobalState.reportSomethingStrange("Snažíme sa zistiť typ z nejakého čudného DerALParser.TypeSpecificationContext");
			return new Type();
		}
		
		if(typeNameCtx.Int() != null){
			typeToRet.type = Type.TypeEnum.INT;
		}
		else if(typeNameCtx.Double() != null){
			typeToRet.type = Type.TypeEnum.DOUBLE;
		}
		else if(typeNameCtx.Unsigned() != null){
			typeToRet.type = Type.TypeEnum.INT;
		}
		else if(typeNameCtx.Char() != null){
			typeToRet.type = Type.TypeEnum.CHAR;
		}
		else if(typeNameCtx.Bool() != null){
			typeToRet.type = Type.TypeEnum.BOOL;
		}
		else if(typeNameCtx.String() != null){
			typeToRet.type = Type.TypeEnum.CHAR;
			typeToRet.arrayLevel++;
		}
		
		return typeToRet;
	}
	
	public static String getLLVMTypeFromType(Type type){
		String ret = "";
		
		if(type.type == Type.TypeEnum.INT){
			ret += "i32";
		}
		else if(type.type == Type.TypeEnum.BOOL){
			ret += "i1";
		}
		else if(type.type == Type.TypeEnum.CHAR){
			ret += "i8";
		}
		else if(type.type == Type.TypeEnum.DOUBLE){
			ret += "double";
		}
		else if(type.type == Type.TypeEnum.VOID){
			ret += "void";
		}
		else if(type.type == Type.TypeEnum.ARRAY_ALLOC){
			return "< "+ Integer.toString(type.arrayLevel) + " x i32 >";
		}
		else{
			DerALCompilerGlobalState.reportSomethingStrange("Snažím sa získať typ pre nejaký čudný typ");
			return "TOTO BY SA NEMALO NIKDY VRATIT";
		}
		
		for(int i=0;i<type.arrayLevel;i++){
			ret += '*';
		}
		
		return ret;
	}
	
	public static String getLLVMTypeFromType(Type.TypeEnum type){
		if(type == Type.TypeEnum.INT){
			return "i32";
		}
		else if(type == Type.TypeEnum.BOOL){
			return "i1";
		}
		else if(type == Type.TypeEnum.CHAR){
			return "i8";
		}
		else if(type == Type.TypeEnum.DOUBLE){
			return "double";
		}
		else if(type == Type.TypeEnum.VOID){
			return "void";
		}
		else if(type == Type.TypeEnum.ARRAY_ALLOC){
			return "TOTO_ESTE_NEVIEM_AKO";
		}
		
		return "TOTO BY SA NEMALO NIKDY VRATIT";
	}
}
