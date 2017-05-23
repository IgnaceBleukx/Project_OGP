package Expressions;

import java.util.List;


import asteroids.model.programs.Function;
import asteroids.part3.programs.SourceLocation;
import asteroids.util.ModelException;

public class FunctionCallExpression extends ValueExpression {

	public FunctionCallExpression(String functionName, List<Expression> actualArgs, SourceLocation sourceLocation){
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
	
	@Override
	public double evaluate() throws ModelException{
		List<Function> functions = this.getProgram().getFunctions();
		Function toExecute = null;
		for (Function function : functions){
			passInformation(function);
			if (function.getFunctionName().equals(this.getFunctionName())){
				toExecute = function;
			}
		}
		if (toExecute == null){
			throw new ModelException("The given functionname does not refer to a function in the program");
		}
		toExecute.setParameters(getActualArgs());
		return toExecute.execute();
	}
}
