package asteroids.model.programs;

import java.util.ArrayList;
import java.util.List;

import Expressions.Expression;
import Statements.Statement;
import Statements.ValueStatement;
import Statements.VoidStatement;
import asteroids.model.Program;
import asteroids.part3.programs.SourceLocation;
import asteroids.util.ModelException;

public class Function {

	public Function(String functionName, Statement body, SourceLocation sourceLocation){
		setFunctionName(functionName);
		setBody(body);
		setSourceLocation(sourceLocation);
	}
	
	private Program program;
	private Statement body;
	private String functionName;
	private SourceLocation sourceLocation;
	private Function function;
	private boolean whileState = false;
	private List<Expression> parameters = new ArrayList<Expression>();
	
	public Program getProgram() {
		return program;
	}
	public void setProgram(Program program) {
		this.program = program;
	}
	public Statement getBody() {
		return body;
	}
	public void setBody(Statement body) {
		this.body = body;
	}
	public String getFunctionName() {
		return functionName;
	}
	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}
	public SourceLocation getSourceLocation() {
		return sourceLocation;
	}
	public void setSourceLocation(SourceLocation sourceLocation) {
		this.sourceLocation = sourceLocation;
	}
	
	public List<Object> getVariables() {
		return variables;
	}
	public void addVariable	(Object variable) {
		this.variables.add(variable);
	}

	public Function getFunction() {
		return function;
	}
	public void setFunction(Function function) {
		this.function = function;
	}

	public boolean isWhileState() {
		return whileState;
	}
	public void setWhileState(boolean whileState) {
		this.whileState = whileState;
	}
	public List<Expression> getParameters() {
		return parameters;
	}
	public void setParameters(List<Expression> parameters) {
		this.parameters = parameters;
	}
	private List<Object> variables = new ArrayList<Object>();
	
	public double execute() throws ModelException{
		getBody().setFunction(this);
		getBody().setProgram(getProgram());
		if (getBody() instanceof VoidStatement){
			((VoidStatement) getBody()).execute();
			return Double.NaN;
		}	
		else{
			return ((ValueStatement) getBody()).execute();
		}
	}
	
	
	
	
	
}
