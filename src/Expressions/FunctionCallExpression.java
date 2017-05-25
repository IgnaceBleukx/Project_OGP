package Expressions;

import java.util.List;

import Statements.BreakException;
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
	public double evaluate() throws ModelException, BreakException{
		List<Function> functions = this.getProgram().getFunctions();
		Function functionToExecute = null;
		for (Function function : functions){
			passInformation(function);
			if (function.getFunctionName().equals(this.getFunctionName())){
				functionToExecute = function;
			}
		}
		if (functionToExecute == null){
			throw new ModelException("The given functionname does not refer to a function in the program");
		}
		passInformation(functionToExecute);
		for(Expression expression : getActualArgs()){
			passInformation(expression);
			functionToExecute.addParameter(new Parameter(((ValueExpression) expression).evaluate()));
		}
		return (double) functionToExecute.execute();
	}
}
