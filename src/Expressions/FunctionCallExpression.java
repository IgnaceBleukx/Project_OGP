package Expressions;

import java.util.List;

public class FunctionCallExpression {

	public FunctionCallExpression(String functionName, List<Expression> actualArgs){
		setFunctionName(functionName);
		setActualArgs(actualArgs);
	}
	
	public String getFunctionName() {
		return functionName;
	}
	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}
	public List<Expression> getActualArgs() {
		return actualArgs;
	}
	public void setActualArgs(List<Expression> actualArgs) {
		this.actualArgs = actualArgs;
	}
	private String functionName;
	private List<Expression> actualArgs;
}
