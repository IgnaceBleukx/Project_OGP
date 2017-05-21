package Statements;

import Expressions.Expression;
import asteroids.part3.programs.SourceLocation;

public class AssignementStatement extends Statement {
	
	public AssignementStatement(String variableName, Expression Value, SourceLocation scourcelocation){
		setValue(value);
		setVariableName(variableName);
	}
	
	public Expression getValue() {
		return value;
	}
	public void setValue(Expression value) {
		this.value = value;
	}

	public String getVariableName() {
		return variableName;
	}

	public void setVariableName(String variableName) {
		this.variableName = variableName;
	}

	private Expression value;
	private String variableName;
	
	
}
