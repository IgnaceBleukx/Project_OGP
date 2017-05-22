package Expressions;

import java.util.List;

import asteroids.part3.programs.SourceLocation;

public class FunctionCallExpression extends ValueExpression {

	public FunctionCallExpression(String functionName, List<Expression> actualArs, SourceLocation sourceLocation){
		setFunctionName(functionName);
		setActualArgs(actualArgs);
		setSourceLocation(sourceLocation);
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
	public SourceLocation getSourceLocation() {
		return sourceLocation;
	}

	public void setSourceLocation(SourceLocation sourceLocation) {
		this.sourceLocation = sourceLocation;
	}
	private String functionName;
	private List<Expression> actualArgs;
	private SourceLocation sourceLocation;
}
